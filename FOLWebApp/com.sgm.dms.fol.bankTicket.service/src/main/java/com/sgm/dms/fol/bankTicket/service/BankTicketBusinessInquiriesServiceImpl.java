package com.sgm.dms.fol.bankTicket.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.bankTicket.api.BankTicketBusinessInquiriesService;
import com.sgm.dms.fol.bankTicket.api.SapBankTicketGenerateDocNumberService;
import com.sgm.dms.fol.bankTicket.dto.BankTicketBusinessInquiriesRequestDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketBusinessInquiriesResultDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketDeadLineDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketDraftsOverdueInterestDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestDeatilQueryDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestIssueAndConfirmDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestMonthDTO;
import com.sgm.dms.fol.bankTicket.dto.IT_ITEM;
import com.sgm.dms.fol.bankTicket.repository.BankTicketDeadLineDao;
import com.sgm.dms.fol.bankTicket.repository.BankTicketInterestDeatilDao;
import com.sgm.dms.fol.bankTicket.repository.BankTicketInterestMonthDao;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.FileUtils;
import com.sgm.dms.fol.common.service.utils.StringUtil;
/****
 * 银票业务查询模块serviceImpl
*
* @author zhang bao
* TODO description
* @date 2016年1月13日
 */
@Service("BankTicketBusinessInquiriesService")
@Transactional(rollbackFor=Exception.class)
public class BankTicketBusinessInquiriesServiceImpl implements BankTicketBusinessInquiriesService {
	
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private BankTicketInterestMonthDao bankTicketInterestMonthDao;
	@Autowired
	private SapBankTicketGenerateDocNumberService sapBankTicketGenerateDocNumberService;
	@Autowired
    private BankTicketInterestDeatilDao bankTicketInterestDeatilDao;
	@Autowired
	private BankTicketDeadLineDao bankTicketDeadLineDao;
	private static String CHARSET = "gb2312";
	
	@Override
	public List<BankTicketDraftsOverdueInterestDTO> findBankTicketInterestByWhere(BankTicketInterestMonthDTO bankticket) throws ServiceBizException {
		try {
			//查询根据条件的所有银票信息
			List<BankTicketDraftsOverdueInterestDTO> bankTicketList=bankTicketInterestMonthDao.findBankTicketInterestMonthByWhere(bankticket);
			
			return bankTicketList;
		} catch (SQLException e) {
			logger.warn("查询银票信息异常:"+e);
			throw new ServiceBizException("查询查询银票信息异常:"+e);
		}
		
	}

//	//贴息 (含税价)
//    private BigDecimal getDisCountAmount(BigDecimal amount,Integer interestDay,Double annualInterestRate) {
//        return MathUtils.getBigDecimal(amount.multiply(new BigDecimal(annualInterestRate.toString())).multiply(new BigDecimal(interestDay)).divide(new BigDecimal(POConstant.YEAR),2,BigDecimal.ROUND_HALF_EVEN));
//    }
//    
//    //金额 (不含税价)
//    private BigDecimal getCountAmount(BigDecimal disCountAmount,Double discountRate) {
//        return disCountAmount.divide(new BigDecimal(discountRate.toString()),2,BigDecimal.ROUND_HALF_EVEN);
//    }
//    
//    //增值税
//    private BigDecimal getDddedTaxAmount(BigDecimal disCountAmount,BigDecimal countAmount) {
//        return disCountAmount.subtract(countAmount);
//    }

//	private BankTicketDeadLineDTO getBankTicketDeadLine(String sapCode) throws SQLException {
//		
//		//首先查询特殊经销商的期限信息
//		List<BankTicketDeadLineDTO> data=bankTicketDeadLineDao.findBankTicketLimitBySapCode(sapCode);
//		if(null!=data&&data.size()>0){
//			return data.get(0);
//		}
//		
//		DealerInfoDTO dealerInfoDTO=new DealerInfoDTO();
//        dealerInfoDTO.setSapCode(sapCode);
//        DealerInfoPo dealerInfoPo=dealerDao.getDealerInfoBySapCode(dealerInfoDTO);
//		String brandId=sapCode.substring(0,2);
//
//		data=bankTicketDeadLineDao.findBankTicketLimitByBrandAndDealerType(brandId,Integer.valueOf(dealerInfoPo.getDealerType()));
//		if(null!=data&&data.size()>0){
//			return data.get(0);
//		}
//		return new BankTicketDeadLineDTO();
//	}

