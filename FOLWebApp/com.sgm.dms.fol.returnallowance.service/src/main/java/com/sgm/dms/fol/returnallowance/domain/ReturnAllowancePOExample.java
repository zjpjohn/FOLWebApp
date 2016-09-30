package com.sgm.dms.fol.returnallowance.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ReturnAllowancePOExample {
	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReturnAllowancePOExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andAllowanceNoIsNull() {
            addCriterion("ALLOWANCE_NO is null");
            return (Criteria) this;
        }

        public Criteria andAllowanceNoIsNotNull() {
            addCriterion("ALLOWANCE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andAllowanceNoEqualTo(String value) {
            addCriterion("ALLOWANCE_NO =", value, "allowanceNo");
            return (Criteria) this;
        }

        public Criteria andAllowanceNoNotEqualTo(String value) {
            addCriterion("ALLOWANCE_NO <>", value, "allowanceNo");
            return (Criteria) this;
        }

        public Criteria andAllowanceNoGreaterThan(String value) {
            addCriterion("ALLOWANCE_NO >", value, "allowanceNo");
            return (Criteria) this;
        }

        public Criteria andAllowanceNoGreaterThanOrEqualTo(String value) {
            addCriterion("ALLOWANCE_NO >=", value, "allowanceNo");
            return (Criteria) this;
        }

        public Criteria andAllowanceNoLessThan(String value) {
            addCriterion("ALLOWANCE_NO <", value, "allowanceNo");
            return (Criteria) this;
        }

        public Criteria andAllowanceNoLessThanOrEqualTo(String value) {
            addCriterion("ALLOWANCE_NO <=", value, "allowanceNo");
            return (Criteria) this;
        }

        public Criteria andAllowanceNoLike(String value) {
            addCriterion("ALLOWANCE_NO like", value, "allowanceNo");
            return (Criteria) this;
        }

        public Criteria andAllowanceNoNotLike(String value) {
            addCriterion("ALLOWANCE_NO not like", value, "allowanceNo");
            return (Criteria) this;
        }

        public Criteria andAllowanceNoIn(List<String> values) {
            addCriterion("ALLOWANCE_NO in", values, "allowanceNo");
            return (Criteria) this;
        }

        public Criteria andAllowanceNoNotIn(List<String> values) {
            addCriterion("ALLOWANCE_NO not in", values, "allowanceNo");
            return (Criteria) this;
        }

        public Criteria andAllowanceNoBetween(String value1, String value2) {
            addCriterion("ALLOWANCE_NO between", value1, value2, "allowanceNo");
            return (Criteria) this;
        }

        public Criteria andAllowanceNoNotBetween(String value1, String value2) {
            addCriterion("ALLOWANCE_NO not between", value1, value2, "allowanceNo");
            return (Criteria) this;
        }

        public Criteria andClaimReturnOrderIdIsNull() {
            addCriterion("CLAIM_RETURN_ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andClaimReturnOrderIdIsNotNull() {
            addCriterion("CLAIM_RETURN_ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andClaimReturnOrderIdEqualTo(Long value) {
            addCriterion("CLAIM_RETURN_ORDER_ID =", value, "claimReturnOrderId");
            return (Criteria) this;
        }

        public Criteria andClaimReturnOrderIdNotEqualTo(Long value) {
            addCriterion("CLAIM_RETURN_ORDER_ID <>", value, "claimReturnOrderId");
            return (Criteria) this;
        }

        public Criteria andClaimReturnOrderIdGreaterThan(Long value) {
            addCriterion("CLAIM_RETURN_ORDER_ID >", value, "claimReturnOrderId");
            return (Criteria) this;
        }

        public Criteria andClaimReturnOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CLAIM_RETURN_ORDER_ID >=", value, "claimReturnOrderId");
            return (Criteria) this;
        }

        public Criteria andClaimReturnOrderIdLessThan(Long value) {
            addCriterion("CLAIM_RETURN_ORDER_ID <", value, "claimReturnOrderId");
            return (Criteria) this;
        }

        public Criteria andClaimReturnOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("CLAIM_RETURN_ORDER_ID <=", value, "claimReturnOrderId");
            return (Criteria) this;
        }

        public Criteria andClaimReturnOrderIdIn(List<Long> values) {
            addCriterion("CLAIM_RETURN_ORDER_ID in", values, "claimReturnOrderId");
            return (Criteria) this;
        }

        public Criteria andClaimReturnOrderIdNotIn(List<Long> values) {
            addCriterion("CLAIM_RETURN_ORDER_ID not in", values, "claimReturnOrderId");
            return (Criteria) this;
        }

        public Criteria andClaimReturnOrderIdBetween(Long value1, Long value2) {
            addCriterion("CLAIM_RETURN_ORDER_ID between", value1, value2, "claimReturnOrderId");
            return (Criteria) this;
        }

        public Criteria andClaimReturnOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("CLAIM_RETURN_ORDER_ID not between", value1, value2, "claimReturnOrderId");
            return (Criteria) this;
        }

        public Criteria andFiledIdIsNull() {
            addCriterion("FILED_ID is null");
            return (Criteria) this;
        }

        public Criteria andFiledIdIsNotNull() {
            addCriterion("FILED_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFiledIdEqualTo(String value) {
            addCriterion("FILED_ID =", value, "filedId");
            return (Criteria) this;
        }

        public Criteria andFiledIdNotEqualTo(String value) {
            addCriterion("FILED_ID <>", value, "filedId");
            return (Criteria) this;
        }

        public Criteria andFiledIdGreaterThan(String value) {
            addCriterion("FILED_ID >", value, "filedId");
            return (Criteria) this;
        }

        public Criteria andFiledIdGreaterThanOrEqualTo(String value) {
            addCriterion("FILED_ID >=", value, "filedId");
            return (Criteria) this;
        }

        public Criteria andFiledIdLessThan(String value) {
            addCriterion("FILED_ID <", value, "filedId");
            return (Criteria) this;
        }

        public Criteria andFiledIdLessThanOrEqualTo(String value) {
            addCriterion("FILED_ID <=", value, "filedId");
            return (Criteria) this;
        }

        public Criteria andFiledIdLike(String value) {
            addCriterion("FILED_ID like", value, "filedId");
            return (Criteria) this;
        }

        public Criteria andFiledIdNotLike(String value) {
            addCriterion("FILED_ID not like", value, "filedId");
            return (Criteria) this;
        }

        public Criteria andFiledIdIn(List<String> values) {
            addCriterion("FILED_ID in", values, "filedId");
            return (Criteria) this;
        }

        public Criteria andFiledIdNotIn(List<String> values) {
            addCriterion("FILED_ID not in", values, "filedId");
            return (Criteria) this;
        }

        public Criteria andFiledIdBetween(String value1, String value2) {
            addCriterion("FILED_ID between", value1, value2, "filedId");
            return (Criteria) this;
        }

        public Criteria andFiledIdNotBetween(String value1, String value2) {
            addCriterion("FILED_ID not between", value1, value2, "filedId");
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

        public Criteria andLinetotalIsNull() {
            addCriterion("LINETOTAL is null");
            return (Criteria) this;
        }

        public Criteria andLinetotalIsNotNull() {
            addCriterion("LINETOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andLinetotalEqualTo(BigDecimal value) {
            addCriterion("LINETOTAL =", value, "linetotal");
            return (Criteria) this;
        }

        public Criteria andLinetotalNotEqualTo(BigDecimal value) {
            addCriterion("LINETOTAL <>", value, "linetotal");
            return (Criteria) this;
        }

        public Criteria andLinetotalGreaterThan(BigDecimal value) {
            addCriterion("LINETOTAL >", value, "linetotal");
            return (Criteria) this;
        }

        public Criteria andLinetotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LINETOTAL >=", value, "linetotal");
            return (Criteria) this;
        }

        public Criteria andLinetotalLessThan(BigDecimal value) {
            addCriterion("LINETOTAL <", value, "linetotal");
            return (Criteria) this;
        }

        public Criteria andLinetotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LINETOTAL <=", value, "linetotal");
            return (Criteria) this;
        }

        public Criteria andLinetotalIn(List<BigDecimal> values) {
            addCriterion("LINETOTAL in", values, "linetotal");
            return (Criteria) this;
        }

        public Criteria andLinetotalNotIn(List<BigDecimal> values) {
            addCriterion("LINETOTAL not in", values, "linetotal");
            return (Criteria) this;
        }

        public Criteria andLinetotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LINETOTAL between", value1, value2, "linetotal");
            return (Criteria) this;
        }

        public Criteria andLinetotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LINETOTAL not between", value1, value2, "linetotal");
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

        public Criteria andReqBillNoIsNull() {
            addCriterion("REQ_BILL_NO is null");
            return (Criteria) this;
        }

        public Criteria andReqBillNoIsNotNull() {
            addCriterion("REQ_BILL_NO is not null");
            return (Criteria) this;
        }

        public Criteria andReqBillNoEqualTo(String value) {
            addCriterion("REQ_BILL_NO =", value, "reqBillNo");
            return (Criteria) this;
        }

        public Criteria andReqBillNoNotEqualTo(String value) {
            addCriterion("REQ_BILL_NO <>", value, "reqBillNo");
            return (Criteria) this;
        }

        public Criteria andReqBillNoGreaterThan(String value) {
            addCriterion("REQ_BILL_NO >", value, "reqBillNo");
            return (Criteria) this;
        }

        public Criteria andReqBillNoGreaterThanOrEqualTo(String value) {
            addCriterion("REQ_BILL_NO >=", value, "reqBillNo");
            return (Criteria) this;
        }

        public Criteria andReqBillNoLessThan(String value) {
            addCriterion("REQ_BILL_NO <", value, "reqBillNo");
            return (Criteria) this;
        }

        public Criteria andReqBillNoLessThanOrEqualTo(String value) {
            addCriterion("REQ_BILL_NO <=", value, "reqBillNo");
            return (Criteria) this;
        }

        public Criteria andReqBillNoLike(String value) {
            addCriterion("REQ_BILL_NO like", value, "reqBillNo");
            return (Criteria) this;
        }

        public Criteria andReqBillNoNotLike(String value) {
            addCriterion("REQ_BILL_NO not like", value, "reqBillNo");
            return (Criteria) this;
        }

        public Criteria andReqBillNoIn(List<String> values) {
            addCriterion("REQ_BILL_NO in", values, "reqBillNo");
            return (Criteria) this;
        }

        public Criteria andReqBillNoNotIn(List<String> values) {
            addCriterion("REQ_BILL_NO not in", values, "reqBillNo");
            return (Criteria) this;
        }

        public Criteria andReqBillNoBetween(String value1, String value2) {
            addCriterion("REQ_BILL_NO between", value1, value2, "reqBillNo");
            return (Criteria) this;
        }

        public Criteria andReqBillNoNotBetween(String value1, String value2) {
            addCriterion("REQ_BILL_NO not between", value1, value2, "reqBillNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoIsNull() {
            addCriterion("EXPRESS_NO is null");
            return (Criteria) this;
        }

        public Criteria andExpressNoIsNotNull() {
            addCriterion("EXPRESS_NO is not null");
            return (Criteria) this;
        }

        public Criteria andExpressNoEqualTo(String value) {
            addCriterion("EXPRESS_NO =", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotEqualTo(String value) {
            addCriterion("EXPRESS_NO <>", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThan(String value) {
            addCriterion("EXPRESS_NO >", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("EXPRESS_NO >=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThan(String value) {
            addCriterion("EXPRESS_NO <", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThanOrEqualTo(String value) {
            addCriterion("EXPRESS_NO <=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLike(String value) {
            addCriterion("EXPRESS_NO like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotLike(String value) {
            addCriterion("EXPRESS_NO not like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoIn(List<String> values) {
            addCriterion("EXPRESS_NO in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotIn(List<String> values) {
            addCriterion("EXPRESS_NO not in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoBetween(String value1, String value2) {
            addCriterion("EXPRESS_NO between", value1, value2, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotBetween(String value1, String value2) {
            addCriterion("EXPRESS_NO not between", value1, value2, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressSendTimeIsNull() {
            addCriterion("EXPRESS_SEND_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExpressSendTimeIsNotNull() {
            addCriterion("EXPRESS_SEND_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExpressSendTimeEqualTo(Date value) {
            addCriterionForJDBCDate("EXPRESS_SEND_TIME =", value, "expressSendTime");
            return (Criteria) this;
        }

        public Criteria andExpressSendTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("EXPRESS_SEND_TIME <>", value, "expressSendTime");
            return (Criteria) this;
        }

        public Criteria andExpressSendTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("EXPRESS_SEND_TIME >", value, "expressSendTime");
            return (Criteria) this;
        }

        public Criteria andExpressSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EXPRESS_SEND_TIME >=", value, "expressSendTime");
            return (Criteria) this;
        }

        public Criteria andExpressSendTimeLessThan(Date value) {
            addCriterionForJDBCDate("EXPRESS_SEND_TIME <", value, "expressSendTime");
            return (Criteria) this;
        }

        public Criteria andExpressSendTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EXPRESS_SEND_TIME <=", value, "expressSendTime");
            return (Criteria) this;
        }

        public Criteria andExpressSendTimeIn(List<Date> values) {
            addCriterionForJDBCDate("EXPRESS_SEND_TIME in", values, "expressSendTime");
            return (Criteria) this;
        }

        public Criteria andExpressSendTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("EXPRESS_SEND_TIME not in", values, "expressSendTime");
            return (Criteria) this;
        }

        public Criteria andExpressSendTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EXPRESS_SEND_TIME between", value1, value2, "expressSendTime");
            return (Criteria) this;
        }

        public Criteria andExpressSendTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EXPRESS_SEND_TIME not between", value1, value2, "expressSendTime");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("PHONE is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("PHONE =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("PHONE <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("PHONE >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("PHONE >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("PHONE <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("PHONE <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("PHONE like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("PHONE not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("PHONE in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("PHONE not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("PHONE between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("PHONE not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNull() {
            addCriterion("BANK_ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNotNull() {
            addCriterion("BANK_ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountEqualTo(String value) {
            addCriterion("BANK_ACCOUNT =", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotEqualTo(String value) {
            addCriterion("BANK_ACCOUNT <>", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThan(String value) {
            addCriterion("BANK_ACCOUNT >", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_ACCOUNT >=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThan(String value) {
            addCriterion("BANK_ACCOUNT <", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThanOrEqualTo(String value) {
            addCriterion("BANK_ACCOUNT <=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLike(String value) {
            addCriterion("BANK_ACCOUNT like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotLike(String value) {
            addCriterion("BANK_ACCOUNT not like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountIn(List<String> values) {
            addCriterion("BANK_ACCOUNT in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotIn(List<String> values) {
            addCriterion("BANK_ACCOUNT not in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountBetween(String value1, String value2) {
            addCriterion("BANK_ACCOUNT between", value1, value2, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotBetween(String value1, String value2) {
            addCriterion("BANK_ACCOUNT not between", value1, value2, "bankAccount");
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

        public Criteria andAddressIsNull() {
            addCriterion("ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("ADDRESS =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("ADDRESS <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("ADDRESS >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ADDRESS >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("ADDRESS <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("ADDRESS <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("ADDRESS like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("ADDRESS not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("ADDRESS in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("ADDRESS not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("ADDRESS between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("ADDRESS not between", value1, value2, "address");
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

        public Criteria andApplyDateEqualTo(Date value) {
            addCriterionForJDBCDate("APPLY_DATE =", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("APPLY_DATE <>", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThan(Date value) {
            addCriterionForJDBCDate("APPLY_DATE >", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("APPLY_DATE >=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThan(Date value) {
            addCriterionForJDBCDate("APPLY_DATE <", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("APPLY_DATE <=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateIn(List<Date> values) {
            addCriterionForJDBCDate("APPLY_DATE in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("APPLY_DATE not in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("APPLY_DATE between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("APPLY_DATE not between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplicantIsNull() {
            addCriterion("APPLICANT is null");
            return (Criteria) this;
        }

        public Criteria andApplicantIsNotNull() {
            addCriterion("APPLICANT is not null");
            return (Criteria) this;
        }

        public Criteria andApplicantEqualTo(Long value) {
            addCriterion("APPLICANT =", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantNotEqualTo(Long value) {
            addCriterion("APPLICANT <>", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantGreaterThan(Long value) {
            addCriterion("APPLICANT >", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantGreaterThanOrEqualTo(Long value) {
            addCriterion("APPLICANT >=", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantLessThan(Long value) {
            addCriterion("APPLICANT <", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantLessThanOrEqualTo(Long value) {
            addCriterion("APPLICANT <=", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantIn(List<Long> values) {
            addCriterion("APPLICANT in", values, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantNotIn(List<Long> values) {
            addCriterion("APPLICANT not in", values, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantBetween(Long value1, Long value2) {
            addCriterion("APPLICANT between", value1, value2, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantNotBetween(Long value1, Long value2) {
            addCriterion("APPLICANT not between", value1, value2, "applicant");
            return (Criteria) this;
        }

        public Criteria andCustomerRemarkIsNull() {
            addCriterion("CUSTOMER_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andCustomerRemarkIsNotNull() {
            addCriterion("CUSTOMER_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerRemarkEqualTo(String value) {
            addCriterion("CUSTOMER_REMARK =", value, "customerRemark");
            return (Criteria) this;
        }

        public Criteria andCustomerRemarkNotEqualTo(String value) {
            addCriterion("CUSTOMER_REMARK <>", value, "customerRemark");
            return (Criteria) this;
        }

        public Criteria andCustomerRemarkGreaterThan(String value) {
            addCriterion("CUSTOMER_REMARK >", value, "customerRemark");
            return (Criteria) this;
        }

        public Criteria andCustomerRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_REMARK >=", value, "customerRemark");
            return (Criteria) this;
        }

        public Criteria andCustomerRemarkLessThan(String value) {
            addCriterion("CUSTOMER_REMARK <", value, "customerRemark");
            return (Criteria) this;
        }

        public Criteria andCustomerRemarkLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_REMARK <=", value, "customerRemark");
            return (Criteria) this;
        }

        public Criteria andCustomerRemarkLike(String value) {
            addCriterion("CUSTOMER_REMARK like", value, "customerRemark");
            return (Criteria) this;
        }

        public Criteria andCustomerRemarkNotLike(String value) {
            addCriterion("CUSTOMER_REMARK not like", value, "customerRemark");
            return (Criteria) this;
        }

        public Criteria andCustomerRemarkIn(List<String> values) {
            addCriterion("CUSTOMER_REMARK in", values, "customerRemark");
            return (Criteria) this;
        }

        public Criteria andCustomerRemarkNotIn(List<String> values) {
            addCriterion("CUSTOMER_REMARK not in", values, "customerRemark");
            return (Criteria) this;
        }

        public Criteria andCustomerRemarkBetween(String value1, String value2) {
            addCriterion("CUSTOMER_REMARK between", value1, value2, "customerRemark");
            return (Criteria) this;
        }

        public Criteria andCustomerRemarkNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_REMARK not between", value1, value2, "customerRemark");
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

        public Criteria andReportDateEqualTo(Date value) {
            addCriterionForJDBCDate("REPORT_DATE =", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("REPORT_DATE <>", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThan(Date value) {
            addCriterionForJDBCDate("REPORT_DATE >", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("REPORT_DATE >=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThan(Date value) {
            addCriterionForJDBCDate("REPORT_DATE <", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("REPORT_DATE <=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateIn(List<Date> values) {
            addCriterionForJDBCDate("REPORT_DATE in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("REPORT_DATE not in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("REPORT_DATE between", value1, value2, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("REPORT_DATE not between", value1, value2, "reportDate");
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

        public Criteria andReporterEqualTo(Long value) {
            addCriterion("REPORTER =", value, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterNotEqualTo(Long value) {
            addCriterion("REPORTER <>", value, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterGreaterThan(Long value) {
            addCriterion("REPORTER >", value, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterGreaterThanOrEqualTo(Long value) {
            addCriterion("REPORTER >=", value, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterLessThan(Long value) {
            addCriterion("REPORTER <", value, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterLessThanOrEqualTo(Long value) {
            addCriterion("REPORTER <=", value, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterIn(List<Long> values) {
            addCriterion("REPORTER in", values, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterNotIn(List<Long> values) {
            addCriterion("REPORTER not in", values, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterBetween(Long value1, Long value2) {
            addCriterion("REPORTER between", value1, value2, "reporter");
            return (Criteria) this;
        }

        public Criteria andReporterNotBetween(Long value1, Long value2) {
            addCriterion("REPORTER not between", value1, value2, "reporter");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Short value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Short value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Short value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Short value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Short value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Short> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Short> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Short value1, Short value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Short value1, Short value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andApproveDateIsNull() {
            addCriterion("APPROVE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andApproveDateIsNotNull() {
            addCriterion("APPROVE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andApproveDateEqualTo(Date value) {
            addCriterionForJDBCDate("APPROVE_DATE =", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("APPROVE_DATE <>", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateGreaterThan(Date value) {
            addCriterionForJDBCDate("APPROVE_DATE >", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("APPROVE_DATE >=", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateLessThan(Date value) {
            addCriterionForJDBCDate("APPROVE_DATE <", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("APPROVE_DATE <=", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateIn(List<Date> values) {
            addCriterionForJDBCDate("APPROVE_DATE in", values, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("APPROVE_DATE not in", values, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("APPROVE_DATE between", value1, value2, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("APPROVE_DATE not between", value1, value2, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveManIsNull() {
            addCriterion("APPROVE_MAN is null");
            return (Criteria) this;
        }

        public Criteria andApproveManIsNotNull() {
            addCriterion("APPROVE_MAN is not null");
            return (Criteria) this;
        }

        public Criteria andApproveManEqualTo(Long value) {
            addCriterion("APPROVE_MAN =", value, "approveMan");
            return (Criteria) this;
        }

        public Criteria andApproveManNotEqualTo(Long value) {
            addCriterion("APPROVE_MAN <>", value, "approveMan");
            return (Criteria) this;
        }

        public Criteria andApproveManGreaterThan(Long value) {
            addCriterion("APPROVE_MAN >", value, "approveMan");
            return (Criteria) this;
        }

        public Criteria andApproveManGreaterThanOrEqualTo(Long value) {
            addCriterion("APPROVE_MAN >=", value, "approveMan");
            return (Criteria) this;
        }

        public Criteria andApproveManLessThan(Long value) {
            addCriterion("APPROVE_MAN <", value, "approveMan");
            return (Criteria) this;
        }

        public Criteria andApproveManLessThanOrEqualTo(Long value) {
            addCriterion("APPROVE_MAN <=", value, "approveMan");
            return (Criteria) this;
        }

        public Criteria andApproveManIn(List<Long> values) {
            addCriterion("APPROVE_MAN in", values, "approveMan");
            return (Criteria) this;
        }

        public Criteria andApproveManNotIn(List<Long> values) {
            addCriterion("APPROVE_MAN not in", values, "approveMan");
            return (Criteria) this;
        }

        public Criteria andApproveManBetween(Long value1, Long value2) {
            addCriterion("APPROVE_MAN between", value1, value2, "approveMan");
            return (Criteria) this;
        }

        public Criteria andApproveManNotBetween(Long value1, Long value2) {
            addCriterion("APPROVE_MAN not between", value1, value2, "approveMan");
            return (Criteria) this;
        }

        public Criteria andApproveMsgIsNull() {
            addCriterion("APPROVE_MSG is null");
            return (Criteria) this;
        }

        public Criteria andApproveMsgIsNotNull() {
            addCriterion("APPROVE_MSG is not null");
            return (Criteria) this;
        }

        public Criteria andApproveMsgEqualTo(String value) {
            addCriterion("APPROVE_MSG =", value, "approveMsg");
            return (Criteria) this;
        }

        public Criteria andApproveMsgNotEqualTo(String value) {
            addCriterion("APPROVE_MSG <>", value, "approveMsg");
            return (Criteria) this;
        }

        public Criteria andApproveMsgGreaterThan(String value) {
            addCriterion("APPROVE_MSG >", value, "approveMsg");
            return (Criteria) this;
        }

        public Criteria andApproveMsgGreaterThanOrEqualTo(String value) {
            addCriterion("APPROVE_MSG >=", value, "approveMsg");
            return (Criteria) this;
        }

        public Criteria andApproveMsgLessThan(String value) {
            addCriterion("APPROVE_MSG <", value, "approveMsg");
            return (Criteria) this;
        }

        public Criteria andApproveMsgLessThanOrEqualTo(String value) {
            addCriterion("APPROVE_MSG <=", value, "approveMsg");
            return (Criteria) this;
        }

        public Criteria andApproveMsgLike(String value) {
            addCriterion("APPROVE_MSG like", value, "approveMsg");
            return (Criteria) this;
        }

        public Criteria andApproveMsgNotLike(String value) {
            addCriterion("APPROVE_MSG not like", value, "approveMsg");
            return (Criteria) this;
        }

        public Criteria andApproveMsgIn(List<String> values) {
            addCriterion("APPROVE_MSG in", values, "approveMsg");
            return (Criteria) this;
        }

        public Criteria andApproveMsgNotIn(List<String> values) {
            addCriterion("APPROVE_MSG not in", values, "approveMsg");
            return (Criteria) this;
        }

        public Criteria andApproveMsgBetween(String value1, String value2) {
            addCriterion("APPROVE_MSG between", value1, value2, "approveMsg");
            return (Criteria) this;
        }

        public Criteria andApproveMsgNotBetween(String value1, String value2) {
            addCriterion("APPROVE_MSG not between", value1, value2, "approveMsg");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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
            addCriterionForJDBCDate("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterionForJDBCDate("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterionForJDBCDate("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_DATE not between", value1, value2, "createDate");
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
            addCriterionForJDBCDate("UPDATE_DATE =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATE_DATE <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterionForJDBCDate("UPDATE_DATE >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATE_DATE >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterionForJDBCDate("UPDATE_DATE <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATE_DATE <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterionForJDBCDate("UPDATE_DATE in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("UPDATE_DATE not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("UPDATE_DATE between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("UPDATE_DATE not between", value1, value2, "updateDate");
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