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