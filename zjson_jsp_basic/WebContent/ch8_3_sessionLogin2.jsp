<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%session.setAttribute("id",request.getParameter("id")); %>
<html>
<head>
<title>Session Login</title>
</head>
<body>
<center>
<h3>로그인되었습니다.</h3>
<h3>로그인 아이디 : <%=(String)session.getAttribute("id") %></h3>
<a href="ch8_3_sessionLogin.jsp">로그인</a>
<a href="ch8_3_sessionLogout.jsp">로그아웃</a>
</center>
</body>
</html>
