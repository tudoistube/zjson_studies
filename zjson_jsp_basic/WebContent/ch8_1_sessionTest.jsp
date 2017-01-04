<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
	String name;
	if (session.getAttribute("name")!=null){
		name=(String)session.getAttribute("name");
	}else{
		name="세션 값 없음.";
	}
%>
<!--
	reference on a subject : Session 
	http://kbseok.tistory.com/entry/%EC%84%B8%EC%85%98
 -->
<html>
<head>
<title>Session Test</title>
</head>
<body>
<h2>세션 테스트</h2>
<input type="button" onclick="location.href='ch8_1_sessionSet.jsp'" value="세션 값 저장">
<input type="button" onclick="location.href='ch8_1_sessionDel.jsp'" value="세션 값 삭제">
<input type="button" onclick="location.href='ch8_1_sessionInvalidate.jsp'" value="세션 초기화">
<h3><%=name %></h3>
</body>
</html>
