package com.huizhongcf.mobile.api.Interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessRequired  {
	 
	 
	/**
	 * @description 登陆校验
	 * @return
	 * @author shiyang
	 * 2016年10月19日 下午3:53:36
	 */
	public boolean login() default false;
	

}
