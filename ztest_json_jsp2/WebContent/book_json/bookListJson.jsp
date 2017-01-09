<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>JSON 으로 출력하기...</title>
	<script type="text/javascript">
		function addBook(){
			frmBook.submit();
		}
	</script>
	
	<script type="text/javascript">
		var myVar = '<%= request.getAttribute("resultBook") %>';    /* retrieve json from request attribute */
		//var mytest = eval('(' + myVar + ')');
		var jsonObj = JSON.parse(myVar);
		
		alert("myVar : " + myVar)  ;
		alert("myVar.channel.item[0]" + myVar.channel.item[0]);
		//alert("2 : " + jsonObj);       
	</script>
</head>
<body>


<h1>JSON 으로 출력하기...</h1>



<c:if test="${not empty requestScope.zresult }">
	${zresult }
</c:if>

</body>
</html>