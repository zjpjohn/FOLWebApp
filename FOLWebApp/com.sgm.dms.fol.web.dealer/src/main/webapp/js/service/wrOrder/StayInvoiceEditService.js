/**
 * 待开发票编辑
 */
FolService.factory('StayInvoiceEditService',['CommonService',function(CommonService){
		return {
			query: function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			//批量更新
			update: function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			//保存
			save:  function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			//删除
			del:   function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			//上报
			inform: function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			//
			queryDeatil:  function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(response_info){
					typeof errorCallback==='function' && errorCallback(response_info);
				});
			},
			exit:  function(data,url,callback,errorCallback){
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