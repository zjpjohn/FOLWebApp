package com.sgm.dms.fol.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.common.api.domain.CodeDTO;
import com.sgm.dms.fol.common.api.services.CodeService;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/**
* 字典表Controller
*
* @author DELL
* TODO description
* @date 2016年3月22日
 */
@Controller
@RequestMapping("/code")
public class CodeController extends BaseController{
    // 日志
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired  
    private CodeService codeService;
    
    /**
     * 查询code数据
     */
    @RequestMapping(value = "/findCodeTypeNames", method = RequestMethod.POST)
    @ValidationRequestUrl
    @ResponseBody
    public Object findCodeTypeNames(@RequestBody String[] codeVos,HttpServletRequest request) throws Throwable {
        List<List<?>> responsedata = findCodeTypeNamesToDataBase(codeVos);
        return responsedata;
    }

    /**
     * 到 数据库查询初始数据
     */
    private List<List<?>> findCodeTypeNamesToDataBase(String[] codeVos) throws SQLException {

        List<List<?>> codeDTOlist = new ArrayList<List<?>>();
        for (int i = 0; i < codeVos.length; i++) {
            String column = codeVos[i];
            List<CodeDTO> codeDTOs = codeService.findCodeByTypeName(column);
            codeDTOlist.add(codeDTOs);
        }
        return codeDTOlist;
    }
}
