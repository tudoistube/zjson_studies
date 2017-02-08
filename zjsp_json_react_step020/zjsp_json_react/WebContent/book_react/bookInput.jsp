<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ReactInputGET.yes</title>
</head>
<body>

	<h1>Using Daum Open API, search a book and get JSON data to render the book list to REACT component.</h1>

	<form method="POST" action="bookList.yes">
		 책이름 : <input type="text" name="bookName"/><br/>
		 <input type="submit" value="검색"/>
		 <input type="reset" value="취소"/>
	</form>

</body>
</html>