	@Override
	public void gernerateBankTicketInvoiceNumber(BankTicketInterestMonthDTO interest,long userId) throws ServiceAppException {
		//1.检查该月的该客户的这些银票是否已生成发票号码了,2,发送sap请求返回贴息凭证号,3更新这些银票的贴息凭证号
		try {
			if(!checkBankTicketIsHasInvoiceNumber(interest.getSapCode(),interest.getYear(),interest.getMonth())){
				throw new ServiceBizException("该月份该用户的银票已经生成了凭证号");
			}
		} catch (SQLException e) {
		    logger.warn(e.getMessage());
		    throw new ServiceBizException("检查银票凭证号数据异常："+e);
		}
		
		try {
			//初始化数据
			initBankTicketInterestMonthDTO(interest,userId);
			//插入月度表记录
			int cnt=bankTicketInterestMonthDao.saveBankTicketInterestMonth(interest);
			if(0==cnt){
				throw new ServiceBizException("保存月度贴息记录失败");
			}
		} catch (SQLException e) {
			logger.warn(e.getMessage());
			throw new ServiceBizException("保存月度贴息信息异常:"+e);
		}
		
		//初始化数据
		List<BankTicketBusinessInquiriesRequestDTO> request=initBusinessInquiriesData(interest);
		//调用sap生成凭证号
		BankTicketBusinessInquiriesResultDTO result=sapBankTicketGenerateDocNumberService.gernerateBankTicketInvoiceNumber(request);
		if(null==result){
			throw new ServiceBizException("调用sap返回结果为空");
		}
		
		//根据返回结果更新贴息凭证号
		interest.setCommonUpdateData(userId);
		if(result.getType().equals(POConstant.SAP_GENERATE_BANK_TICKET_CERTIFICATE_NO_SUC)){
			//赋值凭证号
			if(!StringUtil.isEmpty(result.getBelnr())){
				interest.setCertificateNo(result.getBelnr());;
			}
			interest.setRemark("贴息汇总成功");
			interest.setFlag(POConstant.SAP_GENERATE_BANK_TICKET_CERTIFICATE_NO_SUC+"");
		}else if(result.getType().equals(POConstant.SAP_GENERATE_BANK_TICKET_CERTIFICATE_NO_FAIL)){
			interest.setFlag(POConstant.SAP_GENERATE_BANK_TICKET_CERTIFICATE_NO_FAIL+"");
			interest.setRemark(result.getMsg());
		}
		
		
		try {
			int cnt=bankTicketInterestMonthDao.updateBankTicketInterestMonth(interest);
			if(0==cnt){
				throw new ServiceBizException("更新月度贴息");
			}
		} catch (SQLException e) {
			logger.warn(e.getMessage());
			throw new ServiceBizException("更新月度贴息:异常信息"+e);
		}
	}



