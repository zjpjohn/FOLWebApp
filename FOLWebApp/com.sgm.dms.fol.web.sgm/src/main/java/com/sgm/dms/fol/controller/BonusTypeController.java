package com.sgm.dms.fol.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.domain.CodeDTO;
import com.sgm.dms.fol.common.api.services.CodeService;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.vo.CodeVo;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/*
 * 奖金类型数据维护controller
 * @author zhang bao
 * @date 2015年12月8日
 */
@Controller
@RequestMapping("/bonus/type")
public class BonusTypeController extends BaseController{
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	//CODE SERVICE
	@Autowired
	private CodeService codeService;
	
	/**
	 * 查询奖金类型数据
	 * 
	 * @param codeVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/query")
	@ValidationRequestUrl
	@ResponseBody
	public Object findBonusTypeData(@Validated @RequestBody CodeVo codeVo) throws Throwable {
	    //初始化处理类型
	    CodeDTO code=initBonusType(codeVo);
		
		//数据库查询奖金类型
		List<CodeDTO> data=codeService.findCodeDataByCodeCnAndCode(code);
		
		//日期格式化
		convertDate(data);

		//设置记录总数
		Map<String,Object> responsedata=MapUtil.setQueryDataToMap(data, Long.valueOf(getBonusTypeTotal(code)));
		return responsedata;
	}
	
	/** 
	* 获取奖金类型总数
	* @param code
	* @return
	* @throws Exception
	*/
	private int getBonusTypeTotal(CodeDTO code) throws Exception {
		int total=codeService.getTotalTypeByWhs(code);
		return total;
	}
	
	/** 
    * 数据转换
	* @param data
	* @throws Exception
	*/
	private void convertDate(List<CodeDTO> data) throws Exception{
		if(null!=data&&data.size()>0){
		for(CodeDTO code:data){
			if(null!=code){
				if(!StringUtil.isEmpty(code.getCreateDate())){
				    code.setcDate(DateUtil.date2str(code.getCreateDate()));
				}
				
				if(!StringUtil.isEmpty(code.getUpdateDate())){
					code.setuDate(DateUtil.date2str(code.getUpdateDate()));
				}
			}
			
		}
		}
		
	}
	
	/**
	 * 新增字典数据
	 * 
	 * @param codeVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public Object addBonusType(@RequestBody CodeVo vo,HttpServletRequest request) throws Throwable {
	    //初始化数据
	    CodeDTO dto=initNewBonusType(vo,request);
		
	    //保存奖金
		codeService.saveCodeType(dto);

		return true;
	}
	
	/**
	 * 修改字典数据
	 * 
	 * @param codeVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public Object updateBonusType(@RequestBody CodeVo vo,HttpServletRequest request)  throws Throwable {
	    //初始化数据
        CodeDTO dto=initNewBonusType(vo,request);
        
        //更新奖金
		codeService.updateCodeTypeByCodeId(dto);
		
		return true;
	}
	
	/**
	 * 根绝codeID查询data
	 * 
	 * @param codeVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/find",method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public Object findOneBonusType(@RequestBody CodeVo vo)  throws Throwable {
		
	    //转换DTO
		CodeDTO dto = BeanMapper.map(vo, CodeDTO.class);
		
		//查询出code
		CodeDTO codeDTO = codeService.findCodeByCodeId(dto.getCodeId());
		
		return codeDTO;
	}

	/**
	 * 初始化数据类型
	 */
	private CodeDTO initBonusType(CodeVo codeVo) throws Exception{
	    CodeDTO code=BeanMapper.map(codeVo, CodeDTO.class);
        
        //设置type_name
        code.setType(POConstant.TYPE_NAME_CODE_BONUS+"");
        return code;
	}
	
	/**
	 * 初始化新增奖金类型
	 */
	private CodeDTO initNewBonusType(CodeVo codeVo,HttpServletRequest request) throws Exception{
	    CodeDTO dto = BeanMapper.map(codeVo, CodeDTO.class);
        dto.setType(String.valueOf(POConstant.TYPE_NAME_CODE_BONUS));
        dto.setStatus(new Long(POConstant.VALID_STATUS));
        Long userId = CookieUtil.getUserId(request);
        dto.setCreateBy(userId);
        dto.setUpdateBy(userId);
        return dto;
	}
}
