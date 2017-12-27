<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<%@ include file="/common/zTree.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>菜单管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<style type="text/css">
		legend{
			font-size: 15px;
		}
		textarea{
			height: 60px; 
			width: 280px;
			font-size: 13px;
		}
		#menu {
			float: left;
			width: 95%;
			height: 90%;
			overflow: auto;
			display: none;
		}
		.menuDiv {
			float: left;
 			background: #fafafa;
			padding: 5px;
 			display: none;
			width:55%;
		}
		.message{ 
			width:100%; 
			height:5%; 
			margin:5px 0px 0px 5px; 
			font-weight:bold; 
			font-size:15px; 
			color:#044b77;
		}
		.blank{
			float: left;
			width: 5%;
			height: 90%;
		}
		.div1 {
			float: left;
			width: 23%;
			height: 100%;
			font-size: 15px;
			border: 1px solid #6699FF;
		}
		.FormF {
			background: #FFF;
			padding: 3px 0 4px 5px;
			font-size: 12px;
		}
	</style>
	
	<script type="text/javascript">
		var zTreeObj;
		var setting = {
			edit: {
				drag : {
					prev : dropPrev,
					inner : false,
					next : dropNext
				},
				enable : true,
				showRemoveBtn : false,
				showRenameBtn : false
			},
			data : {
				key : {
				    name : 'name_zh'
				},
				simpleData : {
					enable : true,
					idKey : 'menu_id',
					pIdKey : 'parent_id',
					rootPId : '0'
				}
			},
			view : {
				showIcon : true,
				showTitle : false,
				expandSpeed : "fast"
			},
			callback : {
 				onRightClick : zTreeOnRightClick,
 				beforeRemove : zTreeBeforeRemove,
 				onRemove : zTreeOnRemove,
 				onDrop : zTreeOnDrop
			}
		};
		$(document).ready(function(){
			var zTreeNodes = null;
			//查询树节点
			$.ajax({
				url : "${app}/menu/menus",
				type : "post",
				dataType : "json",
				async : false,
				success : function(msg){
					if(msg != null && msg.length != 0){
						zTreeNodes = msg;
					}else{
						//如果没有数据，显示添加菜单组件
						addMenu();
					}
				},		
				error : function(){
					$.messager.alert("提示信息","系统错误！","info");
				}
			});
			zTreeObj = $.fn.zTree.init($("#menu"), setting, zTreeNodes);
			$("#menu").show();
		});
		/**************拖拽排序**************/
		//拖拽到目标节点时，设置是否允许移动到目标节点前面的操作
		function dropPrev(treeId, treeNodes, targetNode){
			if(treeNodes[0].isParent || treeNodes[0].level == 0){
				if(targetNode.level == 0){
					return true;
				}else{
					return false;
				}
			}else{
				var pTreeNode = treeNodes[0].getParentNode();
				var pTargetNode = targetNode.getParentNode();
				if(!pTargetNode){
					return false;
				}else{
					if(pTreeNode.menu_id == pTargetNode.menu_id){
						return true;
					}else{
						return false;
					}
				}
				
			}
		}
		//拖拽到目标节点时，设置是否允许移动到目标节点后面的操作
		function dropNext(treeId, treeNodes, targetNode){
			if(treeNodes[0].isParent || treeNodes[0].level == 0){
				if(targetNode.level == 0){
					return true;
				}else{
					return false;
				}
			}else{
				var pTreeNode = treeNodes[0].getParentNode();
				var pTargetNode = targetNode.getParentNode();
				if(!pTargetNode){
					return false;
				}else{
					if(pTreeNode.menu_id == pTargetNode.menu_id){
						return true;
					}else{
						return false;
					}
				}
			}
		}
		//拖拽结束后的回调
		function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType, isCopy){
			if(targetNode != null){
				var nodesSortParam = "";
				if(targetNode.isParent){
					var pNodes = zTreeObj.getNodesByParam("parent_id", targetNode.parent_id, null);
					for(var i=0; i<pNodes.length; i++){
						nodesSortParam += pNodes[i].menu_id + "," + zTreeObj.getNodeIndex(pNodes[i]) + "$";
					}
				}else{
					var cNodes = targetNode.getParentNode().children;
					for(var i=0; i<cNodes.length; i++){
						nodesSortParam += cNodes[i].menu_id + "," + zTreeObj.getNodeIndex(cNodes[i]) + "$";
					}
				}
				
				nodesSort(nodesSortParam.substring(0, nodesSortParam.length-1));
			}
		}
		//拖动节点排序
		function nodesSort(nSParam){
			$.ajax({
				url : "${app}/menu/nodesSort",
				type : "post",
				data : {'nSParam' : nSParam},
				async : false,
				dataType:'json',
				success:function(msg){
					if(msg.requestState == 'true'){
						$.messager.show({
							title:'信息提示',
							msg:'排序成功!',
							timeout:5000,
							showType:'slide'
						});
					}else{
						$.messager.show({
							title:'信息提示',
							msg:'排序失败!',
							timeout:5000,
							showType:'slide'
						});
					}
				},
				error : function(){
					$.messager.alert("提示信息","系统错误！","info");
				}
			});
		}
		/**************拖拽排序**************/
		//右键回调
		function zTreeOnRightClick(event, treeId, treeNode) {
			if (!treeNode) {
				zTreeObj.cancelSelectedNode();
			} else if (treeNode) {
				$("#menuDiv").fadeOut();
				$(".div2").fadeOut();
				zTreeObj.selectNode(treeNode, false);
				if(!treeNode.getParentNode()){
					showRightMenu("root", event.clientX, event.clientY, treeNode);
				}else{
					showRightMenu("node", event.clientX, event.clientY, treeNode);
				}
			}
		}
		//显示右键菜单
		function showRightMenu(v_nodeType, x, y, treeNode) {
			$("#rightMenu").show();
			if (v_nodeType == "root") {
				$("#grant").hide();
			} else if (v_nodeType == "node") {
				$("#grant").show();
			}
			$("#rightMenu").css({"top":y+"px", "left":x+"px", "visibility":"visible"});
		}
		//子节点选中事件
		function selectedMenuNode(){
			if($("#pid").length == 0 && $("#menuUrl").length == 0){
				$("#pidTr").append(afterContentSelectedNode1);
				$("#menuUrlTr").append(afterContentSelectedNode2);
				//查询所用的根节点
				$('#pid').combobox({
					url : '${app}/menu/selectMenuRoot',
					valueField : 'menu_id',
					textField : 'name_zh'
				});
				$.parser.parse("#menuUrlTr");
			}
		}
		//父节点选中事件
		function selectedMenuRoot(){
			$("#pidTr").children().remove();
			$("#menuUrlTr").children().remove();
		}
		//添加菜单
		function addMenu() {
			$("#addTitle").html("添加菜单");
			if($("#rootType1").length == 0){
				$("#menuTypeTr").append(afterContentSelectAdd);
			}
			if($("#menuUrl").length != 0 || $("#pid").length != 0){
				$("#menuUrlTr").children().remove();
				$("#pidTr").children().remove();
			}
			$("#menuForm")[0].reset();
			$("#menuId").val("");
			$("#nameZh").textbox('setValue',"");
			$("#nameEn").textbox('setValue',"");
			$("#remark").textbox('setValue',"");
			$(".div2").hide();
			$("#menuDiv").show();
		}
		//修改菜单
		function editMenu() {
			//此处可以去zTree里直接取值给表单赋值，不用去查询DB
			var selectNode = zTreeObj.getSelectedNodes()[0];
			$("#nameZh").textbox('setValue',selectNode.name_zh);
			$("#nameEn").textbox('setValue',selectNode.name_en);
			$("#remark").textbox('setValue',selectNode.remark);
			
			$("#menuId").val(selectNode.menu_id);
			if($("#menuTypeTr").length != 0){
				$("#menuTypeTr").children().remove();
			}
			if($("#pidTr").length != 0){
				$("#pidTr").children().remove();
			}
			//如果为子菜单
			if(selectNode.menu_type == "1"){
				if($("#menuUrl").length == 0){
					$("#menuUrlTr").append(afterContentSelectedNode2);
					$.parser.parse("#menuUrlTr");
					$("#menuUrl").textbox('setValue',selectNode.menu_url);
				}else{
					$("#menuUrl").textbox('setValue',selectNode.menu_url);
				}
			}else if(selectNode.menu_type == "0"){
				$("#menuUrlTr").children().remove();
			}
			$("#addTitle").html("修改菜单");
			$(".div2").hide();
			$("#menuDiv").show();
		}
		//选择"子菜单"追加的内容
		var afterContentSelectedNode1 = 
			"<td class='tdR'>" +
				"<span style='color: red'>*</span>根菜单：" +
			"</td>" +
			"<td class='FormF'>" +
				"<input id='pid' name='parentId' class='easyui-combobox' editable='false' panelHeight='150px' style='width: 280px;height: 24px;' required='true'/>" +
			"</td>";
		var afterContentSelectedNode2 = 
		   	"<td class='tdR'>" +
				"<span style='color: red'>*</span>菜单路径：" +
			"</td>" +
			"<td class='FormF'>" +
				"<input id='menuUrl' name='menuUrl' class='easyui-textbox' style='width: 280px;height: 24px;' data-options=\"required:true,validType:['length[0,100]']\"/>" +
			"</td>";
		//选择"添加菜单"追加的内容
		var afterContentSelectAdd = 
			"<td class='tdR'>" +
				"<span style='color: red'>*</span>菜单类型：" +
			"</td>" +
			"<td class='FormF'>" +
				"<input type='radio' id='rootType1' name='menuType' value='0' onclick='isSelected();'>根菜单" +
				"&nbsp;&nbsp;" +
				"<input type='radio' id='nodeType1' name='menuType' value='1' onclick='isSelected();'>子菜单 " +
			"</td>";
		//添加选中根目录/子目录事件
		function isSelected(){
			if($("#nodeType1").is(":checked")){
				selectedMenuNode();
			}else if($("#rootType1").is(":checked")){
				selectedMenuRoot();
			}
		}
		//删除菜单
		function removeMenu(){
			zTreeObj.removeNode(zTreeObj.getSelectedNodes()[0], true);
		}
		//删除前回调
		function zTreeBeforeRemove(treeId, treeNode) {
			if(treeNode.isParent){
				$.messager.alert("提示信息","不能删除父节点","warning");
				return false;
			}else{
				return confirm("是否删除“"+treeNode.name_zh+"”节点");
			}
		}
		//删除后回调
		function zTreeOnRemove(event, treeId, treeNode){
			$.ajax({
				url : "${app}/menu/deleteMenu/" + treeNode.menu_id,
				type : "post",
				dataType : "json",
				async : false,
				success : function(obj){
					var msg = "";
					if(obj.messageCode == "0005"){
						msg = "删除成功!";
					}else if(obj.messageCode == "0006") {
						msg = "删除失败!";
					}
					$.messager.show({
						title:'信息提示',
						msg:msg,
						timeout:5000,
						showType:'slide'
					});
				},		
				error : function(){
					$.messager.alert("提示信息","系统错误！","error");
				}
			});
			zTreeObj.refresh();
			var nodes = zTreeObj.getNodes();
			if(nodes.length == 0){
				addMenu();
			};
		}
		//菜单中文名不能重复
		function checkMenuNameZh(){
			var flag = false;
			menuId = $("#menuId").val();
			nameZh = $("#nameZh").val();
			$.ajax({
				url : "${app}/menu/checkMenuIsRepeat",
				type : "post",
				data : {menuId : menuId, nameZh : nameZh},
				dataType : "json",
				async : false,
				success : function(msg){
					if(msg.isRepeat == "repeat"){
						$.messager.alert("提示信息","菜单中文名称不能重复!","warning");
						flag = false;
					}else if(msg.isRepeat == "noRepeat"){
						flag = true;
					}
				},		
				error : function(){
					$.messager.alert("提示信息","系统错误！","error");
					flag = false;
				}
			});
			
			return flag;
		}
		//菜单英文名不能重复
		function checkMenuNameEn(){
			var flag = false;
			menuId = $("#menuId").val();
			nameEn = $("#nameEn").val();
			$.ajax({
				url : "${app}/menu/checkMenuIsRepeat",
				type : "post",
				data : {menuId : menuId, nameEn : nameEn},
				dataType : "json",
				async : false,
				success : function(msg){
					if(msg.isRepeat == "repeat"){
						$.messager.alert("提示信息","菜单英文名称不能重复!","warning");
						flag = false;
					}else if(msg.isRepeat == "noRepeat"){
						flag = true;
					}
				},		
				error : function(){
					$.messager.alert("提示信息","系统错误！","error");
					flag = false;
				}
			});
			
			return flag;
		}
		//菜单授权前
		var roleDatagrid = "";
		function authorizeMenu(){
			if(roleDatagrid != ""){
				$("#roleDatagrid").datagrid("reload");
			}
			$("#menuDiv").hide();
			var div2Css = {
				float: "left",
				width: "70%",
				height: "100%",
				display: "block",
				border: "1px solid #6699FF"
			};
			$(".div2").css(div2Css);
			var menuId = zTreeObj.getSelectedNodes()[0].menu_id;
			roleDatagrid = $("#roleDatagrid").datagrid({
				url : '${app}/role/authorizationMenuList/' + menuId,
				title : '',
				fit : true,
				striped : true,
				toolbar:"#roleToolbar",
				border : false,
				idField : 'role_id',
				columns : [[{
					field:'ck',
					checkbox:true
				},{
					field : 'role_code',
					title : '角色代码',
					width : 240,
					sortable : true
				},{
					field : 'role_name',
					title : '角色中文名',
					width : 220,
					sortable : true
				},{
					field : 'remark',
					title : '角色描述',
					width : 450,
					sortable : true
				}]],
				onLoadSuccess : function(data){
					$.each(data.rows, function(key, value){
						if(value.is_authorized == "Y"){
							roleDatagrid.datagrid('selectRow', key);
						}else if(value.is_authorized == "N"){
							roleDatagrid.datagrid('unselectRow', key);
						}
					});
				}
			});
		}
		// roleDatagrid查询
		function searchFun() {
			roleDatagrid.datagrid('load',serializeObject($("#searchForm")));
		}
		//执行菜单授权
		function doAuthorizeMenu(){
			var menuId = zTreeObj.getSelectedNodes()[0].menu_id;
			var rows = roleDatagrid.datagrid('getSelections');
			if(rows.length == 0){
				$.messager.show({
					title:'信息提示',
					msg:'请选择记录!',
					timeout:5000,
					showType:'slide'
				});
				return false;
			}else{
				openMask();
				var roleIds = "";
	   			for(var i = 0; i < rows.length; i++){
	   				roleIds += rows[i].role_id+",";
	   			}
	   			roleIds = roleIds.substr(0, roleIds.length-1);
	   			$.ajax({
	   				url : "${app}/role/doAuthorizeMenu",
					type : "post",
					data : {"menuId" : menuId, "roleIds" : roleIds},
					dataType : "json",
					async : false,
					success : function(msg){
						closeMask();
						if(msg.messageCode == "0014"){
							$.messager.show({
								title:'信息提示',
								msg:'授权成功!',
								timeout:5000,
								showType:'slide'
							});
						}else if(msg.messageCode == "0015"){
							$.messager.show({
								title:'信息提示',
								msg:'授权失败!',
								timeout:5000,
								showType:'slide'
							});
						}
					},		
					error : function(){
						closeMask();
						$.messager.alert("提示信息","系统错误！","error");
					}
	   			});
			}
		}
		//提交
		function submitForm(){
			var menuForm = $("#menuForm");
			var radioNumAdd = $("[type=radio]").length == 2;
			var radioNumEdit = $("[type=radio]").length == 0;
			var radioChecked = $("[type=radio]:checked").length > 0;
			menuForm.form('submit',{
				url:'${app}/menu/addOrUpdateMenu',
				onSubmit:function(){
					if((radioNumAdd && radioChecked) || radioNumEdit){
						if(menuForm.form('validate')){
							if(checkMenuNameZh() && checkMenuNameEn()){
								openMask();
								return true;
							}else{
								return false;
							}
						}else{
							return false;
						}
					}else{
						$.messager.alert("提示信息","请选择菜单类型","info");
						return false;
					}
				},
				success:function(data){
					closeMask();
					var obj = eval("(" + data + ")");
					parent.refreshTab("${app}/menu/toMenuList?messageCode=" + obj.messageCode,"菜单管理");
				}
			});
		}
	</script>
  </head>
  <body style="background-color: white;">
		<div class="div1">
			<div id="message" class="message">点击右键菜单操作</div>
			<div id="menu" class="ztree"></div>
		</div>
		<div id="blank" class="blank"></div>
		<div id="menuDiv" class="menuDiv">
			<form id="menuForm" class="easyui-form" method="post" modelAttribute="menu">
				<input type="hidden" id="menuId" name="menuId">
				<fieldset>
					<legend id="addTitle"></legend>
					<table class="tableForm" border="0" width="80%">
						<tr id="menuTypeTr">
						</tr>
						<tr id="pidTr">
						</tr>
						<tr id="menuUrlTr">
						</tr>
						<tr>
							<td class="tdR">
								<span style="color: red">*</span>菜单中文名称：
							</td>
							<td class="FormF">
								<input id="nameZh" name="nameZh" class="easyui-textbox" data-options="required:true,validType:['length[0,30]','chineseRule']" style="width: 280px;height: 24px;" />
							</td>
						</tr>
						<tr>
							<td class="tdR">
								<span style="color: red">*</span>菜单英文名称：
							</td>
							<td class="FormF">
								<input id="nameEn" name="nameEn" class="easyui-textbox" data-options="required:true,validType:['length[0,40]','englishRule']" style="width: 280px;height: 24px;" />
							</td>
						</tr>
						<tr>
							<td class="tdR">
								菜单描述：
							</td>
							<td class="FormF">
								<input id="remark" name="remark" class='easyui-textbox' data-options="multiline:true,validType:['length[0,100]']" style="width:330px;height:60px"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: center">
								<a id="btnSubmit" class="easyui-linkbutton" onclick="submitForm()" iconCls="icon-ok">提交</a>&nbsp;&nbsp;
							</td>
						</tr>
					</table>
				</fieldset>
			</form>
		</div>
		<div class="div2" style="display: none; height:100%;" fit="true">
			<div region="center" border="false" style="height:100%; overflow: hidden;">
  				<table id="roleDatagrid"></table>  
  			</div>
		</div>
		<div id="roleToolbar" style="display: none;">
	  		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="doAuthorizeMenu();">菜单授权</a>
			<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
		</div>
		<div id="rightMenu" class="easyui-menu" style="width: 100px;">
			<a id="addMenu" onclick="addMenu()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加菜单</a>
			<a id="editMenu" onclick="editMenu()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改菜单</a>
			<a id="removeMenu" onclick="removeMenu()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除菜单</a>
			<a id="grant" onclick="authorizeMenu()" class="easyui-linkbutton" data-options="iconCls:'icon-tip',plain:true">菜单授权</a>
		</div>
	</body>
</html>