/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.service
*
* @File name : BankTicketLimitAmountServiceImpl.java
*
* @Author : DELL
*
* @Date : 2016年1月14日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月14日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.bankTicket.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.bankTicket.api.BankTicketAuditFlowService;
import com.sgm.dms.fol.bankTicket.api.BankTicketLimitAmountService;
import com.sgm.dms.fol.bankTicket.domain.BankTicketLimitAmountPo;
import com.sgm.dms.fol.bankTicket.dto.BankTicketLimitAmountDTO;
import com.sgm.dms.fol.bankTicket.repository.BankTicketLimitAmountDao;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.constants.TypeConstant;
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
* @date 2016年1月14日
*/
@Service("BankTicketLimitAmountService")
@Transactional(rollbackFor=Exception.class)
public class BankTicketLimitAmountServiceImpl implements BankTicketLimitAmountService{
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private BankTicketLimitAmountDao bankTicketLimitAmountDao;
    
    @Autowired
    private DealerDao dealerDao;
    
    @Autowired
    private BankTicketAuditFlowService bankTicketAuditFlowService;
    /*
    *
    * @author DELL
    * @date 2016年1月14日
    * @param bankTicketLimitAmountDTO
    * @return
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketLimitAmountService#findBankTicketLimitAmountByWhere(com.sgm.dms.fol.bankTicket.dto.BankTicketLimitAmountDTO)
    */
    	
    @Override
    public List<BankTicketLimitAmountDTO> findBankTicketLimitAmountByWhere(BankTicketLimitAmountDTO bankTicketLimitAmountDTO) throws ServiceAppException{
        List<BankTicketLimitAmountDTO> bankTicketLimitAmountlist;
        try {
            bankTicketLimitAmountlist=bankTicketLimitAmountDao.findBankTicketLimitAmountByWhere(bankTicketLimitAmountDTO);
            if(bankTicketLimitAmountlist!=null&&bankTicketLimitAmountlist.size()>0){
                for (BankTicketLimitAmountDTO dto : bankTicketLimitAmountlist) {
                    dto.setEncryptId(RSAUtil.encryptByPublicKey(dto.getId().toString()));
                    dto.setMoneyDisplay(dto.getAmountLimit());
                    dto.setAmountLimit(dto.getAmountLimit().multiply(BigDecimal.valueOf(POConstant.BANK_TICKET_AMOUNT_BASE_UNIT)));
                    
                }
            }
        }catch (Exception e) {
            logger.error(e);
            throw(new ServiceAppException(e.getMessage()));
        }
        return bankTicketLimitAmountlist;
    }

    /*
    *
    * @author DELL
    * @date 2016年1月15日
    * @param bankTicketLimitAmountDTO
    * @return
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketLimitAmountService#addBankTicketLimitAmount(com.sgm.dms.fol.bankTicket.dto.BankTicketLimitAmountDTO)
    */
    	
