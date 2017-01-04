<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

	
%>
<table border="1" width="600">

	<tr>
		<th>표지</th>

		<th>제목</th>

		<th>가격</th>

		<th>설명</th>
	</tr>

<c:if test="${not empty requestScope.resultBook }">

	<c:forEach var="vo" items="${requestScope.resultBook}" >
	
		<tr>
			<td><img src="${vo.book_img }" width=50 height=50 /></td>
	
			<td width=30>${vo.book_author }</td>
	
			<td>${vo.book_sale_price }</td>
	
			<td>${vo.book_description }</td>
	
	</c:forEach>

</c:if>


</table>
</body>
</html>