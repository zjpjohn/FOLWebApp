/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.api
*
* @File name : DealerService.java
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
	
package com.sgm.dms.fol.common.api.services;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.common.api.domain.DealerInfoDTO;



/*
 *
 * @author shenweiwei
 * TODO description
 * @date 2015年10月22日
 */

public interface DealerService {
    
    //根据SAP_CODE区间查找经销商信息
    public List<DealerInfoDTO> getDealerInfoBySapCodeInterval(DealerInfoDTO dealerinfodto) throws SQLException;
    
    //根据SAP_CODE查找单个经销商信息
    public DealerInfoDTO getDealerInfoBySapCode(DealerInfoDTO dealerinfodto) throws SQLException;

    //根据Dealer_Id查找单个经销商信息
    public DealerInfoDTO getDealerInfoByDealerId(DealerInfoDTO dealerinfodto) throws SQLException;
    
    /**
     * 分页查询经销商信息
     * @author wangfl
     * @date 2016年8月30日
     * @param dto
     * @return
     */
    List<DealerInfoDTO> dealerInfoPageList(DealerInfoDTO dto);
    
    /**
     * 按条件查询总数
     * @author wangfl
     * @date 2016年8月30日
     * @param dto
     * @return
     */
    Integer getDealerInfoListNum(DealerInfoDTO dto);
}
