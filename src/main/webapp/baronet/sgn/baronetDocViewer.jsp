<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Properties" %>
<%@page import="org.apache.commons.codec.binary.Base64" %>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@taglib prefix="c" uri="/c_rt" %>
<%@taglib prefix="fmt" uri="/fmt" %>
<%@taglib prefix="fn" uri="/fn" %>
<%@taglib prefix="ut" uri="/ut" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="signCd" value='<%=request.getParameter("signCd") %>' />
<c:set var="username" value='<%=request.getParameter("username") %>' />
<c:set var="password" value='<%=request.getParameter("password") %>' />
<c:set var="unid" value='<%=request.getParameter("unid") %>' />
<%
//String encodeUsername = Base64.encodeBase64String(request.getParameter("password").getBytes());
//System.out.println(encodeUsername);

	WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
	Properties prop = (Properties) context.getBean("appProperties");

	String decodeUsername = new String(Base64.decodeBase64(request.getParameter("username")));
	String decodePassword = new String(Base64.decodeBase64(request.getParameter("password")));
%>
<!DOCTYPE html>
<html lang="ko">
<head>

<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0, target-densitydpi=medium-dpi" />
<title>결재 문서 열람</title>

<ut:link href="${contextPath}/baronet/css/base.css" rel="stylesheet" type="text/css" />

<ut:script type="text/javascript" src="${contextPath}/common/js/framework/json2.js"></ut:script>
<ut:script type="text/javascript" src="${contextPath}/common/js/framework/jquery-1.7.2.js"></ut:script>
<ut:script type="text/javascript" src="${contextPath}/common/js/sign/iscroll.js"></ut:script>

<script type="text/javascript">

	var login = "<%=prop.getProperty("dwe.baronetgw.domain") %>/names.nsf?login&username=<%=decodeUsername %>&password=<%=decodePassword %>&redirectto=";

	$(document).ready(function() {
		//아이폰 플로팅버튼 제어
		if (navigator.platform == 'iPad' || navigator.platform == 'iPhone' || navigator.platform == 'iPod' || navigator.platform == 'Linux armv6l') {
			var iscroll = new iScroll("contents");
			$("#contents").css("height", window.innerHeight);
		}

<c:choose>
	<c:when test="${signCd eq ''}">
		fn_alert("결재 구분 코드가 올바르지 않습니다.");
	</c:when>
	<c:when test="${username eq ''}">
		fn_alert("아이디가 올바르지 않습니다.");
	</c:when>
	<c:when test="${password eq ''}">
		fn_alert("비밀번호가 올바르지 않습니다.");
	</c:when>
	<c:when test="${unid eq ''}">
		fn_alert("결재문서의 키 값이 올바르지 않습니다.");
	</c:when>
</c:choose>
	});

	var goSign = function(type) {
		var url = type == "sign" ? $("#signURL").attr("data-url") : $("#rejectURL").attr("data-url");
		var parameter = {};
		parameter["param"] = [{
		            "username": "${username }",
		            "password": "${password }",
		            "goURL": url + "<%=decodeUsername %>"
		    	}];
		$.ajax({
			type: "post",
		    url: "<c:url value="/baronet/sgn/baronetDocViewer.baro" />",
		    data : JSON.stringify(parameter),
		    datatype: "json",
		    contentType: "application/json+sua; charset=utf-8",
		    success: function(data){
		    	//서버에서 data.result[0].* 로 넘어온다.(JSONArray 형태로 넘어옴.)
		    	if(data.result != undefined) {
		    		var result = data.result[0];

		    		if(result != undefined && result.result == "success" ) {
		    			if(type == "sign") {
		    				fn_alert("결재가 완료 되었습니다.");
		    			}
		    			else {
		    				fn_alert("반려가 완료 되었습니다.");
		    			}
		    		}
		    		else if(result.result == "nodata") {
		    			fn_alert("No Data!!!");
		    		}
		    		else if(result.result == "error") {
		    			fn_alert(result.resultMsg);
		    		}
		    	}
		    	else {
		    		fn_alert("No Data!!!");
		    	}
		    }
		});
	};

	var fn_alert = function(msg) {
		$(".float_btn").html("<span>" + msg + "</span>");
		$(".float_btn").css({"background-color":"#97a9ca", "font-size":"20px", "font-weight":"bold", "color":"red", "padding-top":"15px"});
	};
</script>

</head>

<body>

<div id="contents" class="wrapper">

	<!-- contents -->
<c:choose>
<c:when test="${signCd == 'DWEP050001' }"><%-- 업무공지 --%>
	<c:import url="baronetSign050001.jsp" charEncoding="utf-8">
		<c:param name="username" value="${username }" />
		<c:param name="password" value="${password }" />
		<c:param name="unid" value="${unid }" />
	</c:import>
