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
FolController.controller('PersonRoleCtrl',['$scope','$state','$timeout','PersonRoleService',function($scope,$state,$timeout,PersonRoleService){
	$scope.istableshow=false;

		//获取下拉框	
		PersonRoleService.get('authority/authorityamount/code/type/1001',function(data){
			$timeout(function(){
				$('#userStatus').combobox('loadData',data);
				$('#userStatus').combobox('select',data[0].code);
			});
		},function(data){
			console.log("error:"+data);
		});
		
		PersonRoleService.get('system/systemamount/type/1|2',function(data){
			$timeout(function(){
				data.unshift({positionId:'',positionName:'请选择'});
				$('#positionId').combobox('loadData',data);
				$('#positionId').combobox('select',data[0].positionId);
			});
		},function(response_info){
			console.log("error:"+response_info);
		});

	
	//带分页的查询方法
	var queryData = function(pageNum, pageSize) {
		$scope.istableshow=true;
		var userData={
			userName: $('#userName').textbox('getValue'),
			userAccount: $('#userAccount').textbox('getValue'),
			userStatus: folUtil.getComboBoxDataById('userStatus'),
			positionId: folUtil.getComboBoxDataById('positionId'),
			beginNo: (pageNum-1)*pageSize,
			endNo: pageNum*pageSize+1
		};

		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		PersonRoleService.post(userData,'common/users/query',function(data){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			console.log(response_info);
		});
	};
	
	//角色查询
	$scope.findPersonRole=function(){
		queryData(1,10);
		$("#datagrid").datagrid("getPager").pagination({
			onSelectPage:function(pageNumber, pageSize){
				queryData(pageNumber, pageSize);
			}
		});
	};
	
	//跳转到修改页面
	$scope.updataPersonRoles=function(){
		var row=$('#datagrid').datagrid('getSelected');
		if (row){
			PersonRoleService.setPersonData(row);
			$state.go('updataPersonRole');
		}else{
			alert('请选择一条记录');
		}
	};
	
	//跳转到新增页面
	$scope.userAdd=function(){
		$state.go('userAdd');
	};
	
	//删除
	$scope.delUser=function(){
		var row=$('#datagrid').datagrid('getSelected');
		if(row){
			if(confirm("确定删除吗?")) {
				PersonRoleService.del(row,'common/users/delete',function(data){
					alert("删除成功");
					queryData(1,10);
				},function(response_info){
					alert("删除失败");
					console.log(response_info);
				});
			}
		}else{
			alert('请选择一条记录');
		}
	};

}]);

