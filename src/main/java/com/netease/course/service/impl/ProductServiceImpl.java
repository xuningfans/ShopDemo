package com.netease.course.service.impl;

import org.springframework.stereotype.Service;

import com.netease.course.meta.Product;
import com.netease.course.service.ProductService;

/**
 * 商品Service层实现类
 * 
 * @author 公猴脖子男
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

	/**
	 * 根据Id查找商品
	 * 
	 * @param productId
	 *            商品Id
	 * @return
	 */
	@Override
	public Product select(String productId) {
		if (productId != null) {
			Product product = new Product();
			product.setProductId(Integer.valueOf(productId));
			return select(product);
		}
		return null;
	}

}
