/**
 * 文件上传的Service
 */
FolService.factory('uploadFileService', ['CommonService', function(CommonService) {

    return {
        file: null,
        openUploadFile: function(url, callback, errorCallback) {
            CommonService.getJSON(url, function(data) {
                typeof callback === 'function' && callback(data);
            }, function(response_info) {
                typeof errorCallback === 'function' && errorCallback(response_info);
            });
        },
        openDownloadFile: function(url, callback, errorCallback) {
            CommonService.getJSON(url, function(data) {
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
        saveFile: function(data, url, callback, errorCallback) {
            CommonService.post(data, url, function(reponse_info) {
                typeof callback === 'function' && callback(reponse_info);
            }, function(reponse_info) {
                typeof errorCallback === 'function' && errorCallback(reponse_info);
            });
        },
        setFile: function(file) {
            this.file = file;
        },
        getFile: function() {
            return this.file;
        }
    };
}]);