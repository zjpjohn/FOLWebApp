
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : InvoiceFollowDro.java
 *
 * @Author : Lujinglei
 *
 * @Date : 2016年5月10日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月10日    Lujinglei    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */
	
package com.dealer.dro;

import java.math.BigDecimal;

/**
 *
 * @author Lujinglei
 * TODO description
 * @date 2016年5月10日
 */

public class InvoiceFollowDro {
    
    private Integer num;

    private String wrType;
    private BigDecimal partCost;    // 配件费用
    
    private BigDecimal labourCost;  // 工时费用
    
    private BigDecimal otherCost;   // 其它费用
    private BigDecimal tax;         // 税金
    private BigDecimal linetotal;   // 不含税金额
    private BigDecimal gross;       // 含税金额


    
    /**
     * @return the num
     */
    public Integer getNum() {
        return num;
    }

    
    /**
     * @param num the num to set
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    
    /**
     * @return the wrType
     */
    public String getWrType() {
        return wrType;
    }

    
    /**
     * @param wrType the wrType to set
     */
    public void setWrType(String wrType) {
        this.wrType = wrType;
    }

    
    /**
     * @return the partCost
     */
    public BigDecimal getPartCost() {
        return partCost;
    }

    
    /**
     * @param partCost the partCost to set
     */
    public void setPartCost(BigDecimal partCost) {
        this.partCost = partCost;
    }

    
    /**
     * @return the labourCost
     */
    public BigDecimal getLabourCost() {
        return labourCost;
    }

    
    /**
     * @param labourCost the labourCost to set
     */
    public void setLabourCost(BigDecimal labourCost) {
        this.labourCost = labourCost;
    }

    
    /**
     * @return the otherCost
     */
    public BigDecimal getOtherCost() {
        return otherCost;
    }

    
    /**
     * @param otherCost the otherCost to set
     */
    public void setOtherCost(BigDecimal otherCost) {
        this.otherCost = otherCost;
    }

    
    /**
     * @return the tax
     */
    public BigDecimal getTax() {
        return tax;
    }

    
    /**
     * @param tax the tax to set
     */
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    
    /**
     * @return the gross
     */
    public BigDecimal getGross() {
        return gross;
    }

    
    /**
     * @param gross the gross to set
     */
    public void setGross(BigDecimal gross) {
        this.gross = gross;
    }

    
    /**
     * @return the linetotal
     */
    public BigDecimal getLinetotal() {
        return linetotal;
    }

    
    /**
     * @param linetotal the linetotal to set
     */
    public void setLinetotal(BigDecimal linetotal) {
        this.linetotal = linetotal;
    }


  
}
