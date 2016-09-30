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
