package com.sj.cms.service;

import com.github.pagehelper.PageInfo;
import com.sj.cms.domain.User;
import com.sj.cms.vo.UserVO;

public interface UserService {
	/**
	 * 
	 * @Title: insert 
	 * @Description: 添加
	 * @param u
	 * @return: void
	 */
	boolean insert(UserVO u);
	/**
	 * 
	 * @Title: selects 
	 * @Description: 展示分页
	 * @param username
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: PageInfo<Object>
	 */
	PageInfo<Object> selects(String username, Integer page, Integer pageSize);
	/**
	 * 
	 * @Title: login 
	 * @Description: 登陆
	 * @param userVO
	 * @return
	 * @return: UserVO
	 */
	User login(UserVO userVO);
	
	int updateByPrimaryKeySelective(User user);

}
