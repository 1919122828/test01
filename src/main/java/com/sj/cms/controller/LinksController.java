package com.sj.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.cms.domain.Links;
import com.sj.cms.utils.CMSConstant;
import com.sj.cms.utils.CMSRuntimeEx;

@RequestMapping("links")
@Controller
public class LinksController {
	
	@Resource
	private com.sj.cms.service.LinksService linksService;
	
	@RequestMapping("selects")
	public String show(Model model) {
		List<Object> selectslinks = linksService.selectslinks();
		
		model.addAttribute("list", selectslinks);
		
		return "admin/links/links";
	}
	@RequestMapping("toadd")
	public String toadd(Model model) {
		
		
		return "admin/links/linksadd";
	}
	
	/**
	 * 
	 * @Title: save 
	 * @Description: TODO
	 * @param links
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("save")
	public boolean save(Links links,Model model) {
		
		try {
			return linksService.addLinks(links) > 0;
			
		} catch (CMSRuntimeEx e) {
			
			model.addAttribute("error", e.getMessage());
			return false;
		}
		
	}
	

}
