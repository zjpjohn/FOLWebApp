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