<%@ page language='java' contentType='text/html; charset=UTF-8'
	pageEncoding='UTF-8'%>
<%@include file='/common/jsp/comm/include/baseSetting.jsp'%>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=10">

<!-- Common Library 처리 -->
<jsp:include page='/common/jsp/comm/include/clientLib.jsp'>
	<jsp:param value='사내간행물 등록' name='title' />
	<jsp:param value='proxy' name='popupType'/>
</jsp:include>

<!-- 공통 : 세션정보 -->
<jsp:include page='/common/jsp/comm/include/sessionInfo.jsp' />

<!-- 공통 : 첨부컴포넌트 -->
<jsp:include page='/common/jsp/comp/fileupload/uploadCompEcmActiveX.jsp' />

<!-- JavaScript Logic 처리  -->
<ut:script src='${contextPath}/tech/publication/publicationDataDraft.js'></ut:script>

<!-- 디자인 적용 CSS -->
<link href='${contextPath}/common/css/base.css' type='text/css'	rel='stylesheet' />

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
				<a class='btn s1' id='btnAppn' style='display:none'><span>등록신청</span></a>
				<a class='btn s1' id='btnComplete' style='display:none'><span>등록완료</span></a>
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
					<span id='signFormTitle'>사내간행물 등록</span>
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
				<div><h3>서지사항</h3></div>
				<div class='list_st1' id='techDataDtl'>
				<form id='techDataDtlForm'>
					<table width='100%' border='0' cellspacing='3' cellpadding='0'>
						<col width='15%' />
						<col width='22%' />
						<col width='15%' />
						<col width='22%' />
						<col width='26%' />

						<tr>
							<td class='tit'>제목(표제)</td>
							<td class='inpt' colspan=3><input type='text' name='subject' id='subject' style='width: 98%' class='int_s1 f_l'></td>
							<form name="imgForm" id="imgForm" method="POST" enctype="multipart/form-data">
  								<td class='inpt' rowspan=3>
  									<div style='font-size : 15px; font-family:맑은고딕;font-weight:bold;color : red'>&nbsp;◎ 표지</div>
    								<input type="text" id="fileName" class="file_input_textbox" readonly="readonly">
                  					<div class="file_input_div"> <a class='btn s1' id='btnFind'><span id='findName'></span></a>
                  					<input type="file" class="file_input_hidden" onchange="f_changeImg(this)" />
                  					</div>
                  					<div style='font-size : 13px; font-family:맑은고딕;color : blue'>&nbsp;(gif, jpg, bmp 파일만 가능)</div>
                  					<!--  <a class='btn s4' id='btnUpload'><span>등록</span></a> -->
  								</td>
							</form>
						</tr>

						<tr>
							<td class='tit'>초록</td>
							<td class='inpt' colspan=3><textarea type='text' name='sumry' id='sumry' style='height:100px; width: 98%' class='int_s1'></textarea>
							</td>
						</tr>
						<tr>
							<td class='tit'>KEYWORD</td>
							<td class='inpt' colspan=3><input type='text' name='keywd' id='keywd' style='width: 98%' class='int_s1 f_l'></td>
						</tr>
						</table>

						<table width='100%' border='0' cellspacing='3' cellpadding='0'>
						<col width='15%' />
						<col width='35%' />
						<col width='15%' />
						<col width='35%' />
						<tr>
							<td class='tit'>발행팀</td>
							<td class='inpt'><input	type='text' name='issueTeam' id='issueTeam' style='width: 98%' class='int_s1 f_l' /></td>
							<td class='tit'>발행인</td>
							<td class='inpt'><input	type='text' name='issueEr' id='issueEr' style='width: 98%' class='int_s1 f_l' /></td>
						</tr>
						<tr>
							<td class='tit'>발행팀장</td>
							<td class='inpt'><input	type='text' name='issueMgr' id='issueMgr' style='width: 98%' class='int_s1 f_l' /></td>
							<td class='tit'>발행일</td>
							<td class='inpt'><input type='text' name='issueDd' id='issueDd' style='width: 98%' class='int_s1 f_l' /></td>
						</tr>
						<tr>
							<td class='tit'>편집담당자</td>
							<td class='inpt'><input type='text' name='edtPerchrg' id='edtPerchrg' style='width: 98%' class='int_s1 f_l'></td>
							<td class='tit'>배포구분</td>
							<td class='inpt'><input	type='text' name='distrCls' id='distrCls' style='width: 98%' class='int_s1 f_l' /></td>
						</tr>
						<tr>
							<td class='tit'>감수자</td>
							<td class='inpt'><input type='text' name='prfrd' id='prfrd' style='width: 98%' class='int_s1 f_l'></td>
							<td class='tit'>발행부수</td>
							<td class='inpt'><input	type='text' name='issueCpys' id='issueCpys' style='width: 20%' class='int_s1 f_l' />부</td>
						</tr>
						</table>

					<div class="clear"></div>
					<br>
					<!--첨부파일/문서첨부-->
					<div class="box">
						<h3><span>첨부 파일</span></h3>
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