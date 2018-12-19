package com.tingcream.ssoSite1.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tingcream.ssoBase.common.RedisHelper;
import com.tingcream.ssoBase.model.SessionInfo;
import com.tingcream.ssoSite1.annotation.LoginAuthAnnotation;

@Component
public class LoginAuthInterceptor implements HandlerInterceptor {
	
	
	
	@Value("${sso.ssoServer.checkLoginUrl}")
	private String  ssoCheckLoginUrl;
	
	@Autowired
	private RedisHelper redisHelper ;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		LoginAuthAnnotation  pac=	 handlerMethod.getMethodAnnotation(LoginAuthAnnotation.class);
	     if(pac==null) {
		     return  true;//controller方法上没有标记这个注解，直接放行
	     } 
	     
		 //---下面 需要验证用户是否已登录，先验证本地，如果本地未登录则使浏览器重定向到sso去验证
			 String contextPath = request.getContextPath();//   /  /demo
			 //  http://www.site1.com:8001/
			 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath; 
		  
			String uri=request.getRequestURI();
			String requestPage=uri.replaceFirst(contextPath, "").replaceAll("/+", "/"); // 请求页面  /aa/bb 
			
			String returnUrl =basePath+requestPage;//得到returnUrl,  http://www.site1.com:8001//aa/bb
			String siteSessionId = request.getSession().getId();//局部站点的sessionId
			
			SessionInfo sessionInfo = (SessionInfo) redisHelper.hget(RedisHelper.SESSION_INFOS, siteSessionId);
			if(sessionInfo!=null) {//如果存在，则说明本地已登录了
				return true ;
			}else {
				//重定向到sso 去验证
			     String url =ssoCheckLoginUrl+"?returnUrl="+returnUrl+"&siteSessionId="+siteSessionId;
				 response.sendRedirect(url);//浏览器重定向
				 return false ;
			}
	}

	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	

}
