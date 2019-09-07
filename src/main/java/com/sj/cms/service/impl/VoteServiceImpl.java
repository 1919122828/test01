/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: VoteServiceImpl.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.service 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月26日 下午2:11:31 
 * @version: V1.0   
 */
package com.sj.cms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sj.cms.dao.VoteMapper;
import com.sj.cms.domain.Vote;
import com.sj.cms.service.VoteService;

/**
 * 
 * @ClassName: VoteServiceImpl 
 * @Description: TODO
 * @author: 19191
 * @date: 2019年8月26日 下午3:42:03
 */
@Service
public class VoteServiceImpl implements VoteService {
	
	@Resource
	private VoteMapper voteMapper;

	/* (non Javadoc) 
	 * @Title: selects
	 * @Description: TODO
	 * @return 
	 * @see com.bobo.cms.service.VoteService#selects() 
	 */
	@Override
	public List<Vote> selects() {
		// TODO Auto-generated method stub
		return voteMapper.selects();
	}

	/* (non Javadoc) 
	 * @Title: insert
	 * @Description: TODO
	 * @param vote
	 * @return 
	 * @see com.bobo.cms.service.VoteService#insert(com.bobo.cms.domain.Vote) 
	 */
	@Override
	public int insert(Vote vote) {
		// TODO Auto-generated method stub
		return voteMapper.insert(vote);
	}

	@Override
	public List<Map> select(Integer id) {
		// TODO Auto-generated method stub
		return voteMapper.select(id);
	}

}
