<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<%-- Common Library 처리 --%>
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType"/>
	<jsp:param value="비자발급 신청서" name="title" />
</jsp:include>

<%-- JavaScript Logic 처리 --%>
<ut:script src="${contextPath}/trip/visaAppn/visaAppnList.js"></ut:script>

<%-- 공통 : 세션정보 --%>
<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />

<%-- HTTP Request Parameter 처리 --%>
<script type="text/javascript">

</script>

<link href="${contextPath}/common/css/base.css" type="text/css"
	rel="stylesheet" />
</head>


<body>
	<!--right-->
	<div id="container">

		<!--top_bar-->
		<div id="map_bar">
			<span class="left"></span><span class="right"></span>
			<div class="map">
				<span>해외출장</span> &nbsp;>&nbsp; <strong>비자발급 신청서</strong>
			</div>
		</div>
		<!--//top_bar-->

		<!--CONTENTS-->
		<div id="contents">

			<!--검색설정-->
			<div class="int_box">
			<!-- Button -->
			<div class="btn-area">
				<a class="btn s1" id="visaAppnSearch"><span>조회</span></a>
				<a class="btn s5" id="visaAppnDraft"><span>작성</span></a>
			</div>

				<div class="bg_T">
					<span class="left"></span><span class="right"></span>
				</div>
				<div class="content">
					<form id="searchForm">
					<!-- 검색 조건 시작 -->
						<table id="table_sign_search" width="100%" border="0"
							cellspacing="0" cellpadding="0">
							<col width="81px" />
							<col width="" />
							<col width="81px" />
							<col width="" />
							<col width="81px" />
							<col width="" />
							<col width="81px" />
							<col width="450px" />
							<tr>
								<td class="tit">작성자</td>
								<td class="inpt">
									<span id="empInput" style="display: inline-block; width: 150px; height: 22px"></span>
									</td>
								<td class="tit">소     속</td>
								<td class="inpt"><input name="orgNmInput" id="orgNmInput" type="text"
									style="width: 130px;" class="int_s1" /></td>
								<td class="tit">결재상태</td>
								<td class="inpt">
									<select name="signStsCd" id="signStsCd"
										style="width: 126px" class="sel_st1">
										</select>
								</td>
								<td class="tit">조회기간</td>
								<td class="inpt">
									<div id='simpleDt' >
										<select name="simpleDtCd" style="width:90px" class="sel_st1 f_l mar_r5">
											<option value="31">최근 1달</option>
											<option value="62">최근 2달</option>
											<option value="93">최근 3달</option>
											<option value="186">최근 6달</option>
											<option value="366">최근 1년</option>
											<option value="DT">상세지정</option>
										</select>
									</div>
									<div id='detailDt' style="display:none">
										<span class="f_l">시작일&nbsp;:&nbsp;</span>
										<input type="text" name="startDate" style="width:90px" class="int_s1 f_l">
										<span class="f_l">&nbsp;~&nbsp;</span>
										<span class="f_l">종료일&nbsp;:&nbsp;</span>
										<input type="text" name="endDate" style="width:90px" class="int_s1 f_l">
									</div>
								</td>
							</tr>
							<tr>
<%-- 회계연동은 고려하지 않음.추후 회계 연관정보를 입력하는 칸 정도 추가는 고려해볼만함.
								<td class="tit">Ref No</td>
								<td class="inpt">
									<input name="refNoInput" id="refNoInput" type="text"
									style="width: 130px;" class="int_s1" />
									</td>
--%>
								<td class="tit">출장자</td>
								<td class="inpt">
									<span id="visaUserInput" style="display: inline-block; width: 150px; height: 22px"></span>
								</td>
								<td class="tit">출국 예정일</td>
								<td class="inpt" colspan="3">
									<input name="stDateInput" id="stDateInput" type="text"
									style="width: 100px;" class="int_s1" /> ~ <input name="edDateInput" id="edDateInput" type="text"
									style="width: 100px;" class="int_s1" />
								</td>

							</tr>
						</table>
						<!-- 검색 조건 끝 -->
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
				<h3><span id="visaAppnListCnt"></span> <span>목록</span></h3><span class="cont" ><span id="listCnt"></span></span>

				<!--리스트-->
				<div class="list_st2" style="min-height:450px;">
					<table id="visaAppnList" style="width: 900px;"></table>
					<div id="visaAppnListPager"></div>
				</div>
				<!--//리스트-->
			</div>
			<!--//임시저장함 목록-->
		</div>
		<!-- board list layer -->
		<div id="emmisionLayerDiv" style="position: absolute; bottom:0;left:0; display:none;">
			<div id="emmisionLayerBody" class="layer_width250" style="width: 1050px;text-align:left">
				<a id="notiClose" style="" class="btn s1"><span>close</span></a>
				<br>
				<br>
				<br>
				<img src="${contextPath}/common/images/cmas/cmas_nt_visa.jpg">
				<br>
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