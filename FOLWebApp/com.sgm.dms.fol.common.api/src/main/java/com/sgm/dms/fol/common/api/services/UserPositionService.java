/**
 * 
 * @ClassName: MenuService
 * @Description: TODO
 * @author: wangyixin
 * @date: 2015年6月24日 上午10:04:00
 *
 */
package com.sgm.dms.fol.common.api.services;

import java.util.List;

import com.sgm.dms.fol.common.api.domain.UserPositionDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;




public interface UserPositionService {
	/**
	 * 
	 * @Title: deteteUserPositionByUserId
	 * @Description: 删除人员岗位对应关系
	 * @param: TODO
	 * @return: void
	 * @author: wangyx
	 * @date: 2015年7月5日 上午10:44:01
	 * @throws
	 */
	//void deteteUserPositionByUserId(long userId) throws ServiceAppException;
	/**
	 * 
	 * @Title: insertUserPosition
	 * @Description: 插入人员岗位对应关系
	 * @param: TODO
	 * @return: void
	 * @author: wangyx
	 * @date: 2015年7月5日 上午10:43:57
	 * @throws
	 */
	//void insertUserPosition(UserDTO userAddDTO) throws ServiceAppException;
	/**
	 * 
	 * @Title: getListUserPositionById
	 * @Description: 获取人员岗位对应关系列表
	 * @param: TODO
	 * @return: List<UserPositionDTO>
	 * @author: wangyx
	 * @date: 2015年7月5日 上午10:44:06
	 * @throws
	 */
	List<UserPositionDTO> getListUserPositionById(long id) throws ServiceAppException;
}
