package com.sj.cms.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sj.cms.domain.Links;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class LinksServiceImplTest {

	
	@Resource
	private com.sj.cms.dao.LinksMapper linksMapper;
	@Test
	public void testAddLinks() {
		
		Links links = new Links();
		
		links.setText("百度");
		links.setUrl("http://baidu.com");
		
		linksMapper.addLinks(links);
	}

}
