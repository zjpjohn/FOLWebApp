package com.sgm.dms.fol.common.service.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 
 * @author wangfl
 * 根据文件名验证文件类型是否匹配
 * @date 2016年3月21日
 */
@Target({ElementType.FIELD}) 
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy = com.sgm.dms.fol.common.service.annotation.FileTypeImpl.class)  
public @interface FileType {
    /** 文件类型白名单，多个文件类型用逗号分隔 */
    public String fileType();
    
    /** 验证结果信息 */
    public String message() default "文件类型不对";
    
    Class<?>[] groups() default {};  
    
    Class<? extends Payload>[] payload() default {}; 
}
