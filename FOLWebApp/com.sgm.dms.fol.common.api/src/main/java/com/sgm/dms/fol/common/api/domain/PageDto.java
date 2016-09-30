package com.sgm.dms.fol.common.api.domain;


public class PageDto {
	private int pageNum;
    private int pageSize;
    private int beginNo;
    private int endNo;
    
    public int getBeginNo() {
		return beginNo;
	}
	public void setBeginNo(int beginNo) {
		this.beginNo = beginNo;
	}
	public int getEndNo() {
		return endNo;
	}
	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
