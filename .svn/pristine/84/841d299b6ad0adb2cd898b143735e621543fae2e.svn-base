<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType"/>
	<jsp:param value="국내출장 정산서" name="title" />
	<jsp:param value="popup" name="type" />
</jsp:include>

<%-- 파일 업로드 --%>
<jsp:include page="/common/jsp/comp/fileupload/upload4RexComp.jsp">
	<jsp:param value="hiduploaddiv" name="objId" />
	<jsp:param value="f_upCallback" name="callbackFunc" />
</jsp:include>

<%-- 공통 : 세션정보 --%>
<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />

<%-- 공통 : 첨부컴포넌트 --%>
<jsp:include page="/common/jsp/comp/fileupload/uploadComp.jsp" />

<!-- JavaScript Logic 처리 -->
<ut:script src="${contextPath}/trip/innerTrip/innerTripDraft.js"></ut:script>

<!-- HTTP Request Parameter 처리 -->
<script type="text/javascript">
	var datas = ${param.datas}; // 파라메터로 인코딩 된 데이터
</script>

<link href="${contextPath}/common/css/base.css" type="text/css"
	rel="stylesheet" />
</head>


<body>
	<!--
	Class Name 	: innerTripList.jsp
	Description : 작성함 - 양식관리에 등록된 결재양식 목록
 -->
	<!--right-->
	<div id="container">

		<!--top_bar-->
		<!-- <div id="map_bar">
			<span class="left"></span><span class="right"></span>
			<div class="map">
				<span>국내출장</span> &nbsp;>&nbsp; <strong>국내출장 신청서</strong>
			</div>
		</div> -->
		<!--//top_bar-->

		<!--CONTENTS-->
		<div id="contents">

			<div class="btn-area">
				<!-- <a class="btn s1" id="sapConfN" style="display:none"><span>SAP 반려</span></a>  -->
				<a class="btn s1" id="innerTripSign"><span>결재선 지정</span></a>
				<a class="btn s1" id="innerTripDelete" style="display:none"><span>삭제</span></a>
				<a class="btn s1" id="innerTripSave"><span>저장</span></a>
				<!-- <a class="btn s1" id="innerTripSignChange"><span>결재자변경</span></a>  -->
				<a class="btn s1" id="innerTripDraft"><span>상신</span></a>
			</div>

			<div class="clear"></div>

			<br>

			<!-- 양식명칭 -->
			<div id="viewTitle" class="form_title" align="center">
				<h6>
					국내출장 정산서
				</h6>
			</div>
			<!-- //양식명칭 -->

			<br> <br>

			<!--결재선-->
			<div id="appr_line2">
				<table id="right_signln" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr class="signln_pos">
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
						</tr>
						<tr class="signln_nm">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr class="signln_dt">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!--//결재선-->


			<div class="clear"></div>

			<br><br>

			<div class="list_st1">

				<table id="docInfo">
						<tr>
							<td class="tit" style="width:10%">문서 번호</td>
							<td class="inpt" style="width:90%" colspan="3" id="docNo"></td>
						</tr>
						<!--
						<tr>
							<td class="tit" style="width:10%">Ref No</td>
							<td class="inpt" style="width:40%"><span id="tRefNo"></span></td>
							<td class="tit" style="width:10%">전표번호</td>
							<td class="inpt" style="width:40%"><span id="tOrdNo"></span></td>
						</tr>
						-->
						<tr>
							<td class="tit" style="width:10%">작성자</td>
							<td class="inpt" style="width:40%" id="drafter"></td>
							<td class="tit" style="width:10%">소속팀</td>
							<td class="inpt" style="width:40%"><span id="drafterOrgNm"></span></td>
						</tr>

						<!-- IF 협의자 존재 -->
						<tr id="signAssistor">
							<td class="tit">협의자</td>
							<td class="inpt" id="signAssistorVal" colspan="3"></td>
						</tr>
						<!-- //IF 협의자 존재 -->
				</table>
				<table id="docInfoDetail">
						<tr>
							<td class="tit" style="width:10%">경비구분</td>
							<td class="inpt" style="width:40%"><span id="bdgtType"></span> (<input name="docType" type="radio" value="H">본사 <input name="docType" type="radio" value="G">지역)
								<a href="javascript:;" class="button-light" id="innerTripBdgtChange">예산번호 선택</a>
							</td>
							<td class="tit" style="width:10%">예산번호</td>
							<td class="inpt" style="width:40%" id="Aufnr"><span id="aufnrNo"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">기표/증빙일자</td>
							<td class="inpt" style="width:40%">
								<input type="text" name="ordDate" style="width:90px">
							</td>
							<td class="tit" style="width:10%">집행팀</td>
							<td class="inpt" style="width:40%" id="bdgtTeam" colspan="3"></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">출장자</td>
							<td class="inpt" style="width:40%">
								<span id="tripUserId" style="display: inline-block; width: 150px; height: 22px"></span>
							</td>
							<td class="tit" style="width:10%">출장자 소속팀</td>
							<td class="inpt" style="width:40%"><span id="tripUserOrgNm"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">출장장소</td>
							<td class="inpt" style="width:40%">
							    <input id="tripTarget" type="text" style="width:30%" maxlength="40"> (목적 : <input id="tripPurpose" type="text" style="width:50%" maxlength="40">)
							</td>
							<td class="tit" style="width:10%">일정</td>
							<td class="inpt" style="width:40%">
								<span class="f_l">시작일&nbsp;:&nbsp;</span>
								<input type="text" name="startDate" style="width:90px" class="int_s1 f_l">
								<span class="f_l">&nbsp;~&nbsp;</span>
								<span class="f_l">종료일&nbsp;:&nbsp;</span>
								<input type="text" name="endDate" style="width:90px" class="int_s1 f_l">
							    &nbsp; <span id="betDate"></span>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">업무대행자</td>
							<td class="inpt" style="width:40%">
								<span id="tripAgent" style="display: inline-block; width: 150px; height: 22px"></span>
							</td>

							<td class="tit" style="width:10%">송금계좌</td>
							<td class="inpt" style="width:40%"><span> / 지불예정일 : </span><input type="text" name="payDate" style="width:90px"></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">교통편</td>
							<td class="inpt" style="width:90%" colspan="3">
								<a href="http://barocon.daewooenc.com/icms/images/emission.jpg" target="_blank" class="button-light" id="searchIst">교통수단별 온실가스 배출량</a><br><br>
								※교통비 미신청 : <select id="compCar" style="width:50px">
									<option value="N" selected="selected">N</option>
									<option value="Y">Y</option>
								</select><br>
								<table id="transpDetail">
										<tr>
											<td class="tit" style="width:12%; text-align:center">출발지</td>
											<td class="tit" style="width:12%; text-align:center">도착지</td>
											<td class="tit" style="width:15%; text-align:center">교통수단</td>
											<td class="tit" style="width:10%; text-align:center">왕복/편도</td>
											<td class="tit" style="width:14%; text-align:center">이동거리/유류량</td>
											<td class="tit" style="width:20%; text-align:center">이용금액</td>
											<td class="tit" style="width:12%; text-align:center">청구금액</td>
											<td class="tit" style="width:5%; text-align:center"></td>
										</tr>
								</table>
								<span class="pull-right">
									<a href="javascript:;" class="button-light" id="addBtn">추가</a><!--<a href="javascript:;" class="button-light" id="delBtn">삭제</a>-->
									<!-- <a href="javascript:;" class="button-light" id="innerTripDetailConf">확정</a><a href="javascript:;" class="button-light" id="innerTripDetailEdit">수정</a>  -->
								</span>
								<!-- <br>
								※ 표준교통비 조회 : 항공, 철도, 버스  -->
								<br>
								<br>
								<a href="http://map.naver.com/" class="button-light" id="searchDist" target="_blank">이동거리 조회</a> ※ 이동거리 입력은 온실가스 배출량 산정에 필요함
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">교통비</td>
							<td class="inpt" style="width:90%" colspan="3">
								<table>
										<tr>
											<td class="tit" style="width:25%">항공</td>
											<td class="inpt" style="width:25%; text-align:right;" id="tAmountTotal1">0원</td>
											<td class="tit" style="width:25%">항공 외</td>
											<td class="inpt" style="width:25%; text-align:right;" id="tAmountTotal2">0원</td>
										</tr>
										<tr>
											<td class="tit" style="width:25%">교통비 총계</td>
											<td class="inpt" style="width:25%; text-align:right;" id="tAmountTotal">0원</td>
											<td class="tit" style="width:50%; text-align:center" colspan="2"></td>
										</tr>
										<tr style="display:none;" id="viewOld1">
											<td class="tit" style="width:25%">일당비</td>
											<td class="inpt" style="width:25%; text-align:right;">
												<span id="dayAmAmt">0원</span>
											</td>
											<td class="tit" style="width:25%;">
												<span id="excptDayChkSpan"><input type="checkbox" id="excptDay"> 일당비 제외 </span>
												<span id="eduTripChkSpan"><input type="checkbox" id="eduTripChk"> 교육출장
													<span id="eduTripChkText" style="display:none;">(표준액 : <input type="text" id="eduTripAmt" style="width:50px">)</span>
												</span>
											</td>
											<td class="tit" style="width:25%; text-align:center" id="dayAm"></td>
										</tr>
										<tr style="display:none;" id="viewOld2">
											<td class="tit" style="width:25%">숙박비</td>
											<td class="inpt" style="width:25%; text-align:right;">
												<span id="nightAmAmt">0원</span>
												<span id="excptNightSpan" style="display:none;">
													<input type="text" id="excptNightAmt">
												</span>
											</td>
											<td class="tit" style="width:25%;">
												<span id="excptNightChkSpan"><input type="checkbox" id="excptNight"> 숙박비 제외</span>
											</td>
											<td class="tit" style="width:25%; text-align:center" id="nightAm"></td>
										</tr>
										<tr style="display:none;" id="viewOld3">
											<td class="tit" style="width:25%">합계</td>
											<td class="inpt" style="width:75%; text-align:right;" colspan="3">
												<span id="tripTotalAmt">0원</span>
											</td>
										</tr>

