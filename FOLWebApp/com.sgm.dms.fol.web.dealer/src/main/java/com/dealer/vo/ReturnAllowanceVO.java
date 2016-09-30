/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.dealer
*
* @File name : ReturnAllowanceVO.java
*
* @Author : st78sr
*
* @Date : 2016年8月1日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年8月1日    st78sr    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.dealer.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgm.dms.fol.common.api.domain.PageVo;

/*
*
* @author st78sr
* TODO description
* @date 2016年8月1日
*/

public class ReturnAllowanceVO extends PageVo {
    private int id; //主键ID
    private String allowanceNo; //折让单号
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date applyDateStart; //申请时间-开始
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date applyDateEnd; //申请时间-结束
    private int status; //处理状态
    
    /*
    *
    * @author st78sr
    * @date 2016年8月1日
    * tags
    * @return the applyDateStart
    */
    
    public Date getApplyDateStart() {
        return applyDateStart;
    }

    
    /*
    *
    * @author st78sr
    * @date 2016年8月1日
    * tags
    * @param applyDateStart the applyDateStart to set
    */
    
    public void setApplyDateStart(Date applyDateStart) {
        this.applyDateStart = applyDateStart;
    }

    
    /*
    *
    * @author st78sr
    * @date 2016年8月1日
    * tags
    * @return the applyDateEnd
    */
    
    public Date getApplyDateEnd() {
        return applyDateEnd;
    }

    
    /*
    *
    * @author st78sr
    * @date 2016年8月1日
    * tags
    * @param applyDateEnd the applyDateEnd to set
    */
    
    public void setApplyDateEnd(Date applyDateEnd) {
        this.applyDateEnd = applyDateEnd;
    }

    
    /*
    *
    * @author st78sr
    * @date 2016年8月1日
    * tags
    * @return the id
    */
    
    public int getId() {
        return id;
    }
    
    /*
    *
    * @author st78sr
    * @date 2016年8月1日
    * tags
    * @param id the id to set
    */
    
    public void setId(int id) {
        this.id = id;
    }
    
    /*
    *
    * @author st78sr
    * @date 2016年8月1日
    * tags
    * @return the allowanceNo
    */
    
    public String getAllowanceNo() {
        return allowanceNo;
    }
    
    /*
    *
    * @author st78sr
    * @date 2016年8月1日
    * tags
    * @param allowanceNo the allowanceNo to set
    */
    
    public void setAllowanceNo(String allowanceNo) {
        this.allowanceNo = allowanceNo;
    }
    
    
    /*
    *
    * @author st78sr
    * @date 2016年8月1日
    * tags
    * @return the status
    */
    
    public int getStatus() {
        return status;
    }
    
    /*
    *
    * @author st78sr
    * @date 2016年8月1日
    * tags
    * @param status the status to set
    */
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
