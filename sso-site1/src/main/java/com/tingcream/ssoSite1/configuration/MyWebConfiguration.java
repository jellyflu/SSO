package com.tingcream.ssoSite1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.tingcream.ssoSite1.mvc.LoginAuthInterceptor;


/**
*  mvc配置信息
*   @author jelly
*
*/
@Configuration  
public class MyWebConfiguration  extends WebMvcConfigurerAdapter  {
	
	
	 @Autowired
	 private  LoginAuthInterceptor loginAuthInterceptor;//登录拦截器
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginAuthInterceptor)
				.addPathPatterns("/**");
		//		.excludePathPatterns("/user_toLogin")
		//		.excludePathPatterns("/user_login")
		//		.excludePathPatterns("/user_noLogin")
		//		.excludePathPatterns("/user_noPermission")
		//		.excludePathPatterns("/user_loginImgVerifyCode");
		
	}
	
 


}
