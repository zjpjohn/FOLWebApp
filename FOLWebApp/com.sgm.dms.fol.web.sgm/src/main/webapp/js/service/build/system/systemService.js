FolService.factory('AuthorityService',['CommonService',function(CommonService){
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
					roleId :request.roleId,
					roleName:request.roleName,
					roleDesc:request.roleDesc,
					roleStatus:request.roleStatus,
					roleType:request.roleType
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
/**
 * UserService
 */
FolService.factory('UserService',['CommonService',function(CommonService){
	return {
		logout:function(url,callback,errorCallback){
			CommonService.post(url,function(data){
				typeof callback==='function' && callback(data);
			},function(resoponse){
				typeof errorCallback==='function' && errorCallback(resoponse);
			});
		}
	};
}]);
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