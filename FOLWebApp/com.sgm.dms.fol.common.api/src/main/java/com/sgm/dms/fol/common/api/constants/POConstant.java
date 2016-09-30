package com.sgm.dms.fol.common.api.constants;

public class POConstant {

	//EAI返回处理结果标志
	public static final int EAI_OUTPUT_RESULT_NOT_PROCESSED = 0;
	public static final int EAI_OUTPUT_RESULT_PROCESSING = 1;
	public static final int EAI_OUTPUT_RESULT_PROCESSED = 2;
	public static final int EAI_OUTPUT_RESULT_PROCESS_FAILED = 3;
	
	//EAI返回订单是否处理完毕
	public static final int EAI_OUTPUT_ORDER_NOT_COMPLETED = 1;
	public static final int EAI_OUTPUT_ORDER_COMPLETED = 2;
	
	//SAP索赔订单类型
	public static final String SAP_TYPE_PARTS_WARRANTY = "ZREA";
	
	//订单类型
	public static final int TYPE_REGULAR_ORDER = 101;  //常规订单
	public static final int TYPE_PAINT_DSO_HOME_STOCK_ORDER=121;  //DSO国内备货
	public static final int TYPE_PAINT_DSO_HOME_NOSTOCK_ORDER=122;  //DSO国内备货
	public static final int TYPE_PAINT_DSO_ABROAD_ORDER=123;  //DSO国外备货
	
	//订单状态
	public static final int STATUS_DRAFT = 0; 				//草稿 
	public static final int STATUS_WAIT_CONFIRM = 1; 		//待确认
	public static final int STATUS_WAIT_PAY = 2;  			//待付款（待提交）
	public static final int STATUS_SUBMITED = 3;  			//已提交（已提交，等待SO）
	public static final int STATUS_WAIT_DISTRIBUTION = 4; 	//配货中（等待Billing）
	public static final int STATUS_DELIVERED = 5;   		//已发货（部分Billing）
	public static final int STATUS_COMPLETED = 6;   		//已完成（Billing，已取消）
//	public static final int STATUS_CREATED = 1; 			//已创建
//	public static final int STATUS_SUPPLIER_CONFIRMED = 2;  //供应商单方确认
//	public static final int STATUS_BOTH_CONFIRMED = 3;  	//双方确认（待提交）
//	public static final int STATUS_WAIT_SUBMIT = 4; 		//待配货（已提交，等待SO）
//	public static final int STATUS_HAVE_DISTRIBUTION = 5;   //已配货（等待Billing）
//	public static final int STATUS_DELIVERED = 6;   		//已发货（部分Billing，Billing）
//	public static final int STATUS_SIGNED = 7;   			//已签收（已完成）
//	public static final int STATUS_CANCELED = 8;   			//已取消

	//处理状态
	public static final int PROCESSING_STATUS_TO_SUBMIT = 1;			//待提交
	public static final int PROCESSING_STATUS_WAITING_SO = 2;			//等待SO
	public static final int PROCESSING_STATUS_WAITING_BILLING = 3;		//等待Billing
	public static final int PROCESSING_STATUS_BILLING = 4;				//Billing
	public static final int PROCESSING_STATUS_TO_CANCEL = 5;			//等待取消
	public static final int PROCESSING_STATUS_CANCELLING = 6;			//向SAP申请取消
	public static final int PROCESSING_STATUS_CANCELED = 7;				//已取消
	public static final int PROCESSING_STATUS_SUBMIT_FAIL = 8;			//订单提交失败
	public static final int PROCESSING_STATUS_ORDER_FAIL =9;			//订单处理失败
	public static final int PROCESSING_STATUS_PARTLY_BILLING = 10;		//部分Billing
	
	public static final int NOT_BACK_ORDER = 0;
	public static final int IS_BACK_ORDER = 1;
	
	public static final int NULL_INT = Integer.MIN_VALUE;
	public static final long NULL_LONG = Long.MIN_VALUE;
	public static final double NULL_DOUBLE = Double.NEGATIVE_INFINITY;
	
