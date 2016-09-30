package com.sgm.dms.fol.bonus.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.xml.ws.Holder;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsClientProxy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.bonus.api.BonusIssueCheckService;
import com.sgm.dms.fol.bonus.api.BonusIssueService;
import com.sgm.dms.fol.bonus.client.BonusFundRequestVo;
import com.sgm.dms.fol.bonus.client.BonusFundResultVo;
import com.sgm.dms.fol.bonus.client.BonusOperationWebService;
import com.sgm.dms.fol.bonus.client.BonusService;
import com.sgm.dms.fol.bonus.client.SGMCommonHeaderType;
import com.sgm.dms.fol.bonus.client.TranformBonusFund;
import com.sgm.dms.fol.bonus.client.TranformBonusFundResponse;
import com.sgm.dms.fol.bonus.common.CommonHelper;
import com.sgm.dms.fol.bonus.constants.PayBonusResultCodeConstants;
import com.sgm.dms.fol.bonus.dto.BonusIssueFileDTO;
import com.sgm.dms.fol.bonus.dto.BonusIssueFileFlowDTO;
import com.sgm.dms.fol.bonus.dto.BonusIssueFileRcTsDTO;
import com.sgm.dms.fol.bonus.dto.BonusIssueRecordDTO;
import com.sgm.dms.fol.bonus.dto.BonusIssueResultDTO;
import com.sgm.dms.fol.bonus.dto.PayBonusDTO;
import com.sgm.dms.fol.bonus.dto.RequestPayBonus;
import com.sgm.dms.fol.bonus.dto.ResponsePayBonus;
import com.sgm.dms.fol.bonus.repository.BonusIssueDao;
import com.sgm.dms.fol.bonus.repository.BonusIssueFileFlowDao;
import com.sgm.dms.fol.bonus.repository.BonusIssueFileRcTsDao;
import com.sgm.dms.fol.common.api.constants.CodeConstant;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.domain.CodeDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.services.CodeService;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.FileUtils;
import com.sgm.dms.fol.common.service.utils.RSAUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;

/****
 * 奖金发放impl
 * 
 * @author ZhangBao TODO description
 * @date 2015年12月15日
 */
@Service("BonusIssueService")
@Transactional(rollbackFor=Exception.class)
public class BonusIssueServiceImpl implements BonusIssueService {
	
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private BonusIssueDao bonusIssueDao;
	@Autowired
	private BonusIssueFileFlowDao bonusIssueFileFlowDao;
	@Autowired
	private BonusIssueFileRcTsDao bonusIssueFileRcTsDao;
	@Autowired
	private CodeService codeService;
	private String CHARSET ="gb2312";
	
	@Autowired
	private BonusIssueCheckService bonusIssueCheckService;
	@Override
	public List<BonusIssueFileDTO> findBonusIssueFileByWhs(
			BonusIssueFileDTO bonusVo) throws ServiceAppException {
		List<BonusIssueFileDTO> data = null;
		try {
			data = bonusIssueDao.findBonusIssueFileByWhs(bonusVo);
			if(data!=null && data.size()>0){
    			for (BonusIssueFileDTO dto : data) {
    			    dto.setEncryptId(RSAUtil.encryptByPublicKey(dto.getId().toString()));
//    			    dto.setEncryptBatchNo(RSAUtil.encryptByPublicKey(dto.getBatchNo()));
                }
			}
		} catch (SQLException e) {
		    logger.error(e);
			throw new ServiceBizException("查询奖金发放记录数据库异常:" + e);

		} catch (Exception e) {
		    logger.error(e);
            throw new ServiceBizException("查询奖金发放记录数据库异常:" + e);
        }
		return data;
	}

	@Override
	public int findBonusIssueFileCountByWhs(BonusIssueFileDTO bonus)
			throws ServiceAppException {
		int total = 0;
		try {
			total = bonusIssueDao.findBonusIssueFileCountByWhs(bonus);
		} catch (SQLException e) {
		    logger.error(e);
			throw new ServiceBizException("查询奖金发放总记录数异常:" + e);
		}
		return total;
	}

	@Override
	public int addImportBonusIssueFile(BonusIssueFileDTO vo, String token,String filedId,Long userId) throws ServiceAppException,IOException{
		int result = 0;
		
    	List<Object> data = getBonusTxtFile(filedId, token);
    	String batchNo = vo.getBatchNo();
    	if(StringUtil.isEmpty(batchNo)){
    		 batchNo=getBatchNo();
    	}
    	
    	// 创建大文件对象
    
    	BonusIssueFileDTO bonusFile = initBonusIssueFile(vo,userId);
    	bonusFile.setBatchNo(batchNo);
    	bonusFile.setFiledId(filedId);
    	Integer flag = checkBatchNoIsMatched(batchNo);
    	if(flag!=null&&0==flag){
    		//没有该批次的文件 add
    		saveBonusFile(bonusFile,data);
    		result=1;
    	}else if(flag!=null&&3==flag){
    		//该批次号已审核通过,不能在传
    	}else{
    		//update 直接更新文件
    		try {
    			int re=bonusIssueDao.updateBonusIssueFileStatusById(bonusFile);
    			if(0==re){
    				throw new ServiceBizException("更新数据失败");
    			}
    			
    			//作废原来的发放记录
    			List<BonusIssueRecordDTO> objList=bonusIssueDao.findBonusRecordFileByBatchNo(batchNo);
    			if(null!=objList&&objList.size()>1){
    				for(BonusIssueRecordDTO bonusRecord:objList){
    					bonusRecord.setStatus(POConstant.IS_NOT_STATUS);
    					bonusIssueDao.updateBonusIssueRecordStatusById(bonusRecord);
    				}
    			}
    			List<BonusIssueRecordDTO> list = BeanMapper.mapList(data,BonusIssueRecordDTO.class);
    			if (null != list && list.size() > 0) {
    				for (BonusIssueRecordDTO bonusRecord : list) {
    					initBonusRecord(bonusRecord, bonusFile);
    					bonusIssueDao.saveBonusIssueRecord(bonusRecord);
    				}
    			}
    
    			result=1;
    		} catch (SQLException e) {
    		    logger.error(e);
    			throw new ServiceBizException("更新或者保存数据失败:"+e);
    		}
    	}

		if(0==result){
		    throw(new ServiceBizException("导入奖金发放记录失败"));
        }

		return result;
	}
	
	
	

