
package com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>changePaymentToInvoiceCompensationResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="changePaymentToInvoiceCompensationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://ws.agent.service.dms.sgm.com/}claimOperationCompensationResultVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "changePaymentToInvoiceCompensationResponse", propOrder = {
    "_return"
})
public class ChangePaymentToInvoiceCompensationResponse {

    @XmlElement(name = "return")
    protected ClaimOperationCompensationResultVO _return;

    /**
     * ��ȡreturn���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ClaimOperationCompensationResultVO }
     *     
     */
    public ClaimOperationCompensationResultVO getReturn() {
        return _return;
    }

    /**
     * ����return���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ClaimOperationCompensationResultVO }
     *     
     */
    public void setReturn(ClaimOperationCompensationResultVO value) {
        this._return = value;
    }

}
