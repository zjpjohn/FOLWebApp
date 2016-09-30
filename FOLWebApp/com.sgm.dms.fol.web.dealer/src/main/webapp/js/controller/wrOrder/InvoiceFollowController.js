/**
 * 索赔单发票跟踪
 */
FolController.controller('InvoiceFollowController',['$scope','$rootScope','$q','$timeout','InvoiceFollowService','CodeService','$window',function($scope,$rootScope,$q,$timeout,InvoiceFollowService,CodeService,$window){
	function pagination(){
		var datagrid=$('#datagrid').datagrid('getPager');

		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){

				$scope.bill_data.beginNo=pageSize*(pageNumber-1)<=0?0:pageSize*(pageNumber-1)+1;
				$scope.bill_data.endNo=(pageSize * pageNumber)<=0?10:(pageSize * pageNumber);
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				InvoiceFollowService.query($scope.bill_data,'wrOrder/invoiceFollow/query',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
					console.log("error:"+response_info);
	
				});
			}
		});
	}
	
	$scope.istableshow=false;
	
	$timeout(function(){
		var fields=['SGM受理状态'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
			
			var typesId=['#status'];
			console.log(data);
			$timeout(function(){
				for(var i=0;i<typesId.length;i++){
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
			console.log('error'+response_info);
		});

	});
	
	//查询
	$scope.query=function(){
		
		$scope.istableshow=true;

			$scope.bill_data={
					invoiceNo:$('#invoiceNo').textbox('getValue'),
					expressNo:$('#expressNo').textbox('getValue'),
					status:folUtil.getComboBoxDataById('status'),
					beginNo:0,
					endNo:10
				};
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					InvoiceFollowService.query($scope.bill_data,'wrOrder/invoiceFollow/query',function(data,response_info){
						$.messager.progress('close');
					    $('#datagrid').datagrid('loadData',{total:0,rows:[]});
						$('#datagrid').datagrid('loadData',data);
						pagination();
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
						
						console.log("error:"+response_info);
					});	
				};

	//重做
	$scope.anew=function(){
		var canceller=$q.defer();
		var promise=canceller.promise;
		var rows=$('#datagrid').datagrid('getChecked');
		var flag=false;

		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}
		
		$.each(rows,function(index,value){
			console.log(value);
			if(value.status!==CLAIM_STATUS.WR_ORDER_INVOICE_SGM_RETURN){
				$.messager.alert('提示',value.invoiceNo+'的发票号状态不是SGM已退回，不能进行重做操作');
				flag=true;
				return;
			}
		});
		
		if(flag){
			return;
		}

//		var datas= [];
//		$.each(rows,function(i,val){
//			datas.push({invoiceNo:val['invoiceNo'],status:val['status'],sign:val['sign']});
//		});
	
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		$scope.bill_data={
				status:folUtil.getComboBoxDataById('status'),		
				beginNo:0,
				endNo:10
		};

		canceller.resolve(rows);

		promise.then(function(data){
			console.log(data);
			InvoiceFollowService.anew(data,'wrOrder/invoiceFollow/anew',function(data,response_info){
				$.messager.alert('提示','重做成功');
				$scope.query();
			},function(response_info){
				$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
					return;
				}
				$.messager.alert('提示','重做失败请检查原因');
			});	
		});
	};
		//作废
