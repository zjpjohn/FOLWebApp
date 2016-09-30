/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.api
*
* @File name : DealerBankRelevanceService.java
*
* @Author : DELL
*
* @Date : 2016年1月6日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月6日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.bankTicket.api;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bankTicket.dto.DealerBankRelevanceDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;

/*
*
* @author DELL
* TODO description
* @date 2016年1月6日
*/

public interface DealerBankRelevanceService {
    
    /**
     * 查询特殊经销商和银行关联的记录
     */
    public List<DealerBankRelevanceDTO> findDealerBankRelevanceByWhere(DealerBankRelevanceDTO dealerBankRelevanceDTO) throws ServiceAppException;

    /**
     * 查询特殊经销商和银行关联的记录总数
     */
    public int findDealerBankRelevanceCountByWhere(DealerBankRelevanceDTO dealerBankRelevanceDTO) throws ServiceAppException;
    /**
     * 新增经销商银行关系记录
     */
    public int addDealerBankRelevance(DealerBankRelevanceDTO dealerBankRelevanceDTO,Long userId) throws ServiceAppException;
    
    /**
     * 删除经销商银行关系记录
     */
    public int deleteDealerBankRelevance(DealerBankRelevanceDTO dealerBankRelevanceDTO,Long userId) throws ServiceAppException;
    /**
     * 删除经销商银行关系记录
     */
    public int deleteDealerBankRelevanceVer(DealerBankRelevanceDTO dealerBankRelevanceDTO,Long userId) throws ServiceAppException;
    /**
     * 更新经销商信息
     */
    public int updateDealerBankRelevance(DealerBankRelevanceDTO dealerBankRelevanceDTO,long userId) throws ServiceAppException;

    /**
     * 银行与特殊经销商关系审核通过
     */
    public void dealerBankRelevanceAuditSuccess(List<DealerBankRelevanceDTO> dealerBankRelevanceDTOList,Long userId) throws ServiceAppException,SQLException;
    
    /**
     * 银行与特殊经销商关系审核驳回
     */
    public void dealerBankRelevanceAuditReject(List<DealerBankRelevanceDTO> dealerBankRelevanceDTOList,Long userId) throws ServiceAppException,SQLException;
}