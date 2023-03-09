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
            docfbaro.common.StringUtil"
            %>
<%
	UserDefinition uInfo = UserInfoHolder.getUserInfo(UserDefinition.class);
	String loclCd	 		= uInfo.getLoclCd();
	String userKNm       	= uInfo.getUserNm();
	String userENm       	= uInfo.getUserEnm();
	String orgCd			= uInfo.getOrgCd();
	String priv 			= uInfo.getPriv();
%>

<%@include file="/common/jsp/comm/include/baseSetting.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
	<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
		<jsp:param value="공통업무시스템" name="title"/>
		<jsp:param value="frame" name="type"/>
	</jsp:include>
	<ut:script type="text/javascript" src="${contextPath}/common/js/comm/globalvariable.js"></ut:script>

	<!-- 공통 : 세션정보 -->
	<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />
	<script type="text/javascript">
	var userName = gf_GetCookie('loclCd') == 'ko_KR' ? "<%=userKNm%>" : "<%=userENm%>";
	var ds_Auth = new DataSet();
	function f_CloseFrame() {
		if ( gf_ConfirmMsg("co.cfm.appClose") ) {
			var valuePath = window.top == window ? window : top;
			valuePath.f_CloseFrame();
		}
	}
	function f_makePrivList() {
		ds_Auth.setAllData(gv_cmasComm.auth['AUTH_LIST'].auth);

		//권한  DataSet Binding
		if ( gf_GetCookie("loclCd") == "ko_KR") {
			ds_Auth.bind($("select[name='authCd']")[0], {val: "orgCd", text: "value"});
			$("#title").removeClass("en");
			$("#settingBtn").removeClass("en");

		}
		else {
			ds_Auth.bind($("select[name='authCd']")[0], {val: "code", text: "orgNmEn"});
			//$("#title").css("background","url("+ gv_ContextPath +"/common/images/top/title_En.png)");
			$("#title").addClass("en");
			$("#settingBtn").addClass("en");

		}

		//권한 Select Box 선택
		$("select[name='authCd']").change(function(){
			var orgCd = gf_GetValue($("select[name='authCd'] option:selected").val());
			var orgNm  = gf_GetValue($("select[name='authCd'] option:selected").text());
			ds_Auth.filter(
				function (DataSetRow) {
					if ( DataSetRow.get("value") == orgNm && DataSetRow.get("orgCd") == orgCd ) {
						return true;
					}
					return false;
				}
			);

			if ( ds_Auth.size() > 1 ) {
				alert('error ');
			}

			var strPrivCd = ds_Auth.get(0, "code");
			var strSiteCd = ds_Auth.get(0, "siteCd");
			var strOrgCd = ds_Auth.get(0, "orgCd");
			// filter 해제
			ds_Auth.filter(null);
			f_ChangePriv(strPrivCd, strSiteCd, strOrgCd);
			$("select[name='authCd']").val(orgCd);

		} );

		var langCd = gf_GetCookie('loclCd');
		$("select[name='langCd']").val(langCd);
		//언어 검색 Select Box 선택
		$("select[name='langCd']").change(function(){
			if ( !gf_ConfirmMsg("co.inf.changeLocale") ) {
				$("select[name='langCd']").val(gf_GetCookie('loclCd'));
				return;
			}
			var l_langCd = gf_GetValue($("select[name='langCd'] option:selected").val());
			//var orgNm  = gf_GetValue($("select[name='langCd'] option:selected").text());
			gf_SetCookie('loclCd',l_langCd);

			top.location.reload();

		} );


		var priv = "<%=priv%>";
		$("select[name='authCd']").val(priv);

		fn_MlangSet();
	}

	function f_ChangePriv(strPrivCd, strSiteCd, strOrgCd) {
		var strSvcAct =  "/co/common/session/changePrivM.xpl";
		var datas= {
				privCd : strPrivCd,
				siteCd : strSiteCd,
				orgCd : strOrgCd
			};
		// 세션변경 transaction 호출
		gf_Transaction("SELECT_CHANGE_SESSION", strSvcAct, datas, {}, "f_Callback", true);
	}


	/**
	* @class Transaction 처리 후 수행되는 Callback 함수
	* @author 권준호
	* @since 2013-04-04
	* @version 1.0
	*/
	function f_Callback(strSvcId, obj, resultData){

		  // transaction의 정상 처리 여부를 판단한다.
		  if (!gf_ChkTransaction(strSvcId, resultData, true )) {
			  return;
		  }

		  switch(strSvcId) {
		  	case "SELECT_CHANGE_SESSION" :
		  		//f_resetGlobal();
				break;
		  	default :
		  		break;
		  }
	}

	$(function(){
		f_makePrivList();
		$('strong[name="userName"]').html(userName);
	});
	</script>
</head>
<body>

<!--top-->
<div id="top_wrap" >
	<a href="#"><img src="${contextPath}/common/images/top/logo.png" alt="DAEWOO E&C" class="logo"/></a>
	<img src="${contextPath}/common/images/top/logo_txt.png" alt="DAEWOO E&C Information Technology" class="txt"/>
    <!-- <div class="title" id="title">공통업무시스템</div> -->

	<div class="info2">
        <span class="name">
			<strong>언어</strong>
			<span class="gap"></span>

			<span class="position">
				<select name="langCd" id="langCd" style="width:70px" >
					<option value="ko_KR">Korean</option>
					<option value="en_US">English</option>
				</select>
			</span>
    	</span>
	</div>

    <div class="info f_r">
        <span class="name">
			<strong name="userName"></strong>
			<span class="gap"></span>

			<span class="position"><select name="authCd" id="authCd" style="width:200px" ></select></span>
		</span>
	</div>
    	<div class="btn_logout"><a href="#0" onclick="f_CloseFrame()"></a></div>
</div>
<!--//top-->

</body>
</html>