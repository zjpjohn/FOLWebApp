/**
 * FOL-SAP接口状态查询
 */
FolController.controller('InterfaceStatusController',['$scope','$rootScope','$q','$timeout','InterfaceStatusService','CodeService','$window',function($scope,$rootScope,$q,$timeout,InterfaceStatusService,CodeService,$window){
	
	$scope.istableshow=false;
	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){

				$scope.bill_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
				$scope.bill_data.endNo=pageSize*pageNumber;
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				InterfaceStatusService.query($scope.bill_data,'interface/status/query',function(data,response_info){
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
		var fields=['SGM受理状态'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
			
			var typesId=['#process_status'];
			
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

		//查询
	$scope.query=function(){
			$scope.bill_data={
					invoiceNo:$('#invoiceNo').textbox('getValue'),
					process_status:folUtil.getComboBoxDataById('process_status'),
					startTime:$('#startTime').datebox('getValue'),
					endTime:$('#endTime').datebox('getValue'),
					beginNo:0,
					endNo:10
				};
			if($('#startTime').datebox('getValue') !=null 
					&& $('#endTime').datebox('getValue')!=null
					&& $('#startTime').datebox('getValue')!=''
					&& $('#endTime').datebox('getValue')!=''){
				
				if($('#startTime').datebox('getValue')>$('#endTime').datebox('getValue')){
					$.messager.alert('提示','开始时间不能大于结束时间');
					return;
				}
				
			}
			$scope.istableshow=true;
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					InterfaceStatusService.query($scope.bill_data,'interface/status/query',function(data,response_info){
						$.messager.progress('close');
						$('#datagrid').datagrid('loadData',{total:0,rows:[]});
						$('#datagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
						console.log("error:"+response_info);
					});		
				};

}]);
