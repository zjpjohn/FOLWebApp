/**
 * @ClassName: demoAmountController
 * @Description: 岗位角色Controller
 * @author: LuHui
 * @date: 2015年10月19日 上午9:51:56
 * 
 * 
 */
package com.sgm.dms.fol.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.common.api.constants.CodeConstant;
import com.sgm.dms.fol.common.api.constants.CookieConstant;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.domain.PositionDTO;
import com.sgm.dms.fol.common.api.domain.PositionSwitchDTO;
import com.sgm.dms.fol.common.api.exception.VoNotValidException;
import com.sgm.dms.fol.common.api.services.PositionService;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.PropertiesUtil;
import com.sgm.dms.fol.vo.PositionSwitchVo;
import com.sgm.dms.fol.vo.PositionVo;
import com.sgm.solution.framework.dataAuthority.services.Encryptor;

/**
 * 
 * 岗位Controller
 * @date 2016年4月6日
 */
@Controller
@RequestMapping("/system/systemamount")
public class PositionController extends BaseController {
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());

	// 岗位 SERVICE
	@Autowired
	private PositionService systemService;

	/**
	 * 岗位查询
	 * 
	 * @param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getSystemmounts(@Validated @RequestBody PositionVo positionVo,HttpServletRequest request) throws Throwable {
		//初始化数据
		PositionDTO searchDto = BeanMapper.map(positionVo,PositionDTO.class);
		
		//数据库查询数据
		List<PositionDTO> dtoList = systemService.getPositionList(searchDto);

		//批量转换VO
		List<PositionVo> voList = BeanMapper.mapList(dtoList, PositionVo.class);
		
		//设置前台需要的数据
		Map<String, Object> responsedata=MapUtil.setQueryDataToMap(voList, systemService.getPositionCount(searchDto));
	
		return responsedata;
	}

	/**
	 * 岗位切换列表查询(不分页)
	 * 
	 * @param positionVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/find", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object findSystemmounts(@Validated @RequestBody PositionSwitchVo positionSwitchVo,HttpServletRequest request)
			throws Throwable {
		// 设置测试模式并返回账号
		String acnt=setTestModel(request);
		
		// 数据初始化
        PositionSwitchDTO positionSwitchDto = BeanMapper.map(positionSwitchVo, PositionSwitchDTO.class);
        
		// 解密
        positionSwitchDto.setUserAccount(acnt);
		
        // 查询数据
        List<PositionSwitchDTO> dtoList = systemService.getSystemmounts(positionSwitchDto);
		
        // 批量转换成VO
        List<PositionSwitchVo> voList = BeanMapper.mapList(dtoList, PositionSwitchVo.class);
        
        //设置前台需要的数据
		Map<String, Object> responsedata=MapUtil.setQueryDataToMap(voList, null);
		
		return responsedata;
	}
	
	/** 
	 * 设置测试模式
	 * @author DELL
	 * TODO description
	 * @date 2016年3月25日
	 * @param request
	 * @return
	 */
	private String setTestModel(HttpServletRequest request){
	    String acnt = request.getHeader(CodeConstant.LDAP_USER_REQUEST_STRING);
        logger.info("acnt:" + acnt);

        // 如果是测试模式就走入到IF里
        String model = PropertiesUtil.getProperty("logon.account.model.test");

        if (model != null && Boolean.valueOf(model)) {
            acnt = PropertiesUtil.getProperty("logon.account.name");
        }
        return acnt;
	}

	/**
	 * 新增岗位信息及岗位角色对应关系 METHOD: POST
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object addPosition(@RequestBody PositionVo vo ,HttpServletRequest request) throws Throwable {
		//数据初始化
	    PositionDTO positionDTO=initData(vo,POConstant.OPERATION_ADD,request);
		
	    //调用新增岗位SERVICE
	    systemService.addPosition(positionDTO);

		return true;
	}
	
	private PositionDTO initData(PositionVo vo,String operationType,HttpServletRequest request) throws Exception{
	    PositionDTO dto = BeanMapper.map(vo, PositionDTO.class);
        Long userId = CookieUtil.getUserId(request);
        if(POConstant.OPERATION_ADD.equals(operationType)){
            dto.setCreateBy(userId);
            dto.setUpdateBy(userId);
        }else{
            dto.setUpdateBy(userId); 
        }
        
        return dto;
	}
        /*
         * 
        *
        * @author Lujinglei
        * TODO 验证签名并修改角色
        * @date 2016年4月6日
        * @param request
        * @param vo
        * @param userId
        * @return
        * @throws Exception
         */
       @RequestMapping(value = "/update",method = RequestMethod.POST, produces = "application/json")
       //@SGMValidationRequest
       public @ResponseBody Object updatePosition(//@Validated @SGMValidationResource 
    		   @RequestBody PositionVo vo,HttpServletRequest request  
               //,@SGMValidationUserInfo @CookieValue(CookieConstant.COOKIE_USER_ID) String userId
               ) throws Exception {
                    //数据初始化
               PositionDTO positionDTO=initData(vo,POConstant.OPERATION_UPDATE,request);
                 
               //调用更新岗位SERVICE
               systemService.updatePosition(positionDTO);
               return true;
       }



	/**
	 * 
	 * 删除岗位
	 * @param vo
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object deletePosition(@Validated @RequestBody PositionVo vo,HttpServletRequest request)
			throws Throwable {
	    //数据初始化
        PositionDTO positionDTO=initData(vo,POConstant.OPERATION_DELETE,request);
        
        //调用删除岗位SERVICE
		systemService.deletePosition(positionDTO);

		return true;
	}

	/** 
	 * 获取所有岗位类型
	 * @author DELL
	 * TODO description
	 * @date 2016年3月25日
	 * @param type
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/type/{type}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getPositionByType(@PathVariable String type,HttpServletRequest request)
			throws Throwable {	
		//数据初始化
		PositionDTO dto = new PositionDTO();
		if(StringUtils.isNotBlank(type)){
			String[] typeArray = type.split("\\|");
			if(typeArray != null && typeArray.length == 1){
				dto.setPositionType(Integer.parseInt(type));//当岗位类型只有一个时，查询时按该岗位类型查询；当岗位类型不是一个时，默认查询所有岗位类型。
			}
		}
        //调用获取岗位的SERVICE
        List<PositionDTO> dtoList = systemService.getPositions(dto);
        
        //批量转换成VO
        List<PositionVo> voList = BeanMapper.mapList(dtoList, PositionVo.class);
        
        return voList;
	}

	/** 
	 * 查询数据库相关类型的岗位的用户
	 * @author DELL
	 * TODO description
	 * @date 2016年3月25日
	 * @param type
	 * @param userAccount
	 * @param request
	 * @param response
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/type/{type}/user/{userAccount}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getPositionByType(@PathVariable int type,
			@PathVariable String userAccount, HttpServletRequest request) throws Throwable {		
		//调用SERVICE
		List<PositionDTO> dtoList = systemService.getPositionsChecked(type,userAccount);
		
		//批量转换成VO
		List<PositionVo> voList = BeanMapper.mapList(dtoList, PositionVo.class);
		
		return voList;
	}

	/** 
	 * 查询出相关的岗位名的总数
	 * @author DELL
	 * TODO description
	 * @date 2016年3月28日
	 * @param positionName
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/name/{positionName}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getAuthorityCountByName(@PathVariable String positionName,HttpServletRequest request)
			throws Throwable {
		//验证数据
	    checkPositionName(positionName);
		
		//获取相关的岗位的名的count数
		Integer num = systemService.getPositionCountByName(positionName);
		
		return num;
	}
	
	private void checkPositionName(String positionName) throws Exception{
	    if (positionName.length() == 0) {
            throw new VoNotValidException("PositionName Name is Incorrect");
        }
	}
	
    /*
     * 
    *
    * @author Lujinglei
    * TODO 获取签名sign，返回vo对象
    * @date 2016年4月7日
    * @param vo
    * @param request
    * @param userId
    * @return
    * @throws Throwable
     */
    
    @RequestMapping(value = "/getSign", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getSign(@Validated @RequestBody PositionVo vo,HttpServletRequest request,@CookieValue(CookieConstant.COOKIE_USER_ID) String userId)
            throws Throwable {
        PositionVo result=new PositionVo();
        result.setPositionId(vo.getPositionId());
        result.setPositionDesc(vo.getPositionDesc());
        result.setDpPositionCode(vo.getDpPositionCode());
        result.setPositionType(vo.getPositionType());
        result.setRoles(vo.getRoles());
    //    result.setUserAccount(vo.getUserAccount());
        String[] refVales=new String[]{vo.getPositionId(),String.valueOf(vo.getPositionType()),vo.getRoles()};
        String sign=Encryptor.generateSignFromResource(userId,refVales);
        result.setSign(sign);

        return result;
    }
}
