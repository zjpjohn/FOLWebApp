/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : DataReportVo.java
*
* @Author : shenweiwei
*
* @Date : 2015年10月23日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年10月23日    shenweiwei    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.reserve.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.sgm.dms.fol.common.api.domain.BaseDTO;


/*
 *
 * @author shenweiwei
 * TODO description
 * @date 2015年10月23日
 */

public class DataReportDTO extends BaseDTO implements Serializable{

    private static final long serialVersionUID = -3744386390085424118L;

    //客户代码
    private String sapCode;
    
    //客户名称
    private String dealerName;
    
    //储备金余额
    private BigDecimal reserveTotalAmount;
    
    //储备金冻结金额
    private BigDecimal reserveFrozenAmount;
    
    //储备金可用余额
    private BigDecimal reserveAvailAmount;
    
    //储备金余额
    private BigDecimal bonusTotalAmount;
    
    //储备金冻结金额
    private BigDecimal bonusFrozenAmount;
    
    //储备金可用余额
    private BigDecimal bonusAvailAmount;

    
    /**
     * @return the sapCode
     */
    public String getSapCode() {
        return sapCode;
    }

    
    /**
     * @param sapCode the sapCode to set
     */
    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }

    
    /**
     * @return the dealerName
     */
    public String getDealerName() {
        return dealerName;
    }

    
    /**
     * @param dealerName the dealerName to set
     */
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    
 
    
    /**
     * @return the reserveFrozenAmount
     */
    public BigDecimal getReserveFrozenAmount() {
        return reserveFrozenAmount;
    }

    
    /**
     * @param reserveFrozenAmount the reserveFrozenAmount to set
     */
    public void setReserveFrozenAmount(BigDecimal reserveFrozenAmount) {
        this.reserveFrozenAmount = reserveFrozenAmount;
    }

    
    /**
     * @return the reserveAvailAmount
     */
    public BigDecimal getReserveAvailAmount() {
        return reserveAvailAmount;
    }

    
    /**
     * @param reserveAvailAmount the reserveAvailAmount to set
     */
    public void setReserveAvailAmount(BigDecimal reserveAvailAmount) {
        this.reserveAvailAmount = reserveAvailAmount;
    }

    
    /**
     * @return the bonusTotelAmount
     */
    public BigDecimal getBonusTotalAmount() {
        return bonusTotalAmount;
    }

    
    /**
     * @param bonusTotelAmount the bonusTotelAmount to set
     */
    public void setBonusTotalAmount(BigDecimal bonusTotalAmount) {
        this.bonusTotalAmount = bonusTotalAmount;
    }

    
    /**
     * @return the bonusFrozenAmount
     */
    public BigDecimal getBonusFrozenAmount() {
        return bonusFrozenAmount;
    }

    
    /**
     * @param bonusFrozenAmount the bonusFrozenAmount to set
     */
    public void setBonusFrozenAmount(BigDecimal bonusFrozenAmount) {
        this.bonusFrozenAmount = bonusFrozenAmount;
    }

    
    /**
     * @return the bonusAvailAmount
     */
    public BigDecimal getBonusAvailAmount() {
        return bonusAvailAmount;
    }

    
    /**
     * @param bonusAvailAmount the bonusAvailAmount to set
     */
    public void setBonusAvailAmount(BigDecimal bonusAvailAmount) {
        this.bonusAvailAmount = bonusAvailAmount;
    }


    /**
     * @return the reserveTotalAmount
     */
    public BigDecimal getReserveTotalAmount() {
        return reserveTotalAmount;
    }


    /**
     * @param reserveTotalAmount the reserveTotalAmount to set
     */
    public void setReserveTotalAmount(BigDecimal reserveTotalAmount) {
        this.reserveTotalAmount = reserveTotalAmount;
    }
    
    
}
