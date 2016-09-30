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