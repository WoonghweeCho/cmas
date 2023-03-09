<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType"/>
	<jsp:param value="비자정보" name="title" />
	<jsp:param value="popup" name="type" />
</jsp:include>
<ut:script src="${contextPath}/trip/outerTrip/visaInfoAddP.js"></ut:script>

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
			<span class="title">비자정보 입력</span>
		</div>
		<!-- CONTENT -->
		<div id="content">
			<!-- Button -->
			<div class="btn-area">
				<a class="btn s4" id="deleteBtn"><span>비자정보 삭제</span></a>
				<a class="btn s1" id="confirmBtn"><span>확인</span></a>
				<a class="btn s5" id="closeBtn"><span>닫기</span></a>
			</div>

			<div class="list_st1">

				<table id="docInfo" >
						<tr id="natListTr">
							<td class="tit" style="width:20%">국가</td>
							<td class="inpt" style="width:80%">
								<input type="text" name="txtSrchTxt" style="width: 120px" /><span id="searchBtn" class="ui-icon ui-icon-search" lang="kr" style="width: 16px; height: 16px; display: inline-block;"></span>
								<a href="javascript:;" class="button-light" id="filterNull" style="float: right;">전체조회</a>
								<!--리스트-->
								<div class="list_st2" style="min-height:150px;">
									<table id="natList" style="width: 100%;"></table>
									<div id="natListPager"></div>
								</div>
								<!--//리스트-->
							</td>
						</tr>
						<tr id="selNatTr" style="display: none;">
							<td class="tit" style="width:20%">국가</td>
							<td class="inpt" style="width:80%">
								<span id="selectedNat"></span> <a href="javascript:;" class="button-light" id="reSearch">다시검색</a>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">비자유무</td>
							<td class="inpt" style="width:80%">
								<select name="visaYn" id="visaYn">
									<option value="Y">소지</option>
									<option value="N">미소지</option>
									<option value="V">비자면제국가방문</option>
									<option value="T">현지공항비자발급</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">비자 발급일</td>
							<td class="inpt" style="width:80%">
								<input name="visaStartDate" type="text" />
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">비자 만료일</td>
							<td class="inpt" style="width:80%">
								<input name="visaEndDate" type="text" />
							</td>
						</tr>
				</table>
			</div>
			<!--방문지와 통합할려다 실패함-->
			<!-- <div class="box">
				<h3><span>방문지</span></h3>
				<div class="list_st1">
					<div id="cityArea">
					</div>
					<!-- <table>
						<tr>
							<td class="tit" style="width:20%">방문도시명</td>
							<td class="inpt" style="width:80%" colspan="3">
								<input name="vCity" type="text">
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">체류시작일</td>
							<td class="inpt" style="width:30%">
								<input name="vCity" type="text">
							</td>
							<td class="tit" style="width:20%">체류종료일</td>
							<td class="inpt" style="width:30%">
								<input name="vCity" type="text">
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">주요업무내용</td>
							<td class="inpt" style="width:80%" colspan="3">
								<textarea name="tWorkDtl" style="width:97%"></textarea>
							</td>
						</tr>
					</table>
					<div class="clear"></div>
					<span class="pull-right">
						<a href="javascript:;" class="button-light" id="addCityBtn">추가</a><a href="javascript:;" class="button-light" id="delCityBtn">삭제</a>
					</span>
				</div>
			</div> -->

		</div>
		<!--//CONTENTS-->
		<div class="bottom_bg"><span class="left"></span><span class="right"></span></div>
	</div>
	<!--//right-->

</body>
</html>