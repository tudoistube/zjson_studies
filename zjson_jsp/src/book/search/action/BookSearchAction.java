package book.search.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ActionManager.ActionForward;
import ActionManager.ActionInterface;
import book.dto.BookDTO;
import zjson.util.HttpClientGet;

public class BookSearchAction implements ActionInterface {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String key = ""; //...자신의 key 를 넣음.
		String book = request.getParameter("bookName");
		System.out.println("검색할 책 이름 : " + book);
		
		// 요청할 주소를 넣으세요
		String url = "https://apis.daum.net/search/book?apikey=3250431f017bec03f26cc7781dfca95b&q=다음카카오&output=json";
		
		// 다음 서버로 부터 json 받아오기 
		String json = HttpClientGet.get_JSONDATA(url);	
		System.out.println("반환된 JSON DATA : " + json);
		
		/*...JSON 파싱하기 */
		
		//ArrayList<BookDTO> list = BoardDAO.getList();
		ArrayList<BookDTO> list = new ArrayList<BookDTO>();
		
		//...파싱해서 책 담기.
		parsingJson(list, json);
		
		ActionForward zaction = new ActionForward();
		
		System.out.println(list.size());
		
		if (list.size() > 0) 
		{			
			//HttpSession session = request.getSession();
			//session.setAttribute("session_list",list);

			//...검색결과를 Request 영역에 담기.
			request.setAttribute("resultBook", list);			

			//...Dispatcher 설정.
			zaction.setRedirect(false);
			//...이동할 페이지.
			zaction.setPath("./bookList.jsp");
			
		} else
		{
			String msg = "Error occured...";
			request.setAttribute("errMsg", msg);
			zaction.setRedirect(false);
			zaction.setPath("Error.jsp");
		}

		System.out.println("...E.execute....");

		return zaction;
	}

	private void parsingJson(ArrayList<BookDTO> bookList, String json) throws ParseException {
		
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
			
			String author = (String)imsi.get("author_t");
			String book_img = (String)imsi.get("cover_s_url");
			String book_sale_price = (String)imsi.get("sale_price");
			String book_description = (String)imsi.get("description");
		
			BookDTO bookDto = new BookDTO(author, book_img, book_sale_price, book_description);
			
			bookList.add(bookDto);
				
		}//...E.for(int i=0; i<item.size(); i++)
		
	}

}

































