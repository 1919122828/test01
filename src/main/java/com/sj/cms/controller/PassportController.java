package com.sj.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.executor.BatchResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sj.cms.domain.User;
import com.sj.cms.utils.CMSConstant;
import com.sj.cms.utils.CMSRuntimeEx;
import com.sj.cms.vo.UserVO;
@RequestMapping("passport")
@Controller
public class PassportController {
	@Resource
	private com.sj.cms.service.UserService userService;
	/**
	 * 
	 * @Title: toreg 
	 * @Description: 去注册
	 * @return
	 * @return: String
	 */
	@GetMapping(value = "reg")//只支持get请求
	public String toreg(UserVO userVO) {
		return "passport/reg";
	}
	/**
	 * 
	 * @Title: toreg 
	 * @Description: 注册
	 * @return
	 * @return: String
	 */
	@PostMapping(value = "reg")//只支持post请求
	public String toreg(@Valid UserVO userVO,BindingResult result,Model model,RedirectAttributes redirectAttributes) {
		
		//如果没满足那就回到注册页面
		if(result.hasErrors()) {
			
			return "passport/reg";
		}
		
		try {
			userVO.setRole("0");
			userService.insert(userVO);
			redirectAttributes.addFlashAttribute(userVO);
			return "redirect:login";
		} catch (CMSRuntimeEx e) {
			model.addAttribute("error", e.getMessage());
		}
		catch (Exception e) {
			model.addAttribute("error", "注册系统出现异常");
			
		}
		return "passport/reg";
		
	}
	/**
	 * 
	 * @Title: login 
	 * @Description: 跳转登陆
	 * @param userVO
	 * @return
	 * @return: String
	 */
	@GetMapping("login")
	public String login(UserVO userVO) {
		return "passport/login";
		
	}
	/**
	 * 
	 * @Title: login 
	 * @Description: 登陆
	 * @param userVO
	 * @return
	 * @return: String
	 */
	@PostMapping("login")
	public String login(UserVO userVO,HttpServletRequest request,Model model) {
		
		try {
			
			User u = userService.login(userVO);
			
			HttpSession session = request.getSession(false);
			
			if(u.getRole().equals(CMSConstant.ROLE_GENERAL)) {
				session.setAttribute(CMSConstant.SESSION_GENERAL, u);
				return "redirect:/my";
			}
			
			session.setAttribute(CMSConstant.SESSION_ADMIN, u);
			return "redirect:/admin";
		} catch (CMSRuntimeEx e) {
			
			model.addAttribute("error", e.getMessage());
		}catch (Exception e) {
			
			model.addAttribute("error","系统异常请联系管理员");
		}
		
		
		return "passport/login";
	}
	
	@GetMapping(value = "logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		if(session!=null) {
			session.invalidate();
		}
		
		return "redirect:/passport/login";
		
		
	}
	

}
