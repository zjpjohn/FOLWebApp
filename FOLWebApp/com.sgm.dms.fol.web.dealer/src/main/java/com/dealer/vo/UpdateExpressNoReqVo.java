
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : UpdateExpressNoReqVo.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年5月16日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月16日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.dealer.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.sgm.dms.fol.common.api.domain.PageVo;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldRefIds;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldSign;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;

/**
 * @author wangfl
 * 批量更新快递单号Vo
 * @date 2016年5月16日
 */
@SGMValidationResource
public class UpdateExpressNoReqVo extends PageVo{
	/**事务号，多个事务号用逗号","分开*/
	@NotBlank(message="事务号不能为空")
	private String tsId;
	/**快递单号*/
	@SGMValidationFieldRefIds(seq=1)
	private String expressNo;
	@SGMValidationFieldSign
	private String sign;
	
	public String getTsId() {
		return tsId;
	}
	public void setTsId(String tsId) {
		this.tsId = tsId;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}

}
