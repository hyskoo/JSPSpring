package kr.pe.krh.action.commons;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.pe.krh.action.Action;
import kr.pe.krh.service.MemberService;
import kr.pe.krh.vo.MemberVO;

public class LoginAction implements Action {
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "commons/login_success";
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPwd(pwd);
		
		HttpSession session = request.getSession();
		
		try {
			MemberVO login = memberService.Login(member);
			session.setAttribute("loginUser", login);
			session.setMaxInactiveInterval(60*60);
			
		} catch (SQLException e) {
			e.printStackTrace();
			url = "error/500_error";
		}
		
		return url;
	}

}
