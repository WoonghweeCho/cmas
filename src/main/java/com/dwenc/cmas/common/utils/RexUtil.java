package com.dwenc.cmas.common.utils;

import com.dwenc.cmas.common.utils.ConfigUtil;
import java.io.InputStream;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import docfbaro.common.WebContext;

import com.clipsoft.rextoolkit.*;
import com.clipsoft.rextoolkit.enumtype.*;
import com.clipsoft.rextoolkit.oof.*;
import com.clipsoft.rextoolkit.oof.enumtype.*;
import com.clipsoft.rextoolkit.parameter.*;
import com.clipsoft.rextoolkit.parameter.enumtype.*;


@Service
public class RexUtil {
	@Async
	public void exportPDF(Map<String, Object> map){
		HttpServletRequest req = WebContext.getRequest();
		HttpServletResponse res = WebContext.getResponse();
		System.out.println("pdf 변환 모듈 시작");
		// server ip address
		String serverType = req.getServerName();
		String serverIp = "";
		if 	(serverType.equals("iworks.daewooenc.com")
			|| serverType.equals("icmsdev.daewooenc.com"))
			serverIp = "http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();	// 운영 또는 개발
		else serverIp = "http://172.21.29.210:8081"; // 로컬
//		else serverIp = "http://172.21.18.201:8080/cmas"; // 로컬


//		Properties properties = ConfigUtil.getProperties();

		String rexDb = "";
		String signDocTitle = "테스트 PDF 변환";
//		String pdfFileNm = "";

		RexSOPToolkit rexSOPToolkit = new RexSOPToolkit();

		Oof oof = new Oof();
		oof.setTitle(signDocTitle);
		oof.setEnableLog(true);
		oof.setEnableThread(true);


		XmlContent xmlContent = (XmlContent) ContentFactory.createContent(ContentType.XML);
		xmlContent.setRoot("gubun/rpt1/rexdataset/rexrow");
		xmlContent.setPreservedWhitespace(true);
		xmlContent.setBindingMode(BindingModeType.NAME);

		HttpConnection httpConnection = (HttpConnection) ConnectionFactory.createConnection(ConnectionType.HTTP);
		httpConnection.setPath(serverIp + "/RexServer30/rexservice.jsp");
		httpConnection.setMethod("post");
		httpConnection.setNamespace("*");
		httpConnection.addHttpParam(new HttpParam("Q1SQL", OofSpecialField.AUTO.getValue()));
		httpConnection.addHttpParam(new HttpParam("OE", "None"));

		if(req.getParameter("rex_db") == null || req.getParameter("rex_db").length() == 0) {
			rexDb = "oracle1";
		}else{
			rexDb = (String)map.get("rex_db");
		}
		System.out.println("Rexpert DB :: " + rexDb);
		httpConnection.addHttpParam(new HttpParam("CN", rexDb));
		httpConnection.addHttpParam(new HttpParam("ID", "SDXML"));
		httpConnection.addHttpParam(new HttpParam("PE", "FALSE"));
		httpConnection.addHttpParam(new HttpParam("QC", "1"));
		httpConnection.addHttpParam(new HttpParam("OT", "DataOnly"));
		httpConnection.addHttpParam(new HttpParam("Q1Type", "SQL"));
		httpConnection.addContent(xmlContent);

		System.out.println("line : 81");

		com.clipsoft.rextoolkit.oof.File file = new com.clipsoft.rextoolkit.oof.File();
		file.setType(FileType.REB);
		file.setPath(serverIp + "/RexServer30/rebfiles/"+ map.get("rex_rptname") +".reb");
		System.out.println(serverIp);
		file.setShowParameterDialog(true);

		oof.addFile(file);
		oof.addConnection(httpConnection);

		Set<String> keys = map.keySet();
		for (String key : keys) {
			System.out.println(key + " : " + (String)map.get(key));
		    oof.addField(new Field(key, (String)map.get(key)));
		}

		System.out.println("line : 98");
//		Enumeration params = req.getParameterNames();

//		while (params.hasMoreElements()) {
//			String name = (String)params.nextElement();
//			String value = req.getParameter(name);
//			System.out.println(name+":"+value);
//
//			oof.addField(new Field(name, value));
//		}

		rexSOPToolkit.setRexEspUrl("http://172.20.1.78:8080" + "/RexESP/RexSOPServer");
		rexSOPToolkit.setSubmitEncodingType(SubmitEncodingType.EUC_KR);

		res.setHeader("Context-Type","application/pdf");
		PdfExportParameter pdfExportParameter = ParameterFactory.createPdfExportParameter();
		pdfExportParameter.setOof(oof.createOof());
		pdfExportParameter.setOofProcessType(OofProcessType.PATH);
		pdfExportParameter.setFilePath("c:/cmasEvidence/");

		System.out.println("line : 118");

//		pdfExportParameter.setFileName("xmlmemo_"+java.util.Calendar.getInstance().getTimeInMillis()+".pdf");
		pdfExportParameter.setFileName((String)map.get("pdf_file_nm"));
		pdfExportParameter.setDocumentTitle(signDocTitle);

		RexToolkitResult result = rexSOPToolkit.export(pdfExportParameter);

		HashMap info = result.getInfoMap();

		InputStream is = rexSOPToolkit.fileStream(
			(String)info.get("filePath"),
			(String)info.get("fileName"),
			ExportType.PDF.getValue(),
			false
		);
		System.out.println("pdf 변환 모듈 끝");

//		RexToolkitUtility rexToolkitUtility = new RexToolkitUtility();
//
//		out.clear();
//		out = pageContext.pushBody();
//
//		rexToolkitUtility.setContentType(ExportType.PDF);
//		rexToolkitUtility.responseOutputStream(res, is);
	}
}
