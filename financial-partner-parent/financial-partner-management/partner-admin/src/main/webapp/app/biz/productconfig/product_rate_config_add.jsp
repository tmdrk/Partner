<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>奖金配置管理页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">   
  </head>
  
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<form id="productForm" class="easyui-form" method="post" modelAttribute="productConfig">
		<table class="tableForm" width="100%" height="80%" border="0">
			<tr>
				<td class="tdR" width="10%">产品类型：</td>
				<td width="20%">
					<select id="productType" name="productType" class="easyui-combobox" panelHeight="130px" editable="false"  style="width: 150px;">
						<c:forEach var="obj" items="${CodeMap['PRODUCT_TYPE']}">
							<option value="${obj.key}">${obj.value}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="tdR" width="10%">期限（月）：</td>
				<td width="20%">
					<input  type="text" id="productTerm" name="productTerm"  class='easyui-numberbox' precision="0" max="100" min="0" style="width: 150px;height: 24px;"/>
				</td>
			</tr>
			<tr>
				<td class="tdR" width="10%">折算系数：</td>
				<td width="20%">
					<input id="discountMolecular" name="discountMolecular" class='easyui-numberbox' precision="0" max="100" min="1" style="width: 65px;height: 24px;"/>
					&nbsp;&nbsp;/&nbsp;&nbsp;<input id="discountDenominator" name="discountDenominator" class='easyui-numberbox' precision="0" max="100" min="1" style="width: 65px;height: 24px;"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">      
					<a class="easyui-linkbutton" id="submitButton"  iconCls="icon-ok" onclick="createProduct();">保存</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancelCreateProduct();">关闭</a>
				</td>
			</tr>
		</table>
	</form>
  </body>
</html>
