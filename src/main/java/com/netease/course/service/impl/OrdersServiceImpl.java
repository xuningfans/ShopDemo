package com.netease.course.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.netease.course.meta.Orders;
import com.netease.course.meta.Product;
import com.netease.course.meta.User;
import com.netease.course.service.OrdersService;

/**
 * 订单相关Service层实现类
 * 
 * @author 公侯脖子男
 */
@Service
public class OrdersServiceImpl extends BaseServiceImpl<Orders> implements OrdersService {

	/**
	 * 根据用户Id查找订单
	 * 
	 * @param user
	 * @return 查询结果集合
	 */
	@Override
	public List<Orders> selectOrders(User user) {
		if (user.getUserType() == 1) {
			return ordersDao.selectAll();
		}
		return ordersDao.selectOrders(user);
	}

	/**
	 * 查找所有订单（管理员）
	 * 
	 * @return 查询结果集合
	 *//*
	@Override
	public List<Orders> selectAll() {
		return ordersDao.selectAll();
	}*/

	/**
	 * 购买并生成订单
	 * 
	 * @param user
	 *            下单用户
	 * @param productId
	 *            购买的商品Id
	 */
	@Override
	public void buyProduct(User user, String productId) {
		Product product = new Product();
		product.setProductId(Integer.valueOf(productId));
		product = productDao.select(product);
		Orders order = new Orders();
		order.setUser(user);
		order.setProduct(product);
		order.setState(new Random().nextInt(3));
		order.setOrdersPrice(product.getProductPrice());
		ordersDao.insert(order);
	}

}
