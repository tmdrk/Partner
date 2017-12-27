<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>添加角色</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<%@ include file="/common/header.jsp"%>
	<script type="text/javascript">
		//提交
		function submitForm(){
			var roleForm = $("#roleForm");
			roleForm.form('submit',{
				url:'${app}/role/doUpdateRole',
				onSubmit:function(){
					if(roleForm.form("validate")){
						openMask();
						return true;
					}else{
						return false;
					}
				},
				success:function(data){
					closeMask();
					var obj = eval("(" + data + ")");
					parent.refreshTab("${app}/role/toRoleList?messageCode=" + obj.messageCode,"角色管理");
					parent.closeTab("修改角色信息");
				}
			});
		}
		
		//取消
		function resetForm(){
			parent.closeTab("修改角色信息");
		}
		
	</script>
  </head>
  
  <body style="background: white;">
  	<form id="roleForm" class="easyui-form" method="post" modelAttribute="role">
  		<input type="hidden" name="roleId" value="${role.roleId}">
		<table class="tableForm" border="0" width="100%">
			<tr>
				<td class="tdR"><span style="color: red;">*</span>角色代码:</td>
				<td>
					<input id="roleCode" name="roleCode" value="${role.roleCode}" class="easyui-textbox" data-options="required:true,validType:['length[0,30]','roleRule']" style="width: 175px;height: 24px;"/>&nbsp;&nbsp;
					<span style="color: red;">角色代码必须是以"ROLE_"开头的大写字母、数字、下划线组成的字符串！</span>
				</td>
			</tr>
			<tr>
			    <td class="tdR"><span style="color: red">*</span>角色中文名:</td>
				<td align="left">
					<input id="roleName" name="roleName" value="${role.roleName}" class="easyui-textbox" data-options="required:true,validType:['length[0,30]','chineseRule']" style="width: 175px;height: 24px;"/>&nbsp;&nbsp;
					<span style="color: red;">角色中文名，请输入汉字！</span>
				</td>
			</tr>
			<tr>
				<td class="tdR"><span style="color: red">*</span>角色描述:</td>
				<td valign="middle">
					<input id="remark" name="remark" class='easyui-textbox' data-options="required:true,multiline:true,validType:['length[0,100]']" value="${role.remark}" style="width:330px;height:60px"/>						
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="submitButton"  iconCls="icon-ok" onclick="submitForm();">提交</a>
					&nbsp;
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:resetForm();">取消</a>
				</td>
			</tr>
		</table>
	</form>
  </body>
</html>
