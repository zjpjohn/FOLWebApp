/**
* @ClassName: demoAmountVo
* @Description: TODO
* @author: a bao
* @date: 2015年10月9日 上午9:42:56
* 
* 
*/
package com.sgm.dms.fol.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.sgm.dms.fol.common.api.domain.PageVo;


public class ReserveVo  extends PageVo  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//主键
	private String sapCode;
	
	//名称
	private String dealerName;
		
	//冻结资金 
	private BigDecimal frozenAmount;
		
	//总资金
	private BigDecimal totalAmount;
		
	//渠道类型
	private String dealerType;
	
	//渠道类型名称
	private String dealerTypeName;
		
	//储备金类型
	private int reserveType;
	
	//储备金类型名称
    private String reserveTypeName;
		
	//可用资金
	private BigDecimal availAmount;
	
	//变化参考（bullngno或者其它）
	private String referCode;
	
	//变化类型（扣款，打款，冻结，解冻或系统问题补偿等）
	private String referType;
	
	//变动金额
	private BigDecimal changeAmount;
	
	//变动日期
	private String changeTime;
	
	//变动前可用余额
	private BigDecimal beforeAvailAmount;
	
	//变动后余额
    private BigDecimal afterChangeAmount;
	
	//开始日期
	private String startTime;
	
	//结束日期
	private String endTime;
	
	//储备金本月余额
    private BigDecimal reserveAmount;
    
    //储备金上月月余额
    private BigDecimal reserveLastAmount;
    
    //储备金变动增加的余额
    private BigDecimal reserveChangeAddAmount;
    
    //储备金变动减少的余额
    private BigDecimal reserveChangeSubtractAmount;
    
    //SAP储备金本月余额
    private BigDecimal sapReserveAmount;
    
    //SAP储备金上月月余额
    private BigDecimal sapReserveLastAmount;
    
    //余额是否有差异
    private String difference;
    
    //余额差异
    private BigDecimal differenceAmount;
    
    //开始的客户代码
    private String startSapCode;
    
    //结束的客户代码
    private String endSapCode;
	
	//摘要
	private String remark;
	
	//业务码
    private int businessCode;
    
 	//查询年份
    private Integer year;
    
    //查询月份
    private Integer month;

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
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

	public String getDealerType() {
		return dealerType;
	}

	public void setDealerType(String dealerType) {
		this.dealerType = dealerType;
	}

	public int getReserveType() {
		return reserveType;
	}

	public void setReserveType(int reserveType) {
		this.reserveType = reserveType;
	}

	public BigDecimal getAvailAmount() {
		return availAmount;
	}

	public void setAvailAmount(BigDecimal availAmount) {
		this.availAmount = availAmount;
	}

    /**
     * @return the referCode
     */
    public String getReferCode() {
        return referCode;
    }

    /**
     * @param referCode the referCode to set
     */
    public void setReferCode(String referCode) {
        this.referCode = referCode;
    }

    /**
     * @return the changeAmount
     */
    public BigDecimal getChangeAmount() {
        return changeAmount;
    }

    /**
     * @param changeAmount the changeAmount to set
     */
    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }

    /**
     * @return the referType
     */
    public String getReferType() {
        return referType;
    }

    /**
     * @param referType the referType to set
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
     * @param changeTime the changeTime to set
     */
    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    /**
     * @return the beforeAvailAmount
     */
    public BigDecimal getBeforeAvailAmount() {
        return beforeAvailAmount;
    }

    /**
     * @param beforeAvailAmount the beforeAvailAmount to set
     */
    public void setBeforeAvailAmount(BigDecimal beforeAvailAmount) {
        this.beforeAvailAmount = beforeAvailAmount;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
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
     * @param startTime the startTime to set
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
     * @param endTime the endTime to set
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
     * @param sapCode the sapCode to set
     */
    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }

    
    /**
     * @return the reserveAmount
     */
    public BigDecimal getReserveAmount() {
        return reserveAmount;
    }

    
    /**
     * @param reserveAmount the reserveAmount to set
     */
    public void setReserveAmount(BigDecimal reserveAmount) {
        this.reserveAmount = reserveAmount;
    }

    
    /**
     * @return the reserveLastAmount
     */
    public BigDecimal getReserveLastAmount() {
        return reserveLastAmount;
    }

    
    /**
     * @param reserveLastAmount the reserveLastAmount to set
     */
    public void setReserveLastAmount(BigDecimal reserveLastAmount) {
        this.reserveLastAmount = reserveLastAmount;
    }

    
    /**
     * @return the reserveChangeAddAmount
     */
    public BigDecimal getReserveChangeAddAmount() {
        return reserveChangeAddAmount;
    }

    
    /**
     * @param reserveChangeAddAmount the reserveChangeAddAmount to set
     */
    public void setReserveChangeAddAmount(BigDecimal reserveChangeAddAmount) {
        this.reserveChangeAddAmount = reserveChangeAddAmount;
    }

    
    /**
     * @return the reserveChangeSubtractAmount
     */
    public BigDecimal getReserveChangeSubtractAmount() {
        return reserveChangeSubtractAmount;
    }

    
    /**
     * @param reserveChangeSubtractAmount the reserveChangeSubtractAmount to set
     */
    public void setReserveChangeSubtractAmount(BigDecimal reserveChangeSubtractAmount) {
        this.reserveChangeSubtractAmount = reserveChangeSubtractAmount;
    }

    
    /**
     * @return the sapReserveAmount
     */
    public BigDecimal getSapReserveAmount() {
        return sapReserveAmount;
    }

    
    /**
     * @param sapReserveAmount the sapReserveAmount to set
     */
    public void setSapReserveAmount(BigDecimal sapReserveAmount) {
        this.sapReserveAmount = sapReserveAmount;
    }

    
    /**
     * @return the sapReserveLastAmount
     */
    public BigDecimal getSapReserveLastAmount() {
        return sapReserveLastAmount;
    }

    
    /**
     * @param sapReserveLastAmount the sapReserveLastAmount to set
     */
    public void setSapReserveLastAmount(BigDecimal sapReserveLastAmount) {
        this.sapReserveLastAmount = sapReserveLastAmount;
    }

    
    /**
     * @return the difference
     */
    public String getDifference() {
        return difference;
    }

    
    /**
     * @param difference the difference to set
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
     * @param startSapCode the startSapCode to set
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
     * @param endSapCode the endSapCode to set
     */
    public void setEndSapCode(String endSapCode) {
        this.endSapCode = endSapCode;
    }

    /**
     * @return the differenceAmount
     */
    public BigDecimal getDifferenceAmount() {
        return differenceAmount;
    }

    /**
     * @param differenceAmount the differenceAmount to set
     */
    public void setDifferenceAmount(BigDecimal differenceAmount) {
        this.differenceAmount = differenceAmount;
    }

    /**
     * @return the dealerTypeName
     */
    public String getDealerTypeName() {
        return dealerTypeName;
    }

    /**
     * @param dealerTypeName the dealerTypeName to set
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
     * @param reserveTypeName the reserveTypeName to set
     */
    public void setReserveTypeName(String reserveTypeName) {
        this.reserveTypeName = reserveTypeName;
    }

    /**
     * @return the businessCode
     */
    public int getBusinessCode() {
        return businessCode;
    }

    /**
     * @param businessCode the businessCode to set
     */
    public void setBusinessCode(int businessCode) {
        this.businessCode = businessCode;
    }

	public BigDecimal getAfterChangeAmount() {
		return afterChangeAmount;
	}

	public void setAfterChangeAmount(BigDecimal afterChangeAmount) {
		this.afterChangeAmount = afterChangeAmount;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
	
} 
