package com.sgm.dms.fol.vo;

import java.math.BigDecimal;

import com.sgm.dms.fol.common.api.domain.PageVo;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldRefIds;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldSign;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;
/**
 * 票据贴息年化利率Vo
 * @author lujinglei
 *
 */
@SGMValidationResource
public class TicketInterestRateVo extends PageVo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer num;	//序号
	@SGMValidationFieldRefIds(seq=1)
    private BigDecimal annualInterestRate;	//年利率
    private BigDecimal discountRate;	//税率
    private BigDecimal addedTaxRate;	//增值税率
    private Integer status;		//是否有效(1代表有效，2代表无效)
    private String updateBy;	//修改人
    private String auditMsg;	//审核意见
//	@SGMValidationFieldRefIds(seq=2)
    private BigDecimal currentInterestRate;//当前利率
//	@SGMValidationFieldRefIds(seq=3)
    private BigDecimal unAuditRateCur; //当前待审核年利率
//	@SGMValidationFieldRefIds(seq=4)
    private BigDecimal historyInterestRate;//历史利率
    private String auditStatusName; //审批状态名称
//	@SGMValidationFieldRefIds(seq=5)
    private String auditStatus; //审批状态
    private String auditDate;//审核时间
	@SGMValidationFieldSign
    private String sign;
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public BigDecimal getAnnualInterestRate() {
		return annualInterestRate;
	}
	public void setAnnualInterestRate(BigDecimal annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
	public BigDecimal getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}
	public BigDecimal getAddedTaxRate() {
		return addedTaxRate;
	}
	public void setAddedTaxRate(BigDecimal addedTaxRate) {
		this.addedTaxRate = addedTaxRate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getAuditMsg() {
		return auditMsg;
	}
	public void setAuditMsg(String auditMsg) {
		this.auditMsg = auditMsg;
	}
	public BigDecimal getCurrentInterestRate() {
		return currentInterestRate;
	}
	public void setCurrentInterestRate(BigDecimal currentInterestRate) {
		this.currentInterestRate = currentInterestRate;
	}
	public BigDecimal getUnAuditRateCur() {
		return unAuditRateCur;
	}
	public void setUnAuditRateCur(BigDecimal unAuditRateCur) {
		this.unAuditRateCur = unAuditRateCur;
	}
	public BigDecimal getHistoryInterestRate() {
		return historyInterestRate;
	}
	public void setHistoryInterestRate(BigDecimal historyInterestRate) {
		this.historyInterestRate = historyInterestRate;
	}
	public String getAuditStatusName() {
		return auditStatusName;
	}
	public void setAuditStatusName(String auditStatusName) {
		this.auditStatusName = auditStatusName;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}

}
