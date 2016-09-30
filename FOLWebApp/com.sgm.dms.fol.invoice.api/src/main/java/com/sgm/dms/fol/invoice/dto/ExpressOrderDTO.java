package com.sgm.dms.fol.invoice.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.sgm.dms.fol.common.api.domain.BaseDTO;

public class ExpressOrderDTO extends BaseDTO{
    private String expressNo;               // 快递单号               

    private Short expressUnit;              // 快递公司              

    private Date changeTime;                // 最新时间              

    private String changeMsg;               // 最新信息              

    private String postUser;                // 快递人员            

    private BigDecimal postUserPhone;       // 快递人员电话      

    private Short expressStatus;            // 快递状态             

    private Short valid;                    // 有效性                

    private String remark;                  // 备注                  



    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo == null ? null : expressNo.trim();
    }

    public Short getExpressUnit() {
        return expressUnit;
    }

    public void setExpressUnit(Short expressUnit) {
        this.expressUnit = expressUnit;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getChangeMsg() {
        return changeMsg;
    }

    public void setChangeMsg(String changeMsg) {
        this.changeMsg = changeMsg == null ? null : changeMsg.trim();
    }

    public String getPostUser() {
        return postUser;
    }

    public void setPostUser(String postUser) {
        this.postUser = postUser == null ? null : postUser.trim();
    }

    public BigDecimal getPostUserPhone() {
        return postUserPhone;
    }

    public void setPostUserPhone(BigDecimal postUserPhone) {
        this.postUserPhone = postUserPhone;
    }

    public Short getExpressStatus() {
        return expressStatus;
    }

    public void setExpressStatus(Short expressStatus) {
        this.expressStatus = expressStatus;
    }

    public Short getValid() {
        return valid;
    }

    public void setValid(Short valid) {
        this.valid = valid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

}