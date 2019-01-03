package com.tingcream.ssoSite2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tingcream.ssoClient.configuration.EnableSsoAutoConfig;

 

@SpringBootApplication(scanBasePackages= {"com.tingcream.ssoSite2"})
//@Import({RedisAutoConfiguration.class}) //springboot中默认已引入了RedisAutoConfiguration这个自动化配置 ,

@RestController
@EnableSsoAutoConfig
public class SsoSite2Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SsoSite2Application.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(SsoSite2Application.class, args);
	}
	
	@Value("${spring.application.name}")
	private String applicationName ;
  
	@RequestMapping("/welcome")
	public String welcome(HttpServletRequest request) {
		String sessionId=request.getSession().getId();
		
		return applicationName+": sessionId:"+sessionId;
	}
 
	
}

