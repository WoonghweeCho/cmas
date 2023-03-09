<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp" %>
<html>
<head>
	<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
		<jsp:param value="DextDownloadMonitor" name="title"/>
		<jsp:param value="popup" name="type"/>
	</jsp:include>

<%
	String svrAddr = request.getServerName();
	int svrPort = request.getServerPort();
	String context = request.getContextPath();
	String centerUrl = "";

	if (svrPort == 80) {
		centerUrl = "http://" + svrAddr + context + "/";
	}
	else {
		centerUrl = "http://" + svrAddr + ":" + Integer.toString(svrPort) + context + "/";
	}
%>

	<script type="text/javascript">
	var gContextPath = "${contextPath}";
	$.jgrid.no_legacy_api = true;
	$.jgrid.useJSON = true;
	</script>

	<SCRIPT FOR="FileDownloadMonitor" Event="OnError(nCode, sMsg, sDetailMsg)" Language="javascript">
		OnErrorDownloadMonitor(nCode, sMsg, sDetailMsg);
	</SCRIPT>

	<script type="text/javascript" for="FileDownloadMonitor" event="OnCreationComplete()">
       	OnLoading();
    </script>

	<SCRIPT LANGUAGE="JavaScript">
		function OnErrorDownloadMonitor(nCode, sMsg, sDetailMsg)
		{
			alert(nCode);
			alert(sMsg);
			alert(sDetailMsg);
		}
		//파일 다운로드 매니저가 넘겨주는 아이템들을 저장한다
		function OnLoading() {
			document.all("FileDownloadMonitor").Items = opener.document.all("FileDownloadManager").Items;
		}
	</SCRIPT>

</head>
<body bottomMargin="0" leftMargin="0" topMargin="0" rightMargin="0">
	<h3>${parma.title }</h3>
    <OBJECT ID="FileDownloadMonitor"  height=420 width=445
		CodeBase = "<%=centerUrl%>common/install/download/DEXTUploadX.cab#version=3,2,10,0"
		CLASSID="CLSID:471678BB-F992-4BE6-9761-7767883E8619">
		<param name="EnableBehindDownload" value="TRUE" />
	</OBJECT>
</body>
</html>