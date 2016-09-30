FolService.factory('ReturnAllowanceManagerService', ['CommonService', function(CommonService) {
    //折让单扫描
    return {
        query: function(data, url, callback, errorCallback) {
            CommonService.post(data, url, function(data) {
                typeof callback === 'function' && callback(data);
            }, function(response_info) {
                typeof errorCallback === 'function' && errorCallback(response_info);
            });
        },
        report: function(data, url, callback, errorCallback) {
            CommonService.post(data, url, function(data) {
                typeof callback === 'function' && callback(data);
            }, function(response_info) {
                typeof errorCallback === 'function' && errorCallback(response_info);
            });
        },
        reject: function(data, url, callback, errorCallback) {
            CommonService.post(data, url, function(data) {
                typeof callback === 'function' && callback(data);
            }, function(response_info) {
                typeof errorCallback === 'function' && errorCallback(response_info);
            });
        },
        getToken: function(url, callback, errorCallback) {
            CommonService.getJSON(url, function(data) {
                typeof callback === 'function' && callback(data);
            }, function(response_info) {
                typeof errorCallback === 'function' && errorCallback(response_info);
            });
        },
        deatilQuery: function(data, url, callback, errorCallback) {
            CommonService.post(data, url, function(data) {
                typeof callback === 'function' && callback(data);
            }, function(response_info) {
                typeof errorCallback === 'function' && errorCallback(response_info);
            });
        },
        downlandXmlInfo: function(url, callback, errorCallback) {
            CommonService.get(url, function(data, response_info) {
                typeof callback === 'function' && callback(data, response_info);
            }, function(data, response_info) {
                typeof errorCallback === 'function' && errorCallback(data, response_info);
            });
        }
    };
}]);