<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>

<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="본사층별안내등록" name="title" />
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
<ut:script src="${contextPath}/info/floor/hqFloorDraft.js"></ut:script>

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
				<a class="btn s1" id="btnSave" style="display:none"><span>저장</span></a>
				<a class="btn s2" id="btnDelete" style="display:none"><span>삭제</span></a>
				<a class="btn s5" id="btnCancle"><span>닫기</span></a>
			</div>
			<!--//BTN-->
			<div class="clear"></div>
			<br>

			<!-- 양식명칭 -->
			<div id="viewTitle" class="form_title" align="center">
				<h6>
					<span id="signFormTitle">본사층별 안내</span>
				</h6>
			</div>
			<!-- //양식명칭 -->

			<br>

			<!--본문-->
			<div class="box">
				<h3>상세내용</h3>
				<div class="list_st1" id="techDataDtl">
				<form id='techDataDtlForm'>
					<table width='100%' border='0' cellspacing='3' cellpadding='0'>
						<col width='20%' />
						<col width='80%' />

						<tr>
							<td class='tit'>층</td>
							<td class='inpt'><input type="text" name="floor" id="floor" style="width: 10%" class="int_s1 f_l">층	</td>
						</tr>
						<tr>
							<td class='tit'>본부</td>
							<td class='inpt'><textarea type="text" name="bonbu" id="bonbu" style='height:80px; width: 98%' class="int_s1 f_l"></textarea>
							</td>
						</tr>
						<tr>
							<td class='tit'>팀</td>
							<td class='inpt'><textarea type='text' name='team' id='team' style='height:140px; width: 98%' class='int_s1'></textarea>
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