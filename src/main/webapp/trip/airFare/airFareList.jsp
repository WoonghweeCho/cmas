<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType"/>
	<jsp:param value="결재시스템" name="title" />
</jsp:include>

<!-- JavaScript Logic 처리 -->
<ut:script src="${contextPath}/trip/airFare/airFareList.js"></ut:script>

<!-- HTTP Request Parameter 처리 -->
<script type="text/javascript">

</script>

<link href="${contextPath}/common/css/base.css" type="text/css"
	rel="stylesheet" />
</head>


<body>
	<!--
	Class Name 	: innerTripList.jsp
	Description : 작성함 - 양식관리에 등록된 결재양식 목록
 -->
	<!--right-->
	<div id="container">

		<!--top_bar-->
		<div id="map_bar">
			<span class="left"></span><span class="right"></span>
			<div class="map">
				<span>항공료</span> &nbsp;>&nbsp; <strong>항공료 리스트</strong>
			</div>
		</div>
		<!--//top_bar-->

		<!--CONTENTS-->
		<div id="contents">

			<!--검색설정-->
			<div class="int_box">
			<!-- Button -->
			<div class="btn-area">
				<a class="btn s1" id="signSearch"><span>조회</span></a>
			</div>

				<div class="bg_T">
					<span class="left"></span><span class="right"></span>
				</div>
				<div class="content">
					<form id="searchForm">
						<table id="table_sign_search" width="100%" border="0"
							cellspacing="0" cellpadding="0">
							<col width="100px" />
							<col width="" />
							<col width="100px" />
							<col width="" />
							<col width="100px" />
							<col width="" />
							<col width="100px" />
							<col width="" />
							<col width="100px" />
							<col width="" />
							<col width="100px" />
							<col width="300px" />
							<tr>
								<td class="tit">영문</td>
								<td class="inpt">
									<input type="text" id="userEnm" name="userEnm" style="width: 130px" class="int_st1">
								</td>
								<td class="tit">이름</td>
								<td class="inpt">
									<input type="text" id="userKnm" name="userKnm" style="width: 130px" class="int_st1">
								</td>

								<td class="tit" style="display:none">REF NO</td>
								<td class="inpt" style="display:none">
									<input type="text" id="refNo" name="refNo" style="width: 130px" class="int_st1">
								</td>

								<td class="tit">문서상태</td>
								<td class="inpt">
									<select name="docStsCd" id="docStsCd" style="width: 100px" class="sel_st1">
										<option value="" selected="selected">전체</option>
										<option value="Y">입력완료</option>
										<option value="N">입력전</option>
									</select>
								</td>
								<td class="tit">여행사</td>
								<td class="inpt">
									<select name="tourNm" id="tourNm" style="width: 100px" class="sel_st1">
										<option value="" selected="selected">전체</option>
										<option value="D">동서여행사</option>
										<option value="H">현대드림투어</option>
										<option value="J">주원항공여행사</option>
										<option value="N">하나투어</option>
									</select>
								</td>
								<td class="tit">조회기간</td>
								<td class="inpt">
								<select name="simpleDt" id='simpleDt' style="width:90px" class="sel_st1 f_l mar_r5">
									<option value="7" selected="selected">최근 1주</option>
									<option value="30">최근 1달</option>
									<option value="60">최근 2달</option>
									<option value="90">최근 3달</option>
									<option value="180">최근 6달</option>
									<option value="360">최근 1년</option>
									<option value="DT">상세지정</option>
								</select>
									<div id='detailDt' style="display:none">
										<span class="f_l">시작일&nbsp;:&nbsp;</span>
										<input type="text" name="startDate" style="width:90px" class="int_s1 f_l">
										<span class="f_l">&nbsp;~&nbsp;</span>
										<span class="f_l">종료일&nbsp;:&nbsp;</span>
										<input type="text" name="endDate" style="width:90px" class="int_s1 f_l">
									</div>
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

			<!--임시저장함 목록-->
			<div class="box">
				<h3><span id="airFareListCnt"></span> <span>목록</span></h3><span class="cont" ><span id="listCnt"></span></span>

				<!--리스트-->
				<div class="list_st2" style="min-height:450px;">
					<table id="airFareList" style="width: 900px;"></table>
					<div id="airFareListPager"></div>
				</div>
				<!--//리스트-->
			</div>
			<!--//임시저장함 목록-->
		</div>
		<!--//CONTENTS-->
		<div class="bottom_bg">
			<span class="left"></span><span class="right"></span>
		</div>
	</div>
	<!--//right-->

</body>
</html>