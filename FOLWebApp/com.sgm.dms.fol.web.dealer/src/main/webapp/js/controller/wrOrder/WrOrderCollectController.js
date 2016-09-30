/**
 * 索赔月度财务汇总Controller
 * @author wangfl
 */
FolController.controller('WrOrderCollectController',['$scope','$rootScope','$q','$timeout','WrOrderCollectService','$window','CodeService',function($scope,$rootScope,$q,$timeout,WrOrderCollectService,$window,CodeService){
	$scope.istableshow=false;
	
	$timeout(function(){
		//初始化页面下拉框
		var BatchArray=new Array();
		BatchArray.push({value:'',displayName:'请选择'});
		BatchArray.push({value:'01',displayName:'第一批次'});
		BatchArray.push({value:'02',displayName:'第二批次'});
		$('#startWrBatch').combobox('loadData',BatchArray);
		$('#startWrBatch').combobox('select',BatchArray[0]['value']);
		
		$('#endWrBatch').combobox('loadData',BatchArray);
		$('#endWrBatch').combobox('select',BatchArray[0]['value']);
		
		var fields=['索赔类型'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
			
//			$timeout(function(){
				if(data.length>0){
					$('#wrType').combobox('loadData',data[0]);
				}
//			});
			
		},function(response_info){
			$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			console.log("error:"+response_info);
		});

		//初始化开始结束年月
		folUtil.setComboBoxYearDataById('startyear');
		folUtil.setComboBoxMonthDataById('startmonth');
		
		folUtil.setComboBoxYearDataById('endyear');
		folUtil.setComboBoxMonthDataById('endmonth');
    });
	
	var selectpage=function(){
		if($('#wrOrderDataGrid').length>0){
			var wrOrderDataGrid=$('#wrOrderDataGrid').datagrid('getPager');
			wrOrderDataGrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo = pageSize*(pageNumber-1)<=0 ? 0 : pageSize * (pageNumber - 1) + 1;
					$scope.query_data.endNo = (pageSize * pageNumber)<=0?10:(pageSize * pageNumber);
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					WrOrderCollectService.query($scope.query_data,'invoice/wrCollect/wrOrders',function(data,response_info){
						$.messager.progress('close');
						$('#wrOrderDataGrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
						console.log("error:"+data);
		
					});
				}
			});
		}
	}

	//查询
	$scope.query=function(){
		$scope.istableshow= true;
		
		var startTime=folUtil.getComboBoxDataById('startyear')+folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('startmonth'));
		var endTime=folUtil.getComboBoxDataById('endyear')+folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('endmonth'));
		
		//条件检查
		if(startTime != "" && endTime != "" ){
			if((startTime > endTime) || (startTime == endTime && folUtil.getComboBoxDataById('startWrBatch') > folUtil.getComboBoxDataById('endWrBatch'))){
				$.messager.alert('提示',"开始月-批次不能大于结束月-批次");
				return;
			}
		}

		$scope.query_data={
				//startWrDate:$('#startWrDate').textbox('getValue').substring(0,7),
				startWrBatch:startTime != "" ? startTime + (folUtil.getComboBoxDataById('startWrBatch') != "" ? folUtil.getComboBoxDataById('startWrBatch')  : "01") : "",					
				//endWrDate:$('#endWrDate').textbox('getValue').substring(0,7),
				endWrBatch:endTime != "" ? endTime + (folUtil.getComboBoxDataById('endWrBatch') != "" ? folUtil.getComboBoxDataById('endWrBatch')  : "02") : "",
				wrType:folUtil.getComboBoxDatasById('wrType'),
				beginNo:0,
				endNo:10
		};
			
			
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					WrOrderCollectService.query($scope.query_data,'invoice/wrCollect/wrOrders',function(data,response_info){
						$.messager.progress('close');
						$('#wrOrderDataGrid').datagrid('loadData',data);
						selectpage();
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
						console.log("error:"+response_info);
					});		
					
					
		};
	//汇总
	$scope.collect=function(){
		
		var rows=$('#wrOrderDataGrid').datagrid('getChecked');
		
		/*if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}*/
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		WrOrderCollectService.checkdata(rows,'invoice/wrCollect/collect/getSign',function(data,response_info){
			console.log(1);
			WrOrderCollectService.collect(data.rows,'invoice/wrCollect/collect',function(data,response_info){
				$.messager.progress('close');
				$.messager.alert('提示','汇总成功');
				WrOrderCollectService.query($scope.query_data,'invoice/wrCollect/wrOrders',function(data,response_info){
					$.messager.progress('close');
					$('#wrOrderDataGrid').datagrid('loadData',data);
					selectpage();
				},function(response_info){
					$.messager.progress('close');
					if(response_info.state===STATE.TIMEOUT){
						console.log('timeout');
						$.messager.alert('提示','系统超时请稍后访问');
					}
					console.log("error:"+response_info);
				});
			},function(response_info){
				$.messager.progress('close');
				$.messager.alert('提示',response_info.message);
			});	
		},function(response_info){
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};
		//导出excel
	$scope.exportExcel=function(){
					
		WrOrderCollectService.getToken('services/tokens',function(data,response_info){
			if(folUtil.isNull($scope.query_data)){
				$.messager.alert('提示','请先查询一次再导出');
				return;
			}
			$.messager.progress('close');
			
			var wrTypeTemp = new Array();
			if($scope.query_data.wrType != null && $scope.query_data.wrType.length > 0){
				for(var index in  $scope.query_data.wrType){
					if($scope.query_data.wrType[index] != "" && $scope.query_data.wrType[index] != "-1"){
						wrTypeTemp.push($scope.query_data.wrType[index]);
					}
				}
			}
			
			$window.open('invoice/wrCollect/exportExcel?startWrDate='+$scope.query_data.startWrDate+'&endWrDate='+$scope.query_data.endWrDate+'&wrType='+wrTypeTemp+'&startWrBatch='+$scope.query_data.startWrBatch+'&endWrBatch='+$scope.query_data.endWrBatch+'&token='+data['token']);
		},function(data,response_info){
			console.log(data);
		});
	};	


}]);
