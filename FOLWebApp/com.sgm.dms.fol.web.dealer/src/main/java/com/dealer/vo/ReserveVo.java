/**
 * @ClassName: demoAmountVo
 * @Description: TODO
 * @author: a bao
 * @date: 2015年10月9日 上午9:42:56
 * 
 * 
 */
package com.dealer.vo;

import java.io.Serializable;

import com.sgm.dms.fol.common.api.domain.PageVo;

public class ReserveVo extends PageVo implements Serializable {

	private static final long serialVersionUID = 1L;

	// 主键
	private String sapCode;

	// 名称
	private String dealerName;

	// 冻结资金
	private String frozenAmount;

	// 总资金
	private String totalAmount;

	// 渠道类型
	private String dealerType;

	// 渠道类型名称
	private String dealerTypeName;

	// 储备金类型
	private Integer reserveType;

	// 储备金类型名称
	private String reserveTypeName;

	// 可用资金
	private String availAmount;

	// 变化参考（bullngno或者其它）
	private String referCode;

	// 变化类型（扣款，打款，冻结，解冻或系统问题补偿等）
	private String referType;

	// 变动金额
	private String changeAmount;

	// 变动日期
	private String changeTime;

	// 变动前可用余额
	private String beforeAvailAmount;

	// 变动后余额
	private String afterChangeAmount;

	// 开始日期
	private String startTime;

	// 结束日期
	private String endTime;

	// 储备金本月余额
	private String reserveAmount;

	// 储备金上月月余额
	private String reserveLastAmount;

	// 储备金变动增加的余额
	private String reserveChangeAddAmount;

	// 储备金变动减少的余额
	private String reserveChangeSubtractAmount;

	// SAP储备金本月余额
	private String sapReserveAmount;

	// SAP储备金上月月余额
	private String sapReserveLastAmount;

	// 余额是否有差异
	private String difference;

	// 余额差异
	private String differenceAmount;

	// 开始的客户代码
	private String startSapCode;

	// 结束的客户代码
	private String endSapCode;

	// 摘要
	private String remark;

	// 业务码
	private Integer businessCode;
	private String token;
	private Integer num;

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getFrozenAmount() {
		return frozenAmount;
	}

