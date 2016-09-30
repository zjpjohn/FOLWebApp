/**
 * system controller
 */
FolController.controller('SystemController',['$scope','$window',function($scope,$window){
	$scope.doLogout=function(){
		$window.location.href="http://dp.saic-gm.com/am/oauth/logout";
	};
}]);