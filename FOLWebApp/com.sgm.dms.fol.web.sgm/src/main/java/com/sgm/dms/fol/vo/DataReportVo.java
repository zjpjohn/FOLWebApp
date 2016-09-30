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
	
package com.sgm.dms.fol.vo;

import java.io.Serializable;

import com.sgm.dms.fol.common.api.domain.PageVo;


/*
 *
 * @author shenweiwei
 * TODO description
 * @date 2015年10月23日
 */

public class DataReportVo extends PageVo implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    //客户代码
    private String sapCode;
    
    //客户名称
    private String dealerName;
    
    //储备金余额
    private String reserveTotalAmount;
    
    //储备金冻结金额
    private String reserveFrozenAmount;
    
    //储备金可用余额
    private String reserveAvailAmount;
    
    //储备金余额
    private String bonusTotalAmount;
    
    //储备金冻结金额
    private String bonusFrozenAmount;
    
    //储备金可用余额
    private String bonusAvailAmount;
    

    
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


    
    public String getReserveTotalAmount() {
        return reserveTotalAmount;
    }


    
    public void setReserveTotalAmount(String reserveTotalAmount) {
        this.reserveTotalAmount = reserveTotalAmount;
    }


    
    public String getReserveFrozenAmount() {
        return reserveFrozenAmount;
    }


    
    public void setReserveFrozenAmount(String reserveFrozenAmount) {
        this.reserveFrozenAmount = reserveFrozenAmount;
    }


    
    public String getReserveAvailAmount() {
        return reserveAvailAmount;
    }


    
    public void setReserveAvailAmount(String reserveAvailAmount) {
        this.reserveAvailAmount = reserveAvailAmount;
    }


    
    public String getBonusTotalAmount() {
        return bonusTotalAmount;
    }


    
    public void setBonusTotalAmount(String bonusTotalAmount) {
        this.bonusTotalAmount = bonusTotalAmount;
    }


    
    public String getBonusFrozenAmount() {
        return bonusFrozenAmount;
    }


    
    public void setBonusFrozenAmount(String bonusFrozenAmount) {
        this.bonusFrozenAmount = bonusFrozenAmount;
    }


    
    public String getBonusAvailAmount() {
        return bonusAvailAmount;
    }


    
    public void setBonusAvailAmount(String bonusAvailAmount) {
        this.bonusAvailAmount = bonusAvailAmount;
    }

    
 
   
}
