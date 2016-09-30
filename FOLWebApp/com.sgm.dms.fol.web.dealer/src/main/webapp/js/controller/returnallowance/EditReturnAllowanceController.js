FolController.controller('EditReturnAllowanceController', ['$scope', '$timeout', '$state', '$stateParams', '$window', '$rootScope', '$q','$cookies', 'ReturnAllowanceManagerService', 'uploadFileService', function($scope, $timeout, $state, $stateParams, $window, $rootScope, $q,$cookies, ReturnAllowanceManagerService, uploadFileService) {
    var canceller = $q.defer();
    var promise = canceller.promise;

    /**
     *  创建监听事件
     */
    $timeout(function() {
        $('#deatilWindow').panel({
            onBeforeOpen: function() {
                pagination();
            }
        });

        ReturnAllowanceManagerService.queryReturnAllowance({ id: $stateParams.data.id }, 'returnallowance/toAddOrUpdate', function(data, response_info) {
            initPageData(data);
        }, function(response_info) {
            if (response_info.state === STATE.TIMEOUT) {
                console.log('timeout');
                $.messager.alert('提示', '系统超时请稍后访问');
            }
        });
    });

    /**
     * 读取XML数据并初始化到页面上
     */
    var initXmlData = function(data) {
        if (folUtil.isNull(data)) {
            $('#ReqBillNo').textbox('setValue', null);
            $('#InvKind').textbox('setValue', null);
            $('#Szlb').textbox('setValue', null);
            $('#TypeCode').textbox('setValue', null);
            $('#InvNo').textbox('setValue', null);
            $('#date').textbox('setValue', null);
            $('#BuyerName').textbox('setValue', null);
            $('#BuyTaxCode').textbox('setValue', null);
            $('#SellerName').textbox('setValue', null);
            $('#SellTaxCode').textbox('setValue', null);
            $('#Amount').textbox('setValue', null);
            $('#TaxRate').textbox('setValue', null);
            $('#Tax').textbox('setValue', null);
            $('#ReqMemo').textbox('setValue', null);
            $('#Reason').textbox('setValue', null);
            $('#Operator').textbox('setValue', null);

            $('#commoditydatagrid').datagrid('loadData', { total: 0, rows: [] });
        } else {
            $('#ReqBillNo').textbox('setValue', data.ReqBillNo);
            $('#InvKind').textbox('setValue', (data.InvKind === '0' || data.InvKind === '1') ? InvKind.specialInvoice : data.InvKind);
            $('#Szlb').textbox('setValue', data.Szlb);
            $('#TypeCode').textbox('setValue', data.TypeCode);
            $('#InvNo').textbox('setValue', data.InvNo);
            $('#date').textbox('setValue', data.date);
            $('#BuyerName').textbox('setValue', data.BuyerName);
            $('#BuyTaxCode').textbox('setValue', data.BuyTaxCode);
            $('#SellerName').textbox('setValue', data.SellerName);
            $('#SellTaxCode').textbox('setValue', data.SellTaxCode);
            $('#Amount').textbox('setValue', data.Amount);
            $('#TaxRate').textbox('setValue', data.TaxRate);
            $('#Tax').textbox('setValue', data.Tax);
            $('#ReqMemo').textbox('setValue', data.ReqMemo);
            $('#Reason').textbox('setValue', data.Reason);
            $('#Operator').textbox('setValue', data.Operator);

            $('#commoditydatagrid').datagrid('loadData', { total: data.RedInvReqBillMx.length, rows: data.RedInvReqBillMx });
        }
    };

    $scope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
        if (!folUtil.isNull(fromState['name']) && fromState['name'] !== 'uploadFilePage') {
            initXmlData(null);
            uploadFileService.setFile(null);
        } else {
            if (!folUtil.isNull(uploadFileService.getFile())) {
                var fileId = uploadFileService.getFile().fileId;
                ReturnAllowanceManagerService.downloadXmlFile('returnallowance/getXmlInfo?fileId=' + fileId, function(data, response_info) {
                    initXmlData(data);
                }, function(response_info) {
                    if (response_info.state === STATE.TIMEOUT) {
                        console.log('timeout');
                        $.messager.alert('提示', '系统超时请稍后访问');
                    }
                });
            }
        }
    });

    /**
     * 导入XML文件
     */
    $scope.importXmlFile = function() {
        var returnAllowance = {
            allowanceNo: $('#allowanceNo').textbox('getValue'),
            reqBillNo: $('#reqBillNo').textbox('getValue'),
            expressNo: $('#expressNo').textbox('getValue'),
            postTime: $('#postTime').datebox('getValue'),
            telphone: $('#telphone').textbox('getValue'),
            BankAccount: $('#BankAccount').textbox('getValue'),
            companyAddress: $('#companyAddress').textbox('getValue'),
            remark: $('#remark').textbox('getValue'),
            returnOrder: $('#returndatagrid').datagrid('getRows')
        };
        ReturnAllowanceManagerService.setReturnAllowance(returnAllowance);
        $state.go('uploadFilePage', { pageName: 'returnallowance', data: null });
    }

    /**
     * 保存草稿折让单
     */
    $scope.save = function() {
        var rows = $('#returndatagrid').datagrid('getRows');
        if (rows.length <= 0) {
            $.messager.alert('提示', '请添加一条退货证明');
            return;
        } else if (rows.length > 1) {
            $.messager.alert('提示', '只能有一条退货证明');
            return;
        }

        if (folUtil.isNull($stateParams.data.filedId) && folUtil.isNull(uploadFileService.getFile())) {
            $.messager.alert('提示', '请导入一条XML文件');
            return;
        }

        var data = {
            filedId: folUtil.isNull(uploadFileService.getFile()) ? $stateParams.data.filedId : uploadFileService.getFile().fileId,
            allowanceNo: $('#allowanceNo').textbox('getValue'),
            reqBillNo: $('#reqBillNo').textbox('getValue'),
            expressNo: $('#expressNo').textbox('getValue'),
            expressSendTime: $('#postTime').datebox('getValue'),
            phone: $('#telphone').textbox('getValue'),
            bankAccount: $('#BankAccount').textbox('getValue'),
            address: $('#companyAddress').textbox('getValue'),
            customerRemark: $('#remark').textbox('getValue'),
            id: $stateParams.data.id,
            returnOrderNo: rows[0].returnOrderNo,
            returnOrderInfo:rows[0]
        };

        ReturnAllowanceManagerService.save(data, 'returnallowance/addOrUpdate', function(data, response_info) {
            $.messager.alert('提示', '保存折让单成功');
        }, function(response_info) {
            if (response_info.state === STATE.TIMEOUT) {
                console.log('timeout');
                $.messager.alert('提示', '系统超时请稍后访问');
            } else {
                $.messager.alert('提示', '保存折让单失败，原因：'+response_info.message);
            }
        });
    };

    /**
     * 上报草稿折让单
     */
    $scope.report = function() {
        var data = {
            id: $stateParams.data.id
        };

        ReturnAllowanceManagerService.report([data], 'returnallowance/report', function(data, response_info) {
            $.messager.alert('提示', '上报折让单成功');
            $state.go('retrunAllowanceManager');
        }, function(response_info) {
            if (response_info.state === STATE.TIMEOUT) {
                console.log('timeout');
                $.messager.alert('提示', '系统超时请稍后访问');
            } else {
                $.messager.alert('提示', '上报折让单失败，原因：'+ response_info.message);
            }
        });
    };

    /**
     * 作废折让单
     */
    $scope.delete = function() {
    	var returnOrder=$('#returndatagrid').datagrid('getRows');
    	
        var data = {
            id: $stateParams.data.id,
            returnOrderNo:returnOrder[0]['returnOrderNo']
        };

        ReturnAllowanceManagerService.delete(data, 'returnallowance/delete', function(data, response_info) {
            $.messager.alert('提示', '作废折让单成功');
            $state.go('retrunAllowanceManager');
        }, function(response_info) {
            if (response_info.state === STATE.TIMEOUT) {
                console.log('timeout');
                $.messager.alert('提示', '系统超时请稍后访问');
            } else {
                $.messager.alert('提示', '作废折让单失败，原因：'+ response_info.message);
            }
        });
    };

    /**
     * 返回折让单查询管理页面
     */
    $scope.cancel = function() {
        // $window.history.back();
        $state.go('retrunAllowanceManager');
    };

    /**
     * 打开查询退货证明窗口
     */
    $scope.addReturn = function() {
        canceller.resolve();
        promise.then(function() {
            //显示WINDOW
            $('#deatilWindow').window('open');
        }).then(function() {
            //默认查询一次
        	$('#deatilsdatagrid').datagrid('loadData', { total: 0, rows: [] });
            queryReturnOrder();
        });
    };

    /**
     * 删除退货证明
     */
    $scope.removeReturn = function() {
        $('#returndatagrid').datagrid('loadData', { total: 0, rows: [] });
    };

    /**
     * 查询退货证明
     */
    var queryReturnOrder = function() {
        $scope.query_data = {
            beginNo: 0,
            endNo: 10
        };

        $.messager.progress({
            title: 'Please waiting',
            msg: 'Loading data...',
            interval: PROGRESS_ACTION_TIMEOUT
        });

        ReturnAllowanceManagerService.queryReturnOrder($scope.query_data, 'returnallowance/getReturnOrders', function(data, response_info) {
            $.messager.progress('close');
            $('#deatilsdatagrid').datagrid('loadData', { total: 0, rows: [] });
            $('#deatilsdatagrid').datagrid('loadData', data);
        }, function(response_info) {
            $.messager.progress('close');
            if (response_info.state === STATE.TIMEOUT) {
                console.log('timeout');
                $.messager.alert('提示', '系统超时请稍后访问');
            }
        });
    };

    /**
     * 返回
     */
    $scope.returnCancel = function() {
        //关闭WINDOW
        $('#deatilWindow').window('close');
    };

    /**
     * 选择退货证明
     */
    $scope.submit = function() {
        var rows = $('#deatilsdatagrid').datagrid('getSelections');
        if (rows.length <= 0 || rows.length > 1) {
            $.messager.alert('提示', '请选择一条记录');
            return;
        }

        $('#returndatagrid').datagrid('loadData', { total: 0, rows: [] });
        $('#returndatagrid').datagrid('loadData', { total: rows.length, rows: rows });

        //关闭WINDOW
        $('#deatilWindow').window('close');
    };
    
    /**
     * 查询明细
     */
    $scope.detail=function(){
    	var rows = $('#deatilsdatagrid').datagrid('getSelections');
        if (rows.length <= 0 || rows.length > 1) {
            $.messager.alert('提示', '请选择一条记录');
            return;
        }
        
        var returnOrderNo=rows[0].returnOrderNo;
        var options='?aType=3&dpPositionCode='+$cookies.COOKIE_DP_POSITION_CODE+'&ascCode='+$cookies.COOKIE_FOL_SAP_CODE+'&parent_menuId=2200_220030_5&rtNo='+returnOrderNo

        $window.open(POL_WORKER_SERVER_URL+options);
    }

    var initPageData = function(response_data) {
        $('#allowanceNo').textbox('setValue', response_data.allowanceNo);
        $('#reqBillNo').textbox('setValue', response_data.reqBillNo);
        $('#expressNo').textbox('setValue', response_data.expressNo);
        $('#postTime').textbox('setValue', response_data.expressSendTime);
        $('#telphone').textbox('setValue', response_data.phone);
        $('#BankAccount').textbox('setValue', response_data.bankAccount);
        $('#companyAddress').textbox('setValue', response_data.address);
        $('#remark').textbox('setValue', response_data.customerRemark);
        $('#returndatagrid').datagrid('loadData', { total: 1, rows: [response_data.returnOrderInfo] });

        ReturnAllowanceManagerService.downloadXmlFile('returnallowance/getXmlInfo?fileId=' + response_data.filedId, function(data, response_info) {
            $('#ReqBillNo').textbox('setValue', data.ReqBillNo);
            $('#InvKind').textbox('setValue', (data.InvKind === '0' || data.InvKind === '1') ? InvKind.specialInvoice : data.InvKind);
            $('#Szlb').textbox('setValue', data.Szlb);
            $('#TypeCode').textbox('setValue', data.TypeCode);
            $('#InvNo').textbox('setValue', data.InvNo);
            $('#date').textbox('setValue', data.date);
            $('#BuyerName').textbox('setValue', data.BuyerName);
            $('#BuyTaxCode').textbox('setValue', data.BuyTaxCode);
            $('#SellerName').textbox('setValue', data.SellerName);
            $('#SellTaxCode').textbox('setValue', data.SellTaxCode);
            $('#Amount').textbox('setValue', data.Amount);
            $('#TaxRate').textbox('setValue', data.TaxRate);
            $('#Tax').textbox('setValue', data.Tax);
            $('#ReqMemo').textbox('setValue', data.ReqMemo);
            $('#Reason').textbox('setValue', data.Reason);
            $('#Operator').textbox('setValue', data.Operator);
            $('#commoditydatagrid').datagrid('loadData', { total: data.RedInvReqBillMx.length, rows: data.RedInvReqBillMx })
        }, function(response_info) {
            if (response_info.state === STATE.TIMEOUT) {
                console.log('timeout');
                $.messager.alert('提示', '系统超时请稍后访问');
            }
        });
    };

    /**
     * 分页
     */
    var pagination = function() {
        if ($('#deatilsdatagrid').length <= 0) {
            return;
        }

        var datagrid = $('#deatilsdatagrid').datagrid('getPager');
        datagrid.pagination({
            onSelectPage: function(pageNumber, pageSize) {
                $scope.query_data.beginNo = pageSize * (pageNumber - 1) == 0 ? 0 : pageSize * (pageNumber - 1) + 1;
                $scope.query_data.endNo = pageSize * pageNumber;

                $.messager.progress({
                    title: 'Please waiting',
                    msg: 'Loading data...',
                    interval: PROGRESS_ACTION_TIMEOUT
                });

                ReturnAllowanceManagerService.queryReturnOrder($scope.query_data, 'returnallowance/getReturnOrders', function(data, response_info) {
                    $.messager.progress('close');
                    $('#deatilsdatagrid').datagrid('loadData', data);
                }, function(response_info) {
                    $.messager.progress('close');
                    if (response_info.state === STATE.TIMEOUT) {
                        console.log('timeout');
                        $.messager.alert('提示', '系统超时请稍后访问');
                    }
                });
            }
        });
    };
}]);