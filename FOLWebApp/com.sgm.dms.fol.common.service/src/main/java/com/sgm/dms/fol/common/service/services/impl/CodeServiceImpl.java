/*
 * Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.service
 *
 * @File name : CodeServiceImpl.java
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

package com.sgm.dms.fol.common.service.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgm.dms.fol.common.api.domain.CodeDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.services.CodeService;
import com.sgm.dms.fol.common.service.domains.CodePo;
import com.sgm.dms.fol.common.service.repository.CodeDao;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CommonUtils;
import com.sgm.dms.fol.common.service.utils.StringUtil;

/*
 *
 * @author shenweiwei
 * TODO description
 * @date 2015年10月15日
 */
@Service("CodeService")
public class CodeServiceImpl implements CodeService {

    //日志操作
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private CodeDao codeDao;

    /*
     * 根据TypeName查code
     * @author shenweiwei
     * @date 2015年10月15日
     * @param typeName
     * @return (non-Javadoc)
     * @see com.sgm.dms.fol.common.api.services.CodeService#findCodeByTypeName(java.lang.String)
     */

    @Override
    public List<CodeDTO> findCodeByTypeName(String typeName) throws ServiceAppException,SQLException{
        List<CodeDTO> codedtolist=new ArrayList<CodeDTO>();

        List<CodePo> codePolist = codeDao.findCodeByTypeName(typeName);
            
        CodeDTO codeDTO=new CodeDTO();
        codeDTO.setCodeId(0L);
        codeDTO.setCode("-1");
        codeDTO.setCodeCnDesc("请选择");
        codeDTO.setSelected(true);
        codeDTO.setEnCode("-1");
        codedtolist.add(codeDTO);
        codedtolist.addAll(BeanMapper.mapList(codePolist, CodeDTO.class));

        return codedtolist;
    }
    
    public List<CodeDTO> findCodeByType(long type) throws ServiceAppException,SQLException{
        List<CodeDTO> codedtolist=new ArrayList<CodeDTO>();

        List<CodePo> codePolist = codeDao.findCodeByType(type);
        CodeDTO codeDTO=new CodeDTO();
        codeDTO.setCodeId(0L);
        codeDTO.setCode("-1");
        codeDTO.setCodeCnDesc("请选择");
        codedtolist.add(codeDTO);
        codedtolist.addAll(BeanMapper.mapList(codePolist, CodeDTO.class));
        
        return codedtolist;
    }

	@Override
	public CodeDTO findCodeByCodeId(Long codeId) throws ServiceAppException,SQLException{
        CodePo codePo = codeDao.findCodeByCodeId(codeId);
        
        CodeDTO codedto=BeanMapper.map(codePo, CodeDTO.class);
		return codedto;
	}

	@Override
	public int saveCodeType(CodeDTO codeDTO) throws ServiceAppException,SQLException{
		int result=0;
		CommonUtils.filterSpecialWord(codeDTO);
		initCodeData(codeDTO);
		result+=codeDao.saveCodeType(codeDTO);	
		return result;
	}

	private void initCodeData(CodeDTO codeDto) throws ServiceAppException,SQLException{
		String code="";
		//组合code
		if(!StringUtil.isEmpty(codeDto.getCode())){
		   code=String.valueOf(codeDto.getCode());
		   if(1==code.length()){
			   code="100"+code;
		   }else if(2==code.length()){
			   code="10"+code;
		   }
		}
		int codeId=0;
		if(!StringUtil.isEmpty(codeDto.getType())){
			codeId=Integer.valueOf(codeDto.getType()+code);
		}
	   Date now=new Date();
	   codeDto.setCodeId(Long.parseLong(String.valueOf(codeId)));
	   codeDto.setCreateDate(now);
	   codeDto.setUpdateDate(now);

	}

	@Override
	public int updateCodeTypeByCodeId(CodeDTO codeDto) throws ServiceAppException,SQLException{
	    CommonUtils.filterSpecialWord(codeDto);
		int result=codeDao.updateCodeTypeByCodeId(codeDto);
		if(result<=0){
		    throw new ServiceBizException("UPDATE BONUS TYPE FAIL");
		}
		return result;
	}

	@Override
	public List<CodeDTO> findCodeDataByCodeCnAndCode(CodeDTO dto) throws ServiceAppException,SQLException{
		List<CodeDTO> data=codeDao.findCodeDataByCodeCnAndCode(dto);
		return data;
	}

	@Override
	public int getTotalTypeByWhs(CodeDTO code) throws ServiceAppException,SQLException{
		return codeDao.getTotalTypeByWhs(code);
	}

	@Override
	public CodeDTO getcodeCnDescByCodeAndTypeName(CodeDTO codeDTO) throws ServiceAppException,SQLException{
		List<CodeDTO> list=codeDao.getcodeCnDescByCodeAndTypeName(codeDTO);
		if(null==list||list.size()==0){
			throw new ServiceBizException("CODE OR TYPE IS EMPTY");
		}
		return list.get(0);
	}

}
