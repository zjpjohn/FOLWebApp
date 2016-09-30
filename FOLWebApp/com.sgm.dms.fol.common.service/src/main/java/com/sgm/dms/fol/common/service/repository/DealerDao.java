/**
* @ClassName: reserveAmountDao
* @Description: 角色权限 DAO
* @author:LuHui
* @date: 2015年10月19日 上午10:43:42
* 
* 
*/
package com.sgm.dms.fol.common.service.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sgm.dms.fol.common.api.domain.DealerInfoDTO;
import com.sgm.dms.fol.common.service.domains.DealerInfo;
import com.sgm.dms.fol.common.service.domains.DealerInfoExample;
import com.sgm.dms.fol.common.service.domains.DealerInfoPo;




public interface DealerDao {
	/////////////////////////////////自动生成Dao////start//////////////////////////////
	int countByExample(DealerInfoExample example);

    int deleteByExample(DealerInfoExample example);

    int insert(DealerInfo record);

    int insertSelective(DealerInfo record);

    List<DealerInfo> selectByExample(DealerInfoExample example);

    int updateByExampleSelective(@Param("record") DealerInfo record, @Param("example") DealerInfoExample example);

    int updateByExample(@Param("record") DealerInfo record, @Param("example") DealerInfoExample example);
    /////////////////////////////////自动生成Dao////end//////////////////////////////
	
	//根据SAP_CODE区间查找经销商信息
	public List<DealerInfoPo> getDealerInfoBySapCodeInterval(DealerInfoDTO dealerinfodto) throws SQLException;
	
	//根据SAP_CODE查找单个经销商信息
    public DealerInfoPo getDealerInfoBySapCode(DealerInfoDTO dealerinfodto) throws SQLException;
    
    //根据Dealer_Id查找单个经销商信息
    public DealerInfoPo getDealerInfoByDealerId(DealerInfoDTO dealerinfodto) throws SQLException;
    //分页查询
    List<DealerInfo> selectPageListByExample(DealerInfoExample example);
}
