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
/**
 * 奖金定义controller
 */
FolController.controller('BonusDefinitionController',['$scope','$timeout','$state','BonusDefinitionSerivce',function($scope,$timeout,$state,BonusDefinitionSerivce){
	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.codeVo.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.codeVo.endNo=pageSize*pageNumber;
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				BonusDefinitionSerivce.queryBonusDefinition($scope.codeVo,'bonus/type/query',function(data,response_info){
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
	});
	
	$scope.addBonusDefinition=function(){
		$state.go('addBonusDefinition');
	};
	
	$scope.queryBonusDefinition=function(){
		$scope.istableshow=true;
		
		$scope.codeVo={
				code:$scope.code,
				codeCnDesc:$scope.codeCnDesc,
				beginNo:0,
				endNo:10
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BonusDefinitionSerivce.queryBonusDefinition($scope.codeVo,'bonus/type/query',function(data,response_info){
			$.messager.progress('close');
			if(data.length===0){
				$scope.istableshow=false;
				$.messager.alert('提示','没有相关符合的数据');
			}else{
				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
				$('#datagrid').datagrid('loadData',data);
			}
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				$scope.istableshow=false;
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};
	
	$scope.updateBonusDefinition=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length>1){
			$.messager.alert('提示', '不能选择多个奖金类型，请重新选择');
			return;
		}else if(rows.length<1){
			$.messager.alert('提示', '请选择一个奖金类型，请重新选择');
			return;
		}
		
		$state.go('updateBonusDefinition',{bonusType: rows[0]});
	};
}]);

/**
 * 新增奖金定义controller
 */
FolController.controller('AddBonusDefinitionController',['$scope','$timeout','$state','BonusDefinitionSerivce',function($scope,$timeout,$state,BonusDefinitionSerivce){
	$scope.addBonusDefinition=function(){
		var codeVo={
			code:$scope.code,
			codeCnDesc:$scope.codeCnDesc,
			remark:$scope.remark,
			typeName:'奖金类型'
		};
		
		if(!$scope.checkData(codeVo)){
			return;
		}
		
		BonusDefinitionSerivce.addBonusDefinition(codeVo,'bonus/type/add',function(data,response_info){
			$.messager.alert('提示','新增奖金成功');
			$state.go('bonusDefinition');
		},function(response_info){
			$.messager.alert('提示','新增奖金失败');
			console.log(response_info);
		});
	};
	
	$scope.checkData=function(data){
		if(folUtil.isNull(data.code)){
			$.messager.alert('提示', '请填写奖金代码，再继续操作');
			return false;
		}
		
		if(folUtil.isNull(data.codeCnDesc)){
			$.messager.alert('提示', '请填写奖金名称，再继续操作');
			return false;
		}
		return true;
	};
}]);

/**
 * 更新奖金定义controller
 */
FolController.controller('UpdateBonusDefinitionController',['$scope','$timeout','$state','$stateParams','BonusDefinitionSerivce',function($scope,$timeout,$state,$stateParams,BonusDefinitionSerivce){
	console.log($stateParams.bonusType);
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		$('#code').textbox('setValue',$stateParams.bonusType.code);
		$('#codeCnDesc').textbox('setValue',$stateParams.bonusType.codeCnDesc);
		$('#remark').textbox('setValue',$stateParams.bonusType.remark);
		$scope.code=$stateParams.bonusType.code;
		$scope.codeCnDesc=$stateParams.bonusType.codeCnDesc;
		$scope.remark=$stateParams.bonusType.remark;
		$scope.codeId=$stateParams.bonusType.codeId;
	});
	
	/**
	 * 更新bonusType
	 */
	$scope.updateBonusDefinition=function(){
		var codeVo={
			codeId:$scope.codeId,
			code:$scope.code,
			codeCnDesc:$scope.codeCnDesc,
			remark:$scope.remark,
		};
		
		if(!$scope.checkData(codeVo)){
			return;
		}
		
		BonusDefinitionSerivce.updateBonusDefinition(codeVo,'bonus/type/update',function(data,response_info){
			$.messager.alert('提示','更新奖金成功');
			$state.go('bonusDefinition');
		},function(response_info){
			$.messager.alert('提示','更新奖金失败');
			console.log(response_info);
		});
	};
	
	/**
	 * 检查数据
	 */
	$scope.checkData=function(data){
		if(folUtil.isNull(data.code)){
			$.messager.alert('提示', '请填写奖金代码，再继续操作');
			return false;
		}
		
		if(folUtil.isNull(data.codeCnDesc)){
			$.messager.alert('提示', '请填写奖金名称，再继续操作');
			return false;
		}
		return true;
	};
}]);
/**
 * 奖金发放
 */
