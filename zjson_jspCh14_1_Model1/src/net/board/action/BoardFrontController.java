package net.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 public class BoardFrontController 
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
		
		if(command.equals("/BoardWrite.bo")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/qna_board_write.jsp");
		}else if(command.equals("/BoardReplyAction.bo")){
			action = new BoardReplyAction();
			try{
				/*
				 * ...command 에 해당하는 Action 객체를 실행하여 비즈니스 로직을 실행한 후
				 * ...(비즈니스 로직은 execute 메서드를 호출해서 실행하고 있다) 리턴 값으로 각각의 ActionForward 객체를 반환함.
				 */
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/BoardDelete.bo")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/qna_board_delete.jsp");
		}else if(command.equals("/BoardModify.bo")){
			action = new BoardModifyView();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
	 	}else if(command.equals("/BoardAddAction.bo")){
			action  = new BoardAddAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardReplyView.bo")){
			action = new BoardReplyView();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/BoardModifyAction.bo")){
			action = new BoardModifyAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/BoardDeleteAction.bo")){
			action = new BoardDeleteAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/BoardList.bo")){
			action = new BoardListAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/BoardDetailAction.bo")){
			action = new BoardDetailAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
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