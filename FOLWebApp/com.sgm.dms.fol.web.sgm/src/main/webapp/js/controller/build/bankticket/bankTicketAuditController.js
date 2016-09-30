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



/**
 *票据贴息年化利率审核
 */
FolController.controller('TicketInterestRateAuditController',['$scope','$timeout','$state','$q','TicketInterestRateAuditService',function($scope,$timeout,$state,$q,TicketInterestRateAuditService){
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
					
					TicketInterestRateAuditService.query($scope.query_data,'interestRate/audit/query',function(data,response_info){
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
			TicketInterestRateAuditService.query($scope.query_data,'interestRate/audit/query',function(data,response_info){
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
	});

	/**
	 * 票据贴息年化利率审核通过
	 */
	$scope.auditSuccess=function(){

		var rows=$('#datagrid').datagrid('getChecked');

		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}
		
		var datas= [];
		$.each(rows,function(i,val){
			datas.push({auditMsg:val['auditMsg'],auditStatus:val['auditStatus']});
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

		TicketInterestRateAuditService.auditSuccess(datas,'interestRate/audit/auditSuccess',function(data,response_info){
			$.messager.alert('提示','审批成功');
			TicketInterestRateAuditService.query($scope.query_data,'interestRate/audit/query',function(data,response_info){
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
	 * 票据贴息年化利率驳回
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
		$scope.query_data={
				auditStatus:$scope.auditStatus,		
				beginNo:0,
				endNo:10
		};
		TicketInterestRateAuditService.reject(datas,'interestRate/audit/reject',function(data,response_info){
			$.messager.alert('提示','驳回成功');
			TicketInterestRateAuditService.query($scope.query_data,'interestRate/audit/query',function(data,response_info){
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
