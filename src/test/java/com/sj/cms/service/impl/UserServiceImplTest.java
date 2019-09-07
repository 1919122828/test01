package com.sj.cms.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sj.cms.domain.User;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class UserServiceImplTest {

	@Resource
	private com.sj.cms.service.UserService userService;
	@Test
	public void testInsert() {
		
		User u = new User();
		
		u.setUsername("test5");
		u.setPassword("123");
		u.setCreate_time(new Date());
		//userService.insert(u);
	}

}
