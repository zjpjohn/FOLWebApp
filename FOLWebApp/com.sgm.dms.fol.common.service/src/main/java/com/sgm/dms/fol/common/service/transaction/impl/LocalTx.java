package com.sgm.dms.fol.common.service.transaction.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by LuZhen on 2015-09-29.
 * 本地事务，默认30s超时，传播实行为默认
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional(value = "localManager",propagation = Propagation.REQUIRED,timeout =30)
public @interface LocalTx {
}
