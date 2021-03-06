<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <body>
    <form id="organizationForm" class="easyui-form" method="post" modelAttribute="partnerOrganization">
    	<c:if test="${!empty partnerOrganizationInfo.organization_id}">
    		<input  type="hidden" id="organizationId" name="organizationId" value="${partnerOrganizationInfo.organization_id}"/>
    	</c:if>
		<table class="tableForm" width="100%" border="0">
			<tr>
				<td class="tdR">编号:</td>
				<td>
					<span>${partnerOrganizationInfo.organization_no}</span>
				</td>
			</tr>
			<tr>
				<td class="tdR" width="30%"><span style="color: red">*</span>名称:</td>
				<td width="70%">
					<input type="text" id="prepaymentDate" name="organizationName" value="${partnerOrganizationInfo.organization_name}" class='easyui-textbox' data-options="required:true,validType:['length[0,20]']" style="width: 175px;height: 24px;"/>
				</td>
			</tr>
			<tr>
				<td class="tdR">上级:</td>
				<td>
					<span>${partnerOrganizationInfo.previousOrganizationName}</span>
				</td>
			</tr>
			<tr>
				<td class="tdR" width="30%"><span style="color: red">*</span>简称:</td>
				<td width="70%">
					<input type="text" id="prepaymentDate" name="shortName" value="${partnerOrganizationInfo.short_name}" class='easyui-textbox' data-options="required:true,validType:['length[0,10]']" style="width: 175px;height: 24px;"/>
				</td>
			</tr>
			<tr>
				<td class="tdR" width="30%"><span style="color: red">*</span>负责人:</td>
				<td width="70%">
					<input type="text" id="prepaymentDate" name="principal" value="${partnerOrganizationInfo.principal}" class='easyui-textbox' data-options="required:true,validType:['length[0,10]']" style="width: 175px;height: 24px;"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a class="easyui-linkbutton" id="submitButton"  iconCls="icon-ok" onclick="submitFn();">提交</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancelFn();">取消</a>
				</td>
			</tr>
		</table>
	</form>
  </body>
</html>
