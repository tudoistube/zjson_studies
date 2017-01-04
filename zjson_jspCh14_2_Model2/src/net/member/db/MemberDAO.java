package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	public MemberDAO() {
		try{
			Context init = new InitialContext();
	  		ds = 
	  			(DataSource) init.lookup("java:comp/env/jdbc/ZORACLEDB");
			System.out.println("DB 연결 성공 :: ds = " + ds);	  		
		}catch(Exception ex){
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
		
	}
	
	public int isMember(MemberBean member){
		String sql="SELECT MEMBER_PW FROM  zjsp_ch14_2_MEMBER WHERE MEMBER_ID=?";
		int result=-1;
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getMEMBER_ID());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				if(rs.getString("MEMBER_PW").equals(
									member.getMEMBER_PW())){
					result=1;//일치.
				}else{
					result=0;//불일치.
				}
			}else{
				result=-1;//아이디 존재하지 않음.
			}
		}catch(Exception ex){
			System.out.println("isMember 에러: " + ex);			
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		
		return result;
	}
	
	public boolean joinMember(MemberBean member){
		String sql="INSERT INTO  zjsp_ch14_2_MEMBER VALUES (?,?,?,?,?,?)";
		int result=0;
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getMEMBER_ID());
			pstmt.setString(2, member.getMEMBER_PW());
			pstmt.setString(3, member.getMEMBER_NAME());
			pstmt.setInt(4, member.getMEMBER_AGE());
			pstmt.setString(5, member.getMEMBER_GENDER());
			pstmt.setString(6, member.getMEMBER_EMAIL());
			result=pstmt.executeUpdate();
			
			if(result!=0){
				return true;
			}
		}catch(Exception ex){
			System.out.println("joinMember 에러: " + ex);			
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		
		return false;
	}
	
	public List getMemberList(){
		String sql="SELECT * FROM  zjsp_ch14_2_MEMBER";
		List memberlist=new ArrayList();
		
		try{
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				MemberBean mb=new MemberBean();
				mb.setMEMBER_ID(rs.getString("MEMBER_ID"));
				mb.setMEMBER_PW(rs.getString("MEMBER_PW"));
				mb.setMEMBER_NAME(rs.getString("MEMBER_NAME"));
				mb.setMEMBER_AGE(rs.getInt("MEMBER_AGE"));
				mb.setMEMBER_GENDER(rs.getString("MEMBER_GENDER"));
				mb.setMEMBER_EMAIL(rs.getString("MEMBER_EMAIL"));
				
				memberlist.add(mb);
			}
			
			return memberlist;
		}catch(Exception ex){
			System.out.println("getDeatilMember 에러: " + ex);			
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return null;
	}
	
	public MemberBean getDetailMember(String id){
		String sql="SELECT * FROM  zjsp_ch14_2_MEMBER WHERE MEMBER_ID=?";
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			rs.next();
			
			MemberBean mb=new MemberBean();
			mb.setMEMBER_ID(rs.getString("MEMBER_ID"));
			mb.setMEMBER_PW(rs.getString("MEMBER_PW"));
			mb.setMEMBER_NAME(rs.getString("MEMBER_NAME"));
			mb.setMEMBER_AGE(rs.getInt("MEMBER_AGE"));
			mb.setMEMBER_GENDER(rs.getString("MEMBER_GENDER"));
			mb.setMEMBER_EMAIL(rs.getString("MEMBER_EMAIL"));
			
			return mb;
		}catch(Exception ex){
			System.out.println("getDeatilMember 에러: " + ex);			
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		
		return null;
	}
	
	/*
	public boolean deleteMember(String id){
	 String sql ="DELETE FROM MEMBER WHERE MEMBER_ID=?";
	 int result = 0;
	 try{
	  con = ds.getConnection();
	  pstmt=con.prepareStatement(sql);
	  pstmt.setString(1,id);

	  result = pstmt.executeUpdate();
	  if(result != 0){
	   return true;
	  }

	 }
	 catch(Exception ex){
	  System.out.println("deleteMember 에러: " + ex);	
	 }

	 finally{
	 if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
	 }
	 return false;
	}
	*/
	public boolean deleteMember(String id){
		/*
		 * ...회원을 탈퇴시키면, 해당 회원이 작성한 글도 같이 삭제가 되도록 처리함.
		 * ...동시에 두개의 DELETE 문이 실행되어야 하기 때문에 트랜잭션 처리도 해주어야 한다.
		 */
		String sql1 = "DELETE FROM zjsp_ch14_2_memberboard WHERE BOARD_ID=?";
		String sql2="DELETE FROM zjsp_ch14_2_member WHERE MEMBER_ID=?";
		boolean isSuccess = false;
		int result1=0;
		int result2=0;
		boolean result =false;
		System.out.println("deleteId="+id);
		try{
			con=ds.getConnection();
			/*
			 * ...오라클은 기본적으로 autoCommit 이 설정되 있지 않지만, 
			 * ...기본적으로 자바에서 제공하는 JDBC 의 Connection 객체는 autoCommit 이다.
			 * ...setAutoCommit(false) 하여 DML 문장 실행결과가 메모리 상에서만 작업이 이뤄지다가
			 * ...commit() 이나 rollback() 메서드에서 처리여부가 결저오딘다.
			 */
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql1);
			pstmt.setString(1, id);
			result1=pstmt.executeUpdate();

			pstmt=con.prepareStatement(sql2);
			pstmt.setString(1, id);
			result2=pstmt.executeUpdate();
			
			/*
			 * ...각 삭제 작업에서 삭제된 행의 개수(executeUpdate 메서드를 실행하면 해당 작업에 의해 적용된 행의 개수가 리턴됨)가
			 * ...한 행 이상일 때 result 를 true 로 설정해줌.
			 */
			if(result1>0 && result2>0){
				result = true;
			}
			isSuccess=true;
		}catch(Exception ex){
			System.out.println("deleteMember 에러: " + ex);	
		}finally{
			try{
				if(isSuccess){
					con.commit();
				}
				else{
					con.rollback();
				}
			}
			catch(Exception e){};
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		
		return result;
	}
}