package com.netease.course.service;

import com.netease.course.meta.Product;

public interface ProductService extends BaseService<Product> {

	Product select(String productId);

}
