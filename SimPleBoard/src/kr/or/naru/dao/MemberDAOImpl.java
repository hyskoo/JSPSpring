package kr.or.naru.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import kr.or.naru.vo.MemberVO;


public class MemberDAOImpl implements MemberDAO {

	private static MemberDAOImpl instance=new MemberDAOImpl();
	private MemberDAOImpl() {}
	public static MemberDAOImpl getInstance() {
		return instance;
	}
	
	@Override
	public List<MemberVO> selectMemberList(SqlSession session) throws SQLException {
		List<MemberVO> memberList = session.selectList("Member-Mapper.selectMemberList", null);
		return memberList;
	}

	@Override
	public int selectMemberListCount(SqlSession session) throws SQLException {
		int count = session.selectOne("Member-Mapper.selectMemberListCount",null);		
		return count;
	}

	@Override
	public MemberVO selectMemberById(String id, SqlSession session) throws SQLException {
		MemberVO member = session.selectOne("Member-Mapper.selectMemberById",id);	
		return member;
	}

	@Override
	public void insertMember(MemberVO member, SqlSession session) throws SQLException {
		session.update("Member-Mapper.insertMember",member);
	}

	@Override
	public void updateMember(MemberVO member, SqlSession session) throws SQLException {
		session.update("Member-Mapper.updateMember",member);
	}

	@Override
	public void deleteMember(String id, SqlSession session) throws SQLException {
		session.update("Member-Mapper.deleteMember",id);
	}

}
