package com.qmtec.servicecore.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ChannelInfo implements Cloneable, Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_flume_channelInfo.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_flume_channelInfo.component_name
     *
     * @mbg.generated
     */
    private String componentName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_flume_channelInfo.start_time
     *
     * @mbg.generated
     */
    private Date startTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_flume_channelInfo.ip_addr
     *
     * @mbg.generated
     */
    private String ipAddr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_flume_channelInfo.put_success_count
     *
     * @mbg.generated
     */
    private String putSuccessCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_flume_channelInfo.put_attempt_count
     *
     * @mbg.generated
     */
    private String putAttemptCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_flume_channelInfo.percentage
     *
     * @mbg.generated
     */
    private String percentage;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_flume_channelInfo.channel_size
     *
     * @mbg.generated
     */
    private String channelSize;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_flume_channelInfo.take_success_count
     *
     * @mbg.generated
     */
    private String takeSuccessCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_flume_channelInfo.take_attempt_count
     *
     * @mbg.generated
     */
    private String takeAttemptCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_flume_channelInfo.run_state
     *
     * @mbg.generated
     */
    private Integer runState;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_flume_channelInfo.delete_flag
     *
     * @mbg.generated
     */
    private Integer deleteFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_flume_channelInfo.channel_capacity
     *
     * @mbg.generated
     */
    private String channelCapacity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_flume_channelInfo.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_flume_channelInfo.modify_time
     *
     * @mbg.generated
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_flume_channelInfo
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_flume_channelInfo.id
     *
     * @return the value of t_flume_channelInfo.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_flume_channelInfo.id
     *
     * @param id the value for t_flume_channelInfo.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_flume_channelInfo.component_name
     *
     * @return the value of t_flume_channelInfo.component_name
     *
     * @mbg.generated
     */
    public String getComponentName() {
        return componentName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_flume_channelInfo.component_name
     *
     * @param componentName the value for t_flume_channelInfo.component_name
     *
     * @mbg.generated
     */
    public void setComponentName(String componentName) {
        this.componentName = componentName == null ? null : componentName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_flume_channelInfo.start_time
     *
     * @return the value of t_flume_channelInfo.start_time
     *
     * @mbg.generated
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_flume_channelInfo.start_time
     *
     * @param startTime the value for t_flume_channelInfo.start_time
     *
     * @mbg.generated
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_flume_channelInfo.ip_addr
     *
     * @return the value of t_flume_channelInfo.ip_addr
     *
     * @mbg.generated
     */
    public String getIpAddr() {
        return ipAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_flume_channelInfo.ip_addr
     *
     * @param ipAddr the value for t_flume_channelInfo.ip_addr
     *
     * @mbg.generated
     */
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr == null ? null : ipAddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_flume_channelInfo.put_success_count
     *
     * @return the value of t_flume_channelInfo.put_success_count
     *
     * @mbg.generated
     */
    public String getPutSuccessCount() {
        return putSuccessCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_flume_channelInfo.put_success_count
     *
     * @param putSuccessCount the value for t_flume_channelInfo.put_success_count
     *
     * @mbg.generated
     */
    public void setPutSuccessCount(String putSuccessCount) {
        this.putSuccessCount = putSuccessCount == null ? null : putSuccessCount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_flume_channelInfo.put_attempt_count
     *
     * @return the value of t_flume_channelInfo.put_attempt_count
     *
     * @mbg.generated
     */
    public String getPutAttemptCount() {
        return putAttemptCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_flume_channelInfo.put_attempt_count
     *
     * @param putAttemptCount the value for t_flume_channelInfo.put_attempt_count
     *
     * @mbg.generated
     */
    public void setPutAttemptCount(String putAttemptCount) {
        this.putAttemptCount = putAttemptCount == null ? null : putAttemptCount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_flume_channelInfo.percentage
     *
     * @return the value of t_flume_channelInfo.percentage
     *
     * @mbg.generated
     */
    public String getPercentage() {
        return percentage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_flume_channelInfo.percentage
     *
     * @param percentage the value for t_flume_channelInfo.percentage
     *
     * @mbg.generated
     */
    public void setPercentage(String percentage) {
        this.percentage = percentage == null ? null : percentage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_flume_channelInfo.channel_size
     *
     * @return the value of t_flume_channelInfo.channel_size
     *
     * @mbg.generated
     */
    public String getChannelSize() {
        return channelSize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_flume_channelInfo.channel_size
     *
     * @param channelSize the value for t_flume_channelInfo.channel_size
     *
     * @mbg.generated
     */
    public void setChannelSize(String channelSize) {
        this.channelSize = channelSize == null ? null : channelSize.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_flume_channelInfo.take_success_count
     *
     * @return the value of t_flume_channelInfo.take_success_count
     *
     * @mbg.generated
     */
    public String getTakeSuccessCount() {
        return takeSuccessCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_flume_channelInfo.take_success_count
     *
     * @param takeSuccessCount the value for t_flume_channelInfo.take_success_count
     *
     * @mbg.generated
     */
    public void setTakeSuccessCount(String takeSuccessCount) {
        this.takeSuccessCount = takeSuccessCount == null ? null : takeSuccessCount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_flume_channelInfo.take_attempt_count
     *
     * @return the value of t_flume_channelInfo.take_attempt_count
     *
     * @mbg.generated
     */
    public String getTakeAttemptCount() {
        return takeAttemptCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_flume_channelInfo.take_attempt_count
     *
     * @param takeAttemptCount the value for t_flume_channelInfo.take_attempt_count
     *
     * @mbg.generated
     */
    public void setTakeAttemptCount(String takeAttemptCount) {
        this.takeAttemptCount = takeAttemptCount == null ? null : takeAttemptCount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_flume_channelInfo.run_state
     *
     * @return the value of t_flume_channelInfo.run_state
     *
     * @mbg.generated
     */
    public Integer getRunState() {
        return runState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_flume_channelInfo.run_state
     *
     * @param runState the value for t_flume_channelInfo.run_state
     *
     * @mbg.generated
     */
    public void setRunState(Integer runState) {
        this.runState = runState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_flume_channelInfo.delete_flag
     *
     * @return the value of t_flume_channelInfo.delete_flag
     *
     * @mbg.generated
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_flume_channelInfo.delete_flag
     *
     * @param deleteFlag the value for t_flume_channelInfo.delete_flag
     *
     * @mbg.generated
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_flume_channelInfo.channel_capacity
     *
     * @return the value of t_flume_channelInfo.channel_capacity
     *
     * @mbg.generated
     */
    public String getChannelCapacity() {
        return channelCapacity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_flume_channelInfo.channel_capacity
     *
     * @param channelCapacity the value for t_flume_channelInfo.channel_capacity
     *
     * @mbg.generated
     */
    public void setChannelCapacity(String channelCapacity) {
        this.channelCapacity = channelCapacity == null ? null : channelCapacity.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_flume_channelInfo.create_time
     *
     * @return the value of t_flume_channelInfo.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_flume_channelInfo.create_time
     *
     * @param createTime the value for t_flume_channelInfo.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_flume_channelInfo.modify_time
     *
     * @return the value of t_flume_channelInfo.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_flume_channelInfo.modify_time
     *
     * @param modifyTime the value for t_flume_channelInfo.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_flume_channelInfo
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", componentName=").append(componentName);
        sb.append(", startTime=").append(startTime);
        sb.append(", ipAddr=").append(ipAddr);
        sb.append(", putSuccessCount=").append(putSuccessCount);
        sb.append(", putAttemptCount=").append(putAttemptCount);
        sb.append(", percentage=").append(percentage);
        sb.append(", channelSize=").append(channelSize);
        sb.append(", takeSuccessCount=").append(takeSuccessCount);
        sb.append(", takeAttemptCount=").append(takeAttemptCount);
        sb.append(", runState=").append(runState);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", channelCapacity=").append(channelCapacity);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_flume_channelInfo
     *
     * @mbg.generated
     */
    @Override
    public ChannelInfo clone() throws CloneNotSupportedException {
        return (ChannelInfo) super.clone();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_flume_channelInfo
     *
     * @mbg.generated
     */
    public static ChannelInfo.Builder builder() {
        return new ChannelInfo.Builder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_flume_channelInfo
     *
     * @mbg.generated
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        private ChannelInfo obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        public Builder() {
            this.obj = new ChannelInfo();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_flume_channelInfo.id
         *
         * @param id the value for t_flume_channelInfo.id
         *
         * @mbg.generated
         */
        public Builder id(String id) {
            obj.setId(id);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_flume_channelInfo.component_name
         *
         * @param componentName the value for t_flume_channelInfo.component_name
         *
         * @mbg.generated
         */
        public Builder componentName(String componentName) {
            obj.setComponentName(componentName);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_flume_channelInfo.start_time
         *
         * @param startTime the value for t_flume_channelInfo.start_time
         *
         * @mbg.generated
         */
        public Builder startTime(Date startTime) {
            obj.setStartTime(startTime);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_flume_channelInfo.ip_addr
         *
         * @param ipAddr the value for t_flume_channelInfo.ip_addr
         *
         * @mbg.generated
         */
        public Builder ipAddr(String ipAddr) {
            obj.setIpAddr(ipAddr);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_flume_channelInfo.put_success_count
         *
         * @param putSuccessCount the value for t_flume_channelInfo.put_success_count
         *
         * @mbg.generated
         */
        public Builder putSuccessCount(String putSuccessCount) {
            obj.setPutSuccessCount(putSuccessCount);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_flume_channelInfo.put_attempt_count
         *
         * @param putAttemptCount the value for t_flume_channelInfo.put_attempt_count
         *
         * @mbg.generated
         */
        public Builder putAttemptCount(String putAttemptCount) {
            obj.setPutAttemptCount(putAttemptCount);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_flume_channelInfo.percentage
         *
         * @param percentage the value for t_flume_channelInfo.percentage
         *
         * @mbg.generated
         */
        public Builder percentage(String percentage) {
            obj.setPercentage(percentage);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_flume_channelInfo.channel_size
         *
         * @param channelSize the value for t_flume_channelInfo.channel_size
         *
         * @mbg.generated
         */
        public Builder channelSize(String channelSize) {
            obj.setChannelSize(channelSize);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_flume_channelInfo.take_success_count
         *
         * @param takeSuccessCount the value for t_flume_channelInfo.take_success_count
         *
         * @mbg.generated
         */
        public Builder takeSuccessCount(String takeSuccessCount) {
            obj.setTakeSuccessCount(takeSuccessCount);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_flume_channelInfo.take_attempt_count
         *
         * @param takeAttemptCount the value for t_flume_channelInfo.take_attempt_count
         *
         * @mbg.generated
         */
        public Builder takeAttemptCount(String takeAttemptCount) {
            obj.setTakeAttemptCount(takeAttemptCount);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_flume_channelInfo.run_state
         *
         * @param runState the value for t_flume_channelInfo.run_state
         *
         * @mbg.generated
         */
        public Builder runState(Integer runState) {
            obj.setRunState(runState);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_flume_channelInfo.delete_flag
         *
         * @param deleteFlag the value for t_flume_channelInfo.delete_flag
         *
         * @mbg.generated
         */
        public Builder deleteFlag(Integer deleteFlag) {
            obj.setDeleteFlag(deleteFlag);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_flume_channelInfo.channel_capacity
         *
         * @param channelCapacity the value for t_flume_channelInfo.channel_capacity
         *
         * @mbg.generated
         */
        public Builder channelCapacity(String channelCapacity) {
            obj.setChannelCapacity(channelCapacity);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_flume_channelInfo.create_time
         *
         * @param createTime the value for t_flume_channelInfo.create_time
         *
         * @mbg.generated
         */
        public Builder createTime(Date createTime) {
            obj.setCreateTime(createTime);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_flume_channelInfo.modify_time
         *
         * @param modifyTime the value for t_flume_channelInfo.modify_time
         *
         * @mbg.generated
         */
        public Builder modifyTime(Date modifyTime) {
            obj.setModifyTime(modifyTime);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        public ChannelInfo build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table t_flume_channelInfo
     *
     * @mbg.generated
     */
    public enum Column {
        id("id", "id", "VARCHAR", false),
        componentName("component_name", "componentName", "VARCHAR", false),
        startTime("start_time", "startTime", "TIMESTAMP", false),
        ipAddr("ip_addr", "ipAddr", "VARCHAR", false),
        putSuccessCount("put_success_count", "putSuccessCount", "VARCHAR", false),
        putAttemptCount("put_attempt_count", "putAttemptCount", "VARCHAR", false),
        percentage("percentage", "percentage", "VARCHAR", false),
        channelSize("channel_size", "channelSize", "VARCHAR", false),
        takeSuccessCount("take_success_count", "takeSuccessCount", "VARCHAR", false),
        takeAttemptCount("take_attempt_count", "takeAttemptCount", "VARCHAR", false),
        runState("run_state", "runState", "INTEGER", false),
        deleteFlag("delete_flag", "deleteFlag", "INTEGER", false),
        channelCapacity("channel_capacity", "channelCapacity", "VARCHAR", false),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        modifyTime("modify_time", "modifyTime", "TIMESTAMP", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_flume_channelInfo
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
         * This method corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_flume_channelInfo
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
         * This method corresponds to the database table t_flume_channelInfo
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
         * This method corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table t_flume_channelInfo
     *
     * @mbg.generated
     */
    public enum RunState {
        RUNING(new Integer("1"), "已开启"),
        CLOSE(new Integer("2"), "关闭"),
        PORTMONITORINGEXCEPTION(new Integer("3"), "端口监控异常");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        private final Integer value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        RunState(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        public Integer getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        public Integer value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_flume_channelInfo
         *
         * @mbg.generated
         */
        public String getName() {
            return this.name;
        }
    }
}