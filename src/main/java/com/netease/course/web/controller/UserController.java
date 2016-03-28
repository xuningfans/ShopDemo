package com.netease.course.web.controller;

import static com.netease.course.web.utils.WebUtil.setCookie;
import static com.netease.course.web.utils.WebUtil.validData;
import static com.netease.course.web.utils.WebUtil.getUserByCookieAndSession;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.netease.course.meta.User;
import com.netease.course.service.UserService;

@Controller
// @SessionAttributes("loginUser")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(HttpSession session, HttpServletRequest request, ModelMap model) {
		User loginUser = getUserByCookieAndSession(session, request);
		if (loginUser != null) {
			model.addAttribute("loginUser", loginUser);
			return "font/mainhead";
		}
		return "font/loginhead";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String login(String userName, String userPassword, HttpSession session, HttpServletResponse response,
			Model model) {

		if (validData(userName) && validData(userPassword)) {
			User formUser = new User();
			formUser.setUserName(userName);
			formUser.setUserPassword(userPassword);
			// 验证表单用户名密码
			User loginUser = userService.login(formUser);
			if (loginUser != null) {
				try {
					setCookie(response, loginUser, 60);
					session.setAttribute("loginUser", loginUser);
					model.addAttribute("loginUser", loginUser);
					return "font/mainhead";
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		model.addAttribute("message", "用户或密码错误！");
		model.addAttribute("userName", userName);
		return "font/loginhead";
	}

	

	@RequestMapping(value = { "logout" }, method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("jsonUser".equals(cookie.getName())) {
					cookie.setMaxAge(0);
					cookie.setValue("");
					response.addCookie(cookie);
				}
			}
		}
		if (session != null) {
			session.removeAttribute("loginUser");
			// 直接销毁Session好像Spring还会创建
			// session.invalidate();
		}
		return "font/loginhead";
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String root(Model model) {
		return "index";
	}

	@RequestMapping(value = { "/menu" }, method = RequestMethod.GET)
	public String menu(HttpSession session, HttpServletRequest request, ModelMap model) {
		User loginUser = getUserByCookieAndSession(session, request);
		if (loginUser != null) {
			model.addAttribute("user", loginUser);
		}
		return "menu";
	}
/*
	@RequestMapping(value = { "/test" }, method = RequestMethod.GET)
	public Model login(Model model) {
		User u = new User();
		u.setUserName("aaa");
		u.setUserPassword("123");
		u.setUserType(1);
		model.addAttribute(u);
		return model;
	}*/

}
