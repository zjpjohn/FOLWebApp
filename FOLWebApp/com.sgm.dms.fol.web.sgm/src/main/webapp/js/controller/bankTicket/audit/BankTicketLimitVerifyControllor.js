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


