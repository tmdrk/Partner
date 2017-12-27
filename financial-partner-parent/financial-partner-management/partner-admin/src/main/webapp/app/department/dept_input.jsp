<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <body>
    <form id="deptForm" class="easyui-form" method="post" modelAttribute="department">
    	<c:if test="${!empty parentId}">
    		<input type="hidden" id="parentId" name="parentId" value="${parentId}"/>
    	</c:if>
    	<c:if test="${!empty department.deptId}">
    		<input  type="hidden" id="deptId" name="deptId" value="${department.deptId}"/>
    	</c:if>
		<table class="tableForm" width="100%" height="63%" border="0">
			<tr>
				<td class="tdR" width="30%"><span style="color: red">*</span>部门名称:</td>
				<td width="70%">
					<input type="text" id="prepaymentDate" name="deptName" value="${department.deptName}" class='easyui-textbox' data-options="required:true,validType:['length[0,20]']" style="width: 175px;height: 24px;"/>
				</td>
			</tr>
			<tr>
				<td class="tdR"><span style="color: red">*</span>部门编号:</td>
				<td>
					<input type="text" id="prepaymentDate" name="deptNo" value="${department.deptNo}" class='easyui-textbox' data-options="required:true,validType:['length[0,30]']" style="width: 175px;height: 24px;"/>
				</td>
			</tr>
			<tr>
				<td class="tdR">备注:</td>
				<td>
					<input name="remark" class='easyui-textbox' data-options="multiline:true,validType:['length[0,100]']" value="${department.remark}" style="width:200px;height:75px"/>
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
