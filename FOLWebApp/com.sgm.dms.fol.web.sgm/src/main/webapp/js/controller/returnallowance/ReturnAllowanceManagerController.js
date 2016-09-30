/**
 * 折让单管理Controller 
 * create by sww
 */
FolController.controller('ReturnAllowanceManagerController', ['$scope', '$rootScope', '$timeout', '$state', '$stateParams', '$q', '$window' ,'ReturnAllowanceManagerService', 'CodeService', function($scope, $rootScope, $timeout, $state, $stateParams, $q, $window , ReturnAllowanceManagerService, CodeService) {
    $scope.istableshow = false;
    $scope.flag=true

    var canceller = $q.defer();
    var promise = canceller.promise;

    /**
     * 初始化数据
     */
    $timeout(function() {     
        $scope.setData();
    });

    var pagination=function(){
    	if($('#datagrid').length>0){

	        $('#datagrid').datagrid({
	        	onDblClickRow:function(index,row){
	        		$state.go('allowanceReject', { data: row });
	        	}
	        });
	        
	        var datagrid = $('#datagrid').datagrid('getPager');
	        datagrid.pagination({
	            onSelectPage: function(pageNumber, pageSize) {
	            	$rootScope.return_allowance_query_data.beginNo = pageSize * (pageNumber - 1) == 0 ? 0 : pageSize * (pageNumber - 1) + 1;;
	            	$rootScope.return_allowance_query_data.endNo = pageSize * pageNumber;
	
	                $.messager.progress({
	                    title: 'Please waiting',
	                    msg: 'Loading data...',
	                    interval: PROGRESS_ACTION_TIMEOUT
	                });
	
	                ReturnAllowanceManagerService.query($rootScope.return_allowance_query_data, 'returnallowance/query', function(data, response_info) {
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
//    var endEditing = function(tableid) {
//        var rows = $('#' + tableid).datagrid('getRows');
//        $.each(rows, function(i, val) {
//            if ($('#datagrid').datagrid('validateRow', i)) {
//                $('#datagrid').datagrid('endEdit', i);
//            }
//        });
//    };

    /**
     * 查询折让单
     */
    $scope.query = function() {
        $scope.istableshow = true;
        $rootScope.return_allowance_query_data = {
            sapCode: $('#sapCode').textbox('getValue'),
            dealerName: $('#dealerName').textbox('getValue'),
            allowanceNo: $('#allowanceNo').textbox('getValue'),
            expressNo: $('#expressNo').textbox('getValue'),
            status: $('#status').combobox('getValue') === '-1' ? null : $('#status').combobox('getValue'),
            approveDateStart: $('#startTime').datebox('getValue'),
            approveDateEnd: $('#endTime').datebox('getValue'),
            companyCode: [],
            beginNo: 0,
            endNo: 10
        }
        
        if((!folUtil.isNull($rootScope.return_allowance_query_data.approveDateStart)&& !folUtil.isNull($rootScope.return_allowance_query_data.approveDateEnd))&&
        		$rootScope.return_allowance_query_data.approveDateStart>$rootScope.return_allowance_query_data.approveDateEnd){
        	$.messager.alert('提示','开始日期不能大于结束日期');
        	return;
        }

        if (!folUtil.isNull($('#shanghai').attr('checked'))) {
        	$rootScope.return_allowance_query_data.companyCode.push(COMPANY_CODE.SH);
        }
        if (!folUtil.isNull($('#tianjin').attr('checked'))) {
        	$rootScope.return_allowance_query_data.companyCode.push(COMPANY_CODE.TJ);
        }
        if (!folUtil.isNull($('#chongqin').attr('checked'))) {
        	$rootScope.return_allowance_query_data.companyCode.push(COMPANY_CODE.CQ);
        }
//        if(!folUtil.isNull($('#wuhan').attr('checked'))){
//        	$scope.query_data.companyCode.push(COMPANY_CODE.WH);
//        }
        

        ReturnAllowanceManagerService.query($rootScope.return_allowance_query_data, 'returnallowance/query', function(data, response_info) {
            $.messager.progress('close');
	        
            if($scope.flag){
//            	$('#datagrid').datagrid().datagrid('enableCellEditing');
            	pagination();
            }
            //一定要写在关闭Editing和分页之前
            $('#datagrid').datagrid('loadData', { rows: [], total: 0 });
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
     * 重置
     */
    $scope.reset = function() {
        $('#sapCode').textbox('setValue', null);
        $('#dealerName').textbox('setValue', null);
        $('#allowanceNo').textbox('setValue', null);
        $('#expressNo').textbox('setValue', null);
        $('#status').combobox('select', -1);
        $('#startTime').datebox('setValue', null);
        $('#endTime').datebox('setValue', null);

        $('#shanghai').attr('checked', false);
        $('#tianjin').attr('checked', false);
        $('#chongqin').attr('checked', false);
//        $('#wuhan').attr('checked', false);
    };

    /**
     * 查找经销商
     */
    $scope.findDealer = function() {
        $state.go('findDealer', { stateName: 'allowanceScanManager' });
    };

    /**
     * 开票
     */
    $scope.report = function() {
        var rows = $('#datagrid').datagrid('getChecked');
        if (rows.length <= 0) {
            $.messager.alert('提示', '请选择一条记录');
            return;
        }

        if (!confirm('确认开票？')) {
            return;
        }

        //关闭编辑
//        endEditing('datagrid');

            ReturnAllowanceManagerService.report(rows, 'returnallowance/billing', function(data, response_info) {
                $.messager.alert('提示', '开票成功');
                $scope.query();
            }, function(response_info) {
                if (response_info.state === STATE.TIMEOUT) {
                    console.log('timeout');
                    $.messager.alert('提示', '系统超时请稍后访问');
                } else {
                    $.messager.alert('提示', '开票失败，原因：' + response_info.message);
                }
            });

    };

    /**
     * 拒绝
     */
    $scope.reject = function() {
        var rows = $('#datagrid').datagrid('getChecked');
        if (rows.length <= 0) {
            $.messager.alert('提示', '请选择一条记录');
            return;
        }

        if (!confirm('确认拒绝？')) {
            return;
        }

        //关闭编辑
//        endEditing('datagrid');

            ReturnAllowanceManagerService.reject(rows, 'returnallowance/reject', function(data, response_info) {
                $.messager.alert('提示', '拒绝成功');
                $scope.query();
            }, function(response_info) {
                if (response_info.state === STATE.TIMEOUT) {
                    console.log('timeout');
                    $.messager.alert('提示', '系统超时请稍后访问');
                } else {
                    $.messager.alert('提示', '拒绝失败，原因：' + response_info.message);
                }
            });

    };

    /**
     * 导出
     */
    $scope.exportExcel = function() {
        if (folUtil.isNull($rootScope.return_allowance_query_data)) {
            $.messager.alert('提示', '请先查询一次再导出');
            return;
        }

        ReturnAllowanceManagerService.getToken('services/tokens', function(data, response_info) {
            var companyCodeList = [];

            if (!folUtil.isNull($('#shanghai').attr('checked'))) {
                companyCodeList.push(COMPANY_CODE.SH);
            }
            if (!folUtil.isNull($('#tianjin').attr('checked'))) {
                companyCodeList.push(COMPANY_CODE.TJ);
            }
            if (!folUtil.isNull($('#chongqin').attr('checked'))) {
                companyCodeList.push(COMPANY_CODE.CQ);
            }
//            if (!folUtil.isNull($('#wh').attr('checked'))) {
//                companyCodeList.push(COMPANY_CODE.WH);
//            }

            var companyCodeStr = '';
            angular.forEach(companyCodeList, function(value,index) {
            	companyCodeStr += value + ',';
            });

            var url = 'returnallowance/exportListExcel';
            var condition = '?sapCode=' + $('#sapCode').textbox('getValue') + '&dealerName=' + encodeURI(encodeURI($('#dealerName').textbox('getValue'))) + '&allowanceNo=' + $('#allowanceNo').textbox('getValue') + '&expressNo=' + $('#expressNo').textbox('getValue') + '&status=' + ($('#status').combobox('getValue') === '-1' ? null : $('#status').combobox('getValue')) + '&approveDateStart=' + $('#startTime').datebox('getValue') + '&approveDateEnd=' + $('#endTime').datebox('getValue') + '&companyCodeStr=' + companyCodeStr.substr(0, companyCodeStr.length - 1);
            
            $window.open(url + condition);
        }, function(response_info) {
            $.messager.progress('close');
            if (response_info.state === STATE.TIMEOUT) {
                console.log('timeout');
                $.messager.alert('提示', '系统超时请稍后访问');
            }
        });
    };

    /**
     * 批量导出开票文件
     */
    $scope.exportTxt = function() {
        var rows = $('#datagrid').datagrid('getChecked');
        if (rows.length <= 0) {
            $.messager.alert('提示', '请选择一条记录');
            return;
        }

        var allowanceIds = '';
        angular.forEach(rows, function(value,index) {
            allowanceIds += value.id + ',';
        });
        
        ReturnAllowanceManagerService.getToken('services/tokens', function(data, response_info) {
            var url = 'returnallowance/exportBillingFile';
            var condition = '?ids=' + allowanceIds.substr(0, allowanceIds.length - 1);
            $window.open(url + condition);
        }, function(response_info) {
            $.messager.progress('close');
            if (response_info.state === STATE.TIMEOUT) {
                console.log('timeout');
                $.messager.alert('提示', '系统超时请稍后访问');
            }
        });
    };

    /**
     * 查看折让单明细并提供拒绝功能
     */
    $scope.allowanceDeatil = function() {
        var rows = $('#datagrid').datagrid('getChecked');
        if (rows.length <= 0) {
            $.messager.alert('提示', '请选择一条记录');
            return;
        } else if (rows.length > 1) {
            $.messager.alert('提示', '不能选择多条记录');
            return;
        }

        $state.go('allowanceReject', { data: rows[0] });
    };
    
    $scope.setData=function(){
    	if(!folUtil.isNull($rootScope.return_allowance_query_data)){

    		if(!folUtil.isNull($rootScope.return_allowance_query_data.sapCode)){
    			$('#sapCode').textbox('setValue', $rootScope.return_allowance_query_data.sapCode);
    		}
    		
    		if(!folUtil.isNull($rootScope.return_allowance_query_data.dealerName)){
    			$('#dealerName').textbox('setValue', $rootScope.return_allowance_query_data.dealerName);
    		}
    		
    		if(!folUtil.isNull($rootScope.return_allowance_query_data.allowanceNo)){
    			$('#allowanceNo').textbox('setValue', $rootScope.return_allowance_query_data.allowanceNo);    	        
    		}
    		
    		if(!folUtil.isNull($rootScope.return_allowance_query_data.expressNo)){
    			$('#expressNo').textbox('setValue', $rootScope.return_allowance_query_data.expressNo);
    		}
    		
//    		if(!folUtil.isNull($rootScope.return_allowance_query_data.status)){
//    	        $('#status').combobox('select', $rootScope.return_allowance_query_data.status);	        
//    		}
    		
    		if(!folUtil.isNull($rootScope.return_allowance_query_data.approveDateStart)){
    			$('#startTime').datebox('setValue', $rootScope.return_allowance_query_data.approveDateStart);    	        
    		}
    		
    		if(!folUtil.isNull($rootScope.return_allowance_query_data.approveDateEnd)){
    			$('#endTime').datebox('setValue', $rootScope.return_allowance_query_data.approveDateEnd);
    		}
    		
    		if(!folUtil.isNull($rootScope.return_allowance_query_data.companyCode)&&$rootScope.return_allowance_query_data.companyCode.length>0){
    	        angular.forEach($rootScope.return_allowance_query_data.companyCode,function(value,index){
    	        	if(value===COMPANY_CODE.SH){
    	        		$('#shanghai').attr('checked', true);
    	        	}else if(value===COMPANY_CODE.CQ){
    	        		$('#chongqin').attr('checked', true);
    	        	}else if(value===COMPANY_CODE.TJ){
    	        		$('#tianjin').attr('checked', true);
    	        	}
    	        });
    		}
    		
    		if (!folUtil.isNull($stateParams.data)) {
                $('#sapCode').textbox('setValue', $stateParams.data.sapCodes);
            }

            $.messager.progress({
                title: 'Please waiting',
                msg: 'Loading data...',
                interval: PROGRESS_ACTION_TIMEOUT
            });

            var fields = ['折让单处理状态'];
            CodeService.findCodeTypeNames(fields, 'code/findCodeTypeNames', function(data) {
                $.messager.progress('close');

                var typesId = ['#status'];

                if (!folUtil.isNull(data[0])) {
                	
                	var finalvalue=[];
                	angular.forEach(data[0],function(value,index){
                		if(value.code!='3'&&value.code!='1'){
                			finalvalue.push(value);
                		}
                	});
                	$('#status').combobox('loadData', finalvalue);
                	
                    if(!folUtil.isNull($rootScope.return_allowance_query_data.status)){
                    	$('#status').combobox('select', $rootScope.return_allowance_query_data.status);	        
                    }
                    
                    $scope.query();
                }
                
            }, function(response_info) {
                $.messager.progress('close');
                if (response_info.state === STATE.TIMEOUT) {
                    console.log('timeout');
                    $.messager.alert('提示', '系统超时请稍后访问');
                }
            });
            
            $('#datagrid').datagrid('loadData', { rows: [], total: 0 });
    	}else{
    		if (!folUtil.isNull($stateParams.data)) {
                $('#sapCode').textbox('setValue', $stateParams.data.sapCodes);
            }

            $.messager.progress({
                title: 'Please waiting',
                msg: 'Loading data...',
                interval: PROGRESS_ACTION_TIMEOUT
            });

            var fields = ['折让单处理状态'];
            CodeService.findCodeTypeNames(fields, 'code/findCodeTypeNames', function(data) {
                $.messager.progress('close');

                var typesId = ['#status'];

                if (!folUtil.isNull(data[0])) {
                	
                	var finalvalue=[];
                	angular.forEach(data[0],function(value,index){
                		if(value.code!='3'&&value.code!='1'){
                			finalvalue.push(value);
                		}
                	});
                	$('#status').combobox('loadData', finalvalue);
                    $('#status').combobox('select', data[0][0]['code']);
                }
                
            }, function(response_info) {
                $.messager.progress('close');
                if (response_info.state === STATE.TIMEOUT) {
                    console.log('timeout');
                    $.messager.alert('提示', '系统超时请稍后访问');
                }
            });
    	}
    };

    //    $scope.reject = function() {
    //    	$state.go('allowanceReject');
    //    };

}]);