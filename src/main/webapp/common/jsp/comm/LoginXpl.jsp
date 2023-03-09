<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.HashMap,
            java.util.Map,
            java.util.List,
            java.util.ArrayList,
            docfbaro.iam.UserInfo,
            docfbaro.iam.authentication.UserDefinition,
            jcf.iam.core.common.util.UserInfoHolder,
            docfbaro.common.WebContext,
            docfbaro.common.Constants"
            %>
<%-- JSTL 1.2 Standard Tag Declaration --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c_rt" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- Spring Tag Declaration --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader("Expires",0);
  if (request.getProtocol().equals("HTTP/1.1"))
          response.setHeader("Cache-Control", "no-cache");

  String contextPath = request.getContextPath();
  String imagePath =   contextPath + "/images";
  String cssPath =     contextPath + "/common/css";
  String jsPath =      contextPath + "/common/js";

%>
<c:set var="imagePath" value="${pageContext.request.contextPath}/images" />
<c:set var="cssPath" value="${pageContext.request.contextPath}/common/css" />
<c:set var="jsPath" value="${pageContext.request.contextPath}/common/js" />

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<base href="<%--=baseUrl+"/" --%>" ></base>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv="Expires" content="-1"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="No-Cache"/>
<script type='text/javascript' src='${pageContext.request.contextPath}/js/framework/jquery-1.7.2.js' ></script>
<script type="text/javascript" src="${jsPath}/dwedu.js"></script>
<script type="text/javascript">

    var gContextPath   = '<%=contextPath%>';

</script>


<%@ page import="docfbaro.common.ServletUtil" %>
<%@ page import="java.util.Properties" %>
<%@ page import="com.dwenc.cmas.common.utils.ConfigUtil" %>
<%
	String svrAddr = request.getServerName();
	int svrPort = request.getServerPort();
	String context = request.getContextPath();
	String centerUrl = "http://" + svrAddr + ":" + Integer.toString(svrPort) + context + "/";
	System.out.println("===================== centerUrl :" + centerUrl);

	String error = request.getParameter("error");
	String userId = "userId";


%>
<script type='text/javascript' src='<%=centerUrl%>common/js/framework/jquery-1.7.2.js' ></script>
<script type="text/javascript" src="<%=centerUrl%>common/js/dwedu.js"></script>
<title>:::: 대우건설 공통업무시스템 ::::</title>
<style type="text/css">

body {
	margin:0px;
	font-family:Verdana, Geneva, sans-serif, Gulim;
	font-size: 9pt;
	color: #333333;
	background:white;
}
.logininput {
	border:1px solid #c6c6c4;
	font-size:9pt;
	background-color:#fefefe;
	color:#6e7070;
	padding-left:5px;
}
.notice_font01 {
	font-family: Gulim;
	font-size:9pt;
	color:#242323;
	line-height:15px;
	padding:0px;
}

.notice_font02 {
	font-family: Gulim;
	font-size:8pt;
	color:#939393;
}

.downcenter {
	border:1px solid #c6c6c4;
	font-size:11pt;
	background-color:#4972d9;
	color:#ffffff;
	padding-left:5px;
	cursor:hand;
}

img {
	border:0px;
}

a:link { color:#204040;text-decoration:none;}
a:visited { color:white;text-decoration:none;}
a:hover { color:black;text-decoration:underline;}

.ellipsis {
    overflow:hidden;
    white-space:nowrap;
    text-overflow:ellipsis;
}
</style>
<!--
<script src="<%= centerUrl %>lib/js/swfobject_modified.js" type="text/javascript"></script>
-->
<!-- <script src="<%= centerUrl %>jsp/common/coPortalMain/js/swfobject_modified.js" type="text/javascript"></script> -->

<!-- 순서 주의 : http://www-cs-students.stanford.edu/~tjw/jsbn/-->
<script type="text/javascript" src="<%=centerUrl%>common/js/jsbn.js"></script>
<script type="text/javascript" src="<%=centerUrl%>common/js/rsa.js"></script>
<script type="text/javascript" src="<%=centerUrl%>common/js/prng4.js"></script>
<script type="text/javascript" src="<%=centerUrl%>common/js/rng.js"></script>
<script type="text/javascript" src="<%=centerUrl%>common/js/framework/jquery.base64.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	var userId = cfGetCookie('userId');
	if(userId != null && jQuery.trim(userId) != "") {
		$('#userId').val(userId);
	}
	if (cfGetCookie('rememberId') == 'true') {
		$('#rememberId')[0].checked = true;
	}
	$('#userId').focus();
	$('#submit').attr("disabled", true);

    // RSA key 재생성
    cfGetRSAKey(document.getElementById('f'));
});

function fcLogin(type) {
	var fm = document.loginForm;

	if(fm.userId.value=='') {
		cfGetMsg('co.err.noUserId');
		fm.userId.focus();
		return;
	}
	if(fm.userPwd.value=='') {
		cfGetMsg('co.err.wrongPasswd');
		fm.userPwd.focus();
		return;
	}
	fm.type.value = type;

	var loclCd = ($("input[name='rdoLan']:checked").val());
	$('#f')[0].loclCd.value = loclCd;
	cfSetCookie('rememberId', $('#rememberId')[0].checked);
	cfSetCookie('userId', $('#rememberId')[0].checked ? $('#userId').val() : '');
    cfEncryptedLogin($('#loginForm')[0], $('#f')[0], loclCd);
}

