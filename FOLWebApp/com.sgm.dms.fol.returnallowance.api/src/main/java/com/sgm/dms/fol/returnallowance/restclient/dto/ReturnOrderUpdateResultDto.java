/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : returnallowance.api
*
* @File name : ReturnOrderUpdateResultDto.java
*
* @Author : st78sr
*
* @Date : 2016年8月18日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年8月18日    st78sr    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.returnallowance.restclient.dto;


/*
*
* @author st78sr
* TODO description
* @date 2016年8月18日
*/

public class ReturnOrderUpdateResultDto {

    private String msg;
    private String code;
    
    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }
    
    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
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
    
    
}
