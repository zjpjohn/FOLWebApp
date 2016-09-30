var frameworkModule = angular.module('common.ui.framework', [ 'ngResource', 'ui.router' ]);

frameworkModule.provider('permissionService', function() {
	var provider = {};
	var config = {
		permissionDataUrl : ""
	};

	provider.doConfig = function(permissionDataUrl) {
		config.permissionDataUrl = permissionDataUrl;
	};

	provider.$get = function($rootScope, $resource, $state, $location, $q) {
		var service = {};
		var path = $location.path();
		var permissionList = [];
		$rootScope.user = {};

		service.init = function() {
			var deferred = $q.defer();
			var permissionDataResource = $resource(config.permissionDataUrl, {}, {
				get : {
					method : 'GET'
				}
			});
			permissionDataResource.get(function(data) {
				var aUser = data;

				$rootScope.user.id = aUser.userId;
				$rootScope.user.name = aUser.userName;
				permissionList = aUser.permission;
				deferred.resolve("permissionService init");
			}, function(err) {
			});
			return deferred.promise;
		};

		service.check = function(userPermission) {
			var isCheck = false;
			for (var i = 0; i < permissionList.length; i++) {
				if (permissionList[i] == userPermission) {
					isCheck = true;
					break;
				}
			}
			return isCheck;
		}

		return service;
	}

	return provider;
});

