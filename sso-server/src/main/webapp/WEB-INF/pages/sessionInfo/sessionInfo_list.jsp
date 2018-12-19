<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SSO会话管理</title>
    <link href="${pageContext.request.contextPath}/resources/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/datetimepicker/css/bootstrap-datetimepicker.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/bootstrap-table/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/layer-v3.1.0/layer/theme/default/layer.css">
    
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
    
<script>
  var  webRoot="${pageContext.request.contextPath }";
</script>
    
    
</head>
<body>
  
 
 
 <!-- 查询区块  -->
 <div class="col-sm-12"  >
     
        <div class="panel panel-primary" style="margin-bottom: 0px" >
                 <!-- 利用data-target指定要折叠的分组列表 -->
                <div class="panel-heading"  style="padding: 7px 15px" data-toggle="collapse" data-target="#myGroup1"  > 
                     <h4 class="panel-title">
                       		SSO会话查询
                        <span class="glyphicon glyphicon-chevron-up right" style="float: right"></span>
                     </h4>
                 </div>
                 <!-- .panel-collapse和.collapse标明折叠元素 .in表示要显示出来 -->
                 <div id="myGroup1" class="panel-collapse collapse in"  style="margin-top: 10px;margin-bottom: 10px" >
                    <form  class="form-horizontal required-validate" id="sessionInfoSearch"> 
				     <div class="form-group col-sm-7 " >									
						<label for="" class="col-sm-3 control-label">客户端IP:</label>
							<div class="col-sm-7">
								<input type="text"  class="form-control " id="browserIp" name="browserIp" placeholder="请输入客户端IP"  >
						    </div>
					 </div>
				    <div class="form-group col-sm-5 " >
						<label for="" class="col-sm-3 control-label">是否在线:</label>	
							 <div class="col-sm-8">									
								 <select class="form-control" id="isOnline" name="isOnline">
								      <option value="" >请选择</option>
								      <option  value="1">是</option>
								      <option  value="0">否</option>
								</select>
							</div>										
					  </div>
					  
					  <div class="form-group col-sm-7  " >
						<label for="" class="col-sm-3 control-label">登录时间:</label>
							<div class="col-sm-8" >
								<div style="float:left;width:47%;">
									<input  style="width: 100%;" id="begintime" name="begintime" type="text" readonly  class="form-control">	
								</div>
								<div style="float:left;width:4%;">~</div>
								<div style="float:left;width:47%;">
									<input  style="width: 100%;" id="endtime" name="endtime" type="text" readonly class="form-control">	
								</div>
							</div>																							    
					 </div>
					 
					 <div style="margin-left:57%">
					     <button type="button" onclick="sessionInfo_search()" class="btn btn-primary" >查&nbsp;&nbsp;&nbsp;询</button>
					  </div>
			   </form>
                    
                 </div>
             </div><!--panel end-->
    
   </div>   
    <!-- 查询区块  end -->
		   
	
<!-- 列表区块 -->	 
 <%--  <div class="col-sm-12">
        <div class="panel panel-primary"  >
                利用data-target指定要折叠的分组列表
            <div class="panel-heading"  style="padding: 7px 15px" data-toggle="collapse" data-target="#myGroup2"  > 
                 <h4 class="panel-title">
                   		SSO会话列表
                    <span class="glyphicon glyphicon-chevron-up right" style="float: right"></span>
                 </h4>
             </div>
             .panel-collapse和.collapse标明折叠元素 .in表示要显示出来
           <div id="myGroup2" class="panel-collapse collapse in"   >
             <table id="dataTable" class="table table-bordered table-hover" >
			  </table>
             </div>
         </div>
	</div>   --%>
	
<div class="col-sm-12" >
  <table id="dataTable" class="table table-bordered table-hover" >
				  
  </table>
   
</div>	
<!-- 列表区块 end -->	   
	 
 

			  
	 	
 		

