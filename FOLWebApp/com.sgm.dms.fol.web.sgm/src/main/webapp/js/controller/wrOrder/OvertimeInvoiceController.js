/**
 * 经销商提交超时未处理发票查询
 */
FolController.controller('OvertimeInvoiceController',['$scope','$rootScope','$q','$timeout','OvertimeInvoiceService','$window',function($scope,$rootScope,$q,$timeout,OvertimeInvoiceService,$window){
	
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
				OvertimeInvoiceService.query($scope.bill_data,'overtime/invoice/query',function(data,response_info){
					$.messager.progress('close');
					$('#datagrid').datagrid('loadData',data);
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
});
		//查询
	$scope.query=function(){
			$scope.bill_data={
					sapCode:$('#sapCode').textbox('getValue'),
					invoiceNo:$('#invoiceNo').textbox('getValue'),
					expressNo:$('#expressNo').textbox('getValue'),
					startTime:$('#startTime').textbox('getValue'),
					endTime:$('#endTime').textbox('getValue'),
			//		status:folUtil.getComboBoxDataById('status'),
					beginNo:0,
					endNo:10
				};
			$scope.istableshow=true;
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					OvertimeInvoiceService.query($scope.bill_data,'overtime/invoice/query',function(data,response_info){
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
