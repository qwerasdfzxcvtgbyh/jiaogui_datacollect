package com.qmtec.servicecore.entity.example;

import com.qmtec.servicecore.entity.DataModelRelationColumn;
import java.util.ArrayList;
import java.util.List;

public class DataModelRelationColumnExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    protected Integer offset;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    protected Integer rows;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    public DataModelRelationColumnExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel_Column
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
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    public DataModelRelationColumnExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    public DataModelRelationColumnExample orderBy(String ... orderByClauses) {
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
     * This method corresponds to the database table t_dataModel_Column
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
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel_Column
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
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    public static Criteria newAndCreateCriteria() {
        DataModelRelationColumnExample example = new DataModelRelationColumnExample();
        return example.createCriteria();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    public DataModelRelationColumnExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    public DataModelRelationColumnExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
        if (condition) {
            then.example(this);
        } else {
            otherwise.example(this);
        }
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    public Integer getOffset() {
        return this.offset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    public void setRows(Integer rows) {
        this.rows = rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    public Integer getRows() {
        return this.rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    public DataModelRelationColumnExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    public DataModelRelationColumnExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated
     */
    public DataModelRelationColumnExample page(Integer page, Integer pageSize) {
        this.offset = page * pageSize;
        this.rows = pageSize;
        return this;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_dataModel_Column
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

        public Criteria andDatamodelIdIsNull() {
            addCriterion("dataModel_id is null");
            return (Criteria) this;
        }

        public Criteria andDatamodelIdIsNotNull() {
            addCriterion("dataModel_id is not null");
            return (Criteria) this;
        }

        public Criteria andDatamodelIdEqualTo(String value) {
            addCriterion("dataModel_id =", value, "datamodelId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_dataModel_Column
         *
         * @mbg.generated
         */
        public Criteria andDatamodelIdEqualToColumn(DataModelRelationColumn.Column column) {
            addCriterion(new StringBuilder("dataModel_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDatamodelIdNotEqualTo(String value) {
            addCriterion("dataModel_id <>", value, "datamodelId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_dataModel_Column
         *
         * @mbg.generated
         */
        public Criteria andDatamodelIdNotEqualToColumn(DataModelRelationColumn.Column column) {
            addCriterion(new StringBuilder("dataModel_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDatamodelIdGreaterThan(String value) {
            addCriterion("dataModel_id >", value, "datamodelId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_dataModel_Column
         *
         * @mbg.generated
         */
        public Criteria andDatamodelIdGreaterThanColumn(DataModelRelationColumn.Column column) {
            addCriterion(new StringBuilder("dataModel_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDatamodelIdGreaterThanOrEqualTo(String value) {
            addCriterion("dataModel_id >=", value, "datamodelId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_dataModel_Column
         *
         * @mbg.generated
         */
        public Criteria andDatamodelIdGreaterThanOrEqualToColumn(DataModelRelationColumn.Column column) {
            addCriterion(new StringBuilder("dataModel_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDatamodelIdLessThan(String value) {
            addCriterion("dataModel_id <", value, "datamodelId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_dataModel_Column
         *
         * @mbg.generated
         */
        public Criteria andDatamodelIdLessThanColumn(DataModelRelationColumn.Column column) {
            addCriterion(new StringBuilder("dataModel_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDatamodelIdLessThanOrEqualTo(String value) {
            addCriterion("dataModel_id <=", value, "datamodelId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_dataModel_Column
         *
         * @mbg.generated
         */
        public Criteria andDatamodelIdLessThanOrEqualToColumn(DataModelRelationColumn.Column column) {
            addCriterion(new StringBuilder("dataModel_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDatamodelIdLike(String value) {
            addCriterion("dataModel_id like", value, "datamodelId");
            return (Criteria) this;
        }

        public Criteria andDatamodelIdNotLike(String value) {
            addCriterion("dataModel_id not like", value, "datamodelId");
            return (Criteria) this;
        }

        public Criteria andDatamodelIdIn(List<String> values) {
            addCriterion("dataModel_id in", values, "datamodelId");
            return (Criteria) this;
        }

        public Criteria andDatamodelIdNotIn(List<String> values) {
            addCriterion("dataModel_id not in", values, "datamodelId");
            return (Criteria) this;
        }

        public Criteria andDatamodelIdBetween(String value1, String value2) {
            addCriterion("dataModel_id between", value1, value2, "datamodelId");
            return (Criteria) this;
        }

        public Criteria andDatamodelIdNotBetween(String value1, String value2) {
            addCriterion("dataModel_id not between", value1, value2, "datamodelId");
            return (Criteria) this;
        }

        public Criteria andColumnIdIsNull() {
            addCriterion("column_id is null");
            return (Criteria) this;
        }

        public Criteria andColumnIdIsNotNull() {
            addCriterion("column_id is not null");
            return (Criteria) this;
        }

        public Criteria andColumnIdEqualTo(String value) {
            addCriterion("column_id =", value, "columnId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_dataModel_Column
         *
         * @mbg.generated
         */
        public Criteria andColumnIdEqualToColumn(DataModelRelationColumn.Column column) {
            addCriterion(new StringBuilder("column_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andColumnIdNotEqualTo(String value) {
            addCriterion("column_id <>", value, "columnId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_dataModel_Column
         *
         * @mbg.generated
         */
        public Criteria andColumnIdNotEqualToColumn(DataModelRelationColumn.Column column) {
            addCriterion(new StringBuilder("column_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andColumnIdGreaterThan(String value) {
            addCriterion("column_id >", value, "columnId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_dataModel_Column
         *
         * @mbg.generated
         */
        public Criteria andColumnIdGreaterThanColumn(DataModelRelationColumn.Column column) {
            addCriterion(new StringBuilder("column_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andColumnIdGreaterThanOrEqualTo(String value) {
            addCriterion("column_id >=", value, "columnId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_dataModel_Column
         *
         * @mbg.generated
         */
        public Criteria andColumnIdGreaterThanOrEqualToColumn(DataModelRelationColumn.Column column) {
            addCriterion(new StringBuilder("column_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andColumnIdLessThan(String value) {
            addCriterion("column_id <", value, "columnId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_dataModel_Column
         *
         * @mbg.generated
         */
        public Criteria andColumnIdLessThanColumn(DataModelRelationColumn.Column column) {
            addCriterion(new StringBuilder("column_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andColumnIdLessThanOrEqualTo(String value) {
            addCriterion("column_id <=", value, "columnId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_dataModel_Column
         *
         * @mbg.generated
         */
        public Criteria andColumnIdLessThanOrEqualToColumn(DataModelRelationColumn.Column column) {
            addCriterion(new StringBuilder("column_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andColumnIdLike(String value) {
            addCriterion("column_id like", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotLike(String value) {
            addCriterion("column_id not like", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdIn(List<String> values) {
            addCriterion("column_id in", values, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotIn(List<String> values) {
            addCriterion("column_id not in", values, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdBetween(String value1, String value2) {
            addCriterion("column_id between", value1, value2, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotBetween(String value1, String value2) {
            addCriterion("column_id not between", value1, value2, "columnId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_dataModel_Column
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_dataModel_Column
         *
         * @mbg.generated
         */
        private DataModelRelationColumnExample example;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_dataModel_Column
         *
         * @mbg.generated
         */
        protected Criteria(DataModelRelationColumnExample example) {
            super();
            this.example = example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_dataModel_Column
         *
         * @mbg.generated
         */
        public DataModelRelationColumnExample example() {
            return this.example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_dataModel_Column
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
         * This method corresponds to the database table t_dataModel_Column
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
     * This class corresponds to the database table t_dataModel_Column
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
         * This method corresponds to the database table t_dataModel_Column
         *
         * @mbg.generated
         */
        void criteria(Criteria criteria);
    }

    public interface IExampleWhen {
        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_dataModel_Column
         *
         * @mbg.generated
         */
        void example(com.qmtec.servicecore.entity.example.DataModelRelationColumnExample example);
    }
}