frameworkModule.provider('navigationService', function() {
	var provider = {};
	var config = {
		menuDataUrl : "",
		stateProviderRef : null
	};

	provider.doConfig = function(menuDataUrl, stateProviderRef) {
		config.menuDataUrl = menuDataUrl;
		config.stateProviderRef = stateProviderRef;
	};

	provider.$get = function($rootScope, $resource, $state, $location, permissionService) {
		var service = {};
		var path = $location.path();
		var selectMenuId = null;
		var selectMenuData = null;
		$rootScope.menuDatas = [];
		$rootScope.childPages = [];

		service.init = function() {
			$rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
				var pmenuId01 = null;
				var pmenuId02 = null;
				if ($rootScope.appStatus) {
					var pindex = $rootScope.appStatus.activePageId.indexOf("_");
					if (pindex != -1) {
						pmenuId01 = $rootScope.appStatus.activePageId.substring(0, pindex);
						pmenuId02 = $rootScope.appStatus.activePageId.substring(pindex + 1);
					} else {
						pmenuId01 = $rootScope.appStatus.activePageId;
					}
					if (pmenuId02 != null) {
						for (var i = 0; i < $rootScope.menuDatas.length; i++) {
							var md = $rootScope.menuDatas[i];
							if (pmenuId01 == md.id) {
								for (var j = 0; j < md.submenus.length; j++) {
									var smd = md.submenus[j];
									if (pmenuId02 == smd.id) {
										smd.active = false;
										break;
									}
								}
								break;
							}
						}
					} else {
						for (var i = 0; i < $rootScope.menuDatas.length; i++) {
							var md = $rootScope.menuDatas[i];
							if (pmenuId01 == md.id) {
								md.active = false;
								break;
							}
						}
					}
				}

				var index = toState.name.indexOf("_");
				var menuId01 = null;
				var menuId02 = null;
				if (index != -1) {
					menuId01 = toState.name.substring(0, index);
					menuId02 = toState.name.substring(index + 1);
				} else {
					menuId01 = toState.name;
				}
				for (var i = 0; i < $rootScope.menuDatas.length; i++) {
					var md = $rootScope.menuDatas[i];
					if (md.id == menuId01) {
						md.active = true;
						if (menuId02 != null) {
							for (j = 0; j < md.submenus.length; j++) {
								var smd = md.submenus[j];
								if (smd.id == menuId02) {
									smd.active = true;
									$rootScope.appStatus = {
										activePageId : md.id + "_" + smd.id,
										activePagePath : [ {
											name : md.name,
											url : null
										}, {
											name : smd.name,
											url : md.id + '_' + smd.id
										} ]
									};
								}
							}
						} else {
							md.active = true;
							$rootScope.appStatus = {
								activePageId : md.id,
								activePagePath : [ {
									name : md.name,
									url : md.id
								} ]
							};
						}
						break;
					}
				}
			});

			var menuDataResource = $resource(config.menuDataUrl, {}, {
				get : {
					method : 'GET',
					isArray : true
				}
			});

			menuDataResource.get(function(data) {
				var menuList = data;

				for (var i = 0; i < menuList.length; i++) {
					var d = menuList[i];
					var isCheck = false;
					if (d.permissions.length > 0) {
						for (var pi = 0; pi < d.permissions.length; pi++) {
							isCheck = permissionService.check(d.permissions[pi]);
							if (isCheck) {
								break;
							}
						}
					} else {
						isCheck = true;
					}

					if (isCheck) {
						var menu = {
							active : false,
							id : d.id,
							name : d.name,
							url : d.url,
							submenus : []
						};
						if (d.submenus.length > 0) {
							for (var j = 0; j < d.submenus.length; j++) {
								var sd = d.submenus[j];
								var isSubCheck = false;
								if (sd.permissions.length > 0) {
									for (var api = 0; api < sd.permissions.length; api++) {
										isSubCheck = permissionService.check(sd.permissions[api]);
										if (isSubCheck) {
											break;
										}
									}
								} else {
									isSubCheck = true;
								}

								if (isSubCheck) {
									var subMenu = {
										active : false,
										id : sd.id,
										name : sd.name,
										url : sd.url
									};
									menu.submenus.push(subMenu);
									if (path == '/' + d.id + "_" + sd.id) {
										selectMenuId = d.id + "_" + sd.id;
										var selectMenuData = {
											activePageId : d.id + "_" + sd.id,
											activePagePath : [ {
												name : d.name,
												url : null
											}, {
												name : sd.name,
												url : d.id + "_" + sd.id
											} ]
										};
									}
									config.stateProviderRef.state(d.id + "_" + sd.id, {
										url : '/' + d.id + "_" + sd.id,
										templateUrl : sd.url
									});
								}

							}
						} else {
							if (path == '/' + d.id) {
								selectMenuId = d.id;
								var selectMenuData = {
									activePageId : d.id,
									activePagePath : [ {
										name : d.name,
										//url : d.id
										url : null
									} ]
								};
							}

							config.stateProviderRef.state(""+d.id, {
								url : '/' + d.id,
								templateUrl : d.url
							});
						}
						$rootScope.menuDatas.push(menu);
					}
				}

				if (selectMenuId != null && selectMenuData != null) {
					$rootScope.appStatus = selectMenuData;
					$state.go(selectMenuId);
				} else {
					if ($rootScope.menuDatas.length > 0) {
						if ($rootScope.menuDatas[0].submenus.length > 0) {
							$rootScope.appStatus = {
								activePageId : $rootScope.menuDatas[0].id + "_"
										+ $rootScope.menuDatas[0].submenus[0].id,
								activePagePath : [ {
									name : $rootScope.menuDatas[0].name,
									url : null
								}, {
									name : $rootScope.menuDatas[0].submenus[0].name,
									url : $rootScope.menuDatas[0].id + '_' + $rootScope.menuDatas[0].submenus[0].id
								} ]
							};
							$state.go($rootScope.menuDatas[0].id + "_" + $rootScope.menuDatas[0].submenus[0].id);
						} else {
							$rootScope.appStatus = {
								activePageId : $rootScope.menuDatas[0].id,
								activePagePath : [ {
									name : $rootScope.menuDatas[0].name,
									url : $rootScope.menuDatas[0].id
								} ]
							};
							$state.go($rootScope.menuDatas[0].id);
						}
					}
				}
			}, function(err) {
			});
		}

		service.registerChildPage = function(pageKey, pageName, pageTemplateUrl) {
			var key = $rootScope.appStatus.activePageId + "_" + pageKey;
			var allowRegister = true;
			for (var i = 0; i < $rootScope.childPages.length; i++) {
				if ($rootScope.childPages[i].pageKey == key) {
					allowRegister = false;
					break;
				}
			}
			if (allowRegister) {
				var path = [];
				for (var i = 0; i < $rootScope.appStatus.activePagePath.length; i++) {
					path.push({
						name : $rootScope.appStatus.activePagePath[i].name,
						url : $rootScope.appStatus.activePagePath[i].url
					});
				}
				path.push({
					name : pageName,
					url : key
				});
				$rootScope.childPages.push({
					pageKey : key,
					pagePath : path
				});

				config.stateProviderRef.state(key, {
					url : '/' + key,
					templateUrl : pageTemplateUrl
				});
			}
			return key;
		}

		service.goChildPage = function(key) {
			for (var i = 0; i < $rootScope.childPages.length; i++) {
				if ($rootScope.childPages[i].pageKey == key) {
					$rootScope.appStatus = {
						activePageId : $rootScope.childPages[i].pageKey,
						activePagePath : $rootScope.childPages[i].pagePath
					};
					$state.go(key);
					break;
				}
			}
		}
		
		service.goPage = function(key) {
			$state.go(key);
		}

		return service;
	}

	return provider;
});

