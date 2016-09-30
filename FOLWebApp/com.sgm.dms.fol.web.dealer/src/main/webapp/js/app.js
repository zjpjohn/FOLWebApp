var workflowApp = angular.module('Framework', ['ngTouch', 'ngCookies', 'common.ui.framework', 'common.ui.easyui', 'ui.router', 'ajoslin.promise-tracker',
    'FolController', 'FolService'
]);
workflowApp.run(['$rootScope', '$interval', '$window', function($rootScope, $interval, $window) {
    $rootScope.reserveType = [
        { code: RESERVE_TYPE_PARTS, codeCnDesc: '配件' }
        //	         ,{code:RESERVE_TYPE_CAR,codeCnDesc:'整车'}
    ];

    $rootScope.bonusType = [{
            code: BUSINESS_CODE_ACCESSORIES,
            codeCnDesc: '配件'
        }
        // ,{code:RESERVE_TYPE_CAR,codeCnDesc:'整车'}
    ];

    //	localStorage.setItem('intervalActionNum',0);
    //
    //	//计时累加
    //	$interval(function(){
    //		localStorage.setItem('usedtime',parseInt(localStorage.getItem('usedtime')===null?0:localStorage.getItem('usedtime'))
    //				+PROGRESS_ACTION_TIMEOUT);
    //	},PROGRESS_ACTION_TIMEOUT);
    //
    //	//刷新或者url变更的监听事件
    //	$rootScope.$on('$locationChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
    //		//登陆超时
    //		folUtil.logonTimeOut($interval,$window);	
    //	});

}]);

workflowApp.config(function($httpProvider, $stateProvider, $locationProvider, navigationServiceProvider, permissionServiceProvider) {
    //Console兼容性检查
    if (!window.console) {
        var methods = ["log", "debug", "info", "warn", "error", "assert", "dir", "dirxml", "group",
            "groupEnd", "time", "timeEnd", "count", "trace", "profile", "profileEnd"
        ];

        window.console = {};
        for (var i = 0; i < methods.length; ++i) {
            window.console[methods[i]] = function() {};
        }
    }
    //HTTP拦截器
    $httpProvider.interceptors.push('HttpInterceptor');

    //定义路径，并实例化
    permissionServiceProvider.doConfig("user/users");
    //菜单路由
    navigationServiceProvider.doConfig("user/menus", $stateProvider);

    //用来配置不是菜单的状态路由
    $stateProvider
    // 上传页面
        .state('uploadFilePage', {
            url: '/uploadFilePage',
            templateUrl: 'views/common/uploadFilePage.html',
            params: {
                pageName: null,
                data: null
            }
        })
        //我的账户
        .state('account', {
            url: '/account',
            templateUrl: 'views/data/account.html'
        })
        //我的账户(储备金变动明显查询)
        .state('reserveChangeDeatilInquiry', {
            url: '/reserveChangeDeatilInquiry',
            templateUrl: 'views/data/reserveChangeDeatilInquiry.html'
        })
        //我的账户(奖金变动明显查询)
        .state('bonusChangeDeatilInquiry', {
            url: '/bonusChangeDeatilInquiry',
            templateUrl: 'views/data/bonusChangeDetailInquiry.html'
        })
        //我的账户(对账单签收)
        .state('reconciliationSign', {
            url: '/reconciliationSign',
            templateUrl: 'views/data/reconciliationSign.html'
        })
        //岗位切换
        .state('msg_identity', {
            url: '/msg_identity',
            templateUrl: '/msg_identity.html'
        })
        //奖金月度报表
        .state('bonusMonthForm', {
            url: '/bonusMonthForm',
            templateUrl: 'views/data/bonusMonthForm.html'
        })
        //银票明细
        .state('bankTicketDeatil', {
            url: '/bonusMonthForm',
            templateUrl: 'views/data/bonusMonthForm.html'
        })
        //索赔发票跟踪查询
        .state('InvoiceFollow', {
            url: '/InvoiceFollow',
            templateUrl: 'views/wrOrder/invoiceFollow.html'
        })
        //待开发票编辑
        .state('StayInvoiceEdit', {
            url: '/StayInvoiceEdit',
            templateUrl: 'views/wrOrder/stayInvoiceEdit.html'
        })
        //索赔月度财务汇总
        .state('WrOrderCollect', {
            url: '/WrOrderCollect',
            templateUrl: 'views/wrOrder/wrOrderCollect.html'
        })
        //折让单查询管理页面
        .state('retrunAllowanceManager', {
            url: '/returnAllowanceManager',
            templateUrl: 'views/returnallowance/allowancemanager.html'
        })
        //新建折让单
        .state('createRetrunAllowance', {
            url: '/createReturnAllowance',
            templateUrl: 'views/returnallowance/createallowance.html'
        })
        //编辑折让单
        .state('editRetrunAllowance', {
            url: '/editReturnAllowance',
            templateUrl: 'views/returnallowance/editallowance.html',
            params: {
                data: null
            }
        })
        .state('returnAllowanceInfo',{
        	url:'/returnAllowanceInfo',
        	templateUrl:'views/returnallowance/returnallowanceinfo.html',
        	params: {
                data: null
            }
        });
    	
});
workflowApp.run(['navigationService', 'permissionService', function(navigationService, permissionService) {
    permissionService.init().then(function(result) {
        navigationService.init();
    }, function(reason) {});
}]);

//关闭ajax缓存
$.ajaxSetup({
    cache: false
});

function formatGridYN(val, row) {
    return val == 0 ? "否" : "是";
}

function formatGridDate(val, row) {
    var date = new Date(val);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
}
folUtil.getTextBox();
//workflowApp.controller("MyController", function($scope, ngDialog) {
//	$scope.doOpen = function() {
//		alert('doOpen');
//		ngDialog.open({template: 'views/doOpen.html'});
//	};
//	
//	$scope.selectFile = function(file) {
//		alert('selectFile');
//	};
//
//	$scope.doNextOpen = function() {
//		alert('doNextOpen');
//	};
//});
// 这里是controller.js
var FolController = angular.module('FolController', []);
// 这里是service.js
var FolService = angular.module('FolService', []);