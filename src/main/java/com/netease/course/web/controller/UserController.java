package com.netease.course.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netease.course.meta.User;
import com.netease.course.service.UserService;

@Controller
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value={"/"}, method=RequestMethod.GET)
	public String root() {
		return "index";
	}
	
	@RequestMapping(value={"/main"}, method=RequestMethod.GET)
	public String main() {
		return "font/main";
	}
	
	@RequestMapping(value={"/menu"}, method=RequestMethod.GET)
	public String menu() {
		return "font/menu";
	}

	@RequestMapping(value={"/login"}, method=RequestMethod.GET)
	public String login() {
		return "font/loginhead";
	}

	@RequestMapping(value={"/login"}, method=RequestMethod.POST)
	public String login(@ModelAttribute User user, Model model) {
		System.out.println(user);
		User loginUser = userService.login(user);
		System.out.println(loginUser);
		if (loginUser != null) {
			model.addAttribute("loginUser", loginUser);
			return "font/mainhead";
		}
		model.addAttribute("message", "用户或密码错误！");
		model.addAttribute("userName", user.getUserName());
		return "font/loginhead";
	}
	
	@RequestMapping(value={"logout"}, method=RequestMethod.GET)
	public String logout() {
		System.out.println("logout");
		return "redirect:login";
	}
	
	/*@RequestMapping(value={"/head"}, method=RequestMethod.GET)
	public String head() {
		return "inc/loginhead";
	}*/

	
	
	
	/*@RequestMapping(value={"/"}, method=RequestMethod.GET)
	public Model login(Model model) {
		User u = new User();
		u.setUserName("aaa");
		u.setUserPassword("123");
		u.setUserType(true);
		model.addAttribute(u);
		return model;
	}*/

}
