package yes.book.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import net.board.action.Action;
import net.board.action.ActionForward;
import yes.book.dao.BookDAO;
import yes.book.dto.BookDTO;
import zjson.util.HttpClientGet;

public class BookListAddPostAction implements Action {
	
	BookDAO bookDao = new BookDAO();
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{		 
		 
		 
		//...get json-data from daum api.
		String key = "3250431f017bec03f26cc7781dfca95b"; //...자신의 api key 를 넣음.
		String bookName = request.getParameter("bookName");
		System.out.println("검색할 책 이름 : " + bookName);		 
		 
		// 요청할 주소를 넣으세요
		//String url = "https://apis.daum.net/search/book?apikey=3250431f017bec03f26cc7781dfca95b&q=CSS&output=json";
		String url = "https://apis.daum.net/search/book?apikey="+key+"&q="+bookName+"&output=json";
		
		// 다음 서버로 부터 json 받아오기 
		String json = HttpClientGet.get_JSONDATA(url);	
		System.out.println("반환된 JSON DATA : " + json);		 

/*...JSON 파싱하기 */
		
		//ArrayList<BookDTO> list = BoardDAO.getList();
		ArrayList<BookDTO> list = new ArrayList<BookDTO>();
		
		//...파싱해서 책 저장하기.
		parsingJsonAndSave(list, json);
		
		ActionForward zaction = new ActionForward();
		
		System.out.println(list.size());
		
		if (list.size() > 0) 
		{			
			//HttpSession session = request.getSession();
			//session.setAttribute("session_list",list);

			//...검색결과를 Request 영역에 담기.
			request.setAttribute("resultBook", list);
			String zresult = "saved into local Db.";
			request.setAttribute("zresult", zresult);

			//...Dispatcher 설정.
			zaction.setRedirect(false);
			//...이동할 페이지.
			zaction.setPath("./book_json/bookListJson.jsp");
			
		} else
		{
			String msg = "Error occured...";
			request.setAttribute("errMsg", msg);
			zaction.setRedirect(false);
			zaction.setPath("Error.jsp");
		}	
		
//---------------------------------------------------------------------------------
		/*
		BoardDAO boarddao=new BoardDAO();
		List boardlist=new ArrayList();
		
		int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		int listcount=boarddao.getListCount(); //총 리스트 수를 받아옴
		boardlist = boarddao.getBoardList(page,limit); //리스트를 받아옴
		
		//총 페이지 수
		int maxpage=(int)((double)listcount/limit+0.95); //0.95를 더해서 올림 처리
		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
		int startpage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
		int endpage = startpage+10-1;

		if (endpage> maxpage) endpage= maxpage;

		
		request.setAttribute("page", page); //현재 페이지 수
		request.setAttribute("maxpage", maxpage); //최대 페이지 수
		request.setAttribute("startpage", startpage); //현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("endpage", endpage); //현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("listcount",listcount); //글 수
		request.setAttribute("boardlist", boardlist);
		
		ActionForward forward= new ActionForward();
	 	forward.setRedirect(false);
		forward.setPath("./board/qna_board_list.jsp");
		*/
		return zaction;	
	 }
	 
	 private void parsingJsonAndSave(ArrayList<BookDTO> bookList, String json) throws ParseException {
			
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(json);
			
			//...get channel.
			JSONObject channel = (JSONObject)obj.get("channel");
			
			//...get item array from channel.
			//...because it starts with '[', it is jsonarray.
			JSONArray item = (JSONArray)channel.get("item");
			
			for(int i=0; i<item.size(); i++)
			{
				JSONObject imsi = (JSONObject) item.get(i);
				
				String book_isbn = (String)imsi.get("isbn");
				String book_title = (String)imsi.get("title");
				String book_author = (String)imsi.get("author_t");
				String book_img = (String)imsi.get("cover_s_url");
				String book_sale_price = (String)imsi.get("sale_price");
				String book_description = (String)imsi.get("description");
				String book_pub_date = (String)imsi.get("pub_date");
			
				//BookDTO bookDto = new BookDTO(book_author, book_img, book_sale_price, book_description);
				BookDTO bookDto = new BookDTO(book_isbn, book_title, book_author, book_img, book_sale_price, book_description, book_pub_date);
				
				bookList.add(bookDto);
				
				boolean result=false;
				result = bookDao.bookDtoInsert(bookDto);
				if(result==false){
		   			System.out.println("failed to save Book : " + bookDto.toString());
		   			break;
		   		}
					
			}//...E.for(int i=0; i<item.size(); i++)
			
	}	 
}