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