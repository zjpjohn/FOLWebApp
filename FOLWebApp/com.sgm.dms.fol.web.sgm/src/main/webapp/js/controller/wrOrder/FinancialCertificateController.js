/**
 * SAP每日财务凭证查询
 */
FolController.controller('FinancialCertificateController',['$scope','$rootScope','$q','$timeout','FinancialCertificateService','$window',function($scope,$rootScope,$q,$timeout,FinancialCertificateService,$window){
	
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
				FinancialCertificateService.query($scope.bill_data,'financial/certificate/query',function(data,response_info){
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
});
		//查询
	$scope.query=function(){
			$scope.bill_data={
					sapCode:$('#sapCode').textbox('getValue'),
					approveMan:$('#approveMan').textbox('getValue'),
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
					FinancialCertificateService.query($scope.bill_data,'financial/certificate/query',function(data,response_info){
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
				//文件导出
	$scope.exportCertificate=function(){
					
					FinancialCertificateService.getToken('services/tokens',function(data){
						if(folUtil.isNull($scope.bill_data)){
							$.messager.alert('提示','请先查询一次再导出');
							return;
						}
						
						$.messager.progress('close');
						$window.open('financial/certificate/invoiceDeatilExp?sapCode='+$scope.bill_data.sapCode+'&approveMan='+encodeURI(encodeURI($scope.bill_data.approveMan))+'&startTime='+$scope.bill_data.startTime+'&endTime='+$scope.bill_data.endTime+'&token='+data['token']);
					},function(response_info){
						$.messager.progress('close');
						console.log(response_info);
					});
				};

}]);