	private boolean isTurnFile(String batchNo)  {
		List<BonusIssueFileFlowDTO> list=null;
		try {
			list = bonusIssueFileFlowDao.findBonusCurrentStatus(batchNo);
		} catch (SQLException e) {
			logger.error(e);
			throw new ServiceBizException("根据批次号查询异常 :"+e);
		}
		if(null==list||list.size()==0){
			throw new ServiceBizException("未找到该批次的记录");
		}
		BonusIssueFileFlowDTO bonusFlow=list.get(0);
		if(null!=bonusFlow&&POConstant.BONUS_FLIE_FLOW_BACKED==bonusFlow.getOperatorStatus()){
			return true;
		}
		return false;
	}

	private void saveBonusFile(BonusIssueFileDTO bonusFile,List<Object> data) {
		try {
			int result = bonusIssueDao.saveBonusIssueFile(bonusFile);
			if (0 == result) {
				throw new ServiceBizException("保存数据失败");
			}
			
			//添加文件更新记录状态为已上传
			BonusIssueFileFlowDTO flow=initBonusIssueFileFlow(bonusFile,POConstant.BONUS_FILE_FLOW_UPLOADED);
			result=bonusIssueFileFlowDao.saveBonusIssueFileFlow(flow);
			List<BonusIssueRecordDTO> list = BeanMapper.mapList(data,
					BonusIssueRecordDTO.class);
			if (null != list && list.size() > 0) {
				for (BonusIssueRecordDTO bonusRecord : list) {
					initBonusRecord(bonusRecord, bonusFile);
					result += bonusIssueDao.saveBonusIssueRecord(bonusRecord);
				}
			}
		} catch (SQLException e) {
		    logger.error(e);
			throw new ServiceBizException("保存数据失败:" + e);
		}
		
	}

	private BonusIssueFileFlowDTO initBonusIssueFileFlow(BonusIssueFileDTO bonusFile,int status) {
		BonusIssueFileFlowDTO flow=new BonusIssueFileFlowDTO();
		flow.setBatchNo(bonusFile.getBatchNo());
		flow.setCreateDate(bonusFile.getCreateDate());
		flow.setCreateBy(bonusFile.getCreateBy());
		flow.setUpdateBy(bonusFile.getUpdateBy());
		flow.setUpdateDate(bonusFile.getUpdateDate());
		flow.setOperatorStatus(status);
		flow.setOperatorDate(bonusFile.getCreateDate());
		return flow;
	}

	private void initBonusRecord(BonusIssueRecordDTO bonusRecord,
			BonusIssueFileDTO bonusFile) throws ServiceBizException{
		bonusRecord.setBonusType(bonusFile.getBonusType());
		bonusRecord.setDealerType(bonusFile.getDealerType());
		bonusRecord.setStatus(POConstant.IS_STATUS);// 状态为有效
		bonusRecord.setEffectDate(bonusFile.getEffectDate());
		bonusRecord.setCreateDate(bonusFile.getCreateDate());
		bonusRecord.setUpdateDate(bonusFile.getUpdateDate());
		bonusRecord.setCreateBy(bonusFile.getCreateBy());
		bonusRecord.setUpdateBy(bonusFile.getUpdateBy());
		bonusRecord.setBatchNo(bonusFile.getBatchNo());
		
		
		try {
			CodeDTO codeDTO=new CodeDTO();
			codeDTO.setCodeCnDesc(bonusRecord.getServName());
			codeDTO.setBeginNo(0);
			codeDTO.setEndNo(10);
			List<CodeDTO> codedtos=codeService.findCodeDataByCodeCnAndCode(codeDTO);
			
			if(codedtos==null||codedtos.size()<=0){
				throw(new ServiceBizException("没有相关的销售公司CODE"));
			}
			
			bonusRecord.setServ(Integer.valueOf(codedtos.get(0).getCode()));
		} catch (SQLException e) {
		    logger.error(e);
			throw(new ServiceAppException(e));
		}
		
		
	}

	private BonusIssueFileDTO initBonusIssueFile(BonusIssueFileDTO dto,Long userId) {
		Date now = new Date();
		if (null != dto) {
		    dto.setStatus(POConstant.IS_STATUS);
		    dto.setEffectDate(now);
		    dto.setIssueStatus(POConstant.UN_ISSUE);// 待发放状态
		    dto.setMatchState(POConstant.UN_MATCH_STATE);// 未审核状态
		    dto.setCreateBy(userId);
		    dto.setUpdateBy(userId);
		}
		
		return dto;
	}

