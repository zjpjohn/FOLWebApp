FolController.controller('ReturnAllowanceManagerController', ['$scope', '$timeout', '$state', '$rootScope', '$q', 'CodeService', 'ReturnAllowanceManagerService', function($scope, $timeout, $state, $rootScope, $q, CodeService, ReturnAllowanceManagerService) {
    $scope.istableshow = false;
    $scope.flag=true;

    $timeout(function() {
        
        $.messager.progress({
            title: 'Please waiting',
            msg: 'Loading data...',
            interval: PROGRESS_ACTION_TIMEOUT
        });

        var fields = ['折让单处理状态'];
        CodeService.findCodeTypeNames(fields, 'code/findCodeTypeNames', function(data) {
            $.messager.progress('close');

            var typesId = ['#status'];

            for (var i = 0; i < data.length; i++) {
                if (!folUtil.isNull(data[i])) {
                    $(typesId[i]).combobox('loadData', data[i]);
                    $(typesId[i]).combobox('select', data[i][0]['code']);
                }
            }
        }, function(response_info) {
            $.messager.progress('close');
            if (response_info.state === STATE.TIMEOUT) {
                console.log('timeout');
                $.messager.alert('提示', '系统超时请稍后访问');
            }
        });
    });
    
    var pagination=function(){
    	if($('#datagrid').length>0){
    		$('#datagrid').datagrid({
            	onDblClickRow:function(index,row){
            		$state.go('returnAllowanceInfo', { data: row });
            	}
            });
            
            var datagrid = $('#datagrid').datagrid('getPager');
            datagrid.pagination({
                onSelectPage: function(pageNumber, pageSize) {
                    $scope.query_data.beginNo = pageSize * (pageNumber - 1) == 0 ? 0 : pageSize * (pageNumber - 1) + 1;;
                    $scope.query_data.endNo = pageSize * pageNumber;

                    $.messager.progress({
                        title: 'Please waiting',
                        msg: 'Loading data...',
                        interval: PROGRESS_ACTION_TIMEOUT
                    });

                    ReturnAllowanceManagerService.queryReturnAllowance($scope.query_data, 'returnallowance/query', function(data, response_info) {
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
    	}
    }

    /**
     * 查询折让单
     */
    $scope.query = function() {
    	$scope.istableshow = true;
    	
        $scope.query_data = {
            allowanceNo: $('#allowanceNo').textbox('getValue'),
            status: $('#status').combobox('getValue') === '-1' ? null : $('#status').combobox('getValue'),
            applyDateStart: $('#startTime').datebox('getValue'),
            applyDateEnd: $('#endTime').datebox('getValue'),
            beginNo: 0,
            endNo: 10
        };
        
        if((!folUtil.isNull($scope.query_data.applyDateStart)&& !folUtil.isNull($scope.query_data.applyDateEnd))&&
        		$scope.query_data.applyDateStart>$scope.query_data.applyDateEnd){
        	$.messager.alert('提示','开始日期不能大于结束日期');
        	return;
        }

        $.messager.progress({
            title: 'Please waiting',
            msg: 'Loading data...',
            interval: PROGRESS_ACTION_TIMEOUT
        });

        ReturnAllowanceManagerService.queryReturnAllowance($scope.query_data, 'returnallowance/query', function(data, response_info) {
        	$.messager.progress('close');
        	
        	if($scope.flag){
        		pagination();
        	}

        	$('#datagrid').datagrid('loadData', {total:0,rows:[]});
            $('#datagrid').datagrid('loadData', data);
            $scope.flag=false;
        }, function(response_info) {
            $.messager.progress('close');
            if (response_info.state === STATE.TIMEOUT) {
                console.log('timeout');
                $.messager.alert('提示', '系统超时请稍后访问');
            }
        });
    };

    /**
     * 重置条件
     */
    $scope.reset = function() {
        $('#allowanceNo').textbox('setValue', null);
        $('#status').combobox('select', -1);
        $('#startTime').datebox('setValue', null);
        $('#endTime').datebox('setValue', null);
//        $('#datagrid').datagrid('loadData', {
//            total: 0,
//            rows: []
//        });
    };

    /**
     * 跳转到新增折让单页面
     */
    $scope.createAllowance = function() {
        $state.go('createRetrunAllowance');
    };

    /**
     * 上报折让单
     */
    $scope.report = function() {
        var rows = $('#datagrid').datagrid('getChecked');

        if (folUtil.isNull(rows)||rows.length <= 0) {
            $.messager.alert('提示','请选择一条折让单记录');
            return
        } else if (rows.length > 1) {
            $.messager.alert('提示','不能选择多条折让单记录');
            return
        }
        
            ReturnAllowanceManagerService.report(rows, 'returnallowance/report', function(data, response_info) {
                $.messager.alert('提示','折让单上报成功');
                $scope.query();
            }, function(response_info) {                
                if (response_info.state === STATE.TIMEOUT) {
                    console.log('timeout');
                    $.messager.alert('提示', '系统超时请稍后访问');
                }else{
                	$.messager.alert('提示', '上报折让单失败，原因：'+response_info.message);
                }
            });

    };

    /**
     * 删除折让单
     */
    $scope.cancel = function() {
        var rows = $('#datagrid').datagrid('getChecked');

        if (folUtil.isNull(rows)||rows.length <= 0) {
            $.messager.alert('提示','请选择一条折让单记录');
            return
        } else if (rows.length > 1) {
            $.messager.alert('提示','不能选择多条折让单记录');
            return
        }

            if (confirm("确定作废吗?")) {
                ReturnAllowanceManagerService.delete(rows[0], 'returnallowance/delete', function(data, response_info) {
                    $.messager.alert('提示','折让单作废成功');
                    $scope.query();
                }, function(response_info) {
                    if (response_info.state === STATE.TIMEOUT) {
                        console.log('timeout');
                        $.messager.alert('提示', '系统超时请稍后访问');
                    }else{
                    	$.messager.alert('提示', '作废折让单失败，原因：'+ response_info.message);
                    }
                });
            }
        

    };

    /**
     * 编辑折让单
     */
    $scope.edit = function() {
        var rows = $('#datagrid').datagrid('getChecked');
        
        if (folUtil.isNull(rows)||rows.length <= 0) {
            $.messager.alert('提示','请选择一条折让单记录');
            return;
        } else if (rows.length > 1) {
            $.messager.alert('提示','不能选择多条折让单记录');
            return;
        }else if(rows[0]['status']!==RETURN_ALLOWANCE_STATUE.RETURN_ALLOWANCE_STATUS_DLR_NOT_REPORTED){
        	$.messager.alert('提示','状态不是Dealer待上报不能进行编辑');
            return;
        }
        $state.go('editRetrunAllowance', { data: rows[0] });
    }

    /**
     * 查询折让单明细
     */
    $scope.detail=function(){
    	var rows = $('#datagrid').datagrid('getChecked');
        
        if (folUtil.isNull(rows)||rows.length <= 0) {
            $.messager.alert('提示','请选择一条折让单记录');
            return;
        } else if (rows.length > 1) {
            $.messager.alert('提示','不能选择多条折让单记录');
            return;
        }
        $state.go('returnAllowanceInfo', { data: rows[0] });
    }
}]);