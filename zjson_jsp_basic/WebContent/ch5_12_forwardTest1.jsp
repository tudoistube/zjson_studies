<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<html>
<body>
<jsp:forward page='<%=request.getParameter("forwardPage") %>' >
	<jsp:param name="tel" value="034-1234-5678"/>
</jsp:forward>
</body>
</html>
