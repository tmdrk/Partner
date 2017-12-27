<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<%@ include file="/common/zTree.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>部门管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<style type="text/css">
		.trreDiv {
			float: left;
			width: 23%;
			height: 100%;
			font-size: 15px;
			border: 1px solid #6699FF;
		}
		#treeDept {
			float: left;
			width: 95%;
			height: 90%;
			overflow: auto;
		}
	</style>
	<script type="text/javascript">
		var zTreeObj = null;
		var deptDialog = null;
		$(document).ready(function(){
			var setting = {
					data: {
						key:{name: 'dept_name'},
						simpleData: {
							enable: true,
							idKey: 'dept_id',
							pIdKey: 'parent_id'
						}
					},
					view: {
						showTitle: false,
						expandSpeed: 0
					},
					callback: {
						onRightClick : zTreeOnRightClick
					}
			};
			
			var zTreeNodes = null;
			//查询树节点
			$.ajax({
				url : "${app}/department/departments",
				type : "post",
				dataType : "json",
				async : false,
				success : function(msg){
					if(msg != null && msg.length != 0){
						zTreeNodes = msg;
					}
				},		
				error : function(){
					$.messager.alert("提示信息","系统错误！","info");
				}
			});
			
			zTreeObj = $.fn.zTree.init($("#treeDept"), setting, zTreeNodes);
			zTreeObj.expandAll(true);//全部展开
		});
		
		//右键回调事件
		function zTreeOnRightClick(event, treeId, treeNode) {
			if(treeNode != null){
				zTreeObj.selectNode(treeNode);//选中当前节点
				$("#deptId").val(treeNode.dept_id);
				$('#rightMenu').menu('show', {
					left: event.pageX,
					top: event.pageY
				});
			}
		}
		
		//打开设置提前还款时间输入框
		function openDeptDialog(v_url,v_oper){
			deptDialog = $('#deptDialog').dialog({  
				top:100,
				title: v_oper,   
				width: 350,   
				height: 220,  
				closed: false,   
				cache: false,   
				href: v_url,   
				modal: true
			}); 
		}
		
		//添加一级部门
		function addBigDept(){
			var url = "${app}/department/toInputDept";
			openDeptDialog(url,"添加一级部门");
		}
		
		//添加部门
		function addDept(){
			var url = "${app}/department/toInputDept?parentId=" + $("#deptId").val();
			openDeptDialog(url,"添加部门");
		}
		
		//修改部门
		function editDept(){
			var url = "${app}/department/toInputDept?deptId=" + $("#deptId").val();
			openDeptDialog(url,"修改部门");
		}
		
		//删除部门
		function removeDept(){
			var nodes = zTreeObj.getSelectedNodes();//获取 zTree当前被选中的节点数据集合
			var isParent = nodes[0].isParent;//记录 treeNode 节点是否为父节点。
			if(isParent){
				$.messager.alert("信息提示", "父节点不能执行删除操作!!", "warning",function(){});
				return;
			}
			$.messager.confirm("请确认", "您确认要删除该部门吗？", function(b){
				if(b){
					openMask();
					$.ajax({
						async:false,
						type:'post',
						url:"${app}/department/doDelDept/" + $("#deptId").val(),
						dataType:'json',
						success:function(data){
							closeMask();
							parent.createTab('${app}/department/toListTreeDept?messageCode=' + data.messageCode,'部门管理');
						}
					});
				}
			});
		}
		
		//提交
		function submitFn(){
			var deptForm = $("#deptForm");
			deptForm.form('submit',{
				url:'${app}/department/doAddEditDept',
				onSubmit:function(){
					if(deptForm.form("validate")){
				    	openMask();
						return true;
					}else{
						return false;
					}
				},
				success:function(msg){
					closeMask();
					var data = eval("(" + msg + ")");
					parent.createTab('${app}/department/toListTreeDept?messageCode=' + data.messageCode,'部门管理');
				}
			});
		}
		
		//取消
		function cancelFn(){
			deptDialog.dialog('close');
		}
	</script>
  </head>
  <body style="background-color: white;">
  	<div class="trreDiv">
	  	<a id="addMenu" onclick="addBigDept()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加一级部门</a>
		<ul id="treeDept" class="ztree"></ul>
	</div>
	<input type="hidden" id="deptId" name="deptId"/>
	<div id="rightMenu" class="easyui-menu" style="width: 100px;display: none;">
		<a id="addMenu" onclick="addDept()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加部门</a>
		<a id="editMenu" onclick="editDept()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改部门</a>
		<a id="removeMenu" onclick="removeDept()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除部门</a>
	</div>
	<div id="deptDialog"></div>
  </body>
</html>