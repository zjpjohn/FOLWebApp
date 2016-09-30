/**
 * 索赔单发票跟踪
 */
FolService.factory('WrOrderCollectService',['CommonService',function(CommonService){
		return {
			//查询
			query: function(data,url, callback, errorCallback) {
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(data,response_info){
					typeof errorCallback==='function' && errorCallback(data,response_info);
				});
			},
			//汇总
			collect: function(data,url, callback, errorCallback) {
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(data,response_info){
					typeof errorCallback==='function' && errorCallback(data,response_info);
				});
			},
			getToken:function(url,callback,errorCallback){
				CommonService.getJSON(url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(data,response_info){
					typeof errorCallback==='function' && errorCallback(data,response_info);
				});
			},
			checkdata:function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(data,response_info);
				});
			},

		};
	}]);