<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType"/>
	<jsp:param value="시내교통비 정산" name="title" />
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
<ut:script src="${contextPath}/trip/cityTransp/cityTranspViewDoc.js"></ut:script>

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
				<span>국내출장</span> &nbsp;>&nbsp; <strong>시내교통비</strong>
			</div>
		</div>  -->
		<!--//top_bar-->

		<!--CONTENTS-->
		<div id="contents">

			<div class="btn-area">
				<a class="btn s1" id="confirmAcct" style="display:none"><span>상태값변경(승인)</span></a>
				<a class="btn s1" id="docDelBtn" style="display:none"><span>신청서삭제</span></a>
				<a class="btn s1" id="closeBtn"><span>닫기</span></a>
			</div>

			<div class="clear"></div>

			<br>

			<!-- 양식명칭 -->
			<div id="viewTitle" class="form_title" align="center">
				<h6>
					시내교통비 정산서
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
						</tr>
						<tr class="signln_nm">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr class="signln_dt">
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
							<td class="inpt" style="width:90%" colspan="5" id="docNo"></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">문서 번호</td>
							<td class="inpt" style="width:90%" colspan="5" id="signId"></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">전표번호</td>
							<td class="inpt" style="width:90%" colspan="5"><span id="tBelnr"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">Ref No</td>
							<td class="inpt" style="width:30%"><span id="tRefNo"></span></td>
							<td class="tit" style="width:10%">기표일자</td>
							<td class="inpt" style="width:20%"><span id="tOrdDate"></span></td>
							<td class="tit" style="width:10%">증빙일자</td>
							<td class="inpt" style="width:20%"><span id="tOrdDate2"></span></td>
						</tr>
						<!-- IF 협의자 존재 -->
						<tr id="signAssistor">
							<td class="tit">협의자</td>
							<td class="inpt" id="signAssistorVal" colspan="7"></td>
						</tr>
						<!-- //IF 협의자 존재 -->
				</table>
				<table id="docInfoDetail">
						<tr>
							<td class="tit" style="width:10%">경비구분</td>
							<td class="inpt" style="width:40%" id="bdgtType"></td>
							<td class="tit" style="width:10%">예산번호</td>
							<td class="inpt" style="width:40%" id="Aufnr"><span id="tAufnr"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">집행팀</td>
							<td class="inpt" style="width:90%" colspan="3" id="bdgtTeam"><span id="tExecTeam"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">소속팀</td>
							<td class="inpt" style="width:90%" colspan="3" id="drafterOrgNm"><span id="tDrafterOrgNm"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">최종수정자</td>
							<td class="inpt" style="width:90%" colspan="3" id="drafter"><span id="tDrafter"></span></td>
						</tr>
				</table>

				<script type="text/javascript">
					//var drafterText = gv_userId + " " + gv_userNm;
					//$("#drafter").text(drafterText);
				</script>

				<table id="transpInfo" >
						<tr>
							<td class="tit" style="text-align:center;width:12%">신청자</td>
							<td class="tit" style="text-align:center;width:9%">사용일</td>
							<td class="tit" style="text-align:center">출발지-도착지-목적지<br>교통수단</td>
							<td class="tit" style="text-align:center;width:12%">사용목적</td>
							<td class="tit" style="text-align:center;width:12%">현장경비종류</td>
							<td class="tit" style="text-align:center;width:10%">이동거리</td>
							<td class="tit" style="text-align:center;width:10%">금액</td>
						</tr>
				</table>
				<table id="amountInfo" >
						<tr>
							<td class="tit" style="width:10%">합계</td>
							<td class="inpt" style="width:90%"><span id="tAmountTotal"></span> 원</td>
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