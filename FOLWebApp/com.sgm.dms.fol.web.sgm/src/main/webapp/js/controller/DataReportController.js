FolController.controller('accountCtrl',['$scope','AcountService',function($scope,AcountService){
	var query_data={sapCode:'123'};
	AcountService.getAccountAmountInfo(query_data,'datareport/account/query',function(data){	
		$scope.account=data;
	},function(data){
		console.log("error:"+data);
	});
}]);
