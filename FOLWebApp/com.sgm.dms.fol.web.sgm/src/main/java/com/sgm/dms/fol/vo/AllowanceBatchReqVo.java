
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.sgm
 *
 * @File name : AllowanceBatchReqVo.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年8月11日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年8月11日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.vo;

import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldRefIds;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldSign;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;

/**
 * 折让单批量操作请求Vo
 * @author wangfl
 * @date 2016年8月11日
 */
@SGMValidationResource
public class AllowanceBatchReqVo {
	/**主键ID*/
	@SGMValidationFieldRefIds(seq=1)
	private Long id;
	
	@SGMValidationFieldRefIds(seq=2)
    private String returnOrderNo;//退货证明单号
	
	@SGMValidationFieldRefIds(seq=3)
    private String sapCode; //SAP代码
	
	/**受理意见*/
	private String approveMsg; 
	
	@SGMValidationFieldSign
    private String sign;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApproveMsg() {
		return approveMsg;
	}

	public void setApproveMsg(String approveMsg) {
		this.approveMsg = approveMsg;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getReturnOrderNo() {
		return returnOrderNo;
	}

	public void setReturnOrderNo(String returnOrderNo) {
		this.returnOrderNo = returnOrderNo;
	}

	public String getSapCode() {
		return sapCode;
	}

	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	
}
