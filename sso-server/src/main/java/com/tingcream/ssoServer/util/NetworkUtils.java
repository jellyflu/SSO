package com.tingcream.ssoServer.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 网络工具类
 * @author jelly
 * @date 2018年10月10日 下午3:21:37
 *
 */
public class NetworkUtils {

	/**
	 * 获取客户端ip
	 * @author jelly
	 * @date 2018年10月10日 下午3:21:37
	 * @param request
	 * @return
	 */
	public static String getBrowserIp(HttpServletRequest request){
		 String  header = request.getHeader("X-Real-IP"); 
	        if(StringUtils.isNoneBlank(header) && !header.equalsIgnoreCase("unkown")){
	            return header;
	        }
	        header = request.getHeader("X-Forwarded-For");
	        if (StringUtils.isNoneBlank(header) && !header.equalsIgnoreCase("unkown")){
	            return header;
	        }
	        header = request.getHeader("Proxy-Client-IP");
	        if (StringUtils.isNoneBlank(header) && !header.equalsIgnoreCase("unkown")){
	            return header;
	        }
	        header = request.getHeader("WL-Proxy-Client-IP");
	        if (StringUtils.isNoneBlank(header) && !header.equalsIgnoreCase("unkown")){
	            return header;
	        }
	        return request.getRemoteAddr();
	}
	
	/**
	 * 获取浏览器名称
	 * @author jelly
	 * @date 2018年10月10日 下午3:21:37
	 * @param request
	 * @return
	 */
	public static String  getBrowserName(HttpServletRequest request){
		String userAgent = request.getHeader("User-Agent");
		if(userAgent == null){
			return "";
		}
		userAgent = userAgent.toUpperCase();
		if(userAgent.contains("MSIE") || userAgent.contains("TRIDENT"))
			return "IE";
		else if(userAgent.contains("CHROME")){
			return "CHROME";
		}
		else if(userAgent.contains("FIREFOX")){
			return "FIREFOX";
		}
		else if(userAgent.contains("OPERA")){
			return "OPERA";
		}
		else if(userAgent.contains("SAFARI")){
			return "SAFARI";
		}
		else if(userAgent.contains("QQBROWSER")){
			return "QQBROWSER";
		}
		return "";
	}
}
