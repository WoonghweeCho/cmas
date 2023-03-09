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
<ut:script src="${contextPath}/trip/outerTrip/retCost.js"></ut:script>

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


	<!-- TOP BAR -->
	<div id="popup_wrap_mini" style="min-width:280px">
		<div class="top_bar">
			<span class="title">반납자 / 반납일 입력</span>
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
						<tr>
							<td class="tit" style="width:20%">반납자</td>
							<td class="inpt" style="width:80%">
								<span id="payRutUser" style="display: inline-block; width: 150px; height: 22px"></span>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">반납일</td>
							<td class="inpt" style="width:80%">
								<input id="payRutDate" type="text">
							</td>
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