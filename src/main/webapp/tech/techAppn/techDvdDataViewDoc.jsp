<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>

<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="DVD 자료" name="title" />
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
<ut:script src="${contextPath}/tech/techAppn/techDvdDataViewDoc.js"></ut:script>

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
				<!-- <a class="btn s1" id="btnSave"><span>저장</span></a> -->
				<a class="btn s1" id="btnDraft" style="display:none"><span>대출신청</span></a>
				<a class="btn s2" id="btnEdit" style="display:none"><span>편집</span></a>
				<a class="btn s2" id="btnDelete" style="display:none"><span>삭제</span></a>
				<a class="btn s5" id="btnCancle"><span>닫기</span></a>
			</div>
			<!--//BTN-->

			<div class="clear"></div>

			<br>

			<!-- 양식명칭 -->
			<div id="viewTitle" class="form_title" align="center">
				<h6>
					<span id="signFormTitle">DVD자료</span>
				</h6>
			</div>
			<!-- //양식명칭 -->

		<!--문서정보-->
			<div id="infoDiv" class="box">
				<h3>문서정보</h3>
				<div class="list_st1">
					<form>
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td class="tit" style="width:10%">등록번호</td>
								<td class="inpt" style="width:90%">&nbsp;<span id="regNo"></span></td>
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
						<tr>
							<td class="tit" style="width:10%">타이틀</td>
							<td class="inpt" style="width:40%" colspan=3>&nbsp;<span id="subject"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">배우명</td>
							<td class="inpt" style="width:90%" colspan=3>&nbsp;<span id="actorNm"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">감독</td>
							<td class="inpt" style="width:40%">&nbsp;<span id="spv"></span></td>
							<td class="tit" style="width:10%">상영시간</td>
							<td class="inpt" style="width:40%">&nbsp;<span id="playTime"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">더빙</td>
							<td class="inpt" style="width:40%">&nbsp;<span id="dbng"></span></td>
							<td class="tit" style="width:10%">자막</td>
							<td class="inpt" style="width:40%">&nbsp;<span id="sbtl"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">분류(장르)</td>
							<td class="inpt" style="width:40%">&nbsp;<span id="dvdCls_disp"></span></td>
							<td class="tit" style="width:10%">제작사</td>
							<td class="inpt" style="width:40%">&nbsp;<span id="mkco"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">오디오</td>
							<td class="inpt" style="width:40%">&nbsp;<span id="recTp"></span></td>
							<td class="tit" style="width:10%">스크린</td>
							<td class="inpt" style="width:40%">&nbsp;<span id="scrn"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">내용</td>
							<td class="inpt" style="width:90%" colspan=3>
								<textarea id="cont" style="width: 95%; height: 200px;"></textarea></td>
							</td>
						</tr>
						</table>
					</form>
				</div>
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