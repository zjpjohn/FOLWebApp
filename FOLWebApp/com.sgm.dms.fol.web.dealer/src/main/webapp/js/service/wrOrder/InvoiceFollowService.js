/**
 * 索赔单发票跟踪
 */
FolService.factory('InvoiceFollowService',['CommonService',function(CommonService){
		return {
			query:function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			//重做
			anew: function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			//作废
//			cancel:function(data,url,callback,errorCallback){
//				CommonService.post(data,url,function(data,response_info){
//					typeof callback==='function' && callback(data,response_info);
//				},function(response_info){
//					typeof errorCallback==='function' && errorCallback(response_info);
//				});
//			},
			//明细
			queryDeatil:function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			getToken:function(url,callback,errorCallback){
				CommonService.getJSON(url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(data,response_info){
					typeof errorCallback==='function' && errorCallback(data,response_info);
				});
			},
			getSign:function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			}
		};
	}]);