<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>

<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="도서/간행물 대여 신청" name="title" />
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
<ut:script src="${contextPath}/tech/techAppn/techAppnDraft.js"></ut:script>

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
				<a class="btn s1" id="btnAppn"><span>신청</span></a>
				<a class="btn s2" id="btnDelete" style="display:none"><span>삭제</span></a>
				<a class="btn s5" id="btnCancle"><span>닫기</span></a>
			</div>
			<!--//BTN-->

			<div class="clear"></div>

			<br>

			<!-- 양식명칭 -->
			<div id="viewTitle" class="form_title" align="center">
				<h6>
					<span id="signFormTitle">신청</span>
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
								<td class="inpt" style="widht:90%"><span id="regNo"></span></td>
							</tr>
							<!-- <tr>
								<td class="tit" style="width:10%">문서번호</td>
								<td class="inpt" style="widht:90%"><span id="docNo"></span></td>
							</tr> -->

 						</table>
					</form>
				</div>
			</div>
			<!--//문서정보-->

			<br><br>

			<!--본문-->
			<div class="box">
				<h3>신청자 정보</h3>
				<div class="list_st1">
					<form>
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
						<tr>
							<td class="tit" style="width:10%">신청자</td>
							<td class="inpt" style="width:40%"><span id="drafter"></span></td>
							<td class="tit" style="width:10%">소속팀</td>
							<td class="inpt" style="width:40%"><span id="drafterOrgNm"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:10%">신청일</td>
							<td class="inpt" style="width:40%" id="appnDd"></td>
							<td class="tit" style="width:10%"></td>
							<td class="inpt" style="width:40%"></td>
						</tr>

 						</table>
					</form>
				</div>

				<br><br>

				<h3>신청 정보</h3>
				<div class="list_st1" id="techDataDtl">
				<form id='techDataDtlForm'>
					<table width='100%' border='0' cellspacing='3' cellpadding='0'>
						<tr>
							<td class='tit' style="width:10%">제목</td>
							<td class='inpt' style="width:90%" colspan=3><span id="subject"></span></td>
						</tr>
						<tr>
							<td class='tit' style="width:10%">대여일</td>
							<td class='inpt'style="width:40%"><input type="text" id="rentDd" name="rentDd" style="width:90px"></td>
							<td class='tit' style="width:10%">반납일</td>
							<td class='inpt' style="width:40%"><input type="text" id="rtrnDd" name="rtrnDd" style="width:90px"></td>
						</tr>
						</table>
					</form>
				</div>
			</div>

			<div class="clear"></div>

			<!--첨부파일/문서첨부-->
<!-- 	<div class="box">
			<h3><span>첨부 파일</span></h3>
			<div id="atchFile">
				<div id="table" name="fileComponent" class="upload"></div>
			</div>
			<div class="bottom_bg">
				<span class="left"></span><span class="right"></span>
			</div>
		</div>
 -->
	    <!--//CONTENTS-->
		<!--//본문-->
		</div>
		<!-- CONTENT -->
	</div>
	<!-- //POPUP_WRAP -->

</body>
</html>