/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.service
*
* @File name : DealerInfoPo.java
*
* @Author : shenweiwei
*
* @Date : 2015年10月22日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年10月22日    shenweiwei    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.common.service.domains;

import java.sql.Date;
import java.sql.Timestamp;


/*
 *
 * @author shenweiwei
 * TODO description
 * @date 2015年10月22日
 */

public class DealerInfoPo extends BasePo{
    private String dealerCode; //经销商编号
    private String dealerFullName; //经销商全名
    private String brandName;  //品牌名称
    private String province;    //省份
    private String city;    //城市
    private String companyAddress; //地址
    private String businessPhone;  //公司电话
    private String fax; //传真
    private String hotLine; //热线
    private String dealerName; //经销商简称
    private String plant;   //工厂
    private String dealerWarrantyCode; // 索赔编号
    private String sapCode; //SAP编号
    private String postalCode; //邮编
    private String email; //EMAIL
    private String dealerStatus; //DEALER状态
    private String region; //区域
    private String fmc; //FMC编号
    private Date onlineDate; //上线日期
    private Date openDate; //开业日期
    private String starGrade; //星级
    private String rank; //等级
    private String bookingPhone; //预约电话
    private String companyHomepage; //主页
    private String remark; //备注
    private String onlineStatus; //在线状态
    private String bank; //开户银行
    private String bankAccount; //开户帐号
    private String dutyNumber; //税号
    private String contactName; //联系人
    private String phoneNumber; //联系人电话
    private String dealerId; //经销商ID
    private String groupName;  //集团名称
    private String dealerType; //经销商类型代码
    private String dealerTypeName; //经销商类型名称
    private String provinceCode; //省份代码
    private String landCityCode;  //城市代码
    private String townCityCode; //县级代码
    private Timestamp operateDate; //时间戳
    private String statusCode; //状态代码
    private String serviceCode; //售后代码
    private Date serviceOpenDate; //售后开业日期
    private String dmesRegistAddress; //DMES注册地址
    private Integer brandId; //品牌ID
    private Long sfrRegionId; //SFR区域ID
    private String Phone; //联系电话
    private Long sgmRegionId; //SGM区域ID
    private String asccode; //ASC代码
    private String accountName; //账号名称
    