	public List<Object> getBonusTxtFile(String filedId, String token) throws IOException {

		List<Object> list = new ArrayList<Object>();

		// 创建reader

		ByteArrayOutputStream out = (ByteArrayOutputStream) FileUtils.downLoadStream(filedId, token);
		if(null==out){
			throw new IOException("创建outputstream 失败");
		}
		ByteArrayInputStream is = new ByteArrayInputStream(out.toByteArray());
		BufferedReader br = new BufferedReader(new InputStreamReader(is,CHARSET));
		try {
			// 文件行内容
			String lineTxt = null;

			// 经销商code
			String sapCode = "";

			// 批次号
			String flowNo = "";

			// 备注
			String remark = "";

			// 销售公司
			String serv = "";
			// 奖金1
			String bonus1 = "";

			// 奖金2
			String bonus2 = "";

			// 拼接文件内容
			int index = 0;
			String fileName="";
			ByteArrayInputStream input=null;
			while ((lineTxt = br.readLine()) != null) {
				Map<String, String> obj = new Hashtable<String, String>();
				if (index > 0) {
					
					String[] str = lineTxt.split(",");
					// 获取经销商代码
					sapCode = str[0];// 获取dealerCode
					flowNo = str[1];
					remark = str[2];
					serv = str[3];
					bonus1 = str[4];
					if(str.length==6){
						bonus2 =str[5] ;
					}
					
					
					//上传文件
					// 单个文件结束,保存需要的信息,上传到服务器
					fileName=sapCode+"-"+DateUtil.formartDate(new Date());
					input = new ByteArrayInputStream(lineTxt.getBytes(CHARSET));
					String fileid = FileUtils.upload(fileName, token,input);
					input.close();
					
					// 保存需要的信息
					obj.put("sapCode", sapCode);
					obj.put("flowNo", flowNo);
					obj.put("remark", remark);
					obj.put("servName", serv);
					obj.put("amount", bonus1);
					obj.put("fileId", fileid);
					list.add(obj);
					if (!StringUtil.isEmpty(bonus2)) {
						obj = new Hashtable<String, String>();
						obj.put("sapCode", sapCode);
						obj.put("flowNo", flowNo);
						obj.put("remark", remark);
						obj.put("servName", serv);
						obj.put("amount", bonus2);
						obj.put("fileId", fileid);
						list.add(obj);
					}
					

				}
				index++;
			}

		}catch(Exception e){
		    logger.error(e);
		    throw(new IOException(e));
		} finally {
			br.close();
			is.close();
			out.close();
		}

		return list;

	}

	@Override
	public Integer checkBatchNoIsMatched(String batchNo) throws ServiceBizException {
		try {
			List<BonusIssueFileDTO> data = bonusIssueDao.findBonusIssueFileByBatchNo(batchNo);
			if (null != data && data.size() > 0) {
				BonusIssueFileDTO bonusfile = data.get(0);
				return bonusfile.getMatchState();

			}
		} catch (SQLException e) {
		    logger.error(e);
			throw new ServiceBizException("检查发放文件出错" + e);
		}
		return 0;
	}

	@Override
	public int updateBonusIssueFile(List<BonusIssueFileDTO> vo,long userId) throws ServiceBizException {
		int result=0;
		if(null==vo||vo.size()==0){
			throw new ServiceBizException("作废的奖金文件数据文件为空");
		}
		//批量更新奖金文件
		for(BonusIssueFileDTO bonus:vo){
			try {
				//更新奖金文件
				bonus.setCommonUpdateData(userId);
				bonus.setStatus(POConstant.IS_NOT_STATUS);
				bonus.setId(Integer.valueOf(RSAUtil.decryptByPrivateKey(bonus.getEncryptId())));
				result=bonusIssueDao.updateBonusIssueFileStatusById(bonus);
				//更新奖金文件的记录
				List<BonusIssueRecordDTO> data=bonusIssueDao.findBonusRecordFileByBatchNo(bonus.getBatchNo());
				if(null!=data&&data.size()>0){
					for(BonusIssueRecordDTO bonusdto:data){
						//更新奖金文件
						bonusdto.setCommonUpdateData(userId);
						bonusdto.setStatus(POConstant.IS_NOT_STATUS);
						result=bonusIssueDao.updateBonusIssueRecordStatusById(bonusdto);
					}
				}
			} catch (SQLException e) {
			    logger.error(e);
				throw new ServiceBizException("更新数据失败"+e);
			} catch (NumberFormatException e) {
			    logger.error(e);
                throw new ServiceBizException("更新数据失败"+e);
            } catch (Exception e) {
                logger.error(e);
                throw new ServiceBizException("更新数据失败"+e);
            }
		}
		
		if(0==result){
            throw new ServiceBizException("导入奖金发放记录失败");
        }
		
		return result;
	}
	
	@Override
	public String getBatchNo() throws ServiceBizException{
		String date=DateUtil.formartDate(new Date());
		String batchNo="";
		try {
			batchNo = bonusIssueDao.getMaxBatchNo();
		} catch (SQLException e) {
		    logger.error(e);
			throw new ServiceBizException("获取批次号失败:"+e);
		}
		if(StringUtil.isEmpty(batchNo)){
			return date+CodeConstant.BONUS_FILE_BATCH_NO;
		}
		batchNo=String.valueOf(Long.valueOf(batchNo)+1);
		return batchNo;
	}
	

