<%@page import="docfbaro.common.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@page import="java.util.*" %>
<%@page import="docfbaro.iam.UserInfo"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>simple</title>
<script language="javascript" src="./rexscript/rexpert.js"></script>
<script language="javascript" src="./rexscript/rexpert_properties.js"></script>
<script language="javascript" src="./rexscript/rexpert_properties.jsp"></script>

</head>


<script language="JavaScript">
var finishexportCallback = null;
function finishexport(oRexCtl, sEvent, oArgs){
	if(finishexportCallback != null){
		finishexportCallback(oRexCtl, sEvent, oArgs)
	}
}
function hyperlinkclicked(oRexCtl, sEvent, oArgs){
	window.open(oArgs.Path);
}

var RexCtl= null;
var zoomSize = "0";
var zoomCnt = "4";
var params = {};

function fnopen() {
	var oReport = GetfnParamSet("0");
	//oReport.rptname = "testserver";
	<%
	Enumeration params = request.getParameterNames();
	while (params.hasMoreElements()) {
		String name = (String)params.nextElement();
		String value = request.getParameter(name);
%>
		params["<%=name%>"] = "<%=value%>";
<%
	}
%>
	if(typeof(params.type) == "undefined"){
	}else if(params.type == "file"){
		oReport.path = params.path;
		oReport.xpath = params.xpath;
	}

	if(typeof(params.rex_db) != "undefined"){
		oReport.connectname= params.rex_db;
	}

	if(typeof(params.cons) != "undefined"){
		params.cons = eval(params.cons);
		for(var idx in params.cons){
			var con = params.cons[idx];
			oReport.con(con.name).type = con.type;
			oReport.con(con.name).datatype = con.datatype;
			if(typeof(con.connectname) != "undefined")
				oReport.con(con.name).connectname = con.connectname;
			oReport.con(con.name).namespace = con.namespace;	// 생략하면, sub id를 사용

			alert(con.type);
			if(con.type == "memo")
				oReport.con(con.name).data = window.clipboardData.getData("Text");
		}
	}else{
		//oReport.data = window.clipboardData.getData("Text");
	}

	oReport.datatype = "xml";
	oReport.rptname = params.rex_rptname;

for(key in params){
	if(key == "cons") continue;
	oReport.param(key).value = params[key];
}

	//oReport.param("t1").value = "bbb";
	//oReport.param("t2").value = "ccc";
	rex_gsPluginTypeWeb = "crypto.clipsoft";
	rex_gsPluginWebParam = {"name": "name",
                                     "common-enable-encode": "1",
                                     "common-enable-decode": "0",
                                     "common-delimiter": "|!|",
                                     "common-encoding": "utf-8",
                                     "common-enable-log": "0",
                                     "common-log-filename": "c:\test2.log"};
	oReport.event.init = fnReportEvent;
	oReport.event.finishdocument = finishDocument;
	oReport.event.movepage = movepage;
	oReport.event.finishexport = finishexport;
	oReport.event.hyperlinkclicked = hyperlinkclicked;
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

		<%if(StringUtil.getText(request.getParameter("direct_print"), "false").equals("true")){%>
		setTimeout(function(){RexCtl.Print(true, 1, -1, 1, "");},100);
		<%}%>

		RexCtl.SetCSS("appearance.toolbar.visible=1");
		RexCtl.UpdateCSS();
	}
}
function finishDocument(oRexCtl, sEvent, oArgs) {
	if (sEvent == "finishdocument") {
		RexCtl = oRexCtl;
		if(params["programCode"] == "ACPF010001"){
			RexCtl.Zoom("80");
		}else{
			RexCtl.Zoom("114");
		}

		if(typeof(frameElement) != "undefined" && typeof(frameElement.getAttribute("hiddenLoadFunc")) != "undefined"){
			var hiddenLoadFunc = parent[frameElement.getAttribute("hiddenLoadFunc")];
			var eventNm = "mousewheel";
			hiddenLoadFunc("finish", RexCtl.CurrentPage(), RexCtl.TotalPageCount());


			var moveFunc = function(e){hiddenLoadFunc("move", RexCtl.CurrentPage());};
			if (RexCtl.addEventListener) {
				RexCtl.addEventListener(eventNm, moveFunc);
			} else if (frameElement.attachEvent) {
				RexCtl.attachEvent("on" + eventNm, moveFunc);
			} else if (frameElement["on" + eventNm]) {
				RexCtl["on" + eventNm] = moveFunc;
			}
		}
	}
}
function movepage(oRexCtl, sEvent, oArgs) {
	alert("movepage");
	if (sEvent == "movepage") {

		if(typeof(frameElement) != "undefined" && typeof(frameElement.getAttribute("hiddenLoadFunc")) != "undefined")
			parent[frameElement.getAttribute("hiddenLoadFunc")]("move", oArgs);
	}
}

function fnWholepage(){
//	RexCtl.Export(true, "ppt", "", 1, -1, ""); // .Zoom("100%");
	RexCtl.Zoom("wholepage");
}

function fnFileDownload(callback, fileType, path, startPage, endPage){
	finishexportCallback = callback;
	fileType = typeof(fileType) == "undefined"?"pdf": fileType;
	startPage = typeof(startPage) == "undefined"? 1: startPage;
	endPage = typeof(endPage) == "undefined"? -1: endPage;

	if(typeof(path) == "undefined"){
		alert("파일 경로를 지정하시오");
	}

	RexCtl.Export(false, fileType, path, startPage, endPage, "");
}

function fnBtnEvent(strBtnId, strFileNm) {
	//alert("도착");
	//alert("값은? : " + strBtnId);
	switch(strBtnId) {
		case "save" :
			//alert("저장");
			//RexCtl.Export(false, "xls", "c:\\test.xls", 1, -1, "");
			//RexCtl.Export(false, "hwp", "c:\\test.hwp", 1, -1, "");

			RexCtl.Export(true, "ppt", "", 1, -1, "");
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
		case "XLS" :
			//alert("XLS");
			var saveUrl = "c:\\temp\\"+strFileNm+".xls";
			//RexCtl.Export(true, "xls", "c:\\{%file.title%}.xls", 1, -1, "");
			RexCtl.Export(false, "xls", saveUrl, 1, -1, "");

			break;
	}
}

// 전자증빙 호출을 위한 함수 이니 한번더 삭제하면 죽여버리갓서
function fn_CallEproof(sUrl) {
	setTimeout("window.open('"+sUrl+ "','','toolbar=no,resizable=no,status=no,scrollbars=no,left=0,top=0,width=1280,height=840')" ,1);
}
</script>
<body onload="fnopen();" leftmargin=0 topmargin=0 scroll=no style="width:100%; height:100%" >
	<iframe id="ifrmRexPreview1" name="ifrmRexPreview1" src="" width="100%" height="100%" frameborder="0"></iframe>
</body>
<script type="text/javascript">
	rex_writeRexCtl("RexCtl");
</script>
</html>