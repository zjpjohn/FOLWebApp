
package com.sgm.dms.fol.reserve.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SGMCommonHeaderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SGMCommonHeaderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MessageId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="From" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="To" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="SLATimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="LoggingSeverity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Cache" type="{http://www.saic-gm.com/esb/schemas/common/SGMCommonHeader/v1}CachePolicyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SGMCommonHeaderType", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMCommonHeader/v1", propOrder = {
    "messageId",
    "from",
    "to",
    "timestamp",
    "slaTimeout",
    "loggingSeverity",
    "cache"
})
public class SGMCommonHeaderType {

    @XmlElement(name = "MessageId", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMCommonHeader/v1", required = true)
    protected String messageId;
    @XmlElement(name = "From", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMCommonHeader/v1", required = true)
    protected String from;
    @XmlElement(name = "To", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMCommonHeader/v1", required = true)
    protected String to;
    @XmlElement(name = "Timestamp", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMCommonHeader/v1", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timestamp;
    @XmlElement(name = "SLATimeout", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMCommonHeader/v1")
    protected Integer slaTimeout;
    @XmlElement(name = "LoggingSeverity", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMCommonHeader/v1")
    protected Integer loggingSeverity;
    @XmlElement(name = "Cache", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMCommonHeader/v1")
    protected CachePolicyType cache;

    /**
     * Gets the value of the messageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Sets the value of the messageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageId(String value) {
        this.messageId = value;
    }

    /**
     * Gets the value of the from property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrom(String value) {
        this.from = value;
    }

    /**
     * Gets the value of the to property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTo(String value) {
        this.to = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimestamp(XMLGregorianCalendar value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the slaTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSLATimeout() {
        return slaTimeout;
    }

    /**
     * Sets the value of the slaTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSLATimeout(Integer value) {
        this.slaTimeout = value;
    }

    /**
     * Gets the value of the loggingSeverity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLoggingSeverity() {
        return loggingSeverity;
    }

    /**
     * Sets the value of the loggingSeverity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLoggingSeverity(Integer value) {
        this.loggingSeverity = value;
    }

    /**
     * Gets the value of the cache property.
     * 
     * @return
     *     possible object is
     *     {@link CachePolicyType }
     *     
     */
    public CachePolicyType getCache() {
        return cache;
    }

    /**
     * Sets the value of the cache property.
     * 
     * @param value
     *     allowed object is
     *     {@link CachePolicyType }
     *     
     */
    public void setCache(CachePolicyType value) {
        this.cache = value;
    }

}
