package com.netease.course.dao;

import java.util.List;

import com.netease.course.meta.Orders;
import com.netease.course.meta.User;

public interface OrdersDao extends BaseDao<Orders>{

	//通过用户查找订单  管理员查找所有订单，用户查找自己订单
	List<Orders> selectOrders(User user);
}