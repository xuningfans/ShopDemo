package com.netease.course.service;

import java.util.List;

import com.netease.course.meta.Orders;
import com.netease.course.meta.User;

public interface OrdersService extends BaseService<Orders> {
	
	List<Orders> selectOrders(User user);
	
	void buyProduct(User user, String productId);

}
