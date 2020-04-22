package com.jsp.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;
import com.jsp.service.BoardServiceImpl;

public class BoardModifyAction implements Action {
	
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "board/modifyBoard_success";
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		BoardVO board = new BoardVO();
		board.setBno(bno);
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		try {
			boardService.modify(board);
		} catch (SQLException e) {
			e.printStackTrace();
			url = "error/500_error";
		}
		request.setAttribute("board", board);
		
		return url;
	}

}
