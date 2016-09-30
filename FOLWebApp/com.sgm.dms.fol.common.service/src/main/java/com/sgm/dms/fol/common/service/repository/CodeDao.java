/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.service
*
* @File name : CodeDao.java
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
	
package com.sgm.dms.fol.common.service.repository;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.common.api.domain.CodeDTO;
import com.sgm.dms.fol.common.service.domains.CodePo;


/*
 *
 * @author shenweiwei
 * TODO description
 * @date 2015年10月15日
 */

public interface CodeDao {
    /**
     * 根据TypeName查code
     */
    public List<CodePo> findCodeByTypeName(String typeName) throws SQLException;
    
    /**
     * 根据Type查code
     */
    public List<CodePo> findCodeByType(long type) throws SQLException;
    
    /**
     * 根据CODE_ID查找CODE
     * @param codeId
     * @return
     * @throws SQLException
     */
    public CodePo findCodeByCodeId(Long codeId) throws SQLException;
    
    /*****
     * 新增数据字典类型
     */
	public int saveCodeType(CodeDTO code)throws SQLException;
	
	/****
	 * 查询最大的type
	 */
	public Integer findMaxCodeType()throws SQLException;
	
	/*****
	 * 更新数据字典类型
	 */
	public int updateCodeTypeByCodeId(CodeDTO code)throws SQLException;
	
	/***
	 * 根据code和codecn查询data
	 */
	public List<CodeDTO> findCodeDataByCodeCnAndCode(CodeDTO codeDTO)throws SQLException;
	
	public int getTotalTypeByWhs(CodeDTO code)throws SQLException;
	
	/*****
	 * 根据code和typename查询
	 * 
	 */
	public List<CodeDTO> getcodeCnDescByCodeAndTypeName(CodeDTO codeDTO)throws SQLException;
	/****
	 * 查询某个类型的type值
	 */
	public int findMaxCodeTypeByTypeName(String typeNmae)throws SQLException;
}
