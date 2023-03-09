<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType" />
	<jsp:param value="국내 출장 에러 케이스" name="title" />
	<jsp:param value="popup" name="type" />
</jsp:include>
<ut:script src="${contextPath}/trip/innerTrip/innerTripError.js"></ut:script>

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
				<span class="title">국내 출장 에러 케이스</span>
			</div>
			<!-- CONTENT -->
			<div id="content">
				<!-- Button -->
				<div class="btn-area">
					<a class="btn s1" id="innerTripSearch"><span>조회</span></a> <a
						class="btn s5" id="closeBtn"><span>닫기</span></a>
				</div>

				<!--검색설정-->
				<div class="int_box">

					<div class="bg_T">
						<span class="left"></span><span class="right"></span>
					</div>
					<div class="content">
						<form id="searchForm">
							<table id="table_sign_search" width="100%" border="0"
								cellspacing="0" cellpadding="0">
								<col width="81px" />
								<col width="" />
								<col width="81px" />
								<col width="" />
								<col width="81px" />
								<col width="450px" />
								<tr>
									<td class="tit">작성자</td>
									<td class="inpt"><span id="empInput"
										style="display: inline-block; width: 150px; height: 22px"></span>
									</td>
									<td class="tit">소속팀</td>
									<td class="inpt"><input name="orgNmInput" id="orgNmInput"
										type="text" style="width: 130px;" class="int_s1" /></td>
									<td class="tit">결재상태</td>
									<td class="inpt"><select name="signStsCd" id="signStsCd"
										style="width: 126px" class="sel_st1">
									</select></td>
								</tr>
								<tr>
									<td class="tit">Ref No</td>
									<td class="inpt"><input name="refNoInput" id="refNoInput"
										type="text" style="width: 130px;" class="int_s1" /></td>
									<td class="tit">출장자</td>
									<td class="inpt"><span id="tripUserInput"
										style="display: inline-block; width: 150px; height: 22px"></span>
									</td>
									<td class="tit">출장기간</td>
									<td class="inpt"><input name="stDateInput"
										id="stDateInput" type="text" style="width: 100px;"
										class="int_s1" /> ~ <input name="edDateInput"
										id="edDateInput" type="text" style="width: 100px;"
										class="int_s1" /></td>
								</tr>
							</table>
						</form>
					</div>

					<div class="bg_B">
						<span class="left"></span><span class="right"></span>
					</div>
				</div>
				<!--//검색설정-->

				<br>

				<!--임시저장함 목록-->
				<div class="box">
					<h3>
						<span id="innerTripListCnt"></span> <span>목록</span>
					</h3>
					<span class="cont"><span id="listCnt"></span></span>

					<!--리스트-->
					<div class="list_st2" style="min-height: 450px;">
						<table id="innerTripList" style="width: 900px;"></table>
						<div id="innerTripListPager"></div>
					</div>
					<!--//리스트-->
				</div>
				<!--//임시저장함 목록-->

			</div>
			<!--//CONTENTS-->
			<div class="bottom_bg">
				<span class="left"></span><span class="right"></span>
			</div>
		</div>
		<!--//right-->
	</div>
</body>
</html>