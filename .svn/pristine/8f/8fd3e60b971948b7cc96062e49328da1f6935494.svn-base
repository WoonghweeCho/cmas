<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType"/>
	<jsp:param value="GHR 신청서 의견" name="title" />
	<jsp:param value="popup" name="type" />
</jsp:include>
<ut:script src="${contextPath}/trip/outerTrip/ghrPop.js"></ut:script>

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
			<span class="title">신청서 GHR 의견</span>
		</div>
		<!-- CONTENT -->
		<div id="content">
			<!-- Button -->
			<div class="btn-area">
				<a class="btn s5" id="closeBtn"><span>닫기</span></a>
			</div>

			<div class="list_st1">

				<table id="docInfo" style="height:450px">
						<tr>
							<td class="tit" style="width:20%">신청서 <br>GHR <br>협조의견</td>
							<td class="inpt" style="width:80%">
								<textarea id="ghrComment" style="width:95%; height:450px"></textarea>
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