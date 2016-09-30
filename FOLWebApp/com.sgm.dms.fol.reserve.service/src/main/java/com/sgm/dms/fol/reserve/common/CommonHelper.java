package com.sgm.dms.fol.reserve.common;

import java.util.GregorianCalendar;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.sgm.dms.fol.reserve.client.SGMCommonHeaderType;


/****
 * 调用服务走esb 需要的header
 * @author zhang  bao 
 *
 */
public class CommonHelper {
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
			throw new Exception(e.getMessage());
		} 
		type.setTimestamp(xmlDatetime);
		type.setMessageId("FOL"+"-"+"UUID:"+UUID.randomUUID());
		return type;
	}
}
