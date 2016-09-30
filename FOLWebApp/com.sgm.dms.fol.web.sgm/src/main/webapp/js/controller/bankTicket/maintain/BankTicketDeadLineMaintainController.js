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
