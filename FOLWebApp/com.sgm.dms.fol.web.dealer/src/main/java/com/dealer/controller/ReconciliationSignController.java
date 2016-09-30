package com.dealer.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dealer.dro.ExportBillsDro;
import com.dealer.vo.BillFileDetailVo;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.AutoPojo;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.FileUtils;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.SysException;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.dms.fol.reconciliation.dto.BillFileDetailDTO;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/**
 * 对账单签收Controller
 * @author ShenWeiwei
 *
 */
@Controller
@RequestMapping("/datareport/account/reconciliationSign")
public class ReconciliationSignController extends BaseController{
	protected Logger logger= LogManager.getLogger(this.getClass());

	@Autowired
	private ReconciliationManagementService reconciliationManagementService;
	
	
	/**
	 * 对账单查询
	 * @param billFileDetailDTO
	 * @return
	 * @throws SQLException 
	 * @throws ServiceBizException 
	 */
	@RequestMapping(value="/query",method=RequestMethod.POST)
	@ResponseBody
	@ValidationRequestUrl
	public Map<String, Object> query(@RequestBody BillFileDetailVo bill,HttpServletRequest request,HttpServletResponse response) throws ServiceBizException, SQLException{
		return getBillFileDeatilDTOToDataBase(bill);
	}
	
	/**
	 * 到数据库查询对账单
	 * @param billFileDetailDTO
	 * @return
	 * @throws SQLException 
	 * @throws ServiceBizException 
	 */
	private Map<String, Object> getBillFileDeatilDTOToDataBase(BillFileDetailVo vo) throws ServiceBizException, SQLException {
		BillFileDetailDTO billDto = BeanMapper.map(vo, BillFileDetailDTO.class);
		billDto.setDbBillDate(DateUtil.strToDate(vo.getCurrentDate()));

		int total = findBillFileDetailCount(vo);
		List<Object> returnList = new ArrayList<Object>();
		List<BillFileDetailDTO> billFileDetailDTOList = reconciliationManagementService
				.findDealerBillFileDetailForBillFilePublishedByFdAndPage(billDto);
		returnList.add(billFileDetailDTOList);

		// 响应
		return MapUtil.setQueryDataToMap(billFileDetailDTOList, Long.parseLong(total+""));
	}

	/*
	*
	* @author ZhangBao
	* @date 2015年11月14日
	* @param bill
	* @return
	*/
		
	private int findBillFileDetailCount(BillFileDetailVo bill) throws ServiceBizException, SQLException {
		BillFileDetailDTO billDto = BeanMapper.map(bill, BillFileDetailDTO.class);
		billDto.setDbBillDate(DateUtil.strToDate(bill.getCurrentDate()));
		int total=0;
		billDto.setEndNo(0);
		List<BillFileDetailDTO>   data = reconciliationManagementService.findDealerBillFileDetailForBillFilePublishedByFdAndPage(billDto);
		total=data.size();
		return total;
	}

	/*****
	 * 导出对账单(小)
	 * @throws ServiceBizException 
	 * @throws SysException 
	 * @throws IOException 
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/exportBillFileDetail")
	@ValidationRequestUrl
	public  ResponseEntity<byte[]> exportBillFileDetail(HttpServletRequest request,HttpServletResponse response) throws ServiceBizException, SysException, IOException, SQLException{
		BillFileDetailVo vo=AutoPojo.bindRequestParam(request, BillFileDetailVo.class);
		BillFileDetailDTO bill=BeanMapper.map(vo, BillFileDetailDTO.class);
		bill.setDbBillDate(DateUtil.strToDate(vo.getCurrentDate()));
		bill.setStatus(0);
		
		//设置导出文件header
		String [] header={"序号","标题","账单年月","生效日期","签收状态","确认状态"};
				
		//查询数据
		List<BillFileDetailDTO> data=reconciliationManagementService.findDealerFileDetailByFdAndPage(bill);
		
/*		//转换为要导出的数据
		List<ExportBillsVo> expList=BeanMapper.mapList(data, ExportBillsVo.class);*/
		//转换为要导出的数据
		List<ExportBillsDro> expList=convertList(data);
		
		//设置序号
		setLineNumber(expList);
		
		//定义文件名称
		String fileName="Bill_DETAIL_" + vo.getCurrentDate() + ".xls";
		
