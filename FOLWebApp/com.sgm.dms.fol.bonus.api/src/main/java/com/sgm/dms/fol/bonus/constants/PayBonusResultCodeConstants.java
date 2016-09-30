package com.sgm.dms.fol.bonus.constants;

/*****
 * 奖金发放 Constants
 * @author zhang bao
 *
 */
public class PayBonusResultCodeConstants {
	
	//失败时：
		//001:导入记录数据类型错误或其他原因 ,
		//002:出现重复，没有导入到条件记录表,
		//003:生效日期未到,需要到时间重新提交,
		//004:新的配件奖金额度值大于0,奖金额度控制失效,
		//005:a918中不存在对应的客户记录
		//006:没有导入到条件记录表
		
		//成功时:007:成功导入到条件记录表
	
	//CODE
	public static final String PAY_BONUS_SUC="S";//校验成功
	public static final String PAY_BONUS_FAIL="F";//校验失败
	
	public static final String PAY_BONUS_DATA_TYPE_ERR="001";
	public static final String PAY_BONUS_DATA_TYPE_ERR_MSG="导入 记录数据类型错误或其他原因";
	public static final String PAY_BONUS_DATA_REPEAT="002";
	public static final String PAY_BONUS_DATA_REPEAT_MSG="出现重复,没有导入到条件记录表";
	public static final String PAY_BONUS_EFFECT_DATE_ERR="003";
	public static final String PAY_BONUS_EFFECT_DATE_ERR_MSG="生效日期未到,需到时间重新提交";
	public static final String PAY_BONUS_LIMIT_CONTROL_LAPSED_ERR="004";
	public static final String PAY_BONUS_LIMIT_CONTROL_LAPSED_ERR_MSG="新的配件奖金额度大于等于0,奖金额度控制失效";
	public static final String PAY_BONUS_DATA_NOT_FOUND="005";
	public static final String PAY_BONUS_DATA_NOT_FOUND_MSG="A918中不存在对应的条件客户记录";
	public static final String PAY_BONUS_DATA_SAVE_FAILED="006";
	public static final String PAY_BONUS_DATA_SAVE_FAILED_MSG="没有导入到条件表中";
	public static final String PAY_BONUS_SUC_CODE="007";
	public static final String PAY_BONUS_SUC_MSG="成功导入到条件记录表";
	public static final String PAY_BONUS_FOl_FAILD_CODE="008";


}
