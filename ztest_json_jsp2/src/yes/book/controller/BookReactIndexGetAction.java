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

public class BookReactIndexGetAction implements Action {
	
	BookDAO bookDao = new BookDAO();	
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{	 
		
		String zmessage = "Merry Christmas^_____^!!!";
		System.out.println("zmessage : " + zmessage);		 
		
		//...검색결과를 Request 영역에 담기.
		request.setAttribute("zmessage", zmessage);
		
		ActionForward zaction = new ActionForward();		
		
		//...Dispatcher 설정.
		zaction.setRedirect(false);
		//...이동할 페이지.
		zaction.setPath("./book_react/index.jsp");

		return zaction;	
	 }	
	 
	 
}