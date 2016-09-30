/*
 * 奖金对账月度报表service
 */
FolService.factory('BonusService',['CommonService',function(CommonService){
	
		return {
			findBonusAmount:function(data,url,callback,errorCallback){
				CommonService.post(data,url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(data,response_info){
					typeof errorCallback==='function' && errorCallback(data,response_info);
				});
			},
		
			findBonusMonthForm : function(data, url, callback, errorCallback) {
				CommonService.post(data, url,
						function(data, response_info) {
							typeof callback === 'function'
									&& callback(data, response_info);
						}, function(data, response_info) {
							typeof errorCallback === 'function'
									&& errorCallback(data, response_info);
						});
			},
			getToken:function(url,callback,errorCallback){
				CommonService.getJSON(url,function(data,response_info){
					typeof callback==='function' && callback(data,response_info);
				},function(data,response_info){
					typeof errorCallback==='function' && errorCallback(data,response_info);
				});
			},
		};
	
}]);