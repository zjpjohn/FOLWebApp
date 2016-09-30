
package com.sgm.dms.fol.common.service.domains;

import java.util.Date;

public class BasePo {
	private Date createDate;
	private long createBy;
	private Date updateDate;
	private long updateBy;

	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(long createBy) {
		this.createBy = createBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(long updateBy) {
		this.updateBy = updateBy;
	}
	
	public void setCommonUpdateData(long userid){
        Date now=new Date();
        setUpdateBy(userid);
        setUpdateDate(now);
        
    }
    public void setCommonAddData(long userid){
        Date now=new Date();
        setCreateBy(userid);
        setUpdateBy(userid);
        setCreateDate(now);
        setUpdateDate(now);
        
    }
}