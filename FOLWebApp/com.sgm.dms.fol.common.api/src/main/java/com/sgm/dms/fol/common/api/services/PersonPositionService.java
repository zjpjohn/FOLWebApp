/**
* @ClassName: ReserveAmountService
* @Description: 岗位 INTERFACE
* @author: luhui
* @date: 2015年10月9日 上午10:10:43
* 
* 
*/
package com.sgm.dms.fol.common.api.services;

import java.util.List;

import com.sgm.dms.fol.common.api.domain.PersonRoleDTO;
import com.sgm.dms.fol.common.api.domain.UserDTO;
import com.sgm.dms.fol.common.api.domain.UserSearchDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;

public interface PersonPositionService {
/**
 * @ClassName: ReserveAmountService
 * @Description: 接口服务
 * @author: LuHui
 * @date: 2015年10月19日 下午 14:33
 * 
 */
	
	//查询人员
	public List<PersonRoleDTO> findA(PersonRoleDTO pRole) throws ServiceBizException;
	//
	public int updataPersonRole(PersonRoleDTO per)throws ServiceBizException;
	
	public long searchUsersCount(UserSearchDTO dto) throws ServiceBizException;
	public List<UserDTO> searchUsersList(UserSearchDTO dto) throws ServiceBizException;
}
