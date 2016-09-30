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
