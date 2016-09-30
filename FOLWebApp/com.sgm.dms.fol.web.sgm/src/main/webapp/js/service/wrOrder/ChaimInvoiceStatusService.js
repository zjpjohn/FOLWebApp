/**
 * 索赔发票状态查询
 */
FolService.factory('ChaimInvoiceStatusService',['CommonService',function(CommonService){
		return {
			query:function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			//重处理
			anew: function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			//保存
			save: function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			//同意
			agree: function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			//退回
			back: function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			//查询明细
			queryDeatil: function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
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