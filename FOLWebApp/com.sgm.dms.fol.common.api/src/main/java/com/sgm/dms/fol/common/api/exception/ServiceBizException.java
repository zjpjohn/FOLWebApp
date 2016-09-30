package com.sgm.dms.fol.common.api.exception;

/**
 * Created by LuZhen on 2015-09-29.
 * Service层异常统一封装的业务异常，一般无法直接处理
 */
public class ServiceBizException extends  RuntimeException{

    private static final long serialVersionUID = -5526463365254777876L;

    public ServiceBizException(String msg) {
        super(msg);
    }
    
    public ServiceBizException(String msg, Throwable cause) {
    	super(msg, cause);
    }
    
    public ServiceBizException(Throwable cause) {
    	super(cause);
    }
}
