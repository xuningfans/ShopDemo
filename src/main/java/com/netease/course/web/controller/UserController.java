package com.netease.course.web.controller;

import static com.netease.course.web.utils.WebUtil.cookie2Bean;
import static com.netease.course.web.utils.WebUtil.validData;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.netease.course.meta.User;
import com.netease.course.service.UserService;

@Controller
@SessionAttributes("loginUser")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/login" })
	public String login(@ModelAttribute @Validated User user, BindingResult br, HttpSession session,
			HttpServletResponse response, HttpServletRequest request, Model model) {

		User loginUser = null;
		loginUser = (User) session.getAttribute("loginUser");
		if (loginUser != null) {
			return "font/mainhead";
		}
		User cookieUser = null;
		cookieUser = cookie2Bean(request.getCookies(), User.class);
		if (cookieUser != null) {
			if (validData(cookieUser.getUserName()) && validData(cookieUser.getUserPassword())) {

				if (cookieUser != null) {
					loginUser = userService.login(cookieUser);
					if (loginUser != null) {
						model.addAttribute("loginUser", loginUser);
						return "font/mainhead";
					}
				}
			}
		}
		if (br.hasErrors()) {
			return "font/loginhead";
		}

		// if (validData(user.getUserName()) &&
		// validData(user.getUserPassword())) {
		loginUser = userService.login(user);
		if (loginUser != null) {
			setCookie(response, loginUser);
			model.addAttribute("loginUser", loginUser);
			return "font/mainhead";
		}
		model.addAttribute("message", "用户或密码错误！");
		model.addAttribute("userName", user.getUserName());
		return "font/loginhead";
	}

	/**
	 * 为客户端设置Cookie
	 * 
	 * @param response
	 * @param user
	 */
	private void setCookie(HttpServletResponse response, User user) {

		// 新建userName，userPassword两个Cookie
		Cookie usCookie = new Cookie("userName", user.getUserName());

		// 此处密码Cookie需加密，演示案例在此不加密
		Cookie psCookie = new Cookie("userPassword", user.getUserPassword());

		// 设置Cookie有效期60秒
		usCookie.setMaxAge(60);
		psCookie.setMaxAge(60);

		// 将设置好的Cookie添加至response
		response.addCookie(usCookie);
		response.addCookie(psCookie);

	}

	@RequestMapping(value = { "logout" }, method = RequestMethod.GET)
	public String logout() {
		System.out.println("logout");
		return "redirect:login";
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String root( Model model ) {
		return "index";
	}

	@RequestMapping(value = { "/menu" }, method = RequestMethod.GET)
	public String menu() {
		return "font/menu";
	}

	/*
	 * @RequestMapping(value = { "/login" }, method = RequestMethod.GET) public
	 * String login() { return "font/loginhead"; }
	 */
	/*
	 * @RequestMapping(value={"/head"}, method=RequestMethod.GET) public String
	 * head() { return "inc/loginhead"; }
	 */

	@RequestMapping(value = { "/test" }, method = RequestMethod.GET)
	public Model login(Model model) {
		User u = new User();
		u.setUserName("aaa");
		u.setUserPassword("123");
		u.setUserType(1);
		model.addAttribute(u);
		return model;
	}

}
