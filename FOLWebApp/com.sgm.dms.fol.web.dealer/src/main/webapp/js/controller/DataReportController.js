/**
 * 我的账户controller
 */
FolController.controller('accountCtrl',['$rootScope','$scope','$state','$timeout','AccountService','$window',function($rootScope,$scope,$state,$timeout,AccountService,$window){

	$.messager.progress({
		title:'Please waiting',
		msg:'Loading data...',
		interval:PROGRESS_ACTION_TIMEOUT
	});
	
	AccountService.getAccountAmountInfo('datareport/account/query',function(data,response_info){
		$.messager.progress('close');
		$rootScope.account=data;
	},function(data,response_info){
		$.messager.progress('close');
		if(response_info.state===STATE.TIMEOUT){
			console.log('timeout');
			$.messager.alert('提示','系统超时或请检查账号状态,如没问题请刷新页面重新操作');
		}
		console.log("error:"+data);
	});
	
	$scope.reserveChangeDeatilInquiry=function(){
		$state.go('reserveChangeDeatilInquiry');
	};
	
	$scope.bonusChangeDeatilInquiry=function(){
		$state.go('bonusChangeDeatilInquiry');
	};
	
	$scope.reconciliationSign=function(){
		$state.go('reconciliationSign');
	};
	
}]);

/**
 * 储备金变动明细controller
 */
