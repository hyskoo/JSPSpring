package kr.pe.krh.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import kr.pe.krh.vo.MemberVO;

public interface MemberDAO {

	public MemberVO selectMemberInfoByLogin(MemberVO member, SqlSession session) throws SQLException;
	
	
	
}
