/**
 * 索赔发票状态查询
 */
FolController.controller('ChaimInvoiceStatusController',['$scope','$rootScope','$q','$timeout','ChaimInvoiceStatusService','CodeService','$window',function($scope,$rootScope,$q,$timeout,ChaimInvoiceStatusService,CodeService,$window){
	$scope.istableshow=false;
	$scope.flag=true
	
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
				ChaimInvoiceStatusService.query($scope.bill_data,'invoice/statusQuery/query',function(data,response_info){
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

	$timeout(function(){
		var fields=['SGM受理状态'];
		CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
			$.messager.progress('close');
			
			var typesId=['#status'];
			
//			var timer=$timeout(function(){
				for(var i=0;i<data.length;i++){
					if(!folUtil.isNull(data[i])){
						$(typesId[i]).combobox('loadData',data[i]);
						$(typesId[i]).combobox('select',data[i][0]['code']);
					}
				}
//			});			
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	});
	
	$scope.query=function(){	
		$scope.bill_data={
				sapCode:$('#sapCode').textbox('getValue'),
				invoiceNo:$('#invoiceNo').textbox('getValue'),
				expressNo:$('#expressNo').textbox('getValue'),
				status:folUtil.getComboBoxDataById('status'),
				approveMan:$('#approveMan').textbox('getValue'),
				beginNo:0,
				endNo:10
			};
		$scope.istableshow=true;
			
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		ChaimInvoiceStatusService.query($scope.bill_data,'invoice/statusQuery/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
			$('#datagrid').datagrid('loadData',data);
			
			if($scope.flag){
				$('#datagrid').datagrid().datagrid('enableCellEditing');
				pagination();
			}

			$scope.flag=false;
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
			console.log("error:"+response_info);
		});	
					
	};
		//重处理
	$scope.anew=function(){
		var rows=$('#datagrid').datagrid('getChecked');
		
		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}	
		
		var invoiceNoStr = "";
		for(var rownum in rows){
			if(invoiceNoStr.length>0){
				invoiceNoStr = invoiceNoStr + ",";
			}
			invoiceNoStr = invoiceNoStr + rows[rownum]['invoiceNo']+rows[rownum]['sapCode'];
			if(rows[rownum]['status']!= 5){
				$.messager.alert('提示','当前状态不是SAP处理失败状态，不能给予重处理操作！');
				return;
			}
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		ChaimInvoiceStatusService.anew({"invoiceNo":invoiceNoStr},'invoice/statusQuery/rehandle',function(data,response_info){
			$.messager.alert('提示','重处理操作成功');
			$.messager.progress('close');
			ChaimInvoiceStatusService.anew($scope.bill_data,'invoice/statusQuery/query',function(data,response_info){
				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
				$('#datagrid').datagrid('loadData',data);
			},function(response_info){
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
			$.messager.alert('提示','重处理操作失败');
			console.log("error:"+response_info);
		});		
	};	
	
	//保存备注
	$scope.save=function(){
		$scope.endEditing('datagrid');	
		
		var rows=$('#datagrid').datagrid('getChecked');

		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}
					
		var datas= [];
		$.each(rows,function(i,val){
			datas.push({"invoiceNo":val['invoiceNo'],"sapCode":val['sapCode'],"remark":val['remark']});
		});
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		/*$scope.bill_data={	
	//			approveMsg:$scope.approveMsg,
				remark:$scope.remark,
				beginNo:0,
				endNo:10
		};*/
		ChaimInvoiceStatusService.checkdata(datas,'invoice/statusQuery/saveRemark/getSign',function(data,response_info){
			ChaimInvoiceStatusService.save(data.rows,'invoice/statusQuery/saveRemark',function(data,response_info){
				$.messager.alert('提示','保存成功');
				$.messager.progress('close');
				ChaimInvoiceStatusService.query($scope.bill_data,'invoice/statusQuery/query',function(data,response_info){
					$('#datagrid').datagrid('loadData',{total:0,rows:[]});
					$('#datagrid').datagrid('loadData',data);
				},function(response_info){
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
				$.messager.alert('提示','保存失败');
				console.log("error:"+response_info);
			});	
		},function(response_info){
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};	
	//退回
	$scope.back=function(){
					
		var rows=$('#datagrid').datagrid('getChecked');

		if(rows.length<=0){
			$.messager.alert('提示','请选择至少一条记录，再进行操作');
			return;
		}
		for(var rownum in rows){
			if(rows[rownum]['status'] == 4){
				$.messager.alert('提示','当前状态是SAP处理成功状态，不能给予退回操作！');
				return;
			}
		}
		var back_datas = "";
		$.each(rows,function(i,val){
			if(back_datas.length > 0){
				back_datas = back_datas + ",";
			}
			back_datas = back_datas + val['invoiceNo']+"||"+val['sapCode'];
		});
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
			
		ChaimInvoiceStatusService.back({"invoiceNo":back_datas},'invoice/statusQuery/sendBack',function(data,response_info){
			$.messager.alert('提示','退回操作成功');
			ChaimInvoiceStatusService.query($scope.bill_data,'invoice/statusQuery/query',function(data,response_info){
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
	
		$rootScope.claimStatus={};
		$rootScope.claimStatus.sapCode = rows[0]['sapCode'];
		$rootScope.claimStatus.invoiceNo=rows[0]['invoiceNo'];
		$rootScope.claimStatus.expressNo = rows[0]['expressNo'];
		
		ChaimInvoiceStatusService.queryDeatil($rootScope.claimStatus,'invoice/statusQuery/detail',function(data,response_info){
			$('#deatildatagrid').datagrid('loadData',{total:0,rows:[]});
			$('#deatildatagrid').datagrid('loadData',data);
		},function(response_info){
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
			console.log("error:"+response_info);
		});
		
		$('#DeatilWindow').window('open');
			
	}
		
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
FolController.controller('DeatilController',['$scope','$rootScope','$state','$timeout','$window','ChaimInvoiceStatusService',function($scope,$rootScope,$state,$timeout,$window,ChaimInvoiceStatusService){

	$timeout(function(){		
		if($('#deatildatagrid').length>0){
			var datagrid=$('#deatildatagrid').datagrid('getPager');

			datagrid.pagination({
				onSelectPage:function(pageNumber,pageSize){

					$scope.bill_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
					$scope.bill_data.endNo=pageSize*pageNumber;
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					ChaimInvoiceStatusService.query($scope.bill_data,'invoice/statusQuery/query',function(data,response_info){
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
				}
			});
		}
		
	});
	$scope.exit=function(){
//		$state.go('StayInvoiceEdit');
		$('#DeatilWindow').window('close');
		$scope.bill_data={
				expressNo:$('#sapCode').textbox('getValue'),
				invoiceNo:$('#invoiceNo').textbox('getValue'),
				expressNo:$('#expressNo').textbox('getValue'),
				status:folUtil.getComboBoxDataById('status'),
				expressNo:$('#approveMan').textbox('getValue'),
				beginNo:0,
				endNo:10
			};
		$scope.istableshow=true;
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		ChaimInvoiceStatusService.query($scope.bill_data,'invoice/statusQuery/query',function(data,response_info){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',{total:0,rows:[]});
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
}]);