/**
 * @ClassName: Controller
 * @Description: 人员角色Controller
 * @author: LuHui
 * @date: 2015年10月19日 上午9:51:56
 * 
 * 
 */
package com.sgm.dms.fol.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.sgm.dms.fol.common.api.domain.AuthorityDTO;
import com.sgm.dms.fol.common.api.domain.CodeDTO;
import com.sgm.dms.fol.common.api.domain.PersonRoleDTO;
import com.sgm.dms.fol.common.api.domain.UserDTO;
import com.sgm.dms.fol.common.api.domain.UserSearchDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.services.AuthorityService;
import com.sgm.dms.fol.common.api.services.CodeService;
import com.sgm.dms.fol.common.api.services.PersonPositionService;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.vo.UserSearchVo;
import com.sgm.dms.fol.vo.UserVO;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationRequest;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationUserInfo;
import com.sgm.solution.framework.dataAuthority.services.Encryptor;

/**
 * 
 * 人员操作Controller
 * @date 2016年4月6日
 */
@Controller
@RequestMapping("/person/personRole")
public class PersonRoleController extends BaseController {
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());

	// Code SERVICE
	@Autowired
	private CodeService codeService;
	@Autowired
	private PersonPositionService personRoleService;
	@Autowired
	private AuthorityService authorityService;
	
	/**
	 * 人员角色查询
	 * 
	 * @param reserveAmountDTO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object findPsersonRoles(@Validated @RequestBody UserSearchVo vo, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        //数据初始化
        UserSearchDTO dto = BeanMapper.map(vo, UserSearchDTO.class);
        
        //查询人员角色
        List<UserDTO> dtoList = personRoleService.searchUsersList(dto);
        
        //批量转换成VO
        List<UserVO> volist = BeanMapper.mapList(dtoList, UserVO.class);
        
        //设置成前台需要的数据
        Map<String, Object> responsedata=MapUtil.setQueryDataToMap(volist, personRoleService.searchUsersCount(dto));
        
		return responsedata;
	}
	
	/**
	 * 修改人员角色
	 * 
	 * @param reserveAmountDTO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updata", method = RequestMethod.POST)
	@ResponseBody
	public Object updatafindPsersonRole(@Validated @RequestBody List<Object> personRole,
			HttpServletRequest request)
			throws Throwable {
	    //数据初始化
	    PersonRoleDTO personRoleDTO=initPersonRoleDTO(personRole);
	    
	    //调用更新用户角色SERVICE
		personRoleService.updataPersonRole(personRoleDTO);
		
		return true;
	}

	/**
	 * 数据初始化
	 */
	private PersonRoleDTO initPersonRoleDTO(List<Object> personRole) throws Exception{
	    int userId=Integer.parseInt((personRole.get(1)).toString());
        PersonRoleDTO role=BeanMapper.map(personRole.get(0), PersonRoleDTO.class);
        role.setUserId(Long.parseLong(userId+""));
        return role;
	}

	/**
	 * 查询code数据
	 */
	@RequestMapping(value = "/findCodeTypeNames", method = RequestMethod.POST)
	@ResponseBody
	public Object findCodeTypeNames() throws Throwable {
	    List<List<?>> responsedata= findCodeTypeNamesToDataBase();
	        
	    return responsedata;
	}

	/**
	 * 到 数据库查询初始数据
	 * @throws SQLException 
	 */
	private  List<List<?>> findCodeTypeNamesToDataBase()
			throws ServiceBizException, SQLException {
		String[] columns = { "角色类型","人员类型","部门"};
		 List<List<?>> codeDTOlist = new ArrayList<List<?>>();
	        for (int i = 0; i < columns.length; i++) {
	            String column = columns[i];
	            List<CodeDTO> codeDTOs = codeService.findCodeByTypeName(column);
	            codeDTOlist.add(codeDTOs);
	        }
	        return codeDTOlist;
	}
	/**
	 * 查询
	 */
	@RequestMapping(value = "/findRoleNames", method = RequestMethod.POST)
	@ResponseBody
	public Object findRoleNames() throws Throwable {
	    List<AuthorityDTO> responsedata= findRoleNamesToDataBase();
	    return responsedata;
		
	}

	/**
	 * 到 数据库查询初始数据
	 * @throws SQLException 
	 */
	private  List<AuthorityDTO> findRoleNamesToDataBase()
			throws ServiceBizException, SQLException {
		 AuthorityDTO search = new AuthorityDTO();
	            List<AuthorityDTO> authorityDTOs =authorityService.getAuthoritymounts(search);
	        return authorityDTOs;
	}
	

	
	/**
	  * 获取签名
	  */
	 @RequestMapping(value = {"/{positionId}"}, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	 public @ResponseBody UserSearchVo findVO(@PathVariable String positionId, @PathVariable String userAccount, @CookieValue("user_id") String userId) throws Exception{
	     //
		 UserSearchVo result=new UserSearchVo();
	     result.setPositionId(positionId);
	     result.setUserAccount(userAccount);
	     String[] refVales=new String[]{positionId,userAccount};
	     String sign=Encryptor.generateSignFromResource(userId,refVales);
	     result.setSign(sign);
	     return result;
	 }
	/**
	 * 验证签名
	 */
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@SGMValidationRequest
	public @ResponseBody UserSearchVo testAnnotation(@RequestBody @SGMValidationResource UserSearchVo vo,
			@SGMValidationUserInfo @CookieValue("user_id") String userId) throws Exception {
			System.out.println(vo.getPositionId());
			System.out.println(vo.getUserAccount());
			System.out.println(vo.getSign());
			return vo;
	}

}
