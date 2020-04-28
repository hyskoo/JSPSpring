package kr.pe.krh.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import kr.pe.krh.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public MemberVO selectMemberInfoByLogin(MemberVO member, SqlSession session) throws SQLException {	
		return session.selectOne("Member-Mapper.selectMemberInfoByLogin", member);
	}

	
	
}
