<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="java.util.HashMap,
            java.util.Map,
            java.util.List,
            java.util.ArrayList,
            docfbaro.iam.UserInfo,
            docfbaro.iam.authentication.UserDefinition,
            jcf.iam.core.common.util.UserInfoHolder,
            docfbaro.common.WebContext,
            docfbaro.common.Constants,
            docfbaro.common.StringUtil,
            docfbaro.common.ObjUtil,
            com.nets.sso.agent.*"
            %>
<%@ taglib prefix="c" uri="/c_rt" %>
<%@ taglib prefix="fmt" uri="/fmt" %>
<%@ taglib prefix="fn" uri="/fn" %>
<%@ taglib prefix="ut" uri="/ut" %>
<jsp:useBean id="userInfo" class="docfbaro.iam.UserInfo"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}" ></c:set>
<meta charset="utf-8" />

<%
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
		pageContext.setAttribute("appProperties", context.getBean("appProperties"));
		java.util.Properties appProp = (java.util.Properties)context.getBean("appProperties");
        String contextPath = request.getContextPath();
        String svrAddr = request.getServerName();
        int svrPort = request.getServerPort();
        String portString = svrPort == 80 ? "" : ":"+Integer.toString(svrPort);
        String centerUrl = "http://" + svrAddr + portString  + contextPath + "/";
        //var gv_MenuPrive = ${fn:replace(userInfo.getUserInfo().getEtcInfo().get("JSONMenuAuth"), "`", ",")};
        UserDefinition uInfo 	= UserInfoHolder.getUserInfo(UserDefinition.class);
		Map    etcInfo 		 	= null;
		String menuPrivStr 	 	= "";
		String authListStr 		= "";

		// 현재 로케일정보를 가져오기 위해 쿠키 조회
		Cookie loclCookie = Util.getCookie(request.getCookies(), "loclCd");
		String loclCd = "ko_KR";   // default ko_KR
		if ( !ObjUtil.isNull(loclCookie) ) {
			loclCd = StringUtil.getText(loclCookie.getValue());
		}

		try{
			etcInfo 		 	= uInfo.getEtcInfo();
			menuPrivStr 	 	= StringUtil.getText(etcInfo.get("JSONMenuAuth_"+loclCd)).replace('`', ',');
		}catch(Exception e){}
%>

<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" />

<title>${param.title}</title>
<script type="text/javascript">
var gv_ContextPath = "${contextPath}";
var gContextPath = "${contextPath}";
var gv_ServerUrl = "<%=centerUrl%>";
var gv_IsProxy = "${param.popupType}" == "proxy"? true : false;
gv_IsTablet = typeof(gv_IsTablet) == "undefined" ? false : gv_IsTablet;
</script>

<ut:link href="${contextPath}/baronet/css/base.css" rel="stylesheet" type="text/css" />
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
<ut:script type="text/javascript" src="${contextPath}/common/js/framework/jquery.ui.monthpicker.js"></ut:script>

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
<ut:script type="text/javascript" src="${contextPath}/common/js/sign/comsign.js"></ut:script>
<ut:script type="text/javascript" src="${contextPath}/common/js/comm/globalvariable.js"></ut:script>
<ut:script type="text/javascript" src="${contextPath}/common/js/comm/frmcontent.js"></ut:script>

<c:choose>
<c:when test="${param.type != 'popup'}">
        <c:if test="${param.popupType != 'proxy'}">
                <jsp:include page="/common/jsp/comm/include/netsSsocheck.jsp" />
        </c:if>
</c:when>
</c:choose>

