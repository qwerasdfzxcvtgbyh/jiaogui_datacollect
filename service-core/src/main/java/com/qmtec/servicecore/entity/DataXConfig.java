package com.qmtec.servicecore.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DataXConfig implements Cloneable, Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_datax_config.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_datax_config.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_datax_config.code
     *
     * @mbg.generated
     */
    private String code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_datax_config.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_datax_config.modify_time
     *
     * @mbg.generated
     */
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_datax_config.startup_time
     *
     * @mbg.generated
     */
    private Date startupTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_datax_config.json_file_path
     *
     * @mbg.generated
     */
    private String jsonFilePath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_datax_config.json_file_name
     *
     * @mbg.generated
     */
    private String jsonFileName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_datax_config.pythonscript_path
     *
     * @mbg.generated
     */
    private String pythonscriptPath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_datax_config.pythonscript__file_name
     *
     * @mbg.generated
     */
    private String pythonscriptFileName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_datax_config.server_ip
     *
     * @mbg.generated
     */
    private String serverIp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_datax_config.server_port
     *
     * @mbg.generated
     */
    private String serverPort;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_datax_config.runState
     *
     * @mbg.generated
     */
    private Integer runstate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_datax_config.remark
     *
     * @mbg.generated
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_datax_config.json_file_content
     *
     * @mbg.generated
     */
    private String jsonFileContent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_datax_config.pythonscript_content
     *
     * @mbg.generated
     */
    private String pythonscriptContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_datax_config
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_datax_config.id
     *
     * @return the value of t_datax_config.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_datax_config.id
     *
     * @param id the value for t_datax_config.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_datax_config.name
     *
     * @return the value of t_datax_config.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_datax_config.name
     *
     * @param name the value for t_datax_config.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_datax_config.code
     *
     * @return the value of t_datax_config.code
     *
     * @mbg.generated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_datax_config.code
     *
     * @param code the value for t_datax_config.code
     *
     * @mbg.generated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_datax_config.create_time
     *
     * @return the value of t_datax_config.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_datax_config.create_time
     *
     * @param createTime the value for t_datax_config.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_datax_config.modify_time
     *
     * @return the value of t_datax_config.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_datax_config.modify_time
     *
     * @param modifyTime the value for t_datax_config.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_datax_config.startup_time
     *
     * @return the value of t_datax_config.startup_time
     *
     * @mbg.generated
     */
    public Date getStartupTime() {
        return startupTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_datax_config.startup_time
     *
     * @param startupTime the value for t_datax_config.startup_time
     *
     * @mbg.generated
     */
    public void setStartupTime(Date startupTime) {
        this.startupTime = startupTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_datax_config.json_file_path
     *
     * @return the value of t_datax_config.json_file_path
     *
     * @mbg.generated
     */
    public String getJsonFilePath() {
        return jsonFilePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_datax_config.json_file_path
     *
     * @param jsonFilePath the value for t_datax_config.json_file_path
     *
     * @mbg.generated
     */
    public void setJsonFilePath(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath == null ? null : jsonFilePath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_datax_config.json_file_name
     *
     * @return the value of t_datax_config.json_file_name
     *
     * @mbg.generated
     */
    public String getJsonFileName() {
        return jsonFileName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_datax_config.json_file_name
     *
     * @param jsonFileName the value for t_datax_config.json_file_name
     *
     * @mbg.generated
     */
    public void setJsonFileName(String jsonFileName) {
        this.jsonFileName = jsonFileName == null ? null : jsonFileName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_datax_config.pythonscript_path
     *
     * @return the value of t_datax_config.pythonscript_path
     *
     * @mbg.generated
     */
    public String getPythonscriptPath() {
        return pythonscriptPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_datax_config.pythonscript_path
     *
     * @param pythonscriptPath the value for t_datax_config.pythonscript_path
     *
     * @mbg.generated
     */
    public void setPythonscriptPath(String pythonscriptPath) {
        this.pythonscriptPath = pythonscriptPath == null ? null : pythonscriptPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_datax_config.pythonscript__file_name
     *
     * @return the value of t_datax_config.pythonscript__file_name
     *
     * @mbg.generated
     */
    public String getPythonscriptFileName() {
        return pythonscriptFileName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_datax_config.pythonscript__file_name
     *
     * @param pythonscriptFileName the value for t_datax_config.pythonscript__file_name
     *
     * @mbg.generated
     */
    public void setPythonscriptFileName(String pythonscriptFileName) {
        this.pythonscriptFileName = pythonscriptFileName == null ? null : pythonscriptFileName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_datax_config.server_ip
     *
     * @return the value of t_datax_config.server_ip
     *
     * @mbg.generated
     */
    public String getServerIp() {
        return serverIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_datax_config.server_ip
     *
     * @param serverIp the value for t_datax_config.server_ip
     *
     * @mbg.generated
     */
    public void setServerIp(String serverIp) {
        this.serverIp = serverIp == null ? null : serverIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_datax_config.server_port
     *
     * @return the value of t_datax_config.server_port
     *
     * @mbg.generated
     */
    public String getServerPort() {
        return serverPort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_datax_config.server_port
     *
     * @param serverPort the value for t_datax_config.server_port
     *
     * @mbg.generated
     */
    public void setServerPort(String serverPort) {
        this.serverPort = serverPort == null ? null : serverPort.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_datax_config.runState
     *
     * @return the value of t_datax_config.runState
     *
     * @mbg.generated
     */
    public Integer getRunstate() {
        return runstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_datax_config.runState
     *
     * @param runstate the value for t_datax_config.runState
     *
     * @mbg.generated
     */
    public void setRunstate(Integer runstate) {
        this.runstate = runstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_datax_config.remark
     *
     * @return the value of t_datax_config.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_datax_config.remark
     *
     * @param remark the value for t_datax_config.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_datax_config.json_file_content
     *
     * @return the value of t_datax_config.json_file_content
     *
     * @mbg.generated
     */
    public String getJsonFileContent() {
        return jsonFileContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_datax_config.json_file_content
     *
     * @param jsonFileContent the value for t_datax_config.json_file_content
     *
     * @mbg.generated
     */
    public void setJsonFileContent(String jsonFileContent) {
        this.jsonFileContent = jsonFileContent == null ? null : jsonFileContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_datax_config.pythonscript_content
     *
     * @return the value of t_datax_config.pythonscript_content
     *
     * @mbg.generated
     */
    public String getPythonscriptContent() {
        return pythonscriptContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_datax_config.pythonscript_content
     *
     * @param pythonscriptContent the value for t_datax_config.pythonscript_content
     *
     * @mbg.generated
     */
    public void setPythonscriptContent(String pythonscriptContent) {
        this.pythonscriptContent = pythonscriptContent == null ? null : pythonscriptContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_datax_config
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
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", startupTime=").append(startupTime);
        sb.append(", jsonFilePath=").append(jsonFilePath);
        sb.append(", jsonFileName=").append(jsonFileName);
        sb.append(", pythonscriptPath=").append(pythonscriptPath);
        sb.append(", pythonscriptFileName=").append(pythonscriptFileName);
        sb.append(", serverIp=").append(serverIp);
        sb.append(", serverPort=").append(serverPort);
        sb.append(", runstate=").append(runstate);
        sb.append(", remark=").append(remark);
        sb.append(", jsonFileContent=").append(jsonFileContent);
        sb.append(", pythonscriptContent=").append(pythonscriptContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_datax_config
     *
     * @mbg.generated
     */
    @Override
    public DataXConfig clone() throws CloneNotSupportedException {
        return (DataXConfig) super.clone();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_datax_config
     *
     * @mbg.generated
     */
    public static DataXConfig.Builder builder() {
        return new DataXConfig.Builder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_datax_config
     *
     * @mbg.generated
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        private DataXConfig obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        public Builder() {
            this.obj = new DataXConfig();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_datax_config.id
         *
         * @param id the value for t_datax_config.id
         *
         * @mbg.generated
         */
        public Builder id(String id) {
            obj.setId(id);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_datax_config.name
         *
         * @param name the value for t_datax_config.name
         *
         * @mbg.generated
         */
        public Builder name(String name) {
            obj.setName(name);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_datax_config.code
         *
         * @param code the value for t_datax_config.code
         *
         * @mbg.generated
         */
        public Builder code(String code) {
            obj.setCode(code);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_datax_config.create_time
         *
         * @param createTime the value for t_datax_config.create_time
         *
         * @mbg.generated
         */
        public Builder createTime(Date createTime) {
            obj.setCreateTime(createTime);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_datax_config.modify_time
         *
         * @param modifyTime the value for t_datax_config.modify_time
         *
         * @mbg.generated
         */
        public Builder modifyTime(Date modifyTime) {
            obj.setModifyTime(modifyTime);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_datax_config.startup_time
         *
         * @param startupTime the value for t_datax_config.startup_time
         *
         * @mbg.generated
         */
        public Builder startupTime(Date startupTime) {
            obj.setStartupTime(startupTime);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_datax_config.json_file_path
         *
         * @param jsonFilePath the value for t_datax_config.json_file_path
         *
         * @mbg.generated
         */
        public Builder jsonFilePath(String jsonFilePath) {
            obj.setJsonFilePath(jsonFilePath);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_datax_config.json_file_name
         *
         * @param jsonFileName the value for t_datax_config.json_file_name
         *
         * @mbg.generated
         */
        public Builder jsonFileName(String jsonFileName) {
            obj.setJsonFileName(jsonFileName);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_datax_config.pythonscript_path
         *
         * @param pythonscriptPath the value for t_datax_config.pythonscript_path
         *
         * @mbg.generated
         */
        public Builder pythonscriptPath(String pythonscriptPath) {
            obj.setPythonscriptPath(pythonscriptPath);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_datax_config.pythonscript__file_name
         *
         * @param pythonscriptFileName the value for t_datax_config.pythonscript__file_name
         *
         * @mbg.generated
         */
        public Builder pythonscriptFileName(String pythonscriptFileName) {
            obj.setPythonscriptFileName(pythonscriptFileName);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_datax_config.server_ip
         *
         * @param serverIp the value for t_datax_config.server_ip
         *
         * @mbg.generated
         */
        public Builder serverIp(String serverIp) {
            obj.setServerIp(serverIp);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_datax_config.server_port
         *
         * @param serverPort the value for t_datax_config.server_port
         *
         * @mbg.generated
         */
        public Builder serverPort(String serverPort) {
            obj.setServerPort(serverPort);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_datax_config.runState
         *
         * @param runstate the value for t_datax_config.runState
         *
         * @mbg.generated
         */
        public Builder runstate(Integer runstate) {
            obj.setRunstate(runstate);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_datax_config.remark
         *
         * @param remark the value for t_datax_config.remark
         *
         * @mbg.generated
         */
        public Builder remark(String remark) {
            obj.setRemark(remark);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_datax_config.json_file_content
         *
         * @param jsonFileContent the value for t_datax_config.json_file_content
         *
         * @mbg.generated
         */
        public Builder jsonFileContent(String jsonFileContent) {
            obj.setJsonFileContent(jsonFileContent);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_datax_config.pythonscript_content
         *
         * @param pythonscriptContent the value for t_datax_config.pythonscript_content
         *
         * @mbg.generated
         */
        public Builder pythonscriptContent(String pythonscriptContent) {
            obj.setPythonscriptContent(pythonscriptContent);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        public DataXConfig build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table t_datax_config
     *
     * @mbg.generated
     */
    public enum Column {
        id("id", "id", "VARCHAR", false),
        name("name", "name", "VARCHAR", true),
        code("code", "code", "VARCHAR", false),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        modifyTime("modify_time", "modifyTime", "TIMESTAMP", false),
        startupTime("startup_time", "startupTime", "TIMESTAMP", false),
        jsonFilePath("json_file_path", "jsonFilePath", "VARCHAR", false),
        jsonFileName("json_file_name", "jsonFileName", "VARCHAR", false),
        pythonscriptPath("pythonscript_path", "pythonscriptPath", "VARCHAR", false),
        pythonscriptFileName("pythonscript__file_name", "pythonscriptFileName", "VARCHAR", false),
        serverIp("server_ip", "serverIp", "VARCHAR", false),
        serverPort("server_port", "serverPort", "VARCHAR", false),
        runstate("runState", "runstate", "INTEGER", false),
        remark("remark", "remark", "VARCHAR", false),
        jsonFileContent("json_file_content", "jsonFileContent", "LONGVARCHAR", false),
        pythonscriptContent("pythonscript_content", "pythonscriptContent", "LONGVARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_datax_config
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
         * This method corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_datax_config
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
         * This method corresponds to the database table t_datax_config
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
         * This method corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table t_datax_config
     *
     * @mbg.generated
     */
    public enum Runstate {
        RUNING(new Integer("1"), "已开启"),
        UNOPENED(new Integer("2"), "未开启"),
        PUSHED(new Integer("3"), "已接收请求"),
        STARTING(new Integer("4"), "正在启动"),
        CREATEPROFILE(new Integer("5"), "创建配置文件"),
        CREATESCRIPTS(new Integer("6"), "创建脚本"),
        EXECSCRIPTS(new Integer("7"), "执行脚本"),
        FAIL(new Integer("8"), "失败"),
        PUSHING(new Integer("9"), "开始推送"),
        EXCEPTION(new Integer("10"), "启动异常"),
        RESTART(new Integer("11"), "重启"),
        RESTARTING(new Integer("12"), "正在重启");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        private final Integer value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        Runstate(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        public Integer getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        public Integer value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_datax_config
         *
         * @mbg.generated
         */
        public String getName() {
            return this.name;
        }
    }
}