<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType"/>
	<jsp:param value="결재시스템" name="title" />
</jsp:include>

<!-- JavaScript Logic 처리 -->
<ut:script src="${contextPath}/trip/airFare/invoiceList.js"></ut:script>

<!-- HTTP Request Parameter 처리 -->
<script type="text/javascript">

</script>

<link href="${contextPath}/common/css/base.css" type="text/css"
	rel="stylesheet" />
</head>



<body>
	<!--
	Class Name 	: invoiceList.jsp
	Description : 인보이스리스트 화면
 -->
	<!--right-->
	<div id="container">

		<!--top_bar-->
		<div id="map_bar">
			<span class="left"></span><span class="right"></span>
			<div class="map">
				<span>항공료</span> &nbsp;>&nbsp; <strong>항공료 INVOICE</strong>
			</div>
		</div>
		<!--//top_bar-->

		<!--CONTENTS-->
		<div id="contents">

			<!--검색설정-->
			<div class="int_box">
			<!-- Button -->
			<div class="btn-area">
				<a class="btn s1" id="print"><span>인쇄</span></a>
				<a class="btn s1" id="invoiceSearch"><span>조회</span></a>
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
								<td class="tit">여행사</td>
								<td class="inpt">
								<select name="tourC" id="tourC" style="width: 126px" class="sel_st1"></select></td>
								<td class="tit">회계년월</td>
								<td class="inpt"><input type="text" name="timeYm"></td>
								<td class="tit">조회선택</td>
								<td class="inpt"><select name="fareCls" id="fareCls" style="width: 126px" class="sel_st1"></select></td>
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
				<h3><span id="normFareListCnt"></span> <span>정상전표</span></h3><span class="cont" ><span id="listCnt"></span></span>
				<div class="clear"></div>
				전표번호 : <span id="Belnr1"></span> / 증빙일 : <span id="EBldat1"></span> / 합계금액 : <span id="ENormalsum"></span>
				<!--리스트-->
				<div class="list_st2" style="min-height:200px;">
					<table id="normFareList" style="width: 900px;"></table>
					<div id="normFareListPager"></div>
				</div>
				<!--//리스트-->
				<h3><span id="refdFareListCnt"></span> <span>리펀딩전표</span></h3><span class="cont" ><span id="listCnt"></span></span>
				<div class="clear"></div>
				전표번호 : <span id="Belnr2"></span> / 증빙일 : <span id="EBldat2"></span> / 합계금액 : <span id="ERefundsum"></span>
				<!--리스트-->
				<div class="list_st2" style="min-height:200px;">
					<table id="refdFareList" style="width: 900px;"></table>
					<div id="refdFareListPager"></div>
				</div>
				<!--//리스트-->
				<h3><span id="cancFareListCnt"></span> <span>취소전표</span></h3><span class="cont" ><span id="listCnt"></span></span>
				<div class="clear"></div>
				전표번호 : <span id="Belnr3"></span> / 증빙일 : <span id="EBldat3"></span> / 합계금액 : <span id="ECancelsum"></span>
				<!--리스트-->
				<div class="list_st2" style="min-height:200px;">
					<table id="cancFareList" style="width: 900px;"></table>
					<div id="cancFareListPager"></div>
				</div>
				<!--//리스트-->
				<!-- board list layer -->
				<div id="emmisionLayerDiv" style="position: absolute; bottom:0;left:0; display:none;">
					<div id="emmisionLayerBody" class="layer_width250" style="width: 660px;">
						<a id="layer_close2" style="cursor:pointer; color:#4374D9">close</a>
						<br>
						<!--리스트-->
						<h3><span id="belListCnt"></span> <span>전표리스트</span></h3><span class="cont" ><span id="listCnt"></span></span>
						<div class="list_st2" style="min-height:200px;">
							<table id="belList" style="width: 640px;"></table>
							<div id="belListPager"></div>
						</div>
						<br>
					</div>
				</div>
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