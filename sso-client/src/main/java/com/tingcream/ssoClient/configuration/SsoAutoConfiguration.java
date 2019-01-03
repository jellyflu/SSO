package com.tingcream.ssoClient.configuration;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.tingcream.ssoClient.common.SpringContextAware;
import com.tingcream.ssoClient.mvc.SsoLoginAuthInterceptor;
import com.tingcream.ssoClient.properties.SsoProperties;

/**
 * SSO 自动化配置信息
 * @author jelly
 *
 */
@Configuration
@EnableConfigurationProperties(SsoProperties.class)
@ComponentScan(basePackages="com.tingcream.ssoClient.mvc")
public class SsoAutoConfiguration  extends WebMvcConfigurerAdapter  {
	
	   
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
	     
	    //注入sso登录拦截器
	    @Override
		public void addInterceptors(InterceptorRegistry registry) {
	    	SsoLoginAuthInterceptor interceptor=new SsoLoginAuthInterceptor();
	    	 
	    	interceptor.setSessionInfoHelper(SpringContextAware.getBean(SessionInfoHelper.class));
	    	interceptor.setSsoCheckLoginUrl(ssoProperties.getSsoServer().getCheckLoginUrl());
		    registry.addInterceptor(interceptor)
				    .addPathPatterns("/**");
			
		}

}
