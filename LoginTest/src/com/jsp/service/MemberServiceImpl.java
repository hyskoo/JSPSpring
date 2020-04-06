package com.jsp.service;

import java.sql.SQLException;

import com.jsp.dao.MemberDao;
import com.jsp.dao.MemberDaoImpl;
import com.jsp.dto.MemberVO;

public class MemberServiceImpl implements MemberService {
	private MemberDao mDao;
	
	private static MemberService mService;
	
	private MemberServiceImpl() {
		mDao = MemberDaoImpl.getInstance();
	}
	
	public static MemberService getInstance() {
		if (mService == null) {
			mService = new MemberServiceImpl();
		}
		return mService;
	}

	@Override
	public MemberVO LoginCheck(MemberVO vo) {
		MemberVO memVO = null;
		try {
			memVO = mDao.LoginCheck(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memVO;
	}
	
}