FolController.controller('reserveChangeDeatilCtrl',['$scope','$rootScope','$timeout','AccountService','$cookies','$q','$window',function($scope,$rootScope,$timeout,AccountService,$cookies,$q,$window){
	$scope.istableshow=false;
	
	$timeout(function(){
		var changeAmountDataGrid=$('#changeAmountDataGrid').datagrid('getPager');
		
		changeAmountDataGrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.reserve_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.reserve_data.endNo=pageSize*pageNumber;		
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});
				
				//查询变动储备金
				AccountService.findChangeReserveAmount($scope.reserve_data,'datareport/account/reservedeatilchange/changedeatilreserve/query',function(data,response_info){
					$.messager.progress('close');
									
					$('#changeAmountDataGrid').datagrid('loadData',data);
				},function(data,response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log(response_info);
						$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
					}else{
						$.messager.alert("提示",response_info.message);
					}
					console.log(data);
				});
			}
		});
		
		var freezeAmountDataGrid=$('#freezeAmountDataGrid').datagrid('getPager');
		freezeAmountDataGrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.reserve_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.reserve_data.endNo=pageSize*pageNumber;		
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});
					
				//查询冻结储备金
				AccountService.findFreezeReserveAmount($scope.reserve_data,'datareport/account/reservedeatilchange/freezedeatilreserve/query',function(data,response_info){					
					$.messager.progress('close');
					$('#freezeAmountDataGrid').datagrid('loadData',data);
				},function(data,response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log(response_info);
						$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
					}else{
						$.messager.alert("提示",response_info.message);
					}
					console.log(data);
				});
			}
		});
		
	});
	
	$scope.findReserveDeatilChange=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var changeReserveAmountResult=false,freezeReserveAmountResult=false;
		
		if($('#startTime').datebox('getText')>$('#endTime').datebox('getText')){
			$.messager.alert('提示','开始时间不能大于结束时间');
			return;
		}
		
		$scope.reserve_data={
					reserveType:folUtil.getComboBoxDataById('reserveType'),
					businessCode:folUtil.getComboBoxDataById('businessCode'),
					startTime:$('#startTime').datebox('getValue'),
					endTime:$('#endTime').datebox('getValue'),
					sapCode:$cookies.COOKIE_FOL_SAP_CODE,
					beginNo:0,
					endNo:10
				};
		
		$scope.istableshow=true;
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		//查询变动储备金
		AccountService.findChangeReserveAmount($scope.reserve_data,'datareport/account/reservedeatilchange/changedeatilreserve/query',function(data,response_info){
			changeReserveAmountResult=true;
			
			$('#changeAmountDataGrid').datagrid('loadData',data);
			if(freezeReserveAmountResult&&changeReserveAmountResult){
				defferred.resolve('success');
			}
		},function(data,response_info){
			if(response_info.state===STATE.TIMEOUT){
				console.log(response_info);
				defferred.reject('timeout');
			}else{
				defferred.reject('error');
			}
			console.log(data);
		});

		//查询冻结储备金
		AccountService.findFreezeReserveAmount($scope.reserve_data,'datareport/account/reservedeatilchange/freezedeatilreserve/query',function(data,response_info){
			freezeReserveAmountResult=true;
			
			$('#freezeAmountDataGrid').datagrid('loadData',data);
			if(freezeReserveAmountResult&&changeReserveAmountResult){
				defferred.resolve('success');
			}
		},function(data,response_info){
			if(response_info.state===STATE.TIMEOUT){
				defferred.reject('timeout');
			}else{
				defferred.reject('error');
			}
			console.log(data);
		});
		
		promise.then(function(data){
			console.log(data);
			if(data=='success'){
				$.messager.progress('close');
			}
		},function(data){
			console.log(data);
			$.messager.progress('close');
			$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
		});
	};
	
	$.messager.progress({
		title:'Please waiting',
		msg:'Loading data...',
		interval:PROGRESS_ACTION_TIMEOUT
	});
	
	AccountService.findCodeTypeNames('datareport/account/reservedeatilchange/findCodeTypeNames',function(data){
		$.messager.progress('close');

		$timeout(function(){
			$('#reserveType').combobox('loadData',$rootScope.reserveType);
			$('#reserveType').combobox('select',$rootScope.reserveType[0]['code']);
			
			if(!folUtil.isNull(data.rows)){
				$('#businessCode').combobox('loadData',data.rows[0]);
				$('#businessCode').combobox('select',data.rows[0][0]['code']);
			}
			
		});
	},function(data,response_info){
		$.messager.progress('close');
		if(response_info.state===STATE.TIMEOUT){
			console.log('timeout');
			$.messager.alert('提示','系统超时或请检查账号状态,如没问题请刷新页面重新操作');
		}
		console.log("error:"+data);
	});
	//文件导出
	$scope.deatilChangeResult=function(){
		
		AccountService.getToken('services/tokens',function(data){
			if(folUtil.isNull($scope.reserve_data)){
				$.messager.alert('提示','请先查询一次再导出');
				return;
			}
			$.messager.progress('close');
			$window.open('datareport/account/reservedeatilchange/ReserveChangeDeatil?reserveType='+$scope.reserve_data.reserveType+'&businessCode='+$scope.reserve_data.businessCode+'&sapCode='+$cookies.COOKIE_FOL_SAP_CODE+'&startTime='+$scope.reserve_data.startTime+'&endTime='+$scope.reserve_data.endTime+'&token='+data['token']);
			$window.open('datareport/account/reservedeatilchange/ReserveFreezeDeatil?reserveType='+$scope.reserve_data.reserveType+'&businessCode='+$scope.reserve_data.businessCode+'&sapCode='+$cookies.COOKIE_FOL_SAP_CODE+'&startTime='+$scope.reserve_data.startTime+'&endTime='+$scope.reserve_data.endTime+'&token='+data['token']);
			console.log('datareport/account/reservedeatilchange/ReserveChangeDeatil?reserveType='+$scope.reserve_data.reserveType+'&businessCode='+$scope.reserve_data.businessCode+'&sapCode='+$cookies.COOKIE_FOL_SAP_CODE+'&startTime='+$scope.reserve_data.startTime+'&endTime='+$scope.reserve_data.endTime+'&token='+data['token']);
		},function(data){
			console.log(data);
		});
	};
}]);

/**
 * 对账单签收
 */
