package com.sgm.dms.fol.common.api.interceptor;

/**
 * Created by LuZhen on 2015-09-25.
 */
public interface IInterceptor {

    /**
     * 在方法调用后进行拦截，打印调用信息
     * @return String 拦截器名称
     */
    public String getInterceptorName();


}
