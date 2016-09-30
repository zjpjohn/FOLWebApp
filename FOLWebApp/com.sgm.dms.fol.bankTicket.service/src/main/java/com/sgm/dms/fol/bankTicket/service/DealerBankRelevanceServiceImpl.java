/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.service
*
* @File name : DealerBankRelevanceServiceImpl.java
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
	
package com.sgm.dms.fol.bankTicket.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.bankTicket.api.BankTicketAuditFlowService;
import com.sgm.dms.fol.bankTicket.api.DealerBankRelevanceService;
import com.sgm.dms.fol.bankTicket.domain.DealerBankRelevancePo;
import com.sgm.dms.fol.bankTicket.dto.BankInfoDTO;
import com.sgm.dms.fol.bankTicket.dto.DealerBankRelevanceDTO;
import com.sgm.dms.fol.bankTicket.repository.BankInfoDao;
import com.sgm.dms.fol.bankTicket.repository.DealerBankRelevanceDao;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.domain.DealerInfoDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.service.domains.DealerInfoPo;
import com.sgm.dms.fol.common.service.repository.DealerDao;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CommonUtils;
import com.sgm.dms.fol.common.service.utils.RSAUtil;

/*
*
* @author DELL
* TODO description
* @date 2016年1月6日
*/
@Service("DealerBankRelevanceService")
@Transactional(rollbackFor=Exception.class)
public class DealerBankRelevanceServiceImpl implements DealerBankRelevanceService{
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private DealerBankRelevanceDao dealerBankRelevanceDao;
    
    @Autowired
    private DealerDao dealerDao;
    
    @Autowired
    private BankInfoDao bankInfoDao;
    
    @Autowired
    private BankTicketAuditFlowService bankTicketAuditFlowService;
    
    /*
    *
    * @author DELL
    * @date 2016年1月6日
    * @param dealerBankRelevanceDTO
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.DealerBankRelevanceService#findDealerBankRelevanceByWhere(com.sgm.dms.fol.bankTicket.dto.DealerBankRelevanceDTO)
    */
    	
    @Override
    public List<DealerBankRelevanceDTO> findDealerBankRelevanceByWhere(DealerBankRelevanceDTO dealerBankRelevanceDTO) throws ServiceAppException {
        List<DealerBankRelevanceDTO> list=null;
        
        try {
            list=dealerBankRelevanceDao.findDealerBankRelevanceByWhere(dealerBankRelevanceDTO);
            
            if(list!=null&&list.size()>0){
                for (DealerBankRelevanceDTO dto : list) {
                    dto.setEncryptId(RSAUtil.encryptByPublicKey(dto.getId().toString()));
                }
            }

        } catch (SQLException e) {
            logger.error(e);
            throw(new ServiceAppException("银行与特殊经销商关系的数据查询失败,"+e.getMessage()));
        } catch (Exception e) {
            logger.error(e);
            throw(new ServiceAppException(e.getMessage()));
        }
        return list;
    }

    /*
    *
    * @author DELL
    * @date 2016年1月6日
    * @param dealerBankRelevanceDTO
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.DealerBankRelevanceService#addDealerBankRelevance(com.sgm.dms.fol.bankTicket.dto.DealerBankRelevanceDTO)
    */
    	
    @Override
    public int addDealerBankRelevance(DealerBankRelevanceDTO dealerBankRelevanceDTO,Long userId) throws ServiceAppException {
        int result=0;
        try {
            DealerInfoDTO dealerinfodto=new DealerInfoDTO();
            dealerinfodto.setSapCode(dealerBankRelevanceDTO.getSapCode()+"");
            
            DealerInfoPo dealerInfoPo=dealerDao.getDealerInfoBySapCode(dealerinfodto);
            if(dealerInfoPo==null){
                throw(new ServiceBizException("没有该代码的批发商"));
            }
            
            BankInfoDTO bankInfoDTO=new BankInfoDTO();
            bankInfoDTO.setBankAbbr(dealerBankRelevanceDTO.getBankAbbr());
            
            BankInfoDTO bankInfodto=bankInfoDao.findBankinfo(bankInfoDTO);
            if(bankInfodto==null){
                throw(new ServiceBizException("没有该银行信息"));
            }
            
            dealerBankRelevanceDTO.setBankId(bankInfodto.getId());
            dealerBankRelevanceDTO.setCommonAddData(userId);
            dealerBankRelevanceDTO.setCommonUpdateData(userId);
            CommonUtils.filterSpecialWord(dealerBankRelevanceDTO);
            result+=dealerBankRelevanceDao.addDealerBankRelevance(dealerBankRelevanceDTO);
            
            result+=bankTicketAuditFlowService.addBankTicketAuditFlowRecord(POConstant.BANK_TICKET_ADD_UN_AUDIT, null, POConstant.BUSINESS_TABLE_ADD_DEALER_BANK_RELEVANCE, dealerBankRelevanceDTO.getId(), userId);
            
            if(result<2){
                throw(new ServiceBizException("新增银行与特殊经销商关系失败"));
            }
            
            
        } catch (SQLException e) {
            logger.error(e);
            throw(new ServiceBizException("银行与特殊经销商关系的数据新增失败,"+e.getMessage()));
        }
        return result;
    }

