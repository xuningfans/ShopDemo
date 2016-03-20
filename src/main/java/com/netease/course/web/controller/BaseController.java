package com.netease.course.web.controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
	
	@Resource
	ServletContext application;	
}
