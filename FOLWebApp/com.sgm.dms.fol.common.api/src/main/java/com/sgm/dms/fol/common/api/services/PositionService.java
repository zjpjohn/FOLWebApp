/**
* @ClassName: ReserveAmountService
* @Description: 岗位 INTERFACE
* @author: luhui
* @date: 2015年10月9日 上午10:10:43
* 
* 
*/
package com.sgm.dms.fol.common.api.services;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sgm.dms.fol.common.api.domain.PositionDTO;
import com.sgm.dms.fol.common.api.domain.PositionRoleDTO;
import com.sgm.dms.fol.common.api.domain.PositionSwitchDTO;
import com.sgm.dms.fol.common.api.domain.UserDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;

public interface PositionService {
/**
 * @ClassName: ReserveAmountService
 * @Description: 岗位  接口服务
 * @author: LuHui
 * @date: 2015年10月19日 下午 14:33
 * 
 */
	//查询岗位列表-不分页
	public List<PositionDTO> getPositions(PositionDTO positionDTO) throws ServiceBizException;
	//查询岗位列表-分页
	public List<PositionDTO> getPositionList(PositionDTO positionDTO) throws ServiceBizException;
	//查询岗位列表数量-分页
	public long getPositionCount(PositionDTO positionDTO) throws ServiceBizException;
	//新增岗位
	public int addPosition(PositionDTO positionDTO) throws ServiceBizException;
	//修改岗位
	public int updatePosition(PositionDTO positionDTO) throws ServiceBizException;
	//删除岗位
	public int deletePosition(PositionDTO positionDTO) throws ServiceBizException;
	//查询角色
	public List<PositionRoleDTO> findA(PositionRoleDTO pRole) throws ServiceBizException;
	//修改岗位对应 的角色信息
	public int updateAuthorityAdd(PositionRoleDTO pRole) throws ServiceBizException;
		
	//修改岗位对应 的角色信息
	public int updateAuthorityDel(PositionRoleDTO pRole) throws ServiceBizException;
	
	public boolean checkRoleMenu(long roleId, String menuId);	
	
	/**
	 * 根据该用户查询出所拥有的岗位
	 * @param userDTO
	 * @return
	 * @throws ServiceBizException
	 */
	public List<PositionDTO> getPositionsByUserId (UserDTO userDTO) throws SQLException;
	
	/**
     * 根据该用户账户查询出所拥有的岗位
     * @param userDTO
     * @return
     * @throws ServiceBizException
     */
    public List<PositionDTO> getPositionsByUserAccount (UserDTO userDTO) throws ServiceBizException;
	
	public List<PositionDTO> getPositionsChecked (@Param("type") int type, @Param("userAccount") String userAccount) throws ServiceBizException;
	
	public int getPositionCountByName(String name) throws SQLException;
	
	public List<PositionSwitchDTO> getSystemmounts(PositionSwitchDTO positionSwitchDto) throws ServiceBizException;

	public List<PositionSwitchDTO> getPositionSwitch(PositionSwitchDTO positionSwitchDto) throws ServiceBizException;

}
