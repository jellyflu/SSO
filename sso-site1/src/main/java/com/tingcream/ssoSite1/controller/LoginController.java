package com.tingcream.ssoSite1.controller;

 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	
	//退出登录
    @RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
    	System.out.println("回调site1本地退出方法");
		return "/logout.jsp";
	}
 
    

}
