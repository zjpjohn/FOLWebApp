FolService.factory('CustomerReconcileListImportService',['CommonService',function(CommonService){
	//客户对账单导入
	return {
		customerBilling:function(data,callback,errorCallback){
			CommonService.post(data,'',function(data){
				typeof callback==='function' && callback(data);
			},function(response_info){
				typeof errorCallback==='function' && errorCallback(response_info);
			});
		}
	};
}]);