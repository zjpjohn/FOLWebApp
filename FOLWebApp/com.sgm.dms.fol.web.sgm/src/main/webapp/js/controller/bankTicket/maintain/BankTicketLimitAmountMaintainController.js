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