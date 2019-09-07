package com.sj.cms.service;

import java.util.List;

import com.sj.cms.domain.Category;
import com.sj.cms.domain.Channel;

public interface ChannelService {

	List selectChannel();

	List selectCategorysByCid(Integer cid);



	

}
