<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String name=request.getParameter("name");
%>
<html>
<body>
여기는 INCLUDED 페이지임.<BR>
<b><%=name%></b><BR>
여기는 INCLUDED 페이지임.<BR>
</body>
</html>
