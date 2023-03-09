<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            docfbaro.common.ServletUtil,
            com.nets.sso.agent.*"
            %>
<%
	// User-Agent를 활용하여 모바일 브라우저인지를 확인하고 모바일 브라우저일 경우 모바일용 페이지로 이동시킴.
	String ua = WebContext.getRequest().getHeader("User-Agent");
	ua = ua.toLowerCase();
	if(ua.matches(".*(android|blackberry|googlebot-mobile|iemobile|ipad|iphone|ipod|opera mobile|palmos|webos).*"))
		response.sendRedirect(request.getContextPath() + "/common/jsp/tablet/tablet.jsp");

	// 현재 로케일정보를 가져오기 위해 쿠키 조회
	Cookie loclCookie = Util.getCookie(request.getCookies(), "loclCd");
	String loclCd = "ko_KR";   // default ko_KR
	if ( !ObjUtil.isNull(loclCookie) ) {
		loclCd = StringUtil.getText(loclCookie.getValue());
	}

	// 세션정보를 get 하기 위해 userdefinition 을 get
	UserDefinition uInfo 	= UserInfoHolder.getUserInfo(UserDefinition.class);
	// 각종 url 및 domain, 권한, 메뉴 정보를 가져오기 위해 etcinfo get
	Map    etcInfo 		 	= uInfo.getEtcInfo();
	String menuPrivStr 	 	= StringUtil.getText(etcInfo.get("JSONMenuAuth_"+loclCd)).replace('`', ',');
	String authListStr 	 	= StringUtil.getText(etcInfo.get("JSONAuthList")).replace('`', ',');

	String userId           = uInfo.getUserId();
	String userNm           = loclCd.equals("ko_KR") ? uInfo.getUserNm() : uInfo.getUserEnm();
	String orgCd            = uInfo.getOrgCd();
	/*String hdofcOrgNm       = loclCd.equals("ko_KR") ? uInfo.getOrgInfo().get("hdofcOrgNm").toString() : uInfo.getOrgInfo().get("hdofcOrgNmEn").toString();
	String orgNm       		= loclCd.equals("ko_KR") ? uInfo.getOrgInfo().get("orgNm").toString() : uInfo.getOrgInfo().get("orgNmEn").toString();
	String userNm       	= loclCd.equals("ko_KR") ? uInfo.getUserNm() : uInfo.getUserEnm();*/
	String cmasUrl			= StringUtil.getText(etcInfo.get("CMAS_URL")).replace('`', ',');

	String titleName = "업무시스템";
	Cookie menuCookie = Util.getCookie(request.getCookies(), "viewMenu");
	String menuCd = "";   // default ko_KR
	if ( !ObjUtil.isNull(menuCookie) ) {
		menuCd = StringUtil.getText(menuCookie.getValue());
	}
	if( "CM04".equals(menuCd) ){
		titleName = "건설기술정보센터";
	}
%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp" %>
<html>
<head>
	<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
		<jsp:param value="<%=titleName%>" name="title"/>
		<jsp:param value="root" name="type"/>
	</jsp:include>
	<style type="text/css">
	body {
	    margin: 0px;
	    padding: 0px;
	}

	/* iframe's parent node */
	div#root {
	    position: fixed;
	    width: 100%;
	    height: 100%;
	}

	/* iframe itself */
	div#root > iframe {
	    display: block;
	    width: 100%;
	    height: 100%;
	    border: none;
	}
	</style>
	<script type="text/javascript">
	// 로그인 한 사용자의 메뉴 권한 JSON DATA
	var gv_MenuPrive = <%=menuPrivStr%>
	// 로그인한 사용자의 겸직 JSON DATA
	var gv_AuthList = <%=authListStr%>

 	var gv_cmasUrl			= "<%=cmasUrl%>";
 	var gv_userId 			= "<%=userId%>";
 	var gv_userNm			= "<%=userNm%>";
 	var gv_orgCd			= "<%=orgCd%>";


	var f_CloseFrame = function() {

		if ($.browser.msie == true)  {
	      	window.opener = "nothing";
	      	window.open('', '_parent', '');
	      	window.close();
		} else {
			window.open('', '_self', '').close();
		}


	};
	</script>

</head>
<frameset rows="50,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="topMenu.jsp" name="topMenu" scrolling="No" noresize="noresize" id="topFrame" title="top" />
  <frameset cols="236,*" frameborder="no" border="0" framespacing="0">
    <frame src="leftMenu.jsp" name="left" id="left" title="left" scrolling="No" noresize="noresize" />
	<frame src="" name="main" id="main"  width="206px" title="contents"/>
  </frameset>
</frameset>
</html>

