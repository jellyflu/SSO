package com.tingcream.ssoClient.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * SSO受保护的资源 注解  <br/>
 * 需标记在受SSO登录拦截器保护的controller请求路径上
 * @author jelly
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SsoLoginAuth {

}