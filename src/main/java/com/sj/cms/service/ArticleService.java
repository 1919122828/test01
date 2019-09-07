package com.sj.cms.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.sj.cms.domain.Article;
import com.sj.cms.vo.ArticleVO;

public interface ArticleService {

	int insertSelecttive(Article article);

	Article selectByPrimaryKey(Integer id);

	PageInfo<Map> selects(Article article, Integer page, Integer pageSize);

	int updateByPrimaryKeySelective(Article record);

	int insertSelective(ArticleVO article);
	

}
