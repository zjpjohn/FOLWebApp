
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.api
 *
 * @File name : AuthorMethodService.java
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
	
package com.sgm.dms.fol.common.api.services;

import java.util.List;

import com.sgm.dms.fol.common.api.domain.AuthorMethodDTO;
import com.sgm.dms.fol.common.api.domain.RoleMenuDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;

/**
 * @author wangfl
 * TODO description
 * @date 2016年3月31日
 */

public interface AuthorMethodService {

    /**
     * 
     * @author wangfl
     * 查询方法权限列表-分页
     * @date 2016年3月31日
     * @param authorMethodDTO
     * @return
     * @throws ServiceBizException
     */
    List<AuthorMethodDTO>  getAuthorMethodList(AuthorMethodDTO authorMethodDTO) throws ServiceBizException;
    
    /**
     * 
     * @author wangfl
     * 查询方法权限列表总数
     * @date 2016年3月31日
     * @param authorMethodDTO
     * @return
     * @throws ServiceBizException
     */
    Long  getAuthorMethodListCount(AuthorMethodDTO authorMethodDTO) throws ServiceBizException;
    
    /**
     * 
     * @author wangfl
     * 新增方法权限信息
     * @date 2016年3月31日
     * @param authorMethodDTO
     * @throws ServiceBizException
     */
    void addAuthorMethod(AuthorMethodDTO authorMethodDTO) throws ServiceBizException;
    
    /**
     * 
     * @author wangfl
     * 修改方法权限信息
     * @date 2016年3月31日
     * @param authorMethodDTO
     * @throws ServiceBizException
     */
    void updateAuthorMethod(AuthorMethodDTO authorMethodDTO) throws ServiceBizException;
    
    /**
     * 
     * @author wangfl
     * 根据methodId删除方法权限信息
     * @date 2016年4月1日
     * @param methodId
     * @throws ServiceBizException
     */
    void delAuthorMethodById(Integer methodId) throws ServiceBizException;
    
    /**
     * 
     * @author wangfl
     * 查询所有方法权限信息-By RoleId
     * @date 2016年4月1日
     * @param roleId
     * @return
     * @throws ServiceBizException
     */
    public List<RoleMenuDTO> findMethodByMenuTypeRoleId(long roleId) throws ServiceBizException;
    
}
