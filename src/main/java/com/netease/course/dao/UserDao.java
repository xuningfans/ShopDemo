package com.netease.course.dao;

import com.netease.course.meta.User;

/**
 * 用户相关Dao接口
 * 
 * @author 公猴脖子男
 */
public interface UserDao extends BaseDao<User> {

	/**
	 * 用户登录验证方法
	 * @param user
	 * @return
	 */
	User login(User user);
}