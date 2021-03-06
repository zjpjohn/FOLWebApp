
package com.sgm.dms.fol.bonus.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tranformUnFrznBonusFundResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tranformUnFrznBonusFundResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://ws.agent.service.dms.sgm.com/}bonusFundResultVo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tranformUnFrznBonusFundResponse", propOrder = {
    "_return"
})
public class TranformUnFrznBonusFundResponse {

    @XmlElement(name = "return")
    protected BonusFundResultVo _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link BonusFundResultVo }
     *     
     */
    public BonusFundResultVo getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link BonusFundResultVo }
     *     
     */
    public void setReturn(BonusFundResultVo value) {
        this._return = value;
    }

}
