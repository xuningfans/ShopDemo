package com.netease.course.web.controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

/*************************
 * Controller层基类
 *
 * @author 公猴脖子男
 *************************/
public class BaseController {

	/**
	 * 自动注入ServletContext供公用
	 */
	@Resource
	ServletContext application;

}
