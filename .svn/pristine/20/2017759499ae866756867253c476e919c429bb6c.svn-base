<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/common/jsp/comm/include/baseSetting.jsp" %>

<html>
<head>
	<!-- Common Library 처리 -->
	<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
		<jsp:param value="우편번호 검색" name="title"/>
		<jsp:param value="proxy" name="popupType"/>
	</jsp:include>
	<script type="text/javascript" src="${contextPath}/common/js/sign/comcall.js"></script>

	<!-- JavaScript Logic 처리 -->
	<ut:script src="${contextPath}/common/jsp/comp/zipCode/zipCode.js"></ut:script>

	<!-- HTTP Request Parameter 처리 -->
	<script type="text/javascript">
	</script>

	<link href="${contextPath}/common/css/base.css" type="text/css" rel="stylesheet" />
</head>

<body>
<div id="container">
		<!--CONTENTS-->
		<div id="contents">
			<!-- Button -->
			<div class="btn-area">
				<a class="btn s1" id="search"><span>조회</span></a>
			</div>
			<!-- //Button -->

			<!--검색설정-->
			<div class="int_box">
				<div class="bg_T"><span class="left"></span><span class="right"></span></div>
				<div class="content">
					<form id="searchForm" action="" >
						<table id="table_pstSrch_search" width=300 border="0" cellspacing="0" cellpadding="0">
							<col width="170px"/>
							<col width=""/>
							<col width="81px"/>
							<col width=""/>
							<col width="81px"/>
							<col width="450px"/>
							<tr>
								<td class="tit">구분</td>
								<td class="inpt"><select name="addsCd" id="addsCd" style="width:90px" class="sel_st1">
								                                   <option value="P">구주소</option>
                                                                   <option value="S">신주소</option>
                                                       </select></td>
								<td class="inpt"><input name="pstSrch" id="pstSrch" type="text" style="width:150px" class="int_s1" /></td>
							</tr>
						</table>
					</form>
				</div>
			<div class="bg_B"><span class="left"></span><span class="right"></span></div>
			</div>
			<!--//검색설정-->

			<br>

			<!--목록-->
			<div class="box">
				<h3><span id="outputPstSrchspan"></span> 우편번호</h3><span class="cont" ><span id="listCnt"></span></span>

				<!--리스트-->
				<div class="list_st2" style="min-height:250px;">
					<table id="outputPstSrchList" style="width: 150px;"></table>
					<div id="outputPstSrchListPager"></div>
				</div>
				<!--//리스트-->
			</div>
			<!--//목록-->

		</div>
		<!--//CONTENTS-->
		<div class="bottom_bg"><span class="left"></span><span class="right"></span></div>
	</div>
	<!--//right-->

</body>
</html>
</html>