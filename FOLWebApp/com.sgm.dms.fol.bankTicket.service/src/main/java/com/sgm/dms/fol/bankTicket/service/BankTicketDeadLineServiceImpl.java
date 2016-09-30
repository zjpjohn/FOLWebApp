/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.service
*
* @File name : BankTicketDeadLineServiceImpl.java
*
* @Author : DELL
*
* @Date : 2016年1月11日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月11日    DELL    1.0
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
import com.sgm.dms.fol.bankTicket.api.BankTicketDeadLineService;
import com.sgm.dms.fol.bankTicket.domain.BankTicketDeadLinePo;
import com.sgm.dms.fol.bankTicket.dto.BankTicketDeadLineDTO;
import com.sgm.dms.fol.bankTicket.repository.BankTicketDeadLineDao;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.constants.TypeConstant;
import com.sgm.dms.fol.common.api.domain.DealerInfoDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.service.domains.CodePo;
import com.sgm.dms.fol.common.service.domains.DealerInfoPo;
import com.sgm.dms.fol.common.service.repository.CodeDao;
import com.sgm.dms.fol.common.service.repository.DealerDao;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CommonUtils;
import com.sgm.dms.fol.common.service.utils.RSAUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;

/*
*
* @author DELL
* TODO description
* @date 2016年1月11日
*/
@Service("BankTicketDeadLineService")
@Transactional(rollbackFor=Exception.class)
public class BankTicketDeadLineServiceImpl implements BankTicketDeadLineService{
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private BankTicketDeadLineDao bankTicketDeadLineDao;
    
    @Autowired
    private BankTicketAuditFlowService bankTicketAuditFlowService;
    
    @Autowired
    private DealerDao dealerDao;
    
    @Autowired
    private CodeDao codeDao;


    /*
    *
    * @author DELL
    * @date 2016年1月11日
    * @param bankTicketDeadLineDTO
    * @return
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketDeadLineService#addBankTicketDeadLine(com.sgm.dms.fol.bankTicket.dto.BankTicketDeadLineDTO)
    */
    	
    @Override
    public int addBankTicketDeadLineWithBrand(BankTicketDeadLineDTO bankTicketDeadLineDTO,Long userId) throws ServiceAppException{
        BankTicketDeadLinePo bankTicketDeadLinePo=BeanMapper.map(bankTicketDeadLineDTO, BankTicketDeadLinePo.class);
        int result=0;
        
        try {
            bankTicketDeadLinePo.setType(TypeConstant.BANK_TICKET_BRAND_TYPE);
            bankTicketDeadLinePo.setCommonAddData(userId);
            bankTicketDeadLinePo.setCommonUpdateData(userId);
            CommonUtils.filterSpecialWord(bankTicketDeadLinePo);
            
            BankTicketDeadLinePo tempPo=new BankTicketDeadLinePo();
            tempPo.setBrandId(bankTicketDeadLinePo.getBrandId());
            tempPo.setType(TypeConstant.BANK_TICKET_BRAND_TYPE);
            tempPo.setDealerType(bankTicketDeadLinePo.getDealerType());
            
            if(StringUtil.isEmpty(bankTicketDeadLinePo.getDealerType())){
                throw(new ServiceBizException("渠道类型不能为空"));
            }
            
            List<CodePo> list=codeDao.findCodeByType(POConstant.DEALER_TYPE);
            Boolean flag=false;
            if(list!=null&&list.size()>0){
                for (CodePo codePo : list) {
                    if(codePo.getCode().equals(Integer.toString(bankTicketDeadLinePo.getDealerType()))){
                        flag=true;
                        break;
                    }
                }
            }
            if(!flag){
                throw(new ServiceBizException("渠道类型不存在"));
            }
            
            if(bankTicketDeadLineDao.findBankTicketDeadLineCount(tempPo)>0){
                throw(new ServiceBizException("同品牌同渠道不能重复"));
            }
            
            if(bankTicketDeadLinePo.getFreePeriodDay().intValue()>bankTicketDeadLinePo.getDeadlineDay().intValue()){
                throw(new ServiceBizException("免息天数不能大于票据天数"));
            }
                      
            bankTicketDeadLinePo.setCommonUpdateData(userId);
            bankTicketDeadLinePo.setCommonAddData(userId);
            result+=bankTicketDeadLineDao.addBankTicketDeadLine(bankTicketDeadLinePo);            
            result+=bankTicketAuditFlowService.addBankTicketAuditFlowRecord(POConstant.BANK_TICKET_ADD_UN_AUDIT,null, POConstant.BUSINESS_TABLE_ADD_BANK_TICKET_DEADLINE, bankTicketDeadLinePo.getId(), userId);
            
            if(result<2){
                throw(new ServiceBizException("新增数据失败"));
            }
            
        } catch (SQLException e) {
            logger.error(e);
            throw(new ServiceAppException(e.getMessage()));
        }
        return result;
    }

