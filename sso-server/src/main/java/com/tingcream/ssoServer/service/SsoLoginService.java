package com.tingcream.ssoServer.service;

import java.util.Date;
import java.util.List;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tingcream.ssoBase.common.RedisHelper;
import com.tingcream.ssoBase.model.SessionInfo;
import com.tingcream.ssoBase.model.User;
import com.tingcream.ssoBase.util.EncryptionUtil;
import com.tingcream.ssoBase.util.TLDateFormatUtil;
import com.tingcream.ssoServer.mapper.SessionInfoMapper;
import com.tingcream.ssoServer.mapper.UserMapper;
 

@Service
public class SsoLoginService {
	
	 
	
	@Autowired
	private SessionInfoMapper sessionInfoMapper;
	
	
	@Autowired
	private RedisHelper redisHelper ;
	
	@Autowired
	private UserMapper userMapper;
	
	
	
	/**
	 * sso检测用户登录 (此时用户浏览器在局部上未登录，但全局上可能已经登录了)
	 * @param domain 浏览器访问的站点域
	 * @param siteSessionId 站点sessionId
	 * @param ssoSessionId  sso全局sessionId
	 * @param browserIp  浏览器ip
	 * @param browserName 浏览器名称
	 * @return
	 */
	public  int  checkLogin(String domain,String siteSessionId,String ssoSessionId, String browserIp, String browserName) {
		
 
		 SessionInfo  globalSessionInfo  = (SessionInfo) redisHelper.hget(RedisHelper.SESSION_INFOS, ssoSessionId);//从缓存中获取全局sessionInfo信息
		 
	     if(globalSessionInfo!=null) {//用户浏览器已经全局登录
	    	 
	    	  // 全局会话已登录，创建局部会话，使浏览器局部登录。
	    	  User user= globalSessionInfo.getUser();// 获取用户对象
	    	   
	    	  SessionInfo  siteSessionInfo =new SessionInfo();
	    	  siteSessionInfo.setSessionId(siteSessionId);
	    	  siteSessionInfo.setSessionType(SessionInfo.SESSIONTYPE_SITE);
	    	  siteSessionInfo.setParentSessionId(ssoSessionId);
	    	  siteSessionInfo.setDomain(domain);
	    	  siteSessionInfo.setBrowserIp(browserIp);
	    	  siteSessionInfo.setBrowserName(browserName);
	    	  siteSessionInfo.setLoginTime(TLDateFormatUtil.format(new Date()));
	    	//siteSessionInfo.setLogoutTime(null);
	    	  siteSessionInfo.setIsOnline(1);
	    	  siteSessionInfo.setUserId(user.getUserId());
	    	  siteSessionInfo.setUsername(user.getUsername());
	    	  siteSessionInfo.setUser(user);
	    	  
	    	  
	    	  redisHelper.hset(RedisHelper.SESSION_INFOS, siteSessionId, siteSessionInfo);//局部会话写入redis中
	    	  sessionInfoMapper.save(siteSessionInfo);//局部会话写入表中
	    	 
	    	 return 1;// 需要重定向到returnUrl
	    	 
	     }else {
	    	     //用户浏览器未全局登录
	    	 
	    	 return 2;// 需要内部转发到用户登录页
	     }
		
		 
	}
   
	
	/**
	 * 验证用户提交的用户名、密码是否正确
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public   User  checkUsernamePwd(String username,String password) {
		  password=EncryptionUtil.md5Hex(password);//密码 md5
		 return  userMapper.findByUsernamePwd(username, password);
		  
	}
	
    /**
     * sso用户提交数据登录   (此时用户浏览器在局部上未登录，全局也未登录)
     * @param siteSessionId
     * @param site
     * @param ssoSessionId
     * @param browserName
     * @param browserIp
     * @param user 
     * @return
     */
	public void userLogin( String siteSessionId, String domain, String ssoSessionId,
			String browserName, String browserIp, User user) {
		
		  //---用户登录操作，为浏览器创建了全局会话和局部会话 
		  
		  //全局会话
		  SessionInfo  globalSessionInfo =new SessionInfo();
		  globalSessionInfo.setSessionId(ssoSessionId);
		  globalSessionInfo.setSessionType(SessionInfo.SESSIONTYPE_GLOBAL);
		 // globalSessionInfo.setParentSessionId();
		 // globalSessionInfo.setDomain(domain);
		  globalSessionInfo.setBrowserIp(browserIp);
		  globalSessionInfo.setBrowserName(browserName);
		  globalSessionInfo.setLoginTime(TLDateFormatUtil.format(new Date()));
    	 // siteSessionInfo.setLogoutTime(null);
		  globalSessionInfo.setIsOnline(1);
		  globalSessionInfo.setUserId(user.getUserId());
		  globalSessionInfo.setUsername(user.getUsername());
		  globalSessionInfo.setUser(user);
		  
		  
		  //局部会话
		  SessionInfo  siteSessionInfo =new SessionInfo();
		  siteSessionInfo.setSessionId(siteSessionId);
		  siteSessionInfo.setSessionType(SessionInfo.SESSIONTYPE_SITE);
		   siteSessionInfo.setParentSessionId(ssoSessionId);
		   siteSessionInfo.setDomain(domain);;
		  siteSessionInfo.setBrowserIp(browserIp);
		  siteSessionInfo.setBrowserName(browserName);
		  siteSessionInfo.setLoginTime(TLDateFormatUtil.format(new Date()));
    	  // siteSessionInfo.setLogoutTime(null);
		  siteSessionInfo.setIsOnline(1);
		  siteSessionInfo.setUserId(user.getUserId());
		  siteSessionInfo.setUsername(user.getUsername());
		  siteSessionInfo.setUser(user);
		  
		  
		  
		  redisHelper.hset(RedisHelper.SESSION_INFOS, ssoSessionId, globalSessionInfo);//全局会话写入redis中
    	  redisHelper.hset(RedisHelper.SESSION_INFOS, siteSessionId, siteSessionInfo);//局部会话写入redis中
		  sessionInfoMapper.save(globalSessionInfo);//全局会话写入表中
		  sessionInfoMapper.save(siteSessionInfo);//局部会话写入表中
    	  
	}

   /**
    * SSO 登陆会话 强制下线
    * @param ssoSessionId
    */
	public void setLogout(String ssoSessionId) {
		
		 List<SessionInfo> sessionList = sessionInfoMapper.findOnlineChildrenByParendId(ssoSessionId);
		 if(sessionList!=null && sessionList.size()>0) {
			  int len=sessionList.size()+1;
			  Object[] keys=new Object[len];
			  for(int i=0;i<sessionList.size();i++) {
				  keys[i]=sessionList.get(i).getSessionId();
			  }
			  keys[len-1]=ssoSessionId;
			 
			 redisHelper.hdel(RedisHelper.SESSION_INFOS, keys);//redis中 清除
		     sessionInfoMapper.setLogout(keys); //数据库中 设置下线
			 
		 }
		 
	
		
	}
	
 

}
