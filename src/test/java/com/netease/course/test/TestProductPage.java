package com.netease.course.test;

import org.junit.Test;

import com.netease.course.meta.PageBean;
import com.netease.course.meta.Product;
import com.netease.course.meta.QuerryInfo;

public class TestProductPage extends BaseTest{

	@Test
	public void Test01(){
		QuerryInfo querryInfo = new QuerryInfo();
		querryInfo.setCurrentPage(1);
		querryInfo.setPageSize(2);
		PageBean<Product> page = productService.getPage(querryInfo);
		System.out.println(page.getCurrentPage());
		System.out.println(page.getList().get(1));
		System.out.println(page.getTotalPage());
		System.out.println(page.getNextPage());
		Integer[] pageBar = page.getPageBar();
		for (Integer integer : pageBar) {
			System.out.println(integer);
		}
		System.out.println(page.getPageSize());
		System.out.println(page.getPreviousPage());
		System.out.println(page.getTotalRecord());
		System.out.println(page);
	}
}
