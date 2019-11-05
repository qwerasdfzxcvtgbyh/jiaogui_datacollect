package com.qmtec.servicecore.entity.example;

import com.qmtec.servicecore.entity.TbPartitionKeyVal;
import java.util.ArrayList;
import java.util.List;

public class TbPartitionKeyValExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    protected Integer offset;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    protected Integer rows;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public TbPartitionKeyValExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public TbPartitionKeyValExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public TbPartitionKeyValExample orderBy(String ... orderByClauses) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < orderByClauses.length; i++) {
            sb.append(orderByClauses[i]);
            if (i < orderByClauses.length - 1) {
                sb.append(" , ");
            }
        }
        this.setOrderByClause(sb.toString());
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
        rows = null;
        offset = null;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public static Criteria newAndCreateCriteria() {
        TbPartitionKeyValExample example = new TbPartitionKeyValExample();
        return example.createCriteria();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public TbPartitionKeyValExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public TbPartitionKeyValExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
        if (condition) {
            then.example(this);
        } else {
            otherwise.example(this);
        }
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public Integer getOffset() {
        return this.offset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public void setRows(Integer rows) {
        this.rows = rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public Integer getRows() {
        return this.rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public TbPartitionKeyValExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public TbPartitionKeyValExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
    public TbPartitionKeyValExample page(Integer page, Integer pageSize) {
        this.offset = page * pageSize;
        this.rows = pageSize;
        return this;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
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

        public Criteria andPartIdIsNull() {
            addCriterion("PART_ID is null");
            return (Criteria) this;
        }

        public Criteria andPartIdIsNotNull() {
            addCriterion("PART_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPartIdEqualTo(Long value) {
            addCriterion("PART_ID =", value, "partId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria andPartIdEqualToColumn(TbPartitionKeyVal.Column column) {
            addCriterion(new StringBuilder("PART_ID = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPartIdNotEqualTo(Long value) {
            addCriterion("PART_ID <>", value, "partId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria andPartIdNotEqualToColumn(TbPartitionKeyVal.Column column) {
            addCriterion(new StringBuilder("PART_ID <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPartIdGreaterThan(Long value) {
            addCriterion("PART_ID >", value, "partId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria andPartIdGreaterThanColumn(TbPartitionKeyVal.Column column) {
            addCriterion(new StringBuilder("PART_ID > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPartIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PART_ID >=", value, "partId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria andPartIdGreaterThanOrEqualToColumn(TbPartitionKeyVal.Column column) {
            addCriterion(new StringBuilder("PART_ID >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPartIdLessThan(Long value) {
            addCriterion("PART_ID <", value, "partId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria andPartIdLessThanColumn(TbPartitionKeyVal.Column column) {
            addCriterion(new StringBuilder("PART_ID < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPartIdLessThanOrEqualTo(Long value) {
            addCriterion("PART_ID <=", value, "partId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria andPartIdLessThanOrEqualToColumn(TbPartitionKeyVal.Column column) {
            addCriterion(new StringBuilder("PART_ID <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPartIdIn(List<Long> values) {
            addCriterion("PART_ID in", values, "partId");
            return (Criteria) this;
        }

        public Criteria andPartIdNotIn(List<Long> values) {
            addCriterion("PART_ID not in", values, "partId");
            return (Criteria) this;
        }

        public Criteria andPartIdBetween(Long value1, Long value2) {
            addCriterion("PART_ID between", value1, value2, "partId");
            return (Criteria) this;
        }

        public Criteria andPartIdNotBetween(Long value1, Long value2) {
            addCriterion("PART_ID not between", value1, value2, "partId");
            return (Criteria) this;
        }

        public Criteria andIntegerIdxIsNull() {
            addCriterion("INTEGER_IDX is null");
            return (Criteria) this;
        }

        public Criteria andIntegerIdxIsNotNull() {
            addCriterion("INTEGER_IDX is not null");
            return (Criteria) this;
        }

        public Criteria andIntegerIdxEqualTo(Integer value) {
            addCriterion("INTEGER_IDX =", value, "integerIdx");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria andIntegerIdxEqualToColumn(TbPartitionKeyVal.Column column) {
            addCriterion(new StringBuilder("INTEGER_IDX = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIntegerIdxNotEqualTo(Integer value) {
            addCriterion("INTEGER_IDX <>", value, "integerIdx");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria andIntegerIdxNotEqualToColumn(TbPartitionKeyVal.Column column) {
            addCriterion(new StringBuilder("INTEGER_IDX <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIntegerIdxGreaterThan(Integer value) {
            addCriterion("INTEGER_IDX >", value, "integerIdx");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria andIntegerIdxGreaterThanColumn(TbPartitionKeyVal.Column column) {
            addCriterion(new StringBuilder("INTEGER_IDX > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIntegerIdxGreaterThanOrEqualTo(Integer value) {
            addCriterion("INTEGER_IDX >=", value, "integerIdx");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria andIntegerIdxGreaterThanOrEqualToColumn(TbPartitionKeyVal.Column column) {
            addCriterion(new StringBuilder("INTEGER_IDX >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIntegerIdxLessThan(Integer value) {
            addCriterion("INTEGER_IDX <", value, "integerIdx");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria andIntegerIdxLessThanColumn(TbPartitionKeyVal.Column column) {
            addCriterion(new StringBuilder("INTEGER_IDX < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIntegerIdxLessThanOrEqualTo(Integer value) {
            addCriterion("INTEGER_IDX <=", value, "integerIdx");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria andIntegerIdxLessThanOrEqualToColumn(TbPartitionKeyVal.Column column) {
            addCriterion(new StringBuilder("INTEGER_IDX <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIntegerIdxIn(List<Integer> values) {
            addCriterion("INTEGER_IDX in", values, "integerIdx");
            return (Criteria) this;
        }

        public Criteria andIntegerIdxNotIn(List<Integer> values) {
            addCriterion("INTEGER_IDX not in", values, "integerIdx");
            return (Criteria) this;
        }

        public Criteria andIntegerIdxBetween(Integer value1, Integer value2) {
            addCriterion("INTEGER_IDX between", value1, value2, "integerIdx");
            return (Criteria) this;
        }

        public Criteria andIntegerIdxNotBetween(Integer value1, Integer value2) {
            addCriterion("INTEGER_IDX not between", value1, value2, "integerIdx");
            return (Criteria) this;
        }

        public Criteria andPartKeyValIsNull() {
            addCriterion("PART_KEY_VAL is null");
            return (Criteria) this;
        }

        public Criteria andPartKeyValIsNotNull() {
            addCriterion("PART_KEY_VAL is not null");
            return (Criteria) this;
        }

        public Criteria andPartKeyValEqualTo(String value) {
            addCriterion("PART_KEY_VAL =", value, "partKeyVal");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria andPartKeyValEqualToColumn(TbPartitionKeyVal.Column column) {
            addCriterion(new StringBuilder("PART_KEY_VAL = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPartKeyValNotEqualTo(String value) {
            addCriterion("PART_KEY_VAL <>", value, "partKeyVal");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria andPartKeyValNotEqualToColumn(TbPartitionKeyVal.Column column) {
            addCriterion(new StringBuilder("PART_KEY_VAL <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPartKeyValGreaterThan(String value) {
            addCriterion("PART_KEY_VAL >", value, "partKeyVal");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria andPartKeyValGreaterThanColumn(TbPartitionKeyVal.Column column) {
            addCriterion(new StringBuilder("PART_KEY_VAL > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPartKeyValGreaterThanOrEqualTo(String value) {
            addCriterion("PART_KEY_VAL >=", value, "partKeyVal");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria andPartKeyValGreaterThanOrEqualToColumn(TbPartitionKeyVal.Column column) {
            addCriterion(new StringBuilder("PART_KEY_VAL >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPartKeyValLessThan(String value) {
            addCriterion("PART_KEY_VAL <", value, "partKeyVal");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria andPartKeyValLessThanColumn(TbPartitionKeyVal.Column column) {
            addCriterion(new StringBuilder("PART_KEY_VAL < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPartKeyValLessThanOrEqualTo(String value) {
            addCriterion("PART_KEY_VAL <=", value, "partKeyVal");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria andPartKeyValLessThanOrEqualToColumn(TbPartitionKeyVal.Column column) {
            addCriterion(new StringBuilder("PART_KEY_VAL <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPartKeyValLike(String value) {
            addCriterion("PART_KEY_VAL like", value, "partKeyVal");
            return (Criteria) this;
        }

        public Criteria andPartKeyValNotLike(String value) {
            addCriterion("PART_KEY_VAL not like", value, "partKeyVal");
            return (Criteria) this;
        }

        public Criteria andPartKeyValIn(List<String> values) {
            addCriterion("PART_KEY_VAL in", values, "partKeyVal");
            return (Criteria) this;
        }

        public Criteria andPartKeyValNotIn(List<String> values) {
            addCriterion("PART_KEY_VAL not in", values, "partKeyVal");
            return (Criteria) this;
        }

        public Criteria andPartKeyValBetween(String value1, String value2) {
            addCriterion("PART_KEY_VAL between", value1, value2, "partKeyVal");
            return (Criteria) this;
        }

        public Criteria andPartKeyValNotBetween(String value1, String value2) {
            addCriterion("PART_KEY_VAL not between", value1, value2, "partKeyVal");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        private TbPartitionKeyValExample example;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        protected Criteria(TbPartitionKeyValExample example) {
            super();
            this.example = example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public TbPartitionKeyValExample example() {
            return this.example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria when(boolean condition, ICriteriaWhen then) {
            if (condition) {
                then.criteria(this);
            }
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        public Criteria when(boolean condition, ICriteriaWhen then, ICriteriaWhen otherwise) {
            if (condition) {
                then.criteria(this);
            } else {
                otherwise.criteria(this);
            }
            return this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PARTITION_KEY_VALS
     *
     * @mbg.generated
     */
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

    public interface ICriteriaWhen {
        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        void criteria(Criteria criteria);
    }

    public interface IExampleWhen {
        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEY_VALS
         *
         * @mbg.generated
         */
        void example(com.qmtec.servicecore.entity.example.TbPartitionKeyValExample example);
    }
}