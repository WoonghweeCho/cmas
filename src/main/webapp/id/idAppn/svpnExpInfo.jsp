<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="특별ID-SVPN 종료 안내 메일" name="title" />
	<jsp:param value="proxy" name="popupType"/>
</jsp:include>

<!-- HTTP Request Parameter 처리 -->
<script type="text/javascript">

</script>

<link href="${contextPath}/common/css/base.css" type="text/css"
	rel="stylesheet" />
</head>

<body>
	<!--right-->
	<div id="container">
		<!--CONTENTS-->
		<div id="contents">
			<br><br>
			특별ID-SVPN 종료 안내 메일발송 배치 프로그램(To. 특별ID 시스템에 등록된 SVPN 담당자)<br><br>
			개발 : http://icmsdev.daewooenc.com:8081/cbat/coa/coa02/tempid/svpnExpInfo.xpl<br><br>
			운영 : http://iworks.daewooenc.com/cbat/coa/coa02/tempid/svpnExpInfo.xpl<br><br>
		</div>
	</div>
	<!--//right-->

</body>
</html>