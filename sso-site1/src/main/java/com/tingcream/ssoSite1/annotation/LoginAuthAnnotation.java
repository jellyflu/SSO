package com.tingcream.ssoSite1.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 需要登录验证的controller方法需要标注此注解 （受保护的资源）
 * @author jelly
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LoginAuthAnnotation {

}
