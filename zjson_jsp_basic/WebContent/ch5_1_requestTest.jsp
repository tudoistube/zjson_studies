<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%request.setCharacterEncoding("utf-8");%>
<html>
<head>
<title>Request Test</title>
</head>
<body>
<h1>Request 예제입니다.</h1>
<table border="1" width="400">
	<tr>
		<td>이름</td>
		<td><%=request.getParameter("name") %></td>
	</tr>
	<tr>
		<td>성별</td>
		<td>
			<%if(request.getParameter("gender").equals("male")) {%>	남자
			<%} else {%>여자<%} %>
		</td>
	</tr>
	<tr>
		<td>취미</td>
		<td>
			<%
			String[] hobby=request.getParameterValues("hobby");
			for(int i=0;i<hobby.length;i++){
			%>
			<%=hobby[i] %>&nbsp;&nbsp;
			<%} %>
		</td>
	</tr>
</table>
</body>
</html>