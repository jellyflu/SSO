package com.tingcream.ssoClient.model;

import java.io.Serializable;
import java.util.List;

/**
 * 会话信息（SSO全局会话、站点局部会话）
 * @author jelly
 *
 */
public class SessionInfo implements Serializable{

    
	public static final   Integer  SESSIONTYPE_GLOBAL=1;
	public static final   Integer  SESSIONTYPE_SITE=2;
	 
	private static final long serialVersionUID = 1L;
	private String sessionId ;//会话id //主键id uuid
	private Integer sessionType;//1、全局会话  2、局部(站点)会话
	private String parentSessionId;//父会话id
	private String domain;//站点域，如 www.site1.com
	private String browserIp; //浏览器客户端ip
	private String browserName;//浏览器客户端名称
	private String loginTime;//登录时间
	private String logoutTime;//退出时间
	private Integer isOnline=1;//是否在线  1在线  2离线
	
	private String userId;//用户id
	private String username;//用户名
	
	
	
	private User user;//扩展pojo属性
	private  List<SessionInfo> children; //扩展pojo属性
	
 


	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getSessionType() {
		return sessionType;
	}

	public void setSessionType(Integer sessionType) {
		this.sessionType = sessionType;
	}

	public String getParentSessionId() {
		return parentSessionId;
	}

	public void setParentSessionId(String parentSessionId) {
		this.parentSessionId = parentSessionId;
	}

	 
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBrowserIp() {
		return browserIp;
	}

	public void setBrowserIp(String browserIp) {
		this.browserIp = browserIp;
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public List<SessionInfo> getChildren() {
		return children;
	}

	public void setChildren(List<SessionInfo> children) {
		this.children = children;
	}
	
	
	
	
	
	
	

}
