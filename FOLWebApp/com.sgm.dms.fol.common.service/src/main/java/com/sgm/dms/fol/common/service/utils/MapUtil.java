/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.service
*
* @File name : MapUtil.java
*
* @Author : DELL
*
* @Date : 2016年3月22日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年3月22日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.common.service.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
*
* @author DELL
* TODO description
* @date 2016年3月22日
*/

public class MapUtil {

    public static Map<String, Object> setQueryDataToMap(List<?> rows,Long total){
        Map<String, Object> map=new HashMap<>();
        map.put("rows", rows);
        map.put("total", total);
        return map;
    }
}
