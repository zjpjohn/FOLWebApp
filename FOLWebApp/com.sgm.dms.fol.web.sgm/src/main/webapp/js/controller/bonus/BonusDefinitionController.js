/**
 * 奖金定义controller
 */
FolController.controller('BonusDefinitionController',['$scope','$timeout','$state','BonusDefinitionSerivce',function($scope,$timeout,$state,BonusDefinitionSerivce){
	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.codeVo.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;;
				$scope.codeVo.endNo=pageSize*pageNumber;
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				BonusDefinitionSerivce.queryBonusDefinition($scope.codeVo,'bonus/type/query',function(data,response_info){
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
	});
	
	$scope.addBonusDefinition=function(){
		$state.go('addBonusDefinition');
	};
	
	$scope.queryBonusDefinition=function(){
		$scope.istableshow=true;
		
		$scope.codeVo={
				code:$scope.code,
				codeCnDesc:$scope.codeCnDesc,
				beginNo:0,
				endNo:10
		}
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		BonusDefinitionSerivce.queryBonusDefinition($scope.codeVo,'bonus/type/query',function(data,response_info){
			$.messager.progress('close');
			if(data.length===0){
				$scope.istableshow=false;
				$.messager.alert('提示','没有相关符合的数据');
			}else{
				$('#datagrid').datagrid('loadData',{total:0,rows:[]});
				$('#datagrid').datagrid('loadData',data);
			}
		},function(response_info){
			$.messager.progress('close');
			if(response_info.state===STATE.TIMEOUT){
				$scope.istableshow=false;
				$.messager.alert('提示','系统超时请稍后访问');
			}
		});
	};
	
	$scope.updateBonusDefinition=function(){
		var rows=$('#datagrid').datagrid('getSelections');
		
		if(rows.length>1){
			$.messager.alert('提示', '不能选择多个奖金类型，请重新选择');
			return;
		}else if(rows.length<1){
			$.messager.alert('提示', '请选择一个奖金类型，请重新选择');
			return;
		}
		
		$state.go('updateBonusDefinition',{bonusType: rows[0]});
	};
}]);

/**
 * 新增奖金定义controller
 */
FolController.controller('AddBonusDefinitionController',['$scope','$timeout','$state','BonusDefinitionSerivce',function($scope,$timeout,$state,BonusDefinitionSerivce){
	$scope.addBonusDefinition=function(){
		var codeVo={
			code:$scope.code,
			codeCnDesc:$scope.codeCnDesc,
			remark:$scope.remark,
			typeName:'奖金类型'
		};
		
		if(!$scope.checkData(codeVo)){
			return;
		}
		
		BonusDefinitionSerivce.addBonusDefinition(codeVo,'bonus/type/add',function(data,response_info){
			$.messager.alert('提示','新增奖金成功');
			$state.go('bonusDefinition');
		},function(response_info){
			$.messager.alert('提示','新增奖金失败');
			console.log(response_info);
		});
	};
	
	$scope.checkData=function(data){
		if(folUtil.isNull(data.code)){
			$.messager.alert('提示', '请填写奖金代码，再继续操作');
			return false;
		}
		
		if(folUtil.isNull(data.codeCnDesc)){
			$.messager.alert('提示', '请填写奖金名称，再继续操作');
			return false;
		}
		return true;
	};
}]);

/**
 * 更新奖金定义controller
 */
FolController.controller('UpdateBonusDefinitionController',['$scope','$timeout','$state','$stateParams','BonusDefinitionSerivce',function($scope,$timeout,$state,$stateParams,BonusDefinitionSerivce){
	console.log($stateParams.bonusType);
	/**
	 * 数据初始化
	 */
	$timeout(function(){
		$('#code').textbox('setValue',$stateParams.bonusType.code);
		$('#codeCnDesc').textbox('setValue',$stateParams.bonusType.codeCnDesc);
		$('#remark').textbox('setValue',$stateParams.bonusType.remark);
		$scope.code=$stateParams.bonusType.code;
		$scope.codeCnDesc=$stateParams.bonusType.codeCnDesc;
		$scope.remark=$stateParams.bonusType.remark;
		$scope.codeId=$stateParams.bonusType.codeId;
	});
	
	/**
	 * 更新bonusType
	 */
	$scope.updateBonusDefinition=function(){
		var codeVo={
			codeId:$scope.codeId,
			code:$scope.code,
			codeCnDesc:$scope.codeCnDesc,
			remark:$scope.remark,
		};
		
		if(!$scope.checkData(codeVo)){
			return;
		}
		
		BonusDefinitionSerivce.updateBonusDefinition(codeVo,'bonus/type/update',function(data,response_info){
			$.messager.alert('提示','更新奖金成功');
			$state.go('bonusDefinition');
		},function(response_info){
			$.messager.alert('提示','更新奖金失败');
			console.log(response_info);
		});
	};
	
	/**
	 * 检查数据
	 */
	$scope.checkData=function(data){
		if(folUtil.isNull(data.code)){
			$.messager.alert('提示', '请填写奖金代码，再继续操作');
			return false;
		}
		
		if(folUtil.isNull(data.codeCnDesc)){
			$.messager.alert('提示', '请填写奖金名称，再继续操作');
			return false;
		}
		return true;
	};
}]);