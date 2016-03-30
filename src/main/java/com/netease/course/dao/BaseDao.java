package com.netease.course.dao;

import java.util.List;

/**
 * 基类Dao
 * 
 * @author 公侯脖子男
 *
 * @param <T>
 */
public interface BaseDao<T> {
	/**
	 * 添加单个对象
	 * 
	 * @param entity
	 * @return 影响行数
	 */
	public int insert(T entity);

	/**
	 * 添加多个对象
	 * 
	 * @param list
	 * @return 影响行数
	 */
	public int insertList(List<T> list);

	/**
	 * 修改单个对象
	 * 
	 * @param entity
	 * @return 影响的行数
	 */
	public int update(T entity);

	/**
	 * 删除单个对象
	 * 
	 * @param entity
	 * @return 影响的行数
	 */
	public int delete(T entity);

	/**
	 * 通过主键删除多个对象
	 * 
	 * @param ids
	 *            主键
	 * @return 查询结果集
	 */
	public int deleteListById(String[] ids);

	/**
	 * 查询单个对象
	 * 
	 * @param entity
	 * @return 查询结果集
	 */
	public T select(T entity);

	/**
	 * 查询多个对象
	 * 
	 * @param entity
	 * @return 查询结果集
	 */
	public List<T> selectList(T entity);
}
