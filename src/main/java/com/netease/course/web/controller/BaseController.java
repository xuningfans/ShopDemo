package com.netease.course.web.controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Controller
public class BaseController {
	
	@Resource
	ServletContext application;
	
	public static final ObjectMapper OM = new ObjectMapper();
	public static final ObjectWriter OW = new ObjectMapper().writer().withDefaultPrettyPrinter();
}
