/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.api
*
* @File name : VoNotValidException.java
*
* @Author : DELL
*
* @Date : 2016年3月23日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年3月23日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.common.api.exception;


/*
*
* @author DELL
* TODO description
* @date 2016年3月23日
*/

public class VoNotValidException extends RuntimeException{

    private static final long serialVersionUID = 991405809100548543L;
    
    public VoNotValidException(String msg) {
        super(msg);
    }
    
    public VoNotValidException(Exception ex){
        super(ex.getMessage());
    }

}
