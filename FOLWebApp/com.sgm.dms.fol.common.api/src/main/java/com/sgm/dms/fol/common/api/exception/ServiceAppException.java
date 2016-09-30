package com.sgm.dms.fol.common.api.exception;

/**
 * Created by LuZhen on 2015-09-29. Service层异常统一封装的程序运行异常，不做异常捕捉与处理
 */
public class ServiceAppException extends RuntimeException {

    private static final long serialVersionUID = -5526463365254777876L;

    public ServiceAppException(String msg){
        super(msg);
    }

    public ServiceAppException(String msg, Exception e){
        super(msg, e);
    }

    public ServiceAppException(Exception e){
        super(e);
    }
}
