<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="헤외산업규격목록 리스트" name="title" />
	<jsp:param value="proxy" name="popupType"/>
</jsp:include>

<!-- 공통 : 세션정보 -->
<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />

<!-- JavaScript Logic 처리 -->
<ut:script src="${contextPath}/tech/techAppn/techOsDataList.js"></ut:script>

<!-- HTTP Request Parameter 처리 -->
<script type="text/javascript">

</script>

<link href="${contextPath}/common/css/base.css" type="text/css" rel="stylesheet" />
</head>

<!--top_bar-->
<div id="map_bar">
	<span class="left"></span><span class="right"></span>
	<div class="map">
	<span>산업규격</span> &nbsp;>&nbsp; <strong>구)산업규격</strong>
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
					<div style="color:red">BS/EN/ISO 규격은 보유량이 방대하여 검색 소요 시간이 오래 걸릴 수 있으니 상세한 규격번호(숫자)로 검색하시기 바랍니다.</div>
				</div>
				<!-- Button -->
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
							<col width="50px" />
							<col width="81px" />
							<col width="" />

							<tr>
							 <td class="tit">구분</td>
								<td class='inpt'><select name="osCls" class="sel_st1">
									<option value="MSS" selected="selected">MSS</option>
									<!-- <option value="API">API</option> -->
									<option value="AASHTO">AASHTO</option>
									<option value="ACI">ACI</option>
									<option value="ASCE">ASCE</option>
									<option value="ASHRAE">ASHRAE</option>
									<option value="ASME">ASME</option>
									<option value="ASTM">ASTM</option>
									<option value="AWWA">AWWA</option>
									<option value="BSI">BSI</option>
									<option value="ICC">ICC</option>
									<option value="IEC">IEC</option>
									<option value="API">API</option>
									<option value="NACE">NACE</option>
							    	<option value="NFPA">NFPA</option>
									<option value="PIP">PIP</option>
									</select>
								</td>
								<td class="tit">규격번호</td>
								<td class="inpt"><input name="subject" id="subject"
								    style="width: 90%" class="int_s1">
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