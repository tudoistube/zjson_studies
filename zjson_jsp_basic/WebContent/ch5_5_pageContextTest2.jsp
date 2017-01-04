<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>pageContext Test</title>
</head>
<body>
<%
pageContext.include("ch5_5_pageContextTest3.jsp");
//pageContext.include("ch3_4_dogs.html"); //...Okay, it works.
%>
<h2>pageContext의 forward 메소드로 포워딩된 페이지입니다.</h2>
</body>
</html>
