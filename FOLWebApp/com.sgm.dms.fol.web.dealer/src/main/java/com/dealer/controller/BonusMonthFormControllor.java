package com.dealer.controller;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.dealer.dro.BonusMonthFormDro;
import com.dealer.vo.BonusMonthFormVo;
import com.sgm.dms.fol.bonus.api.BonusService;
import com.sgm.dms.fol.bonus.dto.BonusReconcileDTO;
import com.sgm.dms.fol.common.api.domain.CodeDTO;
import com.sgm.dms.fol.common.api.services.CodeService;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.AutoPojo;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.RSAUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/**
 * 奖金月度报表
 * 
 * @author lujinglei
 * 
 */
@Controller
@RequestMapping("/bonus/bonusMonthForm")
public class BonusMonthFormControllor extends BaseController {
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private CodeService codeService;
	@Autowired
	private BonusService bonusService;
	@Autowired
	private ReconciliationManagementService reconciliationManagementService;

	/*****
	 * 奖金月度报表
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	@ResponseBody
	public Object getBonusMonthDeatil(@Validated @RequestBody BonusMonthFormVo bonusVo,HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		// BonusReconcileDTO bonusDto =
		// BeanMapper.map(bonusVo,BonusReconcileDTO.class);
		bonusVo.setSapCode(RSAUtil.decryptByPrivateKey(CookieUtil.getSapCode(request)));
		List<BonusMonthFormVo> bonusList = findBonusMonthFormToDataBase(bonusVo);
		// 将金额转换成0，0.01
		StringUtil.fontFormatFinance(bonusList);
		setMoneyFormat(bonusList);
		// 响应
		return MapUtil.setQueryDataToMap(bonusList, Long.parseLong(getQueryTotalSize(bonusVo) + ""));
	}

	/**
	 * 查询code数据
	 */
	@RequestMapping(value = "/findCodeTypeNames", method = RequestMethod.POST)
	@ValidationRequestUrl
	@ResponseBody
	public Object findCodeTypeNames(HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<List<?>> list = findCodeTypeNamesToDataBase();
		
		// 响应
		return MapUtil.setQueryDataToMap(list, Long.parseLong(list.size() + ""));
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

	/**
	 * 到数据库查询数据并返回DTO数据集
	 * 
	 * @throws SQLException
	 */
	private List<BonusMonthFormVo> findBonusMonthFormToDataBase(
			BonusMonthFormVo bonusVo) throws SQLException {
		BonusReconcileDTO searchDto = BeanMapper.map(bonusVo,
				BonusReconcileDTO.class);
		List<BonusReconcileDTO> data = bonusService.getBonusMonthForm(searchDto);

		logger.info("=====查询完成======");
		return convertDtoToVo(data);
	}

	/**
	 * 数据统一转换成VO对象
	 */
	private List<BonusMonthFormVo> convertDtoToVo(List<BonusReconcileDTO> dtos) {
		return BeanMapper.mapList(dtos, BonusMonthFormVo.class);
	}
	/**
	 * 查询总条数
	 * @param bonusVo
	 * @return
	 * @throws SQLException
	 */
	private int getQueryTotalSize(BonusMonthFormVo bonusVo) throws SQLException {
		bonusVo.setBeginNo(null);
		bonusVo.setEndNo(null);
		List<BonusMonthFormVo> bonusMonthDeatilVos = findBonusMonthFormToDataBase(bonusVo);
		return bonusMonthDeatilVos.size();
	}

	/*****
	 * 导出奖金月度报表
	 */
	@RequestMapping("/bonusMonthForm")
	@ValidationRequestUrl
	public ResponseEntity<byte[]> exportMonthDeatilResult(HttpServletRequest request,HttpServletResponse response) throws Exception {
		BonusMonthFormVo vo = AutoPojo.bindRequestParam(request,
				BonusMonthFormVo.class);
		BonusReconcileDTO bonusDto = BeanMapper
				.map(vo, BonusReconcileDTO.class);
		// 设置标题
		String[] header = { "序号", "客户代码", "客户名称", "年度", "月度","本月期初余额",
				"本月增加:新增颁发, 订单取消(奖金解冻) , 退货奖金返回  ", "本月减少:订单扣减, 订单冻结  ","本月期末可用余额" };
		//解密
		bonusDto.setSapCode(RSAUtil.decryptByPrivateKey(CookieUtil.getSapCode(request)));
		// 查询数据
		List<BonusReconcileDTO> data = bonusService.getBonusMonthForm(bonusDto);

		// 转换为要导出的数据
		List<BonusMonthFormDro> expList = BeanMapper.mapList(data,
				BonusMonthFormDro.class);
		setExportMoneyFormat(expList);
		StringUtil.fontFormatFinance(expList);
		// 设置序号
		setLineNumber(expList);
		// 定义文件名称
		String fileName = "Bonus_monthForm" + DateUtil.date2str(new Date())
				+ ".xls";
		// 导出
		ByteArrayOutputStream bos = reconciliationManagementService
				.<BonusMonthFormDro> exportXls4BillFiles(expList,
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

	private void setLineNumber(List<BonusMonthFormDro> expList) {
		if (null != expList && expList.size() > 0) {
			for (int i = 0; i < expList.size(); i++) {
				BonusMonthFormDro vo = expList.get(i);
				vo.setNum(i + 1);
			}
		}
	}
	private void setMoneyFormat(List<BonusMonthFormVo> list){
		for (BonusMonthFormVo bonusMonthFormVo : list) {
			bonusMonthFormVo.setBonusSub(String.format("%,.2f",new BigDecimal(bonusMonthFormVo.getBonusSub())));
			bonusMonthFormVo.setOrderSub(String.format("%,.2f",new BigDecimal(bonusMonthFormVo.getOrderSub()==null?"0":bonusMonthFormVo.getOrderSub())));
		}
	}
	private void setExportMoneyFormat(List<BonusMonthFormDro> list){
		for (BonusMonthFormDro bonusDro : list) {
			bonusDro.setBonusSub(String.format("%,.2f",new BigDecimal(bonusDro.getBonusSub())));
			bonusDro.setOrderSub(String.format("%,.2f",new BigDecimal(bonusDro.getOrderSub()==null?"0":bonusDro.getOrderSub())));
		}
	}
}
