package com.sgm.dms.fol.common.api.constants;



public class CodeConstant {
	
	public final static String PROJECT_NAME = "FOL";//本系统项目名
	
	public final static String WOL_PROJECT_NAME = "WOL";//WOL项目名
	
	
	
	//消息异常代码定义
	
	//系统异常代码定义
	/**	数据库异常 */
	public static final int DB_ERROR = 404;
	/**	文件异常 */
	public static final int FILE_ERROR = 444;
	/**	网络异常 */
	public static final int NETWORK_ERROR = 333;
	
	/**	调用储备金错误 */
	public static final int RESERVE_NETWORK_ERROR = 100501;

	//业务异常代码定义
	/**	用户不存在 */
	public static final int USER_NOT_EXIST = 401;
	//LDAP中的用户名(LDAP登录成功)
	public static final String LDAP_USER_REQUEST_STRING = "iv-user";
	
	public final static int SUCCESS = 0;//执行成功
	
	//冻结储备金信息
	public final static int FREEZE_ASC_CODE_NO_EXIST = 100101;//asccode不存在
	public final static int FREEZE_ORDERNO_EMPTY = 100102;//订单号为空
	public final static int FREEZE_RESERVE_NO_ENOUGH = 100103;//没有足够的保证金用于冻结
	public final static int FREEZE_RESERVE_ALREADY_FREEZE = 100104;//该订单已经冻结过储备金，不能重复冻结
	public final static int FREEZE_RESERVE_AMOUNT_ZERO = 100105;//冻结的资金小于等于0
	public final static int FREEZE_ORDERNO_TOO_LONG = 100106;//订单号太长

    //扣除储备金
	public final static int DEDUCT_ASC_CODE_EMPTY = 100201;//asccode空
	public final static int DEDUCT_ASC_CODE_NO_EXIST = 100202;//asccode不存在
	public final static int DEDUCT_ORDERNO_EMPTY = 100203;//订单号为空
	public final static int DEDUCT_BILLINGNO_EMPTY = 100204;//发票号为空
	public final static int DEDUCT_BILLINGNO_EXISTS = 100205;//发票号已经存在
	public final static int DEDUCT_ORDERNO_TOO_LONG = 100206;//订单号太长
	public final static int DEDUCT_BILLINGNO_TOO_LONG = 100207;//发票号太长
	public final static int DEDUCT_BILLINGNO_UPDATE_FAILED = 100208;//扣款失败，更新记录数为0。
	//释放储备金
	public final static int RELEASE_ASC_CODE_NO_EXIST = 100301;//asccode不存在
	public final static int RELEASE_FREEZERECORD_NOEXIST = 100302;//解冻的储备金不存在
	public final static int RELEASE_FREEZERECORD_ALREADY_FREEZE = 100303;//该订单已经解冻过储备金，不能重复解冻
	public final static int RELEASE_FREEZERECORD_ORDERNO_TOO_LONG = 100104;//订单号太长
	
	
	public static final int SYSTEM_ERROR = 9999;//系统错误
	
	//文件导入
	
	
	//导入文件结果状态
	public static final int IMPORT_FILE_RESULT_NOT_EXIST=1;//未检测到文件
	public static final int IMPORT_FILE_RESULT_EXIST_UPDATE=2;//检测到未发布的文件
	public static final int IMPORT_FILE_RESULT_EXIST_PUBLISH=3;//检测到已发布的文件
	
	public static final int IMPORT_FILE_RESULT_SUC=100401;//上传成功
	public static final int IMPORT_FILE_RESULT_FAILED_NO_DATE=100402;//账单日期不能为空
	public static final int IMPORT_FILE_RESULT_FAILED_ANALYSIS_ERR=100403;//读取文件出错
	public static final int IMPORT_FILE_RESULT_FAILED_PUBLISH_ERR=100404;//检测到已发布的文件
	public static final int IMPORT_FILE_RESULT_FAILED_TYPE_ERROR=100405;//上传文件类型格式不对
	
	public static final int IMPORT_INQUIRIES_RESULT_SUC=100501;//上传成功
	public static final int IMPORT_INQUIRIES_RESULT_FAILED_NO_DATE=100502;//请选择日期
	public static final int IMPORT_INQUIRIES_RESULT_FAILED_ANALYSIS_ERR=100503;//读取文件出错
	public static final int IMPORT_INQUIRIES_RESULT_FAIL=100504;//上传失败
	
	
	
	
	//发放奖金
	public static final String BONUS_FILE_BATCH_NO="0001";//初始批次号
	
	//TS_ID
	public static final String BONUS_PAY_TS_ID="FOL";
	public static final String BONUS_ISSUE_REFER_TYPE="60000000";//奖金发放
	public static final String BONUS_BUTTON_REFER_TYPE="30120100";//订单奖金扣除
	public static final String BONUS_UNFREEZE_REFER_TYPE="40120700";//订单奖金解冻
	public static final String BONUS_FREEZE_REFER_TYPE="40120500";//订单奖金冻结
	public static final String BONUS_AVAIL_RATIO="35%";//奖金使用比例
	public static final String RESERVE_AMOUNT_EX_HANDING_ADD="50000000";//储备金异常处理加款
	public static final String RESERVE_AMOUNT_EX_HANDING_SUBTRACT="50000001";//储备金异常处理减款
	public static final String BONUS_AMOUNT_EX_HANDING_ADD="50000100";//奖金异常处理加款
	public static final String BONUS_AMOUNT_EX_HANDING_SUBTRACT="50000101";//奖金异常处理加款
	public static final String WR_RESERVE_AMOUNT_ADD="30000200";//索赔订单储备金加款
	public static final String AMOUNT_EXCEPTION_HANDING_TS_ID="FOL_EX_HA";//金额异常处理TS_ID
	
	public static final int PAY_BONUS_EMPTY_BATCH_NO=100501;//批次号为空
	
	//奖金发放
	public static final int PAY_BONUS_FAIL=100502;//发放失败
	public static final int PAY_BONUS_SUC=100503;//发放成功
	
}
