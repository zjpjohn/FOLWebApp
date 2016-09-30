
package com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>claimPaymentOperationRequestVO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="claimPaymentOperationRequestVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="approved_amount_tax" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="approved_amount_total" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="crosscheck_amount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="labor_amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="labor_amount_tax" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="other_amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="other_amount_tax" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="paid_amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="part_handling_amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="parts_amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="parts_amount_tax" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="paymentClaimOrderVO" type="{http://ws.agent.service.dms.sgm.com/}paymentClaimOrderVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ts_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ts_time" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "claimPaymentOperationRequestVO", propOrder = {
    "approvedAmountTax",
    "approvedAmountTotal",
    "crosscheckAmount",
    "laborAmount",
    "laborAmountTax",
    "otherAmount",
    "otherAmountTax",
    "paidAmount",
    "partHandlingAmount",
    "partsAmount",
    "partsAmountTax",
    "paymentClaimOrderVO",
    "tsId",
    "tsTime"
})
public class ClaimPaymentOperationRequestVO {

    @XmlElement(name = "approved_amount_tax")
    protected double approvedAmountTax;
    @XmlElement(name = "approved_amount_total")
    protected double approvedAmountTotal;
    @XmlElement(name = "crosscheck_amount")
    protected int crosscheckAmount;
    @XmlElement(name = "labor_amount")
    protected double laborAmount;
    @XmlElement(name = "labor_amount_tax")
    protected double laborAmountTax;
    @XmlElement(name = "other_amount")
    protected double otherAmount;
    @XmlElement(name = "other_amount_tax")
    protected double otherAmountTax;
    @XmlElement(name = "paid_amount")
    protected double paidAmount;
    @XmlElement(name = "part_handling_amount")
    protected double partHandlingAmount;
    @XmlElement(name = "parts_amount")
    protected double partsAmount;
    @XmlElement(name = "parts_amount_tax")
    protected double partsAmountTax;
    @XmlElement(nillable = true)
    protected List<PaymentClaimOrderVO> paymentClaimOrderVO;
    @XmlElement(name = "ts_id")
    protected String tsId;
    @XmlElement(name = "ts_time")
    protected long tsTime;

    /**
     * ��ȡapprovedAmountTax���Ե�ֵ��
     * 
     */
    public double getApprovedAmountTax() {
        return approvedAmountTax;
    }

    /**
     * ����approvedAmountTax���Ե�ֵ��
     * 
     */
    public void setApprovedAmountTax(double value) {
        this.approvedAmountTax = value;
    }

    /**
     * ��ȡapprovedAmountTotal���Ե�ֵ��
     * 
     */
    public double getApprovedAmountTotal() {
        return approvedAmountTotal;
    }

    /**
     * ����approvedAmountTotal���Ե�ֵ��
     * 
     */
    public void setApprovedAmountTotal(double value) {
        this.approvedAmountTotal = value;
    }

    /**
     * ��ȡcrosscheckAmount���Ե�ֵ��
     * 
     */
    public int getCrosscheckAmount() {
        return crosscheckAmount;
    }

    /**
     * ����crosscheckAmount���Ե�ֵ��
     * 
     */
    public void setCrosscheckAmount(int value) {
        this.crosscheckAmount = value;
    }

    /**
     * ��ȡlaborAmount���Ե�ֵ��
     * 
     */
    public double getLaborAmount() {
        return laborAmount;
    }

    /**
     * ����laborAmount���Ե�ֵ��
     * 
     */
    public void setLaborAmount(double value) {
        this.laborAmount = value;
    }

    /**
     * ��ȡlaborAmountTax���Ե�ֵ��
     * 
     */
    public double getLaborAmountTax() {
        return laborAmountTax;
    }

    /**
     * ����laborAmountTax���Ե�ֵ��
     * 
     */
    public void setLaborAmountTax(double value) {
        this.laborAmountTax = value;
    }

    /**
     * ��ȡotherAmount���Ե�ֵ��
     * 
     */
    public double getOtherAmount() {
        return otherAmount;
    }

    /**
     * ����otherAmount���Ե�ֵ��
     * 
     */
    public void setOtherAmount(double value) {
        this.otherAmount = value;
    }

    /**
     * ��ȡotherAmountTax���Ե�ֵ��
     * 
     */
    public double getOtherAmountTax() {
        return otherAmountTax;
    }

    /**
     * ����otherAmountTax���Ե�ֵ��
     * 
     */
    public void setOtherAmountTax(double value) {
        this.otherAmountTax = value;
    }

    /**
     * ��ȡpaidAmount���Ե�ֵ��
     * 
     */
    public double getPaidAmount() {
        return paidAmount;
    }

    /**
     * ����paidAmount���Ե�ֵ��
     * 
     */
    public void setPaidAmount(double value) {
        this.paidAmount = value;
    }

    /**
     * ��ȡpartHandlingAmount���Ե�ֵ��
     * 
     */
    public double getPartHandlingAmount() {
        return partHandlingAmount;
    }

    /**
     * ����partHandlingAmount���Ե�ֵ��
     * 
     */
    public void setPartHandlingAmount(double value) {
        this.partHandlingAmount = value;
    }

    /**
     * ��ȡpartsAmount���Ե�ֵ��
     * 
     */
    public double getPartsAmount() {
        return partsAmount;
    }

    /**
     * ����partsAmount���Ե�ֵ��
     * 
     */
    public void setPartsAmount(double value) {
        this.partsAmount = value;
    }

    /**
     * ��ȡpartsAmountTax���Ե�ֵ��
     * 
     */
    public double getPartsAmountTax() {
        return partsAmountTax;
    }

    /**
     * ����partsAmountTax���Ե�ֵ��
     * 
     */
    public void setPartsAmountTax(double value) {
        this.partsAmountTax = value;
    }

    /**
     * Gets the value of the paymentClaimOrderVO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentClaimOrderVO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentClaimOrderVO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaymentClaimOrderVO }
     * 
     * 
     */
    public List<PaymentClaimOrderVO> getPaymentClaimOrderVO() {
        if (paymentClaimOrderVO == null) {
            paymentClaimOrderVO = new ArrayList<PaymentClaimOrderVO>();
        }
        return this.paymentClaimOrderVO;
    }

    /**
     * ��ȡtsId���Ե�ֵ��
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
     * ����tsId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsId(String value) {
        this.tsId = value;
    }

    /**
     * ��ȡtsTime���Ե�ֵ��
     * 
     */
    public long getTsTime() {
        return tsTime;
    }

    /**
     * ����tsTime���Ե�ֵ��
     * 
     */
    public void setTsTime(long value) {
        this.tsTime = value;
    }

}
