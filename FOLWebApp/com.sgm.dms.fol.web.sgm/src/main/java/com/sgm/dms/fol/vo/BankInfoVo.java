package com.sgm.dms.fol.vo;

import com.sgm.dms.fol.common.api.domain.PageVo;

public class BankInfoVo extends PageVo{
    private String bankAbbr;
    private String bankChName;
    private String bankEnName;
    
    @Override
    public String toString() {
        return "BankInfoVo [bankAbbr=" + bankAbbr + ", bankChName=" + bankChName + ", bankEnName=" + bankEnName + "]";
    }
    
    public String getBankAbbr() {
        return bankAbbr;
    }
    
    public void setBankAbbr(String bankAbbr) {
        this.bankAbbr = bankAbbr;
    }
    
    public String getBankChName() {
        return bankChName;
    }
    
    public void setBankChName(String bankChName) {
        this.bankChName = bankChName;
    }
    
    public String getBankEnName() {
        return bankEnName;
    }
    
    public void setBankEnName(String bankEnName) {
        this.bankEnName = bankEnName;
    }
}
