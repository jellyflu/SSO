<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <link href="${pageContext.request.contextPath}/resources/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
  var  webRoot="${pageContext.request.contextPath }";
</script>
    
    
</head>
<body>
  <div class="col-sm-12">
	 
		    <h4>登录用户</h4>
		 <div style="margin-top: 5px">
		     <label>用户名</label>:  ${sessionInfo.user.username }
		     &nbsp;&nbsp;&nbsp;&nbsp;
		    <label >生日</label>: ${sessionInfo.user.birthday }   
		  </div>
		   <div>
		       <label>邮箱</label>: ${sessionInfo.user.email }  
		     &nbsp;&nbsp;&nbsp;&nbsp;
		        <label>城市</label>: ${sessionInfo.user.city }   
		  </div>
		  
		   <h4>登录域</h4>
		  <table class="table table-bordered table-hover">
		   <tr>
		     <td>登录域</td>
		     <td>登录时间</td>
		     <td>退出时间</td>
		     <td>在线</td>
		   </tr>
		   <c:if test="${!empty sessionInfo.children}">
		     <c:forEach items="${sessionInfo.children }" var="info">
		       <tr>
		        <td>${info.domain }</td>
			     <td>${info.loginTime }</td>
			     <td>${info.logoutTime }</td>
			     <td>${info.isOnline==1?'是':'否' }</td>
		       </tr> 
		     </c:forEach>
		   </c:if>
		 
		     
		  </table>
		 
	 
	 																							 
		 
	 

 
 </div>
			  
	 	
 		

</body>
</html>
<script src="${pageContext.request.contextPath }/resources/bootstrap-3.3.7/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap-3.3.7/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath }/resources/app/sessionInfo.js"></script>
<script>
</script>
 