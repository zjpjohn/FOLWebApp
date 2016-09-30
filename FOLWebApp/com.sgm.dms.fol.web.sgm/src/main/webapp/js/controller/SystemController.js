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