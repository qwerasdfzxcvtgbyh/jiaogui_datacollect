package com.qmtec.servicecore.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class TbIDXS implements Cloneable, Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column IDXS.INDEX_ID
     *
     * @mbg.generated
     */
    private Long indexId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column IDXS.CREATE_TIME
     *
     * @mbg.generated
     */
    private Integer createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column IDXS.DEFERRED_REBUILD
     *
     * @mbg.generated
     */
    private Boolean deferredRebuild;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column IDXS.INDEX_HANDLER_CLASS
     *
     * @mbg.generated
     */
    private String indexHandlerClass;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column IDXS.INDEX_NAME
     *
     * @mbg.generated
     */
    private String indexName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column IDXS.INDEX_TBL_ID
     *
     * @mbg.generated
     */
    private Long indexTblId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column IDXS.LAST_ACCESS_TIME
     *
     * @mbg.generated
     */
    private Integer lastAccessTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column IDXS.ORIG_TBL_ID
     *
     * @mbg.generated
     */
    private Long origTblId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column IDXS.SD_ID
     *
     * @mbg.generated
     */
    private Long sdId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column IDXS.INDEX_ID
     *
     * @return the value of IDXS.INDEX_ID
     *
     * @mbg.generated
     */
    public Long getIndexId() {
        return indexId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column IDXS.INDEX_ID
     *
     * @param indexId the value for IDXS.INDEX_ID
     *
     * @mbg.generated
     */
    public void setIndexId(Long indexId) {
        this.indexId = indexId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column IDXS.CREATE_TIME
     *
     * @return the value of IDXS.CREATE_TIME
     *
     * @mbg.generated
     */
    public Integer getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column IDXS.CREATE_TIME
     *
     * @param createTime the value for IDXS.CREATE_TIME
     *
     * @mbg.generated
     */
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column IDXS.DEFERRED_REBUILD
     *
     * @return the value of IDXS.DEFERRED_REBUILD
     *
     * @mbg.generated
     */
    public Boolean getDeferredRebuild() {
        return deferredRebuild;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column IDXS.DEFERRED_REBUILD
     *
     * @param deferredRebuild the value for IDXS.DEFERRED_REBUILD
     *
     * @mbg.generated
     */
    public void setDeferredRebuild(Boolean deferredRebuild) {
        this.deferredRebuild = deferredRebuild;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column IDXS.INDEX_HANDLER_CLASS
     *
     * @return the value of IDXS.INDEX_HANDLER_CLASS
     *
     * @mbg.generated
     */
    public String getIndexHandlerClass() {
        return indexHandlerClass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column IDXS.INDEX_HANDLER_CLASS
     *
     * @param indexHandlerClass the value for IDXS.INDEX_HANDLER_CLASS
     *
     * @mbg.generated
     */
    public void setIndexHandlerClass(String indexHandlerClass) {
        this.indexHandlerClass = indexHandlerClass == null ? null : indexHandlerClass.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column IDXS.INDEX_NAME
     *
     * @return the value of IDXS.INDEX_NAME
     *
     * @mbg.generated
     */
    public String getIndexName() {
        return indexName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column IDXS.INDEX_NAME
     *
     * @param indexName the value for IDXS.INDEX_NAME
     *
     * @mbg.generated
     */
    public void setIndexName(String indexName) {
        this.indexName = indexName == null ? null : indexName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column IDXS.INDEX_TBL_ID
     *
     * @return the value of IDXS.INDEX_TBL_ID
     *
     * @mbg.generated
     */
    public Long getIndexTblId() {
        return indexTblId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column IDXS.INDEX_TBL_ID
     *
     * @param indexTblId the value for IDXS.INDEX_TBL_ID
     *
     * @mbg.generated
     */
    public void setIndexTblId(Long indexTblId) {
        this.indexTblId = indexTblId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column IDXS.LAST_ACCESS_TIME
     *
     * @return the value of IDXS.LAST_ACCESS_TIME
     *
     * @mbg.generated
     */
    public Integer getLastAccessTime() {
        return lastAccessTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column IDXS.LAST_ACCESS_TIME
     *
     * @param lastAccessTime the value for IDXS.LAST_ACCESS_TIME
     *
     * @mbg.generated
     */
    public void setLastAccessTime(Integer lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column IDXS.ORIG_TBL_ID
     *
     * @return the value of IDXS.ORIG_TBL_ID
     *
     * @mbg.generated
     */
    public Long getOrigTblId() {
        return origTblId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column IDXS.ORIG_TBL_ID
     *
     * @param origTblId the value for IDXS.ORIG_TBL_ID
     *
     * @mbg.generated
     */
    public void setOrigTblId(Long origTblId) {
        this.origTblId = origTblId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column IDXS.SD_ID
     *
     * @return the value of IDXS.SD_ID
     *
     * @mbg.generated
     */
    public Long getSdId() {
        return sdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column IDXS.SD_ID
     *
     * @param sdId the value for IDXS.SD_ID
     *
     * @mbg.generated
     */
    public void setSdId(Long sdId) {
        this.sdId = sdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", indexId=").append(indexId);
        sb.append(", createTime=").append(createTime);
        sb.append(", deferredRebuild=").append(deferredRebuild);
        sb.append(", indexHandlerClass=").append(indexHandlerClass);
        sb.append(", indexName=").append(indexName);
        sb.append(", indexTblId=").append(indexTblId);
        sb.append(", lastAccessTime=").append(lastAccessTime);
        sb.append(", origTblId=").append(origTblId);
        sb.append(", sdId=").append(sdId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    @Override
    public TbIDXS clone() throws CloneNotSupportedException {
        return (TbIDXS) super.clone();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    public static TbIDXS.Builder builder() {
        return new TbIDXS.Builder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table IDXS
         *
         * @mbg.generated
         */
        private TbIDXS obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table IDXS
         *
         * @mbg.generated
         */
        public Builder() {
            this.obj = new TbIDXS();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column IDXS.INDEX_ID
         *
         * @param indexId the value for IDXS.INDEX_ID
         *
         * @mbg.generated
         */
        public Builder indexId(Long indexId) {
            obj.setIndexId(indexId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column IDXS.CREATE_TIME
         *
         * @param createTime the value for IDXS.CREATE_TIME
         *
         * @mbg.generated
         */
        public Builder createTime(Integer createTime) {
            obj.setCreateTime(createTime);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column IDXS.DEFERRED_REBUILD
         *
         * @param deferredRebuild the value for IDXS.DEFERRED_REBUILD
         *
         * @mbg.generated
         */
        public Builder deferredRebuild(Boolean deferredRebuild) {
            obj.setDeferredRebuild(deferredRebuild);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column IDXS.INDEX_HANDLER_CLASS
         *
         * @param indexHandlerClass the value for IDXS.INDEX_HANDLER_CLASS
         *
         * @mbg.generated
         */
        public Builder indexHandlerClass(String indexHandlerClass) {
            obj.setIndexHandlerClass(indexHandlerClass);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column IDXS.INDEX_NAME
         *
         * @param indexName the value for IDXS.INDEX_NAME
         *
         * @mbg.generated
         */
        public Builder indexName(String indexName) {
            obj.setIndexName(indexName);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column IDXS.INDEX_TBL_ID
         *
         * @param indexTblId the value for IDXS.INDEX_TBL_ID
         *
         * @mbg.generated
         */
        public Builder indexTblId(Long indexTblId) {
            obj.setIndexTblId(indexTblId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column IDXS.LAST_ACCESS_TIME
         *
         * @param lastAccessTime the value for IDXS.LAST_ACCESS_TIME
         *
         * @mbg.generated
         */
        public Builder lastAccessTime(Integer lastAccessTime) {
            obj.setLastAccessTime(lastAccessTime);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column IDXS.ORIG_TBL_ID
         *
         * @param origTblId the value for IDXS.ORIG_TBL_ID
         *
         * @mbg.generated
         */
        public Builder origTblId(Long origTblId) {
            obj.setOrigTblId(origTblId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column IDXS.SD_ID
         *
         * @param sdId the value for IDXS.SD_ID
         *
         * @mbg.generated
         */
        public Builder sdId(Long sdId) {
            obj.setSdId(sdId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table IDXS
         *
         * @mbg.generated
         */
        public TbIDXS build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    public enum Column {
        indexId("INDEX_ID", "indexId", "BIGINT", false),
        createTime("CREATE_TIME", "createTime", "INTEGER", false),
        deferredRebuild("DEFERRED_REBUILD", "deferredRebuild", "BIT", false),
        indexHandlerClass("INDEX_HANDLER_CLASS", "indexHandlerClass", "VARCHAR", false),
        indexName("INDEX_NAME", "indexName", "VARCHAR", false),
        indexTblId("INDEX_TBL_ID", "indexTblId", "BIGINT", false),
        lastAccessTime("LAST_ACCESS_TIME", "lastAccessTime", "INTEGER", false),
        origTblId("ORIG_TBL_ID", "origTblId", "BIGINT", false),
        sdId("SD_ID", "sdId", "BIGINT", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table IDXS
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table IDXS
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table IDXS
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table IDXS
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table IDXS
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table IDXS
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table IDXS
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table IDXS
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table IDXS
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table IDXS
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table IDXS
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
         * This method corresponds to the database table IDXS
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table IDXS
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table IDXS
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
         * This method corresponds to the database table IDXS
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
         * This method corresponds to the database table IDXS
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}