	@Override
	public boolean payBonus(String batchNo,long userid) throws ServiceAppException,SQLException {
		//定义返回结果 默认false
		boolean flag=true;
		
    	//根据批次号查询需要发放的奖金记录
    	List<BonusIssueRecordDTO> data=bonusIssueDao.findBonusRecordByBatchNoLock(batchNo);
    	if(null==data||0==data.size()){
    		logger.error("没找到有效数据");
    		return false;
    	}
    	List<RequestPayBonus> bonus=bonusRecord2Req(data);
    	
    	//调用sap检查接口
    	List<ResponsePayBonus> resp=sapCheckPayBonusData(bonus);
    	if(null==resp||resp.size()==0){
    		throw new ServiceBizException("sap back msg  is empty");
    	}
    	
    	//添加检查结果记录
    	int result=addBonusIssueResult(resp,userid);
    	if(0==result){
    		//保存失败
    		throw new ServiceBizException("保存发放结果失败");
    	}

    	//发放主逻辑
    	try {
    		flag=executeBonusIssueOption(resp,bonus,batchNo,userid);
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    		throw new ServiceBizException("查询发放结果数据库错误:" + ex.getMessage());
    	}

    	//返回结果
//		if(!flag){
//		    throw(new ServiceBizException("奖金发放失败"));
//      }
		
		return flag;
	}
	
	private boolean executeBonusIssueOption(List<ResponsePayBonus> resp, List<RequestPayBonus> bonus, String batchNo,long userid) throws Exception {
		boolean isFlag=true;
		int rt=0;
		BonusIssueFileRcTsDTO rcts=null;
		String flag="SUC";
		
		List<PayBonusDTO> data=resp2PayBonus(resp,bonus);
		for(PayBonusDTO bonusPay:data){
		    try {
    			//根据sap校验状态 如果校验成功，则进行发放，否则,更新发放状态为发放失败
    			BonusIssueRecordDTO bonusIssueRecordDTO=getRecordByFlowNo(String.valueOf(bonusPay.getZz_id()));
    			bonusIssueRecordDTO.setCommonUpdateData(userid);
    			
    			//校验成功
    			if(bonusPay.getZ_number().equals(PayBonusResultCodeConstants.PAY_BONUS_SUC)){
    				rcts=new BonusIssueFileRcTsDTO();
    				rcts.setBatchNo(batchNo);
    				rcts.setFlowNo(String.valueOf(bonusPay.getZz_id()));
    				rcts.setAmount(bonusPay.getZ_komxwrt());
    				rcts.setCommonAddData(userid);
    				rcts.setTsId(CodeConstant.BONUS_PAY_TS_ID);
    				rt=bonusIssueFileRcTsDao.saveBonusIssueFileRcTs(rcts);
    					
    				//发放奖金 调用奖金服务
    				String tsId=CodeConstant.BONUS_PAY_TS_ID+rcts.getId();
    				BonusFundResultVo result=updateDealerBonus(bonusPay,tsId);
    					
    				//更新发放状态
    				if(POConstant.BONUS_SERVICE_EXECUTE_SUC==result.getResultCode()){
    					//发放成功
    				    bonusIssueRecordDTO.setIssueStatus(POConstant.ISSUE_SUC);
    				}else{
    				    executeBonusIssueFolFaild(bonusPay.getZz_id(), userid);
    				    bonusIssueRecordDTO.setIssueStatus(POConstant.ISSUE_FOL_FAIL);
    					flag="FAIL";	
    				}
    				rt=bonusIssueDao.updateBonusIssueRecordStatusById(bonusIssueRecordDTO);
    				if(0==rt){
    					//发放失败,回滚操作
    					throw new ServiceBizException("更近发放奖金状态失败");
    				}
    			}else{
    			  //校验失败 更新发放状态为失败
                  bonusIssueRecordDTO.setIssueStatus(POConstant.ISSUE_FAIL);
                  rt=bonusIssueDao.updateBonusIssueRecordStatusById(bonusIssueRecordDTO);
                  flag="FAIL";
    			}
            
            } catch (Exception e) {
               logger.error(e);
               e.printStackTrace();
               executeBonusIssueFolFaild(bonusPay.getZz_id(), userid);
            }
		}
		//更新文件发放记录
		List<BonusIssueFileDTO> bonusDto=bonusIssueDao.findBonusIssueFileByBatchNoLock(batchNo);
		if(null==bonusDto||bonusDto.size()==0){
			throw new ServiceBizException("批次号文件未找到");
		}
		BonusIssueFileDTO bonusFileDto=bonusDto.get(0);
		if("FAIL".equals(flag)){
			//更新状态为fol失败
			bonusFileDto.setIssueStatus(POConstant.ISSUE_FAIL);
			isFlag=false;
		}else if("SUC".equals(flag)){
			bonusFileDto.setIssueStatus(POConstant.ISSUE_SUC);
			isFlag=true;
		}
		bonusFileDto.setCommonUpdateData(userid);
		bonusFileDto.setPublishDate(new Date());
		rt=bonusIssueDao.updateBonusIssueFileStatusById(bonusFileDto);
		
		
		return isFlag;
	}

	//FOL处理失败新增一条FOL处理失败的RESULT纪录
    private boolean executeBonusIssueFolFaild(Integer recordId,Long userId) throws Exception{
        BonusIssueResultDTO bonusIssueResultDTO=new BonusIssueResultDTO();
        bonusIssueResultDTO.setIssueRecordId(recordId);
        bonusIssueResultDTO.setExecuteStatus(POConstant.EXECUTE_FAIL);
        bonusIssueResultDTO.setCommonAddData(userId);
        bonusIssueResultDTO.setCommonUpdateData(userId);
        bonusIssueResultDTO.setExecuteDate(new Date());
        bonusIssueResultDTO.setIssueErrMsgCode(POConstant.BONUS_CHECK_CODE_FOL_FAILD);
        bonusIssueDao.saveBonusIssueResult(bonusIssueResultDTO); 
        
        return true;
    }

