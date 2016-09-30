package com.sgm.dms.fol.bankTicket.dto;

import java.util.List;


/****
 * 调用SAP生成贴息号码request
*
* @author zhang bao
* TODO description
* @date 2016年1月14日
 */
public class BankTicketBusinessInquiriesRequestDTO {
	private String z_bukrs;//公司代码
	private String z_blart;//DN代买是银票数据 DA
	private String z_budat;
	private String z_waers;
	private String z_bldat;
	private String z_monat;
	private List<IT_ITEM> it_item;
	
	public String getZ_bukrs() {
		return z_bukrs;
	}
	public void setZ_bukrs(String z_bukrs) {
		this.z_bukrs = z_bukrs;
	}
	public String getZ_blart() {
		return z_blart;
	}
	public void setZ_blart(String z_blart) {
		this.z_blart = z_blart;
	}
	public String getZ_budat() {
		return z_budat;
	}
	public void setZ_budat(String z_budat) {
		this.z_budat = z_budat;
	}
	public String getZ_waers() {
		return z_waers;
	}
	public void setZ_waers(String z_waers) {
		this.z_waers = z_waers;
	}
	public String getZ_bldat() {
		return z_bldat;
	}
	public void setZ_bldat(String z_bldat) {
		this.z_bldat = z_bldat;
	}
	public String getZ_monat() {
		return z_monat;
	}
	public void setZ_monat(String z_monat) {
		this.z_monat = z_monat;
	}
	public List<IT_ITEM> getIt_item() {
		return it_item;
	}
	public void setIt_item(List<IT_ITEM> it_item) {
		this.it_item = it_item;
	}
	
	
	
}