	//储备金类型（整车，配件）
	public static final int RESERVE_TYPE_CAR=102;      //整车
	public static final int RESERVE_TYPE_PARTS=101;    //配件
	
	//用户类型
	public static final int USER_TYPE_SGM=1;      //SGM
	public static final int USER_TYPE_DEALER=2;    //客户
	
	public static final int VALID_STATUS=10011001;//字典表中有效状态
	public static final int INVALID_STATUS=10011002;//字典表中无效状态
	
	public static final int TYPE_NAME_CODE_BONUS=1079;//奖金类型
	public static final int MATCH_STATE=1080;//审核状态
    public static final int BONUS_SAP_CHECK=1090;//奖金发放调用sap检查结果
    public static final int PAY_BONUS_STATE=1010;//奖金发放状态
    public static final int SERV_TYPE=1011;//销售公司类型
    public static final int DEALER_TYPE=1073;//渠道类型
    public static final int CHANGE_TYPE=1074;//变动类型
    public static final int ROLE_TYPE=1071;//角色类型
    public static final int SEX_TYPE=1012;//性别
    public static final int STATUS=1001;//状态
    
	//有效状态
	public static final int IS_STATUS=1;//有效
	
	public static final int IS_NOT_STATUS=2;//无效
	
	//文件确认状态
	public static final int FILE_NO_CHECK=0;//文件未确认
	public static final int	FILE_CHECKED=1;//文件已确认
	
	//文件确认状态
	public static final int FILE_NO_CONFIRM=0;//文件未确认
	public static final int	FILE_CONFIRMED=1;//文件已确认
	
	//奖金发放状态
	public static final int WAIT_ISSUE=1;//待发放
	public static final int ISSUE_FAIL=2;//发放失败
	public static final int ISSUE_SUC=3;//发放成功
	public static final int UN_ISSUE=4;//未发放
	public static final int ISSUE_FOL_FAIL=5;//FOL处理失败
	
	// 奖金发放调用sap检查 返回状态
	public static final int EXECUTE_SUC=1;//处理成功
	public static final int EXECUTE_FAIL=2;//处理失败
	public static final int EXECUTE_UN_DEAL=0;//待处理
	
	//奖金发放文件审核状态 (审核状态)1、待审核 ，2、重新审核,3、审核通过,4、未审核
	public static final int WAIT_MATCH_STATE=1;//待审核
	public static final int MATCH_AGAIN_STATE=2;//重新审核
	public static final int MATCH_STATE_SUC=3;//审核通过
	public static final int UN_MATCH_STATE=4;//未审核
	
	//记录状态
	public static final int BONUS_FILE_FLOW_UPLOADED=1;//已上传
	public static final int BONUS_FLIE_FLOW_BACKED=2;//已驳回
	
	//奖金发放服务返回状态
	public static final int BONUS_SERVICE_EXECUTE_SUC=0;//成功
	public static final int BONUS_SERVICE_EXECUTE_FALI=1;//失败
	
	//奖金CHECK_CODE
	public static final String BONUS_CHECK_CODE_FOL_FAILD="008";//FOL处理失败
	
    /*********上汽返回签收结果*******************/
    public static final String SQ_SIGN_BANK_TICKET_EXECUTE_SUC="PE1I0000";//处理成功
    public static final String SQ_SIGN_BANK_TICKET_EXECUTE_FAIL="PE1S9999";//处理失败
    public static final String SQ_SIGN_BANK_TICKET_EXECUTED="PE1S9998";//正在处理
    public static final String SQ_SIGN_BANK_TICKET_EXECUTE_NOT_FOUND_NUMBER="PE1S9997";//票号不存在
    
    /*****fol检查银票结果************/
    public static final String FOL_CHECK_BANK_TICKET_INIT="0";//初始化
    public static final String FOL_CHECK_BANK_TICKET_SUC="SU00";//检查通过
    public static final String FOL_CHECK_BANK_TICKET_FAIL="SU01";//检查不通过
    
