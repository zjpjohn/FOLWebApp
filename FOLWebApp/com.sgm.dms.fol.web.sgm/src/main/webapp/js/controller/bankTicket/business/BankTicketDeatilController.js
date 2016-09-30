/**
 * 银票明细controller
 */
FolController.controller('BankTicketDeatilController', ['$scope', '$timeout', '$q', '$state', 'BankTicketDeatilService', 'BankInfoService', 'CodeService', function($scope, $timeout, $q, $state, BankTicketDeatilService, BankInfoService, CodeService) {
    $scope.istableshow = false;

    $timeout(function() {
        if ($('#datagrid').length > 0) {
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

                    BankTicketDeatilService.query($scope.query_data, 'bankTicket/deatil/query', function(data, response_info) {
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

        $.messager.progress({
            title: 'Please waiting',
            msg: 'Loading data...',
            interval: PROGRESS_ACTION_TIMEOUT
        });

        /**
         * 查询所有银行
         */
        BankInfoService.findAll('bankTicket/bankInfo/findAll', function(data, response_info) {
            $.messager.progress('close');

            if (!folUtil.isNull(data)) {
                var bank = [];
                bank.push({ bankAbbr: '-1', bankChName: '请选择' });
                $.each(data.rows, function(i, val) {
                    bank.push(val);
                });
                $timeout(function() {
                    $('#bankId').combobox('loadData', bank);
                    $('#bankId').combobox('select', bank[0]['bankAbbr']);
                });
            }

        }, function(response_info) {
            $.messager.progress('close');
            if (response_info.state === STATE.TIMEOUT) {
                console.log('timeout');
                $.messager.alert('提示', '系统超时请稍后访问');
            }
        });

        var documentNumber = [];
        documentNumber.push({ value: '-1', displayName: '请选择' });
        documentNumber.push({ value: '0', displayName: '银票已到期' });
        documentNumber.push({ value: '1', displayName: '银票未到期' });
        documentNumber.push({ value: '2', displayName: '银票已背书' });
        documentNumber.push({ value: '3', displayName: '银票已贴现' });

        $timeout(function() {
            $('#bankTicketStatus').combobox('loadData', documentNumber);
            $('#bankTicketStatus').combobox('select', documentNumber[0]['value']);
        });

        /**
         * 查询类型
         */
        var fields = ['渠道类型', '品牌'];
        CodeService.findCodeTypeNames(fields, 'code/findCodeTypeNames', function(data) {
            $.messager.progress('close');

            var typesId = ['#dealerType', '#brandId'];

            $timeout(function() {
                for (var i = 0; i < data.length; i++) {
                    if (!folUtil.isNull(data[i])) {
                        $(typesId[i]).combobox('loadData', data[i]);
                        $(typesId[i]).combobox('select', data[i][0]['code']);
                    }
                }
            });

        }, function(response_info) {
            $.messager.progress('close');
            if (response_info.state === STATE.TIMEOUT) {
                console.log('timeout');
                $.messager.alert('提示', '系统超时请稍后访问');
            }
        });
    });

    /**
     * 根据条件查询银票明细
     */
    $scope.query = function() {
        $scope.istableshow = true;

        $scope.query_data = {
            dealerType: folUtil.getComboBoxDataById('dealerType'),
            brandId: folUtil.getComboBoxDataById('brandId'),
            bankId: folUtil.getComboBoxDataById('bankId'),
            sapCode: $('#sapCode').textbox('getValue'),
            dealerName: $('#dealerName').textbox('getValue'),
            documentNumber: $('#documentNumber').textbox('getValue'),
            acceptanceNumber: $('#acceptanceNumber').textbox('getValue'),
            issueDate: $('#issueDate').datebox('getValue'),
            expirationDate: $('#expirationDate').datebox('getValue'),
            bankTicketStatus: folUtil.getComboBoxDataById('bankTicketStatus'),
            beginNo: 0,
            endNo: 10
        };

        $.messager.progress({
            title: 'Please waiting',
            msg: 'Loading data...',
            interval: PROGRESS_ACTION_TIMEOUT
        });

        BankTicketDeatilService.query($scope.query_data, 'bankTicket/deatil/query', function(data, response_info) {
            $.messager.progress('close');
            $('#datagrid').datagrid('loadData', { total: 0, rows: [] });
            $('#datagrid').datagrid('loadData', data);
        }, function(response_info) {
            $.messager.progress('close');
            if (response_info.state === STATE.TIMEOUT) {
                console.log('timeout');
                $.messager.alert('提示', '系统超时请稍后访问');
            }
        });
    };
}]);