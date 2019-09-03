package com.qmtec.dataSource.Enum;

public enum DbType {

    // rds
    TYPE_MYSQL(new Integer("1"),"mysql"),
    /// file
    TYPE_HDFS(new Integer("2"),"hdfs");
    // nosql

    private final Integer value;

    private final String type;

    public Integer getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    DbType(Integer value, String type) {
        this.value = value;
        this.type = type;
    }
}
