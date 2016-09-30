package com.sgm.dms.fol.controller;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.bankTicket.api.BankTicketBusinessInquiriesService;
import com.sgm.dms.fol.bankTicket.dto.BankTicketDraftsOverdueInterestDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestIssueAndConfirmDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestMonthDTO;
import com.sgm.dms.fol.common.api.exception.VoNotValidException;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.AutoPojo;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.dro.BankTicketBusinessInquiriesDRO;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.dms.fol.vo.BankTicketBusinessInquiriesVo;
import com.sgm.dms.fol.vo.BankTicketDraftsOverdueInterestFormVo;
import com.sgm.dms.fol.vo.BankTicketMonthInterestVo;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/******
 * 银票业务查询controller
*
* @author zhang bao
* TODO description
* @date 2016年1月14日
 */
@Controller
@RequestMapping("/bankTicket/businessInquiries")
public class BankTicketBusinessInquiriesController extends BaseController{
	
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private ReconciliationManagementService reconciliationManagementService;
	
	@Autowired
	private BankTicketBusinessInquiriesService bankTicketBusinessInquiriesService;
	
	/** 
	 * 查询票据贴息开票清单
	 * @author DELL
	 * TODO description
	 * @date 2016年3月24日
	 * @param vo
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	@ResponseBody
	public Object getBankTicketBusinessInquiries(@RequestBody BankTicketBusinessInquiriesVo vo) throws Throwable {
	    //检查VO
	    checkBankTicketBusinessInquiriesVo(vo);
	    
	    //数据初始化
	    BankTicketInterestMonthDTO bankTicketInterestMonthDTO=BeanMapper.map(vo, BankTicketInterestMonthDTO.class);
	    
	    //查询票据贴息开票清单
	    List<BankTicketDraftsOverdueInterestDTO> list=bankTicketBusinessInquiriesService.findBankTicketInterestByWhere(bankTicketInterestMonthDTO);
	    
	    //批量转换成VO
	    List<BankTicketDraftsOverdueInterestFormVo> voList=BeanMapper.mapList(list, BankTicketDraftsOverdueInterestFormVo.class);
	    
	    //转换成财务专用的格式
	    StringUtil.fontFormatFinance(voList);
	    
	    //设置前台需要的数据
	    Map<String, Object> responsedata=MapUtil.setQueryDataToMap(voList, Long.valueOf(getBankTicketDraftsOfOverdueInterestCount(bankTicketInterestMonthDTO)));
	    
	    return responsedata;
	}
	
	private void checkBankTicketBusinessInquiriesVo(BankTicketBusinessInquiriesVo vo) throws Exception{
	    if(null==vo||StringUtil.isEmpty(vo.getYear())||StringUtil.isEmpty(vo.getMonth())){
          throw new VoNotValidException("======年月为空，请选择年月在查询=======");
      }
	}
	
	private int getBankTicketDraftsOfOverdueInterestCount(BankTicketInterestMonthDTO bankTicketInterestMonthDTO) throws SQLException {
		return bankTicketBusinessInquiriesService.findBankTicketInterestCountByWhere(bankTicketInterestMonthDTO);
	}

	/**
	 * 贴息统计生成贴息凭证号
	 */
	@RequestMapping(value = "/sendInterestAmount", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	@ResponseBody
	public Object sendInterestAmount(@RequestBody BankTicketBusinessInquiriesVo vo,HttpServletRequest request) throws Throwable {
	    //检查VO
        checkBankTicketBusinessInquiriesVo(vo);
	    
	    //数据初始化
		BankTicketInterestMonthDTO requestDto=BeanMapper.map(vo, BankTicketInterestMonthDTO.class);
		
		//调用贴息统计生成贴息凭证号SERVICE
		bankTicketBusinessInquiriesService.gernerateBankTicketInvoiceNumber(requestDto,CookieUtil.getUserId(request));
		
		return true;
	}
	
	/**
	 * 导出贴息清单
	 * @throws Exception 
	 */
	@RequestMapping(value = "/exportInterestAmount")
	public ResponseEntity<byte[]> exportBillFile(HttpServletRequest request)
			throws Throwable {
		BankTicketBusinessInquiriesVo vo = AutoPojo.bindRequestParam(request, BankTicketBusinessInquiriesVo.class);

		// 查询数据
//		BankTicketDraftsOverdueInterestDTO bankticket=BeanMapper.map(vo, BankTicketDraftsOverdueInterestDTO.class);
//		bankticket.setYearMonth(vo.getYear()+vo.getMonth());
		
		BankTicketInterestMonthDTO bankTicketInterestMonthDTO=BeanMapper.map(vo, BankTicketInterestMonthDTO.class);
		bankTicketInterestMonthDTO.setBeginNo(0);
		bankTicketInterestMonthDTO.setEndNo(0);
		
		List<BankTicketDraftsOverdueInterestDTO> list=bankTicketBusinessInquiriesService.findBankTicketInterestByWhere(bankTicketInterestMonthDTO);

		// 转换为要导出的数据
		List<BankTicketBusinessInquiriesDRO> expList =BeanMapper.mapList(list, BankTicketBusinessInquiriesDRO.class);
		
		//设置输出金额格式
		//StringUtil.fontFormatFinance(expList);

		// 定义文件名称
		String fileName = "INTEREST_" + DateUtil.formartDate(new Date()) + ".csv";
		
		// 设置导出文件header
				String[] header = { "SAP客户代码", "客户名称", "贴息（含税价", "金额（不含税价）","增值税", "发票号码","EMS快递单号"};
		// 导出文件
		//ByteArrayOutputStream bos = reconciliationManagementService.<BankTicketBusinessInquiriesDRO> exportXls4BillFile(expList, vo.getToken(),header, fileName);
		ByteArrayOutputStream bos = reconciliationManagementService.exportCsvFile(header, expList, fileName, vo.getToken());

		return new ResponseEntity<byte[]>(bos.toByteArray(),
				getHeaders(fileName), HttpStatus.CREATED);

	}
	
	private HttpHeaders getHeaders(String fileName) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return headers;
	}
	
