/**
 * 经销商service
 */
FolService.factory('DealerInfoService', ['CommonService', function(CommonService) {
    //查询字典值
    return {
        query: function(data, url, callback, errorCallback) {
            CommonService.post(data, url, function(data, response_info) {
                typeof callback === 'function' && callback(data, response_info);
            }, function(response_info) {
                typeof errorCallback === 'function' && errorCallback(response_info);
            });
        }
    };
}]);