/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: Vote.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.domain 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月26日 下午2:05:46 
 * @version: V1.0   
 */
package com.sj.cms.domain;

import java.io.Serializable;

/**
 * 
 * @ClassName: Vote 
 * @Description: TODO
 * @author: 19191
 * @date: 2019年8月26日 下午3:39:55
 */
public class Vote implements Serializable {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private Integer articleId;
	private String optionId;
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the articleId
	 */
	public Integer getArticleId() {
		return articleId;
	}
	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	/**
	 * @return the optionId
	 */
	public String getOptionId() {
		return optionId;
	}
	/**
	 * @param optionId the optionId to set
	 */
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
	
}
