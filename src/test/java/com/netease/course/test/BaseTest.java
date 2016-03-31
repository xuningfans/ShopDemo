package com.netease.course.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netease.course.service.OrdersService;
import com.netease.course.service.ProductService;
import com.netease.course.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class BaseTest {
	@Autowired
	protected OrdersService ordersService;

	@Autowired
	protected ProductService productService;

	@Autowired
	protected UserService userService;

}
