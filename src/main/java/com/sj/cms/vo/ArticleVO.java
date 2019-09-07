package com.sj.cms.vo;

import com.sj.cms.domain.Article;

public class ArticleVO extends Article {

	private String url;//图片地址
	private String descr;//图片地址

	
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
