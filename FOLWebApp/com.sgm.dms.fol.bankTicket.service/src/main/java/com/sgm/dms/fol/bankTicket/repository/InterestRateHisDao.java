/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.service
*
* @File name : InterestRateHisDao.java
*
* @Author : DELL
*
* @Date : 2016年1月26日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月26日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.bankTicket.repository;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bankTicket.domain.InterestRateHisPo;
import com.sgm.dms.fol.bankTicket.dto.TicketInterestRateDTO;

/*
*
* @author DELL
* TODO description
* @date 2016年1月26日
*/

public interface InterestRateHisDao {

    /**
     * get current interest rate history
     */
    public InterestRateHisPo findInterestRateHis() throws SQLException;
    
    /**
     * get current interest rate history
     */
    public InterestRateHisPo findInterestRateCurrentHis() throws SQLException;
    /**
     * 票据贴息年化利率查询
     * @return
     * @throws SQLException
     */
    public List<TicketInterestRateDTO> getInterestRateHis(TicketInterestRateDTO ticketInterestRateDTO) throws SQLException; 
    /**
     * 票据贴息当前年化利率
     * @param ticketInterestRateDTO
     * @return
     * @throws SQLException
     */
    public List<TicketInterestRateDTO> getInterestRateCurrent(TicketInterestRateDTO ticketInterestRateDTO) throws SQLException; 
    /**
     * 票据贴息年化利率审核查询
     * @param ticketInterestRateDTO
     * @return
     * @throws SQLException
     */
    public List<TicketInterestRateDTO> getInterestRateAudit(TicketInterestRateDTO ticketInterestRateDTO) throws SQLException; 
    
    /**
     * 票据贴息年化利率审核
     * @param ticketInterestRateDTO
     * @return
     * @throws SQLException
     */
    public int updateInterestRateCurAudit(TicketInterestRateDTO ticketInterestRateDTO) throws SQLException;
    /**
     * 票据贴息年化利率(新增修改记录)
     * @param ticketInterestRateDTO
     * @return
     * @throws SQLException
     */
    public int updateInterestRateHis(TicketInterestRateDTO ticketInterestRateDTO) throws SQLException;
}
