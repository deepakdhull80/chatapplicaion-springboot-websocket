package com.work.ChatApp.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.work.ChatApp.Model.Group;
import com.work.ChatApp.Model.User;
import com.work.ChatApp.Service.Service;

@Controller
public class DashboardController {

	@Autowired
	private Service service;

	@GetMapping({ "/dashboard", "/" })
	public String dashboardGet(HttpServletRequest request, ModelMap map) {

		User user = (User) request.getSession().getAttribute("user");

		if (user == null) {
			return "redirect:/login";
		}

		List<Group> groupList = service.getAllGroups(user);

		map.addAttribute("user", user);
		map.addAttribute("groups", groupList);

		return "dashboard";
	
	}
	
	/*
	 * @Param: groupid
	 * 
	 * open messaging group 
	 * 
	 * 
	 * */
	
	@GetMapping("group/{groupid}")
	public String messagingGroup(HttpServletRequest request, @PathVariable String groupid, ModelMap map) {
		User user = (User) request.getSession().getAttribute("user");

		if (user == null) {
			return "redirect:/login";
		}

		Group gp = service.getGroupDetail(groupid, user);
		if(gp==null) {
			return "redirect:/";
		}
		map.addAttribute("group", gp);
		return "groupchat";
	}

	
	/*
	 * 
	 * creating chat group for user.
	 * 
	 * */
	
	@PostMapping("/create-group")
	public String createGroup(HttpServletRequest request,@ModelAttribute Group group, ModelMap map) {
		User user = (User) request.getSession().getAttribute("user");

		if (user == null) {
			return "redirect:/login";
		}
		Group resl = service.createGroup(group, user);
		
		if(resl==null) {
			return "redirect:/deshboard";
		}
		
		return "redirect:/group/"+resl.getGroupid();
	}
}
