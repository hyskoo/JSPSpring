package com.jsp.request;

import java.util.Date;

import com.jsp.dto.PdsVO;

public class ModifyPdsRequest {
	private int pno;
	private String title;
	private String writer;
	private String content;
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public PdsVO toPdsVO() {
		PdsVO pds = new PdsVO();
		pds.setPno(pno);
		pds.setTitle(title);
		pds.setWriter(writer);
		pds.setContent(content);
		pds.setRegDate(new Date());
		pds.setUpdatedate(new Date());
		return pds;
	}
}
