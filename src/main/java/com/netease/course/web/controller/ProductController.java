package com.netease.course.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.netease.course.meta.Product;
import com.netease.course.service.ProductService;
import com.netease.course.web.utils.MyFileUtils;

/**
 * @author Administrator
 *
 */
@Controller
public class ProductController extends BaseController {

	@Autowired
	ProductService productService;

	/*******************************************
	 * 增加产品第一次请求，调用ftl
	 * 
	 * @return
	 ********************************************/
	@RequestMapping(value = "/addproduct", method = RequestMethod.GET)
	public String addPruduct() {
		return "back/manager/addproduct";
	}

	/*******************************************
	 * 增加产品form表单提交地址
	 * 
	 * @param product
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 ********************************************/
	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	@ResponseBody
	public String addPruduct(@ModelAttribute Product product, HttpServletRequest request)
			throws UnsupportedEncodingException {

		// 将当前ServletContext初始化给CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(application);

		String rootPath = application.getRealPath("/");

		// 检查表单是否为enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			try {
				MyFileUtils.saveFile(rootPath, product, request);
				productService.insert(product);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "redirect:/main";
	}
	
	
	/*************************************************
	 * 产品删除请求
	 * @return
	 *************************************************/
	@RequestMapping(value = "/deleteproduct/{productId}", method = RequestMethod.GET)
	public String deletePruduct(@PathVariable String productId) {
		System.out.println(productId);
		return "redirect:/main";
	}
	

	/*************************************************
	 * 产品修改第一次请求
	 * 
	 * @param product
	 * @param model
	 * @return
	 *************************************************/
	@RequestMapping(value = { "/editproduct/{productId}" }, method = RequestMethod.GET)
	public String editproduct(@PathVariable String productId, Model model) {
		Product product = productService.select(productId);
		model.addAttribute("product", product);

		return "back/manager/editproduct";
	}

	/*************************************************
	 * 产品修改form提交
	 * 
	 * @param product
	 * @param model
	 * @return
	 *************************************************/
	@RequestMapping(value = { "/editproduct/{productId}" }, method = RequestMethod.POST)
	public String edit(@PathVariable("productId") String productId, @ModelAttribute Product product,
			HttpServletRequest request, Model model) {
		// 将当前ServletContext初始化给CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(application);

		String rootPath = application.getRealPath("/");

		// 检查表单是否为enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			try {
				System.out.println(product);
				String productPath = productService.select(product).getProductImage();
				File file = new File(rootPath + productPath);
				System.out.println(rootPath + productPath);
				if(file.exists()){
					file.delete();
				}
				MyFileUtils.saveFile(rootPath, product, request);
				productService.update(product);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/main";
	}

	/*************************************************
	 * 单个产品详情
	 * 
	 * @param productId
	 * @param model
	 * @return
	 *************************************************/
	@RequestMapping(value = { "/detail/{productId}" }, method = RequestMethod.GET)
	public String edit(@PathVariable @Param("productId") String productId, Model model) {
		Product product = productService.select(productId);
		model.addAttribute("product", product);

		return "font/detail";
	}

	/*************************************************
	 * 产品列表
	 * 
	 * @param model
	 * @return
	 *************************************************/
	@RequestMapping(value = { "/main" }, method = RequestMethod.GET)
	public String main(Model model) {
		List<Product> products = productService.selectList(null);
		for (Product product : products) {
			System.out.println(product);
		}
		model.addAttribute("products", products);

		return "font/main";
	}

}
