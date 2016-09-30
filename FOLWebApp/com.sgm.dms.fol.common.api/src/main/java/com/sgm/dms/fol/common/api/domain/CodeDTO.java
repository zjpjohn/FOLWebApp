/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.api
*
* @File name : CodeDTO.java
*
* @Author : shenweiwei
*
* @Date : 2015年10月15日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年10月15日    shenweiwei    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.common.api.domain;

import org.springframework.beans.factory.annotation.Value;

/*
 *
 * @author shenweiwei
 * TODO description
 * @date 2015年10月15日
 */

public class CodeDTO extends BaseDTO{
    private Long codeId;
    private String type;
    private String typeName;
    private String codeEnDesc;
    private String codeCnDesc;
    private Integer num;
    private Long status;
    private String code;
    private String enCode;
    private Integer beginNo;
    private Integer endNo;
    private String cDate;
    private String uDate;
    
    @Value(value="false")
    private boolean selected;
    
    private String remark;
    
    /**
     * @return the codeId
     */
    public Long getCodeId() {
        return codeId;
    }
    /**
     * @param codeId the codeId to set
     */
    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return the typeName
     */
    public String getTypeName() {
        return typeName;
    }
    /**
     * @param typeName the typeName to set
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    /**
     * @return the codeEnDesc
     */
    public String getCodeEnDesc() {
        return codeEnDesc;
    }
    /**
     * @param codeEnDesc the codeEnDesc to set
     */
    public void setCodeEnDesc(String codeEnDesc) {
        this.codeEnDesc = codeEnDesc;
    }
    /**
     * @return the codeCnDesc
     */
    public String getCodeCnDesc() {
        return codeCnDesc;
    }
    /**
     * @param codeCnDesc the codeCnDesc to set
     */
    public void setCodeCnDesc(String codeCnDesc) {
        this.codeCnDesc = codeCnDesc;
    }
    /**
     * @return the num
     */
    public Integer getNum() {
        return num;
    }
    /**
     * @param num the num to set
     */
    public void setNum(Integer num) {
        this.num = num;
    }
    /**
     * @return the status
     */
    public Long getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(Long status) {
        this.status = status;
    }
    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }
    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
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
	public String getcDate() {
		return cDate;
	}
	public void setcDate(String cDate) {
		this.cDate = cDate;
	}
	public String getuDate() {
		return uDate;
	}
	public void setuDate(String uDate) {
		this.uDate = uDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEnCode() {
		return enCode;
	}
	public void setEnCode(String enCode) {
		this.enCode = enCode;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
