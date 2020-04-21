package com.jsp.action.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dispatcher.ViewResolver;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberServiceImpl;
import com.jsp.utils.MakeLogForException;

public class MemberDetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 화면결정
		String url = "member/detail";

		// 파라미터처리
		String id = request.getParameter("id");

		// 서비스 요청 -> 결과
		// 결과에 따른 화면분할
		MemberVO member = null;
		try {
			member = MemberServiceImpl.getInstance().getMember(id);
		} catch (SQLException e) {
			e.printStackTrace();
			MakeLogForException.makeLog(request, e);

			url = "error/500_error";
			request.setAttribute("exception", e);
		}

		if (member == null) {
			url = "member/detail_fail";
		}

		request.setAttribute("member", member);

		return url;
	}

}
