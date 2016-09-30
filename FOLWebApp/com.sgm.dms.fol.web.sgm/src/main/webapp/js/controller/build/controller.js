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
/**
 * 银行特殊经销商维护controller
 */
FolController.controller('BankSpecialDealerMaintainController',['$scope','$timeout','$state','$q','BankSpecialDealerMaintainService','CodeService',function($scope,$timeout,$state,$q,BankSpecialDealerMaintainService,CodeService){
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		if($('#datagrid').length>0){
			var datagrid=$('#datagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankSpecialDealerMaintainService.query($scope.query_data,'bankTicket/dealerBankRelevance/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}
			});
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		var fields=['渠道类型','品牌','银票审核状态'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
				
			var typesId=['#dealerType','#brand','#auditStatus'];
				
			$timeout(function(){
				for(var i=0;i<data.length;i++){
					if(!folUtil.isNull(data[i])){
						if(data[i][1]['typeName']=='银票审核状态'){
							var datas=[];
							
							$.each(data[i],function(i,val){
								if(val['codeCnDesc'].indexOf('修改')<0){
									datas.push(val);
								}
							});
							
							$(typesId[i]).combobox('loadData',datas);
							$(typesId[i]).combobox('select',datas[0]['code']);
						}else{
							$(typesId[i]).combobox('loadData',data[i]);
							$(typesId[i]).combobox('select',data[i][0]['code']);
						}
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
	});
	
	/**
	 * 新增银行特殊经销商
	 */
	$scope.insert=function(){
		$state.go('addBankSpecialDealer');
	};
	
	/**
	 * 导入银行特殊经销商
	 */
	$scope.import=function(){
		$state.go('uploadFileController',{pageName:'bankSpecialDealer'});
	};
	
	/**
	 * 删除银行特殊经销商
	 */
	$scope.deleteSpecialDealer=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择一条记录，再进行删除操作');
			return ;
		}else if(rows.length>1){
			$.messager.alert('提示','只能选择一条记录，再进行删除操作');
			return ;
		}else if(rows[0]['auditStatus']==STATE.BANK_TICKET_ADD_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_UPDATE_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_DELETE_UN_AUDIT){
			$.messager.alert('提示','当前状态是待审核不能给予删除');
			return;
		}
		
		BankSpecialDealerMaintainService.deleteSpecialDealer(rows[0],'bankTicket/dealerBankRelevance/delete',function(data,response_info){
			defferred.resolve('success');
		},function(response_info){
			defferred.reject('fail');
		});
		
		promise.then(function(res){
			$.messager.alert('提示', '删除银行特殊经销商成功');
			
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			BankSpecialDealerMaintainService.query($scope.query_data,'bankTicket/dealerBankRelevance/query',function(data,response_info){
				$.messager.progress('close');
				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
				$('#datagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});
			
		},function(res){
			$.messager.alert('提示', '删除银行特殊经销商失败');
		});
	};
	
	/**
	 * 查询银行特殊经销商
	 */
	$scope.query=function(){
		$scope.istableshow=true;
		
		$scope.query_data={
				dealerType:folUtil.getComboBoxDataById('dealerType'),
				sapCode:$scope.sapCode,
				dealerName:$scope.dealerName,
				brand:folUtil.getComboBoxDataById('brand'),
				auditStatus:folUtil.getComboBoxDataById('auditStatus'),
				beginNo:0,
				endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankSpecialDealerMaintainService.query($scope.query_data,'bankTicket/dealerBankRelevance/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	}
}]);

/**
 * 新增银行特殊经销商Controller
 */
FolController.controller('AddBankSpecialDealerController',['$scope','$timeout','$state','$q','BankSpecialDealerMaintainService','CodeService',function($scope,$timeout,$state,$q,BankSpecialDealerMaintainService,CodeService){
	$timeout(function(){
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		var fields=['渠道类型'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
				
			var typesId=['#dealerType','#mainTainType'];
				
			$timeout(function(){
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
		
		BankSpecialDealerMaintainService.getBankInfoAllList('bankTicket/dealerBankRelevance/getBankInfoAllList',function(data,response_info){
			var bankinfolist=[];
			bankinfolist.push({bankAbbr:-1,bankChName:'请选择'});
			
			$.each(data,function(i,val){
				bankinfolist.push(val);
			});
			
			$timeout(function(){
				$('#bank').combobox('loadData',bankinfolist);
				$('#bank').combobox('select',bankinfolist[0]['bankAbbr']);
			});
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	});
	
	/**
	 * 新增银行特殊经销商
	 */
	$scope.addBankSpecialDealer=function(){
		var bankSpecialDealer={
				dealerType:folUtil.getComboBoxDataById('dealerType'),
				bankAbbr:folUtil.getComboBoxDataById('bank'),
				sapCode:$('#sapCode').textbox('getValue')
		};
		
		if(!check()){
			return;
		}
		
		BankSpecialDealerMaintainService.addBankSpecialDealer(bankSpecialDealer,'bankTicket/dealerBankRelevance/add',function(data,response_info){
			$.messager.alert('提示', '新增银行特殊经销商成功');
			$state.go('bankSpecialDealerMaintain');
		},function(response_info){
			$.messager.alert('提示', '新增银行特殊经销商失败');
		});
	};
	
	/**
	 * 检查数据
	 */
	function check(){
		if(folUtil.getComboBoxDataById('dealerType')==-1){
			$.messager.alert('提示','渠道类型不能为空');
			return false;
		}else if(folUtil.getComboBoxDataById('bank')==-1){
			$.messager.alert('提示','银行不能为空');
			return false;
		}else if(folUtil.isNull($('#sapCode').textbox('getValue'))){
			$.messager.alert('提示','客户代码不能为空');
			return false;
		}
		return true;
	}
	
	/**
	 * 取消
	 */
	$scope.cancel=function(){
		$state.go('bankSpecialDealerMaintain');
	};
}]);
/**
 * 银票期限维护controller
 */
FolController.controller('BankTicketDeadLineMaintainController',['$scope','$timeout','$state','$q','$stateParams','BankTicketDeadLineMaintainService','CodeService',function($scope,$timeout,$state,$q,$stateParams,BankTicketDeadLineMaintainService,CodeService){
	var typeName='brand';
	
	/**
	 * 数据初始化
	 */
	$timeout(function(){		
		if($('#branddatagrid').length>0){
			var datagrid=$('#branddatagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankTicketDeadLineMaintainService.query($scope.query_data,'bankTicket/deadLine/queryBrand',function(data,response_info){
						$.messager.progress('close');
						$('#branddatagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}
			});

			$scope.query_data={
					beginNo:0,
					endNo:10
			};
			
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			BankTicketDeadLineMaintainService.query($scope.query_data,'bankTicket/deadLine/queryBrand',function(data,response_info){
				$.messager.progress('close');
				$('#branddatagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});
		}
		if($('#specialdealerdatagrid').length>0){
			var datagrid=$('#specialdealerdatagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankTicketDeadLineMaintainService.query($scope.query_data,'bankTicket/deadLine/querySpecialDealer',function(data,response_info){
						$.messager.progress('close');
						$('#specialdealerdatagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}
			});
		}
		
		/**
		 * 监听切换TABS事件
		 */
		$('.sgm_tabs').tabs({
			onSelect:function(title){
//				$scope.$apply(function(){
//					$scope.istableshow=false;
//				})
				
				
				if(title=='维护特殊经销商'){
					typeName='specialDealer';
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					var fields=['渠道类型','品牌','银票审核状态'];
					CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
						$.messager.progress('close');
							
						var typesId=['#dealerType','#brand','#auditStatus'];
							
						$timeout(function(){
							for(var i=0;i<data.length;i++){
								if(!folUtil.isNull(data[i])){
									if(data[i][1]['typeName']=='银票审核状态'){
										var datas=[];
										
										$.each(data[i],function(i,val){
											if(val['codeCnDesc'].indexOf('修改')<0){
												datas.push(val);
											}
										});
										
										$(typesId[i]).combobox('loadData',datas);
										$(typesId[i]).combobox('select',datas[0]['code']);
									}else{
										$(typesId[i]).combobox('loadData',data[i]);
										$(typesId[i]).combobox('select',data[i][0]['code']);
									}
								}
							}
						});
						
						$.messager.progress({
							title:'Please waiting',
							msg:'Loading data...',
							interval:PROGRESS_ACTION_TIMEOUT
						});	
						
						BankTicketDeadLineMaintainService.query($scope.query_data,'bankTicket/deadLine/querySpecialDealer',function(data,response_info){
							$.messager.progress('close');
							$('#specialdealerdatagrid').datagrid('loadData',{total:0,rows:[]});
							$('#specialdealerdatagrid').datagrid('loadData',data);
						},function(response_info){
							$.messager.progress('close');
							if(response_info.state===STATE.TIMEOUT){
								console.log('timeout');
								$.messager.alert('提示','系统超时请稍后访问');
							}
						});
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}else{
					typeName='brand';
					
//					$scope.$apply(function(){
//						$scope.istableshow=true;
//					})
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					$scope.query_data={
							beginNo:0,
							endNo:10
					};
					
					BankTicketDeadLineMaintainService.query($scope.query_data,'bankTicket/deadLine/queryBrand',function(data,response_info){
						$.messager.progress('close');
						$('#branddatagrid').datagrid('loadData',{total:0,rows:[]});
						$('#branddatagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}
			}
		});
	});
	
	/**
	 * 新增按照品牌渠道维护或者特殊经销商维护的银票期限
	 */
	$scope.addBankTicketDeadLingBrand=function(){
		$state.go('addBankTicketDeadLineBrandOrSpecialDealer',{data:{maintainType:1}});
	};
	
	/**
	 * 新增按照特殊经销商维护的银票期限
	 */
	$scope.addBankTicketDeadLingSpecialDealer=function(){
		$state.go('addBankTicketDeadLineBrandOrSpecialDealer',{data:{maintainType:2}});
	};
	
	/**
	 * 更新按照品牌渠道维护或者特殊经销商维护的银票期限
	 */
	$scope.updateBankTicketDeadLingBrand=function(){
		var rows=$('#branddatagrid').datagrid('getSelections');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择一条记录，再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示','只能一条记录，再进行操作');
			return;
		}else if(rows[0]['auditStatus']==STATE.BANK_TICKET_ADD_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_UPDATE_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_DELETE_UN_AUDIT){
			$.messager.alert('提示','当前状态是待审核不能给予修改');
			return;
		}
		
		$state.go('updateBankTicketDeadLineBrandOrSpecialDealer',{data:{maintainType:1,vo:rows[0]}});
	};
	
	/**
	 * 更新按照特殊经销商维护的银票期限
	 */
	$scope.updateBankTicketDeadLingSpecialDealer=function(){
		var rows=$('#specialdealerdatagrid').datagrid('getSelections');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择一条记录，再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示','只能一条记录，再进行操作');
			return;
		}else if(rows[0]['auditStatus']==STATE.BANK_TICKET_ADD_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_UPDATE_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_DELETE_UN_AUDIT){
			$.messager.alert('提示','当前状态是待审核不能给予修改');
			return;
		}
		
		$state.go('updateBankTicketDeadLineBrandOrSpecialDealer',{data:{maintainType:2,vo:rows[0]}});
	};
	
	/**
	 * 导入银行特殊经销商
	 */
	$scope.import=function(){
		$state.go('uploadFileController',{pageName:'bankSpecialDealer'});
	};
	
	/**
	 * 删除银行特殊经销商
	 */
	$scope.deleteBankTicketDeadLine=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var rows;
		if(typeName=='brand'){
			rows=$('#branddatagrid').datagrid('getSelections');
		}else if(typeName=='specialDealer'){
			rows=$('#specialdealerdatagrid').datagrid('getSelections');
		}
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择一条记录，再进行删除操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示','只能一条记录，再进行删除操作');
			return;
		}else if(rows[0]['auditStatus']==STATE.BANK_TICKET_ADD_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_UPDATE_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_DELETE_UN_AUDIT){
			$.messager.alert('提示','当前状态是待审核不能给予删除');
			return;
		}
		
		BankTicketDeadLineMaintainService.deleteBankTicketDeadLine(rows[0],'bankTicket/deadLine/delete',function(response_info){
			defferred.resolve('success');
		},function(response_info){
			defferred.reject('fail');
		});
		
		promise.then(function(res){
			$.messager.alert('提示', '删除银行特殊经销商成功');
			
			var url;
			if(typeName=='brand'){
				url='bankTicket/deadLine/queryBrand';
			}else if(typeName=='specialDealer'){
				url='bankTicket/deadLine/querySpecialDealer';
			}
			
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			BankTicketDeadLineMaintainService.query($scope.query_data,url,function(data,response_info){
				$.messager.progress('close');

				if(typeName=='brand'){
					$('#branddatagrid').datagrid('loadData',{total:0,rows:[]});
					$('#branddatagrid').datagrid('loadData',data);
				}else if(typeName=='specialDealer'){
					$('#specialdealerdatagrid').datagrid('loadData',{total:0,rows:[]});
					$('#specialdealerdatagrid').datagrid('loadData',data);
				}
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});
			
		},function(res){
			$.messager.alert('提示', '删除银行特殊经销商失败');
		});
	};
	
	/**
	 * 查询银行特殊经销商
	 */
	$scope.query=function(){
		$scope.istableshow=true;
		
		$scope.query_data={
				dealerType:folUtil.getComboBoxDataById('dealerType'),
				sapCode:$('#sapCode').textbox('getValue'),
				dealerName:$('#dealerName').textbox('getValue'),
				brandId:folUtil.getComboBoxDataById('brand'),
				auditStatus:folUtil.getComboBoxDataById('auditStatus'),
				beginNo:0,
				endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankTicketDeadLineMaintainService.query($scope.query_data,'bankTicket/deadLine/querySpecialDealer',function(data,response_info){
			$.messager.progress('close');
			$('#specialdealerdatagrid').datagrid('loadData',{total:0,rows:[]});
			$('#specialdealerdatagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	}

}]);

/**
 * 新增银票期限Controller
 */
FolController.controller('AddBankTicketDeadLineMaintainController',['$scope','$timeout','$state','$stateParams','$q','BankTicketDeadLineMaintainService','CodeService',function($scope,$timeout,$state,$stateParams,$q,BankTicketDeadLineMaintainService,CodeService){
	$scope.data=$stateParams.data;
	
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		var fields=['渠道类型','品牌'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
				
			var typesId=['#dealerType','#brand'];
				
			$timeout(function(){
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
		
	});
	
	/**
	 * 新增银票期限
	 */
	$scope.addBankTicketDeadLine=function(){
		if($scope.data.maintainType==1&&!$scope.chenkBrand()){
			return;
		}else if($scope.data.maintainType==2&&!$scope.checkSpecialDealer()){
			return;
		}
		
		var bankTicketDeadLine={
				dealerType:folUtil.getComboBoxDataById('dealerType'),//渠道类型
				brandId:folUtil.getComboBoxDataById('brand'),//品牌
				deadlineDay:$('#deadlineDay').numberbox('getValue'),//票据天数
				freePeriodDay:$('#freePeriodDay').numberbox('getValue'),//免息期（天）
				effectDate:$('#effectDate').textbox('getValue')//起效日
		};
		
		if(!$scope.checkValue(bankTicketDeadLine)){
			return;
		}
		
		if($('#sapCode').length>0){
			bankTicketDeadLine.sapCode=folUtil.isNull($('#sapCode').textbox('getValue'))?null:$('#sapCode').textbox('getValue');
			bankTicketDeadLine.expireDate=folUtil.isNull($('#expireDate').textbox('getValue'))?null:$('#expireDate').textbox('getValue');//到期日
		}
		
		
		var url;
		if($scope.data.maintainType==1){
			url='bankTicket/deadLine/addBrand';
		}else{
			url='bankTicket/deadLine/addSpecialDealer';
		}
		
		BankTicketDeadLineMaintainService.addBankTicketDeadLine(bankTicketDeadLine,url,function(response_info){
			$.messager.alert('提示', '新增银票期限成功');
			$state.go('bankTicketDeadLineMaintain');			
		},function(response_info){
			$.messager.alert('提示', '新增银票期限失败,');
		});
	};
	
	/**
	 * 取消
	 */
	$scope.cancel=function(){
		$state.go('bankTicketDeadLineMaintain');
	}
	
	/**
	 * 检查数据（品牌）
	 */
	$scope.chenkBrand=function(){
		if(folUtil.isNull(folUtil.getComboBoxDataById('dealerType'))){
			$.messager.alert('提示','渠道类型不能为空');
			return false;
		}
		if(folUtil.isNull(folUtil.getComboBoxDataById('brand'))){
			$.messager.alert('提示','品牌不能为空');
			return false;
		}
		if(folUtil.isNull($('#deadlineDay').numberbox('getValue'))){
			$.messager.alert('提示','票据天数不能为空');
			return false;
		}
		if(folUtil.isNull($('#freePeriodDay').numberbox('getValue'))){
			$.messager.alert('提示','免息期不能为空');
			return false;
		}
		if(folUtil.isNull($('#effectDate').textbox('getValue'))){
			$.messager.alert('提示','起效日不能为空');
			return false;	
		}
		
		return true;
	}
	
	/**
	 * 检查数据（特殊经销商）
	 */
	$scope.checkSpecialDealer=function(){
		if(folUtil.isNull(folUtil.getComboBoxDataById('dealerType'))||folUtil.getComboBoxDataById('dealerType')==-1){
			$.messager.alert('提示','渠道类型不能为空');
			return false;
		}
		if(folUtil.isNull(folUtil.getComboBoxDataById('brand'))||folUtil.getComboBoxDataById('brand')==-1){
			$.messager.alert('提示','品牌不能为空');
			return false;
		}
		if(folUtil.isNull($('#sapCode').textbox('getValue'))){
			$.messager.alert('提示','SAP代码不能为空');
			return false;
		}
		if(folUtil.isNull($('#deadlineDay').numberbox('getValue'))){
			$.messager.alert('提示','票据天数不能为空');
			return false;
		}
		if(folUtil.isNull($('#freePeriodDay').numberbox('getValue'))){
			$.messager.alert('提示','免息期不能为空');
			return false;
		}
		if(folUtil.isNull($('#effectDate').textbox('getValue'))){
			$.messager.alert('提示','起效日不能为空');
			return false;	
		}
		if(folUtil.isNull($('#expireDate').textbox('getValue'))){
			$.messager.alert('提示','到期日不能为空');
			return false;	
		}
		
		return true;
	}
	
	$scope.checkValue=function(vo){
		if(parseFloat(vo.deadlineDay)<parseFloat(vo.freePeriodDay)){
			$.messager.alert('提示','票据天数不能小于免息天数');
			return false;
		}
		
		if(!folUtil.isNull(vo.expireDate)&&(vo.effectDate >= vo.expireDate)){
			$.messager.alert('提示','生效日期必须小于到期日期');
			return false;
		}
		return true;
	};
}]);

/**
 * 修改银票期限Controller
 */
FolController.controller('UpdateBankTicketDeadLineMaintainController',['$scope','$timeout','$state','$stateParams','BankTicketDeadLineMaintainService',function($scope,$timeout,$state,$stateParams,BankTicketDeadLineMaintainService){
	$scope.data=$stateParams.data;
	
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		$('#dealerType').textbox('setValue',$scope.data.vo.dealerTypeName);
		$('#brand').textbox('setValue',$scope.data.vo.brandName);
		$('#deadlineDay').textbox('setValue',$scope.data.vo.deadlineDay);
		$('#freePeriodDay').textbox('setValue',$scope.data.vo.freePeriodDay);
		$('#auditStatus').textbox('setValue',$scope.data.vo.auditStatusName);
		$('#effectDate').textbox('setValue',$scope.data.vo.effectDateDisplay);
		
		if($scope.data.maintainType==2){
			$timeout(function(){
				$('#sapCode').textbox('setValue',$scope.data.vo.sapCode);
				$('#dealerName').textbox('setValue',$scope.data.vo.dealerName);
				$('#expireDate').textbox('setValue',$scope.data.vo.expireDateDisplay);
			});		
		}
	});
	
	/**
	 * 修改银票期限（品牌）
	 */
	$scope.updateDeadLineWithBrand=function(){
		var data={
			encryptId:$scope.data.vo.encryptId,
			deadlineDay:$('#currentdeadlineDay').numberbox('getValue'),
			freePeriodDay:$('#currentfreePeriodDay').numberbox('getValue'),
			effectDate:$('#currenteffectDate').datebox('getValue')
		}
		
		if(!$scope.checkValue(data)){
			return;
		}
		
		BankTicketDeadLineMaintainService.updateDeadLineWithBrand(data,'bankTicket/deadLine/updateDeadLine',function(response_info){
			$.messager.alert('提示', '修改银票期限成功');
			$state.go('bankTicketDeadLineMaintain');
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}else{
				$.messager.alert('提示','修改银票期限失败,');
			}
		});
	}
	
	/**
	 * 修改银票期限（特殊经销商）
	 */
	$scope.updateDeadLineWithSpecialDealer=function(){
		var data={
				encryptId:$scope.data.vo.encryptId,
				deadlineDay:$('#currentdeadlineDay').numberbox('getValue'),
				freePeriodDay:$('#currentfreePeriodDay').numberbox('getValue'),
				effectDate:$('#currenteffectDate').datebox('getValue'),
				expireDate:$('#currentexpireDate').datebox('getValue')
			}
			
			if(!$scope.checkValue(data)){
				return;
			}
		
			BankTicketDeadLineMaintainService.updateDeadLineWithBrand(data,'bankTicket/deadLine/updateDeadLine',function(response_info){
				$.messager.alert('提示', '修改银票期限成功');
				$state.go('bankTicketDeadLineMaintain');
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}else{
					$.messager.alert('提示','修改银票期限失败,');
				}
			});
	}
	
	/**
	 * 取消
	 */
	$scope.cancel=function(){
		$state.go('bankTicketDeadLineMaintain');
	};
	
	/**
	 * 检查数据
	 */
	$scope.checkValue=function(vo){
		if(parseFloat(vo.deadlineDa)<parseFloat(vo.freePeriodDay)){
			$.messager.alert('提示','票据天数不能小于免息天数');
			return false;
		}else if(!folUtil.isNull(vo.expireDate)&&(vo.effectDate>=vo.expireDate)){
			$.messager.alert('提示','生效日期必须小于到期日期');
			return false;
		}
		return true;
	};
}]);

/**
 * 银票限额维护
 */
FolController.controller('BankTicketLimitAmountMaintainController',['$scope','$timeout','$q','$state','BankTicketLimitAmountMaintainService','CodeService',function($scope,$timeout,$q,$state,BankTicketLimitAmountMaintainService,CodeService){
	var typeName='amountLimit';
	
	/**
	 * 数据初始化
	 */
	$timeout(function(){		
		if($('#datagrid').length>0){
			var datagrid=$('#datagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankTicketLimitAmountMaintainService.query($scope.query_data,'bankTicket/limitAmount/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}
			});
			
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			$scope.query_data={
					beginNo:0,
					endNo:10
			};
			
			BankTicketLimitAmountMaintainService.query($scope.query_data,'bankTicket/limitAmount/query',function(data,response_info){
				$.messager.progress('close');
				$('#datagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});
		}
			if($('#specialdealerdatagrid').length>0){
			var datagrid=$('#specialdealerdatagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankTicketLimitAmountMaintainService.query($scope.query_data,'bankTicket/limitAmount/querySpecialDealer',function(data,response_info){
						$.messager.progress('close');
						$('#specialdealerdatagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}
			});

			$scope.query_data={
					beginNo:0,
					endNo:10
			};
			
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			BankTicketLimitAmountMaintainService.query($scope.query_data,'bankTicket/limitAmount/querySpecialDealer',function(data,response_info){
				$.messager.progress('close');
				$('#specialdealerdatagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});
		}
		
		$('.sgm_tabs').tabs({
			onSelect:function(title){
//				$scope.$apply(function(){
//					$scope.istableshow=false;
//				})
				
				
				if(title=='维护特殊经销商'){
					typeName='specialDealer';
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					var fields=['渠道类型','品牌','银票审核状态'];
					CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
						$.messager.progress('close');
							
						var typesId=['#dealerType','#brand','#auditStatus'];
							
						$timeout(function(){
							for(var i=0;i<data.length;i++){
								if(!folUtil.isNull(data[i])){
									if(data[i][1]['typeName']=='银票审核状态'){
										var datas=[];
										
										$.each(data[i],function(i,val){
											if(val['codeCnDesc'].indexOf('修改')<0){
												datas.push(val);
											}
										});
										
										$(typesId[i]).combobox('loadData',datas);
										$(typesId[i]).combobox('select',datas[0]['code']);
									}else{
										$(typesId[i]).combobox('loadData',data[i]);
										$(typesId[i]).combobox('select',data[i][0]['code']);
									}
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

					$scope.query_data={
							beginNo:0,
							endNo:10
					};
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankTicketLimitAmountMaintainService.query($scope.query_data,'bankTicket/limitAmount/querySpecialDealer',function(data,response_info){
						$.messager.progress('close');
						$('#specialdealerdatagrid').datagrid('loadData',{total:0,rows:[]});
						$('#specialdealerdatagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}else{
					typeName='limitAomunt';
					
//					$scope.$apply(function(){
//						$scope.istableshow=true;
//					})
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					$scope.query_data={
							beginNo:0,
							endNo:10
					};
					
					BankTicketLimitAmountMaintainService.query($scope.query_data,'bankTicket/limitAmount/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagrid').datagrid('loadData',{total:0,rows:[]});
						$('#datagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}
			}
		});
	});
	
	/**
	 * 跳转到新增银票限额页面
	 */
	$scope.addBankTicketLimitAmount=function(){
		$state.go('addBankTicketLimitAmountMaintain',{data:{maintainType:1}});
	}
	
	/**
	 * 跳转到新增银票限额（特殊经销商）页面
	 */
	$scope.addBankTicketLimitAmountSpecialDealer=function(){
		$state.go('addBankTicketLimitAmountMaintain',{data:{maintainType:2}});
	}
	
	/**
	 * 查询特殊经销商
	 */
	$scope.querySpecialDealer=function(){
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		$scope.query_data={
				dealerType:folUtil.getComboBoxDataById('dealerType'),
				brandId:folUtil.getComboBoxDataById('brand'),
				sapCode:$('#sapCode').textbox('getValue'),
				dealerName:$('#dealerName').textbox('getValue'),
				auditStatus:folUtil.getComboBoxDataById('auditStatus'),
				beginNo:0,
				endNo:10
		};
		
		BankTicketLimitAmountMaintainService.query($scope.query_data,'bankTicket/limitAmount/querySpecialDealer',function(data,response_info){
			$.messager.progress('close');
			$('#specialdealerdatagrid').datagrid('loadData',{total:0,rows:[]});
			$('#specialdealerdatagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};
	
	/**
	 * 删除银票限额
	 */
	$scope.deleteBankTicketLimitAmount=function(){
		var rows;
		if(typeName=='amountLimit'){
			rows=$('#datagrid').datagrid('getSelections');
		}else{
			rows=$('#specialdealerdatagrid').datagrid('getSelections');
		}
		
		if(rows.length<=0){
			$.messager.alert('提示', '请选择一条记录，再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多条记录，再进行操作');
			return;
		}else if(rows[0]['auditStatus']==STATE.BANK_TICKET_ADD_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_UPDATE_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_DELETE_UN_AUDIT){
			$.messager.alert('提示','当前状态是待审核不能给予删除');
			return;
		}
		
		BankTicketLimitAmountMaintainService.deleteBankTicketLimitAmount(rows[0],'bankTicket/limitAmount/delete',function(response_info){
			$.messager.alert('提示','删除银票限额成功');
			
			var requestParams={};
			if(typeName=='amountLimit'){
				requestParams.url='bankTicket/limitAmount/query';
				requestParams.datagridName='#datagrid';				
			}else{
				requestParams.url='bankTicket/limitAmount/querySpecialDealer';
				requestParams.datagridName='#specialdealerdatagrid';
			}
			
			BankTicketLimitAmountMaintainService.query($scope.query_data,requestParams.url,function(data,response_info){
				$.messager.progress('close');
				$(requestParams.datagridName).datagrid('loadData',{total:0,rows:[]});
				$(requestParams.datagridName).datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}else{
				$.messager.alert('提示','删除银票限额失败');
			}
		});
	};
	
	/**
	 * 跳转到修改银票限额
	 */
	$scope.updateBankTicketLimitAmount=function(){
		var rows;
		if(typeName=='amountLimit'){
			rows=$('#datagrid').datagrid('getSelections');
		}else{
			rows=$('#specialdealerdatagrid').datagrid('getSelections');
		}
		
		if(rows.length<=0){
			$.messager.alert('提示', '请选择一条记录，再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多条记录，再进行操作');
			return;
		}else if(rows[0]['auditStatus']==STATE.BANK_TICKET_ADD_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_UPDATE_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_DELETE_UN_AUDIT){
			$.messager.alert('提示','当前状态是待审核不能给予修改');
			return;
		}
		
		$state.go('updateBankTicketLimitAmountMaintain',{data:{maintainType:1,vo:rows[0]}});
	}
	
	/**
	 * 跳转到修改银票限额（特殊经销商）
	 */
	$scope.updateBankTicketLimitAmountSpecialDealer=function(){
		var rows;
		if(typeName=='amountLimit'){
			rows=$('#datagrid').datagrid('getSelections');
		}else{
			rows=$('#specialdealerdatagrid').datagrid('getSelections');
		}
		
		if(rows.length<=0){
			$.messager.alert('提示', '请选择一条记录，再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多条记录，再进行操作');
			return;
		}else if(rows[0]['auditStatus']==STATE.BANK_TICKET_ADD_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_UPDATE_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_DELETE_UN_AUDIT){
			$.messager.alert('提示','当前状态是待审核不能给予修改');
			return;
		}
		
		$state.go('updateBankTicketLimitAmountMaintain',{data:{maintainType:2,vo:rows[0]}});
	}

}]);

/**
 * 新增银票限额Controller
 */
FolController.controller('AddBankTicketLimitAmountMaintainController',['$scope','$timeout','$q','$state','$stateParams','BankTicketLimitAmountMaintainService','CodeService',function($scope,$timeout,$q,$state,$stateParams,BankTicketLimitAmountMaintainService,CodeService){
	$scope.data=$stateParams.data;
	
	$timeout(function(){
		if($scope.data.maintainType==2){
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			var fields=['渠道类型','品牌'];
			CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
				$.messager.progress('close');
					
				var typesId=['#dealerType','#brand'];
					
				$timeout(function(){
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
		}
	});
	
	/**
	 * 新增银票限额
	 */
	$scope.addBankTicketLimitAmount=function(){
		if(!check()){
			return;
		}
		
		var bankTicketLimitVo={
				amountLimit:$('#limitAmount').numberbox('getValue'),
				effectDate:$('#effectDate').datebox('getValue')
		}
		
		var url;
		if($scope.data.maintainType==2){
			bankTicketLimitVo['dealerType']=folUtil.getComboBoxDataById('dealerType');
			bankTicketLimitVo['brandId']=folUtil.getComboBoxDataById('brand');
			bankTicketLimitVo['sapCode']=$('#sapCode').textbox('getValue');
			bankTicketLimitVo['expireDate']=$('#expireDate').datebox('getValue');
			url='bankTicket/limitAmount/addSpecialDealer';
		}else{
			url='bankTicket/limitAmount/add';
		}
		
		BankTicketLimitAmountMaintainService.addBankTicketLimitAmount(bankTicketLimitVo,url,function(response_info){
			$.messager.alert('提示','新增银票限额成功');
			$state.go('bankTicketLimitAmountMaintain');
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}else{
				$.messager.alert('提示','新增银票限额失败');
			}
		});
	};
	
	/**
	 * 取消
	 */
	$scope.cancel=function(){
		$state.go('bankTicketLimitAmountMaintain');
	};
	
	/**
	 * 检查数据
	 */
	function check(){
		if(folUtil.isNull($('#limitAmount').numberbox('getValue'))){
			$.messager.alert('提示','票据限额不能为空')
			return false;
		}
		if(folUtil.isNull($('#effectDate').datebox('getValue'))){
			$.messager.alert('提示','起效日不能为空')
			return false;
		}
			
		if($scope.data.maintainType==2){
			if(folUtil.isNull(folUtil.getComboBoxDataById('dealerType'))||folUtil.getComboBoxDataById('dealerType')==-1){
				$.messager.alert('提示','渠道类型不能为空')
				return false;
			} 
			
			if(folUtil.isNull(folUtil.getComboBoxDataById('brand'))||folUtil.getComboBoxDataById('brand')==-1){
				$.messager.alert('提示','品牌不能为空')
				return false;
			}
			
			if(folUtil.isNull($('#sapCode').textbox('getValue'))){
				$.messager.alert('提示','客户代码不能为空')
				return false;
			}
			
			if(folUtil.isNull($('#expireDate').datebox('getValue'))){
				$.messager.alert('提示','到期日不能为空')
				return false;
			}
		}
		
		return true;
	}
}]);

/**
 * 更新银票限额Controller
 */
FolController.controller('UpdateBankTicketLimitAmountMaintainController',['$scope','$timeout','$q','$state','$stateParams','BankTicketLimitAmountMaintainService',function($scope,$timeout,$q,$state,$stateParams,BankTicketLimitAmountMaintainService){
	$scope.data=$stateParams.data;
	
	$timeout(function(){
		$('#amountLimit').textbox('setValue',$scope.data.vo.moneyDisplay);
		$('#effectDateDisplay').textbox('setValue',$scope.data.vo.effectDateDisplay);
		$('#auditStatusName').textbox('setValue',$scope.data.vo.auditStatusName);
		$('#auditMsg').textbox('setValue',$scope.data.vo.auditMsg);
		
		if($scope.data.maintainType==2){
			$('#dealerTypeName').textbox('setValue',$scope.data.vo.dealerTypeName);
			$('#sapCode').textbox('setValue',$scope.data.vo.sapCode);
			$('#brandName').textbox('setValue',$scope.data.vo.brandName);
			$('#dealerName').textbox('setValue',$scope.data.vo.dealerName);
			$('#expireDateDisplay').textbox('setValue',$scope.data.vo.expireDateDisplay);
		}
	});
	
	/**
	 * 更新银票限额
	 */
	$scope.updateBankTicketLimitAmount=function(){
		if(!check()){
			return;
		}
		
		var bankTicketLimitVo={
				amountLimit:$('#currentAmountLimit').numberbox('getValue'),
				effectDate:$('#effectDate').datebox('getValue'),
				encryptId:$scope.data.vo.encryptId
		}
		
		if($scope.data.maintainType==2){
			bankTicketLimitVo['expireDate']=$('#expireDate').datebox('getValue');
		}
		
		BankTicketLimitAmountMaintainService.updateBankTicketLimitAmount(bankTicketLimitVo,'bankTicket/limitAmount/update',function(response_info){
			$.messager.alert('提示','修改银票限额成功');
			$state.go('bankTicketLimitAmountMaintain');
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}else{
				$.messager.alert('提示','修改银票限额失败');
			}
		});
	};
	
	/**
	 * 取消
	 */
	$scope.cancel=function(){
		$state.go('bankTicketLimitAmountMaintain');
	};
	
	/**
	 * 检查数据
	 */
	function check(){			
		if($scope.data.maintain==2){
			if($('#effectDate').datebox('getValue')<$('#expireDate').datebox('getValue')){
				return false;
			} 
		}
		
		return true;
	}

}]);
/**
 * 银行银票额度维护controller
 */
FolController.controller('BankTicketLimitMaintainController',['$scope','$timeout','$state','$q','BankTicketLimitMaintainService','CodeService',function($scope,$timeout,$state,$q,BankTicketLimitMaintainService,CodeService){
	$scope.istableshow=false;
	
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		if($('#datagridDealer').length>0){
			var datagrid=$('#datagridDealer').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankTicketLimitMaintainService.query($scope.query_data,'bankTictet/limits/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagridDealer').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}
			});
		}
		if($('#datagridBank').length>0){
			var datagrid=$('#datagridBank').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankTicketLimitMaintainService.query($scope.query_data,'bankTictet/limits/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagridBank').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}
			});
		}

		
		var types=['#dealerTypeFirst','#bankTicketAuditStatusFirst'];
		var fields=['渠道类型','银票审核状态'];
		initData(types,fields);
		$scope.query(1);
		$scope.selectedTable='dealer'
		
		/**
		 * 监听切换tabs事件
		 */
		$('.sgm_tabs').tabs({
			onSelect:function(title){
				//初始化数据
				if(title=='银行渠道银票额度维护'){
					var types=['#dealerTypeFirst','#bankTicketAuditStatusFirst'];
					var fields=['渠道类型','银票审核状态'];
					
					initData(types,fields);
					$scope.query(1);
					$scope.selectedTable='dealer'
				}else{
					var types=['#bankTicketAuditStatusSecond'];
					var fields=['银票审核状态'];
					initData(types,fields);
					$scope.query(2);
					$scope.selectedTable='bank'
				}				
			}
		});
		
	});
	
	/**
	 * 数据初始化
	 */
	function initData(typesId,fields){
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	

		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
	
			$timeout(function(){
				for(var i=0;i<data.length;i++){
					if(!folUtil.isNull(data[i])){
						if(data[i][1]['typeName']=='银票审核状态'){
								var datas=[];
								
								$.each(data[i],function(i,val){
									if(val['codeCnDesc'].indexOf('删除')<0){
										datas.push(val);
									}
								});
								
								$(typesId[i]).combobox('loadData',datas);
								$(typesId[i]).combobox('select',datas[0]['code']);
						}else if(data[i][1]['typeName']=='维护类型'){
							var datas=[];
							
							$.each(data[i],function(i,val){
								if(val['codeCnDesc'].indexOf('请选择')<0){
									datas.push(val);
								}
							});
							
							$(typesId[i]).combobox('loadData',datas);
							$(typesId[i]).combobox('select',datas[0]['code']);
						}else{
							$(typesId[i]).combobox('loadData',data[i]);
							$(typesId[i]).combobox('select',data[i][0]['code']);
						}
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
	}
	
	/**
	 * 查询
	 */
	$scope.query=function(maintainType){
		$scope.istableshow=true;
		
		$scope.query_data={
				maintainType:maintainType,				
				beginNo:0,
				endNo:10
		};
		
		if(maintainType==1){
			$scope.query_data.dealerType=folUtil.getComboBoxDataById('dealerTypeFirst');
			$scope.query_data.auditStatus=folUtil.getComboBoxDataById('bankTicketAuditStatusFirst');
		}else{
			$scope.query_data.auditStatus=folUtil.getComboBoxDataById('bankTicketAuditStatusSecond');
		}		
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankTicketLimitMaintainService.query($scope.query_data,'bankTictet/limits/query',function(data,response_info){
			$.messager.progress('close');
			if(maintainType==1){
				$('#datagridDealer').datagrid('loadData',{total:0,rows:[]});
				$('#datagridDealer').datagrid('loadData',data);
			}else{
				$('#datagridBank').datagrid('loadData',{total:0,rows:[]});
				$('#datagridBank').datagrid('loadData',data);
			}
			
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};
	
	/**
	 * 跳转到新增银行特殊经销商额度页面
	 */
	$scope.insertDealer=function(){		
		$state.go('addBankTicketLimit',{data:{maintainType:1,type:'add',pageTitle:'新增银行银票额度'}});
	};
	
	/**
	 * 跳转到新增银行额度页面
	 */
	$scope.insertBank=function(){
		$state.go('addBankTicketLimit',{data:{maintainType:2,type:'add',pageTitle:'新增银行银票额度'}});
	};
	
	/**
	 * 跳转到修改银行或者银行特殊经销商额度页面
	 */
	$scope.update=function(){
		var rows;
		if($scope.selectedTable=='dealer'){
			rows=$('#datagridDealer').datagrid('getSelections');
		}else{
			rows=$('#datagridBank').datagrid('getSelections');
		}

		if(rows.length<=0){
			$.messager.alert('提示', '请选择某条记录在进行修改');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多条记录进行修改');
			return;			
		}else if(rows[0]['auditStatus']==STATE.BANK_TICKET_ADD_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_UPDATE_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_DELETE_UN_AUDIT){
			$.messager.alert('提示','当前状态是待审核不能给予修改');
			return;
		}
		
		if($scope.selectedTable=='dealer'){
			$state.go('updateBankTicketLimit',{data:{bankBankTicketVo:rows[0],maintainType:1,type:'update',pageTitle:'更新银行银票额度'}});
		}else{
			$state.go('updateBankTicketLimit',{data:{bankBankTicketVo:rows[0],maintainType:2,type:'update',pageTitle:'更新银行银票额度'}});
		}
		
	};
	
	$scope.import=function(){
		$state.go('uploadFilePage',{pageName:'bankTicketLimitMaintain'});
	};
}]);

/**
 * 新增银行银票额度controller
 */
FolController.controller('AddBankTicketLimitController',['$scope','$timeout','$state','$q','$stateParams','BankTicketLimitMaintainService','CodeService',function($scope,$timeout,$state,$q,$stateParams,BankTicketLimitMaintainService,CodeService){
	$scope.data=$stateParams.data;
	
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		$('#bankTicketLimit').panel({title:$scope.data.pageTitle});
		
		var fields=['渠道类型'];
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
				
			var typesId=['#dealerType'];
				
			$timeout(function(){
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
	});
	
	/**
	 * 新增银票信息
	 */
	$scope.addBankTicketLimit=function(){
		var bankTicketData={
				dealerType:$('#dealerType').length>0?folUtil.getComboBoxDataById('dealerType'):null,
				bankAbbr:$scope.bankAbbr,
				bankChName:$scope.bankCnDesc,
				amount:$scope.totalAmount,
				bankEnName:$scope.bankEnDesc,
				maintainType:$stateParams.data.maintainType
		};
		
		if(!check()){
			return;
		}
		
		BankTicketLimitMaintainService.addBankTicketLimit(bankTicketData,'bankTictet/limits/add',function(response_info){
			$.messager.alert('提示','银行银票额度新增成功');
			$state.go('bankTicketLimitMaintain');
		},function(response_info){
			$.messager.alert('提示','银行银票新增失败');
		});
	};
	
	/**
	 * 检查数据
	 */
	function check(){
		if(folUtil.isNull($scope.bankAbbr)){
			$.messager.alert('提示','银行简称不能为空');
			return false;
		}
		
		if(folUtil.isNull($scope.bankCnDesc)){
			$.messager.alert('提示','银行中文名称不能为空');
			return false;
		}
		
		if($('#dealerType').length>0&&(folUtil.isNull(folUtil.getComboBoxDataById('dealerType'))||folUtil.getComboBoxDataById('dealerType')==-1)){
			$.messager.alert('提示','渠道类型不能为空');
			return false;
		}
		
		if(folUtil.isNull($scope.totalAmount)){
			$.messager.alert('提示','银票总额不能为空');
			return false;
		}
		return true;
	}
}]);

/**
 * 更新银行银票额度controller
 */
FolController.controller('UpdateBankTicketLimitController',['$scope','$timeout','$state','$q','$stateParams','BankTicketLimitMaintainService','CodeService',function($scope,$timeout,$state,$q,$stateParams,BankTicketLimitMaintainService,CodeService){
	$scope.data=$stateParams.data;
	$timeout(function(){
		$('#bankTicketLimit').panel({title:$scope.data.pageTitle});
		/**
		 * 初始化字典
		 */
		var fields=['渠道类型'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
				
			var typesId=['#dealerType'];
				
			$timeout(function(){
				for(var i=0;i<data.length;i++){
					if(!folUtil.isNull(data[i])){
						$(typesId[i]).combobox('loadData',data[i]);
//						$(typesId[i]).combobox('select',$scope.data.bankBankTicketVo.dealerType);
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
		
		/**
		 * 数据初始化
		 */
		$('#dealerType').combobox('select',$scope.data.bankBankTicketVo.dealerType);
		$('#bankAbbr').textbox('setValue',$scope.data.bankBankTicketVo.bankAbbr);
		$('#bankCnDesc').textbox('setValue',$scope.data.bankBankTicketVo.bankChName);
		$('#totalAmount').numberbox('setValue',$scope.data.bankBankTicketVo.moneyDisplay);
		$('#bankEnDesc').textbox('setValue',$scope.data.bankBankTicketVo.bankEnName);
	});
	
	/**
	 * 更新银票信息
	 */
	$scope.updateBankTicketLimit=function(){
		console.log($scope.data.bankBankTicketVo.bankId);
		var bankTicketData={
				dealerType:$('#dealerType').length>0?folUtil.getComboBoxDataById('dealerType'):null,
				bankAbbr:$scope.bankAbbr,
				bankChName:$scope.bankCnDesc,
				amount:$scope.totalAmount,
				bankEnName:$scope.bankEnDesc,
				bankId:$scope.data.bankBankTicketVo.bankId,
				encryptId:$scope.data.bankBankTicketVo.encryptId
		};
		
		BankTicketLimitMaintainService.updateBankTicketLimit(bankTicketData,'bankTictet/limits/update',function(response_info){
			$.messager.alert('提示','银行银票额度更新成功');
			$state.go('bankTicketLimitMaintain');
		},function(response_info){
			$.messager.alert('提示','银行银票额度更新失败');
			console.log(response_info);
		});
	};
}]);
/**
 * 票据贴息年化利率维护controller
 */
FolController.controller('TicketInterestRateMaintainController',['$scope','$timeout','$state','$q','TicketInterestRateMaintainService',function($scope,$timeout,$state,$q,TicketInterestRateMaintainService){

	$timeout(function(){
		$('#datagrid').datagrid().datagrid('enableCellEditing');
		$('#datagridHis').datagrid().datagrid('enableCellEditing');
		if($('#datagrid').length>0){
			var datagrid=$('#datagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					TicketInterestRateMaintainService.queryCur($scope.query_data,'interestRate/maintain/queryCur',function(data,response_info){
						$.messager.progress('close');
						$('#datagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});			
				}
			
			});
		}
		if($('#datagridHis').length>0){
			var datagrid=$('#datagridHis').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					TicketInterestRateMaintainService.queryHis($scope.query_data,'interestRate/maintain/queryHis',function(data,response_info){
						$.messager.progress('close');
						$('#datagridHis').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});			
				}
			
			});
		}
	});

	
	$scope.query_data={
			auditStatus:$scope.auditStatus,		
			beginNo:0,
			endNo:10
	};
	TicketInterestRateMaintainService.queryCur($scope.query_data,'interestRate/maintain/queryCur',function(data,response_info){
		$.messager.progress('close');
		$('#datagrid').datagrid('loadData',data);
	},function(response_info){
		$.messager.progress('close');
		if(response_info.state===STATE.TIMEOUT){
			console.log('timeout');
			$.messager.alert('提示','系统超时请稍后访问');
		}
	});
	TicketInterestRateMaintainService.queryHis($scope.query_data,'interestRate/maintain/queryHis',function(data,response_info){
		$.messager.progress('close');
		$('#datagridHis').datagrid('loadData',{total:0,rows:[]});	
		$('#datagridHis').datagrid('loadData',data);
	},function(response_info){
		$.messager.progress('close');
		if(response_info.state===STATE.TIMEOUT){
			console.log('timeout');
			$.messager.alert('提示','系统超时请稍后访问');
		}
	});


	$scope.update=function(){
		var rows;
		rows=$('#datagrid').datagrid('getSelections');

		if(rows.length<=0){
			$.messager.alert('提示', '请选择最新一条记录进行修改');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多条记录进行修改');
			return;			
		}else if(rows[0]['unAuditRateCur']!=null){
			$.messager.alert('提示','当前状态是待审核不能给予修改');
			return;
		}
		
		/**
		 * 新增修改记录
		 */
		$state.go('updateInterestRateHis');
	};
	}]);
/**
 * 新增修改年化利率记录
 */
FolController.controller('UpdateInterestRateController',['$scope','$timeout','$state','$q','TicketInterestRateMaintainService','CodeService',function($scope,$timeout,$state,$q,TicketInterestRateMaintainService,CodeService){
//	$timeout(function(){
//		$.messager.progress({
//			title:'Please waiting',
//			msg:'Loading data...',
//			interval:PROGRESS_ACTION_TIMEOUT
//		});		
//		
//	});

	/**
	 * 新增修改记录
	 */
	$scope.confirm=function(){
		var interestRate={
				annualInterestRate:$('#annualInterestRate').textbox('getValue')
		};
		
//		if(!check()){
//			return;
//		}
//		
		TicketInterestRateMaintainService.checkdata(interestRate,'interestRate/maintain/getSign',function(data,response_info){
			console.log(data);
		TicketInterestRateMaintainService.confirm(data,'interestRate/maintain/update',function(data,response_info){
			
			$.messager.alert('提示', '修改成功');
			$state.go('ticketInterestRateMaintain');
			
		},function(response_info){
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}else{
				$.messager.alert('提示', '修改失败');
			}
		});
		},function(response_info){
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});

	};
	
//	/**
//	 * 检查数据
//	 */
//	function check(){
//		if(folUtil.isNull($('#annualInterestRate').textbox('getValue'))){
//			$.messager.alert('提示','客户代码不能为空');
//			return false;
//		}
//		return true;
//	}
	
	/**
	 * 取消
	 */
	$scope.cancel=function(){
		$state.go('ticketInterestRateMaintain');
	};
}]);
/**
 *银行银票額度审核
 */
FolController.controller('BankBankTicketLimitVerifyControllor',['$scope','$timeout','$state','$q','BankBankTicketLimitVerifyService','CodeService',function($scope,$timeout,$state,$q,BankBankTicketLimitVerifyService,CodeService){
	$scope.istableshow=false;
	
	$timeout(function(){
		$('#datagrid').datagrid().datagrid('enableCellEditing');

		if($('#datagrid').length>0){
			var datagrid=$('#datagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankBankTicketLimitVerifyService.query($scope.query_data,'bankBankTicketVerify/limits/query',function(data,response_info){
						$.messager.progress('close');
						
						$('#datagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
			
				}
			
			});

			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			$scope.query_data={
					auditStatus:$scope.auditStatus,		
					beginNo:0,
					endNo:10
			};
			BankBankTicketLimitVerifyService.query($scope.query_data,'bankBankTicketVerify/limits/query',function(data,response_info){
				$.messager.progress('close');
				$('#datagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});
		}
	});
	
//	auditMsg:$scope.auditMsg;
	/**
	 * 银行银票审核通过
	 */
	$scope.auditSuccess=function(){
		var rows=$('#datagrid').datagrid('getChecked');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}
		
		var datas= [];
		$.each(rows,function(i,val){
			datas.push({encryptId:val['encryptId'],auditMsg:val['auditMsg'],moneyDisplay:val['amount'],auditStatus:val['auditStatus']});
		});
	
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankBankTicketLimitVerifyService.auditSuccess(datas,'bankBankTicketVerify/limits/auditSuccess',function(data,response_info){
			$.messager.progress('close');
			$.messager.alert('提示','审批成功');
			BankBankTicketLimitVerifyService.query($scope.query_data,'bankBankTicketVerify/limits/query',function(data,response_info){
				$.messager.progress('close');
				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
				$('#datagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});				
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
				return;
			}
			$.messager.alert('提示','审核失败请检查原因');
		});		
	}
	
	/**
	 * 银行银票审核驳回
	 */
	$scope.reject=function(){
		var rows=$('#datagrid').datagrid('getChecked');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}
		
		var datas= [];
		$.each(rows,function(i,val){
			datas.push({encryptId:val['encryptId'],auditMsg:val['auditMsg'],moneyDisplay:val['amount'],auditStatus:val['auditStatus']});
		});
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankBankTicketLimitVerifyService.reject(datas,'bankBankTicketVerify/limits/reject',function(data,response_info){
			$.messager.alert('提示','驳回成功');
			BankBankTicketLimitVerifyService.query($scope.query_data,'bankBankTicketVerify/limits/query',function(data,response_info){
				$.messager.progress('close');
				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
				$('#datagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});				
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
				return;
			}
			$.messager.alert('提示','驳回失败请检查原因');
		});
		
	}

}]);

/**
 * 银行特殊经销商审核
 */
FolController.controller('BankSpecialDealerVerifyControllor',['$scope','$timeout','$state','$q','BankSpecialDealerVerifyService','CodeService',function($scope,$timeout,$state,$q,BankSpecialDealerVerifyService,CodeService){		
	
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		if($('#datagrid').length>0){
			var datagrid=$('#datagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	

					BankSpecialDealerVerifyService.query($scope.query_data,'bankTicketVerify/specialDealer/querySpecialDealer',function(data,response_info){
						$.messager.progress('close');
						$('#datagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}
			});
		}
		
		/**
		 * 查询类型数据
		 */
		var fields=['渠道类型','品牌'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
				
			var typesId=['#dealerType','#brand'];
				
			$timeout(function(){
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
	});

	/**
	 * 查询银行特殊经销商
	 */
	$scope.query=function(){
		$scope.istableshow=true;
		
		$scope.query_data={
				dealerType:folUtil.getComboBoxDataById('dealerType'),
				sapCode:$('#sapCode').textbox('getValue'),
				dealerName:$('#dealerName').textbox('getValue'),
				brand:folUtil.getComboBoxDataById('brand'),
				beginNo:0,
				endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankSpecialDealerVerifyService.query($scope.query_data,'bankTicketVerify/specialDealer/querySpecialDealer',function(data,response_info){
			$('#datagrid').datagrid().datagrid('enableCellEditing');
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});

	};
	
	/**
	 * 银行与特殊经销商关系审核通过
	 */
	$scope.auditSuccess=function(){
		var rows=$('#datagrid').datagrid('getChecked');
		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankSpecialDealerVerifyService.auditSuccess(rows,'bankTicketVerify/specialDealer/auditSuccess',function(data,response_info){
			$.messager.progress('close');
			$.messager.alert('提示','审批成功');
			
			BankSpecialDealerVerifyService.query($scope.query_data,'bankTicketVerify/specialDealer/querySpecialDealer',function(data,response_info){
				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
				$('#datagrid').datagrid('loadData',data);
			},function(response_info){
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});
		},function(response_info){
			$.messager.progress('close');
			$.messager.alert('提示','审核失败请检查原因');
		});
	}
		
	/**
	 * 银行与特殊经销商关系审核驳回
	 */
	$scope.reject=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		var rows=$('#datagrid').datagrid('getChecked');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankSpecialDealerVerifyService.reject(rows,'bankTicketVerify/specialDealer/reject',function(data,response_info){
			$.messager.progress('close');
			$.messager.alert('提示','驳回成功');
			
			BankSpecialDealerVerifyService.query($scope.query_data,'bankTicketVerify/specialDealer/querySpecialDealer',function(data,response_info){
				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
				$('#datagrid').datagrid('loadData',data);
			},function(response_info){
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});
			
			$('#datagrid').datagrid('getSelections',data);
		},function(response_info){
			$.messager.progress('close');
			$.messager.alert('提示','驳回失败请检查原因')
		});
	}
		
}]);

/**
 * 银票期限审核
 */
FolController.controller('BankTicketDeadLineVerifyControllor',['$scope','$timeout','$state','$q','$stateParams','BankTicketDeadLineVerifyService','CodeService',function($scope,$timeout,$state,$q,$stateParams,BankTicketDeadLineVerifyService,CodeService){
	var typeName='brand';

	/**
	 * 初始化数据
	 */
	$timeout(function(){		
		$('#branddatagrid').datagrid().datagrid('enableCellEditing');
		$('#specialdealerdatagrid').datagrid().datagrid('enableCellEditing');
		if($('#branddatagrid').length>0){
			var datagrid=$('#branddatagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.brand_query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
					$scope.brand_query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});
					
					BankTicketDeadLineVerifyService.query($scope.brand_query_data,'bankTicketVerify/deadLine/queryBrand',function(data,response_info){
						$.messager.progress('close');
						$('#branddatagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
			
				}
			});
			
			$scope.brand_query_data={
					auditStatus:$scope.auditStatus,
					beginNo:0,
					endNo:10
			};
			
			BankTicketDeadLineVerifyService.query($scope.brand_query_data,'bankTicketVerify/deadLine/queryBrand',function(data,response_info){
				$.messager.progress('close');
				$('#branddatagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}						
			});
		}
		if($('#specialdealerdatagrid').length>0){
			var datagrid=$('#specialdealerdatagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.specialdealer_query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.specialdealer_query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankTicketDeadLineVerifyService.query($scope.specialdealer_query_data,'bankTicketVerify/deadLine/querySpecialDealer',function(data,response_info){
						$.messager.progress('close');
						$('#specialdealerdatagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}
			});
		}
		
		/**
		 * 监听切换tabs事件
		 */
		$('.sgm_tabs').tabs({
			onSelect:function(title){
				
				
				if(title=='审核特殊经销商'){
					typeName='specialDealer';
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					var fields=['渠道类型','品牌'];
					CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
						$.messager.progress('close');
							
						var typesId=['#dealerType','#brand'];

						$timeout(function(){
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
					
					$scope.specialdealer_query_data={
							dealerType:folUtil.getComboBoxDataById('dealerType'),
							sapCode:$('#sapCode').textbox('getValue'),
							dealerName:$('#dealerName').textbox('getValue'),
							brandId:folUtil.getComboBoxDataById('brand'),
							beginNo:0,
							endNo:10
					};
					BankTicketDeadLineVerifyService.query($scope.specialdealer_query_data,'bankTicketVerify/deadLine/querySpecialDealer',function(data,response_info){
//						$.messager.progress('close');
						$('#specialdealerdatagrid').datagrid('loadData',{total:0,rows:[]});
						$('#specialdealerdatagrid').datagrid('loadData',data);
					},function(response_info){
//						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}else{
					typeName='brand';

					$scope.brand_query_data={
							beginNo:0,
							endNo:10
					};
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankTicketDeadLineVerifyService.query($scope.brand_query_data,'bankTicketVerify/deadLine/queryBrand',function(data,response_info){
						$.messager.progress('close');
						$('#branddatagrid').datagrid('loadData',{total:0,rows:[]});
						$('#branddatagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
						
					});
				}
			}
		});
	});	
			
	/**
	 * 品牌银票期限审核通过
	 */
	$scope.auditSuccessBrand=function(){
		var rows=$('#branddatagrid').datagrid('getChecked');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择至少选择一条记录，再进行操作');
			return;
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankTicketDeadLineVerifyService.auditSuccess(rows,'bankTicketVerify/deadLine/auditSuccess',function(data,response_info){
			$.messager.progress('close');
			$.messager.alert('提示','审批成功');
			
			BankTicketDeadLineVerifyService.query($scope.brand_query_data,'bankTicketVerify/deadLine/queryBrand',function(data,response_info){
				$.messager.progress('close');
				$('#branddatagrid').datagrid('loadData',{total:0,rows:[]});
				$('#branddatagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});
		},function(response_info){
			$.messager.progress('close');
			$.messager.alert('提示','审核失败请检查原因')
		});
	}
		
	/**
	 * 品牌银票期限审核驳回
	 */
	$scope.rejectBrand=function(){
		var rows=$('#branddatagrid').datagrid('getChecked');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});					
		
		BankTicketDeadLineVerifyService.reject(rows,'bankTicketVerify/deadLine/reject',function(data,response_info){
			$.messager.alert('提示',"驳回成功");
			
			BankTicketDeadLineVerifyService.query($scope.brand_query_data,'bankTicketVerify/deadLine/queryBrand',function(data,response_info){
				$.messager.progress('close');
				$('#branddatagrid').datagrid('loadData',{total:0,rows:[]});
				$('#branddatagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
				
			});
		},function(response_info){
			$.messager.progress('close');
			$.messager.alert('提示','驳回失败请检查原因')
		});
		
	}			
	
	/**
	 * 查询银行特殊经销商
	 */
	$scope.query=function(){
		$scope.istableshow=true;
		
		$scope.brand_query_data={
				dealerType:folUtil.getComboBoxDataById('dealerType'),
				sapCode:$('#sapCode').textbox('getValue'),
				dealerName:$('#dealerName').textbox('getValue'),
				brandId:folUtil.getComboBoxDataById('brand'),
				beginNo:0,
				endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankTicketDeadLineVerifyService.query($scope.brand_query_data,'bankTicketVerify/deadLine/querySpecialDealer',function(data,response_info){
			$.messager.progress('close');
			$('#specialdealerdatagrid').datagrid('loadData',{total:0,rows:[]});
			$('#specialdealerdatagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	}
	
	/**
	 * 特殊经销商银票期限审核通过
	 */
	$scope.auditSuccess=function(){
		var rows=$('#specialdealerdatagrid').datagrid('getChecked');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
	
		BankTicketDeadLineVerifyService.auditSuccess(rows,'bankTicketVerify/deadLine/auditSuccess',function(data,response_info){
			$.messager.progress('close');
			$.messager.alert('提示','审批成功');
			
			
				BankTicketDeadLineVerifyService.query($scope.specialdealer_query_data,'bankTicketVerify/deadLine/querySpecialDealer',function(data,response_info){
					$('#specialdealerdatagrid').datagrid('loadData',{total:0,rows:[]});
					$('#specialdealerdatagrid').datagrid('loadData',data);
				},function(response_info){
					
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});	
			
		},function(response_info){
			$.messager.progress('close');
			$.messager.alert('提示','审核失败请检查原因')
		});
	}
	
	/**
	 * 特殊经销商银票期限审核驳回
	 */
	$scope.reject=function(){
		var rows=$('#specialdealerdatagrid').datagrid('getChecked');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankTicketDeadLineVerifyService.reject(rows,'bankTicketVerify/deadLine/reject',function(data,response_info){
			$.messager.progress('close');
			$.messager.alert('提示',"驳回成功");
			
				BankTicketDeadLineVerifyService.query($scope.specialdealer_query_data,'bankTicketVerify/deadLine/querySpecialDealer',function(data,response_info){
					$('#specialdealerdatagrid').datagrid('loadData',{total:0,rows:[]});
					$('#specialdealerdatagrid').datagrid('loadData',data);
				},function(response_info){
					
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});	
			
		},function(response_info){
			$.messager.progress('close');
			$.messager.alert('提示','驳回失败请检查原因')
		});
		
	}			
}]);



/**
 * 银票限额审核controllor
 */
FolController.controller('BankTicketLimitVerifyControllor',['$scope','$timeout','$state','$q','BankTicketLimitService','CodeService',function($scope,$timeout,$state,$q,BankTicketLimitService,CodeService){	
	var typeName='brand';
	
	/**
	 * 初始化数据
	 */
	$timeout(function(){	
		$('#branddatagrid').datagrid().datagrid('enableCellEditing');
		$('#specialdealerdatagrid').datagrid().datagrid('enableCellEditing');
		if($('#branddatagrid').length>0){
			var datagrid=$('#branddatagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					BankTicketLimitService.query($scope.query_data,'bankTicketLimitAmountVerify/limits/queryBrand',function(data,response_info){
						$.messager.progress('close');
						$('#branddatagrid').datagrid('loadData',data);
					},function(data,response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
						console.log("error:"+data);
					});
				
				}
			});
			$scope.query_data={
					auditStatus:$scope.auditStatus,
					beginNo:0,
					endNo:10
			};
			
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			BankTicketLimitService.query($scope.query_data,'bankTicketLimitAmountVerify/limits/queryBrand',function(data,response_info){
				$.messager.progress('close');
				$('#branddatagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});
		}
		if($('#specialdealerdatagrid').length>0){
			var datagrid=$('#specialdealerdatagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankTicketLimitService.query($scope.query_data,'bankTicketLimitAmountVerify/limits/querySpecialDealer',function(data,response_info){
						$.messager.progress('close');
						$('#specialdealerdatagrid').datagrid('loadData',data);
					},function(data,response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
						console.log("error:"+data);
					});
				}
			});
		}

		/**
		 * 监听切换tabs事件
		 */
		$('.sgm_tabs').tabs({
			onSelect:function(title){				
				if(title=='审核特殊经销商'){
					typeName='specialDealer';
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					var fields=['渠道类型','品牌'];
					CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
						$.messager.progress('close');
							
						var typesId=['#dealerType','#brand'];
							
						$timeout(function(){
							for(var i=0;i<data.length;i++){
								if(!folUtil.isNull(data[i])){
									$(typesId[i]).combobox('loadData',data[i]);
									$(typesId[i]).combobox('select',data[i][0]['code']);
								}
							}
						});
					},function(data,response_info){
						$.messager.progress('close');
							if(response_info.state===STATE.TIMEOUT){
								console.log('timeout');
								$.messager.alert('提示','系统超时请稍后访问');
							}
						console.log("error:"+data);
					});

					BankTicketLimitService.query($scope.query_data,'bankTicketLimitAmountVerify/limits/querySpecialDealer',function(data,response_info){
						$.messager.progress('close');
						
						$('#specialdealerdatagrid').datagrid('loadData',{total:0,rows:[]});
						$('#specialdealerdatagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}else{
					typeName='brand';

					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					$scope.query_data={
							beginNo:0,
							endNo:10
					};
					
					BankTicketLimitService.query($scope.query_data,'bankTicketLimitAmountVerify/limits/queryBrand',function(data,response_info){
						$.messager.progress('close');
						$('#branddatagrid').datagrid('loadData',{total:0,rows:[]});
						$('#branddatagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}
			}
		});
		
	});	
	
	/**
	 * 品牌银票限额审核通过
	 */
	$scope.auditSuccessBrand=function(){
		var rows=$('#branddatagrid').datagrid('getChecked');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankTicketLimitService.auditSuccess(rows,'bankTicketLimitAmountVerify/limits/auditSuccess',function(data,response_info){
			$.messager.progress('close');
			$.messager.alert('提示','审核成功');
			BankTicketLimitService.query($scope.query_data,'bankTicketLimitAmountVerify/limits/queryBrand',function(data,response_info){
				$.messager.progress('close');
				$('#branddatagrid').datagrid('loadData',{total:0,rows:[]});
				$('#branddatagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});		
		},function(response_info){
			$.messager.progress('close');
			$.messager.alert('提示','审核失败请检查原因')
		});
	}
	
	/**
	 * 品牌银票限额审核驳回
	 */
	$scope.rejectBrand=function(){
		var rows=$('#branddatagrid').datagrid('getChecked');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankTicketLimitService.reject(rows,'bankTicketLimitAmountVerify/limits/reject',function(data,response_info){
			$.messager.progress('close');
			$.messager.alert('提示','驳回成功');
			BankTicketLimitService.query($scope.query_data,'bankTicketLimitAmountVerify/limits/queryBrand',function(data,response_info){
				$.messager.progress('close');
				$('#branddatagrid').datagrid('loadData',{total:0,rows:[]});
				$('#branddatagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});		
		},function(response_info){
			$.messager.progress('close');
			$.messager.alert('提示','驳回失败请检查原因')
		});
	};
	
	
	/**
	 * 查询银行特殊经销商
	 */
	$scope.find=function(){
		$scope.istableshow=true;
		
		$scope.query_data={
				dealerType:folUtil.getComboBoxDataById('dealerType'),
				sapCode:$('#sapCode').textbox('getValue'),
				dealerName:$('#dealerName').textbox('getValue'),
				brandId:folUtil.getComboBoxDataById('brand'),
				beginNo:0,
				endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankTicketLimitService.query($scope.query_data,'bankTicketLimitAmountVerify/limits/querySpecialDealer',function(data,response_info){
			$.messager.progress('close');
			$('#specialdealerdatagrid').datagrid('loadData',{total:0,rows:[]});
			$('#specialdealerdatagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};
	
	/**
	 * 特殊经销商银票限额审核通过
	 */
	$scope.auditSuccess=function(){
		var rows=$('#specialdealerdatagrid').datagrid('getChecked');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}
	
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankTicketLimitService.auditSuccess(rows,'bankTicketLimitAmountVerify/limits/auditSuccess',function(data,response_info){
			$.messager.progress('close');
			$.messager.alert('提示','审核成功');
			BankTicketLimitService.query($scope.query_data,'bankTicketLimitAmountVerify/limits/querySpecialDealer',function(data,response_info){
				$.messager.progress('close');
				$('#specialdealerdatagrid').datagrid('loadData',{total:0,rows:[]});
				$('#specialdealerdatagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});		
		},function(response_info){
			$.messager.progress('close');
			$.messager.alert('提示','审核失败请检查原因')
		});
	}
	
	/**
	 * 特殊经销商银票限额审核驳回
	 */
	$scope.reject=function(){
		var rows=$('#specialdealerdatagrid').datagrid('getChecked');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankTicketLimitService.reject(rows,'bankTicketLimitAmountVerify/limits/reject',function(data,response_info){
			$.messager.progress('close');
			$.messager.alert('提示','驳回成功');
			BankTicketLimitService.query($scope.query_data,'bankTicketLimitAmountVerify/limits/querySpecialDealer',function(data,response_info){
				$.messager.progress('close');
				$('#specialdealerdatagrid').datagrid('loadData',{total:0,rows:[]});
				$('#specialdealerdatagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});		
		},function(response_info){
			$.messager.progress('close');
			$.messager.alert('提示','驳回失败请检查原因')
		});
	}
}]);



/**
 *票据贴息年化利率审核
 */
FolController.controller('TicketInterestRateAuditController',['$scope','$timeout','$state','$q','TicketInterestRateAuditService',function($scope,$timeout,$state,$q,TicketInterestRateAuditService){
	$scope.istableshow=false;
	
	$timeout(function(){
		$('#datagrid').datagrid().datagrid('enableCellEditing');

		if($('#datagrid').length>0){
			var datagrid=$('#datagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					TicketInterestRateAuditService.query($scope.query_data,'interestRate/audit/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagrid').datagrid('loadData',{total:0,rows:[]});
						$('#datagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
			
				}
			
			});

			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			$scope.query_data={
					auditStatus:$scope.auditStatus,		
					beginNo:0,
					endNo:10
			};
			TicketInterestRateAuditService.query($scope.query_data,'interestRate/audit/query',function(data,response_info){
				$.messager.progress('close');
				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
				$('#datagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});
		}
	});

	/**
	 * 票据贴息年化利率审核通过
	 */
	$scope.auditSuccess=function(){

		var rows=$('#datagrid').datagrid('getChecked');

		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}
		
		var datas= [];
		$.each(rows,function(i,val){
			datas.push({auditMsg:val['auditMsg'],auditStatus:val['auditStatus']});
		});
	
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		$scope.query_data={
				auditStatus:$scope.auditStatus,		
				beginNo:0,
				endNo:10
		};

		TicketInterestRateAuditService.auditSuccess(datas,'interestRate/audit/auditSuccess',function(data,response_info){
			$.messager.alert('提示','审批成功');
			TicketInterestRateAuditService.query($scope.query_data,'interestRate/audit/query',function(data,response_info){
				$.messager.progress('close');
				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
				$('#datagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});				
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
				return;
			}
			$.messager.alert('提示','审核失败请检查原因');
		});	
	}
	
	/**
	 * 票据贴息年化利率驳回
	 */
	$scope.reject=function(){
		var rows=$('#datagrid').datagrid('getChecked');

		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}
		
		var datas= [];
		$.each(rows,function(i,val){
			datas.push({encryptId:val['encryptId'],auditMsg:val['auditMsg'],moneyDisplay:val['amount'],auditStatus:val['auditStatus']});
		});
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		$scope.query_data={
				auditStatus:$scope.auditStatus,		
				beginNo:0,
				endNo:10
		};
		TicketInterestRateAuditService.reject(datas,'interestRate/audit/reject',function(data,response_info){
			$.messager.alert('提示','驳回成功');
			TicketInterestRateAuditService.query($scope.query_data,'interestRate/audit/query',function(data,response_info){
				$.messager.progress('close');
				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
				$('#datagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});				
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
				return;
			}
			$.messager.alert('提示','驳回失败请检查原因');
		});
		
	}

}]);

/**
 * 票据贴息controller
 */
FolController.controller('BankTicketCollectController',['$scope','$timeout','$q','$state','BankTicketDeatilService','BankInfoService','CodeService',function($scope,$timeout,$q,$state,BankTicketDeatilService,BankInfoService,CodeService){
	$scope.istableshow=false;
	
	/**
	 * 数据初始化
	 */
	$timeout(function(){		
		if($('#datagrid').length>0){
			var datagrid=$('#datagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankTicketDeatilService.query($scope.query_data,'bankTicket/collect/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}
			});
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	

		/**
		 * 查询所有银行
		 */
		BankInfoService.findAll('bankTicket/bankInfo/findAll',function(data,response_info){
			$.messager.progress('close');	

			if(!folUtil.isNull(data)){
				var bank=[];
				bank.push({bankAbbr:'-1',bankChName:'请选择'});
				$.each(data.rows,function(i,val){
					bank.push(val);
				});
				console.log(data);
				console.log(bank);
				$timeout(function(){	
					$('#bankId').combobox('loadData',bank);
					$('#bankId').combobox('select',bank[0]['bankAbbr']);
				});
			}
			
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});

		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		/**
		 * 查询所有渠道类型
		 */
		var fields=['渠道类型'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
			
			var typesId=['#dealerType'];
			
			$timeout(function(){
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
	});
	
	/**
	 * 银票汇总查询
	 */
	$scope.query=function(){
		$scope.istableshow=true;
		
		$scope.query_data={
				dealerType:folUtil.getComboBoxDataById('dealerType'),
				bankId:folUtil.getComboBoxDataById('bankId'),
				beginNo:0,
				endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		BankTicketDeatilService.query($scope.query_data,'bankTicket/collect/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};
}]);
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
/**
 * 票据贴息controller
 */
FolController.controller('BankTicketInterestController',['$scope','$timeout','$q','$state','BankTicketInterestService','BankInfoService',function($scope,$timeout,$q,$state,BankTicketInterestService,BankInfoService){
	$scope.istableshow=false;
	
	/**
	 * 数据初始化
	 */
	function init(){	
		$timeout(function(){
			if($('#datagrid').length>0){
				var datagrid=$('#datagrid').datagrid('getPager');
				datagrid.pagination({
					onSelectPage:function(pageNumber,pageSize){
						$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
						$scope.query_data.endNo=pageSize*pageNumber;
						
						$.messager.progress({
							title:'Please waiting',
							msg:'Loading data...',
							interval:PROGRESS_ACTION_TIMEOUT
						});	
						
						BankTicketInterestService.query($scope.query_data,'bankTicket/bankTicketInterest/query',function(data,response_info){
							$.messager.progress('close');
							$('#datagrid').datagrid('loadData',data);
						},function(response_info){
							$.messager.progress('close');
							if(response_info.state===STATE.TIMEOUT){
								console.log('timeout');
								$.messager.alert('提示','系统超时请稍后访问');
							}
							console.log("error:"+response_info);
						});
					}
				});
			}			
		},PROGRESS_ACTION_TIMEOUT);
	}
	
	$timeout(function(){
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	

		BankInfoService.findAll('bankTicket/bankInfo/findAll',function(data,response_info){
			$.messager.progress('close');	

			if(!folUtil.isNull(data)){
				var bank=[];
				bank.push({bankAbbr:'-1',bankChName:'请选择'});
				$.each(data.rows,function(i,val){
					bank.push(val);
				});
				$timeout(function(){	
					$('#bankId').combobox('loadData',bank);
					$('#bankId').combobox('select',bank[0]['bankAbbr']);
				});
			}
			
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
		
		var documentNumber=[];
		documentNumber.push({value:'-1',displayName:'请选择'});
		documentNumber.push({value:'0',displayName:'无'});
		documentNumber.push({value:'1',displayName:'有'});
		
		$timeout(function(){	
			$('#documentNumberExists').combobox('loadData',documentNumber);
			$('#documentNumberExists').combobox('select',documentNumber[0]['value']);
		});
	});
	
	/**
	 * 票据贴息查询
	 */
	$scope.query=function(){
		init();
		$scope.istableshow=true;

		$scope.query_data={
				sapCode:$('#sapCode').textbox('getValue'),
				bankId:folUtil.getComboBoxDataById('bankId'),
				acceptanceNumber:$('#acceptanceNumber').textbox('getValue'),
				documentNumberExists:folUtil.getComboBoxDataById('documentNumberExists'),
				startIssueDate:$('#startIssueDate').datebox('getValue'),
				endIssueDate:$('#endIssueDate').datebox('getValue'),
				beginNo:0,
				endNo:10
		};
		
		if(!check()){
			return;
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankTicketInterestService.query($scope.query_data,'bankTicket/bankTicketInterest/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
			console.log("error:"+response_info);
		});
	};
	
	/**
	 * 检查条件
	 */
	function check(){
		if($('#startIssueDate').datebox('getValue')>$('#endIssueDate').datebox('getValue')){
			$.messager.alert('提示','起始开票日期不能大于结束开票日期');
			return false;
		}
		
		return true;
	}
}]);
/**
 * 银票贴息明细Controllor
 */
FolController.controller('BankTicketInterestDeatilCtrl',['$scope','$rootScope','$timeout','$q','$state','BankTicketInterestDeatilService',function($scope,$rootScope,$timeout,$q,$state,BankTicketInterestDeatilService){

$scope.istableshow=false;
	
	$timeout(function(){		
		if($('#interestDatagrid').length>0){
			var datagrid=$('#interestDatagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
				
					BankTicketInterestDeatilService.query($scope.query_data,'bankTicket/interestDeatil/query',function(data,response_info){
						$.messager.progress('close');
						$('#interestDatagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}
			});
		}
		
	});

	/**
	 * 贴息明细查询
	 */
	$scope.query=function(){
		$('#interestDataTable').attr('class','row-fluid ng-show');
		
		$scope.istableshow=true;
		
		$scope.query_data={
				acceptanceNumber:$('#acceptanceNumber').textbox('getValue'),
				documentNumber:$('#documentNumber').textbox('getValue'),
				startIssueDate:$('#startIssueDate').datebox('getValue'),
				endIssueDate:$('#endIssueDate').datebox('getValue'),
				startExpirationDate:$('#startExpirationDate').datebox('getValue'),
				endExpirationDate:$('#endExpirationDate').datebox('getValue'),
				sapCode:$rootScope.bankticket_interest_deatil_data['sapCode'],
				year:$rootScope.bankticket_interest_deatil_data['year'],
				month:$rootScope.bankticket_interest_deatil_data['month'],
				beginNo:0,
				endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		BankTicketInterestDeatilService.query($scope.query_data,'bankTicket/interestDeatil/query',function(data,response_info){
			$.messager.progress('close');
			$('#interestDatagrid').datagrid('loadData',{total:0,rows:[]});
			$('#interestDatagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};
}]);

/**
 * 票据贴息清单controller
 */
FolController.controller('BankTicketInterestInventoryController',['$scope','$rootScope','$timeout','$q','$state','$window','BankTicketInterestInventoryService',function($scope,$rootScope,$timeout,$q,$state,$window,BankTicketInterestInventoryService){
	$scope.istableshow=false;
	
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		folUtil.setComboBoxYearDataById('year');
		folUtil.setComboBoxMonthDataById('month');
		
		if($('#datagrid').length>0){
			var datagrid=$('#datagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankTicketInterestInventoryService.query($scope.query_data,'bankTicket/businessInquiries/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}
			
			});
			$('#interestDeatilWindow').panel({
				onBeforeOpen:function(){
					$('#interestDataTable').attr('class','row-fluid ng-hide');
				}
			});
		}
	});
	
	/**
	 * 查询每月的票据贴息汇总
	 */
	$scope.query=function(){
		$scope.istableshow=true;		
		
		$scope.query_data={
				year:folUtil.getComboBoxDataById('year'),
				month:folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month')),
				sapCode:$('#sapCode').textbox('getValue'),
				beginNo:0,
				endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankTicketInterestInventoryService.query($scope.query_data,'bankTicket/businessInquiries/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	}

	/**
	 * 导出文件
	 */
	$scope.exportFile=function(){
		if(folUtil.isNull($scope.query_data)){
			$.messager.alert('提示','请先查询一次再进行操作');
			return;
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankTicketInterestInventoryService.getToken('services/tokens',function(data){
			$.messager.progress('close');
			var url='bankTicket/businessInquiries/exportInterestAmount?year='+$scope.query_data.year+'&month='+$scope.query_data.month+'&token='+data['token'];
			console.log(url);
			$window.open(url);
		},function(data){
			$.messager.progress('close');
			console.log(data);
		});
	};
	
	/**
	 * 导入文件
	 */
	$scope.importFile=function(){
		$state.go('uploadFilePage',{pageName:'bankTicketInterest'});
	};
	
	/**
	 * 银票贴息
	 */
	$scope.BankTicketInterestInventoryUpload=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		if(rows.length<=0){
			$.messager.alert('提示','选择一条记录，再操作');
		}else if(rows.length>1){
			$.messager.alert('提示','只能一条记录，再操作');
		}else{
			BankTicketInterestInventoryService.InterestInventoryUpload(rows[0],'bankTicket/businessInquiries/sendInterestAmount',function(data,response_info){
				console.log('suceess');
			},function(response_info){
				console.log('fail');
			});
		}
	};
	
	/**
	 * 票据开票清单发布
	 */
	$scope.issue=function(){
		var rows=$('#datagrid').datagrid('getChecked');
		if(rows.length<=0){
			$.messager.alert('提示','选择一条记录，再操作');
			return;
		}
		var ids = "";
		for (var i = 0; i < rows.length; i++) {
			if(rows[i]['issueStatus']==STATE.SUCCESS_BANK_TICKET_ISSUE){
				$.messager.alert('提示','你选择的第'+(i+1)+'行记录已发布过不能再次发布');
				return;
			}
			ids += rows[i]['id']+",";
		}
		
			BankTicketInterestInventoryService.issue({ids:ids},'bankTicket/businessInquiries/issue',function(response_info){
				$.messager.alert('提示', '发布成功');
				
				BankTicketInterestInventoryService.query($scope.query_data,'bankTicket/businessInquiries/query',function(data,response_info){
					$('#datagrid').datagrid('loadData',{total:0,rows:[]});
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});
			},function(response_info){
				$.messager.alert('提示', '发布失败');
			});
	};

	/**
	 * 查看银票贴息明细
	 */
	$scope.bankTicketInterestDeatil=function(){
		var rows=$('#datagrid').datagrid('getChecked');
		if(rows.length<=0){
			$.messager.alert('提示','选择一条记录，再操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示','只能一条记录，再操作');
			return;
		}
		
		rows[0]['year']=folUtil.getComboBoxDataById('year');
		rows[0]['month']=folUtil.getComboBoxDataById('month');
		$rootScope.bankticket_interest_deatil_data=rows[0];
		$('#interestDeatilWindow').window('open');
	//	$state.go('bankTicketInterestDeatil');
	};
}]);
/**
 * 银票异常清单Controller
 * shenweiwei
 */
FolController.controller('BankTicketExceptionInventoryController',['$scope','$timeout','$window','BankTicketExceptionInventoryService',function($scope,$timeout,$window,BankTicketExceptionInventoryService){
	$scope.istableshow=false;
	/**
	 * 数据初始化
	 */
	$timeout(function(){		
		if($('#datagrid').length>0){
			var datagrid=$('#datagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankTicketExceptionInventoryService.query($scope.query_data,'bankticket/exception/inventory',function(data,response_info){
						$.messager.progress('close');
						$('#datagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}
			});
		}
	});
	
	$scope.query=function(){
		$scope.istableshow=true;
		
		$scope.query_data={
				sapCode:$('#sapCode').textbox('getValue'),
				acceptanceNumber:$('#acceptanceNumber').textbox('getValue'),
				beginNo:0,
				endNo:10
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankTicketExceptionInventoryService.query($scope.query_data,'bankticket/exception/inventory',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{rows:[],total:0});
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};
	
	$scope.exportFile=function(){
		if(folUtil.isNull($scope.query_data)){
			$.messager.alert('提示','请先查询一次再导出');
			return;
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankTicketExceptionInventoryService.getToken('services/tokens',function(data){
			$.messager.progress('close');
			$window.open('bankticket/exception/exportQueryResult?sapCode='+$scope.query_data.sapCode+'&acceptanceNumber='+$scope.query_data.acceptanceNumber+'&token='+data['token']);
		},function(response_info){
			$.messager.progress('close');
			console.log(response_info);
		});
		
	};
}]);
/**
 * 奖金余额对账
 */
FolController.controller('bonusAmountReconcileCtrl',['$scope','$rootScope','$timeout','BonusService','$window',function($scope,$rootScope,$timeout,BonusService,$window){
	$scope.istableshow=false;
	
	/**
	 * 数据初始化，因为使用NG-IF所以要给执行语句时间
	 */
	function init(){
		$timeout(function(){
			if($('#datagrid').length>0){
				var datagrid=$('#datagrid').datagrid('getPager');
				datagrid.pagination({
					onSelectPage:function(pageNumber,pageSize){
		
						$scope.bonus_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
						$scope.bonus_data.endNo=pageSize*pageNumber;
						$.messager.progress({
							title:'Please waiting',
							msg:'Loading data...',
							interval:PROGRESS_ACTION_TIMEOUT
						});	
						BonusService.findBonusAmount($scope.bonus_data,'bonus/bonusAmountReconcile/query',function(data,response_info){
							$.messager.progress('close');
							$('#datagrid').datagrid('loadData',data);
						},function(response_info){
							$.messager.progress('close');
							if(response_info.state===STATE.TIMEOUT){
								console.log('timeout');
								$.messager.alert('提示','系统超时请稍后访问');
							}		
						});
					}
				});
			}
		},PROGRESS_ACTION_TIMEOUT);
	}
	
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		BonusService.findCodeTypeNames('bonus/bonusAmountReconcile/findCodeTypeNames',function(data,response_info){
			$.messager.progress('close');
			var typesId=['#dealerType'];

			$timeout(function(){
				$('#bonusType').combobox('loadData',$rootScope.bonusType);
				$('#bonusType').combobox('select',$rootScope.bonusType[0]['code']);
				folUtil.setComboBoxYearDataById('year');
				folUtil.setComboBoxMonthDataById('month');
				folUtil.setComboBoxYesAndNoById('isDiff');
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
	});
	
	/**
	 * 查询奖金余额对账
	 */
	$scope.findbonusAmountReconcile=function(){
		init();
		var changeTime=folUtil.getComboBoxDataById('year')+'-'+folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month'));
		$scope.bonus_data={dealerType:folUtil.getComboBoxDataById('dealerType'),
				bonusType:folUtil.getComboBoxDataById('bonusType'),
				changeTime:changeTime,
				isDiff:folUtil.getComboBoxDataById('isDiff'),
				endSapCode:$('#endSapCode').textbox('getValue'),
				startSapCode:$('#startSapCode').textbox('getValue'),
				year:folUtil.getComboBoxDataById('year'),
				month:folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month')),
				beginNo:0,
				endNo:10
			};
					
		$scope.istableshow=true;
			
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BonusService.findbonusAmountReconcile($scope.bonus_data,'bonus/bonusAmountReconcile/query',function(data,response_info){
				$.messager.progress('close');
				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
				$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};		
		
	/**
	 * 文件导出
	 */
	$scope.bonusAmountReconcile=function(){			
		BonusService.getToken('services/tokens',function(data,response_info){
			if(folUtil.isNull($scope.bonus_data)){
				$.messager.alert('提示','请先查询一次再导出');
				return;
			}
				
			$.messager.progress('close');
			$window.open('bonus/bonusAmountReconcile/amountReconcile?bonusType='+$scope.bonus_data.bonusType+'&isDiff='+$scope.bonus_data.isDiff+'&endSapCode='+$scope.bonus_data.endSapCode+'&startSapCode='+$scope.bonus_data.startSapCode+'&changeTime='+$scope.bonus_data.changeTime+'&year='+$scope.bonus_data.year+'&month='+$scope.bonus_data.month+'&dealerType='+$scope.bonus_data.dealerType+'&token='+data['token']);
		},function(response_info){
			console.log(response_info);
		});
	};
}]);

/**
 * 奖金月度明细
 */
FolController.controller('bonusMonthDeatilCtrl',['$scope','$rootScope','$timeout','BonusService','$window',function($scope,$rootScope,$timeout,BonusService,$window){
	$scope.istableshow=false;

	/**
	 * 数据初始化
	 */
	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				
				$scope.bonus_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
				$scope.bonus_data.endNo=pageSize*pageNumber;

				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
					
				BonusService.findBonusAmount($scope.bonus_data,'bonus/bonusMonthdeatil/query',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});
			}
		});
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		BonusService.findCodeTypeNames('bonus/bonusMonthdeatil/findCodeTypeNames',function(data,response_info){
			$.messager.progress('close');
			var typesId=['#dealerType'];
	
			$timeout(function(){
				$('#bonusType').combobox('loadData',$rootScope.bonusType);
				$('#bonusType').combobox('select',$rootScope.bonusType[0]['code']);
				folUtil.setComboBoxYearDataById('year');
				folUtil.setComboBoxMonthDataById('month');
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
	});

	/**
	 * 查询月度明细
	 */
	$scope.findBonusMonthDeatil=function(){
		var changeTime=folUtil.getComboBoxDataById('year')+'-'+folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month'));
		$scope.bonus_data={dealerType:folUtil.getComboBoxDataById('dealerType'),
				bonusType:folUtil.getComboBoxDataById('bonusType'),
				sapCode:$('#sapCode').textbox('getValue'),
				dealerName:$('#dealerName').textbox('getValue'),
				changeTime:changeTime,
				beginNo:0,
				endNo:10
			};
		
		$scope.istableshow=true;
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	

		//查询变动奖金
		BonusService.findBonusMonthDeatil($scope.bonus_data,'bonus/bonusMonthdeatil/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};

	/**
	 * 文件导出
	 */
	$scope.monthDeatilResult=function(){
		BonusService.getToken('services/tokens',function(data,response_info){
			if(folUtil.isNull($scope.bonus_data)){
				$.messager.alert('提示','请先查询一次再导出');
				return;
			}
			
			$.messager.progress('close');
			$window.open('bonus/bonusMonthdeatil/monthDeatilResult?dealerName='+encodeURI(encodeURI($scope.bonus_data.dealerName))+'&sapCode='+$scope.bonus_data.sapCode+'&dealerType='+$scope.bonus_data.dealerType+'&bonusType='+$scope.bonus_data.bonusType+'&changeTime='+$scope.bonus_data.changeTime+'&token='+data['token']);
		},function(response_info){
			console.log(response_info);
		});
	};
	
	/**
	 * 订单查看
	 */
	$scope.findOrderDeatil=function(id){
		var rows=$('#'+id).datagrid('getSelections');
		if(rows.length<1){
			$.messager.alert('提示','请选择一条记录再操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示','不能选择多条记录，请选择一条记录再操作');
			return;
		}else if(rows[0]['referType'].indexOf('SAP')>-1){
			$.messager.alert('提示','该条记录不能查看订单相关信息');
			return;
		}
		$.each(rows,function(i,val){
			var referCode=val['referCode'];
			var nums=referCode.split('|');
			$window.open(POL_SERVER_URL+'index.html#/1300_130020?orderNo='+nums[0]);
		});
		
	};
	
	/**
	 * billing查看
	 */
	$scope.findBillingDeatil=function(id){
		var rows=$('#'+id).datagrid('getSelections');

		if(rows.length<1){
			$.messager.alert('提示','请选择一条记录再操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示','不能选择多条记录，请选择一条记录再操作');
			return;
		}else if(rows[0]['referType'].indexOf('SAP')>-1){
			$.messager.alert('提示','该条记录不能查看billing相关信息');
			return;
		}
		$.each(rows,function(i,val){
			var referCode=val['referCode'];
			var nums=referCode.split('|')
			$window.open(POL_SERVER_URL+'index.html#/1300_130040?billingNo='+nums[1]);
		});
	}
}]);

/**
 * 奖金余额汇总表
 */
FolController.controller('bonusAmountCollectCtrl',['$scope','$rootScope','$timeout','BonusService','$window',function($scope,$rootScope,$timeout,BonusService,$window){
	$scope.istableshow=false;
	
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){

				$scope.bonus_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
				$scope.bonus_data.endNo=pageSize*pageNumber;
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				BonusService.findBonusAmount($scope.bonus_data,'bonus/amountReconcileCollect/query',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}	
				});
			}
		});
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BonusService.findCodeTypeNames('bonus/amountReconcileCollect/findCodeTypeNames',function(data,response_info){
			$.messager.progress('close');
			var typesId=['#typeName'];

			$timeout(function(){
			
				folUtil.setComboBoxYearDataById('year');
				folUtil.setComboBoxMonthDataById('month');
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
	});
		
	/**
	 * 查询奖金余额汇总
	 */
	$scope.findBonusAmountCollect=function(){
		var changeTime=folUtil.getComboBoxDataById('year')+'-'+folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month'));
		$scope.bonus_data={
				typeName:folUtil.getComboBoxDataById('typeName'),
				changeTime:changeTime,
				year:folUtil.getComboBoxDataById('year'),
				month:folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month')),
				beginNo:0,
				endNo:10
			};
		
		$scope.istableshow=true;
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		BonusService.findBonusAmountCollect($scope.bonus_data,'bonus/amountReconcileCollect/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};	

	/**
	 * 文件导出
	 */
	$scope.amountReconcileCollect=function(){
		
		BonusService.getToken('services/tokens',function(data,response_info){
			if(folUtil.isNull($scope.bonus_data)){
				$.messager.alert('提示','请先查询一次再导出');
				return;
			}
			
			$window.open('bonus/amountReconcileCollect/amountReconcileCollect?typeName='+$scope.bonus_data.typeName+'&changeTime='+$scope.bonus_data.changeTime+'&year='+$scope.bonus_data.year+'&month='+$scope.bonus_data.month+'&token='+data['token']);
		},function(response_info){
			console.log(response_info);
		});
	};
}]);
/**
 * 奖金定义controller
 */
FolController.controller('BonusDefinitionController',['$scope','$timeout','$state','BonusDefinitionSerivce',function($scope,$timeout,$state,BonusDefinitionSerivce){
	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.codeVo.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.codeVo.endNo=pageSize*pageNumber;
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				BonusDefinitionSerivce.queryBonusDefinition($scope.codeVo,'bonus/type/query',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});
			}
		});
	});
	
	$scope.addBonusDefinition=function(){
		$state.go('addBonusDefinition');
	};
	
	$scope.queryBonusDefinition=function(){
		$scope.istableshow=true;
		
		$scope.codeVo={
				code:$scope.code,
				codeCnDesc:$scope.codeCnDesc,
				beginNo:0,
				endNo:10
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BonusDefinitionSerivce.queryBonusDefinition($scope.codeVo,'bonus/type/query',function(data,response_info){
			$.messager.progress('close');
			if(data.length===0){
				$scope.istableshow=false;
				$.messager.alert('提示','没有相关符合的数据');
			}else{
				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
				$('#datagrid').datagrid('loadData',data);
			}
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				$scope.istableshow=false;
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};
	
	$scope.updateBonusDefinition=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length>1){
			$.messager.alert('提示', '不能选择多个奖金类型，请重新选择');
			return;
		}else if(rows.length<1){
			$.messager.alert('提示', '请选择一个奖金类型，请重新选择');
			return;
		}
		
		$state.go('updateBonusDefinition',{bonusType: rows[0]});
	};
}]);

/**
 * 新增奖金定义controller
 */
FolController.controller('AddBonusDefinitionController',['$scope','$timeout','$state','BonusDefinitionSerivce',function($scope,$timeout,$state,BonusDefinitionSerivce){
	$scope.addBonusDefinition=function(){
		var codeVo={
			code:$scope.code,
			codeCnDesc:$scope.codeCnDesc,
			remark:$scope.remark,
			typeName:'奖金类型'
		};
		
		if(!$scope.checkData(codeVo)){
			return;
		}
		
		BonusDefinitionSerivce.addBonusDefinition(codeVo,'bonus/type/add',function(data,response_info){
			$.messager.alert('提示','新增奖金成功');
			$state.go('bonusDefinition');
		},function(response_info){
			$.messager.alert('提示','新增奖金失败');
			console.log(response_info);
		});
	};
	
	$scope.checkData=function(data){
		if(folUtil.isNull(data.code)){
			$.messager.alert('提示', '请填写奖金代码，再继续操作');
			return false;
		}
		
		if(folUtil.isNull(data.codeCnDesc)){
			$.messager.alert('提示', '请填写奖金名称，再继续操作');
			return false;
		}
		return true;
	};
}]);

/**
 * 更新奖金定义controller
 */
FolController.controller('UpdateBonusDefinitionController',['$scope','$timeout','$state','$stateParams','BonusDefinitionSerivce',function($scope,$timeout,$state,$stateParams,BonusDefinitionSerivce){
	console.log($stateParams.bonusType);
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		$('#code').textbox('setValue',$stateParams.bonusType.code);
		$('#codeCnDesc').textbox('setValue',$stateParams.bonusType.codeCnDesc);
		$('#remark').textbox('setValue',$stateParams.bonusType.remark);
		$scope.code=$stateParams.bonusType.code;
		$scope.codeCnDesc=$stateParams.bonusType.codeCnDesc;
		$scope.remark=$stateParams.bonusType.remark;
		$scope.codeId=$stateParams.bonusType.codeId;
	});
	
	/**
	 * 更新bonusType
	 */
	$scope.updateBonusDefinition=function(){
		var codeVo={
			codeId:$scope.codeId,
			code:$scope.code,
			codeCnDesc:$scope.codeCnDesc,
			remark:$scope.remark,
		};
		
		if(!$scope.checkData(codeVo)){
			return;
		}
		
		BonusDefinitionSerivce.updateBonusDefinition(codeVo,'bonus/type/update',function(data,response_info){
			$.messager.alert('提示','更新奖金成功');
			$state.go('bonusDefinition');
		},function(response_info){
			$.messager.alert('提示','更新奖金失败');
			console.log(response_info);
		});
	};
	
	/**
	 * 检查数据
	 */
	$scope.checkData=function(data){
		if(folUtil.isNull(data.code)){
			$.messager.alert('提示', '请填写奖金代码，再继续操作');
			return false;
		}
		
		if(folUtil.isNull(data.codeCnDesc)){
			$.messager.alert('提示', '请填写奖金名称，再继续操作');
			return false;
		}
		return true;
	};
}]);
/**
 * 奖金发放
 */
FolController.controller('BonusIssueController',['$scope','$timeout','$q','$window','BonusIssueService',function($scope,$timeout,$q,$window,BonusIssueService){
	$scope.istableshow=false;
	
	$timeout(function(){
		if($('#datagrid').length>0){
			var datagrid=$('#datagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BonusIssueService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}
			});
		}
	});
	
	/**
	 * 奖金待发放的查询
	 */
	$scope.query=function(){
		$scope.istableshow=true;
		
		$scope.query_data={
				batchNo:$scope.batchNo,
				matchState:STATE.SUCCESS_AUDIT,
				issueStatus:STATE.WAIT_ISSUE,
				type:TYPE.BONUS_ISSUE,
				beginNo:0,
				endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BonusIssueService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	}
	
	/**
	 * 奖金发放
	 */
	$scope.bonusIssue=function(){	
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length<1){
			$.messager.alert('提示', '请选择一个文件在发放');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多个文件在发放');
			return;
		}else{
			var defferred=$q.defer();
			var promise=defferred.promise;
			
			// BonusIssueService.issue(rows[0],'bonus/bonusissue/payBonus',function(data,response_info){
			// 	defferred.resolve('success');
			// },function(data,response_info){
			// 	defferred.reject('fail');
			// });
			
			// promise.then(function(data){
			// 	$.messager.alert('提示', '奖金发放成功');
				
			// 	BonusIssueService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
			// 		$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			// 		$('#datagrid').datagrid('loadData',data);
			// 	},function(response_info){
			// 		if(response_info.state===STATE.TIMEOUT){
			// 			console.log('timeout');
			// 			$.messager.alert('提示','系统超时请稍后访问');
			// 		}
			// 	});
			// },function(data){
			// 	if(response_info.state===STATE.TIMEOUT){
			// 		console.log('timeout');
			// 		$.messager.alert('提示','系统超时请稍后访问');
			// 	}else{
			// 		$.messager.alert('提示', '奖金发放失败');
			// 	}
				
			// 	BonusIssueService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
			// 		$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			// 		$('#datagrid').datagrid('loadData',data);
			// 	},function(response_info){
			// 		if(response_info.state===STATE.TIMEOUT){
			// 			console.log('timeout');
			// 			$.messager.alert('提示','系统超时请稍后访问');
			// 		}
			// 	});
			// });

			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			BonusIssueService.issue(rows[0],'bonus/bonusissue/payBonus',function(data,response_info){
				$.messager.progress('close');
				defferred.resolve('success');
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					defferred.resolve('timeout');
				}else{
					defferred.reject('fail');
				}
			});

			promise.then(function(msg){
				if(msg==='success'){
					$.messager.alert('提示', '奖金发放成功');
				}else if(msg='timeout'){
					$.messager.alert('提示', '奖金发放操作正在进行中，由于执行时间长请稍后查询结果');
				}
				
				BonusIssueService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
					$('#datagrid').datagrid('loadData',{total:0,rows:[]});
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});
			},function(msg){
				$.messager.alert('提示', '奖金发放失败');
				
				BonusIssueService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
					$('#datagrid').datagrid('loadData',{total:0,rows:[]});
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});
			});
		}
	};
	
	
	
	/**
	 * 奖金回退
	 */
	$scope.back=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length<1){
			$.messager.alert('提示', '请选择一个文件在发放');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多个文件在发放');
			return;
		}else{
			BonusIssueService.issue(rows,'bonus/bonusissue/bonusReject',function(data,response_info){
				defferred.resolve('success');
			},function(data,response_info){
				defferred.reject('fail');
			});
			
			promise.then(function(){
				$.messager.alert('提示', '奖金回退成功');
				
				BonusIssueService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
					$('#datagrid').datagrid('loadData',{total:0,rows:[]});
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});
			},function(){
				$.messager.alert('提示', '奖金回退失败');
				
			});
		}
	};
	
	//导出
	$scope.exportFile=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		if(rows.length<1){
			$.messager.alert('提示', '请选择一个奖金记录再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多个奖金记录进行操作');
			return;
		}else{
			BonusIssueService.getToken('services/tokens',function(data){
				$.messager.progress('close');
				$window.open('bonus/bonusissue/exportFile?issueState='+STATE.WAIT_ISSUE+'&batchNo='+rows[0]['batchNo']+'&token='+data['token']);
			},function(response_info){
				console.log(response_info);
			});
		}
	}
}]);

/**
 * 奖金发放成功
 */
FolController.controller('BonusIssueSuccessController',['$scope','$timeout','$q','$window','BonusIssueService',function($scope,$timeout,$q,$window,BonusIssueService){
	$timeout(function(){
		/**
		 * 奖金颁发的表格分页
		 */
		if($('#bonusIssueGridDate').length>0){
			var bonusIssueGridDate=$('#bonusIssueGridDate').datagrid('getPager');
			
			bonusIssueGridDate.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.bonus_Issue_Query_Condition.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.bonus_Issue_Query_Condition.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BonusIssueService.query($scope.bonus_Issue_Query_Condition,'bonus/bonusissue/queryBonusIssueSuc',function(data,response_info){
						$.messager.progress('close');
						$('#bonusIssueGridDate').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}
			});
		}
	});
	/**
	 * 奖金颁发查询
	 */
	$scope.bonusIssueQuery=function(){
		$scope.istableshow=true;
		
		$scope.bonus_Issue_Query_Condition={
				batchNo:$('#bonusIssueBatchNo').textbox('getValue'),
				beginDate:$('#bonusIssueBeginDate').datebox('getValue'),
				endDate:$('#bonusIssueEndDate').datebox('getValue'),
				beginNo:0,
				endNo:10
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BonusIssueService.bonusIssueQuery($scope.bonus_Issue_Query_Condition,'bonus/bonusissue/queryBonusIssueSuc',function(data,response_info){
			$.messager.progress('close');
			$('#bonusIssueGridDate').datagrid('loadData',{total:0,rows:[]});
			$('#bonusIssueGridDate').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};
	//导出
	$scope.exportFile=function(){
		var rows=$('#bonusIssueGridDate').datagrid('getSelections');
		if(rows.length<1){
			$.messager.alert('提示', '请选择一个奖金记录再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多个奖金记录进行操作');
			return;
		}else{
			BonusIssueService.getToken('services/tokens',function(data){
				$.messager.progress('close');
				$window.open('bonus/bonusissue/exportFile?issueState='+STATE.SUCCESS_ISSUE+'&batchNo='+rows[0]['batchNo']+'&token='+data['token']);
			},function(response_info){
				console.log(response_info);
			});
		}
	}
}]);

/**
 * 奖金发放文件审核controller
 */
FolController.controller('BonusIssueVersionFileAuditController',['$scope','$timeout','$q','$window','BonusIssueVersionFileAuditService','CodeService',function($scope,$timeout,$q,$window,BonusIssueVersionFileAuditService,CodeService){
	$scope.istableshow=false;
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.query_data.endNo=pageSize*pageNumber;
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				BonusIssueVersionFileAuditService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});
			}
		});
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		var fields=['渠道类型','奖金类型'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
			
			var typesId=['#dealerType','#bonusType'];
			
			$timeout(function(){
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
	});
	
	/**
	 * 奖金发放文件查询
	 */
	$scope.query=function(){
		$scope.istableshow=true;
		
		$scope.query_data={
				dealerType:folUtil.getComboBoxDataById('dealerType'),
				bonusType:folUtil.getComboBoxDataById('bonusType'),
				beginDate:$('#startTime').datebox('getValue'),
				endDate:$('#endTime').datebox('getValue'),
				batchNo:$scope.batchNo,
				matchState:STATE.REAST_AUDIT,
				type:TYPE.BONUS_AUDIT,
				beginNo:0,
				endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BonusIssueVersionFileAuditService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};
	
	/**
	 * 奖金发放文件审核通过
	 */
	$scope.audit=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length<1){
			$.messager.alert('提示', '请选择一条记录再进行操作');
			return;
		}
		
		BonusIssueVersionFileAuditService.audit(rows,'bonus/bonusissue/bonusAudit',function(response_info){
			defferred.resolve('success');
		},function(response_info){
			defferred.reject('fail');
		});
		
		
		promise.then(function(){
			$.messager.alert('提示','奖金发放文件审核成功');
			BonusIssueVersionFileAuditService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
				$.messager.progress('close');
				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
				$('#datagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});
		},function(){
			$.messager.alert('提示','奖金发放文件审核失败');
		});
	};
	
	/**
	 * 奖金发放文件驳回
	 */
	$scope.reject=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var rows=$('#datagrid').datagrid('getSelections');
//		
		if(rows.length<1){
			$.messager.alert('提示', '请选择一条记录再进行操作');
			return;
		}
		
		BonusIssueVersionFileAuditService.reject(rows,'bonus/bonusissue/bonusReject',function(data,response_info){
			defferred.resolve('success');
		},function(response_info){
			defferred.reject('fail');
		});
		
		promise.then(function(){
			$.messager.alert('提示','奖金发放文件驳回成功');
			BonusIssueVersionFileAuditService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
				$.messager.progress('close');
				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
				$('#datagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});
		},function(){
			$.messager.alert('提示','奖金发放文件驳回失败');
		});
	};
	
	//导出
	$scope.exportFile=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		if(rows.length<1){
			$.messager.alert('提示', '请选择一个奖金记录再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多个奖金记录进行操作');
			return;
		}else{
			BonusIssueVersionFileAuditService.getToken('services/tokens',function(data){
				$.messager.progress('close');
				$window.open('bonus/bonusissue/exportFile?issueState='+STATE.FAIL_ISSUE+'&batchNo='+rows[0]['batchNo']+'&token='+data['token']);
			},function(response_info){
				console.log(response_info);
			});
		}
	};
}]);
/**
 * 奖金发放版本上传controller
 */
FolController.controller('BonusIssueVersionUploadController',['$rootScope','$scope','$state','$timeout','$q','$window','BonusIssueVersionUploadService','CodeService',function($rootScope,$scope,$state,$timeout,$q,$window,BonusIssueVersionUploadService,CodeService){
	/**
	 * 数据初始化
	 */
	$scope.istableshow=false;
	
	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.query_data.endNo=pageSize*pageNumber;
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				BonusIssueVersionUploadService.issueVersionQuery($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});
			}
		});
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		var fields=['渠道类型','奖金类型'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
				
			var typesId=['#dealerType','#bonusType'];
				
			$timeout(function(){
	//				$('#bonusType').combobox('loadData',$rootScope.bonusType);
	//				$('#bonusType').combobox('select',$rootScope.bonusType[0]['code']);
				
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
	});
	
	/**
	 * 发放版本查询
	 */
	$scope.issueVersionQuery=function(){
		$scope.istableshow=true;
		
		$scope.query_data={
			dealerType:folUtil.getComboBoxDataById('dealerType'),
			bonusType:folUtil.getComboBoxDataById('bonusType'),
			beginDate:$('#startTime').datebox('getValue'),
			endDate:$('#endTime').datebox('getValue'),
			batchNo:$scope.batchNo,
			matchState:STATE.WAIT_AUDIT,
			type:TYPE.BONUS_UPLOAD,
			beginNo:0,
			endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BonusIssueVersionUploadService.issueVersionQuery($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};
	
	/**
	 * 发放版本重新上传
	 */
	$scope.issueVersionReUpload=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		if(rows.length>1){
			$.messager.alert('提示','不能选择多条记录重新上传');
		}else if(rows.length<1){
			$.messager.alert('提示','请选择一条记录重新上传');
		}else{
			var data=new Array();
			$.each(rows[0],function(i,val){
				if(!folUtil.isNull(val)&&!folUtil.isNull(val['publishDate'])){
					val['publishDate']=null;
				}
				data.push(val);
			});
			$state.go('uploadFilePage',{pageName:'bonusIssueVersion',data:data});
		}
	};
	
	/**
	 * 作废
	 */
	$scope.cancel=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length<1){
			$.messager.alert('提示','请选择一条记录再进行作废操作');
		}else{
			var defferred=$q.defer();
			var promise=defferred.promise;
			
			BonusIssueVersionUploadService.cancel(rows,'bonus/bonusissue/cancalBonusFile',function(data,response_info){
				defferred.resolve('success');
			},function(data,response_info){
				defferred.resvole('reject');
			});
			
			promise.then(function() {
				$.messager.alert('提示', '发放文件作废成功');
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				BonusIssueVersionUploadService.issueVersionQuery($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',{total:0,rows:[]});
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});
			},function(){
				$.messager.alert('提示', '发放文件作废失败');
			});
			
		}
	};
	
	/**
	 * 确认需要审核
	 */
	$scope.confirmAudit=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length<1){
			$.messager.alert('提示','请选择一条记录再进行作废操作');
		}else{
			var defferred=$q.defer();
			var promise=defferred.promise;
			
			BonusIssueVersionUploadService.confirmAudit(rows,'bonus/bonusissue/confirmAudit',function(data,response_info){
				defferred.resolve('success');
			},function(response_info){
				defferred.resvole('reject');
			});
			
			promise.then(function() {
				$.messager.alert('提示', '发放文件已确认需要审核');
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				BonusIssueVersionUploadService.issueVersionQuery($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',{total:0,rows:[]});
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});
			},function(){
				$.messager.alert('提示', '发放文件确认需要审核失败');
			});
			
		}
	};
	
	/**
	 * 下载模板
	 */
	$scope.downloadTemp=function(){
		
		$.messager.progress({
			title:'Please waiting',
			msg:'downloading template...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BonusIssueVersionUploadService.getToken('services/tokens',function(data){
			$.messager.progress('close');
			var url='bonus/bonusissue/downloadTemp?token='+data['token'];
			console.log(url);
			$window.open(url);
		},function(response_info){
			$.messager.progress('close');
			console.log(response_info);
		});
	};
	
	/**
	 * 导入文件
	 */
	$scope.uploadBonusIssueVersion=function(){
		$state.go('uploadFilePage',{pageName:'bonusIssueVersion'});
	};
	
	//导出
	$scope.exportFile=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		if(rows.length<1){
			$.messager.alert('提示', '请选择一个奖金记录再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多个奖金记录进行操作');
			return;
		}else{
			BonusIssueVersionUploadService.getToken('services/tokens',function(data){
				$.messager.progress('close');
				$window.open('bonus/bonusissue/exportFile?issueState='+STATE.WAIT_ISSUE+'&batchNo='+rows[0]['batchNo']+'&token='+data['token']);
			},function(response_info){
				console.log(response_info);
			});
		}
	};
}]);
/**
 * 奖金重处理
 */
FolController.controller('BonusReastDisposeController',['$scope','$timeout','$q','BonusReastDisposeService','CodeService',function($scope,$timeout,$q,BonusReastDisposeService,CodeService){
	$scope.istableshow=false;
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.query_data.endNo=pageSize*pageNumber;
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				BonusReastDisposeService.query($scope.query_data,'bonus/bonusissue/queryBonusIssueFail',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});
			}
		});
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		var fields=['奖金类型','销售公司类型'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
			var typesId=['#bonusType','#serv'];
			
			$timeout(function(){
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
	});
	
	/**
	 * 重处理的奖金查询
	 */
	$scope.query=function(){
		$scope.istableshow=true;
		
		$scope.query_data={
				bonusType:folUtil.getComboBoxDataById('bonusType'),
				serv:folUtil.getComboBoxDataById('serv'),
				batchNo:$scope.batchNo,
				sapCode:$scope.sapCode,
				beginDate:$('#startTime').datebox('getValue'),
				endDate:$('#endTime').datebox('getValue'),
				issueStatus:STATE.FAIL_ISSUE,
				type:TYPE.BONUS_ISSUE,
				beginNo:0,
				endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BonusReastDisposeService.query($scope.query_data,'bonus/bonusissue/queryBonusIssueFail',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};
	
	/**
	 * 奖金重处理
	 */
	$scope.reastDispose=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var rows=$('#datagrid').datagrid('getSelections');
		if(rows.length<1){
			$.messager.alert('提示','请选择相应的记录再操作');
			return;
		}
		
		BonusReastDisposeService.reastDispose(rows,'bonus/bonusissue/payAgainBonus',function(data,response_info){
			defferred.resolve('success');
		},function(response_info){			
			defferred.reject('fail');
		});
		
		promise.then(function(res){
			$.messager.alert('提示', '奖金重处理成功');
			
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			BonusReastDisposeService.query($scope.query_data,'bonus/bonusissue/queryBonusIssueFail',function(data,response_info){
				$.messager.progress('close');
				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
				$('#datagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});
		},function(res){
			$.messager.alert('提示', '奖金重处理失败');
			
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			BonusReastDisposeService.query($scope.query_data,'bonus/bonusissue/queryBonusIssueFail',function(data,response_info){
				$.messager.progress('close');
				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
				$('#datagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});
		});
	}
	
	$scope.back=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var rows=$('#datagrid').datagrid('getSelections');
//		
		if(rows.length<1){
			$.messager.alert('提示', '请选择一条记录再进行操作');
			return;
		}
		
		BonusReastDisposeService.reject(rows,'bonus/bonusissue/bonusReject',function(data,response_info){
			defferred.resolve('success');
		},function(response_info){
			defferred.reject('fail');
		});
		
		promise.then(function(){
			$.messager.alert('提示','奖金发放文件驳回成功');
			BonusReastDisposeService.query($scope.query_data,'bonus/bonusissue/queryBonusIssueFail',function(data,response_info){
				$.messager.progress('close');
				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
				$('#datagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});
		},function(){
			$.messager.alert('提示','奖金发放文件驳回失败');
		});
	}
	
	//导出
	$scope.exportFile=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		if(rows.length<1){
			$.messager.alert('提示', '请选择一个奖金记录再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多个奖金记录进行操作');
			return;
		}else{
			BonusReastDisposeService.getToken('services/tokens',function(data){
				$.messager.progress('close');
				$window.open('bonus/bonusissue/exportFile?issueState='+STATE.FAIL_ISSUE+'&batchNo='+rows[0]['batchNo']+'&token='+data['token']);
			},function(response_info){
				console.log(response_info);
			});
		}
	};
	
}])
FolController.controller('authorityCtrl',['$scope','$rootScope','$http','$state','$timeout','AuthorityService',function($scope,$rootScope,$http,$state,$timeout,AuthorityService){
	$scope.istableshow=false;
	
	//获取角色类型下拉列表
	AuthorityService.systemRoRiMainss('authority/authorityamount/code/type/1071',function(data){
		$timeout(function(){
			console.log(data);
			$('#roleType').combobox('loadData',data);
			$('#roleType').combobox('select',data[0].code);
		});		
	},function(data){
		console.log("error:"+data);
	});
		
	//带分页的查询方法
	var queryData = function(pageNum, pageSize) {
		$scope.istableshow=true;
		
		var reserve_data={
			roleType: folUtil.getComboBoxDataById('roleType'),
			beginNo: (pageNum-1)*pageSize,
			endNo: pageNum*pageSize+1
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		AuthorityService.systemRoRiMain(reserve_data,'authority/authorityamount/query',function(data){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			console.log(response_info);
		});
	};
	
	//角色查询
	$scope.systemRoRiMain=function(){
		queryData(1,10);
		$("#datagrid").datagrid("getPager").pagination({
			onSelectPage:function(pageNumber, pageSize){
				queryData(pageNumber, pageSize);
			}
		});
	};
	
	$scope.delsystem=function(){
		var row=$('#datagrid').datagrid('getSelected');
		if(row){
			if(confirm("确定删除吗?")) {
				AuthorityService.delsystem(row,'authority/authorityamount/delAuthority',function(data){
					alert("删除成功");
					$('#datagrid').datagrid('loadData',{total:0,rows:[]});
					queryData(1,10);
				},function(data){
					alert("删除失败");
					console.log(data);
				});
			}
		}else{
			alert('请选择一条记录');
		}
	};
	
	//跳转到修改菜单页面
	$scope.systemRoRiMains=function(){
		var row=$('#datagrid').datagrid('getSelected');
		if(row){
			AuthorityService.setRoleData(row);
			$state.go('systemRoRiMains');
		}else{
			alert('请选择一条记录');
		}
	};
	//跳转到修改权限页面
	$scope.updateRoleAuthority=function(){
		var row=$('#datagrid').datagrid('getSelected');
		if(row){
			AuthorityService.setRoleData(row);
			$state.go('updateRoleAuthority');
		}else{
			alert('请选择一条记录');
		}
	};
	//跳转到添加页面
	$scope.systemRoRiMainsAd=function(){
		$state.go('systemRoRiMainsAdd');
	};
}]);
//修改菜单
FolController.controller('authorityUpdateCtrl',['$scope','$state','$timeout','AuthorityService',function($scope,$state,$timeout,AuthorityService){
	$timeout(function(){
//		$('#roleId').attr('value',AuthorityService.getRoleData().roleId);
//		$('#roleName').attr('value',AuthorityService.getRoleData().roleName);
//		$('#roleDesc').attr('value',AuthorityService.getRoleData().roleDesc);
		$('#roleId').textbox('setValue',AuthorityService.getRoleData().roleId);
		$('#roleName').textbox('setValue',AuthorityService.getRoleData().roleName);
		$('#roleDesc').textbox('setValue',AuthorityService.getRoleData().roleDesc);
	});
	

	//获取下拉框的值
	AuthorityService.systemRoRiMainss('authority/authorityamount/code/type/1071',function(data){
		$timeout(function(){
			$('#roleType').combobox('loadData',data);
			$('#roleType').combobox('select',AuthorityService.getRoleData().roleType);
		});
	},function(data){
		console.log("error:"+data);
	});
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
	//查询 储备金管理
	$("#roleType").combobox({
		onChange: function(n,o){
			var roleType = $('#roleType').combobox('getValue');
			if(roleType!='') {
				AuthorityService.findMenu('','authority/authorityamount/Menuquery/type/'+roleType+'/role/'+AuthorityService.getRoleData().roleId,function(data){
					$.messager.progress('close');
					$('#roledatagrid').datagrid({
						data: data,
						onLoadSuccess:function(data){
							if(data){
								$.each(data.rows,function(index,item){
									if(item.checked){
										$('#roledatagrid').datagrid('checkRow',index);
									}
								});
							}
						}
					});
				},function(data){
					console.log(data);
				});
			} else {
				$('#roledatagrid').datagrid('loadData',[]);
			}
		}
	});
		
	//确认修改
	$scope.systemPoRoMainA=function(){
		if($("#roleName").textbox("getValue")==null||$("#roleName").textbox("getValue")==''||$("#roleName").textbox("getValue")<0){
   	 		alert("请填写角色名称");
 			//$('#sumbitAddBtn').linkbutton('enable');
	 		return ;
 		}
   	 	if($("#roleType").combobox("getValue")==null||$("#roleType").combobox("getValue")==''||$("#roleType").combobox("getValue")<0){
   	 		alert("请选择角色类型");
 			//$('#sumbitAddBtn').linkbutton('enable');
	 		return ;
 		}
   	 	
   	 	var datas = {
 			roleId : $('#roleId').textbox('getValue'),
 			roleName : $('#roleName').textbox('getValue'),
 			roleType : folUtil.getComboBoxDataById('roleType'),
 			roleDesc : $('#roleDesc').textbox('getValue')
 		};
 		
 		var menus = "";
 		var checkedlist=$('#roledatagrid').datagrid('getChecked');
 		if(checkedlist.length>0) {
 			$.each(checkedlist,function(i,val){
 				menus = menus + val.menuId + ",";
 			});
 			menus=menus+" ";
 			menus=menus.replace(", ", "");
 		}
 		datas.menuId = menus;
 		AuthorityService.checkdata(datas,'authority/authorityamount/getSign',function(data,response_info){

		AuthorityService.systemPoRoMainA(datas,'authority/authorityamount/update',function(data){
			alert("修改成功");
			$state.go('systemRoRiMain');
		},function(data){
			alert("修改失败");
			console.log(data);
		});
	},function(response_info){
		if(response_info.state===STATE.TIMEOUT){
			console.log('timeout');
			$.messager.alert('提示','系统超时请稍后访问');
		}
	});
		
	};
	
}]);

//修改角色权限
FolController.controller('roleAuthorityUpdateCtrl',['$scope','$state','$timeout','AuthorityService',function($scope,$state,$timeout,AuthorityService){
	$timeout(function(){
		$('#roleId').textbox('setValue',AuthorityService.getRoleData().roleId);
		$('#roleName').textbox('setValue',AuthorityService.getRoleData().roleName);
		$('#roleDesc').textbox('setValue',AuthorityService.getRoleData().roleDesc);
	});
	

	//获取下拉框的值
	AuthorityService.systemRoRiMainss('authority/authorityamount/code/type/1071',function(data){
		$timeout(function(){
			$('#roleType').combobox('loadData',data);
			$('#roleType').combobox('select',AuthorityService.getRoleData().roleType);
		});
	},function(data){
		console.log("error:"+data);
	});
	$.messager.progress({
		title:'Please waiting',
		msg:'Loading data...',
		interval:PROGRESS_ACTION_TIMEOUT
	});	
	//查询 储备金管理
	$("#roleType").combobox({
		onChange: function(n,o){
			var roleType = $('#roleType').combobox('getValue');
			if(roleType!='') {
				AuthorityService.findMenu('','authority/authorityamount/methodQuery/role/'+AuthorityService.getRoleData().roleId,function(data){
					$.messager.progress('close');
					$('#roledatagrid').datagrid({
						data: data,
						onLoadSuccess:function(data){
							if(data){
								$.each(data.rows,function(index,item){
									if(item.checked){
										$('#roledatagrid').datagrid('checkRow',index);
									}
								});
							}
						}
					});
				},function(data){
					console.log(data);
				});
			} else {
				$('#roledatagrid').datagrid('loadData',[]);
			}
		}
	});
		
	//确认修改
	$scope.systemPoRoMainA=function(){
		if($("#roleName").textbox("getValue")==null||$("#roleName").textbox("getValue")==''||$("#roleName").textbox("getValue")<0){
   	 		alert("请填写角色名称");
 			//$('#sumbitAddBtn').linkbutton('enable');
	 		return ;
 		}
   	 	if($("#roleType").combobox("getValue")==null||$("#roleType").combobox("getValue")==''||$("#roleType").combobox("getValue")<0){
   	 		alert("请选择角色类型");
 			//$('#sumbitAddBtn').linkbutton('enable');
	 		return ;
 		}
   	 	
   	 	var data = {
 			roleId : $('#roleId').textbox('getValue'),
 			roleName : $('#roleName').textbox('getValue'),
 			roleType : folUtil.getComboBoxDataById('roleType'),
 			roleDesc : $('#roleDesc').textbox('getValue')
 		};
 		
 		var methods = "";
 		var checkedlist=$('#roledatagrid').datagrid('getChecked');
 		if(checkedlist.length>0) {
 			$.each(checkedlist,function(i,val){
 				methods = methods + val.methodId + ",";
 			});
 			methods=methods+" ";
 			methods=methods.replace(", ", "");
 		}
 		data.methodId = methods;

		AuthorityService.systemPoRoMainA(data,'authority/authorityamount/updateAuthorityMethod',function(data){
			alert("修改成功");
			$state.go('systemRoRiMain');
		},function(data){
			alert("修改失败");
			console.log(data);
		});
		
	};
	
}]);
FolController.controller('authorityAddCtrl',['$scope','$http','$state','$timeout','AuthorityService',function($scope,$http,$state,$timeout,AuthorityService){
	//获取下拉框的值
	AuthorityService.systemRoRiMainss('authority/authorityamount/code/type/1071',function(data){
		$timeout(function(){
			$('#roleType').combobox('loadData',data);
			$('#roleType').combobox('select',data[0].code);
		});
	},function(response_info){
		console.log("error:"+response_info);
	});
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
	//查询 储备金管理 菜单,菜单类型
	$("#roleType").combobox({
		onChange: function(n,o){
			var roleType = $('#roleType').combobox('getValue');
			if(roleType!='') {
				AuthorityService.findMenu('','authority/authorityamount/Menuquery/type/'+roleType,function(data){
					$.messager.progress('close');
					$('#roledatagrid').datagrid('loadData',data);
				},function(data){
					console.log(data);
				});
			} else {
				$('#roledatagrid').datagrid('loadData',[]);
			}
		}
	});
	
	//确认添加
	$scope.systemPoRoMainAdd=function(){
		if($("#roleName").textbox("getValue")==null||$("#roleName").textbox("getValue")==''||$("#roleName").textbox("getValue")<0){
   	 		$.messager.alert('提示','请填写角色名称');
	 		return ;
 		}
   	 	if($("#roleType").combobox("getValue")==null||$("#roleType").combobox("getValue")==''||$("#roleType").combobox("getValue")<0){
   	 		$.messager.alert('提示','请选择角色类型');
	 		return ;
 		}
		
		//判断角色名称是否存在
		AuthorityService.get('authority/authorityamount/name/'+$("#roleName").textbox("getValue"),function(data){
			if(data[0] == 0) {
				createRole();
			} else {
				$.messager.alert('提示','角色已存在,不能新增');
			}
		},function(data){
			$.messager.alert('提示','新增失败');
			console.log(data);
		});
	};
	
	var createRole = function() {
		var roleData = {
			roleName : $('#roleName').textbox('getValue'),
			roleType : folUtil.getComboBoxDataById('roleType'),
			roleDesc : $('#roleDesc').textbox('getValue')
		};
		
		var menus = "";
		var checkedlist=$('#roledatagrid').datagrid('getChecked');
		if(checkedlist.length>0) {
			$.each(checkedlist,function(i,val){
				menus = menus + val.menuId + ",";
			});
			menus=menus+" ";
			menus=menus.replace(", ", "");
		}
		roleData.menuId = menus;
 		AuthorityService.checkdata(roleData,'authority/authorityamount/getSign',function(data,response_info){
		AuthorityService.systemPoRoMainA(roleData,'authority/authorityamount/roleAdd',function(data){
			$.messager.alert('提示','新增成功');
			$state.go('systemRoRiMain');
		},function(data){
			$.messager.alert('提示','新增失败');
			console.log(data);
		});
 		},function(response_info){
 			if(response_info.state===STATE.TIMEOUT){
 				console.log('timeout');
 				$.messager.alert('提示','系统超时请稍后访问');
 			}
 		});
	};
}]);
FolController.controller('PersonRoleCtrl',['$scope','$state','$timeout','PersonRoleService',function($scope,$state,$timeout,PersonRoleService){
	$scope.istableshow=false;

		//获取下拉框	
		PersonRoleService.get('authority/authorityamount/code/type/1001',function(data){
			$timeout(function(){
				$('#userStatus').combobox('loadData',data);
				$('#userStatus').combobox('select',data[0].code);
			});
		},function(data){
			console.log("error:"+data);
		});
		
		PersonRoleService.get('system/systemamount/type/1|2',function(data){
			$timeout(function(){
				data.unshift({positionId:'',positionName:'请选择'});
				$('#positionId').combobox('loadData',data);
				$('#positionId').combobox('select',data[0].positionId);
			});
		},function(response_info){
			console.log("error:"+response_info);
		});

	
	//带分页的查询方法
	var queryData = function(pageNum, pageSize) {
		$scope.istableshow=true;
		var userData={
			userName: $('#userName').textbox('getValue'),
			userAccount: $('#userAccount').textbox('getValue'),
			userStatus: folUtil.getComboBoxDataById('userStatus'),
			positionId: folUtil.getComboBoxDataById('positionId'),
			beginNo: (pageNum-1)*pageSize,
			endNo: pageNum*pageSize+1
		};

		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		PersonRoleService.post(userData,'common/users/query',function(data){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			console.log(response_info);
		});
	};
	
	//角色查询
	$scope.findPersonRole=function(){
		queryData(1,10);
		$("#datagrid").datagrid("getPager").pagination({
			onSelectPage:function(pageNumber, pageSize){
				queryData(pageNumber, pageSize);
			}
		});
	};
	
	//跳转到修改页面
	$scope.updataPersonRoles=function(){
		var row=$('#datagrid').datagrid('getSelected');
		if (row){
			PersonRoleService.setPersonData(row);
			$state.go('updataPersonRole');
		}else{
			alert('请选择一条记录');
		}
	};
	
	//跳转到新增页面
	$scope.userAdd=function(){
		$state.go('userAdd');
	};
	
	//删除
	$scope.delUser=function(){
		var row=$('#datagrid').datagrid('getSelected');
		if(row){
			if(confirm("确定删除吗?")) {
				PersonRoleService.del(row,'common/users/delete',function(data){
					alert("删除成功");
					queryData(1,10);
				},function(response_info){
					alert("删除失败");
					console.log(response_info);
				});
			}
		}else{
			alert('请选择一条记录');
		}
	};

}]);

FolController.controller('updataPersonRoleCtrl',['$scope','$state','$stateParams','$timeout','PersonRoleService',function($scope,$state,$stateParams,$timeout,PersonRoleService){
	$timeout(function(){
//		$scope.userBirthday = PersonRoleService.getPersonData().birthday;
		
		$('#birthday').datebox('setValue',PersonRoleService.getPersonData().birthday);
		$('#userAccount').textbox('setValue',PersonRoleService.getPersonData().userAccount);
		$('#userName').textbox('setValue',PersonRoleService.getPersonData().userName);
		$('#handPhone').textbox('setValue',PersonRoleService.getPersonData().handPhone);
		$('#zipCode').textbox('setValue',PersonRoleService.getPersonData().zipCode);
		$('#addr').textbox('setValue',PersonRoleService.getPersonData().addr);
		$('#email').textbox('setValue',PersonRoleService.getPersonData().email);
	});
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
		//获取SGM职位列表
		$("#userType").combobox({
			onChange: function(n,o){
				var userType = $('#userType').combobox('getValue');
				if(userType!='') {
					PersonRoleService.get('system/systemamount/type/'+userType+'/user/'+PersonRoleService.getPersonData().userAccount,function(data){
						$timeout(function(){
							$.messager.progress('close');		
							$('#grid_positions').datagrid({
								data: data[0],
								onLoadSuccess:function(data){
									if(data){
										$.each(data.rows,function(index,item){
											if(item.checked){
												$('#grid_positions').datagrid('checkRow',index);
											}
										});
									}
								}
							});
						});
					},function(response_info){
						console.log(response_info);
					});
				} else {
					$('#grid_positions').datagrid('loadData',[]);
				}
			}
		});

	
	//获取下拉框	
	PersonRoleService.get('authority/authorityamount/code/type/1001',function(data){
		$timeout(function(){
			$('#userStatus').combobox('loadData',data);
			$('#userStatus').combobox('select',PersonRoleService.getPersonData().userStatus);
		});
	},function(response_info){
		console.log("error:"+response_info);
	});
	
	PersonRoleService.get('authority/authorityamount/code/type/1012',function(data){
		$timeout(function(){
			$('#gender').combobox('loadData',data);
			$('#gender').combobox('select',PersonRoleService.getPersonData().gender);
		});
	},function(response_info){
		console.log("error:"+response_info);
	});
	
	PersonRoleService.get('authority/authorityamount/code/type/1071',function(data){
		$timeout(function(){
			$('#userType').combobox('loadData',data);
			$('#userType').combobox('select',PersonRoleService.getPersonData().userType);
		});
	},function(response_info){
		console.log("error:"+response_info);
	});
	
	$scope.userUpdate=function(){
		if($("#userAccount").textbox("getValue")==null||$("#userAccount").textbox("getValue")==''||$("#userAccount").textbox("getValue")<0){
	 		alert("请填写帐号名");
	 		return ;
		}
	 	if($("#userType").combobox("getValue")==null||$("#userType").combobox("getValue")==''||$("#userType").combobox("getValue")==0){
	 		alert("请选择用户类型");
	 		return ;
		}
	 	if($("#userName").textbox("getValue")==null||$("#userName").textbox("getValue")==''||$("#userName").textbox("getValue")<0){
	 		alert("请填写姓名");
	 		return ;
		}
	 	if($("#gender").combobox("getValue")==null||$("#gender").combobox("getValue")==''||$("#gender").combobox("getValue")==0){
	 		alert("请选择性别");
	 		return ;
		}
	 	if($("#userStatus").combobox("getValue")==null||$("#userStatus").combobox("getValue")==''||$("#userStatus").combobox("getValue")==0){
	 		alert("请选择启用状态");
	 		return ;
		}
	 	
	 	//验证手机号
	 	var reg=/^1\d{10}$/;
	 	if(!folUtil.isNull($("#handPhone").textbox("getValue"))&&!reg.test($("#handPhone").textbox("getValue"))){
	 		alert("请正确填写手机号！");
	 		return ;
		}
	     
	 	//验证邮件格式
	     var reg=/[a-zA-Z0-9]{1,10}@[a-zA-Z0-9]{1,5}\.[a-zA-Z0-9]{1,5}/;
	     if(!folUtil.isNull($("#email").textbox("getValue"))&&!reg.test($("#email").textbox("getValue"))){
	        alert("请正确填写邮箱！");
	        return ;
	     }
		
		var userData = {
			userAccount: $('#userAccount').textbox('getValue'),
			userType: folUtil.getComboBoxDataById('userType'),
			userName: $('#userName').textbox('getValue'),
			gender: folUtil.getComboBoxDataById('gender'),
			handPhone: $('#handPhone').textbox('getValue'),
			birthday: $('#birthday').textbox('getValue'),
			zipCode: $('#zipCode').textbox('getValue'),
			addr: $('#addr').textbox('getValue'),
			email: $('#email').textbox('getValue'),
			userStatus: folUtil.getComboBoxDataById('userStatus')
		};
		
		var positions = "";
		var checkedlist=$('#grid_positions').datagrid('getChecked');
		if(checkedlist.length>0) {
			$.each(checkedlist,function(i,val){
				positions = positions + val.positionId + ",";
			});
			positions=positions+" ";
			positions=positions.replace(", ", "");
		}
		userData.positions = positions;
		PersonRoleService.checkdata(userData,'common/users/getSign',function(data,response_info){
	
		PersonRoleService.put(userData,'common/users/update',function(data){
			alert("修改成功");
			$state.go('personRole');
		},function(response_info){
 			if(response_info.state===STATE.TIMEOUT){
 				console.log('timeout');
 				$.messager.alert('提示','系统超时请稍后访问');
 			}else{
 				alert("修改失败");
 			}
			console.log(response_info);
		});
 		},function(response_info){
 			if(response_info.state===STATE.TIMEOUT){
 				console.log('timeout');
 				$.messager.alert('提示','系统超时请稍后访问');
 			}
 		});
	};
}]);

FolController.controller('addUserCtrl',['$scope','$state','$stateParams','$timeout','PersonRoleService','AuthorityService',function($scope,$state,$stateParams,$timeout,PersonRoleService,AuthorityService){

	//获取下拉框	
	PersonRoleService.get('authority/authorityamount/code/type/1001',function(data){
		$timeout(function(){
			$('#userStatus').combobox('loadData',data);
			$('#userStatus').combobox('select',data[1].code);
		});		
	},function(response_info){
		console.log("error:"+response_info);
	});
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
	PersonRoleService.get('authority/authorityamount/code/type/1012',function(data){
		$timeout(function(){
			$.messager.progress('close');	
			$('#gender').combobox('loadData',data);
			$('#gender').combobox('select',data[0].code);
		});	
	},function(response_info){
		console.log("error:"+response_info);
	});
	
	PersonRoleService.get('authority/authorityamount/code/type/1071',function(data){
		$timeout(function(){
			$('#userType').combobox('loadData',data);
			$('#userType').combobox('select',data[1].code);
		});
	},function(response_info){
		console.log("error:"+response_info);
	});
	
	//获取SGM职位列表
	$("#userType").combobox({
		onChange: function(n,o){
			var userType = $('#userType').combobox('getValue');
			if(userType!='') {
				PersonRoleService.get('system/systemamount/type/'+userType,function(data){
					$('#grid_positions').datagrid('loadData',data);
				},function(data){
					console.log(data);
				});
			} else {
				$('#grid_positions').datagrid('loadData',[]);
			}
		}
	});
	
	/**
	 * 检查用户
	 */
	$scope.checkUser=function(){
		
	}
	
	//新增
	$scope.userAdd=function(){
		
		if($("#userAccount").textbox("getValue")==null||$("#userAccount").textbox("getValue")==''||$("#userAccount").textbox("getValue")<0){
	 		alert("请填写帐号名");
	 		return ;
		}
	 	if($("#userType").combobox("getValue")==null||$("#userType").combobox("getValue")==''||$("#userType").combobox("getValue")==0){
	 		alert("请选择用户类型");
	 		return ;
		}
	 	if($("#userName").textbox("getValue")==null||$("#userName").textbox("getValue")==''||$("#userName").textbox("getValue")<0){
	 		alert("请填写姓名");
	 		return ;
		}
	 	if($("#gender").combobox("getValue")==null||$("#gender").combobox("getValue")==''||$("#gender").combobox("getValue")==0||$("#gender").combobox("getValue")==-1){
	 		alert("请选择性别");
	 		return ;
		}
	 	if($("#userStatus").combobox("getValue")==null||$("#userStatus").combobox("getValue")==''||$("#userStatus").combobox("getValue")==0){
	 		alert("请选择启用状态");
	 		return ;
		}
	 	
	 	//验证手机号
	 	var reg=/^1\d{10}$/;
	 	if(!folUtil.isNull($("#handPhone").textbox("getValue"))&&!reg.test($("#handPhone").textbox("getValue"))){
	 		alert("请正确填写手机号！");
	 		return ;
		}
	     
	 	//验证邮件格式
	     var reg=/[a-zA-Z0-9]{1,10}@[a-zA-Z0-9]{1,5}\.[a-zA-Z0-9]{1,5}/;
	     if(!folUtil.isNull($("#email").textbox("getValue"))&&!reg.test($("#email").textbox("getValue"))){
	        alert("请正确填写邮箱！");
	        return ;
	     }
		
   	 	//判断用户帐号是否存在
   	 	PersonRoleService.get('common/users/account/'+$("#userAccount").textbox("getValue"),function(data){
			if(data[0] == 0) {
				createUser();
			} else {
				alert("帐号已存在,不能新增");
			}
		},function(response_info){
			alert("新增失败");
			console.log(response_info);
		});
	};
	
	var createUser = function () {
		var userData = {
			userAccount: $('#userAccount').textbox('getValue'),
			userType: folUtil.getComboBoxDataById('userType'),
			userName: $('#userName').textbox('getValue'),
			gender: folUtil.getComboBoxDataById('gender'),
			handPhone: $('#handPhone').textbox('getValue'),
			birthday: $('#birthday').textbox('getValue'),
			zipCode: $('#zipCode').textbox('getValue'),
			addr: $('#addr').textbox('getValue'),
			email: $('#email').textbox('getValue'),
			userStatus: folUtil.getComboBoxDataById('userStatus')
		};
		
		var positions = "";
		var checkedlist=$('#grid_positions').datagrid('getChecked');
		if(checkedlist.length>0) {
			$.each(checkedlist,function(i,val){
				positions = positions + val.positionId + ",";
			});
			positions=positions+" ";
			positions=positions.replace(", ", "");
		}
		userData.positions = positions;
 		AuthorityService.checkdata(userData,'common/users/getSign',function(data,response_info){	
		PersonRoleService.post(userData, 'common/users/add',function(data){
			alert("新增成功");
			$state.go('personRole');
		},function(response_info){
			alert("新增失败");
			console.log(response_info);
		});
 		},function(response_info){
 			if(response_info.state===STATE.TIMEOUT){
 				console.log('timeout');
 				$.messager.alert('提示','系统超时请稍后访问');
 			}
 		});
	};
	
	
}]);
	


FolController.controller('systemCtrl',['$scope','$state','SystemService',function($scope,$state,SystemService){
	$scope.istableshow=false;
	
	//带分页的查询方法
	var queryData = function(pageNum, pageSize) {
		$scope.istableshow=true;
		
		var positiondata={
			positionName:$('#positionName').textbox('getValue'),
			dpPositionCode:$('#dpPositionCode').textbox('getValue'),
			beginNo: (pageNum-1)*pageSize,
			endNo: pageNum*pageSize+1
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		SystemService.systemPoRoMain(positiondata,'system/systemamount/query',function(data){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			console.log(response_info);
		});
	};
	
	//岗位查询
	$scope.systemPoRoMain=function(){
		queryData(1,10);
		$("#datagrid").datagrid("getPager").pagination({
			onSelectPage:function(pageNumber, pageSize){
				queryData(pageNumber, pageSize);
			}
		});
	};
	
	//跳转修改页面
	$scope.systemPoRoMains=function(){
		var row=$('#datagrid').datagrid('getSelected');
		if(row){
			SystemService.setPositionData(row);
			$state.go('systemPoRoMains');
		}else{
			alert('请选择一条记录');
		}
	};
	
	//跳转到添加页面
	$scope.systemPoRoAdd=function(){
		$state.go('systemPoRoMainsAdd');
	};
	
	//删除
	$scope.systemPoRoDel=function(){
		var row=$('#datagrid').datagrid('getSelected');
		if(row){
			if(confirm("确定删除吗?")) {
				SystemService.del(row,'system/systemamount/del',function(data){
					alert("删除成功");
					queryData(1,10);
				},function(response_info){
					alert("删除失败");
					console.log(response_info);
				});
			}
		}else{
			alert('请选择一条记录');
		}
	};
	
}]);

FolController.controller('systemUpdateCtrl',['$scope','$state','$timeout','$stateParams','SystemService',function($scope,$state,$timeout,$stateParams,SystemService){
	$timeout(function(){
		$('#positionId').textbox('setValue',SystemService.getPositionData().positionId);
		$('#positionName').textbox('setValue',SystemService.getPositionData().positionName);
		$('#positionDesc').textbox('setValue',SystemService.getPositionData().positionDesc);
		$('#dpPositionCode').textbox('setValue',SystemService.getPositionData().dpPositionCode);
//		$('#positionId').attr('value',SystemService.getPositionData().positionId);
//		$('#positionName').attr('value',SystemService.getPositionData().positionName);
//		$('#positionDesc').attr('value',SystemService.getPositionData().positionDesc);
//		$('#dpPositionCode').attr('value',SystemService.getPositionData().dpPositionCode);
	});
		//获取下拉框的值
		SystemService.get('authority/authorityamount/code/type/1071',function(data){
			$timeout(function(){
				$('#positionType').combobox('loadData',data);
				$('#positionType').combobox('select',SystemService.getPositionData().positionType);
			});
		},function(response_info){
			console.log("error:"+response_info);
		});
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
		
		//查询 储备金管理
		$("#positionType").combobox({
			onChange: function(n,o){
				var positionType = $('#positionType').combobox('getValue');
				if(positionType!='') {
					SystemService.get('authority/authorityamount/type/'+positionType+'/position/'+SystemService.getPositionData().positionId,function(data){
						$.messager.progress('close');
						$('#roledatagrid').datagrid('loadData',data);
						$('#roledatagrid').datagrid({
							data: data,
							onLoadSuccess:function(data){
								if(data){
									$.each(data.rows,function(index,item){
										if(item.checked){
											$('#roledatagrid').datagrid('checkRow',index);
										}
									});
								}
							}
						});
					},function(response_info){
						console.log(response_info);
					});
				} else {
					$('#roledatagrid').datagrid('loadData',[]);
				}
			}
		});

	
	//确认修改
	$scope.systemPoRoMainA=function(){
		if($("#positionName").textbox("getValue")==null||$("#positionName").textbox("getValue")==''||$("#positionName").textbox("getValue")<0){
   	 		alert("请填写岗位名称");
	 		return ;
 		}
   	 	if($("#positionType").combobox("getValue")==null||$("#positionType").combobox("getValue")==''||$("#positionType").combobox("getValue")<0){
   	 		alert("请选择岗位类型");
	 		return ;
 		}
   	 	
   	 	var positionData={
 			positionName: $("#positionName").textbox("getValue"),
 			positionId: $("#positionId").textbox("getValue"),
 			positionType: folUtil.getComboBoxDataById('positionType'),
 			positionDesc: $("#positionDesc").textbox("getValue"),
 			dpPositionCode: $("#dpPositionCode").textbox("getValue")
 		};
   	 	
   	 	var roles = "";
		var checkedlist=$('#roledatagrid').datagrid('getChecked');
		if(checkedlist.length>0) {
			$.each(checkedlist,function(i,val){
				roles = roles + val.roleId + ",";
			});
			roles=roles+" ";
			roles=roles.replace(", ", "");
		}
		positionData.roles = roles;
		SystemService.checkdata(positionData,'system/systemamount/getSign',function(data,response_info){

		SystemService.put(data,'system/systemamount/update',function(data){
			alert("修改成功");
			$state.go('systemPoRoMain');
		},function(response_info){
			alert("修改失败");
			console.log(response_info);
		});
 		},function(response_info){
 			if(response_info.state===STATE.TIMEOUT){
 				console.log('timeout');
 				$.messager.alert('提示','系统超时请稍后访问');
 			}
 		});
	};
}]);

FolController.controller('systemAddCtrl',['$scope','$state','$stateParams','$timeout','SystemService','AuthorityService',function($scope,$state,$stateParams,$timeout,SystemService,AuthorityService){

	//获取下拉框的值
	SystemService.get('authority/authorityamount/code/type/1071',function(data){
		$timeout(function(){
			$('#positionType').combobox('loadData',data);
			$('#positionType').combobox('select',data[0].code);
		});
	},function(response_info){
		console.log("error:"+response_info);
	});
	$.messager.progress({
		title:'Please waiting',
		msg:'Loading data...',
		interval:PROGRESS_ACTION_TIMEOUT
	});	
	//根据类型查询角色列表 
	$("#positionType").combobox({
		onChange: function(n,o){
			var positionType = $('#positionType').combobox('getValue');
			if(positionType!='') {
				SystemService.get('authority/authorityamount/type/'+positionType,function(data){
					$.messager.progress('close');	
					$('#roledatagrid').datagrid('loadData',data);
				},function(response_info){
					console.log(response_info);
				});
			} else {
				$('#roledatagrid').datagrid('loadData',[]);
			}
		}
	});
	
	//新增
	$scope.systemPoRoMainAdd=function(){
		if($("#positionName").textbox("getValue")==null||$("#positionName").textbox("getValue")==''||$("#positionName").textbox("getValue")<0){
   	 		alert("请填写岗位名称");
	 		return ;
 		}
   	 	if($("#positionType").combobox("getValue")==null||$("#positionType").combobox("getValue")==''||$("#positionType").combobox("getValue")<0){
   	 		alert("请选择岗位类型");
	 		return ;
 		}
		
   	 	//判断岗位名称是否存在
   	 	SystemService.get('system/systemamount/name/'+$("#positionName").textbox("getValue"),function(data){
			if(data[0] == 0) {
				createPosition();
			} else {
				alert("岗位已存在,不能新增");
			}
		},function(data){
			alert("新增失败");
			console.log(data);
		});
	};
	
	var createPosition = function() {
		var systemdata={
			positionName: $("#positionName").textbox("getValue"),
			positionType: folUtil.getComboBoxDataById('positionType'),
			positionDesc: $("#positionDesc").textbox("getValue"),
			dpPositionCode: $("#dpPositionCode").textbox("getValue")
		};
		
		var roles = "";
		var checkedlist=$('#roledatagrid').datagrid('getChecked');
		if(checkedlist.length>0) {
			$.each(checkedlist,function(i,val){
				roles = roles + val.roleId + ",";
			});
			roles=roles+" ";
			roles=roles.replace(", ", "");
		}
		systemdata.roles = roles;
 		AuthorityService.checkdata(systemdata,'system/systemamount/getSign',function(data,response_info){
		SystemService.post(systemdata,'system/systemamount/add',function(data){
			alert("新增成功");
			$state.go('systemPoRoMain');
		},function(response_info){
			alert("新增失败");
			console.log(response_info);
		});
	},function(response_info){
		if(response_info.state===STATE.TIMEOUT){
			console.log('timeout');
			$.messager.alert('提示','系统超时请稍后访问');
		}
	});
	};
}]);
/**
 * 
 */
FolController.controller('UserController',['$scope','UserService',function($scope,UserService){
	$scope.doLogout=function(){
		UserService.logout('common/logout',function(data){
			$.messager.alert("退出已成功");
		},function(response_info){
			console.log(response_info);
		});
	};
}]);
FolController.controller('authorityMaintainCtrl',['$scope','$rootScope','$http','$state','$timeout','RoleAuthorityService',function($scope,$rootScope,$http,$state,$timeout,RoleAuthorityService){
	$scope.istableshow=false;
	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.authority_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
				$scope.authority_data.endNo=pageSize*pageNumber;
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				RoleAuthorityService.systemRoRiMain($scope.authority_data,'authority/authormethod/query',function(data){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});
			}
		});
	});
	//角色查询
	$scope.systemRoRiMain=function(){
		$scope.istableshow=true;
		$scope.authority_data={
				clazz:$('#clazz').textbox('getValue'),
				beginNo:0,
				endNo:10
		};
		RoleAuthorityService.systemRoRiMain($scope.authority_data,'authority/authormethod/query',function(data){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	}
	$scope.delsystem=function(){
		var row=$('#datagrid').datagrid('getSelected');
		if(row){
			if(confirm("确定删除吗?")) {
				RoleAuthorityService.delsystem(row,'authority/authormethod/delete',function(data){
					alert("删除成功");
					$scope.authority_data.beginNo=0;
					$scope.authority_data.endNo=10;
					RoleAuthorityService.systemRoRiMain($scope.authority_data,'authority/authormethod/query',function(data){
					//	$.messager.progress('close');
						$('#datagrid').datagrid('loadData',{total:0,rows:[]});
						$('#datagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});	
				},function(data){
					alert("删除失败");
					console.log(data);
				});
			}
		}else{
			alert('请选择一条记录');
		}
	};
	
	//跳转到修改页面
	$scope.systemRoRiMains=function(){
		var row=$('#datagrid').datagrid('getSelected');
		if(row){
			RoleAuthorityService.setRoleData(row);
			$state.go('roleAuthorityMains');
		}else{
			alert('请选择一条记录');
		}
	};
	
	//跳转到添加页面
	$scope.systemRoRiMainsAd=function(){
		$state.go('roleAuthorityMainsAdd');
	};
}]);

FolController.controller('roleAuthorityMaintainUpdateCtrl',['$scope','$state','$timeout','RoleAuthorityService',function($scope,$state,$timeout,RoleAuthorityService){
	$timeout(function(){

		$('#clazz').textbox('setValue',RoleAuthorityService.getRoleData().clazz);
		$('#methodId').textbox('setValue',RoleAuthorityService.getRoleData().methodId);
		$('#method').textbox('setValue',RoleAuthorityService.getRoleData().method);
		$('#url').textbox('setValue',RoleAuthorityService.getRoleData().url);
	});	
		
	//确认修改
	$scope.systemPoRoMainA=function(){
		if($("#clazz").textbox("getValue")==null||$("#clazz").textbox("getValue")==''||$("#clazz").textbox("getValue")<0){
   	 		alert("请填写角色权限类名");
	 		return ;
 		}
		if($("#method").textbox("getValue")==null||$("#method").textbox("getValue")==''||$("#method").textbox("getValue")<0){
   	 		alert("请填写角色权限方法名");
	 		return ;
 		}
		if($("#url").textbox("getValue")==null||$("#url").textbox("getValue")==''||$("#url").textbox("getValue")<0){
   	 		alert("请填写角色权限URI");
	 		return ;
 		}
   	 	
   	 	var datas = {
   	 		methodId:$('#methodId').textbox('getValue'),
 			clazz : $('#clazz').textbox('getValue'),
 			method : $('#method').textbox('getValue'),
 			url : $('#url').textbox('getValue')
 		};
   	 //RoleAuthorityService.checkdata(datas,'system/systemamount/getSign',function(data,response_info){

   	 RoleAuthorityService.systemPoRoMainA(datas,'authority/authormethod/update',function(data){
			alert("修改成功");
			$state.go('roleAuthorityMain');
		},function(data){
			if(response_info.state===STATE.TIMEOUT){
 				console.log('timeout');
 				$.messager.alert('提示','系统超时请稍后访问');
 			}else{
 				alert("修改失败");
 			}
			console.log(data);
		});
		/*},function(response_info){
 			if(response_info.state===STATE.TIMEOUT){
 				console.log('timeout');
 				$.messager.alert('提示','系统超时请稍后访问');
 			}
 		});*/
		
	};
	$scope.cancel= function(){
		$state.go('roleAuthorityMain');
	}
	
}]);

FolController.controller('roleAuthorityMaintainAddCtrl',['$scope','$http','$state','$timeout','RoleAuthorityService',function($scope,$http,$state,$timeout,RoleAuthorityService){
	//确认添加
	$scope.systemPoRoMainAdd=function(){
		if($("#clazz").textbox("getValue")==null||$("#clazz").textbox("getValue")==''||$("#clazz").textbox("getValue")<0){
   	 		alert("请填写角色权限类名");
	 		return ;
 		}
		if($("#method").textbox("getValue")==null||$("#method").textbox("getValue")==''||$("#method").textbox("getValue")<0){
   	 		alert("请填写角色权限方法名");
	 		return ;
 		}
		if($("#url").textbox("getValue")==null||$("#url").textbox("getValue")==''||$("#url").textbox("getValue")<0){
   	 		alert("请填写角色权限URI");
	 		return ;
 		}

			var roleAuthorityData = {
				clazz : $('#clazz').textbox('getValue'),
				method : $('#method').textbox('getValue'),
				url : $('#url').textbox('getValue')
			};	

		RoleAuthorityService.systemPoRoMainA(roleAuthorityData,'authority/authormethod/add',function(data){
			$.messager.alert('提示','新增成功');
			$state.go('roleAuthorityMain');
		},function(data){
			$.messager.alert('提示','新增失败');
			console.log(data);
		});
	};
		$scope.cancel= function(){
			$state.go('roleAuthorityMain');
		}

}]);
/**
 * 储备金余额查询
 */
FolController.controller('reserveAmountCtrl',['$scope','$rootScope','ReserveService','$state','$timeout','$window',function($scope,$rootScope,ReserveService,$state,$timeout,$window){
	$scope.istableshow=false;
	
	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.reserve_amount_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
				$scope.reserve_amount_data.endNo=pageSize*pageNumber;
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				ReserveService.findReserveAmount($scope.reserve_amount_data,'reserve/reserveamount/query',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});
			}
		});

		$('#ReserveDeatilWindow').panel({
			onBeforeOpen:function(){
				$('#changeAmountDataGrid').attr('class','row-fluid ng-hide');
				$('#freezeAmountDataGrid').attr('class','row-fluid ng-hide');
			}
		});
	});
	

	$scope.findReserveAmount=function(){
		$scope.reserve_amount_data={
				dealerType:folUtil.getComboBoxDataById('dealerType'),
				reserveType:folUtil.getComboBoxDataById('reserveType'),
				sapCode:$('#sapCode').val(),
				beginNo:0,
				endNo:10
		};

		$scope.istableshow=true;
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		ReserveService.findReserveAmount($scope.reserve_amount_data,'reserve/reserveamount/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};
	
	$.messager.progress({
		title:'Please waiting',
		msg:'Loading data...',
		interval:PROGRESS_ACTION_TIMEOUT
	});	
	
	ReserveService.findCodeTypeNames('reserve/reserveamount/findCodeTypeNames',function(data,response_info){
		$.messager.progress('close');
		
		var typesId=['#dealerType'];
		
		$timeout(function(){
			$('#reserveType').combobox('loadData',$rootScope.reserveType);
			$('#reserveType').combobox('select',$rootScope.reserveType[0]['code']);
		
			for(var i=0;i<data.length;i++){
				if(!folUtil.isNull(data)){
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
	
	//文件导出
	$scope.exportReserveQueryResult=function(){
		
		ReserveService.getToken('services/tokens',function(data){
			if(folUtil.isNull($scope.reserve_amount_data)){
				$.messager.alert('提示','请先查询一次再导出');
				return;
			}
			
			$.messager.progress('close');
			$window.open('reserve/reserveamount/exportReserveQueryResult?dealerType='+$scope.reserve_amount_data.dealerType+'&reserveType='+$scope.reserve_amount_data.reserveType+'&sapCode='+$scope.reserve_amount_data.sapCode+'&token='+data['token']);
		},function(response_info){
			$.messager.progress('close');
			console.log(response_info);
		});
	};
	
	/**
	 * 打开储备金明细界面
	 */
	$scope.findReconciliationDeatil=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择某条记录再操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示','只能选择一条记录进行操作');
			return;
		}
		
		$scope.dealer=rows[0];
		
		$('#deatilSapCode').textbox({value:$scope.dealer.sapCode});
		
		$('#ReserveDeatilWindow').window('open');
	};
	
}]);

/**
 * 储备金变动明细
 */
FolController.controller('reserveDeatilChangeCtrl',['$scope','$rootScope','$timeout','ReserveService','$q','$window',function($scope,$rootScope,$timeout,ReserveService,$q,$window){
	$scope.istableshow=false;
	
	$timeout(function(){
		var changeAmountDataGrid=$('#changeAmountDataGrid').datagrid('getPager');
		
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var changeReserveAmountResult=false,freezeReserveAmountResult=false;
		changeAmountDataGrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.reserve_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.reserve_data.endNo=pageSize*pageNumber;	
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
					
				ReserveService.findChangeReserveAmount($scope.reserve_data,'reserve/reservedeatilchange/changedeatilreserve/query',function(data,response_info){
					$.messager.progress('close');
					$('#changeAmountDataGrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log(response_info);
						$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
					}else{
						$.messager.alert("提示",response_info.message);
					}
				});
			}
		});
		
		var freezeAmountDataGrid=$('#freezeAmountDataGrid').datagrid('getPager');
		freezeAmountDataGrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.reserve_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.reserve_data.endNo=pageSize*pageNumber;	
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				ReserveService.findFreezeReserveAmount($scope.reserve_data,'reserve/reservedeatilchange/freezedeatilreserve/query',function(data,response_info){
					$.messager.progress('close');
					$('#freezeAmountDataGrid').datagrid('loadData',data);
				},function(response_info){
					if(response_info.state===STATE.TIMEOUT){
						$.messager.progress('close');
						$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
					}else{
						$.messager.alert("提示",response_info.message);
					}
				});
			}
		});
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		ReserveService.findCodeTypeNames('reserve/reservedeatilchange/findCodeTypeNames',function(data,response_info){
			$.messager.progress('close');
			var typesId=['#dealerType','#businessCode'];

			$timeout(function(){
				$('#reserveType').combobox('loadData',$rootScope.reserveType);
				$('#reserveType').combobox('select',$rootScope.reserveType[0]['code']);
				
				for(var i=0;i<data.length;i++){
					if(!folUtil.isNull(data[i])){
						$(typesId[i]).combobox('loadData',data[i]);
						$(typesId[i]).combobox('select',data[i][0]['code']);
					}
				}
			});
		},function(response_info){
			$.messager.progress('close');
			if(folUtil.isNull(response_info)){
				$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
			}
		});
	});
	
	
	$scope.findReserveDeatilChange=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var changeReserveAmountResult=false,freezeReserveAmountResult=false;
		if($('#startTime').datebox('getText')>$('#endTime').datebox('getText')){
			$.messager.alert('提示','开始时间不能大于结束时间');
			return;
		}
		
		$scope.reserve_data={
				dealerType:folUtil.getComboBoxDataById('dealerType'),
				reserveType:folUtil.getComboBoxDataById('reserveType'),
				sapCode:$('#sapCode').val(),
				businessCode:folUtil.getComboBoxDataById('businessCode'),
				startTime:$('#startTime').datebox('getValue'),
				endTime:$('#endTime').datebox('getValue'),
				beginNo:0,
				endNo:10
			};
		$scope.istableshow=true;

		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		//查询变动储备金
		ReserveService.findChangeReserveAmount($scope.reserve_data,'reserve/reservedeatilchange/changedeatilreserve/query',function(data,response_info){
			$('#changeAmountDataGrid').datagrid('loadData',{total:0,rows:[]});
			$('#changeAmountDataGrid').datagrid('loadData',data);
			
			changeReserveAmountResult=true;
			if(freezeReserveAmountResult&&changeReserveAmountResult){
				defferred.resolve('success');
			}
		},function(response_info){
			if(response_info.state===STATE.TIMEOUT){
				console.log(response_info);
				defferred.reject('timeout');
			}else{
				defferred.reject('error');
			}			
		});

		//查询冻结储备金
		ReserveService.findFreezeReserveAmount($scope.reserve_data,'reserve/reservedeatilchange/freezedeatilreserve/query',function(data,response_info){
			$('#freezeAmountDataGrid').datagrid('loadData',{total:0,rows:[]});
			$('#freezeAmountDataGrid').datagrid('loadData',data);

			freezeReserveAmountResult=true;
			if(freezeReserveAmountResult&&changeReserveAmountResult){
				defferred.resolve('success');
			}
		},function(response_info){
			if(response_info.state===STATE.TIMEOUT){
				defferred.reject('timeout');
			}else{
				defferred.reject('error');
			}
		});
		
		promise.then(function(data){
			console.log(data);
			if(data==='success'){
				$.messager.progress('close');
			}
		},function(data){
			console.log(data);
			$.messager.progress('close');
			if(data=='timeout'){
				$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
			}else{
				$.messager.alert("提示",'系统出错');
			}
			
		});
	};

	//文件导出
	$scope.deatilChangeResult=function(){
		
		ReserveService.getToken('services/tokens',function(data){
			if(folUtil.isNull($scope.reserve_data)){
				$.messager.alert('提示','请先查询一次再导出');
				return;
			}
			
			$window.open('reserve/reservedeatilchange/ChangeReserveAmount?dealerType='+$scope.reserve_data.dealerType+'&reserveType='+$scope.reserve_data.reserveType+'&businessCode='+$scope.reserve_data.businessCode+'&sapCode='+$scope.reserve_data.sapCode+'&startTime='+$scope.reserve_data.startTime+'&endTime='+$scope.reserve_data.endTime+'&token='+data['token']);
			$window.open('reserve/reservedeatilchange/FreezeReserveAmount?dealerType='+$scope.reserve_data.dealerType+'&reserveType='+$scope.reserve_data.reserveType+'&businessCode='+$scope.reserve_data.businessCode+'&sapCode='+$scope.reserve_data.sapCode+'&startTime='+$scope.reserve_data.startTime+'&endTime='+$scope.reserve_data.endTime+'&token='+data['token']);
		},function(response_info){
			console.log(response_info);
		});
	};
	
	/**
	 * 订单查看
	 */
	$scope.findOrderDeatil=function(id){
		var rows=$('#'+id).datagrid('getSelections');
		if(rows.length<1){
			$.messager.alert('提示','请选择一条记录再操作');
			return;
		}
		$.each(rows,function(i,val){
			var referCode=val['referCode'];
			var nums=referCode.split('|');
			$window.open(POL_SERVER_URL+'index.html#/1300_130020?orderNo='+nums[0]);
		});
		
	};
	
	/**
	 * billing查看
	 */
	$scope.findBillingDeatil=function(id){
		var rows=$('#'+id).datagrid('getSelections');

		if(rows.length<1){
			$.messager.alert('提示','请选择一条记录再操作');
			return;
		}
		$.each(rows,function(i,val){
			var referCode=val['referCode'];
			var nums=referCode.split('|')
			$window.open(POL_SERVER_URL+'index.html#/1300_130040?billingNo='+nums[1]);
		});
	}
}]);

/**
 * 储备金变动明细(储备金余额)
 */
FolController.controller('reserveAmountDeatilChangeCtrl',['$scope','$rootScope','$timeout','ReserveService','$q','$window',function($scope,$rootScope,$timeout,ReserveService,$q,$window){
	$scope.istableshow=false;
	
	$timeout(function(){
		$('#ReserveDeatilWindow').panel({
			onBeforeOpen:function(){
				$('#changeAmountDataTable').attr('class','row-fluid ng-hide');
				$('#freezeAmountDataTable').attr('class','row-fluid ng-hide');
			}
		});
		
		var changeAmountDataGrid=$('#changeAmountDataGrid').datagrid('getPager');
		
		changeAmountDataGrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.reserve_deatil_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.reserve_deatil_data.endNo=pageSize*pageNumber;	
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
					
				ReserveService.findChangeReserveAmount($scope.reserve_deatil_data,'reserve/reservedeatilchange/changedeatilreserve/query',function(data,response_info){
					$('#changeAmountDataTable').attr('class','row-fluid ng-show');
					$('#changeAmountDataGrid').datagrid('loadData',data);
					$.messager.progress('close');
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
					}else{
						$.messager.alert("提示",response_info.message);
					}
				});
			}
		});
		
		var freezeAmountDataGrid=$('#freezeAmountDataGrid').datagrid('getPager');
		freezeAmountDataGrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
		
				$scope.reserve_deatil_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.reserve_deatil_data.endNo=pageSize*pageNumber;	
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				ReserveService.findFreezeReserveAmount($scope.reserve_deatil_data,'reserve/reservedeatilchange/freezedeatilreserve/query',function(data,response_info){
					$('#freezeAmountDataTable').attr('class','row-fluid ng-show');
					$('#freezeAmountDataGrid').datagrid('loadData',data);
					$.messager.progress('close');
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
					}else{
						$.messager.alert("提示",response_info.message);
					}
				});
			}
		});
		

		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		ReserveService.findCodeTypeNames('reserve/reservedeatilchange/findCodeTypeNames',function(data,response_info){
			$.messager.progress('close');
			var typesId=['#deatilDealerType','#deatilBusinessCode'];

			$timeout(function(){
				$('#deatilReserveType').combobox('loadData',$rootScope.reserveType);
				$('#deatilReserveType').combobox('select',$rootScope.reserveType[0]['code']);
				
				for(var i=0;i<data.length;i++){
					if(!folUtil.isNull(data[i])){
						$(typesId[i]).combobox('loadData',data[i]);
						$(typesId[i]).combobox('select',data[i][0]['code']);
					}
				}
			});
		},function(response_info){
			$.messager.progress('close');
			if(folUtil.isNull(response_info)){
				$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
			}
		});
		
	});
	
	
	$scope.findReserveDeatilChange=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var changeReserveAmountResult=false,freezeReserveAmountResult=false;
		if($('#startTime').datebox('getText')>$('#endTime').datebox('getText')){
			$.messager.alert('提示','开始时间不能大于结束时间');
			return;
		}
		
		$scope.reserve_deatil_data={
				dealerType:folUtil.getComboBoxDataById('deatilDealerType'),
				reserveType:folUtil.getComboBoxDataById('deatilReserveType'),
				sapCode:$('#deatilSapCode').textbox('getValue'),
				businessCode:folUtil.getComboBoxDataById('deatilBusinessCode'),
				startTime:$('#startTime').datebox('getValue'),
				endTime:$('#endTime').datebox('getValue'),
				beginNo:0,
				endNo:10
			};
		
		$scope.istableshow=true;

		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		//查询变动储备金
		ReserveService.findChangeReserveAmount($scope.reserve_deatil_data,'reserve/reservedeatilchange/changedeatilreserve/query',function(data,response_info){
			changeReserveAmountResult=true;

			$('#changeAmountDataTable').attr('class','row-fluid ng-show');
			$('#changeAmountDataGrid').datagrid('loadData',{total:0,rows:[]});
			$('#changeAmountDataGrid').datagrid('loadData',data);
			if(freezeReserveAmountResult&&changeReserveAmountResult){
				defferred.resolve('success');
			}
		},function(response_info){
			if(response_info.state===STATE.TIMEOUT){
				console.log(response_info);
				defferred.reject('timeout');
			}else{
				defferred.reject('error');
			}
		});

		//查询冻结储备金
		ReserveService.findFreezeReserveAmount($scope.reserve_deatil_data,'reserve/reservedeatilchange/freezedeatilreserve/query',function(data,response_info){
			freezeReserveAmountResult=true;

			$('#freezeAmountDataTable').attr('class','row-fluid ng-show');
			$('#freezeAmountDataGrid').datagrid('loadData',{total:0,rows:[]});
			$('#freezeAmountDataGrid').datagrid('loadData',data);
			if(freezeReserveAmountResult&&changeReserveAmountResult){
				defferred.resolve('success');
			}
		},function(response_info){
			if(response_info.state==STATE.TIMEOUT){
				defferred.reject('timeout');
			}else{
				defferred.reject('error');
			}
		});
		
		promise.then(function(data){
			console.log(data);
			if(data=='success'){
				$.messager.progress('close');
			}
		},function(response_info){
			$.messager.progress('close');
			$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
		});
	};

	//文件导出
	$scope.deatilChangeResult=function(){
		
		ReserveService.getToken('services/tokens',function(data){
			if(folUtil.isNull($scope.reserve_deatil_data)){
				$.messager.alert('提示','请先查询一次再导出');
				return;
			}
			
			$window.open('reserve/reservedeatilchange/ChangeReserveAmount?dealerType='+$scope.reserve_deatil_data.dealerType+'&reserveType='+$scope.reserve_deatil_data.reserveType+'&businessCode='+$scope.reserve_deatil_data.businessCode+'&sapCode='+$scope.reserve_deatil_data.sapCode+'&startTime='+$scope.reserve_deatil_data.startTime+'&endTime='+$scope.reserve_deatil_data.endTime+'&token='+data['token']);
			$window.open('reserve/reservedeatilchange/FreezeReserveAmount?dealerType='+$scope.reserve_deatil_data.dealerType+'&reserveType='+$scope.reserve_deatil_data.reserveType+'&businessCode='+$scope.reserve_deatil_data.businessCode+'&sapCode='+$scope.reserve_deatil_data.sapCode+'&startTime='+$scope.reserve_deatil_data.startTime+'&endTime='+$scope.reserve_deatil_data.endTime+'&token='+data['token']);
		},function(response_info){
			console.log(response_info);
		});
	};
	
	/**
	 * 订单查看
	 */
	$scope.findOrderDeatil=function(id){
		var rows=$('#'+id).datagrid('getSelections');
		if(rows.length<1){
			$.messager.alert('提示','请选择一条记录再操作');
			return;
		}
		$.each(rows,function(i,val){
			var referCode=val['referCode'];
			var nums=referCode.split('|');
			$window.open(POL_SERVER_URL+'index.html#/1300_130020?orderNo='+nums[0]);
		});
		
	};
	
	/**
	 * billing查看
	 */
	$scope.findBillingDeatil=function(id){
		var rows=$('#'+id).datagrid('getSelections');

		if(rows.length<1){
			$.messager.alert('提示','请选择一条记录再操作');
			return;
		}
		$.each(rows,function(i,val){
			var referCode=val['referCode'];
			var nums=referCode.split('|')
			$window.open(POL_SERVER_URL+'index.html#/1300_130040?billingNo='+nums[1]);
		});
	}
}]);

/**
 * 储备金月度明细
 */
FolController.controller('reserveMonthDeatilCtrl',['$scope','$rootScope','$timeout','ReserveService','$window',function($scope,$rootScope,$timeout,ReserveService,$window){
	$scope.istableshow=false;

	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				
				$scope.reserve_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.reserve_data.endNo=pageSize*pageNumber;
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
					
				ReserveService.findReserveAmount($scope.reserve_data,'reserve/reservemonthdeatil/query',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});
			}
		});
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		ReserveService.findCodeTypeNames('reserve/reservemonthdeatil/findCodeTypeNames',function(data,response_info){
			$.messager.progress('close');
			var typesId=['#dealerType'];

			$timeout(function(){
				$('#reserveType').combobox('loadData',$rootScope.reserveType);
				$('#reserveType').combobox('select',$rootScope.reserveType[0]['code']);
				folUtil.setComboBoxYearDataById('year');
				folUtil.setComboBoxMonthDataById('month');
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
	});
	
	$scope.findReserveMonthDeatil=function(){
		var changeTime=folUtil.getComboBoxDataById('year')+'-'+folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month'));
		$scope.reserve_data={dealerType:folUtil.getComboBoxDataById('dealerType'),
				reserveType:folUtil.getComboBoxDataById('reserveType'),
				sapCode:$('#sapCode').textbox('getValue'),
				dealerName:$('#dealerName').textbox('getValue'),
				changeTime:changeTime,
				beginNo:0,
				endNo:10
			};
		
		$scope.istableshow=true;
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	

		//查询变动储备金
		ReserveService.findReserveMonthDeatil($scope.reserve_data,'reserve/reservemonthdeatil/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};

	//文件导出
	$scope.monthDeatilResult=function(){
		ReserveService.getToken('services/tokens',function(data){
			if(folUtil.isNull($scope.reserve_data)){
				$.messager.alert('提示','请先查询一次再导出');
				return;
			}
			
			console.log('sapCode2:'+$scope.reserve_data.sapCode);
			$window.open('reserve/reservemonthdeatil/monthDeatilResult?dealerName='+encodeURI(encodeURI($scope.reserve_data.dealerName))+'&sapCode='+$scope.reserve_data.sapCode+'&dealerType='+$scope.reserve_data.dealerType+'&reserveType='+$scope.reserve_data.reserveType+'&changeTime='+$scope.reserve_data.changeTime+'&token='+data['token']);
		},function(response_info){
			console.log(response_info);
		});
	};
	
	/**
	 * 订单查看
	 */
	$scope.findOrderDeatil=function(id){
		var rows=$('#'+id).datagrid('getSelections');
		if(rows.length<1){
			$.messager.alert('提示','请选择一条记录再操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示','不能选择多条记录，请选择一条记录再操作');
			return;
		}else if(rows[0]['referType'].indexOf('SAP')>-1){
			$.messager.alert('提示','该条记录不能查看订单相关信息');
			return;
		}
		$.each(rows,function(i,val){
			var referCode=val['referCode'];
			var nums=referCode.split('|');
			$window.open(POL_SERVER_URL+'index.html#/1300_130020?orderNo='+nums[0]);
		});
		
	};
	
	/**
	 * billing查看
	 */
	$scope.findBillingDeatil=function(id){
		var rows=$('#'+id).datagrid('getSelections');

		if(rows.length<1){
			$.messager.alert('提示','请选择一条记录再操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示','不能选择多条记录，请选择一条记录再操作');
			return;
		}else if(rows[0]['referType'].indexOf('SAP')>-1){
			$.messager.alert('提示','该条记录不能查看billing相关信息');
			return;
		}
		$.each(rows,function(i,val){
			var referCode=val['referCode'];
			var nums=referCode.split('|')
			$window.open(POL_SERVER_URL+'index.html#/1300_130040?billingNo='+nums[1]);
		});
	}
}]);
/**
 * 储备金余额对账
 */
FolController.controller('reserveAmountReconcileCtrl',['$scope','$rootScope','$timeout','ReserveService','$window',function($scope,$rootScope,$timeout,ReserveService,$window){
	$scope.istableshow=false;

	function init(){
		//分页初始化
		$timeout(function(){
			if($('#datagrid').length>0){
				var datagrid=$('#datagrid').datagrid('getPager');
				datagrid.pagination({
					onSelectPage:function(pageNumber,pageSize){
		
						$scope.reserve_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
						$scope.reserve_data.endNo=pageSize*pageNumber;
						
						$.messager.progress({
							title:'Please waiting',
							msg:'Loading data...',
							interval:PROGRESS_ACTION_TIMEOUT
						});	
						
						ReserveService.findReserveAmountReconcile($scope.reserve_data,'reserve/reserveamountreconcile/query',function(data,response_info){
							$.messager.progress('close');
							$('#datagrid').datagrid('loadData',data);
						},function(response_info){
							$.messager.progress('close');
							if(response_info.state===STATE.TIMEOUT){
								console.log('timeout');
								$.messager.alert('提示','系统超时请稍后访问');
							}
						});
					}
				});
			}
		},PROGRESS_ACTION_TIMEOUT);
	}
	
	$scope.findReserveAmountReconcile=function(){
		$scope.istableshow=true;
		
		init();
		
		var changeTime=folUtil.getComboBoxDataById('year')+'-'+folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month'));
		$scope.reserve_data={dealerType:folUtil.getComboBoxDataById('dealerType'),
				reserveType:folUtil.getComboBoxDataById('reserveType'),
				startSapCode:$('#startSapCode').textbox('getValue'),
				endSapCode:$('#endSapCode').textbox('getValue'),
				changeTime:changeTime,
				year:folUtil.getComboBoxDataById('year'),
				month:folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month')),
				difference:folUtil.getComboBoxDataById('difference'),
				beginNo:0,
				endNo:10
		};

		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		ReserveService.findReserveAmountReconcile($scope.reserve_data,'reserve/reserveamountreconcile/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};
	
	$.messager.progress({
		title:'Please waiting',
		msg:'Loading data...',
		interval:PROGRESS_ACTION_TIMEOUT
	});	
	
	ReserveService.findCodeTypeNames('reserve/reserveamountreconcile/findCodeTypeNames',function(data,response_info){
		$.messager.progress('close');
		var typesId=['#dealerType'];
		
		$timeout(function(){
			$('#reserveType').combobox('loadData',$rootScope.reserveType);
			$('#reserveType').combobox('select',$rootScope.reserveType[0]['code']);
			folUtil.setComboBoxYearDataById('year');
			folUtil.setComboBoxMonthDataById('month');
			folUtil.setComboBoxYesAndNoById('difference');
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
	//文件导出
	$scope.amountReconcile=function(){
			
			ReserveService.getToken('services/tokens',function(data,response_info){
				if(folUtil.isNull($scope.reserve_data)){
					$.messager.alert('提示','请先查询一次再导出');
					return;
				}
				
				$window.open('reserve/reserveamountreconcile/amountReconcile?dealerType='+$scope.reserve_data.dealerType+'&reserveType='+$scope.reserve_data.reserveType+'&startSapCode='+$scope.reserve_data.startSapCode+'&endSapCode='+$scope.reserve_data.endSapCode+'&changeTime='+$scope.reserve_data.changeTime+'&year='+$scope.reserve_data.year+'&month='+$scope.reserve_data.month+'&difference='+$scope.reserve_data.difference+'&token='+data['token']);
			},function(response_info){
				console.log(response_info);
			});
		};
}]);
/**
 * 资金余额报表
 */
FolController.controller('FundBalanceStatementCtrl',['$scope','$rootScope','$timeout','FundBalanceStatementService','CodeService','$window',function($scope,$rootScope,$timeout,FundBalanceStatementService,CodeService,$window){
	$scope.istableshow=false;
	
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.fund_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
				$scope.fund_data.endNo=pageSize*pageNumber;
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
			
				FundBalanceStatementService.query($scope.fund_data,'fund/fundBanceStatement/query',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});	
			}
		});
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		FundBalanceStatementService.findCodeTypeNames('fund/fundBanceStatement/findCodeTypeNames',function(data){
			$.messager.progress('close');
			
			var typesId=['#dealerType'];
			
			$timeout(function(){
				$('#reserveType').combobox('loadData',$rootScope.reserveType);
				$('#reserveType').combobox('select',$rootScope.reserveType[0]['code']);
			
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
			console.log("error:"+response_info);
		});
	});
	
	/**
	 * 资金余额报表查询
	 */
	$scope.query=function(){
		$scope.fund_data={
				dealerType:folUtil.getComboBoxDataById('dealerType'),
				reserveType:folUtil.getComboBoxDataById('reserveType'),
				endSapCode:$('#endSapCode').textbox('getValue'),
				startSapCode:$('#startSapCode').textbox('getValue'),
				dealerName:$('#dealerName').textbox('getValue'),			
				beginNo:0,
				endNo:10
			};
					
		$scope.istableshow=true;
			
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		FundBalanceStatementService.query($scope.fund_data,'fund/fundBanceStatement/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}	
		});	
	};
				
	/**
	 * 文件导出
	 */
	$scope.exportFile=function(){		
		FundBalanceStatementService.getToken('services/tokens',function(data,response_info){
			if(folUtil.isNull($scope.fund_data)){
				$.messager.alert('提示','请先查询一次再导出');
				return;
			}
			
			$.messager.progress('close');
			$window.open('fund/fundBanceStatement/exportFile?reserveType='+$scope.fund_data.reserveType+'&endSapCode='+$scope.fund_data.endSapCode+'&startSapCode='+$scope.fund_data.startSapCode+'&dealerType='+$scope.fund_data.dealerType+'&dealerName='+$scope.fund_data.dealerName+'&token='+data['token']);
		},function(response_info){
			console.log(response_info);
		});
	};
}]);
/**
 * 客户对账单倒入controller
 */
FolController.controller('DealerReconciliationImportController',['$scope','$state','$timeout','$window','DealerReconciliationImportService',function($scope,$state,$timeout,$window,DealerReconciliationImportService){
	$scope.istableshow=false;
	$scope.isdeatiltableshow=false;
	
	$timeout(function(){
		folUtil.setComboBoxYearDataById('year');
		folUtil.setComboBoxMonthDataById('month');

		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.query_data.endNo=pageSize*pageNumber;
					
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				DealerReconciliationImportService.query($scope.query_data,'reconciliation/billFile/queryBillFile',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});
			}
		});
	});
	
	//对账单查询
	$scope.query=function(){
		$scope.istableshow=true;
		var queryTime=folUtil.getComboBoxDataById('year')+'-'+folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month'));
		
		$scope.$apply(function(){
			$scope.query_data={
					currentDate:queryTime,
					year:folUtil.getComboBoxDataById('year'),
					month:folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month')),
					beginNo:0,
					endNo:10
			};
		});
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		DealerReconciliationImportService.query($scope.query_data,'reconciliation/billFile/queryBillFile',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};
	
	//文件上传功能
	$scope.checkIn=function(){
		$state.go('uploadFilePage',{pageName:'bill'});
	};
	
	//数据导出功能
	$scope.checkOut=function(){
		if(folUtil.isNull($scope.query_data)){
			$.messager.alert('提示', '先查询一次再导出');
			return;
		}
		var queryTime=$scope.query_data.year+'-'+$scope.query_data.month;
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		//文件导出
		DealerReconciliationImportService.getToken('services/tokens',function(data){
			$.messager.progress('close');
			$window.open('reconciliation/billFile/exportBillFile?currentDate='+queryTime+'&token='+data['token']);

		},function(response_info){
			$.messager.progress('close');
			$.messager.alert('提示','系统错误或者超时，请刷新重试');
		});
	};
	
	//总对账单状态修改已发布
	$scope.reconciliationStatusUpdateWithPublish=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		var flag=false;
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择需要发布的记录，再去操作发布');
			return;
		}
		
		$.each(rows,function(i,val){
			if(val.status==STATE.PUBLISHED){
				$.messager.alert('提示','该文件已发布不能再发布');
				flag=true;
				return;
			}
		});
		
		if(flag){
			return;
		}else{
			DealerReconciliationImportService.getToken('services/tokens',function(data){
				$.each(rows,function(i,val){
					val.token=data['token'];
				});
				
				DealerReconciliationImportService.reconciliationStatusUpdate(rows,'reconciliation/billFile/publishFileStatus',function(data,response_info){
					$.messager.alert('提示','发布进行中，请稍后查看');
					DealerReconciliationImportService.query($scope.query_data,'reconciliation/billFile/queryBillFile',function(data,response_info){
						$('#datagrid').datagrid('loadData',{total:0,rows:[]});
						$('#datagrid').datagrid('loadData',data);
					},function(data){
						console.log(data);
					});
					
					DealerReconciliationImportService.reconciliationStatusUpdateWithPublish(rows,'reconciliation/billFile/publishFile',function(data,response_info){
					DealerReconciliationImportService.query($scope.query_data,'reconciliation/billFile/queryBillFile',function(data,response_info){
						$('#datagrid').datagrid('loadData',{total:0,rows:[]});
						$('#datagrid').datagrid('loadData',data);
					},function(data){
						console.log(data);
					});
					},function(data,response_info){
						/*$.messager.alert('提示','发布失败');
					console.log(data);*/
					});
				},function(data,response_info){
					$.messager.alert('提示','发布失败');
					console.log(data);
				});
			},function(response_info){
				console.log(response_info);
			});
			
		}
	};

	//打开明细界面
	$scope.findReconciliationDeatil=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length>0){
			if(rows[0].status===STATE.UN_PUBLISH || rows[0].status===STATE.PUBLISHING){
				$.messager.alert("提示", "该文件未发布不能查看明细");
				return;
			}
			$timeout(function(){
				var deatildatagrid=$('#deatildatagrid').datagrid('getPager');
				deatildatagrid.pagination({
					onSelectPage:function(pageNumber,pageSize){
						$scope.query_params.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
						$scope.query_params.endNo=pageSize*pageNumber;
							
						$.messager.progress({
							title:'Please waiting',
							msg:'Loading data...',
							interval:PROGRESS_ACTION_TIMEOUT
						});
						
						DealerReconciliationImportService.ReconciliationSignQuery($scope.query_data,'reconciliation/billFile/queryBillFileDetail',function(data,response_info){
							$.messager.progress('close');
							$('#deatildatagrid').datagrid('loadData',data);
						},function(response_info){
							$.messager.progress('close');
							if(response_info.state===STATE.TIMEOUT){
								console.log('timeout');
								$.messager.alert('提示','系统超时请稍后访问');
							}
						});
					}
				});
			});
			console.log('year1:'+$scope.query_data.year);
			$('#DeatilWindow').window('open');
			
		}else if(rows.length>1){
			$.messager.alert("提示", "选择多条文件，不能查看明细，请选择单条文件");
		}else{
			$.messager.alert("提示", "请选择文件才能查看明细");
		}
	};
	
}]);

/**
 * 客户对账单明细controller
 */
FolController.controller('DealerReconciliationDeatilController',['$scope','$state','$timeout','$window','DealerReconciliationImportService',function($scope,$state,$timeout,$window,DealerReconciliationImportService){
	$timeout(function(){
		$('#DeatilWindow').panel({
			onBeforeOpen:function(){
				$('#deatilistable').attr('class','row-fluid ng-hide');
				$('#dealerCode').textbox('setValue','');
				$('#dealerName').textbox('setValue','');
				$('#deatilYear').textbox('setValue',$scope.query_data.year);
				$('#deatilMonth').textbox('setValue',$scope.query_data.month);
			}
		});
		
		var deatildatagrid=$('#deatildatagrid').datagrid('getPager');
		deatildatagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.query_params.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.query_params.endNo=pageSize*pageNumber;
					
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});
				
				DealerReconciliationImportService.ReconciliationSignQuery($scope.query_data,'reconciliation/billFile/queryBillFileDetail',function(data,response_info){
					$.messager.progress('close');
					$('#deatildatagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});
			}
		});
	});

	//客户对账单明细查询
	$scope.ReconciliationSignQuery=function(){
		console.log($scope.query_data);
//		var currentTime=$scope.query_data.year+'-'+$scope.query_data.month;
		var currentTime=$('#deatilYear').textbox('getValue')+'-'+$('#deatilMonth').textbox('getValue');

		$scope.$apply(function(){
			$scope.query_params={
					currentDate:currentTime,
					dealerCode:folUtil.isNull($scope.dealerCode)?'':$scope.dealerCode,
					dealerName:folUtil.isNull($scope.dealerName)?'':$scope.dealerName,
					beginNo:0,
					endNo:10
				};
		});

		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		//查询客户对账单
		DealerReconciliationImportService.ReconciliationSignQuery($scope.query_params,'reconciliation/billFile/queryBillFileDetail',function(data,response_info){
			$.messager.progress('close');
			
			$('#deatilistable').attr('class','row-fluid ng-show');
			console.log($('#deatilistable').attr('class'));
			
			$('#deatildatagrid').datagrid('loadData',{total:0,rows:[]});
			$('#deatildatagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});

	};
	
	/**
	 * 对账单明细导出
	 * @Author wangfl
	 */
	$scope.exportRecDeatil = function(){
		var rows=$('#deatildatagrid').datagrid('getSelections');
		
		if(rows.length>1){
			$.messager.alert('提示','不能查看多条记录的明细');
			return;
		}else if(rows.length<1){
			$.messager.alert('提示','请选择一条记录，再点击查看明细');
			return;
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		//alert(JSON.stringify(rows[0]));
		DealerReconciliationImportService.getToken('services/tokens',function(data){
			$.messager.progress('close');
			$window.open('reconciliation/billFile/exportBillFileDetailSingle?filedId='+rows[0]['filedId']+'&token='+data['token']);
		},function(data){
			console.log(data);
		});
	};
	
	//客户对账单明细导出
	$scope.checkOutDeatil=function(){
		var queryTime=$scope.query_data.year+'-'+$scope.query_data.month;
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		DealerReconciliationImportService.getToken('services/tokens',function(data){
			$.messager.progress('close');
			console.log('reconciliation/billFile/exportBillFileDetail?dealerCode='+$scope.query_params.dealerCode+'&dealerName='+$scope.query_params.dealerName+'&currentDate='+queryTime+'&token='+data['token']);
			$window.open('reconciliation/billFile/exportBillFileDetail?dealerCode='+$scope.query_params.dealerCode+'&dealerName='+$scope.query_params.dealerName+'&currentDate='+queryTime+'&token='+data['token']);
		},function(response_info){
			$.messager.progress('close');
			$.messager.alert('提示','系统错误或者超时，请刷新重试');
		});
	};
}]);

/**
 * 索赔发票状态查询
 */
FolController.controller('ChaimInvoiceStatusController',['$scope','$rootScope','$q','$timeout','ChaimInvoiceStatusService','CodeService','$window',function($scope,$rootScope,$q,$timeout,ChaimInvoiceStatusService,CodeService,$window){
	$scope.istableshow=false;
	$scope.flag=true
	
	function pagination(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.bill_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
				$scope.bill_data.endNo=pageSize*pageNumber;
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				ChaimInvoiceStatusService.query($scope.bill_data,'invoice/statusQuery/query',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
					console.log("error:"+response_info);
	
				});
			}
		});
	}

	$timeout(function(){
		var fields=['SGM受理状态'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
			
			var typesId=['#status'];
			
//			var timer=$timeout(function(){
				for(var i=0;i<data.length;i++){
					if(!folUtil.isNull(data[i])){
						$(typesId[i]).combobox('loadData',data[i]);
						$(typesId[i]).combobox('select',data[i][0]['code']);
					}
				}
//			});			
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	});
	
	$scope.query=function(){	
		$scope.bill_data={
				sapCode:$('#sapCode').textbox('getValue'),
				invoiceNo:$('#invoiceNo').textbox('getValue'),
				expressNo:$('#expressNo').textbox('getValue'),
				status:folUtil.getComboBoxDataById('status'),
				approveMan:$('#approveMan').textbox('getValue'),
				beginNo:0,
				endNo:10
			};
		$scope.istableshow=true;
			
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		ChaimInvoiceStatusService.query($scope.bill_data,'invoice/statusQuery/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
			
			if($scope.flag){
				$('#datagrid').datagrid().datagrid('enableCellEditing');
				pagination();
			}

			$scope.flag=false;
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
			console.log("error:"+response_info);
		});	
					
	};
		//重处理
	$scope.anew=function(){
		var rows=$('#datagrid').datagrid('getChecked');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}	
		
		var invoiceNoStr = "";
		for(var rownum in rows){
			if(invoiceNoStr.length>0){
				invoiceNoStr = invoiceNoStr + ",";
			}
			invoiceNoStr = invoiceNoStr + rows[rownum]['invoiceNo']+rows[rownum]['sapCode'];
			if(rows[rownum]['status']!= 5){
				$.messager.alert('提示','当前状态不是SAP处理失败状态，不能给予重处理操作！');
				return;
			}
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		ChaimInvoiceStatusService.anew({"invoiceNo":invoiceNoStr},'invoice/statusQuery/rehandle',function(data,response_info){
			$.messager.alert('提示','重处理操作成功');
			$.messager.progress('close');
			ChaimInvoiceStatusService.anew($scope.bill_data,'invoice/statusQuery/query',function(data,response_info){
				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
				$('#datagrid').datagrid('loadData',data);
			},function(response_info){
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
				console.log("error:"+response_info);
			});		
	
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
				return;
			}
			$.messager.alert('提示','重处理操作失败');
			console.log("error:"+response_info);
		});		
	};	
	
	//保存备注
	$scope.save=function(){
		$scope.endEditing('datagrid');	
		
		var rows=$('#datagrid').datagrid('getChecked');

		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}
					
		var datas= [];
		$.each(rows,function(i,val){
			datas.push({"invoiceNo":val['invoiceNo'],"sapCode":val['sapCode'],"remark":val['remark']});
		});
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		/*$scope.bill_data={	
	//			approveMsg:$scope.approveMsg,
				remark:$scope.remark,
				beginNo:0,
				endNo:10
		};*/
		ChaimInvoiceStatusService.checkdata(datas,'invoice/statusQuery/saveRemark/getSign',function(data,response_info){
			ChaimInvoiceStatusService.save(data.rows,'invoice/statusQuery/saveRemark',function(data,response_info){
				$.messager.alert('提示','保存成功');
				$.messager.progress('close');
				ChaimInvoiceStatusService.query($scope.bill_data,'invoice/statusQuery/query',function(data,response_info){
					$('#datagrid').datagrid('loadData',{total:0,rows:[]});
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
					console.log("error:"+response_info);
				});		
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
					return;
				}
				$.messager.alert('提示','保存失败');
				console.log("error:"+response_info);
			});	
		},function(response_info){
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};	
	//退回
	$scope.back=function(){
					
		var rows=$('#datagrid').datagrid('getChecked');

		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}
		for(var rownum in rows){
			if(rows[rownum]['status'] == 4){
				$.messager.alert('提示','当前状态是SAP处理成功状态，不能给予退回操作！');
				return;
			}
		}
		var back_datas = "";
		$.each(rows,function(i,val){
			if(back_datas.length > 0){
				back_datas = back_datas + ",";
			}
			back_datas = back_datas + val['invoiceNo']+"||"+val['sapCode'];
		});
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
			
		ChaimInvoiceStatusService.back({"invoiceNo":back_datas},'invoice/statusQuery/sendBack',function(data,response_info){
			$.messager.alert('提示','退回操作成功');
			ChaimInvoiceStatusService.query($scope.bill_data,'invoice/statusQuery/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
				console.log("error:"+response_info);
			});		
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
				return;
			}
			$.messager.alert('提示','退回操作失败');
			console.log("error:"+response_info);
		});		
	};	

	//打开明细界面
	$scope.deatil=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		if(rows.length<=0){
			$.messager.alert('提示','选择一条记录，再操作');
			return
		}else if(rows.length>1){
			$.messager.alert('提示','只能一条记录，再操作');
			return
		}
	
		$rootScope.claimStatus={};
		$rootScope.claimStatus.sapCode = rows[0]['sapCode'];
		$rootScope.claimStatus.invoiceNo=rows[0]['invoiceNo'];
		$rootScope.claimStatus.expressNo = rows[0]['expressNo'];
		
		ChaimInvoiceStatusService.queryDeatil($rootScope.claimStatus,'invoice/statusQuery/detail',function(data,response_info){
			$('#deatildatagrid').datagrid('loadData',{total:0,rows:[]});
			$('#deatildatagrid').datagrid('loadData',data);
		},function(response_info){
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
			console.log("error:"+response_info);
		});
		
		$('#DeatilWindow').window('open');
			
	}
		
	$scope.endEditing=function(tableid){
		var rows=$('#'+tableid).datagrid('getRows');
		   $.each(rows,function(i,val){
			   if ($('#datagrid').datagrid('validateRow', i)){
			        $('#datagrid').datagrid('endEdit', i);
			   }
		   });
	};

}]);
/**
 *	明细controller
 */
FolController.controller('DeatilController',['$scope','$rootScope','$state','$timeout','$window','ChaimInvoiceStatusService',function($scope,$rootScope,$state,$timeout,$window,ChaimInvoiceStatusService){

	$timeout(function(){		
		if($('#deatildatagrid').length>0){
			var datagrid=$('#deatildatagrid').datagrid('getPager');

			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){

					$scope.bill_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
					$scope.bill_data.endNo=pageSize*pageNumber;
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					ChaimInvoiceStatusService.query($scope.bill_data,'invoice/statusQuery/query',function(data,response_info){
						$.messager.progress('close');
						$('#deatildatagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
						console.log("error:"+response_info);
					});
				}
			});
		}
		
	});
	$scope.exit=function(){
//		$state.go('StayInvoiceEdit');
		$('#DeatilWindow').window('close');
		$scope.bill_data={
				expressNo:$('#sapCode').textbox('getValue'),
				invoiceNo:$('#invoiceNo').textbox('getValue'),
				expressNo:$('#expressNo').textbox('getValue'),
				status:folUtil.getComboBoxDataById('status'),
				expressNo:$('#approveMan').textbox('getValue'),
				beginNo:0,
				endNo:10
			};
		$scope.istableshow=true;
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		ChaimInvoiceStatusService.query($scope.bill_data,'invoice/statusQuery/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#deatildatagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
			console.log("error:"+response_info);
		});
	};	
}]);
/**
 * 快递单号扫描发票
 */
FolController.controller('ExpressScanInvoiceController',['$scope','$rootScope','$q','$timeout','ExpressScanInvoiceService','$window',function($scope,$rootScope,$q,$timeout,ExpressScanInvoiceService,$window){
	
	function pagination(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){

				$scope.bill_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
				$scope.bill_data.endNo=pageSize*pageNumber;
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				ExpressScanInvoiceService.query($scope.bill_data,'invoice/expressNoScanInvoice/query',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
					console.log("error:"+response_info);
	
				});
			}
		});
	}
	$scope.istableshow=false;
//	$timeout(function(){
//		if($('#datagrid').length>0){
//
//		}
//});
		//查询
	$scope.query=function(){

			$scope.bill_data={
					invoiceNo:$('#invoiceNo').textbox('getValue'),
					expressNo:$('#expressNo').textbox('getValue'),
					sapCode:$('#sapCode').textbox('getValue'),
					beginNo:0,
					endNo:10
				};
			$scope.istableshow=true;

			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
					ExpressScanInvoiceService.query($scope.bill_data,'invoice/expressNoScanInvoice/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagrid').datagrid('loadData',{total:0,rows:[]});
						$('#datagrid').datagrid('loadData',data);
						pagination();
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
						console.log("error:"+response_info);
					});		
				};
				
		//同意
	$scope.agree=function(){
			var rows=$('#datagrid').datagrid('getChecked');
	
			if(rows.length<=0){
				$.messager.alert('提示','请选择至少一条记录，再进行操作');
				return;
			}else if(rows[0]['status'] !=2 ){
				$.messager.alert('提示','当前状态已处理,不能给予操作');
				return;
			}
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	

					ExpressScanInvoiceService.agree(rows,'invoice/expressNoScanInvoice/auditSuccess',function(data,response_info){
						$.messager.alert('提示','同意操作成功');
						ExpressScanInvoiceService.query($scope.bill_data,'invoice/expressNoScanInvoice/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagrid').datagrid('loadData',{total:0,rows:[]});
						$('#datagrid').datagrid('loadData',data);
						},function(response_info){
							$.messager.progress('close');
							if(response_info.state===STATE.TIMEOUT){
								console.log('timeout');
								$.messager.alert('提示','系统超时请稍后访问');
							}
						});	
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
							return;
						}
						$.messager.alert('提示','同意操作失败');
						console.log("error:"+response_info);
					});		
				};
		//退回
	$scope.back=function(){
				var rows=$('#datagrid').datagrid('getChecked');
				
				if(rows.length<=0){
					$.messager.alert('提示','请选择至少一条记录，再进行操作');
					return;
				}else if(rows[0]['status'] !=2 ){
					$.messager.alert('提示','当前状态已处理,不能给予操作');
					return;
				}
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	

					ExpressScanInvoiceService.back(rows,'invoice/expressNoScanInvoice/reject',function(data,response_info){
						$.messager.alert('提示','退回操作成功');
						ExpressScanInvoiceService.query($scope.bill_data,'invoice/expressNoScanInvoice/query',function(data,response_info){
							$.messager.progress('close');
							$('#datagrid').datagrid('loadData',{total:0,rows:[]});
							$('#datagrid').datagrid('loadData',data);
						},function(response_info){
							$.messager.progress('close');
							if(response_info.state===STATE.TIMEOUT){
								console.log('timeout');
								$.messager.alert('提示','系统超时请稍后访问');
							}
							console.log("error:"+response_info);
						});		
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
							return;
						}
						$.messager.alert('提示','退回操作失败');
						console.log("error:"+response_info);
					});		
				};	

}]);

/**
 * SAP每日财务凭证查询
 */
FolController.controller('FinancialCertificateController',['$scope','$rootScope','$q','$timeout','FinancialCertificateService','$window',function($scope,$rootScope,$q,$timeout,FinancialCertificateService,$window){
	
	$scope.istableshow=false;
	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){

				$scope.bill_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
				$scope.bill_data.endNo=pageSize*pageNumber;
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				FinancialCertificateService.query($scope.bill_data,'financial/certificate/query',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
					console.log("error:"+response_info);
	
				});
			}
		});
});
		//查询
	$scope.query=function(){
			$scope.bill_data={
					sapCode:$('#sapCode').textbox('getValue'),
					approveMan:$('#approveMan').textbox('getValue'),
					startTime:$('#startTime').datebox('getValue'),
					endTime:$('#endTime').datebox('getValue'),
					beginNo:0,
					endNo:10
				};
			if($('#startTime').datebox('getValue') !=null 
					&& $('#endTime').datebox('getValue')!=null
					&& $('#startTime').datebox('getValue')!=''
					&& $('#endTime').datebox('getValue')!=''){
				
				if($('#startTime').datebox('getValue')>$('#endTime').datebox('getValue')){
					$.messager.alert('提示','开始时间不能大于结束时间');
					return;
				}
				
			}
			$scope.istableshow=true;
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					FinancialCertificateService.query($scope.bill_data,'financial/certificate/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagrid').datagrid('loadData',{total:0,rows:[]});
						$('#datagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
						console.log("error:"+response_info);
					});		
				};
				//文件导出
	$scope.exportCertificate=function(){
					
					FinancialCertificateService.getToken('services/tokens',function(data){
						if(folUtil.isNull($scope.bill_data)){
							$.messager.alert('提示','请先查询一次再导出');
							return;
						}
						
						$.messager.progress('close');
						$window.open('financial/certificate/invoiceDeatilExp?sapCode='+$scope.bill_data.sapCode+'&approveMan='+encodeURI(encodeURI($scope.bill_data.approveMan))+'&startTime='+$scope.bill_data.startTime+'&endTime='+$scope.bill_data.endTime+'&token='+data['token']);
					},function(response_info){
						$.messager.progress('close');
						console.log(response_info);
					});
				};

}]);

/**
 * FOL-SAP接口状态查询
 */
FolController.controller('InterfaceStatusController',['$scope','$rootScope','$q','$timeout','InterfaceStatusService','CodeService','$window',function($scope,$rootScope,$q,$timeout,InterfaceStatusService,CodeService,$window){
	
	$scope.istableshow=false;
	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){

				$scope.bill_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
				$scope.bill_data.endNo=pageSize*pageNumber;
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				InterfaceStatusService.query($scope.bill_data,'interface/status/query',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
					console.log("error:"+response_info);
	
				});
			}
		});
		var fields=['SGM受理状态'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
			
			var typesId=['#process_status'];
			
			$timeout(function(){
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
});

		//查询
	$scope.query=function(){
			$scope.bill_data={
					invoiceNo:$('#invoiceNo').textbox('getValue'),
					process_status:folUtil.getComboBoxDataById('process_status'),
					startTime:$('#startTime').datebox('getValue'),
					endTime:$('#endTime').datebox('getValue'),
					beginNo:0,
					endNo:10
				};
			if($('#startTime').datebox('getValue') !=null 
					&& $('#endTime').datebox('getValue')!=null
					&& $('#startTime').datebox('getValue')!=''
					&& $('#endTime').datebox('getValue')!=''){
				
				if($('#startTime').datebox('getValue')>$('#endTime').datebox('getValue')){
					$.messager.alert('提示','开始时间不能大于结束时间');
					return;
				}
				
			}
			$scope.istableshow=true;
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					InterfaceStatusService.query($scope.bill_data,'interface/status/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagrid').datagrid('loadData',{total:0,rows:[]});
						$('#datagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
						console.log("error:"+response_info);
					});		
				};

}]);

/**
 * 经销商提交超时未处理发票查询
 */
FolController.controller('OvertimeInvoiceController',['$scope','$rootScope','$q','$timeout','OvertimeInvoiceService','$window',function($scope,$rootScope,$q,$timeout,OvertimeInvoiceService,$window){
	
	$scope.istableshow=false;
	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){

				$scope.bill_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
				$scope.bill_data.endNo=pageSize*pageNumber;
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				OvertimeInvoiceService.query($scope.bill_data,'overtime/invoice/query',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
					console.log("error:"+data);
	
				});
			}
		});
});
		//查询
	$scope.query=function(){
			$scope.bill_data={
					sapCode:$('#sapCode').textbox('getValue'),
					invoiceNo:$('#invoiceNo').textbox('getValue'),
					expressNo:$('#expressNo').textbox('getValue'),
					startTime:$('#startTime').textbox('getValue'),
					endTime:$('#endTime').textbox('getValue'),
			//		status:folUtil.getComboBoxDataById('status'),
					beginNo:0,
					endNo:10
				};
			$scope.istableshow=true;
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					OvertimeInvoiceService.query($scope.bill_data,'overtime/invoice/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagrid').datagrid('loadData',{total:0,rows:[]});
						$('#datagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
						console.log("error:"+response_info);
					});		
				};

}]);

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