	private List<PayBonusDTO> resp2PayBonus(List<ResponsePayBonus> resp, List<RequestPayBonus> bonus) {
		if(null==resp||resp.size()==0||null==bonus||bonus.size()==0){
			throw new ServiceBizException("数据为空");
		}
		List<PayBonusDTO> data=BeanMapper.mapList(resp, PayBonusDTO.class);
		for(PayBonusDTO payBonus:data){
			payBonus.setZz_kunnr(payBonus.getZz_kunnr().substring(3));
			for(RequestPayBonus req:bonus){
				if(payBonus.getZz_id().equals(req.getZ_id())){
					//将该条金额存进去
					payBonus.setZ_komxwrt(req.getZ_komxwrt());
				}
			}
		}
		return data;
	}

	private BonusFundResultVo updateDealerBonus(PayBonusDTO  bonus,String tsId) throws Exception {
		BonusFundRequestVo bonusFundRequestVo=initBonusFundRequestVo(bonus,tsId); 
		BonusService service=new BonusService();
		BonusFundResultVo result=null;
		Holder<SGMCommonHeaderType> header=new Holder<SGMCommonHeaderType>();
		header.value=CommonHelper.tansfer(new GregorianCalendar());
		TranformBonusFund bonusFund=new TranformBonusFund();
		BonusOperationWebService ws=service.getBonusServicePort();
		bonusFund.setArg0(bonusFundRequestVo);
		
		Client client = JaxWsClientProxy.getClient(ws);
		client.getInInterceptors().add(new LoggingInInterceptor());//输入流日志拦截器
		client.getOutInterceptors().add(new LoggingOutInterceptor());//输出流日志拦截器
		
		TranformBonusFundResponse bonusFundResponse= ws.tranformBonusFund(bonusFund, header);
		
		result=bonusFundResponse.getReturn();
		return result;
	}

	private BonusFundRequestVo initBonusFundRequestVo(PayBonusDTO respList,String tsId) {
		BonusFundRequestVo req=new BonusFundRequestVo();
		req.setSapCode(respList.getZz_kunnr());
		req.setReferType(Long.parseLong(CodeConstant.BONUS_ISSUE_REFER_TYPE));
		req.setReferCode(tsId);
		req.setAmount(respList.getZ_komxwrt().doubleValue());
		req.setTsId(tsId);
		req.setFundType(null);
		return req;
	}

	

	private BonusIssueRecordDTO getRecordByFlowNo(String flowNo) throws SQLException {
		List<BonusIssueRecordDTO> data=bonusIssueDao.findBonusIssueRecordByFlowNo(flowNo);
		if(null==data||data.size()==0){
			throw new ServiceBizException("没有找到数据");
		}
		return data.get(0);
	}

	private int addBonusIssueResult(List<ResponsePayBonus> resp,long userId) {
		int result=0;
		
		List<BonusIssueResultDTO> bonusRt=initBonusResultdata(resp,userId);
		
		try {
			for(BonusIssueResultDTO dto:bonusRt){
			result=bonusIssueDao.saveBonusIssueResult(dto);
			}
		} catch (SQLException e) {			
			logger.error("保存发放结果失败:"+e); 
			throw new ServiceBizException("保存发放结果失败:"+e);
		}
		return result;
		
		
	}

	private List<BonusIssueResultDTO> initBonusResultdata(List<ResponsePayBonus> respPayBonus,long userId) {
		List<BonusIssueResultDTO> data=new ArrayList<BonusIssueResultDTO>();
		for(ResponsePayBonus resp:respPayBonus){
			Date now=new Date();
			BonusIssueResultDTO bonusRt=new BonusIssueResultDTO();
			bonusRt.setCommonAddData(userId);
			bonusRt.setExecuteDate(now);
			//处理状态
			if(!StringUtil.isEmpty(resp.getZ_number())&&resp.getZ_number().equals(PayBonusResultCodeConstants.PAY_BONUS_SUC)){
				bonusRt.setExecuteStatus(POConstant.EXECUTE_SUC);
			}else{
				bonusRt.setExecuteStatus(POConstant.EXECUTE_FAIL);
			}
			bonusRt.setIssueRecordId(resp.getZz_id());
			bonusRt.setIssueErrMsgCode(resp.getZ_message_code());
			data.add(bonusRt);
		}
		return data;
	}

	
	private List<ResponsePayBonus> sapCheckPayBonusData(List<RequestPayBonus> bonus) throws ServiceAppException{
		//try
		/*logger.info("=====调用 sap 检查数据  start =======");
		List<ResponsePayBonus> data=new ArrayList<ResponsePayBonus>();
		for(int i=0;i<bonus.size();i++){
		ResponsePayBonus resp=new ResponsePayBonus();
		resp.setZ_number(PayBonusResultCodeConstants.PAY_BONUS_SUC);
		resp.setZ_message_code(PayBonusResultCodeConstants.PAY_BONUS_SUC_CODE);
		resp.setZz_id(bonus.get(i).getZ_id());
		resp.setZz_kunnr(bonus.get(i).getZ_kunnr());
		data.add(resp);
		}*/
		List<ResponsePayBonus> data=bonusIssueCheckService.bonusIssueCheckData(bonus);
		//调用sap接口校验奖金发放数据
		logger.info("=====调用 sap 检查数据  over =======");
		return data;
	}

