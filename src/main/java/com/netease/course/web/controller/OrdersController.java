package com.netease.course.web.controller;

import static com.netease.course.web.utils.WebUtil.jsonUser2Bean;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netease.course.meta.Orders;
import com.netease.course.meta.User;
import com.netease.course.service.OrdersService;

@Controller
public class OrdersController extends BaseController {
	
	@Resource
	private OrdersService ordersService;

	@RequestMapping(value="/listorders")
	public String listorder(@CookieValue(value = "jsonUser", required=false) String jsonUser, HttpSession session, ModelMap model){
		
		User user = (User) session.getAttribute("loginUser");
		if( user!=null ){
			List<Orders> orders = ordersService.selectOrders(user);
			model.addAttribute("orders", orders);
			return "listorder";
		}
		
		if( jsonUser != null ){
			user = jsonUser2Bean(User.class, jsonUser);
			List<Orders> orders = ordersService.selectOrders(user);
			model.addAttribute("orders", orders);
			return "listorder";
		}
		
		model.addAttribute("message", "请登录！");
		return "error";
	}
	
	@RequestMapping(value="/buyproduct/{productId}")
	public String buyproduct(@CookieValue(value = "jsonUser", required=false) String jsonUser,@PathVariable String productId, HttpSession session, ModelMap model){
		
		User user = (User) session.getAttribute("loginUser");
		if( user!=null ){
			ordersService.buyProduct(user, productId);
			List<Orders> orders = ordersService.selectOrders(user);
			model.addAttribute("orders", orders);
			return "listorder";
		}
		
		if( jsonUser != null ){
			user = jsonUser2Bean(User.class, jsonUser);
			if( user != null )	{
				ordersService.buyProduct(user, productId);
				List<Orders> orders = ordersService.selectOrders(user);
				model.addAttribute("orders", orders);
				return "listorder";
			}
		}
		
		model.addAttribute("message", "请登录！");
		return "error";
	}
}
