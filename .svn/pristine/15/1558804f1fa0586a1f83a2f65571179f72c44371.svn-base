<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<%-- Common Library 처리 --%>
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType"/>
	<jsp:param value="해외출장시스템" name="title" />
	<jsp:param value="popup" name="type" />
</jsp:include>

<%-- 파일 업로드 --%>
<jsp:include page="/common/jsp/comp/fileupload/upload4RexComp.jsp">
	<jsp:param value="hiduploaddiv" name="objId" />
	<jsp:param value="f_upCallback" name="callbackFunc" />
</jsp:include>

<%-- 공통 : 세션정보 --%>
<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />

<%-- 공통 : 첨부컴포넌트 --%>
<jsp:include page="/common/jsp/comp/fileupload/uploadComp.jsp" />

<%-- JavaScript Logic 처리 --%>
<ut:script src="${contextPath}/trip/outerTrip/outerTripAdjustReadOnly.js"></ut:script>

<%-- HTTP Request Parameter 처리 --%>
<script type="text/javascript">
	var datas = ${param.datas}; // 파라메터로 인코딩 된 데이터
</script>

<link href="${contextPath}/common/css/base.css" type="text/css"
	rel="stylesheet" />
</head>


<body id="popup_body">

		<!--CONTENTS-->
		<div id="contents">

			<div class="btn-area">
				<a class="btn s1" id="createPdf" style="display:none"><span>PDF증빙생성</span></a>
				<a class="btn s1" id="print"><span>인쇄</span></a>
				<a class="btn s1" id="ghrCmtUpdate" style="display:none"><span>검토의견수정</span></a>
				<a class="btn s1" id="adjustReDraft" style="display:none"><span>재작성</span></a>
				<%--  기능추가. 2019.05.xx G.KIM --%>
				<a class="btn s1" id="docDelBtn" style="display:none"><span>신청서삭제</span></a>
				<a class="btn s1" id="rejectSap" style="display:none"><span>임시전표삭제</span></a>
				<%-- end of 기능추가. 2019.05.xx G.KIM --%>
				<a class="btn s1" id="outerTripClose"><span>닫기</span></a>
			</div>

			<div class="clear"></div>

			<br>

			<!-- 양식명칭 -->
			<div id="viewTitle" class="form_title" align="center">
				<h6>
					해외출장 보고서 및 정산서
				</h6>
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

			<div class="list_st1">

				<table id="docInfo">
						<tr id="refTr" style="display:none">
							<td class="tit" style="width:10%">작성기준일</td>
							<td class="inpt" style="width:90%" colspan="3">
								<span id="refDay"></span>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">정산 구분</td>
							<td class="inpt" style="width:90%" colspan="3">
								<select id="jsGubun">
									<option value="" selected="selected"></option>
									<option value="A">추가</option>
									<option value="R">반납</option>
									<option value="N">일반</option>
								</select>
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
							<td class="tit" style="width:10%">REF NO</td>
							<td class="inpt" style="width:40%">
								<span id="refNo"></span>
							</td>
							<td class="tit" style="width:10%">GITA NO</td>
							<td class="inpt" style="width:40%">
								<span id="gitaNo"></span>
							</td>
						</tr>
						<tr id="tSignId">
							<td class="tit" style="width:10%">결재 문서 번호</td>
							<td class="inpt" style="width:90%" colspan="3">
								<span id="signId"></span>
							</td>
						</tr>
						<tr id="rejContTr" style="display:none">
							<td class="tit" style="width:10%">반려사유</td>
							<td class="inpt" style="width:90%" colspan="3">
								<span id="rejCont"></span>
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
				<table id="docInfoDetail">
						<tr>
							<td class="tit" style="width:10%">출장자</td>
							<td class="inpt" style="width:90%">
								<span id="tripUserInfo"></span>
								 (출장인원 : <span id="tripUserTotal"></span>명)
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">출장국가</td>
							<td class="inpt" style="width:90%">
								<input type="text" id="tripNat">
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">출장기간</td>
							<td class="inpt" style="width:90%">
								최초 : <span id="fTripDate">입력시값</span><br>
								실사용 : <input type="text" id="rTripDateStart"> ~ <input type="text" id="rTripDateEnd"> (기간 : <span id="rTripDate"></span>일) 지불/반납예정일 : <input type="text" id="payDate">
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">요청 사항</td>
							<td class="inpt" style="width:90%"><textarea id="tReq" style="width: 95%; height: 50px;"></textarea></td>
						</tr>
				</table>
				<div class="clear"></div>
				<br>
				<h3><span>출장 보고 내용</span></h3>
				<table id="docRptDetail">
						<tr>
							<td class="tit" style="width:10%">출장목적</td>
							<td class="inpt" style="width:90%"><input id="tPurp" type="text" style="width: 95%"></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">세부일정</td>
							<td class="inpt" style="width:90%">
								<textarea id="tPlanDtl" style="width: 95%; height: 50px;"></textarea></td>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">업무보고내용</td>
							<td class="inpt" style="width:90%">
								<textarea id="tRptDtl" style="width: 95%; height: 50px;"></textarea></td>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">입수자료목록</td>
							<td class="inpt" style="width:90%"><input id="tDataList" type="text" style="width: 95%"></td>
						</tr>
				</table>
				<div class="clear"></div>
				<br>
				<h3><span>출장기간 이용숙박 일수 (이용일수 입력)</span></h3>
				<table>
						<tr>
							<td class="tit" style="width:20%">숙박시설</td>
							<td class="inpt" style="width:13%"><input type="text" size=5 id="s1" value="0">일</td>
							<td class="tit" style="width:20%">해외현장</td>
							<td class="inpt" style="width:13%"><input type="text" size=5 id="s2" value="0">일</td>
							<td class="tit" style="width:20%">법인/지사</td>
							<td class="inpt" style="width:14%"><input type="text" size=5 id="s3" value="0">일</td>
						</tr>
						<tr>
							<td class="tit" style="text-align: left" colspan="6">※ 최초지급기준 숙박 변동시 체재비 재산정</td>
						</tr>
				</table>
				<div class="clear"></div>
				<br>
				<h3><span>SAP 예산집행</span></h3>
				<table>
						<tr>
							<td class="tit" style="width:10%">경비구분</td>
							<td class="inpt" style="width:40%">
								<span id="bdgtType"></span> / 업무구분 : <span id="cGubun"></span>
							</td>
							<td class="tit" style="width:10%">예산번호</td>
							<td class="inpt" style="width:40%">
								<span id="aufnr"></span>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">집행팀</td>
							<td class="inpt" style="width:40%">
								<span id="kostv"></span>
							</td>
							<td class="tit" style="width:10%">작성자</td>
							<td class="inpt" style="width:40%">
								<span id="drafterInfo"></span>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">송금계좌</td>
							<td class="inpt" style="width:40%">
								<span id="accRetInfo"></span>
							</td>
							<td class="tit" style="width:10%">반납방법</td>
							<td class="inpt" style="width:40%">
								<span id="accWay"></span>

								<select id="culkum" style="display:none">
									<option value="" selected="selected"></option>
									<option value="B200">건축사업본부 우리은행 951-000702-01-004</option>
									<option value="B231">건축사업본부 제일은행 110-10-022476</option>
									<option value="B202">국내영업본부 우리은행 951-000702-01-120</option>
									<option value="B203">기술연구원 우리은행 951-000702-01-123</option>
									<option value="B204">법무팀 우리은행 951-000702-01-118</option>
									<option value="B205">HSE팀 우리은행 951-000702-01-117</option>
									<option value="B206">외주구매본부 우리은행 951-000702-01-122</option>
									<option value="B207">인사팀 우리은행 951-000702-01-032</option>
									<option value="B208">전략기획본부 우리은행 1006-501-227525</option>
									<option value="B209">주택사업본부 우리은행 951-000702-01-003</option>
									<option value="B23A">주택사업본부 제일은행 110-10-022528</option>
									<option value="B20B">총무팀 우리은행 951-000702-01-119</option>
									<option value="B20C">토목사업본부 우리은행 951-000702-01-005</option>
									<option value="B23D">토목사업본부 제일은행 110-10-022465</option>
									<option value="B20E">플랜트사업본부 우리은행 951-000702-01-006</option>
									<option value="B23F">플랜트사업본부 제일은행 110-10-022487</option>
									<option value="B20G">해외영업본부 우리은행 951-000702-01-121</option>
									<option value="B20H">개발사업본부 우리은행 1005-501-777090</option>
									<option value="B20I">재무금융본부 우리은행 1006-501-227525</option>
								</select>
							</td>
						</tr>
						<tr id="retCost" style="display:none">
							<td class="tit" style="width:10%">반납자</td>
							<td class="inpt" style="width:40%">
								<span id="payRutUser"></span>
							</td>
							<td class="tit" style="width:10%">반납일</td>
							<td class="inpt" style="width:40%">
								<span id="payRutDate"></span>
							</td>
						</tr>
				</table>
				<div class="clear"></div>
				<br>
				<h3><span>항공료</span></h3>
				<table>
						<tr>
							<td class="tit">예산</td>
							<td class="inpt"><span id="af1">0</span> 원</td>
							<td class="tit">청구</td>
							<td class="inpt"><span id="af2">0</span> 원</td>
							<td class="tit">차액</td>
							<td class="inpt"><span id="af3">0</span> 원</td>
						</tr>
						<tr>
							<td class="tit" style="text-align: left" colspan="6">※ 협조의뢰시 자동계산됩니다.</td>
						</tr>
				</table>
				<div class="clear"></div>
				<br>
				<h3><span>기타 경비</span></h3>
				<table>
						<tr>
							<td class="tit" style="text-align: center; width:25%;">항목</td>
							<td class="tit" style="text-align: center; width:25%;">최초지급분 (USD/\)</td>
							<td class="tit" style="text-align: center; width:25%;">실사용분 (USD/\)</td>
							<td class="tit" style="text-align: center; width:25%;">차액 (USD)</td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:25%;">현지항공료</td>
							<td class="inpt" style="text-align: center; width:25%;">$ <span id="loAirFare1">0</span> / \ <span id="loAirFareEx1">0</span></td>
							<td class="inpt" style="text-align: center; width:25%;">$ <input type="text" id="loAirFare2" style="width:80px"> / \ <span id="loAirFareEx2">0</span></td>
							<td class="inpt" style="text-align: center; width:25%;">$ <span id="loAirFare3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:25%;">현지교통비</td>
							<td class="inpt" style="text-align: center; width:25%;">$ <span id="loTranFare1">0</span> / \ <span id="loTranFareEx1">0</span></td>
							<td class="inpt" style="text-align: center; width:25%;">$ <input type="text" id="loTranFare2" style="width:80px"> / \ <span id="loTranFareEx2">0</span></td>
							<td class="inpt" style="text-align: center; width:25%;">$ <span id="loTranFare3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:25%;">VISA FEE</td>
							<td class="inpt" style="text-align: center; width:25%;">$ <span id="visaFeeFare1">0</span> / \ <span id="visaFeeFareEx1">0</span></td>
							<td class="inpt" style="text-align: center; width:25%;">$ <input type="text" id="visaFeeFare2" style="width:80px"> / \ <span id="visaFeeFareEx2">0</span></td>
							<td class="inpt" style="text-align: center; width:25%;">$ <span id="visaFeeFare3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:25%;">Over Charge</td>
							<td class="inpt" style="text-align: center; width:25%;">$ <span id="ovCharFare1">0</span> / \ <span id="ovCharFareEx1">0</span></td>
							<td class="inpt" style="text-align: center; width:25%;">$ <input type="text" id="ovCharFare2" style="width:80px"> / \ <span id="ovCharFareEx2">0</span></td>
							<td class="inpt" style="text-align: center; width:25%;">$ <span id="ovCharFare3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:25%;">복리후생</td>
							<td class="inpt" style="text-align: center; width:25%;">$ <span id="vocLeeFare1">0</span> / \ <span id="vocLeeFareEx1">0</span></td>
							<td class="inpt" style="text-align: center; width:25%;">$ <input type="text" id="vocLeeFare2" style="width:80px"> / \ <span id="vocLeeFareEx2">0</span></td>
							<td class="inpt" style="text-align: center; width:25%;">$ <span id="vocLeeFare3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:25%;">접대비</td>
							<td class="inpt" style="text-align: center; width:25%;">$ <span id="hostFare1">0</span> / \ <span id="hostFareEx1">0</span></td>
							<td class="inpt" style="text-align: center; width:25%;">$ <input type="text" id="hostFare2" style="width:80px"> / \ <span id="hostFareEx2">0</span></td>
							<td class="inpt" style="text-align: center; width:25%;">$ <span id="hostFare3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:25%;">계</td>
							<td class="inpt" style="text-align: center; width:25%;">$ <span id="etcTotal1">0</span> / \ <span id="etcTotalEx1">0</span></td>
							<td class="inpt" style="text-align: center; width:25%;">$ <span id="etcTotal2">0</span> / \ <span id="etcTotalEx2">0</span></td>
							<td class="inpt" style="text-align: center; width:25%;">$ <span id="etcTotal3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:25%;">집행세부내역</td>
							<td class="inpt" colspan="3" style="text-align: center; width:75%;"><textarea id="execDtl" style="width: 95%; height: 50px;"></textarea>
						</tr>
				</table>
				<div class="clear"></div>
				※ 기타경비 정산은 SAP 가불정산화면을 이용하여 별도 처리 하시기 바랍니다.
				<div class="clear"></div>
				<br>
				<h3><span>체재비</span></h3>
				<table>
						<tr>
							<td class="tit" style="text-align: center; width:28%;" colspan="2" rowspan="2">구분</td>
							<td class="tit" style="text-align: center; width:12%;" rowspan="2">금액</td>
							<td class="tit" style="text-align: center; width:24%;" colspan="2">최초지급분</td>
							<td class="tit" style="text-align: center; width:24%;" colspan="2">실사용분</td>
							<td class="tit" style="text-align: center; width:12%;">차액</td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:12%;">일수</td>
							<td class="tit" style="text-align: center; width:12%;">계</td>
							<td class="tit" style="text-align: center; width:12%;">일수</td>
							<td class="tit" style="text-align: center; width:12%;">계</td>
							<td class="tit" style="text-align: center; width:12%;">계</td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:12%;" rowspan="4">일반</td>
							<td class="tit" style="text-align: center; width:16%;">숙박비</td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="usNightRef"></span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="usNightDay">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="usNightDayAmt">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><input id="usNightDay2" type="text" style="width:50px;"></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="usNightDayAmt2">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="usNight3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:16%;">일당비</td>
							<td class="inpt" style="text-align: center; width:12%;"><input id="usDayRef" type="text" style="width:50px;"></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="usDayDay">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="usDayDayAmt">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><input id="usDayDay2" type="text" style="width:50px;"></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="usDayDayAmt2">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="usDay3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:16%;">연수비</td>
							<td class="inpt" style="text-align: center; width:12%;"><input id="eduRef" type="text" style="width:50px;"></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="eduDay">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="eduDayAmt">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><input id="eduDay2" type="text" style="width:50px;"></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="eduDayAmt2">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="edu3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:16%;">현장숙소</td>
							<td class="inpt" style="text-align: center; width:12%;"><input id="spotRef" type="text" style="width:50px;"></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="spotDay">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="spotDayAmt">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><input id="spotDay2" type="text" style="width:50px;"></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="spotDayAmt2">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="spot3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:12%;" rowspan="2">유럽</td>
							<td class="tit" style="text-align: center; width:16%;">숙박비</td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="euNightRef"></span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="euNightDay">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="euNightDayAmt">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><input id="euNightDay2" type="text" style="width:50px;"></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="euNightDayAmt2">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="euNight3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:16%;">일당비</td>
							<td class="inpt" style="text-align: center; width:12%;"><input id="euDayRef" type="text" style="width:50px;"></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="euDayDay">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="euDayDayAmt">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><input id="euDayDay2" type="text" style="width:50px;"></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="euDayDayAmt2">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="euDay3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:12%;" rowspan="2">영국</td>
							<td class="tit" style="text-align: center; width:16%;">숙박비</td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="enNightRef"></span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="enNightDay">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="enNightDayAmt">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><input id="enNightDay2" type="text" style="width:50px;"></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="enNightDayAmt2">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="enNight3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:16%;">일당비</td>
							<td class="inpt" style="text-align: center; width:12%;"><input id="enDayRef" type="text" style="width:50px;"></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="enDayDay">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="enDayDayAmt">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><input id="enDayDay2" type="text" style="width:50px;"></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="enDayDayAmt2">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="enDay3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:12%;" rowspan="2">일본</td>
							<td class="tit" style="text-align: center; width:16%;">숙박비</td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="jaNightRef"></span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="jaNightDay">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="jaNightDayAmt">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><input id="jaNightDay2" type="text" style="width:50px;"></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="jaNightDayAmt2">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="jaNight3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:16%;">일당비</td>
							<td class="inpt" style="text-align: center; width:12%;"><input id="jaDayRef" type="text" style="width:50px;"></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="jaDayDay">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="jaDayDayAmt">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><input id="jaDayDay2" type="text" style="width:50px;"></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="jaDayDayAmt2">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;"><span id="jaDay3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:12%;" rowspan="4">소계</td>
							<td class="tit" style="text-align: center; width:28%;" colspan="2">일반＄: <span id="usdRef"></span></td>
							<td class="inpt" style="text-align: center; width:24%;" colspan="2"><span id="usdTotal">0</span></td>
							<td class="inpt" style="text-align: center; width:24%;" colspan="2"><span id="usdTotal2">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;" colspan="2"><span id="usdTotal3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:28%;" colspan="2">유럽 €: <span id="euroRef"></span></td>
							<td class="inpt" style="text-align: center; width:24%;" colspan="2"><span id="euroTotal">0</span></td>
							<td class="inpt" style="text-align: center; width:24%;" colspan="2"><span id="euroTotal2">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;" colspan="2"><span id="euroTotal3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:28%;" colspan="2">영국￡: <span id="gbpRef"></span></td>
							<td class="inpt" style="text-align: center; width:24%;" colspan="2"><span id="gbpTotal">0</span></td>
							<td class="inpt" style="text-align: center; width:24%;" colspan="2"><span id="gbpTotal2">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;" colspan="2"><span id="gbpTotal3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:28%;" colspan="2">일본￥: <span id="jaRef"></span></td>
							<td class="inpt" style="text-align: center; width:24%;" colspan="2"><span id="jaTotal">0</span></td>
							<td class="inpt" style="text-align: center; width:24%;" colspan="2"><span id="jaTotal2">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;" colspan="2"><span id="jaTotal3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:12%;" rowspan="2">계</td>
							<td class="tit" style="text-align: center; width:28%;" colspan="2">개인지급합계(\) = 체재비소계 X 인원</td>
							<td class="inpt" style="text-align: center; width:24%;" colspan="2"><span id="cTotalAmt">0</span></td>
							<td class="inpt" style="text-align: center; width:24%;" colspan="2"><span id="cTotalAmt2">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;" colspan="2"><span id="cTotalAmt3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:28%;" colspan="2">총계(\) = 합계 + 항공료</td>
							<td class="inpt" style="text-align: center; width:24%;" colspan="2"><span id="TotalAmt">0</span></td>
							<td class="inpt" style="text-align: center; width:24%;" colspan="2"><span id="TotalAmt2">0</span></td>
							<td class="inpt" style="text-align: center; width:12%;" colspan="2"><span id="TotalAmt3">0</span></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:12%;">비고</td>
							<td class="inpt" style="text-align: center; width:88%;" colspan="7"><textarea id="rem" style="width: 95%; height: 50px;"></textarea></td>
						</tr>
						<tr>
							<td class="tit" style="text-align: center; width:12%;">협조내역</td>
							<td class="inpt" style="text-align: center; width:88%;" colspan="7"><textarea id="assDtl" style="width: 95%; height: 50px;"></textarea></td>
						</tr>
						<tr id="ghrTr" style="display:none">
							<td class="tit" style="text-align: center; width:12%;">GHR <br> 협조팀 의견</td>
							<td class="inpt" style="text-align: center; width:88%;" colspan="7">
								<textarea id="ghrComment" style="width:95%; height:80px" readonly="readonly"></textarea>
							</td>
						</tr>
				</table>
				<div class="clear"></div>
				<br>
				<!--첨부파일/문서첨부-->
				<div class="box">
					<h3><span>첨부 파일</span></h3>
					<div id="atchFile">
						<div id="table" name="fileComponent" class="upload"></div>
					</div>
				</div>
		</div>
		<!--//CONTENTS-->
		<div class="bottom_bg">
			<span class="left"></span><span class="right"></span>
		</div>

</body>
</html>