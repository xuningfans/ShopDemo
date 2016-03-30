package com.netease.course.dao;

import java.util.List;

import com.netease.course.meta.Orders;
import com.netease.course.meta.User;

/**
 * 订单相关Dao接口
 * 
 * @author 公侯脖子男
 */
public interface OrdersDao extends BaseDao<Orders> {

	/**
	 * 通过用户Id查找订单 用户查找自己订单
	 * 
	 * @param user
	 * @return 订单结果集合
	 */
	List<Orders> selectOrders(User user);

	/**
	 * 通过用户Id查找订单 用户查找自己订单
	 * 
	 * @return 订单结果集合
	 */
	List<Orders> selectAll();
}