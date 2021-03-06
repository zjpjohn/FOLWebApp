/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.service
*
* @File name : ExceptionDao.java
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
	
package com.sgm.dms.fol.common.service.repository;

import java.sql.SQLException;

import com.sgm.dms.fol.common.service.domains.ExceptionHandingPo;

/*
*
* @author DELL
* TODO description
* @date 2016年1月22日
*/

public interface ExceptionHandingDao {

    /**
     * 保存金额异常处理
     */
    public int addAmountExceptionHanding(ExceptionHandingPo exceptionHandingPo) throws SQLException;

    /**
     * 获取TS_ID序列
     */
    public long getCurrentTsId() throws SQLException;
}
