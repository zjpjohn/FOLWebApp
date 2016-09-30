/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.api
*
* @File name : ExceptionHandingService.java
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
	
package com.sgm.dms.fol.common.api.services;

import com.sgm.dms.fol.common.api.domain.ExceptionHandingDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;

/*
*
* @author DELL
* TODO description
* @date 2016年1月22日
*/

public interface ExceptionHandingService {

    /**
     * 保存金额异常处理
     */
    public Boolean addAmountExceptionHanding(ExceptionHandingDTO exceptionHandingDTO,Long userId) throws ServiceAppException;
}
