<%@page contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/header.jsp"%>
		<meta>
		<title></title>
	</head>

	<body>
		<font color="red" size="5px"><b>对不起，系统出错了，请稍后重试。</b> </font>
		<br/>
		<c:if test="${not empty exception}">
		<font color=red><b>错误信息为：</b> </font>
		<br/>
		<s:property value="exception" />
		</c:if>
		<br/>
		<c:if test="${not empty exceptionStack}">
		<font color=red><b>错误的堆栈信息为：</b> </font>
		<br/>
		<s:property value="exceptionStack" />
		</c:if>
	</body>
</html>
