FolService.factory('ReturnAllowanceManagerService', ['CommonService', function(CommonService) {

    return {
        returnAllowance: null,
        report: function(data, url, callback, errorCallback) {
            CommonService.post(data, url, function(data, response_info) {
                typeof callback === 'function' && callback(data, response_info);
            }, function(data, response_info) {
                typeof errorCallback === 'function' && errorCallback(data, response_info);
            });
        },
        delete: function(data, url, callback, errorCallback) {
            CommonService.post(data, url, function(data, response_info) {
                typeof callback === 'function' && callback(data, response_info);
            }, function(data, response_info) {
                typeof errorCallback === 'function' && errorCallback(data, response_info);
            });
        },
        queryReturnOrder: function(data, url, callback, errorCallback) {
            CommonService.post(data, url, function(data, response_info) {
                typeof callback === 'function' && callback(data, response_info);
            }, function(data, response_info) {
                typeof errorCallback === 'function' && errorCallback(data, response_info);
            });
        },
        save: function(data, url, callback, errorCallback) {
            CommonService.post(data, url, function(data, response_info) {
                typeof callback === 'function' && callback(data, response_info);
            }, function(data, response_info) {
                typeof errorCallback === 'function' && errorCallback(data, response_info);
            });
        },
        downloadXmlFile: function(url, callback, errorCallback) {
            CommonService.get(url, function(data, response_info) {
                typeof callback === 'function' && callback(data, response_info);
            }, function(data, response_info) {
                typeof errorCallback === 'function' && errorCallback(data, response_info);
            });
        },
        queryReturnAllowance: function(data, url, callback, errorCallback) {
            CommonService.post(data, url, function(data, response_info) {
                typeof callback === 'function' && callback(data, response_info);
            }, function(data, response_info) {
                typeof errorCallback === 'function' && errorCallback(data, response_info);
            });
        },
        toAddOrUpdate: function(url, callback, errorCallback) {
            CommonService.query(url, function(data, response_info) {
                typeof callback === 'function' && callback(data, response_info);
            }, function(data, response_info) {
                typeof errorCallback === 'function' && errorCallback(data, response_info);
            });
        },
        setReturnAllowance: function(returnAllowance) {
            this.returnAllowance = returnAllowance;
        },
        getReturnAllowance: function() {
            return this.returnAllowance;
        }
    };

}]);