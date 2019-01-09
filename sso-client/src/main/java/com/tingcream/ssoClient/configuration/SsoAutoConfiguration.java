package com.tingcream.ssoClient.configuration;

import java.io.Serializable;
import java.util.List;

import javax.servlet.DispatcherType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.tingcream.ssoClient.common.SpringContextAware;
import com.tingcream.ssoClient.filter.SsoLoginAuthFilter;
import com.tingcream.ssoClient.properties.SsoProperties;

/**
 * SSO 自动化配置信息
 * @author jelly
 *
 */
@Configuration
@EnableConfigurationProperties(SsoProperties.class)
@ComponentScan(basePackages="com.tingcream.ssoClient.controller")
public class SsoAutoConfiguration   {
	
	   
		@Autowired
		private  SsoProperties  ssoProperties;
	
	    @Bean
	    @ConditionalOnMissingBean(StringRedisSerializer.class)
		public  StringRedisSerializer   stringRedisSerializer() {
			return new  StringRedisSerializer();
		}
	    
		@Bean  
		@ConditionalOnMissingBean(GenericJackson2JsonRedisSerializer.class)
		public  GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer() {
			return new   GenericJackson2JsonRedisSerializer();
		}
	    
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Bean
		public  RedisTemplate<Serializable,Object>  redisTemplate( 
				 JedisConnectionFactory  connectionFactory,
				 StringRedisSerializer stringRedisSerializer,
				 GenericJackson2JsonRedisSerializer  genericJackson2JsonRedisSerializer ) {
			
			RedisTemplate  redisTemplate=new RedisTemplate();
			redisTemplate.setConnectionFactory(connectionFactory);
			redisTemplate.setKeySerializer(stringRedisSerializer);
			redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
			redisTemplate.setHashKeySerializer(stringRedisSerializer);
			redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
			return    redisTemplate;
		}
		
	    @Bean
	    public  RedisHelper  redisHelper( RedisTemplate<Serializable,Object> redisTemplate) {
	    	RedisHelper redisHelper = new  RedisHelper();
	    	redisHelper.setRedisTemplate(redisTemplate);
	    	return redisHelper;
	    }
	    
	    @Bean
	    public  SessionInfoHelper  sessionInfoHelper(RedisHelper  redisHelper) {
	    	SessionInfoHelper  sessionInfoHelper=new SessionInfoHelper();
	    	sessionInfoHelper.setRedisHelper(redisHelper);
	    	return  sessionInfoHelper;
	    }
	    
	    //spring容器感知bean
	    @Bean
	    public SpringContextAware  springContextAware() {
	    	  return new  SpringContextAware();
	    }
	    
	    
	    @Bean
	    public   FilterRegistrationBean  ssoLoginAuthFilterRegistration(
	    		SessionInfoHelper sessionInfoHelper) {
	    	
	    	 FilterRegistrationBean registration = new FilterRegistrationBean();
	    	 
	    	 //filter 过滤器排除路径 
	    	 List<String> excludePaths= ssoProperties.getSsoClient().getExcludePaths();
	    	 excludePaths.add(ssoProperties.getSsoClient().getLogoutPage());
	    	 excludePaths.add("/favicon.ico");
	    	 
	    	 //new 过滤器
	    	SsoLoginAuthFilter filter=  new    SsoLoginAuthFilter();
	    	filter.setSessionInfoHelper(sessionInfoHelper);
	    	filter.setSsoCheckLoginUrl(ssoProperties.getSsoServer().getCheckLoginUrl());
	    	filter.setExcludePaths(excludePaths);
	    	
	    	registration.setFilter(filter);
	    	registration.setName("ssoLoginAuthFilter");
	    	registration.setOrder(1);
	    	 
	    	registration.setDispatcherTypes(DispatcherType.REQUEST);
	        registration.addUrlPatterns("/*");
	    	
	    	return registration;
	    }
	    
  

}
