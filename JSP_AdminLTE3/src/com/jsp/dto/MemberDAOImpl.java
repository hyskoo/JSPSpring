package com.jsp.dto;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jsp.dao.MemberDAO;
import com.jsp.mybatis.OracleMyBatisSqlSessionFactoryBuilder;

public class MemberDAOImpl implements MemberDAO {
	
	private static MemberDAO instacne = new MemberDAOImpl();
	private MemberDAOImpl () {}
	public static MemberDAO getInstance() {
		return instacne;
	}
	
	// SqlSessionFactory
	private SqlSessionFactory sessionFactory = OracleMyBatisSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	
	@Override
	public List<MemberVO> selectMemberList() throws SQLException {
		SqlSession session = sessionFactory.openSession();
		List<MemberVO> memberList = session.selectList("Member-Mapper.selectMemberList", null);
		session.close();
		return memberList;
	}

	@Override
	public int selectMemberListCount() throws SQLException {
		int count = 0;
		SqlSession session = sessionFactory.openSession();
		count = session.selectOne("Member-Mapper.selectMemberListCount", null);
		session.close();
		return count;
	}

	@Override
	public MemberVO selectMemberById(String id) throws SQLException {
		MemberVO member = null;
		SqlSession session = sessionFactory.openSession();
		member = session.selectOne("Member-Mapper.selectMemberById", id);
		session.close();
		return member;
	}

	@Override
	public void insertMember(MemberVO member) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		session.update("Member-Mapper.insertMember", member);
		session.close();
	}

	@Override
	public void updateMember(MemberVO member) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		session.update("Member-Mapper.updateMember", member);
		session.close();
	}

	@Override
	public void deleteMember(String id) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		session.update("Member-Mapper.deleteMember", id);
		session.close();
	}

}