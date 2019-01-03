package com.tingcream.ssoClient.configuration;

import com.tingcream.ssoClient.model.SessionInfo;
import com.tingcream.ssoClient.model.User;

/**
 * SessionInfo 辅助bean
 * @author jelly
 *
 */
public class SessionInfoHelper {
	
	/**
	 *   key             field           value
	 *  sessionInfos   字符串<sessionId>   <SessionInfo>对象
	 *                  ...                ...
	 */
  public static  String SESSION_INFOS="sessionInfos"; 
	 
 private RedisHelper redisHelper;
 public void setRedisHelper(RedisHelper redisHelper) {
		this.redisHelper = redisHelper;
}
	   
 /**
  * 获取SesionInfo 
  * @param sessionId
  * @return
  */
 public SessionInfo  getSessionInfo(String sessionId) {
	 return  (SessionInfo) redisHelper.hget(SESSION_INFOS, sessionId);
 }
 /**
  * 写入 SessionInfo
  * @param sessionId
  * @param sessionInfo
  */
 public  void  saveSessionInfo(String sessionId,SessionInfo sessionInfo) {
	 redisHelper.hset(SESSION_INFOS, sessionId, sessionInfo);
 }
 /**
  * 清除SessionInfos 
  * @param sessionIds
  */
 public  void  clearSessionInfos(String[] sessionIds) {
	 Object[] keys=new Object[sessionIds.length];
	 for(int i=0;i<sessionIds.length;i++) {
		 keys[i]=sessionIds[i];
	 }
	 redisHelper.hdel(SESSION_INFOS, keys);//redis中 清除
 }
 /**
  * 获取sessionInfo 中的User
  * @param sessionId
  * @return
  */
 public User getSessionUser(String sessionId) {
	 SessionInfo sessionInfo =  (SessionInfo) redisHelper.hget(SESSION_INFOS, sessionId);
	 if(sessionInfo!=null) {
		 return  sessionInfo.getUser();
	 } else {
		 return null ;
	 }
 }
  
   
   
}
