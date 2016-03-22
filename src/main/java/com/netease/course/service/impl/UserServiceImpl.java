package com.netease.course.service.impl;

import org.springframework.stereotype.Service;

import com.netease.course.meta.User;
import com.netease.course.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Override
	public User login(String userName, String userPassword) {
		if (userName != null && userPassword != null) {
			User user = new User();
			user.setUserName(userName);
			user.setUserPassword(userPassword);
			return select(user);
		}
		return null;
	}
}
