/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.dealer
*
* @File name : BankInfoVo.java
*
* @Author : DELL
*
* @Date : 2016年1月21日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月21日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.dealer.vo;

import java.math.BigDecimal;

import com.sgm.dms.fol.common.api.domain.PageVo;

/*
*
* @author DELL
* TODO description
* @date 2016年1月21日
*/

public class BankInfoVo extends PageVo implements java.io.Serializable  {

    private static final long serialVersionUID = -6102851490997224760L;
    
    private Integer bankId;//银行id
    private String bankAbbr;//银行缩写
    private String bankChName;//银行中文名称
    private BigDecimal vailidAmount;//可用额度
    private BigDecimal exptdAmount;//今天到期额度
    private BigDecimal exptmrAmount;//明天到期到期额度
    private BigDecimal expaftertmrAmount;//后天到期
    
    public Integer getBankId() {
        return bankId;
    }
    
    public void setBankId(Integer bankId) {
        this.bankId = bankId;
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
    
    public BigDecimal getVailidAmount() {
        return vailidAmount;
    }
    
    public void setVailidAmount(BigDecimal vailidAmount) {
        this.vailidAmount = vailidAmount;
    }
    
    public BigDecimal getExptdAmount() {
        return exptdAmount;
    }
    
    public void setExptdAmount(BigDecimal exptdAmount) {
        this.exptdAmount = exptdAmount;
    }
    
    public BigDecimal getExptmrAmount() {
        return exptmrAmount;
    }
    
    public void setExptmrAmount(BigDecimal exptmrAmount) {
        this.exptmrAmount = exptmrAmount;
    }
    
    public BigDecimal getExpaftertmrAmount() {
        return expaftertmrAmount;
    }
    
    public void setExpaftertmrAmount(BigDecimal expaftertmrAmount) {
        this.expaftertmrAmount = expaftertmrAmount;
    }
}
