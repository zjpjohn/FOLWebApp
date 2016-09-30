package com.sgm.dms.fol.bankTicket.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.bankTicket.api.BankBankTicketService;
import com.sgm.dms.fol.bankTicket.api.BankTicketAuditFlowService;
import com.sgm.dms.fol.bankTicket.domain.BankBankTicketPo;
import com.sgm.dms.fol.bankTicket.dto.BankBankTicketDTO;
import com.sgm.dms.fol.bankTicket.dto.BankInfoDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketValidAmountDTO;
import com.sgm.dms.fol.bankTicket.dto.QueryBankTicketTotalAmountDTO;
import com.sgm.dms.fol.bankTicket.repository.BankBankTicketDao;
import com.sgm.dms.fol.bankTicket.repository.BankInfoDao;
import com.sgm.dms.fol.bankTicket.repository.BankTicketDAO;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CommonUtils;
import com.sgm.dms.fol.common.service.utils.RSAUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
/*****
 * 银行银票信息serviceImpl
*
* @author Administrator
* TODO description
* @date 2016年2月1日
 */
@Service("BankBankTicketService")
@Transactional(rollbackFor=Exception.class)
public class BankBankTicketServiceImpl implements BankBankTicketService {
	
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private BankBankTicketDao bankBankTicketDao; 
	@Autowired
	private BankInfoDao bankInfoDao;
	@Autowired
	private BankTicketAuditFlowService bankTicketAuditFlowService;
	@Autowired
	private BankTicketDAO bankTicketDAO;
	
	@Override
	public int saveBankBankTicket(BankBankTicketDTO bankBankTicketDTO,BankInfoDTO bankInfoDTO,long userId) throws ServiceAppException {
		int result=0;
		try {
		    //如果是维护的新增银行银票则需要看银行是否存在如果不存在则不能新建
//		    if(bankBankTicketDTO.getDealerType()!=null&&bankBankTicketDTO.getDealerType()>=0){
//		        BankInfoDTO dto=bankInfoDao.findBankinfo(bankInfoDTO);
//                if(dto==null){
//                    throw new ServiceBizException("没有该银行信息不能新建");
//                }  
//		    }else{
		        
//		    }
            
            //新增银行记录,按照银行新增走以下方法
		    if(bankInfoDTO.getDealerType()==null){
		        bankInfoDTO.setDealerType(null);
                BankInfoDTO dto=bankInfoDao.findBankinfo(bankInfoDTO);
                if(dto!=null){
                    throw new ServiceBizException("已有重复的银行信息存在");
                } 
                
                initBankInfoData(bankInfoDTO,userId);
                CommonUtils.filterSpecialWord(bankInfoDTO);
                result=bankInfoDao.saveBankInfo(bankInfoDTO);
                    
                if(0==result){
                    throw new ServiceBizException("保存银行信息失败");
                }
		    }else{
		        bankInfoDTO=bankInfoDao.findBankinfo(bankInfoDTO);
		    }
		        
			
			//渠道维护的额度是否超过银行总额度
			if(bankBankTicketDTO.getDealerType()!=null&&bankBankTicketDTO.getDealerType()>=0){
			    //当前计算出的金额
			    BigDecimal currentAmount=getAllDealerBankBankTicketAmount().add(bankBankTicketDTO.getAmount());
			
			    //服务器的银行总金额
			    BankBankTicketDTO serverdto=bankBankTicketDao.findBankBankTicketDataByBankAbbr(bankInfoDTO);
			
			    if(serverdto!=null && POConstant.BANK_TICKET_AUDIT_SUCCESS!=serverdto.getAuditStatus().intValue()){
			        throw new ServiceBizException("该银行银票不是审核通过状态，不能新建银行渠道银票");
			    }
			    if(serverdto!=null && serverdto.getAmount().compareTo(currentAmount)==-1){
			        throw new ServiceBizException("渠道维护的银行银票金额大于银行银票金额");
			    }
			}
			
			//增加银行银票额度
			initDataBankBankTicket(bankBankTicketDTO,bankInfoDTO,userId);
			
			CommonUtils.filterSpecialWord(bankBankTicketDTO);
			result=bankBankTicketDao.saveBankBankTicket(bankBankTicketDTO);
			
			if(0==result){
				throw new ServiceBizException("保存银行银票信息失败");
			}
			
	        //增加流水记录
			
            result=bankTicketAuditFlowService.addBankTicketAuditFlowRecord(POConstant.BANK_TICKET_ADD_UN_AUDIT,null,POConstant.BUSINESS_TABLE_ADD_BANK_BANK_TICKET_MAINTAIN,bankBankTicketDTO.getId(),userId);
            if(0==result){
                throw new ServiceBizException("保存流水记录失败");
            }
			
		} catch (SQLException e) {
			throw new ServiceBizException("保存银行银票信息失败"+e);
		}
		return result;
	}

