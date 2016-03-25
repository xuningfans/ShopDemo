package com.netease.course.web.controller;

import static com.netease.course.web.utils.WebUtil.*;

import java.io.UnsupportedEncodingException;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.netease.course.meta.User;
import com.netease.course.service.UserService;

@Controller
@SessionAttributes("loginUser")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/login" })
	public String login(@ModelAttribute @Validated User formUser, BindingResult br, HttpSession session,
			HttpServletResponse response, HttpServletRequest request, Model model) {

		// 从Session中验证用户名密码
		User loginUser = null;
		loginUser = (User) session.getAttribute("loginUser");
		if (loginUser != null) {
			return "font/mainhead";
		}

		// 从Cookie中验证用户名密码
		User cookieUser = null;
		cookieUser = jsonCookie2Bean(request.getCookies(), User.class);
		if (cookieUser != null) {
			if (validData(cookieUser.getUserName()) && validData(cookieUser.getUserPassword())) {

				loginUser = userService.login(cookieUser);
				if (loginUser != null) {
					model.addAttribute("loginUser", loginUser);
					return "font/mainhead";
				}
			}
		}
		if (br.hasErrors()) {
			return "font/loginhead";
		}

		// 验证表单用户名密码
		loginUser = userService.login(formUser);
		if (loginUser != null) {
			try {
				setCookie(response, loginUser);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("loginUser", loginUser);
			return "font/mainhead";
		}
		model.addAttribute("message", "用户或密码错误！");
		model.addAttribute("userName", formUser.getUserName());
		return "font/loginhead";
	}

	

	@RequestMapping(value = { "logout" }, method = RequestMethod.GET)
	public String logout(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		return "redirect:login";
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String root(Model model) {
		return "index";
	}

	@RequestMapping(value = { "/menu" }, method = RequestMethod.GET)
	public String menu() {
		return "menu";
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
