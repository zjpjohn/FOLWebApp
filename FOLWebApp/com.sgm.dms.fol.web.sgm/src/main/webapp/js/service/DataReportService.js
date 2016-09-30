FolService.factory('AcountService',['CommonService',function(CommonService){
	return {
		getAccountAmountInfo:function(data,url,callback,errorCallback){
			CommonService.crosPost(data,url,function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
	
}]);