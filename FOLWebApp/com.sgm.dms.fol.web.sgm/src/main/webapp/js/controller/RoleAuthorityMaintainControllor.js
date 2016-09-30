FolController.controller('authorityMaintainCtrl',['$scope','$rootScope','$http','$state','$timeout','RoleAuthorityService',function($scope,$rootScope,$http,$state,$timeout,RoleAuthorityService){
	$scope.istableshow=false;
	$timeout(function(){
		var datagrid=$('#datagrid').datagrid('getPager');
		datagrid.pagination({
			onSelectPage:function(pageNumber,pageSize){
				$scope.authority_data.beginNo=pageSize*(pageNumber-1)==0?0:pageSize*(pageNumber-1)+1;
				$scope.authority_data.endNo=pageSize*pageNumber;
				
				$.messager.progress({
					title:'Please waiting',
					msg:'Loading data...',
					interval:PROGRESS_ACTION_TIMEOUT
				});	
				
				RoleAuthorityService.systemRoRiMain($scope.authority_data,'authority/authormethod/query',function(data){
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
	//角色查询
	$scope.systemRoRiMain=function(){
		$scope.istableshow=true;
		$scope.authority_data={
				clazz:$('#clazz').textbox('getValue'),
				beginNo:0,
				endNo:10
		};
		RoleAuthorityService.systemRoRiMain($scope.authority_data,'authority/authormethod/query',function(data){
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
	$scope.delsystem=function(){
		var row=$('#datagrid').datagrid('getSelected');
		if(row){
			if(confirm("确定删除吗?")) {
				RoleAuthorityService.delsystem(row,'authority/authormethod/delete',function(data){
					alert("删除成功");
					$scope.authority_data.beginNo=0;
					$scope.authority_data.endNo=10;
					RoleAuthorityService.systemRoRiMain($scope.authority_data,'authority/authormethod/query',function(data){
					//	$.messager.progress('close');
						$('#datagrid').datagrid('loadData',{total:0,rows:[]});
						$('#datagrid').datagrid('loadData',data);
					},function(response_info){
						$.messager.progress('close');
						if(response_info.state===STATE.TIMEOUT){
							console.log('timeout');
							$.messager.alert('提示','系统超时请稍后访问');
						}
					});	
				},function(data){
					alert("删除失败");
					console.log(data);
				});
			}
		}else{
			alert('请选择一条记录');
		}
	};
	
	//跳转到修改页面
	$scope.systemRoRiMains=function(){
		var row=$('#datagrid').datagrid('getSelected');
		if(row){
			RoleAuthorityService.setRoleData(row);
			$state.go('roleAuthorityMains');
		}else{
			alert('请选择一条记录');
		}
	};
	
	//跳转到添加页面
	$scope.systemRoRiMainsAd=function(){
		$state.go('roleAuthorityMainsAdd');
	};
}]);

FolController.controller('roleAuthorityMaintainUpdateCtrl',['$scope','$state','$timeout','RoleAuthorityService',function($scope,$state,$timeout,RoleAuthorityService){
	$timeout(function(){

		$('#clazz').textbox('setValue',RoleAuthorityService.getRoleData().clazz);
		$('#methodId').textbox('setValue',RoleAuthorityService.getRoleData().methodId);
		$('#method').textbox('setValue',RoleAuthorityService.getRoleData().method);
		$('#url').textbox('setValue',RoleAuthorityService.getRoleData().url);
	});	
		
	//确认修改
	$scope.systemPoRoMainA=function(){
		if($("#clazz").textbox("getValue")==null||$("#clazz").textbox("getValue")==''||$("#clazz").textbox("getValue")<0){
   	 		alert("请填写角色权限类名");
	 		return ;
 		}
		if($("#method").textbox("getValue")==null||$("#method").textbox("getValue")==''||$("#method").textbox("getValue")<0){
   	 		alert("请填写角色权限方法名");
	 		return ;
 		}
		if($("#url").textbox("getValue")==null||$("#url").textbox("getValue")==''||$("#url").textbox("getValue")<0){
   	 		alert("请填写角色权限URI");
	 		return ;
 		}
   	 	
   	 	var datas = {
   	 		methodId:$('#methodId').textbox('getValue'),
 			clazz : $('#clazz').textbox('getValue'),
 			method : $('#method').textbox('getValue'),
 			url : $('#url').textbox('getValue')
 		};
   	 //RoleAuthorityService.checkdata(datas,'system/systemamount/getSign',function(data,response_info){

   	 RoleAuthorityService.systemPoRoMainA(datas,'authority/authormethod/update',function(data){
			alert("修改成功");
			$state.go('roleAuthorityMain');
		},function(data){
			if(response_info.state===STATE.TIMEOUT){
 				console.log('timeout');
 				$.messager.alert('提示','系统超时请稍后访问');
 			}else{
 				alert("修改失败");
 			}
			console.log(data);
		});
		/*},function(response_info){
 			if(response_info.state===STATE.TIMEOUT){
 				console.log('timeout');
 				$.messager.alert('提示','系统超时请稍后访问');
 			}
 		});*/
		
	};
	$scope.cancel= function(){
		$state.go('roleAuthorityMain');
	}
	
}]);

FolController.controller('roleAuthorityMaintainAddCtrl',['$scope','$http','$state','$timeout','RoleAuthorityService',function($scope,$http,$state,$timeout,RoleAuthorityService){
	//确认添加
	$scope.systemPoRoMainAdd=function(){
		if($("#clazz").textbox("getValue")==null||$("#clazz").textbox("getValue")==''||$("#clazz").textbox("getValue")<0){
   	 		alert("请填写角色权限类名");
	 		return ;
 		}
		if($("#method").textbox("getValue")==null||$("#method").textbox("getValue")==''||$("#method").textbox("getValue")<0){
   	 		alert("请填写角色权限方法名");
	 		return ;
 		}
		if($("#url").textbox("getValue")==null||$("#url").textbox("getValue")==''||$("#url").textbox("getValue")<0){
   	 		alert("请填写角色权限URI");
	 		return ;
 		}

			var roleAuthorityData = {
				clazz : $('#clazz').textbox('getValue'),
				method : $('#method').textbox('getValue'),
				url : $('#url').textbox('getValue')
			};	

		RoleAuthorityService.systemPoRoMainA(roleAuthorityData,'authority/authormethod/add',function(data){
			$.messager.alert('提示','新增成功');
			$state.go('roleAuthorityMain');
		},function(data){
			$.messager.alert('提示','新增失败');
			console.log(data);
		});
	};
		$scope.cancel= function(){
			$state.go('roleAuthorityMain');
		}

}]);