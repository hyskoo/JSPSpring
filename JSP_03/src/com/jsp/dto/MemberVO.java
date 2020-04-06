package com.jsp.dto;

public class MemberVO {
	private String id; // 아이디
	private String pwd; // 패스워드
	private String name;
	private int enabled; // 사용여부
	private String picture; // 사진파일명
	private String authority; // 권한
	private String phone; // 전화번호
	private String address;
	private String email; // 이메일

	
	public MemberVO() { }

	public MemberVO(String id, String pwd, String name, int enabled, String picture, String authority, String phone,
			String address, String email) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.enabled = enabled;
		this.picture = picture;
		this.authority = authority;
		this.phone = phone;
		this.address = address;
		this.email = email;
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

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", enabled=" + enabled + ", picture="
				+ picture + ", authority=" + authority + ", phone=" + phone + ", address=" + address + ", email="
				+ email + "]";
	}
}
