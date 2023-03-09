<%@page import="com.dwenc.cmas.common.utils.ConfigUtil"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="java.net.InetAddress"%>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="java.io.InputStream,java.io.OutputStream,java.util.*,java.text.*,com.clipsoft.rextoolkit.*,com.clipsoft.rextoolkit.enumtype.*,com.clipsoft.rextoolkit.oof.*, com.clipsoft.rextoolkit.oof.enumtype.*,com.clipsoft.rextoolkit.parameter.*,com.clipsoft.rextoolkit.parameter.enumtype.* " %>
<%@ page import="docfbaro.iam.UserInfo"%>

<script language="JavaScript">
var rex_gsRexServiceRootURL = window.location.protocol + "//" + window.location.host + "/RexServer30/";
var rex_gsReportURL = rex_gsRexServiceRootURL + "rebfiles/";

<%
	Properties properties = ConfigUtil.getProperties();

	String rebUrl = "";
	String rexServiceRootUrl = "";
	String rebNm = "";
	String rexDb = "";
	String rexesp = "";
	String signDocTitle = "테스트 PDF 변환";
%>
	<%=rebUrl%> = rex_gsReportURL;
	<%=rexServiceRootUrl%> = rex_gsRexServiceRootURL;

<%

	RexSOPToolkit rexSOPToolkit = new RexSOPToolkit();

	Oof oof = new Oof();
	oof.setTitle(signDocTitle);
	oof.setEnableLog(true);
	oof.setEnableThread(true);


	XmlContent xmlContent = (XmlContent) ContentFactory.createContent(ContentType.XML);
	//xmlContent.setRoot(OofSpecialField.DATASET_XML_ROOT.getValue());
	xmlContent.setRoot("gubun/rpt1/rexdataset/rexrow");
	xmlContent.setPreservedWhitespace(true);
	xmlContent.setBindingMode(BindingModeType.NAME);

	/*
	CsvContent csvContent = (CsvContent) ContentFactory.createContent(ContentType.CSV);
	csvContent.setRowDelim("|#|");
	csvContent.setColDelim("|*|");
	csvContent.setDataSetDelim("|@|");
	csvContent.setEncoding(EncodingType.UTF_8);
    */

	// server ip address
    String serverIp = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

	HttpConnection httpConnection = (HttpConnection) ConnectionFactory.createConnection(ConnectionType.HTTP);
	httpConnection.setPath(serverIp + "/RexServer30/rexservice.jsp");
	//System.out.println(rexServiceRootUrl+"rexservice.jsp");
	httpConnection.setMethod("post");
	httpConnection.setNamespace("*");
	httpConnection.addHttpParam(new HttpParam("Q1SQL", OofSpecialField.AUTO.getValue()));
	httpConnection.addHttpParam(new HttpParam("OE", "None"));

	if(request.getParameter("rex_db") == null || request.getParameter("rex_db").length() == 0) {
		rexDb = "oracle1";
	}else{
		rexDb = request.getParameter("rex_db");
	}
	System.out.println("Rexpert DB :: " + rexDb);
	httpConnection.addHttpParam(new HttpParam("CN", rexDb));
	httpConnection.addHttpParam(new HttpParam("ID", "SDXML"));
	httpConnection.addHttpParam(new HttpParam("PE", "FALSE"));
	httpConnection.addHttpParam(new HttpParam("QC", "1"));
	httpConnection.addHttpParam(new HttpParam("OT", "DataOnly"));
	httpConnection.addHttpParam(new HttpParam("Q1Type", "SQL"));
	httpConnection.addContent(xmlContent);

	com.clipsoft.rextoolkit.oof.File file = new com.clipsoft.rextoolkit.oof.File();
	file.setType(FileType.REB);
	file.setPath(serverIp + "/RexServer30/rebfiles/"+request.getParameter("rex_rptname")+".reb");
	System.out.println(serverIp + "/RexServer30/rebfiles/"+request.getParameter("rex_rptname"));
	file.setShowParameterDialog(true);

	oof.addFile(file);
	oof.addConnection(httpConnection);
	Enumeration params = request.getParameterNames();

	while (params.hasMoreElements()) {
		String name = (String)params.nextElement();
		String value = request.getParameter(name);
		System.out.println(name+":"+value);

		oof.addField(new Field(name, value));
	}

	rexSOPToolkit.setRexEspUrl(properties.getProperty("dwe.serverInfo.rexesp.url") + "/RexESP/RexSOPServer");
	rexSOPToolkit.setSubmitEncodingType(SubmitEncodingType.EUC_KR);

	response.setHeader("Context-Type","application/pdf");
	PdfExportParameter pdfExportParameter = ParameterFactory.createPdfExportParameter();
	pdfExportParameter.setOof(oof.createOof());
	pdfExportParameter.setOofProcessType(OofProcessType.PATH);
	pdfExportParameter.setFilePath("c:/test/");
	pdfExportParameter.setFileName("xmlmemo_"+java.util.Calendar.getInstance().getTimeInMillis()+".pdf");
	pdfExportParameter.setDocumentTitle(signDocTitle);

	RexToolkitResult result = rexSOPToolkit.export(pdfExportParameter);

	HashMap info = result.getInfoMap();

	InputStream is = rexSOPToolkit.fileStream(
		(String)info.get("filePath"),
		(String)info.get("fileName"),
		ExportType.PDF.getValue(),
		false
	);

	RexToolkitUtility rexToolkitUtility = new RexToolkitUtility();

	out.clear();
	out = pageContext.pushBody();

	rexToolkitUtility.setContentType(ExportType.PDF);
	rexToolkitUtility.responseOutputStream(response, is);


%>
</script>
