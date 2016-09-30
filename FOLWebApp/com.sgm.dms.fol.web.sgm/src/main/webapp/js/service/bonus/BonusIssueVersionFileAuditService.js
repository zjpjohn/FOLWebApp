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