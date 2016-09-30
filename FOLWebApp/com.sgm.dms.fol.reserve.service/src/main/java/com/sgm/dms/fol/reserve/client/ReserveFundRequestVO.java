
package com.sgm.dms.fol.reserve.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for reserveFundRequestVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="reserveFundRequestVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="entry_date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fund_type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="posting_date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refer_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refer_type" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="sap_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ts_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reserveFundRequestVO", propOrder = {
    "amount",
    "entryDate",
    "fundType",
    "postingDate",
    "referCode",
    "referType",
    "sapCode",
    "tsId"
})
public class ReserveFundRequestVO {

    protected double amount;
    @XmlElement(name = "entry_date")
    protected String entryDate;
    @XmlElement(name = "fund_type", required = true)
    protected String fundType;
    @XmlElement(name = "posting_date")
    protected String postingDate;
    @XmlElement(name = "refer_code")
    protected String referCode;
    @XmlElement(name = "refer_type")
    protected long referType;
    @XmlElement(name = "sap_code")
    protected String sapCode;
    @XmlElement(name = "ts_id")
    protected String tsId;

    /**
     * Gets the value of the amount property.
     * 
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(double value) {
        this.amount = value;
    }

    /**
     * Gets the value of the entryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntryDate() {
        return entryDate;
    }

    /**
     * Sets the value of the entryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntryDate(String value) {
        this.entryDate = value;
    }

    /**
     * Gets the value of the fundType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundType() {
        return fundType;
    }

    /**
     * Sets the value of the fundType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundType(String value) {
        this.fundType = value;
    }

    /**
     * Gets the value of the postingDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostingDate() {
        return postingDate;
    }

    /**
     * Sets the value of the postingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostingDate(String value) {
        this.postingDate = value;
    }

    /**
     * Gets the value of the referCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferCode() {
        return referCode;
    }

    /**
     * Sets the value of the referCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferCode(String value) {
        this.referCode = value;
    }

    /**
     * Gets the value of the referType property.
     * 
     */
    public long getReferType() {
        return referType;
    }

    /**
     * Sets the value of the referType property.
     * 
     */
    public void setReferType(long value) {
        this.referType = value;
    }

    /**
     * Gets the value of the sapCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSapCode() {
        return sapCode;
    }

    /**
     * Sets the value of the sapCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSapCode(String value) {
        this.sapCode = value;
    }

    /**
     * Gets the value of the tsId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTsId() {
        return tsId;
    }

    /**
     * Sets the value of the tsId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsId(String value) {
        this.tsId = value;
    }

}
