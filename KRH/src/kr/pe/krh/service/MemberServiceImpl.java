package kr.pe.krh.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.pe.krh.dao.MemberDAO;
import kr.pe.krh.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	private SqlSessionFactory sessionFactory;
	public void setSessionFactory(SqlSessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}

	@Override
	public MemberVO Login(MemberVO member) throws SQLException{
		SqlSession session = sessionFactory.openSession();
		
		MemberVO memberInfo = memberDAO.selectMemberInfoByLogin(member, session);
		
		session.close();
		return memberInfo;
	}
	
	

}
