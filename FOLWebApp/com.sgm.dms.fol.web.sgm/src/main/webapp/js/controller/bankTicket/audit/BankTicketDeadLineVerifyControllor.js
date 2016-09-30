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


