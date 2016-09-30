package com.sgm.dms.fol.invoice.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sgm.dms.fol.invoice.domain.WrOrder;
import com.sgm.dms.fol.invoice.dto.WrOrderDTO;

public interface WrOrderDao {

	/**
	 * 
	 * @author wangfl
	 * 索赔单插入
	 * @date 2016年5月13日
	 * @param wrOrder
	 * @return
	 */
    int insert(WrOrder wrOrder);
    
    /**
     * 
     * @author wangfl
     * 根据tsId获取发票索赔单列表明细
     * @date 2016年5月16日
     * @param tsId
     * @return
     */
    List<WrOrderDTO> findWrOrderListByTsId(@Param("tsId") String tsId);
    
    /**
     * 
     * @author wangfl
     * 根据tsId删除索赔单
     * @date 2016年5月17日
     * @param tsId
     * @return
     */
    int delWrOrderByTsId(@Param("tsId") String tsId);
    
    /**
     * 
     * @author wangfl
     * 批量插入索赔单
     * @date 2016年5月25日
     * @param wrOrderList
     * @return
     */
    int insertList(@Param("wrOrderList") List<WrOrder> wrOrderList);
    
    /**
     * 
     * @author wangfl
     * 根据WR_NO，RO_NO，LINE_NO查询索赔单列表
     * @date 2016年5月25日
     * @param wrOrderDtoList
     * @return
     */
    List<WrOrderDTO> findWrOrderListByPrimary(@Param("wrOrderDtoList") List<WrOrderDTO> wrOrderDtoList);
}