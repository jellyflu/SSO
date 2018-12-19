package com.tingcream.ssoServer.common;

import org.springframework.stereotype.Component;

@Component
public class SsoLoginHelper {

	/**
	 * 从returnUrl中截取出site域  ，如 <br/>
	 *  http://www.site1.com:8001/aa/bb 截取出 www.site1.com  <br/>
	 *  https://www.site2.com/aa/cc 截取出 www.site2.com  <br/>
	 * @param returnUrl 
	 * @return
	 */
	public String getDomainFormReturnUrl(String returnUrl) {
		String str="";
		if(returnUrl.startsWith("http://")) {
			str=returnUrl.replaceFirst("http://", "");
		}else if(returnUrl.startsWith("https://")) {
			str=returnUrl.replaceFirst("https://", "");
		}
		 String[] ss= str.split("/");
		 return ss[0].split(":")[0];
	}
	
 
}
