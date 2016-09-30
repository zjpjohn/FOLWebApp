var workflowApp = angular.module('Framework', ['ngTouch', 'ngCookies',
    'common.ui.framework', 'common.ui.easyui', 'ui.router',
    'ajoslin.promise-tracker', 'FolController', 'FolService'
]);
workflowApp.run(['$rootScope', '$window', '$interval', 'navigationService', 'permissionService',
    function($rootScope, $window, $interval, navigationService, permissionService) {
        $rootScope.reserveType = [{
                code: BUSINESS_CODE_ACCESSORIES,
                codeCnDesc: '配件'
            }
            // ,{code:RESERVE_TYPE_CAR,codeCnDesc:'整车'}
        ];

        $rootScope.bonusType = [{
                code: BUSINESS_CODE_ACCESSORIES,
                codeCnDesc: '配件'
            }
            // ,{code:RESERVE_TYPE_CAR,codeCnDesc:'整车'}
        ];

        permissionService.init().then(function(result) {
            navigationService.init();
        }, function(reason) {
            console.log(reason);
        });

        $rootScope.inherit = {
            width: 'inherit',
            height: 'inherit'
        };



        //			localStorage.setItem('intervalActionNum',0);
        //			
        //			//计时累加
        //			$interval(function(){
        //				localStorage.setItem('usedtime',parseInt(localStorage.getItem('usedtime')===null?0:localStorage.getItem('usedtime'))
        //						+PROGRESS_ACTION_TIMEOUT);
        //			},PROGRESS_ACTION_TIMEOUT);
        //
        //			//刷新或者url变更的监听事件
        //			$rootScope.$on('$locationChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
        //				//登陆超时
        //				folUtil.logonTimeOut($interval,$window);
        //			});
    }
]);

