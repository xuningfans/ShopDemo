package com.netease.course.web.controller;

import static com.netease.course.web.utils.WebUtil.setCookie;
import static com.netease.course.web.utils.WebUtil.getUserByCookieAndSession;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.apache.commons.lang3.StringUtils.isBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.netease.course.meta.User;
import com.netease.course.service.UserService;

/**
 * 用户登录注销Controller
 * 
 * @author 公侯脖子男
 */
@Controller
// @SessionAttributes("loginUser")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	/***********************************************************
	 * GET请求，从Cookie和Session中验证用户 通过验证自动登录，未通过跳转登录表单页面
	 * 
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 ***********************************************************/
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(HttpSession session, HttpServletRequest request, ModelMap model) {
		// 调用WebUtil中getUserByCookieAndSession方法验证登录信息
		User user = getUserByCookieAndSession(session, request);
		if (user != null) {
			// 数据库验证
			User loginUser = userService.login(user.getUserName(), user.getUserPassword());
			if (loginUser != null) {
				// Session 保存登录信息
				session.setAttribute("loginUser", loginUser);
				// 返回登录信息
				model.addAttribute("loginUser", loginUser);
				// 验证成功，登录成功
				return "font/mainhead";
			}
		}
		return "font/loginhead";
	}

	/***********************************************************
	 * POST请求，调用Service层验证表单数据 通过则登录，失败跳转回登录页面并给出提示信息
	 * 
	 * @param userName
	 *            登录用户名
	 * @param userPassword
	 *            用户密码
	 * @param session
	 * @param response
	 * @param model
	 * @return
	 ***********************************************************/
	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String login(String userName, String userPassword, HttpSession session, HttpServletResponse response,
			Model model) {
		// 通过Apache StringUtils验证数据非空， null "" " "
		if (!(isBlank(userName) && isBlank(userPassword))) {
			// 验证表单用户名密码
			User loginUser = userService.login(userName, userPassword);
			if (loginUser != null) {
				try {
					// 为客户端设置Cookie和Session
					setCookie(response, loginUser, 60);
					session.setAttribute("loginUser", loginUser);
					// 返回登录信息，用户回显欢迎语句
					model.addAttribute("loginUser", loginUser);
					return "font/mainhead";
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}

		// 添加失败信息并跳转登录页回显
		model.addAttribute("message", "用户或密码错误！");
		model.addAttribute("userName", userName);
		return "font/loginhead";
	}

	/****************************************************
	 * 注销请求方法 将Cookie和Session设置失效
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 *****************************************************/
	@RequestMapping(value = { "logout" }, method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		// 获取Cookie
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				// 如果该Cookie为登录信息， 则将其失效并回写
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



	
	/*
	 * @RequestMapping(value = { "/test" }, method = RequestMethod.GET) public
	 * Model login(Model model) { User u = new User(); u.setUserName("aaa");
	 * u.setUserPassword("123"); u.setUserType(1); model.addAttribute(u); return
	 * model; }
	 */

}
