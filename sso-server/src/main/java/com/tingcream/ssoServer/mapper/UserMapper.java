package com.tingcream.ssoServer.mapper;

import org.apache.ibatis.annotations.Param;

import com.tingcream.ssoClient.model.User;

public interface UserMapper {
        
   	public int save(User user);      
   	public int update(User user);     
   	public int deleteById(String id);        
    public  User findById(String id);    
    
    /**
     * 用户登录  根据用户名、密码查询用户
     * @param username 用户名
     * @param password 密码(MD5)
     * @return
     */
    public User findByUsernamePwd(@Param("username") String username,
    		@Param("password")String password);
	
}
