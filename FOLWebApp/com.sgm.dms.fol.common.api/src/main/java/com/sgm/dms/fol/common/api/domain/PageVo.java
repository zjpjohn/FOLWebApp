package com.sgm.dms.fol.common.api.domain;

public class PageVo {
	@Deprecated
	private Integer pageNum;
	@Deprecated
    private Integer pageSize;
    private Integer beginNo;
    private Integer endNo;
    
//    public int getBeginNo() {
//    	beginNo = (getPageNum()-1)*getPageSize();
//		return beginNo;
//	}
//	public void setBeginNo(int beginNo) {
//		this.beginNo = beginNo;
//	}
//	public int getEndNo() {
//		endNo = getPageNum()*getPageSize()+1;
//		return endNo;
//	}
//	public void setEndNo(int endNo) {
//		this.endNo = endNo;
//	}
//	public int getPageNum() {
//		return pageNum;
//	}
//	public void setPageNum(int pageNum) {
//		this.pageNum = pageNum;
//	}
//	public int getPageSize() {
//		return pageSize;
//	}
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
    
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
	@Deprecated
	public Integer getPageNum() {
		return pageNum;
	}
	@Deprecated
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	@Deprecated
	public Integer getPageSize() {
		return pageSize;
	}
	@Deprecated
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
