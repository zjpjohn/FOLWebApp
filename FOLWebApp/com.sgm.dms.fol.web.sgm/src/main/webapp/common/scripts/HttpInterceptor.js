/**
 * shenweiwei
 * http拦截器
 */
FolService.factory('HttpInterceptor',['$q','$window','$interval','$cookies',function($q,$window,$interval,$cookies){
	return {
		'request':function(config){
			console.log(config);
//			folUtil.logonTimeOut($interval,$window);
			
//			if(folUtil.isNull($cookies.COOKIE_FOL_USER_ID)){
//				$window.location.href=FOL_SERVER_URL;
//			}
			return config;
		},
		'response':function(response){
			console.log(response);
			
			var sgmtimeout='系统登录/System Login';
			if(!folUtil.isNull(response)&&response.data.toString().indexOf(sgmtimeout)>=0){
				$window.location.href=FOL_SERVER_URL;
			}
			
			return $q.when(response);
		},
		'requestError':function(rejection){
			return $q.reject(rejection);
		},
		'responseError':function(rejection){
			console.log(rejection);
			return rejection
		}
	};
}]);