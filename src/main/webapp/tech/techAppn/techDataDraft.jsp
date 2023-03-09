<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>

<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="자료등록" name="title" />
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
<ut:script src="${contextPath}/tech/techAppn/techDataDraft.js"></ut:script>

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
					<span id="signFormTitle">도서 자료</span>
				</h6>
			</div>
			<!-- //양식명칭 -->

		<!--문서정보-->
			<div id="infoDiv" class="box">
				<h3>문서정보</h3>
				<div class="list_st1">
					<form>
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<col width="10%" />
							<col width="40%" />
							<col width="10%" />
							<col width="40%" />

							<tr>
								<td class="tit">등록번호</td>
								<td class="inpt" name="regNo" id="doregNo"></td>
					   			<td class="tit">UDC코드</td>
								<td class="inpt"><input type="text" name="interClscd" id="interClscd" style="width: 90%"></td>
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
						<col width='40%' />
						<col width='10%' />
						<col width='40%' />

						<tr>
							<td class='tit'>제목</td>
							<td class='inpt' colspan=3>&nbsp;<input type="text" name="subject" id="subject" style="width: 90%" class="int_s1 f_l"></td>
						</tr>
						<tr>
							<td class='tit'>발행처</td>
							<td class='inpt'><input type='text' name='issuePl' id='issuePl' style='width: 90%' class='int_s1' /></td>
							<td class='tit'>발행년도</td>
							<td class='inpt'><input type="text" name="issueYr" id="issueYr" style="width: 90%" class="int_s1 f_l"></td>
						</tr>
						<tr>
							<td class='tit'>저자</td>
							<td class='inpt'><input type="text" name="ath" id="ath" style="width: 90%" class="int_s1 f_l"></td>
							<td class='tit'>분류</td>
							<td class='inpt'><select name="cls">
									<option value="BK" selected="selected">구매 도서</option>
									<!-- <option value="P">사내간행물</option> -->
								</select></td>
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
		</div>
	    <!--//CONTENTS-->
		<!--//본문-->
		</div>
		<!-- CONTENT -->
	</div>
	<!-- //POPUP_WRAP -->

</body>
</html>