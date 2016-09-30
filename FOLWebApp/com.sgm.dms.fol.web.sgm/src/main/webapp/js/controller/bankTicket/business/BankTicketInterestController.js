/**
 * 票据贴息controller
 */
FolController.controller('BankTicketInterestController',['$scope','$timeout','$q','$state','BankTicketInterestService','BankInfoService',function($scope,$timeout,$q,$state,BankTicketInterestService,BankInfoService){
	$scope.istableshow=false;
	
	/**
	 * 数据初始化
	 */
	function init(){	
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
						
						BankTicketInterestService.query($scope.query_data,'bankTicket/bankTicketInterest/query',function(data,response_info){
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
		},PROGRESS_ACTION_TIMEOUT);
	}
	
	$timeout(function(){
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	

		BankInfoService.findAll('bankTicket/bankInfo/findAll',function(data,response_info){
			$.messager.progress('close');	

			if(!folUtil.isNull(data)){
				var bank=[];
				bank.push({bankAbbr:'-1',bankChName:'请选择'});
				$.each(data.rows,function(i,val){
					bank.push(val);
				});
				$timeout(function(){	
					$('#bankId').combobox('loadData',bank);
					$('#bankId').combobox('select',bank[0]['bankAbbr']);
				});
			}
			
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				console.log('timeout');
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
		
		var documentNumber=[];
		documentNumber.push({value:'-1',displayName:'请选择'});
		documentNumber.push({value:'0',displayName:'无'});
		documentNumber.push({value:'1',displayName:'有'});
		
		$timeout(function(){	
			$('#documentNumberExists').combobox('loadData',documentNumber);
			$('#documentNumberExists').combobox('select',documentNumber[0]['value']);
		});
	});
	
	/**
	 * 票据贴息查询
	 */
	$scope.query=function(){
		init();
		$scope.istableshow=true;

		$scope.query_data={
				sapCode:$('#sapCode').textbox('getValue'),
				bankId:folUtil.getComboBoxDataById('bankId'),
				acceptanceNumber:$('#acceptanceNumber').textbox('getValue'),
				documentNumberExists:folUtil.getComboBoxDataById('documentNumberExists'),
				startIssueDate:$('#startIssueDate').datebox('getValue'),
				endIssueDate:$('#endIssueDate').datebox('getValue'),
				beginNo:0,
				endNo:10
		};
		
		if(!check()){
			return;
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BankTicketInterestService.query($scope.query_data,'bankTicket/bankTicketInterest/query',function(data,response_info){
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
	
	/**
	 * 检查条件
	 */
	function check(){
		if($('#startIssueDate').datebox('getValue')>$('#endIssueDate').datebox('getValue')){
			$.messager.alert('提示','起始开票日期不能大于结束开票日期');
			return false;
		}
		
		return true;
	}
}]);