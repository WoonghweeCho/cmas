<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="docfbaro.common.WebContext"%>
<%@ taglib prefix="c" uri="/c_rt" %>
<%@ taglib prefix="fmt" uri="/fmt" %>
<%@ taglib prefix="fn" uri="/fn" %>
<%@ taglib prefix="ut" uri="/ut" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" ></c:set>

<%
	String ua = WebContext.getRequest().getHeader("User-Agent");

	if ( ua != null && ua.indexOf("MSIE") > -1 ) {
		//if ( ua.indexOf("MSIE 10") < 0 ) {

			ua = "MSIE";

		//}
		//else {
		//	ua = "MB";
		//}
	} else {
		ua = "MB";
	}

	request.setAttribute("ua", ua);

%>
	<script language="javascript">
	var gv_DrmType = "${param.drmType}";
	var userAgent = "<%=ua%>";
	</script>
<c:choose>

	<c:when test="${ua == 'MSIE'}">

		<ut:script type="text/javascript" src="${contextPath}/common/js/comp/upload/fileuploadMSIE.js"></ut:script>

		<script type="text/javascript" FOR="FileUploadManager" EVENT="OnTransfer_Click()">
 			var winstyle="height=355,width=445, status=no,toolbar=no,menubar=no,location=no,resizable=yes";
 			window.open("${contextPath}/common/jsp/comp/fileupload/DextFileUploadMonitor.jsp", null, winstyle);
		</SCRIPT>

		<SCRIPT FOR="FileUploadManager" Event="OnError(nCode, sMsg, sDetailMsg)" LANGUAGE="javascript">
			if ( nCode == 20000119 ) {

			}
			else {
				var errMsg = "Error Code->"+nCode + "\r\n" +
				             "Error Message ->"+sMsg+"\r\n" +
				             "Detail Error Message ->"+sDetailMsg;
				gf_AlertMsg(errMsg);
			}
		</SCRIPT>

		<SCRIPT FOR="FileUploadManager" Event="OnFile_DoubleClick(nItemIndex)"
			language="javascript">
			// ### 20140703. 업로드화면에서도 더블클릭으로 다운로드 가능.
			//DRM 적용시 해제 : jsko
			if (!gf_IsNull(gv_DrmType) && gv_DrmType.toUpperCase() != "NONE") {
				gf_onFileDownload();
			} else {
				gf_onFileDownload();
				//document.all("FileDownloadManager").DownloadExecute(nItemIndex);
			}

			//document.all("FileDownloadManager").Execute();
		</SCRIPT>

		<SCRIPT FOR="FileDownloadManager" Event="OnError(nCode, sMsg, sDetailMsg)" language="javascript">
		var errMsg = "Error Code->"+nCode + "\r\n" +
        			 "Error Message ->"+sMsg+"\r\n" +
        			 "Detail Error Message ->"+sDetailMsg;
		gf_AlertMsg(errMsg);
		</SCRIPT>

		<SCRIPT FOR="FileDownloadManager" Event="OnFile_DoubleClick(nItemIndex)" language="javascript">

		//DRM 적용시 해제 : jsko
		if ( !gf_IsNull(gv_DrmType) && gv_DrmType.toUpperCase() != "NONE"  ) {
			gf_onFileDownload();
		}
		else {
			gf_onFileDownload();
		//document.all("FileDownloadManager").DownloadExecute(nItemIndex);
		}

		//document.all("FileDownloadManager").Execute();


		</SCRIPT>

		<script type="text/javascript" for="FileDownloadManager" event="OnCreationComplete()">
       		gf_Trace('filedownload manager is loaded ');
    	</script>


    	<script language='javascript'>
	    	function OpenDownloadMonitor() {
	    		var winstyle= "height=420,width=445, status=no,toolbar=no,menubar=no,location=no" ;
	    		window.open("${contextPath}/common/jsp/comp/fileupload/DextFileDownloadMonitor.jsp", null, winstyle);
	    	}
    	</script>

    	<SCRIPT FOR="FileUploadManager" Event="OnChangedStatus(nFileCount, nFileSize)" LANGUAGE="javascript">
        	OnChangedStatus(nFileCount, nFileSize);
		</SCRIPT>

		<SCRIPT LANGUAGE="JavaScript">
            function OnChangedStatus(nFileCount, nFileSize)
            {
            	// 파일이 추가된경우
            	if ( nFileCount > gv_FileCnt ) {
            		// file
            		gf_AddFileInfoGdsFile(gv_FileCnt, nFileCount);
            	}
            	else if ( nFileCount < gv_FileCnt )  {
					// 파일이 삭제 된경우
            		gf_DeleteFileInfoGdsFile();
            	}
            }
		</SCRIPT>

	</c:when>

    <c:otherwise>
		<ut:script type="text/javascript" src="${contextPath}/common/js/comp/upload/fileupload.js"></ut:script>
		<ut:link href="${contextPath}/common/css/fileupload.css" type="text/css" rel="stylesheet" />
    </c:otherwise>
</c:choose>
