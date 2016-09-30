package com.sgm.dms.fol.returnallowance.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReturnOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReturnOrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andReturnOrderNoIsNull() {
            addCriterion("RETURN_ORDER_NO is null");
            return (Criteria) this;
        }

        public Criteria andReturnOrderNoIsNotNull() {
            addCriterion("RETURN_ORDER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andReturnOrderNoEqualTo(String value) {
            addCriterion("RETURN_ORDER_NO =", value, "returnOrderNo");
            return (Criteria) this;
        }

        public Criteria andReturnOrderNoNotEqualTo(String value) {
            addCriterion("RETURN_ORDER_NO <>", value, "returnOrderNo");
            return (Criteria) this;
        }

        public Criteria andReturnOrderNoGreaterThan(String value) {
            addCriterion("RETURN_ORDER_NO >", value, "returnOrderNo");
            return (Criteria) this;
        }

        public Criteria andReturnOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("RETURN_ORDER_NO >=", value, "returnOrderNo");
            return (Criteria) this;
        }

        public Criteria andReturnOrderNoLessThan(String value) {
            addCriterion("RETURN_ORDER_NO <", value, "returnOrderNo");
            return (Criteria) this;
        }

        public Criteria andReturnOrderNoLessThanOrEqualTo(String value) {
            addCriterion("RETURN_ORDER_NO <=", value, "returnOrderNo");
            return (Criteria) this;
        }

        public Criteria andReturnOrderNoLike(String value) {
            addCriterion("RETURN_ORDER_NO like", value, "returnOrderNo");
            return (Criteria) this;
        }

        public Criteria andReturnOrderNoNotLike(String value) {
            addCriterion("RETURN_ORDER_NO not like", value, "returnOrderNo");
            return (Criteria) this;
        }

        public Criteria andReturnOrderNoIn(List<String> values) {
            addCriterion("RETURN_ORDER_NO in", values, "returnOrderNo");
            return (Criteria) this;
        }

        public Criteria andReturnOrderNoNotIn(List<String> values) {
            addCriterion("RETURN_ORDER_NO not in", values, "returnOrderNo");
            return (Criteria) this;
        }

        public Criteria andReturnOrderNoBetween(String value1, String value2) {
            addCriterion("RETURN_ORDER_NO between", value1, value2, "returnOrderNo");
            return (Criteria) this;
        }

        public Criteria andReturnOrderNoNotBetween(String value1, String value2) {
            addCriterion("RETURN_ORDER_NO not between", value1, value2, "returnOrderNo");
            return (Criteria) this;
        }

        public Criteria andSapCodeIsNull() {
            addCriterion("SAP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSapCodeIsNotNull() {
            addCriterion("SAP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSapCodeEqualTo(String value) {
            addCriterion("SAP_CODE =", value, "sapCode");
            return (Criteria) this;
        }

        public Criteria andSapCodeNotEqualTo(String value) {
            addCriterion("SAP_CODE <>", value, "sapCode");
            return (Criteria) this;
        }

        public Criteria andSapCodeGreaterThan(String value) {
            addCriterion("SAP_CODE >", value, "sapCode");
            return (Criteria) this;
        }

        public Criteria andSapCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SAP_CODE >=", value, "sapCode");
            return (Criteria) this;
        }

        public Criteria andSapCodeLessThan(String value) {
            addCriterion("SAP_CODE <", value, "sapCode");
            return (Criteria) this;
        }

        public Criteria andSapCodeLessThanOrEqualTo(String value) {
            addCriterion("SAP_CODE <=", value, "sapCode");
            return (Criteria) this;
        }

        public Criteria andSapCodeLike(String value) {
            addCriterion("SAP_CODE like", value, "sapCode");
            return (Criteria) this;
        }

        public Criteria andSapCodeNotLike(String value) {
            addCriterion("SAP_CODE not like", value, "sapCode");
            return (Criteria) this;
        }

        public Criteria andSapCodeIn(List<String> values) {
            addCriterion("SAP_CODE in", values, "sapCode");
            return (Criteria) this;
        }

        public Criteria andSapCodeNotIn(List<String> values) {
            addCriterion("SAP_CODE not in", values, "sapCode");
            return (Criteria) this;
        }

        public Criteria andSapCodeBetween(String value1, String value2) {
            addCriterion("SAP_CODE between", value1, value2, "sapCode");
            return (Criteria) this;
        }

        public Criteria andSapCodeNotBetween(String value1, String value2) {
            addCriterion("SAP_CODE not between", value1, value2, "sapCode");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("COMPANY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("COMPANY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("COMPANY_NAME =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("COMPANY_NAME <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("COMPANY_NAME >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANY_NAME >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("COMPANY_NAME <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("COMPANY_NAME <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("COMPANY_NAME like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("COMPANY_NAME not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("COMPANY_NAME in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("COMPANY_NAME not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("COMPANY_NAME between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("COMPANY_NAME not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andTaxBureauNameIsNull() {
            addCriterion("TAX_BUREAU_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTaxBureauNameIsNotNull() {
            addCriterion("TAX_BUREAU_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTaxBureauNameEqualTo(String value) {
            addCriterion("TAX_BUREAU_NAME =", value, "taxBureauName");
            return (Criteria) this;
        }

        public Criteria andTaxBureauNameNotEqualTo(String value) {
            addCriterion("TAX_BUREAU_NAME <>", value, "taxBureauName");
            return (Criteria) this;
        }

        public Criteria andTaxBureauNameGreaterThan(String value) {
            addCriterion("TAX_BUREAU_NAME >", value, "taxBureauName");
            return (Criteria) this;
        }

        public Criteria andTaxBureauNameGreaterThanOrEqualTo(String value) {
            addCriterion("TAX_BUREAU_NAME >=", value, "taxBureauName");
            return (Criteria) this;
        }

        public Criteria andTaxBureauNameLessThan(String value) {
            addCriterion("TAX_BUREAU_NAME <", value, "taxBureauName");
            return (Criteria) this;
        }

        public Criteria andTaxBureauNameLessThanOrEqualTo(String value) {
            addCriterion("TAX_BUREAU_NAME <=", value, "taxBureauName");
            return (Criteria) this;
        }

        public Criteria andTaxBureauNameLike(String value) {
            addCriterion("TAX_BUREAU_NAME like", value, "taxBureauName");
            return (Criteria) this;
        }

        public Criteria andTaxBureauNameNotLike(String value) {
            addCriterion("TAX_BUREAU_NAME not like", value, "taxBureauName");
            return (Criteria) this;
        }

        public Criteria andTaxBureauNameIn(List<String> values) {
            addCriterion("TAX_BUREAU_NAME in", values, "taxBureauName");
            return (Criteria) this;
        }

        public Criteria andTaxBureauNameNotIn(List<String> values) {
            addCriterion("TAX_BUREAU_NAME not in", values, "taxBureauName");
            return (Criteria) this;
        }

        public Criteria andTaxBureauNameBetween(String value1, String value2) {
            addCriterion("TAX_BUREAU_NAME between", value1, value2, "taxBureauName");
            return (Criteria) this;
        }

        public Criteria andTaxBureauNameNotBetween(String value1, String value2) {
            addCriterion("TAX_BUREAU_NAME not between", value1, value2, "taxBureauName");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeIsNull() {
            addCriterion("INVOICE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeIsNotNull() {
            addCriterion("INVOICE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeEqualTo(String value) {
            addCriterion("INVOICE_CODE =", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotEqualTo(String value) {
            addCriterion("INVOICE_CODE <>", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeGreaterThan(String value) {
            addCriterion("INVOICE_CODE >", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_CODE >=", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeLessThan(String value) {
            addCriterion("INVOICE_CODE <", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_CODE <=", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeLike(String value) {
            addCriterion("INVOICE_CODE like", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotLike(String value) {
            addCriterion("INVOICE_CODE not like", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeIn(List<String> values) {
            addCriterion("INVOICE_CODE in", values, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotIn(List<String> values) {
            addCriterion("INVOICE_CODE not in", values, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeBetween(String value1, String value2) {
            addCriterion("INVOICE_CODE between", value1, value2, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotBetween(String value1, String value2) {
            addCriterion("INVOICE_CODE not between", value1, value2, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberIsNull() {
            addCriterion("INVOICE_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberIsNotNull() {
            addCriterion("INVOICE_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberEqualTo(String value) {
            addCriterion("INVOICE_NUMBER =", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberNotEqualTo(String value) {
            addCriterion("INVOICE_NUMBER <>", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberGreaterThan(String value) {
            addCriterion("INVOICE_NUMBER >", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_NUMBER >=", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberLessThan(String value) {
            addCriterion("INVOICE_NUMBER <", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_NUMBER <=", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberLike(String value) {
            addCriterion("INVOICE_NUMBER like", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberNotLike(String value) {
            addCriterion("INVOICE_NUMBER not like", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberIn(List<String> values) {
            addCriterion("INVOICE_NUMBER in", values, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberNotIn(List<String> values) {
            addCriterion("INVOICE_NUMBER not in", values, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberBetween(String value1, String value2) {
            addCriterion("INVOICE_NUMBER between", value1, value2, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberNotBetween(String value1, String value2) {
            addCriterion("INVOICE_NUMBER not between", value1, value2, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andGrossIsNull() {
            addCriterion("GROSS is null");
            return (Criteria) this;
        }

        public Criteria andGrossIsNotNull() {
            addCriterion("GROSS is not null");
            return (Criteria) this;
        }

        public Criteria andGrossEqualTo(BigDecimal value) {
            addCriterion("GROSS =", value, "gross");
            return (Criteria) this;
        }

        public Criteria andGrossNotEqualTo(BigDecimal value) {
            addCriterion("GROSS <>", value, "gross");
            return (Criteria) this;
        }

        public Criteria andGrossGreaterThan(BigDecimal value) {
            addCriterion("GROSS >", value, "gross");
            return (Criteria) this;
        }

        public Criteria andGrossGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("GROSS >=", value, "gross");
            return (Criteria) this;
        }

        public Criteria andGrossLessThan(BigDecimal value) {
            addCriterion("GROSS <", value, "gross");
            return (Criteria) this;
        }

        public Criteria andGrossLessThanOrEqualTo(BigDecimal value) {
            addCriterion("GROSS <=", value, "gross");
            return (Criteria) this;
        }

        public Criteria andGrossIn(List<BigDecimal> values) {
            addCriterion("GROSS in", values, "gross");
            return (Criteria) this;
        }

        public Criteria andGrossNotIn(List<BigDecimal> values) {
            addCriterion("GROSS not in", values, "gross");
            return (Criteria) this;
        }

        public Criteria andGrossBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GROSS between", value1, value2, "gross");
            return (Criteria) this;
        }

        public Criteria andGrossNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GROSS not between", value1, value2, "gross");
            return (Criteria) this;
        }

        public Criteria andTaxIsNull() {
            addCriterion("TAX is null");
            return (Criteria) this;
        }

        public Criteria andTaxIsNotNull() {
            addCriterion("TAX is not null");
            return (Criteria) this;
        }

        public Criteria andTaxEqualTo(BigDecimal value) {
            addCriterion("TAX =", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotEqualTo(BigDecimal value) {
            addCriterion("TAX <>", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxGreaterThan(BigDecimal value) {
            addCriterion("TAX >", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TAX >=", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxLessThan(BigDecimal value) {
            addCriterion("TAX <", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TAX <=", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxIn(List<BigDecimal> values) {
            addCriterion("TAX in", values, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotIn(List<BigDecimal> values) {
            addCriterion("TAX not in", values, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TAX between", value1, value2, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TAX not between", value1, value2, "tax");
            return (Criteria) this;
        }

        public Criteria andNetAmountIsNull() {
            addCriterion("NET_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andNetAmountIsNotNull() {
            addCriterion("NET_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andNetAmountEqualTo(BigDecimal value) {
            addCriterion("NET_AMOUNT =", value, "netAmount");
            return (Criteria) this;
        }

        public Criteria andNetAmountNotEqualTo(BigDecimal value) {
            addCriterion("NET_AMOUNT <>", value, "netAmount");
            return (Criteria) this;
        }

        public Criteria andNetAmountGreaterThan(BigDecimal value) {
            addCriterion("NET_AMOUNT >", value, "netAmount");
            return (Criteria) this;
        }

        public Criteria andNetAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("NET_AMOUNT >=", value, "netAmount");
            return (Criteria) this;
        }

        public Criteria andNetAmountLessThan(BigDecimal value) {
            addCriterion("NET_AMOUNT <", value, "netAmount");
            return (Criteria) this;
        }

        public Criteria andNetAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("NET_AMOUNT <=", value, "netAmount");
            return (Criteria) this;
        }

        public Criteria andNetAmountIn(List<BigDecimal> values) {
            addCriterion("NET_AMOUNT in", values, "netAmount");
            return (Criteria) this;
        }

        public Criteria andNetAmountNotIn(List<BigDecimal> values) {
            addCriterion("NET_AMOUNT not in", values, "netAmount");
            return (Criteria) this;
        }

        public Criteria andNetAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("NET_AMOUNT between", value1, value2, "netAmount");
            return (Criteria) this;
        }

        public Criteria andNetAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("NET_AMOUNT not between", value1, value2, "netAmount");
            return (Criteria) this;
        }

        public Criteria andReportDateIsNull() {
            addCriterion("REPORT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andReportDateIsNotNull() {
            addCriterion("REPORT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andReportDateEqualTo(String value) {
            addCriterion("REPORT_DATE =", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotEqualTo(String value) {
            addCriterion("REPORT_DATE <>", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThan(String value) {
            addCriterion("REPORT_DATE >", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThanOrEqualTo(String value) {
            addCriterion("REPORT_DATE >=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThan(String value) {
            addCriterion("REPORT_DATE <", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThanOrEqualTo(String value) {
            addCriterion("REPORT_DATE <=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLike(String value) {
            addCriterion("REPORT_DATE like", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotLike(String value) {
            addCriterion("REPORT_DATE not like", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateIn(List<String> values) {
            addCriterion("REPORT_DATE in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotIn(List<String> values) {
            addCriterion("REPORT_DATE not in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateBetween(String value1, String value2) {
            addCriterion("REPORT_DATE between", value1, value2, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotBetween(String value1, String value2) {
            addCriterion("REPORT_DATE not between", value1, value2, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReporterIsNull() {
            addCriterion("REPORTER is null");
            return (Criteria) this;
        }

        public Criteria andReporterIsNotNull() {
            addCriterion("REPORTER is not null");
            return (Criteria) this;
        }

        public Criteria andReporterEqualTo(String value) {
            addCriterion("REPORTER =", value, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterNotEqualTo(String value) {
            addCriterion("REPORTER <>", value, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterGreaterThan(String value) {
            addCriterion("REPORTER >", value, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterGreaterThanOrEqualTo(String value) {
            addCriterion("REPORTER >=", value, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterLessThan(String value) {
            addCriterion("REPORTER <", value, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterLessThanOrEqualTo(String value) {
            addCriterion("REPORTER <=", value, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterLike(String value) {
            addCriterion("REPORTER like", value, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterNotLike(String value) {
            addCriterion("REPORTER not like", value, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterIn(List<String> values) {
            addCriterion("REPORTER in", values, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterNotIn(List<String> values) {
            addCriterion("REPORTER not in", values, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterBetween(String value1, String value2) {
            addCriterion("REPORTER between", value1, value2, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterNotBetween(String value1, String value2) {
            addCriterion("REPORTER not between", value1, value2, "reporter");
            return (Criteria) this;
        }

        public Criteria andAcceptDateIsNull() {
            addCriterion("ACCEPT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andAcceptDateIsNotNull() {
            addCriterion("ACCEPT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptDateEqualTo(String value) {
            addCriterion("ACCEPT_DATE =", value, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateNotEqualTo(String value) {
            addCriterion("ACCEPT_DATE <>", value, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateGreaterThan(String value) {
            addCriterion("ACCEPT_DATE >", value, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateGreaterThanOrEqualTo(String value) {
            addCriterion("ACCEPT_DATE >=", value, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateLessThan(String value) {
            addCriterion("ACCEPT_DATE <", value, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateLessThanOrEqualTo(String value) {
            addCriterion("ACCEPT_DATE <=", value, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateLike(String value) {
            addCriterion("ACCEPT_DATE like", value, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateNotLike(String value) {
            addCriterion("ACCEPT_DATE not like", value, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateIn(List<String> values) {
            addCriterion("ACCEPT_DATE in", values, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateNotIn(List<String> values) {
            addCriterion("ACCEPT_DATE not in", values, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateBetween(String value1, String value2) {
            addCriterion("ACCEPT_DATE between", value1, value2, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateNotBetween(String value1, String value2) {
            addCriterion("ACCEPT_DATE not between", value1, value2, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andSgmRemarkIsNull() {
            addCriterion("SGM_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andSgmRemarkIsNotNull() {
            addCriterion("SGM_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andSgmRemarkEqualTo(String value) {
            addCriterion("SGM_REMARK =", value, "sgmRemark");
            return (Criteria) this;
        }

        public Criteria andSgmRemarkNotEqualTo(String value) {
            addCriterion("SGM_REMARK <>", value, "sgmRemark");
            return (Criteria) this;
        }

        public Criteria andSgmRemarkGreaterThan(String value) {
            addCriterion("SGM_REMARK >", value, "sgmRemark");
            return (Criteria) this;
        }

        public Criteria andSgmRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("SGM_REMARK >=", value, "sgmRemark");
            return (Criteria) this;
        }

        public Criteria andSgmRemarkLessThan(String value) {
            addCriterion("SGM_REMARK <", value, "sgmRemark");
            return (Criteria) this;
        }

        public Criteria andSgmRemarkLessThanOrEqualTo(String value) {
            addCriterion("SGM_REMARK <=", value, "sgmRemark");
            return (Criteria) this;
        }

        public Criteria andSgmRemarkLike(String value) {
            addCriterion("SGM_REMARK like", value, "sgmRemark");
            return (Criteria) this;
        }

        public Criteria andSgmRemarkNotLike(String value) {
            addCriterion("SGM_REMARK not like", value, "sgmRemark");
            return (Criteria) this;
        }

        public Criteria andSgmRemarkIn(List<String> values) {
            addCriterion("SGM_REMARK in", values, "sgmRemark");
            return (Criteria) this;
        }

        public Criteria andSgmRemarkNotIn(List<String> values) {
            addCriterion("SGM_REMARK not in", values, "sgmRemark");
            return (Criteria) this;
        }

        public Criteria andSgmRemarkBetween(String value1, String value2) {
            addCriterion("SGM_REMARK between", value1, value2, "sgmRemark");
            return (Criteria) this;
        }

        public Criteria andSgmRemarkNotBetween(String value1, String value2) {
            addCriterion("SGM_REMARK not between", value1, value2, "sgmRemark");
            return (Criteria) this;
        }

        public Criteria andProposerIsNull() {
            addCriterion("PROPOSER is null");
            return (Criteria) this;
        }

        public Criteria andProposerIsNotNull() {
            addCriterion("PROPOSER is not null");
            return (Criteria) this;
        }

        public Criteria andProposerEqualTo(String value) {
            addCriterion("PROPOSER =", value, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerNotEqualTo(String value) {
            addCriterion("PROPOSER <>", value, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerGreaterThan(String value) {
            addCriterion("PROPOSER >", value, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerGreaterThanOrEqualTo(String value) {
            addCriterion("PROPOSER >=", value, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerLessThan(String value) {
            addCriterion("PROPOSER <", value, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerLessThanOrEqualTo(String value) {
            addCriterion("PROPOSER <=", value, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerLike(String value) {
            addCriterion("PROPOSER like", value, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerNotLike(String value) {
            addCriterion("PROPOSER not like", value, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerIn(List<String> values) {
            addCriterion("PROPOSER in", values, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerNotIn(List<String> values) {
            addCriterion("PROPOSER not in", values, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerBetween(String value1, String value2) {
            addCriterion("PROPOSER between", value1, value2, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerNotBetween(String value1, String value2) {
            addCriterion("PROPOSER not between", value1, value2, "proposer");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNull() {
            addCriterion("APPLY_DATE is null");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNotNull() {
            addCriterion("APPLY_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andApplyDateEqualTo(String value) {
            addCriterion("APPLY_DATE =", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotEqualTo(String value) {
            addCriterion("APPLY_DATE <>", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThan(String value) {
            addCriterion("APPLY_DATE >", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_DATE >=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThan(String value) {
            addCriterion("APPLY_DATE <", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThanOrEqualTo(String value) {
            addCriterion("APPLY_DATE <=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLike(String value) {
            addCriterion("APPLY_DATE like", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotLike(String value) {
            addCriterion("APPLY_DATE not like", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateIn(List<String> values) {
            addCriterion("APPLY_DATE in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotIn(List<String> values) {
            addCriterion("APPLY_DATE not in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateBetween(String value1, String value2) {
            addCriterion("APPLY_DATE between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotBetween(String value1, String value2) {
            addCriterion("APPLY_DATE not between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andDiscountOrderStatusIsNull() {
            addCriterion("DISCOUNT_ORDER_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andDiscountOrderStatusIsNotNull() {
            addCriterion("DISCOUNT_ORDER_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountOrderStatusEqualTo(Short value) {
            addCriterion("DISCOUNT_ORDER_STATUS =", value, "discountOrderStatus");
            return (Criteria) this;
        }

        public Criteria andDiscountOrderStatusNotEqualTo(Short value) {
            addCriterion("DISCOUNT_ORDER_STATUS <>", value, "discountOrderStatus");
            return (Criteria) this;
        }

        public Criteria andDiscountOrderStatusGreaterThan(Short value) {
            addCriterion("DISCOUNT_ORDER_STATUS >", value, "discountOrderStatus");
            return (Criteria) this;
        }

        public Criteria andDiscountOrderStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("DISCOUNT_ORDER_STATUS >=", value, "discountOrderStatus");
            return (Criteria) this;
        }

        public Criteria andDiscountOrderStatusLessThan(Short value) {
            addCriterion("DISCOUNT_ORDER_STATUS <", value, "discountOrderStatus");
            return (Criteria) this;
        }

        public Criteria andDiscountOrderStatusLessThanOrEqualTo(Short value) {
            addCriterion("DISCOUNT_ORDER_STATUS <=", value, "discountOrderStatus");
            return (Criteria) this;
        }

        public Criteria andDiscountOrderStatusIn(List<Short> values) {
            addCriterion("DISCOUNT_ORDER_STATUS in", values, "discountOrderStatus");
            return (Criteria) this;
        }

        public Criteria andDiscountOrderStatusNotIn(List<Short> values) {
            addCriterion("DISCOUNT_ORDER_STATUS not in", values, "discountOrderStatus");
            return (Criteria) this;
        }

        public Criteria andDiscountOrderStatusBetween(Short value1, Short value2) {
            addCriterion("DISCOUNT_ORDER_STATUS between", value1, value2, "discountOrderStatus");
            return (Criteria) this;
        }

        public Criteria andDiscountOrderStatusNotBetween(Short value1, Short value2) {
            addCriterion("DISCOUNT_ORDER_STATUS not between", value1, value2, "discountOrderStatus");
            return (Criteria) this;
        }

        public Criteria andValidIsNull() {
            addCriterion("VALID is null");
            return (Criteria) this;
        }

        public Criteria andValidIsNotNull() {
            addCriterion("VALID is not null");
            return (Criteria) this;
        }

        public Criteria andValidEqualTo(Short value) {
            addCriterion("VALID =", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotEqualTo(Short value) {
            addCriterion("VALID <>", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThan(Short value) {
            addCriterion("VALID >", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThanOrEqualTo(Short value) {
            addCriterion("VALID >=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThan(Short value) {
            addCriterion("VALID <", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThanOrEqualTo(Short value) {
            addCriterion("VALID <=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidIn(List<Short> values) {
            addCriterion("VALID in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotIn(List<Short> values) {
            addCriterion("VALID not in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidBetween(Short value1, Short value2) {
            addCriterion("VALID between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotBetween(Short value1, Short value2) {
            addCriterion("VALID not between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("CREATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("CREATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(Long value) {
            addCriterion("CREATE_BY =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(Long value) {
            addCriterion("CREATE_BY <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(Long value) {
            addCriterion("CREATE_BY >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(Long value) {
            addCriterion("CREATE_BY >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(Long value) {
            addCriterion("CREATE_BY <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(Long value) {
            addCriterion("CREATE_BY <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<Long> values) {
            addCriterion("CREATE_BY in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<Long> values) {
            addCriterion("CREATE_BY not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(Long value1, Long value2) {
            addCriterion("CREATE_BY between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(Long value1, Long value2) {
            addCriterion("CREATE_BY not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("UPDATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("UPDATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(Long value) {
            addCriterion("UPDATE_BY =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(Long value) {
            addCriterion("UPDATE_BY <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(Long value) {
            addCriterion("UPDATE_BY >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(Long value) {
            addCriterion("UPDATE_BY >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(Long value) {
            addCriterion("UPDATE_BY <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(Long value) {
            addCriterion("UPDATE_BY <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<Long> values) {
            addCriterion("UPDATE_BY in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<Long> values) {
            addCriterion("UPDATE_BY not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(Long value1, Long value2) {
            addCriterion("UPDATE_BY between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(Long value1, Long value2) {
            addCriterion("UPDATE_BY not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("UPDATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("UPDATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("UPDATE_DATE =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("UPDATE_DATE <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("UPDATE_DATE >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("UPDATE_DATE <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("UPDATE_DATE in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("UPDATE_DATE not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE not between", value1, value2, "updateDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}