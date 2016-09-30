package com.sgm.dms.fol.bankTicket.dto;

import com.sgm.dms.fol.common.api.domain.BaseDTO;

/*****
 * 银行信息
*
* @author zhang bao
* TODO description
* @date 2015年12月31日
 */
public class BankInfoDTO  extends BaseDTO{
	private Long id;//主键
	private String bankAbbr;//银行缩写
	private String bankChName;//银行中文名称
	private String bankEnName;//银行英文名称
	private Integer status;//状态
	private String remark;//备注
	private Integer dealerType;

	public String getBankAbbr() {
		return bankAbbr;
	}
	public void setBankAbbr(String bankAbbr) {
		this.bankAbbr = bankAbbr;
	}

	public String getBankEnName() {
		return bankEnName;
	}
	public void setBankEnName(String bankEnName) {
		this.bankEnName = bankEnName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    public String getBankChName() {
        return bankChName;
    }
    public void setBankChName(String bankChName) {
        this.bankChName = bankChName;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getDealerType() {
        return dealerType;
    }
    public void setDealerType(Integer dealerType) {
        this.dealerType = dealerType;
    }
  
}
