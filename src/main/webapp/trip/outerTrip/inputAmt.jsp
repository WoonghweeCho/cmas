<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType"/>
	<jsp:param value="체제비입력" name="title" />
	<jsp:param value="popup" name="type" />
</jsp:include>
<ut:script src="${contextPath}/trip/outerTrip/inputAmt.js"></ut:script>

<script type="text/javascript">
	var gContextPath = "${contextPath}";
	$.jgrid.no_legacy_api = true;
	$.jgrid.useJSON = true;
	var datas = ${param.datas}; // 파라메터로 인코딩 된 데이터
</script>
</head>

<body id="popup_body">
<!--
	Class Name 	: excluReglSelectP.jsp
	Description : 전결규정 조회
 -->
 <!--right-->
	<!-- POPUP_WRAP -->
	<div id="popup_wrap">


	<!-- TOP BAR -->
	<div id="popup_wrap">
		<div class="top_bar">
			<span class="title">체제비 입력</span>
		</div>
		<!-- CONTENT -->
		<div id="content">
			<!-- Button -->
			<div class="btn-area">
				<a class="btn s1" id="confirmBtn"><span>확인</span></a>
				<a class="btn s5" id="closeBtn"><span>닫기</span></a>
			</div>

			<div class="list_st1">

				<table id="docInfo" >
						<tr id="natListTr">
							<td class="tit" style="width:20%">구분</td>
							<td class="inpt" style="width:80%" colspan="2">
								입력할 체제비를 선택해주세요.
								<table>
									<tr>
										<td style="width: 25%">
											<input type="checkbox" id="usNight"> 숙박비(＄)
										</td>
										<td style="width: 25%">
											<input type="checkbox" id="usDay"> 일당비(＄)
										</td>
										<td style="width: 25%">
											<input type="checkbox" id="edu"> 연수비(＄)
										</td>
										<td style="width: 25%">
											<input type="checkbox" id="spot"> 현장숙소(＄)
										</td>
									</tr>
									<tr>
										<td style="width: 25%">
											<input type="checkbox" id="euNight"> 유럽숙박비(€)
										</td>
										<td style="width: 25%">
											<input type="checkbox" id="euDay"> 유럽일당비(€)
										</td>
										<td style="width: 25%">
											<input type="checkbox" id="enNight"> 영국숙박비(￡)
										</td>
										<td style="width: 25%">
											<input type="checkbox" id="enDay"> 영국일당비(￡)
										</td>
									</tr>
									<tr>
										<td style="width: 25%">
											<input type="checkbox" id="jaNight"> 일본숙박비(￥)
										</td>
										<td style="width: 75%" colspan="3">
											<input type="checkbox" id="jaDay"> 일본일당비(￥)
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr id="usNightTr" style="display:none">
							<td class="tit" style="width:20%">숙박비(＄)</td>
							<td class="inpt" style="width:40%">
								<input id="usNightI" type="text" style="ime-mode:disabled;"/> 일
							</td>
							<td class="tit" style="width:40%">숙박비(＄) 日 기준액 <input type="text" id="usNightRef" style="width:60px"></td>
						</tr>
						<tr id="usDayTr" style="display:none">
							<td class="tit" style="width:20%">일당비(＄)</td>
							<td class="inpt" style="width:40%">
								<input id="usDayI" type="text" style="ime-mode:disabled;"/> 일
							</td>
							<td class="tit" style="width:40%">일당비(＄) 日 기준액 <input type="text" id="usDayRef" style="width:60px"></td>
						</tr>
						<tr id="eduTr" style="display:none">
							<td class="tit" style="width:20%">연수비(＄)</td>
							<td class="inpt" style="width:40%">
								<input id="eduI" type="text" style="ime-mode:disabled;"/> 일
							</td>
							<td class="tit" style="width:40%">연수비(＄) 日 기준액 <input type="text" id="eduRef" style="width:60px"></td>
						</tr>
						<tr id="spotTr" style="display:none">
							<td class="tit" style="width:20%">현장숙소(＄)</td>
							<td class="inpt" style="width:40%">
								<input id="spotI" type="text" style="ime-mode:disabled;"/> 일
							</td>
							<td class="tit" style="width:40%">현장숙소(＄) 日 기준액 <input type="text" id="spotRef" style="width:60px"></td>
						</tr>
						<tr id="euNightTr" style="display:none">
							<td class="tit" style="width:20%">유럽숙박비(€)</td>
							<td class="inpt" style="width:40%">
								<input id="euNightI" type="text" style="ime-mode:disabled;"/> 일
							</td>
							<td class="tit" style="width:40%">유럽숙박비(€) 日 기준액 <input type="text" id="euNightRef" style="width:60px"></td>
						</tr>
						<tr id="euDayTr" style="display:none">
							<td class="tit" style="width:20%">유럽일당비(€)</td>
							<td class="inpt" style="width:40%">
								<input id="euDayI" type="text" style="ime-mode:disabled;"/> 일
							</td>
							<td class="tit" style="width:40%">유럽일당비(€) 日 기준액 <input type="text" id="euDayRef" style="width:60px"></td>
						</tr>
						<tr id="enNightTr" style="display:none">
							<td class="tit" style="width:20%">영국숙박비(￡)</td>
							<td class="inpt" style="width:40%">
								<input id="enNightI" type="text" style="ime-mode:disabled;"/> 일
							</td>
							<td class="tit" style="width:40%">영국숙박비(￡) 日 기준액 <input type="text" id="enNightRef" style="width:60px"></td>
						</tr>
						<tr id="enDayTr" style="display:none">
							<td class="tit" style="width:20%">영국일당비(￡)</td>
							<td class="inpt" style="width:40%">
								<input id="enDayI" type="text" style="ime-mode:disabled;"/> 일
							</td>
							<td class="tit" style="width:40%">영국일당비(￡) 日 기준액 <input type="text" id="enDayRef" style="width:60px"></td>
						</tr>
						<tr id="jaNightTr" style="display:none">
							<td class="tit" style="width:20%">일본숙박비(￥)</td>
							<td class="inpt" style="width:40%">
								<input id="jaNightI" type="text" style="ime-mode:disabled;"/> 일
							</td>
							<td class="tit" style="width:40%">일본숙박비(￥) 日 기준액 <input type="text" id="jaNightRef" style="width:60px"></td>
						</tr>
						<tr id="jaDayTr" style="display:none">
							<td class="tit" style="width:20%">일본일당비(￥)</td>
							<td class="inpt" style="width:40%">
								<input id="jaDayI" type="text" style="ime-mode:disabled;"/> 일
							</td>
							<td class="tit" style="width:40%">일본일당비(￥) 日 기준액 <input type="text" id="jaDayRef" style="width:60px"></td>
						</tr>
				</table>
			</div>

		</div>
		<!--//CONTENTS-->
		<div class="bottom_bg"><span class="left"></span><span class="right"></span></div>
	</div>
	<!--//right-->

</body>
</html>