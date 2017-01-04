import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HangulServlet
 */
@WebServlet("/ch3_1_loginKorean")
public class Ch3_1_LoginKorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Ch3_1_LoginKorServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * 1번 : Ch3_UtilKorean 이용. protected void doGet(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException { //
	 * TODO Auto-generated method stub
	 * response.setContentType("text/html;charset=euc-kr");
	 * 
	 * String id = request.getParameter("id"); String korId =
	 * Ch3_UtilKorean.toKor(id); String password =
	 * request.getParameter("password");
	 * 
	 * PrintWriter out = response.getWriter(); out.println("original = " + id);
	 * out.println(); out.println("hangul Process = " + korId); out.println("");
	 * out.println("password = " + password); }
	 */

	/*
	 * 2번 : server.xml :: Connector 태그에서 Get 방식일 경우 한글처리 속성 
	 * URIEncoding="euc-kr" 이용
	 *
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=euc-kr");

		String id = request.getParameter("id");

		String password = request.getParameter("password");

		PrintWriter out = response.getWriter();
		out.println("original = " + id);
		out.println("password = " + password);
	}
	 * /
	/*
	 * 3번 : server.xml :: Connector 태그에서 Get 방식일 경우 한글처리 속성 
	 * useBodyEncodingForURI="true" 이용
	 * request.setCharacterEncoding("euc-kr"); 필요.
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//...testOnly.
		//request.setCharacterEncoding("UTF-8");
		//response.setContentType("text/html;charset=UTF-8");

		String id = request.getParameter("id");

		String password = request.getParameter("password");

		PrintWriter out = response.getWriter();
		out.println("id = " + id);
		out.println("password = " + password);
	}
	
}
