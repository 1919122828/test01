package com.sj.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sj.cms.domain.Category;
import com.sj.cms.domain.Channel;
import com.sj.cms.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService {
	@Resource
	private com.sj.cms.dao.ChannelMapper channelMapper;
	@Resource
	private com.sj.cms.dao.CategoryMapper categoryMapper;
	@Override
	public List selectChannel() {
		// TODO Auto-generated method stub
		return channelMapper.selectChannel();
	}

	@Override
	public List selectCategorysByCid(Integer cid) {
		// TODO Auto-generated method stub
		return categoryMapper.selectCategorysByCid(cid);
	}


	
	
	

}
