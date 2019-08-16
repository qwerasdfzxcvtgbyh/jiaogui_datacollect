package com.qmtec.servicecore.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class TbPartitionKeys implements Cloneable, Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PARTITION_KEYS.TBL_ID
     *
     * @mbg.generated
     */
    private Long tblId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PARTITION_KEYS.PKEY_NAME
     *
     * @mbg.generated
     */
    private String pkeyName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PARTITION_KEYS.PKEY_COMMENT
     *
     * @mbg.generated
     */
    private String pkeyComment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PARTITION_KEYS.PKEY_TYPE
     *
     * @mbg.generated
     */
    private String pkeyType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PARTITION_KEYS.INTEGER_IDX
     *
     * @mbg.generated
     */
    private Integer integerIdx;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PARTITION_KEYS
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PARTITION_KEYS.TBL_ID
     *
     * @return the value of PARTITION_KEYS.TBL_ID
     *
     * @mbg.generated
     */
    public Long getTblId() {
        return tblId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PARTITION_KEYS.TBL_ID
     *
     * @param tblId the value for PARTITION_KEYS.TBL_ID
     *
     * @mbg.generated
     */
    public void setTblId(Long tblId) {
        this.tblId = tblId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PARTITION_KEYS.PKEY_NAME
     *
     * @return the value of PARTITION_KEYS.PKEY_NAME
     *
     * @mbg.generated
     */
    public String getPkeyName() {
        return pkeyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PARTITION_KEYS.PKEY_NAME
     *
     * @param pkeyName the value for PARTITION_KEYS.PKEY_NAME
     *
     * @mbg.generated
     */
    public void setPkeyName(String pkeyName) {
        this.pkeyName = pkeyName == null ? null : pkeyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PARTITION_KEYS.PKEY_COMMENT
     *
     * @return the value of PARTITION_KEYS.PKEY_COMMENT
     *
     * @mbg.generated
     */
    public String getPkeyComment() {
        return pkeyComment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PARTITION_KEYS.PKEY_COMMENT
     *
     * @param pkeyComment the value for PARTITION_KEYS.PKEY_COMMENT
     *
     * @mbg.generated
     */
    public void setPkeyComment(String pkeyComment) {
        this.pkeyComment = pkeyComment == null ? null : pkeyComment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PARTITION_KEYS.PKEY_TYPE
     *
     * @return the value of PARTITION_KEYS.PKEY_TYPE
     *
     * @mbg.generated
     */
    public String getPkeyType() {
        return pkeyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PARTITION_KEYS.PKEY_TYPE
     *
     * @param pkeyType the value for PARTITION_KEYS.PKEY_TYPE
     *
     * @mbg.generated
     */
    public void setPkeyType(String pkeyType) {
        this.pkeyType = pkeyType == null ? null : pkeyType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PARTITION_KEYS.INTEGER_IDX
     *
     * @return the value of PARTITION_KEYS.INTEGER_IDX
     *
     * @mbg.generated
     */
    public Integer getIntegerIdx() {
        return integerIdx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PARTITION_KEYS.INTEGER_IDX
     *
     * @param integerIdx the value for PARTITION_KEYS.INTEGER_IDX
     *
     * @mbg.generated
     */
    public void setIntegerIdx(Integer integerIdx) {
        this.integerIdx = integerIdx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEYS
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tblId=").append(tblId);
        sb.append(", pkeyName=").append(pkeyName);
        sb.append(", pkeyComment=").append(pkeyComment);
        sb.append(", pkeyType=").append(pkeyType);
        sb.append(", integerIdx=").append(integerIdx);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEYS
     *
     * @mbg.generated
     */
    @Override
    public TbPartitionKeys clone() throws CloneNotSupportedException {
        return (TbPartitionKeys) super.clone();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PARTITION_KEYS
     *
     * @mbg.generated
     */
    public static TbPartitionKeys.Builder builder() {
        return new TbPartitionKeys.Builder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PARTITION_KEYS
     *
     * @mbg.generated
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table PARTITION_KEYS
         *
         * @mbg.generated
         */
        private TbPartitionKeys obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEYS
         *
         * @mbg.generated
         */
        public Builder() {
            this.obj = new TbPartitionKeys();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column PARTITION_KEYS.TBL_ID
         *
         * @param tblId the value for PARTITION_KEYS.TBL_ID
         *
         * @mbg.generated
         */
        public Builder tblId(Long tblId) {
            obj.setTblId(tblId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column PARTITION_KEYS.PKEY_NAME
         *
         * @param pkeyName the value for PARTITION_KEYS.PKEY_NAME
         *
         * @mbg.generated
         */
        public Builder pkeyName(String pkeyName) {
            obj.setPkeyName(pkeyName);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column PARTITION_KEYS.PKEY_COMMENT
         *
         * @param pkeyComment the value for PARTITION_KEYS.PKEY_COMMENT
         *
         * @mbg.generated
         */
        public Builder pkeyComment(String pkeyComment) {
            obj.setPkeyComment(pkeyComment);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column PARTITION_KEYS.PKEY_TYPE
         *
         * @param pkeyType the value for PARTITION_KEYS.PKEY_TYPE
         *
         * @mbg.generated
         */
        public Builder pkeyType(String pkeyType) {
            obj.setPkeyType(pkeyType);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column PARTITION_KEYS.INTEGER_IDX
         *
         * @param integerIdx the value for PARTITION_KEYS.INTEGER_IDX
         *
         * @mbg.generated
         */
        public Builder integerIdx(Integer integerIdx) {
            obj.setIntegerIdx(integerIdx);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEYS
         *
         * @mbg.generated
         */
        public TbPartitionKeys build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table PARTITION_KEYS
     *
     * @mbg.generated
     */
    public enum Column {
        tblId("TBL_ID", "tblId", "BIGINT", false),
        pkeyName("PKEY_NAME", "pkeyName", "VARCHAR", false),
        pkeyComment("PKEY_COMMENT", "pkeyComment", "VARCHAR", false),
        pkeyType("PKEY_TYPE", "pkeyType", "VARCHAR", false),
        integerIdx("INTEGER_IDX", "integerIdx", "INTEGER", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table PARTITION_KEYS
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table PARTITION_KEYS
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table PARTITION_KEYS
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table PARTITION_KEYS
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table PARTITION_KEYS
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table PARTITION_KEYS
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEYS
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEYS
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEYS
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEYS
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEYS
         *
         * @mbg.generated
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEYS
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEYS
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEYS
         *
         * @mbg.generated
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEYS
         *
         * @mbg.generated
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table PARTITION_KEYS
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}