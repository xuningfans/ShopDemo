package com.netease.course.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netease.course.meta.User;
import com.netease.course.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:*.xml")
public class Service {
	@Autowired
	UserService userService;

	@Test
	public void test() throws Exception {
		User u = new User();
		u.setUserName("aaa");
		u.setUserPassword("123");
		u.setUserType(1);
		userService.insert(u);
	}

}
