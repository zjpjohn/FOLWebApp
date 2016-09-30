/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : ReturnAllowanceVO.java
*
* @Author : st78sr
*
* @Date : 2016年8月8日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年8月8日    st78sr    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgm.dms.fol.common.api.domain.PageVo;

/*
*
* @author st78sr
* TODO description
* @date 2016年8月8日
*/

public class ReturnAllowanceVO extends PageVo {
    private Long id; //主键ID
    private String allowanceNo; //折让单号
    private String returnOrderNo; //退货证明单号
    private String expressNo; //快递单号
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date approveDateStart; //受理日期-开始
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date approveDateEnd; //受理日期-结束
    private int status; //处理状态
    private String sapCode; //SAPCode，在SGM端查询时使用
    private String dealerName; //经销商简称，在SGM端查询时使用
    private List<String> companyCode; //公司代码
    private String companyCodeStr; //公司代码
    private Long returnOrderId;//退货单ID
    
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return the allowanceNo
     */
    public String getAllowanceNo() {
        return allowanceNo;
    }
    
    /**
     * @param allowanceNo the allowanceNo to set
     */
    public void setAllowanceNo(String allowanceNo) {
        this.allowanceNo = allowanceNo;
    }
    
    
    
    /**
     * @return the expressNo
     */
    public String getExpressNo() {
        return expressNo;
    }

    
    /**
     * @param expressNo the expressNo to set
     */
    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    /**
     * @return the approveDateStart
     */
    public Date getApproveDateStart() {
        return approveDateStart;
    }
    
    /**
     * @param approveDateStart the approveDateStart to set
     */
    public void setApproveDateStart(Date approveDateStart) {
        this.approveDateStart = approveDateStart;
    }
    
    /**
     * @return the approveDateEnd
     */
    public Date getApproveDateEnd() {
        return approveDateEnd;
    }
    
    /**
     * @param approveDateEnd the approveDateEnd to set
     */
    public void setApproveDateEnd(Date approveDateEnd) {
        this.approveDateEnd = approveDateEnd;
    }
    
    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }
    
    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    
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

    
    public List<String> getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(List<String> companyCode) {
		this.companyCode = companyCode;
	}

	/**
     * @return the returnOrderNo
     */
    public String getReturnOrderNo() {
        return returnOrderNo;
    }

    
    /**
     * @param returnOrderNo the returnOrderNo to set
     */
    public void setReturnOrderNo(String returnOrderNo) {
        this.returnOrderNo = returnOrderNo;
    }

	public Long getReturnOrderId() {
		return returnOrderId;
	}

	public void setReturnOrderId(Long returnOrderId) {
		this.returnOrderId = returnOrderId;
	}

	public String getCompanyCodeStr() {
		return companyCodeStr;
	}

	public void setCompanyCodeStr(String companyCodeStr) {
		if(null != companyCodeStr && !"".equals(companyCodeStr)){
			String[] companyCodeArray = companyCodeStr.split(",");
			this.companyCode = new ArrayList<>();
			for (int i = 0; i < companyCodeArray.length; i++) {
				companyCode.add(companyCodeArray[i]);
			}
		}
		
		this.companyCodeStr = companyCodeStr;
	}
}
