/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.service
*
* @File name : TransitionTypeServiceImpl.java
*
* @Author : shenweiwei
*
* @Date : 2015年10月22日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年10月22日    shenweiwei    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.common.service.services.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgm.dms.fol.common.api.domain.TransitionTypeDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.services.TransitionTypeService;
import com.sgm.dms.fol.common.service.domains.TransitionTypePo;
import com.sgm.dms.fol.common.service.repository.TransitionTypeDao;
import com.sgm.dms.fol.common.service.utils.BeanMapper;


/*
 *
 * @author shenweiwei
 * TODO description
 * @date 2015年10月22日
 */
@Service("TransitionTypeService")
public class TransitionTypeServiceImpl implements TransitionTypeService{

    //日志操作
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private TransitionTypeDao transitionTypeDao;
    /*
    *
    * @author shenweiwei
    * @date 2015年10月22日
    * @return
    * @throws ServiceAppException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.common.api.services.TransitionTypeService#queryAll()
    */
    	
    @Override
    public List<TransitionTypeDTO> queryAll() throws ServiceAppException  {
        List<TransitionTypePo> list;
        try {
            list = transitionTypeDao.queryAll();
        } catch (SQLException e) {
            logger.info("查询信息异常 :"+e.getMessage());
            throw new ServiceBizException(e.getMessage());
        }
        List<TransitionTypeDTO> TransitionTypeDTOs=BeanMapper.mapList(list, TransitionTypeDTO.class);
        return TransitionTypeDTOs;
    }

}