FolController.controller('updataPersonRoleCtrl',['$scope','$state','$stateParams','$timeout','PersonRoleService',function($scope,$state,$stateParams,$timeout,PersonRoleService){
	$timeout(function(){
//		$scope.userBirthday = PersonRoleService.getPersonData().birthday;
		
		$('#birthday').datebox('setValue',PersonRoleService.getPersonData().birthday);
		$('#userAccount').textbox('setValue',PersonRoleService.getPersonData().userAccount);
		$('#userName').textbox('setValue',PersonRoleService.getPersonData().userName);
		$('#handPhone').textbox('setValue',PersonRoleService.getPersonData().handPhone);
		$('#zipCode').textbox('setValue',PersonRoleService.getPersonData().zipCode);
		$('#addr').textbox('setValue',PersonRoleService.getPersonData().addr);
		$('#email').textbox('setValue',PersonRoleService.getPersonData().email);
	});
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
		//获取SGM职位列表
		$("#userType").combobox({
			onChange: function(n,o){
				var userType = $('#userType').combobox('getValue');
				if(userType!='') {
					PersonRoleService.get('system/systemamount/type/'+userType+'/user/'+PersonRoleService.getPersonData().userAccount,function(data){
						$timeout(function(){
							$.messager.progress('close');		
							$('#grid_positions').datagrid({
								data: data[0],
								onLoadSuccess:function(data){
									if(data){
										$.each(data.rows,function(index,item){
											if(item.checked){
												$('#grid_positions').datagrid('checkRow',index);
											}
										});
									}
								}
							});
						});
					},function(response_info){
						console.log(response_info);
					});
				} else {
					$('#grid_positions').datagrid('loadData',[]);
				}
			}
		});

	
	//获取下拉框	
	PersonRoleService.get('authority/authorityamount/code/type/1001',function(data){
		$timeout(function(){
			$('#userStatus').combobox('loadData',data);
			$('#userStatus').combobox('select',PersonRoleService.getPersonData().userStatus);
		});
	},function(response_info){
		console.log("error:"+response_info);
	});
	
	PersonRoleService.get('authority/authorityamount/code/type/1012',function(data){
		$timeout(function(){
			$('#gender').combobox('loadData',data);
			$('#gender').combobox('select',PersonRoleService.getPersonData().gender);
		});
	},function(response_info){
		console.log("error:"+response_info);
	});
	
	PersonRoleService.get('authority/authorityamount/code/type/1071',function(data){
		$timeout(function(){
			$('#userType').combobox('loadData',data);
			$('#userType').combobox('select',PersonRoleService.getPersonData().userType);
		});
	},function(response_info){
		console.log("error:"+response_info);
	});
	
	$scope.userUpdate=function(){
		if($("#userAccount").textbox("getValue")==null||$("#userAccount").textbox("getValue")==''||$("#userAccount").textbox("getValue")<0){
	 		alert("请填写帐号名");
	 		return ;
		}
	 	if($("#userType").combobox("getValue")==null||$("#userType").combobox("getValue")==''||$("#userType").combobox("getValue")==0){
	 		alert("请选择用户类型");
	 		return ;
		}
	 	if($("#userName").textbox("getValue")==null||$("#userName").textbox("getValue")==''||$("#userName").textbox("getValue")<0){
	 		alert("请填写姓名");
	 		return ;
		}
	 	if($("#gender").combobox("getValue")==null||$("#gender").combobox("getValue")==''||$("#gender").combobox("getValue")==0){
	 		alert("请选择性别");
	 		return ;
		}
	 	if($("#userStatus").combobox("getValue")==null||$("#userStatus").combobox("getValue")==''||$("#userStatus").combobox("getValue")==0){
	 		alert("请选择启用状态");
	 		return ;
		}
	 	
	 	//验证手机号
	 	var reg=/^1\d{10}$/;
	 	if(!folUtil.isNull($("#handPhone").textbox("getValue"))&&!reg.test($("#handPhone").textbox("getValue"))){
	 		alert("请正确填写手机号！");
	 		return ;
		}
	     
	 	//验证邮件格式
	     var reg=/[a-zA-Z0-9]{1,10}@[a-zA-Z0-9]{1,5}\.[a-zA-Z0-9]{1,5}/;
	     if(!folUtil.isNull($("#email").textbox("getValue"))&&!reg.test($("#email").textbox("getValue"))){
	        alert("请正确填写邮箱！");
	        return ;
	     }
		
		var userData = {
			userAccount: $('#userAccount').textbox('getValue'),
			userType: folUtil.getComboBoxDataById('userType'),
			userName: $('#userName').textbox('getValue'),
			gender: folUtil.getComboBoxDataById('gender'),
			handPhone: $('#handPhone').textbox('getValue'),
			birthday: $('#birthday').textbox('getValue'),
			zipCode: $('#zipCode').textbox('getValue'),
			addr: $('#addr').textbox('getValue'),
			email: $('#email').textbox('getValue'),
			userStatus: folUtil.getComboBoxDataById('userStatus')
		};
		
		var positions = "";
		var checkedlist=$('#grid_positions').datagrid('getChecked');
		if(checkedlist.length>0) {
			$.each(checkedlist,function(i,val){
				positions = positions + val.positionId + ",";
			});
			positions=positions+" ";
			positions=positions.replace(", ", "");
		}
		userData.positions = positions;
		PersonRoleService.checkdata(userData,'common/users/getSign',function(data,response_info){
	
		PersonRoleService.put(userData,'common/users/update',function(data){
			alert("修改成功");
			$state.go('personRole');
		},function(response_info){
 			if(response_info.state===STATE.TIMEOUT){
 				console.log('timeout');
 				$.messager.alert('提示','系统超时请稍后访问');
 			}else{
 				alert("修改失败");
 			}
			console.log(response_info);
		});
 		},function(response_info){
 			if(response_info.state===STATE.TIMEOUT){
 				console.log('timeout');
 				$.messager.alert('提示','系统超时请稍后访问');
 			}
 		});
	};
}]);

