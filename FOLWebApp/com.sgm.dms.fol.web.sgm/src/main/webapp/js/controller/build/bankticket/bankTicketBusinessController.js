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