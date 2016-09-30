package com.sgm.dms.fol.common.service.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/*****
 * 数字utils类
*
* @author Administrator
* TODO description
* @date 2016年1月13日
 */
public class MathUtils {
	
	/****
	 * 将计算结果返回两位小数bigdecimal
	 */
	
	public static BigDecimal getBigDecimal(BigDecimal num){
		if(!StringUtil.isEmpty(num)){
			return new BigDecimal(new DecimalFormat("#0.00").format(num));
		}
		return num;
		
	}
	

}
