
package com.sgm.dms.fol.bonus.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tranformFrznBonusFundCompensation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tranformFrznBonusFundCompensation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://ws.agent.service.dms.sgm.com/}bonusFundCompensationRequestVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tranformFrznBonusFundCompensation", propOrder = {
    "arg0"
})
public class TranformFrznBonusFundCompensation {

    protected BonusFundCompensationRequestVO arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link BonusFundCompensationRequestVO }
     *     
     */
    public BonusFundCompensationRequestVO getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BonusFundCompensationRequestVO }
     *     
     */
    public void setArg0(BonusFundCompensationRequestVO value) {
        this.arg0 = value;
    }

}
