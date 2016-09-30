/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.api
*
* @File name : BankInfoService.java
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

import java.util.List;

import com.sgm.dms.fol.bankTicket.dto.BankInfoDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;

/*
*
* @author DELL
* TODO description
* @date 2016年1月6日
*/

public interface BankInfoService {
//
//    /*****
//     * 保存银行信息
//     */
//    public int saveBankInfo(BankInfoDTO bankInfoDTO) throws SQLException;
    
    /****
     * 根据缩写或渠道类型+缩写查询银行信息
     */
    public BankInfoDTO findBankinfo(BankInfoDTO bankInfoDTO)throws ServiceAppException;
    
//    /**
//     * 更新银行信息
//     */
//    public int updateBankInfo(BankInfoDTO bankInfoDTO) throws SQLException;
    
    /**
     * 查询出银行所有的数据
     */
    public List<BankInfoDTO> getBankInfoAllList() throws ServiceAppException;
    
}
