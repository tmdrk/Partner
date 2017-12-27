<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>控件授权</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@ include file="/common/header.jsp"%>
<script type="text/javascript">
	var datagrid;
	$(function() {
		datagrid = $('#datagrid').datagrid({
			url : '${app}/role/authorizationControlList/${pageControlInfo.control_id}',
			title : '',
			pagination : false,
			fit : true,
			toolbar : "#toolbar",
			border : false,
			idField : 'role_id',
			singleSelect : false,
			columns : [[{
				field:'ck',
				checkbox : true
			},{
				field : 'role_code',
				title : '角色代码',
				width : 280
			},{
				field : 'role_name',
				title : '角色中文名',
				width : 180
			},{
				field : 'remark',
				title : '角色描述',
				width : 650
			}]],
			onLoadSuccess : function(data){
				$.each(data.rows, function(key, value){
					if(value.is_authorized == "Y"){
						datagrid.datagrid('selectRow', key);
					}else if(value.is_authorized == "N"){
						datagrid.datagrid('unselectRow', key);
					}
				});
			}
		});
		
	});

	//提交
	function submitForm() {
		var authorizationForm = $("#authorizationForm");
		var rows = datagrid.datagrid('getSelections');
		var roleIds = "";
		$.each(rows, function(i) {  
			if(i == rows.length - 1){
				roleIds += rows[i].role_id;
			}else{
				roleIds += rows[i].role_id + ",";
			}
		});
		authorizationForm.form('submit', {
			url : '${app}/control/doAuthorizationControl?roleIds=' + roleIds,
			onSubmit : function() {
				if (authorizationForm.form("validate")) {
					if(rows.length == 0){
						$.messager.show({
							title : '信息提示',
							msg : '请选择一个角色!',
							timeout : 5000,
							showType : 'slide'
						});
						return false;
					}else{
						openMask();
						return true;
					}
				} else {
					return false;
				}
			},
			success : function(data) {
				closeMask();
				var obj = eval("(" + data + ")");
				parent.refreshTab("${app}/control/toCtrlList/${pageControlInfo.page_id}?messageCode="+ obj.messageCode, "控件管理");
				parent.closeTab("控件授权");
			}
		});
	}

	//取消
	function resetForm() {
		parent.closeTab("控件授权");
	}
	
</script>
</head>

<body class="easyui-layout" fit="true" style="width: 100%; height: 100%;">
	<div region="north" border="false" style="height: 32px; overflow: hidden;">
		<form id="authorizationForm" class="easyui-form" method="post" modelAttribute="controlRoleRelation">
			<input type="hidden" id="controlId" name="controlId" value="${pageControlInfo.control_id}" />
			<table class="tableForm" border="0" width="100%">
				<tr>
					<td class="tdR" width="12%">控件信息:</td>
					<td align="left">&nbsp;&nbsp;${pageControlInfo.page_name} --> ${pageControlInfo.control_name}</td>
				</tr>
			</table>
		</form>
	</div>
	<div region="center" border="false" style="overflow: hidden;">
		<table id="datagrid"></table>
	</div>
	<div id="toolbar" style="display: none;">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="submitForm();">授权</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="javascript:resetForm();">取消</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
	</div>
</body>
</html>
