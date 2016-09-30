/**
 * 岗位切换service（dealer）
 */
FolService.factory('msgIdentityService', [
		'CommonService',
		function(CommonService) {
			return {
				getPositionSwitch : function(data, url, callback, errorCallback) {
					CommonService.post(data, url,
							function(data, response_info) {
								typeof callback === 'function'
										&& callback(data, response_info);
							}, function(data, response_info) {
								typeof errorCallback === 'function'
										&& errorCallback(data, response_info);
							});
				}
			};
		} ]);