package com.tingcream.ssoSite2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.tingcream.ssoClient.configuration.EnableSsoAutoConfig;

 

@SpringBootApplication(scanBasePackages= {"com.tingcream.ssoSite2"})
//@Import({RedisAutoConfiguration.class}) //springboot中默认已引入了RedisAutoConfiguration这个自动化配置 ,

@EnableSsoAutoConfig
public class SsoSite2Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SsoSite2Application.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(SsoSite2Application.class, args);
	}
	
	 
 
	
}

