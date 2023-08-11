package com.care.dbQuiz.dto;
/*
CREATE TABLE db_quiz(
id varchar2(100),
pw varchar2(100),
username varchar2(100),
address varchar2(300),
mobile varchar2(30),
primary key(id)
);
COMMIT;
 */
public class MemberDTO {
	private String id;
	private String pw;
	private String confirm;
	private String username;
	private String address;
	private String mobile;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
