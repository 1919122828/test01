package com.sj.cms.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sj.cms.domain.User;
import com.sj.cms.utils.PageUtil;
@RequestMapping("user")
@Controller
public class UserController {

	@Resource
	private com.sj.cms.service.UserService userService;
	/**
	 * 
	 * @Title: userList 
	 * @Description: 展示分页
	 * @param username
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("selects")
	public String userList(@RequestParam(defaultValue = "")String username,Model model,
			@RequestParam(defaultValue = "3")Integer pageSize,
			@RequestParam(defaultValue = "1")Integer page
			) {
		
		PageInfo<Object> info = userService.selects(username,page,pageSize);
		
		String pages = PageUtil.page(page, info.getPages(), "/user/selects?username="+username, pageSize);
		
		model.addAttribute("list", info.getList());
		model.addAttribute("username", username);
		model.addAttribute("pages", pages);
		return "admin/users";
	}
	
	
	/**
	 * 
	 * @Title: update 
	 * @Description:修改用户
	 * @param user
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("update")
	public boolean update(User user) {
		user.setUpdateTime(new Date());
		return userService.updateByPrimaryKeySelective(user)>0;
	}
	
}
