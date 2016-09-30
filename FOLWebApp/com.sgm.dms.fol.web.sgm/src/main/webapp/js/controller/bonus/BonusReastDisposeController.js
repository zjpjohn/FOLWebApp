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