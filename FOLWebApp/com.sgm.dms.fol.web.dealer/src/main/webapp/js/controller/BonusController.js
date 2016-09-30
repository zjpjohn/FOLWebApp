/**
 * 奖金月度报表
 */
FolController.controller('bonusMonthFormCtrl',['$scope','$rootScope','$q','$timeout','BonusService','$window',function($scope,$rootScope,$q,$timeout,BonusService,$window){
	
	$scope.istableshow=false;
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
				BonusService.findBonusAmount($scope.bonus_data,'bonus/bonusMonthForm/query',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
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
});
	
	$scope.findBonusMonthForm=function(){
		var defferred=$q.defer();
		var promise=defferred.promise;
		
		var changeReserveAmountResult=false,freezeReserveAmountResult=false;
		if($('#startTime').datebox('getText')>$('#endTime').datebox('getText')){
			$.messager.alert('提示','开始时间不能大于结束时间');
			return;
		}
		$scope.bonus_data={
				//changeTime:changeTime,
				startTime:$('#startTime').datebox('getValue'),
				endTime:$('#endTime').datebox('getValue'),

				beginNo:0,
				endNo:10
			};
					$scope.istableshow=true;
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					BonusService.findBonusMonthForm($scope.bonus_data,'bonus/bonusMonthForm/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagrid').datagrid('loadData',{total:0,rows:[]});
						$('#datagrid').datagrid('loadData',data);
					},function(data,response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
						console.log(response_info)
						console.log("error:"+data);
					});
					
			
				};	

				
				
	// 文件导出
	$scope.bonusMonthForm=function(){
			
		BonusService.getToken('services/tokens',function(data,response_info){
				if(folUtil.isNull($scope.bonus_data)){
					$.messager.alert('提示','请先查询一次再导出');
					return;
				}
				
				$.messager.progress('close');
				$window.open('bonus/bonusMonthForm/bonusMonthForm?startTime='+$scope.bonus_data.startTime+'&endTime='+$scope.bonus_data.endTime+'&token='+data['token']);
			},function(data,response_info){
				console.log(data);
			});
		};
}]);