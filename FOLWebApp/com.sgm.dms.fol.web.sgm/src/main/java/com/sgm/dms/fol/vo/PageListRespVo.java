
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : PageListQueryVo.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年8月18日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年8月18日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.vo;

import java.util.List;

import com.sgm.dms.fol.common.api.domain.BaseVo;

/**
 * 分页查询响应Vo
 * @author wangfl
 * @date 2016年8月30日
 * @param <T>
 */

public class PageListRespVo<T> extends BaseVo {
	private List<T> rows;//数据列表
	private Integer total;//分页total
	
	
	public PageListRespVo() {}
	
	public PageListRespVo(List<T> rows, Integer total) {
		this.rows = rows;
		this.total = total;
	}
	
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
}
