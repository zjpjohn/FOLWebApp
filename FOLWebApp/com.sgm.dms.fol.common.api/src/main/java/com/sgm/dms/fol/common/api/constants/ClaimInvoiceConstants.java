
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.api
 *
 * @File name : ClaimInvoiceConstants.java
 *
 * @Author : Lujinglei
 *
 * @Date : 2016年6月3日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年6月3日    Lujinglei    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.common.api.constants;



/**
 *
 * @author Lujinglei
 * description 索赔发票凭证字典
 * @date 2016年6月3日
 */

public class ClaimInvoiceConstants {

    public static final String BUKRS ="9k70";       //公司代码
    public static final String BLART ="CC";         //凭证借贷类型(DA(19):贷方,DN:借方(40))
    public static final String WAERS ="CNY";        //货币种类
    public static final String BKTXT = "1";         //头信息
    public static final String UMSKZ = "G";         //特殊代码(贷方：G,借方不需要)
    public static final String MWSKZ = "Y0";        //税码（Y0:0%进项税）
    public static final String POSTING_KEY_BR="19";  //贷方
    public static final String POSTING_KEY_LOAN="40";  //借方
    public static final String SGM_ACCOUNT="S500400111";  //贷方账号
   
    public static final String KOSTL="9K70050000";  //成本中心
    public static final String WR_TYPE_COMMON="S030400009";//一般索赔、运输索赔
    public static final String WR_TYPE_OLD_CAR="S548300032";//二手车索赔
    public static final String WR_TYPE_RECALL="S030400029";//召回
    public static final String INVOICE_TITLE="9k70"; //发票抬头
    public static final String CO_CODE="9k70"; //公司代码
    
    public static final int SAP_DEAL_SUCCESS=4; //SAP处理成功
    public static final int SAP_DEAL_FAIL=5;    //SAP失败
    public static final int SGM_DEAL_SUCCESS=6;    //SGM处理成功
    public static final int FOL_DEAL_FAIL=7;    //SAP失败
    
}
