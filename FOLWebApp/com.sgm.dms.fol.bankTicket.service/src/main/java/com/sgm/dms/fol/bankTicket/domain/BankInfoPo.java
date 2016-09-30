package com.sgm.dms.fol.bankTicket.domain;

import com.sgm.dms.fol.common.service.domains.BasePo;

/*****
 * 银行信息
*
* @author zhang bao
* TODO description
* @date 2015年12月31日
 */
public class BankInfoPo extends BasePo{
	private Long id;//主键
	private String bankAbbr;//银行缩写
	private String bankChName;//银行中文名称
	private String bankEnName;//银行英文名称
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
    public String getBankAbbr() {
        return bankAbbr;
    }
    
    public void setBankAbbr(String bankAbbr) {
        this.bankAbbr = bankAbbr;
    }
    
    public String getBankChName() {
        return bankChName;
    }
    
    public void setBankChName(String bankChName) {
        this.bankChName = bankChName;
    }
    public String getBankEnName() {
		return bankEnName;
	}
	public void setBankEnName(String bankEnName) {
		this.bankEnName = bankEnName;
	}
	
	
	
	


}
