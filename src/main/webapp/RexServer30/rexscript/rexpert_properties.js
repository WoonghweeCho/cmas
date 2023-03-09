// repert.js info
// version : rexpert 3.0
// date : 2010-04-15
//

//Rexpert context root url & etc
//rex_gsRexServiceRootURL = "http://" + window.location.host + "/RexServer30/";
rex_gsRexServiceRootURL = window.location.protocol + "//" + window.location.host + "/RexServer30/";
//rex_gsRexServiceRootURL = "http://localhost:8080/RexServer30/RexServer30/";

rex_gsPreViewURL = rex_gsRexServiceRootURL + "rexpreview.jsp";
rex_gsReportURL = rex_gsRexServiceRootURL + "rebfiles/";
rex_gsRptServiceURL = rex_gsRexServiceRootURL + "rexservice.jsp";

// Export Service URL
rex_gsRptExportServiceURL = rex_gsRexServiceRootURL + "exportservice.jsp";

// Export URL
rex_gsRptExportURL = rex_gsRexServiceRootURL + "ReqExport.jsp";

//default DBconnection
rex_gsUserService = "oracle1";

//viewer Version
rex_viewer_version = "1,0,0,306";
rex_viewer_install = "EACH";	// EACH, ONCE, NONE

//CSV
rex_gsCsvSeparatorColumn = "|*|";
rex_gsCsvSeparatorRow = "|#|";
rex_gsCsvSeparatorDataSet = "|@|";
rex_gsCsvEncoding = "UTF-8";

//XML
rex_gsXPath = "gubun/rpt1/rexdataset/rexrow";

//Rexpert Web Viewer Windows Size
rex_gsPreViewFeatures = "center=yes,scrollbars=no,status=no,toolbar=no,resizable=1,location=no,menu=no,width=825,height=600";
rex_gsPreViewFeaturesModal = "center:yes;resizable:no;scroll:no;status:no;dialogWidth:825px;dialogHeight:600px";

//Language information
rex_gsViewerLanguage = "ko";

// RexServer 2.5 compatible
rex_gsServerVersion = "3.0"	// 2.5, 3.0
//rex_gsServerVersion = "2.5"	// 2.5, 3.0

rex_gsRptServiceURL_RexServer25 = rex_gsRexServiceRootURL + "rexservice25.jsp";

rex_gsCsvSeparatorColumn_RexServer25 = "|*|";
rex_gsCsvSeparatorRow_RexServer25 = "|#|";
rex_gsCsvSeparatorDataSet_RexServer25 = "|@|";
rex_gsCsvEncoding_RexServer25 = "utf-8";
rex_gsXPath_RexServer25 = "root/main/rpt1/rexdataset/rexrow";

//plugin - webcrypto
/*
rex_gsPluginTypeWeb = "crypto.clipsoft";
rex_gsPluginWebParam = {"name": "name",
						"common-enable-encode": "1",
						"common-enable-decode": "0",
						"common-delimiter": "|!|",
						"common-encoding": "utf-8",
						"common-enable-log": "0",
						"common-log-filename": "c:\test2.log"};

rex_gsPluginTypeWeb = "crypto.xecureweb";
rex_gsPluginWebParam = {"name": "name",
						"common-enable-encode": "1",
						"common-enable-decode": "0",
						"common-delimiter": "|!|",
						"common-encoding": "utf-8",
						"common-enable-log": "0",
						"common-log-filename": "c:\test2.log",
						"xecureweb-gateaddr": "10.14.57.12:443:8080",
						"xecureweb-authtype": "",
						"xecureweb-mehtod": "POST"};
*/

// plugin - DRM
/*
rex_gsPluginType = "markany";
rex_gsPluginParam = {"datapath": rex_gsRexServiceRootURL + "/plugin/markany/MaFpsCommon.jsp",
					"datafilename": "MaPrintInfoCUSTRP.dat",
					"enablecapture": "0",
					"enablespool": "0",
					"enableprinter": "0",
					"printeroption": "3",
					"imagesaferoption": "0"};
*/
/*
rex_gsPluginType = "bcqure";
rex_gsPluginParam = {"initpath": rex_gsRexServiceRootURL +  "/plugin/bcqre/prnInit/",
					"datapath": rex_gsRexServiceRootURL +  "/plugin/bcqre/bcqre.server.jsp",
					"docnumber": "ksfc",
					"docname": "ksfc",
					"barcodewidth": "600",
					"barcodeminheight": "50",
					"barcodemaxheight": "200"};
*/

