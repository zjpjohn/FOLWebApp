/**
 * 经销商提交超时未处理发票查询
 */
FolService.factory('OvertimeInvoiceService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
			CommonService.post(data,url,function(data,response_info){
				typeof callback==='function' && callback(data,response_info);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		},
	};
	
}]);