package com.qmtec.servicecore.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class TbDBS implements Cloneable, Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column DBS.DB_ID
     *
     * @mbg.generated
     */
    private Long dbId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column DBS.DESC
     *
     * @mbg.generated
     */
    private String desc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column DBS.DB_LOCATION_URI
     *
     * @mbg.generated
     */
    private String dbLocationUri;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column DBS.NAME
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column DBS.OWNER_NAME
     *
     * @mbg.generated
     */
    private String ownerName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column DBS.OWNER_TYPE
     *
     * @mbg.generated
     */
    private String ownerType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column DBS.CTLG_NAME
     *
     * @mbg.generated
     */
    private String ctlgName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table DBS
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column DBS.DB_ID
     *
     * @return the value of DBS.DB_ID
     *
     * @mbg.generated
     */
    public Long getDbId() {
        return dbId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column DBS.DB_ID
     *
     * @param dbId the value for DBS.DB_ID
     *
     * @mbg.generated
     */
    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column DBS.DESC
     *
     * @return the value of DBS.DESC
     *
     * @mbg.generated
     */
    public String getDesc() {
        return desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column DBS.DESC
     *
     * @param desc the value for DBS.DESC
     *
     * @mbg.generated
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column DBS.DB_LOCATION_URI
     *
     * @return the value of DBS.DB_LOCATION_URI
     *
     * @mbg.generated
     */
    public String getDbLocationUri() {
        return dbLocationUri;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column DBS.DB_LOCATION_URI
     *
     * @param dbLocationUri the value for DBS.DB_LOCATION_URI
     *
     * @mbg.generated
     */
    public void setDbLocationUri(String dbLocationUri) {
        this.dbLocationUri = dbLocationUri == null ? null : dbLocationUri.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column DBS.NAME
     *
     * @return the value of DBS.NAME
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column DBS.NAME
     *
     * @param name the value for DBS.NAME
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column DBS.OWNER_NAME
     *
     * @return the value of DBS.OWNER_NAME
     *
     * @mbg.generated
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column DBS.OWNER_NAME
     *
     * @param ownerName the value for DBS.OWNER_NAME
     *
     * @mbg.generated
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column DBS.OWNER_TYPE
     *
     * @return the value of DBS.OWNER_TYPE
     *
     * @mbg.generated
     */
    public String getOwnerType() {
        return ownerType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column DBS.OWNER_TYPE
     *
     * @param ownerType the value for DBS.OWNER_TYPE
     *
     * @mbg.generated
     */
    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType == null ? null : ownerType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column DBS.CTLG_NAME
     *
     * @return the value of DBS.CTLG_NAME
     *
     * @mbg.generated
     */
    public String getCtlgName() {
        return ctlgName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column DBS.CTLG_NAME
     *
     * @param ctlgName the value for DBS.CTLG_NAME
     *
     * @mbg.generated
     */
    public void setCtlgName(String ctlgName) {
        this.ctlgName = ctlgName == null ? null : ctlgName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DBS
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dbId=").append(dbId);
        sb.append(", desc=").append(desc);
        sb.append(", dbLocationUri=").append(dbLocationUri);
        sb.append(", name=").append(name);
        sb.append(", ownerName=").append(ownerName);
        sb.append(", ownerType=").append(ownerType);
        sb.append(", ctlgName=").append(ctlgName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DBS
     *
     * @mbg.generated
     */
    @Override
    public TbDBS clone() throws CloneNotSupportedException {
        return (TbDBS) super.clone();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DBS
     *
     * @mbg.generated
     */
    public static TbDBS.Builder builder() {
        return new TbDBS.Builder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table DBS
     *
     * @mbg.generated
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table DBS
         *
         * @mbg.generated
         */
        private TbDBS obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table DBS
         *
         * @mbg.generated
         */
        public Builder() {
            this.obj = new TbDBS();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column DBS.DB_ID
         *
         * @param dbId the value for DBS.DB_ID
         *
         * @mbg.generated
         */
        public Builder dbId(Long dbId) {
            obj.setDbId(dbId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column DBS.DESC
         *
         * @param desc the value for DBS.DESC
         *
         * @mbg.generated
         */
        public Builder desc(String desc) {
            obj.setDesc(desc);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column DBS.DB_LOCATION_URI
         *
         * @param dbLocationUri the value for DBS.DB_LOCATION_URI
         *
         * @mbg.generated
         */
        public Builder dbLocationUri(String dbLocationUri) {
            obj.setDbLocationUri(dbLocationUri);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column DBS.NAME
         *
         * @param name the value for DBS.NAME
         *
         * @mbg.generated
         */
        public Builder name(String name) {
            obj.setName(name);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column DBS.OWNER_NAME
         *
         * @param ownerName the value for DBS.OWNER_NAME
         *
         * @mbg.generated
         */
        public Builder ownerName(String ownerName) {
            obj.setOwnerName(ownerName);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column DBS.OWNER_TYPE
         *
         * @param ownerType the value for DBS.OWNER_TYPE
         *
         * @mbg.generated
         */
        public Builder ownerType(String ownerType) {
            obj.setOwnerType(ownerType);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column DBS.CTLG_NAME
         *
         * @param ctlgName the value for DBS.CTLG_NAME
         *
         * @mbg.generated
         */
        public Builder ctlgName(String ctlgName) {
            obj.setCtlgName(ctlgName);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table DBS
         *
         * @mbg.generated
         */
        public TbDBS build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table DBS
     *
     * @mbg.generated
     */
    public enum Column {
        dbId("DB_ID", "dbId", "BIGINT", false),
        desc("DESC", "desc", "VARCHAR", true),
        dbLocationUri("DB_LOCATION_URI", "dbLocationUri", "VARCHAR", false),
        name("NAME", "name", "VARCHAR", true),
        ownerName("OWNER_NAME", "ownerName", "VARCHAR", false),
        ownerType("OWNER_TYPE", "ownerType", "VARCHAR", false),
        ctlgName("CTLG_NAME", "ctlgName", "VARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table DBS
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table DBS
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table DBS
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table DBS
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table DBS
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table DBS
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table DBS
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table DBS
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table DBS
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table DBS
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table DBS
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
         * This method corresponds to the database table DBS
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table DBS
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table DBS
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
         * This method corresponds to the database table DBS
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
         * This method corresponds to the database table DBS
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}