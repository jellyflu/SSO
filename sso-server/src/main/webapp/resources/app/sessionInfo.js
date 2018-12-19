

  //搜索框    ok
 function sessionInfo_search(){ 	 
	    $('#dataTable').bootstrapTable('removeAll');
		$("#dataTable").bootstrapTable('refresh',{
			url: webRoot+'/ssoAdmin/sessionInfo_list', 
		});//刷新表格 重新加载数据
  } 
 
 var operateEvents = {
  		 
		 //强制下线
  	    'click .kickOut': function (e, value, row, index) {
  	    	 layer.confirm('确定强制下线此SSO登录会话吗?', {icon: 3, title:'提示'}, function(index){
				 $.ajax({
						url : webRoot+"/ssoAdmin/sessionInfo_kickOut",
			 				data:{
			 					'sessionId':row.sessionId,
			 				},
			 				type : 'post',
							dataType : 'json',
							success:function(data){
								if(data.status==200){
									  layer.alert(data.message, {icon: 1});
									  sessionInfo_search();
								}
								else if(data.status==300){
									eval(data.evalStr);
								}else{
									 layer.alert(data.message, {icon: 2});
								}
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								  layer.alert(errorThrown, {icon: 2});
							}
					})
				 layer.close(index);
			 }); 
  	        
        },
       
	      //查看详情
	   	 'click .detail': function (e, value, row, index) {
		   		 
		   		 var Jurl =  webRoot+"/ssoAdmin/sessionInfo_detail?sessionId=" + row.sessionId;    		 
		   		 var index = layer.open({
		   			  //skin:"layui-layer-molv",
					  type: 2,
					  title:"SSO登录会话详情",
					  content: Jurl,
					  area: ['500px', '450px'], //宽高
				 });
  	    
	      },	   	 
  	};  
