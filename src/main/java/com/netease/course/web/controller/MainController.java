package com.netease.course.web.controller;

import static com.netease.course.web.utils.WebUtil.getUserByCookieAndSession;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netease.course.meta.User;

/**
 * 导航页
 *
 * @author 公猴脖子男
 */
@Controller
public class MainController extends BaseController {
	
	/*********************************************************
	 * 导航页
	 * 
	 * @param model
	 * @return
	 *********************************************************/
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String root() {
		return "index";
	}

	/*********************************************************
	 * 主页
	 * 
	 * @param model
	 * @return
	 *********************************************************/
	@RequestMapping(value = { "/main" }, method = RequestMethod.GET)
	public String main() {
		return "main";
	}

	/*********************************************************
	 * 左侧菜单页
	 * 
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 *********************************************************/
	@RequestMapping(value = { "/menu" }, method = RequestMethod.GET)
	public String menu(HttpSession session, HttpServletRequest request, ModelMap model) {
		// 根据用户显示菜单
		User loginUser = getUserByCookieAndSession(session, request);
		if (loginUser != null) {
			model.addAttribute("user", loginUser);
		}
		return "menu";
	}

	/**********************************************************
	 * 素材下载方法
	 * 
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 **********************************************************/
	@RequestMapping(value = { "/download" }, method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(HttpSession session, HttpServletRequest request, ModelMap model) {

		try {
			File file = new File(application.getRealPath("/") + "WEB-INF/download/book_photo.zip");
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			httpHeaders.setContentDispositionFormData("attachment", "book_photo.zip");
			ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
					httpHeaders, HttpStatus.CREATED);
			return responseEntity;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