	private void initBankInfoData(BankInfoDTO bankInfoDTO,long userId) {
		bankInfoDTO.setCommonAddData(userId);
		bankInfoDTO.setStatus(POConstant.IS_STATUS);
		
	}

	private void initDataBankBankTicket(BankBankTicketDTO bankBankTicketDTO, BankInfoDTO bankInfoDTO, long userId) {
		if(null!=bankBankTicketDTO){
			bankBankTicketDTO.setCommonAddData(userId);
			bankBankTicketDTO.setBankId(bankInfoDTO.getId().longValue());
			bankBankTicketDTO.setStatus(POConstant.IS_STATUS);
			bankBankTicketDTO.setAuditStatus(POConstant.BANK_TICKET_ADD_UN_AUDIT);
		}
	}

	@Override
	public List<BankBankTicketDTO> findBankBankTicketDataByWhs(BankBankTicketDTO bankBankTicketDTO)
			throws ServiceBizException {
	    List<BankBankTicketDTO> bankBankTicketDTOList;
	    try {
	        bankBankTicketDTOList=bankBankTicketDao.findBankBankTicketDataByWhs(bankBankTicketDTO);
	    
	        if(bankBankTicketDTOList!=null&&bankBankTicketDTOList.size()>0){
	            for (BankBankTicketDTO dto : bankBankTicketDTOList) {
	                dto.setEncryptId(RSAUtil.encryptByPublicKey(dto.getId().toString()));
	                dto.setAmount(dto.getAmount().multiply(BigDecimal.valueOf(POConstant.BANK_TICKET_AMOUNT_BASE_UNIT)));
	            } 
	        }	       
	    } catch (SQLException e) {
	        logger.error(e);
            throw(new ServiceAppException(e));
        } catch (Exception e) {
            logger.error(e);
            throw(new ServiceAppException(e));
        }
		return bankBankTicketDTOList;
	}

	@Override
	public int updateBankBankTicket(BankBankTicketDTO bankBankTicketDTO,BankInfoDTO bankInfoDTO,Long userId) throws ServiceAppException {
	    int result=0;
	    try {
	        bankBankTicketDTO.setAuditStatus(POConstant.BANK_TICKET_UPDATE_UN_AUDIT);
	        bankBankTicketDTO.setId(Long.valueOf(RSAUtil.decryptByPrivateKey(bankBankTicketDTO.getEncryptId())));
	        
	        //判断修改的银行银票额度是否超过当前已拥有的银票总额
	        QueryBankTicketTotalAmountDTO queryBankTicketTotalAmountDTO=new QueryBankTicketTotalAmountDTO();
	        queryBankTicketTotalAmountDTO.setBankId(bankBankTicketDTO.getBankId());
	        queryBankTicketTotalAmountDTO.setFolResult(POConstant.FOL_CHECK_BANK_TICKET_SUC);
	        queryBankTicketTotalAmountDTO.setValid(POConstant.IS_STATUS);
	        queryBankTicketTotalAmountDTO.setSapExecuteResult(POConstant.SQ_RECEIVE_BANKTICKET_CHECK_RESULT_SUC);
	        List<String> items=new ArrayList<String>();
	        items.add(POConstant.SQ_SIGN_BANK_TICKET_EXECUTE_SUC);
	        items.add(POConstant.SQ_SIGN_BANK_TICKET_EXECUTED);
	        queryBankTicketTotalAmountDTO.setSaicResultList(items);
	        
	        BigDecimal totalAmount;
	        //查询出经销商已使用总额
	        if(bankBankTicketDTO.getDealerType()!=null&&bankBankTicketDTO.getDealerType()>=0){
	            BankBankTicketDTO serverdto=bankBankTicketDao.findBankBankTicketDataByBankAbbr(bankInfoDTO);
                if(POConstant.BANK_TICKET_AUDIT_SUCCESS!=serverdto.getAuditStatus().intValue()){
                    throw new ServiceBizException("该银行银票不是审核通过状态，不能修改银行渠道银票");
                }
                
	            queryBankTicketTotalAmountDTO.setDealerType(bankBankTicketDTO.getDealerType());
	            totalAmount=bankBankTicketDao.findUsedBankTicketDealerTotalAmount(queryBankTicketTotalAmountDTO);
	        }else{
	        //查询出银行已使用总额
	            List<QueryBankTicketTotalAmountDTO> resultlist=bankBankTicketDao.findUsedBankTicketTotalAmount(queryBankTicketTotalAmountDTO);
	            if(resultlist!=null&&resultlist.size()>0){
	                totalAmount=resultlist.get(0).getAvailAmount();
	            }else{
	                totalAmount=BigDecimal.valueOf(0);
//	                throw new ServiceBizException("查询不到该银行可用额度");
	            }
	        }
	        
	        if(totalAmount.compareTo(bankBankTicketDTO.getAmount().multiply(BigDecimal.valueOf(POConstant.BANK_TICKET_AMOUNT_BASE_UNIT)))>0){
	            throw new ServiceBizException("更新的银行银票总额不能小于当前所有有效银票加起来的总和");
	        }
	        
	        bankInfoDTO.setId(bankBankTicketDTO.getBankId());
	        bankInfoDTO.setCommonUpdateData(userId);
	        CommonUtils.filterSpecialWord(bankInfoDTO);
	        result+=bankInfoDao.updateBankInfo(bankInfoDTO);
	        
	        bankBankTicketDTO.setCommonUpdateData(userId);
            CommonUtils.filterSpecialWord(bankBankTicketDTO);
	        result+=bankBankTicketDao.updateBankBankTicket(bankBankTicketDTO);
	        
	        if(result<2){
	            throw new ServiceBizException("更新银行银票信息失败"+bankBankTicketDTO.getId());
	        }
        } catch (SQLException e) {
            logger.error(e);
            throw(new ServiceAppException(e.getMessage()));
        } catch (NumberFormatException e) {
            logger.error(e);
            throw(new ServiceAppException(e.getMessage()));
        } catch (Exception e) {
            logger.error(e);
            throw(new ServiceAppException(e.getMessage()));
        }
		return result;
	}

