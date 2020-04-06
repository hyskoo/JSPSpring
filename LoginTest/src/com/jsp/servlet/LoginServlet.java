package com.jsp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;
import com.jsp.service.MemberServiceImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberService mService = MemberServiceImpl.getInstance();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/common/loginForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String url = "/WEB-INF/views/common/login_success.jsp";
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String check = request.getParameter("terms");
		
		
		MemberVO memVO = new MemberVO();
		memVO.setId(id);
		memVO.setPwd(pwd);

		
		// 로그인후 결과값체크. 1일경우 해당 사용자가 존재한다.
		MemberVO loginCheck = mService.LoginCheck(memVO);
		if (loginCheck == null) {
			//사용자가 입력한 아이디 값을 유지한다.
			request.setAttribute("id", id);
			url = "/WEB-INF/views/common/loginForm.jsp";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginCheck);
			if (check != null && check.equals("on")) {
				System.out.println("1111");
			}
		}
		
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
