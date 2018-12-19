package com.tingcream.ssoSite1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tingcream.ssoBase.common.RedisHelper;
import com.tingcream.ssoBase.model.SessionInfo;
import com.tingcream.ssoBase.model.User;
import com.tingcream.ssoSite1.annotation.LoginAuthAnnotation;

/**
 * 订单controller  登录后才能访问
 * @author jelly
 *
 */
@Controller
public class OrderController {
	
	@Autowired
	private RedisHelper redisHelper ;
	
	//订单信息1   获取登录用户信息
	@LoginAuthAnnotation
	@RequestMapping("/order_info1")
	public String order_info1(HttpServletRequest request,HttpServletResponse response) {
		 
		String sessionId = request.getSession().getId();
		SessionInfo sessionInfo = (SessionInfo) redisHelper.hget(RedisHelper.SESSION_INFOS, sessionId);
		User sessionUser =  sessionInfo.getUser();
		request.setAttribute("sessionUser", sessionUser);
		return "/order/order_info1.jsp";
	}
	
	//订单信息2   获取登录用户信息
	@LoginAuthAnnotation
	@RequestMapping("/order_info2")
	public String order_info2(HttpServletRequest request,HttpServletResponse response) {
		 
		String sessionId = request.getSession().getId();
		SessionInfo sessionInfo = (SessionInfo) redisHelper.hget(RedisHelper.SESSION_INFOS, sessionId);
		User sessionUser =  sessionInfo.getUser();
		request.setAttribute("sessionUser", sessionUser);
		return "/order/order_info2.jsp";
	}
 
}
