
package com.sgm.dms.fol.invoice.header;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>CachePolicyType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
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
@XmlType(name = "CachePolicyType", propOrder = {
    "cacheOnly",
    "usingCache",
    "entry",
    "expire"
})
public class CachePolicyType {

    @XmlElement(name = "CacheOnly")
    protected Boolean cacheOnly;
    @XmlElement(name = "UsingCache")
    protected Boolean usingCache;
    @XmlElement(name = "Entry")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar entry;
    @XmlElement(name = "Expire")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expire;

    /**
     * ��ȡcacheOnly���Ե�ֵ��
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
     * ����cacheOnly���Ե�ֵ��
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
     * ��ȡusingCache���Ե�ֵ��
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
     * ����usingCache���Ե�ֵ��
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
     * ��ȡentry���Ե�ֵ��
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
     * ����entry���Ե�ֵ��
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
     * ��ȡexpire���Ե�ֵ��
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
     * ����expire���Ե�ֵ��
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
