
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.sgm
 *
 * @File name : SaveRemarkReqVo.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年5月20日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月20日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldRefIds;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldSign;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;

/**
 * @author wangfl
 * 保存备注请求Vo
 * @date 2016年5月20日
 */
@SGMValidationResource
public class SaveRemarkReqVo {
	/**发票号*/
	@NotBlank(message="发票号不能为空")
	private String invoiceNo;
	/**备注*/
	@SGMValidationFieldRefIds(seq=1)
	private String remark;
	@SGMValidationFieldSign
    private String sign;
	@NotBlank(message="经销商代码不能为空")
	private String sapCode;
	
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
}
