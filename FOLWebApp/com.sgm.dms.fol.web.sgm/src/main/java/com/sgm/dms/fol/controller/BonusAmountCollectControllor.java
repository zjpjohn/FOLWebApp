package com.sgm.dms.fol.controller;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
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

import com.sgm.dms.fol.bonus.api.BonusService;
import com.sgm.dms.fol.bonus.dto.BonusReconcileDTO;
import com.sgm.dms.fol.common.api.domain.CodeDTO;
import com.sgm.dms.fol.common.api.services.CodeService;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.AutoPojo;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.dro.AmountReconcileCollectDRO;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.dms.fol.vo.AmountCollectVo;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/**
 * 奖金余额汇总表
 * 
 * @author lujinglei
 * 
 */
@Controller
@RequestMapping("/bonus/amountReconcileCollect")
public class BonusAmountCollectControllor extends BaseController {
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private CodeService codeService;
	@Autowired
	private BonusService bonusService;
	@Autowired
	private ReconciliationManagementService reconciliationManagementService;

	/**
	 * 奖金余额汇总
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	@ResponseBody
	public Object getAmountReconcileCollect(@Validated @RequestBody AmountCollectVo amountCollectVo) throws Throwable {
		// 数据库查询奖金余额汇总
		List<AmountCollectVo> bonusList = findAmountReconcileCollectToDataBase(amountCollectVo);
		
		// 转换为财务专用格式
		setMoneyFormat(bonusList);
		
		// 设置成前台需要的数据
		Map<String, Object> responsedata=MapUtil.setQueryDataToMap(bonusList, Long.valueOf(getQueryTotalSize(amountCollectVo)));
		
		return responsedata;
	}

	/**
	 * 查询code数据
	 */
	@RequestMapping(value = "/findCodeTypeNames", method = RequestMethod.POST)
	@ValidationRequestUrl
	@ResponseBody
	public Object findCodeTypeNames() throws Throwable {
		List<List<?>> responsedata = findCodeTypeNamesToDataBase();
		
		return responsedata;
	}

	/**
	 * 到 数据库查询初始数据
	 */
	private List<List<?>> findCodeTypeNamesToDataBase() throws SQLException {
		String[] columns = { "品牌" };
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
	private List<AmountCollectVo> findAmountReconcileCollectToDataBase(
			AmountCollectVo amountCollectVo) throws SQLException {
		BonusReconcileDTO searchDto = BeanMapper.map(amountCollectVo,
				BonusReconcileDTO.class); 
		List<BonusReconcileDTO> data = bonusService.getAmountReconcileCollect(searchDto);
		logger.info("=====查询完成======");
		return convertDtoToVo(data);
	}

	/**
	 * 数据统一转换成VO对象
	 */
	private List<AmountCollectVo> convertDtoToVo(List<BonusReconcileDTO> dtos) {
		return BeanMapper.mapList(dtos, AmountCollectVo.class);
	}
	/**
	 * 查询记录数
	 * @return
	 * @throws SQLException
	 */
	private int getQueryTotalSize(AmountCollectVo amountCollectVo) throws SQLException {
		amountCollectVo.setBeginNo(null);
		amountCollectVo.setEndNo(null);
		List<AmountCollectVo> amountCollectVos = findAmountReconcileCollectToDataBase(amountCollectVo);
		return amountCollectVos.size();
	}
	/**
	 * 导出奖金余额汇总表
	 */
	@RequestMapping("/amountReconcileCollect")
	public ResponseEntity<byte[]> exportMonthDeatilResult(
			HttpServletRequest request) throws Exception {
		AmountCollectVo vo = AutoPojo.bindRequestParam(request,
				AmountCollectVo.class);
		BonusReconcileDTO bonusDto = BeanMapper
				.map(vo, BonusReconcileDTO.class);
		// 设置标题
		String[] header = { "序号", "品牌","客户代码", "本月期初余额", "财务本月颁发", "退货奖金返回", "订单使用",
				"本月期末余额" };
		// 查询数据
		List<BonusReconcileDTO> data = bonusService
				.getAmountReconcileCollect(bonusDto);

		// 转换为要导出的数据
		List<AmountReconcileCollectDRO> expList = BeanMapper.mapList(data,
				AmountReconcileCollectDRO.class);
		// 转换为财务专用格式
		setMoneyFormat(expList);
		// 设置序号
		setLineNumber(expList);
		// 定义文件名称
		String fileName = "Bonus_amountCollectVo"
				+ DateUtil.date2str(new Date()) + ".xls";
		// 导出
		ByteArrayOutputStream bos = reconciliationManagementService
				.<AmountReconcileCollectDRO> exportXls4BillFiles(expList,
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

	private void setLineNumber(List<AmountReconcileCollectDRO> expList) {
		if (null != expList && expList.size() > 0) {
			for (int i = 0; i < expList.size(); i++) {
				AmountReconcileCollectDRO amountReconcileCollectDRO = expList.get(i);
				amountReconcileCollectDRO.setNum(i + 1);
			}
		}
	}
	
	/**
	 * 设置成财务专用的格式
	 */
	private void setMoneyFormat(List<?> list) throws Exception{
	    StringUtil.fontFormatFinance(list);
		for (Object object : list) {
		    if(object instanceof AmountCollectVo){
		        AmountCollectVo amountCollectVo=(AmountCollectVo)object;
	            amountCollectVo.setBonusAdd(String.format("%,.2f",new BigDecimal(amountCollectVo.getBonusAdd()==null?"0":amountCollectVo.getBonusAdd())));
	            amountCollectVo.setOrderSub(String.format("%,.2f",new BigDecimal(amountCollectVo.getOrderSub()==null?"0":amountCollectVo.getOrderSub())));
		    }else if(object instanceof AmountReconcileCollectDRO){
		        AmountReconcileCollectDRO bonusDro=(AmountReconcileCollectDRO)object;
		        bonusDro.setBonusAdd(String.format("%,.2f",new BigDecimal(bonusDro.getBonusAdd())));
	            bonusDro.setOrderSub(String.format("%,.2f",new BigDecimal(bonusDro.getOrderSub()==null?"0":bonusDro.getOrderSub())));
		    }
		    
		}
	}
}