	@Override
	public BankBankTicketDTO findBankBankTicketDataById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findBankBankTicketDataCountByWhs(BankBankTicketDTO bankBankTicketDTO) throws ServiceAppException {
		 int total=0;
		 try {
			total=bankBankTicketDao.findBankBankTicketDataCountByWhs(bankBankTicketDTO);
		} catch (SQLException e) {
		    logger.error(e);
			throw new ServiceBizException("查询银行银票记录异常:"+e);
		}
		return total;
	}

	private BigDecimal getAllDealerBankBankTicketAmount() throws ServiceAppException{
	    try {
	        BigDecimal totalAmount=new BigDecimal(0);
            List<BankBankTicketDTO> list=bankBankTicketDao.findAllDealerBankBankTicketData();
            
            for (BankBankTicketDTO bankBankTicketDTO : list) {
                totalAmount.add(bankBankTicketDTO.getAmount());
            }
            
            return totalAmount;
	    } catch (SQLException e) {
	        logger.error(e);
            throw new ServiceBizException(e.getMessage());
        }
	}

	@Override
	public int updateBankBankTicketAudit(BankBankTicketDTO bankBankTicketDTO, BankInfoDTO bankInfoDTO, Long userId)
			throws ServiceAppException {
        BankBankTicketPo bankBankTicketPo=BeanMapper.map(bankBankTicketDTO, BankBankTicketPo.class);

		int result = 0;
		try {
			    bankBankTicketPo.setCommonUpdateData(userId);
			    CommonUtils.filterSpecialWord(bankBankTicketDTO);
	            result+=bankBankTicketDao.updateBankBankTicket(bankBankTicketDTO);
	            
	            if(result<1){
	                throw(new ServiceBizException("update bankticket fail"));
	            }
		} catch (Exception e) {
		    logger.error(e);
            throw(new ServiceBizException(e.getMessage()));
		}
		return result;
	}

    /*
    *
    * @author DELL
    * @date 2016�?�?1�?
    * @param bankTicketValidAmountDTO
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankBankTicketService#findBankAvailAmount(com.sgm.dms.fol.bankTicket.dto.BankTicketValidAmountDTO)
    */
    	
