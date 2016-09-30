/**
 * 文件上传controller
 */
FolController.controller('uploadFileController', ['$rootScope', '$scope', '$window', '$timeout', '$state', '$stateParams', 'uploadFileService', 'CodeService', function($rootScope, $scope, $window, $timeout, $state, $stateParams, uploadFileService, CodeService) {
    $scope.pageName = $stateParams.pageName;

    /**
     * 初始化数据
     */
    // $timeout(setInitData());

    /**
     * 取消
     */
    $scope.cancel = function() {
        $window.history.back();
    };

    /**
     * 下载文件
     */
    $scope.openDownloadFile = function() {
        uploadFileService.openDownloadFile('services/tokens', function(data) {
            $.each(data, function(i, field) {
                if (i == "token") {
                    alert($('#result').val().filename);
                    //					$("#div1").append(Date() + "---get token---" + field+"<br/>");
                    $window.open(FOL_UPLOAD_SERVER_URL+'fileservice/html/download.html?token=' + field + '&fileId=' + $('#result').val(), '_blank', 'height=300,width=600,scrollbars=no,location=no');
                }
            });
        }, function(data) {
            console.log(data);
        });
    };

    /**
     * 上传文件
     */
    $scope.openUploadFile = function() {
        uploadFileService.openUploadFile('services/tokens', function(data) {
            console.log('data:' + data);
            $.each(data, function(i, field) {
                if (i == "token") {
                    //					$("#div1").append(Date() + "---get token---" + field+"<br/>");
                    //$window.open('http://dapi.saic-gm.com/fileservice/html/upload.html?token='+field+'&resultId=result', '_blank',	'height=300,width=600,scrollbars=no,location=no');
                    $window.open(FOL_UPLOAD_SERVER_URL+'fileservice/html/upload.html?token=' + field + '&resultId=result' + '&resultName=resultName', '_blank', 'height=300,width=600,scrollbars=no,location=no');
                }
            });
        }, function(data) {
            console.log(data);
        });
    };

    /**
     * 文件保存
     */
    $scope.saveFile = function() {
        if ($stateParams.pageName === 'createRetrunAllowance') {
            var return_allowance = {
                fileId: $('#result').val(),
                fileName: $('#resultName').val()
            }
            uploadFileService.setFile(return_allowance);
            $state.go($stateParams.pageName);
            return;
        }

        uploadFileService.getToken('services/tokens', function(data, response_info) {
            $.each(data, function(i, field) {
                if (i == "token") {
                    var data = {
                        filePath: FOL_UPLOAD_SERVER_URL+"fileservice/v1/files/" + $('#result').val() + "?token=" + field,
                        // data:$stateParams.data,
                        fileId: $('#result').val(),
                        fileName: $('#resultName').val(),
                        token: field
                    };

                    if (folUtil.isNull($('#result').val())) {
                        $.messager.alert('提示', '请选择文件再上传');
                        return;
                    }

                    $.messager.progress({
                        title: 'Please waiting',
                        msg: 'Loading data...',
                        interval: PROGRESS_ACTION_TIMEOUT
                    });

                    $.each(UPLOAD_URL, function(i, val) {
                        if (i.toLowerCase() === $stateParams.pageName.toLowerCase()) {
                            uploadFileService.saveFile(data, val, function(response_info) {
                                $window.history.back();
                                $.messager.progress('close');
                                $.messager.alert('提示', '上传文件成功');
                            }, function(response_info) {
                                $.messager.progress('close');

                                if (response_info.state === STATE.TIMEOUT) {
                                    console.log('timeout');
                                    $.messager.alert('提示', '系统超时请稍后访问');

                                } else {
                                    $.messager.alert('提示', response_info.message);
                                }

                            });
                        }
                    });
                }
            });
        }, function(response_info) {
            console.log(response_info);
        });
    };

    /**
     * 检查数据
     */
    function checkData() {
        return true;
    }

    /**
     * 数据初始化
     */
    function setInitData() {
        console.log($stateParams.pageName);
    }

}]);