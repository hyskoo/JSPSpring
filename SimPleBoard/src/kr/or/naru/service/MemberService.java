package kr.or.naru.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.naru.exception.InvalidPasswordException;
import kr.or.naru.exception.NotFoundIDException;
import kr.or.naru.vo.MemberVO;


public interface MemberService {
	
	//로그인
	void login(String id, String pwd)throws SQLException,
										NotFoundIDException,InvalidPasswordException;
	
	//회원리스트조회
	List<MemberVO> getMemberList()throws SQLException;	
	
	//회원정보조회
	MemberVO getMember(String id)throws SQLException;
	
	//회원등록
	void regist(MemberVO member)throws SQLException;	
	
	//회원수정
	void modify(MemberVO member)throws SQLException;
	
	//회원삭제
	void remove(String id)throws SQLException;
	
}
