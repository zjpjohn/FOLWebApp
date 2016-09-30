
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : returnallowance.service
 *
 * @File name : BaseDao.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年8月23日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年8月23日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */

package com.sgm.dms.fol.common.service.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * Dao层基类，用于抽取mybatis-generator自动生成的Dao方法
 * @author wangfl
 * @date 2016年8月23日
 */

public interface BaseDao<T, K> {
	int countByExample(K example);

	int deleteByExample(K example);

	int deleteByPrimaryKey(Long id);

	Long insert(T record);

	Long insertSelective(T record);

	List<T> selectByExample(K example);

	T selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") T record, @Param("example") K example);

	int updateByExample(@Param("record") T record, @Param("example") K example);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);
}
