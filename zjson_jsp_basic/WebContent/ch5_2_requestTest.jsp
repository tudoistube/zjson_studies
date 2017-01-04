<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.Enumeration"%>
<html>
<head>
<title>Request Test2</title>
</head>
<body>
<h1>헤더정보 예제</h1>
<table border="1">
	<tr>
		<td>헤더이름</td>
		<td>헤더값</td>
	</tr>
<%
Enumeration e=request.getHeaderNames();
while(e.hasMoreElements()){
	String headerName=(String)e.nextElement();
%>
	<tr>
		<td><%=headerName %></td>
		<td><%=request.getHeader(headerName) %>
	</td>
<%}%>
</table>
</body>
</html>
