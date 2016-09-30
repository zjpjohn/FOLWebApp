package com.sgm.dms.fol.common.service.domains;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DealerInfoExample {
    protected String orderByClause;

    protected boolean distinct;
    
    private Integer beginNo;
    
    private Integer endNo;

    protected List<Criteria> oredCriteria;

    public DealerInfoExample() {
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

        public Criteria andOfficalDealerIdIsNull() {
            addCriterion("OFFICAL_DEALER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOfficalDealerIdIsNotNull() {
            addCriterion("OFFICAL_DEALER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOfficalDealerIdEqualTo(Long value) {
            addCriterion("OFFICAL_DEALER_ID =", value, "officalDealerId");
            return (Criteria) this;
        }

        public Criteria andOfficalDealerIdNotEqualTo(Long value) {
            addCriterion("OFFICAL_DEALER_ID <>", value, "officalDealerId");
            return (Criteria) this;
        }

        public Criteria andOfficalDealerIdGreaterThan(Long value) {
            addCriterion("OFFICAL_DEALER_ID >", value, "officalDealerId");
            return (Criteria) this;
        }

        public Criteria andOfficalDealerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("OFFICAL_DEALER_ID >=", value, "officalDealerId");
            return (Criteria) this;
        }

        public Criteria andOfficalDealerIdLessThan(Long value) {
            addCriterion("OFFICAL_DEALER_ID <", value, "officalDealerId");
            return (Criteria) this;
        }

        public Criteria andOfficalDealerIdLessThanOrEqualTo(Long value) {
            addCriterion("OFFICAL_DEALER_ID <=", value, "officalDealerId");
            return (Criteria) this;
        }

        public Criteria andOfficalDealerIdIn(List<Long> values) {
            addCriterion("OFFICAL_DEALER_ID in", values, "officalDealerId");
            return (Criteria) this;
        }

        public Criteria andOfficalDealerIdNotIn(List<Long> values) {
            addCriterion("OFFICAL_DEALER_ID not in", values, "officalDealerId");
            return (Criteria) this;
        }

        public Criteria andOfficalDealerIdBetween(Long value1, Long value2) {
            addCriterion("OFFICAL_DEALER_ID between", value1, value2, "officalDealerId");
            return (Criteria) this;
        }

        public Criteria andOfficalDealerIdNotBetween(Long value1, Long value2) {
            addCriterion("OFFICAL_DEALER_ID not between", value1, value2, "officalDealerId");
            return (Criteria) this;
        }

        public Criteria andDealerCodeIsNull() {
            addCriterion("DEALER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDealerCodeIsNotNull() {
            addCriterion("DEALER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDealerCodeEqualTo(String value) {
            addCriterion("DEALER_CODE =", value, "dealerCode");
            return (Criteria) this;
        }

        public Criteria andDealerCodeNotEqualTo(String value) {
            addCriterion("DEALER_CODE <>", value, "dealerCode");
            return (Criteria) this;
        }

        public Criteria andDealerCodeGreaterThan(String value) {
            addCriterion("DEALER_CODE >", value, "dealerCode");
            return (Criteria) this;
        }

        public Criteria andDealerCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DEALER_CODE >=", value, "dealerCode");
            return (Criteria) this;
        }

        public Criteria andDealerCodeLessThan(String value) {
            addCriterion("DEALER_CODE <", value, "dealerCode");
            return (Criteria) this;
        }

        public Criteria andDealerCodeLessThanOrEqualTo(String value) {
            addCriterion("DEALER_CODE <=", value, "dealerCode");
            return (Criteria) this;
        }

        public Criteria andDealerCodeLike(String value) {
            addCriterion("DEALER_CODE like", value, "dealerCode");
            return (Criteria) this;
        }

        public Criteria andDealerCodeNotLike(String value) {
            addCriterion("DEALER_CODE not like", value, "dealerCode");
            return (Criteria) this;
        }

        public Criteria andDealerCodeIn(List<String> values) {
            addCriterion("DEALER_CODE in", values, "dealerCode");
            return (Criteria) this;
        }

        public Criteria andDealerCodeNotIn(List<String> values) {
            addCriterion("DEALER_CODE not in", values, "dealerCode");
            return (Criteria) this;
        }

        public Criteria andDealerCodeBetween(String value1, String value2) {
            addCriterion("DEALER_CODE between", value1, value2, "dealerCode");
            return (Criteria) this;
        }

        public Criteria andDealerCodeNotBetween(String value1, String value2) {
            addCriterion("DEALER_CODE not between", value1, value2, "dealerCode");
            return (Criteria) this;
        }

        public Criteria andDealerFullnameIsNull() {
            addCriterion("DEALER_FULLNAME is null");
            return (Criteria) this;
        }

        public Criteria andDealerFullnameIsNotNull() {
            addCriterion("DEALER_FULLNAME is not null");
            return (Criteria) this;
        }

        public Criteria andDealerFullnameEqualTo(String value) {
            addCriterion("DEALER_FULLNAME =", value, "dealerFullname");
            return (Criteria) this;
        }

        public Criteria andDealerFullnameNotEqualTo(String value) {
            addCriterion("DEALER_FULLNAME <>", value, "dealerFullname");
            return (Criteria) this;
        }

        public Criteria andDealerFullnameGreaterThan(String value) {
            addCriterion("DEALER_FULLNAME >", value, "dealerFullname");
            return (Criteria) this;
        }

        public Criteria andDealerFullnameGreaterThanOrEqualTo(String value) {
            addCriterion("DEALER_FULLNAME >=", value, "dealerFullname");
            return (Criteria) this;
        }

        public Criteria andDealerFullnameLessThan(String value) {
            addCriterion("DEALER_FULLNAME <", value, "dealerFullname");
            return (Criteria) this;
        }

        public Criteria andDealerFullnameLessThanOrEqualTo(String value) {
            addCriterion("DEALER_FULLNAME <=", value, "dealerFullname");
            return (Criteria) this;
        }

        public Criteria andDealerFullnameLike(String value) {
            addCriterion("DEALER_FULLNAME like", value, "dealerFullname");
            return (Criteria) this;
        }

        public Criteria andDealerFullnameNotLike(String value) {
            addCriterion("DEALER_FULLNAME not like", value, "dealerFullname");
            return (Criteria) this;
        }

        public Criteria andDealerFullnameIn(List<String> values) {
            addCriterion("DEALER_FULLNAME in", values, "dealerFullname");
            return (Criteria) this;
        }

        public Criteria andDealerFullnameNotIn(List<String> values) {
            addCriterion("DEALER_FULLNAME not in", values, "dealerFullname");
            return (Criteria) this;
        }

        public Criteria andDealerFullnameBetween(String value1, String value2) {
            addCriterion("DEALER_FULLNAME between", value1, value2, "dealerFullname");
            return (Criteria) this;
        }

        public Criteria andDealerFullnameNotBetween(String value1, String value2) {
            addCriterion("DEALER_FULLNAME not between", value1, value2, "dealerFullname");
            return (Criteria) this;
        }

        public Criteria andDealerNameIsNull() {
            addCriterion("DEALER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDealerNameIsNotNull() {
            addCriterion("DEALER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDealerNameEqualTo(String value) {
            addCriterion("DEALER_NAME =", value, "dealerName");
            return (Criteria) this;
        }

        public Criteria andDealerNameNotEqualTo(String value) {
            addCriterion("DEALER_NAME <>", value, "dealerName");
            return (Criteria) this;
        }

        public Criteria andDealerNameGreaterThan(String value) {
            addCriterion("DEALER_NAME >", value, "dealerName");
            return (Criteria) this;
        }

        public Criteria andDealerNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEALER_NAME >=", value, "dealerName");
            return (Criteria) this;
        }

        public Criteria andDealerNameLessThan(String value) {
            addCriterion("DEALER_NAME <", value, "dealerName");
            return (Criteria) this;
        }

        public Criteria andDealerNameLessThanOrEqualTo(String value) {
            addCriterion("DEALER_NAME <=", value, "dealerName");
            return (Criteria) this;
        }

        public Criteria andDealerNameLike(String value) {
            addCriterion("DEALER_NAME like", value, "dealerName");
            return (Criteria) this;
        }

        public Criteria andDealerNameNotLike(String value) {
            addCriterion("DEALER_NAME not like", value, "dealerName");
            return (Criteria) this;
        }

        public Criteria andDealerNameIn(List<String> values) {
            addCriterion("DEALER_NAME in", values, "dealerName");
            return (Criteria) this;
        }

        public Criteria andDealerNameNotIn(List<String> values) {
            addCriterion("DEALER_NAME not in", values, "dealerName");
            return (Criteria) this;
        }

        public Criteria andDealerNameBetween(String value1, String value2) {
            addCriterion("DEALER_NAME between", value1, value2, "dealerName");
            return (Criteria) this;
        }

        public Criteria andDealerNameNotBetween(String value1, String value2) {
            addCriterion("DEALER_NAME not between", value1, value2, "dealerName");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("EMAIL in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("EMAIL not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andDealerTypeIsNull() {
            addCriterion("DEALER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDealerTypeIsNotNull() {
            addCriterion("DEALER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDealerTypeEqualTo(String value) {
            addCriterion("DEALER_TYPE =", value, "dealerType");
            return (Criteria) this;
        }

        public Criteria andDealerTypeNotEqualTo(String value) {
            addCriterion("DEALER_TYPE <>", value, "dealerType");
            return (Criteria) this;
        }

        public Criteria andDealerTypeGreaterThan(String value) {
            addCriterion("DEALER_TYPE >", value, "dealerType");
            return (Criteria) this;
        }

        public Criteria andDealerTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DEALER_TYPE >=", value, "dealerType");
            return (Criteria) this;
        }

        public Criteria andDealerTypeLessThan(String value) {
            addCriterion("DEALER_TYPE <", value, "dealerType");
            return (Criteria) this;
        }

        public Criteria andDealerTypeLessThanOrEqualTo(String value) {
            addCriterion("DEALER_TYPE <=", value, "dealerType");
            return (Criteria) this;
        }

        public Criteria andDealerTypeLike(String value) {
            addCriterion("DEALER_TYPE like", value, "dealerType");
            return (Criteria) this;
        }

        public Criteria andDealerTypeNotLike(String value) {
            addCriterion("DEALER_TYPE not like", value, "dealerType");
            return (Criteria) this;
        }

        public Criteria andDealerTypeIn(List<String> values) {
            addCriterion("DEALER_TYPE in", values, "dealerType");
            return (Criteria) this;
        }

        public Criteria andDealerTypeNotIn(List<String> values) {
            addCriterion("DEALER_TYPE not in", values, "dealerType");
            return (Criteria) this;
        }

        public Criteria andDealerTypeBetween(String value1, String value2) {
            addCriterion("DEALER_TYPE between", value1, value2, "dealerType");
            return (Criteria) this;
        }

        public Criteria andDealerTypeNotBetween(String value1, String value2) {
            addCriterion("DEALER_TYPE not between", value1, value2, "dealerType");
            return (Criteria) this;
        }

        public Criteria andDealerTypeNameIsNull() {
            addCriterion("DEALER_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDealerTypeNameIsNotNull() {
            addCriterion("DEALER_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDealerTypeNameEqualTo(String value) {
            addCriterion("DEALER_TYPE_NAME =", value, "dealerTypeName");
            return (Criteria) this;
        }

        public Criteria andDealerTypeNameNotEqualTo(String value) {
            addCriterion("DEALER_TYPE_NAME <>", value, "dealerTypeName");
            return (Criteria) this;
        }

        public Criteria andDealerTypeNameGreaterThan(String value) {
            addCriterion("DEALER_TYPE_NAME >", value, "dealerTypeName");
            return (Criteria) this;
        }

        public Criteria andDealerTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEALER_TYPE_NAME >=", value, "dealerTypeName");
            return (Criteria) this;
        }

        public Criteria andDealerTypeNameLessThan(String value) {
            addCriterion("DEALER_TYPE_NAME <", value, "dealerTypeName");
            return (Criteria) this;
        }

        public Criteria andDealerTypeNameLessThanOrEqualTo(String value) {
            addCriterion("DEALER_TYPE_NAME <=", value, "dealerTypeName");
            return (Criteria) this;
        }

        public Criteria andDealerTypeNameLike(String value) {
            addCriterion("DEALER_TYPE_NAME like", value, "dealerTypeName");
            return (Criteria) this;
        }

        public Criteria andDealerTypeNameNotLike(String value) {
            addCriterion("DEALER_TYPE_NAME not like", value, "dealerTypeName");
            return (Criteria) this;
        }

        public Criteria andDealerTypeNameIn(List<String> values) {
            addCriterion("DEALER_TYPE_NAME in", values, "dealerTypeName");
            return (Criteria) this;
        }

        public Criteria andDealerTypeNameNotIn(List<String> values) {
            addCriterion("DEALER_TYPE_NAME not in", values, "dealerTypeName");
            return (Criteria) this;
        }

        public Criteria andDealerTypeNameBetween(String value1, String value2) {
            addCriterion("DEALER_TYPE_NAME between", value1, value2, "dealerTypeName");
            return (Criteria) this;
        }

        public Criteria andDealerTypeNameNotBetween(String value1, String value2) {
            addCriterion("DEALER_TYPE_NAME not between", value1, value2, "dealerTypeName");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeIsNull() {
            addCriterion("SAP_DEALER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeIsNotNull() {
            addCriterion("SAP_DEALER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeEqualTo(String value) {
            addCriterion("SAP_DEALER_TYPE =", value, "sapDealerType");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeNotEqualTo(String value) {
            addCriterion("SAP_DEALER_TYPE <>", value, "sapDealerType");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeGreaterThan(String value) {
            addCriterion("SAP_DEALER_TYPE >", value, "sapDealerType");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SAP_DEALER_TYPE >=", value, "sapDealerType");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeLessThan(String value) {
            addCriterion("SAP_DEALER_TYPE <", value, "sapDealerType");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeLessThanOrEqualTo(String value) {
            addCriterion("SAP_DEALER_TYPE <=", value, "sapDealerType");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeLike(String value) {
            addCriterion("SAP_DEALER_TYPE like", value, "sapDealerType");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeNotLike(String value) {
            addCriterion("SAP_DEALER_TYPE not like", value, "sapDealerType");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeIn(List<String> values) {
            addCriterion("SAP_DEALER_TYPE in", values, "sapDealerType");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeNotIn(List<String> values) {
            addCriterion("SAP_DEALER_TYPE not in", values, "sapDealerType");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeBetween(String value1, String value2) {
            addCriterion("SAP_DEALER_TYPE between", value1, value2, "sapDealerType");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeNotBetween(String value1, String value2) {
            addCriterion("SAP_DEALER_TYPE not between", value1, value2, "sapDealerType");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeNameIsNull() {
            addCriterion("SAP_DEALER_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeNameIsNotNull() {
            addCriterion("SAP_DEALER_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeNameEqualTo(String value) {
            addCriterion("SAP_DEALER_TYPE_NAME =", value, "sapDealerTypeName");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeNameNotEqualTo(String value) {
            addCriterion("SAP_DEALER_TYPE_NAME <>", value, "sapDealerTypeName");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeNameGreaterThan(String value) {
            addCriterion("SAP_DEALER_TYPE_NAME >", value, "sapDealerTypeName");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SAP_DEALER_TYPE_NAME >=", value, "sapDealerTypeName");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeNameLessThan(String value) {
            addCriterion("SAP_DEALER_TYPE_NAME <", value, "sapDealerTypeName");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeNameLessThanOrEqualTo(String value) {
            addCriterion("SAP_DEALER_TYPE_NAME <=", value, "sapDealerTypeName");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeNameLike(String value) {
            addCriterion("SAP_DEALER_TYPE_NAME like", value, "sapDealerTypeName");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeNameNotLike(String value) {
            addCriterion("SAP_DEALER_TYPE_NAME not like", value, "sapDealerTypeName");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeNameIn(List<String> values) {
            addCriterion("SAP_DEALER_TYPE_NAME in", values, "sapDealerTypeName");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeNameNotIn(List<String> values) {
            addCriterion("SAP_DEALER_TYPE_NAME not in", values, "sapDealerTypeName");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeNameBetween(String value1, String value2) {
            addCriterion("SAP_DEALER_TYPE_NAME between", value1, value2, "sapDealerTypeName");
            return (Criteria) this;
        }

        public Criteria andSapDealerTypeNameNotBetween(String value1, String value2) {
            addCriterion("SAP_DEALER_TYPE_NAME not between", value1, value2, "sapDealerTypeName");
            return (Criteria) this;
        }

        public Criteria andStatusCodeIsNull() {
            addCriterion("STATUS_CODE is null");
            return (Criteria) this;
        }

        public Criteria andStatusCodeIsNotNull() {
            addCriterion("STATUS_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andStatusCodeEqualTo(Long value) {
            addCriterion("STATUS_CODE =", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeNotEqualTo(Long value) {
            addCriterion("STATUS_CODE <>", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeGreaterThan(Long value) {
            addCriterion("STATUS_CODE >", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("STATUS_CODE >=", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeLessThan(Long value) {
            addCriterion("STATUS_CODE <", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeLessThanOrEqualTo(Long value) {
            addCriterion("STATUS_CODE <=", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeIn(List<Long> values) {
            addCriterion("STATUS_CODE in", values, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeNotIn(List<Long> values) {
            addCriterion("STATUS_CODE not in", values, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeBetween(Long value1, Long value2) {
            addCriterion("STATUS_CODE between", value1, value2, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeNotBetween(Long value1, Long value2) {
            addCriterion("STATUS_CODE not between", value1, value2, "statusCode");
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

        public Criteria andWarrantyCodeIsNull() {
            addCriterion("WARRANTY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andWarrantyCodeIsNotNull() {
            addCriterion("WARRANTY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andWarrantyCodeEqualTo(String value) {
            addCriterion("WARRANTY_CODE =", value, "warrantyCode");
            return (Criteria) this;
        }

        public Criteria andWarrantyCodeNotEqualTo(String value) {
            addCriterion("WARRANTY_CODE <>", value, "warrantyCode");
            return (Criteria) this;
        }

        public Criteria andWarrantyCodeGreaterThan(String value) {
            addCriterion("WARRANTY_CODE >", value, "warrantyCode");
            return (Criteria) this;
        }

        public Criteria andWarrantyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("WARRANTY_CODE >=", value, "warrantyCode");
            return (Criteria) this;
        }

        public Criteria andWarrantyCodeLessThan(String value) {
            addCriterion("WARRANTY_CODE <", value, "warrantyCode");
            return (Criteria) this;
        }

        public Criteria andWarrantyCodeLessThanOrEqualTo(String value) {
            addCriterion("WARRANTY_CODE <=", value, "warrantyCode");
            return (Criteria) this;
        }

        public Criteria andWarrantyCodeLike(String value) {
            addCriterion("WARRANTY_CODE like", value, "warrantyCode");
            return (Criteria) this;
        }

        public Criteria andWarrantyCodeNotLike(String value) {
            addCriterion("WARRANTY_CODE not like", value, "warrantyCode");
            return (Criteria) this;
        }

        public Criteria andWarrantyCodeIn(List<String> values) {
            addCriterion("WARRANTY_CODE in", values, "warrantyCode");
            return (Criteria) this;
        }

        public Criteria andWarrantyCodeNotIn(List<String> values) {
            addCriterion("WARRANTY_CODE not in", values, "warrantyCode");
            return (Criteria) this;
        }

        public Criteria andWarrantyCodeBetween(String value1, String value2) {
            addCriterion("WARRANTY_CODE between", value1, value2, "warrantyCode");
            return (Criteria) this;
        }

        public Criteria andWarrantyCodeNotBetween(String value1, String value2) {
            addCriterion("WARRANTY_CODE not between", value1, value2, "warrantyCode");
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

	public Integer getBeginNo() {
		return beginNo;
	}

	public void setBeginNo(Integer beginNo) {
		this.beginNo = beginNo;
	}

	public Integer getEndNo() {
		return endNo;
	}

	public void setEndNo(Integer endNo) {
		this.endNo = endNo;
	}
    
}