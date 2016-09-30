package com.sgm.dms.fol.returnallowance.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AllowanceInvoiceInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AllowanceInvoiceInfoExample() {
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

        public Criteria andReturnOrderIdIsNull() {
            addCriterion("RETURN_ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdIsNotNull() {
            addCriterion("RETURN_ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdEqualTo(Long value) {
            addCriterion("RETURN_ORDER_ID =", value, "returnOrderId");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdNotEqualTo(Long value) {
            addCriterion("RETURN_ORDER_ID <>", value, "returnOrderId");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdGreaterThan(Long value) {
            addCriterion("RETURN_ORDER_ID >", value, "returnOrderId");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("RETURN_ORDER_ID >=", value, "returnOrderId");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdLessThan(Long value) {
            addCriterion("RETURN_ORDER_ID <", value, "returnOrderId");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("RETURN_ORDER_ID <=", value, "returnOrderId");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdIn(List<Long> values) {
            addCriterion("RETURN_ORDER_ID in", values, "returnOrderId");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdNotIn(List<Long> values) {
            addCriterion("RETURN_ORDER_ID not in", values, "returnOrderId");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdBetween(Long value1, Long value2) {
            addCriterion("RETURN_ORDER_ID between", value1, value2, "returnOrderId");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("RETURN_ORDER_ID not between", value1, value2, "returnOrderId");
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

        public Criteria andBillingReverseNoIsNull() {
            addCriterion("BILLING_REVERSE_NO is null");
            return (Criteria) this;
        }

        public Criteria andBillingReverseNoIsNotNull() {
            addCriterion("BILLING_REVERSE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andBillingReverseNoEqualTo(String value) {
            addCriterion("BILLING_REVERSE_NO =", value, "billingReverseNo");
            return (Criteria) this;
        }

        public Criteria andBillingReverseNoNotEqualTo(String value) {
            addCriterion("BILLING_REVERSE_NO <>", value, "billingReverseNo");
            return (Criteria) this;
        }

        public Criteria andBillingReverseNoGreaterThan(String value) {
            addCriterion("BILLING_REVERSE_NO >", value, "billingReverseNo");
            return (Criteria) this;
        }

        public Criteria andBillingReverseNoGreaterThanOrEqualTo(String value) {
            addCriterion("BILLING_REVERSE_NO >=", value, "billingReverseNo");
            return (Criteria) this;
        }

        public Criteria andBillingReverseNoLessThan(String value) {
            addCriterion("BILLING_REVERSE_NO <", value, "billingReverseNo");
            return (Criteria) this;
        }

        public Criteria andBillingReverseNoLessThanOrEqualTo(String value) {
            addCriterion("BILLING_REVERSE_NO <=", value, "billingReverseNo");
            return (Criteria) this;
        }

        public Criteria andBillingReverseNoLike(String value) {
            addCriterion("BILLING_REVERSE_NO like", value, "billingReverseNo");
            return (Criteria) this;
        }

        public Criteria andBillingReverseNoNotLike(String value) {
            addCriterion("BILLING_REVERSE_NO not like", value, "billingReverseNo");
            return (Criteria) this;
        }

        public Criteria andBillingReverseNoIn(List<String> values) {
            addCriterion("BILLING_REVERSE_NO in", values, "billingReverseNo");
            return (Criteria) this;
        }

        public Criteria andBillingReverseNoNotIn(List<String> values) {
            addCriterion("BILLING_REVERSE_NO not in", values, "billingReverseNo");
            return (Criteria) this;
        }

        public Criteria andBillingReverseNoBetween(String value1, String value2) {
            addCriterion("BILLING_REVERSE_NO between", value1, value2, "billingReverseNo");
            return (Criteria) this;
        }

        public Criteria andBillingReverseNoNotBetween(String value1, String value2) {
            addCriterion("BILLING_REVERSE_NO not between", value1, value2, "billingReverseNo");
            return (Criteria) this;
        }

        public Criteria andBillingNoIsNull() {
            addCriterion("BILLING_NO is null");
            return (Criteria) this;
        }

        public Criteria andBillingNoIsNotNull() {
            addCriterion("BILLING_NO is not null");
            return (Criteria) this;
        }

        public Criteria andBillingNoEqualTo(String value) {
            addCriterion("BILLING_NO =", value, "billingNo");
            return (Criteria) this;
        }

        public Criteria andBillingNoNotEqualTo(String value) {
            addCriterion("BILLING_NO <>", value, "billingNo");
            return (Criteria) this;
        }

        public Criteria andBillingNoGreaterThan(String value) {
            addCriterion("BILLING_NO >", value, "billingNo");
            return (Criteria) this;
        }

        public Criteria andBillingNoGreaterThanOrEqualTo(String value) {
            addCriterion("BILLING_NO >=", value, "billingNo");
            return (Criteria) this;
        }

        public Criteria andBillingNoLessThan(String value) {
            addCriterion("BILLING_NO <", value, "billingNo");
            return (Criteria) this;
        }

        public Criteria andBillingNoLessThanOrEqualTo(String value) {
            addCriterion("BILLING_NO <=", value, "billingNo");
            return (Criteria) this;
        }

        public Criteria andBillingNoLike(String value) {
            addCriterion("BILLING_NO like", value, "billingNo");
            return (Criteria) this;
        }

        public Criteria andBillingNoNotLike(String value) {
            addCriterion("BILLING_NO not like", value, "billingNo");
            return (Criteria) this;
        }

        public Criteria andBillingNoIn(List<String> values) {
            addCriterion("BILLING_NO in", values, "billingNo");
            return (Criteria) this;
        }

        public Criteria andBillingNoNotIn(List<String> values) {
            addCriterion("BILLING_NO not in", values, "billingNo");
            return (Criteria) this;
        }

        public Criteria andBillingNoBetween(String value1, String value2) {
            addCriterion("BILLING_NO between", value1, value2, "billingNo");
            return (Criteria) this;
        }

        public Criteria andBillingNoNotBetween(String value1, String value2) {
            addCriterion("BILLING_NO not between", value1, value2, "billingNo");
            return (Criteria) this;
        }

        public Criteria andOrderNetvalueIsNull() {
            addCriterion("ORDER_NETVALUE is null");
            return (Criteria) this;
        }

        public Criteria andOrderNetvalueIsNotNull() {
            addCriterion("ORDER_NETVALUE is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNetvalueEqualTo(BigDecimal value) {
            addCriterion("ORDER_NETVALUE =", value, "orderNetvalue");
            return (Criteria) this;
        }

        public Criteria andOrderNetvalueNotEqualTo(BigDecimal value) {
            addCriterion("ORDER_NETVALUE <>", value, "orderNetvalue");
            return (Criteria) this;
        }

        public Criteria andOrderNetvalueGreaterThan(BigDecimal value) {
            addCriterion("ORDER_NETVALUE >", value, "orderNetvalue");
            return (Criteria) this;
        }

        public Criteria andOrderNetvalueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ORDER_NETVALUE >=", value, "orderNetvalue");
            return (Criteria) this;
        }

        public Criteria andOrderNetvalueLessThan(BigDecimal value) {
            addCriterion("ORDER_NETVALUE <", value, "orderNetvalue");
            return (Criteria) this;
        }

        public Criteria andOrderNetvalueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ORDER_NETVALUE <=", value, "orderNetvalue");
            return (Criteria) this;
        }

        public Criteria andOrderNetvalueIn(List<BigDecimal> values) {
            addCriterion("ORDER_NETVALUE in", values, "orderNetvalue");
            return (Criteria) this;
        }

        public Criteria andOrderNetvalueNotIn(List<BigDecimal> values) {
            addCriterion("ORDER_NETVALUE not in", values, "orderNetvalue");
            return (Criteria) this;
        }

        public Criteria andOrderNetvalueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORDER_NETVALUE between", value1, value2, "orderNetvalue");
            return (Criteria) this;
        }

        public Criteria andOrderNetvalueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORDER_NETVALUE not between", value1, value2, "orderNetvalue");
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