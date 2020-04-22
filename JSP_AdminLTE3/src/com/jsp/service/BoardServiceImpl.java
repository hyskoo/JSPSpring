package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.dao.BoardDAO;
import com.jsp.dao.ReplyDAO;
import com.jsp.dto.BoardVO;
import com.jsp.request.PageMaker;
import com.jsp.request.SearchCriteria;

public class BoardServiceImpl implements BoardService {
	
	private BoardDAO boardDAO;
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	private ReplyDAO replyDAO;
	public void setReplyDAO(ReplyDAO replyDAO){
		this.replyDAO=replyDAO;
	}

	@Override
	public Map<String, Object> getBoardList(SearchCriteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		List<BoardVO> boardList = boardDAO.selectBoardCriteria(cri);
		int totalCount = boardDAO.selectBoardCriteriaTotalCount(cri);
		
		for (BoardVO board : boardList) {
			int replyCnt = replyDAO.countReply(board.getBno());
			board.setReplycnt(replyCnt);
		}
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		dataMap.put("boardList", boardList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}

	@Override
	public BoardVO getBoard(int bno) throws SQLException {
		boardDAO.increaseViewCnt(bno);
		BoardVO boardByBno = boardDAO.selectBoardByBno(bno);
		return boardByBno;
	}

	@Override
	public BoardVO getBoardForModify(int bno) throws SQLException {
		BoardVO boardByBno = boardDAO.selectBoardByBno(bno);
		return boardByBno;
	}

	@Override
	public void write(BoardVO board) throws SQLException {
		int bno = boardDAO.selectBoardSeqNext();
		board.setBno(bno);
		boardDAO.insertBoard(board);
	}

	@Override
	public void modify(BoardVO board) throws SQLException {
		boardDAO.updateBoard(board);
	}

	@Override
	public void remove(int bno) throws SQLException {
		boardDAO.deleteBoard(bno);
	}

}
