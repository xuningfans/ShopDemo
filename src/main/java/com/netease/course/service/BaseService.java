package com.netease.course.service;

import java.util.List;

/**
 * Service层基类接口
 * 
 * @author 公猴脖子男
 *
 * @param <T>
 */
public interface BaseService<T> {

	/**
	 * 添加单个对象
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int insert(T entity) throws Exception;

	/**
	 * 添加多个对象
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int insertList(List<T> list) throws Exception;

	/**
	 * 修改单个对象
	 * 
	 * @param entity
	 * @return 影响行数
	 * @throws Exception
	 */
	public int update(T entity) throws Exception;

	/**
	 * 删除单个对象
	 * 
	 * @param entity
	 * @return 删除列数
	 * @throws Exception
	 */
	public int delete(T entity) throws Exception;

	/**
	 * 通过id删除多个对象
	 * 
	 * @param ids
	 *            主键数组
	 * @return
	 * @throws Exception
	 */
	public int deleteListById(String[] ids) throws Exception;


	/**
	 * 查询单个对象
	 * 
	 * @param entity
	 * @return 查询结果
	 */
	public T select(T entity);

	/**
	 * 有条件查询多个对象
	 * 
	 * @param entity
	 * @return 结果List集合
	 */
	public List<T> selectList(T entity);

	/**
	 * 无条件查询多个对象
	 * 
	 * @return 结果List集合
	 */
	public List<T> selectList();

	// 通过主键（数组）批量删除数据
	// public int deleteList(String [] pks) throws Exception;

	// //通过关键字分页查询
	// public Page<T> selectPage(Page<T> page);
	//
	//
	// //通过多条件分页查询
	// public Page<T> selectPageUseDyc(Page<T> page);
}
