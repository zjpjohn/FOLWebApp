package com.sgm.dms.fol.bankTicket.dto;

public class IT_ITEM {
	private String sequence;
	private String bschl;//09（借）19（贷
	private String hkont;//sapCode
	private String umskz;//W
	private double dmbtr;//金额
	private String sgtxt;
	private String zfbdt;//到期日期
	private String wbank;//银行名称
	private String kostl;//
	private String wdate;//出票日期
	private String xblnr;//从银票号码截取
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getBschl() {
		return bschl;
	}
	public void setBschl(String bschl) {
		this.bschl = bschl;
	}
	public String getHkont() {
		return hkont;
	}
	public void setHkont(String hkont) {
		this.hkont = hkont;
	}
	public String getUmskz() {
		return umskz;
	}
	public void setUmskz(String umskz) {
		this.umskz = umskz;
	}
	
	public double getDmbtr() {
		return dmbtr;
	}
	public void setDmbtr(double dmbtr) {
		this.dmbtr = dmbtr;
	}
	public String getSgtxt() {
		return sgtxt;
	}
	public void setSgtxt(String sgtxt) {
		this.sgtxt = sgtxt;
	}
	public String getZfbdt() {
		return zfbdt;
	}
	public void setZfbdt(String zfbdt) {
		this.zfbdt = zfbdt;
	}
	public String getXblnr() {
		return xblnr;
	}
	public void setXblnr(String xblnr) {
		this.xblnr = xblnr;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getWbank() {
		return wbank;
	}
	public void setWbank(String wbank) {
		this.wbank = wbank;
	}
	public String getKostl() {
		return kostl;
	}
	public void setKostl(String kostl) {
		this.kostl = kostl;
	}
	
}
