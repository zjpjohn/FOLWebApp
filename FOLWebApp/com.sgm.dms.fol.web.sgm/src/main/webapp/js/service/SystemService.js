FolService.factory('SystemService',['CommonService',function(CommonService){
	var positionData = null;
	//系统岗位角色维护
	return {
		//获取
		get: function(url, callback, errorCallback) {
			CommonService.get(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		//新增
		post: function(data, url, callback, errorCallback) {
			CommonService.post(data, url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		//修改--CommmonService中没有PUT METHOD
		put: function(data, url, callback, errorCallback) {
			CommonService.post(data, url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		//删除--CommmonService中没有DELETE METHOD
		del: function(data, url, callback, errorCallback) {
			CommonService.post(data, url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		//
		setPositionData:function(request) {
			if(request!=null){
				positionData={
					positionId :request.positionId,
					positionName:request.positionName,
					positionDesc:request.positionDesc,
					status:request.status,
					positionType:request.positionType,
					dpPositionCode:request.dpPositionCode
				};
			}else{
				positionData=null;
			}
		},
		getPositionData:function() {
			return positionData;
		},
		
		
		systemPoRoMain:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}, 
	//系统岗位角色设定
		systemPoRoMains:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		//系统岗位对应的角色修改
		systemPoRoMainA:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		/*test:function(url,callback,errorCallback){
			CommonService.crosPostQuery(url,function(data){
				typeof callback==='function' && callback(data);
			},function(data){
				typeof errorCallback==='function' && errorCallback(data);
			});
		}*/
		
		checkdata:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);