package com.sj.cms.dao;

import java.util.List;

import com.sj.cms.domain.Category;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
/**
 * 
 * @Title: selectCategorysByCid 
 * @Description: TODO
 * @param cid
 * @return
 * @return: List
 */
	List selectCategorysByCid(Integer cid);

	List<Category> selectsByCId(Integer cid);
}