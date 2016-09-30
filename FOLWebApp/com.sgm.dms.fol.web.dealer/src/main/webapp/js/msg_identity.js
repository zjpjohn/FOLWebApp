var msgIdentityCtrl = angular.module('msgIdentityCtrlModule', [
		'ajoslin.promise-tracker', 'FolService', 'ngCookies', 'ui.router' ]);

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
			$scope.istableshow = false;
			$.messager.progress({
				title : 'Please waiting',
				msg : 'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});

			$scope.data = {
				userAccount : $cookies.COOKIE_ACCOUNT,
				positionId : $cookies.COOKIE_POSITION_ID
			};

			msgIdentityService.getPositionSwitch($scope.data,
					'system/systemamount/find', function(data, response_info) {
						$.messager.progress('close');
						$scope.accounts = data.rows;
					}, function(data, response_info) {
						$.messager.progress('close');
						if (response_info.state === STATE.TIMEOUT) {
							console.log('timeout');
							$.messager.alert('提示', '系统超时请稍后访问');
						}
						console.log("error:" + data);
					});

			$scope.doLogin = function(positionId, loginId) {
				document.loginform.action = "user/login/dealer/selectposition?positionId="
						+ positionId + "&loginId=" + loginId;
				console.log(document.loginform.action);
				document.loginform.submit();
			};

			$scope.doLogout = function() {
				var pathName = document.location.pathname;
				var index = pathName.substr(1).indexOf("/");
				var contextPath = pathName.substr(0, index + 1);
				window.location = "user/logout/dealer";
			};

		} ]);

// 这里是service.js
var FolService = angular.module('FolService', []);