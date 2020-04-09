package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dto.MemberVO;
import com.jsp.mybatis.OracleMyBatisSqlSessionFactoryBuilder;

public class MemberDAOImpl implements MemberDAO {

	private static MemberDAOImpl instance=new MemberDAOImpl();
	private MemberDAOImpl() {}
	public static MemberDAOImpl getInstance() {
		return instance;
	}
	
	// SqlSessionFactory
	private SqlSessionFactory sessionFactory 
			= OracleMyBatisSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	// 원래 여기에 finally에 session.close를 해야된다.
	
	@Override
	public List<MemberVO> selectMemberList() throws SQLException {
		SqlSession session = sessionFactory.openSession();
		List<MemberVO> memberList = null;
		try {
			memberList = session.selectList("Member-Mapper.selectMemberList", null);
		} finally {
			session.close();
		}
		return memberList;
	}

	@Override
	public int selectMemberListCount() throws SQLException {
		int count=0;		
		SqlSession session=sessionFactory.openSession();
		count=session.selectOne("Member-Mapper.selectMemberListCount",null);		
		session.close();		
		return count;
	}

	@Override
	public MemberVO selectMemberById(String id) throws SQLException {
		SqlSession session=sessionFactory.openSession();		
		MemberVO member=session.selectOne("Member-Mapper.selectMemberById",id);			
		session.close();		
		return member;
	}

	@Override
	public void insertMember(MemberVO member) throws SQLException {
		SqlSession session=sessionFactory.openSession(true);
		session.update("Member-Mapper.insertMember",member);
		session.close();

	}

	@Override
	public void updateMember(MemberVO member) throws SQLException {
		SqlSession session=sessionFactory.openSession(true);
		session.update("Member-Mapper.updateMember",member);
		session.close();

	}

	@Override
	public void deleteMember(String id) throws SQLException {
		SqlSession session=sessionFactory.openSession(true);
		session.update("Member-Mapper.deleteMember",id);
		session.close();

	}

}