    public String getDealerCode() {
        return dealerCode;
    }
    
    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }
    
    public String getDealerFullName() {
        return dealerFullName;
    }
    
    public void setDealerFullName(String dealerFullName) {
        this.dealerFullName = dealerFullName;
    }
    
    public String getBrandName() {
        return brandName;
    }
    
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    
    public String getProvince() {
        return province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCompanyAddress() {
        return companyAddress;
    }
    
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
    
    public String getBusinessPhone() {
        return businessPhone;
    }
    
    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }
    
    public String getFax() {
        return fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }
    
    public String getHotLine() {
        return hotLine;
    }
    
    public void setHotLine(String hotLine) {
        this.hotLine = hotLine;
    }
    
    public String getDealerName() {
        return dealerName;
    }
    
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }
    
    public String getPlant() {
        return plant;
    }
    
    public void setPlant(String plant) {
        this.plant = plant;
    }
    
    public String getDealerWarrantyCode() {
        return dealerWarrantyCode;
    }
    
    public void setDealerWarrantyCode(String dealerWarrantyCode) {
        this.dealerWarrantyCode = dealerWarrantyCode;
    }
    
    public String getSapCode() {
        return sapCode;
    }
    
    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }
    
    public String getPostalCode() {
        return postalCode;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDealerStatus() {
        return dealerStatus;
    }
    
    public void setDealerStatus(String dealerStatus) {
        this.dealerStatus = dealerStatus;
    }
    
    public String getRegion() {
        return region;
    }
    
    public void setRegion(String region) {
        this.region = region;
    }
    
    public String getFmc() {
        return fmc;
    }
    
    public void setFmc(String fmc) {
        this.fmc = fmc;
    }
    
    public Date getOnlineDate() {
        return onlineDate;
    }
    
    public void setOnlineDate(Date onlineDate) {
        this.onlineDate = onlineDate;
    }
    
    public Date getOpenDate() {
        return openDate;
    }
    
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }
    
    public String getStarGrade() {
        return starGrade;
    }
    
    public void setStarGrade(String starGrade) {
        this.starGrade = starGrade;
    }
    
    public String getRank() {
        return rank;
    }
    
    public void setRank(String rank) {
        this.rank = rank;
    }
    
    public String getBookingPhone() {
        return bookingPhone;
    }
    
    public void setBookingPhone(String bookingPhone) {
        this.bookingPhone = bookingPhone;
    }
    
    public String getCompanyHomepage() {
        return companyHomepage;
    }
    
    public void setCompanyHomepage(String companyHomepage) {
        this.companyHomepage = companyHomepage;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public String getOnlineStatus() {
        return onlineStatus;
    }
    
    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }
    
    public String getBank() {
        return bank;
    }
    
    public void setBank(String bank) {
        this.bank = bank;
    }
    
    public String getBankAccount() {
        return bankAccount;
    }
    
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
    
    public String getDutyNumber() {
        return dutyNumber;
    }
    
    public void setDutyNumber(String dutyNumber) {
        this.dutyNumber = dutyNumber;
    }
    
    public String getContactName() {
        return contactName;
    }
    
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getDealerId() {
        return dealerId;
    }
    
    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }
    
    public String getGroupName() {
        return groupName;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    public String getDealerType() {
        return dealerType;
    }
    
    public void setDealerType(String dealerType) {
        this.dealerType = dealerType;
    }
    
    public String getDealerTypeName() {
        return dealerTypeName;
    }
    
    public void setDealerTypeName(String dealerTypeName) {
        this.dealerTypeName = dealerTypeName;
    }
    
    public String getProvinceCode() {
        return provinceCode;
    }
    
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
    
    public String getLandCityCode() {
        return landCityCode;
    }
    
    public void setLandCityCode(String landCityCode) {
        this.landCityCode = landCityCode;
    }
    
    public String getTownCityCode() {
        return townCityCode;
    }
    
    public void setTownCityCode(String townCityCode) {
        this.townCityCode = townCityCode;
    }
    
    public Timestamp getOperateDate() {
        return operateDate;
    }
    
    public void setOperateDate(Timestamp operateDate) {
        this.operateDate = operateDate;
    }
    
    public String getStatusCode() {
        return statusCode;
    }
    
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    
    public String getServiceCode() {
        return serviceCode;
    }
    
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
    
    public Date getServiceOpenDate() {
        return serviceOpenDate;
    }
    
    public void setServiceOpenDate(Date serviceOpenDate) {
        this.serviceOpenDate = serviceOpenDate;
    }
    
    public String getDmesRegistAddress() {
        return dmesRegistAddress;
    }
    
    public void setDmesRegistAddress(String dmesRegistAddress) {
        this.dmesRegistAddress = dmesRegistAddress;
    }
    
    public Integer getBrandId() {
        return brandId;
    }
    
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }
    
    public Long getSfrRegionId() {
        return sfrRegionId;
    }
    
    public void setSfrRegionId(Long sfrRegionId) {
        this.sfrRegionId = sfrRegionId;
    }
    
    public String getPhone() {
        return Phone;
    }
    
    public void setPhone(String phone) {
        Phone = phone;
    }
    
    public Long getSgmRegionId() {
        return sgmRegionId;
    }
    
    public void setSgmRegionId(Long sgmRegionId) {
        this.sgmRegionId = sgmRegionId;
    }
    
    public String getAsccode() {
        return asccode;
    }
    
    public void setAsccode(String asccode) {
        this.asccode = asccode;
    }
    
    public String getAccountName() {
        return accountName;
    }
    
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
}
