/**
 *  银行银票额度维护service
 */
FolService.factory('BankTicketLimitMaintainService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(data,response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},insert:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(data,response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},update:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(data,response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},addBankTicketLimit:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},updateBankTicketLimit:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);