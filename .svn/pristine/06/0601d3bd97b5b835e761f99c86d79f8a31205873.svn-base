package com.dwenc.cmas.common.tlds;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ScriptTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String type = "text/javascript";
	private String src = "";
	private String defer = "false";


	@Override
	public void release() {

		super.release();
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDefer(String defer) {
		if(defer.toLowerCase().equals("true"))
			this.defer = defer;
	}

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		StringBuffer outStr = new StringBuffer();

		try {
			outStr = getScript();
		} catch (IllegalArgumentException illegalArgumentException) {
			illegalArgumentException.printStackTrace();
		} catch (SecurityException securityException) {
			securityException.printStackTrace();
		}

		try {
			out.print(outStr.toString());
		} catch (IOException e) {

			e.printStackTrace();
		}
		return super.doEndTag();
	}

	private StringBuffer getScript() {
		StringBuffer outStr = new StringBuffer();
		String webRoot = pageContext.getServletContext().getRealPath("/");
		
		String contextPath = ((HttpServletRequest) pageContext.getRequest()).getContextPath();
		int attriIdx = src.indexOf("?");
		String attri = attriIdx < 0 ? null : src.substring(src.indexOf("?"));
		String scriptFile = attriIdx < 0 ? src : src.substring(0, src.indexOf("?"));
		String scriptFilePath = scriptFile.startsWith(contextPath) ? scriptFile.replaceFirst(contextPath, ""): scriptFile;
		
		File file = new File(webRoot, scriptFilePath);
		long modifiedDate = file.lastModified();

		if (attri == null)
			attri = "?version=" + modifiedDate;
		else
			attri = attri + "&version=" + modifiedDate;
		scriptFile += attri;
		
		String script = "<script src=\""+ scriptFile + "\"";
		script += "type=\"" + type + "\" ";
		if(defer.equals("true"))
			script += "defer ";
		script += "></script>";
		outStr.append(script);
		return outStr;
	}
}
