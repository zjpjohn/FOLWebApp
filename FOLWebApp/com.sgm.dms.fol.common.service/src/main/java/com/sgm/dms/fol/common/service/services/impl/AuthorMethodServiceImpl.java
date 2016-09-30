
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.service
 *
 * @File name : AuthorMethodServiceImpl.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年3月31日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年3月31日    wangfl    1.0
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
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.common.api.domain.AuthorMethodDTO;
import com.sgm.dms.fol.common.api.domain.RoleMenuDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.services.AuthorMethodService;
import com.sgm.dms.fol.common.service.repository.AuthorMethodDao;


/**
 * @author wangfl
 * TODO description
 * @date 2016年3月31日
 */
@Service
@Transactional(rollbackFor=ServiceBizException.class)
public class AuthorMethodServiceImpl implements AuthorMethodService {
    private Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private AuthorMethodDao authorMethodDao;

    /**
     * @author wangfl
     * @date 2016年3月31日
     * @param authorMethodDTO
     * @return
     * @throws ServiceBizException
     * (non-Javadoc)
     * @see com.sgm.dms.fol.common.api.services.AuthorMethodService#getAuthorMethodList(com.sgm.dms.fol.common.api.domain.AuthorMethodDTO)
     */

    @Override
    public List<AuthorMethodDTO> getAuthorMethodList(AuthorMethodDTO authorMethodDTO) throws ServiceBizException {
        List<AuthorMethodDTO> resultList = null;
        
        try {
            resultList = authorMethodDao.selectAuthorMethodPageList(authorMethodDTO);
        } catch (SQLException e) {
            logger.info("查询信息异常 :"+e.getMessage());
            throw new ServiceBizException(e.getMessage());
        }
        return resultList;
    }

    @Override
    public Long getAuthorMethodListCount(AuthorMethodDTO authorMethodDTO) throws ServiceBizException {
        Long totalNum = null;
        try {
            totalNum = authorMethodDao.selectAuthorMethodListCount(authorMethodDTO);
        } catch (SQLException e) {
            logger.info("查询信息异常 :"+e.getMessage());
            throw new ServiceBizException(e.getMessage());
        }
        return totalNum;
    }

    @Override
    public void addAuthorMethod(AuthorMethodDTO authorMethodDTO) throws ServiceBizException {
        
        try {
            AuthorMethodDTO authorMethodDTOTemp = new AuthorMethodDTO();
            authorMethodDTOTemp.setUrl(authorMethodDTO.getUrl());
            authorMethodDTOTemp.setClazz(authorMethodDTO.getClazz());
            authorMethodDTOTemp.setMethod(authorMethodDTO.getMethod());
            long count = authorMethodDao.selectAuthorMethodListCount(authorMethodDTOTemp);
            if(count > 0){
                throw new ServiceBizException("已存在相同url的方法权限信息");
            }
            authorMethodDao.insert(authorMethodDTO);
        } catch (SQLException e) {
            logger.info("新增信息异常 :"+e.getMessage());
            throw new ServiceBizException(e.getMessage());
        }
        
    }

    @Override
    public void updateAuthorMethod(AuthorMethodDTO authorMethodDTO) throws ServiceBizException {
        try {
            AuthorMethodDTO authorMethodDTOTemp = new AuthorMethodDTO();
            authorMethodDTOTemp.setUrl(authorMethodDTO.getUrl());
            List<AuthorMethodDTO> authorMethodList = authorMethodDao.selectAuthorMethodPageList(authorMethodDTOTemp);
            if(null != authorMethodList){
                for (AuthorMethodDTO authorMethodDTO2 : authorMethodList) {
                    if(authorMethodDTO2.getMethodId().intValue() != authorMethodDTO.getMethodId().intValue()){
                        throw new ServiceBizException("已存在相同url的方法权限信息");
                    }
                }
            }
            
            authorMethodDao.update(authorMethodDTO);
        } catch (SQLException e) {
            logger.info("修改信息异常 :"+e.getMessage());
            throw new ServiceBizException(e.getMessage());
        }
        
    }

    @Override
    public void delAuthorMethodById(Integer methodId) throws ServiceBizException {
        AuthorMethodDTO authorMethodDTO = new AuthorMethodDTO();
        authorMethodDTO.setMethodId(methodId);
        authorMethodDTO.setStatus(0);
        try {
            authorMethodDao.update(authorMethodDTO);
        } catch (SQLException e) {
            logger.info("删除信息异常 :"+e.getMessage());
            throw new ServiceBizException(e.getMessage());
        }
    }

    @Override
    public List<RoleMenuDTO> findMethodByMenuTypeRoleId(long roleId) throws ServiceBizException {
        List<RoleMenuDTO> dtoList = null;
        try {
            dtoList = authorMethodDao.findMethodByMenuTypeRoleId( roleId);
        } catch (SQLException se) {
            logger.info("查询信息异常 :"+se.getMessage());
            throw new ServiceBizException(se.getMessage());
        }
        return dtoList;
    }

}
