package com.netease.course.dao;

import java.util.List;

public interface BaseDao<T> {
	// 添加单个对象
	public int insert(T entity);

	// 添加多个对象
	public int insertList(List<T> list);

	// 修改单个对象
	public int update(T entity);
	
	// 删除单个对象
	public int delete(T entity);

	// 通过主键删除多个对象
	public int deleteListById(String[] ids);
	
	// 通过用户名删除多个对象
	public int deleteListByName(String[] userNames);

	// 查询单个对象
	public T select(T entity);

	// 查询多个对象
	public List<T> selectList(T entity);
}
