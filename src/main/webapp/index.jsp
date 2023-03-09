<!DOCTYPE html>
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

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
	<title></title>
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

<%
	String svrAddr = request.getServerName();
	int svrPort = request.getServerPort();
	String context = request.getContextPath();
	String centerUrl = "http://" + svrAddr + ":" + Integer.toString(svrPort) + context + "/";
	String loginUrl = centerUrl + "co/common/login/loginPageSvc.xpl?";


    //String navigateUrl = "";
    try
    {
        SSOConfig.request = request;

        AuthCheck auth = new AuthCheck(request, response);
        AuthStatus status = auth.checkLogon();
        if(status == AuthStatus.SSOSuccess)
        {	// sso 인증 성공시
            Cookie c = Util.getCookie(request.getCookies(), SSOConfig.sSODomainTokenName());
            if(c != null && c.getValue() != null && !c.getValue().equals(""))
            {
	            String domainAuthCookie = Util.decryptDomainCookie(c.getValue());
            }

            String userId = auth.getSSODomainCookieValue("loginid");
            c = Util.getCookie(request.getCookies(), "dwencInfo");
            String encPwd = c.getValue();
            String pwd = auth.decrypt(SSOConfig.sSODomainCookieEncKey(), SSOConfig.sSODomainEncType(), encPwd);

            String referer = request.getHeader("referer");
    		referer = referer == null ? "" : referer;
    		RefererUtil refererUtil = new RefererUtil();

%>

<script type="text/javascript" type="text/JavaScript">
    var gContextPath = "${contextPath}";
    // 페이지 열릴때 자동 로그인 처리
	$(document).ready(function(){
	  f_Login();
	});

  	var userId = "";
  	var passwd = "";

  	function f_Login() {

		userId = "<%=userId%>";
		passwd = "<%=pwd%>";
		// 로그인시 첫 메뉴는 미결함 으로 고정
		//if (!gf_IsNull(gf_GetCookie("menuCd")) ) {
		//	gf_SetCookie("menuCd", "SG01");
	  	//}


		f_LoginCall();

  	}

	function f_LoginCall() {

		// RSA key 재생성
    	gf_GetRSAKey($('#f')[0]);
		var loclCd = gf_GetCookie("loclCd");
		gf_EncryptedLogin(userId, passwd, $('#f')[0], loclCd);
  	}


  	function f_Close() {

  	}

</script>

</head>

<body>

<form name='f' id='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST'>
  	<input type="hidden" id="j_username" name="j_username" value="" />
	<input type="hidden" id="j_password" name="j_password" value="" />
	<input type="hidden" id="loclCd" name="loclCd" value="">
	<input type="hidden" id="centerUrl" name="centerUrl" value="<%=centerUrl%>">
	<input type="hidden" id="publicKeyModulus" name="publicKeyModulus" >
	<input type="hidden" id="publicKeyExponent" name="publicKeyExponent" >
</form>

<iframe id="ifm" name="ifm" src="" width="0" height="0" frameborder='0' border='0' scrolling='no'/>

</body>
</html>

<%
        }
        else
        { // sso 인증 실패시
%>
</head>
<body>
<form name='aform' id='aform' action='${contextPath}/co/common/login/loginPageSvc.xpl' method='POST'>
  <input type="hidden" id="referer" name="referer" value="/">
</form>
<script type="text/javascript">
	var frm = document.getElementById("aform");
	frm.referer.value = window.location.pathname;
	frm.submit();
</script>
</body>
</html>
<%
        }
    }
    catch(Exception ex)
    {
        response.getWriter().print(ex.getMessage());
    }
%>