    /*****SAP接收检查结果返回状态*****/
    public static final String SQ_RECEIVE_BANKTICKET_CHECK_RESULT_SUC="S";//成功
    public static final String SQ_RECEIVE_BANKTICKET_CHECK_RESULT_FAIL="F";//失败
    
	//银票审核状态
	public static final int BANK_TICKET_ADD_UN_AUDIT=1;//新增待审核
	public static final int BANK_TICKET_UPDATE_UN_AUDIT=2;//修改待审核
	public static final int BANK_TICKET_DELETE_UN_AUDIT=3;//删除待审核
	public static final int BANK_TICKET_ADD_REJECT=4;//新增驳回
	public static final int BANK_TICKET_UPDATE_REJECT=5;//修改驳回
	public static final int BANK_TICKET_DELETE_REJECT=6;//删除驳回
	public static final int BANK_TICKET_AUDIT_SUCCESS=7;//审核通过
	
	//银票业务代码
	public static final int BUSINESS_TABLE_ADD_BANK_BANK_TICKET_MAINTAIN=10;//新增银行银票额度维护
	public static final int BUSINESS_TABLE_UPDATE_BANK_BANK_TICKET_MAINTAIN=11;//修改银行银票额度维护
	public static final int BUSINESS_TABLE_ADD_DEALER_BANK_RELEVANCE=20;//新增银行特殊经销商
	public static final int BUSINESS_TABLE_DELETE_DEALER_BANK_RELEVANCE=21;//删除银行特殊经销商
	public static final int BUSINESS_TABLE_ADD_BANK_TICKET_DEADLINE=30;//新增银票期限
	public static final int BUSINESS_TABLE_DELETE_BANK_TICKET_DEADLINE=31;//删除银票期限
	public static final int BUSINESS_TABLE_UPDATE_BANK_TICKET_DEADLINE=32;//修改银票期限
	public static final int BUSINESS_TABLE_ADD_BANK_TICKET_LIMIT_AMOUNT=40;//新增银票限额
	public static final int BUSINESS_TABLE_UPDATE_BANK_TICKET_LIMIT_AMOUNT=41;//修改银票限额
	public static final int BUSINESS_TABLE_DELETE_BANK_TICKET_LIMIT_AMOUNT=42;//删除银票期限
	
	//******银票计算贴息等相关
	public static final int YEAR=360;//一年天数
	public static final String ANNUAL_INTEREST_RATE="0.0535";//年利率
	public static final String DISCOUNT_RATE="1.17";//含税率
	public static final String ADDED_TAX_RATE="0.17";//增值
		
	//银票月度贴息调用sap返回状态
	public static final String DOCUMENT_TYPE_DA="DA";//银票利息接口接收
	public static final String DOCUMENT_TYPE_BANK_TICKET_DN="DN";//银票数据接收
		
	public static final String ACCOUNTING_BORROW_CODE="09";//借方
	public static final String ACCOUNTING_LOAN__RECEIVE_DATA_CODE="19";//贷方---银票数据接收
		
	public static final String ACCOUNTING_LOAN_MONTH_CODE="50";//贷方---月度统计用到
		
	public static final String SAP_NUMBER_UNTAXAMOUNT="S926100000";//不含税sap_number
	public static final String SAP_NUMBER_ADDTAXAMOUNT="S500400106";//增值税sap_number
  
    //银票月度贴息状态
	public static final int BANK_TICKET_MONTH_ISSUE_WAIT=0;
	public static final int BANK_TICKET_MONTH_ISSUE_SUCCESS=1;
	public static final int BANK_TICKET_MONTH_ISSUE_FAIL=2;
	
	//银票月度确认状态
    public static final int BANK_TICKET_MONTH_CONFIRM_WAIT=0;
    public static final int BANK_TICKET_MONTH_CONFIRM_SUCCESS=1;
    public static final int BANK_TICKET_MONTH_CONFIRM_FAIL=2;
	
