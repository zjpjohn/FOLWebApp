/**
 * 奖金余额对账
 */
FolController.controller('bonusAmountReconcileCtrl',['$scope','$rootScope','$timeout','BonusService','$window',function($scope,$rootScope,$timeout,BonusService,$window){
	$scope.istableshow=false;
	
	/**
	 * 数据初始化，因为使用NG-IF所以要给执行语句时间
	 */
	function init(){
		$timeout(function(){
			if($('#datagrid').length>0){
				var datagrid=$('#datagrid').datagrid('getPager');
				datagrid.pagination({
					onSelectPage:function(pageNumber,pageSize){
		
						$scope.bonus_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
						$scope.bonus_data.endNo=pageSize*pageNumber;
						$.messager.progress({
							title:'Please waiting',
							msg:'Loading data...',
							interval:PROGRESS_ACTION_TIMEOUT
						});	
						BonusService.findBonusAmount($scope.bonus_data,'bonus/bonusAmountReconcile/query',function(data,response_info){
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
	
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		BonusService.findCodeTypeNames('bonus/bonusAmountReconcile/findCodeTypeNames',function(data,response_info){
			$.messager.progress('close');
			var typesId=['#dealerType'];

			$timeout(function(){
				$('#bonusType').combobox('loadData',$rootScope.bonusType);
				$('#bonusType').combobox('select',$rootScope.bonusType[0]['code']);
				folUtil.setComboBoxYearDataById('year');
				folUtil.setComboBoxMonthDataById('month');
				folUtil.setComboBoxYesAndNoById('isDiff');
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
	 * 查询奖金余额对账
	 */
	$scope.findbonusAmountReconcile=function(){
		init();
		var changeTime=folUtil.getComboBoxDataById('year')+'-'+folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month'));
		$scope.bonus_data={dealerType:folUtil.getComboBoxDataById('dealerType'),
				bonusType:folUtil.getComboBoxDataById('bonusType'),
				changeTime:changeTime,
				isDiff:folUtil.getComboBoxDataById('isDiff'),
				endSapCode:$('#endSapCode').textbox('getValue'),
				startSapCode:$('#startSapCode').textbox('getValue'),
				year:folUtil.getComboBoxDataById('year'),
				month:folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month')),
				beginNo:0,
				endNo:10
			};
					
		$scope.istableshow=true;
			
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BonusService.findbonusAmountReconcile($scope.bonus_data,'bonus/bonusAmountReconcile/query',function(data,response_info){
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
	 * 文件导出
	 */
	$scope.bonusAmountReconcile=function(){			
		BonusService.getToken('services/tokens',function(data,response_info){
			if(folUtil.isNull($scope.bonus_data)){
				$.messager.alert('提示','请先查询一次再导出');
				return;
			}
				
			$.messager.progress('close');
			$window.open('bonus/bonusAmountReconcile/amountReconcile?bonusType='+$scope.bonus_data.bonusType+'&isDiff='+$scope.bonus_data.isDiff+'&endSapCode='+$scope.bonus_data.endSapCode+'&startSapCode='+$scope.bonus_data.startSapCode+'&changeTime='+$scope.bonus_data.changeTime+'&year='+$scope.bonus_data.year+'&month='+$scope.bonus_data.month+'&dealerType='+$scope.bonus_data.dealerType+'&token='+data['token']);
		},function(response_info){
			console.log(response_info);
		});
	};
}]);

/**
 * 奖金月度明细
 */
FolController.controller('bonusMonthDeatilCtrl',['$scope','$rootScope','$timeout','BonusService','$window',function($scope,$rootScope,$timeout,BonusService,$window){
	$scope.istableshow=false;

	/**
	 * 数据初始化
	 */
	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				
				$scope.bonus_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
				$scope.bonus_data.endNo=pageSize*pageNumber;

				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
					
				BonusService.findBonusAmount($scope.bonus_data,'bonus/bonusMonthdeatil/query',function(data,response_info){
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
		
		BonusService.findCodeTypeNames('bonus/bonusMonthdeatil/findCodeTypeNames',function(data,response_info){
			$.messager.progress('close');
			var typesId=['#dealerType'];
	
			$timeout(function(){
				$('#bonusType').combobox('loadData',$rootScope.bonusType);
				$('#bonusType').combobox('select',$rootScope.bonusType[0]['code']);
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

	/**
	 * 查询月度明细
	 */
	$scope.findBonusMonthDeatil=function(){
		var changeTime=folUtil.getComboBoxDataById('year')+'-'+folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month'));
		$scope.bonus_data={dealerType:folUtil.getComboBoxDataById('dealerType'),
				bonusType:folUtil.getComboBoxDataById('bonusType'),
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

		//查询变动奖金
		BonusService.findBonusMonthDeatil($scope.bonus_data,'bonus/bonusMonthdeatil/query',function(data,response_info){
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
	 * 文件导出
	 */
	$scope.monthDeatilResult=function(){
		BonusService.getToken('services/tokens',function(data,response_info){
			if(folUtil.isNull($scope.bonus_data)){
				$.messager.alert('提示','请先查询一次再导出');
				return;
			}
			
			$.messager.progress('close');
			$window.open('bonus/bonusMonthdeatil/monthDeatilResult?dealerName='+encodeURI(encodeURI($scope.bonus_data.dealerName))+'&sapCode='+$scope.bonus_data.sapCode+'&dealerType='+$scope.bonus_data.dealerType+'&bonusType='+$scope.bonus_data.bonusType+'&changeTime='+$scope.bonus_data.changeTime+'&token='+data['token']);
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
 * 奖金余额汇总表
 */
FolController.controller('bonusAmountCollectCtrl',['$scope','$rootScope','$timeout','BonusService','$window',function($scope,$rootScope,$timeout,BonusService,$window){
	$scope.istableshow=false;
	
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){

				$scope.bonus_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
				$scope.bonus_data.endNo=pageSize*pageNumber;
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				BonusService.findBonusAmount($scope.bonus_data,'bonus/amountReconcileCollect/query',function(data,response_info){
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
		
		BonusService.findCodeTypeNames('bonus/amountReconcileCollect/findCodeTypeNames',function(data,response_info){
			$.messager.progress('close');
			var typesId=['#typeName'];

			$timeout(function(){
			
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
		
	/**
	 * 查询奖金余额汇总
	 */
	$scope.findBonusAmountCollect=function(){
		var changeTime=folUtil.getComboBoxDataById('year')+'-'+folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month'));
		$scope.bonus_data={
				typeName:folUtil.getComboBoxDataById('typeName'),
				changeTime:changeTime,
				year:folUtil.getComboBoxDataById('year'),
				month:folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month')),
				beginNo:0,
				endNo:10
			};
		
		$scope.istableshow=true;
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		BonusService.findBonusAmountCollect($scope.bonus_data,'bonus/amountReconcileCollect/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};	

	/**
	 * 文件导出
	 */
	$scope.amountReconcileCollect=function(){
		
		BonusService.getToken('services/tokens',function(data,response_info){
			if(folUtil.isNull($scope.bonus_data)){
				$.messager.alert('提示','请先查询一次再导出');
				return;
			}
			
			$window.open('bonus/amountReconcileCollect/amountReconcileCollect?typeName='+$scope.bonus_data.typeName+'&changeTime='+$scope.bonus_data.changeTime+'&year='+$scope.bonus_data.year+'&month='+$scope.bonus_data.month+'&token='+data['token']);
		},function(response_info){
			console.log(response_info);
		});
	};
}]);