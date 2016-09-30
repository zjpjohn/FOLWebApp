package com.sgm.dms.fol.bonus.dto;

import java.math.BigDecimal;

import com.sgm.dms.fol.common.api.domain.BaseDTO;


public class BonusChangeRecordDTO  extends BaseDTO{
    
    
    // 流水号
    private Integer id;

    // 交易ID
    private String tsId;

    // 经销商代码
    private String sapCode;

    // 经销商简称
    private String dealerName;

    // 变动前的奖金金额
    private BigDecimal beforeChangeAmount;

    // 变动的奖金金额
    private BigDecimal changeAmount;

    // 变动后的奖金金额
    private BigDecimal afterChangeAmount;

    // 变化参考
    private String referCode;

    // 变化类型
    private long referType;

    // 备注
    private String remark;

    // 是否会滚 0,1
    private Integer isRollback;

    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getTsId() {
        return tsId;
    }

    
    public void setTsId(String tsId) {
        this.tsId = tsId;
    }

    
    public String getSapCode() {
        return sapCode;
    }

    
    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }

    
    public String getDealerName() {
        return dealerName;
    }

    
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    
    public BigDecimal getBeforeChangeAmount() {
		return beforeChangeAmount;
	}


	public void setBeforeChangeAmount(BigDecimal beforeChangeAmount) {
		this.beforeChangeAmount = beforeChangeAmount;
	}


	public BigDecimal getChangeAmount() {
		return changeAmount;
	}


	public void setChangeAmount(BigDecimal changeAmount) {
		this.changeAmount = changeAmount;
	}


	public BigDecimal getAfterChangeAmount() {
		return afterChangeAmount;
	}


	public void setAfterChangeAmount(BigDecimal afterChangeAmount) {
		this.afterChangeAmount = afterChangeAmount;
	}


	public String getReferCode() {
        return referCode;
    }

    
    public void setReferCode(String referCode) {
        this.referCode = referCode;
    }

    
    public long getReferType() {
        return referType;
    }

    
    public void setReferType(long referType) {
        this.referType = referType;
    }

    
    public String getRemark() {
        return remark;
    }

    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    
    public Integer getIsRollback() {
        return isRollback;
    }

    
    public void setIsRollback(Integer isRollback) {
        this.isRollback = isRollback;
    }
    
    
    

}
