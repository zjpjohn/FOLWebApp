package com.sgm.dms.fol.controller;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.sgm.dms.fol.bonus.api.FundBalanceStatementService;
import com.sgm.dms.fol.bonus.dto.FundBalanceStatementDTO;
import com.sgm.dms.fol.common.api.domain.CodeDTO;
import com.sgm.dms.fol.common.api.services.CodeService;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.AutoPojo;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.dro.FundBalanceStatementDro;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.dms.fol.vo.FundBalanceStatementVo;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/**
 * 资金余额报表Controller
 * @author DELL
 * TODO description
 * @date 2016年3月23日
 */
@Controller
@RequestMapping("/fund/fundBanceStatement")
public class FundBalanceStatementControllor extends BaseController{
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private FundBalanceStatementService fundBalanceStatementService;
	@Autowired
	private ReconciliationManagementService reconciliationManagementService;
	@Autowired
	private CodeService codeService;

	/**
	 * 资金余额报表查询
	 * @param fundBalanceStatementVo
	 * @return Object
	 * @throws Throwable
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object findAmount(@Validated @RequestBody FundBalanceStatementVo fundBalanceStatementVo) throws Throwable {
		// 到数据库查询
		List<FundBalanceStatementVo> fundList = findfundToDataBase(fundBalanceStatementVo);
		
		// 将金额转换成财务格式
		StringUtil.fontFormatFinance(fundList);
		
		// 转换为前台需要的数据
		Map<String, Object> responsedata=MapUtil.setQueryDataToMap(fundList, Long.valueOf(getQueryTotalSize(fundBalanceStatementVo)));
		
		return responsedata;
	}
	
	/**
	 * 查询code数据
	 * @return Object
	 * @throws Throwable
	 */
	@RequestMapping(value = "/findCodeTypeNames", method = RequestMethod.POST)
	@ValidationRequestUrl
	@ResponseBody
	public Object findCodeTypeNames() throws Throwable {
		List<List<?>> responsedata = findCodeTypeNamesToDataBase();		
		return responsedata;
	}

	/**
	 * 到数据库查询初始数据
	 */
	private List<List<?>> findCodeTypeNamesToDataBase() throws SQLException {
		String[] columns = { "渠道类型" };
		List<List<?>> codeDTOlist = new ArrayList<List<?>>();
		for (int i = 0; i < columns.length; i++) {
			String column = columns[i];
			List<CodeDTO> codeDTOs = codeService.findCodeByTypeName(column);
			codeDTOlist.add(codeDTOs);
		}
		return codeDTOlist;
	}
	
	/**
	 * 到数据库查询数据并返回DTO数据集
	 * 
	 * @throws SQLException
	 */
	private List<FundBalanceStatementVo> findfundToDataBase(FundBalanceStatementVo fundBalanceStatementVo)
			throws SQLException {
		FundBalanceStatementDTO searchDto = BeanMapper.map(fundBalanceStatementVo, FundBalanceStatementDTO.class);
		List<FundBalanceStatementDTO> data = fundBalanceStatementService.findAmountByWhere(searchDto);
		logger.info("=====查询完成======");
		return convertDtoToVo(data);
	}

	/**
	 * 数据统一转换成VO对象
	 */
	private List<FundBalanceStatementVo> convertDtoToVo(List<FundBalanceStatementDTO> dtos) {
		return BeanMapper.mapList(dtos, FundBalanceStatementVo.class);
	}
	
	/**
	 * 查询总条数
	 * @param fundBalanceStatementVo
	 * @return
	 * @throws SQLException
	 */
	private int getQueryTotalSize(FundBalanceStatementVo fundBalanceStatementVo) throws SQLException {
		fundBalanceStatementVo.setBeginNo(null);
		fundBalanceStatementVo.setEndNo(null);
		List<FundBalanceStatementVo> fundBalanceStatementVos = findfundToDataBase(fundBalanceStatementVo);
		return fundBalanceStatementVos.size();
	}
	
	/*****
	 * 资金余额报表
	 */
	@RequestMapping("/exportFile")
	public ResponseEntity<byte[]> exportMonthDeatilResult(
			HttpServletRequest request) throws Throwable {
		FundBalanceStatementVo vo = AutoPojo.bindRequestParam(request, FundBalanceStatementVo.class);
		FundBalanceStatementDTO fundBalanceStatementDTO = BeanMapper.map(vo, FundBalanceStatementDTO.class);
		// 设置标题
		String[] header = { "序号", "客户代码", "客户名称", "储备金可用余额", "奖金可用余额","奖金使用比例", "最大可订购配件金额" };
		// 查询数据
		List<FundBalanceStatementDTO> data = fundBalanceStatementService.findAmountByWhere(fundBalanceStatementDTO);
				

		// 转换为要导出的数据
		List<FundBalanceStatementDro> expList = BeanMapper.mapList(data,
				FundBalanceStatementDro.class);
		StringUtil.fontFormatFinance(expList);
		// 设置序号
		setLineNumber(expList);
		// 定义文件名称
		String fileName = "FundBanceStateMent_" + DateUtil.date2str(new Date())
				+ ".xls";
		// 导出
		ByteArrayOutputStream bos = reconciliationManagementService
				.<FundBalanceStatementDro> exportXls4BillFiles(expList,
						vo.getToken(), header, fileName);
		return new ResponseEntity<byte[]>(bos.toByteArray(),
				getHeaders(fileName), HttpStatus.CREATED);

	}

	/**
	 * 获取headers信息
	 */
	private HttpHeaders getHeaders(String fileName) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return headers;
	}

	private void setLineNumber(List<FundBalanceStatementDro> expList) {
		if (null != expList && expList.size() > 0) {
			for (int i = 0; i < expList.size(); i++) {
				FundBalanceStatementDro vo = expList.get(i);
				vo.setNum(i + 1);
			}
		}
	}
		
}
