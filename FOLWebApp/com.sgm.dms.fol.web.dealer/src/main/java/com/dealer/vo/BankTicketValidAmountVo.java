/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.dealer
*
* @File name : BankTicketValidAmountVo.java
*
* @Author : DELL
*
* @Date : 2016年2月21日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年2月21日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.dealer.vo;

import com.sgm.dms.fol.common.api.domain.PageVo;

/*
*
* @author DELL
* TODO description
* @date 2016年2月21日
*/

public class BankTicketValidAmountVo extends PageVo implements java.io.Serializable{

    private static final long serialVersionUID = 6502040009590523483L;

    private Integer bankId;//银行id
    private String bankAbbr;//银行缩写
    private String bankChName;//银行中文名称
    private String availAmount;//可用额度
    private String exptdAmount;//今天到期额度
    private String exptmrAmount;//明天到期到期额度
    private String expaftertmrAmount;//后天到期
    private Integer beginNo;
    private Integer endNo;
    
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
    
    public String getAvailAmount() {
        return availAmount;
    }
    
    public void setAvailAmount(String availAmount) {
        this.availAmount = availAmount;
    }
    
    public String getExptdAmount() {
        return exptdAmount;
    }
    
    public void setExptdAmount(String exptdAmount) {
        this.exptdAmount = exptdAmount;
    }
    
    public String getExptmrAmount() {
        return exptmrAmount;
    }
    
    public void setExptmrAmount(String exptmrAmount) {
        this.exptmrAmount = exptmrAmount;
    }
    
    public String getExpaftertmrAmount() {
        return expaftertmrAmount;
    }
    
    public void setExpaftertmrAmount(String expaftertmrAmount) {
        this.expaftertmrAmount = expaftertmrAmount;
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
    
    
}
