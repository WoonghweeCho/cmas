<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=10">


<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="사내간행물" name="title" />
	<jsp:param value="proxy" name="popupType"/>
</jsp:include>

<!-- 공통 : 세션정보 -->
<jsp:include page="/common/jsp/comm/include/sessionInfo.jsp" />

<!-- 공통 : 첨부컴포넌트 -->
<jsp:include page='/common/jsp/comp/fileupload/uploadCompEcmActiveX.jsp' />

<!-- JavaScript Logic 처리  -->
<ut:script src="${contextPath}/tech/publication/publicationDataViewDoc.js"></ut:script>

<!-- 디자인 적용 CSS -->
<link href="${contextPath}/common/css/base.css" type="text/css"	rel="stylesheet" />

<script type="text/javascript">
	var gContextPath = "${contextPath}";
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
				<a class='btn s1' id='btnComplete' style='display:none'><span>등록완료</span></a>
				<a class="btn s1" id="btnDraft" style="display:none"><span>열람신청</span></a>
				<a class="btn s1" id="btnPrint" style="display:none"><span>서지사항인쇄</span></a>
				<a class="btn s1" id="btnEdit" style="display:none"><span>편집</span></a>
				<a class="btn s2" id="btnDelete" style="display:none"><span>삭제</span></a>
				<a class="btn s5" id="btnCancle"><span>닫기</span></a>
			</div>
			<!--//BTN-->

			<div class="clear"></div>

			<br>

			<!-- 양식명칭 -->
			<div id="viewTitle" class="form_title" align="center">
				<h6>
					<span id="signFormTitle">사내간행물</span>
				</h6>
			</div>
			<!-- //양식명칭 -->

		<!--문서정보-->
			<div id="infoDiv" class="box">
				<h3>문서정보</h3>
				<div class="list_st1">
					<form>
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
						<col width='15%' />
						<col width='35%' />
						<col width='15%' />
						<col width='35%' />

							<tr>
								<td class="tit">등록번호</td>
								<td class="inpt">&nbsp;<span id="cls"></span><span id="regNo"></span>
								<td class="tit">서지번호</td>
								<td class="inpt">&nbsp;<span id="issueRegNo"></span>
								</td>
							</tr>
 						</table>
					</form>
				</div>
			</div>
			<!--//문서정보-->
			<br>

			<!--본문-->
			<div class="box">
				<h3>서지사항</h3>
				<div class="list_st1" id="techDataDtl">
				<form id='techDataDtlForm'>
					<table width='100%' border='0' cellspacing='3' cellpadding='0'>
						<col width='15%' />
						<col width='23%' />
						<col width='15%' />
						<col width='23%' />
						<col width='24%' />
						<tr>
							<td class='tit'>제목(표제)</td>
							<td class='inpt' colspan=3>&nbsp;<span id='subject'></span></td>
							<td class='inpt' rowspan=4>
							<!--첨부파일/문서첨부-->
							<div style="text-align: center;"><a href="url"><img id="img" src="" style="width: 230px; height: auto; text-align: center;" border="0"></a></div>
							<!--<div class="box" id="imgdiv" style="background-image:url()">-->
							</td>
						</tr>
						<tr>
							<td class="tit">초록</td>
							<td class="inpt" colspan=3>
								<textarea id="sumry" style="width: 95%; height: 130px;"></textarea></td>
							</td>
						</tr>
						<tr>
							<td class='tit'>KEYWORD</td>
							<td class="inpt" colspan=3>
								<textarea id="keywd" style="width: 95%; height: 50px;"></textarea></td>
							</td>
						</tr>
					</table>

					<table width='100%' border='0' cellspacing='3' cellpadding='0'>
						<col width='10%' />
						<col width='23%' />
						<col width='10%' />
						<col width='23%' />
						<col width='10%' />
						<col width='23%' />

						<tr>
							<td class='tit'>발행팀</td>
							<td class='inpt'>&nbsp;<span id='issueTeam'></span></td>
							<td class='tit'>발행인</td>
							<td class='inpt'>&nbsp;<span id='issueEr'></span></td>
						</tr>
						<tr>
							<td class='tit'>발행팀장</td>
							<td class='inpt'><span id='issueMgr'></span></td>
							<td class='tit'>발행일</td>
							<td class='inpt'><span id='issueDd'></span></td>
						</tr>
						<tr>
							<td class='tit'>편집담당자</td>
							<td class='inpt'><span id='edtPerchrg'></span></td>
							<td class='tit'>배포구분</td>
							<td class='inpt'><span id='distrCls'></span></td>
						</tr>
						<tr>
							<td class='tit'>감수자</td>
							<td class='inpt'><span id='prfrd'></span></td>
							<td class='tit'>발행부수</td>
							<td class='inpt'><span id='issueCpys'></span>부</td>
						</tr>
						</table>
					<div class="clear"></div>
					<br>
					<!--첨부파일/문서첨부-->
					<div class="box" id ="atchBox">
						<h3><span>첨부 파일</span></h3>
						<div id="atchFile">
							<div id="table" name="fileComponent" class="upload"></div>
						</div>
					</div>
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