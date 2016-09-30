/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.service
*
* @File name : DealerServiceImpl.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgm.dms.fol.common.api.domain.DealerInfoDTO;
import com.sgm.dms.fol.common.api.services.DealerService;
import com.sgm.dms.fol.common.service.domains.DealerInfo;
import com.sgm.dms.fol.common.service.domains.DealerInfoExample;
import com.sgm.dms.fol.common.service.domains.DealerInfoExample.Criteria;
import com.sgm.dms.fol.common.service.repository.DealerDao;
import com.sgm.dms.fol.common.service.utils.BeanMapper;


/*
 *
 * @author shenweiwei
 * TODO description
 * @date 2015年10月22日
 */
@Service("DealerService")
public class DealerServiceImpl implements DealerService{

    @Autowired
    private DealerDao dealerDao;
    /*
    *
    * @author shenweiwei
    * @date 2015年10月22日
    * @param dealerinfodto
    * @return
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.common.api.services.DealerService#getDealerInfoBySapCode(com.sgm.dms.fol.common.api.domain.DealerInfoDTO)
    */
    	
    @Override
    public List<DealerInfoDTO> getDealerInfoBySapCodeInterval(DealerInfoDTO dealerinfodto) throws SQLException {
        List<DealerInfoDTO> dealerInfoDTOs=BeanMapper.mapList(dealerDao.getDealerInfoBySapCodeInterval(dealerinfodto), DealerInfoDTO.class);
        return dealerInfoDTOs;
    }

    /*
    *
    * @author ShenWeiwei
    * @date 2015年10月27日
    * @param dealerinfodto
    * @return
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.common.api.services.DealerService#getDealerInfoBySapCodeInterval(com.sgm.dms.fol.common.api.domain.DealerInfoDTO)
    */
    	
    @Override
    public DealerInfoDTO getDealerInfoBySapCode(DealerInfoDTO dealerinfodto) throws SQLException {
        DealerInfoDTO dealInfoDTO=BeanMapper.map(dealerDao.getDealerInfoBySapCode(dealerinfodto), DealerInfoDTO.class);
        return dealInfoDTO;
    }

	@Override
	public DealerInfoDTO getDealerInfoByDealerId(DealerInfoDTO dealerinfodto)
			throws SQLException {
		DealerInfoDTO dealInfoDTO=BeanMapper.map(dealerDao.getDealerInfoByDealerId(dealerinfodto), DealerInfoDTO.class);
		return dealInfoDTO;
	}

	@Override
	public List<DealerInfoDTO> dealerInfoPageList(DealerInfoDTO dto) {
		
		DealerInfoExample example = new DealerInfoExample();
		example.setBeginNo(dto.getBeginNo());
		example.setEndNo(dto.getEndNo());
		
		Criteria createCriteria = example.createCriteria();
		createCriteria.andSapCodeLike("%" + dto.getSapCode() + "%");
		createCriteria.andDealerNameLike("%" + dto.getDealerName() + "%");
		createCriteria.andStatusCodeEqualTo(1001L);
		List<DealerInfo> DealerInfoList = dealerDao.selectPageListByExample(example);
		
		return BeanMapper.mapList(DealerInfoList, DealerInfoDTO.class);
	}

	@Override
	public Integer getDealerInfoListNum(DealerInfoDTO dto) {
		DealerInfoExample example = new DealerInfoExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andSapCodeLike("%" + dto.getSapCode() + "%");
		createCriteria.andDealerNameLike("%" + dto.getDealerName() + "%");
		createCriteria.andStatusCodeEqualTo(1001L);
		return dealerDao.countByExample(example);
	}
	
}
