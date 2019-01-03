package com.tingcream.ssoServer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tingcream.ssoClient.model.SessionInfo;
import com.tingcream.ssoServer.common.Page;
import com.tingcream.ssoServer.service.SessionInfoService;
import com.tingcream.ssoServer.service.SsoLoginService;
import com.tingcream.ssoServer.util.AjaxUtil;

/**
 * SSO登陆会话 后台管理
 * @author jelly
 *
 */
@Controller
@RequestMapping("/ssoAdmin")
public class SessionInfoController {
	
	@Autowired
	private SessionInfoService  sessionInfoService;
 
	@Autowired
	private SsoLoginService  ssoLoginService ;
	
	//去首页
	@RequestMapping("/sessionInfo_index")
	public String sessionInfo_index() {
		
		return "/sessionInfo/sessionInfo_list.jsp";
	}
	
	
	
	/**
	 * 分页查询  
	 * @param page 分页参数对象
	 * @param browserIp 客户端浏览器ip
	 * @param loginTime  登录时间
	 * @param isOnline  是否在线  1在线 0离线
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sessionInfo_list")
	public Object sessionInfo_list(HttpServletRequest request,
			HttpServletResponse response,Page page,
			String browserIp,String begintime,String endtime ,Integer isOnline) {
		
		Map<String,Object> params =new HashMap<String,Object>();
		params.put("begintime", begintime);
		params.put("endtime", endtime);
		params.put("isOnline", isOnline);
		params.put("browserIp", browserIp);
		
		 Map<String,Object> result =sessionInfoService.findSessionInfoListPage(params,page);
	     return result ;
	 
	}
	
	/**
	 * 强制下线 
	 * @param sessionId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sessionInfo_kickOut")
	public  Object sessionInfo_kickOut(HttpServletRequest request,
			HttpServletResponse response,String sessionId) {
		
		ssoLoginService.setLogout(sessionId);
		
		return AjaxUtil.messageMap(200,"操作成功");
	}
	
	/**
	 * SSO登录会话详情 
	 * @param sessionId
	 * @return
	 */
	 @RequestMapping("/sessionInfo_detail")
	 public  String  sessionInfo_detail(HttpServletRequest request,
				HttpServletResponse response,String sessionId) {
		 
		SessionInfo  sessionInfo = sessionInfoService.findSsoDetail(sessionId);
		request.setAttribute("sessionInfo", sessionInfo);
		  
		return "/sessionInfo/sessionInfo_detail.jsp";
	 }
	
 
	
 
}
