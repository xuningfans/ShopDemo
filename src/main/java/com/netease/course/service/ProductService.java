package com.netease.course.service;

import com.netease.course.meta.Product;

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

}
