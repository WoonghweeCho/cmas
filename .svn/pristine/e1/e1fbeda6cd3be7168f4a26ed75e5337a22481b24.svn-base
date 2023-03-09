<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType"/>
	<jsp:param value="부서지정" name="title" />
	<jsp:param value="popup" name="type" />
</jsp:include>
<ut:script src="${contextPath}/trip/cityTransp/orgChgP.js"></ut:script>

<script type="text/javascript">
	var gContextPath = "${contextPath}";
	$.jgrid.no_legacy_api = true;
	$.jgrid.useJSON = true;
	var datas = ${param.datas}; // 파라메터로 인코딩 된 데이터
</script>
</head>
<body id="popup_body">
	<h3>${param.title }</h3>
	<div id="popup_wrap_mini" style="min-width:280px">
		<div class="top_bar">
			<span class="title">소속팀 선택</span>
		</div>
		<div id="content">
			<!--BTN-->
			<div class="btn-area">
				<a class="btn s1" id="btnConfirm"><span>확인</span></a> <a
					class="btn s5" id="btnClose"><span>닫기</span></a>
					<!-- <a class="btn s1" id="btnReset"><span>출발/도착지 삭제</span></a> -->
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
								<span>소속팀을 선택해주세요.</span>
								<select id="orgList" style="width: 200px" ></select>
							</td>
						</tr>
					</table>

				</div>
				<div class="bg_B">
					<span class="left"></span><span class="right"></span>
				</div>
			</div>
			<!--//검색설정-->

		</div>
		<!--//contents-->
	</div>


</body>
</html>