	private List<RequestPayBonus> bonusRecord2Req(List<BonusIssueRecordDTO> data) {
		List<RequestPayBonus> list=new ArrayList<RequestPayBonus>();
		for(BonusIssueRecordDTO bonus:data){
			RequestPayBonus paybonus=new RequestPayBonus();
			CodeDTO code=null;
			try {
				code=new CodeDTO();
				code.setType(POConstant.SERV_TYPE+"");
				code.setCode(bonus.getServ()==null?null:String.valueOf(bonus.getServ()));
				code=codeService.getcodeCnDescByCodeAndTypeName(code);
				if(null==code){
					throw new ServiceBizException("无效code");
				}
			} catch (Exception e) {
			    logger.error(e);
				throw new ServiceBizException("字典转义异常:"+e);
			}
			paybonus.setZ_vkorg(code.getCodeCnDesc());
			paybonus.setZ_kunnr(bonus.getSapCode());
			
			
			//生效日期 当前日期
			paybonus.setZ_datab(DateUtil.formartDate(new Date()));
			
			paybonus.setZ_komxwrt(bonus.getAmount());

			paybonus.setZ_id(Integer.parseInt(bonus.getFlowNo()));//流水号Integer.parseInt(bonus.getFlowNo())
			
			list.add(paybonus);
		}
		return list;
	}
	
	

    @Override
    public Boolean updateBonusAuditState(List<BonusIssueFileDTO> bonusIssueFileDTOs) throws ServiceBizException {
        try {
            for (BonusIssueFileDTO bonusIssueFileDTO : bonusIssueFileDTOs) {
                int result=0;
                //审核成功
                if(POConstant.MATCH_STATE_SUC==bonusIssueFileDTO.getMatchState().intValue()){
                    bonusIssueFileDTO.setIssueStatus(POConstant.WAIT_ISSUE);
                //驳回或者退回
                }else if(bonusIssueFileDTO.getMatchState()==POConstant.UN_MATCH_STATE){                 
                    BonusIssueFileFlowDTO flow=initBonusIssueFileFlow(bonusIssueFileDTO,POConstant.BONUS_FLIE_FLOW_BACKED);
                    result+=bonusIssueFileFlowDao.saveBonusIssueFileFlow(flow);
                    bonusIssueFileDTO.setIssueStatus(POConstant.UN_ISSUE);
                //确认需要审核
                }else if(bonusIssueFileDTO.getMatchState().intValue()==POConstant.WAIT_MATCH_STATE){
                    if(isTurnFile(bonusIssueFileDTO.getBatchNo())){
                        bonusIssueFileDTO.setMatchState(POConstant.MATCH_AGAIN_STATE);
                    }
                }
                bonusIssueFileDTO.setId(Integer.valueOf(RSAUtil.decryptByPrivateKey(bonusIssueFileDTO.getEncryptId())));
                result+=bonusIssueDao.updateBonusIssueFileStatusById(bonusIssueFileDTO);
                
                if(result<=0){
                    throw(new ServiceBizException("更新审核状态失败,批次号："+bonusIssueFileDTO.getBatchNo()));
                }else if(result<=1&&bonusIssueFileDTO.getMatchState()==POConstant.UN_MATCH_STATE){
                    throw(new ServiceBizException("更新审核状态失败,批次号："+bonusIssueFileDTO.getBatchNo()));
                }
                
                
            }
        } catch (SQLException e) {
            logger.error(e);
            throw(new ServiceAppException("更新审核状态失败"+e.getMessage()));
        } catch (NumberFormatException e) {
            logger.error(e);
            throw(new ServiceAppException("更新审核状态失败"+e.getMessage()));
        } catch (Exception e) {
            logger.error(e);
            throw(new ServiceAppException("更新审核状态失败"+e.getMessage()));
        }

        return true;
    }

	@Override
	public List<BonusIssueRecordDTO> findBonusIssueAgainExecuteByWhs(BonusIssueRecordDTO bonusIssueRecordDTO)
			throws ServiceBizException {
		List<BonusIssueRecordDTO> data=null;
		try {
			data=bonusIssueDao.findBonusIssueAgainExecuteByWhs(bonusIssueRecordDTO);
			recordCode2Name(data);
		} catch (Exception e) {
			throw new ServiceAppException("查询奖金重处理记录失败"+e);
			
		}
		return data;
	}

	private void recordCode2Name(List<BonusIssueRecordDTO> recordList) throws Exception {
		if(null!=recordList&&recordList.size()>0){
			CodeDTO code=new CodeDTO();
			for(BonusIssueRecordDTO record:recordList){
				//奖金类型
				if(!StringUtil.isEmpty(record.getBonusType())){
					code.setCode(record.getBonusType()==null?null:String.valueOf(record.getBonusType()));
					code.setType(String.valueOf(POConstant.TYPE_NAME_CODE_BONUS));
					CodeDTO tempdto=codeService.getcodeCnDescByCodeAndTypeName(code);
					
					if(null!=tempdto){
						record.setBonusTypeName(tempdto.getCodeCnDesc());
					}
				}
				//渠道类型
				if(!StringUtil.isEmpty(record.getDealerType())){
					code.setCode(record.getDealerType()==null?null:String.valueOf(record.getDealerType()));
					code.setType(String.valueOf(POConstant.DEALER_TYPE));
					CodeDTO tempdto=codeService.getcodeCnDescByCodeAndTypeName(code);
					
					if(null!=tempdto){
						record.setDealerTypeName(tempdto.getCodeCnDesc());
					}
				}
				//销售公司
				if(!StringUtil.isEmpty(record.getServ())){
					code.setCode(record.getServ()==null?null:String.valueOf(record.getServ()));
					code.setType(String.valueOf(POConstant.SERV_TYPE));
					CodeDTO tempdto=codeService.getcodeCnDescByCodeAndTypeName(code);
					if(null!=tempdto){
						record.setServName(tempdto.getCodeCnDesc());
					}
				}

				if(record.getId()!=null){
				    record.setEncryptId(RSAUtil.encryptByPublicKey(record.getId().toString()));
	                record.setId(null); 
				}
				
			}
		}
		
	}

