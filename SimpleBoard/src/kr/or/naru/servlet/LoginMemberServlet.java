package kr.or.naru.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.naru.exception.InvalidPasswordException;
import kr.or.naru.exception.NotFoundIDException;
import kr.or.naru.service.MemberServiceImpl;
import kr.or.naru.util.ViewResolver;
import kr.or.naru.vo.MemberVO;

@WebServlet("/commons/login")
public class LoginMemberServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "commons/login";
		
		ViewResolver.viewReolve(request, response, url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "commons/login_success";
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		try {
			MemberServiceImpl.getInstance().login(id, pwd);
			
			MemberVO member = MemberServiceImpl.getInstance().getMember(id);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", member);
			
		} catch (SQLException e) {
			e.printStackTrace();
			url="error/500_error";
			request.setAttribute("exception", e);
		} catch (NotFoundIDException | InvalidPasswordException e) {
			url="commons/loginForm";
			request.setAttribute("msg", e.getMessage());
		}
		
		ViewResolver.viewReolve(request, response, url);
		
	}

}
