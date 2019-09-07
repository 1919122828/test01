package com.sj.cms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sj.cms.domain.Article;
import com.sj.cms.service.ArticleService;
import com.sj.cms.vo.ArticleVO;
@Service
public class ArticleServiceImpl implements ArticleService {
	@Resource
	private com.sj.cms.dao.ArticleMapper articleMapper;

	@Override
	public int insertSelecttive(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.insertSelective(article);
	}

	@Override
	public Article selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<Map> selects(Article article, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, pageSize);
		
		 List<Map> list = articleMapper.selects(article);
		 PageInfo<Map> info = new PageInfo<Map>(list);
		return info;
	}

	@Override
	public int updateByPrimaryKeySelective(Article record) {
		// TODO Auto-generated method stub
		return articleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertSelective(ArticleVO article) {
		// TODO Auto-generated method stub
		 return articleMapper.insertSelective(article);
	}

}