function fnKeyHandleID(){
	var fm = document.loginForm;
	// ID를 대문자로 바꾸고, Enter키 누를때 PW입력란으로 포커스이동
	if(event.keyCode == 13) {
		fm.userid.value = fm.userid.value.toUpperCase();
	    fm.pwd.focus();
	}
}

function fnCheckUserNm() {
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

// RSA 암호화 모듈로 대체됨
function fnLoginCall() {
	var userId = cfHangle(loginForm.userId.value);
	var passwd = cfHangle(loginForm.userPwd.value);
	$('#j_username').val(userId);
	$('#j_password').val(passwd);

	var frm = $('#f')[0];

	var ifm = cfGetPoppyFrame(document);
	frm.target = ifm.name;
	frm.submit();
}

function fnClose() {
	if (cfGetMsieYn() == true)  {
       window.opener = "nothing";
       window.open('', '_parent', '');
       window.close();
 	} else {
 		window.opener = self;
 		self.close();
 	}
}



if(!cfGetMsieYn()){
	/*alert("브라우저 IE7부터 사용가능합니다.");
	if(confirm("IE8로 업데이트 하시겠습니까?")){
		location.href="http://windows.microsoft.com/ko-KR/internet-explorer/downloads/ie-8";
	}*/
}



</script>
</head>

<body bgcolor=black >

<form id='loginForm' name='loginForm' method='post'>
<input type='hidden' name='type' />
<table width="1000" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td height="430">
		<img src="<%=centerUrl%>common/images/login/Main_Visual_PT_ko.jpg" />
    </td>
  </tr>
  <tr>
    <td height="124" align="center" valign="top">
	    <table width="982" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="275" style="padding:20px 0 0 0;" valign="top" align="center">
		        <table width="275" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td align="left"><img src="<%=centerUrl%>common/images/login/main_title01.gif" /></td>
		          </tr>
		          <tr>
		            <td height="14"></td>
		          </tr>
		          <tr>
		            <td>
		            	<!-- MEMBER'S LOGIN -->
			            <table border="0" cellspacing="0" cellpadding="0">
			              <tr>
			                <td width="140" align="left"><img src="<%=centerUrl%>common/images/login/main_login01.gif" /></td>
			                <td width="120"><input type="text" tabindex='1000' name="userId" id="userId" onBlur="fnCheckUserNm();" class="logininput" style="width:115px; height:20px; " value=""/></td>
			                <td width="83" rowspan="3" align="left"><a tabindex='1002' onclick="fcLogin('T')"><img src="<%=centerUrl%>common/images/login/main_login_btn.gif" style="cursor:hand;" /></a></td>
			              </tr>
			              <tr>
			                <td align="left"><img src="<%=centerUrl%>common/images/login/main_login02.gif" /></td>
			                <td><input type="password" tabindex='1001' name="userPwd" id="userPwd" onKeyPress="javascript:if(event.keyCode == 13)fcLogin('T');" class="logininput" style="width:115px; height:20px;"  value=""/></td>
			              </tr>
			            </table>
			            <!-- MEMBER'S LOGIN END -->
			        </td>
		          </tr>
	              <tr height="18">
		                <td>&nbsp;</td>
		          </tr>
		          <tr>
		                <td>
		                	<span>Korean</span><input type="radio" name="rdoLan"  value="ko_KR" checked="checked">&nbsp;&nbsp;&nbsp;
		                	<span>English</span><input type="radio" name="rdoLan"  value="en_US" >
		                </td>
		          </tr>
		          <tr>
		                <td>
		                	&nbsp;&nbsp;&nbsp;
		                </td>
		          </tr>
	              <tr>
						<div class="hiddenCheck" >
						  <input type="checkbox" id="rememberId" style="visibility:hidden;" checked="checked"/>
				      	</div>
		                <td><font color="red">*아이디(사번),비밀번호는 바로넷과 동일합니다.</font></td>
		          </tr>
		          <tr>
		            <td height="10"></td>
		          </tr>
		        </table>
	        </td>

	       <!-- 다운로드 센터  -->
	        <td align="right">
	         	TODO : 다운로드 링크
	    	</td>
	       <!-- 다운로드 센터 -->

	      </tr>
	    </table>
	</td>
  </tr>
  <tr>
    <td height="20" style="padding-top:10px;">
	    <table width="1000" height="70" border="0" cellspacing="0" cellpadding="0" style="background:#FFFFFF;">
	      <tr><td style="height:10px"></td></tr>
		  <tr>
	        <td align="center" style="vertical-align:top;"><img src="<%=centerUrl%>common/images/login/main_copyright_white.gif" /></td>
	      </tr>
	    </table>
    </td>
  </tr>
</table>
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