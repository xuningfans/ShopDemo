package com.netease.course.test;

import java.io.IOException;

import javax.servlet.http.Cookie;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.netease.course.meta.User;

public class TestJackson {

	@Test
	public void test1() {
		try {
			ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
			ObjectMapper objectMapper = new ObjectMapper();
			User u = new User();
			u.setUserName("aaa");
			u.setUserPassword("123");
			u.setUserType(1);
			String jsonUser = null;
			jsonUser = objectWriter.writeValueAsString(u);
			Cookie cookie = new Cookie("jsonUser", jsonUser);
			User user = objectMapper.readValue(cookie.getValue(), User.class);
			System.out.println(cookie.getName());
			System.out.println(cookie.getValue());
			System.out.println("-------------------------------");
			System.out.println(user);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void test2() {
		
		Class<User> clazz = User.class;
		System.out.println(clazz.getSimpleName());
	}
}
