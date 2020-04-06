package com.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/login.html")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		
//		out.println("doGet() execute!!");
//		out.println("<DOCTYPE!>");
//		out.println("<html>");
//		out.println("<head>");
//		out.println("<title>");
//		out.println("로그인 페이지");
//		out.println("</title>");
//		out.println("</head>");
//		out.println("<body>");
//		out.println("<form action=\"login.html\" method=\"post\" >");
//		out.println("아이디 : <input type=\"text\" name=\"id\" /> <br/>");
//		out.println("패스워드 : <input type=\"password\" name=\"pwd\" /> <br/>");
//		out.println("<input type=\"submit\" value=\"로그인\" />");
//		out.println("</form>");
//		out.println("</body>");
//		out.println("</html>");
		
		//Dispatcher는 총괄자라고 생각하면 좀편함. forward는 실제 화면전환이 아니라 사용자에게 받은 request를 다른쪽에 넘겨주는것일뿐.
		
//		/로 시작하면 WebContent부터 시작한다.
		request.getRequestDispatcher("/WEB-INF/views/loginForm.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pwd");
		
//		response.setContentType("text/html; charset=utf-8");
//		PrintWriter out = response.getWriter();
//		
//		out.println("<script>");
//		out.println("alert('로그인성공입니다.')");
//		out.println("location.href='https://www.naver.com';");
//		out.println("</script>");
		
		request.getRequestDispatcher("/WEB-INF/views/login_success.jsp").forward(request, response);
	}
}
