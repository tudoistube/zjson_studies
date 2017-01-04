<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
	Cookie cookie=new Cookie("name","2Be, 2Do");
	cookie.setMaxAge(600);
	response.addCookie(cookie);
%>
<html>
<head>
<title>Cookie Test</title>
</head>
<body>
<h2><%=cookie.getName() %></h2>
<h2><%=cookie.getValue() %></h2>
<h2><%=cookie.getMaxAge() %></h2>
<a href="ch8_2_cookieTest2.jsp">쿠키 값 불러오기</a>
</body>
</html>
