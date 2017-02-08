package yes.book.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yes.book.action.Action;
import yes.book.action.ActionForward;

public class SimpleTestGetAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{	 
		
		String zmessage = "Simple Test";
		System.out.println("zmessage : " + zmessage);		 
		
		//...검색결과를 Request 영역에 담기.
		request.setAttribute("zmessage", zmessage);
		
		ActionForward zaction = new ActionForward();		
		
		//...Dispatcher 설정.
		zaction.setRedirect(false);
		//...이동할 페이지.
		zaction.setPath("./book_react/simpleTest.jsp");

		return zaction;	
	 }	
	 
	 
}