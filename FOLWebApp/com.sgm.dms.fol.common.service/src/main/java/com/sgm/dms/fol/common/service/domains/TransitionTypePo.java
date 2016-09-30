/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.service
*
* @File name : TransitionTypePo.java
*
* @Author : shenweiwei
*
* @Date : 2015年10月22日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年10月22日    shenweiwei    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.common.service.domains;



/*
 *
 * @author shenweiwei
 * TODO description
 * @date 2015年10月22日
 */

public class TransitionTypePo extends BasePo{
    private Long referTypeId;  //外部服务调用类型号
    private String referTypeName; //服务调用类型
    private String referTypeClassify; //服务调用类型分类
    private Integer isPublic; //服务调用类型是否外部使用0:内部;1:外部
    private Long folTypeId; //内部服务调用类型号
    private String referTypeSource; //服务调用类型源系统
    private String sgmReferUrl;
    private String dealerReferUrl;
    private Integer businessCode;// 业务分类
    private String businessCodeName;//业务分类名
    
    /**
     * @return the referTypeId
     */
    public Long getReferTypeId() {
        return referTypeId;
    }
    
    /**
     * @param referTypeId the referTypeId to set
     */
    public void setReferTypeId(Long referTypeId) {
        this.referTypeId = referTypeId;
    }
    
    /**
     * @return the referTypeName
     */
    public String getReferTypeName() {
        return referTypeName;
    }
    
    /**
     * @param referTypeName the referTypeName to set
     */
    public void setReferTypeName(String referTypeName) {
        this.referTypeName = referTypeName;
    }
    
    /**
     * @return the referTypeClassify
     */
    public String getReferTypeClassify() {
        return referTypeClassify;
    }
    
    /**
     * @param referTypeClassify the referTypeClassify to set
     */
    public void setReferTypeClassify(String referTypeClassify) {
        this.referTypeClassify = referTypeClassify;
    }
    
    /**
     * @return the isPublic
     */
    public Integer getIsPublic() {
        return isPublic;
    }
    
    /**
     * @param isPublic the isPublic to set
     */
    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }
    
    /**
     * @return the folTypeId
     */
    public Long getFolTypeId() {
        return folTypeId;
    }
    
    /**
     * @param folTypeId the folTypeId to set
     */
    public void setFolTypeId(Long folTypeId) {
        this.folTypeId = folTypeId;
    }
    
    /**
     * @return the referTypeSource
     */
    public String getReferTypeSource() {
        return referTypeSource;
    }
    
    /**
     * @param referTypeSource the referTypeSource to set
     */
    public void setReferTypeSource(String referTypeSource) {
        this.referTypeSource = referTypeSource;
    }

    /**
     * @return the businessCode
     */
    public Integer getBusinessCode() {
        return businessCode;
    }

    /**
     * @param businessCode the businessCode to set
     */
    public void setBusinessCode(Integer businessCode) {
        this.businessCode = businessCode;
    }

    /**
     * @return the sgmReferUrl
     */
    public String getSgmReferUrl() {
        return sgmReferUrl;
    }

    /**
     * @param sgmReferUrl the sgmReferUrl to set
     */
    public void setSgmReferUrl(String sgmReferUrl) {
        this.sgmReferUrl = sgmReferUrl;
    }

    /**
     * @return the dealerReferUrl
     */
    public String getDealerReferUrl() {
        return dealerReferUrl;
    }

    /**
     * @param dealerReferUrl the dealerReferUrl to set
     */
    public void setDealerReferUrl(String dealerReferUrl) {
        this.dealerReferUrl = dealerReferUrl;
    }

    /**
     * @return the businessCodeName
     */
    public String getBusinessCodeName() {
        return businessCodeName;
    }

    /**
     * @param businessCodeName the businessCodeName to set
     */
    public void setBusinessCodeName(String businessCodeName) {
        this.businessCodeName = businessCodeName;
    }
    
    
}
