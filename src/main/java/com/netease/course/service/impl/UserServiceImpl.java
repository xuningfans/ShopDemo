package com.netease.course.service.impl;

import org.springframework.stereotype.Service;

import com.netease.course.meta.User;
import com.netease.course.service.UserService;

/**
 * 用户Service层实现类
 * 
 * @author 公猴脖子男
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	/**
	 * 用户登录验证
	 * 
	 * @param userName
	 *            用户名
	 * @param userPassword
	 *            密码
	 * @return 用户全部信息
	 */
	@Override
	public User login(String userName, String userPassword) {
		User user = new User();
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		return userDao.login(user);
	}

}
