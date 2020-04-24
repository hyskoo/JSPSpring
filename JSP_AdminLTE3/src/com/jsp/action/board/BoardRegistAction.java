package com.jsp.action.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.BoardVO;
import com.jsp.request.RegistBoardRequest;
import com.jsp.service.BoardService;
import com.jsp.service.BoardServiceImpl;

public class BoardRegistAction implements Action {

	private BoardService boardService;

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = null;

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");

		BoardVO board = new RegistBoardRequest(title, content, writer).toBoardVO();

		try {
			boardService.write(board);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("window.opener.location.href='list.do';window.close();");
		out.println("</script>");

		return url;
	}

}
