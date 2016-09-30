FolService.factory('PersonRoleService',['CommonService',function(CommonService){
	var personData = null;
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
		setPersonData: function(request) {
			if(request!=null){
				personData={
					userAccount: request.userAccount,
					userName: request.userName,
					userType: request.userType,
					gender: request.gender,
					handPhone: request.handPhone,
					birthday: request.birthday,
					zipCode: request.zipCode,
					addr: request.addr,
					email: request.email,
					userStatus: request.userStatus
				};
			}else{
				personData=null;
			}
		},
		getPersonData:function() {
			return personData;
		},
		
		
		systemRoRiMainss:function(url,callback,errorCallback){
			CommonService.query(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		findPersonRole:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		updataPersonRoles:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		updataPersonRole:function(data,url,callback,errorCallback){
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