    @Override
    public void addBankTicketLimitAmount(BankTicketLimitAmountDTO bankTicketLimitAmountDTO,Long userId) throws ServiceAppException,SQLException{
        BankTicketLimitAmountPo bankTicketLimitAmountPo=BeanMapper.map(bankTicketLimitAmountDTO, BankTicketLimitAmountPo.class);
        bankTicketLimitAmountPo.setAuditStatus(POConstant.BANK_TICKET_ADD_UN_AUDIT);
        int result=0;
        
        if(bankTicketLimitAmountPo.getAmountLimit()==null||bankTicketLimitAmountPo.getAmountLimit().doubleValue()<=0){
            throw(new ServiceBizException("票据不能为空,或者不能小于等于0"));
        }
        
        if(bankTicketLimitAmountPo.getEffectDate()==null){
            throw(new ServiceBizException("生效日期不能为空"));
        }
        
        BankTicketLimitAmountDTO dto;
        if(bankTicketLimitAmountPo.getType().intValue()==TypeConstant.BANK_TICKET_LIMIT_AMOUNT_TYPE){
            dto=new BankTicketLimitAmountDTO();
            dto.setType(TypeConstant.BANK_TICKET_LIMIT_AMOUNT_TYPE);
            int count=bankTicketLimitAmountDao.findBankTicketLimitAmountCountByWhere(dto);
            if(count>0){
                throw(new ServiceBizException("已有银票限额记录，不能有重复"));
            }
        }else{
            DealerInfoDTO dealerinfodto=new DealerInfoDTO();
            dealerinfodto.setSapCode(bankTicketLimitAmountPo.getSapCode());
            
            DealerInfoPo dealerInfoPo=dealerDao.getDealerInfoBySapCode(dealerinfodto);
            if(dealerInfoPo==null){
                throw(new ServiceBizException("该客户代码的经销商不存在"));
            }
            
            dto=new BankTicketLimitAmountDTO();
            dto.setSapCode(bankTicketLimitAmountPo.getSapCode());
            dto.setBrandId(bankTicketLimitAmountPo.getBrandId());
            dto.setDealerType(bankTicketLimitAmountPo.getDealerType());
            int count=bankTicketLimitAmountDao.findBankTicketLimitAmountCountByWhere(dto);
            if(count>0){
                throw(new ServiceBizException("同渠道同品牌同经销商不能有重复"));
            }
        }
        
        bankTicketLimitAmountPo.setCommonAddData(userId);
        bankTicketLimitAmountPo.setCommonUpdateData(userId);
        
        result+=bankTicketLimitAmountDao.addBankTicketLimitAmount(bankTicketLimitAmountPo);
        result+=bankTicketAuditFlowService.addBankTicketAuditFlowRecord(POConstant.BANK_TICKET_ADD_UN_AUDIT, null, POConstant.BUSINESS_TABLE_ADD_BANK_TICKET_LIMIT_AMOUNT, bankTicketLimitAmountPo.getId(), userId);
        
        if(result<2){
            throw(new ServiceBizException("add to database fail"));
        }
    }

    /*
    *
    * @author DELL
    * @date 2016年1月15日
    * @param bankTicketLimitAmountDTO
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketLimitAmountService#findBankTicketLimitAmountCountByWhere(com.sgm.dms.fol.bankTicket.dto.BankTicketLimitAmountDTO)
    */
    	
    @Override
    public int findBankTicketLimitAmountCountByWhere(BankTicketLimitAmountDTO bankTicketLimitAmountDTO) throws ServiceAppException {
        int count;
        try {
            count=bankTicketLimitAmountDao.findBankTicketLimitAmountCountByWhere(bankTicketLimitAmountDTO);
        } catch (SQLException e) {
            logger.error(e);
            throw(new ServiceAppException(e.getMessage()));
        }
        return count;
    }

    /*
    *
    * @author DELL
    * @date 2016年1月15日
    * @param bankTicketLimitAmountDTO
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketLimitAmountService#updateBankTicketLimitAmountById(com.sgm.dms.fol.bankTicket.dto.BankTicketLimitAmountDTO)
    */
    	
