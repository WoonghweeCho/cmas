<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="신청목록 리스트" name="title" />
	<jsp:param value="proxy" name="popupType"/>
</jsp:include>

<!-- 공통 : 세션정보 -->
<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />

<!-- JavaScript Logic 처리 -->
<ut:script src="${contextPath}/tech/techAppn/techAppnList.js"></ut:script>

<!-- HTTP Request Parameter 처리 -->
<script type="text/javascript">
	//var datas = ${param.datas}; // 파라메터로 인코딩 된 데이터
	var gv_initMenuCd = "${param.gubun}";
</script>

<link href="${contextPath}/common/css/base.css" type="text/css" rel="stylesheet" />
</head>

<!--top_bar-->
<div id="map_bar">
	<span class="left"></span><span class="right"></span>
	<div class="map">
	<span>관리</span> &nbsp;>&nbsp; <strong>신청현황</strong>
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
			<!-- Button -->
				<div style="float:left">
					<div id="dvNotice" style="color:red;display:none">&nbsp;&nbsp;※ DVD 신청 : AM 12시 이전만 가능 / 대여&반납 : PM 2시 이후만 직원노사협의회 사무실에서 가능 / 신청 후 3일 이내 수령하지 않을경우 대여취소</div>
				</div>
			<div class="btn-area">
				<a class="btn s1" id="search"><span>조회</span></a>
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
							<col width="300px" />
							<col width="81px" />
							<col width="" />
							<tr>
							<!--<td class="tit">구분</td>
								<td class='inpt'><select name="cls" class="sel_st1">
									<option value="ALL" selected="selected">전체</option>
									<option value="BK">구매도서</option>
									<option value="DV">DVD</option></select>
								</td> -->
								<td class="tit">신청자</td>
								<td class="inpt"><input name="userNm" id="userNm" style="width: 100px" class="int_s1"></td>
								<td class="tit">제목</td>
								<td class="inpt"><input name="subject" id="subject" style="width: 400px" class="int_s1"></td>
								<td class="tit">상태</td>
								<td class="inpt"><select name="docSts" style="width: 100px" class="sel_st1">
									<option value="ALL" selected="selected">전체</option>
									<option value="D31">대출신청</option>
									<option value="D50">대출중</option>
									<option value="D51">반납</option>
									</select>
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

			<!--자료  목록-->
			<div class="box">
				<!--리스트-->
				<div class="list_st2" style="min-height:315px;">
					<table id="techAppnList" style="width: 900px;"></table>
					<div id="techAppnListPager"></div>
				</div>
				<!--//리스트-->
			</div>
			<!--//자료 목록-->
		</div>
		<!--//CONTENTS-->
		<div class="bottom_bg">
			<span class="left"></span><span class="right"></span>
		</div>
	</div>
	<!--//right-->

</body>
</html>