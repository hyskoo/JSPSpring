package kr.pe.krh.service;

import java.sql.SQLException;
import java.util.Map;

import kr.pe.krh.util.SearchCriteria;
import kr.pe.krh.vo.BoardVO;

public interface BoardService {

	// 목록조회
	public Map<String, Object> getBoardList(SearchCriteria cri) throws SQLException;

	// 상세보기
	public BoardVO getBoardInfoByBno(int bno) throws SQLException;
	public BoardVO getBoardInfoForModify(int bno) throws SQLException;

	// 등록
	public void write(BoardVO board) throws SQLException;

	// 수정
	public void modify(BoardVO board) throws SQLException;

	// 삭제
	public void remove(int bno) throws SQLException;

}
