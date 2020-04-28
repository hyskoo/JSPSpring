package kr.pe.krh.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.pe.krh.dao.BoardDAO;
import kr.pe.krh.util.PageMaker;
import kr.pe.krh.util.SearchCriteria;
import kr.pe.krh.vo.BoardVO;

public class BoardServiceImpl implements BoardService {
	
	private BoardDAO boardDAO;
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	private SqlSessionFactory sessionFactory;
	public void setSessionFactory(SqlSessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	

	@Override
	public Map<String, Object> getBoardList(SearchCriteria cri) throws SQLException {
		SqlSession session = sessionFactory.openSession();
		
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<BoardVO> boardList = boardDAO.selectBoardList(cri, session);
		int totalCount = boardDAO.selectTotalCountOfBoardList(cri, session);
		
//		for (BoardVO board : boardList) {
//			int replyCount = 0;
//			board.setReplyCount(replyCount);
//		}
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		dataMap.put("boardlist", boardList);
		dataMap.put("pageMaker", pageMaker);
		
		session.close();
		return dataMap;
	}

	@Override
	public BoardVO getBoardInfoByBno(int bno) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
			boardDAO.increaseViewCount(bno, session);
		session.close();
		
		session = sessionFactory.openSession();
			BoardVO board = boardDAO.selectBoardInfoByBno(bno, session);
		session.close();
		return board;
	}

	@Override
	public BoardVO getBoardInfoForModify(int bno) throws SQLException {
		SqlSession session = sessionFactory.openSession();
			BoardVO board = boardDAO.selectBoardInfoByBno(bno, session);
		session.close();
		return board;
	}

	@Override
	public void write(BoardVO board) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		
		boardDAO.insertBoard(board, session);
		
		session.close();
	}

	@Override
	public void modify(BoardVO board) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		
		boardDAO.updateBoard(board, session);
		
		session.close();
	}

	@Override
	public void remove(int bno) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		
		boardDAO.deleteBoard(bno, session);
		
		session.close();
	}

}
