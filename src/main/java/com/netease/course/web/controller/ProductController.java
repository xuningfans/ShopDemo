package com.netease.course.web.controller;

import static com.netease.course.web.utils.WebUtil.getUserByCookieAndSession;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.apache.commons.lang3.StringUtils.isBlank;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.netease.course.meta.Product;
import com.netease.course.meta.User;
import com.netease.course.service.ProductService;
import com.netease.course.web.utils.MyFileUtils;

/*******************************************
 * 产品相关页面
 * 
 * @author 公猴脖子男
 *
 *******************************************/
@Controller
public class ProductController extends BaseController {

	@Autowired
	ProductService productService;

	/*******************************************
	 * 添加产品GET请求，回显商品添加ftl
	 * 
	 * @return
	 ********************************************/
	@RequestMapping(value = "/addproduct", method = RequestMethod.GET)
	public String addPruduct() {
		return "back/manager/addproduct";
	}

	/*******************************************
	 * 增加产品POST请求，接收form表单方法
	 * 
	 * @param product
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 ********************************************/
	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	public String addPruduct(@ModelAttribute Product product, String price, HttpServletRequest request,
			ModelMap model) {

		// 将当前ServletContext初始化给CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(application);

		// 获取项目根目录位置
		String rootPath = application.getRealPath("/");

		// 检查表单是否为enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			try {
				// 保存文件
				MyFileUtils.saveFile(rootPath, product, request);

				if (validProduct(product, price, model)) {
					// 有非法输入项，返回并回显提示
					model.addAttribute("product", product);
					return "back/manager/addproduct";
				}
				productService.insert(product);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 添加成功返回列表页面
		return "redirect:list";
	}

	/*************************************************
	 * 添加修改商品表单验证方法
	 * 
	 * @param product
	 * @param price
	 *            产品价格需单独处理
	 * @param model
	 * @return
	 **************************************************/
	public boolean validProduct(Product product, String price, ModelMap model) {
		// 以下判断各字段是否为空
		boolean flag = false;
		if (isBlank(product.getProductDetail())) {
			model.addAttribute("productDetailMes", "描述不能为空！");
			flag = true;
		}
		if (isBlank(product.getProductTitle())) {
			model.addAttribute("productTitleMes", "商品名不能为空！");
			flag = true;
		}
		if (isBlank(product.getProductSummary())) {
			model.addAttribute("productSummaryMes", "商品详情不能为空！");
			flag = true;
		}
		if (isBlank(product.getProductImage())) {
			model.addAttribute("productImageMes", "请上传图片！");
			flag = true;
		}

		if (price != null) {
			try {
				// 转型数字失败则证明不是数字
				product.setProductPrice(Double.parseDouble(price));
			} catch (Exception e) {
				model.addAttribute("productPriceMes", "商品价格不能为空且必须为数字！");
				flag = true;
				return flag;
			}
			// 价格为数字，判断是否大于0
			if (product.getProductPrice() < 0) {
				flag = true;
				model.addAttribute("productPriceMes", "商品价格不能小于0 ！");
			}
		}
		return flag;
	}

	/*************************************************
	 * 产品删除请求
	 * 
	 * @return
	 *************************************************/
	@RequestMapping(value = "/deleteproduct/{productId}", method = RequestMethod.GET)
	public String deletePruduct(@PathVariable String productId) {
		try {
			// 拿到web根目录
			String rootPath = application.getRealPath("/");
			// 从数据库查出对应商品全部信息
			Product product = productService.select(productId);
			// 根据路径建立文件
			File file = new File(rootPath + product.getProductImage().substring(1));
			// 删除文件
			if (file.exists()) {
				file.delete();
			}
			// 文件删除成功，处理数据库
			productService.delete(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 重定向至商品列表页
		return "redirect:/list";
	}

	/*************************************************
	 * 产品修改GET请求
	 * 
	 * @param product
	 * @param model
	 * @return
	 *************************************************/
	@RequestMapping(value = { "/editproduct/{productId}" }, method = RequestMethod.GET)
	public String editproduct(@PathVariable String productId, Model model) {
		// 从数据库中查询对应ID商品信息
		Product product = productService.select(productId);
		// 将查询到的商品信息回显
		model.addAttribute("product", product);
		return "back/manager/editproduct";
	}

	/*************************************************
	 * 产品修改POST表单接收方法
	 * 
	 * @param product
	 * @param model
	 * @return
	 *************************************************/
	@RequestMapping(value = { "/editproduct/{productId}" }, method = RequestMethod.POST)
	public String edit(@PathVariable("productId") Integer productId, String price, @ModelAttribute Product product,
			HttpServletRequest request, ModelMap model) {

		// 将当前ServletContext初始化给CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(application);

		String rootPath = application.getRealPath("/");

		// 检查表单是否为enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			try {
				// 获取当前商品图片路径
				String productPath = productService.select(product).getProductImage();
				// 预先建立关联
				File file = new File(rootPath + productPath.substring(1));

				// 保存新上传图片
				MyFileUtils.saveFile(rootPath, product, request);

				// 验证数据是否合法
				if (validProduct(product, price, model)) {
					model.addAttribute("product", product);
					return "back/manager/editproduct";
				}

				// 删除文件
				if (file.exists()) {
					file.delete();
				}

				// 处理数据库
				productService.update(product);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 重定向至主页
		return "redirect:/list";
	}

	/*************************************************
	 * 单个产品详情
	 * 
	 * @param productId
	 * @param model
	 * @return
	 *************************************************/
	@RequestMapping(value = { "/detail/{productId}" }, method = RequestMethod.GET)
	public String detail(@PathVariable @Param("productId") String productId, HttpSession session,
			HttpServletRequest request, ModelMap model) {
		// 查找当前商品所有信息
		Product product = productService.select(productId);

		// 添加数据
		model.addAttribute("product", product);
		// 验证用户权限
		User loginUser = getUserByCookieAndSession(session, request);
		if (loginUser != null) {
			model.addAttribute("user", loginUser);
		}
		// 返回详情页
		return "font/detail";
	}

	/*************************************************
	 * 所有产品列表
	 * 
	 * @param model
	 * @return
	 *************************************************/
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String list(HttpSession session, HttpServletRequest request, Model model) {
		// 查找所有商品
		List<Product> products = productService.selectList();
		// 添加商品数据回显
		model.addAttribute("products", products);

		// 验证用户
		User loginUser = getUserByCookieAndSession(session, request);
		if (loginUser != null) {
			model.addAttribute("user", loginUser);
		}
		return "font/list";
	}

}
