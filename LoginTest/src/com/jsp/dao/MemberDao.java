package com.jsp.dao;

import java.sql.SQLException;

import com.jsp.dto.MemberVO;

public interface MemberDao {
	public MemberVO LoginCheck(MemberVO vo) throws SQLException;
}
