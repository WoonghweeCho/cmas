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
<%@page import="java.util.Properties"%>
<html>

<head>
<%
        String svrAddr = request.getServerName();
        int svrPort = request.getServerPort();
        String context = request.getContextPath();
        String centerUrl = "http://" + svrAddr + ":" + Integer.toString(svrPort) + context + "/";
        String loginUrl = centerUrl + "common/jsp/comm/login/SsoLoginXpl.jsp?";

        Properties properties = ConfigUtil.getProperties(null);

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
            //
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
        <jsp:include page="/common/jsp/comm/include/clientLib.jsp">
                <jsp:param value="공통업무시스템" name="title"/>
                <jsp:param value="proxy" name="popupType"/>
        </jsp:include>

                <ut:link href="${contextPath}/common/css/base.css" rel="stylesheet" type="text/css" />
                <ut:link href="${contextPath}/common/css/framework/jquery-ui-custom.css" type="text/css" rel="stylesheet" />
                <ut:link href="${contextPath}/common/css/framework/ui.jqgrid.css" type="text/css" rel="stylesheet" />
                <ut:link href="${contextPath}/common/css/framework/ui.multiselect.css" type="text/css" rel="stylesheet" />
                <ut:link href="${contextPath}/common/css/framework/zTreeStyle.css" type="text/css" rel="stylesheet" />

                <ut:script type="text/javascript" src="${contextPath}/common/js/framework/json2.js"></ut:script>
                <ut:script type="text/javascript" src="${contextPath}/common/js/framework/jquery-1.7.2.js"></ut:script>
                <ut:script type="text/javascript" src="${contextPath}/common/js/framework/jquery-ext.js"></ut:script>
                <ut:script type="text/javascript" src="${contextPath}/common/js/framework/jquery.base64.js"></ut:script>
                <ut:script type="text/javascript" src="${contextPath}/common/js/framework/jquery-ui-custom.min.js"></ut:script>
                <ut:script type="text/javascript" src="${contextPath}/common/js/framework/jquery.ztree.all-3.5.js"></ut:script>
                <ut:script type="text/javascript" src="${contextPath}/common/js/framework/jquery.maskedinput.js"></ut:script>
                <ut:script type="text/javascript" src="${contextPath}/common/js/framework/jquery-lang.js"></ut:script>

                <ut:script type="text/javascript" src="${contextPath}/common/js/cache/commCd_en_US.js"></ut:script>
                <ut:script type="text/javascript" src="${contextPath}/common/js/cache/commCd_ko_KR.js"></ut:script>
                <ut:script type="text/javascript" src="${contextPath}/common/js/cache/message_en_US.js"></ut:script>
                <ut:script type="text/javascript" src="${contextPath}/common/js/cache/message_ko_KR.js"></ut:script>
                <ut:script type="text/javascript" src="${contextPath}/common/js/cache/mlang_en_US.js"></ut:script>
                <ut:script type="text/javascript" src="${contextPath}/common/js/cache/mlang_ko_KR.js"></ut:script>

                <ut:script type="text/javascript" src="${contextPath}/common/js/framework/jqGrid/i18n/grid.locale-en.js"></ut:script>
                <ut:script type="text/javascript" src="${contextPath}/common/js/framework/jquery.jqGrid.src.js"></ut:script>
                <ut:script type="text/javascript" src="${contextPath}/common/js/comm/dataSet.js"></ut:script>
                <ut:script type="text/javascript" src="${contextPath}/common/js/comm/comutil.js"></ut:script>
                <ut:script type="text/javascript" src="${contextPath}/common/js/comm/comtran.js"></ut:script>
                <ut:script type="text/javascript" src="${contextPath}/common/js/comm/comcomp.js"></ut:script>
                <ut:script type="text/javascript" src="${contextPath}/common/js/comm/globalvariable.js"></ut:script>
                <ut:script type="text/javascript" src="${contextPath}/common/js/comm/frmcontent.js"></ut:script>

        <script type="text/javascript">
        var params = {};
        var v_selfClose = true;

        function f_GetUrl() {
                var strUrl = window.location.protocol + "//" + window.location.host;
                strUrl += "${pageContext.request.contextPath}";
                return strUrl;
        }
    function f_login(userId) {
        var authSuccess = false;

        // embed 전 인증
        if(userId != ""){
                        $.ajax(gv_ContextPath + "/common/jsp/dummy.jsp?timeline=" + $.now(),
                                {
                                 headers : {"BARONET_AUTHKEY" : userId },
                                  success: function(data) {
                                        authSuccess = true;
                                  },
                                  error: function(e){
                                          gf_AlertMsg('통합결재 인증 실패');
                                  },
                                  async : false
                        });
                }
                else {
                        gf_AlertMsg('문제 발생 ');
                }

        if ( authSuccess ) {
                gf_SetCookie('loclCd',"ko_KR");
                location.href = "<%=request.getParameter("url")%>";
        }
    }
        $(function(){
                <c:forEach var="entry" items="${param}">
                params["${entry.key}"] = "${entry.value}";
                </c:forEach>
                f_login('<%=userId%>');
        });

        function f_Close() {
                if(!v_selfClose)
                        return;

                if (typeof($.browser.msie) != "undefined" && $.browser.msie)  {
              window.opener = "nothing";
              window.open('', '_parent', '');
              window.close();
                } else {
                        //window.opener = self;
                        //self.close();
                        self.open('about:blank','_self').close();
                }
        }
        </script>
</head>
<body>
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

