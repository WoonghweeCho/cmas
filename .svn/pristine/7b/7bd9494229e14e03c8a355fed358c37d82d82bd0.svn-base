<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType"/>
	<jsp:param value="항공료입력" name="title" />
</jsp:include>

<!-- 공통 : 세션정보 -->
<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />

<!-- JavaScript Logic 처리 -->
<ut:script src="${contextPath}/trip/airFare/airFareDraft.js"></ut:script>

<!-- HTTP Request Parameter 처리 -->
<script type="text/javascript">
	var datas = ${param.datas}; // 파라메터로 인코딩 된 데이터
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
		<!--CONTENTS-->
		<div id="contents">

			<div class="btn-area">
					<a class="btn s1" id="drafteBtn"><span>항공료 입력</span></a>
			</div>

			<div class="clear"></div>

			<br>

			<!-- 양식명칭 -->
			<div id="viewTitle" class="form_title" align="center">
				<h6>
					항공료 입력
				</h6>
			</div>
			<!-- //양식명칭 -->

			<br> <br>

			<div class="clear"></div>

			<br><br>

			<div class="list_st1">
				<table id="docInfo">
						<tr>
							<td class="tit" style="width:10%">출장 기간</td>
							<td class="inpt" style="width:40%"><span id="startDate"></span> ~ <span id="endDate"></span> (기간 : <span id="tripDate"></span>일)</td>
							<td class="tit" style="width:10%">증빙일자</td>
							<td class="inpt" style="width:40%"><span id="orderDate"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">문서번호</td>
							<td class="inpt" style="width:40%"><span id="docNo"></span></td>
							<td class="tit" style="width:10%">RefNo</td>
							<td class="inpt" style="width:40%"><span id="refNo"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">전표번호</td>
							<td class="inpt" style="width:90%" colspan="3"><span id="ordNo"></span></td>
						</tr>
				</table>
				<table id="user0" style="display:none">
						<tr>
							<td class="tit" style="text-align:center">영문이름</td>
							<td class="tit" style="text-align:center">좌석 등급</td>
							<td class="tit" style="text-align:center">최초항공료</td>
							<td class="tit" style="text-align:center">발권일</td>
							<td class="tit" style="text-align:center">발권번호</td>
							<td class="tit" style="text-align:center">발권수수료</td>
						</tr>
						<tr>
							<td class="inpt" style="text-align:center">
								<span name="userEnm"></span>
							</td>
							<td class="inpt" style="text-align:center">
								<span name="sGrade"></span>
							</td>
							<td class="inpt" style="text-align:center">
								<span name="fAirFare"></span>
							</td>
							<td class="inpt" style="text-align:center">
								<input name="issDate" type="text" style="width:120px;">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="issNo1" type="text" style="width:120px;"><input name="issNo2" type="text" style="width:120px;">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="issComm" type="text" style="width:120px;" value="0">
							</td>
						</tr>
						<tr>
							<td class="tit" style="text-align:center">FARE</td>
							<td class="tit" style="text-align:center">전쟁보험료</td>
							<td class="tit" style="text-align:center">공항이용료</td>
							<td class="tit" style="text-align:center">현지공항세</td>
							<td class="tit" style="text-align:center">D/C</td>
							<td class="tit" style="text-align:center">현금분할입력</td>
						</tr>
						<tr>
							<td class="inpt" style="text-align:center">
								<input name="fare" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="warIns" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="airPort1" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="airPort2" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="disC" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="amtDiv" type="text" style="width:120px;">
							</td>
						</tr>
						<tr>
							<td class="tit" >지불방법</td>
							<td class="inpt">
								<select name="payWay">
									<option value="C" selected="selected">카드</option>
									<option value="P">현금</option>
								</select>
							</td>
							<td class="tit">발권취소일</td>
							<td class="inpt">
								<input name="cancDate" type="text" style="width:120px;">
							</td>
							<td class="tit">일련번호</td>
							<td class="inpt">
								<span name="aRefNo"></span>
							</td>
						</tr>
						<tr>
							<td class="tit" >확정항공료</td>
							<td class="inpt">
								<span name="confAmt"></span>
							</td>
							<td class="tit">여정</td>
							<td class="inpt" colspan="3">
								<input name="jourN" type="text" style="width:90%;">
							</td>
						</tr>
						<tr>
							<td class="tit" >여행사</td>
							<td class="inpt" colspan="5">
