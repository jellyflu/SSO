package com.tingcream.ssoClient.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "sso")
public class SsoProperties {

//	sso: 
//		  ssoServer:
//		    checkLoginUrl: http://www.mysso.com:9001/ssoUser_checkLogin
//		    logoutUrl:  http://www.mysso.com:9001/ssoUser_logout
//		  ssoClient:
//		    logoutPage: /logout
	

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
		private String  checkLoginUrl;
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
		private String  logoutPage;

		public String getLogoutPage() {
			return logoutPage;
		}

		public void setLogoutPage(String logoutPage) {
			this.logoutPage = logoutPage;
		}
	}
	
	
	
}
