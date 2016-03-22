package com.netease.course.service;

import com.netease.course.meta.User;

public interface UserService extends BaseService<User>{
	User login(String userName, String userPassword);
}