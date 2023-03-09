<%@ page language='java' contentType='text/html; charset=UTF-8'
	pageEncoding='UTF-8'%>
<%@include file='/common/jsp/comm/include/baseSetting.jsp'%>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=10">

<!-- Common Library 처리 -->
<jsp:include page='/common/jsp/comm/include/clientLib.jsp'>
	<jsp:param value='공지사항 등록' name='title' />
	<jsp:param value='proxy' name='popupType'/>
</jsp:include>

<!-- 공통 : 세션정보 -->
<jsp:include page='/common/jsp/comm/include/sessionInfo.jsp' />

<!-- 공통 : 첨부컴포넌트 -->
<jsp:include page='/common/jsp/comp/fileupload/uploadCompEcmActiveX.jsp' />

<!-- JavaScript Logic 처리  -->
<ut:script src='${contextPath}/tech/techAppn/techNoticeDraft.js'></ut:script>

<!-- 디자인 적용 CSS -->
<link href='${contextPath}/common/css/base.css' type='text/css'	rel='stylesheet' />

<!-- WebEditor core -->
<ut:script src="${contextPath}/common/js/framework/ckeditor/ckeditor.js"></ut:script>

<script type='text/javascript'>
	var datas = ${param.datas}; // 파라메터로 인코딩 된 데이터
	var v_userId = gv_userId;
	var v_userNm = gv_userNm;
	var v_orgCd = gv_orgCd;
	var v_orgNm = gv_orgNm;
	var v_userPositCd = gv_userPositCd;
	var v_userRpswrkCd = gv_userRpswrkCd;
</script>
</head>


<body id='popup_body'>
	<!-- POPUP_WRAP -->
	<div id='popup_wrap'>

		<!-- CONTENT -->
		<div id='content'>

			<!--BTN-->
			<div class='btn-area'>
				<a class='btn s1' id='btnSave'><span>저장</span></a>
				<a class='btn s2' id='btnDelete' style='display:none'><span>삭제</span></a>
				<a class='btn s5' id='btnCancle'><span>닫기</span></a>
			</div>
			<!--//BTN-->

			<div class='clear'></div>
			<br>

			<!-- 양식명칭 -->
			<div id='viewTitle' class='form_title' align='center'>
				<h6>
					<span id='signFormTitle'>공지사항</span>
				</h6>
			</div>
			<!-- //양식명칭 -->

		<!--문서정보-->
			<div id='infoDiv' class='box' style='display:none'>
				<h3>문서정보</h3>
				<div class='list_st1'>
					<form>
						<table width='100%' border='0' cellspacing='3' cellpadding='0'>
							<col width='13%' />
							<col width='87%' />
							<tr>
								<td class='tit'>등록번호</td>
								<td class='inpt' name='regNo' id='doregNo'></td>
							</tr>

 						</table>
					</form>
				</div>
			</div>
		<!--//문서정보-->

		<br>
		<!--본문-->
			<div class='box'>
				<div><h3>문서정보</h3></div>
				<div class='list_st1' id='techDataDtl'>
					<form id='techDataDtlForm'>
						<table width='100%' border='0' cellspacing='3' cellpadding='0'>
							<col width='15%' />
							<col width='35%' />
							<col width='15%' />
							<col width='35%' />
							<tr>
								<td class='tit'>제목</td>
								<td class='inpt' colspan='3'><input type='text' name='subject' id='subject' style='width: 98%' class='int_s1 f_l'></td>
							</tr>
							<tr>
								<td class='tit'>작성자</td>
								<td class="inpt">&nbsp;<span id="drafter"></span></td>
								<td class='tit'>작성일</td>
								<td class="inpt">&nbsp;<span id="fstRegDt"></span></td>
							</tr>
							<tr>
								<td class='tit'>내용</td>
								<td colspan='3'><div id="editBox" class="text-area"  style="height: 310px"></div></td>
							</tr>
						</table>

						<br><br>
						<!--첨부파일/문서첨부 sumry-->
						<div class="box">
						<div><h3>첨부 파일</h3></div>
						<div id="atchFile">
							<div id="table" name="fileComponent" class="upload"></div>
						</div>
						</div>
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