	public void setFrozenAmount(String frozenAmount) {
		this.frozenAmount = frozenAmount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getDealerType() {
		return dealerType;
	}

	public void setDealerType(String dealerType) {
		this.dealerType = dealerType;
	}

	public Integer getReserveType() {
		return reserveType;
	}

	public void setReserveType(Integer reserveType) {
		this.reserveType = reserveType;
	}

	public String getAvailAmount() {
		return availAmount;
	}

	public void setAvailAmount(String availAmount) {
		this.availAmount = availAmount;
	}

	/**
	 * @return the referCode
	 */
	public String getReferCode() {
		return referCode;
	}

	/**
	 * @param referCode
	 *            the referCode to set
	 */
	public void setReferCode(String referCode) {
		this.referCode = referCode;
	}

	/**
	 * @return the changeAmount
	 */
	public String getChangeAmount() {
		return changeAmount;
	}

	/**
	 * @param changeAmount
	 *            the changeAmount to set
	 */
	public void setChangeAmount(String changeAmount) {
		this.changeAmount = changeAmount;
	}

	/**
	 * @return the referType
	 */
	public String getReferType() {
		return referType;
	}

	/**
	 * @param referType
	 *            the referType to set
	 */
	public void setReferType(String referType) {
		this.referType = referType;
	}

	/**
	 * @return the changeTime
	 */
	public String getChangeTime() {
		return changeTime;
	}

	/**
	 * @param changeTime
	 *            the changeTime to set
	 */
	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	/**
	 * @return the beforeAvailAmount
	 */
	public String getBeforeAvailAmount() {
		return beforeAvailAmount;
	}

	/**
	 * @param beforeAvailAmount
	 *            the beforeAvailAmount to set
	 */
	public void setBeforeAvailAmount(String beforeAvailAmount) {
		this.beforeAvailAmount = beforeAvailAmount;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the sapCode
	 */
	public String getSapCode() {
		return sapCode;
	}

	/**
	 * @param sapCode
	 *            the sapCode to set
	 */
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}

	/**
	 * @return the reserveAmount
	 */
	public String getReserveAmount() {
		return reserveAmount;
	}

	/**
	 * @param reserveAmount
	 *            the reserveAmount to set
	 */
	public void setReserveAmount(String reserveAmount) {
		this.reserveAmount = reserveAmount;
	}

	/**
	 * @return the reserveLastAmount
	 */
	public String getReserveLastAmount() {
		return reserveLastAmount;
	}

	/**
	 * @param reserveLastAmount
	 *            the reserveLastAmount to set
	 */
	public void setReserveLastAmount(String reserveLastAmount) {
		this.reserveLastAmount = reserveLastAmount;
	}

	/**
	 * @return the reserveChangeAddAmount
	 */
	public String getReserveChangeAddAmount() {
		return reserveChangeAddAmount;
	}

	/**
	 * @param reserveChangeAddAmount
	 *            the reserveChangeAddAmount to set
	 */
	public void setReserveChangeAddAmount(String reserveChangeAddAmount) {
		this.reserveChangeAddAmount = reserveChangeAddAmount;
	}

	/**
	 * @return the reserveChangeSubtractAmount
	 */
	public String getReserveChangeSubtractAmount() {
		return reserveChangeSubtractAmount;
	}

	/**
	 * @param reserveChangeSubtractAmount
	 *            the reserveChangeSubtractAmount to set
	 */
	public void setReserveChangeSubtractAmount(
			String reserveChangeSubtractAmount) {
		this.reserveChangeSubtractAmount = reserveChangeSubtractAmount;
	}

	/**
	 * @return the sapReserveAmount
	 */
	public String getSapReserveAmount() {
		return sapReserveAmount;
	}

	/**
	 * @param sapReserveAmount
	 *            the sapReserveAmount to set
	 */
	public void setSapReserveAmount(String sapReserveAmount) {
		this.sapReserveAmount = sapReserveAmount;
	}

	/**
	 * @return the sapReserveLastAmount
	 */
	public String getSapReserveLastAmount() {
		return sapReserveLastAmount;
	}

	/**
	 * @param sapReserveLastAmount
	 *            the sapReserveLastAmount to set
	 */
	public void setSapReserveLastAmount(String sapReserveLastAmount) {
		this.sapReserveLastAmount = sapReserveLastAmount;
	}

	/**
	 * @return the difference
	 */
	public String getDifference() {
		return difference;
	}

	/**
	 * @param difference
	 *            the difference to set
	 */
	public void setDifference(String difference) {
		this.difference = difference;
	}

	/**
	 * @return the startSapCode
	 */
	public String getStartSapCode() {
		return startSapCode;
	}

	/**
	 * @param startSapCode
	 *            the startSapCode to set
	 */
	public void setStartSapCode(String startSapCode) {
		this.startSapCode = startSapCode;
	}

	/**
	 * @return the endSapCode
	 */
	public String getEndSapCode() {
		return endSapCode;
	}

	/**
	 * @param endSapCode
	 *            the endSapCode to set
	 */
	public void setEndSapCode(String endSapCode) {
		this.endSapCode = endSapCode;
	}

	/**
	 * @return the differenceAmount
	 */
	public String getDifferenceAmount() {
		return differenceAmount;
	}

	/**
	 * @param differenceAmount
	 *            the differenceAmount to set
	 */
	public void setDifferenceAmount(String differenceAmount) {
		this.differenceAmount = differenceAmount;
	}

	/**
	 * @return the dealerTypeName
	 */
	public String getDealerTypeName() {
		return dealerTypeName;
	}

	/**
	 * @param dealerTypeName
	 *            the dealerTypeName to set
	 */
	public void setDealerTypeName(String dealerTypeName) {
		this.dealerTypeName = dealerTypeName;
	}

	/**
	 * @return the reserveTypeName
	 */
	public String getReserveTypeName() {
		return reserveTypeName;
	}

	/**
	 * @param reserveTypeName
	 *            the reserveTypeName to set
	 */
	public void setReserveTypeName(String reserveTypeName) {
		this.reserveTypeName = reserveTypeName;
	}

	/**
	 * @return the businessCode
	 */
	public Integer getBusinessCode() {
		return businessCode;
	}

	/**
	 * @param businessCode
	 *            the businessCode to set
	 */
	public void setBusinessCode(Integer businessCode) {
		this.businessCode = businessCode;
	}

	public String getAfterChangeAmount() {
		return afterChangeAmount;
	}

	public void setAfterChangeAmount(String afterChangeAmount) {
		this.afterChangeAmount = afterChangeAmount;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
