<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
	session.setAttribute("test","Session Test");
%>
<html>
<head>
<title>EL 내장객체 사용 예제</title>
</head>
<body>
<form action="ch13_1_el_test2.jsp" method="post">
<table border=1>
	<tr><td>이름 : </td><td><input type="text" name="name" value="2Be, 보리차"></td>
	<tr><td colspan=2 align=center><input type="submit" value="입력"></td></tr>
</table>
</form>
</body>
</html>
