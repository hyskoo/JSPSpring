package kr.pe.krh.service;

import java.sql.SQLException;

import kr.pe.krh.vo.MemberVO;

public interface MemberService {
	
	public MemberVO Login(MemberVO member) throws SQLException; 
}
