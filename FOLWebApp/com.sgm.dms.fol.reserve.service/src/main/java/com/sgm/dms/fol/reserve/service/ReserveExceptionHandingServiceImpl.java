/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : reserve.service
*
* @File name : ReserveExceptionHandingServiceImpl.java
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
	
package com.sgm.dms.fol.reserve.service;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import javax.xml.ws.Holder;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgm.dms.fol.common.api.constants.CodeConstant;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.domain.ExceptionHandingDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.services.ExceptionHandingService;
import com.sgm.dms.fol.common.service.domains.ExceptionHandingPo;
import com.sgm.dms.fol.common.service.repository.ExceptionHandingDao;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.reserve.api.ReserveExceptionHandingService;
import com.sgm.dms.fol.reserve.client.ReserveFundRequestVO;
import com.sgm.dms.fol.reserve.client.ReserveFundResultVO;
import com.sgm.dms.fol.reserve.client.ReserveFundService;
import com.sgm.dms.fol.reserve.client.ReserveOperationWebService;
import com.sgm.dms.fol.reserve.client.SGMCommonHeaderType;
import com.sgm.dms.fol.reserve.client.TranformReserveFund;
import com.sgm.dms.fol.reserve.client.TranformReserveFundResponse;
import com.sgm.dms.fol.reserve.common.CommonHelper;

/*
*
* @author DELL
* TODO description
* @date 2016年1月25日
*/
@Service("ReserveExceptionHandingService")
public class ReserveExceptionHandingServiceImpl implements ReserveExceptionHandingService{

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
    * @param exceptionHandingPo
    * @return
    * @throws ServiceAppException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.reserve.api.ReserveExceptionHandingService#reserveAmountExceptionHanding(com.sgm.dms.fol.common.service.domains.ExceptionHandingPo)
    */
    	
    @Override
    public Boolean reserveAmountExceptionHanding(ExceptionHandingDTO exceptionHandingDTO,Long userId) throws ServiceAppException {
        
        ExceptionHandingPo exceptionHandingPo=BeanMapper.map(exceptionHandingDTO, ExceptionHandingPo.class);        
        ReserveFundResultVO result=null;
        Boolean flag=false;
        
        try {
            exceptionHandingPo.setTsId(CodeConstant.AMOUNT_EXCEPTION_HANDING_TS_ID.concat(exceptionHandingDao.getCurrentTsId()+""));
            result=updateReserveAmount(exceptionHandingPo);
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        try {
            if(!flag){
                throw(new ServiceBizException("reserve exception handing faild"));
            }
            
            if(result!=null&&POConstant.RESERVE_SERVICE_EXECUTE_SUC==result.getResultCode()){     
                exceptionHandingDTO.setTsId(exceptionHandingPo.getTsId());
                exceptionHandingService.addAmountExceptionHanding(exceptionHandingDTO, userId);
            }
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw(new ServiceAppException(e));
        }finally {
            if(!flag){
                exceptionHandingPo.setReferType(exceptionHandingPo.getReferType());
                result=updateReserveAmount(exceptionHandingPo);  
            }
        }
    }
    
    private ReserveFundResultVO updateReserveAmount(ExceptionHandingPo exceptionHandingPo) throws ServiceAppException {
        try {
            ReserveFundRequestVO reserveFundRequestVO=initReserveFundResultVo(exceptionHandingPo); 
            ReserveFundService service=new ReserveFundService();
            ReserveFundResultVO result=null;
            Holder<SGMCommonHeaderType> header=new Holder<SGMCommonHeaderType>();
            header.value=CommonHelper.tansfer(new GregorianCalendar());
            TranformReserveFund reserveFund=new TranformReserveFund();
            TranformReserveFundResponse reserveFundResponse=null;
            ReserveOperationWebService ws=service.getReserveFundPort();
            reserveFund.setArg0(reserveFundRequestVO);
            reserveFundResponse=ws.tranformReserveFund(reserveFund, header);
            result=reserveFundResponse.getReturn();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw(new ServiceAppException(e));
        }
    }
    
    private ReserveFundRequestVO initReserveFundResultVo(ExceptionHandingPo exceptionHandingPo) throws Exception{
        ReserveFundRequestVO requestVO=new ReserveFundRequestVO();
        requestVO.setSapCode(exceptionHandingPo.getSapCode());
        requestVO.setReferType(exceptionHandingPo.getReferType());
        requestVO.setReferCode(exceptionHandingPo.getReferCode());
        requestVO.setAmount(new BigDecimal(exceptionHandingPo.getAmount()).doubleValue());
        
        requestVO.setTsId(exceptionHandingPo.getTsId());
        requestVO.setFundType(null);
        return requestVO;
    }

}
