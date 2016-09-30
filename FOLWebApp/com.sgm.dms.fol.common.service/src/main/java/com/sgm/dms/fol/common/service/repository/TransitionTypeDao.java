/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.service
*
* @File name : TransitionTypeDao.java
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
	
package com.sgm.dms.fol.common.service.repository;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.common.service.domains.TransitionTypePo;


/*
 *
 * @author shenweiwei
 * TODO description
 * @date 2015年10月22日
 */

public interface TransitionTypeDao {
    
    /**
     * 查询出所有的 TransitionTypePo
     */
    public List<TransitionTypePo> queryAll() throws SQLException;
    
}
