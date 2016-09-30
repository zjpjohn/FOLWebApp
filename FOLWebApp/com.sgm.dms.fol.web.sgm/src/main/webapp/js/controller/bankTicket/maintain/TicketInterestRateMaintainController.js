/**
 * 票据贴息年化利率维护controller
 */
FolController.controller('TicketInterestRateMaintainController',['$scope','$timeout','$state','$q','TicketInterestRateMaintainService',function($scope,$timeout,$state,$q,TicketInterestRateMaintainService){

	$timeout(function(){
		$('#datagrid').datagrid().datagrid('enableCellEditing');
		$('#datagridHis').datagrid().datagrid('enableCellEditing');
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
					
					TicketInterestRateMaintainService.queryCur($scope.query_data,'interestRate/maintain/queryCur',function(data,response_info){
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
		if($('#datagridHis').length>0){
			var datagrid=$('#datagridHis').datagrid('getPager');
			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){
					$scope.query_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
					$scope.query_data.endNo=pageSize*pageNumber;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					TicketInterestRateMaintainService.queryHis($scope.query_data,'interestRate/maintain/queryHis',function(data,response_info){
						$.messager.progress('close');
						$('#datagridHis').datagrid('loadData',data);
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

	
	$scope.query_data={
			auditStatus:$scope.auditStatus,		
			beginNo:0,
			endNo:10
	};
	TicketInterestRateMaintainService.queryCur($scope.query_data,'interestRate/maintain/queryCur',function(data,response_info){
		$.messager.progress('close');
		$('#datagrid').datagrid('loadData',data);
	},function(response_info){
		$.messager.progress('close');
		if(response_info.state===STATE.TIMEOUT){
			console.log('timeout');
			$.messager.alert('提示','系统超时请稍后访问');
		}
	});
	TicketInterestRateMaintainService.queryHis($scope.query_data,'interestRate/maintain/queryHis',function(data,response_info){
		$.messager.progress('close');
		$('#datagridHis').datagrid('loadData',{total:0,rows:[]});	
		$('#datagridHis').datagrid('loadData',data);
	},function(response_info){
		$.messager.progress('close');
		if(response_info.state===STATE.TIMEOUT){
			console.log('timeout');
			$.messager.alert('提示','系统超时请稍后访问');
		}
	});


	$scope.update=function(){
		var rows;
		rows=$('#datagrid').datagrid('getSelections');

		if(rows.length<=0){
			$.messager.alert('提示', '请选择最新一条记录进行修改');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示', '不能选择多条记录进行修改');
			return;			
		}else if(rows[0]['unAuditRateCur']!=null){
			$.messager.alert('提示','当前状态是待审核不能给予修改');
			return;
		}
		
		/**
		 * 新增修改记录
		 */
		$state.go('updateInterestRateHis');
	};
	}]);
/**
 * 新增修改年化利率记录
 */
FolController.controller('UpdateInterestRateController',['$scope','$timeout','$state','$q','TicketInterestRateMaintainService','CodeService',function($scope,$timeout,$state,$q,TicketInterestRateMaintainService,CodeService){
//	$timeout(function(){
//		$.messager.progress({
//			title:'Please waiting',
//			msg:'Loading data...',
//			interval:PROGRESS_ACTION_TIMEOUT
//		});		
//		
//	});

	/**
	 * 新增修改记录
	 */
	$scope.confirm=function(){
		var interestRate={
				annualInterestRate:$('#annualInterestRate').textbox('getValue')
		};
		
//		if(!check()){
//			return;
//		}
//		
		TicketInterestRateMaintainService.checkdata(interestRate,'interestRate/maintain/getSign',function(data,response_info){
			console.log(data);
		TicketInterestRateMaintainService.confirm(data,'interestRate/maintain/update',function(data,response_info){
			
			$.messager.alert('提示', '修改成功');
			$state.go('ticketInterestRateMaintain');
			
		},function(response_info){
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}else{
				$.messager.alert('提示', '修改失败');
			}
		});
		},function(response_info){
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});

	};
	
//	/**
//	 * 检查数据
//	 */
//	function check(){
//		if(folUtil.isNull($('#annualInterestRate').textbox('getValue'))){
//			$.messager.alert('提示','客户代码不能为空');
//			return false;
//		}
//		return true;
//	}
	
	/**
	 * 取消
	 */
	$scope.cancel=function(){
		$state.go('ticketInterestRateMaintain');
	};
}]);