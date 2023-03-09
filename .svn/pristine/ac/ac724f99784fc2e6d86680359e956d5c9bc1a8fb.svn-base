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
<%@page import="com.nets.sso.agent.AuthCheck"%>
<%@page import="com.nets.sso.agent.SSOConfig"%>
<%@page import="com.nets.sso.agent.Util"%>
<%@page import="java.util.ResourceBundle"%>
<%@ page import="docfbaro.common.ServletUtil" %>
<%@ page import="java.util.Properties" %>

<%
  	response.setHeader("Pragma", "no-cache;");
  	response.setHeader("Expires", "-1;");

  	String contextPath = request.getContextPath();
  	String imagePath =   contextPath + "/images";
  	String cssPath =     contextPath + "/common/css";
  	String jsPath =      contextPath + "/common/js";

	String svrAddr = request.getServerName();
	int svrPort = request.getServerPort();
	String context = request.getContextPath();
	String centerUrl = "http://" + svrAddr + ":" + Integer.toString(svrPort) + context + "/";
	System.out.println("===================== centerUrl :" + centerUrl);

	String error = request.getParameter("error");
	String userId = "userId";

	String loginUrl = centerUrl + "common/jsp/comm/SsoOpenXpl.jsp";

	//SSO 처리
	SSOConfig.request = request;
    AuthCheck auth = new AuthCheck(request, response);
    String returnUrl = Util.uRLEncode(loginUrl , "UTF8");

    String siteDNS = request.getServerName();
    String ssositeValue = "?" + SSOConfig.REQUESTSSOSITEPARAM + "=" + siteDNS;
    String logonUrl = SSOConfig.logonPage() + ssositeValue;
    System.out.println("logonurl->"+logonUrl);
	//SSO 처리 끝.
%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
	<title>대우건설 공통업무시스템</title>
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
	<style type="text/css">
	<!--
	html{width:100%;height:100%;}
	body {width:100%;height:100%;margin:0;padding:0;background:url(${contextPath}/common/images/login/bg.png) repeat;}

	#wrap{width:100%;height:100%;background:url(${contextPath}/common/images/login/bg_hall.png) center center no-repeat;}
	#con_wrap{position:absolute;top:50%;left:50%;width:771px;height:447px;overflow:hidden;margin:-223px 0 0 -385px;z-index:1;}
	#con_wrap .logo{margin-bottom:18px}
	#con_wrap .visual_w{height:367px;background:url(${contextPath}/common/images/login/visual_img.png) no-repeat;}
	#con_wrap .copy{margin:22px 0 0 269px;}

	#con_wrap .log_box{float:right;width:243px;height:367px;background:url(${contextPath}/common/images/login/log_box.png) no-repeat;}
	#con_wrap .inp2{height:33px;width:189px;color:#b2b2b2;font:normal 11px Verdana,sans-serif;border:0;padding-left:12px;line-height:33px;margin-bottom:4px}
	#con_wrap .bt_login{margin-top:5px;cursor:pointer;}
	#con_wrap .radio_area{font:normal 11px Verdana, sans-serif;color:#fff;height:43px}
	#con_wrap .radio_area input{margin:0 0 -5px -2px;}
	#con_wrap .txt1{margin:20px 0 0 23px;}
	#con_wrap .txt2{margin:79px 0 0 23px;}
	-->
	</style>

	<script type="text/javascript">

	$(document).ready(function(){
		var userId = gf_GetCookie('userId');
		if(userId != null && $.trim(userId) != "") {
			$('#userId').val(userId);
		}
		if (gf_GetCookie('rememberId') == 'true') {
			$('#rememberId')[0].checked = true;
		}
		$('#userId').focus();
		$('#submit').attr("disabled", true);

	    // RSA key 재생성
	    //gf_GetRSAKey(document.getElementById('f'));

	    // 로그인폼에 action 바인딩 sso 로그인 처리를 위함
	    var fm = $("form[name='loginForm']");
		fm.attr('action', '<%=logonUrl%>');
	});

	function f_Login(type) {
		var fm = $("form[name='loginForm']");

		if( $('#userId').val() == '' ) {
			gf_AlertMsg('co.err.noUserId');
			$('#userId').focus();
			return;
		}

		if( $('#userPwd').val() =='' ) {
			gf_AlertMsg('co.err.wrongPasswd');
			$('#userPwd').focus();
			return;
		}

		$('#type').val(type);

		var loclCd = ($("input[name='rdoLan']:checked").val());
		$('#f')[0].loclCd.value = loclCd;
		gf_SetCookie('rememberId', $('#rememberId')[0].checked);
		gf_SetCookie('userId', $('#rememberId')[0].checked ? $('#userId').val() : '');
		gf_SetCookie('loclCd',loclCd);

		// sso 도입 이후로 암호화 로그인 없어짐
	    //cfEncryptedLogin($('#loginForm')[0], $('#f')[0], loclCd);
	    f_SsoLoginCall();

	}

	//sso 로그인 으로 처리
	function f_SsoLoginCall() {

		document.loginForm.submit();
	}


	function f_CheckUserNm() {
		// 보안상의 이유로 기능 중지
	// 	return;
		if(loginForm.userId.value == '') return;
		var params = "{\"params\" : {\"qId\" : \"loginXP.chkUserLogin\", \"<%=userId%>\" : \"" +loginForm.userId.value + "\"  }}";
		$.ajax({
		    type: "post",
		    url: '<%= request.getContextPath() %>/co/common/login/existUserId.xpl',
		    data: params,
		    datatype: "json",
		    contentType: "application/json+sua; charset=utf-8",
		    success: function(data){
				var msg;
		    	if(data && data.success) {
		    		msg = data.success[0];
		    		if(msg && msg != '') {
			    		$('#submit').attr("disabled", true);
			    	}
		    	} else {
		    		$('#submit').attr("disabled", true);
		    		cfGetMsg("co.err.noUserId");
		    	}
		    }
		});
	}
	</script>
