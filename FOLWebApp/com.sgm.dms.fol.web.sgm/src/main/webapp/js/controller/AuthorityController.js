FolController.controller('authorityCtrl',['$scope','$rootScope','$http','$state','$timeout','AuthorityService',function($scope,$rootScope,$http,$state,$timeout,AuthorityService){
	$scope.istableshow=false;
	
	//获取角色类型下拉列表
	AuthorityService.systemRoRiMainss('authority/authorityamount/code/type/1071',function(data){
		$timeout(function(){
			console.log(data);
			$('#roleType').combobox('loadData',data);
			$('#roleType').combobox('select',data[0].code);
		});		
	},function(data){
		console.log("error:"+data);
	});
		
	//带分页的查询方法
	var queryData = function(pageNum, pageSize) {
		$scope.istableshow=true;
		
		var reserve_data={
			roleType: folUtil.getComboBoxDataById('roleType'),
			beginNo: (pageNum-1)*pageSize,
			endNo: pageNum*pageSize+1
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		AuthorityService.systemRoRiMain(reserve_data,'authority/authorityamount/query',function(data){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			console.log(response_info);
		});
	};
	
	//角色查询
	$scope.systemRoRiMain=function(){
		queryData(1,10);
		$("#datagrid").datagrid("getPager").pagination({
			onSelectPage:function(pageNumber, pageSize){
				queryData(pageNumber, pageSize);
			}
		});
	};
	
	$scope.delsystem=function(){
		var row=$('#datagrid').datagrid('getSelected');
		if(row){
			if(confirm("确定删除吗?")) {
				AuthorityService.delsystem(row,'authority/authorityamount/delAuthority',function(data){
					alert("删除成功");
					$('#datagrid').datagrid('loadData',{total:0,rows:[]});
					queryData(1,10);
				},function(data){
					alert("删除失败");
					console.log(data);
				});
			}
		}else{
			alert('请选择一条记录');
		}
	};
	
	//跳转到修改菜单页面
	$scope.systemRoRiMains=function(){
		var row=$('#datagrid').datagrid('getSelected');
		if(row){
			AuthorityService.setRoleData(row);
			$state.go('systemRoRiMains');
		}else{
			alert('请选择一条记录');
		}
	};
	//跳转到修改权限页面
	$scope.updateRoleAuthority=function(){
		var row=$('#datagrid').datagrid('getSelected');
		if(row){
			AuthorityService.setRoleData(row);
			$state.go('updateRoleAuthority');
		}else{
			alert('请选择一条记录');
		}
	};
	//跳转到添加页面
	$scope.systemRoRiMainsAd=function(){
		$state.go('systemRoRiMainsAdd');
	};
}]);
//修改菜单
FolController.controller('authorityUpdateCtrl',['$scope','$state','$timeout','AuthorityService',function($scope,$state,$timeout,AuthorityService){
	$timeout(function(){
//		$('#roleId').attr('value',AuthorityService.getRoleData().roleId);
//		$('#roleName').attr('value',AuthorityService.getRoleData().roleName);
//		$('#roleDesc').attr('value',AuthorityService.getRoleData().roleDesc);
		$('#roleId').textbox('setValue',AuthorityService.getRoleData().roleId);
		$('#roleName').textbox('setValue',AuthorityService.getRoleData().roleName);
		$('#roleDesc').textbox('setValue',AuthorityService.getRoleData().roleDesc);
	});
	

	//获取下拉框的值
	AuthorityService.systemRoRiMainss('authority/authorityamount/code/type/1071',function(data){
		$timeout(function(){
			$('#roleType').combobox('loadData',data);
			$('#roleType').combobox('select',AuthorityService.getRoleData().roleType);
		});
	},function(data){
		console.log("error:"+data);
	});
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
	//查询 储备金管理
	$("#roleType").combobox({
		onChange: function(n,o){
			var roleType = $('#roleType').combobox('getValue');
			if(roleType!='') {
				AuthorityService.findMenu('','authority/authorityamount/Menuquery/type/'+roleType+'/role/'+AuthorityService.getRoleData().roleId,function(data){
					$.messager.progress('close');
					$('#roledatagrid').datagrid({
						data: data,
						onLoadSuccess:function(data){
							if(data){
								$.each(data.rows,function(index,item){
									if(item.checked){
										$('#roledatagrid').datagrid('checkRow',index);
									}
								});
							}
						}
					});
				},function(data){
					console.log(data);
				});
			} else {
				$('#roledatagrid').datagrid('loadData',[]);
			}
		}
	});
		
	//确认修改
	$scope.systemPoRoMainA=function(){
		if($("#roleName").textbox("getValue")==null||$("#roleName").textbox("getValue")==''||$("#roleName").textbox("getValue")<0){
   	 		alert("请填写角色名称");
 			//$('#sumbitAddBtn').linkbutton('enable');
	 		return ;
 		}
   	 	if($("#roleType").combobox("getValue")==null||$("#roleType").combobox("getValue")==''||$("#roleType").combobox("getValue")<0){
   	 		alert("请选择角色类型");
 			//$('#sumbitAddBtn').linkbutton('enable');
	 		return ;
 		}
   	 	
   	 	var datas = {
 			roleId : $('#roleId').textbox('getValue'),
 			roleName : $('#roleName').textbox('getValue'),
 			roleType : folUtil.getComboBoxDataById('roleType'),
 			roleDesc : $('#roleDesc').textbox('getValue')
 		};
 		
 		var menus = "";
 		var checkedlist=$('#roledatagrid').datagrid('getChecked');
 		if(checkedlist.length>0) {
 			$.each(checkedlist,function(i,val){
 				menus = menus + val.menuId + ",";
 			});
 			menus=menus+" ";
 			menus=menus.replace(", ", "");
 		}
 		datas.menuId = menus;
 		AuthorityService.checkdata(datas,'authority/authorityamount/getSign',function(data,response_info){

		AuthorityService.systemPoRoMainA(datas,'authority/authorityamount/update',function(data){
			alert("修改成功");
			$state.go('systemRoRiMain');
		},function(data){
			alert("修改失败");
			console.log(data);
		});
	},function(response_info){
		if(response_info.state===STATE.TIMEOUT){
			console.log('timeout');
			$.messager.alert('提示','系统超时请稍后访问');
		}
	});
		
	};
	
}]);

//修改角色权限
FolController.controller('roleAuthorityUpdateCtrl',['$scope','$state','$timeout','AuthorityService',function($scope,$state,$timeout,AuthorityService){
	$timeout(function(){
		$('#roleId').textbox('setValue',AuthorityService.getRoleData().roleId);
		$('#roleName').textbox('setValue',AuthorityService.getRoleData().roleName);
		$('#roleDesc').textbox('setValue',AuthorityService.getRoleData().roleDesc);
	});
	

	//获取下拉框的值
	AuthorityService.systemRoRiMainss('authority/authorityamount/code/type/1071',function(data){
		$timeout(function(){
			$('#roleType').combobox('loadData',data);
			$('#roleType').combobox('select',AuthorityService.getRoleData().roleType);
		});
	},function(data){
		console.log("error:"+data);
	});
	$.messager.progress({
		title:'Please waiting',
		msg:'Loading data...',
		interval:PROGRESS_ACTION_TIMEOUT
	});	
	//查询 储备金管理
	$("#roleType").combobox({
		onChange: function(n,o){
			var roleType = $('#roleType').combobox('getValue');
			if(roleType!='') {
				AuthorityService.findMenu('','authority/authorityamount/methodQuery/role/'+AuthorityService.getRoleData().roleId,function(data){
					$.messager.progress('close');
					$('#roledatagrid').datagrid({
						data: data,
						onLoadSuccess:function(data){
							if(data){
								$.each(data.rows,function(index,item){
									if(item.checked){
										$('#roledatagrid').datagrid('checkRow',index);
									}
								});
							}
						}
					});
				},function(data){
					console.log(data);
				});
			} else {
				$('#roledatagrid').datagrid('loadData',[]);
			}
		}
	});
		
	//确认修改
	$scope.systemPoRoMainA=function(){
		if($("#roleName").textbox("getValue")==null||$("#roleName").textbox("getValue")==''||$("#roleName").textbox("getValue")<0){
   	 		alert("请填写角色名称");
 			//$('#sumbitAddBtn').linkbutton('enable');
	 		return ;
 		}
   	 	if($("#roleType").combobox("getValue")==null||$("#roleType").combobox("getValue")==''||$("#roleType").combobox("getValue")<0){
   	 		alert("请选择角色类型");
 			//$('#sumbitAddBtn').linkbutton('enable');
	 		return ;
 		}
   	 	
   	 	var data = {
 			roleId : $('#roleId').textbox('getValue'),
 			roleName : $('#roleName').textbox('getValue'),
 			roleType : folUtil.getComboBoxDataById('roleType'),
 			roleDesc : $('#roleDesc').textbox('getValue')
 		};
 		
 		var methods = "";
 		var checkedlist=$('#roledatagrid').datagrid('getChecked');
 		if(checkedlist.length>0) {
 			$.each(checkedlist,function(i,val){
 				methods = methods + val.methodId + ",";
 			});
 			methods=methods+" ";
 			methods=methods.replace(", ", "");
 		}
 		data.methodId = methods;

		AuthorityService.systemPoRoMainA(data,'authority/authorityamount/updateAuthorityMethod',function(data){
			alert("修改成功");
			$state.go('systemRoRiMain');
		},function(data){
			alert("修改失败");
			console.log(data);
		});
		
	};
	
}]);
FolController.controller('authorityAddCtrl',['$scope','$http','$state','$timeout','AuthorityService',function($scope,$http,$state,$timeout,AuthorityService){
	//获取下拉框的值
	AuthorityService.systemRoRiMainss('authority/authorityamount/code/type/1071',function(data){
		$timeout(function(){
			$('#roleType').combobox('loadData',data);
			$('#roleType').combobox('select',data[0].code);
		});
	},function(response_info){
		console.log("error:"+response_info);
	});
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
	//查询 储备金管理 菜单,菜单类型
	$("#roleType").combobox({
		onChange: function(n,o){
			var roleType = $('#roleType').combobox('getValue');
			if(roleType!='') {
				AuthorityService.findMenu('','authority/authorityamount/Menuquery/type/'+roleType,function(data){
					$.messager.progress('close');
					$('#roledatagrid').datagrid('loadData',data);
				},function(data){
					console.log(data);
				});
			} else {
				$('#roledatagrid').datagrid('loadData',[]);
			}
		}
	});
	
	//确认添加
	$scope.systemPoRoMainAdd=function(){
		if($("#roleName").textbox("getValue")==null||$("#roleName").textbox("getValue")==''||$("#roleName").textbox("getValue")<0){
   	 		$.messager.alert('提示','请填写角色名称');
	 		return ;
 		}
   	 	if($("#roleType").combobox("getValue")==null||$("#roleType").combobox("getValue")==''||$("#roleType").combobox("getValue")<0){
   	 		$.messager.alert('提示','请选择角色类型');
	 		return ;
 		}
		
		//判断角色名称是否存在
		AuthorityService.get('authority/authorityamount/name/'+$("#roleName").textbox("getValue"),function(data){
			if(data[0] == 0) {
				createRole();
			} else {
				$.messager.alert('提示','角色已存在,不能新增');
			}
		},function(data){
			$.messager.alert('提示','新增失败');
			console.log(data);
		});
	};
	
	var createRole = function() {
		var roleData = {
			roleName : $('#roleName').textbox('getValue'),
			roleType : folUtil.getComboBoxDataById('roleType'),
			roleDesc : $('#roleDesc').textbox('getValue')
		};
		
		var menus = "";
		var checkedlist=$('#roledatagrid').datagrid('getChecked');
		if(checkedlist.length>0) {
			$.each(checkedlist,function(i,val){
				menus = menus + val.menuId + ",";
			});
			menus=menus+" ";
			menus=menus.replace(", ", "");
		}
		roleData.menuId = menus;
 		AuthorityService.checkdata(roleData,'authority/authorityamount/getSign',function(data,response_info){
		AuthorityService.systemPoRoMainA(roleData,'authority/authorityamount/roleAdd',function(data){
			$.messager.alert('提示','新增成功');
			$state.go('systemRoRiMain');
		},function(data){
			$.messager.alert('提示','新增失败');
			console.log(data);
		});
 		},function(response_info){
 			if(response_info.state===STATE.TIMEOUT){
 				console.log('timeout');
 				$.messager.alert('提示','系统超时请稍后访问');
 			}
 		});
	};
}]);