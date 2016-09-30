/**
 * 折让单管理Controller 
 * create by sww
 */
FolController.controller('ReturnAllowanceRejectController', ['$scope', '$rootScope', '$timeout', '$state', '$stateParams', '$q', 'ReturnAllowanceManagerService', function($scope, $rootScope, $timeout, $state, $stateParams, $q, ReturnAllowanceManagerService) {
    var canceller = $q.defer();
    var promise = canceller.promise;

    $scope.isShowXmlInfo = false;
    $scope.openDeatilButtonText = '展开详细信息'
    $scope.xmlInfo;

    /**
     * 初始化数据
     */
    $timeout(function() {
        //折让单明细查询及XML查询
        canceller.resolve();
        promise.then(function() {
            ReturnAllowanceManagerService.deatilQuery({ id: $stateParams.data.id }, 'returnallowance/queryReturnAllowanceById', function(data, response_info) {
                $scope.allowance = {
                    allowanceNo: data.allowanceNo,
                    expressNo: data.expressNo,
                    expressSendTime: data.expressSendTime,
                    companyName: data.companyName,
                    address: data.address,
                    phone: data.phone,
                    bankAccount: data.bankAccount,
                    reqBillNo: data.reqBillNo,
                    sapCode: data.sapCode,
                    customerRemark:data.customerRemark
                };
            }, function(response_info) {
                if (response_info.state === STATE.TIMEOUT) {
                    console.log('timeout');
                    $.messager.alert('提示', '系统超时请稍后访问');
                } else {
                    $.messager.alert('提示', '查询失败，原因：' + response_info.message);
                }
            });
        }).then(function() {
            ReturnAllowanceManagerService.downlandXmlInfo('returnallowance/getXmlInfo?fileId=' + $stateParams.data.filedId, function(data, response_info) {
                $scope.xmlInfo = data;
            }, function(response_info) {
                if (response_info.state === STATE.TIMEOUT) {
                    console.log('timeout');
                    $.messager.alert('提示', '系统超时请稍后访问');
                } else {
                    $.messager.alert('提示', '查询失败，原因：' + response_info.message);
                }
            });
        })

        //退货证明 明细查询
        ReturnAllowanceManagerService.deatilQuery({ returnOrderId: $stateParams.data.claimReturnOrderId }, 'returnallowance/queryReturnOrderDetailByRoId', function(data, response_info) {
            $('#returndatagrid').datagrid('loadData', data);
        }, function(response_info) {
            if (response_info.state === STATE.TIMEOUT) {
                console.log('timeout');
                $.messager.alert('提示', '系统超时请稍后访问');
            } else {
                $.messager.alert('提示', '查询失败，原因：' + response_info.message);
            }
        });

        //电子发票明细查询
        ReturnAllowanceManagerService.deatilQuery({ returnOrderId: $stateParams.data.claimReturnOrderId }, 'returnallowance/queryInvoiceDetailByRoId', function(data, response_info) {
            $('#inovicedatagrid').datagrid('loadData', data);
        }, function(response_info) {
            if (response_info.state === STATE.TIMEOUT) {
                console.log('timeout');
                $.messager.alert('提示', '系统超时请稍后访问');
            } else {
                $.messager.alert('提示', '查询失败，原因：' + response_info.message);
            }
        });
    });

    /**
     * 拒绝折让单
     */
    $scope.reject = function() {
        if (!confirm('确认拒绝？')) {
            return;
        }

        var reject_data = {
            approveMsg: $scope.approveMsg,
            id: $stateParams.data.id,
            returnOrderNo: $stateParams.data.returnOrderNo,
            sapCode: $stateParams.data.sapCode,
            sign:$stateParams.data.sign
        };

        ReturnAllowanceManagerService.reject([reject_data], 'returnallowance/reject', function(data, response_info) {
            $.messager.alert('提示', '拒绝成功');
            $state.go('allowanceScanManager');
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
     * 返回
     */
    $scope.back = function() {
        $state.go('allowanceScanManager');
    };

    /**
     * 打开明细或关闭明细
     */
    $scope.openDeatil = function() {
        if ($scope.openDeatilButtonText === '展开详细信息') {
            $scope.$apply(function() {
                $scope.isShowXmlInfo = true;
                $scope.openDeatilButtonText = '关闭详细信息'
            });
            initXmlData($scope.xmlInfo);
        } else {
            $scope.$apply(function() {
                $scope.isShowXmlInfo = false;
                $scope.openDeatilButtonText = '展开详细信息'
            });

        }

    };

    /**
     * 读取XML数据并初始化到页面上
     */
    var initXmlData = function(data) {
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
    };

}]);