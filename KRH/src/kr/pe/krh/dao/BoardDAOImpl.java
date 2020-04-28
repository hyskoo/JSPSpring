package kr.pe.krh.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.pe.krh.util.SearchCriteria;
import kr.pe.krh.vo.BoardVO;

public class BoardDAOImpl implements BoardDAO {

	@Override
	public List<BoardVO> selectBoardList(SearchCriteria cri, SqlSession session) throws SQLException {
		
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<BoardVO> boardList = session.selectList("Board-Mapper.selectSearchBoardList", cri, rowBounds);
		
		return boardList;
		
	}

	@Override
	public int selectTotalCountOfBoardList(SearchCriteria cri, SqlSession session) throws SQLException {
		return session.selectOne("Board-Mapper.selectTotalCountOfBoardList", cri);
	}

	@Override
	public void insertBoard(BoardVO board, SqlSession session) throws SQLException {
		session.update("Board-Mapper.insertBoard", board);
	}

	@Override
	public void updateBoard(BoardVO board, SqlSession session) throws SQLException {
		session.update("Board-Mapper.updateBoard", board);
	}

	@Override
	public void deleteBoard(int bno, SqlSession session) throws SQLException {
		session.update("Board-Mapper.deleteBoard", bno);
	}

	@Override
	public void increaseViewCount(int bno, SqlSession session) throws SQLException {
		session.update("Board-Mapper.increaseViewCount", bno);
	}

	@Override
	public BoardVO selectBoardInfoByBno(int bno, SqlSession session) throws SQLException {
		return session.selectOne("Board-Mapper.selectBoardInfoByBno", bno);
	}

	@Override
	public int getBoardSeqNext(SqlSession session) throws SQLException {
		return session.selectOne("Board-Mapper.getBoardSeqNext");
	}

}
