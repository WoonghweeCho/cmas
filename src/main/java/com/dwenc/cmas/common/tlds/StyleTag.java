package com.dwenc.cmas.common.tlds;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class StyleTag extends BodyTagSupport{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String href = "";
	private String type = "text/css";
	private String rel = "";
	private String media = "";
	@Override
	public void release() {
		
		super.release();
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void setRel(String rel) {
		this.rel = rel;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
        StringBuffer outStr = new StringBuffer();

       	try {
			outStr = getStyle();
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
	
	private StringBuffer getStyle(){

		StringBuffer outStr = new StringBuffer();
		String webRoot = pageContext.getServletContext().getRealPath("/");

		String contextPath = ((HttpServletRequest) pageContext.getRequest()).getContextPath();
		int attriIdx = href.indexOf("?");
		String attri = attriIdx < 0 ? null : href.substring(href.indexOf("?"));
		String styleFile = attriIdx < 0 ? href : href.substring(0, href.indexOf("?"));
		String styleFilePath = styleFile.startsWith(contextPath) ? styleFile.replaceFirst(contextPath, ""): styleFile;
		
		File file = new File(webRoot, styleFilePath);
		long modifiedDate = file.lastModified();
		
		if (attri == null)
			attri = "?version=" + modifiedDate;
		else
			attri = attri + "&version=" + modifiedDate;
		styleFile += attri;
		
		String style = "<link rel=\"stylesheet\" type=\"" + type +  "\" href=\""+ styleFile + "\"";
		
		if(!rel.equals("")) style += " rel=" + rel;
		if(!media.equals("")) style += " media=" + media;
		
		style += " />";
		outStr.append(style);
		
		return outStr;
	}
}
