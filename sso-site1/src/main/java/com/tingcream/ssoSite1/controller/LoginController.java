package com.tingcream.ssoSite1.controller;

 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	
//	@Value("${sso.ssoServer.logoutUrl}")
//	private String  ssoLogoutUrl;
//	
//	@Value("${sso.ssoClient.logoutPage}")
//	private String  logoutPage;
	
	
	
	
	//退出登录
    @RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
    	System.out.println("回调site1本地退出方法");
		return "/logout.jsp";
	}
//    @RequestMapping("/toLogout")
//    public String toLoginout(HttpServletRequest request,HttpServletResponse response) {
//    	
//    	 String contextPath = request.getContextPath();//   /  /demo
//		 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath; 
//	  
//		String returnUrl =basePath+logoutPage;//得到returnUrl,  http://www.site1.com:8001/logout
//		//String siteSessionId = request.getSession().getId();//局部站点的sessionId
//    	
//		//重定向到sso 去验证
//	     String url =ssoLogoutUrl+"?returnUrl="+returnUrl;;
//		 try {
//			response.sendRedirect(url);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}//浏览器重定向
//		 return null ;
//    }
//    
    

}