//	$scope.cancel=function(){
//		var rows=$('#datagrid').datagrid('getChecked');
//
//		if(rows.length<=0){
//			$.messager.alert('提示','请选择至少一条记录，再进行操作');
//			return;
//		}else if(rows[0]['status'] !=3 ){
//			$.messager.alert('提示','当前状态不是已退回状态,不能给予操作');
//			return;
//		}
//		var datas= [];
//		$.each(rows,function(i,val){
//			datas.push({invoiceNo:val['invoiceNo'],status:val['status']});
//		});
//	
//		$.messager.progress({
//			title:'Please waiting',
//			msg:'Loading data...',
//			interval:PROGRESS_ACTION_TIMEOUT
//		});	
//		$scope.bill_data={
//				status:$scope.status,		
//				beginNo:0,
//				endNo:10
//		};
//
//		InvoiceFollowService.cancel(datas,'wrOrder/invoiceFollow/cancel',function(data,response_info){
//			$.messager.alert('提示','已作废');
//			InvoiceFollowService.query($scope.bill_data,'wrOrder/invoiceFollow/query',function(data,response_info){
//				$.messager.progress('close');
//				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
//				$('#datagrid').datagrid('loadData',data);
//			},function(response_info){
//				$.messager.progress('close');
//				if(response_info.state===STATE.TIMEOUT){
//					console.log('timeout');
//					$.messager.alert('提示','系统超时请稍后访问');
//				}else{
//					$.messager.alert('提示','作废失败');
//				}
//			});				
//		},function(response_info){
//			$.messager.progress('close');
//			if(response_info.state===STATE.TIMEOUT){
//				console.log('timeout');
//				$.messager.alert('提示','系统超时请稍后访问');
//				return;
//			}
//			$.messager.alert('提示','作废失败请检查原因');
//		});	
//				};	

	//打开明细界面
	$scope.deatil=function(){
				var rows=$('#datagrid').datagrid('getSelections');
				if(rows.length<=0){
					$.messager.alert('提示','选择一条记录，再操作');
					return
				}else if(rows.length>1){
					$.messager.alert('提示','只能一条记录，再操作');
					return
				}
				
				$rootScope.invoicefollow={};
				$rootScope.invoicefollow.invoiceFlowNum = rows[0]['flowNo'];
				$rootScope.invoicefollow.invoiceNo=rows[0]['invoiceNo'];
				
				$('#DeatilsWindow').window('open');
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				var data ={
						invoiceNo:$rootScope.invoicefollow.invoiceNo,
						invoiceFlowNum:$rootScope.invoicefollow.invoiceFlowNum 
				}
				InvoiceFollowService.queryDeatil(data,'wrOrder/invoiceFollow/deatil',function(data,response_info){
					$.messager.progress('close');
					$('#deatilsdatagrid').datagrid('loadData',{total:0,rows:[]});
					$('#deatilsdatagrid').datagrid('loadData',data);
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
					console.log("error:"+response_info);
				});
		};
		//文件导出
		$scope.exportBill=function(){
			var rows=$('#datagrid').datagrid('getSelections');
			InvoiceFollowService.getToken('services/tokens',function(data){
				if(folUtil.isNull($scope.bill_data)){
					$.messager.alert('提示','请先查询一次再导出');
					return;
				}else{
					if(rows.length<=0){
						$.messager.alert('提示','选择一条记录，再操作');
						return
					}else if(rows.length>1){
						$.messager.alert('提示','只能一条记录，再操作');
						return
					}
				}
				$scope.invoiceNo=rows[0]['invoiceNo'];
				$scope.bill_data ={
						invoiceNo:$scope.invoiceNo,
				}

				
				$.messager.progress('close');
				$window.open('wrOrder/invoiceFollow/invoiceFollowDeatilExp?invoiceNo='+$scope.bill_data.invoiceNo+'&token='+data['token']);
			},function(response_info){
				$.messager.progress('close');
				console.log(response_info);
			});
		};

}]);

FolController.controller('DeatilsController',['$scope','$rootScope','$timeout','$q','$state','InvoiceFollowService',function($scope,$rootScope,$timeout,$q,$state,InvoiceFollowService){

		$timeout(function(){		
			if($('#deatilsdatagrid').length>0){
				var datagrid=$('#deatilsdatagrid').datagrid('getPager');

				datagrid.pagination({
					onSelectPage:function(pageNumber,pageSize){

						$scope.bill_data.beginNo=pageSize*(pageNumber-1)<=10?0:pageSize*(pageNumber-1)+1;
						$scope.bill_data.endNo=pageSize*pageNumber;
						
						$.messager.progress({
							title:'Please waiting',
							msg:'Loading data...',
							interval:PROGRESS_ACTION_TIMEOUT
						});	
						
						var data ={
								invoiceNo:$rootScope.invoicefollow.invoiceNo,
								invoiceFlowNum:$rootScope.invoicefollow.invoiceFlowNum 
						}
						InvoiceFollowService.queryDeatil(data,'wrOrder/invoiceFollow/deatil',function(data,response_info){
							$.messager.progress('close');
							$('#deatilsdatagrid').datagrid('loadData',{total:0,rows:[]});
							$('#deatilsdatagrid').datagrid('loadData',data);
						},function(response_info){
							$.messager.progress('close');
							if(response_info.state===STATE.TIMEOUT){
								console.log('timeout');
								$.messager.alert('提示','系统超时请稍后访问');
							}
							console.log("error:"+response_info);
						});
					}
				});
			}
			
		});
//		$scope.deatil=function(){
//			var rows=$('#datagrid').datagrid('getSelections');
////			{invoiceNo:rows[0]['invoiceNo']}
//			$.messager.progress({
//				title:'Please waiting',
//				msg:'Loading data...',
//				interval:PROGRESS_ACTION_TIMEOUT
//			});	
//			InvoiceFollowService.queryDeatil(rows,'wrOrder/invoiceFollow/deatil',function(data,response_info){
//				$.messager.progress('close');
//				$('#deatilsdatagrid').datagrid('loadData',{total:0,rows:[]});
//				$('#deatilsdatagrid').datagrid('loadData',data);
//			},function(response_info){
//				$.messager.progress('close');
//				if(response_info.state===STATE.TIMEOUT){
//					console.log('timeout');
//					$.messager.alert('提示','系统超时请稍后访问');
//				}
//				console.log("error:"+response_info);
//			});
//		};

	}]);

