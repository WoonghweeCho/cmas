<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
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
	String loclCd = ServletUtil.getData( request, "loclCd" );
	String instcId = ServletUtil.getData( request, "instcId" );
	String oprMethCls = ServletUtil.getData( request, "oprMethCls" );
	String svcUrl = ServletUtil.getData( request, "svcUrl" );
	System.out.println("===================== svcUrl :" + svcUrl);

	System.out.println("===================== loclCd :" + loclCd);

	String svrAddr = request.getServerName();
	int svrPort = request.getServerPort();
	String context = request.getContextPath();
	String centerUrl = "http://" + svrAddr + ":" + Integer.toString(svrPort) + context + "/";
	System.out.println("===================== centerUrl :" + centerUrl);
	String loginUrl = centerUrl + "common/jsp/comm/login/SsoLoginXpl.jsp?";

	//String userId = ServletUtil.getData( request, "userId" );
	//String pwd = ServletUtil.getData( request, "pwd" );

	String source = ServletUtil.getData( request, "source" );
	String BRANCHPAGE = ServletUtil.getData( request, "BRANCHPAGE" );
	String BRANCH = ServletUtil.getData( request, "BRANCH" );
	String dutySysCd = ServletUtil.getData( request, "dutySysCd" );
	String otherParams = ServletUtil.getData( request, "otherParams" );
	String menuCd = ServletUtil.getData( request, "menuCd" );
	String viewMenu = ServletUtil.getData( request, "viewMenu" );
    //String navigateUrl = "";
    try
    {
        SSOConfig.request = request;

        AuthCheck auth = new AuthCheck(request, response);
        //String siteDNS = SSOConfig.siteDomain();
        //String ssositeValue = "&" + SSOConfig.REQUESTSSOSITEPARAM + "=" + siteDNS;
        //navigateUrl = SSOConfig.logoffPage() + "?" + SSOConfig.returnURLTagName() + "=" + Util.uRLEncode(auth.thisURL(), "UTF8") + ssositeValue;
        AuthStatus status = auth.checkLogon();

        if(status == AuthStatus.SSOFirstAccess)
        {
            auth.trySSO();
        }
        else if(status == AuthStatus.SSOSuccess)
        {
            // ì¿ í¤ê° ë³´ê¸°
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
			Cookie killCookie = new Cookie("viewMenu",null);
%>

<script type="text/javascript" type="text/JavaScript">

    var gContextPath = "${contextPath}";
    var loclCd = "<%=loclCd%>";
    var menuCd = "<%=menuCd%>";
    var viewMenu = "<%=viewMenu%>";
	$(document).ready(function(){
	  // 메뉴 cd 가 인자로 전달된경우는 메뉴 cd 를 cookie에 넣어
	  // frame.jsp 에서 추출할 수 있도록 처리한다.
	  if (!gf_IsNull(menuCd) ) {
		  gf_SetCookie("menuCd", menuCd);
	  }
	  else {
		  // 메뉴 코드가 인자로 전달 안된경우는
		  // 메뉴코드를 미결함으로 고정 한다.
		  // CM9901 층별안내 메뉴코드
		  gf_SetCookie("menuCd", "CM9901");
	  }
	  // 조회되어야 할 메뉴가 지정된 경우.
	  gf_SetCookie("viewMenu", "-");
	  if (!gf_IsNull(viewMenu) ) {// 쿠키를 처리해야 함.
			gf_SetCookie("viewMenu", viewMenu);
	  }

	  if ( !gf_IsNull( loclCd ) ) {
		  if ( loclCd == "KR") {
			  loclCd = "ko_KR";
		  }
		  else {
			  loclCd = "en_US";
		  }
		  gf_SetCookie('loclCd',loclCd);
	  }
	  f_Login();
	});

  	var userId = "";
  	var passwd = "";

  	function f_Login() {

		userId = "<%=userId%>";
		passwd = "<%=pwd%>";
		f_LoginCall();

  	}

	function f_LoginCall() {

		// RSA key 재생성
    	gf_GetRSAKey($('#f')[0]);
		gf_EncryptedLogin(userId, passwd, $('#f')[0], loclCd);
  	}


  	function f_Close() {

  	}

</script>

</head>

<body>

<form name='f' id='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='post'>

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
        else if(status == AuthStatus.SSOFail)
        {
			loginUrl =  loginUrl + SSOConfig.returnURLTagName() + "=" + Util.uRLEncode(auth.thisURL(), "UTF8");

			if ( auth.errorNumber() != ErrorCode.NO_ERR)
            {
                response.getWriter().print(ErrorMessage.getMessage(auth.errorNumber()));
                //auth.errorNumber() -1 :account Error, -2 : password Error
            }
            else
            {
                response.sendRedirect(loginUrl);
            }
        }
        else
        {
            loginUrl = loginUrl + SSOConfig.returnURLTagName() + "=" + Util.uRLEncode(auth.thisURL(), "UTF8");
            String htmlSession = "<script lanage='javascript'>alert('You do not use SSO Server!');</script>";
            response.getWriter().print(htmlSession);
        }
    }
    catch(Exception ex)
    {
        response.getWriter().print(ex.getMessage());
    }
%>
