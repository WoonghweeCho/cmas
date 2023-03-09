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

<%-- 파일 업로드 --%>
<jsp:include page="/common/jsp/comp/fileupload/upload4RexComp.jsp">
	<jsp:param value="hiduploaddiv" name="objId" />
	<jsp:param value="f_upCallback" name="callbackFunc" />
</jsp:include>

<!-- 공통 : 세션정보 -->
<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />

<%-- 공통 : 첨부컴포넌트 --%>
<jsp:include page="/common/jsp/comp/fileupload/uploadComp.jsp" />

<!-- JavaScript Logic 처리 -->
<ut:script src="${contextPath}/trip/innerTrip/innerTripViewDoc.js"></ut:script>

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
				<!-- <a class="btn s1" id="innerTripSave"><span>저장</span></a>
				<a class="btn s1" id="innerTripBdgtChange"><span>예산번호변경</span></a>
				<a class="btn s1" id="innerTripSignChange"><span>결재자변경</span></a>
				<a class="btn s1" id="innerTripDraft"><span>상신</span></a>  -->
				<a class="btn s1" id="createPdf" style="display:none"><span>PDF증빙생성</span></a>
				<a class="btn s1" id="docDelBtn" style="display:none"><span>신청서삭제</span></a>
				<a class="btn s1" id="rejectSap" style="display:none"><span>임시전표삭제</span></a>
				<a class="btn s1" id="confirmSap" style="display:none"><span>회계승인</span></a>
				<a class="btn s1" id="confirmAcct" style="display:none"><span>상태값변경(승인)</span></a>
				<a class="btn s1" id="clsBtn"><span>닫기</span></a>
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
						<tr id="docNoTr" style="display:none">
							<td class="tit" style="width:10%">CMAS docNo</td>
							<td class="inpt" style="width:90%" colspan="3" id="docNo"></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">문서 번호</td>
							<td class="inpt" style="width:90%" colspan="3" id="signId"></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">Ref No</td>
							<td class="inpt" style="width:40%"><span id="tRefNo"></span></td>
							<td class="tit" style="width:10%">전표번호</td>
							<td class="inpt" style="width:40%"><span id="tOrdNo"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">작성자</td>
							<td class="inpt" style="width:90%" id="drafter" colspan="3"></td>
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
							<td class="inpt" style="width:40%" id="bdgtType"></td>
							<td class="tit" style="width:10%">예산번호</td>
							<td class="inpt" style="width:40%"><span id="tAufnr"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">기표/증빙일자</td>
							<td class="inpt" style="width:40%"><span id="tOrdDate"></span> / <span id="tOrdDate2"></span></td>
							<td class="tit" style="width:10%">집행팀</td>
							<td class="inpt" style="width:40%" id="tExecTeam"></td>
						</tr>
				</table>
				<table id="docInfoDetail">
						<tr>
							<td class="tit" style="width:10%">출장자</td>
							<td class="inpt" style="width:40%">
								<span id="tTripUser"></span>
							</td>
							<td class="tit" style="width:10%">소속팀</td>
							<td class="inpt" style="width:40%"><span id="drafterOrgNm"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">출장장소</td>
							<td class="inpt" style="width:40%">
							    <span id="tTripTarget"></span>
							</td>
							<td class="tit" style="width:10%">일정</td>
							<td class="inpt" style="width:40%">
								시작일 :
								<span id="startDate"></span>
								 ~ 종료일 :
								<span id="endDate"></span>
								&nbsp; <span id="betDate"></span>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">업무대행자</td>
							<td class="inpt" style="width:40%">
								<span id="tTripAgent"></span>
							</td>
							<td class="tit" style="width:10%">송급계좌</td>
							<td class="inpt" style="width:40%"><span id="tAccount"></span> / 지불예정일 : <span id="tPayDate"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">교통편</td>
							<td class="inpt" style="width:90%" colspan="3">
								<span id="compCarText">※교통비 미신청 :</span> <select id="compCar">
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
										</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">교통비</td>
							<td class="inpt" style="width:90%" colspan="3">
								<table>
										<tr>
											<td class="tit" style="width:25%">교통비 총계</td>
											<td class="inpt" style="width:25%; text-align:right;" id="tAmountTotal">0원</td>
											<td class="tit" style="width:50%; text-align:center" colspan="2"></td>
										</tr>
										<tr id="viewOld1">
											<td class="tit" style="width:25%">일당비</td>
											<td class="inpt" style="width:25%; text-align:right;">
												<span id="dayAmAmt">0원</span>
												<span id="eduTripSpan" style="display:none;">
													<span id="eduTripAmt"></span>
												</span>
											</td>
											<td class="tit" style="width:25%;">
												<span id="excptDayChkSpan"><input type="checkbox" id="excptDay" readonly="readonly" > 일당비 제외 </span>
												<span id="eduTripChkSpan"><input type="checkbox" id="eduTripChk"> 교육출장 </span>
											</td>
											<td class="tit" style="width:25%; text-align:center" id="dayAm">표준액 : 00000원</td>
										</tr>
										<tr id="viewOld2">
											<td class="tit" style="width:25%">숙박비</td>
											<td class="inpt" style="width:25%; text-align:right;">
												<span id="nightAmAmt">0원</span>
												<span id="excptNightSpan" style="display:none;">
													0원
												</span>
											</td>
											<td class="tit" style="width:25%;">
												<span id="excptNightChkSpan"><input type="checkbox" id="excptNight" readonly="readonly"> 숙박비 제외</span>
											</td>
											<td class="tit" style="width:25%; text-align:center" id="nightAm">표준액 : 00000원 </td>
										</tr>
										<tr id="viewOld3">
											<td class="tit" style="width:25%">합계</td>
											<td class="inpt" style="width:75%; text-align:right;" colspan="3">
												<span id="tripTotalAmt">0원</span>
											</td>
										</tr>
								</table>
							</td>
						</tr>

						<tr id="viewNew1">
							<td class="tit" style="width:10%">숙박 </td>
							<td class="inpt" style="width:90%" colspan="3">
								<table>
										<tr>
											<td class="tit" style="width:25%">숙박시설 일수</td>
											<td class="inpt" style="width:25%; text-align:right;">
												<span id="genAccomoDcnt">0일</span>
											</td>
											<td class="tit" style="width:50%; text-align:center" colspan="2">현장숙소/개인숙박 등 은 [숙박시설 일수]에서 제외입니다 </td>
										</tr>
								</table>
							</td>
						</tr>


						<tr id="viewNew2">
							<td class="tit" style="width:10%">숙박비<br>및<br>기타경비</td>
							<td class="inpt" style="width:90%" colspan="3">
								<table>
										<tr>
											<td class="tit" style="width:25%">(숙박비+식비+잡비) 한도</td>
											<td class="inpt" style="width:25%; text-align:right;" id="limitAccomoAmt">
												<span id="limitAccomoAmt">0원</span>
											</td>
											<td class="tit" style="width:50%; text-align:center" colspan="2">
												한도 = (숙박시설 일수 x 60,000원) + (출장일수 x 30,000원)
											</td>
										</tr>
										<tr>
											<td class="tit" style="width:25%">숙박비</td>
											<td class="inpt" style="width:25%; text-align:right;">
												<span id="accomoAmt">0원</span>
											</td>
											<td class="tit" style="width:50%; text-align:center" colspan="2"></td>
										</tr>
										<tr>
											<td class="tit" style="width:25%">식비</td>
											<td class="inpt" style="width:25%; text-align:right;">
												<span id="eexpAmt">0원</span>
											</td>
											<td class="tit" style="width:50%; text-align:center" colspan="2"></td>
										</tr>
										<tr>
											<td class="tit" style="width:25%">잡비</td>
											<td class="inpt" style="width:25%; text-align:right;">
												<span id="etcAmt">0원</span>
											</td>
											<td class="tit" style="width:50%; text-align:center" colspan="2"></td>
										</tr>
										<tr>
											<td class="tit" style="width:25%">(숙박비+식비+잡비) 합계</td>
											<td class="inpt" style="width:25%; text-align:right;">
												<span id="totAccomoAmt">0원</span>
											</td>
											<td class="tit" style="width:50%; text-align:center" colspan="2">합계금액으로 한도체크 </td>
										</tr>
								</table>
							</td>
						</tr>

						<tr id="viewNew3">
							<td class="tit" style="width:10%">비고 </td>
							<td class="inpt" style="width:90%" colspan="3">
								<span id="rem"></span>
							</td>
						</tr>

						<tr id="viewNew4">
							<td class="tit" style="width:10%">최종 신청금액 </td>
							<td class="inpt" style="width:90%" colspan="3">
								<span id="totAmt">0원</span>
							</td>
						</tr>

						<tr  id="recpTr">
							<td class="tit" style="width:10%">대리수령</td>
							<td class="inpt" style="width:90%" colspan="3">
								<table>

										<tr>
											<td class="tit" style="width:25%">대리수령자</td>
											<td class="inpt" style="width:25%; text-align:right;">
												<span id="receiptUser" style="display: inline-block; width: 150px; height: 22px"></span>
											</td>
											<td class="tit" style="width:25%">대리수령 금액 / 계좌</td>
											<td class="inpt" style="width:25%; text-align:right;">
												<span id="rAmountSpan"><input type="text" id="rAmount" style="width: 150px;"></span>
												 /
												 <span id="rAccountSpan"></span>
											</td>
										</tr>

								</table>
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
			<BR><BR><BR><BR><BR><BR>

		</div>

		<!--//CONTENTS-->
		<div class="bottom_bg">
			<span class="left"></span><span class="right"></span>
		</div>
	</div>
	<!--//right-->

</body>
</html>