FolController.controller('BonusIssueController',['$scope','$timeout','$q','$window','BonusIssueService',function($scope,$timeout,$q,$window,BonusIssueService){
	$scope.istableshow=false;
	
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
					
					BonusIssueService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
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
	});
	
	/**
	 * 奖金待发放的查询
	 */
	$scope.query=function(){
		$scope.istableshow=true;
		
		$scope.query_data={
				batchNo:$scope.batchNo,
				matchState:STATE.SUCCESS_AUDIT,
				issueStatus:STATE.WAIT_ISSUE,
				type:TYPE.BONUS_ISSUE,
				beginNo:0,
				endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BonusIssueService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
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
	
	/**
	 * 奖金发放
	 */
	$scope.bonusIssue=function(){	
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length<1){
			$.messager.alert('提示', '请选择一个文件在发放');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多个文件在发放');
			return;
		}else{
			var defferred=$q.defer();
			var promise=defferred.promise;
			
			// BonusIssueService.issue(rows[0],'bonus/bonusissue/payBonus',function(data,response_info){
			// 	defferred.resolve('success');
			// },function(data,response_info){
			// 	defferred.reject('fail');
			// });
			
			// promise.then(function(data){
			// 	$.messager.alert('提示', '奖金发放成功');
				
			// 	BonusIssueService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
			// 		$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			// 		$('#datagrid').datagrid('loadData',data);
			// 	},function(response_info){
			// 		if(response_info.state===STATE.TIMEOUT){
			// 			console.log('timeout');
			// 			$.messager.alert('提示','系统超时请稍后访问');
			// 		}
			// 	});
			// },function(data){
			// 	if(response_info.state===STATE.TIMEOUT){
			// 		console.log('timeout');
			// 		$.messager.alert('提示','系统超时请稍后访问');
			// 	}else{
			// 		$.messager.alert('提示', '奖金发放失败');
			// 	}
				
			// 	BonusIssueService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
			// 		$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			// 		$('#datagrid').datagrid('loadData',data);
			// 	},function(response_info){
			// 		if(response_info.state===STATE.TIMEOUT){
			// 			console.log('timeout');
			// 			$.messager.alert('提示','系统超时请稍后访问');
			// 		}
			// 	});
			// });

			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			BonusIssueService.issue(rows[0],'bonus/bonusissue/payBonus',function(data,response_info){
				$.messager.progress('close');
				defferred.resolve('success');
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					defferred.resolve('timeout');
				}else{
					defferred.reject('fail');
				}
			});

			promise.then(function(msg){
				if(msg==='success'){
					$.messager.alert('提示', '奖金发放成功');
				}else if(msg='timeout'){
					$.messager.alert('提示', '奖金发放操作正在进行中，由于执行时间长请稍后查询结果');
				}
				
				BonusIssueService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
					$('#datagrid').datagrid('loadData',{total:0,rows:[]});
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});
			},function(msg){
				$.messager.alert('提示', '奖金发放失败');
				
				BonusIssueService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
					$('#datagrid').datagrid('loadData',{total:0,rows:[]});
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});
			});
		}
	};
	
	
	
	/**
	 * 奖金回退
	 */
	$scope.back=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length<1){
			$.messager.alert('提示', '请选择一个文件在发放');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多个文件在发放');
			return;
		}else{
			BonusIssueService.issue(rows,'bonus/bonusissue/bonusReject',function(data,response_info){
				defferred.resolve('success');
			},function(data,response_info){
				defferred.reject('fail');
			});
			
			promise.then(function(){
				$.messager.alert('提示', '奖金回退成功');
				
				BonusIssueService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
					$('#datagrid').datagrid('loadData',{total:0,rows:[]});
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
				});
			},function(){
				$.messager.alert('提示', '奖金回退失败');
				
			});
		}
	};
	
	//导出
	$scope.exportFile=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		if(rows.length<1){
			$.messager.alert('提示', '请选择一个奖金记录再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多个奖金记录进行操作');
			return;
		}else{
			BonusIssueService.getToken('services/tokens',function(data){
				$.messager.progress('close');
				$window.open('bonus/bonusissue/exportFile?issueState='+STATE.WAIT_ISSUE+'&batchNo='+rows[0]['batchNo']+'&token='+data['token']);
			},function(response_info){
				console.log(response_info);
			});
		}
	}
}]);

/**
 * 奖金发放成功
 */
