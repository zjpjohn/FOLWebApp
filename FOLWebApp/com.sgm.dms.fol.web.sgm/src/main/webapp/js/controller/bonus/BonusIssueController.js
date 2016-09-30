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
