FolService.factory('RoleAuthorityService',['CommonService',function(CommonService){
	var roleData = null;
	//系统角色维护
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
		setRoleData:function(request) {
			if(request!=null){
				roleData={
					methodId :request.methodId,
					clazz:request.clazz,
					method:request.method,
					url:request.url,
				};
			}else{
				positionData=null;
			}
		},
		getRoleData:function() {
			return roleData;
		},
		systemRoRiMain:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
	systemRoRiMains:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	systemRoRiMainss:function(url,callback,errorCallback){
		CommonService.query(url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	systemPoRoMainA:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	systemRoRiMainsAd:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	systemPoRoMainAdd:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	delsystem:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	findMenu:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	},
	checkdata:function(data,url,callback,errorCallback){
		CommonService.post(data,url,function(data){
			typeof callback==='function' && callback(data);
		},function(response_info){
			typeof errorCallback==='function' && errorCallback(response_info);
		});
	}
	};
}]);