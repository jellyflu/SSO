package com.tingcream.ssoServer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tingcream.ssoClient.configuration.SessionInfoHelper;
import com.tingcream.ssoClient.model.SessionInfo;
import com.tingcream.ssoClient.model.User;
import com.tingcream.ssoServer.common.SsoLoginHelper;
import com.tingcream.ssoServer.service.SsoLoginService;
import com.tingcream.ssoServer.util.NetworkUtils;

@Controller
public class SsoLoginController {
  	@Autowired
	private SsoLoginService ssoLoginService;
  	
  	@Autowired
  	private SsoLoginHelper ssoLoginHelper;
  	
  	
  	@Autowired
  	private  SessionInfoHelper  sessionInfoHelper;
	
// test
//  	@RequestMapping("/ssoUser_toLogin")
//  	public String  ssoUser_toLogin(HttpServletRequest request,HttpServletResponse response) {
//  		request.setAttribute("errorMsg", "用户名或密码错误");
//  		return "/ssoUser_login.jsp";
//  	}
	
	/**
	 * sso检测用户登录 (此时用户浏览器在局部上未登录，但全局上可能已经登录了)
	 *   http://www.mysso.com:9001/ssoUser_checkLogin?returnUrl=http://www.site1.com:8001/order_info1&siteSessionId=xxxx
	 * @param returnUrl
	 * @param _0x1d2f siteSessionId
	 * @return
	 */
  	@CrossOrigin()//支持ajax跨域
	@RequestMapping("/ssoUser_checkLogin")
	public String ssoUser_checkLogin(HttpServletRequest request,HttpServletResponse response
			,String returnUrl,String _0x1d2f
			) {
  		String siteSessionId=_0x1d2f;
		String  ssoSessionId=request.getSession().getId();//sso全局sessionId
		String  browserName= NetworkUtils.getBrowserName(request);//浏览器名称
		String  browserIp= NetworkUtils.getBrowserIp(request);//浏览器ip
		String domain =ssoLoginHelper.getDomainFormReturnUrl(returnUrl);
		int  result =ssoLoginService.checkLogin(domain, siteSessionId, ssoSessionId,browserIp,browserName);
		if(result==1) {
			//1 需要重定向到returnUrl
			 return "redirect:"+returnUrl;//浏览器重定向
			
		}else {
			//2 需要内部转发到用户登录页
			request.setAttribute("_0x1d2f", siteSessionId);// 
		  	request.setAttribute("returnUrl", returnUrl);//
			return "/ssoUser_login.jsp";
		}
	}
    /**
     * sso用户提交数据登录   (此时用户浏览器在局部上未登录，全局也未登录)
     * @param username  用户名
     * @param password  密码
     * @param  _0x1d2f  siteSessionId
     * @param  returnUrl
     * @return
     */
	@RequestMapping("/ssoUser_login")
	public String ssoUser_login(HttpServletRequest request,HttpServletResponse response,
			String username,String password,String  _0x1d2f,String returnUrl) {
		 if(StringUtils.isBlank(username)) {
			 request.setAttribute("errorMsg", "用户名不能为空!");
			 return "/ssoUser_login.jsp";
		 }  
		 if(StringUtils.isBlank(password)) {
			 request.setAttribute("errorMsg", "密码不能为空!");
			 return "/ssoUser_login.jsp";
		 }  
		 
		 
		 String  siteSessionId=_0x1d2f;
		 String ssoSessionId=request.getSession().getId();//sso全局sessionId
		 String  browserName= NetworkUtils.getBrowserName(request);//浏览器名称
		 String  browserIp= NetworkUtils.getBrowserIp(request);//浏览器ip
		 String domain =ssoLoginHelper.getDomainFormReturnUrl(returnUrl);
		 
		 
		  User user= ssoLoginService.checkUsernamePwd(username, password);
		  if(user==null) {
			  request.setAttribute("_0x1d2f", siteSessionId);
			  request.setAttribute("returnUrl", returnUrl);
			  request.setAttribute("errorMsg", "登录失败,用户名或密码错误!");
			 return "/ssoUser_login.jsp";
		  }else {
			  SessionInfo sessionInfo= sessionInfoHelper.getSessionInfo(ssoSessionId);
			 if(sessionInfo!=null) {
				 return "redirect:"+returnUrl;//浏览器重定向
			 }
			   //用户登录成功  需要重定向到returnUrl
				request.getSession().invalidate();//销毁原session,重新生成session 
				//request.getSession().setAttribute("siteSessionId", siteSessionId);
				
				ssoSessionId=request.getSession().getId();//获取新的全局sessionId
			  
				//创建写入全局会话 、局部会话
			  ssoLoginService.userLogin(siteSessionId, domain, ssoSessionId, browserName, browserIp,user);
			  
			  return "redirect:"+returnUrl;//浏览器重定向
		  }
	}
	
	/**
	 * sso用户注销登录  (此时用户浏览器在全局和局部上都已登录)
	 * http://www.mysso.com:9001/ssoUser_logout?returnUrl=http://www.site1.com:8001/user_logout
	 * 
	 * @param returnUrl
	 * @return
	 */
	@CrossOrigin()//支持ajax跨域
	@RequestMapping("/ssoUser_logout")
	public String ssoUser_logout(HttpServletRequest request,HttpServletResponse response
			,String returnUrl
			) {
		 String  ssoSessionId=request.getSession().getId();//获取sso全局sessionId
		 ssoLoginService.setLogout(ssoSessionId); //sso 会话注销 
		// request.getSession().invalidate(); 
		 
		 return "redirect:"+returnUrl;//浏览器重定向
	}
	
	
	
	 
	
}
