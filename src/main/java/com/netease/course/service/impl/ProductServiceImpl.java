package com.netease.course.service.impl;


import org.springframework.stereotype.Service;

import com.netease.course.meta.Product;
import com.netease.course.service.ProductService;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

	@Override
	public Product select(String productId) {
		if(productId != null){
			Product product = new Product();
			product.setProductId(Integer.valueOf(productId));
			return select(product);
		}
		return null;
	}

}
