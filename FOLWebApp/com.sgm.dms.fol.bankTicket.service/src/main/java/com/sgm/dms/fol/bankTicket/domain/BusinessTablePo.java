/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.api
*
* @File name : BusinessTableDTO.java
*
* @Author : DELL
*
* @Date : 2016年1月6日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月6日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.bankTicket.domain;

import java.util.Date;

/*
*
* @author DELL
* TODO description
* @date 2016年1月6日
*/

public class BusinessTablePo {
    private Long id;//主键
    private String businessTableName;//业务操作的表名
    private Integer businessCode;//业务代码
    private String businessEnDesc;//业务名称英文描述
    private String businessCnDesc;//业务名称中文描述
    private Integer status;//有效性
    private String remark;//备注
    private Integer createBy;//创建人
    private Date createDate;//创建时间
    private Integer updateBy;//更新者
    private Date updateDate;//更新时间
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getBusinessTableName() {
        return businessTableName;
    }
    
    public void setBusinessTableName(String businessTableName) {
        this.businessTableName = businessTableName;
    }
    
    public Integer getBusinessCode() {
        return businessCode;
    }
    
    public void setBusinessCode(Integer businessCode) {
        this.businessCode = businessCode;
    }
    
    public String getBusinessEnDesc() {
        return businessEnDesc;
    }
    
    public void setBusinessEnDesc(String businessEnDesc) {
        this.businessEnDesc = businessEnDesc;
    }
    
    public String getBusinessCnDesc() {
        return businessCnDesc;
    }
    
    public void setBusinessCnDesc(String businessCnDesc) {
        this.businessCnDesc = businessCnDesc;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public Integer getCreateBy() {
        return createBy;
    }
    
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }
    
    public Date getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public Integer getUpdateBy() {
        return updateBy;
    }
    
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
    
    public Date getUpdateDate() {
        return updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
