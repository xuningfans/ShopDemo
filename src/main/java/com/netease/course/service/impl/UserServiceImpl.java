package com.netease.course.service.impl;

import org.springframework.stereotype.Service;

import com.netease.course.meta.User;
import com.netease.course.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Override
	public User login(User user) {
		return select(user);
	}

}