	@Override
	public int findBonusIssueAgainExecuteCountByWhs(BonusIssueRecordDTO bonusIssueRecordDTO)
			throws ServiceBizException {
		int total=0;
		try {
			total = bonusIssueDao.findBonusIssueAgainExecuteCountByWhs(bonusIssueRecordDTO);
		} catch (SQLException e) {
		    logger.error(e);
			throw new ServiceBizException("查询冲处理总记录数失败:"+e);
		}
		return total;
	}

	@Override
	public List<BonusIssueFileDTO> findBonusPublishedFileByWhs(BonusIssueFileDTO bonusVo) throws ServiceBizException {
		List<BonusIssueFileDTO> data=null;
		try {
			data=bonusIssueDao.findBonusPublishedFileByWhs(bonusVo);

		} catch (Exception e) {
		    logger.error(e);
			throw new ServiceBizException("查询奖金颁发记录失败"+e);
			
		}
		return data;
	}

	@Override
	public int findBonusPublishedFileCountByWhs(BonusIssueFileDTO bonusVo) throws ServiceBizException {
		int total=0;
		try {
			total = bonusIssueDao.findBonusPublishedFileCountByWhs(bonusVo);
		} catch (SQLException e) {
		    logger.error(e);
			throw new ServiceBizException("查询奖金颁总记录数失败:"+e);
		}
		return total;
	}

	@Override
	public void payAgainBonus(List<BonusIssueRecordDTO> data, Long userId) throws ServiceAppException,SQLException{
		 
		//判断发放失败原因，如果是fol失败,则不调用sap校验,如果是sap校验失败,则和发放逻辑一样
		//定义返回结果 默认false
		
		List<BonusIssueRecordDTO> sapCheckSucList=new ArrayList<BonusIssueRecordDTO>();
		List<BonusIssueRecordDTO> sapCheckFailList=new ArrayList<BonusIssueRecordDTO>();
		boolean flag=true;
		
		//根据批次号查询需要发放的奖金记录		
		if(null==data||0==data.size()){
			throw new ServiceBizException("没找到有效数据");
		}
		
		for(BonusIssueRecordDTO record:data){
			if(record.getIssueStatus()==POConstant.ISSUE_FOL_FAIL){
				sapCheckSucList.add(record);
			}else if(record.getIssueStatus()==POConstant.ISSUE_FAIL){
				sapCheckFailList.add(record);
			}
		}
		
		List<ResponsePayBonus> respList=null;
		if(sapCheckFailList.size()>0){
		    respList=sapCheckData(sapCheckFailList,userId);
		}

    	if(null!=respList&&respList.size()>0){
        	for(BonusIssueRecordDTO recordDto:sapCheckFailList){
        		for(ResponsePayBonus resp:respList){
        			if(resp.getZz_id().toString().equals(recordDto.getFlowNo())){
        				if(resp.getZ_number().equals(PayBonusResultCodeConstants.PAY_BONUS_SUC)){
        					//校验成功将该记录放到sucList
        					sapCheckSucList.add(recordDto);
        				}else if(PayBonusResultCodeConstants.PAY_BONUS_DATA_REPEAT.equals(resp.getZ_message_code())){
        				    //出现重复记录，SAP已有重复的数据
        				    sapCheckSucList.add(recordDto);
        				}else{
        					recordDto.setIssueStatus(POConstant.ISSUE_FAIL);
        					recordDto.setCommonUpdateData(userId);
        					recordDto.setFlowNo(recordDto.getFlowNo());
        					bonusIssueDao.updateBonusIssueRecordStatusByFlowNo(recordDto);	
        				}
        			}
        		}
        	}
    	}
    		
    	//发放主逻辑
		executeAgainBonusIssueOption(sapCheckSucList,userId);
			
		if(!flag){
		    throw(new ServiceBizException("奖金重处理失败"));
		}
	}
	
	//FOL处理失败新增一条FOL处理失败的RESULT纪录
	private boolean executeAgainBonusIssueFolFaild(BonusIssueRecordDTO bonusIssueRecordDTO,Long userId) throws ServiceAppException,SQLException{                
        BonusIssueResultDTO bonusIssueResultDTO=new BonusIssueResultDTO();
        bonusIssueResultDTO.setIssueRecordId(Integer.valueOf(bonusIssueRecordDTO.getFlowNo()));
        bonusIssueResultDTO.setExecuteStatus(POConstant.EXECUTE_FAIL);
        bonusIssueResultDTO.setCommonAddData(userId);
        bonusIssueResultDTO.setCommonUpdateData(userId);
        bonusIssueResultDTO.setExecuteDate(new Date());
        bonusIssueResultDTO.setIssueErrMsgCode(POConstant.BONUS_CHECK_CODE_FOL_FAILD);
        bonusIssueDao.saveBonusIssueResult(bonusIssueResultDTO); 
	    return true;
	}

