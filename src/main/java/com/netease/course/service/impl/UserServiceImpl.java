package com.netease.course.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.netease.course.meta.User;
import com.netease.course.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Override
	public User login(User user) {
		if (user.getUserName() != null && user.getUserPassword() != null) {
			return userDao.select(user);
		}
		return null;
	}

	@Override
	public List<User> selectList(String... name) {
		return userDao.select(name);
	}

	@Override
	public int insert(User entity) throws Exception {
		return userDao.insert(entity);
	}

	@Override
	public int update(User entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(User entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	// @Override
	// public User select(User entity) {
	// // TODO Auto-generated method stub
	// return null;
	// }

}
