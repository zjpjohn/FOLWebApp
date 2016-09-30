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
/**
 * 文件上传controller
 */
FolController.controller('uploadFileController',['$rootScope','$scope','$window','$timeout','$stateParams','uploadFileService','CodeService',function($rootScope,$scope,$window,$timeout,$stateParams,uploadFileService,CodeService){
	$scope.pageName=$stateParams.pageName;
	
	/**
	 * 初始化数据
	 */
	$timeout(setInitData());
	
	/**
	 * 取消
	 */
	$scope.cancel=function(){
		$window.history.back();
	};
	
	/**
	 * 下载文件
	 */
	$scope.openDownloadFile=function(){
		uploadFileService.openDownloadFile('services/tokens',function(data){
			$.each(data, function(i, field) {
				if (i == "token") {
					alert($('#result').val().filename);
//					$("#div1").append(Date() + "---get token---" + field+"<br/>");
					$window.open('http://dapi.saic-gm.com/fileservice/html/download.html?token='+field+'&fileId='+$('#result').val(), '_blank',	'height=300,width=600,scrollbars=no,location=no');
				}
			});
		},function(data){
			console.log(data);
		});
	};
	
	/**
	 * 上传文件
	 */
	$scope.openUploadFile=function(){
		uploadFileService.openUploadFile('services/tokens',function(data){
			console.log('data:'+data);
			$.each(data, function(i, field) {
				if (i == "token") {
//					$("#div1").append(Date() + "---get token---" + field+"<br/>");
					//$window.open('http://dapi.saic-gm.com/fileservice/html/upload.html?token='+field+'&resultId=result', '_blank',	'height=300,width=600,scrollbars=no,location=no');
					$window.open('http://dapi.saic-gm.com/fileservice/html/upload.html?token='+field+'&resultId=result'+'&resultName=resultName', '_blank',	'height=300,width=600,scrollbars=no,location=no');
				}
			});
		},function(data){
			console.log(data);
		});
	};
	
	/**
	 * 文件保存
	 */
	$scope.saveFile=function(){
		uploadFileService.getToken('services/tokens',function(data,reponse_info){
			$.each(data, function(i, field) {
				if (i == "token") {
					var data={
							filePath: "http://dapi.saic-gm.com/fileservice/v1/files/" + $('#result').val() + "?token=" + field,
							data:$stateParams.data,
							filedId:$('#result').val(),
							fileName:$('#resultName').val(),
							token:field
					};
					
					if(folUtil.isNull($('#result').val())){
						$.messager.alert('提示','请选择文件再上传');
						return ;
					}
					
					if(!checkData()){
						return;
					}
					setOtherData(data);
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					$.each(UPLOAD_URL,function(i, val){
						if(i.toLowerCase()===$stateParams.pageName.toLowerCase()){
							uploadFileService.saveFile(data,val,function(response_info){
								$window.history.back();
								$.messager.progress('close');
								$.messager.alert('提示','上传文件成功');
							},function(response_info){
								$.messager.progress('close');
								
								if(response_info.state===STATE.TIMEOUT){
									console.log('timeout');
									$.messager.alert('提示','系统超时请稍后访问');
									
								}else{
									$.messager.alert('提示',response_info.message);
								}
								
							});
						}
					});
				}
			});
		},function(response_info){
			console.log(response_info);
		});
	};
	
	/**
	 * 数据初始化
	 */
	function setOtherData(data){
		if($stateParams.pageName==='bill'){
			data.effectDate=$('#effectDate').datebox('getValue');
			data.billDate=$('#billDate').datebox('getValue');
		}else if($stateParams.pageName==='bonusIssueVersion'){
			data.dealerType=folUtil.getComboBoxDataById('dealerType');
			data.bonusType=folUtil.getComboBoxDataById('bonusType');
		}else if($stateParams.pageName==='bankTicketInterest'){
			data.year=folUtil.getComboBoxDataById('year');
			data.month=folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month'));
		}
	}
	/**
	 * 检查数据
	 */
	function checkData(){
		if($stateParams.pageName==='bonusIssueVersion'){
			if(folUtil.getComboBoxDataById('dealerType')==-1){
				$.messager.alert('提示', '渠道类型不能为空');
				return false;
			}else if(folUtil.getComboBoxDataById('bonusType')==-1){
				$.messager.alert('提示', '奖金类型不能为空');
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 数据初始化
	 */
	function setInitData(){
		console.log($stateParams.pageName);
		if($stateParams.pageName==='bonusIssueVersion'){
			var fields=['渠道类型','奖金类型'];
			
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
				$.messager.progress('close');
				
				var typesId=['#dealerType','#bonusType'];
				
				$timeout(function(){
//					$('#bonusType').combobox('loadData',$rootScope.bonusType);
//					$('#bonusType').combobox('select',$rootScope.bonusType[0]['code']);
				
					for(var i=0;i<data.length;i++){
						if(!folUtil.isNull(data[i])){
							$(typesId[i]).combobox('loadData',data[i]);
							$(typesId[i]).combobox('select',data[i][0]['code']);
						}
					}
				});
			},function(response_info){
				$.messager.progress('close');
				
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});

		}else if($stateParams.pageName==='bankTicketInterest'){
			$timeout(function(){
				folUtil.setComboBoxYearDataById('year');
				folUtil.setComboBoxMonthDataById('month');
			});
			console.log(3);
		}
	}

}]);