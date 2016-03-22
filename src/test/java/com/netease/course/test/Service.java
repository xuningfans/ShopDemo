package com.netease.course.test;

import java.util.ArrayList;
import java.util.List;

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
	public void testInsert() throws Exception {
		User u = new User();
		u.setUserName("aaa");
		u.setUserPassword("123");
		u.setUserType(1);
		userService.insert(u);
	}

	@Test
	public void testInsertList() throws Exception {
		List<User> list = new ArrayList<User>();
		User u;
		for (int i = 0; i < 10; i++) {
			u = new User();
			u.setUserName("aaa" + i);
			u.setUserPassword("123");
			u.setUserType(1);
			list.add(u);
		}
		userService.insertList(list);
	}

	@Test
	public void testDelete() throws Exception {
		User u;
		u = new User();
		u.setUserName("aaa");
		u.setUserPassword("123");
		u.setUserType(1);
		userService.delete(u);
	}

	@Test
	public void testDeletetListById() throws Exception {
		String[] ids = new String[5];
		for (int i = 0; i < 5; i++) {
			ids[i] = i+"";
		}
		userService.deleteListById(ids);
	}
	
	@Test
	public void testDeletetListByName() throws Exception {
		String[] userNames = new String[5];
		for (int i = 4; i < 9; i++) {
			userNames[i-4] = "aaa" + i;
			System.out.println(userNames[i-4]);
		}
		userService.deleteListByName(userNames);
	}
	
	@Test
	public void testUpdate() throws Exception {
		User u = new User();
		u.setUserId(23);
		u.setUserName("bbb");
		u.setUserPassword("123");
		u.setUserType(1);
		userService.update(u);
	}
	
	@Test
	public void testSelect() throws Exception {
		User u = new User();
		u.setUserId(23);
//		u.setUserName("bbb");
//		u.setUserPassword("123");
		u.setUserType(1);
		System.out.println("------------------------------------------");
		System.out.println(userService.select(u));
		System.out.println("------------------------------------------");
	}
	
	@Test
	public void testSelectList() throws Exception {
		User u = new User();
//		u.setUserId(23);
		u.setUserName("bbb");
		u.setUserPassword("123");
		u.setUserType(1);
		List<?> list = userService.selectList(u);
		System.out.println("------------------------------------------");
		for (Object object : list) {
			System.out.println(object);
		}
		System.out.println("------------------------------------------");
	}
}