    @Override
    public void updateBankTicketLimitAmountById(BankTicketLimitAmountDTO bankTicketLimitAmountDTO,Long userId) throws ServiceAppException,SQLException {
        bankTicketLimitAmountDTO.setAuditStatus(POConstant.BANK_TICKET_UPDATE_UN_AUDIT);
        
        try {
            bankTicketLimitAmountDTO.setId(Long.valueOf(RSAUtil.decryptByPrivateKey(bankTicketLimitAmountDTO.getEncryptId())));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ServiceAppException(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceAppException(e);
        }
        
        BankTicketLimitAmountPo bankTicketLimitAmountPo=BeanMapper.map(bankTicketLimitAmountDTO, BankTicketLimitAmountPo.class);
        
        BankTicketLimitAmountDTO tempDTO=new BankTicketLimitAmountDTO();
        tempDTO.setId(bankTicketLimitAmountPo.getId());
        tempDTO.setBeginNo(0);
        tempDTO.setEndNo(10);
        int count = bankTicketLimitAmountDao.findBankTicketLimitAmountCountByWhere(tempDTO);
        
        if(count<=0){
            throw(new ServiceBizException("该条记录已不存在"));
        }
        
        List<BankTicketLimitAmountDTO> list=bankTicketLimitAmountDao.findBankTicketLimitAmountByWhere(tempDTO);
        
        if(list!=null && list.size()>0){
            BankTicketLimitAmountDTO resultdto=list.get(0);
            
            if(resultdto.getAuditStatus().intValue()==POConstant.BANK_TICKET_ADD_UN_AUDIT||
                    resultdto.getAuditStatus().intValue()==POConstant.BANK_TICKET_UPDATE_UN_AUDIT||
                    resultdto.getAuditStatus().intValue()==POConstant.BANK_TICKET_DELETE_UN_AUDIT){
                throw(new ServiceBizException("当前状态是待审核不能给予修改"));
            }
        }else{
            throw(new ServiceBizException("修改的对象已不存在"));
        }

        bankTicketLimitAmountPo.setCommonUpdateData(userId);
        int result=0;
        result+=bankTicketLimitAmountDao.updateBankTicketLimitAmountById(bankTicketLimitAmountPo);
        result+=bankTicketAuditFlowService.addBankTicketAuditFlowRecord(POConstant.BANK_TICKET_UPDATE_UN_AUDIT, null, POConstant.BUSINESS_TABLE_UPDATE_BANK_TICKET_LIMIT_AMOUNT, bankTicketLimitAmountPo.getId(), userId);
        
        if(result<2){
            throw(new ServiceBizException("update bankticket fail"));
        }
    }

    /*
    *
    * @author DELL
    * @date 2016年1月15日
    * @param bankTicketLimitAmountDTO
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketLimitAmountService#deleteBankTicketLimitAmountById(com.sgm.dms.fol.bankTicket.dto.BankTicketLimitAmountDTO)
    */
    	
    @Override
    public void deleteBankTicketLimitAmountById(BankTicketLimitAmountDTO bankTicketLimitAmountDTO,Long userId) throws ServiceAppException,SQLException {
        
        bankTicketLimitAmountDTO.setAuditStatus(POConstant.BANK_TICKET_DELETE_UN_AUDIT);
        
        try {
            bankTicketLimitAmountDTO.setId(Long.valueOf(RSAUtil.decryptByPrivateKey(bankTicketLimitAmountDTO.getEncryptId())));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ServiceAppException(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceAppException(e);
        }
        
        BankTicketLimitAmountPo bankTicketLimitAmountPo=BeanMapper.map(bankTicketLimitAmountDTO, BankTicketLimitAmountPo.class);
        
        bankTicketLimitAmountDTO.setAuditStatus(null);
        bankTicketLimitAmountDTO.setBeginNo(0);
        bankTicketLimitAmountDTO.setEndNo(10);
        
        int count = bankTicketLimitAmountDao.findBankTicketLimitAmountCountByWhere(bankTicketLimitAmountDTO);
        
        if(count<=0){
            throw(new ServiceBizException("该条记录已不存在"));
        }
        
        List<BankTicketLimitAmountDTO> list=bankTicketLimitAmountDao.findBankTicketLimitAmountByWhere(bankTicketLimitAmountDTO);
        
        if(list!=null && list.size()>0){
            BankTicketLimitAmountDTO resultdto=list.get(0);
            
            if(resultdto.getAuditStatus().intValue()==POConstant.BANK_TICKET_ADD_UN_AUDIT||
                    resultdto.getAuditStatus().intValue()==POConstant.BANK_TICKET_UPDATE_UN_AUDIT||
                    resultdto.getAuditStatus().intValue()==POConstant.BANK_TICKET_DELETE_UN_AUDIT){
                throw new ServiceBizException("当前状态是待审核状态不能给予删除");
            }
        }else{
            throw(new ServiceBizException("删除的对象已不存在"));
        }
        
        bankTicketLimitAmountPo.setCommonUpdateData(userId);
        
        int result=0;
        result+=bankTicketLimitAmountDao.updateBankTicketLimitAmountById(bankTicketLimitAmountPo);
        result+=bankTicketAuditFlowService.addBankTicketAuditFlowRecord(POConstant.BANK_TICKET_DELETE_UN_AUDIT, null, POConstant.BUSINESS_TABLE_DELETE_BANK_TICKET_LIMIT_AMOUNT, bankTicketLimitAmountPo.getId(), userId);
        
        if(result<2){
            throw(new ServiceBizException("delete bankticket fail"));
        }

    }
    
    @Override
	public int deleteBankTicketLimitAmountVerById(BankTicketLimitAmountDTO bankTicketLimitAmountDTO, Long userId)
			throws ServiceAppException {
    	 BankTicketLimitAmountPo bankTicketLimitAmountPo=BeanMapper.map(bankTicketLimitAmountDTO, BankTicketLimitAmountPo.class);
    	 int result=0;
         try {
             bankTicketLimitAmountDTO.setAuditStatus(null);
             bankTicketLimitAmountDTO.setBeginNo(0);
             bankTicketLimitAmountDTO.setEndNo(10);
             
             int count = bankTicketLimitAmountDao.findBankTicketLimitAmountCountByWhere(bankTicketLimitAmountDTO);
         
             if(count<=0){
                 throw(new ServiceBizException("该条记录已不存在"));
             }
             
             List<BankTicketLimitAmountDTO> list=bankTicketLimitAmountDao.findBankTicketLimitAmountByWhere(bankTicketLimitAmountDTO);
             
             if(list!=null && list.size()>0){
                 BankTicketLimitAmountDTO resultdto=list.get(0);
                 
                 if(resultdto.getAuditStatus().intValue()!=POConstant.BANK_TICKET_DELETE_UN_AUDIT){
                     throw(new ServiceBizException("当前状态不是审批待审核状态不能审核通过"));
                 }
             }else{
                 throw(new ServiceBizException("删除的对象已不存在"));
             }
             
             bankTicketLimitAmountPo.setCommonUpdateData(userId);
             
             result+=bankTicketLimitAmountDao.deleteBankTicketLimitAmountById(bankTicketLimitAmountPo);
//             result+=bankTicketAuditFlowService.addBankTicketAuditFlowRecord(POConstant.BANK_TICKET_DELETE_UN_AUDIT, null, POConstant.BUSINESS_TABLE_DELETE_BANK_TICKET_LIMIT_AMOUNT, bankTicketLimitAmountPo.getId(), userId);
             
             if(result<1){
                 throw(new ServiceBizException("delete bankticket fail"));
             }
         } catch (SQLException e) {
             logger.error(e);
             throw(new ServiceAppException(e.getMessage()));
         }
        
         return result;
	}

	@Override
	public int updateBankTicketLimitAmount(BankTicketLimitAmountDTO bankTicketLimitAmountDTO, Long userId)
			throws ServiceAppException {
		int result = 0;
		  BankTicketLimitAmountPo bankTicketLimitAmountPo=BeanMapper.map(bankTicketLimitAmountDTO, BankTicketLimitAmountPo.class);
		  try {
			  bankTicketLimitAmountPo.setCommonUpdateData(userId);
			  CommonUtils.filterSpecialWord(bankTicketLimitAmountDTO);
                result+=bankTicketLimitAmountDao.updateBankTicketLimitAmountById(bankTicketLimitAmountPo);
                if(result<1){
                    throw(new ServiceBizException("修改银票期限记录失败,失败记录ID:"+bankTicketLimitAmountPo.getId()));
                }
            
			
		} catch (Exception e) {
		    logger.error(e);
			throw(new ServiceAppException(e.getMessage()));
		}

		
		return result;
	}

    /*
    *
    * @author DELL
    * @date 2016年3月24日
    * @param bankTicketLimitAmountDTOList
    * @param userId
    * @throws ServiceAppException
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketLimitAmountService#bankTicketLimitAmountAuditSuccess(java.util.List, java.lang.Long)
    */
    	
    @Override
    public void bankTicketLimitAmountAuditSuccess(List<BankTicketLimitAmountDTO> bankTicketLimitAmountDTOList,
                                                  Long userId) throws ServiceAppException, SQLException {
        for (BankTicketLimitAmountDTO bankTicketLimitAmountDTO : bankTicketLimitAmountDTOList) {
            try {
                bankTicketLimitAmountDTO.setId(Long.valueOf(RSAUtil.decryptByPrivateKey(bankTicketLimitAmountDTO.getEncryptId())));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw new ServiceAppException(e);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceAppException(e);
            }
            
            int result = 0;
            
            Integer businessCode = null;//银票业务代码
            if(POConstant.BANK_TICKET_ADD_UN_AUDIT == bankTicketLimitAmountDTO.getAuditStatus()){//审批前状态为（新增待审核）
                businessCode = POConstant.BUSINESS_TABLE_ADD_BANK_TICKET_LIMIT_AMOUNT;
            }else if(POConstant.BANK_TICKET_UPDATE_UN_AUDIT == bankTicketLimitAmountDTO.getAuditStatus()){//审批前状态为（修改待审核）
                businessCode = POConstant.BUSINESS_TABLE_UPDATE_BANK_TICKET_LIMIT_AMOUNT;
            }else if(POConstant.BANK_TICKET_DELETE_UN_AUDIT == bankTicketLimitAmountDTO.getAuditStatus()){//审批前状态为（删除待审核）
                businessCode = POConstant.BUSINESS_TABLE_DELETE_BANK_TICKET_LIMIT_AMOUNT;
            }
            
            result += bankTicketAuditFlowService.addBankTicketAuditFlowRecord(bankTicketLimitAmountDTO.getAuditStatus(),
                                                                              bankTicketLimitAmountDTO.getAuditMsg(), businessCode, bankTicketLimitAmountDTO.getId(), userId);
            if(POConstant.BANK_TICKET_DELETE_UN_AUDIT != bankTicketLimitAmountDTO.getAuditStatus()){
                bankTicketLimitAmountDTO.setAuditStatus(POConstant.BANK_TICKET_AUDIT_SUCCESS);
                result += updateBankTicketLimitAmount(bankTicketLimitAmountDTO, userId);
            }else{
                bankTicketLimitAmountDTO.setAuditStatus(POConstant.BANK_TICKET_AUDIT_SUCCESS);
                result +=deleteBankTicketLimitAmountVerById(bankTicketLimitAmountDTO, userId);
            }
            
            if (result<2) {
                throw new ServiceBizException("银票限额审核失败");
            }
        }
        
    }

    /*
    *
    * @author DELL
    * @date 2016年3月24日
    * @param bankTicketLimitAmountDTOList
    * @param userId
    * @throws ServiceAppException
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketLimitAmountService#bankTicketLimitAmountAuditReject(java.util.List, java.lang.Long)
    */
    	
    @Override
    public void bankTicketLimitAmountAuditReject(List<BankTicketLimitAmountDTO> bankTicketLimitAmountDTOList,
                                                 Long userId) throws ServiceAppException, SQLException {
        for (BankTicketLimitAmountDTO bankTicketLimitAmountDTO : bankTicketLimitAmountDTOList) {
            try {
                bankTicketLimitAmountDTO.setId(Long.valueOf(RSAUtil.decryptByPrivateKey(bankTicketLimitAmountDTO.getEncryptId())));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw new ServiceAppException(e);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceAppException(e);
            }
            
            int result = 0;
            
            /* 设置银票业务类型和审核状态 */
            Integer businessCode = null;//银票业务代码
            if(POConstant.BANK_TICKET_ADD_UN_AUDIT == bankTicketLimitAmountDTO.getAuditStatus()){//审批前状态为（新增待审核）
                businessCode = POConstant.BUSINESS_TABLE_ADD_BANK_TICKET_LIMIT_AMOUNT;
                bankTicketLimitAmountDTO.setAuditStatus(POConstant.BANK_TICKET_ADD_REJECT);
            }
            if(POConstant.BANK_TICKET_UPDATE_UN_AUDIT == bankTicketLimitAmountDTO.getAuditStatus()){//审批前状态为（修改待审核）
                businessCode = POConstant.BUSINESS_TABLE_UPDATE_BANK_TICKET_LIMIT_AMOUNT;
                bankTicketLimitAmountDTO.setAuditStatus(POConstant.BANK_TICKET_UPDATE_REJECT);
            }
            if(POConstant.BANK_TICKET_DELETE_UN_AUDIT == bankTicketLimitAmountDTO.getAuditStatus()){//审批前状态为（删除待审核）
                businessCode = POConstant.BUSINESS_TABLE_DELETE_BANK_TICKET_LIMIT_AMOUNT;
                bankTicketLimitAmountDTO.setAuditStatus(POConstant.BANK_TICKET_DELETE_REJECT);
            }

            result += bankTicketAuditFlowService.addBankTicketAuditFlowRecord(bankTicketLimitAmountDTO.getAuditStatus(), bankTicketLimitAmountDTO.getAuditMsg(), businessCode,bankTicketLimitAmountDTO.getId(), userId);
            result += updateBankTicketLimitAmount(bankTicketLimitAmountDTO, userId);
            
            if (result<2) {
                throw new ServiceBizException("银票限额驳回失败");
            }
        }
        
    }

}
