package com.sj.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("admin")
@Controller
public class AdminController {
	
	/**
	 * 
	 * @Title: show 
	 * @Description: 进入后台
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = {"","/","index"})
	public String show() {
		return "admin/index";
	}
	

}
