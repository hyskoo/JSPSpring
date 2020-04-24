package com.jsp.action.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.service.BoardService;
import com.jsp.service.BoardServiceImpl;

public class BoardRemoveAction implements Action {

	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = null;

		int bno = Integer.parseInt(request.getParameter("bno"));

		try {
			boardService.remove(bno);
		} catch (SQLException e) {
			e.printStackTrace();
			url = "error/500";
			return url;
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('삭제되었습니다');");
		out.println("window.opener.location.reload(true);window.close();");
		out.println("</script>");

		return url;
	}

}
