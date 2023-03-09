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
<ut:script src="${contextPath}/trip/outerTrip/adjustPrint.js"></ut:script>

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
			<h6>해외출장 보고서 및 정산서</h6>
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
				<table id="docInfoDetail" width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td class="tit" style="width: 10%">출장자</td>
						<td class="inpt" style="width: 90%"><span id="tripUserInfo"></span>
							(출장인원 : <span id="tripUserTotal"></span>명)</td>
					</tr>
					<tr>
						<td class="tit" style="width: 10%">출장국가</td>
						<td class="inpt" style="width: 90%"><span id="tripNat"></span>
					</tr>
					<tr>
						<td class="tit" style="width: 10%">출장기간</td>
						<td class="inpt" style="width: 90%">최초 : <span id="fTripDate"></span><br>
							실사용 : <span id="rTripDateStEd"></span> (기간 : <span id="rTripDate"></span>일)
							지불/반납예정일 : <span id="payDate"></span>
						</td>
					</tr>
					<tr>
						<td class="tit" style="width: 10%">요청 사항</td>
						<td class="inpt" style="width: 90%"><span id="tReq"></span></td>
					</tr>
				</table>
				<div class="clear"></div>
				<br>
				<span>▶ 출장 보고 내용</span>
				<table id="docRptDetail" width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td class="tit" style="width: 10%">출장목적</td>
						<td class="inpt" style="width: 90%">
							<span id="tPurp"></span>
						</td>
					</tr>
					<tr>
						<td class="tit" style="width: 10%">세부일정</td>
						<td class="inpt" style="width: 90%">
							<span id="tPlanDtl"></span>
						</td>
					</tr>
					<tr>
						<td class="tit" style="width: 10%">업무보고내용</td>
						<td class="inpt" style="width: 90%">
							<span id="tRptDtl"></span>
						</td>
					</tr>
					<tr>
						<td class="tit" style="width: 10%">입수자료목록</td>
						<td class="inpt" style="width: 90%">
							<span id="tDataList"></span>
						</td>
					</tr>
				</table>
				<div class="clear"></div>
				<br>
				<span>▶ 출장기간 이용숙박 일수</span>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="tit" style="width:20%">숙박시설</td>
						<td class="inpt" style="width:13%"><span id="s1"></span></td>
						<td class="tit" style="width:20%">해외현장</td>
						<td class="inpt" style="width:13%"><span id="s2"></span></td>
						<td class="tit" style="width:20%">법인/지사</td>
						<td class="inpt" style="width:14%"><span id="s3"></span></td>
					</tr>
					<!-- <tr>
						<td class="tit" style="text-align: left" colspan="6">※ 최초지급기준
							숙박 변동시 체재비 재산정</td>
					</tr> -->
				</table>
				<div class="clear"></div>
				<br>
				<span>▶ 출장 경비</span>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="tit" style="text-align: center; width: 31%;" colspan="2">구분</td>
						<td class="tit" style="text-align: center; width: 23%;">최초</td>
						<td class="tit" style="text-align: center; width: 23%;">실사용</td>
						<td class="tit" style="text-align: center; width: 23%;">차액</td>
					</tr>
					<tr>
						<td class="tit" style="text-align: center; width: 25%;" colspan="2">항공료</td>
						<td class="inpt" style="text-align: center; width: 23%;">&#65510; <span id="af1"></span></td>
						<td class="inpt" style="text-align: center; width: 23%;">&#65510; <span id="af2"></span></td>
						<td class="inpt" style="text-align: center; width: 23%;">&#65510; <span id="af3"></span></td>
					</tr>
					<tr>
						<td class="tit" style="text-align: center; width: 25%;" colspan="2">체제비</td>
						<td class="inpt" style="text-align: center; width: 23%;">&#65510; <span id="cTotalAmt"></span></td>
						<td class="inpt" style="text-align: center; width: 23%;">&#65510; <span id="cTotalAmt2"></span></td>
						<td class="inpt" style="text-align: center; width: 23%;">&#65510; <span id="cTotalAmt3"></span></td>
					</tr>
					<tr>
						<td class="tit" style="text-align: center; width:6%;" rowspan="8">기<br>타<br>경<br>비</td>
						<td class="tit" style="text-align: center; width: 25%;">현지항공료</td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span
							id="loAirFare1">0</span> / &#65510; <span id="loAirFareEx1">0</span></td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span id="loAirFare2">0</span> / &#65510; <span
							id="loAirFareEx2">0</span></td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span
							id="loAirFare3">0</span></td>
					</tr>
					<tr>
						<td class="tit" style="text-align: center; width: 25%;">현지교통비</td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span
							id="loTranFare1">0</span> / &#65510; <span id="loTranFareEx1">0</span></td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span id="loTranFare2">0</span> / &#65510; <span
							id="loTranFareEx2">0</span></td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span
							id="loTranFare3">0</span></td>
					</tr>
					<tr>
						<td class="tit" style="text-align: center; width: 25%;">VISA
							FEE</td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span
							id="visaFeeFare1">0</span> / &#65510; <span id="visaFeeFareEx1">0</span></td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span id="visaFeeFare2">0</span> / &#65510; <span
							id="visaFeeFareEx2">0</span></td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span
							id="visaFeeFare3">0</span></td>
					</tr>
					<tr>
						<td class="tit" style="text-align: center; width: 25%;">Over
							Charge</td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span
							id="ovCharFare1">0</span> / &#65510; <span id="ovCharFareEx1">0</span></td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span id="ovCharFare2">0</span> / &#65510; <span
							id="ovCharFareEx2">0</span></td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span
							id="ovCharFare3">0</span></td>
					</tr>
					<tr>
						<td class="tit" style="text-align: center; width: 25%;">복리후생</td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span
							id="vocLeeFare1">0</span> / &#65510; <span id="vocLeeFareEx1">0</span></td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span id="vocLeeFare2">0</span> / &#65510; <span
							id="vocLeeFareEx2">0</span></td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span
							id="vocLeeFare3">0</span></td>
					</tr>
					<tr>
						<td class="tit" style="text-align: center; width: 25%;">접대비</td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span
							id="hostFare1">0</span> / &#65510; <span id="hostFareEx1">0</span></td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span id="hostFare2">0</span> / &#65510; <span
							id="hostFareEx2">0</span></td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span
							id="hostFare3">0</span></td>
					</tr>
					<tr>
						<td class="tit" style="text-align: center; width: 25%;">계</td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span
							id="etcTotal1">0</span> / &#65510; <span id="etcTotalEx1">0</span></td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span
							id="etcTotal2">0</span> / &#65510; <span id="etcTotalEx2">0</span></td>
						<td class="inpt" style="text-align: center; width: 23%;">$ <span
							id="etcTotal3">0</span></td>
					</tr>
					<tr>
						<td class="tit" style="text-align: center; width: 25%;">집행세부내역</td>
						<td class="inpt" colspan="3"
							style="width: 59%;">
							<span id="execDtl"></span></td>
					</tr>
					<tr>
						<td class="tit" style="text-align: center; width: 25%;" colspan="2">비고</td>
						<td class="inpt" colspan="3"
							style="width: 59%;">
							<span id="rem"></span>
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