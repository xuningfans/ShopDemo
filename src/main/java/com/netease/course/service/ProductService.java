package com.netease.course.service;

import com.netease.course.meta.PageBean;
import com.netease.course.meta.Product;
import com.netease.course.meta.QuerryInfo;

/**
 * 商品Service层接口
 * 
 * @author 公猴脖子男
 */
public interface ProductService extends BaseService<Product> {

	/**
	 * 根据Id查找商品
	 * 
	 * @param productId
	 *            商品Id
	 * @return
	 */
	Product select(String productId);

	/**
	 * 根据分页条件查找分页数据
	 * 
	 * @param querryInfo
	 * @return 分页数据
	 */
	PageBean<Product> getPage(QuerryInfo querryInfo);
}
