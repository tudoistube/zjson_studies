<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	StringBuffer sb=null;
	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	
	String sql="INSERT INTO zjsp_clobtable (num,content) VALUES (1,empty_clob())";
	
	//...FOR UPDATE 를 붙여주어 가져온 데이터를 사용하여 SQL 작업을 하는 동안 다른 세션에서 
	//...해당 데이터를 변경할 수 없도록 트랜잭션을 적용시킴. 
 	String sql2="SELECT content FROM zjsp_clobtable WHERE num=1 FOR UPDATE";
 	
	try{
		Class.forName(driver);
		conn=DriverManager.getConnection(url,"system","tiger");
		
		//...CLOB 데이터 삽입이 제대로 처리되게 하기 위해 AutoCommit 을 해제한다.
		//...Otherwise, 다른 트랜잭션 처리에 의해 DeadLock 현상 발생할 수 있다.
		conn.setAutoCommit(false);
		
		sb=new StringBuffer();
		for(int i=0;i<=10000;i++){
			sb.append("2DoIs2Be, 순두부, JoyWins^_____^!");
		}
		
		pstmt=conn.prepareStatement(sql);
  		pstmt.executeUpdate();
  		pstmt.close();
  		
  		pstmt=conn.prepareStatement(sql2);
  		rs=pstmt.executeQuery();
  		if(rs.next()){
  			oracle.sql.CLOB clob=(oracle.sql.CLOB)(rs).getClob("content");
  			BufferedWriter bw=new BufferedWriter(clob.getCharacterOutputStream());
  			bw.write(sb.toString());
  			bw.close();
  		}
  		pstmt.close();
  		
  		
  	   	conn.commit();
  	  	conn.setAutoCommit(true);
  	  	out.println("데이타를 저장했습니다.");
  	  	
  	    rs.close();
		pstmt.close();
		conn.close();
	}catch(Exception e){
		e.printStackTrace();
	}
%>
