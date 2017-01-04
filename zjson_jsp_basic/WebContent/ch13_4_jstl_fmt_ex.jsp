<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="date1" class="java.util.Date"/>

<html>
<head>
<title>JSTL fmt 라이브러리 사용 예제</title>
</head>
<body>

<fmt:setLocale value="en_US"/><!-- en_US, ko_KR -->
<fmt:bundle basename="ch13_4_test">
	<fmt:message key="name"/><br>
	<fmt:message key="say"/><br>
	<fmt:message key="say2">
		<fmt:param value="2Be, 순두부"/>
	</fmt:message>
</fmt:bundle>

<p>
<fmt:formatNumber value="50000" type="currency"/><br>
<fmt:formatNumber value="0.15" type="percent"/><br>
<fmt:formatNumber value="500567300" pattern="###,###,###"/><p>

<fmt:formatDate value="${date1}" type="date"/><br>
<fmt:formatDate value="${date1}" type="time"/><br>
<fmt:formatDate value="${date1}" type="both"/><p>

<fmt:formatDate value="${date1}" type="both" timeStyle="short" dateStyle="short"/><br>
<fmt:formatDate value="${date1}" type="both" timeStyle="long" dateStyle="long"/><br>

</body>
</html>
