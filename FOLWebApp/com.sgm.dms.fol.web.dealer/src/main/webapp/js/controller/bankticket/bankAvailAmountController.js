/**
 * 银行可用额度controller
 */
FolController.controller('BankAvailAmountController',['$scope','$timeout','$q','$state','BankAvailAmountService','BankInfoService',function($scope,$timeout,$q,$state,BankAvailAmountService,BankInfoService){
	$scope.istableshow=false;
	
	$timeout(function(){		
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
					
					BankAvailAmountService.query($scope.query_data,'bankTicket/bankAvailAmount/query',function(data,response_info){
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
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	

		BankInfoService.findAll('bankTicket/bankInfo/findAll',function(data,response_info){
			$.messager.progress('close');	
			console.log(data);
			if(!folUtil.isNull(data.rows)){
				var bank=new Array();
				bank.push({id:'-1',bankChName:'请选择'});
				$.each(data.rows,function(i,val){
					bank.push(val);
				});
				$timeout(function(){	
					$('#bankId').combobox('loadData',bank);
					$('#bankId').combobox('select',bank[0]['id']);
				});
			}
			
		},function(data,response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
			console.log("error:"+data);
		});
	});
	
	$scope.query=function(){
		$scope.istableshow=true;
		
		$scope.query_data={				
				bankId:folUtil.getComboBoxDataById('bankId'),
				beginNo:0,
				endNo:10
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankAvailAmountService.query($scope.query_data,'bankTicket/bankAvailAmount/query',function(data,response_info){
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
	};
}]);