    /*
    *
    * @author DELL
    * @date 2016年1月6日
    * @param dealerBankRelevanceDTO
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.DealerBankRelevanceService#deleteDealerBankRelevance(com.sgm.dms.fol.bankTicket.dto.DealerBankRelevanceDTO,java.lang.Long)
    */
    	
    @Override
    public int deleteDealerBankRelevance(DealerBankRelevanceDTO dealerBankRelevanceDTO,Long userId) throws ServiceAppException {
        int result=0;
        try {
            dealerBankRelevanceDTO.setId(Long.valueOf(RSAUtil.decryptByPrivateKey(dealerBankRelevanceDTO.getEncryptId())));
            DealerBankRelevancePo dealerBankRelevancePo=BeanMapper.map(dealerBankRelevanceDTO, DealerBankRelevancePo.class);
            dealerBankRelevancePo.setAuditStatus(POConstant.BANK_TICKET_DELETE_UN_AUDIT);
            CommonUtils.filterSpecialWord(dealerBankRelevancePo);
            result+=dealerBankRelevanceDao.updateDealerBankRelevance(dealerBankRelevancePo);
        
            result+=bankTicketAuditFlowService.addBankTicketAuditFlowRecord(POConstant.BANK_TICKET_DELETE_UN_AUDIT, null, POConstant.BUSINESS_TABLE_DELETE_DEALER_BANK_RELEVANCE, dealerBankRelevanceDTO.getId(), userId);
            if(result<=0){
                throw(new ServiceBizException("删除银行与特殊经销商关系失败"));
            }
            
        } catch (SQLException e) {
            logger.error(e);
            throw(new ServiceAppException("银行与特殊经销商关系的数据删除失败,"+e.getMessage()));
        } catch (NumberFormatException e) {
            logger.error(e);
            throw(new ServiceAppException("银行与特殊经销商关系的数据删除失败,"+e.getMessage()));
        } catch (Exception e) {
            logger.error(e);
            throw(new ServiceAppException("银行与特殊经销商关系的数据删除失败,"+e.getMessage()));
        }
        return result;
    }
    @Override
    public int deleteDealerBankRelevanceVer(DealerBankRelevanceDTO dealerBankRelevanceDTO,Long userId) throws ServiceAppException {
    	int result=0;
    	try {
    		
    		CommonUtils.filterSpecialWord(dealerBankRelevanceDTO);
    		result+=dealerBankRelevanceDao.deleteDealerBankRelevance(dealerBankRelevanceDTO);
    		
//    		result+=bankTicketAuditFlowService.addBankTicketAuditFlowRecord(POConstant.BANK_TICKET_DELETE_UN_AUDIT, null, POConstant.BUSINESS_TABLE_DELETE_DEALER_BANK_RELEVANCE, dealerBankRelevanceDTO.getId(), userId);
    		if(result<=0){
    			throw(new ServiceBizException("删除银行与特殊经销商关系失败"));
    		}
    		
    	} catch (SQLException e) {
    		logger.error(e);
    		throw(new ServiceBizException("银行与特殊经销商关系的数据删除失败,"+e.getMessage()));
    	}
    	return result;
    }

    /*
    *
    * @author DELL
    * @date 2016年1月7日
    * @param dealerBankRelevanceDTO
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.DealerBankRelevanceService#findDealerBankRelevanceCountByWhere(com.sgm.dms.fol.bankTicket.dto.DealerBankRelevanceDTO)
    */
    	
    @Override
    public int findDealerBankRelevanceCountByWhere(DealerBankRelevanceDTO dealerBankRelevanceDTO) throws ServiceAppException {
        int count=0;
        
        try {
            count=dealerBankRelevanceDao.findDealerBankRelevanceCountByWhere(dealerBankRelevanceDTO);
        } catch (SQLException e) {
            logger.error(e);
            throw(new ServiceBizException("银行与特殊经销商关系的数据查询失败,"+e.getMessage()));
        }
        return count;
    }

	@Override
	public int updateDealerBankRelevance(DealerBankRelevanceDTO dealerBankRelevanceDTO, long userId)
			throws ServiceAppException {
        DealerBankRelevancePo dealerBankRelevancePo=BeanMapper.map(dealerBankRelevanceDTO, DealerBankRelevancePo.class);

		int result = 0;
		try {

				dealerBankRelevancePo.setCommonUpdateData(userId);
				CommonUtils.filterSpecialWord(dealerBankRelevanceDTO);
	            result+=dealerBankRelevanceDao.updateDealerBankRelevance(dealerBankRelevancePo);         
	            if(result<1){
	                throw(new ServiceBizException("修改特殊经销商记录失败,失败记录ID:"+dealerBankRelevancePo.getId()));
	            	}
	       
		} catch (Exception e) {
		    logger.error(e);
            throw(new ServiceBizException(e.getMessage()));
		}
		return result;
	}

