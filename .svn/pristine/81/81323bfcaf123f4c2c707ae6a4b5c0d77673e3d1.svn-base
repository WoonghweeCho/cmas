<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@page import="java.util.*" %>
<html>
<form name="rexForm" method="post">
<%
		Enumeration params = request.getParameterNames();

		while (params.hasMoreElements()) {
			String name = (String)params.nextElement();
			String value = request.getParameter(name);
%>
<input type="hidden" name="<%=name%>" value="<%=value%>">
<%
		}
%>
</form>
<script>
    document.rexForm.action = "convert2.jsp";
    document.rexForm.submit();
</script>
</html>