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