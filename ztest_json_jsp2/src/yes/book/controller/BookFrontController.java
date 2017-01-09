package yes.book.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yes.book.action.Action;
import yes.book.action.ActionForward;

 public class BookFrontController 
 	extends javax.servlet.http.HttpServlet 
 	implements javax.servlet.Servlet {
	 
	 protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
	 	throws ServletException, IOException {
		 
		 /*
		  * ...요청된 전체 URL 중 포트 번호 다음부터 마지막 문자열까지 반환된다.
		  * ...예) http://localhost:8080/big/list.do
		  * ...	↓
		  * ...	big/list.do 가 반환됨.
		  */
		 String RequestURI=request.getRequestURI();
		 
		 /*
		  * ...Context 경로가 반환됨.
		  * .../big/list.do	→  /big 이 반환됨.
		  */
		 String contextPath=request.getContextPath();
		 
		 /*
		  * ...요청종료를 반환받는 부분이다.
		  * ...전체 URI문자열(/big/list.do)에서 컨텍스트 경로 문자열(/big)의 길이 값의 인덱스 위치의 문자로부터
		  * ...마지막 문자까지 추출하여 요청 종료를 파악함.
		  * ...최종적으로 추출되는 문자열은 /list.do 가 됨.
		  */
		 String command=RequestURI.substring(contextPath.length());
		 
		 /*
		  * ...비즈니스 요청을 처리한 후 뷰 페이지로 포워딩하는 작업을 담당하는 클래스.
		  */
		 ActionForward forward=null;
		 
		 /*
		  * ...실질적으로 비즈니스 로직을 처리하는 부분. 인터페이스로 구현해서 각 클래스 메소드를 규격화함.
		  */
		 Action action=null;
		
		if(command.equals("/BookListAddPost.yes")){
			action = new BookListAddPostAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/BookListJsonPOST.yes")){
			action = new BookListJsonPostAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(command.equals("/BookSearchInputGET.yes")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./book_json/bookSearchInput.jsp");
		}
		
		if(forward != null){
			/*
			 * ...전송되어 온 포워딩 방식에 따라 isRedirect() 값에 따라 dispatch 방식 또는 redirect 방식으로 페이지 포워딩 처리.
			 */
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher=
					request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
	 }
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		doProcess(request,response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		doProcess(request,response);
	}	  	 
}