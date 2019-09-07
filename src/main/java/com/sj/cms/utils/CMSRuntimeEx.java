package com.sj.cms.utils;
/**
 * 
 * @ClassName: CMSRuntimeEx 
 * @Description: 自定义异常
 * @author: 19191
 * @date: 2019年8月9日 下午3:35:35
 */
public class CMSRuntimeEx extends RuntimeException{
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	
	
	public CMSRuntimeEx() {
		super();
	}
	
	
	public CMSRuntimeEx(String message) {
		super(message);
	}
	
	

}
