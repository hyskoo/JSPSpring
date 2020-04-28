package kr.pe.krh.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.pe.krh.util.SearchCriteria;
import kr.pe.krh.vo.BoardVO;

public interface BoardDAO {

	public List<BoardVO> selectBoardList(SearchCriteria cri, SqlSession session) throws SQLException;
	public int selectTotalCountOfBoardList(SearchCriteria cri, SqlSession session) throws SQLException;
	
	public BoardVO selectBoardInfoByBno(int bno, SqlSession session) throws SQLException;
	public int getBoardSeqNext(SqlSession session) throws SQLException;
	
	public void insertBoard(BoardVO board, SqlSession session) throws SQLException;
	public void updateBoard(BoardVO board, SqlSession session) throws SQLException;
	public void deleteBoard(int bno, SqlSession session) throws SQLException;
	public void increaseViewCount(int bno, SqlSession session) throws SQLException;
	
	
}
