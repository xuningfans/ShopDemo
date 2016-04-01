package com.netease.course.service;

import java.util.List;

import com.netease.course.meta.Orders;
import com.netease.course.meta.User;

/**
 * 订单Service层接口
 * 
 * @author 公猴脖子男
 */
public interface OrdersService extends BaseService<Orders> {

	/**
	 * 根据用户Id查找订单
	 * 
	 * @param user
	 * @return 查询结果集合
	 */
	List<Orders> selectOrders(User user);

	/**
	 * 购买并生成订单
	 * 
	 * @param user
	 *            下单用户
	 * @param productId
	 *            购买的商品Id
	 */
	void buyProduct(User user, String productId);

	/**
	 * 查找所有订单（管理员）
	 * 
	 * @return 查询结果集合
	 */
//	List<Orders> selectAll();
}
