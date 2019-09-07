package com.sj.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sj.cms.domain.Article;
import com.sj.cms.service.Cms_voteService;
@Service
public class Cms_voteServiceImpl implements Cms_voteService {
	
	@Resource
	private com.sj.cms.dao.Cms_vote cms_vote;

	@Override
	public int articleService(Article article) {
		
		
		
		return cms_vote.articleService(article);
	}

	@Override
	public List looknewtp() {
		// TODO Auto-generated method stub
		return cms_vote.looknewtp();
	}

	@Override
	public void update(int i) {
		// TODO Auto-generated method stub
		cms_vote.update(i);
	}

}
