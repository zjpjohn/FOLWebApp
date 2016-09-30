FolController.controller('DealerInfoController', ['$scope', '$rootScope', '$state', '$stateParams', '$timeout', '$window', 'DealerInfoService', function($scope, $rootScope, $state, $stateParams, $timeout, $window, DealerInfoService) {

    /**
     * 初始化数据
     */
    $timeout(function() {
        var datagridWaitSelected = $('#datagridWaitSelected').datagrid('getPager');
        datagridWaitSelected.pagination({
            onSelectPage: function(pageNumber, pageSize) {
                $scope.query_data.beginNo = pageSize * (pageNumber - 1) == 0 ? 0 : pageSize * (pageNumber - 1) + 1;;
                $scope.query_data.endNo = pageSize * pageNumber;

                $.messager.progress({
                    title: 'Please waiting',
                    msg: 'Loading data...',
                    interval: PROGRESS_ACTION_TIMEOUT
                });

                DealerInfoService.query($scope.query_data, 'dealerInfo/dealerInfoPageList', function(data, response_info) {
                    $.messager.progress('close');
                    $('#datagrid').datagrid('loadData', data);
                }, function(response_info) {
                    $.messager.progress('close');
                    if (response_info.state === STATE.TIMEOUT) {
                        console.log('timeout');
                        $.messager.alert('提示', '系统超时请稍后访问');
                    }
                });
            }
        });
    });

    /**
     * 查询经销商页面条件重置
     */
    $scope.findDealerReset = function() {
        $('#sapCode').textbox('setValue', null);
        $('#dealerName').textbox('setValue', null);
        $('#province').combobox('select', -1);
        $('#city').combobox('select', -1);
        $('#region').combobox('select', -1);
    };

    /**
     * 查询经销商
     */
    $scope.queryDealer = function() {
        $scope.query_data = {
            sapCode: $('#sapCode').textbox('getValue'),
            dealerName: $('#dealerName').textbox('getValue'),
            beginNo: 0,
            endNo: 10
                // province: $('#province').combobox('getValue'),
                // city: $('#city').combobox('getValue'),
                // region: $('#region').combobox('getValue')
        };

        $.messager.progress({
            title: 'Please waiting',
            msg: 'Loading data...',
            interval: PROGRESS_ACTION_TIMEOUT
        });

        DealerInfoService.query($scope.query_data, 'dealerInfo/dealerInfoPageList', function(data, response_info) {
            $.messager.progress('close');
            $('#datagridWaitSelected').datagrid('loadData', { rows: [], total: 0 });
            $('#datagridWaitSelected').datagrid('loadData', data);
        }, function(response_info) {
            $.messager.progress('close');
            if (response_info.state === STATE.TIMEOUT) {
                console.log('timeout');
                $.messager.alert('提示', '系统超时请稍后访问');
            } else {
                $.messager.alert('提示', '查询失败，原因：' + response_info.message);
            }
        });
    };

    /**
     * 向左移动数据
     */
    $scope.left = function() {
        var datagrid_wait_selected_rows = $('#datagridWaitSelected').datagrid('getSelections');
        if (datagrid_wait_selected_rows.length <= 0) {
            $.messager.alert('提示', '请选择一条数据再操作');
            return;
        }

        var datagrid_selected_rows = $('#datagridSelected').datagrid('getRows');
        angular.forEach(datagrid_wait_selected_rows, function(row, i) {
            if (datagrid_selected_rows.length > 0) {
                angular.forEach(datagrid_selected_rows, function(value, j) {
                    if (row['sapCode'] !== value['sapCode']) {
                        $('#datagridSelected').datagrid('appendRow', row);
                    }
                });
            } else {
                $('#datagridSelected').datagrid('appendRow', row);
            }
        });
    };

    /**
     * 向右移动数据
     */
    $scope.right = function() {
        var datagrid_selected_rows = $('#datagridSelected').datagrid('getSelections');
        if (datagrid_selected_rows.length <= 0) {
            $.messager.alert('提示', '请选择一条数据再操作');
            return;
        }

        angular.forEach(datagrid_selected_rows, function(row, index) {
            var rowIndex = $('#datagridSelected').datagrid('getRowIndex', row);
            $('#datagridSelected').datagrid('deleteRow', rowIndex);

        });
    };

    /**
     * 向左移动全部数据
     */
    $scope.leftAll = function() {
        var datagrid_wait_selected_rows = $('#datagridWaitSelected').datagrid('getRows');
        $('#datagridSelected').datagrid('loadData', { total: 0, rows: [] });

        angular.forEach(datagrid_wait_selected_rows, function(row, index) {
            $('#datagridSelected').datagrid('appendRow', row);
        });

    };

    /**
     * 向右移动全部数据
     */
    $scope.rightAll = function() {
        $('#datagridSelected').datagrid('loadData', { total: 0, rows: [] });
    };

    /**
     * 返回已选择的dealer的sapCode
     */
    $scope.submit = function() {
        var rows = $('#datagridSelected').datagrid('getRows');
        var sapCodes = '';
        angular.forEach(rows, function(value, index) {
            sapCodes += value['sapCode'] + ',';
        });

        sapCodes = sapCodes.substr(0, sapCodes.length - 1);

        $state.go($stateParams.stateName, { data: { sapCodes: sapCodes } });
    };

    $scope.cancel = function() {
        $window.history.back();
    };
}]);