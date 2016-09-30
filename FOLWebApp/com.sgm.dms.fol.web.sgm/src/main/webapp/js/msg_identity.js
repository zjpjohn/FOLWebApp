var msgIdentityCtrl = angular.module('msgIdentityCtrlModule', [
		'ajoslin.promise-tracker', 'FolService', 'ngCookies', 'ui.router' ]);
msgIdentityCtrl.run([ '$window', '$interval','$rootScope',
          		function( $window, $interval,$rootScope) {
	var timer;
	
	//计时累加
	$interval(function(){
		localStorage.setItem('usedtime',parseInt(localStorage.getItem('usedtime')===null?0:localStorage.getItem('usedtime'))
				+PROGRESS_ACTION_TIMEOUT);
	},PROGRESS_ACTION_TIMEOUT);

	//刷新或者url变更的监听事件
	$rootScope.$on('$locationChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
		//登陆超时
		logonTimeOut();				
	});
	
	var logonTimeOut=function(){
		console.log(localStorage.getItem('usedtime'));

		if(folUtil.isNull(localStorage.getItem('surplustime'))){
			localStorage.setItem('surplustime',SESSION_TIMEOUT-localStorage.getItem('usedtime'));
		}else{
			localStorage.setItem('surplustime',parseInt(localStorage.getItem('surplustime'))-parseInt(localStorage.getItem('usedtime')));
		}
		
		if(localStorage.getItem('surplustime')>0&&!folUtil.isNull(localStorage.getItem('usedtime'))){
			localStorage.setItem('surplustime',parseInt(localStorage.getItem('surplustime'))+parseInt(localStorage.getItem('usedtime')));
		}
		
		console.log(localStorage.getItem('surplustime'));
		
		timer = $interval(function(){
			alert("用户登陆已过期，请重新登陆");
		},localStorage.getItem('surplustime'),1);
			
		localStorage.setItem('usedtime',0)
		
		timer.then(function(){
			console.log("自动计划任务执行完毕");
		}, function(){
			console.log("自动计划任务执行失败或重置");
		}, function(){
			$window.location.href=WOL_SERVER_URL;
			localStorage.removeItem('surplustime');
			localStorage.removeItem('usedtime');
		});
	}
}]);

msgIdentityCtrl.controller('msgIdentityCtrl', [
		'$rootScope',
		'$timeout',
		'$scope',
		'$filter',
		'$http',
		'$window',
		'msgIdentityService',
		'$cookies',
		'$state',
		function($rootScope, $timeout, $scope, $filter, $http, $window,
				msgIdentityService, $cookies, $state) {
			/*
			 * $http({ method: 'GET', url: 'common/login/identity', headers:
			 * {'Content-Type': 'application/json' }
			 * }).success(function(data,status,headers,config){ $scope.accounts=
			 * data; }).error(function(data,status,headers,config){ });
			 */
			$scope.istableshow = false;
			$.messager.progress({
				title : 'Please waiting',
				msg : 'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});

			$scope.data = {
				userAccount : $cookies.COOKIE_FOL_ACCOUNT,
				positionId : $cookies.COOKIE_FOL_POSITION_ID
			};
			msgIdentityService.getSystemmounts($scope.data,
					'system/systemamount/find', function(data, response_info) {
						$.messager.progress('close');
						$scope.accounts = data.rows;
					}, function(data, response_info) {
						console.log(data);
						$.messager.progress('close');
						if (response_info.state === STATE.TIMEOUT) {
							console.log('timeout');
							$.messager.alert('提示', '系统超时请稍后访问');
						}
					});

			$scope.doLogin = function(positionId,loginId) {
				document.loginform.action = "common/login?positionId="+ positionId+"&loginId="+loginId;
				console.log(document.loginform.action);
				document.loginform.submit();
			};

			$scope.doLogout = function() {
				var pathName = document.location.pathname;
				var index = pathName.substr(1).indexOf("/");
				var contextPath = pathName.substr(0, index + 1);
				window.location = "common/logout";
			};

		} ]);

// 这里是service.js
var FolService = angular.module('FolService', []);