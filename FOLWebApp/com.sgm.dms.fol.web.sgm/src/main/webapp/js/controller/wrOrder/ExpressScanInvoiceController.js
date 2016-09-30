/**
 * 快递单号扫描发票
 */
FolController.controller('ExpressScanInvoiceController',['$scope','$rootScope','$q','$timeout','ExpressScanInvoiceService','$window',function($scope,$rootScope,$q,$timeout,ExpressScanInvoiceService,$window){
	
	function pagination(){
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
				ExpressScanInvoiceService.query($scope.bill_data,'invoice/expressNoScanInvoice/query',function(data,response_info){
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
//	$timeout(function(){
//		if($('#datagrid').length>0){
//
//		}
//});
		//查询
	$scope.query=function(){

			$scope.bill_data={
					invoiceNo:$('#invoiceNo').textbox('getValue'),
					expressNo:$('#expressNo').textbox('getValue'),
					sapCode:$('#sapCode').textbox('getValue'),
					beginNo:0,
					endNo:10
				};
			$scope.istableshow=true;

			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
					ExpressScanInvoiceService.query($scope.bill_data,'invoice/expressNoScanInvoice/query',function(data,response_info){
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
				
		//同意
	$scope.agree=function(){
			var rows=$('#datagrid').datagrid('getChecked');
	
			if(rows.length<=0){
				$.messager.alert('提示','请选择至少一条记录，再进行操作');
				return;
			}else if(rows[0]['status'] !=2 ){
				$.messager.alert('提示','当前状态已处理,不能给予操作');
				return;
			}
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	

					ExpressScanInvoiceService.agree(rows,'invoice/expressNoScanInvoice/auditSuccess',function(data,response_info){
						$.messager.alert('提示','同意操作成功');
						ExpressScanInvoiceService.query($scope.bill_data,'invoice/expressNoScanInvoice/query',function(data,response_info){
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
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
							return;
						}
						$.messager.alert('提示','同意操作失败');
						console.log("error:"+response_info);
					});		
				};
		//退回
	$scope.back=function(){
				var rows=$('#datagrid').datagrid('getChecked');
				
				if(rows.length<=0){
					$.messager.alert('提示','请选择至少一条记录，再进行操作');
					return;
				}else if(rows[0]['status'] !=2 ){
					$.messager.alert('提示','当前状态已处理,不能给予操作');
					return;
				}
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	

					ExpressScanInvoiceService.back(rows,'invoice/expressNoScanInvoice/reject',function(data,response_info){
						$.messager.alert('提示','退回操作成功');
						ExpressScanInvoiceService.query($scope.bill_data,'invoice/expressNoScanInvoice/query',function(data,response_info){
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
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
							return;
						}
						$.messager.alert('提示','退回操作失败');
						console.log("error:"+response_info);
					});		
				};	

}]);
