/**
 * 奖金定义service
 */
FolService.factory('BonusDefinitionSerivce',['CommonService',function(CommonService){
	return {
		queryBonusDefinition:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		addBonusDefinition:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		updateBonusDefinition:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);
/**
 * 奖金发放版本上传service
 */
FolService.factory('BonusIssueService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},issue:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},back:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},bonusIssueQuery:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},getToken:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	}
}]);
/**
 * 奖金发放版本上传service
 */
FolService.factory('BonusIssueVersionFileAuditService',['CommonService',function(CommonService){
	return {
		audit:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},reject:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},findCodeTypeNames:function(url,callback,errorCallback){
			CommonService.query(url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},getToken:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	}
}]);
/**
 * 奖金发放版本上传service
 */
FolService.factory('BonusIssueVersionUploadService',['CommonService',function(CommonService){
	return {
		issueVersionQuery:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},cancel:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},findCodeTypeNames:function(url,callback,errorCallback){
			CommonService.query(url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},confirmAudit:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},getToken:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	}
}]);
/**
 * 奖金重处理Service
 */
FolService.factory('BonusReastDisposeService',['CommonService',function(CommonService){
	return {
		query:function(data,url, callback, errorCallback) {
			CommonService.post(data, url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		reastDispose:function(data,url, callback, errorCallback) {
			CommonService.post(data, url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},reject:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},getToken:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);
/*
 * 奖金对账管理service
 */
FolService.factory('BonusService',['CommonService',function(CommonService){
	return {
		findBonusAmount:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		findCodeTypeNames:function(url,callback,errorCallback){
			CommonService.query(url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		findBonusMonthDeatil:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		findbonusAmountReconcile:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		findBonusAmountCollect:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
		getToken:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
	};
	
}]);