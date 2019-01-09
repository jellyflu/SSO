package com.tingcream.ssoClient.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.AntPathMatcher;

import com.tingcream.ssoClient.configuration.SessionInfoHelper;
import com.tingcream.ssoClient.model.SessionInfo;

public class SsoLoginAuthFilter  implements Filter {
	
	private SessionInfoHelper sessionInfoHelper;
 	private String ssoCheckLoginUrl ;
 	private List<String>  excludePaths;//过滤器拦截排除的路径 支持多个，支持ant路径表达式
 	
 	private  AntPathMatcher antPathMatcher=new AntPathMatcher();
 	
	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}
	public void setSsoCheckLoginUrl(String ssoCheckLoginUrl) {
		this.ssoCheckLoginUrl = ssoCheckLoginUrl;
	}
	public void setExcludePaths(List<String> excludePaths) {
		this.excludePaths = excludePaths;
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		
		HttpServletRequest request =(HttpServletRequest)req;
		HttpServletResponse response =(HttpServletResponse)resp;
		
		System.out.println("SsoLoginAuthFilter 执行");
		 //---下面 需要验证用户是否已登录，先验证本地，如果本地未登录则使浏览器重定向到sso去验证
		
		
		 String contextPath = request.getContextPath();//   /  /demo
//		 //  http://www.site1.com:8001/
		 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath; 
	  
		String uri=request.getRequestURI();
		String requestPage=uri.replaceFirst(contextPath, "").replaceAll("/+", "/"); // 请求页面  /aa/bb 
		
		
		for(String  pattern :excludePaths) {
			 if(antPathMatcher.match(pattern, requestPage)) {
				 chain.doFilter(request, response); //排除的路径， ok放行
		         return;
			 }
		}
		
		String returnUrl =basePath+requestPage;//得到returnUrl,  http://www.site1.com:8001/aa/bb
		String siteSessionId = request.getSession().getId();//局部站点的sessionId
		
		SessionInfo sessionInfo= sessionInfoHelper.getSessionInfo(siteSessionId);
		if(sessionInfo!=null) {//如果存在，则说明本地已登录了
			chain.doFilter(request, response); //放行
            return;
		}else {
			//重定向到sso 去验证
			 String url =ssoCheckLoginUrl+"?returnUrl="+returnUrl+"&_0x1d2f="+siteSessionId;// _0x1d2f  siteSessionId
			 response.sendRedirect(url);//浏览器重定向
			 return ;
		}
	}


	
	@Override
	public void destroy() {
		
	}
	

}
