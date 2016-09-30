/**
* @ClassName: ReserveAmountPo
* @Description: TODO
* @author: A BAO
* @date: 2015年10月9日 上午9:42:56
* 
* 
*/
package com.sgm.dms.fol.reserve.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.sgm.dms.fol.common.api.domain.PageVo;


public class ReservePo  extends PageVo  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//主键
	private String dealerCode;
	
	//名称
	private String dealerName;
	//更新日期
	private Date updateDate;
	
	//TODO
	private  Integer  updateBy;
	
	//创建日期
	private Date createDate;
	
	//TODO
	private Integer createBy;
	
	//最后更新时间
	private Date lastChangeTime;
	
	//冻结资金 
	private BigDecimal frozenAmount;
	
	//总资金
	private BigDecimal totalAmount;
	
	//渠道类型
	private String dealerType;
	
	//储备金类型
	private String reserveType;
	
	//可用资金
	private BigDecimal availAmount;
	
	public BigDecimal getAvailAmount() {
		return availAmount;
	}

	public void setAvailAmount(BigDecimal availAmount) {
		this.availAmount = availAmount;
	}

	public String getDealerType() {
		return dealerType;
	}

	public void setDealerType(String dealerType) {
		this.dealerType = dealerType;
	}

	public String getReserveType() {
		return reserveType;
	}

	public void setReserveType(String reserveType) {
		this.reserveType = reserveType;
	}
	
	public String getDealerCode() {
		return dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getLastChangeTime() {
		return lastChangeTime;
	}

	public void setLastChangeTime(Date lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}

	public BigDecimal getFrozenAmount() {
		return frozenAmount;
	}

	public void setFrozenAmount(BigDecimal frozenAmount) {
		this.frozenAmount = frozenAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	
	
	
	

} 
