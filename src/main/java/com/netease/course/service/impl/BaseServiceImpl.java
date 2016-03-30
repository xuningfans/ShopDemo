package com.netease.course.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.netease.course.dao.BaseDao;
import com.netease.course.dao.OrdersDao;
import com.netease.course.dao.ProductDao;
import com.netease.course.dao.UserDao;
import com.netease.course.service.BaseService;

/**
 * Service层基类接口实现类
 * 
 * @author 公猴脖子男
 *
 * @param <T>
 */
public class BaseServiceImpl<T> implements BaseService<T> {

	/**
	 * 自动注入的用户Dao
	 */
	@Autowired
	protected UserDao userDao;

	/**
	 * 自动注入的商品Dao
	 */
	@Autowired
	protected ProductDao productDao;

	/**
	 * 自动注入的订单Dao
	 */
	@Autowired
	protected OrdersDao ordersDao;

	/**
	 * 基类Dao通过initBaseDao装配对应实现类
	 */
	protected BaseDao<T> baseDao;

	/**
	 * 通过泛型信息自动装配对应Dao给baseDao，实现通用调用 需要对研发本身进行命名与使用规范
	 * 
	 * @throws Exception
	 */
	@PostConstruct
	private void initBaseDao() throws Exception {
		// 获取当前对象（this）的父类和泛型的信息
		// 当前对象（this）是由Spring装配的实现类
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 获取第一个泛型参数UserDao extends BaseDao<User>中的User的字节码
		Class<?> clazz = (Class<?>) type.getActualTypeArguments()[0];
		// 通过泛型名称（getSimpleName）组装属性名（相关的Dao子类的引用名） User-->userDao
		String targetFieldName = clazz.getSimpleName().substring(0, 1).toLowerCase()
				+ clazz.getSimpleName().substring(1) + "Dao";

		// getDeclaredField:可以使用于包括私有、默认、受保护、公共字段，但不包括继承的字段
		// 通过名称（targetFieldName）拿到当前对象（this）对应字段（属性），protected UserDao userDao;
		Field targetField = this.getClass().getSuperclass().getDeclaredField(targetFieldName);

		// 通过名称"baseDao"拿到当前对象（this）对应字段（属性），protected BaseDao<T> baseDao;
		Field baseField = this.getClass().getSuperclass().getDeclaredField("baseDao");

		// 打开属性访问权限
		baseField.setAccessible(true);

		// 拿到UserDao并装配给BaseDao
		// 例如：UserService对象中，BaseDao所指向的实际对象为UserDao对象，
		// 实际对象已被Spring实例化于Ioc容器中
		// targetField.get(this) 获取当前对象（this）的targetField的值
		baseField.set(this, targetField.get(this));
	}

	/**
	 * 添加单个对象
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@Override
	public int insert(T entity) throws Exception {
		return baseDao.insert(entity);
	}

	/**
	 * 添加多个对象
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	@Override
	public int insertList(List<T> entity) throws Exception {
		return baseDao.insertList(entity);
	}

	/**
	 * 删除单个对象
	 * 
	 * @param entity
	 * @return 删除列数
	 * @throws Exception
	 */
	@Override
	public int delete(T entity) throws Exception {
		return baseDao.delete(entity);
	}

	/**
	 * 通过id删除多个对象
	 * 
	 * @param ids
	 *            主键数组
	 * @return
	 * @throws Exception
	 */
	@Override
	public int deleteListById(String[] ids) throws Exception {
		return baseDao.deleteListById(ids);
	}

	/**
	 * 修改单个对象
	 * 
	 * @param entity
	 * @return 影响行数
	 * @throws Exception
	 */
	@Override
	public int update(T entity) throws Exception {
		return baseDao.update(entity);
	}

	/**
	 * 查询单个对象
	 * 
	 * @param entity
	 * @return 查询结果
	 */
	@Override
	public T select(T entity) {
		return baseDao.select(entity);
	}

	/**
	 * 有条件查询多个对象
	 * 
	 * @param entity
	 * @return 结果List集合
	 */
	@Override
	public List<T> selectList(T entity) {
		return (List<T>) baseDao.selectList(entity);
	}

	/**
	 * 无条件查询多个对象
	 * 
	 * @return 结果List集合
	 */
	@Override
	public List<T> selectList() {
		return baseDao.selectList(null);
	}

}
