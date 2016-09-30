//var SERVER_URL='http://dcw.saic-gm.com:8080/web.dealer/'; //服务器地址(dev)
//var FOL_SERVER_URL='http://dcw.saic-gm.com:8080/web.sgm/'; //FOL服务器地址(dev)

var SERVER_URL = 'http://dcw.saic-gm.com:7003/fol4Dealer/'; //服务器地址(qa)
var DP_SERVER_URL = 'http://dp.saic-gm.com'; //FOL服务器地址(qa)
var FOL_WORKER_SERVER_URL='https://dcw.saic-gm.com/fol4Dealer/index.html'
var FOL_UPLOAD_SERVER_URL='http://dapi.saic-gm.com/'
	
var POL_WORKER_SERVER_URL='https://dpj.saic-gm.com/pol4dealer/common/login/dealer';

var RESERVE_TYPE_CAR = "102"; //整车
var RESERVE_TYPE_PARTS = "101"; //配件

var BUSINESS_CODE_ACCESSORIES = 101; //配件
var BUSINESS_CODE_CAR = 102; //整车

var ACTION_TIMEOUT = 10000; //请求超时 (10秒)
var PROGRESS_ACTION_TIMEOUT = 1000; //请求超时 (10秒)
var SESSION_TIMEOUT = 1000 * 60 * 30; //session过期

var TAX_UNIT = 10000000; //用于前台4舍5入

//状态
var STATE = {
    DATABASE_ERROR: 9900, //数据库错误
    HTTP_ERROR: 4400, //HTTP错误
    SUCCESS: 200, //成功
    TIMEOUT: 408, //超时

    PUBLISHED: 1, //已发布
    UN_PUBLISH: 0, //未发布

    CHECKED: 1, //已签收
    UN_CHECK: 0, //未签收

    CONFIRM: 1, //已确认
    UN_CONFIRM: 0 //未确认
};
//状态的错误描述
var STATE_MSG = {
    DATABASE_ERROR_MSG: '数据库错误',
    HTTP_ERROR_MSG: '/HTTP错误'
};

//详细的错误代码
var ERROR_CODE = {
    DATABASE_OPERATION_ERROR: 9901 //数据库操作失败
};
//详细的错误描述
var ERROR_CODE_MSG = {
    DATABASE_OPERATION_ERROR_MSG: '数据库操作失败'
};

//上传请求的URL
var UPLOAD_URL = {
    returnallowance: 'reconciliation/billFile/importFile'
};

var CLAIM_STATUS = {
    WR_ORDER_INVOICE_SGM_UN_RECEIVE: 1, //sgm未接收
    WR_ORDER_INVOICE_SGM_STAY_DEAL: 2, //sgm待处理
    WR_ORDER_INVOICE_SGM_RETURN: 3, //sgm已退回
    WR_ORDER_INVOICE_SAP_DEAL_SUCCESS: 4, //sap处理成功
    WR_ORDER_INVOICE_SAP_DEAL_FAIL: 5, //sap处理失败
    WR_ORDER_INVOICE_SGM_SUCCESS: 6, //sgm处理成功
    WR_ORDER_INVOICE_SGM_FAIL: 7 //sgm处理失败
};

var RETURN_ALLOWANCE_STATUE = {
    RETURN_ALLOWANCE_STATUS_DLR_NOT_REPORTED: 1, //dealer待上报
    RETURN_ALLOWANCE_STATUS_DLR_REPORTED: 2, //dealer已上报
    RETURN_ALLOWANCE_STATUS_DLR_INVALID: 3, //dealer已作废
    RETURN_ALLOWANCE_STATUS_SGM_SUCCESS: 4, //SGM已同意
    RETURN_ALLOWANCE_STATUS_SGM_FAIL: 5, //SGM已退回
    RETURN_ALLOWANCE_STATUS_SAP_SUCCESS: 1 //SAP处理成功
}



var InvKind = {
    specialInvoice: '专用发票'
};