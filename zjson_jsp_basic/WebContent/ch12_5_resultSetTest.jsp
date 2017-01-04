<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%
 	Connection conn = null;
 	String sql="SELECT * FROM zjsp_student order by num";
 	
	try {
  		Context init = new InitialContext();
  		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/ZORACLEDB");
  		conn = ds.getConnection();
  		
  		PreparedStatement pstmt=conn.prepareStatement(sql);
  		ResultSet rs=pstmt.executeQuery();
		
  		while(rs.next()){
  			out.println("<h3>"+rs.getInt(1)+","+rs.getString(2)+"</h3>");
  		}
  		rs.close();
	}catch(Exception e){
		out.println("<h3>데이터 가져오기에 실패하였습니다.</h3>");
		e.printStackTrace();
 	}
%>
