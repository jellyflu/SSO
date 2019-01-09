package com.tingcream.ssoClient.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tingcream.ssoClient.properties.SsoProperties;

@Controller
@EnableConfigurationProperties(SsoProperties.class)
public class SsoLogoutController {
	
	 @Autowired
	 private  SsoProperties  ssoProperties;
		
	 @RequestMapping("/ssoLogout")
	 public String ssoLogout(HttpServletRequest request,HttpServletResponse response) {
	    	
	    	 String contextPath = request.getContextPath();//   /  /demo
			 String basePath = request.getScheme()+"://"+
	    	 request.getServerName()+":"+request.getServerPort()+contextPath; 
			 
			 //sso退出地址
			 String  ssoLogoutUrl= ssoProperties.getSsoServer().getLogoutUrl();
			 
			 //客户端退出回调页面
			 String  logoutPage=ssoProperties.getSsoClient().getLogoutPage();
		  
			String returnUrl =basePath+logoutPage;//得到returnUrl,  http://www.site1.com:8001/logout
			//String siteSessionId = request.getSession().getId();//局部站点的sessionId
			//重定向到sso 去退出
			 try {
				 response.sendRedirect(ssoLogoutUrl+"?returnUrl="+returnUrl);
				 return null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			 return null ;
	 }

}
