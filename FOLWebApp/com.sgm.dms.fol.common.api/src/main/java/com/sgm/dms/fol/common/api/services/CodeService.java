/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.api
*
* @File name : CodeService.java
*
* @Author : shenweiwei
*
* @Date : 2015年10月15日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年10月15日    shenweiwei    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.common.api.services;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.common.api.domain.CodeDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;


/*
 *
 * @author shenweiwei
 * TODO description
 * @date 2015年10月15日
 */

public interface CodeService {
    /**
     * 根据TypeName查code
     */
    public List<CodeDTO> findCodeByTypeName(String typeName) throws ServiceAppException,SQLException;
    /**
     * 根据Type查code
     */
    public List<CodeDTO> findCodeByType(long type) throws ServiceAppException,SQLException;

    /**
     * 根据CODE_ID查找CODE
     * @param codeId
     * @return
     * @throws SQLException
     */
    public CodeDTO findCodeByCodeId(Long codeId) throws ServiceAppException,SQLException;
    
    
    /*****
     * 新增字典数据类型
     */
    public int  saveCodeType(CodeDTO codeDTO) throws ServiceAppException,SQLException;
    
    /****
     * 更新数据字典类型
     */
    /*****
	 * 更新数据字典类型
	 */
	public int updateCodeTypeByCodeId(CodeDTO codeDto) throws ServiceAppException,SQLException;
	
	/***
	 * 根据type和code查询一条codetypedata
	 * @throws SQLException 
	 */
	public List<CodeDTO> findCodeDataByCodeCnAndCode(CodeDTO dto) throws ServiceAppException,SQLException;
	/***
	 * 根据条件查询记录数
	 */
	public int getTotalTypeByWhs(CodeDTO code) throws ServiceAppException,SQLException;
	
	/****
	 * 根据code和typeName查询
	 */
	/*****
	 * 根据code和typename查询
	 * 
	 */
	public CodeDTO getcodeCnDescByCodeAndTypeName(CodeDTO codeDTO) throws ServiceAppException,SQLException;
}
