<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>SSO用户登录</title>
</head>
<body>
  <h2>SSO用户登录</h2>
  <%--form 表单方式提交 --%>
   <form method="post" action="${pageContext.request.contextPath}/ssoUser_login">
	  用户名:<input type="text" name="username" /> <br/>
	  密码:<input type="password" name="password"/> <br/>
	 
		<input type="hidden" name="_0x1d2f" value="${_0x1d2f}"/> 
		<input type="hidden" name="returnUrl" value="${returnUrl}"/> 
		 
	 <div id="errorMsg" style="color: red;font-size: 11px">${errorMsg }</div>
	 <button type="submit" >登录</button>  
	 <button type="reset" >重置</button>
  </form> 
  
  
</body>
</html>
<script src="${pageContext.request.contextPath }/resources/bootstrap-3.3.7/js/jquery.min.js"></script>
 


