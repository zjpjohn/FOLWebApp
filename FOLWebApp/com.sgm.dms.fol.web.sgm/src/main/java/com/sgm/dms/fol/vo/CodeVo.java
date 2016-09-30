package com.sgm.dms.fol.vo;

import java.io.Serializable;

import com.sgm.dms.fol.common.api.domain.PageVo;

public class CodeVo extends PageVo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long codeId;
	 private String type;
	 private String typeName;
	 private String codeEnDesc;
	 private String codeCnDesc;
	 private Integer num;
	 private Long status;
	 private String code;
	 private String remark;
	 
	
	public Long getCodeId() {
		return codeId;
	}
	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getCodeEnDesc() {
		return codeEnDesc;
	}
	public void setCodeEnDesc(String codeEnDesc) {
		this.codeEnDesc = codeEnDesc;
	}
	public String getCodeCnDesc() {
		return codeCnDesc;
	}
	public void setCodeCnDesc(String codeCnDesc) {
		this.codeCnDesc = codeCnDesc;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
	 
	 
}
