/**
 * 客户对账单倒入controller
 */
FolController.controller('DealerReconciliationImportController',['$scope','$state','$timeout','$window','DealerReconciliationImportService',function($scope,$state,$timeout,$window,DealerReconciliationImportService){
	$scope.istableshow=false;
	$scope.isdeatiltableshow=false;
	
	$timeout(function(){
		folUtil.setComboBoxYearDataById('year');
		folUtil.setComboBoxMonthDataById('month');

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
				
				DealerReconciliationImportService.query($scope.query_data,'reconciliation/billFile/queryBillFile',function(data,response_info){
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
	
	//对账单查询
	$scope.query=function(){
		$scope.istableshow=true;
		var queryTime=folUtil.getComboBoxDataById('year')+'-'+folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month'));
		
		$scope.$apply(function(){
			$scope.query_data={
					currentDate:queryTime,
					year:folUtil.getComboBoxDataById('year'),
					month:folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month')),
					beginNo:0,
					endNo:10
			};
		});
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		DealerReconciliationImportService.query($scope.query_data,'reconciliation/billFile/queryBillFile',function(data,response_info){
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
	
	//文件上传功能
	$scope.checkIn=function(){
		$state.go('uploadFilePage',{pageName:'bill'});
	};
	
	//数据导出功能
	$scope.checkOut=function(){
		if(folUtil.isNull($scope.query_data)){
			$.messager.alert('提示', '先查询一次再导出');
			return;
		}
		var queryTime=$scope.query_data.year+'-'+$scope.query_data.month;
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		//文件导出
		DealerReconciliationImportService.getToken('services/tokens',function(data){
			$.messager.progress('close');
			$window.open('reconciliation/billFile/exportBillFile?currentDate='+queryTime+'&token='+data['token']);

		},function(response_info){
			$.messager.progress('close');
			$.messager.alert('提示','系统错误或者超时，请刷新重试');
		});
	};
	
	//总对账单状态修改已发布
	$scope.reconciliationStatusUpdateWithPublish=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		var flag=false;
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择需要发布的记录，再去操作发布');
			return;
		}
		
		$.each(rows,function(i,val){
			if(val.status==STATE.PUBLISHED){
				$.messager.alert('提示','该文件已发布不能再发布');
				flag=true;
				return;
			}
		});
		
		if(flag){
			return;
		}else{
			DealerReconciliationImportService.getToken('services/tokens',function(data){
				$.each(rows,function(i,val){
					val.token=data['token'];
				});
				
				DealerReconciliationImportService.reconciliationStatusUpdate(rows,'reconciliation/billFile/publishFileStatus',function(data,response_info){
					$.messager.alert('提示','发布进行中，请稍后查看');
					DealerReconciliationImportService.query($scope.query_data,'reconciliation/billFile/queryBillFile',function(data,response_info){
						$('#datagrid').datagrid('loadData',{total:0,rows:[]});
						$('#datagrid').datagrid('loadData',data);
					},function(data){
						console.log(data);
					});
					
					DealerReconciliationImportService.reconciliationStatusUpdateWithPublish(rows,'reconciliation/billFile/publishFile',function(data,response_info){
					DealerReconciliationImportService.query($scope.query_data,'reconciliation/billFile/queryBillFile',function(data,response_info){
						$('#datagrid').datagrid('loadData',{total:0,rows:[]});
						$('#datagrid').datagrid('loadData',data);
					},function(data){
						console.log(data);
					});
					},function(data,response_info){
						/*$.messager.alert('提示','发布失败');
					console.log(data);*/
					});
				},function(data,response_info){
					$.messager.alert('提示','发布失败');
					console.log(data);
				});
			},function(response_info){
				console.log(response_info);
			});
			
		}
	};

	//打开明细界面
	$scope.findReconciliationDeatil=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length>0){
			if(rows[0].status===STATE.UN_PUBLISH || rows[0].status===STATE.PUBLISHING){
				$.messager.alert("提示", "该文件未发布不能查看明细");
				return;
			}
			$timeout(function(){
				var deatildatagrid=$('#deatildatagrid').datagrid('getPager');
				deatildatagrid.pagination({
					onSelectPage:function(pageNumber,pageSize){
						$scope.query_params.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
						$scope.query_params.endNo=pageSize*pageNumber;
							
						$.messager.progress({
							title:'Please waiting',
							msg:'Loading data...',
							interval:PROGRESS_ACTION_TIMEOUT
						});
						
						DealerReconciliationImportService.ReconciliationSignQuery($scope.query_data,'reconciliation/billFile/queryBillFileDetail',function(data,response_info){
							$.messager.progress('close');
							$('#deatildatagrid').datagrid('loadData',data);
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
			console.log('year1:'+$scope.query_data.year);
			$('#DeatilWindow').window('open');
			
		}else if(rows.length>1){
			$.messager.alert("提示", "选择多条文件，不能查看明细，请选择单条文件");
		}else{
			$.messager.alert("提示", "请选择文件才能查看明细");
		}
	};
	
}]);

/**
 * 客户对账单明细controller
 */
FolController.controller('DealerReconciliationDeatilController',['$scope','$state','$timeout','$window','DealerReconciliationImportService',function($scope,$state,$timeout,$window,DealerReconciliationImportService){
	$timeout(function(){
		$('#DeatilWindow').panel({
			onBeforeOpen:function(){
				$('#deatilistable').attr('class','row-fluid ng-hide');
				$('#dealerCode').textbox('setValue','');
				$('#dealerName').textbox('setValue','');
				$('#deatilYear').textbox('setValue',$scope.query_data.year);
				$('#deatilMonth').textbox('setValue',$scope.query_data.month);
			}
		});
		
		var deatildatagrid=$('#deatildatagrid').datagrid('getPager');
		deatildatagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.query_params.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.query_params.endNo=pageSize*pageNumber;
					
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});
				
				DealerReconciliationImportService.ReconciliationSignQuery($scope.query_data,'reconciliation/billFile/queryBillFileDetail',function(data,response_info){
					$.messager.progress('close');
					$('#deatildatagrid').datagrid('loadData',data);
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

	//客户对账单明细查询
	$scope.ReconciliationSignQuery=function(){
		console.log($scope.query_data);
//		var currentTime=$scope.query_data.year+'-'+$scope.query_data.month;
		var currentTime=$('#deatilYear').textbox('getValue')+'-'+$('#deatilMonth').textbox('getValue');

		$scope.$apply(function(){
			$scope.query_params={
					currentDate:currentTime,
					dealerCode:folUtil.isNull($scope.dealerCode)?'':$scope.dealerCode,
					dealerName:folUtil.isNull($scope.dealerName)?'':$scope.dealerName,
					beginNo:0,
					endNo:10
				};
		});

		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		//查询客户对账单
		DealerReconciliationImportService.ReconciliationSignQuery($scope.query_params,'reconciliation/billFile/queryBillFileDetail',function(data,response_info){
			$.messager.progress('close');
			
			$('#deatilistable').attr('class','row-fluid ng-show');
			console.log($('#deatilistable').attr('class'));
			
			$('#deatildatagrid').datagrid('loadData',{total:0,rows:[]});
			$('#deatildatagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});

	};
	
	/**
	 * 对账单明细导出
	 * @Author wangfl
	 */
	$scope.exportRecDeatil = function(){
		var rows=$('#deatildatagrid').datagrid('getSelections');
		
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
		//alert(JSON.stringify(rows[0]));
		DealerReconciliationImportService.getToken('services/tokens',function(data){
			$.messager.progress('close');
			$window.open('reconciliation/billFile/exportBillFileDetailSingle?filedId='+rows[0]['filedId']+'&token='+data['token']);
		},function(data){
			console.log(data);
		});
	};
	
	//客户对账单明细导出
	$scope.checkOutDeatil=function(){
		var queryTime=$scope.query_data.year+'-'+$scope.query_data.month;
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		DealerReconciliationImportService.getToken('services/tokens',function(data){
			$.messager.progress('close');
			console.log('reconciliation/billFile/exportBillFileDetail?dealerCode='+$scope.query_params.dealerCode+'&dealerName='+$scope.query_params.dealerName+'&currentDate='+queryTime+'&token='+data['token']);
			$window.open('reconciliation/billFile/exportBillFileDetail?dealerCode='+$scope.query_params.dealerCode+'&dealerName='+$scope.query_params.dealerName+'&currentDate='+queryTime+'&token='+data['token']);
		},function(response_info){
			$.messager.progress('close');
			$.messager.alert('提示','系统错误或者超时，请刷新重试');
		});
	};
}]);
