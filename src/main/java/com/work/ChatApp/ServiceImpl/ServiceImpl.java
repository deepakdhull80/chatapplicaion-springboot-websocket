package com.work.ChatApp.ServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.work.ChatApp.Model.Group;
import com.work.ChatApp.Model.User;
import com.work.ChatApp.Service.Service;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

	
	private List<User> userList = new ArrayList<>(Arrays.asList(new User(123L,"test","test"),new User(124L,"test1","test") , new User(125L,"test3","test")));
	
	private List<Group> groupList = new ArrayList<>(Arrays.asList(new Group("group1","Amazing",new ArrayList<>( Arrays.asList(new User(123L,"test","test"),new User(124L,"test1","test"))) )));

	
	
	@Override
	public User loginUser(User user) {
		User res = null;
		try {
			
			for(User u: userList) {
				if(u.getUserName().equals(user.getUserName())) {
					return u;
				}
			}
			
//			System.out.println(res);
		}catch(Exception e){
//			e.printStackTrace();
			
		}
		
		return res;
	}

	
	@Override
	public Group createGroup(Group group , User user) {
		
		for(Group g :groupList ) {
			if(g.getGroupName().trim().equals(group.getGroupName())) {
				// group exist
				
				g.getUserList().add(user);
				System.out.println("Group already existed.\nUser added in group");
				return g;
			}
		}
		int groupid = groupList.size()+1;
		group.setGroupid("group"+groupid);
		group.setUserList( new ArrayList<User>());
		group.getUserList().add(user);
		groupList.add(group);
		return group;
	}


	@Override
	public Group getGroupDetail(String groupId, User user) {
		
		for(Group g: groupList) {
			if(g.getGroupid().equals(groupId)) {
				for(User u: g.getUserList()) {
					System.out.println("user: "+u.getUserid() +" and input user:"+user.getUserid());
					if(u.getUserid()== user.getUserid()) {
						
						return g;
					}
				}
				return new Group();
			}
		}
		
		return null;
	}
	
	public List<Group> getAllGroups(User user){
		List<Group> gp = new ArrayList<Group>();
		for(Group g: groupList) {
			for(User u: g.getUserList()) {
				if(u.getUserid()==user.getUserid()) {
					gp.add(g);
				}
			}
		}
		
		return gp;
	}


	@Override
	public Group addUserIntoGroup(Group group, String friendUserName) {
		try {
		User userTmp = this.getUser(friendUserName);
		
		if(userTmp==null) {
			System.out.println("user not present");
			return null;
		}
		System.out.println(group.getUserList());
		group.getUserList().add(userTmp);
		return group;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public Group addUserIntoGroup(User user, String groupName) {
		
		for(Group gp : groupList) {
			if(gp.getGroupName().trim().equals(groupName)) {
				gp.getUserList().add(user);
				return gp;
			}
		}
		
		return null;
	}
	
	
	private User getUser(String userName) {
		for(User user: userList) {
			if(user.getUserName().trim().equals(userName)) {
				return user;
			}
		}
		// not exist in list
		return null;
	}


	@Override
	public Group getGroupDetail(String groupid) {
		
		for(Group g : groupList) {
			if(groupid.equals(g.getGroupid())){
				return g;
			}
		}
		
		return null;
	}
	
	
}
