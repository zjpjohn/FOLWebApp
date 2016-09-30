/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bonus.service
*
* @File name : BonusExceptionHandingServiceImpl.java
*
* @Author : DELL
*
* @Date : 2016年1月25日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月25日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.bonus.service;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import javax.xml.ws.Holder;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.bonus.api.BonusExceptionHandingService;
import com.sgm.dms.fol.bonus.client.BonusFundRequestVo;
import com.sgm.dms.fol.bonus.client.BonusFundResultVo;
import com.sgm.dms.fol.bonus.client.BonusOperationWebService;
import com.sgm.dms.fol.bonus.client.BonusService;
import com.sgm.dms.fol.bonus.client.SGMCommonHeaderType;
import com.sgm.dms.fol.bonus.client.TranformBonusFund;
import com.sgm.dms.fol.bonus.client.TranformBonusFundResponse;
import com.sgm.dms.fol.bonus.common.CommonHelper;
import com.sgm.dms.fol.common.api.constants.CodeConstant;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.domain.ExceptionHandingDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.services.ExceptionHandingService;
import com.sgm.dms.fol.common.service.domains.ExceptionHandingPo;
import com.sgm.dms.fol.common.service.repository.ExceptionHandingDao;
import com.sgm.dms.fol.common.service.utils.BeanMapper;

/*
*
* @author DELL
* TODO description
* @date 2016年1月25日
*/
@Service("BonusExceptionHandingService")
@Transactional(rollbackFor=Exception.class)
public class BonusExceptionHandingServiceImpl implements BonusExceptionHandingService{

    // 日志
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private ExceptionHandingService exceptionHandingService;
    
    @Autowired
    private ExceptionHandingDao exceptionHandingDao;
    
    /*
    *
    * @author DELL
    * @date 2016年1月25日
    * @param exceptionHandingDTO
    * @param userId
    * @return
    * @throws ServiceAppException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bonus.api.BonusExceptionHandingService#bonusAmountExceptionHanding(com.sgm.dms.fol.common.api.domain.ExceptionHandingDTO, java.lang.Long)
    */
    	
    @Override
    public Boolean bonusAmountExceptionHanding(ExceptionHandingDTO exceptionHandingDTO,
                                               Long userId) throws ServiceAppException {
        
        ExceptionHandingPo exceptionHandingPo=BeanMapper.map(exceptionHandingDTO, ExceptionHandingPo.class);
        BonusFundResultVo result=null;
        Boolean flag=false;
        try {
            exceptionHandingPo.setTsId(CodeConstant.AMOUNT_EXCEPTION_HANDING_TS_ID.concat(exceptionHandingDao.getCurrentTsId()+""));
            result=updateBonusAmount(exceptionHandingPo);
            flag=true;
        } catch (Exception e) {
            logger.error(e);
        }
        
        try {
            if(!flag){
                throw(new ServiceBizException("reserve exception handing faild"));
            }
            
            if(result!=null&&POConstant.BONUS_SERVICE_EXECUTE_SUC==result.getResultCode()){     
                exceptionHandingDTO.setTsId(exceptionHandingPo.getTsId());
                exceptionHandingService.addAmountExceptionHanding(exceptionHandingDTO, userId);
            }
            
            return true;
        } catch (Exception e) {
            logger.error(e);
            flag=false;
            throw(new ServiceAppException(e));
        } finally {
            if(!flag){
                exceptionHandingPo.setReferType(exceptionHandingPo.getReferType());
                result=updateBonusAmount(exceptionHandingPo);
            }
            
        }
    }
    
    private BonusFundResultVo updateBonusAmount(ExceptionHandingPo exceptionHandingPo) throws ServiceAppException {
        try {
            BonusFundRequestVo bonusFundRequestVo=initBonusFundResultVo(exceptionHandingPo); 
            BonusService service=new BonusService();
            BonusFundResultVo result=null;
            Holder<SGMCommonHeaderType> header=new Holder<SGMCommonHeaderType>();
            header.value=CommonHelper.tansfer(new GregorianCalendar());
            TranformBonusFund bonusFund=new TranformBonusFund();
            BonusOperationWebService ws=service.getBonusServicePort();
            bonusFund.setArg0(bonusFundRequestVo);
            TranformBonusFundResponse bonusFundResponse=ws.tranformBonusFund(bonusFund, header);
            result=bonusFundResponse.getReturn();
            return result;
        } catch (Exception e) {
            logger.error(e);
            throw(new ServiceAppException(e));
        }
    }
    
    private BonusFundRequestVo initBonusFundResultVo(ExceptionHandingPo exceptionHandingPo) throws Exception{
        BonusFundRequestVo requestVO=new BonusFundRequestVo();
        requestVO.setSapCode(exceptionHandingPo.getSapCode());
        requestVO.setReferType(exceptionHandingPo.getReferType());
        requestVO.setReferCode(exceptionHandingPo.getReferCode());
        requestVO.setAmount(new BigDecimal(exceptionHandingPo.getAmount()).doubleValue());
        requestVO.setTsId(exceptionHandingPo.getTsId());
        requestVO.setFundType(null);
        return requestVO;
    }
}
