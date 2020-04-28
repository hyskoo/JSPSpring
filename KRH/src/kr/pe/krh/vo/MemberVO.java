package kr.pe.krh.vo;

import java.util.Date;

public class MemberVO {
	private int mno;
	private String id;
	private String pwd;
	private String name;
	private Date birthday;
	private int phone;
	private String email;
	private String addr;
	private String picturepath;
	private String authority;
	private int enabled;
	private Date regdate;
	private Date updatedate;
	
	public MemberVO() { }
	
	public MemberVO(int mno, String id, String pwd, String name, Date birthday, int phone, String email, String addr,
			String picturepath, String authority, int enabled, Date regdate, Date updatedate) {
		super();
		this.mno = mno;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.addr = addr;
		this.picturepath = picturepath;
		this.authority = authority;
		this.enabled = enabled;
		this.regdate = regdate;
		this.updatedate = updatedate;
	}
	
	@Override
	public String toString() {
		return "MemberVO [mno=" + mno + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", birthday=" + birthday
				+ ", phone=" + phone + ", email=" + email + ", addr=" + addr + ", picturepath=" + picturepath
				+ ", authority=" + authority + ", enabled=" + enabled + ", regdate=" + regdate + ", updatedate="
				+ updatedate + "]";
	}

	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPicturepath() {
		return picturepath;
	}
	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
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
	
	
	
}
