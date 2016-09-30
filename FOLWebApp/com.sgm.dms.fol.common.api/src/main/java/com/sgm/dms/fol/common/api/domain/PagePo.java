package com.sgm.dms.fol.common.api.domain;

import java.util.HashMap;
import java.util.List;

public class PagePo<E> {
	
	private int page;
	private int pageSize;
	private List<E> list;
	private long count;
	private HashMap<String, Object> where;//
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<E> getList() {
		return list;
	}
	public void setList(List<E> list) {
		this.list = list;
	}
	public HashMap<String, Object> getWhere() {
		return where;
	}
	public void setWhere(HashMap<String, Object> where) {
		this.where = where;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
}
