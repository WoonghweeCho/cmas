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

<%@include file="/common/jsp/comm/include/baseSetting.jsp" %>
<html>
<head>
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

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>메인 페이지</title>
<script>
// 이 페이지가 로드될때 실행되는 함ㅅ
var oniFrameload = function () {
};

</script>
	<!-- Common Library 처리 -->

	<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
		<jsp:param value="[업무시스템 명]" name="title"/>
	</jsp:include>

	<!-- JavaScript Logic 처리 -->
	<ut:script src="${contextPath}/info/floor/hqFloor_s.js"></ut:script>

	<link href="${contextPath}/common/css/base.css" type="text/css" rel="stylesheet" />
</head>
<body onload="oniFrameload()">

	<!--리스트-->
	<div class="list_st2" style="min-height:200px;">
		<table id="floorList" style="width: 300px;"></table>
	</div>
	<!--//리스트-->


</body>
</html>