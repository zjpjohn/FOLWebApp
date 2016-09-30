/**
 * SAP每日财务凭证查询
 */
FolService.factory('FinancialCertificateService',['CommonService',function(CommonService){
	return {
		query:function(data,url,callback,errorCallback){
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
		}
	};
	
}]);