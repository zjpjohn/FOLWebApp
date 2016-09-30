/**
 * 票据贴息清单controller
 */
FolController.controller('BankTicketInterestInventoryController',['$scope','$rootScope','$timeout','$q','$state','$window','BankTicketInterestInventoryService',function($scope,$rootScope,$timeout,$q,$state,$window,BankTicketInterestInventoryService){
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
					
					BankTicketInterestInventoryService.query($scope.query_data,'bankTicket/businessInquiries/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagrid').datagrid('loadData',data);
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
		
		folUtil.setComboBoxYearDataById('year');
		folUtil.setComboBoxMonthDataById('month');
	});
	
	$scope.query=function(){
		$scope.istableshow=true;		
		
		$scope.query_data={
				year:folUtil.getComboBoxDataById('year'),
				month:folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month')),
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
			$('#datagrid').datagrid('loadData',data);
		},function(data,response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
			console.log("error:"+data);
		});
	}

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
			$window.open('bankTicket/businessInquiries/exportInterestAmount?year='+$scope.query_data.year+'&month='+$scope.query_data.month+'&token='+data['token']);
		},function(data){
			$.messager.progress('close');
			console.log(data);
		});
	};
	
	$scope.confirm=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		if(rows.length<=0){
			$.messager.alert('提示','选择一条记录，再操作');
		}else if(rows.length>1){
			$.messager.alert('提示','只能一条记录，再操作');
		}else{
			BankTicketInterestInventoryService.confirm(rows[0],'bankTicket/businessInquiries/confirm',function(response_info){
				$.messager.alert('提示', '发布成功');
				
				BankTicketInterestInventoryService.query($scope.query_data,'bankTicket/businessInquiries/query',function(data,response_info){
					$('#datagrid').datagrid('loadData',data);
				},function(data,response_info){
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
					console.log("error:"+data);
				});
			},function(response_info){
				$.messager.alert('提示', '发布失败');
			});
		}
	};
	
	$scope.bankTicketInterestDeatil=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		if(rows.length<=0){
			$.messager.alert('提示','选择一条记录，再操作');
			return
		}else if(rows.length>1){
			$.messager.alert('提示','只能一条记录，再操作');
			return
		}
		
		rows[0]['year']=folUtil.getComboBoxDataById('year');
		rows[0]['month']=folUtil.getComboBoxDataById('month');
		$rootScope.bankticket_interest_deatil_data=rows[0];
		
		$('#interestDeatilWindow').window('open');
	//	$state.go('bankTicketInterestDeatil');
	};
}]);