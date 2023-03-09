<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="특별ID 신청 리스트" name="title" />
	<jsp:param value="proxy" name="popupType"/>
</jsp:include>

<!-- 공통 : 세션정보 -->
<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />

<!-- JavaScript Logic 처리 -->
<ut:script src="${contextPath}/id/idAppn/idAppnList.js"></ut:script>

<!-- HTTP Request Parameter 처리 -->
<script type="text/javascript">

</script>

<link href="${contextPath}/common/css/base.css" type="text/css"
	rel="stylesheet" />
</head>

		<!--top_bar-->
		<div id="map_bar">
			<span class="left"></span><span class="right"></span>
			<div class="map">
				<span>특별ID</span> &nbsp;>&nbsp; <strong>특별ID 신청 목록</strong>
			</div>
		</div>
		<!--//top_bar-->

<body>
	<!--right-->
	<div id="container">
		<!--CONTENTS-->
		<div id="contents">

			<!--검색설정-->
			<div class="int_box">
				<a class="btn s4" id="processInfo"><span style="color:red;">발급절차안내</span></a>
				<a class="btn s4" id="passwordInfo"><span style="color:red;">초기비번안내</span></a>
			<!-- Button -->
			<div class="btn-area">
				<a class="btn s1" id="search"><span>조회</span></a>
				<a class="btn s5" id="idAppnDraft"><span>신규</span></a>
				<a class="btn s5" id="idAppnExtDraft"><span>연장</span></a>
				<a class="btn s5" id="idAppnExpDraft"><span>해지</span></a>
				<a class="btn s5" id="idAppnChgDraft" style="display : none;"><span>SVPN권한추가</span></a>
				<a class="btn s5" id="idAppnEditDraft"><span>기본정보변경</span></a>
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
								<td class="tit">구분</td>
								<td class="inpt"><select name="cls" id="cls"
									style="width: 126px" class="sel_st1"></select></td>
								<td class="tit">문서상태</td>
								<td class="inpt"><select name="docSts" style="width: 126px"
											class="sel_st1 f_l mar_r5"></select></td>
								<td class="tit">최종수정일</td>
								<td class="inpt">
									<div id='simpleDt'>
										<select name="simpleDtCd" style="width: 126px"
											class="sel_st1 f_l mar_r5"></select>
									</div>
									<div id='detailDt' style="display: none">
										<span class="f_l">시작일&nbsp;:&nbsp;</span> <input type="text"
											name="startDate" style="width: 90px" class="int_s1 f_l">
										<span class="f_l">&nbsp;~&nbsp;</span> <span class="f_l">종료일&nbsp;:&nbsp;</span>
										<input type="text" name="endDate" style="width: 90px"
											class="int_s1 f_l">
									</div>
								</td>
							</tr>
							<tr>
								<td class="tit">소속</td>
								<td class="inpt"><select name="orgCd" style="width: 126px"
											class="sel_st1 f_l mar_r5"></select></td>
								<td class="tit">신청자</td>
								<td class="inpt"><input name="appnUser" id="appnUser"
									type="text" style="width: 126px" class="int_s1" /></td>
								<td class="tit">대상자</td>
								<td class="inpt"><input name="trgtUser" id="trgtUser"
									type="text" style="width: 126px" class="int_s1" /></td>
							</tr>
							<tr>
								<td class='tit'>조직명</td>
								<td class='inpt'><input name='orgNm' id='orgNm'
									type='text' style='width: 126px' class='int_s1' /></td>
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

			<!--특별ID 신청 목록-->
			<div class="box">
				<!--리스트-->
				<div class="list_st2" style="min-height:315px;">
					<table id="idAppnList" style="width: 900px;"></table>
					<div id="idAppnListPager"></div>
				</div>
				<!--//리스트-->
			</div>
			<!--//특별ID 신청 목록-->

			<!--특별ID 신청 상세목록-->
			<div class="box">
				<!--리스트-->
				<div class="list_st2" style="min-height:105px;">
					<table id="idAppnDtlList" style="width: 900px;"></table>
					<div id="idAppnDtlListPager"></div>
				</div>
				<!--//리스트-->
			</div>
			<!--//특별ID 신청 상세목록-->
		</div>
		<!--//CONTENTS-->
		<div class="bottom_bg">
			<span class="left"></span><span class="right"></span>
		</div>
	</div>
	<!--//right-->

</body>
</html>