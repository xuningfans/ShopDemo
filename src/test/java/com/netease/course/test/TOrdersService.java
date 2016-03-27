package com.netease.course.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netease.course.meta.Orders;
import com.netease.course.service.OrdersService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:app*.xml")
public class TOrdersService {
	
	@Autowired
	private OrdersService orderService;

	@Test
	public void testInsert() throws Exception {
		Orders orders = new Orders();
		orders.setUserId(1);
		orders.setProductId(1);
		orders.setOrdersPrice(1560.00);
		orders.setIsbuy(false);
		orderService.insert(orders);
	}
	
	@Test
	public void testDelete() throws Exception {
		Orders orders = new Orders();
		orders.setOrdersId(2);
		orders.setUserId(1);
		orders.setProductId(1);
		orders.setOrdersPrice(1560.00);
		orders.setIsbuy(false);
		orderService.delete(orders);
	}
	
	@Test
	public void testUpdate() throws Exception {
		Orders orders = new Orders();
		orders.setOrdersId(3);
		orders.setUserId(3);
		orders.setProductId(2);
		orders.setOrdersPrice(15.00);
		orders.setIsbuy(true);
		orderService.update(orders);
	}
	
	@Test
	public void testSelect() throws Exception {
		Orders orders = new Orders();
		orders.setOrdersId(3);
		orders.setUserId(3);
		orders.setProductId(2);
		orders.setOrdersPrice(15.00);
		orders.setIsbuy(true);
		
		System.out.println(orderService.select(orders));
	}


}
