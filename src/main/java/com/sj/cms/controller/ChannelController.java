package com.sj.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("channel")
@Controller
public class ChannelController {
	@Resource
	private com.sj.cms.service.ChannelService channelService;
	/**
	 * 
	 * @Title: channel 
	 * @Description: 栏目
	 * @return
	 * @return: List
	 */
	@ResponseBody
	@GetMapping("selects")
	public List channel() {
		return channelService.selectChannel();
	}
	/**
	 * 
	 * @Title: selectCategorysByCid 
	 * @Description: 栏目下分类
	 * @return
	 * @return: List
	 */
	@ResponseBody
	@GetMapping("selectCategorysByCid")
	public List selectCategorysByCid(Integer cid) {
		return channelService.selectCategorysByCid(cid);
	}

}
