package com.netease.course.test;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netease.course.meta.OrderEnum;
import com.netease.course.meta.Orders;
import com.netease.course.meta.Product;
import com.netease.course.meta.User;
import com.netease.course.service.OrdersService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:app*.xml")
public class TestOrdersService {
	
	@Autowired
	private OrdersService orderService;

	@Test
	public void testInsert() throws Exception {
		Orders orders = new Orders();
		User u = new User();
		u.setUserId(1);
		u.setUserName("aaa");
		u.setUserPassword("123");
		u.setUserType(1);
		orders.setUser(u);
		Product product = new Product();
		product.setProductId(1);
		orders.setProduct(product);
		orders.setOrdersPrice(1560.00);
		orders.setState(1);
		orderService.insert(orders);
		System.out.println(OrderEnum.valueOf(1));
	}
	
	@Test
	public void testDelete() throws Exception {
		Orders orders = new Orders();
		orders.setOrdersId(1);
		User u = new User();
		u.setUserId(1);
		u.setUserName("aaa");
		u.setUserPassword("123");
		u.setUserType(1);
		orders.setUser(u);
		Product product = new Product();
		product.setProductId(1);
		orders.setProduct(product);
		orders.setOrdersPrice(1560.00);
		orders.setState(0);
		orderService.delete(orders);
	}
	
	@Test
	public void testUpdate() throws Exception {
		Orders orders = new Orders();
		orders.setOrdersId(1);
		User u = new User();
		u.setUserId(1);
		u.setUserName("aaa");
		u.setUserPassword("123");
		u.setUserType(1);
		orders.setUser(u);
		Product product = new Product();
		product.setProductId(1);
		orders.setProduct(product);
		orders.setOrdersPrice(15.00);
		orders.setState(2);
		orderService.update(orders);
	}
	
	@Test
	public void testSelect() throws Exception {
		Orders orders = new Orders();
		orders.setOrdersId(2);
		User u = new User();
		u.setUserId(1);
		u.setUserName("aaa");
		u.setUserPassword("123");
		u.setUserType(1);
		orders.setUser(u);
		Product product = new Product();
		product.setProductId(1);
		orders.setProduct(product);
		orders.setOrdersPrice(15.00);
//		orders.setState(OrderEnum.BUY);
		
		System.out.println(orderService.select(orders));
	}
	
	@Test
	public void test(){
		System.out.println(OrderEnum.PAY);
	}


}
