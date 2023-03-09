<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType" />
	<jsp:param value="결재시스템" name="title" />
	<jsp:param value="print" name="type" />
</jsp:include>

<!-- 공통 : 세션정보 -->
<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />

<!-- JavaScript Logic 처리 -->
<ut:script src="${contextPath}/trip/outerTrip/draftPrint.js"></ut:script>

<!-- HTTP Request Parameter 처리 -->
<script type="text/javascript">
	var datas = ${param.datas}; // 파라메터로 인코딩 된 데이터
</script>

<link href="${contextPath}/common/css/print.css" type="text/css"
	rel="stylesheet" />
</head>


<body id="popup_body">
	<!--
	Class Name 	: innerTripList.jsp
	Description : 작성함 - 양식관리에 등록된 결재양식 목록
 -->

	<!--CONTENTS-->
	<div id="contents">

		<div id="btnDiv" class="btn-area">
			<a class="btn s1" id="print"><span>인쇄</span></a> <a class="btn s1"
				id="outerTripClose"><span>닫기</span></a>
		</div>

		<div class="clear"></div>

		<br>

		<!-- 양식명칭 -->
		<div id="viewTitle" class="form_title" align="center">
			<h6>해외출장 신청서</h6>
		</div>
		<!-- //양식명칭 -->

		<br> <br>

		<!--결재선-->
		<div id="appr_line2">
			<table id="right_signln" border="0" cellspacing="0" cellpadding="0">
				<tbody>
					<tr class="signln_pos">
						<th>&nbsp;</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
					</tr>
					<tr class="signln_nm">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr class="signln_dt">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!--//결재선-->

		<div class="clear"></div>

		<br><br>
		<!--문서정보-->
		<div id="infoDiv" class="box">
			<div class="list_st1">
				<br>

				<table id="docInfo" width="100%" border="0" cellspacing="0"
					cellpadding="0">
						<tr id="refTr" style="display:none">
							<td class="tit" style="width:10%">작성기준일</td>
							<td class="inpt" style="width:90%" colspan="3">
								<span id="refDay"></span>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">문서 번호</td>
							<td class="inpt" style="width:40%">
								<span id="docNo"></span>
							</td>
							<td class="tit" style="width:10%">증빙 일자</td>
							<td class="inpt" style="width:40%">
								<span id="oDate"></span>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">Ref No</td>
							<td class="inpt" style="width:40%">
								<span id="refNo"></span>
							</td>
							<td class="tit" style="width:10%">Gita No</td>
							<td class="inpt" style="width:40%">
								<span id="gitaNo"></span>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">결재 문서 번호</td>
							<td class="inpt" style="width:90%" colspan="3">
								<span id="signId"></span>
							</td>
						</tr>
						<tr id="tOrdNoTr" style="display:none;">
							<td class="tit" style="width:10%">전표 번호</td>
							<td class="inpt" style="width:90%" colspan="3">
								<span id="tOrdNo"></span>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">작성자</td>
							<td class="inpt" style="width:40%" id="drafter"></td>
							<td class="tit" style="width:10%">소속팀</td>
							<td class="inpt" style="width:40%" id="drafterOrgNm"></td>
						</tr>

						<!-- IF 협의자 존재 -->
						<tr id="signAssistor">
							<td class="tit">협의자</td>
							<td class="inpt" id="signAssistorVal" colspan="3"></td>
						</tr>
						<!-- //IF 협의자 존재 -->
				</table>
				<table id="docInfoDetail" width="100%" border="0" cellspacing="0"
					cellpadding="0">
						<tr>
							<td class="tit" style="width:10%">출장자</td>
							<td class="inpt" style="width:90%">
								<span id="tripUserInfo"></span>
								<table width="100%" border="0" cellspacing="0"
					cellpadding="0">
									<tr>
										<td class="tit" style="width:25%;">출장자 영문이름(티켓과 동일)</td>
										<td class="inpt" style="width:25%;"><span id="tripUserEnm"></span></td>
										<td class="tit" style="width:10%;">좌석등급</td>
										<td class="inpt" style="width:10%;">
											<span id="tripUserGradeSpan"></span>
										</td>
										<td class="tit" style="width:10%;">항공료</td>
										<td class="inpt" style="width:20%;">
											<span id="tripUserAirAmount"></span> 원
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">동행인</td>
							<td class="inpt" style="width:90%">
								<table id="tripUserDetail" width="100%" border="0" cellspacing="0"
					cellpadding="0">
										<tr>
											<td class="tit" style="width:40%; text-align:center">동행인 영문이름(티켓과 동일)</td>
											<td class="tit" style="width:30%; text-align:center">좌석등급</td>
											<td class="tit" style="width:30%; text-align:center">항공료</td>
										</tr>
										<!-- <tr>
											<td class="inpt" style="width:40%; text-align:center"><input type="text" name="tUserKnm"></td>
											<td class="inpt" style="width:30%; text-align:center">
												<select name="tGrade">
													<option value="F" selected="selected">First</option>
													<option value="B">Business</option>
													<option value="E">Economy</option>
												</select>
											</td>
											<td class="inpt" style="width:30%; text-align:center">
												<input type="text" name="tAirAmount" value="0">원
											</td>
										</tr> -->
								</table>
								<!-- <span class="pull-right">
									<a href="javascript:;" class="button-light" id="tripUserAddBtn">추가</a><a href="javascript:;" class="button-light" id="tripUserDelBtn">삭제</a>
								</span> -->
								총 인원 : <span id="totalUser"></span>명
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">출장기간</td>
							<td class="inpt" style="width:90%">
								<!-- 00/00/00 ~ 00/00/00 (기간 : 00일) -->
								<span id="tripDate">※ 아래 체류기간 입력시 자동 산정됩니다.</span>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">출장목적</td>
							<td class="inpt" style="width:90%"><span id="tPurp"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">요청 사항</td>
							<td class="inpt" style="width:90%"><span id="tReq"></span></td>
						</tr>
				</table>
				<div class="clear"></div>
				<br>
				<span>▶ 여권비자정보</span>
				<table width="100%" border="0" cellspacing="0"
					cellpadding="0">
						<tr>
							<td class="tit" style="width:10%">여권정보</td>
							<td class="inpt" style="width:90%">
								<table width="100%" border="0" cellspacing="0"
					cellpadding="0">
										<tr>
											<td class="tit" style="width:50%; text-align:center">여권발급일</td>
											<td class="tit" style="width:50%; text-align:center">여권만료일</td>
										</tr>
										<tr>
											<td class="inpt" style="width:50%; text-align:center"><input type="text" id="fPassp" style="border:0" readonly="readonly"></td>
											<td class="inpt" style="width:50%; text-align:center"><input type="text" id="tPassp" style="border:0" readonly="readonly"></td>
										</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">비자정보</td>
							<td class="inpt" style="width:90%">
								<!-- <a href="javascript:;" class="button-light" id="visaInfo">국가별 비자안내</a>  -->
								<table id="visaInfoDetail" width="100%" border="0" cellspacing="0"
					cellpadding="0">
										<tr>
											<td class="tit" style="width:20%; text-align:center">국가</td>
											<td class="tit" style="width:20%; text-align:center">비자유무</td>
											<td class="tit" style="width:60%; text-align:center">비자정보</td>
										</tr>
										<!-- <tr>
											<td class="inpt" style="width:30%; text-align:center">
												<input type="text" name="vNat">
											</td>
											<td class="inpt" style="width:30%; text-align:center">
												<input type="text" name="vVisaYn">
											</td>
											<td class="inpt" style="width:40%; text-align:center">
												발급일 : <input type="text" name="vStartDate"> / 만료일 : <input type="text" name="vEndDate">
											</td>
										</tr> -->
								</table>
								<!-- <span class="pull-right">
									<a href="javascript:;" class="button-light" id="visaAddBtn">추가</a><a href="javascript:;" class="button-light" id="visaDelBtn">삭제</a>
								</span> -->
								<span id="totalNat">총 출장국가 : <span id="totalVisaInfo"></span>개국</span>
							</td>
						</tr>
				</table>
				<div class="clear"></div>
				<br>
				<span>▶ 방문지</span>
				<table id="cityDetail" width="100%" border="0" cellspacing="0"
					cellpadding="0">
						<tr>
							<td class="tit" style="width:30%; text-align:center">국가 / 도시명</td>
							<td class="tit" style="width:20%; text-align:center">체류기간</td>
							<td class="tit" style="width:50%; text-align:center">주요업무내용</td>
						</tr>
						<!-- <tr>
							<td class="inpt" style="width:30%; text-align:center">
								<input type="text" name="natNm" style="width:120px"> / <input type="text" name="cityNm">
							</td>
							<td class="inpt" style="width:20%; text-align:center">
								<input type="text" name="stayStYmd">부터 <input type="text" name="stayEdYmd">까지
							</td>
							<td class="inpt" style="width:40%; text-align:center"><textarea style="width:95%" name="dutyCont"></textarea></td>
						</tr> -->
				</table>
				<!-- <span class="pull-right">
					<a href="javascript:;" class="button-light" id="cityAddBtn">추가</a><a href="javascript:;" class="button-light" id="cityDelBtn">삭제</a>
				</span> -->
				<span id="totalNat">총 방문지 : <span id="totalCityInfo"></span>개국</span>
				<div class="clear"></div>
				<br>
				<span>▶ SAP 예산집행</span>
				<table width="100%" border="0" cellspacing="0"
					cellpadding="0">
						<tr>
							<td class="tit" style="width:10%">경비구분</td>
							<td class="inpt" style="width:40%">
								<span id="bdgtType"></span>
							</td>
							<td class="tit" style="width:10%">예산번호</td>
							<td class="inpt" style="width:40%" id="Aufnr"></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">업무구분</td>
							<td class="inpt" style="width:90%" colspan="3">
								업무구분 :
								<select id="cGubun" style="border:0" disabled="disabled">
										<option value="" selected="selected"></option>
										<option value="c01">견적/Site</option>
										<option value="c02">공사</option>
										<option value="c03">기술연구</option>
										<option value="c04">기타</option>
										<option value="c05">사후관리/Claim</option>
										<option value="c06">설계/엔지니어링</option>
										<option value="c07">수주/영업</option>
										<option value="c08">연수</option>
										<option value="c09">일반관리</option>
										<option value="c10">전략/기획</option>
										<option value="c11">홍보</option>
										<option value="c12">F/S</option>
								</select>
								<!-- <span id="VTCodeSpan" style="display:none;">경비이체코드 : <input type="text"></span> -->
								<span id="VTCodeSpan" style="display:none;"> / 경비이체코드 : <input type="text" id="vTcode" style="border:0" readonly="readonly"></span>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">집행팀</td>
							<td class="inpt" style="width:90%" colspan="3"><span id="bdgtTeam"></span></td>
							<!-- <td class="tit" style="width:10%">작성자</td>
							<td class="inpt" style="width:40%"><span id="drafterSap"></span></td> -->
						</tr>
				</table>
				<div id="dSignDiv" style="color:red;font-weight:bold; display:none;">※ 예산담당팀과 사전협의 요망 (사진협의 미진행시 결재/자금집행 지연)</div>
				<div class="clear"></div>
				<br>
				<span>▶ 항공료 / 기타경비</span>
				<table width="100%" border="0" cellspacing="0"
					cellpadding="0">
						<tr>
							<td class="tit" style="width:10%" colspan="2">항공료</td>
							<td class="inpt" style="width:90%" colspan="2">
								<span id="airTotalAmount"></span>원
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%" colspan="2">특이사항</td>
							<td class="inpt" style="width:90%" colspan="2">
								<span id="pecul"></span>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:3%; text-align:center" rowspan="8">기타<br>경비</td>
							<td class="tit" style="width:10%; text-align:center">구분</td>
							<td class="tit" style="width:30%; text-align:center">금액(USD/&#65510;)</td>
							<td class="tit" style="width:60%; text-align:center">집행세부내역</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%; text-align:center">현지항공료</td>
							<td class="inpt" style="width:30%; text-align:center">$ <span id="loAirFare"></span> / &#65510;    <span id="loAirFareEx">0</span></td>
							<td class="inpt" style="width:60%; text-align:center"><span id="loAirFareDtl"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%; text-align:center">현지교통비</td>
							<td class="inpt" style="width:30%; text-align:center">$ <span id="loTranFare"></span> / &#65510;    <span id="loTranFareEx">0</span></td>
							<td class="inpt" style="width:60%; text-align:center"><span id="loTranFareDtl"></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%; text-align:center">VISA FEE</td>
							<td class="inpt" style="width:30%; text-align:center">$ <span id="visaFeeFare"></span> / &#65510;    <span id="visaFeeFareEx">0</span></td>
							<td class="inpt" style="width:60%; text-align:center"><span id="visaFeeFareDtl"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%; text-align:center">Over Charge</td>
							<td class="inpt" style="width:30%; text-align:center">$ <span id="ovCharFare"></span> / &#65510;    <span id="ovCharFareEx">0</span></td>
							<td class="inpt" style="width:60%; text-align:center"><span id="ovCharFareDtl"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%; text-align:center">복리후생</td>
							<td class="inpt" style="width:30%; text-align:center">$ <span id="vocLeeFare"></span> / &#65510;    <span id="vocLeeFareEx">0</span></td>
							<td class="inpt" style="width:60%; text-align:center"><span id="vocLeeFareDtl"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%; text-align:center">접대비</td>
							<td class="inpt" style="width:30%; text-align:center">$ <span id="hostFare"></span> / &#65510;    <span id="hostFareEx">0</span></td>
							<td class="inpt" style="width:60%; text-align:center"><span id="hostFareDtl"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%; text-align:center">계</td>
							<td class="inpt" style="width:30%; text-align:center">$ <span id="etcTotalUSD">0</span> &#65510; <span id="etcTotalEx">0</span></td>
							<td class="inpt" style="width:60%; text-align:center"> (기타비용정산예정일 : <span id="etcPayDate"></span>)</td>
						</tr>
				</table>
				<div class="clear"></div>
				<span class="pull-right">
					<!-- <a href="javascript:;" class="button-light" id="inputDefault">기본값입력</a> -->
					<!-- <a href="javascript:;" class="button-light" id="inputAmt">체재비 입력</a>  -->
				</span>
				<br>
				<span>▶ 체재비</span>
				<table width="100%" border="0" cellspacing="0"
					cellpadding="0">
						<tr>
							<td class="tit" style="width:10%;">숙소구분</td>
							<td class="inpt" style="width:90%;">
								<span id="innType"></span>
							</td>
						</tr>
						<tr id="usNightTr" style="display:none">
							<td class="tit" style="width:10%;">숙박비($)</td>
							<td class="inpt" style="width:90%;"><span id="usNight"></span></td>
						</tr>
						<tr id="usDayTr" style="display:none">
							<td class="tit" style="width:10%;">일당비($)</td>
							<td class="inpt" style="width:90%;"><span id="usDay"></span></td>
						</tr>
						<tr id="eduTr" style="display:none">
							<td class="tit" style="width:10%;">연수비($)</td>
							<td class="inpt" style="width:90%;"><span id="edu"></span></td>
						</tr>
						<tr id="spotTr" style="display:none">
							<td class="tit" style="width:10%;">현장숙소($)</td>
							<td class="inpt" style="width:90%;"><span id="spot"></span></td>
						</tr>
						<tr id="euNightTr" style="display:none">
							<td class="tit" style="width:10%;">유럽숙박비(€)</td>
							<td class="inpt" style="width:90%;"><span id="euNight"></span></td>
						</tr>
						<tr id="euDayTr" style="display:none">
							<td class="tit" style="width:10%;">유럽일당비(€)</td>
							<td class="inpt" style="width:90%;"><span id="euDay"></span></td>
						</tr>
						<tr id="enNightTr" style="display:none">
							<td class="tit" style="width:10%;">영국숙박비(￡)</td>
							<td class="inpt" style="width:90%;"><span id="enNight"></span></td>
						</tr>
						<tr id="enDayTr" style="display:none">
							<td class="tit" style="width:10%;">영국일당비(￡)</td>
							<td class="inpt" style="width:90%;"><span id="enDay"></span></td>
						</tr>
						<tr id="jaNightTr" style="display:none">
							<td class="tit" style="width:10%;">일본숙박비(￥)</td>
							<td class="inpt" style="width:90%;"><span id="jaNight"></span></td>
						</tr>
						<tr id="jaDayTr" style="display:none">
							<td class="tit" style="width:10%;">일본일당비(￥)</td>
							<td class="inpt" style="width:90%;"><span id="jaDay"></span></td>
						</tr>
				</table>
				<table width="100%" border="0" cellspacing="0"
					cellpadding="0">
						<tr>
							<td class="tit" style="width:10%">체재비합계</td>
							<td class="inpt" style="width:22.5%">USD <span id="usdTotal">0</span> (<span id="usdRef"></span> / &#65510; <span id="usdTotalKr">0</span>)</td>
							<td class="inpt" style="width:22.5%">EURO <span id="euroTotal">0</span> (<span id="euroRef"></span> / &#65510; <span id="euroTotalKr">0</span>)</td>
							<td class="inpt" style="width:22.5%">GBP <span id="gbpTotal">0</span> (<span id="gbpRef"></span> / &#65510; <span id="gbpTotalKr">0</span>)</td>
							<td class="inpt" style="width:22.5%">￥  <span id="jaTotal">0</span> (<span id="jaRef"></span> / &#65510; <span id="jaTotalKr">0</span>)</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">개인지급합계</td>
							<td class="inpt" style="width:90%" colspan="4">
								<span id="cTotalAmt">0</span> 체재비합계 X 인원 + 기타경비<br>
								[송금계좌] <span id="retAccount"></span> 지불예정일 : <span id="payMDate"></span>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">총계</td>
							<td class="inpt" style="width:90%" colspan="4">
								<span id="totalAmount">0</span> (개인지급 합계 + 항공료)
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">비고</td>
							<td class="inpt" style="width:90%" colspan="4">
								<span id="rem"></span>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">협조내역</td>
							<td class="inpt" style="width:90%" readonly="readonly" colspan="4">
								<span id="assistComment"></span>
							</td>
						</tr>
				</table>

				<div class="clear"></div>

			</div>


		</div>
	</div>


	<!--//CONTENTS-->
	<div class="bottom_bg">
		<span class="left"></span><span class="right"></span>
	</div>
</body>
</html>