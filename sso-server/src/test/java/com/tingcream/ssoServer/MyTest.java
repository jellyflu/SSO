package com.tingcream.ssoServer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tingcream.ssoBase.common.RedisHelper;
import com.tingcream.ssoBase.model.User;
import com.tingcream.ssoServer.mapper.UserMapper;

 
@RunWith(SpringRunner.class)
@SpringBootTest
//@ActiveProfiles("dev")//设置激活环境配置 
public class MyTest {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RedisHelper redisHelper ;
	
	/**
	 * 测试ok
	 */
	@Test
	public void test1() {
		User user= userMapper.findById("9F6111DDFDE311E8A35C507B9D60CD8C");
		System.out.println(user);
	}
	/**
	 * 测试ok 控制台输出中文没有乱码
	 */
	@Test
	public void test2() {
		System.out.println("你好啊222222222222222222");
	}

	/**
	 * 测试ok redis连接成功
	 */
	@Test
	public void test3() {
		redisHelper.set("aa", "hello redis");
	   String s=	(String) redisHelper.get("aa");
		System.out.println(s);
	}
	
	
}
