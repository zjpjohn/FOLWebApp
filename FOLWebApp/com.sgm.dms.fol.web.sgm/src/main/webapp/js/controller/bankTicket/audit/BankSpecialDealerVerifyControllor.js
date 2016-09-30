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
