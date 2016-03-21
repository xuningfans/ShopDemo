package com.netease.course.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.course.dao.UserDao;
import com.netease.course.meta.User;
import com.netease.course.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;
	
	@Override
	public User login(User user){
		return userDao.select(user);
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

	@Override
	public User select(User entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