</body>
</html>
<script src="${pageContext.request.contextPath }/resources/bootstrap-3.3.7/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap-3.3.7/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap-table/js/bootstrap-table.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap-table/js/bootstrap-table-zh-CN.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath }/resources/datetimepicker/js/bootstrap-datetimepicker.zh-CN.js"></script> 
<script src="${pageContext.request.contextPath }/resources/layer-v3.1.0/layer/layer.js"></script> 
<script src="${pageContext.request.contextPath }/resources/app/sessionInfo.js"></script>
<script>
$(function(){
	
	  $(window).resize(function(){
		   $("#dataTable").bootstrapTable('resetView');
	 }); 
	
	  
	 $(".panel-heading").click(function(e){
         /*切换折叠指示图标*/
         $(this).find("span").toggleClass("glyphicon-chevron-down");
         $(this).find("span").toggleClass("glyphicon-chevron-up");
        
     });

		$('#begintime').datetimepicker({
     		language:'zh-CN',
 	    	todayHighlight: true,
 	    	minView: 'hour',
 	    	autoclose: true,
 	    	format: 'yyyy-mm-dd hh:ii',
 	    	clearBtn: true,
 	    	minuteStep:1
	    });
		
	    $('#endtime').datetimepicker({
	    	 language:'zh-CN',
		     todayHighlight: true,
		     minView: 'hour',
	 	     autoclose: true,
	 	     format: 'yyyy-mm-dd hh:ii',
		    clearBtn: true,
		    minuteStep:1
	    });
	    
    	  sessionInfo_loadList();
    	   $("#sessionInfoSearch").keydown(function(event) {
  	   		if (event.keyCode == "13") { 
  	   		       sessionInfo_search();
  	   	 	};
  	     });  
	 
	
});

//加载用户数据列表
 function sessionInfo_loadList(){
	
		$('#dataTable').bootstrapTable({
			dataField: 'rows', //要求服务端返回数据键值 就是说记录放的键值是rows
            totalField: 'total',//要求服务器端返回的总记录数的参数名为total 
			url: webRoot+'/ssoAdmin/sessionInfo_list', 
	        method: 'post',
	        height: 400,
	        singleSelect: false,
	        striped: true,                      //是否显示行间隔色
	        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	        pagination: true,                   //是否显示分页（*）
	        sortable: true,                    //是否启用排序
	        sortOrder: "asc",                   //排序方式
	        queryParams: function getParams(params) {                  
               return { 
                         currentPage: (params.offset / params.limit) + 1,
       				     pageSize: params.limit,  
       				     sortName:this.sortName,
                         sortOrder:this.sortOrder,
                         browserIp: $.trim($("#browserIp").val()),  
       				     begintime: $.trim($("#begintime").val()),
       				     endtime: $.trim($("#endtime").val()), 
       				     isOnline:$("#isOnline").val(),
                     };
              
           }, 
	        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
	        pageNumber:1,                       //初始化加载第一页，默认第一页
	        pageSize: 10,            //每页的记录行数（*）
	        pageList: [5,10,20,50], //可供选择的每页的行数（*）
	        strictSearch: true,
	        showColumns: false,                  //是否显示所有的列
	        showRefresh: false,                  //是否显示刷新按钮
	        clickToSelect: true,                //是否启用点击选中行
	       align:'center',
	       columns: [ 
	    	   
/* 
	    	   序号 客户端IP  浏览器 登录时间 注销时间 登录用户  是否在线       
	    	   browserIp  browserName  loginTime logoutTime username isOnline
	    	   */
	    	   
	    	   

	    	    {
	                title: '序号',//标题  可不加  
	                width:20,
	                align:'center',
	                valign:'middle',
	                halign:'center',
	                formatter: function (value, row, index) {  
	                    return index+1;  
	                } 
	 	         }, 
	    	   
	 	        {
		  	          field:'username',
		  	          title:'登录用户',
		  	          sortable:true,
		  	      } , 
	           {
	             field: 'browserIp',
	             title: '客户端IP',
	              sortable:true,
	            }, 
	  	        {
	  	          field:'browserName',
	  	          title:'浏览器',
	  	          sortable:true,
	  	        } , 
	  	         {
		  	          field:'loginTime',
		  	          title:'登录时间',
		  	          sortable:true,
		  	      } , 
	  	         {
		  	          field:'logoutTime',
		  	          title:'退出时间',
		  	          sortable:true,
		  	      } , 
	  	         {
		  	          field:'isOnline',
		  	          title:'是否在线',
		  	          sortable:true,
		  	         formatter:function(value,row,index){
		        		 if(value==1){
		        			 return '是';
		        		 }else{
		        			 return '否';
		        		 }
		        	 }
		  	      } , 
	  	        {
		    	   field: 'operate',
		           title: '操作',
		           events: operateEvents,
		           formatter: operateFormatter
	             } 
	           ],
	        showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
	        cardView: false,                    //是否显示详细视图
	        detailView: false,                  //是否显示父子表
	        contentType:'application/x-www-form-urlencoded; charset=UTF-8',
	         onLoadSuccess: function(data){
	        }
		});
	}
  
  //操作栏
  function operateFormatter(value,row,index) {
	  if(row.isOnline==1){
		  return [
				'<button class="btn btn-info detail">查看详情</button>',
				'<button class="btn btn-warning kickOut" style="margin-left:3px">强制下线</button>',
			].join('');
	  }else{
		  return [
				'<button class="btn btn-info detail">查看详情</button>',
			].join('');
	  }
		
	}

</script>