    /*
    *
    * @author DELL
    * @date 2016年1月11日
    * @param bankTicketDeadLineDTO
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketDeadLineService#deleteBankTicketDeadLine(com.sgm.dms.fol.bankTicket.dto.BankTicketDeadLineDTO)
    */
    	
    @Override
    public int deleteBankTicketDeadLine(BankTicketDeadLineDTO bankTicketDeadLineDTO,Long userId) throws ServiceAppException {
        int result=0;
        try {
            bankTicketDeadLineDTO.setAuditStatus(POConstant.BANK_TICKET_DELETE_UN_AUDIT);
            bankTicketDeadLineDTO.setId(Long.valueOf(RSAUtil.decryptByPrivateKey(bankTicketDeadLineDTO.getEncryptId())));
    
            BankTicketDeadLinePo bankTicketDeadLinePo=BeanMapper.map(bankTicketDeadLineDTO, BankTicketDeadLinePo.class);

            bankTicketDeadLinePo.setCommonUpdateData(userId);
            
            BankTicketDeadLinePo searchPo=new BankTicketDeadLinePo();
            searchPo.setId(bankTicketDeadLinePo.getId());
            searchPo.setBeginNo(0);
            searchPo.setEndNo(10);
            List<BankTicketDeadLineDTO> list=bankTicketDeadLineDao.findBankTicketDeadLine(searchPo);
            
            if(list!=null && list.size()>0){
                BankTicketDeadLineDTO resultdto=list.get(0);
                
                if(resultdto.getAuditStatus().intValue()==POConstant.BANK_TICKET_ADD_UN_AUDIT||
                        resultdto.getAuditStatus().intValue()==POConstant.BANK_TICKET_DELETE_UN_AUDIT||
                        resultdto.getAuditStatus().intValue()==POConstant.BANK_TICKET_UPDATE_UN_AUDIT){
                    throw(new ServiceBizException("当前状态是待审核不能给予删除"));
                }
            }else{
                throw(new ServiceBizException("删除的对象已不存在"));
            }
            result+=bankTicketDeadLineDao.updateBankTicketDeadLine(bankTicketDeadLinePo);

            bankTicketAuditFlowService.addBankTicketAuditFlowRecord(POConstant.BANK_TICKET_DELETE_UN_AUDIT, "", POConstant.BUSINESS_TABLE_DELETE_BANK_TICKET_DEADLINE, bankTicketDeadLinePo.getId(), userId);
            if(result<=0){
                throw(new ServiceBizException("删除数据失败,找不到需要删除的数据"));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw(new ServiceAppException(e.getMessage()));
        } catch (Exception ex) {
            logger.error(ex);
            throw(new ServiceAppException(ex.getMessage()));
        }
        return result;
    }
    
    @Override
	public int deleteBankTicketDeadLineVer(BankTicketDeadLineDTO bankTicketDeadLineDTO, Long userId)
			throws ServiceAppException {
    	 BankTicketDeadLinePo bankTicketDeadLinePo=BeanMapper.map(bankTicketDeadLineDTO, BankTicketDeadLinePo.class);
         int result=0;
         
         try {
             bankTicketDeadLinePo.setCommonUpdateData(userId);
             
             BankTicketDeadLinePo searchPo=new BankTicketDeadLinePo();
             searchPo.setId(bankTicketDeadLinePo.getId());
             searchPo.setBeginNo(0);
             searchPo.setEndNo(10);
             List<BankTicketDeadLineDTO> list=bankTicketDeadLineDao.findBankTicketDeadLine(searchPo);
             
             if(list!=null && list.size()>0){
                 BankTicketDeadLineDTO resultdto=list.get(0);
                 
                 if(resultdto.getAuditStatus().intValue()!=POConstant.BANK_TICKET_DELETE_UN_AUDIT){
                     throw(new ServiceBizException("当前状态不是删除待审核状态，不允许删除"));
                 }
             }else{
                 throw(new ServiceBizException("删除的对象已不存在"));
             }
             result+=bankTicketDeadLineDao.deleteBankTicketDeadLine(bankTicketDeadLinePo);

//             bankTicketAuditFlowService.addBankTicketAuditFlowRecord(POConstant.BANK_TICKET_DELETE_UN_AUDIT, "", POConstant.BUSINESS_TABLE_DELETE_BANK_TICKET_DEADLINE, bankTicketDeadLinePo.getId(), userId);
             if(result<=0){
                 throw(new ServiceBizException("删除数据失败,找不到需要删除的数据"));
             }
         } catch (SQLException e) {
             logger.error(e);
             throw(new ServiceAppException(e.getMessage()));
         }
         return result;
	}

    /*
    *
    * @author DELL
    * @date 2016年1月11日
    * @param bankTicketDeadLineDTO
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketDeadLineService#findBankTicketDeadLine(com.sgm.dms.fol.bankTicket.dto.BankTicketDeadLineDTO)
    */
    	
    @Override
    public List<BankTicketDeadLineDTO> findBankTicketDeadLineWithBrand(BankTicketDeadLineDTO bankTicketDeadLinedto) throws ServiceAppException {
        List<BankTicketDeadLineDTO> list;
        try {
            BankTicketDeadLinePo bankTicketDeadLinePo=BeanMapper.map(bankTicketDeadLinedto, BankTicketDeadLinePo.class);
            bankTicketDeadLinePo.setType(TypeConstant.BANK_TICKET_BRAND_TYPE);
            list=bankTicketDeadLineDao.findBankTicketDeadLine(bankTicketDeadLinePo);
            
            if(list!=null&&list.size()>0){
                for (BankTicketDeadLineDTO dto : list) {
                    dto.setEncryptId(RSAUtil.encryptByPublicKey(dto.getId().toString()));
                }  
            }
            
        } catch (Exception e) {
            logger.error(e);
            throw(new ServiceAppException(e.getMessage()));
        }
        return list;
    }

    /*
    *
    * @author DELL
    * @date 2016年1月11日
    * @param bankTicketDeadLineDTO
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketDeadLineService#findBankTicketDeadLineCount(com.sgm.dms.fol.bankTicket.dto.BankTicketDeadLineDTO)
    */
    	
    @Override
    public int findBankTicketDeadLineCountWithBrand(BankTicketDeadLineDTO bankTicketDeadLineDTO) throws ServiceAppException {
        Integer count=0;
        
        try {
            BankTicketDeadLinePo bankTicketDeadLinePo=BeanMapper.map(bankTicketDeadLineDTO, BankTicketDeadLinePo.class);
            bankTicketDeadLinePo.setType(TypeConstant.BANK_TICKET_BRAND_TYPE);
            count = bankTicketDeadLineDao.findBankTicketDeadLineCount(bankTicketDeadLinePo);
        } catch (SQLException e) {
            logger.error(e);
            throw(new ServiceAppException(e.getMessage()));
        }
        
        return count;
    }

    /*
    *
    * @author DELL
    * @date 2016年1月11日
    * @param bankTicketDeadLineDTO
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketDeadLineService#findBankTicketDeadLineWithSpecialDealer(com.sgm.dms.fol.bankTicket.dto.BankTicketDeadLineDTO)
    */
    	
    @Override
    public List<BankTicketDeadLineDTO> findBankTicketDeadLineWithSpecialDealer(BankTicketDeadLineDTO bankTicketDeadLineDTO) throws ServiceAppException {
        List<BankTicketDeadLineDTO> list=null;
        try {
            BankTicketDeadLinePo bankTicketDeadLinePo=BeanMapper.map(bankTicketDeadLineDTO, BankTicketDeadLinePo.class);
            bankTicketDeadLinePo.setType(TypeConstant.BANK_TICKET_SPECIAL_DEALER_TYPE);
            list=bankTicketDeadLineDao.findBankTicketDeadLine(bankTicketDeadLinePo);
            
            if(list!=null&&list.size()>0){
                for (BankTicketDeadLineDTO dto : list) {
                    dto.setEncryptId(RSAUtil.encryptByPublicKey(dto.getId().toString()));
                }  
            }
            
        } catch (Exception e) {
            logger.error(e);
            throw(new ServiceAppException(e.getMessage()));
        }
        return list;
    }

    /*
    *
    * @author DELL
    * @date 2016年1月11日
    * @param bankTicketDeadLineDTO
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketDeadLineService#findBankTicketDeadLineCountWithSpecialDealer(com.sgm.dms.fol.bankTicket.dto.BankTicketDeadLineDTO)
    */
    	
    @Override
    public int findBankTicketDeadLineCountWithSpecialDealer(BankTicketDeadLineDTO bankTicketDeadLineDTO) throws ServiceAppException {
        Integer count=0;
        
        try {
            BankTicketDeadLinePo bankTicketDeadLinePo=BeanMapper.map(bankTicketDeadLineDTO, BankTicketDeadLinePo.class);
            bankTicketDeadLinePo.setType(TypeConstant.BANK_TICKET_SPECIAL_DEALER_TYPE);
            count = bankTicketDeadLineDao.findBankTicketDeadLineCount(bankTicketDeadLinePo);
        } catch (SQLException e) {
            logger.error(e);
            throw(new ServiceAppException(e.getMessage()));
        }
        
        return count;
    }

    /*
    *
    * @author DELL
    * @date 2016年1月11日
    * @param bankTicketDeadLineDTO
    * @param userId
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketDeadLineService#addBankTicketDeadLineWithSpecialDealer(com.sgm.dms.fol.bankTicket.dto.BankTicketDeadLineDTO, java.lang.Long)
    */
    	
    @Override
    public int addBankTicketDeadLineWithSpecialDealer(BankTicketDeadLineDTO bankTicketDeadLineDTO,
                                                      Long userId) throws ServiceAppException {
        BankTicketDeadLinePo bankTicketDeadLinePo=BeanMapper.map(bankTicketDeadLineDTO, BankTicketDeadLinePo.class);
        int result=0;
        
        try {
            bankTicketDeadLinePo.setType(TypeConstant.BANK_TICKET_SPECIAL_DEALER_TYPE);
            bankTicketDeadLinePo.setCommonAddData(userId);
            bankTicketDeadLinePo.setCommonUpdateData(userId);
            CommonUtils.filterSpecialWord(bankTicketDeadLinePo);
            
            DealerInfoDTO dealerinfodto=new DealerInfoDTO();
            dealerinfodto.setSapCode(bankTicketDeadLinePo.getSapCode());
            DealerInfoPo dealerinfoPo=dealerDao.getDealerInfoBySapCode(dealerinfodto);
            if(dealerinfoPo==null){
                throw(new ServiceBizException("没有该经销商Code:"+dealerinfodto.getSapCode()));
            }

            BankTicketDeadLinePo tempPo=new BankTicketDeadLinePo();
            tempPo.setSapCode(bankTicketDeadLinePo.getSapCode());
            tempPo.setBrandId(bankTicketDeadLinePo.getBrandId());
            tempPo.setType(TypeConstant.BANK_TICKET_SPECIAL_DEALER_TYPE);
            
            if(TypeConstant.BANK_TICKET_SPECIAL_DEALER_TYPE==bankTicketDeadLinePo.getType().intValue()&&!StringUtil.isEmpty(bankTicketDeadLinePo.getSapCode())){ 
                tempPo.setSapCode(bankTicketDeadLinePo.getSapCode());
            }else{
                throw(new ServiceBizException("SAP CODE不能为空")); 
            }
            
            if(StringUtil.isEmpty(bankTicketDeadLinePo.getDealerType())){
                throw(new ServiceBizException("渠道类型不能为空"));
            }
            
            List<CodePo> list=codeDao.findCodeByType(POConstant.DEALER_TYPE);
            Boolean flag=false;
            if(list!=null&&list.size()>0){
                for (CodePo codePo : list) {
                    if(codePo.getCode().equals(Integer.toString(bankTicketDeadLinePo.getDealerType()))){
                        flag=true;
                        break;
                    }
                }
            }
            if(!flag){
                throw(new ServiceBizException("渠道类型不存在"));
            }
            
            if(bankTicketDeadLineDao.findBankTicketDeadLineCount(tempPo)>0){
                throw(new ServiceBizException("同渠道同品牌同经销商CODE重复"));
            }
            
            if(bankTicketDeadLineDao.findBankTicketDeadLineCount(tempPo)>0){
                throw(new ServiceBizException("同品牌同经销商CODE重复"));
            }
            
            if(bankTicketDeadLinePo.getFreePeriodDay().intValue()>bankTicketDeadLinePo.getDeadlineDay().intValue()){
                throw(new ServiceBizException("免息天数不能大于票据天数"));
            }
            
            if(bankTicketDeadLinePo.getEffectDate().after(bankTicketDeadLinePo.getExpireDate())){
                throw(new ServiceBizException("生效日期不能小于到期日期"));
            }
            bankTicketDeadLinePo.setCommonUpdateData(userId);
            bankTicketDeadLinePo.setCommonAddData(userId);
            result+=bankTicketDeadLineDao.addBankTicketDeadLine(bankTicketDeadLinePo);
            result+=bankTicketAuditFlowService.addBankTicketAuditFlowRecord(POConstant.BANK_TICKET_ADD_UN_AUDIT,null, POConstant.BUSINESS_TABLE_ADD_BANK_TICKET_DEADLINE, bankTicketDeadLinePo.getId(), userId);
            
            if(result<2){
                throw(new ServiceBizException("新增数据失败"));
            }
            
        } catch (SQLException e) {
            logger.error(e);
            throw(new ServiceAppException(e.getMessage()));
        }
        return result;
    }

    /*
    *
    * @author DELL
    * @date 2016年1月12日
    * @param bankTicketDeadLineDTO
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketDeadLineService#updateBankTicketDeadLineWithBrand(com.sgm.dms.fol.bankTicket.dto.BankTicketDeadLineDTO)
    */
    	
    @Override
    public void updateBankTicketDeadLine(BankTicketDeadLineDTO bankTicketDeadLineDTO,Long userId) throws ServiceAppException,SQLException {
        int result=0;

        //数据初始化
        try {
            bankTicketDeadLineDTO.setAuditStatus(POConstant.BANK_TICKET_UPDATE_UN_AUDIT);
            bankTicketDeadLineDTO.setId(Long.valueOf(RSAUtil.decryptByPrivateKey(bankTicketDeadLineDTO.getEncryptId())));  
        } catch (NumberFormatException e1) {
            e1.printStackTrace();
            throw(new ServiceAppException(e1.getMessage()));
        } catch (Exception e1) {
            e1.printStackTrace();
            throw(new ServiceAppException(e1.getMessage()));
        }
   
        BankTicketDeadLinePo bankTicketDeadLinePo=BeanMapper.map(bankTicketDeadLineDTO, BankTicketDeadLinePo.class);
        BankTicketDeadLinePo searchPo=new BankTicketDeadLinePo();
        searchPo.setId(bankTicketDeadLinePo.getId());
        searchPo.setBeginNo(0);
        searchPo.setEndNo(10);
        
        //查询银票期限
        List<BankTicketDeadLineDTO> list=bankTicketDeadLineDao.findBankTicketDeadLine(searchPo);
          
        //查出来有对象
        if(list!=null && list.size()>0){
            BankTicketDeadLineDTO resultdto=list.get(0);
            
            if(resultdto.getAuditStatus().intValue()==POConstant.BANK_TICKET_ADD_UN_AUDIT||
                    resultdto.getAuditStatus().intValue()==POConstant.BANK_TICKET_UPDATE_UN_AUDIT||
                    resultdto.getAuditStatus().intValue()==POConstant.BANK_TICKET_DELETE_UN_AUDIT){
                throw(new ServiceBizException("当前状态是待审核不能给予修改"));
            }
            
            try {
                if((bankTicketDeadLinePo.getFreePeriodDay()!=null||bankTicketDeadLinePo.getDeadlineDay()!=null)&&
                        bankTicketDeadLinePo.getFreePeriodDay().intValue()>bankTicketDeadLinePo.getDeadlineDay().intValue()){
                    throw(new ServiceBizException("免息天数不能大于票据天数"));
                }else if(bankTicketDeadLinePo.getDeadlineDay()!=null&&bankTicketDeadLinePo.getEffectDate()!=null&&bankTicketDeadLinePo.getExpireDate()!=null){
                   int days=Long.valueOf((bankTicketDeadLinePo.getEffectDate().getTime()-bankTicketDeadLinePo.getExpireDate().getTime())/1000/60/60/24).intValue();
                   if(bankTicketDeadLinePo.getDeadlineDay()<days){
                       throw(new ServiceBizException("起效日减去到期日的天数不能大于票据天数"));
                   }
                }else if(bankTicketDeadLinePo.getDeadlineDay()!=null&&bankTicketDeadLinePo.getEffectDate()==null&&bankTicketDeadLinePo.getExpireDate()==null){
                    BankTicketDeadLinePo temppo=new BankTicketDeadLinePo();
                    temppo.setId(searchPo.getId());
                    List<BankTicketDeadLineDTO> dtos=bankTicketDeadLineDao.findBankTicketDeadLine(temppo);
                    if(dtos!=null&&dtos.size()>0&&dtos.get(0).getEffectDate()!=null&&dtos.get(0).getExpireDate()!=null){
                        int days=Long.valueOf((dtos.get(0).getExpireDate().getTime()-dtos.get(0).getEffectDate().getTime())/1000/60/60/24).intValue();
                        if(bankTicketDeadLinePo.getDeadlineDay()>days){
                            throw(new ServiceBizException("起效日减去到期日的天数不能大于票据天数"));
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e);
                if(e.getMessage()==null){
                    throw(new ServiceBizException("免息天数或者票据天数不能为空"));  
                }else if(e.getMessage().equals("免息天数不能大于票据天数")){
                    throw(new ServiceBizException(e.getMessage()));
                }else{
                    throw(new ServiceAppException(e));
                }
            }
        
            //如果是生效日为空并且是品牌期限走以下程序并return
            if(bankTicketDeadLinePo.getExpireDate()==null&&bankTicketDeadLinePo.getEffectDate()!=null&&resultdto.getType().intValue()==TypeConstant.BANK_TICKET_BRAND_TYPE){
                bankTicketDeadLinePo.setCommonUpdateData(userId);
                result+=bankTicketDeadLineDao.updateBankTicketDeadLine(bankTicketDeadLinePo);
                result+=bankTicketAuditFlowService.addBankTicketAuditFlowRecord(POConstant.BANK_TICKET_UPDATE_UN_AUDIT, "", POConstant.BUSINESS_TABLE_UPDATE_BANK_TICKET_DEADLINE, bankTicketDeadLinePo.getId(), userId);
            
                if(result<2){
                    throw(new ServiceBizException("修改银票期限记录失败,失败记录ID:"+bankTicketDeadLinePo.getId()));
                }
                
                return ;
            } 
            
            //如果是生效日不为空并且是特殊经销商期限走以下程序
            try {
                if(resultdto.getType().intValue()==TypeConstant.BANK_TICKET_SPECIAL_DEALER_TYPE&&
                        (bankTicketDeadLinePo.getExpireDate()!=null||bankTicketDeadLinePo.getEffectDate()!=null)&&
                        bankTicketDeadLinePo.getEffectDate().after(bankTicketDeadLinePo.getExpireDate())){
                    throw(new ServiceBizException("生效日期不能小于到期日期"));
                }else{
                    bankTicketDeadLinePo.setCommonUpdateData(userId);
                    result+=bankTicketDeadLineDao.updateBankTicketDeadLine(bankTicketDeadLinePo);
                    result+=bankTicketAuditFlowService.addBankTicketAuditFlowRecord(POConstant.BANK_TICKET_UPDATE_UN_AUDIT, "", POConstant.BUSINESS_TABLE_UPDATE_BANK_TICKET_DEADLINE, bankTicketDeadLinePo.getId(), userId);
                }
            } catch (Exception e) {
                logger.error(e);
                if(e.getMessage()==null){
                    throw(new ServiceBizException("生效日期或者到期日期不能为空")); 
                }else if(e.getMessage().equals("生效日期不能小于到期日期")){
                    throw(new ServiceBizException(e.getMessage()));
                }else{
                    throw(new ServiceAppException(e));
                }
            }
        }else{
            throw(new ServiceBizException("更新的对象已不存在"));
        }

        if(result<2){
            throw(new ServiceBizException("修改银票期限记录失败,失败记录ID:"+bankTicketDeadLinePo.getId()));
        }

    }

	@Override
	public int updateAuidtstatus(BankTicketDeadLineDTO bankTicketDeadLineDTO, Long userId) throws ServiceAppException {
		int result = 0;
		  BankTicketDeadLinePo bankTicketDeadLinePo=BeanMapper.map(bankTicketDeadLineDTO, BankTicketDeadLinePo.class);
		  try {
                  bankTicketDeadLinePo.setCommonUpdateData(userId);
                  CommonUtils.filterSpecialWord(bankTicketDeadLineDTO);
                  result+=bankTicketDeadLineDao.updateBankTicketDeadLine(bankTicketDeadLinePo);
                  if(result<1){
                      throw(new ServiceBizException("修改银票期限记录失败,失败记录ID:"+bankTicketDeadLinePo.getId()));
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
    * @param bankTicketDeadLineDTOList
    * @param userId
    * @throws ServiceAppException
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketDeadLineService#bankTicketDeadLineAuditSuccess(java.util.List, java.lang.Long)
    */
    	
    @Override
    public void bankTicketDeadLineAuditSuccess(List<BankTicketDeadLineDTO> bankTicketDeadLineDTOList,
                                               Long userId) throws ServiceAppException, SQLException {
        for (BankTicketDeadLineDTO bankTicketDeadLineDTO : bankTicketDeadLineDTOList) {
            int result=0;
            try {
                bankTicketDeadLineDTO.setId(Long.valueOf(RSAUtil.decryptByPrivateKey(bankTicketDeadLineDTO.getEncryptId())));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw(new ServiceAppException(e));
            } catch (Exception e) {
                e.printStackTrace();
                throw(new ServiceAppException(e));
            }
            
            /* 设置银票业务类型 */
            Integer businessCode = null;//银票业务代码
            if(POConstant.BANK_TICKET_ADD_UN_AUDIT == bankTicketDeadLineDTO.getAuditStatus()){//审批前状态为（新增待审核）
                businessCode = POConstant.BUSINESS_TABLE_ADD_BANK_TICKET_DEADLINE;
            }
            if(POConstant.BANK_TICKET_UPDATE_UN_AUDIT == bankTicketDeadLineDTO.getAuditStatus()){//审批前状态为（修改待审核）
                businessCode = POConstant.BUSINESS_TABLE_UPDATE_BANK_TICKET_DEADLINE;
            }
            if(POConstant.BANK_TICKET_DELETE_UN_AUDIT == bankTicketDeadLineDTO.getAuditStatus()){//审批前状态为（删除待审核）
                businessCode = POConstant.BUSINESS_TABLE_DELETE_BANK_TICKET_DEADLINE;
            }
            
            /* 新增银票审核流水记录 */
            result += bankTicketAuditFlowService.addBankTicketAuditFlowRecord(bankTicketDeadLineDTO.getAuditStatus(),bankTicketDeadLineDTO.getAuditMsg(), businessCode, bankTicketDeadLineDTO.getId(),userId);

            if(POConstant.BANK_TICKET_DELETE_UN_AUDIT != bankTicketDeadLineDTO.getAuditStatus()){//当审批的记录状态不是删除待审核状态时
                bankTicketDeadLineDTO.setAuditStatus(POConstant.BANK_TICKET_AUDIT_SUCCESS);
                result += updateAuidtstatus(bankTicketDeadLineDTO,userId);
            }else{
                bankTicketDeadLineDTO.setAuditStatus(POConstant.BANK_TICKET_AUDIT_SUCCESS);
                result += deleteBankTicketDeadLineVer(bankTicketDeadLineDTO,userId);
            }

            if(result<2){
                throw new ServiceBizException("银票期限审核失败");
            }
        }
    }

    /*
    *
    * @author DELL
    * @date 2016年3月24日
    * @param bankTicketDeadLineDTOList
    * @param userId
    * @throws ServiceAppException
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketDeadLineService#bankTicketDeadLineAuditReject(java.util.List, java.lang.Long)
    */
    	
    @Override
    public void bankTicketDeadLineAuditReject(List<BankTicketDeadLineDTO> bankTicketDeadLineDTOList,
                                              Long userId) throws ServiceAppException, SQLException {
        for (BankTicketDeadLineDTO bankTicketDeadLineDTO : bankTicketDeadLineDTOList) {
            int result = 0;
           
            try {
                bankTicketDeadLineDTO.setId(Long.valueOf(RSAUtil.decryptByPrivateKey(bankTicketDeadLineDTO.getEncryptId())));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw(new ServiceAppException(e));
            } catch (Exception e) {
                e.printStackTrace();
                throw(new ServiceAppException(e));
            }
            
            /* 设置银票业务类型 */
            Integer businessCode = null;//银票业务代码
            if(POConstant.BANK_TICKET_ADD_UN_AUDIT == bankTicketDeadLineDTO.getAuditStatus()){//审批前状态为（新增待审核）
                businessCode = POConstant.BUSINESS_TABLE_ADD_BANK_TICKET_DEADLINE;
                bankTicketDeadLineDTO.setAuditStatus(POConstant.BANK_TICKET_ADD_REJECT);
            }else if(POConstant.BANK_TICKET_UPDATE_UN_AUDIT == bankTicketDeadLineDTO.getAuditStatus()){//审批前状态为（修改待审核）
                businessCode = POConstant.BUSINESS_TABLE_UPDATE_BANK_TICKET_DEADLINE;
                bankTicketDeadLineDTO.setAuditStatus(POConstant.BANK_TICKET_UPDATE_REJECT);
            }else if(POConstant.BANK_TICKET_DELETE_UN_AUDIT == bankTicketDeadLineDTO.getAuditStatus()){//审批前状态为（删除待审核）
                businessCode = POConstant.BUSINESS_TABLE_DELETE_BANK_TICKET_DEADLINE;
                bankTicketDeadLineDTO.setAuditStatus(POConstant.BANK_TICKET_DELETE_REJECT);
            }

            result+=bankTicketAuditFlowService.addBankTicketAuditFlowRecord( bankTicketDeadLineDTO.getAuditStatus(),bankTicketDeadLineDTO.getAuditMsg(), businessCode, bankTicketDeadLineDTO.getId(),userId);
            
            result+=updateAuidtstatus(bankTicketDeadLineDTO,userId);
            
            if(result<2){
                throw new ServiceBizException("银票期限驳回失败");
            }
        
        }
    }

}
