package com.sgm.dms.fol.common.service.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtils implements ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextUtils.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	
	public static Object getBeanById(String id){
		return applicationContext.getBean(id);
	}
	
	public static <T> T getBeanByType(Class<T> requiredType){
		return applicationContext.getBean(requiredType);
	}
	
}
