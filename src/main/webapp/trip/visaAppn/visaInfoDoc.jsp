<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType"/>
	<jsp:param value="비자정보" name="title" />
	<jsp:param value="popup" name="type" />
</jsp:include>
<ut:script src="${contextPath}/trip/visaAppn/visaInfoDoc.js"></ut:script>

<script type="text/javascript">
	var gContextPath = "${contextPath}";
	$.jgrid.no_legacy_api = true;
	$.jgrid.useJSON = true;
</script>

</head>

<body id="popup_body">

 <!--right-->


	<!-- TOP BAR -->
	<div id="popup_wrap">
		<div class="top_bar">
			<span class="title">국가별 비자안내</span>
		</div>
		<!--CONTENTS-->
		<div id="contents">

			<!--검색설정-->
			<div class="int_box">
			<!-- Button -->
				<div class="bg_T">
					<span class="left"></span><span class="right"></span>
				</div>
				<div class="content">
						<table id="table_sign_search" width="100%" border="0"
							cellspacing="0" cellpadding="0">
							<col width="81px" />
							<col width="" />
							<col width="81px" />
							<col width="" />
							<col width="81px" />
							<col width="450px" />
							<tr>
								<td class="tit">국가명</td>
								<td class="inpt">
									<input name="natNmInput" id="natNmInput" type="text" style="width: 130px;"/>
								</td>
							</tr>
						</table>
				</div>

				<div class="bg_B">
					<span class="left"></span><span class="right"></span>
				</div>
			</div>
			<!--//검색설정-->

			<br>

			<!--임시저장함 목록-->
			<div class="box">
				<h3><span id="visaInfoListCnt"></span> <span>목록</span></h3><span class="cont" ><span id="listCnt"></span></span>

				<!--리스트-->
				<div class="list_st2" style="min-height:100px;">
					<table id="visaInfoList"></table>
					<div id="visaInfoListPager"></div>
				</div>
				<!--//리스트-->
				<div class="clear"></div>
				<div class="list_st1">
				<h3><span>국가 세부 정보</span></h3>
				<table id="docInfo" >
						<tr>
							<td class="tit" style="width:20%">국가명</td>
							<td class="inpt" style="width:30%">
								<span id="natNm"></span>
							</td>
							<td class="tit" style="width:20%">국가코드</td>
							<td class="inpt" style="width:30%">
								<span id="natCd"></span>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">위험여부</td>
							<td class="inpt" style="width:30%">
								<select id="riskYn">
										<option value=""></option>
										<option value="Y">Y</option>
										<option value="N">N</option>
								</select>
							</td>
							<td class="tit" style="width:20%">국내 비자 진행 여부</td>
							<td class="inpt" style="width:30%">
								<select id="visaYn">
										<option value=""></option>
										<option value="Y">Y</option>
										<option value="N">N</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">체류가능기간</td>
							<td class="inpt" style="width:30%">
								<textarea id="stayAblePeriod"></textarea>
							</td>
							<td class="tit" style="width:20%">유효기간</td>
							<td class="inpt" style="width:30%">
								<textarea id="validPeriod"></textarea>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">비자 진행 절차</td>
							<td class="inpt" style="width:80%" colspan="3">
								<textarea id="visaPrgrStage" style="width:95%"></textarea>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">비자종류 및 제출 서류</td>
							<td class="inpt" style="width:80%" colspan="3">
								<textarea id="ncsDoc" style="width:95%"></textarea>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">발급소요기간</td>
							<td class="inpt" style="width:80%" colspan="3">
								<textarea id="issueNeedPeriod" style="width:95%"></textarea>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">비자요금</td>
							<td class="inpt" style="width:80%" colspan="3">
								<textarea id="visaFee" style="width:95%;"></textarea>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">특이사항</td>
							<td class="inpt" style="width:80%" colspan="3">
								<textarea id="pecul" style="width:95%"></textarea>
							</td>
						</tr>
				</table>
				</div>
			</div>
			<!--//임시저장함 목록-->
		</div>

		</div>
		<!--//CONTENTS-->
		<div class="bottom_bg"><span class="left"></span><span class="right"></span></div>
	</div>
	<!--//right-->

</body>
</html>