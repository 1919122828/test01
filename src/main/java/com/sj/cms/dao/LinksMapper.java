package com.sj.cms.dao;

import java.util.List;

import com.sj.cms.domain.Links;

public interface LinksMapper {
	/**
	 * 
	 * @Title: addLinks 
	 * @Description: 添加
	 * @param links
	 * @return
	 * @return: int
	 */
	int addLinks(Links links);
	/**
	 * 
	 * @Title: links 
	 * @Description: 查询
	 * @return
	 * @return: List<Object>
	 */
	List<Object> selectslinks();

}
