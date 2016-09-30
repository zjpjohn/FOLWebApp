/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.dealer
*
* @File name : BonusVo.java
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

import com.sgm.dms.fol.common.api.domain.PageVo;

/*
*
* @author DELL
* TODO description
* @date 2016年1月21日
*/

public class BonusVo extends PageVo implements java.io.Serializable{

    private static final long serialVersionUID = 5042885768221015022L;

    // 主键
    private String sapCode;

    // 名称
    private String dealerName;

    // 冻结资金
    private String frozenAmount;

    // 总资金
    private String totalAmount;

    // 渠道类型
    private String dealerType;

    // 渠道类型名称
    private String dealerTypeName;

    // 奖金类型
    private Integer bonusType;

    // 奖金类型名称
    private String bonusTypeName;

    // 可用资金
    private String availAmount;

    // 变化参考（bullngno或者其它）
    private String referCode;

    // 变化类型（扣款，打款，冻结，解冻或系统问题补偿等）
    private String referType;

    // 变动金额
    private String changeAmount;

    // 变动日期
    private String changeTime;

    // 变动前可用余额
    private String beforeAvailAmount;

    // 变动后余额
    private String afterChangeAmount;

    // 开始日期
    private String startTime;

    // 结束日期
    private String endTime;

    // 奖金本月余额
    private String bonusAmount;

    // 奖金上月月余额
    private String bonusLastAmount;

    // 奖金变动增加的余额
    private String bonusChangeAddAmount;

    // 奖金变动减少的余额
    private String bonusChangeSubtractAmount;

    // SAP储备金本月余额
    private String sapBonusAmount;

    // SAP储备金上月月余额
    private String sapBonusLastAmount;

    // 余额是否有差异
    private String difference;

    // 余额差异
    private String differenceAmount;

    // 开始的客户代码
    private String startSapCode;

    // 结束的客户代码
    private String endSapCode;

    // 摘要
    private String remark;

    // 业务码
    private Integer businessCode;
    private String token;
    private Integer num;
    
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
    
    public String getFrozenAmount() {
        return frozenAmount;
    }
    
    public void setFrozenAmount(String frozenAmount) {
        this.frozenAmount = frozenAmount;
    }
    
    public String getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public String getDealerType() {
        return dealerType;
    }
    
    public void setDealerType(String dealerType) {
        this.dealerType = dealerType;
    }
    
    public String getDealerTypeName() {
        return dealerTypeName;
    }
    
    public void setDealerTypeName(String dealerTypeName) {
        this.dealerTypeName = dealerTypeName;
    }
    
    public Integer getBonusType() {
        return bonusType;
    }
    
    public void setBonusType(Integer bonusType) {
        this.bonusType = bonusType;
    }
    
    public String getBonusTypeName() {
        return bonusTypeName;
    }
    
    public void setBonusTypeName(String bonusTypeName) {
        this.bonusTypeName = bonusTypeName;
    }
    
    public String getAvailAmount() {
        return availAmount;
    }
    
    public void setAvailAmount(String availAmount) {
        this.availAmount = availAmount;
    }
    
    public String getReferCode() {
        return referCode;
    }
    
    public void setReferCode(String referCode) {
        this.referCode = referCode;
    }
    
    public String getReferType() {
        return referType;
    }
    
    public void setReferType(String referType) {
        this.referType = referType;
    }
    
    public String getChangeAmount() {
        return changeAmount;
    }
    
    public void setChangeAmount(String changeAmount) {
        this.changeAmount = changeAmount;
    }
    
    public String getChangeTime() {
        return changeTime;
    }
    
    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }
    
    public String getBeforeAvailAmount() {
        return beforeAvailAmount;
    }
    
    public void setBeforeAvailAmount(String beforeAvailAmount) {
        this.beforeAvailAmount = beforeAvailAmount;
    }
    
    public String getAfterChangeAmount() {
        return afterChangeAmount;
    }
    
    public void setAfterChangeAmount(String afterChangeAmount) {
        this.afterChangeAmount = afterChangeAmount;
    }
    
    public String getStartTime() {
        return startTime;
    }
    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public String getEndTime() {
        return endTime;
    }
    
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    public String getBonusAmount() {
        return bonusAmount;
    }
    
    public void setBonusAmount(String bonusAmount) {
        this.bonusAmount = bonusAmount;
    }
    
    public String getBonusLastAmount() {
        return bonusLastAmount;
    }
    
    public void setBonusLastAmount(String bonusLastAmount) {
        this.bonusLastAmount = bonusLastAmount;
    }
    
    public String getBonusChangeAddAmount() {
        return bonusChangeAddAmount;
    }
    
    public void setBonusChangeAddAmount(String bonusChangeAddAmount) {
        this.bonusChangeAddAmount = bonusChangeAddAmount;
    }
    
    public String getBonusChangeSubtractAmount() {
        return bonusChangeSubtractAmount;
    }
    
    public void setBonusChangeSubtractAmount(String bonusChangeSubtractAmount) {
        this.bonusChangeSubtractAmount = bonusChangeSubtractAmount;
    }
    
    public String getSapBonusAmount() {
        return sapBonusAmount;
    }
    
    public void setSapBonusAmount(String sapBonusAmount) {
        this.sapBonusAmount = sapBonusAmount;
    }
    
    public String getSapBonusLastAmount() {
        return sapBonusLastAmount;
    }
    
    public void setSapBonusLastAmount(String sapBonusLastAmount) {
        this.sapBonusLastAmount = sapBonusLastAmount;
    }
    
    public String getDifference() {
        return difference;
    }
    
    public void setDifference(String difference) {
        this.difference = difference;
    }
    
    public String getDifferenceAmount() {
        return differenceAmount;
    }
    
    public void setDifferenceAmount(String differenceAmount) {
        this.differenceAmount = differenceAmount;
    }
    
    public String getStartSapCode() {
        return startSapCode;
    }
    
    public void setStartSapCode(String startSapCode) {
        this.startSapCode = startSapCode;
    }
    
    public String getEndSapCode() {
        return endSapCode;
    }
    
    public void setEndSapCode(String endSapCode) {
        this.endSapCode = endSapCode;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public Integer getBusinessCode() {
        return businessCode;
    }
    
    public void setBusinessCode(Integer businessCode) {
        this.businessCode = businessCode;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public Integer getNum() {
        return num;
    }
    
    public void setNum(Integer num) {
        this.num = num;
    }

}
