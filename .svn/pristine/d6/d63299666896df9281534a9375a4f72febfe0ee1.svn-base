<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>

<!-- 공통 : 세션정보 -->
<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />

<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="본사 층별정보 안내" name="title"/>
	<jsp:param value="proxy" name="popupType"/>
</jsp:include>

<!-- JavaScript Logic 처리 -->
<ut:script src="${contextPath}/info/floor/hqFloor.js"></ut:script>

<!-- HTTP Request Parameter 처리 -->
<script type="text/javascript">
</script>

<link href="${contextPath}/common/css/base.css" type="text/css" rel="stylesheet" />
</head>

<!--top_bar-->
<div id="map_bar">
	<span class="left"></span><span class="right"></span>
	<div class="map">
	 &nbsp;>&nbsp;<strong>본사 층별정보 안내</strong>
	</div>
</div>
<!--//top_bar-->

<body>
	<!--right-->
	<div id="container">
		<!--CONTENTS-->
		<div id="contents">

			<!--검색설정-->
			<div class="int_box">
			<!-- Button -->
			<div class="btn-area">
				<a class="btn s5" id="btnDraft" style="display:none"><span>작성</span></a>
				<a class="btn s1" id="search"><span>조회</span></a>
			</div>
			<br>
			<!-- 목록-->
			<div class="box">
				<div class="list_st2" style="min-height:550px;">
				<table id="floorList" style="width: 800px;"></table>
				<!-- <div id="floorListPager"></div> -->
			<!--//리스트-->
			</div>
			<!--//자료 목록-->
		</div>
		<!--//CONTENTS-->
		<div class="bottom_bg">
			<span class="left"></span><span class="right"></span>
		</div>
	</div>
	<!--//right-->

</body>
</html>