<!--							<input type="radio" name="user0tourC" value="D"> 동서여행사  &nbsp; -->
 								<input type="radio" name="user0tourC" value="H"> 현대드림투어  &nbsp;
<!--							<input type="radio" name="user0tourC" value="J"> 주원항공여행사  &nbsp;-->
								<input type="radio" name="user0tourC" value="N"> 하나투어
							</td>
						</tr>
				</table>

				<br><br>

				<table id="user1" style="display:none">
						<tr>
							<td class="tit" style="text-align:center">영문이름</td>
							<td class="tit" style="text-align:center">좌석 등급</td>
							<td class="tit" style="text-align:center">최초항공료</td>
							<td class="tit" style="text-align:center">발권일</td>
							<td class="tit" style="text-align:center">발권번호</td>
							<td class="tit" style="text-align:center">발권수수료</td>
						</tr>
						<tr>
							<td class="inpt" style="text-align:center">
								<span name="userEnm"></span>
							</td>
							<td class="inpt" style="text-align:center">
								<span name="sGrade"></span>
							</td>
							<td class="inpt" style="text-align:center">
								<span name="fAirFare"></span>
							</td>
							<td class="inpt" style="text-align:center">
								<input name="issDate" type="text" style="width:120px;">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="issNo1" type="text" style="width:120px;"><input name="issNo2" type="text" style="width:120px;">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="issComm" type="text" style="width:120px;" value="0">
							</td>
						</tr>
						<tr>
							<td class="tit" style="text-align:center">FARE</td>
							<td class="tit" style="text-align:center">전쟁보험료</td>
							<td class="tit" style="text-align:center">공항이용료</td>
							<td class="tit" style="text-align:center">현지공항세</td>
							<td class="tit" style="text-align:center">D/C</td>
							<td class="tit" style="text-align:center">현금분할입력</td>
						</tr>
						<tr>
							<td class="inpt" style="text-align:center">
								<input name="fare" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="warIns" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="airPort1" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="airPort2" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="disC" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="amtDiv" type="text" style="width:120px;">
							</td>
						</tr>
						<tr>
							<td class="tit" >지불방법</td>
							<td class="inpt">
								<select name="payWay">
									<option value="C" selected="selected">카드</option>
									<option value="P">현금</option>
								</select>
							</td>
							<td class="tit">발권취소일</td>
							<td class="inpt">
								<input name="cancDate" type="text" style="width:120px;">
							</td>
							<td class="tit">일련번호</td>
							<td class="inpt">
								<span name="aRefNo"></span>
							</td>
						</tr>
						<tr>
							<td class="tit" >확정항공료</td>
							<td class="inpt">
								<span name="confAmt"></span>
							</td>
							<td class="tit">여정</td>
							<td class="inpt" colspan="3">
								<input name="jourN" type="text" style="width:90%;">
							</td>
						</tr>
				</table>

				<br><br>

				<table id="user2" style="display:none">
						<tr>
							<td class="tit" style="text-align:center">영문이름</td>
							<td class="tit" style="text-align:center">좌석 등급</td>
							<td class="tit" style="text-align:center">최초항공료</td>
							<td class="tit" style="text-align:center">발권일</td>
							<td class="tit" style="text-align:center">발권번호</td>
							<td class="tit" style="text-align:center">발권수수료</td>
						</tr>
						<tr>
							<td class="inpt" style="text-align:center">
								<span name="userEnm"></span>
							</td>
							<td class="inpt" style="text-align:center">
								<span name="sGrade"></span>
							</td>
							<td class="inpt" style="text-align:center">
								<span name="fAirFare"></span>
							</td>
							<td class="inpt" style="text-align:center">
								<input name="issDate" type="text" style="width:120px;">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="issNo1" type="text" style="width:120px;"><input name="issNo2" type="text" style="width:120px;">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="issComm" type="text" style="width:120px;" value="0">
							</td>
						</tr>
						<tr>
							<td class="tit" style="text-align:center">FARE</td>
							<td class="tit" style="text-align:center">전쟁보험료</td>
							<td class="tit" style="text-align:center">공항이용료</td>
							<td class="tit" style="text-align:center">현지공항세</td>
							<td class="tit" style="text-align:center">D/C</td>
							<td class="tit" style="text-align:center">현금분할입력</td>
						</tr>
						<tr>
							<td class="inpt" style="text-align:center">
								<input name="fare" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="warIns" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="airPort1" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="airPort2" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="disC" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="amtDiv" type="text" style="width:120px;">
							</td>
						</tr>
						<tr>
							<td class="tit" >지불방법</td>
							<td class="inpt">
								<select name="payWay">
									<option value="C" selected="selected">카드</option>
									<option value="P">현금</option>
								</select>
							</td>
							<td class="tit">발권취소일</td>
							<td class="inpt">
								<input name="cancDate" type="text" style="width:120px;">
							</td>
							<td class="tit">일련번호</td>
							<td class="inpt">
								<span name="aRefNo"></span>
							</td>
						</tr>
						<tr>
							<td class="tit" >확정항공료</td>
							<td class="inpt">
								<span name="confAmt"></span>
							</td>
							<td class="tit">여정</td>
							<td class="inpt" colspan="3">
								<input name="jourN" type="text" style="width:90%;">
							</td>
						</tr>
				</table>

				<br><br>

				<table id="user3" style="display:none">
						<tr>
							<td class="tit" style="text-align:center">영문이름</td>
							<td class="tit" style="text-align:center">좌석 등급</td>
							<td class="tit" style="text-align:center">최초항공료</td>
							<td class="tit" style="text-align:center">발권일</td>
							<td class="tit" style="text-align:center">발권번호</td>
							<td class="tit" style="text-align:center">발권수수료</td>
						</tr>
						<tr>
							<td class="inpt" style="text-align:center">
								<span name="userEnm"></span>
							</td>
							<td class="inpt" style="text-align:center">
								<span name="sGrade"></span>
							</td>
							<td class="inpt" style="text-align:center">
								<span name="fAirFare"></span>
							</td>
							<td class="inpt" style="text-align:center">
								<input name="issDate" type="text" style="width:120px;">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="issNo1" type="text" style="width:120px;"><input name="issNo2" type="text" style="width:120px;">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="issComm" type="text" style="width:120px;" value="0">
							</td>
						</tr>
						<tr>
							<td class="tit" style="text-align:center">FARE</td>
							<td class="tit" style="text-align:center">전쟁보험료</td>
							<td class="tit" style="text-align:center">공항이용료</td>
							<td class="tit" style="text-align:center">현지공항세</td>
							<td class="tit" style="text-align:center">D/C</td>
							<td class="tit" style="text-align:center">현금분할입력</td>
						</tr>
						<tr>
							<td class="inpt" style="text-align:center">
								<input name="fare" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="warIns" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="airPort1" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="airPort2" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="disC" type="text" style="width:120px;" value="0">
							</td>
							<td class="inpt" style="text-align:center">
								<input name="amtDiv" type="text" style="width:120px;">
							</td>
						</tr>
						<tr>
							<td class="tit" >지불방법</td>
							<td class="inpt">
								<select name="payWay">
									<option value="C" selected="selected">카드</option>
									<option value="P">현금</option>
								</select>
							</td>
							<td class="tit">발권취소일</td>
							<td class="inpt">
								<input name="cancDate" type="text" style="width:120px;">
							</td>
							<td class="tit">일련번호</td>
							<td class="inpt">
								<span name="aRefNo"></span>
							</td>
						</tr>
						<tr>
							<td class="tit" >확정항공료</td>
							<td class="inpt">
								<span name="confAmt"></span>
							</td>
							<td class="tit">여정</td>
							<td class="inpt" colspan="3">
								<input name="jourN" type="text" style="width:90%;">
							</td>
						</tr>
				</table>


			</div>

		</div>
		<!--//CONTENTS-->
		<div class="bottom_bg">
			<span class="left"></span><span class="right"></span>
		</div>
	</div>
	<!--//right-->

</body>
</html>