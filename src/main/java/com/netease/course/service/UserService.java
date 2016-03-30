package com.netease.course.service;

import com.netease.course.meta.User;

/**
 * 用户Service层接口
 * 
 * @author 公猴脖子男
 */
public interface UserService extends BaseService<User> {
	/**
	 * 用户登录验证
	 * 
	 * @param userName
	 *            用户名
	 * @param userPassword
	 *            密码
	 * @return 用户全部信息
	 */
	User login(String userName, String userPassword);
}