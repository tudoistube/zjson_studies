<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>Include Action Test</title>
</head>
<body>
<h2>INCLUDER</h2>
<jsp:include page="ch5_13_includeTest2.jsp">
	<jsp:param name="name" value="2Be"/>
</jsp:include>
<h2>INCLUDER</h2>
</body>
</html>
