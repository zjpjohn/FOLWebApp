
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : invoice.service
 *
 * @File name : WrCollectService.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年5月12日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月12日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.invoice.api;

import java.util.List;

import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.invoice.dto.StayInvoiceDTO;
import com.sgm.dms.fol.invoice.dto.WrCollectDTO;
import com.sgm.dms.fol.invoice.dto.WrOrderDTO;

/**
 * @author wangfl
 * 索赔月度财务汇总service
 * @date 2016年5月12日
 */

public interface WrCollectService {
	
	/**
	 * 
	 * @author wangfl
	 * 索赔财务汇总
	 * @date 2016年5月12日
	 * @param wrOrderList
	 * @throws ServiceBizException
	 */
	void collectWrOrder(List<WrOrderDTO> wrOrderList) throws ServiceBizException;

	/**
	 * 
	 * @author wangfl
	 * 分页查询代开发票编辑列表
	 * @date 2016年5月16日
	 * @param dto
	 * @return
	 */
	List<StayInvoiceDTO> getStayInvoicePageList(StayInvoiceDTO dto);
	
	/**
	 * 
	 * @author wangfl
	 * 得到总的代开发票编辑数量
	 * @date 2016年5月16日
	 * @return
	 */
	int getStayInvoiceTotalNum(StayInvoiceDTO dto);
	
	/**
	 * 
	 * @author wangfl
	 * 根据tsId批量更新快递单号
	 * @date 2016年5月16日
	 * @param dto
	 * @return
	 */
	void bathchUpdateExpressNo(WrCollectDTO dto);
	
	/**
	 * 
	 * @author wangfl
	 * 根据tsId获取发票索赔单列表明细
	 * @date 2016年5月16日
	 * @param tsId
	 * @return
	 */
	List<WrOrderDTO> getWrOrderListByTsId(String tsId);

	/**
	 * 
	 * @author wangfl
	 * 根据tsId删除待开发票并取消汇总
	 * @date 2016年5月17日
	 * @param tsId
	 */
	void delStayInvoiceByTsId(String tsId);

	/**
	 * 
	 * @author wangfl
	 * 批量保存待开发票
	 * @date 2016年5月17日
	 * @param dtoList
	 */
	void saveStayInvoice(List<WrCollectDTO> dtoList);

	/**
	 * 
	 * @author wangfl
	 * 待开发票上报
	 * @date 2016年5月17日
	 * @param tsId 多个tsId用逗号分开
	 */
	void reportStayInvoice(String tsId, Long reporterId, String sapCode);
}
