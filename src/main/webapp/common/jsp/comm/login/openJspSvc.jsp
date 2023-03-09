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

<%--
/**
 */
--%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
	// http://localhost:7001/common/jsp/common/login/openJsp.jsp
	String empno = ServletUtil.getData( request, "empno" );
	String signId = ServletUtil.getData( request, "signId" );
	String proxyType = ServletUtil.getData( request, "proxyDialogType" );
	String svrAddr = request.getServerName();
	int svrPort = request.getServerPort();
	String context = request.getContextPath();
	String centerUrl = "http://" + svrAddr + ":" + Integer.toString(svrPort) + context + "/";

	/*try
    {
        SSOConfig.request = request;

        AuthCheck auth = new AuthCheck(request, response);
        //String siteDNS = SSOConfig.siteDomain();
        //String ssositeValue = "&" + SSOConfig.REQUESTSSOSITEPARAM + "=" + siteDNS;
        //navigateUrl = SSOConfig.logoffPage() + "?" + SSOConfig.returnURLTagName() + "=" + Util.uRLEncode(auth.thisURL(), "UTF8") + ssositeValue;
        AuthStatus status = auth.checkLogon();

        if(status == AuthStatus.SSOSuccess)
        {
            // ì¿ í¤ê° ë³´ê¸°
            Cookie c = Util.getCookie(request.getCookies(), SSOConfig.sSODomainTokenName());
            if(c != null && c.getValue() != null && !c.getValue().equals(""))
            {
	            String domainAuthCookie = Util.decryptDomainCookie(c.getValue());
            }

            empno = auth.getSSODomainCookieValue("loginid");
            c = Util.getCookie(request.getCookies(), "dwencInfo");
            String encPwd = c.getValue();
            String pwd = auth.decrypt(SSOConfig.sSODomainCookieEncKey(), SSOConfig.sSODomainEncType(), encPwd);

            String referer = request.getHeader("referer");
    		referer = referer == null ? "" : referer;
    		RefererUtil refererUtil = new RefererUtil();*/

%>



<script type="text/javascript">
$(document).ready(function(){
	/* 더미 예외 처리 20140410
	$.ajax("<%=centerUrl%>common/jsp/dummy.jsp",
	 	{
		 headers : {"BARONET_AUTHKEY" : '<%=empno%>'},
		  success: function(data) {
    		location.href = "<%=centerUrl%>common/jsp/sign/proxyDialog.jsp?proxyDialogType=VIEW&signId=<%=signId%>";
		  },
		  error: function(e){
		  	//alert(e.status);
		  }
	});
	*/
});
</script>
<%
 /*       }
        else {

        }
    }
    catch(Exception ex)
    {
        response.getWriter().print(ex.getMessage());
    }*/
%>
</head>
<body>
</body>
</html>



