package com.sj.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sj.cms.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	List selects(String username);

	User selectByName(@Param("username")String username);
}