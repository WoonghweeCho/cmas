<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="docfbaro.common.WebContext"%>
<%@ taglib prefix="c" uri="/c_rt" %>
<%@ taglib prefix="fmt" uri="/fmt" %>
<%@ taglib prefix="fn" uri="/fn" %>
<%@ taglib prefix="ut" uri="/ut" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" ></c:set>

<ut:script type="text/javascript" src="${contextPath}/common/js/comp/upload/fileuploadECM.js"></ut:script>
<ut:link href="${contextPath}/common/css/fileupload.css" type="text/css" rel="stylesheet" />
