package kr.pe.krh.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoardVO {
	private int bno;
	private String title;
	private int mno;
	private String content;
	private int viewCount;
	private Date regdate;
	private Date updatedate;
	
	private int replyCount;
	List<AttachVO> attachList;

	public BoardVO() { }

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public List<AttachVO> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<AttachVO> attachList) {
		this.attachList = attachList;
	}

	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", mno=" + mno + ", content=" + content + ", viewCount="
				+ viewCount + ", regdate=" + regdate + ", updatedate=" + updatedate + ", replyCount=" + replyCount
				+ ", attachList=" + attachList + "]";
	}

	public BoardVO(int bno, String title, int mno, String content, int viewCount, Date regdate, Date updatedate,
			int replyCount, List<AttachVO> attachList) {
		super();
		this.bno = bno;
		this.title = title;
		this.mno = mno;
		this.content = content;
		this.viewCount = viewCount;
		this.regdate = regdate;
		this.updatedate = updatedate;
		this.replyCount = replyCount;
		this.attachList = attachList;
	}



	
	
	

}