</head>

<body>

<form id='loginForm' name='loginForm' method='post'>
<input type='hidden' name='type' />
<input type="hidden" name="<%=SSOConfig.credentialTypeTagName()%>" value="BASIC" >
<!-- CM:WINDOWSAVEDCREDENTIAL / Normal:BASIC-->
<input type="hidden" name="<%=SSOConfig.returnURLTagName()%>" value="<%=returnUrl%>" />
<div id="wrap">
	<div id="con_wrap">
    	<img src="${contextPath}/common/images/login/logo.png" alt="It's Possible 대우건설" class="logo"/>
    	<div class="visual_w">

            <div class="log_box">

                <table border="0" cellspacing="0" cellpadding="0" align="center" width="201px" style="margin-top:21px">
                  <tr>
                    <td colspan="2"><input name="<%=SSOConfig.iDTagName()%>" id="userId" type="text" value="ID" class="inp2" onBlur="f_CheckUserNm();" /> </td>
                  </tr>
                  <tr>
                    <td colspan="2"><input name="<%=SSOConfig.pwdTagName()%>" type="password" value="" class="inp2" onKeyPress="javascript:if(event.keyCode == 13)f_Login('T');" /></td>
                  </tr>
                  <tr>
                    <td>
                  <div style="font:normal 11px Verdana,sans-serif;margin:10px 0 0 0;">
                            <input name="rememberId" id="rememberId" type="checkbox"  checked="checked" align="top" style="visibility:hidden;float:left;"/>
                            <div  style="visibility:hidden;float:left;margin:4px 15px 0 3px;color:#fff;">Id Saved </div>
                        </div>
                    </td>
                    <td width="71px" align="right" onclick="f_Login('T')" style="cursor:hand"><img src="${contextPath}/common/images/login/btn_log.png" class="bt_login"/></td>
                  </tr>
                  <tr>
                  <td colspan="2" class="radio_area">
                    	<input name="rdoLan" type="radio" value="ko_KR" checked="checked" />Korean &nbsp;
                        <input name="rdoLan" type="radio" value="en_US" />English &nbsp;
                    </td>
                  </tr>
                </table>

           	    <img src="${contextPath}/common/images/login/txt1.png" class="txt1" alt="비밀번호는 대소문자 구분합니다. IE 필수 프로그램 설치"/>
                <img src="${contextPath}/common/images/login/txt2.png" class="txt2" alt="비밀번호 분실시 업무지원-IT도우미-비밀번호찾기-바로넷SVPN초기화로 동료에게 요청."/>
          </div>

        </div>
    	<img src="${contextPath}/common/images/login/copy.png" class="copy" alt="ⓒ2013 DAEWOO Engineering & Construction CO. LTD."/>
	</div>
</div>
</form>
<form name='f' id='f' action='${pageContext.request.contextPath}/j_spring_security_check?pt=true' method='POST'>
	<input type="hidden" id="j_username" name="j_username" value="" />
	<input type="hidden" id="j_password" name="j_password" value="" />
	<input type="hidden" id="loclCd" name="loclCd" value="ko_KR">
	<input type="hidden" id="centerUrl" name="centerUrl" value="<%=centerUrl%>">
	<input type="hidden" id="publicKeyModulus" name="publicKeyModulus" value="<%=WebContext.getRequest().getAttribute("publicKeyModulus")%>">
	<input type="hidden" id="publicKeyExponent" name="publicKeyExponent" value="<%=WebContext.getRequest().getAttribute("publicKeyExponent")%>">
</form>
<div id="poppy" style="visiblility:hidden"></div>
</body>
</html>