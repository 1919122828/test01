/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: AdminInterceptor.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.util 
 * @Description: TODO
 * @author: 19191   
 * @date: 2019年8月21日 上午8:22:29 
 * @version: V1.0   
 */
package com.sj.cms.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sj.cms.vo.UserVO;



/** 
 * @ClassName: AdminInterceptor 
 * @Description: 管理员后台的拦截器
 * @author: 19191
 * @date: 2019年8月21日 上午8:22:29  
 */
public class GeneralInterceptor extends HandlerInterceptorAdapter {
	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		//false: 如果当前请求如果没有创建session对象,则不创建
		HttpSession session = request.getSession(false);
		//如果session不为null 说明有创建session
		if(session==null || null ==session.getAttribute(CMSConstant.SESSION_GENERAL)) {
			request.setAttribute("userVO",new UserVO());
			request.setAttribute("error", "没有权限!");
			request.getRequestDispatcher("/WEB-INF/views/passport/login.jsp").forward(request, response);;
	        return false;
		}
		return true;
	}
	

}
