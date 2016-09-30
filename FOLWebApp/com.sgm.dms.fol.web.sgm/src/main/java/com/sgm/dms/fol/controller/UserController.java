package com.sgm.dms.fol.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.common.api.constants.CookieConstant;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.domain.MenuDTO;
import com.sgm.dms.fol.common.api.domain.RoleDTO;
import com.sgm.dms.fol.common.api.domain.UserDTO;
import com.sgm.dms.fol.common.api.domain.UserSearchDTO;
import com.sgm.dms.fol.common.api.exception.VoNotValidException;
import com.sgm.dms.fol.common.api.services.MenuService;
import com.sgm.dms.fol.common.api.services.RoleService;
import com.sgm.dms.fol.common.api.services.UserService;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.vo.UserSearchVo;
import com.sgm.dms.fol.vo.UserVO;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;
import com.sgm.solution.framework.dataAuthority.services.Encryptor;


@Controller
@RequestMapping("/common")
public class UserController extends BaseController{
	protected Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws Throwable{
		Cookie[] cookies=request.getCookies();
		
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				Cookie newCookie=new Cookie(cookie.getName(), null);
				newCookie.setPath("/");
				newCookie.setMaxAge(0);
				response.addCookie(newCookie);
			}
		}
		request.getSession().invalidate();
		
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		logger.info("local logout success");
		response.sendRedirect(request.getContextPath()+"/logout.html");
	}
	@RequestMapping("/login")
	public void login(HttpServletRequest request,HttpServletResponse response) throws Throwable{
		logger.info("local login success");
		response.sendRedirect(request.getContextPath()+"/index.html");
	}
	@RequestMapping(value="/users", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public UserSearchDTO getUser(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		Long userId = CookieUtil.getUserId(request);
		return getUserWithRole(userId);
	}
	
	@RequestMapping(value="/menus", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<MenuDTO> getMenu(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		List<MenuDTO> menus=menuService.getActiveMenus(CookieUtil.getUserId(request),CookieUtil.getPositionId(request));
		return menus;
	}
	
	private UserSearchDTO getUserWithRole(Long userId) throws Exception {
		UserSearchDTO user = userService.getUserByUserId(userId);
		List<RoleDTO> roles = roleService.getRolesByUser(userId);
		
		List<String> permissions = new ArrayList<String>();
		for(RoleDTO role : roles) {
			permissions.add(role.getRoleName());
		}
		user.setPermission(permissions);
		
		return user;
	}
	
	@RequestMapping(value="/users/query",method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public Object listUsers( @RequestBody UserSearchVo vo,HttpServletRequest request)  throws Throwable {
	    //数据初始化
		UserSearchDTO dto = BeanMapper.map(vo, UserSearchDTO.class);
		
		//调用查询所有用户SERVICE
		List<UserDTO> dtoList = userService.searchUsersList(dto);
		
		//批量转换成VO
		List<UserVO> volist = BeanMapper.mapList(dtoList, UserVO.class);
		
		//设置成前台需要的数据
		Map<String, Object> responsedata = MapUtil.setQueryDataToMap(volist, userService.searchUsersCount(dto));
		
		return responsedata;
	}
	
	/** 
	 * 新增用户
	 * @author DELL
	 * TODO description
	 * @date 2016年3月28日
	 * @param vo
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/users/add",method=RequestMethod.POST,produces="application/json")
	//@SGMValidationRequest
	@ResponseBody
	public Object addUser(HttpServletRequest request,//@SGMValidationResource 
			@RequestBody UserVO vo
                          //,@SGMValidationUserInfo @CookieValue(CookieConstant.COOKIE_USER_ID) String userId
                          )  throws Throwable {
		//初始化数据
	    UserDTO userDTO=initUserDTO(vo, request, POConstant.OPERATION_ADD);

		//调用新增用户SERVICE
		userService.addUser(userDTO);
		
		return true;
	}
	
	/** 
	 * 初始化用户数据
	 * @author DELL
	 * TODO description
	 * @date 2016年3月28日
	 * @param vo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private UserDTO initUserDTO(UserVO vo,HttpServletRequest request,String operationType)throws Exception{
	    UserDTO dto = BeanMapper.map(vo, UserDTO.class);
        Long userId = CookieUtil.getUserId(request);
        dto.setUpdateBy(userId);
        if(POConstant.OPERATION_ADD.equals(operationType)){
            dto.setCreateBy(userId);
        }
        
        if(POConstant.OPERATION_DELETE.equals(operationType)){
            dto.setUserStatus(POConstant.IS_NOT_STATUS);
        }
        return dto;
	}	

    /*
     * 
    *
    * @author Lujinglei
    * TODO 更新用户
    * @date 2016年4月6日
    * @param request
    * @param vo
    * @param userId
    * @return
    * @throws Exception
     */
    @RequestMapping(value = "/users/update" , method = RequestMethod.POST, produces = "application/json")
    //@SGMValidationRequest
    public @ResponseBody Object updateUser(HttpServletRequest request,@SGMValidationResource @RequestBody UserVO vo
           // ,@SGMValidationUserInfo @CookieValue(CookieConstant.COOKIE_USER_ID) String userId
            ) throws Exception {
		// 初始化用户信息
		UserDTO userDTO = initUserDTO(vo, request, POConstant.OPERATION_UPDATE);
		// 调用更新用户SERVICE
		userService.updateUser(userDTO);
		return true;
    }

	/** 
	 * 删除用户
	 * @author DELL
	 * TODO description
	 * @date 2016年3月28日
	 * @param vo
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/users/delete",method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public Object deleteUser(@RequestBody UserVO vo,HttpServletRequest request)  throws Throwable {
		//初始化用户信息
	    UserDTO dto = initUserDTO(vo, request, POConstant.OPERATION_DELETE);
		
	    //调用删除用户SERVICE
	    userService.deleteUser(dto);
		
		return true;
	}
	
	/** 
	 * 根据SSO_ACCOUNT获取用户总数
	 * @author DELL
	 * TODO description
	 * @date 2016年3月28日
	 * @param userAccount
	 * @param request
	 * @param response
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "users/account/{userAccount}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getAuthorityCountByName(@PathVariable String userAccount, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//验证数据
	    checkData(userAccount);
	    
		//调用获取用户总数SERVICE
		Integer num=userService.getUserCountByAccount(userAccount);
		
		return num;
	}
	
	private void checkData(String userAccount) throws Exception{
	    userAccount = userAccount.trim();
	    if(userAccount.length() == 0) {
            throw new VoNotValidException("User Account is Incorrect");
        }
	}
    /*
     * 
    *
    * @author Lujinglei
    * TODO 获取签名sign
    * @date 2016年4月6日
    * @param vo
    * @param request
    * @param userId
    * @return
    * @throws Throwable
     */
    @RequestMapping(value="/users/getSign",method=RequestMethod.POST,produces="application/json")
    @ResponseBody
    public Object getSign(@RequestBody UserVO vo,HttpServletRequest request,@CookieValue(CookieConstant.COOKIE_USER_ID) String userId)  throws Throwable {

        UserVO result=new UserVO();
        result.setUserId(vo.getUserId());
        result.setCustomerId(vo.getCustomerId());
       // result.setUserAccount(userAccount);
        result.setPassword(vo.getPassword());
        result.setUserType(vo.getUserType());
        result.setUserStatus(vo.getUserStatus());
        String[] refVales=new String[]{String.valueOf(vo.getUserId()),vo.getCustomerId(),vo.getPassword(),String.valueOf(vo.getUserType()),String.valueOf(vo.getUserStatus())};
        String sign=Encryptor.generateSignFromResource(userId,refVales);
        result.setSign(sign);

        return result;
    }
    

}
