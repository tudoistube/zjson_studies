<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
<head>
<title>JSTL sql 라이브러리 사용 예제</title>
</head>
<body>

<sql:setDataSource var="conn" driver="oracle.jdbc.driver.OracleDriver" 
				url="jdbc:oracle:thin:@localhost:1521:XE"
				user="system" 
				password="tiger"/>

<c:if test = "${5<10}"> 
	<sql:update dataSource="${conn}">
		INSERT INTO zjsp_jstlsql (num, name) VALUES (1, '홍길동')
	</sql:update>
	<sql:update dataSource="${conn}">
		INSERT INTO zjsp_jstlsql (num, name) VALUES (2, '강호동')
	</sql:update>
	<sql:update dataSource="${conn}">
		INSERT INTO zjsp_jstlsql (num, name) VALUES (3, '홍길동')
	</sql:update>
	<sql:update dataSource="${conn}">
		INSERT INTO zjsp_jstlsql (num, name) VALUES (4, '강호순')
	</sql:update> 
</c:if>

<sql:query var="rs" dataSource="${conn}">
	SELECT * FROM zjsp_jstlsql WHERE name=?
	<sql:param>홍길동</sql:param>
</sql:query>

<c:forEach var="data" items="${rs.rows}">
<!-- 
	<c:out value="${data['num']}"/>
 -->
	<c:out value="${data.num}"/>	
	<c:out value="${data['name']}"/>
	<br>
</c:forEach>

</body>
</html>
