/**
 *  银行银票额度维护service
 */
FolService.factory('BankTicketDeadLineMaintainService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(data,response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},addBankTicketDeadLine:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},deleteBankTicketDeadLine:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},updateDeadLineWithBrand:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},updateDeadLineWithSpecialDealer:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);