<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
	Cookie cookie=new Cookie("language",request.getParameter("language")); 
	cookie.setMaxAge(60*60*24);
	response.addCookie(cookie);
%>
<script>
location.href="ch8_4_cookieExample.jsp"
</script>
