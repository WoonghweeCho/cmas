<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType"/>
	<jsp:param value="해외출장시스템" name="title" />
</jsp:include>

<!-- 공통 : 세션정보 -->
<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />

<!-- JavaScript Logic 처리 -->
<ut:script src="${contextPath}/trip/outerTrip/outerTripList.js"></ut:script>

<!-- HTTP Request Parameter 처리 -->
<script type="text/javascript">

</script>

<link href="${contextPath}/common/css/base.css" type="text/css" rel="stylesheet" />
</head>


<body>
	<!--
	Class Name 	: outerTripList.jsp
	Description : 해외출장 열람 목록
 -->
	<!--right-->
	<div id="container">

		<!--top_bar-->
		<div id="map_bar">
			<span class="left"></span><span class="right"></span>
			<div class="map">
				<span>해외출장</span> &nbsp;>&nbsp; <strong>해외출장신청서</strong>
			</div>
		</div>
		<!--//top_bar-->

		<!--CONTENTS-->
		<div id="contents">
			<div>
				<div style="float:left">
					<div style="color:red;font-weight:bold">** 본부별 해외출장 전담여행사 안내 **</div>
					<div style="color:blue;font-weight:bold">1. 토목사업본부, 주택건축사업본부 항공권 예약/발권 -> (주)현대드림투어 하지선 매니저</div>
					<div style="color:blue">&nbsp;&nbsp;- 평일 : Tel.5091 (본사 16층, 총무팀 상주) / - 주말,휴일(긴급 발권) H.P. 010-2009-4357 / 02-723-2233</div>
					<div style="color:blue;font-weight:bold">2. 그 외 본부/실 항공권 예약/발권 -> (주)하나투어 전시은 매니저</div>
					<div style="color:blue">&nbsp;&nbsp;- 평일 : Tel.4155 (본사 16층, 총무팀 상주) / - 주말,휴일(긴급발권) H.P. 010-5333-1758 / 02-2127-1400</div>
					<div style="color:red;font-weight:bold">당월 출장신청서 결재 지연시 "회계결산 마감"으로 결재 불가 (결재자 반려 후 재작성)</div>
					<div style="color:black;font-weight:bold"><span style="color:red;font-weight:bold">※필독 : 해외 출장 여행자 보험 </span><br>
						<a href="${contextPath}/common/document/cmas/Guide.pdf"><img src="${contextPath}/common/images/pdf.png"> 여행자 보험 안내</a>  &nbsp;&nbsp;
						<a href="${contextPath}/common/document/cmas/Information.pdf"><img src="${contextPath}/common/images/pdf.png"> 보험내역(증권)</a>
					</div>
				</div>

				<div style="float:right">
					<div style="font-weight:bold">◈ 해외 Security 문의 : 글로벌지원팀 윤경주 과장</div>
					<div style="font-weight:bold">◈ 체재비 및 항공 문의 : 총무팀 박주원 과장</div>
					<div style="font-weight:bold">◈ 예산집행팀장 협의 : 해당 예산집행팀으로 문의</div>
					<div style="font-weight:bold">◈ 협의팀 승인 : 경영관리팀 강해연 사원</div>
					<div style="font-weight:bold">◈ 기타경비 증빙 / SAP처리 문의 : 세무팀 정석모 과장</div>
					<div style="font-weight:bold">◈ 시스템 문의 : IT운영팀 나상문 과장</div>
					<div style="font-weight:bold">◈ 출장비 입금 </div>
					<div>&nbsp;&nbsp;12시 이전 결재 : 당일 / 12시 이후 결재 : 익일 </div>

				</div>
			</div>


			<!--검색설정-->
			<div class="int_box">
			<!-- Button -->
			<div class="btn-area">
				<a class="btn s1" id="outerTripError" style="display:none"><span>에러케이스 확인</span></a>
				<a class="btn s1" id="outerTripSearch"><span>조회</span></a>
				<a class="btn s5" id="outerTripDraft"><span>작성</span></a>
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
							<col width="" />
							<col width="81px" />
							<col width="" />
							<col width="81px" />
							<col width="450px" />
							<tr>
								<td class="tit">출 장 자</td>
								<td class="inpt">
									<input name="empNm" id="empNm" type="text" style="width: 130px;" class="int_s1" />
								</td>
								<td class="tit">소 속 팀</td>
								<td class="inpt"><input name="orgNmInput" id="orgNmInput" type="text"
									style="width: 130px;" class="int_s1" /></td>
								<td class="tit">REF NO</td>
								<td class="inpt"><input name="refNo" id="refNo" type="text"
									style="width: 130px;" class="int_s1" /></td>
								<td class="tit">조회기간</td>
								<td class="inpt">
									<div id='simpleDt' >
										<select name="simpleDtCd" style="width:90px" class="sel_st1 f_l mar_r5">
											<option value="14">최근 2주</option>
											<option value="31">최근 1달</option>
											<option value="62">최근 2달</option>
											<option value="93">최근 3달</option>
											<option value="184">최근 6달</option>
											<option value="DT">상세지정</option>
										</select>
									</div>
									<div id='detailDt' style="display:none">
										<span class="f_l">시작일&nbsp;:&nbsp;</span>
										<input type="text" name="startDate" style="width:90px" class="int_s1 f_l">
										<span class="f_l">&nbsp;~&nbsp;</span>
										<span class="f_l">종료일&nbsp;:&nbsp;</span>
										<input type="text" name="endDate" style="width:90px" class="int_s1 f_l">
									</div>
								</td>
							</tr>
							<tr>
								<td class="tit">결재상태</td>
								<td class="inpt">
									<select name="signStsCd" id="signStsCd"
										style="width: 126px" class="sel_st1">
										</select>
								</td>
								<td class="tit">정산서 상태</td>
								<td class="inpt">
									<select name="adjustStsCd" id="adjustStsCd"
										style="width: 126px" class="sel_st1">
										</select>
								</td>
								<td class="tit"></td>
								<td class="inpt">
									<a href="javascript:;" class="btn s1" id="jBtn"><span>정산서작성만 보기</span></a>
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
				<h3><span id="outerTripListCnt"></span> <span>목록</span></h3><span class="cont" ><span id="listCnt"></span></span>
				<div class="btn-area f_r" >
				  	<a class="btn s4" id="excelBtn" onclick="f_DownExcluExcel()" style="display:none"><span>엑셀다운로드</span></a>
				</div>

				<!--리스트-->
				<div class="list_st2" style="min-height:450px;">
					<table id="outerTripList" style="width: 900px;"></table>
					<div id="outerTripListPager"></div>
				</div>
				<!--//리스트-->
			</div>
			<!--//임시저장함 목록-->
		</div>
		<!-- board list layer -->
		<div id="emmisionLayerDiv" style="position: absolute; bottom:0;left:0; display:none;">
			<div id="emmisionLayerBody" class="layer_width250" style="width: 1300px;text-align:left">
				<a id="notiClose" style="" class="btn s1"><span>close</span></a>
				<br>
				<br>
				<br>
				<img src="${contextPath}/common/images/cmas/cmas_nt_2.jpg">
				<br>
			</div>
		</div>
		<!--//CONTENTS-->
		<div class="bottom_bg">
			<span class="left"></span><span class="right"></span>
		</div>
	</div>
	<!--//right-->
	<iframe name="excelDownloader" style="display:none"></iframe>
	<!--//right-->
	<form name="downForm" id="downForm" target=""excelDownloader"" method="post" action="${contextPath}/co/common/file/downloadStream4Excel.xpl">
		<input type="hidden" name="filePath" id="filePath">
		<input type="hidden" name="fileNm" id="fileNm">
		<input type="hidden" name="policy" id="policy" value="excel">
	</form>

</body>
</html>