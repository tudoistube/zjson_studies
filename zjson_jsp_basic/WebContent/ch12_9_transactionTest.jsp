<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%
 	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql="INSERT INTO zjsp_student (num, name) VALUES (11,'장나라')";
	String sql2="SELECT * FROM zjsp_student WHERE num=10"; 	
	try {
  		Context init = new InitialContext();
  		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/ZORACLEDB");
  		conn = ds.getConnection();
  		
  		//...트랜잭션을 이용하기 위해 setAutoCommit 을 해제한다.
  		conn.setAutoCommit(false);
  		
  		pstmt=conn.prepareStatement(sql);
  		pstmt.executeUpdate();
  		pstmt.close();
  		
  		pstmt=conn.prepareStatement(sql2);
  		rs=pstmt.executeQuery();
  		if(!rs.next()){
  			conn.rollback();
  			out.println("<h3>데이터 삽입에 문제가 발생하여 롤백하였습니다.</h3>");
  		}else{
  			conn.commit();
  			out.println("<h3>데이터 삽입이 모두 완료되었습니다.</h3>");
  		}
  		pstmt.close();
  		conn.setAutoCommit(true);
	}catch(Exception e){
		out.println("<h3>데이터 삽입에 실패하였습니다.</h3>");
		e.printStackTrace();
 	}
%>
