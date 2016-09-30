/**
 * 待开发票编辑
 */
FolController.controller('StayInvoiceEditController',['$scope','$rootScope','$q','$timeout','StayInvoiceEditService','$window',function($scope,$rootScope,$q,$timeout,StayInvoiceEditService,$window){

	$scope.istableshow=false;
	
	var pagination=function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.bill_data.beginNo=pageSize*(pageNumber-1)<=0?0:pageSize*(pageNumber-1)+1;;
				$scope.bill_data.endNo=(pageSize * pageNumber)<=0?10:(pageSize * pageNumber);
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				StayInvoiceEditService.query($scope.bill_data,'invoice/edit/stayInvoiceList',function(data,response_info){
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
	
	$timeout(function(){
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		$scope.bill_data={
				expressNo:$scope.expressNo,	
				invoiceNo:$scope.invoiceNo,	
				taxAmount:$scope.taxAmount,
				beginNo:0,
				endNo:10
		};	
		StayInvoiceEditService.query($scope.bill_data,'invoice/edit/stayInvoiceList',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid().datagrid('enableCellEditing');
			$('#datagrid').datagrid('loadData',data);
//			console.log(data);
			
			pagination();
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
			console.log("error:"+response_info);
		});	
	});	
	
	//批量更新
	$scope.update=function(){
		var rows = $('#datagrid').datagrid('getChecked');
		if(rows.length<=0){
			$.messager.alert('提示','请至少选择一条记录');
			return;
		}
		var tsIds = "";
		if(rows.length > 0){
			for(var rownum in rows){
				if(tsIds.length > 0){
					tsIds = tsIds + ",";
				}
				tsIds = tsIds + rows[rownum]['tsId'];
			}
		}
		$rootScope.tsIds = tsIds;
		
		$('#ExrpessNoWindow').window('open');
	};	
	
	//保存
	$scope.save=function(){
		$scope.endEditing('datagrid');
		var rows = $('#datagrid').datagrid('getChecked');
		if(rows.length<=0){
			$.messager.alert('提示','请至少选择一条记录');
			return;
		}
		
		for(var rownum in rows){
//			console.log(Math.abs(rows[rownum]['tax']*TAX_UNIT-rows[rownum]['taxAmount']*TAX_UNIT)/TAX_UNIT);
			if(!folUtil.isNull(rows[rownum]['taxAmount']) && Math.abs(rows[rownum]['tax']*TAX_UNIT-rows[rownum]['taxAmount']*TAX_UNIT)/TAX_UNIT > 0.1){
				$.messager.alert('提示','发票税额与税金相差不能超过0.1元');
				return;
			}
			if(isNaN(rows[rownum]['invoiceNo'])){
				$.messager.alert('提示','发票号只能为数字')
				return;
			}
			if(rows[rownum]['invoiceNo']== null){
				$.messager.alert('提示','发票号不能为空')
				return;	
			}
			if(rows[rownum]['expressNo']== null){
				$.messager.alert('提示','快递单号不能为空')
				return;	
			}
			if(rows[rownum]['invoiceNo'].length>15){
				$.messager.alert('提示','发票号不能超过15位')
				return;	
			}
			
		}
			
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		StayInvoiceEditService.checkdata(rows,'invoice/edit/save/getSign',function(data,response_info){
			StayInvoiceEditService.save(data,'invoice/edit/save',function(data,response_info){
				$.messager.alert('提示','保存成功');
				StayInvoiceEditService.query($scope.bill_data,'invoice/edit/stayInvoiceList',function(data,response_info){
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
				$.messager.alert('提示','保存失败。原因：'+response_info.message);
			});	
		},function(response_info){
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};	
				
	//删除
	$scope.del=function(){
					
		var rows = $('#datagrid').datagrid('getChecked');
		if(rows.length<=0){
			$.messager.alert('提示','请至少选择一条记录');
			return;
		}
		var delete_tsId_params = "";
		if(rows.length > 0){
			for(var rownum in rows){
				if(delete_tsId_params.length > 0){
					delete_tsId_params = delete_tsId_params + ",";
				}
				delete_tsId_params = delete_tsId_params + rows[rownum]['tsId'];
			}
		}
		
		if(rows){
			if(rows.length==1){
				if(confirm("确定作废吗?")) {
					StayInvoiceEditService.del({"tsId":delete_tsId_params},'invoice/edit/delete',function(data){
						alert("作废成功");
						StayInvoiceEditService.query($scope.bill_data,'invoice/edit/stayInvoiceList',function(data){
							$('#datagrid').datagrid('loadData',{total:0,rows:[]});
							$('#datagrid').datagrid('loadData',data);
						},function(response_info){
							if(response_info.state===STATE.TIMEOUT){
								console.log('timeout');
								$.messager.alert('提示','系统超时请稍后访问');
							}
						});
					},function(response_info){
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
							return;
						}
						$.messager.alert('提示','作废失败。原因：'+response_info.message);
					});
				}
			}else{
				alert('请选择一条记录');
				return;
			}			

		}
	};
		
	//上报
	$scope.inform=function(){
		var rows = $('#datagrid').datagrid('getChecked');
		if(rows.length<=0){
			$.messager.alert('提示','请至少选择一条记录');
			return;
		}
		/*if(rows[0]['invoiceNo'] == null){
			$.messager.alert('提示','发票号不能为空');		
			return;
		}*/
		
		var report_data = "";//上报请求参数，事务号，多个事务号用逗号","分开
		for(var rownum in rows){
			if(rows[rownum]['invoiceNo'] == null){
				$.messager.alert('提示','你选中的第'+(parseInt(rownum)+1)+'个发票的发票号为空，请保存发票号后上报。');
				return;
			}
			if(report_data.length > 1){
				report_data = report_data+","
			}
			report_data = report_data + rows[rownum]['tsId']
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		StayInvoiceEditService.inform({"tsId":report_data},'invoice/edit/report',function(data,response_info){
			$.messager.alert('提示','上报成功');
			StayInvoiceEditService.query($scope.bill_data,'invoice/edit/stayInvoiceList',function(data,response_info){
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
			$.messager.alert('提示','上报失败。原因：'+response_info.message);
		});		
	};	
	
	//打开明细界面
	$scope.deatil=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		if(rows.length<=0){
			$.messager.alert('提示','选择一条记录，再操作');
			return;
		}else if(rows.length>1){
			$.messager.alert('提示','只能一条记录，再操作');
			return;
		}
	
		$('#DeatilWindow').window('open');
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		$rootScope.stayInvoiceEdit={};
		$rootScope.stayInvoiceEdit.invoiceFlowNum = rows[0]['tsId'];
		StayInvoiceEditService.queryDeatil({tsId:rows[0]['tsId']},'invoice/edit/detail',function(data,response_info){
			$.messager.progress('close');
			$('#deatildatagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
			console.log("error:"+response_info);
		});
	};
	
	$scope.endEditing=function(tableid){
		var rows=$('#'+tableid).datagrid('getRows');
		$.each(rows,function(i,val){
		   if ($('#datagrid').datagrid('validateRow', i)){
		        $('#datagrid').datagrid('endEdit', i);
		   }
		});
	};	
}]);


/**
 *	明细controller
 */
FolController.controller('DeatilController',['$scope','$rootScope','$timeout','$q','$state','StayInvoiceEditService',function($scope,$rootScope,$timeout,$q,$state,StayInvoiceEditService){

	$scope.exit=function(){
		$('#DeatilWindow').window('close');
			};	


}]);
/**
 * 批量更新
 */
FolController.controller('ExpressNoController',['$scope','$rootScope','$timeout','$q','$state','StayInvoiceEditService',function($scope,$rootScope,$timeout,$q,$state,StayInvoiceEditService){
	$scope.confirm=function(){
		$scope.bill_data={
				expressNo:$('#expressNo').textbox('getValue'),
				tsId:$rootScope.tsIds,
				beginNo:0,
				endNo:10
		};	
		StayInvoiceEditService.checkdata($scope.bill_data,'invoice/edit/bathchUpdateExpressNo/getSign',function(data,response_info){

			StayInvoiceEditService.update(data,'invoice/edit/bathchUpdateExpressNo',function(data,response_info){
				$.messager.alert('提示','更新成功');
				$('#ExrpessNoWindow').window('close');
				
				$scope.bill_data={
						expressNo:$('#expressNo').textbox('getValue'),
						tsId:$rootScope.tsIds,
						beginNo:0,
						endNo:10
				};	
				
				StayInvoiceEditService.query($scope.bill_data,'invoice/edit/stayInvoiceList',function(data,response_info){
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
				$.messager.alert('提示','更新失败');
				$('#ExrpessNoWindow').window('close');
			});	
		},function(response_info){
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	}
	$scope.cancel=function(){
		$('#ExrpessNoWindow').window('close');
		$scope.bill_data={
				expressNo:$scope.expressNo,	
				invoiceNo:$scope.invoiceNo,		
				beginNo:0,
				endNo:10
		};	
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		StayInvoiceEditService.query($scope.bill_data,'invoice/edit/stayInvoiceList',function(data,response_info){
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
