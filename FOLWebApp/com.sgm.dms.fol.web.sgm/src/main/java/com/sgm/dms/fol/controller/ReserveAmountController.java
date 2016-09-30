/**
 * @ClassName: demoAmountController
 * @Description: 储备金Controller
 * @author: ChenChong
 * @date: 2015年10月9日 上午9:51:56
 * 
 * 
 */
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

import com.sgm.dms.fol.common.api.domain.CodeDTO;
import com.sgm.dms.fol.common.api.services.CodeService;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.AutoPojo;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.dro.ExportFilDRO;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.dms.fol.reserve.api.ReserveService;
import com.sgm.dms.fol.reserve.dto.ReserveDTO;
import com.sgm.dms.fol.vo.ExportFileVo;
import com.sgm.dms.fol.vo.ReserveTableDataVo;
import com.sgm.dms.fol.vo.ReserveVo;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/**
 * 
 * 储备金Controller
 * @date 2016年4月6日
 */
@Controller
@RequestMapping("/reserve/reserveamount")
public class ReserveAmountController extends BaseController {

	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());

	// 储备金 SERVICE
	@Autowired
	private ReserveService reserveService;

	// Code SERVICE
	@Autowired
	private CodeService codeService;
	
	// 对账 SERVICE
	@Autowired
	private ReconciliationManagementService reconciliationManagementService;

	/**
	 * 储备金动态明细查询
	 * 
	 * @param reserveAmountDTO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	@ResponseBody
	public Object findReserveAmount(@Validated @RequestBody ReserveVo reserveVo) throws Throwable {

	    //查询储备金余额
		List<ReserveDTO> reservedtos = findReserveAmountByDTO(reserveVo);

		//批量转成VO
		List<ReserveTableDataVo> reserveVos = BeanMapper.mapList(reservedtos, ReserveTableDataVo.class);
		
		//金额转换成财务专用的模式
		StringUtil.fontFormatFinance(reserveVos);
		
		//设置前台需要的对象
		Map<String, Object> responsedata=MapUtil.setQueryDataToMap(reserveVos, Long.valueOf(getQueryTotalSize(reserveVo)));

		return responsedata;
	}

	/**
	 * 查询code数据
	 */
	@RequestMapping(value = "/findCodeTypeNames", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	@ResponseBody
	public Object findCodeTypeNames() throws Throwable {
		List<List<?>> responsedata = findCodeTypeNamesToDataBase();
		return responsedata;
	}

	/**
	 * 根据DTO查询数据库(储备金查询)
	 * 
	 * @return
	 */
	private List<ReserveDTO> findReserveAmountByDTO(ReserveVo reserveVo)
			throws SQLException {
		ReserveDTO searchDto = BeanMapper.map(reserveVo, ReserveDTO.class);
		List<ReserveDTO> data = reserveService.getReserveAmount(searchDto);

		logger.info("=====查询完成======");
		return data;
	}

	/**
	 * 到 数据库查询初始数据
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

	private int getQueryTotalSize(ReserveVo reserveVo) throws SQLException {
		reserveVo.setBeginNo(null);
		reserveVo.setEndNo(null);
		List<ReserveDTO> reservedtos = findReserveAmountByDTO(reserveVo);
		return reservedtos.size();
	}

	/**
	 * 储备金查询结果导出
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/exportReserveQueryResult")
	public ResponseEntity<byte[]> exportReserveQueryResult(HttpServletRequest request) throws Throwable {
		ExportFileVo reserveVo = AutoPojo.bindRequestParam(request,ExportFileVo.class);
		ReserveDTO reserveDto = BeanMapper.map(reserveVo, ReserveDTO.class);

		// 设置导出文件header
		String[] header = { "序号", "客户代码", "客户名称", "渠道类型", "储备金类型", "储备金余额",
				"冻结储备金", "储备金可用余额" };
		// 查询数据
		List<ReserveDTO> data = reserveService.getReserveAmount(reserveDto);

		// 转换为要导出的数据
		List<ExportFilDRO> expList = BeanMapper.mapList(data,
				ExportFilDRO.class);
		StringUtil.fontFormatFinance(expList);
		// 设置序号
		setLineNumber(expList);
		// 定义文件名称
		String fileName = "Reserve_" + DateUtil.date2str(new Date()) + ".xls";
		// 导出文件
		ByteArrayOutputStream bos = reconciliationManagementService
				.<ExportFilDRO> exportXls4BillFile(expList,
						reserveVo.getToken(), header, fileName);

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

	private void setLineNumber(List<ExportFilDRO> expList) {
		if (null != expList && expList.size() > 0) {
			for (int i = 0; i < expList.size(); i++) {
				ExportFilDRO vo = expList.get(i);
				vo.setNum(i + 1);
			}
		}
	}
}
