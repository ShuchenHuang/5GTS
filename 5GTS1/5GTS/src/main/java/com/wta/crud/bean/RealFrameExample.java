package com.wta.crud.bean;

import java.util.ArrayList;
import java.util.List;

public class RealFrameExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RealFrameExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBRealFrame1IsNull() {
            addCriterion("b_real_frame1 is null");
            return (Criteria) this;
        }

        public Criteria andBRealFrame1IsNotNull() {
            addCriterion("b_real_frame1 is not null");
            return (Criteria) this;
        }

        public Criteria andBRealFrame1EqualTo(Integer value) {
            addCriterion("b_real_frame1 =", value, "bRealFrame1");
            return (Criteria) this;
        }

        public Criteria andBRealFrame1NotEqualTo(Integer value) {
            addCriterion("b_real_frame1 <>", value, "bRealFrame1");
            return (Criteria) this;
        }

        public Criteria andBRealFrame1GreaterThan(Integer value) {
            addCriterion("b_real_frame1 >", value, "bRealFrame1");
            return (Criteria) this;
        }

        public Criteria andBRealFrame1GreaterThanOrEqualTo(Integer value) {
            addCriterion("b_real_frame1 >=", value, "bRealFrame1");
            return (Criteria) this;
        }

        public Criteria andBRealFrame1LessThan(Integer value) {
            addCriterion("b_real_frame1 <", value, "bRealFrame1");
            return (Criteria) this;
        }

        public Criteria andBRealFrame1LessThanOrEqualTo(Integer value) {
            addCriterion("b_real_frame1 <=", value, "bRealFrame1");
            return (Criteria) this;
        }

        public Criteria andBRealFrame1In(List<Integer> values) {
            addCriterion("b_real_frame1 in", values, "bRealFrame1");
            return (Criteria) this;
        }

        public Criteria andBRealFrame1NotIn(List<Integer> values) {
            addCriterion("b_real_frame1 not in", values, "bRealFrame1");
            return (Criteria) this;
        }

        public Criteria andBRealFrame1Between(Integer value1, Integer value2) {
            addCriterion("b_real_frame1 between", value1, value2, "bRealFrame1");
            return (Criteria) this;
        }

        public Criteria andBRealFrame1NotBetween(Integer value1, Integer value2) {
            addCriterion("b_real_frame1 not between", value1, value2, "bRealFrame1");
            return (Criteria) this;
        }

        public Criteria andPRealFrame1IsNull() {
            addCriterion("p_real_frame1 is null");
            return (Criteria) this;
        }

        public Criteria andPRealFrame1IsNotNull() {
            addCriterion("p_real_frame1 is not null");
            return (Criteria) this;
        }

        public Criteria andPRealFrame1EqualTo(Integer value) {
            addCriterion("p_real_frame1 =", value, "pRealFrame1");
            return (Criteria) this;
        }

        public Criteria andPRealFrame1NotEqualTo(Integer value) {
            addCriterion("p_real_frame1 <>", value, "pRealFrame1");
            return (Criteria) this;
        }

        public Criteria andPRealFrame1GreaterThan(Integer value) {
            addCriterion("p_real_frame1 >", value, "pRealFrame1");
            return (Criteria) this;
        }

        public Criteria andPRealFrame1GreaterThanOrEqualTo(Integer value) {
            addCriterion("p_real_frame1 >=", value, "pRealFrame1");
            return (Criteria) this;
        }

        public Criteria andPRealFrame1LessThan(Integer value) {
            addCriterion("p_real_frame1 <", value, "pRealFrame1");
            return (Criteria) this;
        }

        public Criteria andPRealFrame1LessThanOrEqualTo(Integer value) {
            addCriterion("p_real_frame1 <=", value, "pRealFrame1");
            return (Criteria) this;
        }

        public Criteria andPRealFrame1In(List<Integer> values) {
            addCriterion("p_real_frame1 in", values, "pRealFrame1");
            return (Criteria) this;
        }

        public Criteria andPRealFrame1NotIn(List<Integer> values) {
            addCriterion("p_real_frame1 not in", values, "pRealFrame1");
            return (Criteria) this;
        }

        public Criteria andPRealFrame1Between(Integer value1, Integer value2) {
            addCriterion("p_real_frame1 between", value1, value2, "pRealFrame1");
            return (Criteria) this;
        }

        public Criteria andPRealFrame1NotBetween(Integer value1, Integer value2) {
            addCriterion("p_real_frame1 not between", value1, value2, "pRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIRealFrame1IsNull() {
            addCriterion("i_real_frame1 is null");
            return (Criteria) this;
        }

        public Criteria andIRealFrame1IsNotNull() {
            addCriterion("i_real_frame1 is not null");
            return (Criteria) this;
        }

        public Criteria andIRealFrame1EqualTo(Integer value) {
            addCriterion("i_real_frame1 =", value, "iRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIRealFrame1NotEqualTo(Integer value) {
            addCriterion("i_real_frame1 <>", value, "iRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIRealFrame1GreaterThan(Integer value) {
            addCriterion("i_real_frame1 >", value, "iRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIRealFrame1GreaterThanOrEqualTo(Integer value) {
            addCriterion("i_real_frame1 >=", value, "iRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIRealFrame1LessThan(Integer value) {
            addCriterion("i_real_frame1 <", value, "iRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIRealFrame1LessThanOrEqualTo(Integer value) {
            addCriterion("i_real_frame1 <=", value, "iRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIRealFrame1In(List<Integer> values) {
            addCriterion("i_real_frame1 in", values, "iRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIRealFrame1NotIn(List<Integer> values) {
            addCriterion("i_real_frame1 not in", values, "iRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIRealFrame1Between(Integer value1, Integer value2) {
            addCriterion("i_real_frame1 between", value1, value2, "iRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIRealFrame1NotBetween(Integer value1, Integer value2) {
            addCriterion("i_real_frame1 not between", value1, value2, "iRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIpbRealFrame1IsNull() {
            addCriterion("ipb_real_frame1 is null");
            return (Criteria) this;
        }

        public Criteria andIpbRealFrame1IsNotNull() {
            addCriterion("ipb_real_frame1 is not null");
            return (Criteria) this;
        }

        public Criteria andIpbRealFrame1EqualTo(Integer value) {
            addCriterion("ipb_real_frame1 =", value, "ipbRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIpbRealFrame1NotEqualTo(Integer value) {
            addCriterion("ipb_real_frame1 <>", value, "ipbRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIpbRealFrame1GreaterThan(Integer value) {
            addCriterion("ipb_real_frame1 >", value, "ipbRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIpbRealFrame1GreaterThanOrEqualTo(Integer value) {
            addCriterion("ipb_real_frame1 >=", value, "ipbRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIpbRealFrame1LessThan(Integer value) {
            addCriterion("ipb_real_frame1 <", value, "ipbRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIpbRealFrame1LessThanOrEqualTo(Integer value) {
            addCriterion("ipb_real_frame1 <=", value, "ipbRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIpbRealFrame1In(List<Integer> values) {
            addCriterion("ipb_real_frame1 in", values, "ipbRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIpbRealFrame1NotIn(List<Integer> values) {
            addCriterion("ipb_real_frame1 not in", values, "ipbRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIpbRealFrame1Between(Integer value1, Integer value2) {
            addCriterion("ipb_real_frame1 between", value1, value2, "ipbRealFrame1");
            return (Criteria) this;
        }

        public Criteria andIpbRealFrame1NotBetween(Integer value1, Integer value2) {
            addCriterion("ipb_real_frame1 not between", value1, value2, "ipbRealFrame1");
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