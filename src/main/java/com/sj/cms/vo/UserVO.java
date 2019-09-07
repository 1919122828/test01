package com.sj.cms.vo;

import org.apache.ibatis.annotations.Lang;
import org.hibernate.validator.constraints.Length;

import com.sj.cms.domain.User;

public class UserVO extends User {
	
	@Length(min=6,max=10,message="长度为6-10")
	private String passwords;

	public String getPasswords() {
		return passwords;
	}

	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}
	
	

}
