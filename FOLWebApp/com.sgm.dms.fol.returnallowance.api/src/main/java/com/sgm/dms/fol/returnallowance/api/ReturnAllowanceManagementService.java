/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : returnallowance.api
*
* @File name : ReturnAllowanceManagementService.java
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
	
package com.sgm.dms.fol.returnallowance.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.returnallowance.dto.AllowanceInvoiceInfoDto;
import com.sgm.dms.fol.returnallowance.dto.ReturnAllowanceDTO;
import com.sgm.dms.fol.returnallowance.dto.ReturnOrderDto;
import com.sgm.dms.fol.returnallowance.restclient.dto.ReturnOrdersRespDto.PartClaimReturnOrderDto;

/*
*
* @author st78sr
* TODO description
* @date 2016年8月1日
*/

public interface ReturnAllowanceManagementService {

    //折让单-查询list(4Dealer)
    public List<ReturnAllowanceDTO> queryReturnAllowanceList4Dealer(ReturnAllowanceDTO returnAllowanceDto) throws ServiceBizException, SQLException;
    
    //折让单-作废(4Dealer)
    public int deleteReturnAllowanceById(ReturnAllowanceDTO returnAllowanceDto);
    
    //折让单-查询list(4SGM)
    public List<ReturnAllowanceDTO> queryReturnAllowanceList4SGM(ReturnAllowanceDTO returnAllowanceDto);

    //根据退货证明ID，查询退货证明ReturnOrder的明细(4SGM)
    public ReturnOrderDto queryReturnOrderDetailByRoId(Long returnOrderId) throws ServiceBizException, SQLException;
    
    //根据退货证明ID，查询退货证明ReturnOrder对应的发票明细(4SGM)
    public List<AllowanceInvoiceInfoDto> queryInvoiceDetailByRoId(Long returnOrderId) throws ServiceBizException, SQLException;
    
    //折让单-拒绝(4SGM)
    public void rejectReturnAllowance4SGM(Integer allowanceId,String returnOrderNo,String sapCode, String approveMsg) throws ServiceBizException,SQLException,IOException;
    
    //折让单-检查折让单状态是否符合要求
    public void checkReturnAllowanceStatus4SGM(List<ReturnAllowanceDTO> returnAllowanceDTOList) throws ServiceBizException, SQLException;
    
    //折让单-检查折让单状态是否符合要求(指定折让单，指定状态)
    public void checkReturnAllowanceStatus4Dealer(ReturnAllowanceDTO returnAllowanceDTO,int returnAllowanceStatus) throws ServiceBizException, SQLException;
    
    //count(4SGM)
    public int countReturnAllowance4SGM(ReturnAllowanceDTO returnAllowanceDto) throws ServiceBizException, SQLException;
    
    //count(4Dealer)
    public int countReturnAllowance4Dealer(ReturnAllowanceDTO returnAllowanceDto) throws ServiceBizException, SQLException;
    
    /**
     * 上报折让单
     * @author wangfl
     * @date 2016年8月9日
     * @param id
     */
	void reportReturnAllowance(Long id)  throws IOException;

	/**
	 * 开票service
	 * @author wangfl
	 * @date 2016年8月11日
	 * @param id
	 * @param approveMsg
	 * @return
	 */
	void billing(Long id, String approveMsg) throws IOException;

	/**
	 * 生成开票文件service
	 * @author wangfl
	 * @date 2016年8月11日
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public String getBillingFileContent(String ids) throws Exception;
	
	/**
	 * 保存退货证明
	 * @author wangfl
	 * @date 2016年8月18日
	 * @param returnOrderNo
	 * @return
	 */
	ReturnOrderDto savaReturnOrder(String returnOrderNo, PartClaimReturnOrderDto returnOrderInfo);

	/**
	 * 保存折让单信息
	 * @author wangfl
	 * @date 2016年8月18日
	 * @param dto
	 * @return 折让单主键id
	 */
	public Long saveReturnAllowance(ReturnAllowanceDTO dto, PartClaimReturnOrderDto returnOrderInfo) throws Exception ;

	/**
	 * 修改折让单信息
	 * @author wangfl
	 * @date 2016年8月18日
	 * @param map
	 */
	public void updateReturnAllowance(ReturnAllowanceDTO map, PartClaimReturnOrderDto returnOrderInfo)  throws Exception;
	
	/**
	 * 获取折让单流水号
	 * @author wangfl
	 * @date 2016年8月18日
	 * @return
	 */
	String getAllowanceNoFlowNo();
	
	/**
	 * 根据id获取折让单
	 * @author wangfl
	 * @date 2016年8月18日
	 * @param id
	 * @return
	 */
	ReturnAllowanceDTO getReturnAllowanceById(Long id);
	
	/**
	 * 根据id获取退货证明
	 * @author wangfl
	 * @date 2016年8月18日
	 * @param id
	 * @return
	 */
	ReturnOrderDto getReturnOrderById(Long id);

	/**
	 * 校验折让单新增和更新的信息
	 * @author wangfl
	 * @date 2016年8月22日
	 * @param returnOrderNo
	 * @param filedId
	 */
	void validReturnAllowance(ReturnOrderDto returnOrderDto, String filedId, String reqBillNo) throws IOException, Exception;

	/**
	 * 修改折让单状态
	 * @param ids
	 * @param returnAllowanceStatusSapSuccess
	 */
	public void updateReturnAllowanceStatus(String ids, int status);
}
