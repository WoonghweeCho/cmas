<%@page import="java.util.*,java.text.*" %><%
	
	// rexpert.properties.dir
	//System.out.println("test");
	// application.getRealPath("");
	//System.setProperty("rexpert.properties.dir", "/inspsappl/web/rexpert/WEB-INF/classes");
	//System.setProperty("rexpert.properties.dir", "D:/rexpert30/RexServer30/WEB-INF/classes");

	
	request.setCharacterEncoding("UTF-8");
 	//String srequestEncoding = config.getInitParameter("requestEncoding");
 	
 	//Date  firstDt = new Date();
 	//SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
 	//System.out.println("firstDt : " + sdf.format(firstDt));


	String designtype = (request.getParameter("designtype") == null ? "" : request.getParameter("designtype"));

	String sDataType = "";
	sDataType = (request.getParameter("datatype") == null ? "" : request.getParameter("datatype"));
	
	//System.out.println(request.getParameter("SAPUSERID"));
	//System.out.println(request.getParameter("other"));

	if (designtype.equals("service"))
	{
		response.setContentType("text/xml;charset=UTF-8");
		RexService.CRexDesign oRexDesign = new RexService.CRexDesign(request, response, application);
		oRexDesign.getServiceList();
		//out.print(oRexDesign.getServiceList());
	} else if (designtype.equals("schema"))
	{
		response.setContentType("text/xml;charset=UTF-8");
		RexService.CRexDesign oRexDesign = new RexService.CRexDesign(request, response, application);
		oRexDesign.getSchemaList();
		//out.print(oRexDesign.getTableList());
	} else if (designtype.equals("table"))
	{
		response.setContentType("text/xml;charset=UTF-8");
		RexService.CRexDesign oRexDesign = new RexService.CRexDesign(request, response, application);
		oRexDesign.getTableList();
		//out.print(oRexDesign.getTableList());
	} else if (designtype.equals("field"))
	{
		response.setContentType("text/xml;charset=UTF-8");
		RexService.CRexDesign oRexDesign = new RexService.CRexDesign(request, response, application);
		oRexDesign.getFieldList();
		//out.print(oRexDesign.getFieldList());
	} else if (designtype.equals("execfield"))
	{
		response.setContentType("text/xml;charset=UTF-8");

		RexService.CRexDesign oRexDesign = new RexService.CRexDesign(request, response, application);

		oRexDesign.getExecFieldList();
		//out.print(oRexDesign.getExecFieldList());
	} else if (designtype.equals("data"))
	{
		/*
		if (sDataType.equals("CSV")) {
			response.setContentType("text/html;charset=UTF-8");
		} else {    // XML
			response.setContentType("text/xml;charset=UTF-8");
			//response.setContentType("text/xml;charset=UTF-8");
		}
		*/

		RexService.CRexDesign oRexDesign = new RexService.CRexDesign(request, response, application);
		//request.setCharacterEncoding("UTF-16");
		if (oRexDesign.m_sDataType.equals("CSV")) {
			//response.setContentType("text/html;charset=UTF-8");
			response.setContentType(oRexDesign.m_sNlsContentTypeCsv);
		} else {    // XML
			//response.setContentType("text/xml;charset=UTF-8");
			response.setContentType(oRexDesign.m_sNlsContentTypeXml);
		}

		oRexDesign.getData();
		//out.print(oRexDesign.getData());
	} else if (designtype.equals("version"))
	{
		response.setContentType("text/xml;charset=UTF-8");
		RexService.CRexService oRexService = new RexService.CRexService(request, response, application);
		oRexService.getVersion();
	} else // run
	{
		String sID = (request.getParameter("ID") == null ? "" : request.getParameter("ID"));
		String sOT = (request.getParameter("OT") == null ? "" : request.getParameter("OT"));
	
		if (!sID.equals("")) {
		
			RexService.CRexDesign30 oRexDesign30 = new RexService.CRexDesign30(request, response, application);

			if (sID.equals("LM")) {
				oRexDesign30.checkLogin();
			} else if (sID.equals("SCL")) {
				oRexDesign30.getServiceList();
			} else if (sID.equals("STLIC")) {
				oRexDesign30.getTableList();
			} else if (sID.equals("SFLIT")) {
				oRexDesign30.getFieldList();
			} else if (sID.equals("SDCSV" ) && sOT.equals("FieldInfoOnly" )) {
				oRexDesign30.getExecFieldList();
			} else if (sID.equals("SDCSV" ) && sOT.equals("DataAndFieldInfo" )) {
				oRexDesign30.getData();
			} else if (sID.equals("SDCSV" ) && sOT.equals("DataOnly" )) {
				oRexDesign30.getData();
			}
		} else {
			/*
			if (sDataType.equals("CSV")) {
				response.setContentType("text/html;charset=UTF-8");
			} else {    // XML
				response.setContentType("text/xml;charset=UTF-8");
				//response.setContentType("text/xml;charset=UTF-8");
			}
			*/
	
			RexService.CRexService oRexService = new RexService.CRexService(request, response, application);
	
			if (oRexService.m_sDataType.equals("CSV")) {
				//response.setContentType("text/html;charset=UTF-8");
				response.setContentType(oRexService.m_sNlsContentTypeCsv);
			} else {    // XML
				//response.setContentType("text/xml;charset=UTF-8");
				response.setContentType(oRexService.m_sNlsContentTypeXml);
			}
	
			oRexService.getData();
		}
	}
	
	//Date  lastDt = new Date();
 	//System.out.println("lastDt : " + sdf.format(lastDt)); 	
%>