package com.sgm.dms.fol.common.service.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * Created by LuZhen on 2015-09-25.
 */
public class CommonUtils {
    public static final String EMPTY = "";//空字符串
    private final static String DATE_FORMAT_TIMESTAMP="yyyy-MM-dd'T'HH:mm:ssZ";
    private final static String DATE_FORMAT_NORMAL="yyyy-MM-dd";

    public static boolean isNullOrEmpty(String str) {
        return (null == str || str.length() == 0);
    }

    public static boolean isNullOrEmpty(Set<?> set) {
        if (null == set) {
            return true;
        }
        return set.isEmpty();
    }

    public static String formatDateByPara(Date date,String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static String formatDateByNormal(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_NORMAL);
        return df.format(date);
    }

    public static String formatDateByTimestamp(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_TIMESTAMP);
        return df.format(date);
    }

    public static String getUUID() {
        return java.util.UUID.randomUUID().toString();
    }

    /**
     * 
     * 功能说明    : 特殊字符转化(值对象)
     * 创建者         : admin
     * 修改日期    : 2015年8月19日
     * @param String value
     * @return value
     */
    public static void filterSpecialWord(Object model){
        if(model != null){
            Field[] field = model.getClass().getDeclaredFields();//获取实体类的所有属性，返回Field数组  
            for(int j=0 ; j<field.length ; j++){//遍历所有属性
                try{
                    String name = field[j].getName();//获取属性的名字
                    String upperName = firstWordToUpper(name);//首字母变大写            
                   String type = field[j].getGenericType().toString();//获取属性的类型
                   if(type.equals("class java.lang.String")){//如果type是类类型，则前面包含"class "，后面跟类名
                       Method m = model.getClass().getMethod("get" + upperName);
                       String value = (String) m.invoke(model);//调用getter方法获取属性值
                       Method n = model.getClass().getDeclaredMethod("set" + upperName, String.class);
                       n.setAccessible(true);
                       n.invoke(model,parseSpecialWord(value));//调用setter方法设定属性值
                   } 
                } catch (Exception e) {
                    //不对该错误进行处理
                } 
            }
        }
    }
    
    /**
     * 
     * 功能说明    : 首字母转大写
     * 创建者         : admin
     * 修改日期    : 2015年8月19日
     * @param String name
     * @return name
     */
    public static String firstWordToUpper(String name){
        String firstWord = EMPTY;
        String otherWord = EMPTY;;
        if(isNotEmpty(name)){
            firstWord = name.substring(0, 1).toUpperCase();
            otherWord = name.substring(1, name.length());
            name = firstWord.concat(otherWord);
        }
        return name;
    }
    
    /**
     * 
     * 功能说明    : 判断字符串非空
     * 创建者         : admin
     * 修改日期    : 2015年8月19日
     * @param multipartFile
     * @return
     */
    public static boolean isNotEmpty(String str) {
        boolean result = true;
        //暂时只判断空值文件
        if (str == null || "".equals(str)) {
            return false;
        }
        return result;
    }
    
    /**
     * 
     * 功能说明    : 特殊字符转化(String)
     * 创建者         : admin
     * 修改日期    : 2015年8月19日
     * @param String value
     * @return value
     */
    public static String parseSpecialWord(String value){
        //You'll need to remove the spaces from the html entities below
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    }
}