	public static final String SPECIAL_GL_W="W";
	public static final String SPECIAL_GL_G="G";//贴息
		
		
	public static final String SAP_GENERATE_BANK_TICKET_CERTIFICATE_NO_SUC="S";//成功
	public static final String SAP_GENERATE_BANK_TICKET_CERTIFICATE_NO_FAIL="F";//失败
	
	//索赔发票处理状态
	public static final int WR_ORDER_INVOICE_SGM_UN_RECEIVE=1;     //sgm未接收
	public static final int WR_ORDER_INVOICE_SGM_STAY_DEAL=2;      //sgm待处理
	public static final int WR_ORDER_INVOICE_SGM_RETURN=3;         //sgm已退回
	public static final int WR_ORDER_INVOICE_SAP_DEAL_SUCCESS=4;   //sap处理成功
	public static final int WR_ORDER_INVOICE_SAP_DEAL_FAIL=5;      //sap处理失败
	public static final int WR_ORDER_INVOICE_SGM_SUCCESS=6;      //sgm处理成功
	public static final int WR_ORDER_INVOICE_SGM_FAIL=7;      //sgm处理失败
	//索赔发票状态
	public static final short WR_INVOICE_VALID_STATUS = 1;           //有效
	public static final short WR_INVOICE_INVALID_STATUS = 0;         //无效  
	//储备金服务返回状态
    public static final int RESERVE_SERVICE_EXECUTE_SUC=0;//成功
    public static final int RESERVE_SERVICE_EXECUTE_FAIL=1;//失败

    //银票基本单位
    public static final int BANK_TICKET_AMOUNT_BASE_UNIT=10000;//万
    
    //操作类型
    public static final String OPERATION_ADD="ADD";
    public static final String OPERATION_UPDATE="UPDATE";
    public static final String OPERATION_DELETE="DELETE";
    
    //wol索赔单状态
    public static final int CLAIM_STATUS_PAYMENT = 33;//wol审核通过状态
    public static final int CLAIM_STATUS_INVOICE = 90;//索赔发票已合并状态
    
    
    public static final int RETURN_ALLOWANCE_STATUS_DLR_NOT_REPORTED = 1;//dealer待上报
    public static final int RETURN_ALLOWANCE_STATUS_DLR_REPORTED = 2;//dealer已上报
    public static final int RETURN_ALLOWANCE_STATUS_DLR_INVALID = 3;//dealer已作废
    public static final int RETURN_ALLOWANCE_STATUS_SGM_SUCCESS = 4;//SGM已同意
    public static final int RETURN_ALLOWANCE_STATUS_SGM_FAIL = 5;//SGM已退回
    public static final int RETURN_ALLOWANCE_STATUS_SAP_SUCCESS = 6;//SAP处理成功
    
    //从FOL去更新POL的退货证明的状态
    public static final int POL_RETURN_ORDER_STATUS_AVAIL = 0;//POL退货证明可用，POL退货证明未绑定FOL折让单
    public static final int POL_RETURN_ORDER_STATUS_NOT_REPORTED = 1;//POL退货证明绑定的FOL折让单状态是待上报
    public static final int POL_RETURN_ORDER_STATUS_REPORTED = 2;//POL退货证明绑定的FOL折让单状态是已上报
    public static final int POL_RETURN_ORDER_STATUS_SGM_SUCCESS = 3;//POL退货证明绑定的FOL折让单状态是SGM审批通过
    public static final int POL_RETURN_ORDER_STATUS_SGM_FAIL = 4;//POL退货证明绑定的FOL折让单状态是SGM已拒绝
    
    //SESSION中的键
    public static final String CLAIM_RETURN_ORDERS_SESSION_NAME = "CLAIM_RETURN_ORDERS_SESSION_NAME";//退货证明session中的键
    public static final String RETURN_ALLOWANCE_XML_INFO_SESSION_NAME = "RETURN_ALLOWANCE_XML_INFO_SESSION_NAME";//XML税控机session中的键
    
}