		//导出文件
		ByteArrayOutputStream bos=reconciliationManagementService.<ExportBillsDro>exportXls4BillFile(expList, vo.getToken(), header,fileName);
		
		
		return new ResponseEntity<byte[]>(bos.toByteArray(),getHeaders(fileName),HttpStatus.CREATED);

		
	}
	
	/*
	*
	* @author ZhangBao
	* TODO description
	* @date 2015年11月16日
	* @param expList
	*/
		
	private void setLineNumber(List<ExportBillsDro> expList) {
		if(null!=expList&&expList.size()>0){
			for(int i=0;i<expList.size();i++){
				ExportBillsDro vo=expList.get(i);
				vo.setNum(i+1);
			}
		}
		
	}

	/****
	 * 签收文件(小)
	 * @throws IOException 
	 */
	@RequestMapping(value = "/recerveFile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	@ResponseBody
	public Boolean recervFile(@Validated @RequestBody List<BillFileDetailVo> billFileDetailVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<BillFileDetailDTO> billFileDetailDTO = BeanMapper.mapList(billFileDetailVo, BillFileDetailDTO.class);
		int result = reconciliationManagementService.updateBillFileDetailStatusById(billFileDetailDTO);

		// 响应
		return result > 0 ? true : false;
	}
	/*
	*
	* @author ZhangBao
	* 获取headers信息
	* @date 2015年11月13日
	* @param string
	* @return
	*/
		
	private HttpHeaders getHeaders(String fileName) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return headers;
	}
	private List<ExportBillsDro> convertList(List<BillFileDetailDTO> data) {
		List<ExportBillsDro> list = BeanMapper.mapList(data, ExportBillsDro.class);
		for (int i = 0; i < list.size(); i++) {
			ExportBillsDro v = list.get(i);
			v.setNum(i + 1);
			if ("0".equals(v.getStatus())) {
				v.setStatus("未签收");
			} else {
				v.setStatus("已签收");
			}
		}
		return list;
	}
	
	/****
	 * 确认文件
	 * @throws Exception 
	 */
	@RequestMapping(value = "/confirmFile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	@ResponseBody
	public Boolean confirmFile(@Validated @RequestBody List<BillFileDetailVo> billFileDetailVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<BillFileDetailDTO> billFileDetailDTO = BeanMapper.mapList(billFileDetailVo, BillFileDetailDTO.class);
		int result = reconciliationManagementService.confirmBillFileDetails(billFileDetailDTO);

		// 响应
		return result > 0 ? true : false;
	}
	
	/**
	 * 下载对账单(明细)
	 * @param billFileDetailDTO
	 * @return
	 * @throws SQLException 
	 * @throws ServiceBizException 
	 */
	@RequestMapping(value = "/downLoadBillFileDetailData", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	public ResponseEntity<byte[]> downLoadBillFileDetailData(HttpServletRequest request,HttpServletResponse response) throws ServiceBizException, SysException, IOException, SQLException{
		BillFileDetailVo vo=AutoPojo.bindRequestParam(request, BillFileDetailVo.class);
		ByteArrayOutputStream bos= (ByteArrayOutputStream) FileUtils.downLoadStream(vo.getFiledId(), vo.getToken());
		HttpHeaders headers = new HttpHeaders();    
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);    
        headers.setContentDispositionFormData("attachment", "Bill_DETAIL_"+ DateUtil.formartDate(new Date())+".txt");    
        return new ResponseEntity<byte[]>(bos.toByteArray(), headers, HttpStatus.CREATED);    

	}

	/*private Object getBillFileDeatilData(BillFileDetailVo bill) throws ServiceBizException, SQLException {
		ResponseDTO responseDTO=new ResponseDTO();
		 BillFileDetailDTO billDto = BeanMapper.map(bill, BillFileDetailDTO.class);
		 billDto.setDbBillDate(DateUtil.strToDate(bill.getCurrentDate()));
		int total=findBillFileDetailDataCount(bill);
		responseDTO.setTotal(total);
		List<Object> returnList=new ArrayList<Object>();
		try {
			List<BillFileDetailDTO> billFileDetailDTOList=reconciliationManagementService.findDealerBillFileDetailForBillFilePublishedByFdAndPage(billDto);
			returnList.add(billFileDetailDTOList);
			
			responseDTO.setState(StateConstant.SUCCESS);
		} catch (Exception e) {
			logger.info(e.getMessage());
			responseDTO.setState(StateConstant.DATABASE_ERROR);
			responseDTO.setErrorCode(CodeConstant.DB_ERROR);
		}
		returnList.add(responseDTO);
		return returnList;
	}

	private int findBillFileDetailDataCount(BillFileDetailVo bill) {
		
		return 0;
	}
*/

	
		
	
	
	
}
