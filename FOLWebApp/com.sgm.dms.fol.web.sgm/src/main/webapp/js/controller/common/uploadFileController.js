/**
 * 文件上传controller
 */
FolController.controller('uploadFileController',['$rootScope','$scope','$window','$timeout','$stateParams','uploadFileService','CodeService',function($rootScope,$scope,$window,$timeout,$stateParams,uploadFileService,CodeService){
	$scope.pageName=$stateParams.pageName;
	
	/**
	 * 初始化数据
	 */
	$timeout(setInitData());
	
	/**
	 * 取消
	 */
	$scope.cancel=function(){
		$window.history.back();
	};
	
	/**
	 * 下载文件
	 */
	$scope.openDownloadFile=function(){
		uploadFileService.openDownloadFile('services/tokens',function(data){
			$.each(data, function(i, field) {
				if (i == "token") {
					alert($('#result').val().filename);
//					$("#div1").append(Date() + "---get token---" + field+"<br/>");
					$window.open('http://dapi.saic-gm.com/fileservice/html/download.html?token='+field+'&fileId='+$('#result').val(), '_blank',	'height=300,width=600,scrollbars=no,location=no');
				}
			});
		},function(data){
			console.log(data);
		});
	};
	
	/**
	 * 上传文件
	 */
	$scope.openUploadFile=function(){
		uploadFileService.openUploadFile('services/tokens',function(data){
			console.log('data:'+data);
			$.each(data, function(i, field) {
				if (i == "token") {
//					$("#div1").append(Date() + "---get token---" + field+"<br/>");
					//$window.open('http://dapi.saic-gm.com/fileservice/html/upload.html?token='+field+'&resultId=result', '_blank',	'height=300,width=600,scrollbars=no,location=no');
					$window.open('http://dapi.saic-gm.com/fileservice/html/upload.html?token='+field+'&resultId=result'+'&resultName=resultName', '_blank',	'height=300,width=600,scrollbars=no,location=no');
				}
			});
		},function(data){
			console.log(data);
		});
	};
	
	/**
	 * 文件保存
	 */
	$scope.saveFile=function(){
		uploadFileService.getToken('services/tokens',function(data,reponse_info){
			$.each(data, function(i, field) {
				if (i == "token") {
					var data={
							filePath: "http://dapi.saic-gm.com/fileservice/v1/files/" + $('#result').val() + "?token=" + field,
							data:$stateParams.data,
							filedId:$('#result').val(),
							fileName:$('#resultName').val(),
							token:field
					};
					
					if(folUtil.isNull($('#result').val())){
						$.messager.alert('提示','请选择文件再上传');
						return ;
					}
					
					if(!checkData()){
						return;
					}
					setOtherData(data);
					
					$.messager.progress({
						title:'Please waiting',
						msg:'Loading data...',
						interval:PROGRESS_ACTION_TIMEOUT
					});	
					
					$.each(UPLOAD_URL,function(i, val){
						if(i.toLowerCase()===$stateParams.pageName.toLowerCase()){
							uploadFileService.saveFile(data,val,function(response_info){
								$window.history.back();
								$.messager.progress('close');
								$.messager.alert('提示','上传文件成功');
							},function(response_info){
								$.messager.progress('close');
								
								if(response_info.state===STATE.TIMEOUT){
									console.log('timeout');
									$.messager.alert('提示','系统超时请稍后访问');
									
								}else{
									$.messager.alert('提示',response_info.message);
								}
								
							});
						}
					});
				}
			});
		},function(response_info){
			console.log(response_info);
		});
	};
	
	/**
	 * 数据初始化
	 */
	function setOtherData(data){
		if($stateParams.pageName==='bill'){
			data.effectDate=$('#effectDate').datebox('getValue');
			data.billDate=$('#billDate').datebox('getValue');
		}else if($stateParams.pageName==='bonusIssueVersion'){
			data.dealerType=folUtil.getComboBoxDataById('dealerType');
			data.bonusType=folUtil.getComboBoxDataById('bonusType');
		}else if($stateParams.pageName==='bankTicketInterest'){
			data.year=folUtil.getComboBoxDataById('year');
			data.month=folUtil.convertMonthToTwoPoint(folUtil.getComboBoxDataById('month'));
		}
	}
	/**
	 * 检查数据
	 */
	function checkData(){
		if($stateParams.pageName==='bonusIssueVersion'){
			if(folUtil.getComboBoxDataById('dealerType')==-1){
				$.messager.alert('提示', '渠道类型不能为空');
				return false;
			}else if(folUtil.getComboBoxDataById('bonusType')==-1){
				$.messager.alert('提示', '奖金类型不能为空');
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 数据初始化
	 */
	function setInitData(){
		console.log($stateParams.pageName);
		if($stateParams.pageName==='bonusIssueVersion'){
			var fields=['渠道类型','奖金类型'];
			
			$.messager.progress({
				title:'Please waiting',
				msg:'Loading data...',
				interval:PROGRESS_ACTION_TIMEOUT
			});	
			
			CodeService.findCodeTypeNames(fields,'code/findCodeTypeNames',function(data){
				$.messager.progress('close');
				
				var typesId=['#dealerType','#bonusType'];
				
				$timeout(function(){
//					$('#bonusType').combobox('loadData',$rootScope.bonusType);
//					$('#bonusType').combobox('select',$rootScope.bonusType[0]['code']);
				
					for(var i=0;i<data.length;i++){
						if(!folUtil.isNull(data[i])){
							$(typesId[i]).combobox('loadData',data[i]);
							$(typesId[i]).combobox('select',data[i][0]['code']);
						}
					}
				});
			},function(response_info){
				$.messager.progress('close');
				
				if(response_info.state===STATE.TIMEOUT){
					console.log('timeout');
					$.messager.alert('提示','系统超时请稍后访问');
				}
			});

		}else if($stateParams.pageName==='bankTicketInterest'){
			$timeout(function(){
				folUtil.setComboBoxYearDataById('year');
				folUtil.setComboBoxMonthDataById('month');
			});
			console.log(3);
		}
	}

}]);