/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.api
*
* @File name : DealerInfoDTO.java
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
	
package com.sgm.dms.fol.common.api.domain;

import java.sql.Date;
import java.sql.Timestamp;


/*
 *
 * @author shenweiwei
 * TODO description
 * @date 2015年10月22日
 */

public class DealerInfoDTO extends BaseDTO{
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
    private String startSapCode;//用来查询dealerCode区间(开始位)
    private String endSapCode;//用来查询dealerCode区间(结束位)
    private Integer beginNo;
    private Integer endNo;
    
    /**
     * @return the dealerCode
     */
    public String getDealerCode() {
        return dealerCode;
    }
    
    /**
     * @param dealerCode the dealerCode to set
     */
    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }
    
    /**
     * @return the dealerFullName
     */
    public String getDealerFullName() {
        return dealerFullName;
    }
    
    /**
     * @param dealerFullName the dealerFullName to set
     */
    public void setDealerFullName(String dealerFullName) {
        this.dealerFullName = dealerFullName;
    }
    
    /**
     * @return the brandName
     */
    public String getBrandName() {
        return brandName;
    }
    
    /**
     * @param brandName the brandName to set
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    
    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }
    
    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }
    
    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }
    
    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    /**
     * @return the companyAddress
     */
    public String getCompanyAddress() {
        return companyAddress;
    }
    
    /**
     * @param companyAddress the companyAddress to set
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
    
    /**
     * @return the businessPhone
     */
    public String getBusinessPhone() {
        return businessPhone;
    }
    
    /**
     * @param businessPhone the businessPhone to set
     */
    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }
    
    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }
    
    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }
    
    /**
     * @return the hotLine
     */
    public String getHotLine() {
        return hotLine;
    }
    
    /**
     * @param hotLine the hotLine to set
     */
    public void setHotLine(String hotLine) {
        this.hotLine = hotLine;
    }
    
    /**
     * @return the dealerName
     */
    public String getDealerName() {
        return dealerName;
    }
    
    /**
     * @param dealerName the dealerName to set
     */
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }
    
    /**
     * @return the plant
     */
    public String getPlant() {
        return plant;
    }
    
    /**
     * @param plant the plant to set
     */
    public void setPlant(String plant) {
        this.plant = plant;
    }
    
    /**
     * @return the dealerWarrantyCode
     */
    public String getDealerWarrantyCode() {
        return dealerWarrantyCode;
    }
    
    /**
     * @param dealerWarrantyCode the dealerWarrantyCode to set
     */
    public void setDealerWarrantyCode(String dealerWarrantyCode) {
        this.dealerWarrantyCode = dealerWarrantyCode;
    }
    
    /**
     * @return the sapCode
     */
    public String getSapCode() {
        return sapCode;
    }
    
    /**
     * @param sapCode the sapCode to set
     */
    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }
    
    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }
    
    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return the dealerStatus
     */
    public String getDealerStatus() {
        return dealerStatus;
    }
    
    /**
     * @param dealerStatus the dealerStatus to set
     */
    public void setDealerStatus(String dealerStatus) {
        this.dealerStatus = dealerStatus;
    }
    
    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }
    
    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }
    
    /**
     * @return the fmc
     */
    public String getFmc() {
        return fmc;
    }
    
    /**
     * @param fmc the fmc to set
     */
    public void setFmc(String fmc) {
        this.fmc = fmc;
    }
    
    /**
     * @return the onlineDate
     */
    public Date getOnlineDate() {
        return onlineDate;
    }
    
    /**
     * @param onlineDate the onlineDate to set
     */
    public void setOnlineDate(Date onlineDate) {
        this.onlineDate = onlineDate;
    }
    
    /**
     * @return the openDate
     */
    public Date getOpenDate() {
        return openDate;
    }
    
    /**
     * @param openDate the openDate to set
     */
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }
    
    /**
     * @return the starGrade
     */
    public String getStarGrade() {
        return starGrade;
    }
    
    /**
     * @param starGrade the starGrade to set
     */
    public void setStarGrade(String starGrade) {
        this.starGrade = starGrade;
    }
    
    /**
     * @return the rank
     */
    public String getRank() {
        return rank;
    }
    
    /**
     * @param rank the rank to set
     */
    public void setRank(String rank) {
        this.rank = rank;
    }
    
    /**
     * @return the bookingPhone
     */
    public String getBookingPhone() {
        return bookingPhone;
    }
    
    /**
     * @param bookingPhone the bookingPhone to set
     */
    public void setBookingPhone(String bookingPhone) {
        this.bookingPhone = bookingPhone;
    }
    
    /**
     * @return the companyHomepage
     */
    public String getCompanyHomepage() {
        return companyHomepage;
    }
    
    /**
     * @param companyHomepage the companyHomepage to set
     */
    public void setCompanyHomepage(String companyHomepage) {
        this.companyHomepage = companyHomepage;
    }
    
    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    /**
     * @return the onlineStatus
     */
    public String getOnlineStatus() {
        return onlineStatus;
    }
    
    /**
     * @param onlineStatus the onlineStatus to set
     */
    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }
    
    /**
     * @return the bank
     */
    public String getBank() {
        return bank;
    }
    
    /**
     * @param bank the bank to set
     */
    public void setBank(String bank) {
        this.bank = bank;
    }
    
    /**
     * @return the bankAccount
     */
    public String getBankAccount() {
        return bankAccount;
    }
    
    /**
     * @param bankAccount the bankAccount to set
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
    
    /**
     * @return the dutyNumber
     */
    public String getDutyNumber() {
        return dutyNumber;
    }
    
    /**
     * @param dutyNumber the dutyNumber to set
     */
    public void setDutyNumber(String dutyNumber) {
        this.dutyNumber = dutyNumber;
    }
    
    /**
     * @return the contactName
     */
    public String getContactName() {
        return contactName;
    }
    
    /**
     * @param contactName the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    
    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * @return the dealerId
     */
    public String getDealerId() {
        return dealerId;
    }
    
    /**
     * @param dealerId the dealerId to set
     */
    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }
    
    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }
    
    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    /**
     * @return the dealerType
     */
    public String getDealerType() {
        return dealerType;
    }
    
    /**
     * @param dealerType the dealerType to set
     */
    public void setDealerType(String dealerType) {
        this.dealerType = dealerType;
    }
    
    /**
     * @return the dealerTypeName
     */
    public String getDealerTypeName() {
        return dealerTypeName;
    }
    
    /**
     * @param dealerTypeName the dealerTypeName to set
     */
    public void setDealerTypeName(String dealerTypeName) {
        this.dealerTypeName = dealerTypeName;
    }
    
    /**
     * @return the provinceCode
     */
    public String getProvinceCode() {
        return provinceCode;
    }
    
    /**
     * @param provinceCode the provinceCode to set
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
    
    /**
     * @return the landCityCode
     */
    public String getLandCityCode() {
        return landCityCode;
    }
    
    /**
     * @param landCityCode the landCityCode to set
     */
    public void setLandCityCode(String landCityCode) {
        this.landCityCode = landCityCode;
    }
    
    /**
     * @return the townCityCode
     */
    public String getTownCityCode() {
        return townCityCode;
    }
    
    /**
     * @param townCityCode the townCityCode to set
     */
    public void setTownCityCode(String townCityCode) {
        this.townCityCode = townCityCode;
    }
    
    /**
     * @return the operateDate
     */
    public Timestamp getOperateDate() {
        return operateDate;
    }
    
    /**
     * @param operateDate the operateDate to set
     */
    public void setOperateDate(Timestamp operateDate) {
        this.operateDate = operateDate;
    }
    
    /**
     * @return the statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }
    
    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    
    /**
     * @return the serviceCode
     */
    public String getServiceCode() {
        return serviceCode;
    }
    
    /**
     * @param serviceCode the serviceCode to set
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
    
    /**
     * @return the serviceOpenDate
     */
    public Date getServiceOpenDate() {
        return serviceOpenDate;
    }
    
    /**
     * @param serviceOpenDate the serviceOpenDate to set
     */
    public void setServiceOpenDate(Date serviceOpenDate) {
        this.serviceOpenDate = serviceOpenDate;
    }
    
    /**
     * @return the dmesRegistAddress
     */
    public String getDmesRegistAddress() {
        return dmesRegistAddress;
    }
    
    /**
     * @param dmesRegistAddress the dmesRegistAddress to set
     */
    public void setDmesRegistAddress(String dmesRegistAddress) {
        this.dmesRegistAddress = dmesRegistAddress;
    }
    
    /**
     * @return the brandId
     */
    public Integer getBrandId() {
        return brandId;
    }
    
    /**
     * @param brandId the brandId to set
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }
    
    /**
     * @return the sfrRegionId
     */
    public Long getSfrRegionId() {
        return sfrRegionId;
    }
    
    /**
     * @param sfrRegionId the sfrRegionId to set
     */
    public void setSfrRegionId(Long sfrRegionId) {
        this.sfrRegionId = sfrRegionId;
    }
    
    /**
     * @return the phone
     */
    public String getPhone() {
        return Phone;
    }
    
    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        Phone = phone;
    }
    
    /**
     * @return the sgmRegionId
     */
    public Long getSgmRegionId() {
        return sgmRegionId;
    }
    
    /**
     * @param sgmRegionId the sgmRegionId to set
     */
    public void setSgmRegionId(Long sgmRegionId) {
        this.sgmRegionId = sgmRegionId;
    }
    
    /**
     * @return the asccode
     */
    public String getAsccode() {
        return asccode;
    }
    
    /**
     * @param asccode the asccode to set
     */
    public void setAsccode(String asccode) {
        this.asccode = asccode;
    }
    
    /**
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }
    
    /**
     * @param accountName the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    
    /**
     * @return the startSapCode
     */
    public String getStartSapCode() {
        return startSapCode;
    }

    
    /**
     * @param startSapCode the startSapCode to set
     */
    public void setStartSapCode(String startSapCode) {
        this.startSapCode = startSapCode;
    }

    
    /**
     * @return the endSapCode
     */
    public String getEndSapCode() {
        return endSapCode;
    }

    
    /**
     * @param endSapCode the endSapCode to set
     */
    public void setEndSapCode(String endSapCode) {
        this.endSapCode = endSapCode;
    }

    
    public Integer getBeginNo() {
        return beginNo;
    }

    
    public void setBeginNo(Integer beginNo) {
        this.beginNo = beginNo;
    }

    
    public Integer getEndNo() {
        return endNo;
    }

    
    public void setEndNo(Integer endNo) {
        this.endNo = endNo;
    }
    
    
}
