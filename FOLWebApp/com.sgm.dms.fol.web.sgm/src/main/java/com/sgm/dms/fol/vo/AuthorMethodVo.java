package com.sgm.dms.fol.vo;

import com.sgm.dms.fol.common.api.domain.PageVo;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldRefIds;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldSign;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;

/**
 * 
 * @author wangfl
 * 方法权限Vo
 * @date 2016年3月31日
 */
@SGMValidationResource
public class AuthorMethodVo extends PageVo {
    /* 方法ID */
    @SGMValidationFieldRefIds(seq=1)
    private Integer methodId;
    /* 方法包名类名 */
    @SGMValidationFieldRefIds(seq=2)
    private String clazz;
    /* 方法名称 */
    private String method;
    /* URL */
    private String url;
    /* 说明 */
    private String remark;
    @SGMValidationFieldSign
    private String sign;
    public Integer getMethodId() {
        return methodId;
    }
    
    public void setMethodId(Integer methodId) {
        this.methodId = methodId;
    }
    
    public String getClazz() {
        return clazz;
    }
    
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
    
    public String getMethod() {
        return method;
    }
    
    public void setMethod(String method) {
        this.method = method;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    
    
    public String getRemark() {
        return remark;
    }

    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the sign
     */
    public String getSign() {
        return sign;
    }

    
    /**
     * @param sign the sign to set
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

}