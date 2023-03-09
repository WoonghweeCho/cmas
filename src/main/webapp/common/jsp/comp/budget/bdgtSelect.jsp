<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType"/>
	<jsp:param value="예산선택" name="title" />
	<jsp:param value="popup" name="type" />
</jsp:include>
<ut:script src="${contextPath}/common/jsp/comp/budget/bdgtSelect.js"></ut:script>

<script type="text/javascript">
	var gContextPath = "${contextPath}";
	$.jgrid.no_legacy_api = true;
	$.jgrid.useJSON = true;
	var datas = ${param.datas}; // 파라메터로 인코딩 된 데이터
</script>
</head>
<body id="popup_body">
	<h3>${param.title }</h3>
	<div id="popup_wrap_mini">
		<div class="top_bar">
			<span class="title">예산조회</span>
		</div>
		<div id="content">
			<!--BTN-->
			<div class="btn-area">
				<a class="btn s1" id="btnSearch"><span>조회</span></a> <a
					class="btn s1" id="btnConfirm"><span>확인</span></a> <a
					class="btn s5" id="btnClose"><span>닫기</span></a>
			</div>

			<!--검색설정-->
			<div class="int_box">
				<div class="bg_T">
					<span class="left"></span><span class="right"></span>
				</div>
				<div class="content">

					<table id="bdgtSearchForm" width="100%" border="0" cellspacing="0" cellpadding="0">
						<col width="100%" />
						<tr>
							<td class="inpt">
								<span style="margin-right:38px">경비 구분</span>
								<select id="bdgtType" style="width: 250px">
									<option value="A">특정경비-임원</option>
									<option value="B">특정경비-팀장</option>
									<option value="C">특정경비-팀</option>
									<option value="I">일반경비</option>
									<option value="O">입찰경비</option>
									<option value="P">사업경비</option>
									<option value="S">사업경비-현장코드</option>
									<option value="R">기술연구원경비</option>
									<option value="K">본사집행현장원가</option>
									<option value="Q">현장경비</option>
									<option value="Q1">PJ-현장경비</option>
									<option value="Q2">PJ-PM경비</option>
									<option value="Q3">PJ-PPM경비</option>
									<option value="Q4">PJ-EM경비</option>
									<option value="Q5">PJ-PCM경비</option>
								</select>
							</td>
						</tr>
						<tr id="bdgtTypeA">
							<td class="inpt">
								<span style="margin-right:53px">임원명</span>
							<input name="txtSrchTxt" id="APiexenm"
								type="text" style="width: 200px" class="int_s1" /></td>
						</tr>
						<tr id="bdgtTypeB">
							<td class="inpt">
								<span style="margin-right:53px">팀장명</span>
							<input name="txtSrchTxt" id="BPiexenm"
								type="text" style="width: 200px" class="int_s1" /></td>
						</tr>
						<tr id="bdgtTypeC">
							<td class="inpt">
								<span style="margin-right:61px">팀 명</span>
							<input name="txtSrchTxt" id="CPiteamnm"
								type="text" style="width: 200px" class="int_s1" /></td>
						</tr>
						<tr id="bdgtTypeI">
							<td class="inpt">
								<span style="margin-right:61px">팀 명</span>
							<input name="txtSrchTxt" id="IPiteamnm"
								type="text" style="width: 200px" class="int_s1" />
								&nbsp;&nbsp;
								<span>예산 내역</span>
							<input name="txtSrchTxt" id="IPiexenm"
								type="text" style="width: 200px" class="int_s1" />
							</td>
						</tr>
						<tr id="bdgtTypeO">
							<td class="inpt">
								<span>수주추진 PJ코드</span>
							<input name="txtSrchTxt" id="OPiexecteam"
								type="text" style="width: 200px" class="int_s1" />
								&nbsp;&nbsp;
								<span>수주추진 PJ명</span>
							<input name="txtSrchTxt" id="OPiiokey"
								type="text" style="width: 200px" class="int_s1" />
							</td>
						</tr>
						<tr id="bdgtTypeP">
							<td class="inpt">
								<span style="margin-right:47px">PJ 코드</span>
							<input name="txtSrchTxt" id="PPiexecteam"
								type="text" style="width: 200px" class="int_s1" />
								&nbsp;&nbsp;
								<span>예산 내역</span>
							<input name="txtSrchTxt" id="PPiiokey"
								type="text" style="width: 200px" class="int_s1" />
							</td>
						</tr>
						<tr id="bdgtTypeR">
							<td class="inpt">
								<span style="margin-right:23px">과제 PJ코드</span>
							<input name="txtSrchTxt" id="RPiexecteam"
								type="text" style="width: 200px" class="int_s1" />
								&nbsp;&nbsp;
								<span>과제 내역</span>
							<input name="txtSrchTxt" id="RPiiokey"
								type="text" style="width: 200px" class="int_s1" />
							</td>
						</tr>
						<tr id="bdgtTypeQ">
						<!-- Bukrs // 예산구분
							Prctrnm 집행팀구분 -->
							<td class="inpt">
								<span style="margin-right:53px">현장명</span>
							<input name="txtSrchTxt" id="QPrctrnm"
								type="text" style="width: 200px" class="int_s1" />
							</td>
						</tr>
						<tr id="bdgtTypeQ1">
							<td class="inpt">
								<span style="margin-right:53px">현장명</span>
							<input name="txtSrchTxt" id="Q1Prctrnm"
								type="text" style="width: 200px" class="int_s1" />
							</td>
						</tr>
						<tr id="bdgtTypeQ2">
							<td class="inpt">
								<span style="margin-right:53px">현장명</span>
							<input name="txtSrchTxt" id="Q2Prctrnm"
								type="text" style="width: 200px" class="int_s1" />
							</td>
						</tr>
						<tr id="bdgtTypeQ3">
							<td class="inpt">
								<span style="margin-right:53px">현장명</span>
							<input name="txtSrchTxt" id="Q3Prctrnm"
								type="text" style="width: 200px" class="int_s1" />
							</td>
						</tr>
						<tr id="bdgtTypeQ4">
							<td class="inpt">
								<span style="margin-right:53px">현장명</span>
							<input name="txtSrchTxt" id="Q4Prctrnm"
								type="text" style="width: 200px" class="int_s1" />
							</td>
						</tr>
						<tr id="bdgtTypeQ5">
							<td class="inpt">
								<span style="margin-right:53px">현장명</span>
							<input name="txtSrchTxt" id="Q5Prctrnm"
								type="text" style="width: 200px" class="int_s1" />
							</td>
						</tr>
						<tr id="bdgtTypeK">
							<td class="inpt">
								<span style="margin-right:47px">PJ 코드</span>
							<input name="txtSrchTxt" id="KPiexecteam"
								type="text" style="width: 200px" class="int_s1" />
								&nbsp;&nbsp;
								<span>예산 내역</span>
							<input name="txtSrchTxt" id="KPiiokey"
								type="text" style="width: 200px" class="int_s1" />
							</td>
						</tr>
						<tr id="bdgtTypeS">
							<td class="inpt">
								<span style="margin-right:47px">PJ 코드</span>
							<input name="txtSrchTxt" id="SPiexecteam"
								type="text" style="width: 200px" class="int_s1" />
								&nbsp;&nbsp;
								<span>예산 내역</span>
							<input name="txtSrchTxt" id="SPiiokey"
								type="text" style="width: 200px" class="int_s1" />
							</td>
						</tr>
					</table>

				</div>
				<div class="bg_B">
					<span class="left"></span><span class="right"></span>
				</div>
			</div>
			<!--//검색설정-->

			<br>

			<div class="box">
					<h3>예산목록</h3>

					<!--리스트-->
					<div class="list_st2" style="min-height: 360px">
						<!-- grid 용 table  -->
						<table id="bdgtList" style="width: 100%"></table>
					</div>
					<!--//리스트-->
				<div class="clear"></div>
			</div>
			<!--//box-->

		</div>
		<!--//contents-->
	</div>


</body>
</html>