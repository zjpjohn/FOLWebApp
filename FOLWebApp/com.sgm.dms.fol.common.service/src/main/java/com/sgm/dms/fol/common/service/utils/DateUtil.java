/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.service
*
* @File name : DateUtil.java
*
* @Author : shenweiwei
*
* @Date : 2015年10月22日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年10月22日    shenweiwei    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.common.service.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;


/*
 *
 * @author shenweiwei
 * TODO description
 * @date 2015年10月22日
 */

public class DateUtil {
	
	/**
	 * 获取nowDate的下一天日期
	 * @author wangfl
	 * @date 2016年9月18日
	 * @param nowDate
	 * @return
	 */
	public static Date nextDate(Date nowDate){
		if(null == nowDate) return null;
        //通过日历获取下一天日期  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(nowDate);  
        cal.add(Calendar.DAY_OF_YEAR, +1);  
        return cal.getTime();
	}
	
	
	/**
	 * 获得当前日期的月初日期
	 * @author wangfl
	 * @date 2016年8月18日
	 * @param date
	 * @return
	 */
	public static Date monthStartDate(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
		try {
			return sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date monthEndDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
	
		return monthStartDate(cal.getTime());// 获得月末是几号
	}
    /**
     * 月份不足2位前面补0
     */
    public static String monthReplenishZero(String month){
        if(month.toString().length()<2){
            return "0"+month;
        }else{
            return month;
        }
        
    }

    /**
     * 将日期格式化yyyyMMddHHmmss
     * @author wangfl
     * @date 2016年9月27日
     * @param date
     * @return
     */
    public static String formatDate(Date date){
    	return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    }
    
    /****
     * 将日期格式化yyyyMMdd
     */
    public static String formartDate(Date date){
    	if(!StringUtils.isEmpty(date)){
    		Timestamp ts=new Timestamp(date.getTime());
    		String [] arrs=ts.toString().split(" ");
    		return arrs[0].replace("-", "");
    	}
    	return "";
    }
    
    /****
     * 将日期格式化yyyy-MM-dd
     */
    public static String date2str(Date date){
    	
    	if(!StringUtils.isEmpty(date)){
    		Timestamp ts=new Timestamp(date.getTime());
    		String [] arrs=ts.toString().split(" ");
    		return arrs[0];
    	}
    	return "";
    }
    /****
     * 将日期格式化yyyyMM
     */
    public static Date strToDate(String date){
    	SimpleDateFormat sf=new SimpleDateFormat("yyyyMM");
    	String year=date.substring(0,4);
    	String month=date.substring(5,7);
    	date=year+month;
    	if(!StringUtils.isEmpty(date)){
    		try {
				return sf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return null;
    }
    
    /****
     * 将日期格式化yyyyMMdd
     */
    public static Date strToDateFormat(String date){
    	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
    	if(!StringUtils.isEmpty(date)){
    		try {
				return sf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return null;
    }
}
