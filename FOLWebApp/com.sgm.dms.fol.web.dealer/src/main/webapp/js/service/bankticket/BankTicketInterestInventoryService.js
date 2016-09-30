/**
 * 
 */

FolService.factory('BankTicketInterestInventoryService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(data,response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},exportFile:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(data,response_info){
				typeof errorCallback==='function' && errorCallback(data,response_info);
			});
		},getToken:function(url,callback,errorCallback){
			CommonService.getJSON(url,function(data){
				typeof callback==='function' && callback(data);
			},function(data){
				typeof errorCallback==='function' && errorCallback(data);
			});
		},confirm:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(response_info){
				typeof callback==='function' && callback(response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
			
		}
	};
}]);