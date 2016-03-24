package com.netease.course.test;

import javax.servlet.http.Cookie;

import org.junit.Test;

import com.netease.course.meta.User;
import com.netease.course.web.utils.WebUtil;

public class Utils {

	@Test
	public void test1(){
		Cookie[] cookies = new Cookie[2];
		cookies[1] = new Cookie("userName", "aaa");
		cookies[0] = new Cookie("userPassword", "aaa");
		
		WebUtil.cookie2Bean(cookies, User.class);
	}
}
