/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bonus.api
*
* @File name : BonusQueryDTO.java
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
	
package com.sgm.dms.fol.bonus.dto;

import java.math.BigDecimal;

import com.sgm.dms.fol.common.api.domain.BaseDTO;

/*
*
* @author DELL
* TODO description
* @date 2016年1月21日
*/

public class BonusQueryDTO extends BaseDTO implements java.io.Serializable{

    private static final long serialVersionUID = 2124298692768010137L;

    // 主键
    private String sapCode;

    // 名称
    private String dealerName;

    // 渠道类型
    private String dealerType;
    // 储备金类型
    private Integer reserveType;

    // 总资金
    private BigDecimal totalAmount;
    // 冻结资金
    private BigDecimal frozenAmount;

    // 可用资金
    private BigDecimal availAmount;

    // 渠道类型名称
    private String dealerTypeName;

    // 储备金类型名称
    private String reserveTypeName;

    // 变化参考（bullngno或者其它）
    private String referCode;

    // 变化类型（扣款，打款，冻结，解冻或系统问题补偿等）
    private String referType;

    // 变动金额
    private BigDecimal changeAmount;

    // 变动日期
    private String changeTime;

    // 变动前可用余额
    private BigDecimal beforeAvailAmount;

    // 变动后余额
    private BigDecimal afterChangeAmount;

    // 变动的开始日期
    private String startTime;

    // 变动的结束日期
    private String endTime;

    // 储备金本月余额
    private BigDecimal reserveAmount;

    // 储备金上月月余额
    private BigDecimal reserveLastAmount;

    // 储备金变动增加的余额
    private BigDecimal reserveChangeAddAmount;

    // 储备金变动减少的余额
    private BigDecimal reserveChangeSubtractAmount;

    // SAP储备金本月余额
    private BigDecimal sapReserveAmount;

    // SAP储备金上月月余额
    private BigDecimal sapReserveLastAmount;

    // SAP奖金本月余额
    private BigDecimal sapBonusAmount;

    // SAP储备金上月月余额
    private BigDecimal sapBonusLastAmount;

    // 余额是否有差异
    private String difference;

    // 差异余额
    private BigDecimal differenceAmount;

    // 开始的客户代码
    private String startSapCode;

    // 结束的客户代码
    private String endSapCode;

    // 摘要
    private String remark;

    // 业务码
    private int businessCode;

    // 开始行
    private Integer beginNo;

    // 结束行
    private Integer endNo;

    // 查询年份
    private Integer year;

    // 查询月份
    private Integer month;
    private int num;// 序号
    
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
    
    public String getDealerType() {
        return dealerType;
    }
    
    public void setDealerType(String dealerType) {
        this.dealerType = dealerType;
    }
    
    public Integer getReserveType() {
        return reserveType;
    }
    
    public void setReserveType(Integer reserveType) {
        this.reserveType = reserveType;
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }
    
    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }
    
    public BigDecimal getAvailAmount() {
        return availAmount;
    }
    
    public void setAvailAmount(BigDecimal availAmount) {
        this.availAmount = availAmount;
    }
    
    public String getDealerTypeName() {
        return dealerTypeName;
    }
    
    public void setDealerTypeName(String dealerTypeName) {
        this.dealerTypeName = dealerTypeName;
    }
    
    public String getReserveTypeName() {
        return reserveTypeName;
    }
    
    public void setReserveTypeName(String reserveTypeName) {
        this.reserveTypeName = reserveTypeName;
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
    
    public BigDecimal getChangeAmount() {
        return changeAmount;
    }
    
    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }
    
    public String getChangeTime() {
        return changeTime;
    }
    
    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }
    
    public BigDecimal getBeforeAvailAmount() {
        return beforeAvailAmount;
    }
    
    public void setBeforeAvailAmount(BigDecimal beforeAvailAmount) {
        this.beforeAvailAmount = beforeAvailAmount;
    }
    
    public BigDecimal getAfterChangeAmount() {
        return afterChangeAmount;
    }
    
    public void setAfterChangeAmount(BigDecimal afterChangeAmount) {
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
    
    public BigDecimal getReserveAmount() {
        return reserveAmount;
    }
    
    public void setReserveAmount(BigDecimal reserveAmount) {
        this.reserveAmount = reserveAmount;
    }
    
    public BigDecimal getReserveLastAmount() {
        return reserveLastAmount;
    }
    
    public void setReserveLastAmount(BigDecimal reserveLastAmount) {
        this.reserveLastAmount = reserveLastAmount;
    }
    
    public BigDecimal getReserveChangeAddAmount() {
        return reserveChangeAddAmount;
    }
    
    public void setReserveChangeAddAmount(BigDecimal reserveChangeAddAmount) {
        this.reserveChangeAddAmount = reserveChangeAddAmount;
    }
    
    public BigDecimal getReserveChangeSubtractAmount() {
        return reserveChangeSubtractAmount;
    }
    
    public void setReserveChangeSubtractAmount(BigDecimal reserveChangeSubtractAmount) {
        this.reserveChangeSubtractAmount = reserveChangeSubtractAmount;
    }
    
    public BigDecimal getSapReserveAmount() {
        return sapReserveAmount;
    }
    
    public void setSapReserveAmount(BigDecimal sapReserveAmount) {
        this.sapReserveAmount = sapReserveAmount;
    }
    
    public BigDecimal getSapReserveLastAmount() {
        return sapReserveLastAmount;
    }
    
    public void setSapReserveLastAmount(BigDecimal sapReserveLastAmount) {
        this.sapReserveLastAmount = sapReserveLastAmount;
    }
    
    public BigDecimal getSapBonusAmount() {
        return sapBonusAmount;
    }
    
    public void setSapBonusAmount(BigDecimal sapBonusAmount) {
        this.sapBonusAmount = sapBonusAmount;
    }
    
    public BigDecimal getSapBonusLastAmount() {
        return sapBonusLastAmount;
    }
    
    public void setSapBonusLastAmount(BigDecimal sapBonusLastAmount) {
        this.sapBonusLastAmount = sapBonusLastAmount;
    }
    
    public String getDifference() {
        return difference;
    }
    
    public void setDifference(String difference) {
        this.difference = difference;
    }
    
    public BigDecimal getDifferenceAmount() {
        return differenceAmount;
    }
    
    public void setDifferenceAmount(BigDecimal differenceAmount) {
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
    
    public int getBusinessCode() {
        return businessCode;
    }
    
    public void setBusinessCode(int businessCode) {
        this.businessCode = businessCode;
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
    
    public Integer getYear() {
        return year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }
    
    public Integer getMonth() {
        return month;
    }
    
    public void setMonth(Integer month) {
        this.month = month;
    }
    
    public int getNum() {
        return num;
    }
    
    public void setNum(int num) {
        this.num = num;
    }

}
