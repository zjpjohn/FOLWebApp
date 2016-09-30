/**
* @ClassName: reserveAmountDao
* @Description: 岗位DAO
* @author: LuHui
* @date: 2015年10月19日 上午10:43:42
* 
* 
*/
package com.sgm.dms.fol.common.service.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sgm.dms.fol.common.api.domain.PositionDTO;
import com.sgm.dms.fol.common.api.domain.PositionRoleDTO;
import com.sgm.dms.fol.common.api.domain.PositionSwitchDTO;
import com.sgm.dms.fol.common.api.domain.UserDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;




public interface PositionDao {
	
	//根据条件查询岗位角色
	public List<PositionDTO> getPositionByWhere(PositionDTO positionDTO) throws SQLException;
	
	//根据条件查询角色权限列表-分页
	public List<PositionDTO> getPositionList(PositionDTO positionDTO) throws SQLException;
	
	//根据条件查询角色权限数量-分页
	public long getPositionCount(PositionDTO positionDTO) throws SQLException;
	
	//新增岗位
	public int addPosition(PositionDTO positionDTO) throws SQLException;
	
	//修改岗位
	public int updatePosition(PositionDTO positionDTO) throws SQLException;
	
	//修改岗位
	public int deletePosition(PositionDTO positionDTO) throws SQLException;
	
	//删除岗位角色对应关系
	public int deteteRoleByPositionId(String positionId) throws SQLException;
	
	//新增岗位角色对应关系
	public int addPositionRole(PositionRoleDTO positionRoleDTO) throws SQLException;
	
	//查询角色
	public List<PositionRoleDTO> findAu(PositionRoleDTO pRole) throws SQLException;
	
	/*//根据岗位额修改角色(添加)
	public List<AuthorityDTO> delAuth(AuthorityDTO authorityDTO) throws SQLException;
	
	//根据岗位额修改角色(删除)
	public List<AuthorityDTO> addAuth(AuthorityDTO authorityDTO) throws SQLException;*/

	public int updateAuthorityAdd(PositionRoleDTO pRole);

	public int updateAuthorityDel(PositionRoleDTO pRole);
	
	public PositionRoleDTO checkRoleMenu(long roleId, String positionId);
	/**
	 * 岗位切换列表查询(sgm)
	 * @param positionSwitchDto
	 * @return
	 * @throws SQLException
	 */
	public List<PositionSwitchDTO> getSystemmounts(PositionSwitchDTO positionSwitchDto) throws SQLException;
	/**
	 * 岗位切换列表查询(dealer)
	 */
	public List<PositionSwitchDTO> getPositionSwitch(PositionSwitchDTO positionSwitchDto) throws SQLException;

	/**
	 * 根据userId查询出Position
	 */
	public List<PositionDTO> getPositionsByUserId(UserDTO userDTO) throws SQLException;
	
	/**
     * 根据该用户账户查询出所拥有的岗位
     * @param userDTO
     * @return
     * @throws ServiceBizException
     */
    public List<PositionDTO> getPositionsByUserAccount (UserDTO userDTO) throws SQLException;
	
	public List<PositionDTO> getPositionsChecked (@Param("type") int type, @Param("userAccount") String userAccount) throws SQLException;
	
	public int getPositionCountByName(String name) throws SQLException;
	
	

}

