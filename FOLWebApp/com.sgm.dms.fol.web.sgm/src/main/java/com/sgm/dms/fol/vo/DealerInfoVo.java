/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : DealerInfoVo.java
*
* @Author : DELL
*
* @Date : 2016年1月22日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月22日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.vo;

import com.sgm.dms.fol.common.api.domain.PageVo;

/*
*
* @author DELL
* TODO description
* @date 2016年1月22日
*/

public class DealerInfoVo extends PageVo implements java.io.Serializable{

    private static final long serialVersionUID = 1275301880015077134L;

    private String sapCode;
    private String officalDealerId;
    private String dealerCode;
    private String dealerFullname;
    private String dealerName;
    private String email;
    private String dealerType;
    private String dealerTypeName;
    private String sapDealerType;
    private String sapDealerTypeName;
    private String statusCode;
    
    public String getSapCode() {
        return sapCode;
    }
    
    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }
    
    public String getOfficalDealerId() {
        return officalDealerId;
    }
    
    public void setOfficalDealerId(String officalDealerId) {
        this.officalDealerId = officalDealerId;
    }
    
    public String getDealerCode() {
        return dealerCode;
    }
    
    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }
    
    public String getDealerFullname() {
        return dealerFullname;
    }
    
    public void setDealerFullname(String dealerFullname) {
        this.dealerFullname = dealerFullname;
    }
    
    public String getDealerName() {
        return dealerName;
    }
    
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
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
    
    public String getSapDealerType() {
        return sapDealerType;
    }
    
    public void setSapDealerType(String sapDealerType) {
        this.sapDealerType = sapDealerType;
    }
    
    public String getSapDealerTypeName() {
        return sapDealerTypeName;
    }
    
    public void setSapDealerTypeName(String sapDealerTypeName) {
        this.sapDealerTypeName = sapDealerTypeName;
    }
    
    public String getStatusCode() {
        return statusCode;
    }
    
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

}