</c:when>
<c:when test="${signCd == 'DWEP080002' }"><%-- 수주정보관리(주택) --%>
	<c:import url="baronetSign080002.jsp" charEncoding="utf-8">
		<c:param name="username" value="${username }" />
		<c:param name="password" value="${password }" />
		<c:param name="unid" value="${unid }" />
	</c:import>
</c:when>
<c:when test="${signCd == 'DWEP080003' }"><%-- 수금통보(주택) --%>
	<c:import url="baronetSign080003.jsp" charEncoding="utf-8">
		<c:param name="username" value="${username }" />
		<c:param name="password" value="${password }" />
		<c:param name="unid" value="${unid }" />
	</c:import>
</c:when>
<c:when test="${signCd == 'DWEP020006' }"><%-- 지정형 법인카드-한도증액 --%>
	<c:import url="baronetSign020006.jsp" charEncoding="utf-8">
		<c:param name="username" value="${username }" />
		<c:param name="password" value="${password }" />
		<c:param name="unid" value="${unid }" />
	</c:import>
</c:when>
<c:when test="${signCd == 'DWEP020001' }"><%-- 지정형 법인카드-신규발급 --%>
	<c:import url="baronetSign020001.jsp" charEncoding="utf-8">
		<c:param name="username" value="${username }" />
		<c:param name="password" value="${password }" />
		<c:param name="unid" value="${unid }" />
	</c:import>
</c:when>
<c:when test="${signCd == 'DWEP020002' }"><%-- 지정형 법인카드-일시정지해지 --%>
	<c:import url="baronetSign020002.jsp" charEncoding="utf-8">
		<c:param name="username" value="${username }" />
		<c:param name="password" value="${password }" />
		<c:param name="unid" value="${unid }" />
	</c:import>
</c:when>
<c:when test="${signCd == 'DWEP020003' }"><%-- 지정형 법인카드-재발급 --%>
	<c:import url="baronetSign020003.jsp" charEncoding="utf-8">
		<c:param name="username" value="${username }" />
		<c:param name="password" value="${password }" />
		<c:param name="unid" value="${unid }" />
	</c:import>
</c:when>
<c:when test="${signCd == 'DWEP020004' }"><%-- 지정형 법인카드-정보변경 --%>
	<c:import url="baronetSign020004.jsp" charEncoding="utf-8">
		<c:param name="username" value="${username }" />
		<c:param name="password" value="${password }" />
		<c:param name="unid" value="${unid }" />
	</c:import>
</c:when>
<c:when test="${signCd == 'DWEP020005' }"><%-- 지정형 법인카드-클린카드제외 --%>
	<c:import url="baronetSign020005.jsp" charEncoding="utf-8">
		<c:param name="username" value="${username }" />
		<c:param name="password" value="${password }" />
		<c:param name="unid" value="${unid }" />
	</c:import>
</c:when>
<c:when test="${signCd == 'DWEP020007' }"><%-- 지정형 법인카드-해지 --%>
	<c:import url="baronetSign020007.jsp" charEncoding="utf-8">
		<c:param name="username" value="${username }" />
		<c:param name="password" value="${password }" />
		<c:param name="unid" value="${unid }" />
	</c:import>
</c:when>
<c:when test="${signCd == 'DWEP030003' }"><%-- SAP ID 신청/변경 --%>
	<c:import url="baronetSign030003.jsp" charEncoding="utf-8">
		<c:param name="username" value="${username }" />
		<c:param name="password" value="${password }" />
		<c:param name="unid" value="${unid }" />
	</c:import>
</c:when>
<c:when test="${signCd == 'DWEP030002' }"><%-- SAP ID 보류/삭제 --%>
	<c:import url="baronetSign030002.jsp" charEncoding="utf-8">
		<c:param name="username" value="${username }" />
		<c:param name="password" value="${password }" />
		<c:param name="unid" value="${unid }" />
	</c:import>
</c:when>
<c:when test="${signCd == 'DWEP030001' }"><%-- Cloud 서비스 신청 --%>
	<c:import url="baronetSign030001.jsp" charEncoding="utf-8">
		<c:param name="username" value="${username }" />
		<c:param name="password" value="${password }" />
		<c:param name="unid" value="${unid }" />
	</c:import>
</c:when>
<c:otherwise>
	NO DATA!!! Parameter is wrong.
</c:otherwise>
</c:choose>
	<!-- //contents -->

</div>

<div class="float_btn">
	<a id="signURL" href="javascript:goSign('sign')" class="btn gray" data-url="">결재</a>
	<a id="rejectURL" href="javascript:goSign('reject')" class="btn white" data-url="">반려</a>
</div>

<div id="mask" style="display:none; position:absolute; top:0; left:0; width:100%; height:100%; background:rgba(0, 0, 0, 0.8); z-index:1000;"></div>

</body>
</html>