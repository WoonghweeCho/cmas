<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="docfbaro.common.Constants"%>
<%@page import="org.apache.commons.beanutils.BeanUtils"%>
<%@page import="com.dwenc.cmas.common.utils.MapUtil"%>
<%@ taglib prefix="c" uri="/c_rt" %>
<%@ taglib prefix="fmt" uri="/fmt" %>
<%@ taglib prefix="fn" uri="/fn" %>
<%@ taglib prefix="ut" uri="/ut" %>
<jsp:useBean id="userInfo" class="docfbaro.iam.UserInfo"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	java.util.Map<String, Object> constantsMap = MapUtil.objectToMap(new Constants());
	request.setAttribute("constants", constantsMap);
	response.setHeader("P3P","CP='CAO PSA CONi OTR OUR DEM ONL'");
	response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\"");

%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" ></c:set>