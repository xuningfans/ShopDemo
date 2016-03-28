package com.netease.course.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netease.course.meta.Product;
import com.netease.course.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:*.xml")
public class TestProductService {

	@Autowired
	private ProductService productService;

	@Test
	public void testInsert() throws Exception {
		Product product = new Product();
		product.setIsSell(true);
		product.setProductDetail("细节");
		product.setProductImage("图片");
		product.setProductPrice(123.55);
		product.setProductSummary("摘要");
		product.setProductTitle("名称");
		
		productService.insert(product);
	}

	@Test
	public void testDelete() throws Exception {
		Product product = new Product();
		product.setProductId(1);
		productService.delete(product);
	}

	@Test
	public void testUpdate() throws Exception {
		Product product = new Product();
		product.setProductId(2);
		product.setIsSell(true);
		product.setProductDetail("细节111");
		product.setProductImage("图片111");
		product.setProductPrice(123.55);
		product.setProductSummary("摘要111");
		product.setProductTitle("名称111");
		
		productService.update(product);
	}
	
	@Test
	public void testSelectList(){
		List<Product> list = productService.selectList(null);
		for (Product product : list) {
			System.out.println(product);
		}
	}
}
