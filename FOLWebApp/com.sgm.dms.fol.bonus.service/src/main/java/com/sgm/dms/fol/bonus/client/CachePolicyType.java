
package com.sgm.dms.fol.bonus.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for CachePolicyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CachePolicyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CacheOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="UsingCache" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Entry" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Expire" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CachePolicyType", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMCommonHeader/v1", propOrder = {
    "cacheOnly",
    "usingCache",
    "entry",
    "expire"
})
public class CachePolicyType {

    @XmlElement(name = "CacheOnly", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMCommonHeader/v1")
    protected Boolean cacheOnly;
    @XmlElement(name = "UsingCache", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMCommonHeader/v1")
    protected Boolean usingCache;
    @XmlElement(name = "Entry", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMCommonHeader/v1")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar entry;
    @XmlElement(name = "Expire", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMCommonHeader/v1")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expire;

    /**
     * Gets the value of the cacheOnly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCacheOnly() {
        return cacheOnly;
    }

    /**
     * Sets the value of the cacheOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCacheOnly(Boolean value) {
        this.cacheOnly = value;
    }

    /**
     * Gets the value of the usingCache property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUsingCache() {
        return usingCache;
    }

    /**
     * Sets the value of the usingCache property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUsingCache(Boolean value) {
        this.usingCache = value;
    }

    /**
     * Gets the value of the entry property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEntry() {
        return entry;
    }

    /**
     * Sets the value of the entry property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEntry(XMLGregorianCalendar value) {
        this.entry = value;
    }

    /**
     * Gets the value of the expire property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpire() {
        return expire;
    }

    /**
     * Sets the value of the expire property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpire(XMLGregorianCalendar value) {
        this.expire = value;
    }

}
