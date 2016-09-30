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