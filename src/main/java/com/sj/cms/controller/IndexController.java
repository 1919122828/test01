/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: IndexController.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.controller 
 * @Description: TODO
 * @author: 19191   
 * @date: 2019年8月20日 下午2:15:08 
 * @version: V1.0   
 */
package com.sj.cms.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.cms.utils.ArticleEnum;
import com.github.pagehelper.PageInfo;
import com.sj.cms.domain.Article;
import com.sj.cms.domain.Category;
import com.sj.cms.domain.Channel;
import com.sj.cms.domain.Links;
import com.sj.cms.service.ArticleService;
import com.sj.cms.service.ChannelService;
import com.sj.cms.utils.PageUtil;

/**
 * 
 * @ClassName: IndexController 
 * @Description: TODO
 * @author: 19191
 * @date: 2019年8月26日 上午9:26:46
 */
@Controller
public class IndexController {
	
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private ChannelService channelService;
	@Resource
	private com.sj.cms.service.LinksService linksService;
	@Resource
	private com.sj.cms.service.Cms_voteService service;
	@RequestMapping("looknewtp")
	public String tpnew(Model model) {
		
		List list  = service.looknewtp();
		model.addAttribute("list", list);
		return "index/index";
	}
	
	/**
	 * 进入首页
	 * @Title: index 
	 * @Description: TODO
	 * @return
	 * @return: String
	 * @throws Exception 
	 */
	@GetMapping(value = {"","/","index"})
	public String index(Article article,@RequestParam(defaultValue = "1")Integer page
			,@RequestParam(defaultValue ="3")Integer pageSize,Model model) throws Exception {
		
		Thread t1 = null;
		Thread t2 = null;
		Thread t3 = null;
		Thread t4 = null;
		Thread t5 = null;
		Thread t6 = null;
		Thread t7 = null;
		Thread t8 = null;
		
		
		
		
		article.setStatus(1);//查询条件为1 .表示审核通过的文章
		
	t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				//所有的栏目
				List<Channel> channels = channelService.selectChannel();
				model.addAttribute("channels", channels);
			}
		});
		t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				//如果栏目ID 为null 则显示热点文章
				if(article.getChannelId()==null) {
					article.setHot(1);//查询条件为热点文章
					PageInfo<Map> info = articleService.selects(article, page, pageSize);
					String pages= PageUtil.page(page, info.getPages(), "/", pageSize);
					
					
					model.addAttribute("hotArticles", info.getList());
					model.addAttribute("pages", pages);
				}
			}
		});
		t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(article.getChannelId()!=null) {
					
					
					//显示栏目下所有分类
					List<Category> categorys = channelService.selectCategorysByCid(article.getChannelId());
					model.addAttribute("categorys", categorys);
				}
			}
		});
			
		t4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				article.setContentType(ArticleEnum.HTML.getCode());
				if(article.getChannelId()!=null) {PageInfo<Map> info = articleService.selects(article, page, pageSize);
				String url="/?channelId="+article.getChannelId();
				
				if(article.getCategoryId()!=null) {
					url+="&categoryId="+article.getCategoryId();
				}
				String pages= PageUtil.page(page, info.getPages(), url, pageSize);
				model.addAttribute("articles", info.getList());
				model.addAttribute("pages", pages);
				
				}
			}
		});
			
		t5 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				//右侧默认现在最新的10篇文章
				PageInfo<Map> info = articleService.selects(article, page, 10);
				model.addAttribute("lasts",info.getList());
			}
		});
		
		t6 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				List<Object> info2 = linksService.selectslinks();
				model.addAttribute("links",info2);
			}
		});
		t7 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Article a = new Article();
				a.setContentType(ArticleEnum.IMAGE.getCode());
				PageInfo<Map> info = articleService.selects(a, 1, 10);
		    	model.addAttribute("images", info.getList());
			}
		});
		
		t8 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				Article a = new Article();
			  //获取json类型的文章..发起的投票文章
		    	a.setContentType(ArticleEnum.JSON.getCode());
		    	PageInfo<Map> info = articleService.selects(a, 1, 10);
		    	
		    	model.addAttribute("votes", info.getList());
				
			}
		});
		
		
		
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		t6.join();
		t7.join();
		t8.join();
		
		
		
		
		return "index/index";
	}

}
