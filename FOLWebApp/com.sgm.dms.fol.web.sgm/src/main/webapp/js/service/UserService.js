/**
 * UserService
 */
FolService.factory('UserService',['CommonService',function(CommonService){
	return {
		logout:function(url,callback,errorCallback){
			CommonService.post(url,function(data){
				typeof callback==='function' && callback(data);
			},function(resoponse){
				typeof errorCallback==='function' && errorCallback(resoponse);
			});
		}
	};
}]);