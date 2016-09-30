/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.service
*
* @File name : StringUtil.java
*
* @Author : ZhangBao
*
* @Date : 2015年11月5日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年11月5日    ZhangBao    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.common.service.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/*
 *
 * @author ZhangBao
 * TODO description
 * @date 2015年11月5日
 */

public class StringUtil  extends StringUtils{
   
	public static String getFileUrl(String fileUrl,String str){
		if(isEmpty(fileUrl)){
			return "";
		}
		int index=fileUrl.indexOf(str);
		 fileUrl=fileUrl.substring(0,index);
		return fileUrl;
		
	}
	
	public static String getFiledId(String filePath){
		if(StringUtil.isEmpty(filePath)){
			return "";
		}
		int fileIdindex=filePath.indexOf("files/")+"files/".length();
		int indexToken=filePath.indexOf("?token=");
		return filePath.substring(fileIdindex,indexToken);
	}
	
	public static String getToken(String filePath){
		if(StringUtil.isEmpty(filePath)){
			return "";
		}
		int fileIdindex=filePath.indexOf("?token=")+"?token=".length();
		return filePath.substring(fileIdindex);
	}
	public static String firstUpperCase(String str){
		if(isEmpty(str)){
			return "";
		}
		String firstStr=str.substring(0,1).toUpperCase();
		if(1==str.length()){
			return firstStr;
		}
		return firstStr+str.substring(1);
	}
	
	/**
	 * 把对象中的数字类型统一转换成财务专用的符号
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> fontFormatFinance(List<T> list) throws Exception{

		for (T t : list) {
			Field[] field=t.getClass().getDeclaredFields();

			for (Field fd : field) {
				String fieldName = fd.getName();
				if("serialVersionUID".equals(fieldName)){
					continue;
				}else if(fieldName.toLowerCase().contains("amount")){
					String getMethodName = "get"
							+ fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1);
					
					Class<?> tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(t, new Object[]{});
					
					if(value!=null&&!value.toString().trim().equals("0")){
						String[] numbers=value.toString().split("\\.");
						String finalNumber=null;
						if(numbers.length==2){
							String beforenumber=numbers[0];
							beforenumber=stringAddComma(beforenumber);
							finalNumber=beforenumber+"."+numbers[1];
						}else{
							String beforenumber=stringAddComma(value.toString());
							finalNumber=beforenumber+".00";
						}
						
						//执行SET方法
						String setMethodName = "set"
								+ fieldName.substring(0, 1).toUpperCase()
								+ fieldName.substring(1);
						
						Method setMethod = tCls.getMethod(setMethodName, new Class[]{String.class});
						Object setvalue = setMethod.invoke(t, new Object[]{finalNumber});
					}else if(value!=null&&value.toString().trim().equals("0")){
					    String setMethodName = "set"
                                + fieldName.substring(0, 1).toUpperCase()
                                + fieldName.substring(1);
                        
                        Method setMethod = tCls.getMethod(setMethodName, new Class[]{String.class});
                        Object setvalue = setMethod.invoke(t, new Object[]{"0.00"});
					}
				}
			}
		}
		return list;
	}
	
	/**
     * 把对象中的数字类型统一转换成财务专用的符号
     * @param list
     * @return
     * @throws Exception
     */
    public static Object fontFormatFinance(Object obj) throws Exception{

            Field[] field=obj.getClass().getDeclaredFields();

            for (Field fd : field) {
                String fieldName = fd.getName();
                if("serialVersionUID".equals(fieldName)){
                    continue;
                }else if(fieldName.contains("Amount")){
                    String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    
                    Class<?> tCls = obj.getClass();
                    Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                    Object value = getMethod.invoke(obj, new Object[]{});
                    
                    if(value!=null&&!value.toString().trim().equals("0")){
                        String[] numbers=value.toString().split("\\.");
                        String finalNumber=null;
                        if(numbers.length==2){
                            String beforenumber=numbers[0];
                            beforenumber=stringAddComma(beforenumber);
                            finalNumber=beforenumber+"."+numbers[1];
                        }else{
                            String beforenumber=stringAddComma(value.toString());
                            finalNumber=beforenumber+".00";
                        }
                        
                        //执行SET方法
                        String setMethodName = "set"
                                + fieldName.substring(0, 1).toUpperCase()
                                + fieldName.substring(1);
                        
                        Method setMethod = tCls.getMethod(setMethodName, new Class[]{String.class});
                        Object setvalue = setMethod.invoke(obj, new Object[]{finalNumber});
                    }else if(value!=null&&value.toString().trim().equals("0")){
                        String setMethodName = "set"
                                + fieldName.substring(0, 1).toUpperCase()
                                + fieldName.substring(1);
                        
                        Method setMethod = tCls.getMethod(setMethodName, new Class[]{String.class});
                        Object setvalue = setMethod.invoke(obj, new Object[]{"0.00"});
                    }
                }
            }

        return obj;
    }
	
	/**
	 * 把字符串中加入逗号
	 * @throws Exception
	 */
	private static String stringAddComma(String beforeNumber) throws Exception{
		String number="";
		if(!beforeNumber.contains("-")){
			while(beforeNumber.length()%3!=0){
				beforeNumber=" "+beforeNumber;
			}
			
			for (int i = 0; i < (beforeNumber.length()/3); i++) {
				String number3=beforeNumber.substring(i*3,i*3+3);
				if((i+1)==(beforeNumber.length()/3)){
					number+=number3;
				}else{
					number+=number3+",";
				}
			}
		}else{
			int length=beforeNumber.length()-1;
			beforeNumber=beforeNumber.replaceAll("-", "");
			while(length%3!=0){
				beforeNumber=" "+beforeNumber;
				length=beforeNumber.length();
			}
			
			for (int i = 0; i < (length/3); i++) {
				String number3=beforeNumber.substring(i*3,i*3+3);
				if((i+1)==(beforeNumber.length()/3)){
					number+=number3;
				}else{
					number+=number3+",";
				}
			}
			number="-"+(number.trim());
		}
		return number.trim();
	}
	
	
	public static boolean matchesNumber(String s) throws Exception{
		String regex="[0-9]*";
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(s);
		if(m.find()){
			return true;
		}else{
			return false;
		}
	}
}
