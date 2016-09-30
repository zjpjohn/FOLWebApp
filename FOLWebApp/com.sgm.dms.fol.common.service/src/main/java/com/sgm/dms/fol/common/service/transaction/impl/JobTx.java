package com.sgm.dms.fol.common.service.transaction.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by LuZhen on 2015-09-29.
 * JOB使用的服务，本地事务，超时为5min
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional(value = "localManager",propagation = Propagation.REQUIRES_NEW,timeout =300)
public @interface JobTx {
}
