package com.sgm.dms.fol.common.service.interceptor.impl;

import com.sgm.dms.fol.common.api.interceptor.IInterceptor;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * Created by LuZhen on 2015-09-25.
 */
@Aspect
@Component
public class LogAopInterceptorImpl implements IInterceptor {


    private final static String LOG_AOP_INTERCEPTOR="LOG_AOP_INTERCEPTOR";

    @Override
    public String getInterceptorName() {
        return LOG_AOP_INTERCEPTOR;
    }

    private static Logger logger = Logger.getLogger(LogAopInterceptorImpl.class);

    /**
     * 设置切入点
     * @return void
     */
    @Pointcut("execution(* com.sgm.dms.pol..*.*(..))")
    //@Pointcut("execution(* demo..*.*(..))")
    public void doMethod() {}


    /**
     * 在方法调用前进行拦截，打印调用信息
     * @param  jp
     * @return void
     */
    @Before("doMethod()")
    public void logBeforeDo(JoinPoint jp) {
        logger.info(" **********Target Method: "+jp.getTarget().toString()+" at " +new Date(). toString());
        logger.info(" **********Begin  Execute Method: "+jp.getSignature().getName().toUpperCase()+" at " +new Date().toString() );

        if(logger.isDebugEnabled())
        {
            logger.debug(" **********Method args as below  ");//
            Object[] args = jp.getArgs();
            for(int i=0 ;i<args.length  ;i++){
                if(args[i]!=null)
                    logger.debug(" ===========> arg value[" + i + "] as <"+args[i].toString()+" > ");
            }
        }
    }

    /**
     * 在方法调用后进行拦截，打印调用信息
     * @param jp
     * @return void
     */
    @After("doMethod()")
    public void logAfterDo(JoinPoint jp) {
        logger.info(" End Method: "+jp.getTarget().getClass().getName().toUpperCase()+"."+jp.getSignature().getName()+" at " +new Date().toString() );
    }


    /**
     * 打印异常堆栈信息
     * @param jp
     * @return void
     */
    @AfterThrowing(pointcut = "doMethod()", throwing = "error")
    public void logException(JoinPoint jp, Throwable error) {
        logger.error(" doMethod :" + jp.getTarget().getClass().getName().toUpperCase() + "." + jp.getSignature().getName()+" at " +new Date().toString());
        logger.error(" error message :" + error.getMessage());
    }

}
