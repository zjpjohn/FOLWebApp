package com.sgm.dms.fol.bonus.dto;

/****
 * 奖金发放验证接口
 * @author zhang bao
 *
 */
public class ResponsePayBonus {
	
	private String zz_vkorg;//销售公司
	private String zz_kunnr;//客户代码
	private Integer zz_id;//序号
	private String z_number;//成功(S)或者失败(F)
	
	//失败时：
	//001:导入记录数据类型错误或其他原因 ,
	//002:出现重复，没有导入到条件记录表,
	//003:生效日期未到,需要到时间重新提交,
	//004:新的配件奖金额度值大于0,奖金额度控制失效,
	//005:a918中不存在对应的客户记录
	//006:没有导入到条件记录表
	
	//成功时:007:成功导入到条件记录表
	private String z_message_code;//失败code()
	public String getZz_vkorg() {
		return zz_vkorg;
	}
	public void setZz_vkorg(String zz_vkorg) {
		this.zz_vkorg = zz_vkorg;
	}
	public String getZz_kunnr() {
		return zz_kunnr;
	}
	public void setZz_kunnr(String zz_kunnr) {
		this.zz_kunnr = zz_kunnr;
	}
	public Integer getZz_id() {
		return zz_id;
	}
	public void setZz_id(Integer zz_id) {
		this.zz_id = zz_id;
	}
	public String getZ_number() {
		return z_number;
	}
	public void setZ_number(String z_number) {
		this.z_number = z_number;
	}
	public String getZ_message_code() {
		return z_message_code;
	}
	public void setZ_message_code(String z_message_code) {
		this.z_message_code = z_message_code;
	}
	
	

}
