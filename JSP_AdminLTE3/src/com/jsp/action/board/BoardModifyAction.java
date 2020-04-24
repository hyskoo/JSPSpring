package com.jsp.action.board;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.BoardVO;
import com.jsp.request.ModifyBoardRequest;
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
		// 파라메터 저장
		int bno = Integer.parseInt(request.getParameter("bno"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");

		String page = request.getParameter("page");
		String perPageNum = request.getParameter("perPageNum");
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");

		// 화면결정
		String url = "redirect:board/detail.do?bno=" + bno;
		// url 파라메터를 String 으로 만들경우 한글깨짐방지
		url += "&page=" + page + "&perPageNum=" + perPageNum + "&searchType=" + searchType + "&keyword="
				+ URLEncoder.encode(keyword, "utf-8");

		BoardVO board = new ModifyBoardRequest(bno, title, content, writer).toBoardVO();

		try {
			boardService.modify(board);
		} catch (SQLException e) {
			e.printStackTrace();
			url = "error/500.jsp";
		}
		// request.setAttribut() 없음.

		// 화면리턴
		return url;
	}

}
