/**
 * 储备金余额查询
 */
FolController.controller('reserveAmountCtrl',['$scope','$rootScope','ReserveService','$state','$timeout','$window',function($scope,$rootScope,ReserveService,$state,$timeout,$window){
	$scope.istableshow=false;
	
	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.reserve_amount_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
				$scope.reserve_amount_data.endNo=pageSize*pageNumber;
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				ReserveService.findReserveAmount($scope.reserve_amount_data,'reserve/reserveamount/query',function(data,response_info){
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

		$('#ReserveDeatilWindow').panel({
			onBeforeOpen:function(){
				$('#changeAmountDataGrid').attr('class','row-fluid ng-hide');
				$('#freezeAmountDataGrid').attr('class','row-fluid ng-hide');
			}
		});
	});
	

	$scope.findReserveAmount=function(){
		$scope.reserve_amount_data={
				dealerType:folUtil.getComboBoxDataById('dealerType'),
				reserveType:folUtil.getComboBoxDataById('reserveType'),
				sapCode:$('#sapCode').val(),
				beginNo:0,
				endNo:10
		};

		$scope.istableshow=true;
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		ReserveService.findReserveAmount($scope.reserve_amount_data,'reserve/reserveamount/query',function(data,response_info){
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
	
	$.messager.progress({
		title:'Please waiting',
		msg:'Loading data...',
		interval:PROGRESS_ACTION_TIMEOUT
	});	
	
	ReserveService.findCodeTypeNames('reserve/reserveamount/findCodeTypeNames',function(data,response_info){
		$.messager.progress('close');
		
		var typesId=['#dealerType'];
		
		$timeout(function(){
			$('#reserveType').combobox('loadData',$rootScope.reserveType);
			$('#reserveType').combobox('select',$rootScope.reserveType[0]['code']);
		
			for(var i=0;i<data.length;i++){
				if(!folUtil.isNull(data)){
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
	
	//文件导出
	$scope.exportReserveQueryResult=function(){
		
		ReserveService.getToken('services/tokens',function(data){
			if(folUtil.isNull($scope.reserve_amount_data)){
				$.messager.alert('提示','请先查询一次再导出');
				return;
			}
			
			$.messager.progress('close');
			$window.open('reserve/reserveamount/exportReserveQueryResult?dealerType='+$scope.reserve_amount_data.dealerType+'&reserveType='+$scope.reserve_amount_data.reserveType+'&sapCode='+$scope.reserve_amount_data.sapCode+'&token='+data['token']);
		},function(response_info){
			$.messager.progress('close');
			console.log(response_info);
		});
	};
	
	/**
	 * 打开储备金明细界面
	 */
	$scope.findReconciliationDeatil=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择某条记录再操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示','只能选择一条记录进行操作');
			return;
		}
		
		$scope.dealer=rows[0];
		
		$('#deatilSapCode').textbox({value:$scope.dealer.sapCode});
		
		$('#ReserveDeatilWindow').window('open');
	};
	
}]);

/**
 * 储备金变动明细
 */
FolController.controller('reserveDeatilChangeCtrl',['$scope','$rootScope','$timeout','ReserveService','$q','$window',function($scope,$rootScope,$timeout,ReserveService,$q,$window){
	$scope.istableshow=false;
	
	$timeout(function(){
		var changeAmountDataGrid=$('#changeAmountDataGrid').datagrid('getPager');
		
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var changeReserveAmountResult=false,freezeReserveAmountResult=false;
		changeAmountDataGrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.reserve_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.reserve_data.endNo=pageSize*pageNumber;	
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
					
				ReserveService.findChangeReserveAmount($scope.reserve_data,'reserve/reservedeatilchange/changedeatilreserve/query',function(data,response_info){
					$.messager.progress('close');
					$('#changeAmountDataGrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log(response_info);
						$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
					}else{
						$.messager.alert("提示",response_info.message);
					}
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
				
				ReserveService.findFreezeReserveAmount($scope.reserve_data,'reserve/reservedeatilchange/freezedeatilreserve/query',function(data,response_info){
					$.messager.progress('close');
					$('#freezeAmountDataGrid').datagrid('loadData',data);
				},function(response_info){
					if(response_info.state===STATE.TIMEOUT){
						$.messager.progress('close');
						$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
					}else{
						$.messager.alert("提示",response_info.message);
					}
				});
			}
		});
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		ReserveService.findCodeTypeNames('reserve/reservedeatilchange/findCodeTypeNames',function(data,response_info){
			$.messager.progress('close');
			var typesId=['#dealerType','#businessCode'];

			$timeout(function(){
				$('#reserveType').combobox('loadData',$rootScope.reserveType);
				$('#reserveType').combobox('select',$rootScope.reserveType[0]['code']);
				
				for(var i=0;i<data.length;i++){
					if(!folUtil.isNull(data[i])){
						$(typesId[i]).combobox('loadData',data[i]);
						$(typesId[i]).combobox('select',data[i][0]['code']);
					}
				}
			});
		},function(response_info){
			$.messager.progress('close');
			if(folUtil.isNull(response_info)){
				$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
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
				dealerType:folUtil.getComboBoxDataById('dealerType'),
				reserveType:folUtil.getComboBoxDataById('reserveType'),
				sapCode:$('#sapCode').val(),
				businessCode:folUtil.getComboBoxDataById('businessCode'),
				startTime:$('#startTime').datebox('getValue'),
				endTime:$('#endTime').datebox('getValue'),
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
		ReserveService.findChangeReserveAmount($scope.reserve_data,'reserve/reservedeatilchange/changedeatilreserve/query',function(data,response_info){
			$('#changeAmountDataGrid').datagrid('loadData',{total:0,rows:[]});
			$('#changeAmountDataGrid').datagrid('loadData',data);
			
			changeReserveAmountResult=true;
			if(freezeReserveAmountResult&&changeReserveAmountResult){
				defferred.resolve('success');
			}
		},function(response_info){
			if(response_info.state===STATE.TIMEOUT){
				console.log(response_info);
				defferred.reject('timeout');
			}else{
				defferred.reject('error');
			}			
		});

		//查询冻结储备金
		ReserveService.findFreezeReserveAmount($scope.reserve_data,'reserve/reservedeatilchange/freezedeatilreserve/query',function(data,response_info){
			$('#freezeAmountDataGrid').datagrid('loadData',{total:0,rows:[]});
			$('#freezeAmountDataGrid').datagrid('loadData',data);

			freezeReserveAmountResult=true;
			if(freezeReserveAmountResult&&changeReserveAmountResult){
				defferred.resolve('success');
			}
		},function(response_info){
			if(response_info.state===STATE.TIMEOUT){
				defferred.reject('timeout');
			}else{
				defferred.reject('error');
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
			if(data=='timeout'){
				$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
			}else{
				$.messager.alert("提示",'系统出错');
			}
			
		});
	};

	//文件导出
	$scope.deatilChangeResult=function(){
		
		ReserveService.getToken('services/tokens',function(data){
			if(folUtil.isNull($scope.reserve_data)){
				$.messager.alert('提示','请先查询一次再导出');
				return;
			}
			
			$window.open('reserve/reservedeatilchange/ChangeReserveAmount?dealerType='+$scope.reserve_data.dealerType+'&reserveType='+$scope.reserve_data.reserveType+'&businessCode='+$scope.reserve_data.businessCode+'&sapCode='+$scope.reserve_data.sapCode+'&startTime='+$scope.reserve_data.startTime+'&endTime='+$scope.reserve_data.endTime+'&token='+data['token']);
			$window.open('reserve/reservedeatilchange/FreezeReserveAmount?dealerType='+$scope.reserve_data.dealerType+'&reserveType='+$scope.reserve_data.reserveType+'&businessCode='+$scope.reserve_data.businessCode+'&sapCode='+$scope.reserve_data.sapCode+'&startTime='+$scope.reserve_data.startTime+'&endTime='+$scope.reserve_data.endTime+'&token='+data['token']);
		},function(response_info){
			console.log(response_info);
		});
	};
	
	/**
	 * 订单查看
	 */
	$scope.findOrderDeatil=function(id){
		var rows=$('#'+id).datagrid('getSelections');
		if(rows.length<1){
			$.messager.alert('提示','请选择一条记录再操作');
			return;
		}
		$.each(rows,function(i,val){
			var referCode=val['referCode'];
			var nums=referCode.split('|');
			$window.open(POL_SERVER_URL+'index.html#/1300_130020?orderNo='+nums[0]);
		});
		
	};
	
	/**
	 * billing查看
	 */
	$scope.findBillingDeatil=function(id){
		var rows=$('#'+id).datagrid('getSelections');

		if(rows.length<1){
			$.messager.alert('提示','请选择一条记录再操作');
			return;
		}
		$.each(rows,function(i,val){
			var referCode=val['referCode'];
			var nums=referCode.split('|')
			$window.open(POL_SERVER_URL+'index.html#/1300_130040?billingNo='+nums[1]);
		});
	}
}]);

/**
 * 储备金变动明细(储备金余额)
 */
FolController.controller('reserveAmountDeatilChangeCtrl',['$scope','$rootScope','$timeout','ReserveService','$q','$window',function($scope,$rootScope,$timeout,ReserveService,$q,$window){
	$scope.istableshow=false;
	
	$timeout(function(){
		$('#ReserveDeatilWindow').panel({
			onBeforeOpen:function(){
				$('#changeAmountDataTable').attr('class','row-fluid ng-hide');
				$('#freezeAmountDataTable').attr('class','row-fluid ng-hide');
			}
		});
		
		var changeAmountDataGrid=$('#changeAmountDataGrid').datagrid('getPager');
		
		changeAmountDataGrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.reserve_deatil_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.reserve_deatil_data.endNo=pageSize*pageNumber;	
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
					
				ReserveService.findChangeReserveAmount($scope.reserve_deatil_data,'reserve/reservedeatilchange/changedeatilreserve/query',function(data,response_info){
					$('#changeAmountDataTable').attr('class','row-fluid ng-show');
					$('#changeAmountDataGrid').datagrid('loadData',data);
					$.messager.progress('close');
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
					}else{
						$.messager.alert("提示",response_info.message);
					}
				});
			}
		});
		
		var freezeAmountDataGrid=$('#freezeAmountDataGrid').datagrid('getPager');
		freezeAmountDataGrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
		
				$scope.reserve_deatil_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.reserve_deatil_data.endNo=pageSize*pageNumber;	
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				ReserveService.findFreezeReserveAmount($scope.reserve_deatil_data,'reserve/reservedeatilchange/freezedeatilreserve/query',function(data,response_info){
					$('#freezeAmountDataTable').attr('class','row-fluid ng-show');
					$('#freezeAmountDataGrid').datagrid('loadData',data);
					$.messager.progress('close');
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
					}else{
						$.messager.alert("提示",response_info.message);
					}
				});
			}
		});
		

		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		ReserveService.findCodeTypeNames('reserve/reservedeatilchange/findCodeTypeNames',function(data,response_info){
			$.messager.progress('close');
			var typesId=['#deatilDealerType','#deatilBusinessCode'];

			$timeout(function(){
				$('#deatilReserveType').combobox('loadData',$rootScope.reserveType);
				$('#deatilReserveType').combobox('select',$rootScope.reserveType[0]['code']);
				
				for(var i=0;i<data.length;i++){
					if(!folUtil.isNull(data[i])){
						$(typesId[i]).combobox('loadData',data[i]);
						$(typesId[i]).combobox('select',data[i][0]['code']);
					}
				}
			});
		},function(response_info){
			$.messager.progress('close');
			if(folUtil.isNull(response_info)){
				$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
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
		
		$scope.reserve_deatil_data={
				dealerType:folUtil.getComboBoxDataById('deatilDealerType'),
				reserveType:folUtil.getComboBoxDataById('deatilReserveType'),
				sapCode:$('#deatilSapCode').textbox('getValue'),
				businessCode:folUtil.getComboBoxDataById('deatilBusinessCode'),
				startTime:$('#startTime').datebox('getValue'),
				endTime:$('#endTime').datebox('getValue'),
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
		ReserveService.findChangeReserveAmount($scope.reserve_deatil_data,'reserve/reservedeatilchange/changedeatilreserve/query',function(data,response_info){
			changeReserveAmountResult=true;

			$('#changeAmountDataTable').attr('class','row-fluid ng-show');
			$('#changeAmountDataGrid').datagrid('loadData',{total:0,rows:[]});
			$('#changeAmountDataGrid').datagrid('loadData',data);
			if(freezeReserveAmountResult&&changeReserveAmountResult){
				defferred.resolve('success');
			}
		},function(response_info){
			if(response_info.state===STATE.TIMEOUT){
				console.log(response_info);
				defferred.reject('timeout');
			}else{
				defferred.reject('error');
			}
		});

		//查询冻结储备金
		ReserveService.findFreezeReserveAmount($scope.reserve_deatil_data,'reserve/reservedeatilchange/freezedeatilreserve/query',function(data,response_info){
			freezeReserveAmountResult=true;

			$('#freezeAmountDataTable').attr('class','row-fluid ng-show');
			$('#freezeAmountDataGrid').datagrid('loadData',{total:0,rows:[]});
			$('#freezeAmountDataGrid').datagrid('loadData',data);
			if(freezeReserveAmountResult&&changeReserveAmountResult){
				defferred.resolve('success');
			}
		},function(response_info){
			if(response_info.state==STATE.TIMEOUT){
				defferred.reject('timeout');
			}else{
				defferred.reject('error');
			}
		});
		
		promise.then(function(data){
			console.log(data);
			if(data=='success'){
				$.messager.progress('close');
			}
		},function(response_info){
			$.messager.progress('close');
			$.messager.alert("提示",'系统超时或请检查账号状态,如没问题请刷新页面重新操作');
		});
	};

	//文件导出
	$scope.deatilChangeResult=function(){
		
		ReserveService.getToken('services/tokens',function(data){
			if(folUtil.isNull($scope.reserve_deatil_data)){
				$.messager.alert('提示','请先查询一次再导出');
				return;
			}
			
			$window.open('reserve/reservedeatilchange/ChangeReserveAmount?dealerType='+$scope.reserve_deatil_data.dealerType+'&reserveType='+$scope.reserve_deatil_data.reserveType+'&businessCode='+$scope.reserve_deatil_data.businessCode+'&sapCode='+$scope.reserve_deatil_data.sapCode+'&startTime='+$scope.reserve_deatil_data.startTime+'&endTime='+$scope.reserve_deatil_data.endTime+'&token='+data['token']);
			$window.open('reserve/reservedeatilchange/FreezeReserveAmount?dealerType='+$scope.reserve_deatil_data.dealerType+'&reserveType='+$scope.reserve_deatil_data.reserveType+'&businessCode='+$scope.reserve_deatil_data.businessCode+'&sapCode='+$scope.reserve_deatil_data.sapCode+'&startTime='+$scope.reserve_deatil_data.startTime+'&endTime='+$scope.reserve_deatil_data.endTime+'&token='+data['token']);
		},function(response_info){
			console.log(response_info);
		});
	};
	
	/**
	 * 订单查看
	 */
	$scope.findOrderDeatil=function(id){
		var rows=$('#'+id).datagrid('getSelections');
		if(rows.length<1){
			$.messager.alert('提示','请选择一条记录再操作');
			return;
		}
		$.each(rows,function(i,val){
			var referCode=val['referCode'];
			var nums=referCode.split('|');
			$window.open(POL_SERVER_URL+'index.html#/1300_130020?orderNo='+nums[0]);
		});
		
	};
	
	/**
	 * billing查看
	 */
	$scope.findBillingDeatil=function(id){
		var rows=$('#'+id).datagrid('getSelections');

		if(rows.length<1){
			$.messager.alert('提示','请选择一条记录再操作');
			return;
		}
		$.each(rows,function(i,val){
			var referCode=val['referCode'];
			var nums=referCode.split('|')
			$window.open(POL_SERVER_URL+'index.html#/1300_130040?billingNo='+nums[1]);
		});
	}
}]);

/**
 * 储备金月度明细
 */
FolController.controller('reserveMonthDeatilCtrl',['$scope','$rootScope','$timeout','ReserveService','$window',function($scope,$rootScope,$timeout,ReserveService,$window){
	$scope.istableshow=false;

	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				
				$scope.reserve_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.reserve_data.endNo=pageSize*pageNumber;
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
					
				ReserveService.findReserveAmount($scope.reserve_data,'reserve/reservemonthdeatil/query',function(data,response_info){
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
		
		ReserveService.findCodeTypeNames('reserve/reservemonthdeatil/findCodeTypeNames',function(data,response_info){
			$.messager.progress('close');
			var typesId=['#dealerType'];

			$timeout(function(){
				$('#reserveType').combobox('loadData',$rootScope.reserveType);
				$('#reserveType').combobox('select',$rootScope.reserveType[0]['code']);
				folUtil.setComboBoxYearDataById('year');
				folUtil.setComboBoxMonthDataById('month');
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
	
	$scope.findReserveMonthDeatil=function(){
		var changeTime=folUtil.getComboBoxDataById('year')+'-'+folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month'));
		$scope.reserve_data={dealerType:folUtil.getComboBoxDataById('dealerType'),
				reserveType:folUtil.getComboBoxDataById('reserveType'),
				sapCode:$('#sapCode').textbox('getValue'),
				dealerName:$('#dealerName').textbox('getValue'),
				changeTime:changeTime,
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
		ReserveService.findReserveMonthDeatil($scope.reserve_data,'reserve/reservemonthdeatil/query',function(data,response_info){
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

	//文件导出
	$scope.monthDeatilResult=function(){
		ReserveService.getToken('services/tokens',function(data){
			if(folUtil.isNull($scope.reserve_data)){
				$.messager.alert('提示','请先查询一次再导出');
				return;
			}
			
			console.log('sapCode2:'+$scope.reserve_data.sapCode);
			$window.open('reserve/reservemonthdeatil/monthDeatilResult?dealerName='+encodeURI(encodeURI($scope.reserve_data.dealerName))+'&sapCode='+$scope.reserve_data.sapCode+'&dealerType='+$scope.reserve_data.dealerType+'&reserveType='+$scope.reserve_data.reserveType+'&changeTime='+$scope.reserve_data.changeTime+'&token='+data['token']);
		},function(response_info){
			console.log(response_info);
		});
	};
	
	/**
	 * 订单查看
	 */
	$scope.findOrderDeatil=function(id){
		var rows=$('#'+id).datagrid('getSelections');
		if(rows.length<1){
			$.messager.alert('提示','请选择一条记录再操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示','不能选择多条记录，请选择一条记录再操作');
			return;
		}else if(rows[0]['referType'].indexOf('SAP')>-1){
			$.messager.alert('提示','该条记录不能查看订单相关信息');
			return;
		}
		$.each(rows,function(i,val){
			var referCode=val['referCode'];
			var nums=referCode.split('|');
			$window.open(POL_SERVER_URL+'index.html#/1300_130020?orderNo='+nums[0]);
		});
		
	};
	
	/**
	 * billing查看
	 */
	$scope.findBillingDeatil=function(id){
		var rows=$('#'+id).datagrid('getSelections');

		if(rows.length<1){
			$.messager.alert('提示','请选择一条记录再操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示','不能选择多条记录，请选择一条记录再操作');
			return;
		}else if(rows[0]['referType'].indexOf('SAP')>-1){
			$.messager.alert('提示','该条记录不能查看billing相关信息');
			return;
		}
		$.each(rows,function(i,val){
			var referCode=val['referCode'];
			var nums=referCode.split('|')
			$window.open(POL_SERVER_URL+'index.html#/1300_130040?billingNo='+nums[1]);
		});
	}
}]);
/**
 * 储备金余额对账
 */
FolController.controller('reserveAmountReconcileCtrl',['$scope','$rootScope','$timeout','ReserveService','$window',function($scope,$rootScope,$timeout,ReserveService,$window){
	$scope.istableshow=false;

	function init(){
		//分页初始化
		$timeout(function(){
			if($('#datagrid').length>0){
				var datagrid=$('#datagrid').datagrid('getPager');
				datagrid.pagination({
					onSelectPage:function(pageNumber,pageSize){
		
						$scope.reserve_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
						$scope.reserve_data.endNo=pageSize*pageNumber;
						
						$.messager.progress({
							title:'Please waiting',
							msg:'Loading data...',
							interval:PROGRESS_ACTION_TIMEOUT
						});	
						
						ReserveService.findReserveAmountReconcile($scope.reserve_data,'reserve/reserveamountreconcile/query',function(data,response_info){
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
		},PROGRESS_ACTION_TIMEOUT);
	}
	
	$scope.findReserveAmountReconcile=function(){
		$scope.istableshow=true;
		
		init();
		
		var changeTime=folUtil.getComboBoxDataById('year')+'-'+folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month'));
		$scope.reserve_data={dealerType:folUtil.getComboBoxDataById('dealerType'),
				reserveType:folUtil.getComboBoxDataById('reserveType'),
				startSapCode:$('#startSapCode').textbox('getValue'),
				endSapCode:$('#endSapCode').textbox('getValue'),
				changeTime:changeTime,
				year:folUtil.getComboBoxDataById('year'),
				month:folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month')),
				difference:folUtil.getComboBoxDataById('difference'),
				beginNo:0,
				endNo:10
		};

		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		ReserveService.findReserveAmountReconcile($scope.reserve_data,'reserve/reserveamountreconcile/query',function(data,response_info){
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
	
	$.messager.progress({
		title:'Please waiting',
		msg:'Loading data...',
		interval:PROGRESS_ACTION_TIMEOUT
	});	
	
	ReserveService.findCodeTypeNames('reserve/reserveamountreconcile/findCodeTypeNames',function(data,response_info){
		$.messager.progress('close');
		var typesId=['#dealerType'];
		
		$timeout(function(){
			$('#reserveType').combobox('loadData',$rootScope.reserveType);
			$('#reserveType').combobox('select',$rootScope.reserveType[0]['code']);
			folUtil.setComboBoxYearDataById('year');
			folUtil.setComboBoxMonthDataById('month');
			folUtil.setComboBoxYesAndNoById('difference');
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
	//文件导出
	$scope.amountReconcile=function(){
			
			ReserveService.getToken('services/tokens',function(data,response_info){
				if(folUtil.isNull($scope.reserve_data)){
					$.messager.alert('提示','请先查询一次再导出');
					return;
				}
				
				$window.open('reserve/reserveamountreconcile/amountReconcile?dealerType='+$scope.reserve_data.dealerType+'&reserveType='+$scope.reserve_data.reserveType+'&startSapCode='+$scope.reserve_data.startSapCode+'&endSapCode='+$scope.reserve_data.endSapCode+'&changeTime='+$scope.reserve_data.changeTime+'&year='+$scope.reserve_data.year+'&month='+$scope.reserve_data.month+'&difference='+$scope.reserve_data.difference+'&token='+data['token']);
			},function(response_info){
				console.log(response_info);
			});
		};
}]);