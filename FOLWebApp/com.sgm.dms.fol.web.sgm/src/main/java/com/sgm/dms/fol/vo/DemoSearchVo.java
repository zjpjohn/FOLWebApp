/**
* @ClassName: MenuDTO
* @Description: TODO
* @author: ChenChong
* @date: 2015年6月5日 上午9:42:56
* 
* 
*/
package com.sgm.dms.fol.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgm.dms.fol.common.api.domain.PageVo;

public class DemoSearchVo extends PageVo implements Serializable {

	private static final long serialVersionUID = 1L;
	//代码
	private String code;
	//名称
	private String name;
	//是否有效
	private int isValid;
	//创建开始日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createDateBegin;
	//创建结束日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createDateEnd;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIsValid() {
		return isValid;
	}
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}
	public Date getCreateDateBegin() {
		return createDateBegin;
	}
	public void setCreateDateBegin(Date createDateBegin) {
		this.createDateBegin = createDateBegin;
	}
	public Date getCreateDateEnd() {
		return createDateEnd;
	}
	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}
}
