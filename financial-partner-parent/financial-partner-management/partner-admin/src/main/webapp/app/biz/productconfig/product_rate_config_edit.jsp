<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>奖金系数配置页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;" >
		<table class="tableForm" border="0" width="100%"  height="100%" >
			<c:forEach var="bonusConfig" items="${bonusConfigs}">
				<tr>  
					<td class="tdR" width="40%" style="font-weight: bold;">${bonusConfig.bonusType }（%）：</td>
					<td class="tdL" width="60%" ><fmt:formatNumber type="number" value="${bonusConfig.bonusOneCoefficient}" pattern="0.00" maxFractionDigits="2"/></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="2" align="center">
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeBonusConfig();">关闭</a>
				</td>
			</tr>
		</table>
  </body>
</html>
