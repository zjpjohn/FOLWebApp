/**
* @ClassName: demoAmountController
* @Description: 角色权限Controller
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
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.common.api.constants.CookieConstant;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.domain.AuthorityDTO;
import com.sgm.dms.fol.common.api.domain.CodeDTO;
import com.sgm.dms.fol.common.api.domain.RoleMenuDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.exception.VoNotValidException;
import com.sgm.dms.fol.common.api.services.AuthorMethodService;
import com.sgm.dms.fol.common.api.services.AuthorityService;
import com.sgm.dms.fol.common.api.services.CodeService;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.vo.AuthorityVo;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationRequest;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationUserInfo;
import com.sgm.solution.framework.dataAuthority.services.Encryptor;

@Controller
@RequestMapping("/authority/authorityamount")
public class AuthorityController extends BaseController {
	protected Logger logger = LogManager.getLogger(this.getClass());

	// 角色权限 SERVICE
	@Autowired
	private AuthorityService authorityService;

	// Code SERVICE
	@Autowired
	private CodeService codeService;
	
	@Autowired
	private AuthorMethodService authorMethodService;
	
	/**
	 * 角色权限查询
	 * 
	 * @param reserveAmountDTO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getAthoritymounts(@Validated @RequestBody AuthorityVo authorityVo, HttpServletRequest request, HttpServletResponse response) throws Throwable {    
	    //初始化数据
	    AuthorityDTO searchDto = BeanMapper.map(authorityVo, AuthorityDTO.class);
	    
	    //角色权限查询
	    List<AuthorityDTO> dtoList = authorityService.getAuthorityList(searchDto);
	    
	    //批量转换成VO
	    List<AuthorityVo> voList = BeanMapper.mapList(dtoList, AuthorityVo.class);
	    
	    //设置成前台需要的参数
	    Map<String, Object> responsedata=MapUtil.setQueryDataToMap(voList, authorityService.getAuthorityCount(searchDto));
	    
	    return responsedata;
	}
	
	/**
	 * 根据角色查询出所有的权限
	 * @param authorityVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/type/{type}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getAthoritymounts(@PathVariable long type, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//数据初始化
		AuthorityDTO dto = new AuthorityDTO();
        dto.setRoleType(type);
       
        //调用查询角色的service
        List<AuthorityDTO> dtoList = authorityService.getAuthoritymounts(dto);
        
        //批量转换成VO
        List<AuthorityVo> voList = BeanMapper.mapList(dtoList, AuthorityVo.class);
        
        return voList;
	}
	

	/** 
	 * 根据角色和岗位查询出所有的权限
	 * @author DELL
	 * TODO description
	 * @date 2016年3月28日
	 * @param type
	 * @param positionId
	 * @param request
	 * @param response
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/type/{type}/position/{positionId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getAthoritymounts(@PathVariable long type, @PathVariable String positionId, HttpServletRequest request, HttpServletResponse response) throws Throwable {	
		//数据初始化
		AuthorityDTO dto = new AuthorityDTO();
        dto.setRoleType(type);
        dto.setPositionId(positionId);
        
        //调用查询角色的service
        List<AuthorityDTO> dtoList = authorityService.getAuthorityByTypePosition(dto);
        
        //批量转换成VO
        List<AuthorityVo> voList = BeanMapper.mapList(dtoList, AuthorityVo.class);
        
        return voList;
	}
	
	/** 
	 * 查询出角色名的总数
	 * @author DELL
	 * TODO description
	 * @date 2016年3月28日
	 * @param roleName
	 * @param request
	 * @param response
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/name/{roleName}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getAuthorityCountByName(@PathVariable String roleName, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//验证数据
		checkRoleName(roleName);
		
		//调用查询出角色名的总数
		Integer num = authorityService.getAuthorityCountByName(roleName);
		
		return num;
	}
	
	private void checkRoleName(String roleName) throws Exception{
	    roleName = roleName.trim();
	    if(roleName.length() == 0) {
            throw new VoNotValidException("Role Name is Incorrect");
        }
	}

	/**
	 * 根据DTO查询数据库角色对应的权限(查询)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/Menuquery", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getMenu(@Validated @RequestBody AuthorityVo authority) throws Throwable {
        
        //数据初始化并设初始值
	    List<RoleMenuDTO> roleMenuDTOList=initData(authority);
	    
	    return roleMenuDTOList;
	}
	
	/**
	 * 初始化数据
	 */
	private List<RoleMenuDTO> initData(AuthorityVo authority) throws Exception{
	    RoleMenuDTO roleM = BeanMapper.map(authority, RoleMenuDTO.class);
	    
	    List<RoleMenuDTO> data;
	    if(null!=roleM&&!StringUtils.isEmpty(roleM.getOptType())&&POConstant.OPERATION_UPDATE.equals(roleM.getOptType().toUpperCase())){
	        //update
	        data = authorityService.findRoleMenu(roleM);
	    }else{
	        // add
	        data=new ArrayList<RoleMenuDTO>();
	    }
	    
	    //设置初始值
	    List<RoleMenuDTO> roleMenuDTOList=setRoleMenuDTOChecked(data);
	    
	    return roleMenuDTOList;
	}
	
	private List<RoleMenuDTO> setRoleMenuDTOChecked(List<RoleMenuDTO> data) throws Exception{
    	RoleMenuDTO roleM = new RoleMenuDTO();
        List<RoleMenuDTO> roleMenuDTOList = authorityService.findMenuByMenuId(roleM);
        if (null != roleMenuDTOList && roleMenuDTOList.size() > 0 && null != data
                && data.size() > 0) {
            for (RoleMenuDTO d : roleMenuDTOList) {
                for (RoleMenuDTO d2 : data) {
                    if (d.getMenuId().equals(d2.getMenuId())) {
                        d.setChecked(true);
                    }
                }
            }
        }
        
        return roleMenuDTOList;
	}
	
	/** 
	 * 根据类型查询权限
	 * @author DELL
	 * TODO description
	 * @date 2016年3月28日
	 * @param type
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/Menuquery/type/{type}", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getMenuByType(@PathVariable long type) throws Throwable {       
        List<RoleMenuDTO> responsedata=authorityService.findMenuByMenuType(type);
        return responsedata;
	}
	
	/** 
	 * 根据类型和角色查询权限
	 * @author DELL
	 * TODO description
	 * @date 2016年3月28日
	 * @param type
	 * @param roleId
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/Menuquery/type/{type}/role/{roleId}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getMenuByTypeRole(@PathVariable long type, @PathVariable long roleId) throws Throwable {
        List<RoleMenuDTO> responsedata=authorityService.findMenuByMenuTypeRoleId(type, roleId);
        return responsedata;
	}
	
	/**
	 * 
	 * @author wangfl
	 * 根据类型和角色查询方法权限信息
	 * @date 2016年4月1日
	 * @param type
	 * @param roleId
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/methodQuery/role/{roleId}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getMethodByTypeRole(@PathVariable long roleId) throws Throwable {
	    List<RoleMenuDTO> responsedata=authorMethodService.findMethodByMenuTypeRoleId(roleId);
	    return responsedata;
	}

	/**
	 * 添加角色And权限
	 * 
	 * @param reserveAmountDTO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/roleAdd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	//@SGMValidationRequest
	@ResponseBody
	public Object addAthoritymount(@RequestBody  AuthorityVo authority, HttpServletRequest request //,@SGMValidationResource AuthorityVo vo,
	                                        //@SGMValidationUserInfo  @CookieValue(CookieConstant.COOKIE_USER_ID) String userId
			) throws Throwable {

		//初始化数据
	    AuthorityDTO authorityDTO=initAuthorityDTO(authority,request,POConstant.OPERATION_ADD);

	    //新增角色And权限
	    authorityService.addAuthority(authorityDTO);
		
		return true;
	}
	
	private AuthorityDTO initAuthorityDTO(AuthorityVo authority,HttpServletRequest request,String operationType) throws Exception{
	    AuthorityDTO authorityDTO = BeanMapper.map(authority, AuthorityDTO.class);
        Long userId = CookieUtil.getUserId(request);
        authorityDTO.setUpdateBy(userId);
        
        if(POConstant.OPERATION_ADD.equals(operationType)){
            authorityDTO.setCreateBy(userId);
        }
        
        return authorityDTO;
	}
	    /**
	     * 
	     *
	     * @author Lujinglei
	     * TODO 验证签名并修改角色
	     * @date 2016年4月7日
	     * @param request
	     * @param vo
	     * @param userId
	     * @return
	     * @throws Exception
	     */
       @RequestMapping(value = "/update" , method = RequestMethod.POST, produces = "application/json")
       //@SGMValidationRequest
       public @ResponseBody Object updateAuthority(//@SGMValidationResource 
    		   @RequestBody AuthorityVo vo, HttpServletRequest request
    		   //, @SGMValidationUserInfo @CookieValue(CookieConstant.COOKIE_USER_ID) String userId
               ) throws Exception {
                //数据初始化
               AuthorityDTO authorityDTO=initAuthorityDTO(vo, request, POConstant.OPERATION_UPDATE);
               //更新角色
               authorityService.updateAuthority(authorityDTO);
    
               return true;
       }
       /**
        * 
        *
        * @author wangfl
        * TODO 修改权限方法
        * @date 2016年4月11日
        * @param authority
        * @param request
        * @param response
        * @return
        * @throws Throwable
        */
	@RequestMapping(value = "/updateAuthorityMethod", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	//@SGMValidationRequest
	@ResponseBody
	public Object updateAuthorityMethod(@RequestBody AuthorityVo authority, HttpServletRequest request//, @SGMValidationResource AuthorityVo vo,
	                                   // @SGMValidationUserInfo @CookieValue(CookieConstant.COOKIE_USER_ID) String userId
			)throws Throwable {
	    //数据初始化
	    AuthorityDTO authorityDTO=initAuthorityDTO(authority, request, POConstant.OPERATION_UPDATE);
	    
	    //更新角色
	    authorityService.updateAuthorityMethod(authorityDTO);
	    return true;
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
		String[] columns = { "角色类型" };
		 List<List<?>> codeDTOlist = new ArrayList<List<?>>();
	        for (int i = 0; i < columns.length; i++) {
	            String column = columns[i];
	            List<CodeDTO> codeDTOs = codeService.findCodeByTypeName(column);
	            codeDTOlist.add(codeDTOs);
	        }
	        return codeDTOlist;
	}

	/**
	 * 删除角色
	 * 
	 * @param reserveAmountDTO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delAuthority", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object delAthoritymount(@Validated @RequestBody AuthorityVo authorityVo, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//数据初始化
	    AuthorityDTO searchDto = BeanMapper.map(authorityVo, AuthorityDTO.class);
		searchDto.setRoleStatus(2L);
		
		//删除角色
		authorityService.delAuthority(searchDto);
		
		return true;
	}
	
	@RequestMapping(value="/code/type/{type}", method={RequestMethod.POST,RequestMethod.GET}, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getCodeByType(@PathVariable long type, HttpServletRequest request) throws Throwable {
		return codeService.findCodeByType(type);
	}
    /*
     * 
    *
    * @author Lujinglei
    * TODO 获取签名sign返回vo对象
    * @date 2016年4月7日
    * @param authority
    * @param request
    * @param userId
    * @return
    * @throws Throwable
     */
    @RequestMapping(value = "/getSign", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getSign(@Validated @RequestBody AuthorityVo authority, HttpServletRequest request,@CookieValue(CookieConstant.COOKIE_USER_ID) String userId) throws Throwable {

        AuthorityVo result=new AuthorityVo();
        result.setRoleId(authority.getRoleId());
        result.setRoleType(authority.getRoleType());
        result.setRoleDesc(authority.getRoleDesc());
        result.setMenuId(authority.getMenuId());  
        String[] refVales=new String[]{String.valueOf(authority.getRoleId()),String.valueOf(authority.getRoleType()),authority.getRoleDesc(),authority.getMenuId()};
        String sign=Encryptor.generateSignFromResource(userId,refVales);
        result.setSign(sign);

        return result;
    }
    

}
