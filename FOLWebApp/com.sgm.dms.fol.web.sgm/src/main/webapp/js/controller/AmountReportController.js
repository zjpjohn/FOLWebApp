FolController.controller('amountReportCtrl',['$scope','AmountReportService',function($scope,AmountReportService){
	//资金余额报表
	$scope.fundBalanceStatement=function(){
		// var reserve={};
		ReserveService.fundBalanceStatement(reserve,function(data){

		},function(data){

		});
	};

	$scope.fundBalanceStatement=function(){
		var reserve={dealertype:'渠道类型',B:'储备金类型',c:'客户代码',d:'客户名称'};
		ReserveService.fundBalanceStatement(reserve,function(data){
			$scope.istableshow=true;
			// console.log(data);
		},function(data){
			// console.log(data);
		});
	};
}]);