package com.tingcream.ssoServer;

 
import java.io.Serializable;

import javax.sql.DataSource;

import org.apache.ibatis.io.VFS;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.tingcream.ssoClient.configuration.RedisHelper;
import com.tingcream.ssoClient.configuration.SessionInfoHelper;
 
@SpringBootApplication(exclude= {
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		JdbcTemplateAutoConfiguration.class,
		AopAutoConfiguration.class,
		MybatisAutoConfiguration.class,
	},scanBasePackages= {"com.tingcream.ssoServer"})



@ImportResource({"classpath:/spring.xml"})
@PropertySource({"classpath:/jdbc.properties"})
//@Import({RedisAutoConfiguration.class}) //springboot中默认已引入了RedisAutoConfiguration这个自动化配置 ,
@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true) 
public class SsoServerApplication  extends SpringBootServletInitializer {
	
	
	private static Logger logger = LoggerFactory.getLogger(SsoServerApplication.class);
	

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SsoServerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SsoServerApplication.class, args);
	}
	 
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
    	
    	logger.info("load SpringBootVFS"); 
    	//DefaultVFS在获取jar上存在问题，使用springboot只能修改  
         VFS.addImplClass(SpringBootVFS.class);
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 设置mybatis的主配置文件
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
     
        Resource sqlMapConfig =    resolver.getResource("classpath:SqlMapConfig.xml");
        
        sqlSessionFactoryBean.setConfigLocation(sqlMapConfig);
        return sqlSessionFactoryBean;
    }
    
    

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
    
 
    
	
}
