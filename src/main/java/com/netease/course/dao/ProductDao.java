package com.netease.course.dao;

import java.util.List;

import com.netease.course.meta.Product;
import com.netease.course.meta.QuerryInfo;

/**
 * 产品Dao相关接口
 * 
 * @author 公猴脖子男
 */
public interface ProductDao extends BaseDao<Product> {

	/**
	 * 分页查找商品
	 * 
	 * @param querryInfo
	 *            分页条件
	 * @return
	 */
	List<Product> getPageData(QuerryInfo querryInfo);

	/**
	 * 根据分页条件获取总记录数
	 * 
	 * @param querryInfo
	 * @return
	 */
	Integer getRecord(QuerryInfo querryInfo);
}