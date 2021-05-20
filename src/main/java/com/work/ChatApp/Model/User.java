package com.work.ChatApp.Model;

public class User {
	
	private long userid;
	private String userName;
	private String password;
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(long userid, String userName, String password) {
		super();
		this.userid = userid;
		this.userName = userName;
		this.password = password;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", userName=" + userName + ", password=" + password + "]";
	}
	

}
