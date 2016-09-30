/**
 * 金额异常处理controller
 */
FolController.controller('AmountExceptionHandingController',['$scope','AmountExceptionHandingService','$state','$timeout',function($scope,AmountExceptionHandingService,$state,$timeout){
	$scope.istableshow=false;
	
	$timeout(function(){
		if($('#datagrid').length>0){

			var datagrid=$('#datagrid').datagrid('getPager');
			
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){				
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					AmountExceptionHandingService.findDealerInfo($scope.query_data,'dealerInfo/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagrid').datagrid('loadData',{total:response_info.total,rows:data});
					},function(data,response_info){
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
	});
	
	
	$scope.findDealerInfo=function(){
		$scope.query_data={				
				sapCode:$('#sapCode').textbox('getValue'),
//				dealerName:$('#dealerName').textbox('getValue'),
				beginNo:0,
				endNo:10
		};

		$scope.istableshow=true;
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		AmountExceptionHandingService.findDealerInfo($scope.query_data,'dealerInfo/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:response_info.total,rows:data});
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}else{
				$.messager.alert('提示',response_info.message);
			}
			console.log("error:"+response_info);
		});
	};
	
	$scope.amountExHanding=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length<=0){
			$.messager.alert('提示', '请选择一条经销商记录，再进行操作');
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多条经销商记录，再进行操作');
		}else{
			$state.go('amountExHanding',{dealerInfo:rows[0]});
		}
	};
}]);

FolController.controller('addAmtExHandingController',['$scope','$state','$stateParams','$timeout','CodeService','AmountExceptionHandingService',function($scope,$state,$stateParams,$timeout,CodeService,AmountExceptionHandingService){
	$scope.dealerInfo=$stateParams.dealerInfo;

	$timeout(function(){
		$('#sapCode').textbox('setValue',$scope.dealerInfo.sapCode);
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		var fields=['变动类型'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
			var typesId=['#changeType'];
			
			$timeout(function(){
				for(var i=0;i<data.length;i++){
					if(!folUtil.isNull(data[i])){
						var changeType=new Array();
						$.each(data[i],function(i,val){
							if(val['code'].indexOf('-1')>=0||
									val['code'].indexOf(TYPE.RESERVE_EX_HANDING_ADD_AMOUNT.toString())>=0||
									val['code'].indexOf(TYPE.RESERVE_EX_HANDING_SUBSTACT_AMOUNT.toString())>=0||
									val['code'].indexOf(TYPE.BONUS_EX_HANDING_ADD_AMOUNT.toString())>=0||
									val['code'].indexOf(TYPE.BONUS_EX_HANDING_SUBSTACT_AMOUNT.toString())>=0){
								changeType.push(val);
							}
						});
						$(typesId[i]).combobox('loadData',changeType);
						$(typesId[i]).combobox('select',changeType[0]['code']);
					}
				}
			});
		},function(response_info){
			$.messager.progress('close');
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			console.log("error:"+response_info);
		});
	});
	
	
	$scope.opertion=function(){
		if(!check()){
			return;
		}
		
		var obj={
			sapCode:$scope.dealerInfo.sapCode,
			amount:$('#changeAmount').numberbox('getValue'),
			disposeNo:$('#disposeNo').textbox('getValue'),
			disposeDesc:$('#disposeDesc').textbox('getValue'),
			referType:folUtil.getComboBoxDataById('changeType'),
			referCode:$('#disposeNo').textbox('getValue')
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});
		
		AmountExceptionHandingService.amountExHanding(obj,'exceptionhanding/amount',function(data,response_info){
			$.messager.progress('close');
			$.messager.alert('提示', '金额调整成功')
			$state.go('exceptionHanding');
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}else{
				$.messager.alert('提示',response_info.message);
			}
		});
	}
	
	function check(){
		if(folUtil.getComboBoxDataById('changeType')<0){
			$.messager.alert('提示','请选择变动类型');
			return false;
		}else if(folUtil.isNull($('#disposeNo').textbox('getValue'))){
			$.messager.alert('提示','请填写处理号');
			return false;
		}else if(folUtil.isNull($('#disposeDesc').textbox('getValue'))){
			$.messager.alert('提示','请填写处理描述');
			return false;
		}else if(folUtil.isNull($('#changeAmount').numberbox('getValue'))){
			$.messager.alert('提示','请填写变动金额');
			return false;
		}
		return true;
	}
}]);