    @Override
    public List<BankTicketValidAmountDTO> findBankAvailAmount(BankTicketValidAmountDTO bankTicketValidAmountDTO) throws ServiceAppException {
        try {
            if(bankTicketValidAmountDTO==null||StringUtil.isEmpty(bankTicketValidAmountDTO.getBankId())){
                throw(new ServiceBizException("银行ID不能为空"));
            }
            
            //查询总额度和到期额度
            List<BankTicketValidAmountDTO> list=bankTicketDAO.findBankTicketValidAmountByBankId(bankTicketValidAmountDTO);
            //查询使用额度
            QueryBankTicketTotalAmountDTO queryBankTicketTotalAmountDTO=new QueryBankTicketTotalAmountDTO();
            queryBankTicketTotalAmountDTO.setFolResult(POConstant.FOL_CHECK_BANK_TICKET_SUC);
            queryBankTicketTotalAmountDTO.setValid(POConstant.IS_STATUS);
            queryBankTicketTotalAmountDTO.setSapExecuteResult(POConstant.SQ_RECEIVE_BANKTICKET_CHECK_RESULT_SUC);
            List<String> items=new ArrayList<String>();
            items.add(POConstant.SQ_SIGN_BANK_TICKET_EXECUTE_SUC);
            items.add(POConstant.SQ_SIGN_BANK_TICKET_EXECUTED);
            queryBankTicketTotalAmountDTO.setSaicResultList(items);
            
            List<QueryBankTicketTotalAmountDTO> resultlist=bankBankTicketDao.findUsedBankTicketTotalAmount(queryBankTicketTotalAmountDTO);
            
            if(list!=null&&list.size()>0){
                for (BankTicketValidAmountDTO dto : list) {
                    if(resultlist!=null&&resultlist.size()>0){
                        for (QueryBankTicketTotalAmountDTO availamountdto : resultlist) {
                            if((availamountdto!=null&&availamountdto.getBankId()!=null)&&(dto!=null&&dto.getBankId()!=null)&&
                                    availamountdto.getBankId().intValue()==dto.getBankId().intValue()){
                                dto.setAvailAmount(dto.getTotalAmount().multiply(BigDecimal.valueOf(POConstant.BANK_TICKET_AMOUNT_BASE_UNIT)).subtract(availamountdto.getAvailAmount()));
                            }
                        }
                    }
                    
                    if(dto!=null&&dto.getAvailAmount()==null){
                        dto.setAvailAmount(dto.getTotalAmount().multiply(BigDecimal.valueOf(POConstant.BANK_TICKET_AMOUNT_BASE_UNIT)));
                    }
                    
                    if(dto!=null){
                    	dto.setExptdAmount(dto.getExptdAmount().multiply(BigDecimal.valueOf(POConstant.BANK_TICKET_AMOUNT_BASE_UNIT)));
                        dto.setExptmrAmount(dto.getExptmrAmount().multiply(BigDecimal.valueOf(POConstant.BANK_TICKET_AMOUNT_BASE_UNIT)));
                        dto.setExpaftertmrAmount(dto.getExpaftertmrAmount().multiply(BigDecimal.valueOf(POConstant.BANK_TICKET_AMOUNT_BASE_UNIT)));
                    }                    
                }
            }
            return list;
        } catch (SQLException e) {
            logger.error(e);
            throw(new ServiceAppException(e));
        }
       
    }

    /*
    *
    * @author DELL
    * @date 2016�?�?1�?
    * @param bankTicketValidAmountDTO
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankBankTicketService#findBankAvailAmountCount(com.sgm.dms.fol.bankTicket.dto.BankTicketValidAmountDTO)
    */
    	
    @Override
    public int findBankAvailAmountCount(BankTicketValidAmountDTO bankTicketValidAmountDTO) throws ServiceAppException {
        try {
            if(bankTicketValidAmountDTO==null||StringUtil.isEmpty(bankTicketValidAmountDTO.getBankId())){
                throw(new ServiceBizException("银行ID不能为空"));
            }
            
            int count=bankTicketDAO.findBankTicketValidAmountCountByBankId(bankTicketValidAmountDTO.getBankId());
            return count;
        } catch (SQLException e) {
            logger.error(e);
            throw(new ServiceAppException(e));
        }
    }

    /*
    * 银行银票审核通过
    * @author DELL
    * @date 2016年3月24日
    * @param bankBankTicketDTOList
    * @param userId
    * @throws ServiceAppException
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankBankTicketService#bankBankTicketAuditSuccess(java.util.List, java.lang.Long)
    */
    	
