<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>员工授权</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@ include file="/common/header.jsp"%>
<script type="text/javascript">
	var datagrid;
	$(function() {
		datagrid = $('#datagrid').datagrid({
			url : '${app}/role/findAllNoPage',
			title : '',
			pagination : false,
			fit : true,
			toolbar : "#toolbar",
			border : false,
			idField : 'role_id',
			singleSelect : false,
			columns : [ [ {
				field:'ck',
				checkbox : true
			}, {
				field : 'role_code',
				title : '角色代码',
				width : 260
			}, {
				field : 'role_name',
				title : '角色中文名',
				width : 240
			}, {
				field : 'remark',
				title : '角色描述',
				width : 750
			}]]
		});
		
	});

	//提交
	function submitForm() {
		var rows = datagrid.datagrid('getSelections');
		if(rows.length == 0){
			$.messager.show({
				title:'信息提示',
				msg:'请选择要授权的角色!',
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
   				url : "${app}/employee/doAddEmpAuthorization/${empIds}/" + roleIds,
				type : "post",
				dataType : "json",
				async : false,
				success : function(msg){
   					closeMask();
					parent.createTab('${app}/employee/toEmpList?messageCode=' + msg.messageCode,'员工管理');
					parent.closeTab("员工授权");
				},		
				error : function(){
					closeMask();
					$.messager.alert("提示信息","系统错误！","error");
				}
   			});
		}
	}

	//取消
	function resetForm() {
		parent.closeTab("员工授权");
	}
	
	
</script>
</head>

<body class="easyui-layout" fit="true" style="width: 100%; height: 100%;">
	<div region="center" border="false" style="overflow: hidden;">
		<table id="datagrid"></table>
	</div>
	<div id="toolbar" style="display: none;">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="submitForm();">提交</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="javascript:resetForm();">返回</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
	</div>
</body>
</html>
