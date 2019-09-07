package com.sj.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("my")
@Controller
public class MyController {

	/**
	 * 
	 * @Title: show 
	 * @Description: 去个人中心
	 * @return
	 * @return: String
	 */
	@GetMapping(value = {"","/","index"})
	public String show() {
		return "my/index";
		
	}
}
