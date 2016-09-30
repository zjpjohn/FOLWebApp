package com.sgm.dms.fol.bonus.common;

import java.util.GregorianCalendar;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sgm.dms.fol.bonus.client.SGMCommonHeaderType;

/****
 * 调用服务走esb 需要的header
 * @author zhang  bao 
 *
 */
public class CommonHelper {
    // 日志
    protected static Logger logger = LogManager.getLogger(CommonHelper.class);
	/**
	 * @throws Exception 
	 * 
	 * @Title: tansfer
	 * @Description: SGMCommonHeaderType初始化
	 * @param: @return
	 * @return: SGMCommonHeaderType
	 * @date: 2015年12月23日 下午4:48:25
	 * @throws
	 */
	public static  SGMCommonHeaderType tansfer(GregorianCalendar nowGregorianCalendar) throws Exception{
		SGMCommonHeaderType type=new SGMCommonHeaderType();
		type.setFrom("FOL");
		type.setTo("FOL");
	    XMLGregorianCalendar xmlDatetime=null;
		try {
			xmlDatetime = DatatypeFactory.newInstance().newXMLGregorianCalendar(nowGregorianCalendar);
		} catch (DatatypeConfigurationException e) {
		    logger.error(e);
			throw new Exception(e.getMessage());
		} 
		type.setTimestamp(xmlDatetime);
		type.setMessageId("FOL"+"-"+"UUID:"+UUID.randomUUID());
		return type;
	}
}
