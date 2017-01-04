package net.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public BoardDAO() {
		try{
			Context init = new InitialContext();
	  	    ds = (DataSource) init.lookup("java:comp/env/jdbc/ZORACLEDB");
	  		
		}catch(Exception ex){
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
		
	}
	//글의 개수 구하기
	public int getListCount() {
		int x= 0;
		
		try{
			
			con=ds.getConnection();
			System.out.println("getConnection");
			pstmt=con.prepareStatement("select count(*) from zjsp_board");
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				x=rs.getInt(1);
			}
		}catch(Exception ex){
			System.out.println("getListCount 에러: " + ex);			
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return x;
	}
	
	//글 목록 보기
	public List getBoardList(int page,int limit){
	/*
	 * ...특정 번째의 레코드부터 특정 번째의 레코드까지의 값을 얻어오는 기능.
	 * ...MySQL 을 사용할 경우, LIMIT 기능을 사용하고, 오라클일 경우, rownum 함수를 이용한다.
	 * ...제일 뒷부분 where 절의 조건이 몇번째 레코드 값을 가져올지를 지정한다.
	 */
		String board_list_sql="select * from "+
		"(select rownum rnum,BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,"+
		"BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,"+
		"BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE from "+
		"(select * from zjsp_board order by BOARD_RE_REF desc,BOARD_RE_SEQ asc)) "+
		"where rnum>=? and rnum<=?";
		
		List list = new ArrayList();
		/*
		 * ...시작행과 끝행의 번호를 계산함.
		 */
		int startrow=(page-1)*10+1; //읽기 시작할 row 번호.
		int endrow=startrow+limit-1; //읽을 마지막 row 번호.		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				BoardBean board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				list.add(board);
			}
			
			return list;
		}catch(Exception ex){
			System.out.println("getBoardList 에러 : " + ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return null;
	}
	
	//글 내용 보기.
	public BoardBean getDetail(int num) throws Exception{
		
		BoardBean board = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(
					"select * from zjsp_board where BOARD_NUM = ?");
			pstmt.setInt(1, num);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
			}
			return board;
		}catch(Exception ex){
			System.out.println("getDetail 에러 : " + ex);
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return null;
	}
	
	//글 등록
	public boolean boardInsert(BoardBean board){
		
		int num =0;
		String sql="";
		
		int result=0;
		
		try{
			con = ds.getConnection();
			/*
			 * ...max 함수를 사용하는 이유.
			 * ...count 함수를 사용할 경우, 만약 10개의 글 중 5번째 글을 삭제할 경우 9개의 레코드가 있게되고,
			 * ...다시 새글을 등록할 경우 글번호 10으로 등록하게 되면 무결성 제약 조건에 위배됨.
			 */
			pstmt=con.prepareStatement("select max(board_num) from zjsp_board");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num =rs.getInt(1)+1;
			else
				num=1;
			/*
			 * ...새로운 글을 등록하는 것은 답변을 다는 것과 다르므로, 답변과 관련된 필드값은 모두 0으로 주었다.
			 * ...board_re_ref 필드 값만 새로운 값으로 저장하는데, 이 값은 글을 정렬하거나 그룹을 묶을때 사용됨.
			 * ...board_re_ref 필드는 같은 글에 대한 답변들은 모두 같은 값이어서, 날짜와 상관없이 답변 글들이
			 * ...하나의 그룹으로 묶여 정렬되게 된다.
			 */
			sql="insert into zjsp_board (BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,";
			sql+="BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF,"+
				"BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,"+
				"BOARD_DATE) values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBOARD_NAME());
			pstmt.setString(3, board.getBOARD_PASS());
			pstmt.setString(4, board.getBOARD_SUBJECT());
			pstmt.setString(5, board.getBOARD_CONTENT());
			pstmt.setString(6, board.getBOARD_FILE());
			pstmt.setInt(7, num);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);
			
			result=pstmt.executeUpdate();
			if(result==0)return false;
			
			return true;
		}catch(Exception ex){
			System.out.println("boardInsert 에러 : "+ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return false;
	}
	
	//글 답변
	public int boardReply(BoardBean board){
		
		String board_max_sql="select max(board_num) from zjsp_board";
		String sql="";
		int num=0;
		int result=0;
		
		/*
		 * ...답변을 할 원본 글 그룹 번호이다.
		 */
		int re_ref=board.getBOARD_RE_REF();
		/*
		 * ...답변 글의 깊이(답글 레벨)이다. 답글 출력시 레벨마다 들여쓰기 처리가 됨.
		 */
		int re_lev=board.getBOARD_RE_LEV();
		/*
		 * ...같은 관련 글 중에서 해당 글이 출력되는 순서이다.
		 * ...원 글인 경우에는 이 값이 0이 되고, 원글의 답글은 1, 그리고 답글의 답글은 2가 된다.
		 * ...현재 글을 답변 대상 글 바로 아래에 출력되도록 처리하기 위함.
		 */
		int re_seq=board.getBOARD_RE_SEQ();
		
		try{
			/*
			 *...답변 글 등록 처리임.
			 *...sql 문 조건절에 board_re_ref, board_re_seq 값을 확인하여 원본 글에 다른 답변 글이 있으면,
			 *...현재 답변을 대상 글보다 board_re_seq 값이 큰 글이 있으면 해당 글들의 board_re_seq 값을
			 *...모두 1씩 증가시켜서 다른 답변 글들을 뒤에 출력되게 처리함.
			 * ...현재 글을 답변 대상 글 바로 아래에 출력되도록 처리하기 위함.
			 */
			con = ds.getConnection();
			pstmt=con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			if(rs.next())num =rs.getInt(1)+1;
			else num=1;
			
			sql="update zjsp_board set BOARD_RE_SEQ=BOARD_RE_SEQ+1 where BOARD_RE_REF=? ";
			sql+="and BOARD_RE_SEQ>?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,re_ref);
			pstmt.setInt(2,re_seq);
			result=pstmt.executeUpdate();
			/*
			 * ...현재 답변 글의 board_re_seq 값은 답변 대상 글의 값에 1을 더한다.
			 * ...현재 답변한 답변 글이 답변 대상글 바로 다음 라인에 출력되게 처리하기 위함.
			 */
			re_seq = re_seq + 1;
			/*
			 * ...답변을 다는 것이므로 현재 답변 레벨 단계에서 1을 증가시킴.
			 * ...원본 글에 답변을 달면, 원본 글의 답변 레벨이 0이므로 답변 레벨은 1이 된다.
			 */
			re_lev = re_lev+1;
			
			sql="insert into zjsp_board (BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,";
			sql+="BOARD_CONTENT, BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,BOARD_RE_SEQ,";
			sql+="BOARD_READCOUNT,BOARD_DATE) values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBOARD_NAME());
			pstmt.setString(3, board.getBOARD_PASS());
			pstmt.setString(4, board.getBOARD_SUBJECT());
			pstmt.setString(5, board.getBOARD_CONTENT());
			pstmt.setString(6, ""); //답장에는 파일을 업로드하지 않음.
			pstmt.setInt(7, re_ref);
			pstmt.setInt(8, re_lev);
			pstmt.setInt(9, re_seq);
			pstmt.setInt(10, 0);
			pstmt.executeUpdate();
			return num;
		}catch(SQLException ex){
			System.out.println("boardReply 에러 : "+ex);
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return 0;
	}
	
	//글 수정
	public boolean boardModify(BoardBean modifyboard) throws Exception{
		
		String sql="update zjsp_board set BOARD_SUBJECT=?,BOARD_CONTENT=? where BOARD_NUM=?";
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, modifyboard.getBOARD_SUBJECT());
			pstmt.setString(2, modifyboard.getBOARD_CONTENT());
			pstmt.setInt(3, modifyboard.getBOARD_NUM());
			pstmt.executeUpdate();
			return true;
		}catch(Exception ex){
			System.out.println("boardModify 에러 : " + ex);
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
			}
		return false;
	}
	
	/*
	 * ...삭제와 관련된 Action 클래스에서 비밀번호를 확인하면 이 코드가 수행됨.
	 */
	//글 삭제
	public boolean boardDelete(int num){
		
		String board_delete_sql="delete from zjsp_board where BOARD_num=?";
		
		int result=0;
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
			/*
			 * ...글이 삭제되면 삭제된 로우(행) 수가 반환되는데, 0이 반환되면 어떤 것도 삭제되지 않은 것임.
			 */
			if(result==0)return false;
			
			return true;
		}catch(Exception ex){
			System.out.println("boardDelete 에러 : "+ex);
		}	finally{
			try{
				if(pstmt!=null)pstmt.close();
				if(con!=null) con.close();
				}
				catch(Exception ex){}
			
		}
		
		return false;
	}
	
	/*
	 * ...글 내용을 확인하는 순간 호출됨.
	 */
	//조회수 업데이트
	public void setReadCountUpdate(int num) throws Exception{
		
		String sql="update zjsp_board set BOARD_READCOUNT = "+
			"BOARD_READCOUNT+1 where BOARD_NUM = "+num;
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch(SQLException ex){
			System.out.println("setReadCountUpdate 에러 : "+ex);
		}
		finally{
			try{
			if(pstmt!=null)pstmt.close();
			if(con!=null) con.close();
			}
			catch(Exception ex){}
		
	}
	}
	
	//글쓴이인지 확인
	public boolean isBoardWriter(int num,String pass){
		
		String board_sql="select * from zjsp_board where BOARD_NUM=?";
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(board_sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			rs.next();
			
			/*
			 * ...글쓴이를 확인하기 위해 비밀번호를 얻고, 수정할 때 입력한 비밀번호와 조회한 글의 비밀번호를
			 * ...확인한다.
			 */
			if(pass.equals(rs.getString("BOARD_PASS"))){
				return true;
			}
		}catch(SQLException ex){
			System.out.println("isBoardWriter 에러 : "+ex);
		}
	finally{
			try{
			if(pstmt!=null)pstmt.close();
			if(con!=null) con.close();
			}
			catch(Exception ex){}
		
	}
		return false;
	}

}
	