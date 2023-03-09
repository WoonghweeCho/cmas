<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp" %>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=8" />
	<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
		<jsp:param value="DextUploadMonitor" name="title"/>
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
	//var datas = ${param.datas};	// 파라메터로 인코딩 된 데이터
	</script>

	<script FOR="FileUploadMonitor" Event="OnError(nCode, sMsg, sDetailMsg)" TYPE="text/javascript">
		OnFileMonitorError(nCode, sMsg, sDetailMsg);
	</SCRIPT>

	<SCRIPT FOR="FileUploadMonitor" Event="OnCreationComplete()" TYPE="text/javascript">
		OnLoading();
	</SCRIPT >

	<SCRIPT LANGUAGE="javascript" for="FileUploadMonitor" event="OnTransferComplete()">
		// 파일 전송이 끝난후 이벤트 opener의 upload callback 함수를 호출하여 bizCallback 을 수행하도록 처리한다.
    	opener.gf_UploadCallback();
	</SCRIPT>


	<SCRIPT LANGUAGE="JavaScript">

	function OnFileMonitorError(nCode, sMsg, sDetailMsg) {
	//	alert(nCode);
	    alert(sMsg);
	//	alert(sDetailMsg);
		self.close();
	}

	function OnLoading() {
		var serverUrl = '<%=centerUrl%>';

		//serverUrl = serverUrl.simpleReplace('localhost','localcmas.daewooenc.com');
		document.all("FileUploadMonitor").UploadURL = serverUrl+'co/common/file/uploadWebFile.xpl';
		document.all("FileUploadMonitor").Items = opener.document.all("FileUploadManager").Items;
		document.all("FileUploadMonitor").Properties = opener.document.all("FileUploadManager").Properties;
		document.all("FileUploadMonitor").CodePage = 65001;
		document.all("FileUploadMonitor").CheckAutoCloseWindow = true;
		document.all("FileUploadMonitor").EnableAutoCloseWindow = false;

		if (document.all("FileUploadMonitor").Count == 0) {
			document.all("FileUploadMonitor").EnableEmptyFileUpload = true;
		}
		//document.all("FileUploadMonitor").Transfer();



	}


    </script>

</head>
<body bottomMargin="0" leftMargin="0" topMargin="0" rightMargin="0">
	<h3>${parma.title }</h3>
	<OBJECT id=FileUploadMonitor height="335" width="445"
	        classid="CLSID:96A93E40-E5F8-497A-B029-8D8156DE09C5"
	        CodeBase="<%=centerUrl%>common/install/download/DEXTUploadX.cab#version=3,2,10,0" VIEWASTEXT>
	        <PARAM NAME="EnableAddFileButton" VALUE="FALSE">
			<PARAM NAME="EnableAddFolderButton" VALUE="FALSE">
			<PARAM NAME="EnableDeleteButton" VALUE="FALSE">

	</OBJECT><br>
	&nbsp;&nbsp;<span style="color: red"><strong>※전송버튼을 눌러 주시기 바랍니다.</strong></span>
</body>
</html>