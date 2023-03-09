<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="docfbaro.iam.UserInfo"%>
<%@page import="docfbaro.iam.authentication.UserDefinition"%>
<%@page import="jcf.iam.core.common.util.UserInfoHolder"%>
<%@page import="docfbaro.common.WebContext"%>
<%@page import="com.dwenc.cmas.common.utils.ConfigUtil"%>
<%@page import="docfbaro.common.ServletUtil" %>
<%@page import="java.util.Properties" %>
<%@page import="docfbaro.common.ObjUtil" %>
<%@page import="docfbaro.common.StringUtil" %>
<%@page import="java.net.URLDecoder" %>
<%@page import="com.dwenc.cmas.common.utils.RefererUtil" %>
<%@page import="com.nets.sso.agent.*" %>
<%@page import="java.util.ResourceBundle"%>
<html>

<head>
	<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
		<jsp:param value="공통업무시스템" name="title"/>
		<jsp:param value="proxy" name="popupType"/>
	</jsp:include>

	<script type="text/javascript" src="${contextPath}/common/js/comm/comjsbn.js"></script>
	<script type="text/javascript" src="${contextPath}/common/js/comm/comrsa.js"></script>
	<script type="text/javascript" src="${contextPath}/common/js/comm/comprng4.js"></script>
	<script type="text/javascript" src="${contextPath}/common/js/comm/comrng.js"></script>
	<script type="text/javascript" src="${contextPath}/common/js/framework/jquery.base64.js"></script>
	<script type="text/javascript">
	var params = {};
	var v_selfClose = true;

	$(function(){
		<c:forEach var="entry" items="${param}">
		params["${entry.key}"] = "${entry.value}";
		</c:forEach>
		gf_SetCookie('loclCd',"ko_KR");
        location.href = "<%=request.getParameter("url")%>";
	});

	function f_Close() {
		if(!v_selfClose)
			return;

		if (typeof($.browser.msie) != "undefined" && $.browser.msie)  {
	      window.opener = "nothing";
	      window.open('', '_parent', '');
	      window.close();
		} else {
			self.open('about:blank','_self').close();
		}
	}
	</script>
</head>
<body>
</body>
</html>
