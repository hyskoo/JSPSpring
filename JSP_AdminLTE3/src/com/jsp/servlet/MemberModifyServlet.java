package com.jsp.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.MemberVO;
import com.jsp.request.MemberRegistRequest;
import com.jsp.service.MemberServiceImpl;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.ViewResolver;

/**
 * Servlet implementation class MemberModify
 */
@WebServlet("/member/modify")
public class MemberModifyServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="member/modify";		
		String id=request.getParameter("id");		
		MemberVO member=null;
		try {
			member=MemberServiceImpl.getInstance().getMember(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("member", member);
		
		ViewResolver.view(request, response, url);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="member/modify_success";
		
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String email=request.getParameter("email");		
		String picture=request.getParameter("picture");		
		String authority = request.getParameter("authority");
		String[] phone = request.getParameterValues("phone");
		
		MemberRegistRequest memberReq = 
				new MemberRegistRequest(id,pwd,authority,email,phone,picture,name);
		
		MemberVO member = memberReq.toMemberVO();
		
		System.out.println(member);
		
		try {
			MemberServiceImpl.getInstance().modify(member);		

			HttpSession session = request.getSession();
			MemberVO loginUser=(MemberVO)session.getAttribute("loginUser");
			if(member.getId().equals(loginUser.getId())) {
				session.setAttribute("loginUser", member);
			}
			
		} catch (SQLException e) {		
			e.printStackTrace();
			url="member/modify_fail";
			String oldFileName = member.getPicture();
			String uploadPath=GetUploadPath.getUploadPath("member.picture.upload");
			File oldFile=new File(uploadPath+File.separator+oldFileName);
			if(oldFile.exists()) {
				oldFile.delete();
			}
		}
		
		request.setAttribute("id", id);
		
		ViewResolver.view(request, response, url);
	}

}






