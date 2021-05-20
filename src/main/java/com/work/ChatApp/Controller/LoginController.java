package com.work.ChatApp.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.work.ChatApp.Model.User;
import com.work.ChatApp.Service.Service;

@Controller
public class LoginController {
	
	@Autowired
	Service service;
	
	@GetMapping("/login")
	@ResponseBody
	public ModelAndView getLogin() {
		return new ModelAndView("login");
	}
	
	@PostMapping("/login")
	public String postLogin(@ModelAttribute User user,HttpServletRequest request, ModelMap map) {
		User resp = service.loginUser(user);
//		System.out.println(user);
		if(resp==null) {
			map.addAttribute("error", "Userid and password invalid");
			
			return "login";
		}
		
		
		
		request.getSession().setAttribute("user",resp);
		return "redirect:/dashboard";
	}
	
}
