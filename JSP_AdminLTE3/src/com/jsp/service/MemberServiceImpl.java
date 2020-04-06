package com.jsp.service;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dao.MemberDAO;
import com.jsp.dto.MemberDAOImpl;
import com.jsp.dto.MemberVO;
import com.jsp.exception.InvaildPasswordException;
import com.jsp.exception.NotFoundIDException;

public class MemberServiceImpl implements MemberService {
	
	private static MemberService instacne = new MemberServiceImpl();
	private MemberServiceImpl() {}
	public static MemberService getInstance() {
		return instacne;
	}
	
	private MemberDAO memberDAO = MemberDAOImpl.getInstance();
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public void login(String id, String pwd) throws SQLException, NotFoundIDException, InvaildPasswordException {
		MemberVO member = memberDAO.selectMemberById(id);
		if (member == null) throw new NotFoundIDException();
		if (!pwd.equals(member.getPwd())) throw new InvaildPasswordException();
	}

	@Override
	public List<MemberVO> getMemberList() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO getMember(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void regist(MemberVO member) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modify(MemberVO member) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) throws SQLException {
		// TODO Auto-generated method stub

	}

}
