<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@page import="java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>simple</title>
<script language="javascript" src="./rexscript/rexpert.js"></script>
<script language="javascript" src="./rexscript/rexpert_properties.js"></script>
<script language="javascript" src="./rexscript/rexpert_properties.jsp"></script>

</head>
<script language="JavaScript">
function finishexport(oRexCtl, sEvent, oArgs){
	try{
		setTimeout(function(){document.title = "FILE_EXPORT|"+oArgs.filename; }, 100);
	}catch(e){
		alert(e);
	}
}

function hyperlinkclicked(oRexCtl, sEvent, oArgs){
	window.open(oArgs.Path);
}


var RexCtl= null;
var zoomSize = "0";
var zoomCnt = "4";

function fnopen() {
	var oReport = GetfnParamSet("0");
	//oReport.rptname = "testserver";
	<%
		Enumeration params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String name = (String)params.nextElement();
			String value = request.getParameter(name);
			if( name.equals("rex_rptname") ){
	%>
			oReport.rptname = "<%=value%>";
			oReport.type = "memo";
			oReport.datatype = "xml";
	<%
			}
	%>
		oReport.param("<%=name%>").value = "<%=value%>";
	<%
		}
	%>
	oReport.data = window.clipboardData.getData("Text");

	//oReport.param("t1").value = "bbb";
	//oReport.param("t2").value = "ccc";
	oReport.event.init = fnReportEvent;
	oReport.event.finishexport = finishexport;
	oReport.iframe(ifrmRexPreview1);
	//oReport.save(true, "pdf", "c:\\test.pdf", 1, -1, "");
	//oReport.open();
	//rptname=test&t1=bbb&t2=ccc
}
//event handler
function fnReportEvent(oRexCtl, sEvent, oArgs) {
	if (sEvent == "init") {
		RexCtl = oRexCtl;
		document.title = "REX_LOAD";
	}
}

function fnWholepage(){
//	RexCtl.Export(true, "ppt", "", 1, -1, ""); // .Zoom("100%");
	RexCtl.Zoom("wholepage");
}

function fnFileDownload(fileType, path, startPage, endPage){
	fileType = typeof(fileType) == "undefined"?"pdf": fileType;
	startPage = typeof(startPage) == "undefined"? 1: startPage;
	endPage = typeof(endPage) == "undefined"? -1: endPage;

	if(typeof(path) == "undefined"){
		alert("파일 경로를 지정하시오");
	}

	RexCtl.Export(false, fileType, path, startPage, endPage, "");
}


function fnBtnEvent(strBtnId) {
	//alert("도착");
	//alert("값은? : " + strBtnId);
	switch(strBtnId) {
		case "save" :
			//alert("저장");
			//RexCtl.Export(false, "xls", "c:\\test.xls", 1, -1, "");
			//RexCtl.Export(false, "hwp", "c:\\test.hwp", 1, -1, "");

			RexCtl.Export(true, "ppt", "noname.ppt", 1, -1, "");
			/*
			RexCtl.Export(false, "jpg", "c:\\test.jpg", 1, -1, "");
			RexCtl.Export(false, "hml", "c:\\test.hml", 1, -1, "");
			RexCtl.Export(false, "rtf", "c:\\test.rtf", 1, -1, "");
			RexCtl.Export(false, "htm", "c:\\test.htm", 1, -1, "");
			RexCtl.Export(false, "txt", "c:\\test.txt", 1, -1, "");
			RexCtl.Export(false, "tif", "c:\\test.tif", 1, -1, "");
			*/
			break;
		case "print" :
			//alert("인쇄");
			RexCtl.Print(true, 1, -1, 1, "");
			break;
		case "first" :
			//alert("처음으로");
			RexCtl.MoveFirst();
			break;
		case "prev" :
			//alert("앞장");
			RexCtl.MovePrev();
			break;
		case "next" :
			//alert("다음장");
			RexCtl.MoveNext();
			break;
		case "last" :
			//alert("마지막장");
			RexCtl.MoveLast();
			break;
		case "zoomIn" :
			//alert("확대!!" + "현재사이즈:" + parseInt(zoomSize)+ "계수:" + parseInt(zoomCnt));
			if ( parseInt(zoomCnt) < 10 )
			{
				zoomCnt = parseInt(zoomCnt) + 1;
				zoomSize = parseInt(zoomCnt) * 25;
				RexCtl.Zoom(zoomSize);
			}
			break;
		case "zoomOut" :
			//alert("축소!!" + "현재사이즈:" + parseInt(zoomSize)+ "계수:" + parseInt(zoomCnt));
			if ( parseInt(zoomCnt) > 2 )
			{
				zoomCnt = parseInt(zoomCnt) - 1;
				zoomSize = parseInt(zoomCnt) * 25;
				RexCtl.Zoom(zoomSize);
			}
			break;
		case "PDF" :
			//alert("PDF출력");
			RexCtl.Export(true, "pdf", "c:\\{file.title}.pdf", 1, -1, "");
			//RexCtl.Export(false, "pdf", "c:\\test.pdf", 1, -1, "");
			break;
	}
}

//전자증빙 호출을 위한 함수 이니 한번더 삭제하면 죽여버리갓서
function fn_CallEproof(sUrl) {
	setTimeout("window.open('"+sUrl+ "','','toolbar=no,resizable=no,status=no,scrollbars=no,left=0,top=0,width=1280,height=840')" ,1);
}

</script>
<body onload="fnopen()" leftmargin=0 topmargin=0 scroll=no>
	<iframe id="ifrmRexPreview1" name="ifrmRexPreview1" src="" width="100%" height="100%" frameborder="0"></iframe>
</body>
<script type="text/javascript">
	rex_writeRexCtl("RexCtl");
</script>
</html>