FolController.controller('addUserCtrl',['$scope','$state','$stateParams','$timeout','PersonRoleService','AuthorityService',function($scope,$state,$stateParams,$timeout,PersonRoleService,AuthorityService){

	//获取下拉框	
	PersonRoleService.get('authority/authorityamount/code/type/1001',function(data){
		$timeout(function(){
			$('#userStatus').combobox('loadData',data);
			$('#userStatus').combobox('select',data[1].code);
		});		
	},function(response_info){
		console.log("error:"+response_info);
	});
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
	PersonRoleService.get('authority/authorityamount/code/type/1012',function(data){
		$timeout(function(){
			$.messager.progress('close');	
			$('#gender').combobox('loadData',data);
			$('#gender').combobox('select',data[0].code);
		});	
	},function(response_info){
		console.log("error:"+response_info);
	});
	
	PersonRoleService.get('authority/authorityamount/code/type/1071',function(data){
		$timeout(function(){
			$('#userType').combobox('loadData',data);
			$('#userType').combobox('select',data[1].code);
		});
	},function(response_info){
		console.log("error:"+response_info);
	});
	
	//获取SGM职位列表
	$("#userType").combobox({
		onChange: function(n,o){
			var userType = $('#userType').combobox('getValue');
			if(userType!='') {
				PersonRoleService.get('system/systemamount/type/'+userType,function(data){
					$('#grid_positions').datagrid('loadData',data);
				},function(data){
					console.log(data);
				});
			} else {
				$('#grid_positions').datagrid('loadData',[]);
			}
		}
	});
	
	/**
	 * 检查用户
	 */
	$scope.checkUser=function(){
		
	}
	
	//新增
	$scope.userAdd=function(){
		
		if($("#userAccount").textbox("getValue")==null||$("#userAccount").textbox("getValue")==''||$("#userAccount").textbox("getValue")<0){
	 		alert("请填写帐号名");
	 		return ;
		}
	 	if($("#userType").combobox("getValue")==null||$("#userType").combobox("getValue")==''||$("#userType").combobox("getValue")==0){
	 		alert("请选择用户类型");
	 		return ;
		}
	 	if($("#userName").textbox("getValue")==null||$("#userName").textbox("getValue")==''||$("#userName").textbox("getValue")<0){
	 		alert("请填写姓名");
	 		return ;
		}
	 	if($("#gender").combobox("getValue")==null||$("#gender").combobox("getValue")==''||$("#gender").combobox("getValue")==0||$("#gender").combobox("getValue")==-1){
	 		alert("请选择性别");
	 		return ;
		}
	 	if($("#userStatus").combobox("getValue")==null||$("#userStatus").combobox("getValue")==''||$("#userStatus").combobox("getValue")==0){
	 		alert("请选择启用状态");
	 		return ;
		}
	 	
	 	//验证手机号
	 	var reg=/^1\d{10}$/;
	 	if(!folUtil.isNull($("#handPhone").textbox("getValue"))&&!reg.test($("#handPhone").textbox("getValue"))){
	 		alert("请正确填写手机号！");
	 		return ;
		}
	     
	 	//验证邮件格式
	     var reg=/[a-zA-Z0-9]{1,10}@[a-zA-Z0-9]{1,5}\.[a-zA-Z0-9]{1,5}/;
	     if(!folUtil.isNull($("#email").textbox("getValue"))&&!reg.test($("#email").textbox("getValue"))){
	        alert("请正确填写邮箱！");
	        return ;
	     }
		
   	 	//判断用户帐号是否存在
   	 	PersonRoleService.get('common/users/account/'+$("#userAccount").textbox("getValue"),function(data){
			if(data[0] == 0) {
				createUser();
			} else {
				alert("帐号已存在,不能新增");
			}
		},function(response_info){
			alert("新增失败");
			console.log(response_info);
		});
	};
	
	var createUser = function () {
		var userData = {
			userAccount: $('#userAccount').textbox('getValue'),
			userType: folUtil.getComboBoxDataById('userType'),
			userName: $('#userName').textbox('getValue'),
			gender: folUtil.getComboBoxDataById('gender'),
			handPhone: $('#handPhone').textbox('getValue'),
			birthday: $('#birthday').textbox('getValue'),
			zipCode: $('#zipCode').textbox('getValue'),
			addr: $('#addr').textbox('getValue'),
			email: $('#email').textbox('getValue'),
			userStatus: folUtil.getComboBoxDataById('userStatus')
		};
		
		var positions = "";
		var checkedlist=$('#grid_positions').datagrid('getChecked');
		if(checkedlist.length>0) {
			$.each(checkedlist,function(i,val){
				positions = positions + val.positionId + ",";
			});
			positions=positions+" ";
			positions=positions.replace(", ", "");
		}
		userData.positions = positions;
 		AuthorityService.checkdata(userData,'common/users/getSign',function(data,response_info){	
		PersonRoleService.post(userData, 'common/users/add',function(data){
			alert("新增成功");
			$state.go('personRole');
		},function(response_info){
			alert("新增失败");
			console.log(response_info);
		});
 		},function(response_info){
 			if(response_info.state===STATE.TIMEOUT){
 				console.log('timeout');
 				$.messager.alert('提示','系统超时请稍后访问');
 			}
 		});
	};
	
	
}]);
	


