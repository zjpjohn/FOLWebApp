/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : returnallowance.service
*
* @File name : ReturnAllowanceDao.java
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
	
package com.sgm.dms.fol.returnallowance.repository;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.common.service.repository.BaseDao;
import com.sgm.dms.fol.returnallowance.domain.ReturnAllowancePO;
import com.sgm.dms.fol.returnallowance.domain.ReturnAllowancePOExample;
import com.sgm.dms.fol.returnallowance.dto.ReturnAllowanceDTO;

/*
*
* @author st78sr
* TODO description
* @date 2016年8月1日
*/

public interface ReturnAllowanceDao extends BaseDao<ReturnAllowancePO, ReturnAllowancePOExample> {
	
    //折让单-查询list(4Dealer)
    public List<ReturnAllowanceDTO> queryReturnAllowanceList4Dealer(ReturnAllowanceDTO returnAllowanceDto) throws SQLException;
    
    //折让单-查询list(4SGM)
    public List<ReturnAllowanceDTO> queryReturnAllowanceList4SGM(ReturnAllowanceDTO returnAllowanceDto) throws SQLException;
    
    //折让单-作废(4Dealer)
    public int deleteReturnAllowanceById(ReturnAllowanceDTO returnAllowanceDto);
    
    //折让单-count(4Dealer)
    public int countReturnAllowance4Dealer(ReturnAllowanceDTO returnAllowanceDto) throws SQLException;
    
    //折让单-count(4SGM)
    public int countReturnAllowance4SGM(ReturnAllowanceDTO returnAllowanceDto) throws SQLException;
}
