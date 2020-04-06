package com.jsp.dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.jsp.dto.MemberVO;
import com.jsp.ibatis.config.SqlMapClientFactory;

public class MemberDaoImpl implements MemberDao {
	private SqlMapClient smc;
	
	private static MemberDaoImpl mDao;
	
	private MemberDaoImpl() {
		smc = SqlMapClientFactory.getSMClient();
	}
	
	public static MemberDaoImpl getInstance() {
		if (mDao == null) {
			mDao = new MemberDaoImpl();
		}
		return mDao;
	}

	@Override
	public MemberVO LoginCheck(MemberVO vo) throws SQLException {
		return (MemberVO) smc.queryForObject("member.LoginCheck", vo);
	}
}
