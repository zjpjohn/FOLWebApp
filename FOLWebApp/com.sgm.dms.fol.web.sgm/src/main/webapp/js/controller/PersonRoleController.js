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
	