FolController.controller('BonusIssueSuccessController',['$scope','$timeout','$q','$window','BonusIssueService',function($scope,$timeout,$q,$window,BonusIssueService){
	$timeout(function(){
		/**
		 * 奖金颁发的表格分页
		 */
		if($('#bonusIssueGridDate').length>0){
			var bonusIssueGridDate=$('#bonusIssueGridDate').datagrid('getPager');
			
			bonusIssueGridDate.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.bonus_Issue_Query_Condition.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.bonus_Issue_Query_Condition.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					BonusIssueService.query($scope.bonus_Issue_Query_Condition,'bonus/bonusissue/queryBonusIssueSuc',function(data,response_info){
						$.messager.progress('close');
						$('#bonusIssueGridDate').datagrid('loadData',data);
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
	/**
	 * 奖金颁发查询
	 */
	$scope.bonusIssueQuery=function(){
		$scope.istableshow=true;
		
		$scope.bonus_Issue_Query_Condition={
				batchNo:$('#bonusIssueBatchNo').textbox('getValue'),
				beginDate:$('#bonusIssueBeginDate').datebox('getValue'),
				endDate:$('#bonusIssueEndDate').datebox('getValue'),
				beginNo:0,
				endNo:10
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BonusIssueService.bonusIssueQuery($scope.bonus_Issue_Query_Condition,'bonus/bonusissue/queryBonusIssueSuc',function(data,response_info){
			$.messager.progress('close');
			$('#bonusIssueGridDate').datagrid('loadData',{total:0,rows:[]});
			$('#bonusIssueGridDate').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};
	//导出
	$scope.exportFile=function(){
		var rows=$('#bonusIssueGridDate').datagrid('getSelections');
		if(rows.length<1){
			$.messager.alert('提示', '请选择一个奖金记录再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多个奖金记录进行操作');
			return;
		}else{
			BonusIssueService.getToken('services/tokens',function(data){
				$.messager.progress('close');
				$window.open('bonus/bonusissue/exportFile?issueState='+STATE.SUCCESS_ISSUE+'&batchNo='+rows[0]['batchNo']+'&token='+data['token']);
			},function(response_info){
				console.log(response_info);
			});
		}
	}
}]);

/**
 * 奖金发放文件审核controller
 */
FolController.controller('BonusIssueVersionFileAuditController',['$scope','$timeout','$q','$window','BonusIssueVersionFileAuditService','CodeService',function($scope,$timeout,$q,$window,BonusIssueVersionFileAuditService,CodeService){
	$scope.istableshow=false;
	/**
	 * 数据初始化
	 */
	$timeout(function(){
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
				
				BonusIssueVersionFileAuditService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
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
		
		var fields=['渠道类型','奖金类型'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
			
			var typesId=['#dealerType','#bonusType'];
			
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
	 * 奖金发放文件查询
	 */
	$scope.query=function(){
		$scope.istableshow=true;
		
		$scope.query_data={
				dealerType:folUtil.getComboBoxDataById('dealerType'),
				bonusType:folUtil.getComboBoxDataById('bonusType'),
				beginDate:$('#startTime').datebox('getValue'),
				endDate:$('#endTime').datebox('getValue'),
				batchNo:$scope.batchNo,
				matchState:STATE.REAST_AUDIT,
				type:TYPE.BONUS_AUDIT,
				beginNo:0,
				endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BonusIssueVersionFileAuditService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
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
	 * 奖金发放文件审核通过
	 */
	$scope.audit=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length<1){
			$.messager.alert('提示', '请选择一条记录再进行操作');
			return;
		}
		
		BonusIssueVersionFileAuditService.audit(rows,'bonus/bonusissue/bonusAudit',function(response_info){
			defferred.resolve('success');
		},function(response_info){
			defferred.reject('fail');
		});
		
		
		promise.then(function(){
			$.messager.alert('提示','奖金发放文件审核成功');
			BonusIssueVersionFileAuditService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
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
		},function(){
			$.messager.alert('提示','奖金发放文件审核失败');
		});
	};
	
	/**
	 * 奖金发放文件驳回
	 */
	$scope.reject=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var rows=$('#datagrid').datagrid('getSelections');
//		
		if(rows.length<1){
			$.messager.alert('提示', '请选择一条记录再进行操作');
			return;
		}
		
		BonusIssueVersionFileAuditService.reject(rows,'bonus/bonusissue/bonusReject',function(data,response_info){
			defferred.resolve('success');
		},function(response_info){
			defferred.reject('fail');
		});
		
		promise.then(function(){
			$.messager.alert('提示','奖金发放文件驳回成功');
			BonusIssueVersionFileAuditService.query($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
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
		},function(){
			$.messager.alert('提示','奖金发放文件驳回失败');
		});
	};
	
	//导出
	$scope.exportFile=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		if(rows.length<1){
			$.messager.alert('提示', '请选择一个奖金记录再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多个奖金记录进行操作');
			return;
		}else{
			BonusIssueVersionFileAuditService.getToken('services/tokens',function(data){
				$.messager.progress('close');
				$window.open('bonus/bonusissue/exportFile?issueState='+STATE.FAIL_ISSUE+'&batchNo='+rows[0]['batchNo']+'&token='+data['token']);
			},function(response_info){
				console.log(response_info);
			});
		}
	};
}]);
/**
 * 奖金发放版本上传controller
 */
FolController.controller('BonusIssueVersionUploadController',['$rootScope','$scope','$state','$timeout','$q','$window','BonusIssueVersionUploadService','CodeService',function($rootScope,$scope,$state,$timeout,$q,$window,BonusIssueVersionUploadService,CodeService){
	/**
	 * 数据初始化
	 */
	$scope.istableshow=false;
	
	$timeout(function(){
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
				
				BonusIssueVersionUploadService.issueVersionQuery($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
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
		
		var fields=['渠道类型','奖金类型'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
				
			var typesId=['#dealerType','#bonusType'];
				
			$timeout(function(){
	//				$('#bonusType').combobox('loadData',$rootScope.bonusType);
	//				$('#bonusType').combobox('select',$rootScope.bonusType[0]['code']);
				
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
	 * 发放版本查询
	 */
	$scope.issueVersionQuery=function(){
		$scope.istableshow=true;
		
		$scope.query_data={
			dealerType:folUtil.getComboBoxDataById('dealerType'),
			bonusType:folUtil.getComboBoxDataById('bonusType'),
			beginDate:$('#startTime').datebox('getValue'),
			endDate:$('#endTime').datebox('getValue'),
			batchNo:$scope.batchNo,
			matchState:STATE.WAIT_AUDIT,
			type:TYPE.BONUS_UPLOAD,
			beginNo:0,
			endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BonusIssueVersionUploadService.issueVersionQuery($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
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
	 * 发放版本重新上传
	 */
	$scope.issueVersionReUpload=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		if(rows.length>1){
			$.messager.alert('提示','不能选择多条记录重新上传');
		}else if(rows.length<1){
			$.messager.alert('提示','请选择一条记录重新上传');
		}else{
			var data=new Array();
			$.each(rows[0],function(i,val){
				if(!folUtil.isNull(val)&&!folUtil.isNull(val['publishDate'])){
					val['publishDate']=null;
				}
				data.push(val);
			});
			$state.go('uploadFilePage',{pageName:'bonusIssueVersion',data:data});
		}
	};
	
	/**
	 * 作废
	 */
	$scope.cancel=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length<1){
			$.messager.alert('提示','请选择一条记录再进行作废操作');
		}else{
			var defferred=$q.defer();
			var promise=defferred.promise;
			
			BonusIssueVersionUploadService.cancel(rows,'bonus/bonusissue/cancalBonusFile',function(data,response_info){
				defferred.resolve('success');
			},function(data,response_info){
				defferred.resvole('reject');
			});
			
			promise.then(function() {
				$.messager.alert('提示', '发放文件作废成功');
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				BonusIssueVersionUploadService.issueVersionQuery($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
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
			},function(){
				$.messager.alert('提示', '发放文件作废失败');
			});
			
		}
	};
	
	/**
	 * 确认需要审核
	 */
	$scope.confirmAudit=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length<1){
			$.messager.alert('提示','请选择一条记录再进行作废操作');
		}else{
			var defferred=$q.defer();
			var promise=defferred.promise;
			
			BonusIssueVersionUploadService.confirmAudit(rows,'bonus/bonusissue/confirmAudit',function(data,response_info){
				defferred.resolve('success');
			},function(response_info){
				defferred.resvole('reject');
			});
			
			promise.then(function() {
				$.messager.alert('提示', '发放文件已确认需要审核');
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				BonusIssueVersionUploadService.issueVersionQuery($scope.query_data,'bonus/bonusissue/query',function(data,response_info){
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
			},function(){
				$.messager.alert('提示', '发放文件确认需要审核失败');
			});
			
		}
	};
	
	/**
	 * 下载模板
	 */
	$scope.downloadTemp=function(){
		
		$.messager.progress({
			title:'Please waiting',
			msg:'downloading template...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BonusIssueVersionUploadService.getToken('services/tokens',function(data){
			$.messager.progress('close');
			var url='bonus/bonusissue/downloadTemp?token='+data['token'];
			console.log(url);
			$window.open(url);
		},function(response_info){
			$.messager.progress('close');
			console.log(response_info);
		});
	};
	
	/**
	 * 导入文件
	 */
	$scope.uploadBonusIssueVersion=function(){
		$state.go('uploadFilePage',{pageName:'bonusIssueVersion'});
	};
	
	//导出
	$scope.exportFile=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		if(rows.length<1){
			$.messager.alert('提示', '请选择一个奖金记录再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多个奖金记录进行操作');
			return;
		}else{
			BonusIssueVersionUploadService.getToken('services/tokens',function(data){
				$.messager.progress('close');
				$window.open('bonus/bonusissue/exportFile?issueState='+STATE.WAIT_ISSUE+'&batchNo='+rows[0]['batchNo']+'&token='+data['token']);
			},function(response_info){
				console.log(response_info);
			});
		}
	};
}]);
/**
 * 奖金重处理
 */
FolController.controller('BonusReastDisposeController',['$scope','$timeout','$q','BonusReastDisposeService','CodeService',function($scope,$timeout,$q,BonusReastDisposeService,CodeService){
	$scope.istableshow=false;
	/**
	 * 数据初始化
	 */
	$timeout(function(){
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
				
				BonusReastDisposeService.query($scope.query_data,'bonus/bonusissue/queryBonusIssueFail',function(data,response_info){
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
		
		var fields=['奖金类型','销售公司类型'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
			var typesId=['#bonusType','#serv'];
			
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
	 * 重处理的奖金查询
	 */
	$scope.query=function(){
		$scope.istableshow=true;
		
		$scope.query_data={
				bonusType:folUtil.getComboBoxDataById('bonusType'),
				serv:folUtil.getComboBoxDataById('serv'),
				batchNo:$scope.batchNo,
				sapCode:$scope.sapCode,
				beginDate:$('#startTime').datebox('getValue'),
				endDate:$('#endTime').datebox('getValue'),
				issueStatus:STATE.FAIL_ISSUE,
				type:TYPE.BONUS_ISSUE,
				beginNo:0,
				endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BonusReastDisposeService.query($scope.query_data,'bonus/bonusissue/queryBonusIssueFail',function(data,response_info){
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
	 * 奖金重处理
	 */
	$scope.reastDispose=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var rows=$('#datagrid').datagrid('getSelections');
		if(rows.length<1){
			$.messager.alert('提示','请选择相应的记录再操作');
			return;
		}
		
		BonusReastDisposeService.reastDispose(rows,'bonus/bonusissue/payAgainBonus',function(data,response_info){
			defferred.resolve('success');
		},function(response_info){			
			defferred.reject('fail');
		});
		
		promise.then(function(res){
			$.messager.alert('提示', '奖金重处理成功');
			
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			BonusReastDisposeService.query($scope.query_data,'bonus/bonusissue/queryBonusIssueFail',function(data,response_info){
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
			$.messager.alert('提示', '奖金重处理失败');
			
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			BonusReastDisposeService.query($scope.query_data,'bonus/bonusissue/queryBonusIssueFail',function(data,response_info){
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
		});
	}
	
	$scope.back=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var rows=$('#datagrid').datagrid('getSelections');
//		
		if(rows.length<1){
			$.messager.alert('提示', '请选择一条记录再进行操作');
			return;
		}
		
		BonusReastDisposeService.reject(rows,'bonus/bonusissue/bonusReject',function(data,response_info){
			defferred.resolve('success');
		},function(response_info){
			defferred.reject('fail');
		});
		
		promise.then(function(){
			$.messager.alert('提示','奖金发放文件驳回成功');
			BonusReastDisposeService.query($scope.query_data,'bonus/bonusissue/queryBonusIssueFail',function(data,response_info){
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
		},function(){
			$.messager.alert('提示','奖金发放文件驳回失败');
		});
	}
	
	//导出
	$scope.exportFile=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		if(rows.length<1){
			$.messager.alert('提示', '请选择一个奖金记录再进行操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多个奖金记录进行操作');
			return;
		}else{
			BonusReastDisposeService.getToken('services/tokens',function(data){
				$.messager.progress('close');
				$window.open('bonus/bonusissue/exportFile?issueState='+STATE.FAIL_ISSUE+'&batchNo='+rows[0]['batchNo']+'&token='+data['token']);
			},function(response_info){
				console.log(response_info);
			});
		}
	};
	
}])