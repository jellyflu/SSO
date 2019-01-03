# SSO
java实现的SSO案例(springboot)，有SSO后台管理界面，SSO全局、局部会话保存到mysql及redis库中。



# 工程介绍

##  sso-client  

 sso-client是一个springboot的插件(starter),它封装了SSO登录拦截器、SSO客户端退出Controller、Session会话管理器等。在站点的pom.xml中需要引入这个插件 ：

```java
<dependency>
   <groupId>com.tingcream</groupId>
   <artifactId>sso-starter-client</artifactId>
    <version>1.0</version>
</dependency>
```



## sso-server:

 SSO服务端工程(http://www.mysso.com:9001) 

1、提供了供sso-client验证用户是否登录、sso用户登录与注销等接口

2、提供了SSO后台管理页面(后台人员使用)

## sso-site1

 SSO客户端工程(站点1,http://www.site1.com:8001) 
 引入sso-starter-client 包

## sso-site2

 SSO客户端工程(站点2,http://www.site2.com:8002)
 引入sso-starter-client 包


# 环境准备

配置本机hosts文件，以方便测试SSO登录

127.0.0.1  www.site1.com  
127.0.0.1  www.site2.com  
127.0.0.1  www.mysso.com  

# 运行截图

sso用户登录页面:
![Image text](https://raw.githubusercontent.com/jellyflu/pub_resouces/master/SSO/ssoLogin.png)

sso后台管理页面1:
![Image text](https://raw.githubusercontent.com/jellyflu/pub_resouces/master/SSO/ssoAdmin-1.png)

sso后台管理页面2:
![Image text](https://raw.githubusercontent.com/jellyflu/pub_resouces/master/SSO/ssoAdmin-2.png)


site1 订单页面1:
![Image text](https://raw.githubusercontent.com/jellyflu/pub_resouces/master/SSO/1.png)


site1 订单页面2: 
![Image text](https://raw.githubusercontent.com/jellyflu/pub_resouces/master/SSO/2.png)


site2 订单页面1:
![Image text](https://raw.githubusercontent.com/jellyflu/pub_resouces/master/SSO/3.png)


site2 订单页面2:
![Image text](https://raw.githubusercontent.com/jellyflu/pub_resouces/master/SSO/4.png) 

