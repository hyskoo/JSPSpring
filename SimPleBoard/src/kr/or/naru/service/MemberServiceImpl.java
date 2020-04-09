package kr.or.naru.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.naru.dao.MemberDAO;
import kr.or.naru.dao.MemberDAOImpl;
import kr.or.naru.exception.InvalidPasswordException;
import kr.or.naru.exception.NotFoundIDException;
import kr.or.naru.vo.MemberVO;
import mybatis.OracleMyBatisSqlSessionFactoryBuilder;

public class MemberServiceImpl implements MemberService {

	// 싱글톤 패턴 구현
	private static MemberServiceImpl instance = new MemberServiceImpl();
	private MemberServiceImpl() {}
	public static MemberServiceImpl getInstance() {
		return instance;
	}
	
	private MemberDAO memberDAO=MemberDAOImpl.getInstance();
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO=memberDAO;
	}
	
	// SqlSessionFactory
	private SqlSessionFactory sessionFactory = OracleMyBatisSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	
	@Override
	public void login(String id, String pwd) throws SQLException, NotFoundIDException, InvalidPasswordException {
		SqlSession session = sessionFactory.openSession();
		try {
			MemberVO member = memberDAO.selectMemberById(id, session);
			if (member == null)	throw new NotFoundIDException();
			if (!pwd.equals(member.getPwd())) throw new InvalidPasswordException();
		} finally {
			session.close();
		}
	}

	@Override
	public List<MemberVO> getMemberList() throws SQLException {
		SqlSession session = sessionFactory.openSession();
		List<MemberVO> memberList = null;
		try {
			memberList = memberDAO.selectMemberList(session);
		} finally {
			session.close();
		}
		return memberList;
	}

	@Override
	public MemberVO getMember(String id) throws SQLException {
		SqlSession session = sessionFactory.openSession();
		MemberVO member = null;
		try {
			member = memberDAO.selectMemberById(id, session);
		} finally {
			session.close();
		}
		return member;
	}

	@Override
	public void regist(MemberVO member) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try {
			memberDAO.insertMember(member, session);
		} finally {
			session.close();
		}
	}

	@Override
	public void modify(MemberVO member) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try {
			memberDAO.updateMember(member, session);
		} finally {
			session.close();
		}
	}

	@Override
	public void remove(String id) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try {
			memberDAO.deleteMember(id, session);
		} finally {
			session.close();
		}
	}

}
