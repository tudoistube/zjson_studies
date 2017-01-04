<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%
	String id=null;
	
	if ((session.getAttribute("id")==null) || 
	  (!((String)session.getAttribute("id")).equals("admin"))) {
		out.println("<script>");
		out.println("location.href='ch12_10_loginForm.jsp'");
		out.println("</script>");
	}
	
	String delete_id=request.getParameter("id");
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	try {
			Context init = new InitialContext();
			DataSource ds = 
				(DataSource) init.lookup("java:comp/env/jdbc/ZORACLEDB");
			conn = ds.getConnection();
			
			pstmt=conn.prepareStatement("DELETE FROM zjsp_member WHERE id=?");
			pstmt.setString(1,delete_id);
			pstmt.executeUpdate();
			
			out.println("<script>");
			out.println("location.href='ch12_10_member_list.jsp'");
			out.println("</script>");
	}catch(Exception e){
		e.printStackTrace();
	}
%>
