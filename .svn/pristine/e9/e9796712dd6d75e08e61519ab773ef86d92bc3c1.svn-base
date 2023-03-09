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
<jsp:include page='/common/jsp/comp/fileupload/uploadCompEcm.jsp' />

<!-- JavaScript Logic 처리  -->
<ut:script src="${contextPath}/tech/publication/printViewDoc.js"></ut:script>

<!-- 디자인 적용 CSS -->
<link href="${contextPath}/common/css/base.css" type="text/css"	rel="stylesheet" />

<script type="text/javascript">
	var datas = ${param.datas}; // 파라메터로 인코딩 된 데이터
</script>
</head>


<body id="popup_body">
	<!-- POPUP_WRAP -->
	<div id="popup_wrap">

		<!-- CONTENT -->
		<div id="content">

			<!--BTN-->
			<div class="btn-area">
				<!--<a class='btn s1' id='btnPrint'><span>인쇄</span></a>-->
				<!-- <a class='btn s5' id='btnCancle'><span>닫기</span></a> -->
			</div>
				<!--본문-->
			<iframe id="rexpert" src="" style="overflow:hidden; width:100%; height:800px;" frameborder="0"> </iframe>

		</div>

</body>
</html>