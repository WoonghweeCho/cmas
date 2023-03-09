<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Properties" %>
<%@page import="org.apache.commons.codec.binary.Base64" %>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%
	WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
	Properties prop = (Properties) context.getBean("appProperties");

	String decodeUsername = new String(Base64.decodeBase64(request.getParameter("username")));
	String decodePassword = new String(Base64.decodeBase64(request.getParameter("password")));
	String contentLink = request.getParameter("contentLink");
	String loginURL = "http://m.dwconst.co.kr" + "/names.nsf?login&";
	loginURL += "username=" + decodeUsername + "&password=" + decodePassword + "&redirectto=";
	loginURL += URLEncoder.encode(contentLink);

	response.sendRedirect(loginURL);
	//out.println(loginURL);
%>