FolController.controller('systemCtrl',['$scope','$state','SystemService',function($scope,$state,SystemService){
	$scope.istableshow=false;
	
	//带分页的查询方法
	var queryData = function(pageNum, pageSize) {
		$scope.istableshow=true;
		
		var positiondata={
			positionName:$('#positionName').textbox('getValue'),
			dpPositionCode:$('#dpPositionCode').textbox('getValue'),
			beginNo: (pageNum-1)*pageSize,
			endNo: pageNum*pageSize+1
		};
		
		$.messager.progress({
			title:'Please waiting',
			msg:'Loading data...',
			interval:PROGRESS_ACTION_TIMEOUT
		});	
		
		SystemService.systemPoRoMain(positiondata,'system/systemamount/query',function(data){
			$.messager.progress('close');
			$('#datagrid').datagrid('loadData',data);
		},function(response_info){
			$.messager.progress('close');
			console.log(response_info);
		});
	};
	
	//岗位查询
	$scope.systemPoRoMain=function(){
		queryData(1,10);
		$("#datagrid").datagrid("getPager").pagination({
			onSelectPage:function(pageNumber, pageSize){
				queryData(pageNumber, pageSize);
			}
		});
	};
	
	//跳转修改页面
	$scope.systemPoRoMains=function(){
		var row=$('#datagrid').datagrid('getSelected');
		if(row){
			SystemService.setPositionData(row);
			$state.go('systemPoRoMains');
		}else{
			alert('请选择一条记录');
		}
	};
	
	//跳转到添加页面
	$scope.systemPoRoAdd=function(){
		$state.go('systemPoRoMainsAdd');
	};
	
	//删除
	$scope.systemPoRoDel=function(){
		var row=$('#datagrid').datagrid('getSelected');
		if(row){
			if(confirm("确定删除吗?")) {
				SystemService.del(row,'system/systemamount/del',function(data){
					alert("删除成功");
					queryData(1,10);
				},function(response_info){
					alert("删除失败");
					console.log(response_info);
				});
			}
		}else{
			alert('请选择一条记录');
		}
	};
	
}]);

FolController.controller('systemUpdateCtrl',['$scope','$state','$timeout','$stateParams','SystemService',function($scope,$state,$timeout,$stateParams,SystemService){
	$timeout(function(){
		$('#positionId').textbox('setValue',SystemService.getPositionData().positionId);
		$('#positionName').textbox('setValue',SystemService.getPositionData().positionName);
		$('#positionDesc').textbox('setValue',SystemService.getPositionData().positionDesc);
		$('#dpPositionCode').textbox('setValue',SystemService.getPositionData().dpPositionCode);
//		$('#positionId').attr('value',SystemService.getPositionData().positionId);
//		$('#positionName').attr('value',SystemService.getPositionData().positionName);
//		$('#positionDesc').attr('value',SystemService.getPositionData().positionDesc);
//		$('#dpPositionCode').attr('value',SystemService.getPositionData().dpPositionCode);
	});
		//获取下拉框的值
		SystemService.get('authority/authorityamount/code/type/1071',function(data){
			$timeout(function(){
				$('#positionType').combobox('loadData',data);
				$('#positionType').combobox('select',SystemService.getPositionData().positionType);
			});
		},function(response_info){
			console.log("error:"+response_info);
		});
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
		
		//查询 储备金管理
		$("#positionType").combobox({
			onChange: function(n,o){
				var positionType = $('#positionType').combobox('getValue');
				if(positionType!='') {
					SystemService.get('authority/authorityamount/type/'+positionType+'/position/'+SystemService.getPositionData().positionId,function(data){
						$.messager.progress('close');
						$('#roledatagrid').datagrid('loadData',data);
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
					},function(response_info){
						console.log(response_info);
					});
				} else {
					$('#roledatagrid').datagrid('loadData',[]);
				}
			}
		});

	
	//确认修改
	$scope.systemPoRoMainA=function(){
		if($("#positionName").textbox("getValue")==null||$("#positionName").textbox("getValue")==''||$("#positionName").textbox("getValue")<0){
   	 		alert("请填写岗位名称");
	 		return ;
 		}
   	 	if($("#positionType").combobox("getValue")==null||$("#positionType").combobox("getValue")==''||$("#positionType").combobox("getValue")<0){
   	 		alert("请选择岗位类型");
	 		return ;
 		}
   	 	
   	 	var positionData={
 			positionName: $("#positionName").textbox("getValue"),
 			positionId: $("#positionId").textbox("getValue"),
 			positionType: folUtil.getComboBoxDataById('positionType'),
 			positionDesc: $("#positionDesc").textbox("getValue"),
 			dpPositionCode: $("#dpPositionCode").textbox("getValue")
 		};
   	 	
   	 	var roles = "";
		var checkedlist=$('#roledatagrid').datagrid('getChecked');
		if(checkedlist.length>0) {
			$.each(checkedlist,function(i,val){
				roles = roles + val.roleId + ",";
			});
			roles=roles+" ";
			roles=roles.replace(", ", "");
		}
		positionData.roles = roles;
		SystemService.checkdata(positionData,'system/systemamount/getSign',function(data,response_info){

		SystemService.put(data,'system/systemamount/update',function(data){
			alert("修改成功");
			$state.go('systemPoRoMain');
		},function(response_info){
			alert("修改失败");
			console.log(response_info);
		});
 		},function(response_info){
 			if(response_info.state===STATE.TIMEOUT){
 				console.log('timeout');
 				$.messager.alert('提示','系统超时请稍后访问');
 			}
 		});
	};
}]);

