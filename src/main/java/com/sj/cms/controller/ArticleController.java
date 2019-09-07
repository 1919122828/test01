package com.sj.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sj.cms.domain.Article;
import com.sj.cms.domain.Cms_vote;
import com.sj.cms.domain.User;
import com.sj.cms.domain.Vote;
import com.sj.cms.service.VoteService;
import com.sj.cms.utils.ArticleEnum;
import com.sj.cms.utils.CMSConstant;
import com.sj.cms.utils.CookieUtil;
import com.sj.cms.utils.JsonUtil;
import com.sj.cms.utils.PageUtil;
import com.sj.cms.vo.ArticleVO;

@RequestMapping("article")
@Controller
public class ArticleController {
	
	@Resource
	private com.sj.cms.service.ArticleService articleService;
	@Resource
	private VoteService voteService;
	

	/**
	 * 
	 * @Title: updateHits 
	 * @Description: 更新
	 * @param article
	 * @param request
	 * @param response
	 * @return: void
	 */
	@PostMapping("updateHits")
	public void updateHits(Article article, HttpServletRequest request, HttpServletResponse response) {
		// 获取hitId 的cookie,
		Cookie cookie = CookieUtil.getCookie(request, "hitId");
		// 如果已经更新过则不更新
		if (CookieUtil.isCheck(cookie, article.getId() + "", response)) {
			
			
			// 获取当前的文章点击量
			Article a2 = articleService.selectByPrimaryKey(article.getId());
			// //增加点击量+1
			article.setHits(a2.getHits() + 1);
			articleService.updateByPrimaryKeySelective(article);

		}

	}
	
	/**
	 * 
	 * @Title: select 
	 * @Description: TODO
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	/*
	 * @GetMapping("select") public String select(Integer id, Model model) { Article
	 * article = articleService.selectByPrimaryKey(id);
	 * model.addAttribute("article", article); return "article/detail";
	 * 
	 * }
	 */
	
