package com.work.ChatApp.Service;

import java.util.List;

import com.work.ChatApp.Model.Group;
import com.work.ChatApp.Model.User;

@org.springframework.stereotype.Service

public interface Service {
	
	public User loginUser(User user);
	
	
	public Group createGroup(Group group ,User user);
	
	public Group getGroupDetail(String groupId,User user);
	
	
	public List<Group> getAllGroups(User user);
	
	
	/*
	 * add user friend into group
	 * */
	public Group addUserIntoGroup(Group group, String friendUserName);
	
	/*
	 * user enter in any group
	 * 
	 * */
	public Group addUserIntoGroup(User user, String groupName);
	
	
	/*
	 * get group detail 
	 * */

	public Group getGroupDetail(String groupid);
	
}
