<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>修改员工</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/common/header.jsp"%>
	<script type="text/javascript">
		$(document).ready(function(){
			limit_textarea_input();//统计文本域输入字数
		});
		$(function(){
			//渠道
			$("#organizationChannelId").combobox({
				url:'${app}/partnerOrganization/findSecondOrgan',
				valueField:'organization_id',
				textField:'organization_name',
				onChange:function(newValue,oldValue){
					$("#organizationAreaId").combobox('clear');
					$("#organizationAreaId").combobox('reload',"${app}/partnerOrganization/findCascadingOrgan?organizationId=" + newValue);
				}
			});
			//区域
			$("#organizationAreaId").combobox({
				valueField:'organization_id',
				textField:'organization_name'
			});
			//初始化
	        $("#organizationChannelId").combobox('loadData',[{organization_id:'',organization_name:'请选择'}]);
	        $("#organizationAreaId").combobox('loadData',[{organization_id:'',organization_name:'请选择'}]);
		});
		//提交
		function submitForm(){
			var editLoginUserForm = $("#editLoginUserForm");
			editLoginUserForm.form('submit',{
				url:'${app}/loginuser/updateLoginUser',
				onSubmit:function(){
					/* if(editLoginUserForm.form("validate")){
						openMask();
						return true;
					}else{
						return false;
					} */
				},
				success:function(data){
					closeMask();
					var obj = eval("(" + data + ")");
					parent.refreshTab("${app}/loginuser/toLoginUserListPage?messageCode=" + obj.messageCode,"用户数据管理");
					parent.closeTab("编辑用户");
				}
			});
		}
		
		//取消
		function resetForm(){
			parent.closeTab("编辑用户");
		}
		// 默认选中的职位
		$(function(){
			//var userType = $("#usertype").val();
			var organizationChannelId = $("#organization_channel_id_name").val();
			var organizationAreaId = $("#organization_area_id").val();
			var userStatus = $("#user_status").val();
			//$("#position option[value= " +position+ "]").attr("selected", "selected");
			//$("#userType").combobox('select',userType);
			$("#organizationChannelId").combobox('select',organizationChannelId);
			$("#organizationAreaId").combobox('select',organizationAreaId);
			$("#userStatus").combobox('select',userStatus);
		});
		
	</script>
  </head>
  
  <body style="background: white;">
  	<form id="editLoginUserForm" class="easyui-form" method="post" modelAttribute="loginUser">
  		<input type="hidden" id="userId" name="userId" value="${loginUser.user_id}"/>
		<table class="tableForm" border="1" width="100%" >
			<tr>
				<td width="15%" class="tdR"><span style="color: red"></span>成为合伙人时间:</td>
				<td width="35%">
					<span>${loginUser.partner_time}</span>
				</td>
				<td width="15%" class="tdR">用户姓名:</td>
				<td width="35%">
					<span>${loginUser.real_name}</span>
				</td>
			</tr>	
			<tr>	
				<td width="15%" class="tdR">用户手机号:</td>
				<td width="35%">
					<span>${loginUser.username}</span>
				</td>
				<td width="15%" class="tdR">用户角色:</td>
				<td width="35%">
					<span>${loginUser.user_role_name}</span>
				</td>
			</tr>
			<tr>	
				<td class="tdR"><span style="color: red"></span>用户身份:</td>
<%-- 					<input type="hidden" id="usertype" value="${loginUser.user_type}"/> --%>
				<td><%-- <select id="userType" name="userType" class="easyui-combobox" data-options="required:true" style="width: 175px;height: 24px;">
					<c:forEach var="obj" items="${CodeMap['USER_TYPE']}">
						<option value="${obj.key}">${obj.value}</option>
					</c:forEach>
				</select> --%>
					<span>${loginUser.user_type_name}</span>
				</td>
				<td width="15%" class="tdR">邀请人推荐码:</td>
				<td width="35%">
					<span>${loginUser.invitation_code}</span>
				</td>
			</tr>
			<tr>
				<td class="tdR"><span style="color: red">*</span>所属渠道:</td>
					<input type="hidden" id="organization_channel_id_name" value="${loginUser.organization_channel_id}"/>
				<td>
					<input type="text" id="organizationChannelId" name="organizationChannelId" class="easyui-combobox" data-options="valueField:'organization_id',textField:'organization_name'" panelHeight="130px" editable="false" style="width: 160px;height: 24px;"/>
				</td>
				<td class="tdR"><span style="color: red">*</span>所属区域:</td>	
					<input type="hidden" id="organization_area_id" value="${loginUser.organization_area_id}"/>
		  		<td>
		  			<input type="text" id="organizationAreaId" name="organizationAreaId" class="easyui-combobox" data-options="valueField:'organization_id',textField:'organization_name'" panelHeight="130px" editable="false" style="width: 160px;height: 24px;"/>
				</td>
			</tr>
			<tr>
				<td class="tdR"><span style="color: red"></span>用户状态:</td>
				<td><span>${loginUser.user_status_name}</span></td>
				<%-- <input type="hidden" id="user_status" value="${loginUser.user_status}"/>
				<td><select id="userStatus" name="userStatus" class="easyui-combobox"  style="width: 175px;height: 24px;">
					<c:forEach var="obj" items="${CodeMap['USER_STATUS']}">
						<option value="${obj.key}">${obj.value}</option>
					</c:forEach>
				</select></td> --%>
			</tr>
			<tr>
				<td  colspan="4" align="center">
					<a class="easyui-linkbutton" id="submitButton"  iconCls="icon-ok" onclick="submitForm();">提交</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="resetForm();">取消</a>
				</td>
			</tr>
		</table>
		<div id="deptDialog"></div>
	</form>
  </body>
</html>