	/**
	 * 导入贴息清单
	 * @throws Exception 
	 */
	@RequestMapping(value = "/importInterestAmount", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object importFile(@Valid @RequestBody BankTicketBusinessInquiriesVo bankTicketBusinessInquiriesVo, BindingResult validResult,HttpServletRequest request)
			throws Throwable {    
	    //验证数据
	    check(validResult);
	    
	    //数据初始化
	    BankTicketInterestMonthDTO bankTicketInterestMonthDTO=BeanMapper.map(bankTicketBusinessInquiriesVo, BankTicketInterestMonthDTO.class);
	    
	    //调用导入贴息清单SERVICE
	    bankTicketBusinessInquiriesService.importInterestAmount(bankTicketInterestMonthDTO,bankTicketBusinessInquiriesVo.getToken(),bankTicketBusinessInquiriesVo.getFiledId(),CookieUtil.getUserId(request));
	    
	    return true;
	}
	
	/** 
	 * 验证数据
	 * @author DELL
	 * TODO description
	 * @date 2016年3月25日
	 * @param validResult
	 * @throws VoNotValidException
	 */
	private void check(BindingResult validResult) throws VoNotValidException{
	    if(validResult.hasErrors()){
	        throw new VoNotValidException(this.getErrorMessages(validResult.getAllErrors()));
	    }
	}

	/** 
	 * 月度票据贴息发布
	 * @author DELL
	 * TODO description
	 * @date 2016年3月25日
	 * @param bankTicketMonthInterestVo
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/issue",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object issueBankTicket(@RequestBody BankTicketMonthInterestVo bankTicketMonthInterestVo) throws Throwable{

	    // 数据初始化
	    String [] idsArray=initData(bankTicketMonthInterestVo);
	    
	    // 批量调用SERVICE
	    batchCallService(idsArray,bankTicketMonthInterestVo);

        return true;
    }
	
	/** 
	 * 数据初始化
	 * @author DELL
	 * TODO description
	 * @date 2016年3月25日
	 * @param bankTicketMonthInterestVo
	 * @return
	 * @throws Exception
	 */
	private String[] initData(BankTicketMonthInterestVo bankTicketMonthInterestVo) throws Exception{
	    String ids = bankTicketMonthInterestVo.getIds();
        String[] idsArray = null;
        if(null != ids){
            idsArray = ids.split(",");
        }
        return idsArray;
	}
	
	/** 
	 * 批量调用SERVICE
	 * @author DELL
	 * TODO description
	 * @date 2016年3月25日
	 * @throws Exception
	 */
	private void batchCallService(String[] idsArray,BankTicketMonthInterestVo bankTicketMonthInterestVo) throws Exception{
	    if(null != idsArray){
            for (String id : idsArray) {
                bankTicketMonthInterestVo.setId(Long.parseLong(id.trim()));
                BankTicketInterestIssueAndConfirmDTO bankTicketInterestIssueAndConfirmDTO=BeanMapper.map(bankTicketMonthInterestVo, BankTicketInterestIssueAndConfirmDTO.class);
                bankTicketBusinessInquiriesService.bankTicketInterestIssue(bankTicketInterestIssueAndConfirmDTO);
            }
        }
	}
}
