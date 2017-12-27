<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<%@ include file="/common/zTree.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>组织架构管理</title>
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
		.ztree li a span.ico_open::after {
            width: 0;
            height: 0;
            border: 4px solid transparent;
            border-left: 8px solid #808080;
            content: "";
            margin: 0;
            cursor: pointer;
            display: inline-block;
            position: relative;
            left: -6px;
            top: 4px;
            transform: rotate(90deg);
        }
        .ztree li a span.ico_close::after {
            width: 0;
            height: 0;
            border: 4px solid transparent;
            border-left: 8px solid #808080;
            content: "";
            margin: 0;
            cursor: pointer;
            display: inline-block;
            position: relative;
            left: -6px;
            top: 4px;
            transform: rotate(0deg);
        }
        .switch {
            display: none !important;
        }
	</style>
	<script type="text/javascript">
	var zTreeObj = null;
	var partnerOrganizationDialog = null;
	var datagrid = null;
	$(document).ready(function(){
		var setting = {
				data: {
					key:{name: 'organization_name'},
					simpleData: {
						enable: true,
						idKey: 'organization_id',
						pIdKey: 'parent_id'
					}
				},
				view: {
					showTitle: false,
					expandSpeed: 0,
					showIcon: false,
					dblClickExpand: false,
					showLine: false
				},
				callback: {
					onClick: onNodeClick //节点被点击的事件回调函数
				}
		};
		
		var zTreeNodes = null;
		//查询树节点
		$.ajax({
			url : "${app}/partnerOrganization/organizations",
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
		
		zTreeObj = $.fn.zTree.init($("#treePartnerOrganization"), setting, zTreeNodes);
		zTreeObj.expandAll(false);//全部展开
		
	});
	
	//点击节点触发回调事件
	function onNodeClick(e,treeId, treeNode) {
		if(treeNode != null){
		    var zTree = $.fn.zTree.getZTreeObj("treePartnerOrganization");
			var sNodes = zTree.getSelectedNodes();
			var parent_id =  treeNode.parent_id;
			var organization_id = treeNode.organization_id;
			if (sNodes.length > 0) {
				var isOpen = sNodes[0].open;
				if(isOpen){
					//不显示当前节点的子节点数据,显示与当前节点同级的节点
				}else{
					$("#toolbar").show();
					showOrganizationInfo(organization_id);//显示当前节点的子节点数据
				}
			}
		    zTree.expandNode(treeNode);
		}
	}
	
	//显示当前节点的子节点数据
	function showOrganizationInfo(organization_id){
		datagrid = $('#datagrid').datagrid({
			url : '${app}/partnerOrganization/organizationChildInfo?organizationId='+organization_id,
			title : '',
			pagination : true,
			pageSize : <%=Constants.PAGE_SIZE%>,
			pageList : [10,20,30,40,50],
			fit:true,
			border : false,
			toolbar:"#toolbar",
			idField : 'organization_id',
			columns : [[{
				field:'rowNumbers',  
			    title: '序号',  
			    width: 50,
			    formatter: function(val,rec,index){  
			    	var op = $('#datagrid').datagrid('options');  
			    	return op.pageSize * (op.pageNumber - 1) + (index + 1);  
			    }  
			},{
				field : 'organization_id',
				title : '组织ID',
				width : 100,
				hidden : true
			},{
				field : 'organization_no',
				title : '组织编号',
				width : 150
			},{
				field : 'organization_name',
				title : '组织名称',
				width : 150
			},{
				field : 'short_name',
				title : '简称',
				width : 150
			},{
				field : 'principal',
				title : '负责人',
				width : 150
			},{
				field : 'previousOrganizationName',
				title : '上级组织',
				width : 150
			},{
				field : 'operate',
				title : '操作',
				width : 100,
				formatter:function(value,rowData,rowIndex){
					var previousOrganizationName = rowData.previousOrganizationName;
					return retStr = "<span style='color:green;' onclick = updatePartnerOrganizationInfo(" + rowData.organization_id + ")>编辑</span>";
				}
			}]]
		});
	}
	
	//打开设置提前还款时间输入框
	function openPartnerOrganizationDialog(v_url,v_oper){
		partnerOrganizationDialog = $('#partnerOrganizationDialog').dialog({  
			top:100,
			title: v_oper,
			maximizable : true,
			resizable : true,
			width: 350,   
			height:245,  
			closed: false,   
			cache: false,   
			href: v_url,   
			modal: true
		}); 
	}
	
	//添加组织信息
	function addPartnerOrganizationInfo(){
		var treeObj = $.fn.zTree.getZTreeObj("treePartnerOrganization");
		var nodes = treeObj.getSelectedNodes();
		var organization_id = nodes[0].organization_id;
		var url = "${app}/partnerOrganization/toAddPartnerOrganization?parentId=" + organization_id;
		openPartnerOrganizationDialog(url,"添加");
	}
	
	//编辑组织信息
	function updatePartnerOrganizationInfo(organization_id,previousOrganizationName){
		var url = "${app}/partnerOrganization/toEditPartnerOrganization?organizationId=" + organization_id;
		openPartnerOrganizationDialog(url,"编辑");
	}
	
	//提交
	function submitFn(){
		var organizationForm = $("#organizationForm");
		organizationForm.form('submit',{
			url:'${app}/partnerOrganization/doAddEditOrganization',
			onSubmit:function(){
				if(organizationForm.form("validate")){
			    	openMask();
					return true;
				}else{
					return false;
				}
			},
			success:function(msg){
				closeMask();
				var data = eval("(" + msg + ")");
				parent.createTab('${app}/partnerOrganization/toListTreeOrgan?messageCode=' + data.messageCode,'组织架构管理');
			}
		});
	}
	
	//取消
	function cancelFn(){
		partnerOrganizationDialog.dialog('close');
	}
</script>
</head>
<body style="background-color: white;" class="easyui-layout">
  	<div id="cc" class="easyui-layout" style="width:100%;height:100%;"> 
	  	<div class="trreDiv" data-options="region:'west',title:'维护组织结构：合伙人事业部',split:true" style="width:200px;">
			<ul id="treePartnerOrganization" class="ztree"  style="margin-left:10px;"></ul>
		</div>
		<input type="hidden" id="organizationId" name="organizationId"/>
		<div id="partnerOrganizationDialog"></div>
    	<div data-options="region:'center',title:'组织展开'" style="padding:5px;background:#eee;">
    		<div id="toolbar" style="display: none;">
	    		<div class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addPartnerOrganizationInfo();">新建</div>
				<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
			</div>
    		<table id="datagrid"></table> 
    	</div>	
		</div>
	</div>  
</body>
</html>