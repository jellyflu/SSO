package com.tingcream.ssoServer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tingcream.ssoClient.model.SessionInfo;
import com.tingcream.ssoClient.model.User;
import com.tingcream.ssoServer.common.Page;
import com.tingcream.ssoServer.mapper.SessionInfoMapper;
import com.tingcream.ssoServer.mapper.UserMapper;
import com.tingcream.ssoServer.util.AjaxUtil;

@Service
public class SessionInfoService {
	
	@Autowired
	private SessionInfoMapper sessionInfoMapper;
  
	@Autowired
	private UserMapper  userMapper;
	//分页查询
	public Map<String, Object> findSessionInfoListPage(Map<String, Object> params, Page page) {
		 
		Map<String,Object> param =new HashMap<String,Object>();
		param.put("pageSize", page.getPageSize());
		param.put("startNum", page.getStartNum());
		param.put("sortName", page.getSortName());
		param.put("sortOrder", page.getSortOrder());
		
		param.put("begintime", params.get("begintime"));
		param.put("endtime", params.get("endtime"));
		param.put("isOnline", params.get("isOnline"));
		param.put("browserIp", params.get("browserIp"));
		
		int count =  sessionInfoMapper.findSessionInfoCountPage(param);
		if (count > 0) {
			List<SessionInfo> list = sessionInfoMapper.findSessionInfoListPage(param);
			Map<String, Object> map = AjaxUtil.messageMap(200, "查询成功");
			map.put("total", count);
			map.put("rows", list);
			return map;
		} else {
			return AjaxUtil.messageMap(200, "查询成功,无数据");
		}
	}
   //查询SSO详情
	public SessionInfo findSsoDetail(String sessionId) {
		
	   SessionInfo sessionInfo =	sessionInfoMapper.findById(sessionId);
	     String userId=sessionInfo.getUserId();//用户id
	    User user= userMapper.findById(userId);
	    sessionInfo.setUser(user);
	    
	   List<SessionInfo> children=   sessionInfoMapper.findChildrenByParendId(sessionId);
	   
	   sessionInfo.setChildren(children);
		 
		return sessionInfo;
	}

 
}
