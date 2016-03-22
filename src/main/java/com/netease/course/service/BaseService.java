package com.netease.course.service;

import java.util.List;

public interface BaseService<T> {

	// 添加单个对象
	public int insert(T entity) throws Exception;

	// 添加多个对象
	public int insertList(List<T> list) throws Exception;

	// 修改单个对象
	public int update(T entity) throws Exception;

	// 删除单个对象
	public int delete(T entity) throws Exception;

	// 删除多个对象
	public int deleteListById(String[] ids) throws Exception;
	
	// 删除多个对象
	public int deleteListByName(String[] userNames) throws Exception;

	// 查询单个对象
	public T select(T entity);

	// 查询多个对象
	public List<T> selectList(T entity);

	// 通过主键（数组）批量删除数据
	// public int deleteList(String [] pks) throws Exception;

	// //通过关键字分页查询
	// public Page<T> selectPage(Page<T> page);
	//
	//
	// //通过多条件分页查询
	// public Page<T> selectPageUseDyc(Page<T> page);
}
