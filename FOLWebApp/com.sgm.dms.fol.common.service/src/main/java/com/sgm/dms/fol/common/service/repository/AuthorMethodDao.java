package com.sgm.dms.fol.common.service.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sgm.dms.fol.common.api.domain.AuthorMethodDTO;
import com.sgm.dms.fol.common.api.domain.RoleMenuDTO;

public interface AuthorMethodDao {
    
    
    /**
     * 
     * @author wangfl
     * 分页查询AuthorMethod列表
     * @date 2016年3月31日
     * @param authorMethodDTO
     * @return
     * @throws SQLException
     */
    List<AuthorMethodDTO> selectAuthorMethodPageList(AuthorMethodDTO authorMethodDTO) throws SQLException;
    
    /**
     * 
     * @author wangfl
     * 根据条件查询AuthorMethod总数
     * @date 2016年3月31日
     * @param authorMethodDTO
     * @return
     * @throws SQLException
     */
    long selectAuthorMethodListCount(AuthorMethodDTO authorMethodDTO) throws SQLException;
    
    /**
     * 
     * @author wangfl
     * 新增
     * @date 2016年3月31日
     * @param authorMethodDTO
     * @throws SQLException
     */
    void insert(AuthorMethodDTO authorMethodDTO) throws SQLException;
    
    /**
     * 
     * @author wangfl
     * 根据id修改
     * @date 2016年3月31日
     * @param authorMethodDTO
     * @throws SQLException
     */
    void update(AuthorMethodDTO authorMethodDTO) throws SQLException;
    
    /**
     * 
     * @author wangfl
     * 根据菜单类型查询方法权限列表
     * @date 2016年4月1日
     * @param menuType
     * @param roleId
     * @return
     * @throws SQLException
     */
    public List<RoleMenuDTO> findMethodByMenuTypeRoleId(@Param("roleId") long roleId) throws SQLException;

}