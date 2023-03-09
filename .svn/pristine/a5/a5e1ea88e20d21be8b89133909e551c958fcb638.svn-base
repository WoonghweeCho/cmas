<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>

<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="DVD 자료등록" name="title" />
	<jsp:param value="proxy" name="popupType"/>
</jsp:include>

<!-- 파일 업로드 -->
<jsp:include page="/common/jsp/comp/fileupload/upload4RexComp.jsp">
	<jsp:param value="hiduploaddiv" name="objId" />
	<jsp:param value="f_upCallback" name="callbackFunc" />
</jsp:include>

<!-- 공통 : 세션정보 -->
<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />

<!-- 공통 : 첨부컴포넌트 -->
<jsp:include page="/common/jsp/comp/fileupload/uploadComp.jsp" />

<!-- JavaScript Logic 처리  -->
<ut:script src="${contextPath}/tech/techAppn/techDvdDataDraft.js"></ut:script>

<!-- 디자인 적용 CSS -->
<link href="${contextPath}/common/css/base.css" type="text/css"	rel="stylesheet" />

<script type="text/javascript">
	var datas = ${param.datas}; // 파라메터로 인코딩 된 데이터
	var v_userId = gv_userId;
	var v_userNm = gv_userNm;
	var v_orgCd = gv_orgCd;
	var v_orgNm = gv_orgNm;
	var v_userPositCd = gv_userPositCd;
	var v_userRpswrkCd = gv_userRpswrkCd;
</script>
</head>


<body id="popup_body">
	<!-- POPUP_WRAP -->
	<div id="popup_wrap">

		<!-- CONTENT -->
		<div id="content">

			<!--BTN-->
			<div class="btn-area">
				<a class="btn s1" id="btnSave"><span>저장</span></a>
				<a class="btn s2" id="btnDelete" style="display:none"><span>삭제</span></a>
				<a class="btn s5" id="btnCancle"><span>닫기</span></a>
			</div>
			<!--//BTN-->

			<div class="clear"></div>

			<br>

			<!-- 양식명칭 -->
			<div id="viewTitle" class="form_title" align="center">
				<h6>
					<span id="signFormTitle">DVD 자료</span>
				</h6>
			</div>
			<!-- //양식명칭 -->

		<!--문서정보-->
			<div id="infoDiv" class="box">
				<h3>문서정보</h3>
				<div class="list_st1">
					<form>
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<col width="13%" />
							<col width="87%" />
							<tr>
								<td class="tit">등록번호</td>
								<td class="inpt" name="regNo" id="doregNo"></td>
							</tr>

 						</table>
					</form>
				</div>
			</div>
			<!--//문서정보-->

			<br>

			<!--본문-->
			<div class="box">
				<h3>문서 상세내용</h3>
				<div class="list_st1" id="techDataDtl">
				<form id='techDataDtlForm'>
					<table width='100%' border='0' cellspacing='3' cellpadding='0'>
						<col width='10%' />
						<col width='23%' />
						<col width='10%' />
						<col width='23%' />
						<col width='10%' />
						<col width='23%' />
						<tr>
							<td class='tit'>타이틀</td>
							<td class='inpt' colspan=3><input type="text" name="subject" id="subject" style="width: 98%" class="int_s1 f_l"></td>
						</tr>
						<tr>
							<td class='tit'>배우명</td>
							<td class='inpt' colspan=3><input type="text" name="actorNm" id="actorNm" style="width: 98%" class="int_s1 f_l"></td>
						</tr>
						<tr>
							<td class='tit'>감독</td>
							<td class='inpt'><input type='text' name='spv' id='spv' style='width: 98%' class='int_s1' /></td>
							<td class='tit'>상영시간</td>
							<td class='inpt'><input type="text" name="playTime" id="playTime" style="width: 98%" class="int_s1 f_l"></td>
						</tr>
						<tr>
							<td class='tit'>더빙</td>
							<td class='inpt'><input type="text" name="dbng" id="dbng" style="width: 98%" class="int_s1 f_l"></td>
							<td class='tit'>자막</td>
							<td class='inpt'><input	type='text' name='sbtl' id='sbtl' style='width: 98%' class='int_s1 f_l' /></td>
						</tr>
						<tr>
							<td class='tit'>분류(장르)</td>
							<td class='inpt'><select name="dvdCls">
									<option value="" selected="selected"></option>
									<option value="D">드라마/코믹</option>
									<option value="A">액션/SF</option>
									<option value="H">공포/스릴러</option>
									<option value="C">어린이/가족/만화</option>
									<option value="P">Playstation2</option>
									<option value="E">기타</option>
									</select>
							</td>
							<td class='tit'>구분</td>
							<td class='inpt'><name='cls' id='cls' value='DV' style='width: 98%' class='int_s1' />DVD</td>
						</tr>
						<tr>
							<td class='tit'>제작사</td>
							<td class='inpt'><input type="text" name="mkco" id="mkco" style="width: 98%" class="int_s1 f_l"></td>
							<td class='tit'>녹음유형</td>
							<td class='inpt'><input	type='text' name='recTp' id='recTp' style='width: 98%' class='int_s1 f_l' /></td>
						</tr>
						<tr>
							<td class='tit'>화면</td>
							<td class='inpt'><input type="text" name="scrn" id="scrn" style="width: 98%" class="int_s1 f_l"></td>
							<td class='tit'></td>
							<td class='inpt'></td>
						</tr>
						<tr>
							<td class='tit'>내용</td>
							<td class='inpt' colspan=3><textarea type='text' name='cont' id='cont' style='height:300px; width: 98%' class='int_s1'></textarea>
							</td>
						</tr>

						</table>
					</form>
				</div>
			</div>
	    <!--//CONTENTS-->
		<!--//본문-->
		</div>
		<!-- CONTENT -->
	</div>
	<!-- //POPUP_WRAP -->

</body>
</html>