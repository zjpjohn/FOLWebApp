package com.sgm.dms.fol.common.api.exception;

/**
 * Created by LuZhen on 2015-09-29.
 * 调用第三方提供的服务遇到异常，需要被程序捕捉并作回滚
 */
public abstract  class ServiceInvocationException extends Exception{

    private static final long serialVersionUID = -5326504408600182142L;

    public ServiceInvocationException(String msg) {
        super(msg);
    }

    //服务补偿，需子类各自实现
    public abstract Object ServiceCompensation();

}
