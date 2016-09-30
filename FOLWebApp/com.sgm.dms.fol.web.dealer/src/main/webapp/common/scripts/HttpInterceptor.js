/**
 * shenweiwei
 * http拦截器
 */
FolService.factory('HttpInterceptor',['$q','$window','$interval','$cookies',function($q,$window,$interval,$cookies){
	return {
		'request':function(config){
			console.log(config);
			if(config.method==='POST'||config.method==='PUT'){
				if(!folUtil.isNull(config.data)){
					var security_code=$.md5(JSON.stringify(config.data));
					config.headers['Content-MD5']=security_code;
				}
			}
//			folUtil.logonTimeOut($interval,$window);
			if(folUtil.isNull($cookies.COOKIE_FOL_USER_ID)){
				$window.location.reload();
			}else{
				return config;
			}
			
		},
		'response':function(response){
			console.log(response);
			if(!folUtil.isNull(response)&&response.status===302){
				$window.location.href=DP_SERVER_URL;
			}
			return $q.when(response);
		},
		'requestError':function(rejection){
			return $q.reject(rejection);
		},
		'responseError':function(rejection){
			console.log(rejection);
			
			if(!folUtil.isNull(rejection)&&rejection.status===302){
				$window.location.href=DP_SERVER_URL;
			}
			
			return rejection
		}
	};
}]);