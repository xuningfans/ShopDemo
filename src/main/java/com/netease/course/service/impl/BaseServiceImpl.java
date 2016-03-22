package com.netease.course.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.netease.course.dao.BaseDao;
import com.netease.course.dao.ProductDao;
import com.netease.course.dao.UserDao;
import com.netease.course.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	protected UserDao userDao;

	@Autowired
	protected ProductDao productDao;

	protected BaseDao<T> baseDao;

	// 通过泛型信息自动装配对应Dao给baseDao，实现通用调用
	// 需要对研发本身进行命名与使用规范
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
		// 通过Spring容器拿到UserDao并装配给BaseDao
		// 例如：UserService对象中，BaseDao所指向的实际对象为UserDao对象，
		// 实际对象已被Spring实例化于Ioc容器中
		// targetField.get(this) 获取当前对象（this）的targetField的值
		baseField.set(this, targetField.get(this));
	}

	@Override
	public int insert(T entity) throws Exception {
		return baseDao.insert(entity);
	}

	@Override
	public int delete(T entity) throws Exception {
		return baseDao.delete(entity);
	}

	@Override
	public int update(T entity) throws Exception {
		return baseDao.update(entity);
	}

	@Override
	public T select(T entity) {
		return baseDao.select(entity);
	}

	@Override
	public List<T> selectList(String... name) {
		return (List<T>) baseDao.select(name);
	}

	// @Override
	// public Page<T> selectPage(Page<T> page) {
	// page.setLists(baseMapper.selectPageList(page));
	// page.setTotal(baseMapper.selectCount(page));
	// return page;
	// }
	//
	// @Override
	// public Page<T> selectPageUseDyc(Page<T> page) {
	// page.setLists(baseMapper.selectPageListUseDyc(page));
	// page.setTotal(baseMapper.selectCountUseDyc(page));
	// return page;
	// }
}

/*@PostConstruct // 在构造方法后，初化前执行
private void initBaseDao() throws Exception {
	// 完成以下逻辑，需要对研发本身进行命名与使用规范
	// this关键字指对象本身，这里指的是调用此方法的实现类（子类）
	System.out.println("=======this :" + this);
	System.out.println("=======父类基本信息：" + this.getClass().getSuperclass());
	System.out.println("=======父类和泛型的信息：" + this.getClass().getGenericSuperclass());

	ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
	// 获取第一个参数的class
	Class<?> clazz = (Class<?>) type.getActualTypeArguments()[0];
	System.out.println("=======class:" + clazz);
	// 转化为属性名（相关的Dao子类的引用名）User userDao
	String localField = clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1)
			+ "Dao";
	System.out.println("=======localField:" + localField);
	// getDeclaredField:可以使用于包括私有、默认、受保护、公共字段，但不包括继承的字段
	Field field = this.getClass().getSuperclass().getDeclaredField(localField);
	System.out.println("=======field:" + field);
	System.out.println("=======field对应的对象:" + field.get(this));
	Field baseField = this.getClass().getSuperclass().getDeclaredField("baseDao");

	System.out.println("=======baseField:" + baseField);
	System.out.println("=======baseField对应的对象:" + baseField.get(this));
	// field.get(this)获取当前this的field字段的值。
	//	例如：User对象中，baseDao所指向的对象为其子类型UserDao对象，子类型对象已被spring实例化于容器中
	baseField.set(this, field.get(this));
	System.out.println("========baseField对应的对象:" + baseDao);

}*/
