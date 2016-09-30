package com.sgm.dms.fol.invoice.dto;


import java.math.BigDecimal;

import com.sgm.dms.fol.common.api.domain.BaseDTO;

public class WrCollectDTO extends BaseDTO{
	
	private Long id;                   //主键id
	
	private String invoiceNo;           // 发票号

    private Long wrId;                  // 索赔单ID
	
    private String tsId;                // 事物号

    private Short valid;                // 状态（0、无效；1、有效）
    private String validStr;                // 状态（0、无效；1、有效）

    private String remark;              // 备注

    private String expressNo;           // 快递单号
    
    private Integer beginNo;           //分页查询开始No
    
	private Integer endNo;             //分页查询结束No
	
	private BigDecimal taxAmount;
	
	private String sapCode;
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo == null ? null : invoiceNo.trim();
    }

    public Long getWrId() {
        return wrId;
    }

    public void setWrId(Long wrId) {
        this.wrId = wrId;
    }
    
    public String getTsId() {
        return tsId;
    }

    public void setTsId(String tsId) {
        this.tsId = tsId == null ? null : tsId.trim();
    }

    public Short getValid() {
        return valid;
    }

    public void setValid(Short valid) {
        this.valid = valid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo == null ? null : expressNo.trim();
    }

	public Integer getBeginNo() {
		return beginNo;
	}

	public void setBeginNo(Integer beginNo) {
		this.beginNo = beginNo;
	}

	public Integer getEndNo() {
		return endNo;
	}

	public void setEndNo(Integer endNo) {
		this.endNo = endNo;
	}

	public String getValidStr() {
		return validStr;
	}

	public void setValidStr(String validStr) {
		this.validStr = validStr;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getSapCode() {
		return sapCode;
	}

	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
    
}