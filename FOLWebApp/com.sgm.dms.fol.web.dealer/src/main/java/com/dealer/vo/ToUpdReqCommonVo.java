
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : UpdateReqCommonVo.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年8月22日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年8月22日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.dealer.vo;

import com.sgm.dms.fol.common.api.domain.BaseVo;

/**
 * 进入修改页面公共请求Vo
 * @author wangfl
 * @date 2016年8月22日
 */

public class ToUpdReqCommonVo extends BaseVo {
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
