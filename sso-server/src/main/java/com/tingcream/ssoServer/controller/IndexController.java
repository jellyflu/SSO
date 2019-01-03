package com.tingcream.ssoServer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SSO后台首页
 * @author jelly
 *
 */
@Controller
public class IndexController {

	//SSO后台首页
	@RequestMapping("/")
	public String index(HttpServletRequest request,HttpServletResponse response) {
		return "/index.jsp";
	}
	
	@RequestMapping("/welcome")
	public String welcome(HttpServletRequest request,HttpServletResponse response) {
		return "/welcome.jsp";
	}
	
	
}
