package com.netease.course.service.impl;

import org.springframework.stereotype.Service;

import com.netease.course.meta.PageBean;
import com.netease.course.meta.Product;
import com.netease.course.meta.QuerryInfo;
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

	/**
	 * 根据分页条件查找分页数据
	 * 
	 * @param querryInfo
	 * @return 分页数据
	 */
	@Override
	public PageBean<Product> getPage(QuerryInfo querryInfo) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setCurrentPage(querryInfo.getCurrentPage());
		pageBean.setPageSize(querryInfo.getPageSize());
		pageBean.setTotalRecord(productDao.getRecord(querryInfo));
		pageBean.setList(productDao.getPageData(querryInfo));
		return pageBean;
	}

}
