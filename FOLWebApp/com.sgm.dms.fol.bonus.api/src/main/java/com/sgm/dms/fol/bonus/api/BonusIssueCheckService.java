package com.sgm.dms.fol.bonus.api;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.sgm.dms.fol.bonus.dto.RequestPayBonus;
import com.sgm.dms.fol.bonus.dto.ResponsePayBonus;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;

/**
 * 奖金发放CheckService
 * @author zhang bao
 * TODO description
 * @date 2016年1月4日
 */
public interface BonusIssueCheckService {
	
	/**
	 * 调用SAP检查service
	 */
	public List<ResponsePayBonus> bonusIssueCheckData(List<RequestPayBonus> req) throws ServiceAppException;

	/**
	 * 上传奖金文件前check
	 */
	public void beforeUploadBonusCheckData(BindingResult validResult) throws ServiceAppException;
	
}