	private List<BankTicketBusinessInquiriesRequestDTO> initBusinessInquiriesData(
			BankTicketInterestMonthDTO interest) {
		List<BankTicketBusinessInquiriesRequestDTO> data=new ArrayList<BankTicketBusinessInquiriesRequestDTO>();
		
		//header
		BankTicketBusinessInquiriesRequestDTO request=new BankTicketBusinessInquiriesRequestDTO();
		request.setZ_budat("");
//		request.setZ_bukrs(interest.getCorpCode());
		request.setZ_bldat("");
		request.setZ_monat("");
		request.setZ_waers("");
		request.setZ_blart(POConstant.DOCUMENT_TYPE_DA);//标示接收银票数据
		List<IT_ITEM> its=new ArrayList<IT_ITEM>();
		IT_ITEM it_ITEM=new IT_ITEM();
		
		// 借方 贴息
		it_ITEM.setSequence(interest.getId()+"");
		it_ITEM.setDmbtr(new Double(interest.getInterestAmount().toString()));
		it_ITEM.setHkont(interest.getSapCode());
		it_ITEM.setWbank("");
		it_ITEM.setWdate("");
		it_ITEM.setZfbdt("");
		it_ITEM.setBschl(POConstant.ACCOUNTING_BORROW_CODE);
		it_ITEM.setXblnr("");
		it_ITEM.setUmskz(POConstant.SPECIAL_GL_G);
		it_ITEM.setKostl("");
		it_ITEM.setSgtxt("");
		its.add(it_ITEM);
		
		//不含税金额
		it_ITEM=new IT_ITEM();
		it_ITEM.setSequence(interest.getId()+"");
		it_ITEM.setDmbtr(new Double(interest.getUnTaxAmount().toString()));
		it_ITEM.setHkont(POConstant.SAP_NUMBER_UNTAXAMOUNT);
		it_ITEM.setWbank("");
		it_ITEM.setWdate("");
		it_ITEM.setZfbdt("");
		it_ITEM.setBschl(POConstant.ACCOUNTING_LOAN_MONTH_CODE);
		it_ITEM.setXblnr("");
		it_ITEM.setUmskz("");
		it_ITEM.setKostl("");
		it_ITEM.setSgtxt("");
		its.add(it_ITEM);
		
		//增值税 金额
		it_ITEM=new IT_ITEM();
		it_ITEM.setSequence(interest.getId()+"");
		it_ITEM.setDmbtr(new Double(interest.getAddTaxAmount().toString()));
		it_ITEM.setHkont(POConstant.SAP_NUMBER_ADDTAXAMOUNT);
		it_ITEM.setWbank("");
		it_ITEM.setWdate("");
		it_ITEM.setZfbdt("");
		it_ITEM.setBschl(POConstant.ACCOUNTING_LOAN_MONTH_CODE);
		it_ITEM.setXblnr("");
		it_ITEM.setUmskz("");
		it_ITEM.setKostl("");
		it_ITEM.setSgtxt("");
		its.add(it_ITEM);
		request.setIt_item(its);
		data.add(request);
		return data;
	}


	private void initBankTicketInterestMonthDTO(BankTicketInterestMonthDTO interest, long userId) {
		interest.setCommonAddData(userId);
		
	}

	private boolean checkBankTicketIsHasInvoiceNumber(String sapCode, String year,String Month) throws SQLException {
		Integer cnt=bankTicketInterestMonthDao.findBankTicketIsHasInvoiceNumberBySapCodeAndTime(sapCode, year, Month);
		if(0==cnt){
			return true;
		}
		return false;
	}

    /*
    *
    * @author DELL
    * @date 2016年1月18日
    * @param bankTicketInterestDTO
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketBusinessInquiriesService#findBankTicketInterestList(com.sgm.dms.fol.bankTicket.dto.BankTicketInterestDTO)
    */
    	
