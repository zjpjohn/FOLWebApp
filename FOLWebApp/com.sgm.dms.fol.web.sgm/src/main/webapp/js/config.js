var POL_SERVER_URL = 'http://universe.saic-gm.com/sgms/pol4sgm/'; //服务器地址(QA)
var FOL_SERVER_URL = 'http://universe.saic-gm.com/fol4SGM'; //FOL生产服务器地址

var RESERVE_TYPE_CAR = "102"; //整车
var BUSINESS_CODE_ACCESSORIES = 101; //配件
var BUSINESS_CODE_CAR = 102; //整车

var ACTION_TIMEOUT = 10000; //请求超时 (10秒)
var PROGRESS_ACTION_TIMEOUT = 1000; //PROGRESS请求触发的时间
var SESSION_TIMEOUT = 1000 * 60 * 30; //session过期

//状态
var STATE = {
    SERVER_ERROR: 500, //后台服务错误
    SUCCESS: 200, //成功
    TIMEOUT: 408, //超时

    PUBLISHING: 2, //发布中
    PUBLISHED: 1, //已发布
    UN_PUBLISH: 0, //未发布

    CHECKED: 1, //已签收
    UN_CHECK: 0, //未签收

    //奖金审核
    WAIT_AUDIT: 1, //待审核
    REAST_AUDIT: 2, //重新审核
    SUCCESS_AUDIT: 3, //审核通过

    //奖金发放
    WAIT_ISSUE: 1, //待发放
    FAIL_ISSUE: 2, //发放失败
    SUCCESS_ISSUE: 3, //发放成功

    //银票审核
    BANK_TICKET_ADD_UN_AUDIT: 1, //新增待审核
    BANK_TICKET_UPDATE_UN_AUDIT: 2, //修改待审核
    BANK_TICKET_DELETE_UN_AUDIT: 3, //删除待审核
    BANK_TICKET_ADD_REJECT: 4, //新增驳回
    BANK_TICKET_UPDATE_REJECT: 5, //修改驳回
    BANK_TICKET_DELETE_REJECT: 6, //删除驳回
    BANK_TICKET_AUDIT_SUCCESS: 7, //审核通过
    INTEREST_RATE_UN_AUDIT: 0, //票据贴息年利率待审核
    INTEREST_RATE_REJECT: 2, //票据贴息年利率驳回
    //银票发放
    WAIT_BANK_TICKET_ISSUE: 0, //待发放
    SUCCESS_BANK_TICKET_ISSUE: 1, //发放成功
};

//类型
var TYPE = {
    BONUS_UPLOAD: 1, //奖金上传
    BONUS_AUDIT: 2, //奖金审核
    BONUS_ISSUE: 3, //奖金发放

    RESERVE_EX_HANDING_ADD_AMOUNT: 50000000, //储备金异常处理加款
    RESERVE_EX_HANDING_SUBSTACT_AMOUNT: 50000001, //储备金异常处理减款
    BONUS_EX_HANDING_ADD_AMOUNT: 50000100, //奖金异常处理加款
    BONUS_EX_HANDING_SUBSTACT_AMOUNT: 50000101 //奖金异常处理减款
};

//状态的错误描述
var STATE_MSG = {
    SERVER_ERROR: '后台服务错误，请联系相关人员'
};

//详细的错误代码
var ERROR_MSG = {
    DATABASE_ERROR: {
        DATABASE_OPERATION_ERROR: { ERROR_CODE: 901, ERROR_MSG: '数据库操作失败' } //数据库操作失败
    },
    FILE_ERROR: {
        UPLOAD_FILE_ERROR: { ERROR_CODE: 100402, ERROR_MSG: '上传文件失败' }
    }
};

//上传请求的URL
var UPLOAD_URL = {
    bill: 'reconciliation/billFile/importFile',
    bonusIssueVersion: 'bonus/bonusissue/importFile',
    bankTicketInterest: 'bankTicket/businessInquiries/importInterestAmount'
};

var COMPANY_CODE = {
    SH: "9K70",
    TJ: "9K75",
    CQ: "9K76",
    WH: "9kkk"
};

var InvKind = {
    specialInvoice: '专用发票'
};