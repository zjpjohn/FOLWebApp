/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.service
*
* @File name : DealerBankRelevanceDao.java
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
	
package com.sgm.dms.fol.bankTicket.repository;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bankTicket.domain.DealerBankRelevancePo;
import com.sgm.dms.fol.bankTicket.dto.DealerBankRelevanceDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;

/*
*
* @author DELL
* TODO description
* @date 2016年1月6日
*/

public interface DealerBankRelevanceDao {
    
    /**
     * 查询特殊经销商和银行关联的记录
     */
    public List<DealerBankRelevanceDTO> findDealerBankRelevanceByWhere(DealerBankRelevanceDTO dealerBankRelevanceDTO) throws SQLException;
    
    /**
     * 查询特殊经销商和银行关联的记录
     */
    public int findDealerBankRelevanceCountByWhere (DealerBankRelevanceDTO dealerBankRelevanceDTO) throws SQLException;
    /**
     * 新增经销商银行关系记录
     */
    public int addDealerBankRelevance(DealerBankRelevanceDTO dealerBankRelevanceDTO) throws SQLException;
    
    /**
     * 删除经销商银行关系记录
     */
    public int deleteDealerBankRelevance(DealerBankRelevanceDTO dealerBankRelevanceDTO) throws SQLException;
    /**
     * 更新经销商信息
     */
    public int updateDealerBankRelevance(DealerBankRelevancePo dealerBankRelevancePo) throws ServiceBizException;
}
