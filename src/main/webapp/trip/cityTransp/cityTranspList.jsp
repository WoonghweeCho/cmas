<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<%-- Common Library 처리 --%>
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType"/>
	<jsp:param value="시내교통비 정산" name="title" />
</jsp:include>

<%-- JavaScript Logic 처리 --%>
<ut:script src="${contextPath}/trip/cityTransp/cityTranspList.js"></ut:script>

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
				<span>국내출장</span> &nbsp;>&nbsp; <strong>시내교통비</strong>
			</div>
		</div>
		<!--//top_bar-->

		<!--CONTENTS-->
		<div id="contents">

			<!--검색설정-->
			<div class="int_box">
			<!-- Button -->
			<div class="btn-area">
				<a class="btn s1" id="cityTranspSearch"><span>조회</span></a>
				<a class="btn s5" id="cityTranspDraft"><span>작성</span></a>
			</div>

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
								<td class="tit">집행팀</td>
								<td class="inpt"><input name="orgNmInput" id="orgNmInput" type="text"
									style="width: 130px;" class="int_s1" /></td>
								<td class="tit">신청자</td>
								<td class="inpt"><span id="empInput" style="display: inline-block; width: 150px; height: 22px"></span></td>
								<td class="tit">Ref No</td>
								<td class="inpt">
									<input name="refNoInput" id="refNoInput" type="text"
									style="width: 130px;" class="int_s1" />
									</td>
							</tr>
							<tr>
								<td class="tit">경비구분</td>
								<td class="inpt"><select name="expCls" id="expCls"
										style="width: 126px" class="sel_st1"></select></td>
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
											<option value="14">최근 2주</option>
											<option value="31">최근 1달</option>
											<option value="62">최근 2달</option>
											<option value="93">최근 3달</option>
											<option value="183">최근 6달</option>
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
				<h3><span id="cityTranspListCnt"></span> <span>목록</span></h3><span class="cont" ><span id="listCnt"></span></span>

				<!--리스트-->
				<div class="list_st2" style="min-height:450px;">
					<table id="cityTranspList" style="width: 900px;"></table>
					<div id="cityTranspListPager"></div>
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

</body>
</html>