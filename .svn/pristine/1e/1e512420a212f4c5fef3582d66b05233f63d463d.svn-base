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

<ut:script src="${contextPath}/common/jsp/comp/sapConfN.js"></ut:script>

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
			<span class="title">SAP 반려</span>
		</div>
		<div id="content">
			<!--BTN-->
			<div class="btn-area">
				<a class="btn s5" id="btnClose"><span>닫기</span></a>
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
								<span>문서번호</span>
								<input type="text" id="docNo" />
							</td>
						</tr>
						<tr>
							<td class="inpt">
								<span>REF NO</span>
								<input type="text" id="refNo" />
							</td>
						</tr>
						<tr>
							<td class="inpt">
								<a class="btn s1" id="confN"><span>전송</span></a>
							</td>
						</tr>
					</table>
					전송결과<br>
					<textarea id="sapResult" style="height:370px; width:90%"></textarea>
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