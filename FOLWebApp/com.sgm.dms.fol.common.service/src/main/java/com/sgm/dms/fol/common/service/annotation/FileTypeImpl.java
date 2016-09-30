
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.service
 *
 * @File name : FileTypeImpl.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年3月21日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年3月21日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.common.service.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;


/**
 * @author wangfl
 * TODO description
 * @date 2016年3月21日
 */

public class FileTypeImpl implements ConstraintValidator<FileType, String> {
    
    private String fileType;

    /**
     * 
     * @author wangfl
     * @date 2016年3月21日
     * @param fileType
     * (non-Javadoc)
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
     */

    @Override
    public void initialize(FileType fileType) {
        this.fileType = fileType.fileType();
    }

    /**
     * 
     * @author wangfl
     * @date 2016年3月21日
     * @param validStr
     * @param constraintContext
     * @return
     * (non-Javadoc)
     * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
     */

    @Override
    public boolean isValid(String validStr, ConstraintValidatorContext constraintContext) {
        if(StringUtils.isBlank(validStr)) 
            return false;//要验证的文件名为空，false；
        if(StringUtils.isBlank(fileType)) 
            return true;//无验证白名单，true；
        
        String realFileType = validStr.substring(validStr.lastIndexOf(".")+1);//文件格式
        String[] fileTypeArray = fileType.split(",");//文件格式白名单列表
        for (String fileTypeTemp : fileTypeArray) {
            if(realFileType.equalsIgnoreCase(fileTypeTemp)) return true;
        }
        
        return false;
    }

}