	private void executeAgainBonusIssueOption(List<BonusIssueRecordDTO> sapCheckSucList, Long userId) throws ServiceAppException,SQLException{
		int rt=0;
		BonusIssueFileRcTsDTO rcts=null;
		PayBonusDTO bonusPay=null;
		
		for(BonusIssueRecordDTO bonusFileRecord:sapCheckSucList){
			//根据sap校验状态 如果校验成功，则进行发放，否则,更新发放状态为发放失败
		    try {
    		    bonusFileRecord.setCommonUpdateData(userId);
    			rcts=new BonusIssueFileRcTsDTO();
    			rcts.setBatchNo(bonusFileRecord.getBatchNo());
    			rcts.setFlowNo(bonusFileRecord.getFlowNo());
    			rcts.setAmount(bonusFileRecord.getAmount());
    			rcts.setCommonAddData(userId);
    			rcts.setTsId(CodeConstant.BONUS_PAY_TS_ID);
    			rt=bonusIssueFileRcTsDao.saveBonusIssueFileRcTs(rcts);
    					
    			//发放奖金 调用奖金服务
//    			String tsId=CodeConstant.BONUS_ISSUE_REFER_TYPE+rcts.getId();
    			String tsId=CodeConstant.BONUS_PAY_TS_ID+rcts.getId();//2016-5-25
    			bonusPay=new PayBonusDTO();
    			bonusPay.setZ_komxwrt(bonusFileRecord.getAmount());
    			bonusPay.setZz_kunnr(bonusFileRecord.getSapCode());
    			BonusFundResultVo result=updateDealerBonus(bonusPay,tsId);
    			
    			//查找发放文件记录（大）
    			List<BonusIssueFileDTO> bonusDto=bonusIssueDao.findBonusIssueFileByBatchNoLock(bonusFileRecord.getBatchNo());
    			if(null==bonusDto||bonusDto.size()==0){
    				throw new ServiceBizException("批次号文件未找到");
    			}
    			BonusIssueFileDTO bonusFileDto=bonusDto.get(0);
    			bonusFileDto.setCommonUpdateData(userId);
    			//更新发放状态
    			if(POConstant.BONUS_SERVICE_EXECUTE_SUC==result.getResultCode()){
    				//发放成功
    			    bonusFileRecord.setIssueStatus(POConstant.ISSUE_SUC);
    				bonusFileDto.setIssueStatus(POConstant.ISSUE_SUC);
    			}else{
//    			      bonusFileRecord.setIssueStatus(POConstant.ISSUE_FAIL);
//                    bonusFileDto.setIssueStatus(POConstant.ISSUE_FAIL);
//                    flag=false;
    			    executeAgainBonusIssueFolFaild(bonusFileRecord, userId);
    			}
    			
    			//更新记录状态
    			rt=bonusIssueDao.updateBonusIssueRecordStatusByFlowNo(bonusFileRecord);
    			
    			//检查该批次的记录是否都是发放成功 如果是更新文件发放状态为成功,否则不更新
    			//更新文件状态
    			if(egxistBonusIssueStatusIsSuc(bonusFileRecord.getBatchNo())){
    				rt=bonusIssueDao.updateBonusIssueFileStatusById(bonusFileDto);
    				
    				if(0==rt){
    					//发放失败,回滚操作
    				    logger.info("更近发放奖金状态失败,批次号:"+bonusFileRecord.getBatchNo()+",事务号："+tsId);
    					throw new ServiceBizException("更近发放奖金状态失败");
    				}
    			}
            
            } catch (Exception e) {
                executeAgainBonusIssueFolFaild(bonusFileRecord, userId);
                logger.error(e);
                e.printStackTrace();
            }
		}

	}

	private boolean egxistBonusIssueStatusIsSuc(String batchNo) {
		try {
			List<BonusIssueRecordDTO> data=bonusIssueDao.findBonusRecordIssueIsNotSucByBatchNo(batchNo);
			if(null==data||data.size()==0){
				//该批次都成功
				return true;
			}
		} catch (SQLException e) {
		    logger.error(e);
			throw new ServiceBizException("查询一个批次号的发放记录异常:"+e);
		}
		return false;
	}

	private List<ResponsePayBonus>  sapCheckData(List<BonusIssueRecordDTO> sapCheckFailList,long userId) throws ServiceAppException{
		List<RequestPayBonus> bonus=bonusRecord2Req(sapCheckFailList);
		
		//调用sap检查接口
		List<ResponsePayBonus> resp=sapCheckPayBonusData(bonus);
		if(null==resp||resp.size()==0){
				return null;
		}		
		//添加检查结果记录
		int result=addBonusIssueResult(resp,userId);
		if(0==result){
			//保存失败
			throw new ServiceBizException("保存发放结果失败");
		}
		return resp;
	}

	@Override
	public List<BonusIssueRecordDTO> findBonusPublishFileDetailByBatchNo(String batchNo,Integer issueStatus) {
		List<BonusIssueRecordDTO> data=null;
		List<BonusIssueRecordDTO>  bonusIssueRecordDTOs = new ArrayList<BonusIssueRecordDTO>();
//		BonusIssueFileDTO bonusIssueFileDTO = new  BonusIssueFileDTO();
//		List<BonusIssueFileDTO> dtos = null;
		try {
				//查询	
//			dtos = bonusIssueDao.findBonusPublishedFileByWhs(bonusIssueFileDTO);
//			for (BonusIssueFileDTO bonusdto : dtos) {
//				if(bonusdto.getBatchNo().equals(bonusIssueFileDTO.equals(getBatchNo()))){
//					
//				}
//			}
				 data =bonusIssueDao.findBonusPublishFileDetailByBatchNo(batchNo,issueStatus);
					if (null != data && data.size() > 0) {
						for (BonusIssueRecordDTO bonusIssueDto : data) {
							if (null != bonusIssueDto
									&& POConstant.RESERVE_TYPE_PARTS == bonusIssueDto
											.getBonusType()) {
								bonusIssueDto.setBonusTypeName("配件");
							} else if (null != bonusIssueDto
									&& POConstant.RESERVE_TYPE_CAR == bonusIssueDto
											.getBonusType()) {
								bonusIssueDto.setBonusTypeName("整车");
							}
							bonusIssueRecordDTOs.add(bonusIssueDto);
						}
					}	

			 recordCode2Name(bonusIssueRecordDTOs);
		} catch (Exception e) {
		    logger.error(e);
			throw new ServiceBizException("查询颁发明细异常:"+e);
		}
		return bonusIssueRecordDTOs;
	}
	

}
