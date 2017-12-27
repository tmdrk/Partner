<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%-- /项目名称 例如：/partner-h5 --%>
<c:set var="app" scope="page" value="${pageContext.request.contextPath}" />
<%-- /项目名称 例如：/partner-h5 --%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%-- http或https协议 --%>
<c:set var="scheme" scope="page" value="${pageContext.request.scheme}"/>
<%-- 域名 --%>
<c:set var="serverName" scope="page" value="${pageContext.request.serverName}"/>
<%-- 端口号 --%>
<c:set var="serverPort" scope="page" value="${pageContext.request.serverPort}"/>
<%-- {http或https}://{域名}:{端口号}/{项目名称} --%>
<c:set var="basePath" value="${scheme}://${serverName}:${serverPort}${app}"></c:set>
<%-- 消息最大长度 --%>
<c:set var="messageMaxLength" value="30"></c:set>


<script type="text/javascript" src="${basePath}/js/lib/jquery-1.10.1/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="${basePath}/js/lib/jquery.cookie.js"></script>
<script type="text/javascript" src="${basePath}/js/commons.js"></script>

<%-- Set all pages that include this page to use XHTML --%>