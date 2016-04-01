package com.netease.course.web.controller;

import static com.netease.course.web.utils.WebUtil.getUserByCookieAndSession;
import static com.netease.course.web.utils.WebUtil.jsonUser2Bean;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netease.course.meta.Orders;
import com.netease.course.meta.User;
import com.netease.course.service.OrdersService;
import com.netease.course.service.UserService;

/*********************************************
 * 订单相关Controller
 * 
 * @author 公侯脖子男
 *********************************************/
@Controller
public class OrdersController extends BaseController {

	/**
	 * 自动注入订单Service层
	 */
	@Resource
	private OrdersService ordersService;

	/**
	 * 自动注入用户Service层
	 */
	@Resource
	private UserService userService;

	/******************************************
	 * 订单列表
	 * 
	 * @param jsonUser
	 *            Cookie中User的Json格式字符串
	 * @param session
	 * @param model
	 * @return
	 *******************************************/
	@RequestMapping(value = "/listorders")
	public String listorder(HttpServletRequest request, HttpSession session, ModelMap model) {

		// 调用WebUtil中getUserByCookieAndSession方法验证登录信息
		User user = getUserByCookieAndSession(session, request);
		if (user != null) {
			User loginUser = userService.login(user.getUserName(), user.getUserPassword());
			if (loginUser != null) {
				List<Orders> orders = ordersService.selectOrders(loginUser);
				model.addAttribute("orders", orders);
				return "listorder";
			}
		}

		// 验证不通过，用户需登录
		model.addAttribute("message", "请登录！");
		return "error";
	}

	/***************************************************
	 * 购买并生成订单
	 * 
	 * @param jsonUser
	 *            Cookie中User的Json格式字符串
	 * @param productId
	 *            产品Id
	 * @param session
	 * @param model
	 * @return
	 ***************************************************/
	@RequestMapping(value = "/buyproduct/{productId}")
	public String buyproduct(@CookieValue(value = "jsonUser", required = false) String jsonUser,
			@PathVariable String productId, HttpSession session, ModelMap model) {

		// Session中验证用户是否登录
		User user = (User) session.getAttribute("loginUser");
		if (user != null) {
			ordersService.buyProduct(user, productId);
			List<Orders> orders = ordersService.selectOrders(user);
			model.addAttribute("orders", orders);
			return "listorder";
		}

		// Cookie中验证用户是否登录
		if (jsonUser != null) {
			user = jsonUser2Bean(User.class, jsonUser);
			if (user != null) {
				ordersService.buyProduct(user, productId);
				List<Orders> orders = ordersService.selectOrders(user);
				model.addAttribute("orders", orders);
				return "listorder";
			}
		}

		// 验证不通过，用户需登录
		model.addAttribute("message", "请登录！");
		return "error";
	}
}
