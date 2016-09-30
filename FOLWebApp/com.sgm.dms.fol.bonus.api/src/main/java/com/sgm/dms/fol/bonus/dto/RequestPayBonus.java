package com.sgm.dms.fol.bonus.dto;

import java.math.BigDecimal;

/****
 * 奖金发放验证接口参数
 * @author zhang bao
 *
 */
public class RequestPayBonus implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String z_vkorg;//销售公司
	private String z_kunnr;//客户代码
	private String z_datab;//生效日期
	private BigDecimal z_komxwrt;//金额
	private Integer z_id;//序号
	public String getZ_vkorg() {
		return z_vkorg;
	}
	public void setZ_vkorg(String z_vkorg) {
		this.z_vkorg = z_vkorg;
	}
	public String getZ_kunnr() {
		return z_kunnr;
	}
	public void setZ_kunnr(String z_kunnr) {
		this.z_kunnr = z_kunnr;
	}
	public String getZ_datab() {
		return z_datab;
	}
	public void setZ_datab(String z_datab) {
		this.z_datab = z_datab;
	}
	public BigDecimal getZ_komxwrt() {
		return z_komxwrt;
	}
	public void setZ_komxwrt(BigDecimal z_komxwrt) {
		this.z_komxwrt = z_komxwrt;
	}
	public Integer getZ_id() {
		return z_id;
	}
	public void setZ_id(Integer z_id) {
		this.z_id = z_id;
	}
	
	

}
