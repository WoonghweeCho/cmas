<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="자료목록 리스트" name="title" />
	<jsp:param value="proxy" name="popupType"/>
</jsp:include>

<!-- 공통 : 세션정보 -->
<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />

<!-- JavaScript Logic 처리 -->
<ut:script src="${contextPath}/tech/publication/publicationDataAppnList.js"></ut:script>

<!-- HTTP Request Parameter 처리 -->
<script type="text/javascript">
	var v_userId = gv_userId;
	var v_userNm = gv_userNm;
	var v_orgCd = gv_orgCd;
	var v_orgNm = gv_orgNm;
	var v_userPositCd = gv_userPositCd;
	var v_userRpswrkCd = gv_userRpswrkCd;
</script>

<link href="${contextPath}/common/css/base.css" type="text/css" rel="stylesheet" />
</head>

<!--top_bar-->
<div id="map_bar">
	<span class="left"></span><span class="right"></span>
	<div class="map">
	<span></span> &nbsp;>&nbsp; <strong>서지정보등록</strong>
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
				<a class="btn s1" id="search"><span>조회</span></a>
				<a class="btn s5" id="publicationDataDraft"><span>간행물등록</span></a>
			</div>

				<div class="bg_T">
					<span class="left"></span><span class="right"></span>
				</div>
				<div class="content">
					<form id="searchForm">
						<table id="table_sign_search" width="100%" border="0"
							cellspacing="0" cellpadding="0">
							<col width="81px" />
							<col width="" />
							<col width="81px" />
							<col width="350px" />
							<col width="81px" />
							<col width="" />
							<col width="81px" />
							<col width="" />
							<tr>
							   	<td class="tit">표제</td>
								<td class="inpt"><input name="subject" id="subject"
								    style="width: 500px" class="int_s1"></td>
								<td class="tit">등록번호</td>
								<td class="inpt"><input name="issueRegNo" id="issueRegNo"
								    style="width: 150px" class="int_s1"></td>
								<td class="inpt"><select name="docSts" style="width: 100px" class="sel_st1">
									<option value="ALL" selected="selected">전체</option>
									<option value="D16">임시저장</option>
									<option value="D17">등록신청</option>
									<option value="D18">등록완료</option>
									</select>
								</td>
							</tr>
						</table>
					</form>
				</div>

				<div class="bg_B">
					<span class="left"></span><span class="right"></span>
				</div>
			</div>
			<!--//검색설정-->

			<br>

			<!--자료  목록-->
			<div class="box">
				<!--리스트-->
				<div class="list_st2" style="min-height:315px;">
					<table id="techDataList" style="width: 900px;"></table>
					<div id="techDataListPager"></div>
				</div>
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