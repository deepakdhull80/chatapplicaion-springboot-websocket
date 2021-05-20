package com.work.ChatApp.Model;

import java.util.ArrayList;
import java.util.List;

public class Group {
	
	String groupid;
	String groupName;
	List<User> userList;
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	@Override
	public String toString() {
		return "Group [groupid=" + groupid + ", groupName=" + groupName + ", userList=" + userList + "]";
	}
	public Group(String groupid, String groupName, List<User> userList) {
		super();
		this.groupid = groupid;
		this.groupName = groupName;
		this.userList = userList;
	}
	public Group() {
		super();
	}
	
	
}
