<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%
 	Connection conn = null;
 	String sql="SELECT * FROM zjsp_student";
 	
	try {
  		Context init = new InitialContext();
  		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/ZORACLEDB");
  		conn = ds.getConnection();
  		
  		//...아무 옵션을 주지 않으면, 기본값은 TYPE_FORWARD_ONLY 값이 적용되어 first(), last() 등 커서 이동 관련 메소드를 사용할 수 없다.
  		//...TYPE_SCROLL_SENSITIVE, CONCUR_UPDATABLE 옵션을 동시에 주어야 레코드의 위치를 자유롭게 이동 시킬 수 있음.  		
  		PreparedStatement pstmt=
			  conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
			        	                   ResultSet.CONCUR_UPDATABLE);
  		ResultSet rs=pstmt.executeQuery();
		
  		rs.last();
  		out.println(rs.getInt(1)+","+rs.getString(2)+"<br>");
  		rs.first();
  		out.println(rs.getInt(1)+","+rs.getString(2)+"<br>");
  		rs.absolute(3);
		out.println(rs.getInt(1)+","+rs.getString(2)+"<br>");
	}catch(Exception e){
		out.println("<h3>데이터 가져오기에 실패하였습니다.</h3>");
		e.printStackTrace();
 	}
%>
