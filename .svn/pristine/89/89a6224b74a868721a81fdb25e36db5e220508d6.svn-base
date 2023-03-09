<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<!-- Common Library 처리 -->
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="종료 안내 메일" name="title" />
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
			개발 : http://icmsdev.daewooenc.com:8081/cbat/cmb/cmb00/cmb0001/sendmail.xpl<br>
			운영 : http://iworks.daewooenc.com/cbat/cmb/cmb00/cmb0001/sendmail.xpl<br><br>

			1. 특별ID-SVPN 종료 안내 메일발송 배치 프로그램(To. 특별ID 시스템에 등록된 SVPN 담당자)<br>
			&nbsp;&nbsp;&nbsp;종료일 = SYSDATE-1 인 사용자<br><br>

			2. 종료 안내 메일발송 배치 프로그램<br>
			&nbsp;&nbsp;&nbsp;종료일-30 = SYSDATE 인 사용자<br>
			&nbsp;&nbsp;&nbsp;종료일-10 = SYSDATE 인 사용자<br>
			&nbsp;&nbsp;&nbsp;종료일-5 = SYSDATE 인 사용자<br>
			특별ID본인 및 신청자에게 발송<br>
		</div>
	</div>
	<!--//right-->

</body>
</html>