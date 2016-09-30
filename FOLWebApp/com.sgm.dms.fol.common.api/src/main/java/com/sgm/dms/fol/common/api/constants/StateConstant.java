/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.api
*
* @File name : StateConstant.java
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
	
package com.sgm.dms.fol.common.api.constants;


/*
 * 状态字典
 * @author shenweiwei
 * TODO description
 * @date 2015年10月22日
 */

public class StateConstant {
    public static String RESERVE_AMOUNT_DIFFENCE_YES="1";//有差异
    public static String RESERVE_AMOUNT_DIFFENCE_NO="0";//无差异
    
    public static int DATABASE_ERROR=9_00;//数据库错误
    
    public static int DATA_ERROR=8_00;//数据错误
    
    public static int APP_ERROR=7_00;//应用错误
    
    public static int HTTP_ERROR=4_00;//http错误
    
    public static int SUCCESS=200;//成功
}
