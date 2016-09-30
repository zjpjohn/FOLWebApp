
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : AllowanceReportReqVo.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年8月9日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年8月9日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.dealer.vo;

import javax.validation.constraints.NotNull;

/**
 * 折让单上报请求Vo
 * @author wangfl
 * @date 2016年8月9日
 */

public class AllowanceReportReqVo {
	@NotNull(message = "id不能为空")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
