<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/common/jsp/comm/include/baseSetting.jsp" %>
<%@ page import="java.util.Enumeration" %>



<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
	<ut:link href="${contextPath}/common/css/base.css" type="text/css" rel="stylesheet" />
		<ut:script type="text/javascript" src="${contextPath}/common/js/framework/json2.js"></ut:script>
		<ut:script type="text/javascript" src="${contextPath}/common/js/framework/jquery-1.7.2.js"></ut:script>
		<ut:script type="text/javascript" src="${contextPath}/common/js/framework/jquery-ext.js"></ut:script>
		<ut:script type="text/javascript" src="${contextPath}/common/js/framework/jquery.base64.js"></ut:script>
		<ut:script type="text/javascript" src="${contextPath}/common/js/framework/jquery-lang.js"></ut:script>
		<ut:script type="text/javascript" src="${contextPath}/common/js/comm/comutil.js"></ut:script>
		<ut:script type="text/javascript" src="${contextPath}/common/js/comm/comjsbn.js"></ut:script>
		<ut:script type="text/javascript" src="${contextPath}/common/js/comm/comrsa.js"></ut:script>
		<ut:script type="text/javascript" src="${contextPath}/common/js/comm/comprng4.js"></ut:script>
		<ut:script type="text/javascript" src="${contextPath}/common/js/comm/comrng.js"></ut:script>
		<ut:script type="text/javascript" src="${contextPath}/common/js/comm/comcrypto.js"></ut:script>


<title></title>
</head>

<body>
<form name='aform' id='aform' action='${pageContext.request.contextPath}/common/jsp/comm/login/SsoOpenXplSvc.jsp' method='POST'>
<%
	Enumeration<String> names = request.getParameterNames();
	while (names.hasMoreElements()) {
		String code = (String) names.nextElement();
		String value = request.getParameter(code);
%>
  <input type="hidden" id="<%=code%>" name="<%=code%>" value="<%=value%>">
<%
	}
%>
</form>

<script type="text/javascript">
	document.getElementById("aform").submit();
</script>

</body>
</html>