    @Override
    public List<BankTicketInterestDeatilQueryDTO> findBankTicketInterestList(BankTicketInterestDTO bankTicketInterestDTO) throws ServiceBizException {
        try {
            BankTicketInterestDeatilQueryDTO bankTicketInterestDeatilQueryDTO=BeanMapper.map(bankTicketInterestDTO,BankTicketInterestDeatilQueryDTO.class);
            List<BankTicketInterestDeatilQueryDTO> list=bankTicketInterestDeatilDao.findBankTicketInterestDeatilByWhere(bankTicketInterestDeatilQueryDTO);
            
            if(list!=null&&list.size()>0){
                for (BankTicketInterestDeatilQueryDTO dto : list) {
                    if(dto.getDeadLineDay()==null||"".equals(dto.getDeadLineDay().toString())){
                        List<BankTicketDeadLineDTO> deadLinddtos=bankTicketDeadLineDao.findBankTicketLimitByBrandAndDealerType(dto.getSapCode().substring(0, 2), dto.getDealerType());
                        
                        if(deadLinddtos!=null&&deadLinddtos.size()>1){
                            throw new ServiceBizException("银票期限基础数据有误");
                        }else if(deadLinddtos!=null&&deadLinddtos.size()>0){
                            BankTicketDeadLineDTO bankTicketDeadLineDTO=deadLinddtos.get(0);
                            dto.setDeadLineDay(bankTicketDeadLineDTO.getDeadlineDay());
                            dto.setFreePeriodDay(bankTicketDeadLineDTO.getFreePeriodDay());
                        }
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
    * @date 2016年1月18日
    * @param bankTicketInterestDTO
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketBusinessInquiriesService#findBankTicketInterestCount(com.sgm.dms.fol.bankTicket.dto.BankTicketInterestDTO)
    */
    	
    @Override
    public int findBankTicketInterestCount(BankTicketInterestDTO bankTicketInterestDTO) throws ServiceBizException {
        int count;
        try {
            BankTicketInterestDeatilQueryDTO bankTicketInterestDeatilQueryDTO=BeanMapper.map(bankTicketInterestDTO,BankTicketInterestDeatilQueryDTO.class);
            count=bankTicketInterestDeatilDao.findBankTicketInterestDeatilCountByWhere(bankTicketInterestDeatilQueryDTO);
        } catch (SQLException e) {            
            logger.error(e);
            throw(new ServiceAppException(e));
        }
        return count;
    }

	@Override
	public int findBankTicketInterestCountByWhere(BankTicketInterestMonthDTO bankTicketInterestMonthDTO) throws SQLException {
		return bankTicketInterestMonthDao.findBankTicketIsHasInvoiceNumberBySapCodeAndTime(bankTicketInterestMonthDTO.getSapCode(), bankTicketInterestMonthDTO.getYear(), bankTicketInterestMonthDTO.getMonth());
	}

	@Override
	public void importInterestAmount(BankTicketInterestMonthDTO inquiries, String token,String filedId,long userId)
			throws ServiceAppException, IOException {
		int result=0;
		
    	List<Object> data = getInterestTxtFile(filedId, token);
    	if(null==data||data.size()==0){
    		throw new ServiceBizException("文件内容为空,请重新上传");
    	}
    	List<BankTicketInterestMonthDTO> interests=BeanMapper.mapList(data, BankTicketInterestMonthDTO.class);
    	if(null==interests||interests.size()==0){
    		throw new ServiceBizException("===从文件读取的数据转换为DTO异常=====");
    	}
    	for(BankTicketInterestMonthDTO ins:interests){
    		initBankTicketInterests(ins,inquiries,userId);
    		
    		//更新发票号码和快递单号
    		try {
    			result=bankTicketInterestMonthDao.updateBankTicketInterestMonthBySapCodeAndTime(ins);
    		} catch (SQLException e) {
    			throw new ServiceAppException(e);
    		}
    		
    	}
    	if(0==result){
    		throw new ServiceBizException("票据贴息在该月没有数据"); 
    	}
    	
	}

	private void initBankTicketInterests(BankTicketInterestMonthDTO ins, BankTicketInterestMonthDTO inquiries,long userId) {
		ins.setYear(inquiries.getYear());
		ins.setMonth(inquiries.getMonth());
		ins.setCommonUpdateData(userId);
	}

	private List<Object> getInterestTxtFile(String filedId, String token) throws IOException {
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

			// 名称
			String dealerName = "";

			// 贴息金额
			String interestAmount = "";

			// 不含税金额
			String unTaxAmount = "";
			// 增值税金额
			String addTaxAmount = "";

			//发票号码
			String invoiceNumber = "";
			
			//快递单号
			String trackNumber="";
			// 拼接文件内容
			int index = 0;
			/*String fileName="";
			ByteArrayInputStream input=null;*/
			while ((lineTxt = br.readLine()) != null) {
				Map<String, String> obj = new Hashtable<String, String>();
				if (index > 0) {
					
					String[] str = lineTxt.split(",");
					System.out.println(str.length);
					if(7!=str.length){
						throw new IOException("============请检查上传的数据，确保每一个都不为空================");
					}
					// 获取经销商代码
					sapCode = str[0];// 获取dealerCode
					dealerName = str[1];
					interestAmount = str[2];
					unTaxAmount = str[3];
					addTaxAmount = str[4];
					invoiceNumber=str[5];
					trackNumber=str[6];
					

					/*// 单个文件结束,保存需要的信息,上传到服务器
					fileName=sapCode+"-"+DateUtil.formartDate(new Date());
					input = new ByteArrayInputStream(lineTxt.getBytes(CHARSET));
					String fileid = FileUtils.upload(fileName, token,input);
					input.close();
*/					
					// 保存需要的信息
					obj.put("sapCode", sapCode);
					obj.put("dealerName", dealerName);
					obj.put("interestAmount", interestAmount);
					obj.put("unTaxAmount", unTaxAmount);
					obj.put("addTaxAmount", addTaxAmount);
					obj.put("invoiceNumber", invoiceNumber);
					obj.put("trackNumber", trackNumber);
					list.add(obj);
					
				}
				index++;
			}

		} finally {
			br.close();
			is.close();
			out.close();
		}

		return list;

	}

    /*
    *
    * @author DELL
    * @date 2016年1月26日
    * @return
    * @throws ServiceAppException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketBusinessInquiriesService#bankTicketInterestIssue()
    */
    	
    @Override
    public boolean bankTicketInterestIssue(BankTicketInterestIssueAndConfirmDTO bankTicketInterestIssueAndConfirmDTO) throws ServiceAppException {
        try {
            bankTicketInterestIssueAndConfirmDTO.setIssueStatus(POConstant.BANK_TICKET_MONTH_ISSUE_SUCCESS);
            int result=bankTicketInterestMonthDao.updateBankTicketInterestIssueStatusOrConfirmStatusById(bankTicketInterestIssueAndConfirmDTO);
            
            if(result<=0){
                throw(new ServiceBizException("update bankTicket status faild"));
            }
        } catch (SQLException e) {            
            logger.error(e);
            throw(new ServiceAppException(e));
        }
        
        return true;
    }

    /*
    *
    * @author DELL
    * @date 2016年1月26日
    * @param bankTicketInterestIssueAndConfirmDTO
    * @return
    * @throws ServiceAppException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketBusinessInquiriesService#bankTicketInterestIssueConfirm(com.sgm.dms.fol.bankTicket.dto.BankTicketInterestIssueAndConfirmDTO)
    */
    	
    @Override
    public boolean bankTicketInterestIssueConfirm(BankTicketInterestIssueAndConfirmDTO bankTicketInterestIssueAndConfirmDTO) throws ServiceAppException {
    	boolean updateResult = false;
        try {
            bankTicketInterestIssueAndConfirmDTO.setConfirmStatus(POConstant.BANK_TICKET_MONTH_CONFIRM_SUCCESS);
            int result=bankTicketInterestMonthDao.updateBankTicketInterestIssueStatusOrConfirmStatusById(bankTicketInterestIssueAndConfirmDTO);
            
            if(result<=0){
                throw(new ServiceBizException("update bankTicket status faild"));
            }else{
            	updateResult = true;
            }
        } catch (SQLException e) {            
            logger.error(e);
            throw(new ServiceAppException(e));
        }
        return updateResult;
    }
	

}
