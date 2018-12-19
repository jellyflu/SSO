package com.tingcream.ssoServer.mapper;

 

import java.util.List;
import java.util.Map;

import com.tingcream.ssoBase.model.SessionInfo;

public interface SessionInfoMapper {
        
   	public int save(SessionInfo sessionInfo);      
   	public int update(SessionInfo sessionInfo);     
   	public int deleteById(String id);        
    public  SessionInfo findById(String id);       
    
    //根据id查询
    public  SessionInfo findSessionInfoById(String id);       
    public List<SessionInfo> findOnlineChildrenByParendId(String sessionId);
	public int setLogout(Object[] sessionIds);
	
	
	public List<SessionInfo> findChildrenByParendId(String sessionId);
	
	
	//分页查询 count
	public int findSessionInfoCountPage(Map<String, Object> param);
	public List<SessionInfo> findSessionInfoListPage(Map<String, Object> param);
	
    	                    
	
}
