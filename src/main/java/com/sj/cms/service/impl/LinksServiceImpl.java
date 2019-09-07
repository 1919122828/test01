package com.sj.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sj.cms.domain.Links;
import com.sj.cms.service.LinksService;
import com.sj.cms.utils.CMSRuntimeEx;
import com.sj.common.utils.StringUtil;
@Service
public class LinksServiceImpl implements LinksService {

	@Resource
	private com.sj.cms.dao.LinksMapper linksMapper;
	
	@Override
	public int addLinks(Links links) {
		if(!StringUtil.isHttpUrl(links.getUrl()))
			throw new CMSRuntimeEx("不是有效URL");
		return linksMapper.addLinks(links);
	}

	@Override
	public List<Object> selectslinks() {
		// TODO Auto-generated method stub
		return linksMapper.selectslinks();
	}



}
