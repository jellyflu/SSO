package com.tingcream.ssoSite1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.tingcream.ssoClient.configuration.EnableSsoAutoConfig;

@SpringBootApplication(scanBasePackages= {"com.tingcream.ssoSite1"})
//@Import({RedisAutoConfiguration.class}) //springboot中默认已引入了RedisAutoConfiguration这个自动化配置 ,
@EnableSsoAutoConfig
public class SsoSite1Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SsoSite1Application.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(SsoSite1Application.class, args);
	}
	
	
}

