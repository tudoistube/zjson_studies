package yes.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import yes.book.dto.BookDTO;

public class BookDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public BookDAO() {
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
			pstmt=con.prepareStatement("select count(*) from zjson_book");
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
		String BOOK_list_sql="select * from "+
		"(select rownum rnum,BOOK_NUM,BOOK_ISBN,BOOK_TITLE,"+
		"BOOK_AUTHOR,BOOK_IMG,BOOK_SALE_PRICE,BOOK_DESCRIPTION,"+
		"BOOK_DATE from "+
		"(select * from zjson_book order by BOOK_DATE desc)) "+
		"where rnum>=? and rnum<=?";
		
		List list = new ArrayList();
		/*
		 * ...시작행과 끝행의 번호를 계산함.
		 */
		int startrow=(page-1)*10+1; //읽기 시작할 row 번호.
		int endrow=startrow+limit-1; //읽을 마지막 row 번호.		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(BOOK_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				BookDTO bookDto = new BookDTO();				
				bookDto.setBook_num(rs.getInt("BOOK_NUM"));
				bookDto.setBook_isbn(rs.getString("BOOK_ISBN"));
				bookDto.setBook_title(rs.getString("BOOK_TITLE"));
				bookDto.setBook_author(rs.getString("BOOK_AUTHOR"));				
				bookDto.setBook_img(rs.getString("BOOK_IMG"));
				bookDto.setBook_sale_price(rs.getString("BOOK_SALE_PRICE"));
				bookDto.setBook_description(rs.getString("BOOK_DESCRIPTION")); 				
				bookDto.setBook_pub_date(rs.getDate("BOOK_DATE")); 
				list.add(bookDto);
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
	public BookDTO getDetail(int num) throws Exception{
		
		BookDTO bookDto = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(
					"select * from zjson_book where BOOK_NUM = ?");
			pstmt.setInt(1, num);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()){				
				bookDto.setBook_num(rs.getInt("BOOK_NUM"));
				bookDto.setBook_isbn(rs.getString("BOOK_ISBN"));
				bookDto.setBook_title(rs.getString("BOOK_TITLE"));
				bookDto.setBook_author(rs.getString("BOOK_AUTHOR"));				
				bookDto.setBook_img(rs.getString("BOOK_IMG"));
				bookDto.setBook_sale_price(rs.getString("BOOK_SALE_PRICE"));
				bookDto.setBook_description(rs.getString("BOOK_DESCRIPTION")); 				
				bookDto.setBook_pub_date(rs.getDate("BOOK_DATE"));
			}
			return bookDto;
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
	public boolean bookDtoInsert(BookDTO bookDto){
		
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
			pstmt=con.prepareStatement("select max(BOOK_num) from zjson_book");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num =rs.getInt(1)+1;
			else
				num=1;
			/*
			 * ...새로운 글을 등록하는 것은 답변을 다는 것과 다르므로, 답변과 관련된 필드값은 모두 0으로 주었다.
			 * ...BOOK_SALE_PRICE 필드 값만 새로운 값으로 저장하는데, 이 값은 글을 정렬하거나 그룹을 묶을때 사용됨.
			 * ...BOOK_SALE_PRICE 필드는 같은 글에 대한 답변들은 모두 같은 값이어서, 날짜와 상관없이 답변 글들이
			 * ...하나의 그룹으로 묶여 정렬되게 된다.
			 */
			sql="insert into zjson_book (BOOK_NUM, BOOK_ISBN, BOOK_TITLE, ";
			sql+="BOOK_AUTHOR, BOOK_IMG, BOOK_SALE_PRICE,"+
				 "BOOK_DESCRIPTION, BOOK_DATE) values(?, ?, ?, ?, ?, ?, ?, ?)";		
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, bookDto.getBook_isbn());
			pstmt.setString(3, bookDto.getBook_title());
			pstmt.setString(4, bookDto.getBook_author());
			pstmt.setString(5, bookDto.getBook_img());
			pstmt.setString(6, bookDto.getBook_sale_price());
			pstmt.setString(7, bookDto.getBook_description());
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			try {

				Date date = formatter.parse(bookDto.getBook_pub_date_string());
				bookDto.setBook_pub_date(date);
				System.out.println("변경 전 날짜 : " + bookDto.getBook_pub_date_string());
				System.out.println("변경 후 날짜 : " + date);
				pstmt.setDate(8, new java.sql.Date(date.getTime()));//sysdate

			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			
			result=pstmt.executeUpdate();
			if(result==0)return false;
			
			return true;
		}catch(Exception ex){
			System.out.println("bookDtoInsert 에러 : "+ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return false;
	}	
	

}
	