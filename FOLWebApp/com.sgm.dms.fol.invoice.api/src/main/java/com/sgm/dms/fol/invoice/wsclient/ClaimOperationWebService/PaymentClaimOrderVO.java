
package com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>paymentClaimOrderVO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="paymentClaimOrderVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="change_time" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="claim_no" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crosscheck_after_status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="crosscheck_before_status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="line_no" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="reason_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refer_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refer_type" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="warranty_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paymentClaimOrderVO", propOrder = {
    "changeTime",
    "claimNo",
    "crosscheckAfterStatus",
    "crosscheckBeforeStatus",
    "lineNo",
    "reasonCode",
    "referCode",
    "referType",
    "remark",
    "warrantyCode"
})
public class PaymentClaimOrderVO {

    @XmlElement(name = "change_time")
    protected long changeTime;
    @XmlElement(name = "claim_no")
    protected String claimNo;
    @XmlElement(name = "crosscheck_after_status")
    protected int crosscheckAfterStatus;
    @XmlElement(name = "crosscheck_before_status")
    protected int crosscheckBeforeStatus;
    @XmlElement(name = "line_no")
    protected int lineNo;
    @XmlElement(name = "reason_code")
    protected String reasonCode;
    @XmlElement(name = "refer_code")
    protected String referCode;
    @XmlElement(name = "refer_type")
    protected long referType;
    protected String remark;
    @XmlElement(name = "warranty_code")
    protected String warrantyCode;

    /**
     * ��ȡchangeTime���Ե�ֵ��
     * 
     */
    public long getChangeTime() {
        return changeTime;
    }

    /**
     * ����changeTime���Ե�ֵ��
     * 
     */
    public void setChangeTime(long value) {
        this.changeTime = value;
    }

    /**
     * ��ȡclaimNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaimNo() {
        return claimNo;
    }

    /**
     * ����claimNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaimNo(String value) {
        this.claimNo = value;
    }

    /**
     * ��ȡcrosscheckAfterStatus���Ե�ֵ��
     * 
     */
    public int getCrosscheckAfterStatus() {
        return crosscheckAfterStatus;
    }

    /**
     * ����crosscheckAfterStatus���Ե�ֵ��
     * 
     */
    public void setCrosscheckAfterStatus(int value) {
        this.crosscheckAfterStatus = value;
    }

    /**
     * ��ȡcrosscheckBeforeStatus���Ե�ֵ��
     * 
     */
    public int getCrosscheckBeforeStatus() {
        return crosscheckBeforeStatus;
    }

    /**
     * ����crosscheckBeforeStatus���Ե�ֵ��
     * 
     */
    public void setCrosscheckBeforeStatus(int value) {
        this.crosscheckBeforeStatus = value;
    }

    /**
     * ��ȡlineNo���Ե�ֵ��
     * 
     */
    public int getLineNo() {
        return lineNo;
    }

    /**
     * ����lineNo���Ե�ֵ��
     * 
     */
    public void setLineNo(int value) {
        this.lineNo = value;
    }

    /**
     * ��ȡreasonCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReasonCode() {
        return reasonCode;
    }

    /**
     * ����reasonCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReasonCode(String value) {
        this.reasonCode = value;
    }

    /**
     * ��ȡreferCode���Ե�ֵ��
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
     * ����referCode���Ե�ֵ��
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
     * ��ȡreferType���Ե�ֵ��
     * 
     */
    public long getReferType() {
        return referType;
    }

    /**
     * ����referType���Ե�ֵ��
     * 
     */
    public void setReferType(long value) {
        this.referType = value;
    }

    /**
     * ��ȡremark���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * ����remark���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * ��ȡwarrantyCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarrantyCode() {
        return warrantyCode;
    }

    /**
     * ����warrantyCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarrantyCode(String value) {
        this.warrantyCode = value;
    }

}
