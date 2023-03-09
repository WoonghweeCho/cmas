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
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<head>
	<jsp:include page="/common/jsp/comm/include/clientLib_ie9.jsp">
		<jsp:param value="[업무시스템 명]" name="title"/>
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
	.infomsg {
	  display:none;
	}
	</style>

<title>메인 페이지</title>
<script>
// 이 페이지가 로드될때 실행되는 함ㅅ
var oniFrameload = function () {
};

</script>
	<!-- Common Library 처리 -->


	<!-- JavaScript Logic 처리 -->
	<ut:script src="${contextPath}/info/site/siteLoc.js"></ut:script>

	<!-- 구글 맵 api -->
	<script src="https://maps.googleapis.com/maps/api/js?v=3&key=AIzaSyDAY9NFNlMhPHzu9dNG9wGm9-dA0PDrXHk"></script>

	<link href="${contextPath}/common/css/base.css" type="text/css" rel="stylesheet" />
</head>
<body onload="oniFrameload()">
	<!--right-->
	<div id="container">
		<!-- 지도 -->
		<table>
			<tr>
				<td><div id="siteLocMap" style="width:400px;height:300px"></div></td>
				<td><!--목록-->

		<div class="box" style="float:left">


			<!--리스트-->
			<div class="list_st2" style="min-height:270px;">
				<table id="siteLocList" style="width: 900px;"></table>
				<div id="siteLocListPager"></div>
			</div>
			<!--//리스트-->
		</div>
		<!--//목록-->

				</td>
			</tr>
		</table>
		<!--//CONTENTS-->
		<div class="bottom_bg"><span class="left"></span><span class="right"></span></div>
	</div>
	<!--//right-->

</body>
</html>