<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType"/>
	<jsp:param value="방문도시 입력" name="title" />
	<jsp:param value="popup" name="type" />
</jsp:include>
<ut:script src="${contextPath}/trip/visaAppn/natSelectP.js"></ut:script>

<script type="text/javascript">
	var gContextPath = "${contextPath}";
	$.jgrid.no_legacy_api = true;
	$.jgrid.useJSON = true;
	var datas = ${param.datas}; // 파라메터로 인코딩 된 데이터
</script>
</head>

<body id="popup_body">
	<div id="popup_wrap">
	<!-- TOP BAR -->
	<div id="popup_wrap">
		<div class="top_bar">
			<span class="title">방문지 입력</span>
		</div>
		<!-- CONTENT -->
		<div id="content">
			<!-- Button -->
			<div class="btn-area">
				<a class="btn s4" id="deleteBtn"><span>방문지정보 삭제</span></a>
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
								<div class="list_st2" style="min-height:100px;">
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
							<td class="tit" style="width:20%">방문 도시명</td>
							<td class="inpt" style="width:80%">
								<input id="cityName" type="text" />
							</td>
						</tr>
				</table>
			</div>
			<a class="btn s4" id="visaInfo"><span><B><font color="#000000">국가별 비자 상세 안내</font></B></span></a>
		</div>
		<!--//CONTENTS-->
		<div class="bottom_bg"><span class="left"></span><span class="right"></span></div>
	</div>
	<!--//right-->

</body>
</html>