    @Override
    public void bankBankTicketAuditSuccess(List<BankBankTicketDTO> bankBankTicketDTOList,
                                           Long userId) throws ServiceAppException, SQLException {
        int result = 0;
        for (BankBankTicketDTO bankBankTicketDTO : bankBankTicketDTOList) {
            try {
                bankBankTicketDTO.setId(Long.valueOf(RSAUtil.decryptByPrivateKey(bankBankTicketDTO.getEncryptId())));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw(new ServiceAppException(e));
            } catch (Exception e) {
                e.printStackTrace();
                throw(new ServiceAppException(e));
            }
            
            /* 设置银票业务类型 */
            Integer businessCode = null;//银票业务代码
            if(POConstant.BANK_TICKET_ADD_UN_AUDIT == bankBankTicketDTO.getAuditStatus()){//审批前状态为（新增待审核）
                businessCode = POConstant.BUSINESS_TABLE_ADD_BANK_BANK_TICKET_MAINTAIN;
            }
            if(POConstant.BANK_TICKET_UPDATE_UN_AUDIT == bankBankTicketDTO.getAuditStatus()){//审批前状态为（修改待审核）
                businessCode = POConstant.BUSINESS_TABLE_UPDATE_BANK_BANK_TICKET_MAINTAIN;
            }
            
            // 判断审核状态
            if(bankBankTicketDTO.getAuditStatus()==POConstant.BANK_TICKET_ADD_UN_AUDIT
                    ||bankBankTicketDTO.getAuditStatus()==POConstant.BANK_TICKET_UPDATE_UN_AUDIT){
                result+=bankTicketAuditFlowService.addBankTicketAuditFlowRecord(POConstant.BANK_TICKET_AUDIT_SUCCESS,bankBankTicketDTO.getAuditMsg(), businessCode, bankBankTicketDTO.getId(), userId); 
            }
            
            bankBankTicketDTO.setAuditStatus(POConstant.BANK_TICKET_AUDIT_SUCCESS);
            BankInfoDTO bankInfoDTO=BeanMapper.map(bankBankTicketDTO, BankInfoDTO.class);
            result+=updateBankBankTicketAudit(bankBankTicketDTO, bankInfoDTO, userId);
            
            if(result<2){
                throw new ServiceBizException("银行银票限额审核失败");
            }
        }
    }

    /*
    *
    * @author DELL
    * @date 2016年3月24日
    * @param bankBankTicketDTOList
    * @param userId
    * @throws ServiceAppException
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankBankTicketService#bankBankTicketAuditReject(java.util.List, java.lang.Long)
    */
    	
    @Override
    public void bankBankTicketAuditReject(List<BankBankTicketDTO> bankBankTicketDTOList,
                                          Long userId) throws ServiceAppException, SQLException {
        int result = 0;
        for (BankBankTicketDTO bankBankTicketDTO : bankBankTicketDTOList) {
            try {
                bankBankTicketDTO.setId(Long.valueOf(RSAUtil.decryptByPrivateKey(bankBankTicketDTO.getEncryptId())));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw(new ServiceAppException(e));
            } catch (Exception e) {
                e.printStackTrace();
                throw(new ServiceAppException(e));
            }
            
            // 判断审核状态，并设置银票业务类型
            if(bankBankTicketDTO.getAuditStatus()==POConstant.BANK_TICKET_ADD_UN_AUDIT){
                bankBankTicketDTO.setAuditStatus(POConstant.BANK_TICKET_ADD_REJECT);
                result+=bankTicketAuditFlowService.addBankTicketAuditFlowRecord(POConstant.BANK_TICKET_AUDIT_SUCCESS,bankBankTicketDTO.getAuditMsg(), POConstant.BUSINESS_TABLE_ADD_BANK_BANK_TICKET_MAINTAIN, bankBankTicketDTO.getId(), userId); 
            }else if(bankBankTicketDTO.getAuditStatus()==POConstant.BANK_TICKET_UPDATE_UN_AUDIT){
                bankBankTicketDTO.setAuditStatus(POConstant.BANK_TICKET_UPDATE_REJECT);
                result+=bankTicketAuditFlowService.addBankTicketAuditFlowRecord(POConstant.BANK_TICKET_AUDIT_SUCCESS,bankBankTicketDTO.getAuditMsg(), POConstant.BUSINESS_TABLE_UPDATE_BANK_BANK_TICKET_MAINTAIN, bankBankTicketDTO.getId(), userId); 
            }
            
            BankInfoDTO bankInfoDTO=BeanMapper.map(bankBankTicketDTO, BankInfoDTO.class);
            result+=updateBankBankTicketAudit(bankBankTicketDTO, bankInfoDTO, userId);
            
            if(result<2){
                throw new ServiceBizException("银行银票限额驳回失败");
            }
        }
        
    }

}
