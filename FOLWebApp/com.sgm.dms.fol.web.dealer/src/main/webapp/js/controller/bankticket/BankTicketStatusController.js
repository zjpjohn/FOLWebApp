/**
 * 银票状态controller
 */
FolController.controller('BankTicketStatusController', ['$scope', '$timeout', '$q', '$state', 'BankTicketDeatilService', 'BankInfoService', function($scope, $timeout, $q, $state, BankTicketDeatilService, BankInfoService) {
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

                    BankTicketDeatilService.query($scope.query_data, 'bankTicket/status/query', function(data, response_info) {
                        $.messager.progress('close');
                        $('#datagrid').datagrid('loadData', data);
                    }, function(data, response_info) {
                        $.messager.progress('close');
                        if (response_info.state === STATE.TIMEOUT) {
                            console.log('timeout');
                            $.messager.alert('提示', '系统超时请稍后访问');
                        }
                        console.log("error:" + data);
                    });
                }
            });
        }

        $.messager.progress({
            title: 'Please waiting',
            msg: 'Loading data...',
            interval: PROGRESS_ACTION_TIMEOUT
        });

        BankInfoService.findAll('bankTicket/bankInfo/findAll', function(data, response_info) {
            $.messager.progress('close');

            if (!folUtil.isNull(data.rows)) {
                var bank = new Array();
                bank.push({ id: '-1', bankChName: '请选择' });
                $.each(data.rows, function(i, val) {
                    bank.push(val);
                });
                $timeout(function() {
                    $('#bankId').combobox('loadData', bank);
                    $('#bankId').combobox('select', bank[0]['bankAbbr']);
                });
            }

        }, function(data, response_info) {
            $.messager.progress('close');
            if (response_info.state === STATE.TIMEOUT) {
                console.log('timeout');
                $.messager.alert('提示', '系统超时请稍后访问');
            }
            console.log("error:" + data);
        });

        var documentNumber = new Array();
        documentNumber.push({ value: '-1', displayName: '请选择' });
        documentNumber.push({ value: '0', displayName: '银票已到期' });
        documentNumber.push({ value: '1', displayName: '银票未到期' });
        documentNumber.push({ value: '2', displayName: '银票已背书' });
        documentNumber.push({ value: '3', displayName: '银票已贴现' });

        $timeout(function() {
            $('#bankTicketStatus').combobox('loadData', documentNumber);
            $('#bankTicketStatus').combobox('select', documentNumber[0]['value']);
        });
    });

    $scope.query = function() {
        $scope.istableshow = true;

        $scope.query_data = {
            bankId: folUtil.isNull(folUtil.getComboBoxDataById('bankId')) ? null : folUtil.getComboBoxDataById('bankId'),
            acceptanceNumber: $('#acceptanceNumber').textbox('getValue'),
            startIssueDate: $('#startIssueDate').datebox('getValue'),
            endIssueDate: $('#endIssueDate').datebox('getValue'),
            bankTicketStatus: folUtil.getComboBoxDataById('bankTicketStatus'),
            beginNo: 0,
            endNo: 10
        };

        $.messager.progress({
            title: 'Please waiting',
            msg: 'Loading data...',
            interval: PROGRESS_ACTION_TIMEOUT
        });

        BankTicketDeatilService.query($scope.query_data, 'bankTicket/status/query', function(data, response_info) {
            $.messager.progress('close');
            $('#datagrid').datagrid('loadData', data);
        }, function(data, response_info) {
            $.messager.progress('close');
            if (response_info.state === STATE.TIMEOUT) {
                console.log('timeout');
                $.messager.alert('提示', '系统超时请稍后访问');
            }
            console.log("error:" + data);
        });
    };
}]);