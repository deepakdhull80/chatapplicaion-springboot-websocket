package com.work.ChatApp.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.work.ChatApp.Model.AddUserModel;
import com.work.ChatApp.Model.Group;
import com.work.ChatApp.Model.Message;
import com.work.ChatApp.Model.User;
import com.work.ChatApp.Service.Service;

@Controller
public class WebSocketController {
	
	@Autowired
	SimpMessagingTemplate messageTemplate;
	
	@Autowired
	Service service;
	
	@MessageMapping("/group.adduser")
	@SendTo("/topic/chat")
	public Message addUser(@Payload Message message,SimpMessageHeaderAccessor headerAccessor) {
		return message;
	}
	
	@MessageMapping("/group.senduser")
	@SendTo("/topic/chat")
	public Message sendMessage(@Payload Message message) {
//		System.out.println(message);
		return message;
	}
	
	
	@MessageMapping("/group.message/{groupid}")
	public void sendToGroup(@DestinationVariable("groupid") String groupid,@Payload Message message) {
		
		messageTemplate.convertAndSend("/topic/message/"+groupid, message);
		
	}
	
	@MessageMapping("/group.add/{groupid}")
	public void addToGroup(@DestinationVariable("groupid") String groupid,@Payload AddUserModel user) {
		
		
		Group group = service.getGroupDetail(groupid);
		
		
		System.out.println(group);
		System.out.println(user);
		Map<String,String> rp;
		
		if(group==null) {
			rp = new HashMap<>();
			rp.put("message", "Group not exist.");
			messageTemplate.convertAndSend("/topic/notify/"+groupid, rp);
		}
		
		Group res = service.addUserIntoGroup(group, user.getUserName());
		
		if(res==null) {
			rp = new HashMap<>();
			rp.put("message", "User not exist.");
			messageTemplate.convertAndSend("/topic/notify/"+groupid, rp);
		}
		
		
		
		rp = new HashMap<>();
		rp.put("message", user.getUserName()+" is added.");
		
		messageTemplate.convertAndSend("/topic/notify/"+groupid, rp);
		
	}
	
}
