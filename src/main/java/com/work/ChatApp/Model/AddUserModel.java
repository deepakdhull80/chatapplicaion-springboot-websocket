package com.work.ChatApp.Model;

public class AddUserModel {

	String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "AddUserModel [userName=" + userName + "]";
	}
	
}
