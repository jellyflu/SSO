package com.tingcream.ssoClient.properties;


import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sso")
public class SsoProperties {

//	sso: 
//		  ssoServer:
//		    checkLoginUrl: http://www.mysso.com:9001/ssoUser_checkLogin
//		    logoutUrl:  http://www.mysso.com:9001/ssoUser_logout
//		  ssoClient:
//		    logoutPage: /logout
	//      excludePath: /logout,/bb,/cc 
	

	private SsoServer  ssoServer;
	private SsoClient  ssoClient;
	
	
	public SsoServer getSsoServer() {
		return ssoServer;
	}
	public void setSsoServer(SsoServer ssoServer) {
		this.ssoServer = ssoServer;
	}
	public SsoClient getSsoClient() {
		return ssoClient;
	}
	public void setSsoClient(SsoClient ssoClient) {
		this.ssoClient = ssoClient;
	}
	public static class SsoServer {
		/**
		 * sso 用户登录验证地址
		 */
		private String  checkLoginUrl;
		/**
		 * sso 用户退出地址
		 */
		private String  logoutUrl;
		public String getCheckLoginUrl() {
			return checkLoginUrl;
		}
		public void setCheckLoginUrl(String checkLoginUrl) {
			this.checkLoginUrl = checkLoginUrl;
		}
		public String getLogoutUrl() {
			return logoutUrl;
		}
		public void setLogoutUrl(String logoutUrl) {
			this.logoutUrl = logoutUrl;
		}
	}
	public static class SsoClient {
		/**
		 * 客户端退出回调页面
		 */
		private String  logoutPage;
		/**
		 * 客户端登录拦截器 排除的拦截路径
		 */
		private List<String> excludePaths= new ArrayList<String>();
		public String getLogoutPage() {
			return logoutPage;
		}
		public void setLogoutPage(String logoutPage) {
			this.logoutPage = logoutPage;
		}
		public List<String> getExcludePaths() {
			return excludePaths;
		}
		public void setExcludePaths(List<String> excludePaths) {
			this.excludePaths = excludePaths;
		}

		 
 
		
	}
	
	
	
}
