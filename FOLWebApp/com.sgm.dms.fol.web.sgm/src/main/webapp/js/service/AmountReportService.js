FolService.factory('AmountReportService',['CommonService',function(CommonService){
	//资金余额报表
	return {
		fundBalanceStatement:function(data,callback,errorCallback){
			CommonService.post(data,'',function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);