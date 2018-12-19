<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>SSO后台管理</title>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->

    <style>
        body {margin:0;
            padding:0;
        }
        #myLeftMenu li.list-group-item{
            cursor: pointer;
        }

        #myLeftMenu li.list-group-item:hover{
           background-color: #9acfea;
        }

        .li_active{
            background-color: #9acfea;
        }

    </style>

</head>
<body>
 
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
       
        <div class="navbar-header">
            <a class="navbar-brand" href="#">SSO后台管理</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">首页</a></li>
                <li><a href="#">关于</a></li>
                <li><a href="#">联系我们</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                       role="button" aria-haspopup="true" aria-expanded="false">欢迎您:admin<span class="caret"></span></a>
                    <ul class="dropdown-menu ">
                        <li><a href="#">退出</a></li>
                        <li><a href="#">个人信息</a></li>

                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>


<div class="container-fluid" style="margin-top: 40px;margin-bottom: 0px">
<h1></h1>

   <div class="row">  
        <div class="col-sm-2">

         

                <div class="panel panel-primary " id="myLeftMenu" >
                    <!-- 利用data-target指定要折叠的分组列表 -->
                   <div class="panel-heading" data-toggle="collapse" data-target="#myLeftMenuGroup1"  > 
                        <h4 class="panel-title">
                          		首页
                           <span class="glyphicon glyphicon-chevron-up right" style="float: right"></span>
                        </h4>
                    </div>
                    <!-- .panel-collapse和.collapse标明折叠元素 .in表示要显示出来 -->
                    <div id="myLeftMenuGroup1" class="panel-collapse collapse in"  >
                        <ul class="list-group">

                            <li class="list-group-item li_active"
                                data-url="${pageContext.request.contextPath }/welcome" data-title="欢迎页">
                            		  欢迎页
          	                  </li>

                            <li class="list-group-item "
                            data-url="${pageContext.request.contextPath }/ssoAdmin/sessionInfo_index" data-title="SSO会话管理">
                              		SSO会话管理
                            </li>

                        </ul>
                    </div>
                </div><!--panel end-->

        </div> <!--end sm2 -->
   
          <!-- 右侧主体内容 -->
        <div class="col-sm-10"  >
           <!-- 面包屑 -->
            <ol class="breadcrumb" style="margin-bottom: 5px">
                <li><a href="#">首页</a></li>
                <li class="active" id="currentTip">欢迎页</li>
            </ol>
            <iframe  id="myiframe"   width="100%"  marginheight="0"
                     src="${pageContext.request.contextPath }/welcome" frameborder="0"
                     scrolling="auto" style="min-height: 470px" >
            </iframe>





        </div>
   </div>  
    
 </div>
</div>

<script src="${pageContext.request.contextPath }/resources/bootstrap-3.3.7/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap-3.3.7/js/bootstrap.js"></script>
<script>
    $(function(){
        $(".panel-heading").click(function(e){
            /*切换折叠指示图标*/
            $(this).find("span").toggleClass("glyphicon-chevron-down");
            $(this).find("span").toggleClass("glyphicon-chevron-up");
        });

        $("#myLeftMenu li.list-group-item").click(function (e) {
            $("#myLeftMenu li.list-group-item").each(function () {
                $(this).removeClass("li_active")
            });
            $(this).addClass("li_active");

            
            
             var title= $(this).data("title");
             var url=$(this).data("url");
             $("#currentTip").html(title);
             $("#myiframe").attr("src",url);
            // $("#myiframe").attr("src",url);//iframe页面跳转 
        });
    });




</script>

</body>
</html>