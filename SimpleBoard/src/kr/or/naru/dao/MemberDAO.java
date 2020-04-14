package kr.or.naru.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.naru.vo.MemberVO;

public interface MemberDAO {
	
	//회원리스트
	List<MemberVO> selectMemberList(SqlSession session) throws SQLException;
	
	//전체 회원리스트 개수	
	int selectMemberListCount(SqlSession session)throws SQLException;
	
	
	//회원정보 조회
	MemberVO selectMemberById(String id, SqlSession session) throws SQLException;
	
	//회원정보 추가
	void insertMember(MemberVO member, SqlSession session) throws SQLException;		
	
	//회원정보 수정
	void updateMember(MemberVO member, SqlSession session) throws SQLException;
	
	//회원정보 삭제
	void deleteMember(String id, SqlSession session) throws SQLException;	
	
}




