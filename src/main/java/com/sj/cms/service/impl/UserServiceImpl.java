package com.sj.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.sj.cms.domain.User;
import com.sj.cms.service.UserService;
import com.sj.cms.utils.CMSRuntimeEx;
import com.sj.cms.utils.Md5Util;
import com.sj.cms.vo.UserVO;
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private com.sj.cms.dao.UserMapper userMapper;

	@Override
	public boolean insert(UserVO u) {
		// 用户名必须唯一
		User u1 = userMapper.selectByName(u.getUsername());
		if(null != u1)
			throw new CMSRuntimeEx("用户已存在");
		//俩次密码一致
		if(!(u.getPassword().equals(u.getPasswords()))) {
			throw new CMSRuntimeEx("俩次密码不一致");
		}
		
		try {
			u.setPassword(Md5Util.md5Encoding(u.getPassword()));
			userMapper.insert(u);
			return true;
		} catch (Exception e) {
			throw new CMSRuntimeEx("注册失败");
		}
	}



	@Override
	public PageInfo<Object> selects(String username, Integer page, Integer pageSize) {
		
		PageHelper.startPage(page, pageSize);
		
		List list = userMapper.selects(username);
		
		PageInfo<Object> info = new PageInfo<Object>(list);
		
		return info;
	}



	@Override
	public User login(UserVO userVO) {
		//调用工具类测试账户密码是不是为空
		if(!com.sj.common.utils.StringUtil.hasText(userVO.getUsername())) {
			throw new CMSRuntimeEx("用户名不能为空");
		}
		if(!com.sj.common.utils.StringUtil.hasText(userVO.getPassword())) {
			throw new CMSRuntimeEx("密码不能为空");
		}
		
		//去数据库查是否存在用户
		User user = userMapper.selectByName(userVO.getUsername());
		if(null==user) {
			throw new CMSRuntimeEx("此用户无效");
		}else {
			if(!Md5Util.md5Encoding(userVO.getPassword()).equals(user.getPassword())) {
				throw new CMSRuntimeEx("密码不正确");
			}
		}
		
		return  user;
	}



	@Override
	public int updateByPrimaryKeySelective(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(user);
	}
	
	
	
}
