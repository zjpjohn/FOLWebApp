/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : returnallowance.service
*
* @File name : ReturnAllowanceManagementServiceImpl.java
*
* @Author : st78sr
*
* @Date : 2016�?8�?1�?
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016�?8�?1�?    st78sr    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.returnallowance.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.exception.VoNotValidException;
import com.sgm.dms.fol.common.service.services.impl.BaseService;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CommonUtils;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.TokenUtils;
import com.sgm.dms.fol.common.service.utils.XmlConverUtil;
import com.sgm.dms.fol.returnallowance.api.ReturnAllowanceManagementService;
import com.sgm.dms.fol.returnallowance.domain.AllowanceInvoiceInfo;
import com.sgm.dms.fol.returnallowance.domain.AllowanceInvoiceInfoExample;
import com.sgm.dms.fol.returnallowance.domain.ReturnAllowancePO;
import com.sgm.dms.fol.returnallowance.domain.ReturnAllowancePOExample;
import com.sgm.dms.fol.returnallowance.domain.ReturnAllowancePOExample.Criteria;
import com.sgm.dms.fol.returnallowance.domain.ReturnOrder;
import com.sgm.dms.fol.returnallowance.domain.ReturnOrderExample;
import com.sgm.dms.fol.returnallowance.domain.SellerTaxNoMapping;
import com.sgm.dms.fol.returnallowance.domain.SellerTaxNoMappingExample;
import com.sgm.dms.fol.returnallowance.dto.AllowanceInvoiceInfoDto;
import com.sgm.dms.fol.returnallowance.dto.ReturnAllowanceDTO;
import com.sgm.dms.fol.returnallowance.dto.ReturnOrderDto;
import com.sgm.dms.fol.returnallowance.repository.AllowanceInvoiceInfoDao;
import com.sgm.dms.fol.returnallowance.repository.ReturnAllowanceDao;
import com.sgm.dms.fol.returnallowance.repository.ReturnOrderDao;
import com.sgm.dms.fol.returnallowance.repository.SellerTaxNoMappingDao;
import com.sgm.dms.fol.returnallowance.restclient.client.ReturnOrderUpdateClient;
import com.sgm.dms.fol.returnallowance.restclient.dto.ReturnOrdersRespDto.PartClaimReturnOrderDto;
import com.sgm.solution.framework.core.exception.ServiceException;