FolController.controller('ReconciliationSignCtrl',['$scope','$timeout','$cookies','$window','$q','AccountService',function($scope,$timeout,$cookies,$window,$q,AccountService){
	$scope.istableshow=false;
	
	$timeout(function(){
		folUtil.setComboBoxYearDataById('year');
		folUtil.setComboBoxMonthDataById('month');

		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.query_params.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.query_params.endNo=pageSize*pageNumber;	

				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});
				
				//查询客户对账单
				AccountService.ReconciliationSignQuery($scope.query_params,'datareport/account/reconciliationSign/query',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
				},function(data,response_info){
					console.log(data);
				});
			}
		});
	});
	
	$.messager.progress({
		title:'Please waiting',
		msg:'Loading data...',
		interval:PROGRESS_ACTION_TIMEOUT
	});
	
//	var query_data={sapCode:'7700005'};
	var query_data={sapCode:$cookies.COOKIE_FOL_SAP_CODE};
	AccountService.getAccountAmountInfo(query_data,'datareport/account/query',function(data){	
		$.messager.progress('close');
		$scope.dealer={sapCode:data.sapCode,dealerName:data.dealerName};
	},function(data){
		$.messager.progress('close');
		if(response_info.state===STATE.TIMEOUT){
			console.log('timeout');
			$.messager.alert('提示','系统超时或请检查账号状态,如没问题请刷新页面重新操作');
		}
		console.log("error:"+data);
	});
	
	//客户对账单查询
	$scope.ReconciliationSignQuery=function(){
		$scope.istableshow=true;

		var currentTime=folUtil.getComboBoxDataById('year')+'-'+folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month'));

		$scope.query_params={
			currentDate:currentTime,
			dealerCode:$cookies.COOKIE_FOL_SAP_CODE,
//			sapCode:'7700005',
			beginNo:0,
			endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		//查询客户对账单
		AccountService.ReconciliationSignQuery($scope.query_params,'datareport/account/reconciliationSign/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',data);
		},function(data,response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时或请检查账号状态,如没问题请刷新页面重新操作');
			}
			console.log("error:"+data);
		});
	};
	
	//客户对账单签收
	$scope.ReconciliationSignIn=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		var flag=false;
		$.each(rows,function(i,val){
			if(val.status==STATE.CHECKED){
				$.messager.alert('提示','该文件已签收不能再签收');
				flag=true;
				return;
			}
		});
		
		if(flag){
			return;
		}
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择需要签收的记录，再去操作签收');
			return;
		}else{
			
			AccountService.ReconciliationSignIn(rows,'datareport/account/reconciliationSign/recerveFile',function(data,response_info){
				$.messager.alert('提示','签收成功');
				
				//重新查询客户对账单
				AccountService.ReconciliationSignQuery($scope.query_params,'datareport/account/reconciliationSign/query',function(data,response_info){
					$('#datagrid').datagrid('loadData',data);
				},function(data,response_info){
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时或请检查账号状态,如没问题请刷新页面重新操作');
					}
				});
			},function(data,response_info){
				console.log(data);
			});
		}
	};

	/**
	 * 数据导出功能
	 */
	$scope.checkOut=function(){
		var queryTime=folUtil.getComboBoxDataById('year')+'-'+folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month'));

		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		AccountService.getToken('services/tokens',function(data){
			$.messager.progress('close');
			$window.open('datareport/account/reconciliationSign/exportBillFileDetail?currentDate='+queryTime+'&dealerCode='+$scope.account.sapCode+'&token='+data['token']);
		},function(data){
			$.messager.progress('close');
			console.log(data);
		});
	};
	
	/**
	 * 对帐单确认无误
	 */
	$scope.confimUnError=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		var flag=false;
		$.each(rows,function(i,val){
			if(val.status==STATE.UN_CHECK){
				$.messager.alert('提示','该文件处于未签收状态,不能进行确认无误操作');
				flag=true;
				return;
			}else if(val.isConfirm===STATE.CONFIRM){
				$.messager.alert('提示','该文件处于已确认状态,不能进行确认无误操作');
				flag=true;
				return;
			}
		});
		
		if(flag)
			return;
		
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		AccountService.confirmFile(rows,'datareport/account/reconciliationSign/confirmFile',function(data,response_info){
			defferred.resolve('success');
		},function(data,response_info){
			defferred.reject('fail');
		});
		
		promise.then(function(rep){
			console.log(rep);
			$.messager.alert('提示', '确认无误操作成功');
			
			$.messager.progress({
				title:'Please waiting',
				msg:'update data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});
			
			//重新查询客户对账单
			AccountService.ReconciliationSignQuery($scope.query_params,'datareport/account/reconciliationSign/query',function(data,response_info){
				$.messager.progress('close');
				$('#datagrid').datagrid('loadData',data);
			},function(data,response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时或请检查账号状态,如没问题请刷新页面重新操作');
				}
			});
		},function(rep){
			console.log(rep);
			$.messager.alert('提示', '确认无误操作失败');
		});
	};
	
	$scope.selectReconciliationDeatil=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length>1){
			$.messager.alert('提示','不能查看多条记录的明细');
			return;
		}else if(rows.length<1){
			$.messager.alert('提示','请选择一条记录，再点击查看明细');
			return;
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		AccountService.getToken('services/tokens',function(data){
			$.messager.progress('close');
			$window.open('datareport/account/reconciliationSign/downLoadBillFileDetailData?filedId='+rows[0]['filedId']+'&token='+data['token']);
		},function(data){
			console.log(data);
		});
	};
}]);