workflowApp.config(function($httpProvider, $stateProvider, $locationProvider,
    navigationServiceProvider, permissionServiceProvider, $intervalProvider, $windowProvider) {
    // Console兼容性检查
    if (!window.console) {
        var methods = ["log", "debug", "info", "warn", "error", "assert",
            "dir", "dirxml", "group", "groupEnd", "time", "timeEnd",
            "count", "trace", "profile", "profileEnd"
        ];

        window.console = {};
        for (var i = 0; i < methods.length; ++i) {
            window.console[methods[i]] = function() {};
        }
    }
    //
    $httpProvider.interceptors.push('HttpInterceptor');

    //	// 定义路径，并实例化
    permissionServiceProvider.doConfig("common/users");
    //	// 菜单路由
    navigationServiceProvider.doConfig("common/menus", $stateProvider);

    // 用来配置不是菜单的状态路由
    $stateProvider
    // 储备金余额查询
        .state('findReserveAmount', {
            url: '/findReserveAmount',
            templateUrl: 'views/reserve/findReserveAmount.html'
        })
        // 储备金变动明细查询
        .state('reserveChangeDeatilInquiry', {
            url: '/reserveChangeDeatilInquiry',
            templateUrl: 'views/reserve/reserveChangeDeatilInquiry.html'
        })
        // 储备金月度明细查询
        .state('reserveMonthDeatil', {
            url: '/reserveMonthDeatil',
            templateUrl: 'views/reserve/reserveMonthDeatil.html'
        })
        // 储备金余额对账
        .state('reserveAmountReconcile', {
            url: '/reserveAmountReconcile',
            templateUrl: 'views/reserve/reserveAmountReconcile.html'
        })
        // 奖金定义
        .state('bonusDefinition', {
            url: '/bonusDefinition',
            templateUrl: 'views/bonus/bonusDefinition.html'
        })
        // 奖金定义新增
        .state('addBonusDefinition', {
            url: '/addBonusDefinition',
            templateUrl: 'views/bonus/addBonusDefinition.html'
        })
        // 奖金定义更新
        .state('updateBonusDefinition', {
            url: '/updateBonusDefinition',
            templateUrl: 'views/bonus/updateBonusDefinition.html',
            params: {
                bonusType: null
            }
        })
        // 差异明细
        .state('differenceDetail', {
            url: '/differenceDetail',
            templateUrl: 'views/bonus/differenceDetail.html'
        })
        // 重新匹配
        .state('reMatching', {
            url: '/reMatching',
            templateUrl: 'views/bonus/reMatching.html'
        })
        // 奖金重处理
        .state('bonusReastDispose', {
            url: '/bonusReastDispose',
            templateUrl: 'views/bonus/bonusReastDispose.html'
        })
        // 奖金颁发查询
        .state('findBonusIssue', {
            url: '/findBonusIssue',
            templateUrl: 'views/bonus/findBonusIssue.html'
        })
        // 奖金查询
        .state('findBonusAmount', {
            url: '/findBonusAmount',
            templateUrl: 'views/bonus/findBonusAmount.html'
        })
        // 奖金变动明细查询
        .state('bonusChangeDetailInquiry', {
            url: '/bonusChangeDetailInquiry',
            templateUrl: 'views/bonus/bonusChangeDetailInquiry.html'
        })
        // 客户对账单导入
        .state('customerBilling', {
            url: '/customerBilling',
            templateUrl: 'views/cheekAccount/customerBilling.html'
        })
        // 客户对账单导入
        .state('customerBillings', {
            url: '/customerBillings',
            templateUrl: 'views/cheekAccount/customerBillings.html'
        })
        // 客户对账单签收
        .state('reconciliationSign', {
            url: '/reconciliationSign',
            templateUrl: 'views/data/reconciliationSign.html'
        })
        // 储备金明细对账
        .state('reserveReconciliation', {
            url: '/reserveReconciliation',
            templateUrl: 'views/cheekAccount/reserveReconciliation.html'
        })
        // 退货折让单审核
        .state('returnDiscountAudit', {
            url: '/returnDiscountAudit',
            templateUrl: 'views/services/returnDiscountAudit.html'
        })
        // 退货证明处理
        .state('returnCertificateProces', {
            url: '/returnCertificateProces',
            templateUrl: 'views/services/returnCertificateProces.html'
        })
        // 系统岗位角色维护
        .state('systemPoRoMain', {
            url: '/systemPoRoMain',
            templateUrl: 'views/system/systemPositionRole.html'
        })
        // 系统岗位角色新增
        .state('systemPoRoMainsAdd', {
            url: '/systemPoRoMainsAdd',
            templateUrl: 'views/system/systemPositionRoleAdd.html'
        })
        // 系统岗位角色设定
        .state('systemPoRoMains', {
            url: '/systemPoRoMains',
            templateUrl: 'views/system/updateSystemPositionRole.html'
        })
        // 系统角色权限维护
        .state('systemRoRiMain', {
            url: '/systemRoRiMain',
            templateUrl: 'views/system/systemRoleAuthority.html'
        })
        // 系统角色权限设置
        .state('systemRoRiMains', {
            url: '/systemRoRiMains',
            templateUrl: 'views/system/systemRoleAuthoritySetting.html'
        })
        // 角色权限修改
        .state('updateRoleAuthority', {
            url: '/updateRoleAuthority',
            templateUrl: 'views/system/roleAuthorityMaintainUpdate.html'
        })
        // 系统角色权限设置添加
        .state('systemRoRiMainsAdd', {
            url: '/systemRoRiMainsAdd',
            templateUrl: 'views/system/systemRoleAuthorityAdd.html'
        })
        // 系统人员角色维护
        .state('personRole', {
            url: '/personRole',
            templateUrl: 'views/system/personRole.html'
        })
        // 系统人员角色维护
        .state('userAdd', {
            url: '/userAdd',
            templateUrl: 'views/system/userAdd.html'
        })
        // 系统人员角色维护(修改)
        .state('updataPersonRole', {
            url: '/updataPersonRole',
            templateUrl: 'views/system/updataPersonRole.html'
        })
        // 角色权限URI维护
        .state('roleAuthorityMain', {
            url: '/roleAuthorityMain',
            templateUrl: 'views/system/roleAuthorityMaintain.html'
        })
        // 角色权限URI设置
        .state('roleAuthorityMains', {
            url: '/roleAuthorityMains',
            templateUrl: 'views/system/roleAuthorityMaintainSetting.html'
        })
        // 角色权限设置URI添加
        .state('roleAuthorityMainsAdd', {
            url: '/roleAuthorityMainsAdd',
            templateUrl: 'views/system/roleAuthorityMaintainAdd.html'
        })
        // 资金余额报表
        .state('fundBalanceStatement', {
            url: '/fundBalanceStatement',
            templateUrl: 'views/data/fundBalanceStatement.html'
        })
        // 上传页面
        .state('uploadFilePage', {
            url: '/uploadFilePage',
            templateUrl: 'views/common/uploadFilePage.html',
            params: {
                pageName: null,
                data: null
            }
        })
        // 岗位切换
        .state('msg_identity', {
            url: '/msg_identity',
            templateUrl: '/msg_identity.html'
        })
        // 奖金余额对账
        .state('bonusAmountReconcile', {
            url: '/bonusAmountReconcile',
            templateUrl: 'views/bonus/bonusAmountReconcile.html'
        })
        // 奖金月度明细
        .state('bonusMonthDeatil', {
            url: '/bonusMonthDeatil',
            templateUrl: 'views/bonus/bonusMonthDeatil.html'
        })
        // 奖金余额汇总表
        .state('bonusAmountCollect', {
            url: '/bonusAmountCollect',
            templateUrl: 'views/bonus/bonusAmountCollect.html'
        })
        /**
         * 银票维护模块
         */
        //银行银票额度维护
        .state('bankTicketLimitMaintain', {
            url: '/bankTicketLimitMaintain',
            templateUrl: 'views/bankTicket/maintain/bankBankTicketLimitMaintain.html'
        })
        //新增银行银票额度
        .state('addBankTicketLimit', {
            url: '/addBankTicketLimit',
            templateUrl: 'views/bankTicket/maintain/addOrUpdateBankTicketLimit.html',
            controller: 'AddBankTicketLimitController',
            params: {
                data: {
                    maintainType: null,
                    type: null,
                    pageTitle: null
                }
            }
        })
        //修改银行银票额度
        .state('updateBankTicketLimit', {
            url: '/updateBankTicketLimit',
            templateUrl: 'views/bankTicket/maintain/addOrUpdateBankTicketLimit.html',
            controller: 'UpdateBankTicketLimitController',
            params: {
                data: {
                    maintainType: null,
                    bankBankTicketVo: null,
                    type: null,
                    pageTitle: null
                }
            }
        })
        //银行特殊经销商维护
        .state('bankSpecialDealerMaintain', {
            url: '/bankSpecialDealerMaintain',
            templateUrl: 'views/bankTicket/maintain/bankSpecialDealerMaintain.html'
        })
        //新增银行特殊经销商
        .state('addBankSpecialDealer', {
            url: '/addBankSpecialDealer',
            templateUrl: 'views/bankTicket/maintain/addBankSpecialDealerMaintain.html',
            controller: 'AddBankSpecialDealerController'
        })
        //银票期限维护
        .state('bankTicketDeadLineMaintain', {
            url: '/bankTicketDeadLineMaintain',
            templateUrl: 'views/bankTicket/maintain/bankTicketDeadLineMaintain.html'
        })
        //新增银票期限
        .state('addBankTicketDeadLineBrandOrSpecialDealer', {
            url: '/addBankTicketDeadLineBrandOrSpecialDealer',
            templateUrl: 'views/bankTicket/maintain/addBankTicketDeadLineBrandOrSpecialDealer.html',
            controller: 'AddBankTicketDeadLineMaintainController',
            params: {
                data: {
                    maintainType: null,
                    title: null
                }
            }
        })
        //修改银票期限
        .state('updateBankTicketDeadLineBrandOrSpecialDealer', {
            url: '/updateBankTicketDeadLineBrandOrSpecialDealer',
            templateUrl: 'views/bankTicket/maintain/updateBankTicketDeadLineBrandOrSpecialDealer.html',
            controller: 'UpdateBankTicketDeadLineMaintainController',
            params: {
                data: {
                    maintainType: null,
                    title: null,
                    vo: null
                }
            }
        })
        //银票限额维护
        .state('bankTicketLimitAmountMaintain', {
            url: '/bankTicketLimitAmountMaintain',
            templateUrl: 'views/bankTicket/maintain/bankTicketLimitAmountMaintain.html'
        })
        //新增银票限额维护
        .state('addBankTicketLimitAmountMaintain', {
            url: '/addBankTicketLimitAmountMaintain',
            templateUrl: 'views/bankTicket/maintain/addBankTicketLimitAmount.html',
            controller: 'AddBankTicketLimitAmountMaintainController',
            params: {
                data: {
                    maintainType: null,
                    title: null
                }
            }
        })
        //修改银票限额维护
        .state('updateBankTicketLimitAmountMaintain', {
            url: '/updateBankTicketLimitAmountMaintain',
            templateUrl: 'views/bankTicket/maintain/updateBankTicketLimitAmount.html',
            controller: 'UpdateBankTicketLimitAmountMaintainController',
            params: {
                data: {
                    maintainType: null,
                    title: null,
                    vo: null
                }
            }
        })
        //票据贴息年化利率维审核
        .state('ticketInterestRateAudit', {
            url: '/TicketInterestRateAudit',
            templateUrl: 'views/bankTicket/audit/ticketInterestRateAudit.html'
        })
        //票据贴息年化利率维维护
        .state('ticketInterestRateMaintain', {
            url: '/ticketInterestRateMaintain',
            templateUrl: 'views/bankTicket/maintain/ticketInterestRateMaintain.html'
        })
        //修改票据贴息年化利率
        .state('updateInterestRateHis', {
            url: '/updateInterestRateHis',
            templateUrl: 'views/bankTicket/maintain/updateInterestRateHis.html',
            controller: 'UpdateInterestRateController',
            params: {
                data: {
                    maintainType: null,
                    title: null,
                    vo: null
                }
            }
        })
        //閾惰閾剁エ棰濆害瀹℃牳
        .state('bankBankTicketLimitVerify', {
            url: '/bankBankTicketLimitVerify',
            templateUrl: 'views/bankTicket/audit/bankBankTicketLimitAudit'
        })
        //閾惰鐗规畩缁忛攢鍟嗗鏍�
        .state('bankSpecialDealerVerify', {
            url: '/bankSpecialDealerVerify',
            templateUrl: 'views/bankTicket/audit/bankSpecialDealerAudit.html'
        })
        //閾剁エ鏈熼檺瀹℃牳
        .state('bankTicketDeadLineVerify', {
            url: '/bankTicketDeadLineVerify',
            templateUrl: 'views/bankTicket/audit/bankTicketDeadLingAudit.html'
        })
        //閾剁エ闄愰瀹℃牳
        .state('bankTicketLimitVerify', {
            url: '/bankTicketLimitVerify',
            templateUrl: 'views/bankTicket/audit/bankTicketLimitAmountAudit.html'
        })
        //票据贴息查询
        .state('findBankTicketInterest', {
            url: 'findBankTicketInterest',
            templateUrl: 'views/bankTicket/business/findBankTicketInterest.html',
        })
        //银票明细查询
        .state('findBankTicketDeatil', {
            url: 'findBankTicketDeatil',
            templateUrl: 'views/bankTicket/business/findBankTicketDeatil.html',
        })
        //银票汇总查询
        .state('findBankTicketCollect', {
            url: 'findBankTicketCollect',
            templateUrl: 'views/bankTicket/business/findBankTicketCollect.html',
        })
        //异常处理
        .state('exceptionHanding', {
            url: 'exceptionHanding',
            templateUrl: 'views/exceptionhanding/exceptionHandingManager.html'
        })
        //金额异常处理
        .state('amountExHanding', {
            url: 'amountExHanding',
            templateUrl: 'views/exceptionhanding/addAmountExceptionHanding.html',
            controller: 'addAmtExHandingController',
            params: {
                dealerInfo: null
            }
        })
        //索赔发票状态查询
        .state('chaimInvoiceStatus', {
            url: 'chaimInvoiceStatus',
            templateUrl: 'views/wrOrder/chaimInvoiceStatus.html'
        })
        //快递单号扫描发票
        .state('expressScanInvoice', {
            url: 'expressScanInvoice',
            templateUrl: 'views/wrOrder/expressScanInvoice.html'
        })
        //SAP每日财务凭证查询
        .state('financialCertificate', {
            url: 'financialCertificate',
            templateUrl: 'views/wrOrder/financialCertificate.html'
        })
        //FOL-SAP接口状态查询
        .state('interfaceStatus', {
            url: 'interfaceStatus',
            templateUrl: 'views/wrOrder/interfaceStatus.html'
        })
        //经销商提交超时未处理发票查询
        .state('overtimeInvoice', {
            url: 'overtimeInvoice',
            templateUrl: 'views/wrOrder/overtimeInvoice.html'
        })
        /**
         * 退货折让单
         */
        //查询经销商
        .state('findDealer', {
            url: 'findDealer',
            templateUrl: 'views/returnallowance/finddealer.html',
            params: {
                stateName: null
            }
        })
        //折让单扫描管理
        .state('allowanceScanManager', {
            url: 'allowanceScanManager',
            templateUrl: 'views/returnallowance/allowancescanmanager.html',
            params: {
                data: null
            }
        })
        //折让单拒绝
        .state('allowanceReject', {
            url: 'allowanceReject',
            templateUrl: 'views/returnallowance/allowancereject.html',
            params: {
                data: null
            }
        });
});

// 关闭ajax缓存
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

// workflowApp.controller("MyController", function($scope, ngDialog) {
// $scope.doOpen = function() {
// alert('doOpen');
// ngDialog.open({template: 'views/doOpen.html'});
// };
//	
// $scope.selectFile = function(file) {
// alert('selectFile');
// };
//
// $scope.doNextOpen = function() {
// alert('doNextOpen');
// };
// });
// 这里是controller.js
var FolController = angular.module('FolController', []);
// 这里是service.js
var FolService = angular.module('FolService', []);