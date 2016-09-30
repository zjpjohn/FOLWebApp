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