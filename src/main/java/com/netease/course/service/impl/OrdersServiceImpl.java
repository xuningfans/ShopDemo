package com.netease.course.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.netease.course.meta.Orders;
import com.netease.course.meta.Product;
import com.netease.course.meta.User;
import com.netease.course.service.OrdersService;

@Service
public class OrdersServiceImpl extends BaseServiceImpl<Orders> implements OrdersService {

	@Override
	public List<Orders> selectOrders(User user) {
		
		if(user.getUserType()==1){
			return ordersDao.selectOrders(user);
		}
		
		if(user.getUserType()==0){
			user.setUserId(null);
			return ordersDao.selectOrders(user);
		}
		return null;
	}

	@Override
	public void buyProduct(User user, String productId) {
		Product product = new Product();
		product.setProductId(Integer.valueOf(productId));
		product = productDao.select(product);
		Orders order = new Orders();
		order.setUser(user);
		order.setProduct(product);
		ordersDao.insert(order);
	}

}
