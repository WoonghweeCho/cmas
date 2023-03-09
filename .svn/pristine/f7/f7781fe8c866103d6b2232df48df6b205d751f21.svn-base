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
<ut:script src="${contextPath}/trip/airFare/invoicePrint.js"></ut:script>

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
			<h6>
				<span id="tourC"></span> 항공료 INVOICE<br>
				회계년도 : <span id="year"></span>년 <span id="month"></span>월
			</h6>
		</div>
		<!-- //양식명칭 -->

		<br> <br>
		<div class="clear"></div>
		<!--문서정보-->
		<div id="infoDiv" class="box">
			<div class="list_st1">
				<h3><span id="normFareListCnt"></span> <span>정상전표</span></h3><span class="cont" ><span id="listCnt"></span></span>
				<div class="clear"></div><br>
				전표번호 : <span id="Belnr1"></span> / 증빙일 : <span id="EBldat1"></span> / 합계금액 : <span id="ENormalsum"></span>
				<table id="norm" width="100%" border="0" cellspacing="0"
					cellpadding="0">
						<tr>
							<td class="tit" style="width:10%; text-align:center">발권번호</td>
							<td class="tit" style="width:15%; text-align:center">이름</td>
							<td class="tit" style="width:10%; text-align:center">사번</td>
							<td class="tit" style="width:15%; text-align:center">발권일</td>
							<td class="tit" style="width:10%; text-align:center">지불방법</td>
							<td class="tit" style="width:10%; text-align:center">실발권금액</td>
							<td class="tit" style="width:10%; text-align:center">손익센터</td>
							<td class="tit" style="width:20%; text-align:center">여정</td>
						</tr>
						<!-- <tr>
							<td class="inpt" style="width:10%; text-align:center">152 11</td>
							<td class="inpt" style="width:15%; text-align:center">CHANG-HYEON JEA</td>
							<td class="inpt" style="width:10%; text-align:center">Z1202576</td>
							<td class="inpt" style="width:15%; text-align:center">2015-10-01</td>
							<td class="inpt" style="width:10%; text-align:center">현금</td>
							<td class="inpt" style="width:10%; text-align:center">100</td>
							<td class="inpt" style="width:10%; text-align:center">3DFUR</td>
							<td class="inpt" style="width:20%; text-align:center">AA</td>
						</tr>  -->
				</table>
				<br>
				<h3><span id="refdFareListCnt"></span> <span>리펀딩전표</span></h3><span class="cont" ><span id="listCnt"></span></span>
				<div class="clear"></div><br>
				전표번호 : <span id="Belnr2"></span> / 증빙일 : <span id="EBldat2"></span> / 합계금액 : <span id="ERefundsum"></span>
				<table id="refd" width="100%" border="0" cellspacing="0"
					cellpadding="0">
						<tr>
							<td class="tit" style="width:10%; text-align:center">발권번호</td>
							<td class="tit" style="width:15%; text-align:center">이름</td>
							<td class="tit" style="width:10%; text-align:center">사번</td>
							<td class="tit" style="width:15%; text-align:center">발권일</td>
							<td class="tit" style="width:10%; text-align:center">지불방법</td>
							<td class="tit" style="width:10%; text-align:center">실발권금액</td>
							<td class="tit" style="width:10%; text-align:center">손익센터</td>
							<td class="tit" style="width:20%; text-align:center">여정</td>
						</tr>
						<!-- <tr>
							<td class="inpt" style="width:10%; text-align:center">152 11</td>
							<td class="inpt" style="width:15%; text-align:center">CHANG-HYEON JEA</td>
							<td class="inpt" style="width:10%; text-align:center">Z1202576</td>
							<td class="inpt" style="width:15%; text-align:center">2015-10-01</td>
							<td class="inpt" style="width:10%; text-align:center">현금</td>
							<td class="inpt" style="width:10%; text-align:center">100</td>
							<td class="inpt" style="width:10%; text-align:center">3DFUR</td>
							<td class="inpt" style="width:20%; text-align:center">AA</td>
						</tr>  -->
				</table>
				<br>
				<h3><span id="cancFareListCnt"></span> <span>취소전표</span></h3><span class="cont" ><span id="listCnt"></span></span>
				<div class="clear"></div><br>
				전표번호 : <span id="Belnr3"></span> / 증빙일 : <span id="EBldat3"></span> / 합계금액 : <span id="ECancelsum"></span>
				<table id="canc" width="100%" border="0" cellspacing="0"
					cellpadding="0">
						<tr>
							<td class="tit" style="width:10%; text-align:center">발권번호</td>
							<td class="tit" style="width:15%; text-align:center">이름</td>
							<td class="tit" style="width:10%; text-align:center">사번</td>
							<td class="tit" style="width:15%; text-align:center">발권일</td>
							<td class="tit" style="width:10%; text-align:center">지불방법</td>
							<td class="tit" style="width:10%; text-align:center">실발권금액</td>
							<td class="tit" style="width:10%; text-align:center">손익센터</td>
							<td class="tit" style="width:20%; text-align:center">여정</td>
						</tr>
						<!-- <tr>
							<td class="inpt" style="width:10%; text-align:center">152 11</td>
							<td class="inpt" style="width:15%; text-align:center">CHANG-HYEON JEA</td>
							<td class="inpt" style="width:10%; text-align:center">Z1202576</td>
							<td class="inpt" style="width:15%; text-align:center">2015-10-01</td>
							<td class="inpt" style="width:10%; text-align:center">현금</td>
							<td class="inpt" style="width:10%; text-align:center">100</td>
							<td class="inpt" style="width:10%; text-align:center">3DFUR</td>
							<td class="inpt" style="width:20%; text-align:center">AA</td>
						</tr>  -->
				</table>
			</div>
		</div>
	</div>


	<!--//CONTENTS-->
	<div class="bottom_bg">
		<span class="left"></span><span class="right"></span>
	</div>
</body>
</html>