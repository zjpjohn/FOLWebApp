package com.sgm.dms.fol.vo;
/****
 * 
*奖金重处理vo
* @author zhang bao
* TODO description
* @date 2015年12月29日
 */
public class BonusIssueAgainExecuteVo {
	private Integer dealerType;//渠道类型
	private Integer bonusType;//奖金类型
	private String batchNo;//批次号
    private Integer serv;//销售公司
    private String sapCode;//客户代码
    private String beginDate;//起始处理日期
    private String endDate;//结束处理日期
    private Integer beginNo;
    private Integer endNo;
	public Integer getDealerType() {
		return dealerType;
	}
	public void setDealerType(Integer dealerType) {
		this.dealerType = dealerType;
	}
	public Integer getBonusType() {
		return bonusType;
	}
	public void setBonusType(Integer bonusType) {
		this.bonusType = bonusType;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	public Integer getServ() {
		return serv;
	}
	public void setServ(Integer serv) {
		this.serv = serv;
	}
	public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}

    public String getBeginDate() {
        return beginDate;
    }
    
    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }
    
    public String getEndDate() {
        return endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public Integer getBeginNo() {
		return beginNo;
	}
	public void setBeginNo(Integer beginNo) {
		this.beginNo = beginNo;
	}
	public Integer getEndNo() {
		return endNo;
	}
	public void setEndNo(Integer endNo) {
		this.endNo = endNo;
	}
    
}
