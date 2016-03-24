package com.netease.course.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.netease.course.meta.Product;
import com.netease.course.service.ProductService;

@Controller
public class ProductController extends BaseController {
	
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/addproduct", method = RequestMethod.GET)
	public String addPruduct() {
		return "back/manager/addproduct";
	}

	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	@ResponseBody
	public String addPruduct(@ModelAttribute Product product, HttpServletRequest request) {
		
		String ret = null;
		
		// 将当前ServletContext初始化给CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(application);

		// 检查表单是否为enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request转换成multiRequest
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

			// 获取出multiRequest中的所有文件名
			Iterator<String> iterator = multiRequest.getFileNames();

			String rootPath = application.getRealPath("/resources/images");
			while (iterator.hasNext()) {
				try {
					// 获取每个文件名
					String upFileName = iterator.next();
					// 通过文件名获取每个文件
					MultipartFile multipartFile = multiRequest.getFile(upFileName);
					// 如果没有文件，跳过此次循环
					if(multipartFile.isEmpty()){
						continue;
					}
					// 获取当前时间戳
					String timeStamp = String.valueOf(new Date().getTime());
					// 根据时间戳随机生成二级目录  如：7/8  3/1
					String savePath = rootPath + File.separator
							+ timeStamp.substring(timeStamp.length() - 2, timeStamp.length() - 1) + File.separator
							+ timeStamp.substring(timeStamp.length() - 1);

					// 创建生成的目录
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdirs();
					}
					// 生成最终储存的文件名，加上时间戳防重名，分隔符使用 __
					String fullSavePath = savePath + File.separator + timeStamp + "__"
							+ multipartFile.getOriginalFilename();
					
					//通过流保存上传文件
					FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(fullSavePath));
					
					product.setProductImage(fullSavePath);
					product.setIsSell(true);
					System.out.println(fullSavePath.substring(fullSavePath.indexOf("WEB-INF")));
					System.out.println(product);
					ret = "上传成功";
					productService.insert(product);
				} catch (IOException e) {
					e.printStackTrace();
					//上传异常，上传失败
					ret = "上传失败！";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} else {
			// 表单格式不正确，上传失败
			ret = "上传失败！";
		}
		
		try {
			// 实在搞不定乱码，手动重新编码。。。
			ret = new String(ret.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			//ignore
		}

		return ret;
	}
	
	
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
