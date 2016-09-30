/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.api
*
* @File name : ResponseDTO.java
*
* @Author : ShenWeiwei
*
* @Date : 2015年10月27日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年10月27日    ShenWeiwei    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.common.api.domain;

import com.sgm.dms.fol.common.api.constants.StateConstant;

/*
 *
 * @author ShenWeiwei
 * TODO description
 * @date 2015年10月27日
 */

public class ResponseDTO extends BaseDTO{
    private String message;
    private int errorCode;
    private int state;
    private int size;
    private int total;
    
	public ResponseDTO() {// ResponseDTO初始状态为成功状态
		this.state = StateConstant.SUCCESS;
	}
	
	public ResponseDTO(int state) {
		super();
		this.state = state;
	}

	public ResponseDTO(int state, String message) {
		this.state = state;
		this.message = message;
	}

	public ResponseDTO(int state, int errorCode, String message) {
		this.state = state;
		this.errorCode = errorCode;
		this.message = message;
	}
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }
    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
    /**
     * @return the errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }
    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    /**
     * @return the state
     */
    public int getState() {
        return state;
    }
    /**
     * @param state the state to set
     */
    public void setState(int state) {
        this.state = state;
    }
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "ResponseDTO [message=" + message + ", errorCode=" + errorCode
				+ ", state=" + state + ", size=" + size + ", total=" + total
				+ "]";
	}
}
