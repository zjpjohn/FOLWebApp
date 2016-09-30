package com.sgm.dms.fol.invoice.restclient.wol_claimOrder.dto;

import java.util.List;

/**
 * 
 * @author wangfl
 * 索赔单列表响应dto
 * @date 2016年6月2日
 */
public class ClaimOrderRespDto {
	/**索赔单列表*/
    private List<ClaimOrderDto> claimOrderResultDTOList;
    /**结果集总数*/
    private int total;
    
	public List<ClaimOrderDto> getClaimOrderResultDTOList() {
		return claimOrderResultDTOList;
	}
	public void setClaimOrderResultDTOList(List<ClaimOrderDto> claimOrderResultDTOList) {
		this.claimOrderResultDTOList = claimOrderResultDTOList;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
    
}
