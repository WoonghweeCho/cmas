<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="docfbaro.common.WebContext"%>
<%

    String referer = request.getHeader("referer");
	String svrAddr = request.getServerName();
	int svrPort = request.getServerPort();
	String context = request.getContextPath();
	String svcUrl = "http://" + svrAddr + ":" + Integer.toString(svrPort) + context;
	request.setAttribute("svcUrl", svcUrl);
%>

<%@ taglib prefix="c" uri="/c_rt" %>
<%@ taglib prefix="fmt" uri="/fmt" %>
<%@ taglib prefix="fn" uri="/fn" %>
<%@ taglib prefix="ut" uri="/ut" %>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" ></c:set>
	<c:set var="fileUp" value="FileUploadMonitor${param.objId}" ></c:set>

	<ut:script type="text/javascript" src="${svcUrl}/common/js/comp/upload/DextUpload4Rexpert.js?version=1"></ut:script>

	<script FOR="${fileUp}" Event="OnError(nCode, sMsg, sDetailMsg)" TYPE="text/javascript">
		${fileUp}OnFileMonitorError(nCode, sMsg, sDetailMsg);
	</SCRIPT>

	<SCRIPT FOR="${fileUp}" Event="OnCreationComplete()" TYPE="text/javascript">
		${fileUp}OnLoading();
	</SCRIPT >

	<SCRIPT LANGUAGE="javascript" for="${fileUp}" event="OnTransferComplete()">
		// 파일 전송이 끝난후 이벤트 opener의 upload callback 함수를 호출하여 bizCallback 을 수행하도록 처리한다.
    	${param.callbackFunc}();
	</SCRIPT>


	<SCRIPT LANGUAGE="JavaScript">

	function ${fileUp}OnFileMonitorError(nCode, sMsg, sDetailMsg) {
		alert(nCode);
		alert(sMsg);
		alert(sDetailMsg);
	}

	function ${fileUp}OnLoading() {
		document.all("${fileUp}").UploadURL = "${svcUrl}/co/common/file/uploadWebFile.xpl";
		document.all("${fileUp}").CodePage = 65001;

		if (document.all("${fileUp}").Count == 0) {
			document.all("${fileUp}").EnableEmptyFileUpload = true;
		}
	}
    </script>