frameworkModule.directive('leftMenu', function() {
	return {
		restrict : 'EA',
		replace : true,
		templateUrl : 'common/views/left-menu.html',
		scope : true,
		controller : function($scope, $element, $attrs, navigationService) {

			$scope.submenusClick = function(e) {
				e.preventDefault();
				var submenu = $(e.currentTarget).siblings('ul');
				var li = $(e.currentTarget).parents('li');
				if (li.hasClass('open')) {
					submenu.slideUp();
					li.removeClass('open');
				} else {
					submenu.slideDown();
					li.addClass('open');
				}
			};
		},
	};
});

frameworkModule.directive('controlGroup',function(){
	return {
		restrict:'EA',
		link : function(scope, elm, attr){
			if(attr.paddingTop!=null){
				$(elm).css('padding-top',attr.paddingTop);
			}
			if(attr.paddingLeft!=null){
				$(elm).css('padding-left',attr.paddingLeft);
			}
			if(attr.paddingRight!=null){
				$(elm).css('padding-right',attr.paddingRight);
			}
			if(attr.paddingBottom!=null){
				$(elm).css('padding-bottom',attr.paddingBottom);
			}
		}
	};
});

frameworkModule.directive('ajaxUpload', function() {
	return {
		restrict : 'A',
		scope : {
			uplaodUrl : '@',
			callbackFunction : '='
		},
		link : function(scope, elm, attr) {
			$(elm).change(function() {
				$(this).upload(scope.uplaodUrl, function(res) {
					scope.$apply(function() {
						if (scope.callbackFunction != null) {
							scope.callbackFunction(res);
						}
					});
				}, 'json');
			});
		}
	};
});

/**
 * 元素无法复制
 * @param  {[type]} ){	return             {		restrict:'A',		link:function(scope, elm, attrs){			elm.on('paste',function(e){				setTimeout(function(){					var text [description]
 * @param  {[type]} 100);			});		}	};} [description]
 * @return {[type]}                        [description]
 */
frameworkModule.directive('disableCopy',function(){
	return {
		restrict:'A',
		link:function(scope, elm, attr){
			console.log(1);
			elm.on('copy',function(e){
				console.log(2);
				setTimeout(function(){
					var text = $(elm).val();
					console.log(3);
					if(text.length>0){
						console.log(1);
						$(elm).val('');
					}
				},100);
			});
		}
	};
});

frameworkModule.controller('identityNumCtrl', [
		'$rootScope',
		'$timeout',
		'$scope',
		'$filter',
		'$http',
		'msgIdentityService',
		'$cookies',
		function($rootScope, $timeout, $scope, $filter, $http,
				msgIdentityService, $cookies) {
			$scope.data = {
				userAccount : $cookies.COOKIE_FOL_ACCOUNT
			};

			msgIdentityService.getPositionSwitch($scope.data,
					'system/systemamount/find', function(data, response_info) {
						$.messager.progress('close');
						$scope.num = data.rows.length;
					}, function(data, response_info) {
						console.log(data);
					});
		} ]);