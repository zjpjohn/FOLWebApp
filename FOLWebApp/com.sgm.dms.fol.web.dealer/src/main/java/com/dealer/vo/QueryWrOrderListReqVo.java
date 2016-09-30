
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : QueryWrOrderListVo.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年5月10日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月10日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.dealer.vo;

import java.util.ArrayList;
import java.util.List;

import com.sgm.dms.fol.common.api.domain.PageVo;

/**
 * @author wangfl
 * 查询未汇总的索赔单请求Vo
 * @date 2016年5月10日
 */
public class QueryWrOrderListReqVo extends PageVo {
	
	/** 开始索赔月 */
	private String startWrDate;//废弃
	/** 开始批次 */
	private String startWrBatch;
	/** 结束索赔月 */
	private String endWrDate;//废弃
	/** 结束批次 */
	private String endWrBatch;
	/** 索赔类型 */
	private List<String> wrType;
	
	public String getStartWrDate() {
		return startWrDate;
	}
	public void setStartWrDate(String startWrDate) {
		this.startWrDate = startWrDate;
	}
	public String getStartWrBatch() {
		return startWrBatch;
	}
	public void setStartWrBatch(String startWrBatch) {
		this.startWrBatch = startWrBatch != null && startWrBatch.length() == 1 ? "0" + startWrBatch : startWrBatch;
	}
	public String getEndWrDate() {
		return endWrDate;
	}
	public void setEndWrDate(String endWrDate) {
		this.endWrDate = endWrDate;
	}
	public String getEndWrBatch() {
		return endWrBatch;
	}
	public void setEndWrBatch(String endWrBatch) {
		this.endWrBatch = endWrBatch != null && endWrBatch.length() == 1 ? "0" + endWrBatch : endWrBatch;
	}
	public List<String> getWrType() {
		return wrType;
	}

	public void setWrType(List<String> wrType) {
		if (wrType == null || wrType.size() == 0)
			return;
		List<String> tempWrType = new ArrayList<String>();
		for (String wrTypeTemp : wrType) {

			if ("".equals(wrTypeTemp) || "-1".equals(wrTypeTemp))
				continue;

			String[] splitStr = wrTypeTemp.split(",");
			for (String string2 : splitStr) {
				if ("".equals(string2) || "-1".equals(string2))
					continue;
				tempWrType.add(string2);
			}

		}
		this.wrType = tempWrType == null || tempWrType.size() == 0 ? null : tempWrType;
	}
}
