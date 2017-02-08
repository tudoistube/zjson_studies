package yes.book.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import yes.book.action.Action;
import yes.book.action.ActionForward;
import yes.book.dao.BookDAO;
import yes.book.dto.BookDTO;
import zjson.util.HttpClientGet;

public class BookListAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{	 
		 
		//...get json-data from daum api.
		String key = "3250431f017bec03f26cc7781dfca95b"; //...자신의 api key 를 넣음.
		String bookName = request.getParameter("bookName");
		System.out.println("검색할 책 이름 : " + bookName);		 
		 
		// 요청할 주소를 넣으세요
		//...http://developers.daum.net/services/apis/search/book
		//String url = "https://apis.daum.net/search/book?apikey=3250431f017bec03f26cc7781dfca95b&q=CSS&output=json";
		String url = "https://apis.daum.net/search/book?apikey="+key+"&q="+bookName+"&result=5&output=json";
		
		// 다음 서버로 부터 json 받아오기 
		String json = HttpClientGet.get_JSONDATA(url);	
		System.out.println("json : " + json);		 

		/*...JSON 파싱하기 */
		JSONArray jsonArr = parsingJson(json);
		System.out.println("jsonArr : " + jsonArr);
		
			
		//HttpSession session = request.getSession();
		//session.setAttribute("session_list",list);
		
		boolean blResult = true;
		String zresult = null;
		
		
		//if(blResult)
		if(json.isEmpty() != true)
		{
			zresult = "data found in json.";
		}else{
			zresult = "data not found in json.";
		}
		
		//...검색결과를 Request 영역에 담기.
		request.setAttribute("zjson", json);
		request.setAttribute("zjsonArr", jsonArr);
		request.setAttribute("zresult", zresult);
		
		ActionForward zaction = new ActionForward();		
		
		//...Dispatcher 설정.
		zaction.setRedirect(false);
		//...이동할 페이지.
		zaction.setPath("./book_react/bookList.jsp");

		return zaction;	
	 }	
	 
	 private JSONArray parsingJson(String json) throws ParseException {
			
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(json);
			
			//...get channel.
			JSONObject channel = (JSONObject)obj.get("channel");
			
			//...get item array from channel.
			//...because it starts with '[', it is jsonarray.
			JSONArray item = (JSONArray)channel.get("item");
			
			return item;
			
	 }
	 
}