<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="자료목록 리스트" name="title" />
	<jsp:param value="proxy" name="popupType"/>
</jsp:include>

<!-- 공통 : 세션정보 -->
<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />

<!-- JavaScript Logic 처리 -->
<ut:script src="${contextPath}/tech/techAppn/techDataList.js"></ut:script>

<!-- HTTP Request Parameter 처리 -->
<script type="text/javascript">

</script>

<link href="${contextPath}/common/css/base.css" type="text/css" rel="stylesheet" />
</head>

<!--top_bar-->
<div id="map_bar">
	<span class="left"></span><span class="right"></span>
	<div class="map">
	<span>보관자료</span> &nbsp;>&nbsp; <strong>보관 도서</strong>
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
					<div style="float:left">
					<!-- <div style="color:red;font-weight:bold">**  **</div> -->
					<div style="color:red">검색어를 입력하지 않고 [조회]버튼 클릭 시 전체 도서 목록이 보여지므로 다소 시간이 소요됩니다.</div>
					</div>
				<!-- Button -->
				<div class="btn-area">
					<a class="btn s1" id="search"><span>조회</span></a>
					<a class="btn s5" id="techDataDraft" style="display:none"><span>도서등록</span></a>
					<a class="btn s5" id="techDvdDataDraft" style="display:none"><span>DVD등록</span></a>
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
							<col width="" />

							<tr>
								<td class="tit">제목</td>
								<td class="inpt"><input name="subject" id="subject"
								    style="width: 300px" class="int_s1">
								</td>
								<td class="tit">저자</td>
								<td class="inpt"><input name="ath" id="ath"
								    style="width: 150px" class="int_s1">
								</td>
								<td class="tit">상태</td>
								<td class="inpt"><select name="docSts" style="width: 100px" class="sel_st1">
									<option value="ALL" selected="selected">전체</option>
									<option value="D30">대출가능</option>
									<option value="D31">대출신청</option>
									<option value="D50">대출중</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="tit">발행년도</td>
								<td class="inpt"><input name="issueYr" id="issueYr"
								    style="width: 100px" class="int_s1">
								</td>
								<td class="tit">발행처</td>
								<td class="inpt"><input name="issuePl" id="issuePl"
								    style="width: 150px" class="int_s1">
								</td>
								<td class="tit">UDC코드</td>
								<td class="inpt"><input name="interClscd" id="interClscd"
								    style="width: 100px" class="int_s1">
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
			<h3>목록</h3>
				<!--리스트-->
				<div class="list_st2" style="min-height:315px;">
					<table id="techDataList" style="width: 900px;"></table>
					<div id="techDataListPager"></div>
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