package com.sgm.dms.fol.controller;

import java.io.ByteArrayOutputStream;
import java.net.URLDecoder;
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
import com.sgm.dms.fol.dro.BonusMonthDeatilDRO;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.dms.fol.vo.BonusMonthDeatilVo;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/**
 * 奖金月度明细查询
 * 
 * @author lujinglei
 * TODO description
 * @date 2016年1月6日
 */
@Controller
@RequestMapping("/bonus/bonusMonthdeatil")
public class BonusMonthDeatilControllor extends BaseController {
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private CodeService codeService;
	@Autowired
	private BonusService bonusService;
	@Autowired
	private ReconciliationManagementService reconciliationManagementService;

	/**
	 * 奖金月度明细查询
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getBonusMonthDeatil(@Validated @RequestBody BonusMonthDeatilVo bonusVo) throws Throwable {
		// 数据库查询月度明细
		List<BonusMonthDeatilVo> bonusList = findBonusMonthDeatilToDataBase(bonusVo);
		
		// 将金额财务专用格式
		StringUtil.fontFormatFinance(bonusList);
		
		// 设置成前台需要的数据
		Map<String, Object> responsedata=MapUtil.setQueryDataToMap(bonusList, Long.valueOf(getQueryTotalSize(bonusVo)));

		return responsedata;
	}
	/**
	 * 查询code数据
	 */
	@RequestMapping(value = "/findCodeTypeNames", method = RequestMethod.POST)
	@ValidationRequestUrl
	@ResponseBody
	public Object findCodeTypeNames() throws Throwable{
		List<List<?>> responsedata = findCodeTypeNamesToDataBase();
			
		return responsedata;
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
	private List<BonusMonthDeatilVo> findBonusMonthDeatilToDataBase(BonusMonthDeatilVo bonusVo)
			throws Exception {
		BonusReconcileDTO searchDto = BeanMapper.map(bonusVo, BonusReconcileDTO.class);
		List<BonusReconcileDTO> data = bonusService.getBonusMonthDeatil(searchDto);
		logger.info("=====查询完成======");
		return convertDtoToVo(data);
	}
	
	/**
     * 到数据库查询数据并返回总数
     * 
     * @throws SQLException
     */
    private int findBonusMonthDeatilCountToDataBase(BonusMonthDeatilVo bonusVo)
            throws Exception {
        BonusReconcileDTO searchDto = BeanMapper.map(bonusVo, BonusReconcileDTO.class);
        List<BonusReconcileDTO> data = bonusService.getBonusMonthDeatil(searchDto);
        logger.info("=====查询完成======");
        return data.size();
    }

	/**
	 * 数据统一转换成VO对象
	 */
	private List<BonusMonthDeatilVo> convertDtoToVo(List<BonusReconcileDTO> dtos) throws Exception{
		return BeanMapper.mapList(dtos, BonusMonthDeatilVo.class);
	}
	/**
	 * 查询总条数
	 * @param bonusVo
	 * @return
	 * @throws SQLException
	 */
	private int getQueryTotalSize(BonusMonthDeatilVo bonusVo) throws Exception {
		bonusVo.setBeginNo(null);
		bonusVo.setEndNo(null);
		return findBonusMonthDeatilCountToDataBase(bonusVo);
	}
	/*****
	 * 导出奖金月度明细
	 */
	@RequestMapping("/monthDeatilResult")
	public ResponseEntity<byte[]> exportMonthDeatilResult(
			HttpServletRequest request) throws Throwable {
		BonusMonthDeatilVo vo = AutoPojo.bindRequestParam(request, BonusMonthDeatilVo.class);
        if (vo.getDealerName() != null) {
            vo.setDealerName(URLDecoder.decode(
                    vo.getDealerName(), "UTF-8"));
        }
		BonusReconcileDTO bonusDto = BeanMapper.map(vo, BonusReconcileDTO.class);
		// 设置标题
		String[] header = { "序号", "客户代码", "客户名称", "SAP过账日期", "FOL处理日期",
				"变动前奖金余额", "变动金额","变动类型", "批次号", "变动后奖金金额" };
		// 查询数据
		List<BonusReconcileDTO> data = bonusService.getBonusMonthDeatil(bonusDto);
				

		// 转换为要导出的数据
		List<BonusMonthDeatilDRO> expList = BeanMapper.mapList(data,
				BonusMonthDeatilDRO.class);
		StringUtil.fontFormatFinance(expList);
		// 设置序号
		setLineNumber(expList);
		// 定义文件名称
		String fileName = "Bonus_monthDeatil" + DateUtil.date2str(new Date())
				+ ".xls";
		// 导出
		ByteArrayOutputStream bos = reconciliationManagementService
				.<BonusMonthDeatilDRO> exportXls4BillFiles(expList,
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

	private void setLineNumber(List<BonusMonthDeatilDRO> expList) {
		if (null != expList && expList.size() > 0) {
			for (int i = 0; i < expList.size(); i++) {
				BonusMonthDeatilDRO vo = expList.get(i);
				vo.setNum(i + 1);
			}
		}
	}
	
}