/**
 * 奖金变动明细controller
 */
FolController.controller('bonusChangeDeatilCtrl',['$scope','$rootScope','$timeout','AccountService','$cookies','$q','$window','CodeService',function($scope,$rootScope,$timeout,AccountService,$cookies,$q,$window,CodeService){
	$scope.istableshow=false;
	
	$timeout(function(){
		var changeAmountDataGrid=$('#changeAmountDataGrid').datagrid('getPager');
		
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var changeBonusAmountResult=false,freezeBonusAmountResult=false;
		
		changeAmountDataGrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				
				$scope.bonus_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.bonus_data.endNo=pageSize*pageNumber;		
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});
				
				//查询变动储备金
				AccountService.findChangeBonusAmount($scope.bonus_data,'bonus/changedeatilbonus/query',function(data,response_info){
					$.messager.progress('close');
					changeBonusAmountResult=true;
					
					$('#changeAmountDataGrid').datagrid('loadData',data);
					if(freezeBonusAmountResult&&changeBonusAmountResult){
						defferred.resolve('success');
					}
				},function(data,response_info){
					if(response_info.state===STATE.TIMEOUT){
						console.log(response_info);
						defferred.reject('timeout');
					}
					console.log(data);
				});
			}
		});
		
		var freezeAmountDataGrid=$('#freezeAmountDataGrid').datagrid('getPager');
		freezeAmountDataGrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.bonus_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.bonus_data.endNo=pageSize*pageNumber;		
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});
					
				//查询冻结储备金
				AccountService.findFreezeBonusAmount($scope.bonus_data,'bonus/freezedeatilbonus/query',function(data,response_info){
					$.messager.progress('close');
					freezeBonusAmountResult=true;
					
					$('#freezeAmountDataGrid').datagrid('loadData',data);
					if(freezeBonusAmountResult&&changeBonusAmountResult){
						defferred.resolve('success');
					}
				},function(data,response_info){
					if(response_info.state===STATE.TIMEOUT){
						console.log(response_info);
						defferred.reject('timeout');
					}
					console.log(data);
				});
			}
		});
		
		promise.then(function(data){
			console.log(data);
			if(data==='success'){
				$.messager.progress('close');
			}
		},function(data){
			console.log(data);
			$.messager.progress('close');
			$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
		});
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		var fields=['变动类型'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
			
			var typesId=['#businessCode'];
			
			$timeout(function(){
				$('#bonusType').combobox('loadData',$rootScope.bonusType);
				$('#bonusType').combobox('select',$rootScope.bonusType[0]['code']);
			
			
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
	});
	
	$scope.findReserveDeatilChange=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var changeBonusAmountResult=false,freezeBonusAmountResult=false;
		
		if($('#startTime').datebox('getText')>$('#endTime').datebox('getText')){
			$.messager.alert('提示','开始时间不能大于结束时间');
			return;
		}
		
		$scope.bonus_data={
					bonusType:folUtil.getComboBoxDataById('bonusType'),
					businessCode:folUtil.getComboBoxDataById('businessCode'),
					startTime:$('#startTime').datebox('getValue'),
					endTime:$('#endTime').datebox('getValue'),
					sapCode:$cookies.COOKIE_FOL_SAP_CODE,
					beginNo:0,
					endNo:10
				};
		
		$scope.istableshow=true;
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		//查询变动储备金
		AccountService.findChangeBonusAmount($scope.bonus_data,'bonus/changedeatilbonus/query',function(data,response_info){
			$.messager.progress('close');
			changeBonusAmountResult=true;
			
			$('#changeAmountDataGrid').datagrid('loadData',data);
			if(freezeBonusAmountResult&&changeBonusAmountResult){
				defferred.resolve('success');
			}
		},function(data,response_info){
			if(response_info.state===STATE.TIMEOUT){
				console.log(response_info);
				defferred.reject('timeout');
			}else{
				defferred.reject('error');
			}
			
			console.log(data);
		});

		//查询冻结储备金
		AccountService.findFreezeBonusAmount($scope.bonus_data,'bonus/freezedeatilbonus/query',function(data,response_info){
			$.messager.progress('close');
			freezeBonusAmountResult=true;
			
			$('#freezeAmountDataGrid').datagrid('loadData',data);
			if(freezeBonusAmountResult&&freezeBonusAmountResult){
				defferred.resolve('success');
			}
		},function(data,response_info){
			if(response_info.state===STATE.TIMEOUT){
				defferred.reject('timeout');
			}else{
				defferred.reject('error');
			}
			console.log(data);
		});
		
		promise.then(function(data){
			console.log(data);
			if(data==='success'){
				$.messager.progress('close');
			}
		},function(data){
			console.log(data);
			$.messager.progress('close');
			$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
		});
	};
	
	$.messager.progress({
		title:'Please waiting',
		msg:'Loading data...',
		interval:PROGRESS_ACTION_TIMEOUT
	});
	
	//文件导出
	$scope.deatilChangeResult=function(){
		
		AccountService.getToken('services/tokens',function(data){
			if(folUtil.isNull($scope.bonus_data)){
				$.messager.alert('提示','请先查询一次再导出');
				return;
			}
			$.messager.progress('close');
			$window.open('bonus/BonusChangeDeatil?reserveType='+$scope.bonus_data.bonusType+'&businessCode='+$scope.bonus_data.businessCode+'&sapCode='+$cookies.COOKIE_FOL_SAP_CODE+'&startTime='+$scope.bonus_data.startTime+'&endTime='+$scope.bonus_data.endTime+'&token='+data['token']);
			$window.open('bonus/BonusFreezeDeatil?reserveType='+$scope.bonus_data.bonusType+'&businessCode='+$scope.bonus_data.businessCode+'&sapCode='+$cookies.COOKIE_FOL_SAP_CODE+'&startTime='+$scope.bonus_data.startTime+'&endTime='+$scope.bonus_data.endTime+'&token='+data['token']);
		},function(data){
			$.messager.progress('close');
			console.log(data);
		});
	};
}]);


