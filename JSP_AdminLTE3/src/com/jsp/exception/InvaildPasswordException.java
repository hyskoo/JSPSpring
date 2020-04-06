package com.jsp.exception;

public class InvaildPasswordException extends Exception {
	
	public InvaildPasswordException() {
		super("패스워드가 일치하지 않습니다");
	}
}