	/**
	 * 
	 * @Title: vote 
	 * @Description:投票
	 * @param vote
	 * @param session
	 * @return
	 * @return: boolean
	 */
		@ResponseBody
		@PostMapping("vote")
		public boolean vote(Vote vote, HttpSession session) {

			try {
	           //从session获取对象
			
			  User user = (User) session.getAttribute(CMSConstant.SESSION_GENERAL);
			  if(null != user) {
				  vote.setUserId(user.getId());
				  
				  voteService.insert(vote) ;
				  return true;
			  }
			 

			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
	/**
	 * 
	 * 
	 * @Title: selectVote
	 * @Description: 查看投票
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping(value = "selectVote")
	public String selectVote(Integer id, Model model) {

		Article article = articleService.selectByPrimaryKey(id);
		
		
		//把json转为java
		List<Map<String, Object>> list = JsonUtil.gsonToListMaps(article.getContent());

		model.addAttribute("article", article);
		model.addAttribute("mapList", list);
		
		//查询投票结果
		List<Map> voteMap = voteService.select(id);

		model.addAttribute("voteMapList", voteMap);
		
		return "article/detailvote";

	}
	/**
	 * 
	 * @Title: publishVote 
	 * @Description: 去发起投票
	 * @param descr
	 * @param article
	 * @param request
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("publishVote")
	public boolean publishVote(String[] descr, ArticleVO article, HttpServletRequest request) {

		List<Map> list = new ArrayList<>();

		// 遍历选项内容
		if (null != descr && descr.length > 0) {
			for (int i = 0; i < descr.length; i++) {
				Map<String, Object> map = new HashMap<>();
				// 封装选项
				char x = 'A';
				map.put(String.valueOf((char) (x + i)), descr[i]);
				list.add(map);
			}

		}
		// 把java转为json
		String content = JsonUtil.gsonString(list);
	//	System.out.println(content);
		article.setContentType(ArticleEnum.JSON.getCode());// 文章类型
		// 封装
		article.setContent(content);

		article.setHits(0);
		article.setStatus(0);
		article.setCreated(new Date());
		article.setUpdated(new Date());
		HttpSession session = request.getSession(false);
		if (null == session)
			return false;
		User user = (User) request.getSession().getAttribute(CMSConstant.SESSION_GENERAL);
		if (null != user)
			article.setUserId(user.getId());
		article.setDeleted(0);
		article. setHot(0);

		return articleService.insertSelective(article) > 0;

	}
	/**
	 * 
	 * @Title: publishVote 
	 * @Description: 去发起投票
	 * @return
	 * @return: String
	 */
	@GetMapping("publishVote")
	public String publishVote() {

		return "article/publishvote";
	}
	
	
	Gson gson = new Gson();
	
	/**
	 * 去发布图片集
	 * 
	 * @Title: publishPic
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	
	@ResponseBody
	@PostMapping("publishPic")
	public boolean publishPic(ArticleVO article, String[] descr ,MultipartFile[] files,HttpServletRequest request) {

		
		//发布文章类型
		
		article.setContentType(ArticleEnum.IMAGE.getCode());
		
		List<ArticleVO> list = new ArrayList<>();
		String newFilename="";
		
		if (files.length > 0) {
			 int i=0;
			for (MultipartFile file : files) {

				if (!file.isEmpty()) {
					
					ArticleVO vo = new ArticleVO();
					
					String path = "d:/pic/";// 文件上传的路径

					// 获取原始名称
					String filename = file.getOriginalFilename();
					// 为了防止文件名称重复.采用随机的UUID的值
					 newFilename = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));

					File f = new File(path + newFilename);

					try {
						file.transferTo(f);// 把文件写入硬盘
						//article.setPicture(newFilename);
						//封装图片描述
						vo.setDescr(descr[i]);
						i++;
						//封装的url
						vo.setUrl(newFilename);
						list.add(vo);
						
						
					} catch (IllegalStateException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		}
		
		//图片集封面
		article.setPicture(newFilename);
		String json = gson.toJson(list);
		//
		article.setContent(json);
		
		
		article.setHits(0);
		article.setStatus(0);
		article.setCreated(new Date());
		article.setUpdated(new Date());
		HttpSession session = request.getSession(false);
		if (null == session)
			return false;
		User user = (User) request.getSession().getAttribute(CMSConstant.SESSION_GENERAL);
		article.setUserId(user.getId());
		article.setDeleted(0);
		article.setHot(0);

		return articleService.insertSelective(article) > 0;

	}
	
	 /**
     * 获取所有的图片集
     * @Title: selectsPic 
     * @Description: TODO
     * @return
     * @return: String
     */
    @GetMapping("selectPic")
    public String selectsPic(Model model ,Integer id) {
    	List<ArticleVO> list = new ArrayList<>();
    	
    	Article article = articleService.selectByPrimaryKey(id);
    	//获取json串
    	String string = article.getContent();
    //解析字符串
    	JsonArray jsonArray = new JsonParser().parse(string).getAsJsonArray();
    	for (JsonElement jsonElement : jsonArray) {
    		//把json串转为java对象
    		ArticleVO vo = gson.fromJson(jsonElement, ArticleVO.class);
    		list.add(vo);
		}
    	
    	
    	
    	
    	model.addAttribute("images", list);	
    	
    	return "article/detailpic";//显示图片明细
    	
    }
	
	@RequestMapping("publishPic")
	public String show() {
		return "my/article/publishpic";
	}
	
	
	/**
	 * 审核文章
	 * @Title: check 
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("check")
	public boolean check(Article article) {
		
		
		return articleService.updateByPrimaryKeySelective(article)>0;
		
	}
	/**
	 * 查询单个文章内容
	 * @Title: select 
	 * @Description: TODO
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping(value = "selectByAdmin")
	public String selectByAdmin(Integer id,Model model) {
		Article article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		return "admin/article/content";
		
	}
	/**
	 * 查询单个文章内容
	 * @Title: select 
	 * @Description: TODO
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	
	  @GetMapping(value = "select") public String select(Integer id,Model model) {
	  Article article = articleService.selectByPrimaryKey(id);
	  model.addAttribute("article", article); return "article/detail";
	  
	  }
	 
	

	
	
	/**
	 * 返回所有文章标题
	 * @Title: selects 
	 * @Description: TODO
	 * @param article
	 * @param page
	 * @param pageSize
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping("selects")
	public String selects(Article article,@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize,Model model) {
		
		//根据角色查询对应的文章.待完善
		
		PageInfo<Map> info = articleService.selects(article, page, pageSize);
		
		String pages = PageUtil.page(page, info.getPages(), "/article/selects", pageSize);
		
		model.addAttribute("pages", pages);
		model.addAttribute("titles", info.getList());
		model.addAttribute("article", article);
		
		return "/article/titles";
	}
	
	
	/**
	 * 管理员返回所有文章标题
	 * @Title: selects 
	 * @Description: TODO
	 * @param article
	 * @param page
	 * @param pageSize
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping("selectsByAdmin")
	public String selectsByAdmin(Article article,@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize,Model model) {
		
		//根据角色查询对应的文章.待完善
		
		PageInfo<Map> info = articleService.selects(article, page, pageSize);
		
		String pages = PageUtil.page(page, info.getPages(), "/article/selectsByAdmin", pageSize);
		
		model.addAttribute("pages", pages);
		model.addAttribute("titles", info.getList());
		model.addAttribute("article", article);
		
		return "admin/article/titles";
	}
	
	

	/**
	 * 
	 * @Title: publish 
	 * @Description: 去发布文章
	 * @return
	 * @return: String
	 */
	@GetMapping("publish")
	public String publish() {
		return "article/publish";
	}
	/**
	 * 
	 * @Title: publish 
	 * @Description: 发布
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@PostMapping("publish")
	public boolean publish(Article article,MultipartFile file,HttpServletRequest request) {
		article.setContentType(ArticleEnum.HTML.getCode());
		String path="d:/pic/";
		if(!file.isEmpty()) {
			//把文件的名称改成uuid
			String filename = file.getOriginalFilename();
			
			String newfilename = UUID.randomUUID()+filename.substring(filename.lastIndexOf("."));
			
			File f = new File(path+newfilename);
			
			try {
				file.transferTo(f);
				article.setPicture(newfilename);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		article.setHits(0);
		article.setStatus(0);
		article.setCreated(new Date());
		article.setUpdated(new Date());

		
		article.setHot(0);
		
		User user = (User) request.getSession().getAttribute(CMSConstant.SESSION_GENERAL);
		article.setUserId(user.getId());
		
		return articleService.insertSelecttive(article)>0;
		
	}
	
	@Resource
	private com.sj.cms.service.Cms_voteService service;
	
	/**
	 * 
	 * @Title: totp 
	 * @Description: 去投票
	 * @return
	 * @return: String
	 */
	@RequestMapping("tp")
	public String totp() {
		
		return "article/tp";
	}
	/**
	 * 
	 * @Title: addvote 
	 * @Description: 发起投票
	 * @return
	 * @return: boolean
	 */
	public boolean addvote(String [] desc,Article article) {
		
		ArrayList<Map> arrayList = new ArrayList<Map>();
		
		char i = 'A';
		for (String str : desc) {
			HashMap<Object,Object> map = new HashMap<Object,Object>();
			
			map.put(i, str);
			arrayList.add(map);
			i++;
		}
		//转换为JSON
		String gsonString = JsonUtil.gsonString(arrayList);
		
		article.setContent(gsonString);
		
		
		return service.articleService(article)>0;
	}
	@ResponseBody
	@RequestMapping("looktitle")
	public boolean looktitle(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		
		Cookie cookie = CookieUtil.getCookie(request, "uname");
		if(null==session) {
			
			return false;
		}
		if(null!=session) {
			String user = cookie.getName();
			if(user!=null) {
				
				service.update(1);
				return true;
			}
		}
		return false;
		
	}
	
	
	
	
}
