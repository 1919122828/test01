/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: VoteMapper.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.dao 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月26日 下午12:49:59 
 * @version: V1.0   
 */
package com.sj.cms.service;

import java.util.List;
import java.util.Map;

import com.sj.cms.domain.Vote;

/**
 * 
 * @ClassName: VoteService 
 * @Description: TODO
 * @author: 19191
 * @date: 2019年8月26日 下午3:42:09
 */
public interface VoteService {
	//查询
	List<Vote> selects();
	//投票
	int insert(Vote vote);
	//查询投票结果
	List<Map> select(Integer id);

}