    /** 银行与特殊经销商关系审核通过
     * @author DELL
     * @date 2016年3月24日
     * @param dealerBankRelevanceDTOList
     * @param userId
     * @throws ServiceAppException
     * (non-Javadoc)
     * @see com.sgm.dms.fol.bankTicket.api.DealerBankRelevanceService#dealerBankRelevanceAuditSuccess(java.util.List, java.lang.Long)
     */	
    @Override
    public void dealerBankRelevanceAuditSuccess(List<DealerBankRelevanceDTO> dealerBankRelevanceDTOList,
                                                Long userId) throws ServiceAppException,SQLException {
        for (DealerBankRelevanceDTO dealerBankRelevanceDTO : dealerBankRelevanceDTOList) {
            int result = 0;
            try {
                dealerBankRelevanceDTO.setId(Long.valueOf(RSAUtil.decryptByPrivateKey(dealerBankRelevanceDTO.getEncryptId())));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw(new ServiceAppException(e));
            } catch (Exception e) {
                e.printStackTrace();
                throw(new ServiceAppException(e));
            }
            
            /* 设置银票业务类型 */
            Integer businessCode = null;//银票业务代码
            if(POConstant.BANK_TICKET_ADD_UN_AUDIT == dealerBankRelevanceDTO.getAuditStatus()){//审批前状态为（新增待审核）
                businessCode = POConstant.BUSINESS_TABLE_ADD_DEALER_BANK_RELEVANCE;
            }
            if(POConstant.BANK_TICKET_DELETE_UN_AUDIT == dealerBankRelevanceDTO.getAuditStatus()){//审批前状态为（删除待审核）
                businessCode = POConstant.BUSINESS_TABLE_DELETE_DEALER_BANK_RELEVANCE;
            }
            
            result+=bankTicketAuditFlowService.addBankTicketAuditFlowRecord(dealerBankRelevanceDTO.getAuditStatus(),dealerBankRelevanceDTO.getAuditMsg(), businessCode, dealerBankRelevanceDTO.getId(),userId);

            if(POConstant.BANK_TICKET_DELETE_UN_AUDIT != dealerBankRelevanceDTO.getAuditStatus()){
                dealerBankRelevanceDTO.setAuditStatus(POConstant.BANK_TICKET_AUDIT_SUCCESS);
                result+=updateDealerBankRelevance(dealerBankRelevanceDTO,userId); 
            }else{
                result+=deleteDealerBankRelevanceVer(dealerBankRelevanceDTO,userId);
            }
            
            if(result<2){
                throw new ServiceBizException("银行与特殊经销商关系审核通过失败");
            }
        }
    }

    /**
     * 银行与特殊经销商关系审核驳回
     * @author DELL
     * @date 2016年3月24日
     * @param dealerBankRelevanceDTOList
     * @param userId
     * @throws ServiceAppException
     * (non-Javadoc)
     * @see com.sgm.dms.fol.bankTicket.api.DealerBankRelevanceService#dealerBankRelevanceAuditReject(java.util.List, java.lang.Long)
     */
    @Override
    public void dealerBankRelevanceAuditReject(List<DealerBankRelevanceDTO> dealerBankRelevanceDTOList,
                                               Long userId) throws ServiceAppException,SQLException {
        for (DealerBankRelevanceDTO dealerBankRelevanceDTO : dealerBankRelevanceDTOList) {
            int result = 0;
            try {
                dealerBankRelevanceDTO.setId(Long.valueOf(RSAUtil.decryptByPrivateKey(dealerBankRelevanceDTO.getEncryptId())));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw(new ServiceAppException(e));
            } catch (Exception e) {
                e.printStackTrace();
                throw(new ServiceAppException(e));
            }
            
            //设置银票业务类型并保存流水
            
            if(dealerBankRelevanceDTO.getAuditStatus()==POConstant.BANK_TICKET_ADD_UN_AUDIT){   
                dealerBankRelevanceDTO.setAuditStatus(POConstant.BANK_TICKET_ADD_REJECT);
                result+=bankTicketAuditFlowService.addBankTicketAuditFlowRecord( dealerBankRelevanceDTO.getAuditStatus(),dealerBankRelevanceDTO.getAuditMsg(), POConstant.BUSINESS_TABLE_ADD_DEALER_BANK_RELEVANCE, dealerBankRelevanceDTO.getId(), userId);
            }else if(dealerBankRelevanceDTO.getAuditStatus()==POConstant.BANK_TICKET_DELETE_UN_AUDIT){
                dealerBankRelevanceDTO.setAuditStatus(POConstant.BANK_TICKET_DELETE_REJECT);
                result+=bankTicketAuditFlowService.addBankTicketAuditFlowRecord( dealerBankRelevanceDTO.getAuditStatus(),dealerBankRelevanceDTO.getAuditMsg(), POConstant.BUSINESS_TABLE_DELETE_DEALER_BANK_RELEVANCE, dealerBankRelevanceDTO.getId(), userId);
            }
            
            result +=updateDealerBankRelevance(dealerBankRelevanceDTO,userId);
            if(result<2){
                throw new ServiceBizException("银行与特殊经销商关系驳回失败");
            }
        }
        
    }
}