FolController.controller('systemAddCtrl',['$scope','$state','$stateParams','$timeout','SystemService','AuthorityService',function($scope,$state,$stateParams,$timeout,SystemService,AuthorityService){

	//获取下拉框的值
	SystemService.get('authority/authorityamount/code/type/1071',function(data){
		$timeout(function(){
			$('#positionType').combobox('loadData',data);
			$('#positionType').combobox('select',data[0].code);
		});
	},function(response_info){
		console.log("error:"+response_info);
	});
	$.messager.progress({
		title:'Please waiting',
		msg:'Loading data...',
		interval:PROGRESS_ACTION_TIMEOUT
	});	
	//根据类型查询角色列表 
	$("#positionType").combobox({
		onChange: function(n,o){
			var positionType = $('#positionType').combobox('getValue');
			if(positionType!='') {
				SystemService.get('authority/authorityamount/type/'+positionType,function(data){
					$.messager.progress('close');	
					$('#roledatagrid').datagrid('loadData',data);
				},function(response_info){
					console.log(response_info);
				});
			} else {
				$('#roledatagrid').datagrid('loadData',[]);
			}
		}
	});
	
	//新增
	$scope.systemPoRoMainAdd=function(){
		if($("#positionName").textbox("getValue")==null||$("#positionName").textbox("getValue")==''||$("#positionName").textbox("getValue")<0){
   	 		alert("请填写岗位名称");
	 		return ;
 		}
   	 	if($("#positionType").combobox("getValue")==null||$("#positionType").combobox("getValue")==''||$("#positionType").combobox("getValue")<0){
   	 		alert("请选择岗位类型");
	 		return ;
 		}
		
   	 	//判断岗位名称是否存在
   	 	SystemService.get('system/systemamount/name/'+$("#positionName").textbox("getValue"),function(data){
			if(data[0] == 0) {
				createPosition();
			} else {
				alert("岗位已存在,不能新增");
			}
		},function(data){
			alert("新增失败");
			console.log(data);
		});
	};
	
	var createPosition = function() {
		var systemdata={
			positionName: $("#positionName").textbox("getValue"),
			positionType: folUtil.getComboBoxDataById('positionType'),
			positionDesc: $("#positionDesc").textbox("getValue"),
			dpPositionCode: $("#dpPositionCode").textbox("getValue")
		};
		
		var roles = "";
		var checkedlist=$('#roledatagrid').datagrid('getChecked');
		if(checkedlist.length>0) {
			$.each(checkedlist,function(i,val){
				roles = roles + val.roleId + ",";
			});
			roles=roles+" ";
			roles=roles.replace(", ", "");
		}
		systemdata.roles = roles;
 		AuthorityService.checkdata(systemdata,'system/systemamount/getSign',function(data,response_info){
		SystemService.post(systemdata,'system/systemamount/add',function(data){
			alert("新增成功");
			$state.go('systemPoRoMain');
		},function(response_info){
			alert("新增失败");
			console.log(response_info);
		});
	},function(response_info){
		if(response_info.state===STATE.TIMEOUT){
			console.log('timeout');
			$.messager.alert('提示','系统超时请稍后访问');
		}
	});
	};
}]);
/**
 * 
 */
FolController.controller('UserController',['$scope','UserService',function($scope,UserService){
	$scope.doLogout=function(){
		UserService.logout('common/logout',function(data){
			$.messager.alert("退出已成功");
		},function(response_info){
			console.log(response_info);
		});
	};
}]);
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