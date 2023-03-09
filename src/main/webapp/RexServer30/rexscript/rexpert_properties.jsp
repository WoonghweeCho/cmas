<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

rex_gsRexServiceRootURL = "<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() %>/RexServer30/";
rex_gsPreViewURL = rex_gsRexServiceRootURL + "rexpreview.jsp";
rex_gsReportURL = rex_gsRexServiceRootURL + "rebfiles/";
rex_gsRptServiceURL = rex_gsRexServiceRootURL + "rexservice.jsp";
// Export Service URL
rex_gsRptExportServiceURL = rex_gsRexServiceRootURL + "exportservice.jsp";
// Export URL
rex_gsRptExportURL = rex_gsRexServiceRootURL + "ReqExport.jsp";

rex_gsRptServiceURL_RexServer25 = rex_gsRexServiceRootURL + "rexservice25.jsp";