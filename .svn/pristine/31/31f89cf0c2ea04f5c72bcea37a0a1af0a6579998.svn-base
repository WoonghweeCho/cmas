<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map,
			docfbaro.iam.UserInfo,
            docfbaro.iam.authentication.UserDefinition,
            jcf.iam.core.common.util.UserInfoHolder,
            docfbaro.common.WebContext,
            docfbaro.common.Constants,
            docfbaro.common.StringUtil"
            %>
<%
	UserDefinition uInfo = UserInfoHolder.getUserInfo(UserDefinition.class);
	Map    etcInfo 		 	= uInfo.getEtcInfo();
	String authListStr 	 	= StringUtil.getText(etcInfo.get("JSONAuthList")).replace('`', ',');
	String loclCd	 		= uInfo.getLoclCd();
	String userId			= uInfo.getUserId();
	String orgCd			= uInfo.getOrgInfo().get("orgCd").toString();
	String orgNm       		= loclCd.equals("ko_KR") ? uInfo.getOrgInfo().get("orgNm").toString() : uInfo.getOrgInfo().get("orgNmEn").toString();
	String userNm       	= loclCd.equals("ko_KR") ? uInfo.getUserNm() : uInfo.getUserEnm();
	String userPositCd       	= StringUtil.getText(uInfo.getTitleInfo().get("userPositCd"));
	String userRpswrkCd       	= StringUtil.getText(uInfo.getTitleInfo().get("userRpswrkCd"));
	String userEnm          = uInfo.getUserEnm();

	if(userRpswrkCd.equals("담당"))
		userRpswrkCd = "";
%>

<script type="text/javascript">
var gv_userId = "<%=userId%>";
var gv_userNm = "<%=userNm%>";
var gv_orgCd = "<%=orgCd%>";
var gv_orgNm = "<%=orgNm%>";
var gv_userPositCd = "<%=userPositCd%>";
var gv_userRpswrkCd = "<%=userRpswrkCd%>";
var gv_userEnm = "<%=userEnm%>";
// 로그인한 사용자의 겸직 JSON DATA
var gv_AuthList = <%=authListStr%>
</script>