package com.tingcream.ssoSite1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tingcream.ssoBase.common.RedisHelper;

@SpringBootApplication(scanBasePackages= {"com.tingcream.ssoSite1"})
//@Import({RedisAutoConfiguration.class}) //springboot中默认已引入了RedisAutoConfiguration这个自动化配置 ,
 
@RestController
public class SsoSite1Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SsoSite1Application.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(SsoSite1Application.class, args);
	}
	
	@Value("${spring.application.name}")
	private String applicationName ;
  
	@RequestMapping("/welcome")
	public String welcome(HttpServletRequest request) {
		String sessionId=request.getSession().getId();
		
		return applicationName+": sessionId:"+sessionId;
	}
	
	

    @Bean
	public  StringRedisSerializer   stringRedisSerializer() {
		return new  StringRedisSerializer();
	}
	@Bean  
	public  GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer() {
		return new   GenericJackson2JsonRedisSerializer();
	}
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean 
	public  RedisTemplate<Object,Object>  redisTemplate( 
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
    public  RedisHelper  redisHelper( /*@Autowired*/ RedisTemplate<Object,Object>  redisTemplate) {
    	RedisHelper redisHelper = new  RedisHelper();
    	redisHelper.setRedisTemplate(redisTemplate);
    	return redisHelper;
    }
    
	
	
	
}

