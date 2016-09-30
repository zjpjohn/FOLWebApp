/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.service
*
* @File name : ExceptionHandingServiceImpl.java
*
* @Author : DELL
*
* @Date : 2016年1月22日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月22日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.common.service.services.impl;


import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.common.api.domain.ExceptionHandingDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.services.ExceptionHandingService;
import com.sgm.dms.fol.common.service.domains.ExceptionHandingPo;
import com.sgm.dms.fol.common.service.repository.ExceptionHandingDao;
import com.sgm.dms.fol.common.service.utils.BeanMapper;


/*
* @author DELL
* TODO description
* @date 2016年1月22日
*/
@Service("ExceptionHandingService")
@Transactional(rollbackFor=ServiceBizException.class)
public class ExceptionHandingServiceImpl implements ExceptionHandingService{

    @Autowired
    private ExceptionHandingDao exceptionHandingDao;
    
    
    /*
    * @author DELL
    * @date 2016年1月22日
    * @param exceptionHandingDTO
    * @return
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.common.api.services.ExceptionHandingService#addAmountExceptionHanding(com.sgm.dms.fol.common.api.domain.ExceptionHandingDTO)
    */
    	
    @Override
    public Boolean addAmountExceptionHanding(ExceptionHandingDTO exceptionHandingDTO,Long userid) throws ServiceAppException {
        ExceptionHandingPo exceptionHandingPo=BeanMapper.map(exceptionHandingDTO, ExceptionHandingPo.class);
      
        try {
           
            exceptionHandingPo.setCommonAddData(userid);
            exceptionHandingPo.setCommonUpdateData(userid);
            exceptionHandingPo.setReferCode(exceptionHandingPo.getDisposeNo());
            exceptionHandingPo.setDisposeUser(userid);
            int count=exceptionHandingDao.addAmountExceptionHanding(exceptionHandingPo);
            
            if(count<=0){
                throw(new ServiceBizException("新增金额异常处理记录失败"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw(new ServiceAppException(e));
        }
        return true;
    }

}
