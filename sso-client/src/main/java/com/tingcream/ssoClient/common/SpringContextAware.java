package com.tingcream.ssoClient.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * spring上下文辅助bean
 * @author jelly
 *
 */
public class SpringContextAware implements ApplicationContextAware {
	
       private static ApplicationContext applicationContext;  
       
       @Override  
       public void setApplicationContext(ApplicationContext applicationContext)  
               throws BeansException {  
           SpringContextAware.applicationContext = applicationContext;  
       }  
         /**
          * 获取applicationContext
          * @author jelly
          * @date 2018年10月10日 下午3:41:29
          * @return
          */
       public static ApplicationContext getApplicationContext(){  
           return applicationContext;  
       }  
         /**
          * 获取bean
          * @author jelly
          * @date 2018年10月10日 下午3:41:24
          * @param name
          * @return
          */
       public static Object getBean(String name){  
           return applicationContext.getBean(name);  
       }  
        /**
         * 从spring 上下文中获取bean
         * @author jelly
         * @date 2018年10月10日 下午3:41:04
         * @param name
         * @param requiredClass
         * @return
         */
       public static <T> T getBean(String name, Class<T>  requiredClass){  
           return applicationContext.getBean(name, requiredClass);  
       }  
       public  static <T> T getBean(Class<T> requiredType){
    	   return applicationContext.getBean(requiredType);
       }
     
}
