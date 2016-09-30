/**
 * 银行特殊经销商维护controller
 */
FolController.controller('BankSpecialDealerMaintainController',['$scope','$timeout','$state','$q','BankSpecialDealerMaintainService','CodeService',function($scope,$timeout,$state,$q,BankSpecialDealerMaintainService,CodeService){
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
					
					BankSpecialDealerMaintainService.query($scope.query_data,'bankTicket/dealerBankRelevance/query',function(data,response_info){
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
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		var fields=['渠道类型','品牌','银票审核状态'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
				
			var typesId=['#dealerType','#brand','#auditStatus'];
				
			$timeout(function(){
				for(var i=0;i<data.length;i++){
					if(!folUtil.isNull(data[i])){
						if(data[i][1]['typeName']=='银票审核状态'){
							var datas=[];
							
							$.each(data[i],function(i,val){
								if(val['codeCnDesc'].indexOf('修改')<0){
									datas.push(val);
								}
							});
							
							$(typesId[i]).combobox('loadData',datas);
							$(typesId[i]).combobox('select',datas[0]['code']);
						}else{
							$(typesId[i]).combobox('loadData',data[i]);
							$(typesId[i]).combobox('select',data[i][0]['code']);
						}
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
	 * 新增银行特殊经销商
	 */
	$scope.insert=function(){
		$state.go('addBankSpecialDealer');
	};
	
	/**
	 * 导入银行特殊经销商
	 */
	$scope.import=function(){
		$state.go('uploadFileController',{pageName:'bankSpecialDealer'});
	};
	
	/**
	 * 删除银行特殊经销商
	 */
	$scope.deleteSpecialDealer=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择一条记录，再进行删除操作');
			return ;
		}else if(rows.length>1){
			$.messager.alert('提示','只能选择一条记录，再进行删除操作');
			return ;
		}else if(rows[0]['auditStatus']==STATE.BANK_TICKET_ADD_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_UPDATE_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_DELETE_UN_AUDIT){
			$.messager.alert('提示','当前状态是待审核不能给予删除');
			return;
		}
		
		BankSpecialDealerMaintainService.deleteSpecialDealer(rows[0],'bankTicket/dealerBankRelevance/delete',function(data,response_info){
			defferred.resolve('success');
		},function(response_info){
			defferred.reject('fail');
		});
		
		promise.then(function(res){
			$.messager.alert('提示', '删除银行特殊经销商成功');
			
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			BankSpecialDealerMaintainService.query($scope.query_data,'bankTicket/dealerBankRelevance/query',function(data,response_info){
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
			
		},function(res){
			$.messager.alert('提示', '删除银行特殊经销商失败');
		});
	};
	
	/**
	 * 查询银行特殊经销商
	 */
	$scope.query=function(){
		$scope.istableshow=true;
		
		$scope.query_data={
				dealerType:folUtil.getComboBoxDataById('dealerType'),
				sapCode:$scope.sapCode,
				dealerName:$scope.dealerName,
				brand:folUtil.getComboBoxDataById('brand'),
				auditStatus:folUtil.getComboBoxDataById('auditStatus'),
				beginNo:0,
				endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankSpecialDealerMaintainService.query($scope.query_data,'bankTicket/dealerBankRelevance/query',function(data,response_info){
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
}]);

/**
 * 新增银行特殊经销商Controller
 */
FolController.controller('AddBankSpecialDealerController',['$scope','$timeout','$state','$q','BankSpecialDealerMaintainService','CodeService',function($scope,$timeout,$state,$q,BankSpecialDealerMaintainService,CodeService){
	$timeout(function(){
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		var fields=['渠道类型'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
				
			var typesId=['#dealerType','#mainTainType'];
				
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
		
		BankSpecialDealerMaintainService.getBankInfoAllList('bankTicket/dealerBankRelevance/getBankInfoAllList',function(data,response_info){
			var bankinfolist=[];
			bankinfolist.push({bankAbbr:-1,bankChName:'请选择'});
			
			$.each(data,function(i,val){
				bankinfolist.push(val);
			});
			
			$timeout(function(){
				$('#bank').combobox('loadData',bankinfolist);
				$('#bank').combobox('select',bankinfolist[0]['bankAbbr']);
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
	 * 新增银行特殊经销商
	 */
	$scope.addBankSpecialDealer=function(){
		var bankSpecialDealer={
				dealerType:folUtil.getComboBoxDataById('dealerType'),
				bankAbbr:folUtil.getComboBoxDataById('bank'),
				sapCode:$('#sapCode').textbox('getValue')
		};
		
		if(!check()){
			return;
		}
		
		BankSpecialDealerMaintainService.addBankSpecialDealer(bankSpecialDealer,'bankTicket/dealerBankRelevance/add',function(data,response_info){
			$.messager.alert('提示', '新增银行特殊经销商成功');
			$state.go('bankSpecialDealerMaintain');
		},function(response_info){
			$.messager.alert('提示', '新增银行特殊经销商失败');
		});
	};
	
	/**
	 * 检查数据
	 */
	function check(){
		if(folUtil.getComboBoxDataById('dealerType')==-1){
			$.messager.alert('提示','渠道类型不能为空');
			return false;
		}else if(folUtil.getComboBoxDataById('bank')==-1){
			$.messager.alert('提示','银行不能为空');
			return false;
		}else if(folUtil.isNull($('#sapCode').textbox('getValue'))){
			$.messager.alert('提示','客户代码不能为空');
			return false;
		}
		return true;
	}
	
	/**
	 * 取消
	 */
	$scope.cancel=function(){
		$state.go('bankSpecialDealerMaintain');
	};
}]);
/**
 * 银票期限维护controller
 */
FolController.controller('BankTicketDeadLineMaintainController',['$scope','$timeout','$state','$q','$stateParams','BankTicketDeadLineMaintainService','CodeService',function($scope,$timeout,$state,$q,$stateParams,BankTicketDeadLineMaintainService,CodeService){
	var typeName='brand';
	
	/**
	 * 数据初始化
	 */
	$timeout(function(){		
		if($('#branddatagrid').length>0){
			var datagrid=$('#branddatagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankTicketDeadLineMaintainService.query($scope.query_data,'bankTicket/deadLine/queryBrand',function(data,response_info){
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

			$scope.query_data={
					beginNo:0,
					endNo:10
			};
			
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			BankTicketDeadLineMaintainService.query($scope.query_data,'bankTicket/deadLine/queryBrand',function(data,response_info){
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
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankTicketDeadLineMaintainService.query($scope.query_data,'bankTicket/deadLine/querySpecialDealer',function(data,response_info){
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
		 * 监听切换TABS事件
		 */
		$('.sgm_tabs').tabs({
			onSelect:function(title){
//				$scope.$apply(function(){
//					$scope.istableshow=false;
//				})
				
				
				if(title=='维护特殊经销商'){
					typeName='specialDealer';
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					var fields=['渠道类型','品牌','银票审核状态'];
					CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
						$.messager.progress('close');
							
						var typesId=['#dealerType','#brand','#auditStatus'];
							
						$timeout(function(){
							for(var i=0;i<data.length;i++){
								if(!folUtil.isNull(data[i])){
									if(data[i][1]['typeName']=='银票审核状态'){
										var datas=[];
										
										$.each(data[i],function(i,val){
											if(val['codeCnDesc'].indexOf('修改')<0){
												datas.push(val);
											}
										});
										
										$(typesId[i]).combobox('loadData',datas);
										$(typesId[i]).combobox('select',datas[0]['code']);
									}else{
										$(typesId[i]).combobox('loadData',data[i]);
										$(typesId[i]).combobox('select',data[i][0]['code']);
									}
								}
							}
						});
						
						$.messager.progress({
							title:'Please waiting',
							msg:'Loading data...',
							interval:PROGRESS_ACTION_TIMEOUT
						});	
						
						BankTicketDeadLineMaintainService.query($scope.query_data,'bankTicket/deadLine/querySpecialDealer',function(data,response_info){
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
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});
				}else{
					typeName='brand';
					
//					$scope.$apply(function(){
//						$scope.istableshow=true;
//					})
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					$scope.query_data={
							beginNo:0,
							endNo:10
					};
					
					BankTicketDeadLineMaintainService.query($scope.query_data,'bankTicket/deadLine/queryBrand',function(data,response_info){
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
	 * 新增按照品牌渠道维护或者特殊经销商维护的银票期限
	 */
	$scope.addBankTicketDeadLingBrand=function(){
		$state.go('addBankTicketDeadLineBrandOrSpecialDealer',{data:{maintainType:1}});
	};
	
	/**
	 * 新增按照特殊经销商维护的银票期限
	 */
	$scope.addBankTicketDeadLingSpecialDealer=function(){
		$state.go('addBankTicketDeadLineBrandOrSpecialDealer',{data:{maintainType:2}});
	};
	
	/**
	 * 更新按照品牌渠道维护或者特殊经销商维护的银票期限
	 */
	$scope.updateBankTicketDeadLingBrand=function(){
		var rows=$('#branddatagrid').datagrid('getSelections');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择一条记录，再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示','只能一条记录，再进行操作');
			return;
		}else if(rows[0]['auditStatus']==STATE.BANK_TICKET_ADD_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_UPDATE_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_DELETE_UN_AUDIT){
			$.messager.alert('提示','当前状态是待审核不能给予修改');
			return;
		}
		
		$state.go('updateBankTicketDeadLineBrandOrSpecialDealer',{data:{maintainType:1,vo:rows[0]}});
	};
	
	/**
	 * 更新按照特殊经销商维护的银票期限
	 */
	$scope.updateBankTicketDeadLingSpecialDealer=function(){
		var rows=$('#specialdealerdatagrid').datagrid('getSelections');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择一条记录，再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示','只能一条记录，再进行操作');
			return;
		}else if(rows[0]['auditStatus']==STATE.BANK_TICKET_ADD_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_UPDATE_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_DELETE_UN_AUDIT){
			$.messager.alert('提示','当前状态是待审核不能给予修改');
			return;
		}
		
		$state.go('updateBankTicketDeadLineBrandOrSpecialDealer',{data:{maintainType:2,vo:rows[0]}});
	};
	
	/**
	 * 导入银行特殊经销商
	 */
	$scope.import=function(){
		$state.go('uploadFileController',{pageName:'bankSpecialDealer'});
	};
	
	/**
	 * 删除银行特殊经销商
	 */
	$scope.deleteBankTicketDeadLine=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var rows;
		if(typeName=='brand'){
			rows=$('#branddatagrid').datagrid('getSelections');
		}else if(typeName=='specialDealer'){
			rows=$('#specialdealerdatagrid').datagrid('getSelections');
		}
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择一条记录，再进行删除操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示','只能一条记录，再进行删除操作');
			return;
		}else if(rows[0]['auditStatus']==STATE.BANK_TICKET_ADD_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_UPDATE_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_DELETE_UN_AUDIT){
			$.messager.alert('提示','当前状态是待审核不能给予删除');
			return;
		}
		
		BankTicketDeadLineMaintainService.deleteBankTicketDeadLine(rows[0],'bankTicket/deadLine/delete',function(response_info){
			defferred.resolve('success');
		},function(response_info){
			defferred.reject('fail');
		});
		
		promise.then(function(res){
			$.messager.alert('提示', '删除银行特殊经销商成功');
			
			var url;
			if(typeName=='brand'){
				url='bankTicket/deadLine/queryBrand';
			}else if(typeName=='specialDealer'){
				url='bankTicket/deadLine/querySpecialDealer';
			}
			
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			BankTicketDeadLineMaintainService.query($scope.query_data,url,function(data,response_info){
				$.messager.progress('close');

				if(typeName=='brand'){
					$('#branddatagrid').datagrid('loadData',{total:0,rows:[]});
					$('#branddatagrid').datagrid('loadData',data);
				}else if(typeName=='specialDealer'){
					$('#specialdealerdatagrid').datagrid('loadData',{total:0,rows:[]});
					$('#specialdealerdatagrid').datagrid('loadData',data);
				}
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});
			
		},function(res){
			$.messager.alert('提示', '删除银行特殊经销商失败');
		});
	};
	
	/**
	 * 查询银行特殊经销商
	 */
	$scope.query=function(){
		$scope.istableshow=true;
		
		$scope.query_data={
				dealerType:folUtil.getComboBoxDataById('dealerType'),
				sapCode:$('#sapCode').textbox('getValue'),
				dealerName:$('#dealerName').textbox('getValue'),
				brandId:folUtil.getComboBoxDataById('brand'),
				auditStatus:folUtil.getComboBoxDataById('auditStatus'),
				beginNo:0,
				endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankTicketDeadLineMaintainService.query($scope.query_data,'bankTicket/deadLine/querySpecialDealer',function(data,response_info){
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

}]);

/**
 * 新增银票期限Controller
 */
FolController.controller('AddBankTicketDeadLineMaintainController',['$scope','$timeout','$state','$stateParams','$q','BankTicketDeadLineMaintainService','CodeService',function($scope,$timeout,$state,$stateParams,$q,BankTicketDeadLineMaintainService,CodeService){
	$scope.data=$stateParams.data;
	
	/**
	 * 数据初始化
	 */
	$timeout(function(){
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
		
	});
	
	/**
	 * 新增银票期限
	 */
	$scope.addBankTicketDeadLine=function(){
		if($scope.data.maintainType==1&&!$scope.chenkBrand()){
			return;
		}else if($scope.data.maintainType==2&&!$scope.checkSpecialDealer()){
			return;
		}
		
		var bankTicketDeadLine={
				dealerType:folUtil.getComboBoxDataById('dealerType'),//渠道类型
				brandId:folUtil.getComboBoxDataById('brand'),//品牌
				deadlineDay:$('#deadlineDay').numberbox('getValue'),//票据天数
				freePeriodDay:$('#freePeriodDay').numberbox('getValue'),//免息期（天）
				effectDate:$('#effectDate').textbox('getValue')//起效日
		};
		
		if(!$scope.checkValue(bankTicketDeadLine)){
			return;
		}
		
		if($('#sapCode').length>0){
			bankTicketDeadLine.sapCode=folUtil.isNull($('#sapCode').textbox('getValue'))?null:$('#sapCode').textbox('getValue');
			bankTicketDeadLine.expireDate=folUtil.isNull($('#expireDate').textbox('getValue'))?null:$('#expireDate').textbox('getValue');//到期日
		}
		
		
		var url;
		if($scope.data.maintainType==1){
			url='bankTicket/deadLine/addBrand';
		}else{
			url='bankTicket/deadLine/addSpecialDealer';
		}
		
		BankTicketDeadLineMaintainService.addBankTicketDeadLine(bankTicketDeadLine,url,function(response_info){
			$.messager.alert('提示', '新增银票期限成功');
			$state.go('bankTicketDeadLineMaintain');			
		},function(response_info){
			$.messager.alert('提示', '新增银票期限失败,');
		});
	};
	
	/**
	 * 取消
	 */
	$scope.cancel=function(){
		$state.go('bankTicketDeadLineMaintain');
	}
	
	/**
	 * 检查数据（品牌）
	 */
	$scope.chenkBrand=function(){
		if(folUtil.isNull(folUtil.getComboBoxDataById('dealerType'))){
			$.messager.alert('提示','渠道类型不能为空');
			return false;
		}
		if(folUtil.isNull(folUtil.getComboBoxDataById('brand'))){
			$.messager.alert('提示','品牌不能为空');
			return false;
		}
		if(folUtil.isNull($('#deadlineDay').numberbox('getValue'))){
			$.messager.alert('提示','票据天数不能为空');
			return false;
		}
		if(folUtil.isNull($('#freePeriodDay').numberbox('getValue'))){
			$.messager.alert('提示','免息期不能为空');
			return false;
		}
		if(folUtil.isNull($('#effectDate').textbox('getValue'))){
			$.messager.alert('提示','起效日不能为空');
			return false;	
		}
		
		return true;
	}
	
	/**
	 * 检查数据（特殊经销商）
	 */
	$scope.checkSpecialDealer=function(){
		if(folUtil.isNull(folUtil.getComboBoxDataById('dealerType'))||folUtil.getComboBoxDataById('dealerType')==-1){
			$.messager.alert('提示','渠道类型不能为空');
			return false;
		}
		if(folUtil.isNull(folUtil.getComboBoxDataById('brand'))||folUtil.getComboBoxDataById('brand')==-1){
			$.messager.alert('提示','品牌不能为空');
			return false;
		}
		if(folUtil.isNull($('#sapCode').textbox('getValue'))){
			$.messager.alert('提示','SAP代码不能为空');
			return false;
		}
		if(folUtil.isNull($('#deadlineDay').numberbox('getValue'))){
			$.messager.alert('提示','票据天数不能为空');
			return false;
		}
		if(folUtil.isNull($('#freePeriodDay').numberbox('getValue'))){
			$.messager.alert('提示','免息期不能为空');
			return false;
		}
		if(folUtil.isNull($('#effectDate').textbox('getValue'))){
			$.messager.alert('提示','起效日不能为空');
			return false;	
		}
		if(folUtil.isNull($('#expireDate').textbox('getValue'))){
			$.messager.alert('提示','到期日不能为空');
			return false;	
		}
		
		return true;
	}
	
	$scope.checkValue=function(vo){
		if(parseFloat(vo.deadlineDay)<parseFloat(vo.freePeriodDay)){
			$.messager.alert('提示','票据天数不能小于免息天数');
			return false;
		}
		
		if(!folUtil.isNull(vo.expireDate)&&(vo.effectDate >= vo.expireDate)){
			$.messager.alert('提示','生效日期必须小于到期日期');
			return false;
		}
		return true;
	};
}]);

/**
 * 修改银票期限Controller
 */
FolController.controller('UpdateBankTicketDeadLineMaintainController',['$scope','$timeout','$state','$stateParams','BankTicketDeadLineMaintainService',function($scope,$timeout,$state,$stateParams,BankTicketDeadLineMaintainService){
	$scope.data=$stateParams.data;
	
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		$('#dealerType').textbox('setValue',$scope.data.vo.dealerTypeName);
		$('#brand').textbox('setValue',$scope.data.vo.brandName);
		$('#deadlineDay').textbox('setValue',$scope.data.vo.deadlineDay);
		$('#freePeriodDay').textbox('setValue',$scope.data.vo.freePeriodDay);
		$('#auditStatus').textbox('setValue',$scope.data.vo.auditStatusName);
		$('#effectDate').textbox('setValue',$scope.data.vo.effectDateDisplay);
		
		if($scope.data.maintainType==2){
			$timeout(function(){
				$('#sapCode').textbox('setValue',$scope.data.vo.sapCode);
				$('#dealerName').textbox('setValue',$scope.data.vo.dealerName);
				$('#expireDate').textbox('setValue',$scope.data.vo.expireDateDisplay);
			});		
		}
	});
	
	/**
	 * 修改银票期限（品牌）
	 */
	$scope.updateDeadLineWithBrand=function(){
		var data={
			encryptId:$scope.data.vo.encryptId,
			deadlineDay:$('#currentdeadlineDay').numberbox('getValue'),
			freePeriodDay:$('#currentfreePeriodDay').numberbox('getValue'),
			effectDate:$('#currenteffectDate').datebox('getValue')
		}
		
		if(!$scope.checkValue(data)){
			return;
		}
		
		BankTicketDeadLineMaintainService.updateDeadLineWithBrand(data,'bankTicket/deadLine/updateDeadLine',function(response_info){
			$.messager.alert('提示', '修改银票期限成功');
			$state.go('bankTicketDeadLineMaintain');
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}else{
				$.messager.alert('提示','修改银票期限失败,');
			}
		});
	}
	
	/**
	 * 修改银票期限（特殊经销商）
	 */
	$scope.updateDeadLineWithSpecialDealer=function(){
		var data={
				encryptId:$scope.data.vo.encryptId,
				deadlineDay:$('#currentdeadlineDay').numberbox('getValue'),
				freePeriodDay:$('#currentfreePeriodDay').numberbox('getValue'),
				effectDate:$('#currenteffectDate').datebox('getValue'),
				expireDate:$('#currentexpireDate').datebox('getValue')
			}
			
			if(!$scope.checkValue(data)){
				return;
			}
		
			BankTicketDeadLineMaintainService.updateDeadLineWithBrand(data,'bankTicket/deadLine/updateDeadLine',function(response_info){
				$.messager.alert('提示', '修改银票期限成功');
				$state.go('bankTicketDeadLineMaintain');
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}else{
					$.messager.alert('提示','修改银票期限失败,');
				}
			});
	}
	
	/**
	 * 取消
	 */
	$scope.cancel=function(){
		$state.go('bankTicketDeadLineMaintain');
	};
	
	/**
	 * 检查数据
	 */
	$scope.checkValue=function(vo){
		if(parseFloat(vo.deadlineDa)<parseFloat(vo.freePeriodDay)){
			$.messager.alert('提示','票据天数不能小于免息天数');
			return false;
		}else if(!folUtil.isNull(vo.expireDate)&&(vo.effectDate>=vo.expireDate)){
			$.messager.alert('提示','生效日期必须小于到期日期');
			return false;
		}
		return true;
	};
}]);

/**
 * 银票限额维护
 */
FolController.controller('BankTicketLimitAmountMaintainController',['$scope','$timeout','$q','$state','BankTicketLimitAmountMaintainService','CodeService',function($scope,$timeout,$q,$state,BankTicketLimitAmountMaintainService,CodeService){
	var typeName='amountLimit';
	
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
					
					BankTicketLimitAmountMaintainService.query($scope.query_data,'bankTicket/limitAmount/query',function(data,response_info){
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
					beginNo:0,
					endNo:10
			};
			
			BankTicketLimitAmountMaintainService.query($scope.query_data,'bankTicket/limitAmount/query',function(data,response_info){
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
			if($('#specialdealerdatagrid').length>0){
			var datagrid=$('#specialdealerdatagrid').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankTicketLimitAmountMaintainService.query($scope.query_data,'bankTicket/limitAmount/querySpecialDealer',function(data,response_info){
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

			$scope.query_data={
					beginNo:0,
					endNo:10
			};
			
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			BankTicketLimitAmountMaintainService.query($scope.query_data,'bankTicket/limitAmount/querySpecialDealer',function(data,response_info){
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
		
		$('.sgm_tabs').tabs({
			onSelect:function(title){
//				$scope.$apply(function(){
//					$scope.istableshow=false;
//				})
				
				
				if(title=='维护特殊经销商'){
					typeName='specialDealer';
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					var fields=['渠道类型','品牌','银票审核状态'];
					CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
						$.messager.progress('close');
							
						var typesId=['#dealerType','#brand','#auditStatus'];
							
						$timeout(function(){
							for(var i=0;i<data.length;i++){
								if(!folUtil.isNull(data[i])){
									if(data[i][1]['typeName']=='银票审核状态'){
										var datas=[];
										
										$.each(data[i],function(i,val){
											if(val['codeCnDesc'].indexOf('修改')<0){
												datas.push(val);
											}
										});
										
										$(typesId[i]).combobox('loadData',datas);
										$(typesId[i]).combobox('select',datas[0]['code']);
									}else{
										$(typesId[i]).combobox('loadData',data[i]);
										$(typesId[i]).combobox('select',data[i][0]['code']);
									}
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

					$scope.query_data={
							beginNo:0,
							endNo:10
					};
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankTicketLimitAmountMaintainService.query($scope.query_data,'bankTicket/limitAmount/querySpecialDealer',function(data,response_info){
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
					typeName='limitAomunt';
					
//					$scope.$apply(function(){
//						$scope.istableshow=true;
//					})
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					$scope.query_data={
							beginNo:0,
							endNo:10
					};
					
					BankTicketLimitAmountMaintainService.query($scope.query_data,'bankTicket/limitAmount/query',function(data,response_info){
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
			}
		});
	});
	
	/**
	 * 跳转到新增银票限额页面
	 */
	$scope.addBankTicketLimitAmount=function(){
		$state.go('addBankTicketLimitAmountMaintain',{data:{maintainType:1}});
	}
	
	/**
	 * 跳转到新增银票限额（特殊经销商）页面
	 */
	$scope.addBankTicketLimitAmountSpecialDealer=function(){
		$state.go('addBankTicketLimitAmountMaintain',{data:{maintainType:2}});
	}
	
	/**
	 * 查询特殊经销商
	 */
	$scope.querySpecialDealer=function(){
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		$scope.query_data={
				dealerType:folUtil.getComboBoxDataById('dealerType'),
				brandId:folUtil.getComboBoxDataById('brand'),
				sapCode:$('#sapCode').textbox('getValue'),
				dealerName:$('#dealerName').textbox('getValue'),
				auditStatus:folUtil.getComboBoxDataById('auditStatus'),
				beginNo:0,
				endNo:10
		};
		
		BankTicketLimitAmountMaintainService.query($scope.query_data,'bankTicket/limitAmount/querySpecialDealer',function(data,response_info){
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
	 * 删除银票限额
	 */
	$scope.deleteBankTicketLimitAmount=function(){
		var rows;
		if(typeName=='amountLimit'){
			rows=$('#datagrid').datagrid('getSelections');
		}else{
			rows=$('#specialdealerdatagrid').datagrid('getSelections');
		}
		
		if(rows.length<=0){
			$.messager.alert('提示', '请选择一条记录，再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多条记录，再进行操作');
			return;
		}else if(rows[0]['auditStatus']==STATE.BANK_TICKET_ADD_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_UPDATE_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_DELETE_UN_AUDIT){
			$.messager.alert('提示','当前状态是待审核不能给予删除');
			return;
		}
		
		BankTicketLimitAmountMaintainService.deleteBankTicketLimitAmount(rows[0],'bankTicket/limitAmount/delete',function(response_info){
			$.messager.alert('提示','删除银票限额成功');
			
			var requestParams={};
			if(typeName=='amountLimit'){
				requestParams.url='bankTicket/limitAmount/query';
				requestParams.datagridName='#datagrid';				
			}else{
				requestParams.url='bankTicket/limitAmount/querySpecialDealer';
				requestParams.datagridName='#specialdealerdatagrid';
			}
			
			BankTicketLimitAmountMaintainService.query($scope.query_data,requestParams.url,function(data,response_info){
				$.messager.progress('close');
				$(requestParams.datagridName).datagrid('loadData',{total:0,rows:[]});
				$(requestParams.datagridName).datagrid('loadData',data);
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
			}else{
				$.messager.alert('提示','删除银票限额失败');
			}
		});
	};
	
	/**
	 * 跳转到修改银票限额
	 */
	$scope.updateBankTicketLimitAmount=function(){
		var rows;
		if(typeName=='amountLimit'){
			rows=$('#datagrid').datagrid('getSelections');
		}else{
			rows=$('#specialdealerdatagrid').datagrid('getSelections');
		}
		
		if(rows.length<=0){
			$.messager.alert('提示', '请选择一条记录，再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多条记录，再进行操作');
			return;
		}else if(rows[0]['auditStatus']==STATE.BANK_TICKET_ADD_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_UPDATE_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_DELETE_UN_AUDIT){
			$.messager.alert('提示','当前状态是待审核不能给予修改');
			return;
		}
		
		$state.go('updateBankTicketLimitAmountMaintain',{data:{maintainType:1,vo:rows[0]}});
	}
	
	/**
	 * 跳转到修改银票限额（特殊经销商）
	 */
	$scope.updateBankTicketLimitAmountSpecialDealer=function(){
		var rows;
		if(typeName=='amountLimit'){
			rows=$('#datagrid').datagrid('getSelections');
		}else{
			rows=$('#specialdealerdatagrid').datagrid('getSelections');
		}
		
		if(rows.length<=0){
			$.messager.alert('提示', '请选择一条记录，再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多条记录，再进行操作');
			return;
		}else if(rows[0]['auditStatus']==STATE.BANK_TICKET_ADD_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_UPDATE_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_DELETE_UN_AUDIT){
			$.messager.alert('提示','当前状态是待审核不能给予修改');
			return;
		}
		
		$state.go('updateBankTicketLimitAmountMaintain',{data:{maintainType:2,vo:rows[0]}});
	}

}]);

/**
 * 新增银票限额Controller
 */
FolController.controller('AddBankTicketLimitAmountMaintainController',['$scope','$timeout','$q','$state','$stateParams','BankTicketLimitAmountMaintainService','CodeService',function($scope,$timeout,$q,$state,$stateParams,BankTicketLimitAmountMaintainService,CodeService){
	$scope.data=$stateParams.data;
	
	$timeout(function(){
		if($scope.data.maintainType==2){
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
		}
	});
	
	/**
	 * 新增银票限额
	 */
	$scope.addBankTicketLimitAmount=function(){
		if(!check()){
			return;
		}
		
		var bankTicketLimitVo={
				amountLimit:$('#limitAmount').numberbox('getValue'),
				effectDate:$('#effectDate').datebox('getValue')
		}
		
		var url;
		if($scope.data.maintainType==2){
			bankTicketLimitVo['dealerType']=folUtil.getComboBoxDataById('dealerType');
			bankTicketLimitVo['brandId']=folUtil.getComboBoxDataById('brand');
			bankTicketLimitVo['sapCode']=$('#sapCode').textbox('getValue');
			bankTicketLimitVo['expireDate']=$('#expireDate').datebox('getValue');
			url='bankTicket/limitAmount/addSpecialDealer';
		}else{
			url='bankTicket/limitAmount/add';
		}
		
		BankTicketLimitAmountMaintainService.addBankTicketLimitAmount(bankTicketLimitVo,url,function(response_info){
			$.messager.alert('提示','新增银票限额成功');
			$state.go('bankTicketLimitAmountMaintain');
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}else{
				$.messager.alert('提示','新增银票限额失败');
			}
		});
	};
	
	/**
	 * 取消
	 */
	$scope.cancel=function(){
		$state.go('bankTicketLimitAmountMaintain');
	};
	
	/**
	 * 检查数据
	 */
	function check(){
		if(folUtil.isNull($('#limitAmount').numberbox('getValue'))){
			$.messager.alert('提示','票据限额不能为空')
			return false;
		}
		if(folUtil.isNull($('#effectDate').datebox('getValue'))){
			$.messager.alert('提示','起效日不能为空')
			return false;
		}
			
		if($scope.data.maintainType==2){
			if(folUtil.isNull(folUtil.getComboBoxDataById('dealerType'))||folUtil.getComboBoxDataById('dealerType')==-1){
				$.messager.alert('提示','渠道类型不能为空')
				return false;
			} 
			
			if(folUtil.isNull(folUtil.getComboBoxDataById('brand'))||folUtil.getComboBoxDataById('brand')==-1){
				$.messager.alert('提示','品牌不能为空')
				return false;
			}
			
			if(folUtil.isNull($('#sapCode').textbox('getValue'))){
				$.messager.alert('提示','客户代码不能为空')
				return false;
			}
			
			if(folUtil.isNull($('#expireDate').datebox('getValue'))){
				$.messager.alert('提示','到期日不能为空')
				return false;
			}
		}
		
		return true;
	}
}]);

/**
 * 更新银票限额Controller
 */
FolController.controller('UpdateBankTicketLimitAmountMaintainController',['$scope','$timeout','$q','$state','$stateParams','BankTicketLimitAmountMaintainService',function($scope,$timeout,$q,$state,$stateParams,BankTicketLimitAmountMaintainService){
	$scope.data=$stateParams.data;
	
	$timeout(function(){
		$('#amountLimit').textbox('setValue',$scope.data.vo.moneyDisplay);
		$('#effectDateDisplay').textbox('setValue',$scope.data.vo.effectDateDisplay);
		$('#auditStatusName').textbox('setValue',$scope.data.vo.auditStatusName);
		$('#auditMsg').textbox('setValue',$scope.data.vo.auditMsg);
		
		if($scope.data.maintainType==2){
			$('#dealerTypeName').textbox('setValue',$scope.data.vo.dealerTypeName);
			$('#sapCode').textbox('setValue',$scope.data.vo.sapCode);
			$('#brandName').textbox('setValue',$scope.data.vo.brandName);
			$('#dealerName').textbox('setValue',$scope.data.vo.dealerName);
			$('#expireDateDisplay').textbox('setValue',$scope.data.vo.expireDateDisplay);
		}
	});
	
	/**
	 * 更新银票限额
	 */
	$scope.updateBankTicketLimitAmount=function(){
		if(!check()){
			return;
		}
		
		var bankTicketLimitVo={
				amountLimit:$('#currentAmountLimit').numberbox('getValue'),
				effectDate:$('#effectDate').datebox('getValue'),
				encryptId:$scope.data.vo.encryptId
		}
		
		if($scope.data.maintainType==2){
			bankTicketLimitVo['expireDate']=$('#expireDate').datebox('getValue');
		}
		
		BankTicketLimitAmountMaintainService.updateBankTicketLimitAmount(bankTicketLimitVo,'bankTicket/limitAmount/update',function(response_info){
			$.messager.alert('提示','修改银票限额成功');
			$state.go('bankTicketLimitAmountMaintain');
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}else{
				$.messager.alert('提示','修改银票限额失败');
			}
		});
	};
	
	/**
	 * 取消
	 */
	$scope.cancel=function(){
		$state.go('bankTicketLimitAmountMaintain');
	};
	
	/**
	 * 检查数据
	 */
	function check(){			
		if($scope.data.maintain==2){
			if($('#effectDate').datebox('getValue')<$('#expireDate').datebox('getValue')){
				return false;
			} 
		}
		
		return true;
	}

}]);
/**
 * 银行银票额度维护controller
 */
FolController.controller('BankTicketLimitMaintainController',['$scope','$timeout','$state','$q','BankTicketLimitMaintainService','CodeService',function($scope,$timeout,$state,$q,BankTicketLimitMaintainService,CodeService){
	$scope.istableshow=false;
	
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		if($('#datagridDealer').length>0){
			var datagrid=$('#datagridDealer').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankTicketLimitMaintainService.query($scope.query_data,'bankTictet/limits/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagridDealer').datagrid('loadData',data);
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
		if($('#datagridBank').length>0){
			var datagrid=$('#datagridBank').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BankTicketLimitMaintainService.query($scope.query_data,'bankTictet/limits/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagridBank').datagrid('loadData',data);
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

		
		var types=['#dealerTypeFirst','#bankTicketAuditStatusFirst'];
		var fields=['渠道类型','银票审核状态'];
		initData(types,fields);
		$scope.query(1);
		$scope.selectedTable='dealer'
		
		/**
		 * 监听切换tabs事件
		 */
		$('.sgm_tabs').tabs({
			onSelect:function(title){
				//初始化数据
				if(title=='银行渠道银票额度维护'){
					var types=['#dealerTypeFirst','#bankTicketAuditStatusFirst'];
					var fields=['渠道类型','银票审核状态'];
					
					initData(types,fields);
					$scope.query(1);
					$scope.selectedTable='dealer'
				}else{
					var types=['#bankTicketAuditStatusSecond'];
					var fields=['银票审核状态'];
					initData(types,fields);
					$scope.query(2);
					$scope.selectedTable='bank'
				}				
			}
		});
		
	});
	
	/**
	 * 数据初始化
	 */
	function initData(typesId,fields){
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	

		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
	
			$timeout(function(){
				for(var i=0;i<data.length;i++){
					if(!folUtil.isNull(data[i])){
						if(data[i][1]['typeName']=='银票审核状态'){
								var datas=[];
								
								$.each(data[i],function(i,val){
									if(val['codeCnDesc'].indexOf('删除')<0){
										datas.push(val);
									}
								});
								
								$(typesId[i]).combobox('loadData',datas);
								$(typesId[i]).combobox('select',datas[0]['code']);
						}else if(data[i][1]['typeName']=='维护类型'){
							var datas=[];
							
							$.each(data[i],function(i,val){
								if(val['codeCnDesc'].indexOf('请选择')<0){
									datas.push(val);
								}
							});
							
							$(typesId[i]).combobox('loadData',datas);
							$(typesId[i]).combobox('select',datas[0]['code']);
						}else{
							$(typesId[i]).combobox('loadData',data[i]);
							$(typesId[i]).combobox('select',data[i][0]['code']);
						}
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
	}
	
	/**
	 * 查询
	 */
	$scope.query=function(maintainType){
		$scope.istableshow=true;
		
		$scope.query_data={
				maintainType:maintainType,				
				beginNo:0,
				endNo:10
		};
		
		if(maintainType==1){
			$scope.query_data.dealerType=folUtil.getComboBoxDataById('dealerTypeFirst');
			$scope.query_data.auditStatus=folUtil.getComboBoxDataById('bankTicketAuditStatusFirst');
		}else{
			$scope.query_data.auditStatus=folUtil.getComboBoxDataById('bankTicketAuditStatusSecond');
		}		
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankTicketLimitMaintainService.query($scope.query_data,'bankTictet/limits/query',function(data,response_info){
			$.messager.progress('close');
			if(maintainType==1){
				$('#datagridDealer').datagrid('loadData',{total:0,rows:[]});
				$('#datagridDealer').datagrid('loadData',data);
			}else{
				$('#datagridBank').datagrid('loadData',{total:0,rows:[]});
				$('#datagridBank').datagrid('loadData',data);
			}
			
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};
	
	/**
	 * 跳转到新增银行特殊经销商额度页面
	 */
	$scope.insertDealer=function(){		
		$state.go('addBankTicketLimit',{data:{maintainType:1,type:'add',pageTitle:'新增银行银票额度'}});
	};
	
	/**
	 * 跳转到新增银行额度页面
	 */
	$scope.insertBank=function(){
		$state.go('addBankTicketLimit',{data:{maintainType:2,type:'add',pageTitle:'新增银行银票额度'}});
	};
	
	/**
	 * 跳转到修改银行或者银行特殊经销商额度页面
	 */
	$scope.update=function(){
		var rows;
		if($scope.selectedTable=='dealer'){
			rows=$('#datagridDealer').datagrid('getSelections');
		}else{
			rows=$('#datagridBank').datagrid('getSelections');
		}

		if(rows.length<=0){
			$.messager.alert('提示', '请选择某条记录在进行修改');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多条记录进行修改');
			return;			
		}else if(rows[0]['auditStatus']==STATE.BANK_TICKET_ADD_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_UPDATE_UN_AUDIT||
				rows[0]['auditStatus']==STATE.BANK_TICKET_DELETE_UN_AUDIT){
			$.messager.alert('提示','当前状态是待审核不能给予修改');
			return;
		}
		
		if($scope.selectedTable=='dealer'){
			$state.go('updateBankTicketLimit',{data:{bankBankTicketVo:rows[0],maintainType:1,type:'update',pageTitle:'更新银行银票额度'}});
		}else{
			$state.go('updateBankTicketLimit',{data:{bankBankTicketVo:rows[0],maintainType:2,type:'update',pageTitle:'更新银行银票额度'}});
		}
		
	};
	
	$scope.import=function(){
		$state.go('uploadFilePage',{pageName:'bankTicketLimitMaintain'});
	};
}]);

/**
 * 新增银行银票额度controller
 */
FolController.controller('AddBankTicketLimitController',['$scope','$timeout','$state','$q','$stateParams','BankTicketLimitMaintainService','CodeService',function($scope,$timeout,$state,$q,$stateParams,BankTicketLimitMaintainService,CodeService){
	$scope.data=$stateParams.data;
	
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		$('#bankTicketLimit').panel({title:$scope.data.pageTitle});
		
		var fields=['渠道类型'];
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
				
			var typesId=['#dealerType'];
				
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
	 * 新增银票信息
	 */
	$scope.addBankTicketLimit=function(){
		var bankTicketData={
				dealerType:$('#dealerType').length>0?folUtil.getComboBoxDataById('dealerType'):null,
				bankAbbr:$scope.bankAbbr,
				bankChName:$scope.bankCnDesc,
				amount:$scope.totalAmount,
				bankEnName:$scope.bankEnDesc,
				maintainType:$stateParams.data.maintainType
		};
		
		if(!check()){
			return;
		}
		
		BankTicketLimitMaintainService.addBankTicketLimit(bankTicketData,'bankTictet/limits/add',function(response_info){
			$.messager.alert('提示','银行银票额度新增成功');
			$state.go('bankTicketLimitMaintain');
		},function(response_info){
			$.messager.alert('提示','银行银票新增失败');
		});
	};
	
	/**
	 * 检查数据
	 */
	function check(){
		if(folUtil.isNull($scope.bankAbbr)){
			$.messager.alert('提示','银行简称不能为空');
			return false;
		}
		
		if(folUtil.isNull($scope.bankCnDesc)){
			$.messager.alert('提示','银行中文名称不能为空');
			return false;
		}
		
		if($('#dealerType').length>0&&(folUtil.isNull(folUtil.getComboBoxDataById('dealerType'))||folUtil.getComboBoxDataById('dealerType')==-1)){
			$.messager.alert('提示','渠道类型不能为空');
			return false;
		}
		
		if(folUtil.isNull($scope.totalAmount)){
			$.messager.alert('提示','银票总额不能为空');
			return false;
		}
		return true;
	}
}]);

/**
 * 更新银行银票额度controller
 */
FolController.controller('UpdateBankTicketLimitController',['$scope','$timeout','$state','$q','$stateParams','BankTicketLimitMaintainService','CodeService',function($scope,$timeout,$state,$q,$stateParams,BankTicketLimitMaintainService,CodeService){
	$scope.data=$stateParams.data;
	$timeout(function(){
		$('#bankTicketLimit').panel({title:$scope.data.pageTitle});
		/**
		 * 初始化字典
		 */
		var fields=['渠道类型'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
				
			var typesId=['#dealerType'];
				
			$timeout(function(){
				for(var i=0;i<data.length;i++){
					if(!folUtil.isNull(data[i])){
						$(typesId[i]).combobox('loadData',data[i]);
//						$(typesId[i]).combobox('select',$scope.data.bankBankTicketVo.dealerType);
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
		
		/**
		 * 数据初始化
		 */
		$('#dealerType').combobox('select',$scope.data.bankBankTicketVo.dealerType);
		$('#bankAbbr').textbox('setValue',$scope.data.bankBankTicketVo.bankAbbr);
		$('#bankCnDesc').textbox('setValue',$scope.data.bankBankTicketVo.bankChName);
		$('#totalAmount').numberbox('setValue',$scope.data.bankBankTicketVo.moneyDisplay);
		$('#bankEnDesc').textbox('setValue',$scope.data.bankBankTicketVo.bankEnName);
	});
	
	/**
	 * 更新银票信息
	 */
	$scope.updateBankTicketLimit=function(){
		console.log($scope.data.bankBankTicketVo.bankId);
		var bankTicketData={
				dealerType:$('#dealerType').length>0?folUtil.getComboBoxDataById('dealerType'):null,
				bankAbbr:$scope.bankAbbr,
				bankChName:$scope.bankCnDesc,
				amount:$scope.totalAmount,
				bankEnName:$scope.bankEnDesc,
				bankId:$scope.data.bankBankTicketVo.bankId,
				encryptId:$scope.data.bankBankTicketVo.encryptId
		};
		
		BankTicketLimitMaintainService.updateBankTicketLimit(bankTicketData,'bankTictet/limits/update',function(response_info){
			$.messager.alert('提示','银行银票额度更新成功');
			$state.go('bankTicketLimitMaintain');
		},function(response_info){
			$.messager.alert('提示','银行银票额度更新失败');
			console.log(response_info);
		});
	};
}]);
/**
 * 票据贴息年化利率维护controller
 */
FolController.controller('TicketInterestRateMaintainController',['$scope','$timeout','$state','$q','TicketInterestRateMaintainService',function($scope,$timeout,$state,$q,TicketInterestRateMaintainService){

	$timeout(function(){
		$('#datagrid').datagrid().datagrid('enableCellEditing');
		$('#datagridHis').datagrid().datagrid('enableCellEditing');
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
					
					TicketInterestRateMaintainService.queryCur($scope.query_data,'interestRate/maintain/queryCur',function(data,response_info){
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
		if($('#datagridHis').length>0){
			var datagrid=$('#datagridHis').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					TicketInterestRateMaintainService.queryHis($scope.query_data,'interestRate/maintain/queryHis',function(data,response_info){
						$.messager.progress('close');
						$('#datagridHis').datagrid('loadData',data);
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

	
	$scope.query_data={
			auditStatus:$scope.auditStatus,		
			beginNo:0,
			endNo:10
	};
	TicketInterestRateMaintainService.queryCur($scope.query_data,'interestRate/maintain/queryCur',function(data,response_info){
		$.messager.progress('close');
		$('#datagrid').datagrid('loadData',data);
	},function(response_info){
		$.messager.progress('close');
		if(response_info.state===STATE.TIMEOUT){
			console.log('timeout');
			$.messager.alert('提示','系统超时请稍后访问');
		}
	});
	TicketInterestRateMaintainService.queryHis($scope.query_data,'interestRate/maintain/queryHis',function(data,response_info){
		$.messager.progress('close');
		$('#datagridHis').datagrid('loadData',{total:0,rows:[]});	
		$('#datagridHis').datagrid('loadData',data);
	},function(response_info){
		$.messager.progress('close');
		if(response_info.state===STATE.TIMEOUT){
			console.log('timeout');
			$.messager.alert('提示','系统超时请稍后访问');
		}
	});


	$scope.update=function(){
		var rows;
		rows=$('#datagrid').datagrid('getSelections');

		if(rows.length<=0){
			$.messager.alert('提示', '请选择最新一条记录进行修改');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多条记录进行修改');
			return;			
		}else if(rows[0]['unAuditRateCur']!=null){
			$.messager.alert('提示','当前状态是待审核不能给予修改');
			return;
		}
		
		/**
		 * 新增修改记录
		 */
		$state.go('updateInterestRateHis');
	};
	}]);
/**
 * 新增修改年化利率记录
 */
FolController.controller('UpdateInterestRateController',['$scope','$timeout','$state','$q','TicketInterestRateMaintainService','CodeService',function($scope,$timeout,$state,$q,TicketInterestRateMaintainService,CodeService){
//	$timeout(function(){
//		$.messager.progress({
//			title:'Please waiting',
//			msg:'Loading data...',
//			interval:PROGRESS_ACTION_TIMEOUT
//		});		
//		
//	});

	/**
	 * 新增修改记录
	 */
	$scope.confirm=function(){
		var interestRate={
				annualInterestRate:$('#annualInterestRate').textbox('getValue')
		};
		
//		if(!check()){
//			return;
//		}
//		
		TicketInterestRateMaintainService.checkdata(interestRate,'interestRate/maintain/getSign',function(data,response_info){
			console.log(data);
		TicketInterestRateMaintainService.confirm(data,'interestRate/maintain/update',function(data,response_info){
			
			$.messager.alert('提示', '修改成功');
			$state.go('ticketInterestRateMaintain');
			
		},function(response_info){
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}else{
				$.messager.alert('提示', '修改失败');
			}
		});
		},function(response_info){
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});

	};
	
//	/**
//	 * 检查数据
//	 */
//	function check(){
//		if(folUtil.isNull($('#annualInterestRate').textbox('getValue'))){
//			$.messager.alert('提示','客户代码不能为空');
//			return false;
//		}
//		return true;
//	}
	
	/**
	 * 取消
	 */
	$scope.cancel=function(){
		$state.go('ticketInterestRateMaintain');
	};
}]);