<!--
										<tr id="receiptTr">
											<td class="tit" style="width:25%">교통비수령</td>
											<td class="inpt" style="width:25%; text-align:right;">
												<span id="receiptUser" style="display: inline-block; width: 150px; height: 22px"></span>
											</td>
											<td class="tit" style="width:25%">
												교통비수령 미지정 <input type="checkbox" id="noReceipt"> 금액 / 계좌
											</td>
											<td class="inpt" style="width:25%; text-align:right;">
												<span id="rAmountSpan"><input type="text" id="rAmount" style="width: 150px;"></span>
												 /
												 <span id="rAccountSpan"></span>
											</td>
										</tr>
-->

								</table>
							</td>
						</tr>

<!--
						<tr>
							<td class="tit" style="width:10%">숙박일수 </td>
							<td class="inpt" style="width:90%" colspan="3">
								&nbsp;
								숙박시설:<input id="genAccomoDcnt" type="text" style="text-align:right; width:40px" maxlength="5">일
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								국내현장:<input id="siteAccomoDcnt" type="text" style="text-align:right; width:40px" maxlength="5">일
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								기타/개인숙박:<input id="etcAccomoDcnt" type="text" style="text-align:right; width:40px" maxlength="5">일
							</td>
						</tr>
-->

						<tr>
							<td class="tit" style="width:10%">숙박 </td>
							<td class="inpt" style="width:90%" colspan="3">
								<table>
										<tr>
											<td class="tit" style="width:25%">숙박시설 일수</td>
											<td class="inpt" style="width:25%; text-align:right;">
												<input id="genAccomoDcnt" type="text" style="text-align:right; width:40px" maxlength="5">일
											</td>
											<td class="tit" style="width:50%; text-align:center" colspan="2">현장숙소/개인숙박 등 은 [숙박시설 일수]에서 제외입니다 </td>
										</tr>
								</table>
							</td>
						</tr>


						<tr>
							<td class="tit" style="width:10%">숙박비<br>및<br>기타경비</td>
							<td class="inpt" style="width:90%" colspan="3">
								<table>
										<tr>
											<td class="tit" style="width:25%; color:#FF0000">(숙박비+식비+잡비) 한도</td>
											<td class="inpt" style="width:25%; text-align:right; color:#FF0000" id="limitAccomoAmt">
												<span id="limitAccomoAmt">0원</span>
											</td>
											<td class="tit" style="width:50%; text-align:center; color:#FF0000" colspan="2">
												한도 = (숙박시설 일수 x 60,000원) + (출장일수 x 30,000원)
											</td>
										</tr>
										<tr>
											<td class="tit" style="width:25%">숙박비</td>
											<td class="inpt" style="width:25%; text-align:right;">
												<input id="accomoAmt" type="text" style="text-align:right; width:120px" maxlength="10">원
											</td>
											<td class="tit" style="width:50%; text-align:center" colspan="2"></td>
										</tr>
										<tr>
											<td class="tit" style="width:25%">식비</td>
											<td class="inpt" style="width:25%; text-align:right;">
												<input id="eexpAmt" type="text" style="text-align:right; width:120px" maxlength="10">원
											</td>
											<td class="tit" style="width:50%; text-align:center" colspan="2"></td>
										</tr>
										<tr>
											<td class="tit" style="width:25%">잡비</td>
											<td class="inpt" style="width:25%; text-align:right;">
												<input id="etcAmt" type="text" style="text-align:right; width:120px" maxlength="10">원
											</td>
											<td class="tit" style="width:50%; text-align:center" colspan="2"></td>
										</tr>
										<tr>
											<td class="tit" style="width:25%">(숙박비+식비+잡비) 합계</td>
											<td class="inpt" style="width:25%; text-align:right; color:#0000FF">
												<span id="totAccomoAmt">0원</span>
											</td>
											<td class="tit" style="width:50%; text-align:center" colspan="2">합계금액으로 한도체크 </td>
										</tr>
								</table>
							</td>
						</tr>

						<tr>
							<td class="tit" style="width:10%">비고 </td>
							<td class="inpt" style="width:90%" colspan="3">
								<input id="rem" type="text" style="width:50%" maxlength="50">
								<!-- <textarea id="rem" style="width: 95%; height: 20px;"></textarea></td> -->
							</td>
						</tr>

						<tr>
							<td class="tit" style="width:10%">최종 신청금액 </td>
							<td class="inpt" style="width:90%; color:#0000FF" colspan="3">
								<span id="totAmt">0원</span>
							</td>
						</tr>

						<tr id="receiptTr">
							<td class="tit" style="width:10%">대리수령</td>
							<td class="inpt" style="width:90%" colspan="3">
									<table>

										<tr>
											<td class="tit" style="width:25%">대리수령자</td>
											<td class="inpt" style="width:25%; text-align:right;">
												<span id="receiptUser" style="display: inline-block; width: 150px; height: 22px"></span>
											</td>
											<td class="tit" style="width:25%">
												대리수령 미지정 <input type="checkbox" id="noReceipt"> 금액 / 계좌
											</td>
											<td class="inpt" style="width:25%; text-align:right;">
												<span id="rAmountSpan"><input type="text" id="rAmount" style="width: 150px;"></span>
												 /
												 <span id="rAccountSpan"></span>
											</td>
										</tr>

								</table>
							</td>
						</tr>

						<tr>
							<td colspan="4">
							<span>
								<b>
								<font color="red">
								 ※ 출장비는 한도 내 실비지급입니다. <br>
								 ※ 실 사용금액에 대한 증빙이 미비하거나 금액의 차이가 있는 경우 지급이 제한될 수 있습니다. <br>
								 ※ 청구금액에 대해서는 반드시 증빙을 첨부해 신청하시기 바랍니다. <br>
								</font>
								 ※ 담당자 : 인사팀 윤은진 대리 (02-2288-3813)
								</b>
							</span>
							 </td>
						</tr>

				</table>
			</div>


			<BR>
			<div class="clear"></div>
			<!--첨부파일/문서첨부-->
			<div class="box">
				<h3><span>첨부 파일</span></h3>
				<div id="atchFile">
					<div id="table" name="fileComponent" class="upload"></div>
				</div>
			</div>

		</div>

		<!-- board list layer -->
		<div id="emmisionLayerDiv" style="position: absolute; bottom:0;left:0; display:none;">
			<div id="emmisionLayerBody" class="layer_width250" style="width: 800px;">
				<a id="layer_close2" style="cursor:pointer; color:#4374D9">close</a>
				<br>
				<img src="${contextPath}/common/images/cmas/emission.jpg">
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