/*
*
* @author st78sr
* TODO description
* @date 2016�?8�?1�?
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class ReturnAllowanceManagementServiceImpl extends BaseService implements ReturnAllowanceManagementService {
	
	@Autowired
	private TokenUtils tokenUtils;

    @Autowired
    private ReturnAllowanceDao returnAllowanceDao;
    
    @Autowired
    private ReturnOrderDao returnOrderDao;
    
    @Autowired
    private AllowanceInvoiceInfoDao allowanceInvoiceInfoDao;
    
    @Autowired
    private ReturnOrderUpdateClient returnOrderUpdateClient;
    
    @Autowired
    private SellerTaxNoMappingDao sellerTaxNoMappingDao;
    
    /*
    *
    * @author st78sr
    * @date 2016�?8�?1�?
    * @param returnAllowanceDto
    * @return
    * @throws ServiceBizException
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.returnallowance.api.ReturnAllowanceManagementService#queryReturnAllowanceList(com.sgm.dms.fol.returnallowance.dto.ReturnAllowanceDTO)
    */

    @Override
    public List<ReturnAllowanceDTO> queryReturnAllowanceList4Dealer(ReturnAllowanceDTO returnAllowanceDto) throws ServiceBizException,
                                                                                                    SQLException {
        List<ReturnAllowanceDTO> raResultDtoList = null;
        try {
            //塞Dealer Code作为查询条件，每家经销商只能查自己的记录
            returnAllowanceDto.setSapCode(getSapCode());
            raResultDtoList = returnAllowanceDao.queryReturnAllowanceList4Dealer(returnAllowanceDto);
        } catch (SQLException e0) {
            throw new ServiceException(e0);
        } catch (Exception e1) {
            throw new ServiceException("unknow error", e1);
        }
        return raResultDtoList;
    }
    

    /*
    *
    * @author st78sr
    * @date 2016�?8�?8�?
    * @param returnAllowanceDto
    * @return
    * @throws ServiceBizException
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.returnallowance.api.ReturnAllowanceManagementService#queryReturnAllowanceList4SGM(com.sgm.dms.fol.returnallowance.dto.ReturnAllowanceDTO)
    */
        
    @Override
    public List<ReturnAllowanceDTO> queryReturnAllowanceList4SGM(ReturnAllowanceDTO returnAllowanceDto) {
        List<ReturnAllowanceDTO> raResultDtoList = null;
        String[] sapCodeArray = null;
        List<String> sapCodeList = new ArrayList<String>();
        
        try {
            //把前端传入的逗号分隔的sapcode，如2100011,2100022。转换成List
            if ("".equals(returnAllowanceDto.getSapCode()) || returnAllowanceDto.getSapCode() == null){
                //do nothing
            }
            else{
                sapCodeArray = returnAllowanceDto.getSapCode().split(",");
                for(String sapcode:sapCodeArray){
                    sapCodeList.add(sapcode);
                }
                returnAllowanceDto.setSapCodeList(sapCodeList);
            }
            
            ArrayList<Integer> statusList = new ArrayList<>();
            statusList.add(POConstant.RETURN_ALLOWANCE_STATUS_DLR_REPORTED);
            statusList.add(POConstant.RETURN_ALLOWANCE_STATUS_SGM_SUCCESS);
            statusList.add(POConstant.RETURN_ALLOWANCE_STATUS_SGM_FAIL);
            statusList.add(POConstant.RETURN_ALLOWANCE_STATUS_SAP_SUCCESS);
            returnAllowanceDto.setStatusList(statusList);
            raResultDtoList = returnAllowanceDao.queryReturnAllowanceList4SGM(returnAllowanceDto);
        } catch (SQLException e0) {
            throw new ServiceException(e0);
        } catch (Exception e1) {
            throw new ServiceException("unknow error", e1);
        }
        return raResultDtoList;
    }
    
    
    /*
    *
    * @author st78sr
    * @date 2016�?8�?3�?
    * @param returnAllowanceDto
    * @return
    * @throws ServiceBizException
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.returnallowance.api.ReturnAllowanceManagementService#deleteReturnAllowanceById(com.sgm.dms.fol.returnallowance.dto.ReturnAllowanceDTO)
    */
    	
    //折让单-dealer作废
    @Override
    public int deleteReturnAllowanceById(ReturnAllowanceDTO returnAllowanceDto){
            //1.更新FOL折让单状态
            int resultOfDelete = returnAllowanceDao.deleteReturnAllowanceById(returnAllowanceDto);
            
            //更新POL退货证明中的折让单处理状态为0
            //2.通过RestClient，调用POL接口更新退货单的折让单状态字段信息，须POL返回成功后再更新FOL本地折让单状态，因为不在一个事务中
            returnOrderUpdateClient.updateReturnOrder4Dealer(returnAllowanceDto.getReturnOrderNo(), POConstant.POL_RETURN_ORDER_STATUS_AVAIL);
            
            return (resultOfDelete);
    }


    /*
    *
    * @author st78sr
    * @date 2016年8月29日
    * @param returnAllowanceDto
    * @return
    * @throws ServiceBizException
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.returnallowance.api.ReturnAllowanceManagementService#countReturnAllowance4Dealer(com.sgm.dms.fol.returnallowance.dto.ReturnAllowanceDTO)
    */
    	
    @Override
    public int countReturnAllowance4Dealer(ReturnAllowanceDTO returnAllowanceDto) throws ServiceBizException,
                                                                                  SQLException {
        int count = 0;
        try{
            //塞Dealer Code作为查询条件，每家经销商只能查自己的记录
            returnAllowanceDto.setSapCode(getSapCode());
            count = returnAllowanceDao.countReturnAllowance4Dealer(returnAllowanceDto);
        } catch (SQLException e0) {
            throw new ServiceException(e0);
        } catch (Exception e1) {
            throw new ServiceException("unknow error", e1);
        }
        return count;
        
    }

    
    /*
    *
    * @author st78sr
    * @date 2016年8月24日
    * @param returnAllowanceDto
    * @return
    * @throws ServiceBizException
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.returnallowance.api.ReturnAllowanceManagementService#countReturnAllowance(com.sgm.dms.fol.returnallowance.dto.ReturnAllowanceDTO)
    */
    @Override
    public int countReturnAllowance4SGM(ReturnAllowanceDTO returnAllowanceDto) throws ServiceBizException, SQLException {
        int count = 0;
        String[] sapCodeArray = null;
        List<String> sapCodeList = new ArrayList<String>();
        
        try{
        	//把前端传入的逗号分隔的sapcode，如2100011,2100022。转换成List
            if ("".equals(returnAllowanceDto.getSapCode()) || returnAllowanceDto.getSapCode() == null){
                //do nothing
            }
            else{
                sapCodeArray = returnAllowanceDto.getSapCode().split(",");
                for(String sapcode:sapCodeArray){
                    sapCodeList.add(sapcode);
                }
                returnAllowanceDto.setSapCodeList(sapCodeList);
            }
            
            ArrayList<Integer> statusList = new ArrayList<>();
            statusList.add(POConstant.RETURN_ALLOWANCE_STATUS_DLR_REPORTED);
            statusList.add(POConstant.RETURN_ALLOWANCE_STATUS_SGM_SUCCESS);
            statusList.add(POConstant.RETURN_ALLOWANCE_STATUS_SGM_FAIL);
            statusList.add(POConstant.RETURN_ALLOWANCE_STATUS_SAP_SUCCESS);
            returnAllowanceDto.setStatusList(statusList);
            
            count = returnAllowanceDao.countReturnAllowance4SGM(returnAllowanceDto);
        } catch (SQLException e0) {
            throw new ServiceException(e0);
        } catch (Exception e1) {
            throw new ServiceException("unknow error", e1);
        }
        return count;
    }
    

    /*
    *
    * @author st78sr
    * @date 2016年8月22日
    * @param returnOrderId
    * @return
    * @throws ServiceBizException
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.returnallowance.api.ReturnAllowanceManagementService#queryReturnOrderDetailByRoId(java.lang.String)
    */
    @Override
    public ReturnOrderDto queryReturnOrderDetailByRoId(Long returnOrderId) throws ServiceBizException, SQLException {
        ReturnOrder returnOrder = null;
        ReturnOrderDto returnOrderDto = null;
        try {
            //根据退货证明ID，查询退货证明ReturnOrder的明细(4SGM)
            returnOrder = returnOrderDao.selectByPrimaryKey(returnOrderId);
            
            //PO转DTO,再返回
            returnOrderDto = BeanMapper.map(returnOrder, ReturnOrderDto.class);
        } catch (Exception e1) {
            throw new ServiceException("unknow error", e1);
        }
        return returnOrderDto;
    }

    /*
    *
    * @author st78sr
    * @date 2016年8月22日
    * @param returnOrderId
    * @return
    * @throws ServiceBizException
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.returnallowance.api.ReturnAllowanceManagementService#queryInvoiceDetailByRoId(java.lang.String)
    */
    	
    @Override
    public List<AllowanceInvoiceInfoDto> queryInvoiceDetailByRoId(Long returnOrderId) throws ServiceBizException, SQLException {
        List<AllowanceInvoiceInfo> allowanceInvoiceInfoList = null;
        List<AllowanceInvoiceInfoDto> allowanceInvoiceInfoDtoList = null;
        
        try{
            //根据退货证明ID，查询退货证明ReturnOrder对应的发票明细(4SGM)
            AllowanceInvoiceInfoExample example = new AllowanceInvoiceInfoExample();
            AllowanceInvoiceInfoExample.Criteria createCriteria = example.createCriteria();
            createCriteria.andReturnOrderIdEqualTo(returnOrderId);
            createCriteria.andValidEqualTo((short)1);
            allowanceInvoiceInfoList = allowanceInvoiceInfoDao.selectByExample(example);
            
            //PO转DTO,再返回
            allowanceInvoiceInfoDtoList = BeanMapper.mapList(allowanceInvoiceInfoList, AllowanceInvoiceInfoDto.class);
        } catch (Exception e1) {
            throw new ServiceException("unknow error", e1);
        }
        
        return allowanceInvoiceInfoDtoList;
    }

    
    @Override
	public void reportReturnAllowance(Long id) throws IOException {
		//根据id查询折让单信息
		ReturnAllowancePO returnAllowancePO = returnAllowanceDao.selectByPrimaryKey(Long.parseLong(id.toString()));
		
		//更新数据库折让单信息
		ReturnAllowancePO record = new ReturnAllowancePO();
		record.setId(id);
		record.setStatus(POConstant.RETURN_ALLOWANCE_STATUS_DLR_REPORTED);
		record.setReportDate(new Date());
		record.setReporter(getUserId().intValue());
		record.setUpdateBy(getUserId().intValue());
		
		ReturnAllowancePOExample example = new ReturnAllowancePOExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andIdEqualTo(Long.parseLong(id.toString()));
		createCriteria.andStatusEqualTo((short)POConstant.RETURN_ALLOWANCE_STATUS_DLR_NOT_REPORTED);
		createCriteria.andValidEqualTo((short)1);
		int updateByExampleSelective = returnAllowanceDao.updateByExampleSelective(record, example);
		if(updateByExampleSelective == 0) throw new ServiceBizException("该折让单不存在或已上报，请刷新重试;");
		
		//调用POL接口更新折让单信息
		returnOrderUpdateClient.updateReturnOrder4Dealer(returnAllowancePO.getReturnOrderNo(), 2);
	}

	@Override
	public void billing(Long id, String approveMsg) throws IOException {
		//根据id查询折让单信息
		ReturnAllowancePO returnAllowancePO = returnAllowanceDao.selectByPrimaryKey(id);
		
		//折让单处理状态变为SGM已同意
		ReturnAllowancePO record = new ReturnAllowancePO();
		record.setStatus(POConstant.RETURN_ALLOWANCE_STATUS_SGM_SUCCESS);
		record.setApproveMsg(approveMsg);
		record.setApproveMan(getUserId().intValue());
		record.setApproveDate(new Date());
		record.setUpdateBy(getUserId().intValue());
		
		ReturnAllowancePOExample example = new ReturnAllowancePOExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andIdEqualTo(Long.parseLong(id.toString()));
		createCriteria.andStatusEqualTo((short)POConstant.RETURN_ALLOWANCE_STATUS_DLR_REPORTED);//只有dealer已上报状态才可开�?
		createCriteria.andValidEqualTo((short)1);
		
		CommonUtils.filterSpecialWord(record);
		CommonUtils.filterSpecialWord(example);
		
		int updateResult = returnAllowanceDao.updateByExampleSelective(record, example );
		
		if(updateResult == 0) throw new ServiceBizException("该折让单不存在或状态不是dealer已上报状态");//数据库没有更新，处理失败�?
		
		//调用POL接口更新折让单信息
		CommonUtils.filterSpecialWord(returnAllowancePO);
		returnOrderUpdateClient.updateReturnOrder4SGM(returnAllowancePO.getReturnOrderNo(), POConstant.POL_RETURN_ORDER_STATUS_SGM_SUCCESS, returnAllowancePO.getSapCode());
	}

	@Override
	public String getBillingFileContent(String ids) throws Exception  {
		StringBuffer resultStr = new StringBuffer();//生成文件的内容
		
		String token = tokenUtils.getTokenStr();//文件上传、下载需要的token
		
		//循环生成每个折让单对应的票文件内容
		for (String id : ids.split(",")) {
			//折让单信息
			ReturnAllowancePO returnAllowance = returnAllowanceDao.selectByPrimaryKey(Long.parseLong(id));
			if(POConstant.RETURN_ALLOWANCE_STATUS_SGM_SUCCESS != returnAllowance.getStatus()) throw new ServiceBizException("只有SGM已同意状态的才可导出开票文件");
			
			//XML折让单税控机信息
			String xmlJsonStr = XmlConverUtil.xmltoJson(returnAllowance.getFiledId(), token);
			JSONObject xmljsonObject = new JSONObject(xmlJsonStr.substring(1, xmlJsonStr.length() - 1));
			
			//折让单开票文件格式如下：
			//折让单号,税种类别,折让单XML中客户名称，XML中客户税号，折让单中客户名称+电话，折让单中银行账号，开具红字增值税专用发票信息表编号16位该字段值;发票:XML文件中发票号码;
			//客户编号:000+七位经销商代码，施一鸣,朱燕萍
			//配件,批,,-1，XML中不含税总价，XML中税率，1301,0,XML中税额,

			resultStr.append(returnAllowance.getAllowanceNo()).append(",");//折让单号,
			//resultStr.append(xmljsonObject.get("InvKind")).append(",");//税种类别,
			resultStr.append("1").append(",");//税种类别,
			resultStr.append(xmljsonObject.get("BuyerName")).append(",");//折让单XML中客户名称，
			resultStr.append(xmljsonObject.get("BuyTaxCode")).append(",");//XML中客户税号,
			resultStr.append(xmljsonObject.get("BuyerName")).append(returnAllowance.getPhone()).append(",");//折让单中客户名称+电话
			resultStr.append(returnAllowance.getBankAccount()).append(",");//折让单中银行账号
			resultStr.append("开具红字增值税专用发票信息表编号").append(xmljsonObject.get("ReqBillNo"));//开具红字增值税专用发票信息表编号16位该字段值;
			resultStr.append(";发票:").append(xmljsonObject.get("InvNo")).append(";");//发票:XML文件中发票号码;
			resultStr.append("\n客户编号:000").append(returnAllowance.getSapCode()).append(",");//客户编号:000+七位经销商代码，
			resultStr.append("施一鸣,朱燕萍");//施一鸣,朱燕萍
			resultStr.append("\n配件,批,,-1,");//配件,批,'',-1,
			resultStr.append(xmljsonObject.get("Amount")).append(",");//XML中不含税总价
			resultStr.append(xmljsonObject.get("TaxRate")).append(",");//XML中税率，
			resultStr.append("1301,0,");//1301,0,
			resultStr.append(xmljsonObject.get("Tax")).append(",");//XML中税额,
			resultStr.append("\n\n");
		}
		
		return resultStr.toString();
	}

	/*
    *
    * @author st78sr
    * @date 2016�?8�?15�?
    * @param returnAllowanceDto
    * @return
    * @throws ServiceBizException
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.returnallowance.api.ReturnAllowanceManagementService#rejectReturnAllowance4SGM(com.sgm.dms.fol.returnallowance.dto.ReturnAllowanceDTO)
    */
	//输入参数：折让单ID，退货证明单号，经销商代码
    @Override
    public void rejectReturnAllowance4SGM(Integer allowanceId,String returnOrderNo,String sapCode, String approveMsg) throws ServiceBizException,SQLException,IOException {
        
        //只有状态为"Dealer已上报"/"SGM已同意"状态的折让单可以被拒绝
        List<Short> statusToReject = new ArrayList<Short>();
        statusToReject.add((short)POConstant.RETURN_ALLOWANCE_STATUS_DLR_REPORTED);
        statusToReject.add((short)POConstant.RETURN_ALLOWANCE_STATUS_SGM_SUCCESS);
        
        //1.更新FOL折让单状态
        ReturnAllowancePO record = new ReturnAllowancePO();
        record.setStatus(POConstant.RETURN_ALLOWANCE_STATUS_SGM_FAIL);
        record.setApproveMsg(approveMsg);
        record.setApproveMan(getUserId().intValue());
        record.setApproveDate(new Date());
        record.setUpdateBy(getUserId().intValue());
        
        ReturnAllowancePOExample example = new ReturnAllowancePOExample();
        Criteria createCriteria = example.createCriteria();
        createCriteria.andIdEqualTo(Long.parseLong(allowanceId.toString()));
        createCriteria.andStatusIn(statusToReject); //只有状态为"Dealer已上报"/"SGM已同意"状态的折让单可以被拒绝
        createCriteria.andValidEqualTo((short)1);
        
        CommonUtils.filterSpecialWord(record);
        CommonUtils.filterSpecialWord(example);
        int updateResult = returnAllowanceDao.updateByExampleSelective(record, example);
        if(updateResult == 0) throw new ServiceBizException("该折让单不存在或状态不是dealer已上报/SGM已同意状态");//数据库没有更新，处理失败
        
        //2.通过RestClient，调用POL接口更新退货单的折让单状态字段信息，须POL返回成功后再更新FOL本地折让单状态，因为不在一个事务中
        returnOrderUpdateClient.updateReturnOrder4SGM(returnOrderNo, POConstant.POL_RETURN_ORDER_STATUS_SGM_FAIL, sapCode);
        
    }

    
    
	/*
    *
    * @author st78sr
    * @date 2016�?8�?22�?
    * @throws ServiceBizException
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.returnallowance.api.ReturnAllowanceManagementService#checkReturnAllowanceStatus4SGM()
    */
    	
    @Override
    public void checkReturnAllowanceStatus4SGM(List<ReturnAllowanceDTO> returnAllowanceDTOList) throws ServiceBizException, SQLException {
        //循环，拒绝每一个折让单
        for (int i = 0; i < returnAllowanceDTOList.size(); i++) {
            ReturnAllowanceDTO returnAllowanceDTO = returnAllowanceDTOList.get(i);
            
            //折让单ID
            long returnAllowanceId = returnAllowanceDTO.getId().longValue();
            
            //判断FOL中，本折让单状态，是否为“dealer已提交sgm未审批”和“sgm已审批通过”，其他状态的折让单不能被拒绝。返回哪个折让单号校验不通过。
            ReturnAllowancePO raPO = returnAllowanceDao.selectByPrimaryKey(returnAllowanceId);
            if(
               (!raPO.getStatus().equals(POConstant.RETURN_ALLOWANCE_STATUS_DLR_REPORTED)) &&
               (!raPO.getStatus().equals(POConstant.RETURN_ALLOWANCE_STATUS_SGM_SUCCESS))
              )
            {
                throw new ServiceBizException("折让单《"+raPO.getAllowanceNo()+"》的状态，不是“dealer已提交”或“sgm已审批通过”，所以不能被拒绝");
            }
        }
        
    }

    
    /*
    *
    * @author st78sr
    * @date 2016年8月29日
    * @param returnAllowanceDTOList
    * @param returnAllowanceStatus
    * @throws ServiceBizException
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.returnallowance.api.ReturnAllowanceManagementService#checkReturnAllowanceStatus4Dealer(java.util.List, int)
    */
    	
    @Override
    public void checkReturnAllowanceStatus4Dealer(ReturnAllowanceDTO returnAllowanceDTO, int returnAllowanceStatus) throws ServiceBizException, SQLException {
        //循环，拒绝每一个折让单
//        for (int i = 0; i < returnAllowanceDTOList.size(); i++) {
//            ReturnAllowanceDTO returnAllowanceDTO = returnAllowanceDTOList.get(i);
            
            //折让单ID
            long returnAllowanceId = returnAllowanceDTO.getId().longValue();
            
            //判断FOL中，本折让单状态，是否为输入参数值returnAllowanceStatus。返回哪个折让单号校验不通过。
            ReturnAllowancePO raPO = returnAllowanceDao.selectByPrimaryKey(returnAllowanceId);
            if(!raPO.getStatus().equals(returnAllowanceStatus))
            {
                throw new ServiceBizException("折让单《"+raPO.getAllowanceNo()+"》的状态，不是“"+"dealer待上报"+"”");
            }
//        }
        
    }


    @Override
	public Long saveReturnAllowance(ReturnAllowanceDTO dto, PartClaimReturnOrderDto returnOrderInfo) throws Exception {
    	CommonUtils.filterSpecialWord(dto);
    	CommonUtils.filterSpecialWord(returnOrderInfo);
    	//校验Service
    	ReturnOrderDto returnOrderDto = BeanMapper.map(returnOrderInfo, ReturnOrderDto.class);;// 退货单
    	returnOrderDto.setBillingOrign(null != returnOrderInfo.getEinvoiceInfos() && !returnOrderInfo.getEinvoiceInfos().isEmpty() ? returnOrderInfo.getEinvoiceInfos().get(0).getBillingOrign() : "");
		
		validReturnAllowance(returnOrderDto, dto.getFiledId(), dto.getReqBillNo());
    	
		//校验该折让单号是否已存在
		final String allowanceNo = dto.getAllowanceNo();
		int countByExample = returnAllowanceDao.countByExample(new ReturnAllowancePOExample(){{createCriteria().andAllowanceNoEqualTo(allowanceNo).andValidEqualTo((short)1);}});
		if(countByExample > 0) throw new ServiceBizException("该折让单号刚刚你已使用，若要继续创建折让单，请刷新重试。");
		
		//保存退货单信息
		returnOrderDto = savaReturnOrder(dto.getReturnOrderNo(), returnOrderInfo);
		
		//保存折让单信息
		ReturnAllowancePO returnAllowance = BeanMapper.map(dto, ReturnAllowancePO.class);
		returnAllowance.setClaimReturnOrderId(returnOrderDto.getId());
		returnAllowance.setSapCode(getSapCode());
		returnAllowance.setLinetotal(returnOrderDto.getNetAmount());
		returnAllowance.setTax(returnOrderDto.getTax());
		returnAllowance.setStatus(POConstant.RETURN_ALLOWANCE_STATUS_DLR_NOT_REPORTED);
		returnAllowance.setValid(1);
		returnAllowance.setApplicant(getUserId().intValue());
		returnAllowance.setApplyDate(new Date());
		returnAllowance.setCreateBy(getUserId().intValue());
		returnAllowance.setUpdateBy(getUserId().intValue());
		//获取XML信息
		String xmltoJson = XmlConverUtil.xmltoJson(dto.getFiledId(), tokenUtils.getTokenStr());
		JSONObject xmlJsonObject = new JSONObject(xmltoJson.substring(1, xmltoJson.length()-1));
		if(null != xmlJsonObject.get("BuyerName")){
			returnAllowance.setCompanyName(xmlJsonObject.get("BuyerName").toString());
		}
		returnAllowanceDao.insert(returnAllowance);
		
		//调用POL接口更新POL中更新退货单状态信息
		returnOrderUpdateClient.updateReturnOrder4Dealer(dto.getReturnOrderNo(), 1);
		
		//返回折让单id
		return returnAllowance.getId();
		
	}

	@Override
	public void updateReturnAllowance(ReturnAllowanceDTO returnAllowanceDTO, PartClaimReturnOrderDto returnOrderInfo) throws Exception {
		CommonUtils.filterSpecialWord(returnAllowanceDTO);
    	CommonUtils.filterSpecialWord(returnOrderInfo);
		
		ReturnAllowancePO returnAllowance = BeanMapper.map(returnAllowanceDTO, ReturnAllowancePO.class);
		ReturnAllowancePO oldReturnAllowance = returnAllowanceDao.selectByPrimaryKey(Long.parseLong(returnAllowanceDTO.getId().toString()));
		
		//校验service
		ReturnOrderDto returnOrderDto = null;
		if(!returnAllowanceDTO.getReturnOrderNo().equals(oldReturnAllowance.getReturnOrderNo())){
			returnOrderDto = BeanMapper.map(returnOrderInfo, ReturnOrderDto.class);
			returnOrderDto.setBillingOrign(null != returnOrderInfo.getEinvoiceInfos() && !returnOrderInfo.getEinvoiceInfos().isEmpty() ? returnOrderInfo.getEinvoiceInfos().get(0).getBillingOrign() : "");
		}else{
			ReturnOrder returnOrder = null;// 退货证明
			ReturnOrderExample returnOrderExample = new ReturnOrderExample();
			com.sgm.dms.fol.returnallowance.domain.ReturnOrderExample.Criteria returnOrderCriteria = returnOrderExample.createCriteria();
			returnOrderCriteria.andReturnOrderNoEqualTo(returnAllowanceDTO.getReturnOrderNo());
			returnOrderCriteria.andSapCodeEqualTo(getSapCode());
			returnOrderCriteria.andValidEqualTo((short) 1);
			List<ReturnOrder> returnOrderList = returnOrderDao.selectByExample(returnOrderExample);
			returnOrder = returnOrderList.get(0);
			returnOrderDto = BeanMapper.map(returnOrder, ReturnOrderDto.class);
			AllowanceInvoiceInfoExample allowanceInvoiceInfoExample = new AllowanceInvoiceInfoExample();
			com.sgm.dms.fol.returnallowance.domain.AllowanceInvoiceInfoExample.Criteria allowanceInvoiceInfoCreateCriteria = allowanceInvoiceInfoExample.createCriteria();
			allowanceInvoiceInfoCreateCriteria.andReturnOrderIdEqualTo(returnOrderDto.getId());
			allowanceInvoiceInfoCreateCriteria.andValidEqualTo((short)1);
			List<AllowanceInvoiceInfo> allowanceInvoiceInfos = allowanceInvoiceInfoDao.selectByExample(allowanceInvoiceInfoExample );
			returnOrderDto.setBillingOrign(null != allowanceInvoiceInfos && !allowanceInvoiceInfos.isEmpty() ? allowanceInvoiceInfos.get(0).getBillingOrign() : "");
		}
		validReturnAllowance(returnOrderDto, returnAllowanceDTO.getFiledId(), returnAllowanceDTO.getReqBillNo());
		
		//更新退货单信息
		if(!returnAllowanceDTO.getReturnOrderNo().equals(oldReturnAllowance.getReturnOrderNo())){//折让单的的退货证明更新时
			//保存退货单信息
			returnOrderDto = savaReturnOrder(returnAllowanceDTO.getReturnOrderNo(), returnOrderInfo);
			//获取XML信息
			String xmltoJson = XmlConverUtil.xmltoJson(returnAllowanceDTO.getFiledId(), tokenUtils.getTokenStr());
			JSONObject xmlJsonObject = new JSONObject(xmltoJson.substring(1, xmltoJson.length()-1));
			if(null != xmlJsonObject.get("BuyerName")){
				returnAllowance.setCompanyName(xmlJsonObject.get("BuyerName").toString());
			}
			returnAllowance.setClaimReturnOrderId(returnOrderDto.getId());
			returnAllowance.setLinetotal(returnOrderDto.getNetAmount());
			returnAllowance.setTax(returnOrderDto.getTax());
		}
		
		//更新折让单信息
		returnAllowance.setUpdateBy(getUserId().intValue());
		ReturnAllowancePOExample updateExample = new ReturnAllowancePOExample();
		Criteria createCriteria = updateExample.createCriteria();
		createCriteria.andIdEqualTo(returnAllowance.getId());
		createCriteria.andValidEqualTo((short)1);
		createCriteria.andStatusEqualTo((short)POConstant.RETURN_ALLOWANCE_STATUS_DLR_NOT_REPORTED);
		int updateByExampleSelective = returnAllowanceDao.updateByExampleSelective(returnAllowance, updateExample);
		
		if(updateByExampleSelective <= 0){
			throw new ServiceBizException("该折让单不存在或不是待上报状态，不可编辑。");
		}
		
		if(!returnAllowanceDTO.getReturnOrderNo().equals(oldReturnAllowance.getReturnOrderNo())){
			//调用POL接口更新POL中更新退货单状态接口
			returnOrderUpdateClient.updateReturnOrder4Dealer(oldReturnAllowance.getReturnOrderNo(), 0);
			returnOrderUpdateClient.updateReturnOrder4Dealer(returnOrderDto.getReturnOrderNo(), 1);
		}
		
	}

	@Override
	public ReturnOrderDto savaReturnOrder(String returnOrderNo, PartClaimReturnOrderDto returnOrderInfo) {
		// 保存退货单信息
		ReturnOrder returnOrder = BeanMapper.map(returnOrderInfo, ReturnOrder.class);;//退货单
		List<AllowanceInvoiceInfo> allowanceInvoiceInfoList = BeanMapper.mapList(returnOrderInfo.getEinvoiceInfos(),AllowanceInvoiceInfo.class);;// 电子发票信息

		returnOrder.setValid((short) 1);
		returnOrder.setSapCode(getSapCode());
		returnOrder.setCreateBy(getUserId());
		returnOrder.setUpdateBy(getUserId());
		returnOrderDao.insert(returnOrder);

		// 保存索赔电子发票信息
		if (null != allowanceInvoiceInfoList) {
			for (AllowanceInvoiceInfo allowanceInvoiceInfo : allowanceInvoiceInfoList) {
				allowanceInvoiceInfo.setReturnOrderId(returnOrder.getId());
				allowanceInvoiceInfo.setReturnOrderNo(returnOrder.getReturnOrderNo());
				allowanceInvoiceInfo.setValid((short) 1);
				allowanceInvoiceInfo.setCreateBy(getUserId());
				allowanceInvoiceInfo.setUpdateBy(getUserId());
				allowanceInvoiceInfoDao.insert(allowanceInvoiceInfo);
			}
		}
		
		return BeanMapper.map(returnOrder, ReturnOrderDto.class);
	}
	
	//CCD+7位车工坊代码+YY+MM+四位流水号
	@Override
	public String getAllowanceNoFlowNo() {
		String resultAllowanceNo = "CCD"+getSapCode()+new SimpleDateFormat("yyMM").format(new Date());
		
		ReturnAllowancePOExample example = new ReturnAllowancePOExample();
		example.setOrderByClause("ALLOWANCE_NO desc");
		Criteria createCriteria = example.createCriteria();
		createCriteria.andCreateDateBetween(DateUtil.monthStartDate(new Date()), DateUtil.monthEndDate(new Date()));
		createCriteria.andSapCodeEqualTo(getSapCode());
		createCriteria.andValidEqualTo((short)1);
		List<ReturnAllowancePO> returnAllowanceList = returnAllowanceDao.selectByExample(example);
		String maxAllowanceNo = null == returnAllowanceList || returnAllowanceList.isEmpty() ? null : returnAllowanceList.get(0).getAllowanceNo();
		
		String flowNo = maxAllowanceNo == null ? "0000" : new DecimalFormat ("0000").format((Integer.parseInt(maxAllowanceNo.substring(maxAllowanceNo.length()-4))+1));
		resultAllowanceNo += flowNo;
		
		return resultAllowanceNo;
	}
	
	@Override
	public ReturnAllowanceDTO getReturnAllowanceById(Long id) {
		return BeanMapper.map(returnAllowanceDao.selectByPrimaryKey(id), ReturnAllowanceDTO.class);
	}

	@Override
	public ReturnOrderDto getReturnOrderById(Long id) {
		return BeanMapper.map(returnOrderDao.selectByPrimaryKey(id), ReturnOrderDto.class);
	}

	@Override
	public void validReturnAllowance(ReturnOrderDto returnOrderDto, String filedId, String reqBillNo) throws IOException, Exception {
		//获取XML信息
		String xmltoJson = XmlConverUtil.xmltoJson(filedId, tokenUtils.getTokenStr());
		JSONObject xmlJsonObject = new JSONObject(xmltoJson.substring(1, xmltoJson.length()-1));
		
		//校验
		// ||不含税金额+税额|—退货含税金额| <= 1
		BigDecimal xmlGross = new BigDecimal(xmlJsonObject.getString("Amount")).add(new BigDecimal(StringUtils.isNotBlank(xmlJsonObject.getString("Tax")) ? xmlJsonObject.getString("Tax") : "0")).abs();
		if(xmlGross.subtract(returnOrderDto.getGross()).abs().compareTo(new BigDecimal(1)) > 0)
			throw new VoNotValidException("红字发票申请单与电子发票含税金额差必须在正负一元之内！");
		
		SellerTaxNoMappingExample sellerTaxNoMappingExample = new SellerTaxNoMappingExample();
		com.sgm.dms.fol.returnallowance.domain.SellerTaxNoMappingExample.Criteria sellerTaxNoMappingCreateCriteria = sellerTaxNoMappingExample.createCriteria();
		sellerTaxNoMappingCreateCriteria.andSellerTaxNoEqualTo(xmlJsonObject.get("SellTaxCode").toString());
		sellerTaxNoMappingCreateCriteria.andValidEqualTo((short)1);
		List<SellerTaxNoMapping> SellerTaxNoMappings = sellerTaxNoMappingDao.selectByExample(sellerTaxNoMappingExample);
		SellerTaxNoMapping sellerTaxNoMapping = SellerTaxNoMappings.get(0);
		
		//XML文件中销方税号与退货证明中所属公司是否匹配
		//i.XML文件中销方税号与退货证明中所有配件退货申请单的退货电子发票表中BILLING_ORIGIN相匹配。ii.XML文件中销方税号要与销方名称相匹配。
		if(!returnOrderDto.getBillingOrign().equals(sellerTaxNoMapping.getBillingOrigin())){
			throw new ServiceBizException("XML文件中销方税号与退货证明中所有配件退货申请单的退货电子发票表中销方公司不匹配");
		}
		
		
		if(!sellerTaxNoMapping.getSellerName().equals(xmlJsonObject.get("SellerName"))){
			throw new ServiceBizException("XML文件中销方税号要与销方名称不匹配");
		}
		
		//XML文件中红票申请单号和专用红票通知单号要一致
		if(!xmlJsonObject.get("ReqBillNo").toString().equals(reqBillNo)){
			throw new ServiceBizException("红票专用通知单号与XML中红票申请单号不匹配，请检查!");
		}
		
	}


	@Override
	public void updateReturnAllowanceStatus(String ids,
			int status) {
		ReturnAllowancePO record = new ReturnAllowancePO();
		record.setStatus(status);
		
		ReturnAllowancePOExample example = new ReturnAllowancePOExample();
		String[] idArray = ids.split(",");
		List<Long> idList = new ArrayList<>();
		for (int i = 0; i < idArray.length; i++) {
			idList.add(Long.parseLong(idArray[i]));
		}
		example.createCriteria().andIdIn(idList);
		
		returnAllowanceDao.updateByExampleSelective(record, example);
	}
    
}
