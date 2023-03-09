<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="docfbaro.common.ServletUtil,
				 docfbaro.common.ObjUtil" %>

<%
//String siteCd =  ServletUtil.getData( request, "siteCd" );
String siteCd =  request.getParameter("siteCd");
String gjahr =  request.getParameter("gjahr");
String monat =  request.getParameter("monat");
%>
<html>
<head>
	<!-- Common Library 처리 -->
	<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
		<jsp:param value="" name="title"/>
		<jsp:param value="proxy" name="popupType"/>
	</jsp:include>

	<!-- JavaScript Logic 처리 -->
	<script type="text/javascript">
		var dsList = new DataSet();

		$(function() {
			f_excuteSap();
		});


		function f_excuteSap(){
			var datas =	{
				siteCd:"<%=siteCd%>",
				gjahr:"<%=gjahr%>",
				monat:"<%=monat%>"
			};

		    gf_Transaction("SELECT_SAP", "/acws/aca/retrieveExeSap.xpl", datas, {}, "f_Callback", false);
		}


		function f_Callback(strSvcId, obj, resultData){
			  // transaction의 정상 처리 여부를 판단한다.
			  if (!gf_ChkTransaction(strSvcId, resultData, true )) {
				  return;
			  }

			  switch(strSvcId) {
			  	case "SELECT_SAP" :
			  		dsList.setAllData(resultData.output1);
			  		fnRun();
			  		self.close();
			  		break;

			  	default :
			  		break;
			  }
		}


		function fnRun() {
		    var objWSH = new ActiveXObject("WScript.Shell");
		    objWSH.Run("sapshcut.exe "+dsList.get(0, "arg")  , 1, "false");
		}

	</script>

	<link href="${contextPath}/css/base.css" type="text/css" rel="stylesheet" />

</head>